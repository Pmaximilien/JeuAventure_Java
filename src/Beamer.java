


public class Beamer extends Item
{
	private boolean charge;
	Room destination;

	public Beamer(Room set)
	{
		super("téléporteur", 1, 1);
		charge = true;
		destination = set;
	}

	public void charge(){
		charge = true;
	}

	public boolean use(Player hero){
		if (charge){
			charge = false;
			hero.teleporte(destination);
			return true;
		}
		else{
			System.out.println("Votre téléporteur n'est pas chargé");
			return false;
		}

	}

	public boolean isUsable(){
		return true;
	}
}
		
