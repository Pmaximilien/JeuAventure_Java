
public class Door{

	private Room recto, verso;
	private boolean etat_recto, etat_verso;


	public Door(Room piece1,boolean sens1, Room piece2, boolean sens2){
		recto = piece1;
		verso = piece2;
		etat_recto=sens1;
		etat_verso=sens2;
		piece1.setExits(piece2.getDescription(), this);
		piece2.setExits(piece1.getDescription(), this);
	}

	public Door(Room piece1,Room piece2){
		recto = piece1;
		verso = piece2;
		etat_recto=true;
		etat_verso=true;
		piece1.setExits(piece2.getDescription(), this);
		piece2.setExits(piece1.getDescription(), this);
	}


	public Room getRoom(String direction){
		if(direction.equals(recto.getDescription())){
			if(etat_recto){
				return recto;
			}
		}
		else if(direction.equals(verso.getDescription())){
			if(etat_verso){
				return verso;
			}
		}
				return null;
	}

		
	

}
		
