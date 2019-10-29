package com.ricardoluis.game.world;

import java.util.ArrayList;
import java.util.Random;

import org.spongepowered.noise.Noise;

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

	public ArrayList<ArrayList<WorldTile>> getWorld() {
		return world;
	}
	
	public boolean validXY(int x,int y) {
		return (x>=0&&y>=0&&x<sizex&&y<sizey);
	}

	public WorldTile getXY(int x,int y) {
		return validXY(x,y)?world.get(x).get(y):new VoidTile();
	}

	public ArrayList<ArrayList<WorldTile>> look(int xmin,int xmax,int ymin,int ymax){
		ArrayList<ArrayList<WorldTile>> output=new ArrayList<>();
		for(int i=xmin;i<xmax;i++) {
			output.add(new ArrayList<>());
			for(int j=ymin;j<ymax;j++) {
				output.get(i-xmin).add(getXY(i,j));
			}
		}
		return output;
	}

	public ArrayList<ArrayList<WorldTile>> look(int x,int y,int range){
		int xmin=x-range;
		int xmax=x+range;
		int ymin=y-range;
		int ymax=y+range;
		return look(xmin,xmax,ymin,ymax);
	}
}
