package com.fundodo.fayaospace.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "fayaospace API",
                version = "1.0.0",
                description = "fayaospaceApiLoggingFilter.java API"
        ),
        security = @io.swagger.v3.oas.annotations.security.SecurityRequirement(
                name = "Bearer Authentication"
        )
)
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, scheme = "bearer", in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {

}
