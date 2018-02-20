package interfaces;

import enums.Rohstoff;

public interface Feld 
{
	public Rohstoff getRohstoff();  
	public int getWuerfelZahl();  
	public int getId();
	public spielbrettKomponenten.Ecke getUnbesetzteEcke();
}
