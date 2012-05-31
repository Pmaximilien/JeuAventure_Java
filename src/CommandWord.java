



public enum CommandWord
{
	GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), EAT("eat"), USE("USE"), STATUS("status"),
	BACK("back"), EXECUTE("execute"), TAKE("take"), DROP("drop"), UNKNOWN("?");


	private String commande;

	CommandWord(String com){
		this.commande = com;
	}


	public String get_commande(){
		return commande;
	}
}
