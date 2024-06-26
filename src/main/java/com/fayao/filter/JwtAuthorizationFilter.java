package com.fayao.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fayao.exception.JwtTokenNotFoundException;
import com.fayao.model.entity.LogSysAlertTrack;
import com.fayao.model.response.ApiErrorResponse;
import com.fayao.service.LogService;
import com.fayao.utils.HttpTools;
import com.fayao.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Order(2)
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	private final LogService logService;

	private final JwtUtil jwtUtil;

	private final ObjectMapper objectMapper;


	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return !HttpTools.isApiRequest();
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

        if(validateToken(request,response)){
			filterChain.doFilter(request, response);
		}

	}

	private boolean validateToken(HttpServletRequest request,HttpServletResponse response) throws IOException {
		boolean isSuccess = false;
		String jwtValidErrorMsg;
		try {

			String token = jwtUtil.resolveToken(request);
			if(Objects.isNull(token)){
				throw new JwtTokenNotFoundException("Token not found");
			}
			Claims claims = jwtUtil.parseJwtClaims(token);
			isSuccess = true;

		} catch (Exception e) {

			LogSysAlertTrack logSysAlertTrack = LogSysAlertTrack.builder().userIp(HttpTools.getIp())
					.alertUri(HttpTools.getQueryUri()).alertMsg(ExceptionUtils.getStackTrace(e)).mid("mid").build();
			logService.loggingAlert(logSysAlertTrack);

			if (e instanceof JwtTokenNotFoundException) {
				log.error("Jwt token not found : {}", e.getMessage());
			} else if (e instanceof ExpiredJwtException) {
				log.error("Jwt token expired : {}", e.getMessage());
			} else {
				log.error("Jwt token not valid: {}", e.getMessage());
			}
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			objectMapper.writeValue(response.getOutputStream(), ApiErrorResponse.fail(ExceptionUtils.getStackTrace(e), HttpStatus.UNAUTHORIZED));
		}
		return isSuccess;
	}
}
