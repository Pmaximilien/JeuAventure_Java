


public class Cookie_magique extends Nourriture
{
	private int force;

	public Cookie_magique(int set){
		super(0, "Cookie_Magique");
		force = set;
	}


	public boolean use(Player hero){
		int init = hero.getchargemax();
		System.out.println("Capacité augmenté de "+init+" à "+ (init + force));
		hero.setchargemax(init + force);
		return true;
	}
}
