import java.util.HashMap;
import java.util.Set;


class Player {

	// Caractèristiques :
	private String name;
	private int charge_max;

	// Inventaire :
	private HashMap <String, Item> sac;

	// État :
	private int ventre;
	private int charge;
	private Room currentRoom;


//	-----     Constructeur  -----
	public Player(String nom) {
		name = nom;
		sac = new HashMap<String, Item>(); 
		charge = 0;
		ventre = 20;
		charge_max=100;	
	}

	public Player(){
		name = "Unknow";
		sac = new HashMap<String, Item>(); 
		charge = 0;
		ventre = 20;
		charge_max=100;
	}

//	----- Ascesseurs -----

	public int getCharge(){
		return charge;}

	public int get_ventre(){
		return ventre;}

	public int get_capacite(){
		return charge_max;}	

	public Room get_position(){
		return currentRoom;
	}


//	----- Gestion État -----

	public void teleporte(Room set){
		currentRoom = set;
	}

	public boolean deplace(String direction){
		return deplace(currentRoom.getExit(direction));
	}

	public boolean deplace(Room nextRoom){
      		if (nextRoom == null || ventre <= 5){
			return false;}
		else {
		    currentRoom = nextRoom;
		    ventre -= 5;
		    return true;
		}

	}


	public void add_ventre(int nb){
		ventre+=nb;
		return;
	}
	
	public void add_capacite(){
		charge_max+=20;
		return;
	}			

	public boolean eat(Item food){
		return false;		
	}


//	----- Gestion inventaire -----

	public Item drop_item(String name){
		if (hasItem(name)){
			Item drop = sac.get(name);		
			charge -= drop.getPoids();
			sac.remove(name);
			return drop;}
		return null;
	}

	public boolean add_item(Item nouveau){
		if (charge + nouveau.getPoids() < charge_max){
			sac.put(nouveau.getNameItem(), nouveau);
			charge += nouveau.getPoids();
			return true;
		}
		else {
			return false;
		}
	}

	public boolean hasItem(String test){
		Set<String> keys = sac.keySet();	
		for(String item : keys)
			if (test.equals(item))
				return true;

		return false;
	}

	public String getSacString(){
		StringBuilder info = new StringBuilder(128);
		
		Set<String> keys = sac.keySet();
		for(String item : keys)
			info.append(" " + item);
		return info.toString();
		}
	}

