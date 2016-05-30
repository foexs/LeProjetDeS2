package fr.iutvalence.info.dut.m2107;

import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * @author popekn
 * Represents the game itself
 */
public class Game {

	private int score;
	protected Ship ship;
	public static final int DEFAULT_SHIP_SIZE=62;
	private LinkedList<Entity> entities;
	/**
	 * contains the settings
	 */
	private MainMenu menu;
	
	/**
	 * Game's algorithm. Everything related to the Game goes here. 
	 */
	public Game(MainMenu menu)
	{
		Weapon weapon=menu.ws.weapons.get(menu.currentWeapon);
		Hull hull=menu.hs.hulls.get(menu.currentHull);
		this.score=0;
		this.menu=menu;
		this.ship=(new Ship(weapon, hull , new Dot(GUI.DEFAULT_WIDTH/2,GUI.DEFAULT_HEIGHT-40), DEFAULT_SHIP_SIZE));
		entities = new LinkedList<Entity>();

	}
	public void enemyTurn(){
		
	}
	/**
	 * @return 
	 */
	//getters and setters
	public int getScore() {
		return score;
	}

	public LinkedList<Entity> getEntities() {
		return entities;
	}
	
	public void spawnItem(Dot position,Item item){
		entities.add(new Entity(position,item));
	}
	public void spawnEnemy(Dot position,Enemy enemy){
		entities.add(new Entity(position,enemy));
	}
	public void spawnEntity(Dot position,int size, int health){
		entities.add(new Entity(position, size, health));
	}
	public Ship getShip() {
		return ship;
	}	
	public int getShipSize() {
		return DEFAULT_SHIP_SIZE;
	}	
	
	public void collisionUpdate(){
		for(int i=0; i<this.entities.size()-1;i++){
			/**
			 * looks if the two hitboxes touches
			 */
			if(this.ship.getHitbox().isIn(this.entities.get(i).getHitbox())){
				/**
				 * if the entity is an item
				 */
				if(this.entities.get(i).getType()==EntityType.ITEM){
					
					this.ship.heal(this.entities.get(i).getItem().getHealthRestoreLevel());
					
					this.ship.increaseDamage(this.entities.get(i).getItem().getBaseDamageLevel());
					/**
					 * clear screen if bomb
					 */
					if(this.entities.get(i).getItem().isBomb()){
						this.entities = new LinkedList<Entity>();
					}
				}
				/**
				 * the entity is an enemy or a rock
				 */
				else{
					/**
					 * deal damages to the ship
					 */
					this.ship.heal(-this.entities.get(i).getHealth());
				}
				/**
				 * destroy entity
				 */
				this.entities.remove(i);
				
			}
			
		}
	}
	
	public void rightKeyPressed(){
		
	}
	public void leftKeyPressed(){
		
	}

	public void upKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	public void downKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	public void enterKeyPressed() {
		// TODO Auto-generated method stub
		
	}
	public void onMouseReleased(int x) {
		ship.setPosition(new Dot(x-62,ship.getPosition().getY()));
	}
	
	
	

	
}