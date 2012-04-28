



public class Nourriture extends Item
{

	private int calories;


	public Nourriture(int set){
		super("Bouffe", 1, 1);
		calories = set;
	}

	public Nourriture(int set, String name){
		super(name, 1, 1);
		calories = set;
	}

	public boolean use(Player hero){
		hero.ventre += calories;
		return true;
	}

//	------  Définition propriété ----- //
 	public boolean isUsable(){
		return true;
	}
 	public boolean isConso(){
		return true;
	}
}
