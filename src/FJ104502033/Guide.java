package FJ104502033;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class Guide extends BasicGameState{

	public String mouse = "No input yet!";
	
	public Guide(int state){
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame state) throws SlickException {
	}

	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException {
		Image how = new Image("Pic/how.png");
	
		g.drawImage(how, 0, 0);
		g.drawString(mouse, 50, 50);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame state, int k) throws SlickException {
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "Mouse psition x: " + xpos + "y: " + ypos;
		if((xpos>764&& xpos<864) && (ypos>473 && ypos<571)){
			if(input.isMouseButtonDown(0)){
				state.enterState(0, new FadeOutTransition(),new FadeInTransition());
			}
		}
	}

	@Override
	public int getID() {
		return 1;
	}
}
