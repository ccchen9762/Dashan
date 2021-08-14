package World;

import java.awt.Window;

import java.io.FileReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import FJ104502033.Resources;

public class world{
	public static Image[][] map;
	public static int WIDTH;
	public static int HEIGHT;
	
	public static void render(float xRender, float yRender){
		int offset =2;
		int xStart = (int) (xRender/Tile.SIZE) - offset;
		int yStart = (int) (yRender/Tile.SIZE) - offset;
		int xEnd = (Window.WIDTH / Tile.SIZE) + xStart + (offset * 2);
		int yEnd = (Window.HEIGHT / Tile.SIZE) + yStart + (offset * 2);
		
		for(int x=xStart ; x<xEnd ; x++){
			for(int y=yStart ; y<yEnd ; y++){
				if(mapTile(x, y)){
					map[x][y].draw(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
				}
			}
		}
	}
	
	public static void load(String path)throws Exception{
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(path));
		JSONObject jObj = (JSONObject)obj; 
		
		JSONArray layers = (JSONArray) jObj.get("layers");
		int amount = layers.size();
		
		for(int i=0 ; i<amount ; i++){
			JSONObject layer = (JSONObject) layers.get(i);
			String type = (String) layer.get("name");
			
			if(type.equals("map")){
				WIDTH = (int) ((long)layer.get("width"));
				HEIGHT = (int) ((long)layer.get("height"));
				map = parse((JSONArray)layer.get("data"));
			}
		}
	}
	
	private static Image[][] parse(JSONArray arr){
		
		Image[][] layer = new Image[WIDTH][HEIGHT];
		int index;
		
		for(int y=0 ; y<WIDTH ; y++){
			for(int x=0 ; x<HEIGHT ; x++){
				index = (int)((long)arr.get((y*WIDTH)+x));
				layer[x][y] = getSpriteImage(index);
			}
		}
		return layer;
	}
	
	private static Image getSpriteImage(int index){
		if(index == 0)
			return null;
		index = index - 1;
		
		SpriteSheet sheet = Resources.getSprite("tiles");
		
		int horizontal = sheet.getHorizontalCount();
		int y = index / horizontal;
		int x = index % horizontal;

		return sheet.getSubImage(x, y);
	}
	
	public static boolean inBounds(int x, int y){
		return(x>=0 && y>=0 && x<WIDTH && y<HEIGHT);
	}
	
	public static boolean mapTile(int x, int y){
		return(inBounds(x, y) && map[x][y] != null);
	}
}
