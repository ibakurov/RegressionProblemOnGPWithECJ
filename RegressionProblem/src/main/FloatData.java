package main;
import ec.gp.GPData;

@SuppressWarnings("serial")
public class FloatData extends GPData {
	public float x;
	
	public void copyTo(final GPData gpd) {
		((FloatData)gpd).x = x; 
	}	
}
