import java.util.HashMap;
import java.util.Set;


class Player {
	
	private String name;
	private HashMap <String, Item> sac;
	private int charge;


	public Player(String nom) {
		name = nom;
		sac = new HashMap<String, Item>(); 
		charge = 0;
	}

	public Player(){
		name = "Unknow";
		sac = new HashMap<String, Item>(); 
		charge = 0;
	}



	public Item drop_item(String name){
		Item drop = sac.get(name);		

		return drop;
	}

	public boolean add_item(Item nouveau){
		if (charge + nouveau.getPoids() < 200){
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

	public String getSacString(){
		StringBuilder info = new StringBuilder(128);
		
		Set<String> keys = sac.keySet();
		for(String item : keys)
			info.append(" " + item);
		return info.toString();
	}
}
