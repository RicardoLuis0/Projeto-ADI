package com.ricardoluis.game.world;

import java.util.ArrayList;
import java.util.Random;

import org.spongepowered.noise.Noise;

import com.ricardoluis.game.player.PlayerManager;
import com.ricardoluis.game.world.tiles.GrassTile;
import com.ricardoluis.game.world.tiles.StoneTile;
import com.ricardoluis.game.world.tiles.VoidTile;
import com.ricardoluis.game.world.tiles.WallTile;
import com.ricardoluis.game.world.tiles.WorldTile;

public class WorldManager {
	private static WorldManager instance=new WorldManager();
	private static final int tile_values=30;
	public static final int sizex=100;
	public static final int sizey=100;

	private WorldManager() {
		generateWorld();
	}

	public static WorldManager getInstance() {
		return instance;
	}

	private ArrayList<ArrayList<WorldTile>> world;

	private WorldTile generateTile(int value) {
		if(value<5) { // 0 - 4 wall
			return new WallTile();
		}else if(value<15) { // 5 - 14 stone
			return new StoneTile();
		}else {// 15 - ~ grass
			return new GrassTile();
		}
	}

	private void generateWorld(){
		int seed=new Random().nextInt();
		world=new ArrayList<>();
		for(int i=0;i<sizex;i++) {
			world.add(new ArrayList<>());
			for(int j=0;j<sizey;j++) {
				world.get(i).add(generateTile((int)(((Noise.intValueNoise3D(i, j, 0, seed)/(double)2147483647)*tile_values)+0.5)));
			}
		}
	}

	public ArrayList<ArrayList<WorldLocation>> getWorld() {
		return look(0,sizex,0,sizey);
	}
	public boolean validXY(int x,int y) {
		return (x>=0&&y>=0&&x<sizex&&y<sizey);
	}
	public boolean validXY(int x,int y,boolean collide) {
		if(x>=0&&y>=0&&x<sizex&&y<sizey) {
			if(collide) {
				class BoolContainer{
					public boolean b;
					public BoolContainer(boolean b) {
						this.b=b;
					}
				}
				BoolContainer has_player=new BoolContainer(false);
				PlayerManager.getInstance().forEach((p)->{
					if(p.isAlive()) {
						int px=p.getX();
						int py=p.getY();
						if(px==x&&py==y) {
							has_player.b=true;
						}
					}
				});
				return !(has_player.b||getXY(x,y).isObstacle());
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public WorldTile getXY(int x,int y) {
		return validXY(x,y)?world.get(x).get(y):new VoidTile();
	}

	public ArrayList<ArrayList<WorldLocation>> look(int xmin,int xmax,int ymin,int ymax){
		ArrayList<ArrayList<WorldLocation>> output=new ArrayList<>();
		for(int i=xmin;i<xmax;i++) {
			output.add(new ArrayList<>());
			for(int j=ymin;j<ymax;j++) {
				output.get(i-xmin).add(new WorldLocation(getXY(i,j)));
			}
		}
		PlayerManager.getInstance().forEach((p)->{
			if(p.isAlive()) {
				int px=p.getX();
				int py=p.getY();
				if(px>=xmin&&px<xmax&&py>=ymin&&py<ymax) {
					output.get(px-xmin).get(py-ymin).player=p;
				}
			}
		});
		return output;
	}

	public ArrayList<ArrayList<WorldLocation>> look(int x,int y,int range){
		int xmin=x-range;
		int xmax=x+range;
		int ymin=y-range;
		int ymax=y+range;
		return look(xmin,xmax,ymin,ymax);
	}
}
