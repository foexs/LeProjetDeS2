package fr.iutvalence.info.dut.m2107;

public class MainMenu {
	public static final String CONFIG_FILE = "Perso.cfg" ;
	public WeaponSet ws;
	public int currentWeapon;
	public HullSet hs;
	public int currentHull;
	public ItemSet is;
	public EnemySet es;
	public boolean weaponSelected;
	
	
	public MainMenu(){
		/**
		 * Load weapons
		 */
		ws = new WeaponSet();
		ws.getWeaponFromFile(CONFIG_FILE);
		currentWeapon=0;
		/**
		 * Load hulls
		 */
		HullSet hs = new HullSet();
		hs.getHullFromFile(CONFIG_FILE);
		currentHull=0;
		/**
		 * Load items
		 */
		ItemSet is = new ItemSet();
		is.getItemFromFile(CONFIG_FILE);
		
		/**
		 * Load enemies
		 */
		EnemySet es = new EnemySet();
		es.getEnnemyFromFile(CONFIG_FILE);
	}
	
	public void rightKeyPressed(){
		if (weaponSelected){
			if (currentWeapon>=ws.weapons.length-1){
				this.currentWeapon=0;
			}else{
				this.currentWeapon++;
			}	
		}
		else{
			
		}
		System.out.println(this.getClass()+" right key pressed");
	}
	public void leftKeyPressed(){
		if (weaponSelected){
			if (currentWeapon<=0){
				this.currentWeapon=ws.weapons.length-1;
			}else{
				this.currentWeapon--;
			}	
		}
		else{
			
		}
		System.out.println(this.getClass()+" left key pressed");
	}

	public void upDownKeyPressed() {
		// TODO Auto-generated method stub
		
	}

	public void enterKeyPressed() {
		// TODO Auto-generated method stub
		
	}


}