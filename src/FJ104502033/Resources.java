package FJ104502033;

import java.util.Map;
import java.util.HashMap;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

import World.Tile;

import org.newdawn.slick.Sound;


public class Resources {
	
	private static Map<String, Image>images;
	private static Map<String, SpriteSheet>sprites;
	private static Map<String, Sound>sounds;
	
	public Resources(){
		images = new HashMap<String, Image>();
		sprites = new HashMap<String, SpriteSheet>();
		sounds = new HashMap<String, Sound>();
		
		try {
			sprites.put("tiles", loadSprite("Pic/tiles.png", Tile.SMALL_SIZE, Tile.SMALL_SIZE));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public static Image loadImage(String path) throws SlickException{
		return new Image(path, false, Image.FILTER_NEAREST);
	}
	
	public static SpriteSheet loadSprite(String path, int tw, int th) throws SlickException{
		return new SpriteSheet(loadImage(path), tw, th);
	}
	
	public static SpriteSheet getSprite(String getter){
		return sprites.get(getter);
	}
	
	public static Image getSpriteImage(String getter, int x, int y){
		return sprites.get(getter).getSubImage(x, y);
	}
	
	public static Image image(String getter){
		return sprites.get(getter);
	}
	
	public static Image getImage(String getter){
		return images.get(getter);
	}
	
	public static Sound getSound(String getter){
		return sounds.get(getter);
	}
}
