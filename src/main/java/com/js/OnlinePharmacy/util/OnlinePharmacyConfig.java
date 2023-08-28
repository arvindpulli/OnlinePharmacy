package com.js.OnlinePharmacy.util;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OnlinePharmacyConfig {

	public OpenAPI swaggerDocOpenAPI() {
		
		Server developmentServer=new Server();
		developmentServer.setUrl("http://localhost:8080");
		developmentServer.setDescription("This server if for development");
		
		Server productionServer=new Server();
		productionServer.setUrl("http://localhost:8080");
		productionServer.setDescription("This server is for production");
		
		Contact contact=new Contact();
		contact.setName("Online Pharmacy");
		contact.setEmail("help.onlinepharmacy.in");
		contact.setUrl("https://cdnjs.com/libraries/font-aweso");
		
		License license=new License();
		license.setName(null);
		license.setUrl(null);
		
		Info info=new Info();
		info.title("Online Pharmacy");
		info.version("1.0");
		info.contact(contact);
		info.description("Designed for Online Medicine Booking");
		
		OpenAPI openApi=new OpenAPI();
		openApi.info(info);
		openApi.servers(Arrays.asList(developmentServer,productionServer));
		return openApi;
		
	}
}
