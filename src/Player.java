import java.util.HashMap;
import java.util.Set;


class Player {
	
	private String name;
	private HashMap <String, Item> sac;
	private int charge;
	private int ventre;
	private int charge_max;

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

	public int get_ventre(){
		return ventre;
	}
		

	public void add_ventre(int nb){
		ventre+=nb;
		return;
	}
	
	public int get_capacite(){
		return charge_max;
	}	

	public void add_capacite(){
		charge_max+=20;
		return;
	}			

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


	// Ascesseur
	public int getCharge(){
		return charge;
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
