


public class Item
{
	private int prix;
	private int poids;
	@SuppressWarnings("unused")
	private String description;
	private String nom_item;

	public Item(String init_nom)
	{
		nom_item = new String(init_nom);
	}

	public Item(String init_nom, int _prix, int _poids)
	{
		nom_item = new String(init_nom);
		prix = _prix;
		poids = _poids;
	}

	public void setIDescription(String _description)
	{
		description = new String(_description);
	}

	public int getPrix()
	{
		return prix;
	}
	
	public int getPoids()
	{
		return poids;
	}

	public String getInfoItem(){
		String resInfo = new String("nom ");

		resInfo += nom_item + " " + poids + " " + prix;

		return resInfo;
	}

	public String getNameItem(){
		return nom_item;
	}

	public boolean isUsable(){
		// par défaut non-utilisable
		return false;
	}
	
	public boolean use(Player heros){
		// Empty
		return false;
	}

	public boolean isConso(){
		// par défaut non-consommable (après "utilisation" on garde l'objet
		return false;
	}
		
}
