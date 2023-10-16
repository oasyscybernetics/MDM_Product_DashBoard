package com.oasys.mdm_android_product.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Oasys", email = "contact@oasys.co", url = "https://www.oasys.co/"), description = "MDM API", title = "MDM Android", version = "1.0", license = @License(name = "License of API", url = "API license URL"), termsOfService = "Terms of service"),
		/*
		 * servers = {
		 * 
		 * @Server( description = "Local ENV", url = "http://localhost:9191" ),
		 * 
		 * @Server( description = "PROD ENV", url = "http://localhost:9191" ) },
		 */
		security = { @SecurityRequirement(name = "bearerAuth") }

)
@SecurityScheme(name = "bearerAuth", description = "JWT auth Description", scheme = "bearer", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", in = SecuritySchemeIn.HEADER)
@Schema(minLength = 1, maxLength = 35000)
public class OpenApiConfig {

}
