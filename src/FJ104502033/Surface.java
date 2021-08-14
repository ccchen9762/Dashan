package FJ104502033;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import World.world;

public class Surface extends StateBasedGame{
		
	public static final int menu = 0;
	public static final int guide = 1;
	public static final int play = 2;
	
	public Surface(String game){
		super(game);
		this.addState(new Menu(menu));
		this.addState(new Guide(guide));
		this.addState(new Play(play));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		
		gc.setMaximumLogicUpdateInterval(60);
		gc.setTargetFrameRate(60);
		gc.setVSync(true);

		this.getState(menu).init(gc, this);
		this.getState(guide).init(gc, this);
		this.getState(play).init(gc, this);
		this.enterState(menu);
	}
	
	public static void main(String[] args) throws SlickException{
		AppGameContainer app;
		try{
			app = new AppGameContainer(new Surface("Dashan"));		
			app.setShowFPS(true);
			app.setDisplayMode(900, 600, false);
			app.setAlwaysRender(true);
			app.start();
		}catch(SlickException e){
			e.printStackTrace();
		}
	}
}


