package fr.iutvalence.info.dut.m2107;

import java.awt.Font;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
/**
 * Define a gui, to execute you need to have the natives in your build path and this argument:"Djava.library.path=lib/natives"
 * @author simon
 *
 */
public class GUI extends BasicGame {
	
		public static final int DEFAULT_HEIGHT=768;
		public static final int DEFAULT_WIDTH=1024;
		public static final int LINE_SIZE=20;
		
	
		private TrueTypeFont font;
		private TrueTypeFont font2;
		private TrueTypeFont font3;
		
		public Image backgroundGame;
		
		public final String backgroundMenu = ("ihm/background menu_1.png");
		
		public static GameContainer container;
		/**
		 * Contains all setup and parameters, can display itself too
		 */
		Game game;
		MainMenu menu;
		
		/**
		 * if false, the app is on menu
		 */
		boolean playing;
		
		
	    public GUI() {
	        super("Afterburned");
	    }

	    @Override
	    public void init(GameContainer container) throws SlickException {
	        GUI.container = container;
	        this.menu=new MainMenu();
	        this.game=new Game(menu);	
	        Font awtFont = new Font("Impact", Font.BOLD, 48);
			font = new TrueTypeFont(awtFont, true);
			Font awtFont2 = new Font("Impact", Font.BOLD, 16);
			font2 = new TrueTypeFont(awtFont2, true);
			Font awtFont3 = new Font("Impact", Font.PLAIN, 16);
			font3 = new TrueTypeFont(awtFont3, true);
    		game.ship.setPosition(new Dot(DEFAULT_WIDTH/2-game.getShipSize(),DEFAULT_HEIGHT-game.getShipSize()*2));
	        }

	    @Override
	    public void render(GameContainer container, Graphics g)  throws SlickException {
	    	/**
	    	 * when playing
	    	 */
	    	if (this.playing)
	    	{
	    		g.drawImage(new Image(menu.currentEnvironment.getBackgroundPath()), 0, 0);//Draw background
	    		font2.drawString(DEFAULT_WIDTH/2 + DEFAULT_WIDTH/4+DEFAULT_WIDTH/24  , LINE_SIZE*1,"Weapon: "+menu.ws.weapons.get(menu.currentWeapon).getName());
	    		font2.drawString(DEFAULT_WIDTH/2 + DEFAULT_WIDTH/4+DEFAULT_WIDTH/24 , LINE_SIZE*2,"Hull: "+menu.hs.hulls.get(menu.currentHull).getName());
	    		font2.drawString(DEFAULT_WIDTH/2 + DEFAULT_WIDTH/4+DEFAULT_WIDTH/24 ,LINE_SIZE*3,"Environment: "+menu.currentEnvironment.name());
	    		g.setColor(Color.red);
	    		g.drawLine(0,game.getShip().getPosition().getY(), DEFAULT_WIDTH,game.ship.getPosition().getY() );
	    		g.drawLine(0,game.getShip().getPosition().getY()+1, DEFAULT_WIDTH,game.ship.getPosition().getY()+1);
	    		g.setColor(Color.white);
	    		/**
	    		 * Draw the ship
	    		 */
	    		g.drawImage(new Image(game.getShip().getShipPath()),game.ship.getHitbox().getMinX(),game.ship.getPosition().getY());
	    		/**
	    		 * Show the aim 'line'
	    		 */
	    		g.drawLine(game.getShip().getPosition().getX(), game.getShip().getPosition().getY(),(int) Math.round(((DEFAULT_HEIGHT-game.getShip().getHitbox().getMinY()))/Math.tan(Math.toRadians(game.getShip().getAngle())))+game.getShip().getPosition().getX(), 0);
	    		font.drawString(DEFAULT_WIDTH/3, DEFAULT_HEIGHT/4,"Move your ship!");
	    		font3.drawString(DEFAULT_WIDTH/6, DEFAULT_HEIGHT/3,"(Hint: you move by clicking, you can click anywhere you want, just remember you can click only once, so be careful.)");
	    		
	    	}
	    	/**
	    	 * when on menu
	    	 */
	    	else
	    	{
	    		g.drawImage(new Image (backgroundMenu), 0, 0);
	    		drawBox(new Dot(330,490),260,35,g);
	    		if(menu.selection==Selection.weapon)
	    			font2.drawString(DEFAULT_WIDTH/4+DEFAULT_WIDTH/10 , DEFAULT_HEIGHT/2+DEFAULT_HEIGHT/8+LINE_SIZE*1,"Weapon: "+menu.ws.weapons.get(menu.currentWeapon).getName());
	    		if(menu.selection==Selection.hull)
	    			font2.drawString( DEFAULT_WIDTH/4+DEFAULT_WIDTH/10 , DEFAULT_HEIGHT/2+DEFAULT_HEIGHT/8+LINE_SIZE*1,"Hull: "+menu.hs.hulls.get(menu.currentHull).getName());
	    		if(menu.selection==Selection.environment)
	    			font2.drawString(DEFAULT_WIDTH/4+DEFAULT_WIDTH/10, DEFAULT_HEIGHT/2+DEFAULT_HEIGHT/8+LINE_SIZE*1,"Environment: "+menu.currentEnvironment.name());
	    		drawBox(new Dot(330,690),260,35,g);
	    		font2.drawString( DEFAULT_WIDTH/4+DEFAULT_WIDTH/8, DEFAULT_HEIGHT/2+DEFAULT_HEIGHT/4+DEFAULT_HEIGHT/8+DEFAULT_HEIGHT/128+LINE_SIZE*1,"Press enter to play");
	    	}
	    	
	    }

