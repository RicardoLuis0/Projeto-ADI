package com.ricardoluis.db;

import java.sql.SQLException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ricardoluis.db.jdbc.DBAccess;

@Path("DB")
public class DB {

	@Path("add_user")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(@FormParam("name") String user_name,@FormParam("birthdate") String user_birthdate) {
		try {
			return Response.ok(new DBAccess().addUser(user_name,user_birthdate)?"true":"false").build();
		}catch(SQLException e) {
			if(e.getMessage().contains("Duplicate entry"))return Response.ok("false").build();
			System.out.println("SQL Exception: "+e.getMessage());
			return Response.serverError().build();
		}catch(IllegalArgumentException e) {
			return Response.ok("{\"error\":\""+e.getMessage().replace("\"","\\\"")+"\"}").build();
		}
	}

	@Path("update_user_birthdate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUserBirthdate(@FormParam("name") String user_name,@FormParam("birthdate") String user_birthdate) {
		try {
			return Response.ok(new DBAccess().updateUserBirthdate(user_name,user_birthdate)?"true":"false").build();
		}catch(SQLException e) {
			System.out.println("SQL Exception: "+e.getMessage());
			return Response.serverError().build();
		}catch(IllegalArgumentException e) {
			return Response.ok("{\"error\":\""+e.getMessage().replace("\"","\\\"")+"\"}").build();
		}
	}

	@Path("delete_user")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@FormParam("name") String user_name) {
		try {
			return Response.ok(new DBAccess().deleteUser(user_name)?"true":"false").build();
		}catch(SQLException e) {
			System.out.println("SQL Exception: "+e.getMessage());
			return Response.serverError().build();
		}catch(IllegalArgumentException e) {
			return Response.ok("{\"error\":\""+e.getMessage().replace("\"","\\\"")+"\"}").build();
		}
	}

	@Path("search")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchUsers(@QueryParam("q") String search_params) {
		try {
			return Response.ok(new DBAccess().searchUsers(search_params)).build();
		}catch(SQLException e) {
			System.out.println("SQL Exception: "+e.getMessage());
			return Response.serverError().build();
		}catch(IllegalArgumentException e) {
			return Response.ok("{\"error\":\""+e.getMessage().replace("\"","\\\"")+"\"}").build();
		}
	}

}
