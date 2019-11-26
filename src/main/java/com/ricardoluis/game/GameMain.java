package com.ricardoluis.game;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ricardoluis.game.player.Player;
import com.ricardoluis.game.player.PlayerManager;
import com.ricardoluis.game.player.exceptions.PlayerCommandException;
import com.ricardoluis.game.world.WorldManager;

@Path("cmd")
public class GameMain {

	@Path("getWorld")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getWorld() {
		return Response.ok(WorldManager.getInstance().getWorld()).build();
	}

	@Path("look")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response look(@FormParam("player") String player_name,@FormParam("range") int range) {
		Player p=PlayerManager.getInstance().findPlayer(player_name);
		if(p==null) {
			return Response.ok("{\"error\":\"No such player\"}").build();
		}
		if(range<1) {
			return Response.ok("{\"error\":\"'"+range+"' too small\"}").build();
		}
		return Response.ok(p.look(range)).build();
	}

	@Path("create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@POST
	public Response commandCreate(@FormParam("player") String player_name) {
		PlayerManager pm=PlayerManager.getInstance();
		return Response.ok(pm.registerPlayer(player_name)?"true":"false").build();//create new player ( as spectator by default )
	}

	@Path("move")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response commandMove(@FormParam("player") String player_name,@FormParam("direction") String direction) {
		Player p=PlayerManager.getInstance().findPlayer(player_name);
		if(p==null) {
			return Response.ok("{\"error\":\"No such player\"}").build();
		}
		return Response.ok(p.move(direction)?"true":"false").build();
	}

	@Path("spawn")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response commandSpawn(@FormParam("player") String player_name) {
		Player p=PlayerManager.getInstance().findPlayer(player_name);
		if(p==null) {
			return Response.ok("{\"error\":\"No such player\"}").build();
		}
		try {
			p.spawn();
			return Response.ok("true").build();
		}catch(PlayerCommandException e) {
			return Response.ok(e.getMessage()).build();
		}
	}

	@Path("scan")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response commandScan(@FormParam("player") String player_name,@FormParam("x") int x,@FormParam("y") int y) {
		Player p=PlayerManager.getInstance().findPlayer(player_name);
		if(p==null) {
			return Response.ok("{\"error\":\"No such player\"}").build();
		}
		try {
			return Response.ok(p.scan(x,y)).build();
		}catch(PlayerCommandException e) {
			return Response.ok(e.getMessage()).build();
		}
	}

	@Path("status")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response commandStatus(@FormParam("player") String player_name) {
		Player p=PlayerManager.getInstance().findPlayer(player_name);
		if(p==null) {
			return Response.ok("{\"error\":\"No such player\"}").build();
		} else {
			return Response.ok( "{\"status\":"+p.getStatus()+"}").build();
		}
	}

	@Path("attack")
	@Produces(MediaType.APPLICATION_JSON)
	@POST
	public Response commandAttack(@FormParam("player") String player_name,@FormParam("direction") String direction) {
		Player p=PlayerManager.getInstance().findPlayer(player_name);
		if(p==null) {
			return Response.ok("{\"error\":\"No such player\"}").build();
		}
		return Response.serverError().build();
	}
}
