package com.fundodo.fayaospace.utils;

import com.fundodo.fayaospace.constant.ApiConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.access-token-validity}")
    private Long accessTokenValidity;

    private JwtParser jwtParser;

    @PostConstruct
    private void init() {
        this.jwtParser = Jwts.parser().setSigningKey(secretKey);
    }


    public String createToken(String userName) {
        Date tokenValidity = new Date(
                System.currentTimeMillis() + TimeUnit.HOURS.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setSubject(userName)
                .setExpiration(tokenValidity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        String token = resolveToken(req);
        return parseJwtClaims(token);
    }

    public Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(ApiConstant.TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(ApiConstant.TOKEN_PREFIX)) {
            return bearerToken.substring(ApiConstant.TOKEN_PREFIX.length());
        }

        return null;
    }


}
