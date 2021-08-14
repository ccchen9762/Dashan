package FJ104502033;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import World.world;

public class Menu extends BasicGameState{
	
	private Music music1;
	private Music music2;
	public String mouse = "No input yet!";

	public Menu(int state){
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame state) throws SlickException {
		Music music1 = new Music("Sound/081.wav");
		Music music2 = new Music("Sound/082.wav");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException {
		Image menu = new Image("Pic/menu.png");
		g.drawImage(menu, 0, 0);
		world.render(250, 250);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame state, int k) throws SlickException {
		Input input = gc.getInput();
		Music music1 = new Music("Sound/081.wav");
		Music music2 = new Music("Sound/082.wav");
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "Mouse psition x: " + xpos + "y: " + ypos;

		if((xpos>423 && xpos<807) && (ypos>246 && ypos<294)){
			if(input.isMouseButtonDown(0)){
				state.enterState(2, new FadeOutTransition(),new FadeInTransition());
				music1.play();
				music1.setVolume(10);
			}
		}
		if((xpos>362 && xpos<792) && (ypos>103 && ypos<148)){
			if(input.isMouseButtonDown(0)){
				state.enterState(1, new FadeOutTransition(),new FadeInTransition());
				music2.play();
				music2.setVolume(10);
			}
		}
	}

	@Override
	public int getID() {
		return 0;
	}
	
}