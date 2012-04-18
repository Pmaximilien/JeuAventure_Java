


public class Beamer extends Item
{
	private boolean charge;
	Room destination;
	GameEngine context;

	public Beamer(Room set, GameEngine jeu)
	{
		super("téléporteur", 1, 1);
		context = jeu;
		charge = false;
		destination = set;
	}

	public void charge(){
		charge = true;
	}

	public void use(){
		if (charge){
			charge = false;
			context.deplace(destination);
		}
		else{
			System.out.println("Votre téléporteur n'est pas chargé");
		}

	}
}
		
