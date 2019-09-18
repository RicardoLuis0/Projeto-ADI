package com.ricardoluis.game;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ricardoluis.game.command.Command;
import com.ricardoluis.game.player.Player;
import com.ricardoluis.game.player.PlayerManager;
import com.ricardoluis.game.world.WorldManager;

@Path("run")
public class GameMain {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response executeCommand(Command cmd) {
		System.out.println(cmd);
		switch(cmd.getCommand()) {
		case "create":
			return commandCreate(cmd);
		case "spawn":
			return commandSpawn(cmd);
		case "scan":
			return commandScan(cmd);
		case "look":
			return commandLook(cmd);
		case "move":
			return commandMove(cmd);
		default:
			return Response.serverError().build();
		}
	}

	@Path("getWorld")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorld() {
		return Response.ok(WorldManager.getInstance().getWorld()).build();
	}

	private Response commandCreate(Command cmd) {
		System.out.println("create");
		PlayerManager pm=PlayerManager.getInstance();
		return Response.ok(pm.registerPlayer(cmd.getPlayerName())?"true":"false").build();//create new player ( as spectator by default )
	}

	private Response commandSpawn(Command cmd) {
		//TODO
		return Response.serverError().build();
	}

	private Response commandScan(Command cmd) {
		
		return Response.serverError().build();
	}

	private Response commandLook(Command cmd) {
		Player p=PlayerManager.getInstance().findPlayer(cmd.getPlayerName());
		if(p==null) {
			return Response.ok("{\"Error\":\"No such player\"}").build();
		}
		int range=-1;
		try {
			if(cmd.getArgs().size()>1) {
				return Response.ok("{\"Error\":\"Too many arguments\"}").build();
			}else if(cmd.getArgs().size()==0) {
				return Response.ok("{\"Error\":\"Too few arguments\"}").build();
			}else {
				if(!cmd.getArgs().get(0).getName().equals("range")) {
					return Response.ok("{\"Error\":\"Invalid Argument '"+cmd.getArgs().get(0).getName().replace("\"","\\\"")+"'\"}").build();
				}else {
					range=Integer.parseInt(cmd.getArgs().get(0).getValue());
					if(range<1) {
						return Response.ok("{\"Error\":\"'"+range+"' too small\"}").build();
					}
				}
			}
		}catch(NumberFormatException e) {
			return Response.ok("{\"Error\":\"'"+cmd.getArgs().get(0).getValue().replace("\"","\\\"")+"' not a number\"}").build();
		}
		return Response.ok(p.look(range)).build();
	}

	private Response commandMove(Command cmd) {
		Player p=PlayerManager.getInstance().findPlayer(cmd.getPlayerName());
		if(p==null) {
			return Response.ok("{\"Error\":\"No such player\"}").build();
		}
		int direction=-1;
		if(cmd.getArgs().size()>1) {
			return Response.ok("{\"Error\":\"Too many arguments\"}").build();
		}else if(cmd.getArgs().size()==0) {
			return Response.ok("{\"Error\":\"Too few arguments\"}").build();
		}else {
			if(!cmd.getArgs().get(0).getName().equals("direction")) {
				return Response.ok("{\"Error\":\"Invalid Argument '"+cmd.getArgs().get(0).getName().replace("\"","\\\"")+"'\"}").build();
			}else {
				switch(cmd.getArgs().get(0).getValue()) {
				case "up":
					direction=2;
					break;
				case "down":
					direction=1;
					break;
				case "left":
					direction=3;
					break;
				case "right":
					direction=0;
					break;
				default:
					return Response.ok("{\"Error\":\"Invalid Direction '"+cmd.getArgs().get(0).getValue().replace("\"","\\\"")+"'\"}").build();
				}
			}
		}
		return Response.ok(p.move(direction)).build();
	}
}
