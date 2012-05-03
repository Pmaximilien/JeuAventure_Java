
import java.util.Random;




public class TransporterRoom extends Room{
	private Random hasard;

	public TransporterRoom(String description){
		super(description);
		hasard = new Random();
	}

	public Room getExit(String direction){
		return findRandomRoom();
	}

	private Room findRandomRoom(){
		return exits.get(hasard.nextInt()); 
	}
}
