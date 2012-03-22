








import java.util.HashMap;



public class Item
{
	private int prix;
	private int poids;
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
}
