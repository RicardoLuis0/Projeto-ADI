package com.ricardoluis.game;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ricardoluis.game.command.Command;

@Path("run")
public class GameMain {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response executeCommand(Command cmd) {
		System.out.println(cmd);
		return Response.serverError().build();
	}
}