	    @Override
	    public void update(GameContainer container, int delta) throws SlickException {
	    		
	    }
	    
	    @Override
	    public void mouseReleased(int bouton, int x, int y)
	    {
	    	if(playing)
	    	{
	    		game.onMouseReleased(x);
	    	}
	    }
	    
	    @Override
	    public void keyReleased(int key, char c){/*
	    	 *General keyReleased 
	    	 */
	        if (Input.KEY_ESCAPE == key) {
	            container.exit();
	        }
	        if (Input.KEY_F11== key){
	        	try{
        			if (container.isFullscreen())
        			{
        					container.setFullscreen(false);	
        			}
	        		else
	        			container.setFullscreen(true);
	        	}
        		catch (SlickException e)
        		{
        		
        		}
	        }
	        if (Input.KEY_RIGHT== key){
	        	if(this.playing){
	        		game.rightKeyPressed();
	        	}
	        	else{
	        		menu.rightKeyPressed();
	        	}
	        }
	        
	        if (Input.KEY_LEFT== key){
	        	if(this.playing){
	        		game.leftKeyPressed();
	        	}
	        	else{
	        		menu.leftKeyPressed();
	        	}
	        	
	        }
	        
			if (Input.KEY_UP== key){
				if(this.playing){
	        		game.upKeyPressed();
	        	}
	        	else{
	        		menu.upKeyPressed();
	        	}
	
			}
			
			if (Input.KEY_DOWN== key){
				if(this.playing){
	        		game.downKeyPressed();
	        	}
	        	else{
	        		menu.downKeyPressed();
	        	}
	        }
			
			if (Input.KEY_ENTER== key){
				if(this.playing){
	        		game.enterKeyPressed();
	        	}
	        	else{
	        		this.playing=true;
	        		
	        	}
	        }
			
	    }
	public static void main(String[] args) throws SlickException {
		new AppGameContainer(new GUI(), DEFAULT_WIDTH, DEFAULT_HEIGHT, false).start();
	}
	
	public static void drawBox(Dot position, int width, int height, Graphics g){
		
		
		g.drawRect(position.getX(), position.getY(), width+2, height+2);
		g.drawRect(position.getX()+1, position.getY()+1, width, height);
		
	}

}
