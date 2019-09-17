package com.ricardoluis.auth;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("auth")
public class AuthResource {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response Auth(@FormParam("login") String login,@FormParam("pass") String pass) {
		System.out.println("Auth\n\nlogin: "+login+"\npass: "+pass);
		return Response.serverError().build();
	}
}
