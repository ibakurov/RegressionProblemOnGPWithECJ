package functions;

import main.FloatData;

import java.util.Random;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

@SuppressWarnings("serial")
public class Ephemeral extends GPNode {

	FloatData rd = new FloatData();
	
	public Ephemeral() {
		Random rand = new Random();
    	rd.x = Math.round(rand.nextFloat());
	}
	
	public String toString() { return rd != null ? String.valueOf(rd.x) : "n"; }

    public int expectedChildren() { return 0; }
	
    @Override
    public void eval(final EvolutionState state,
            final int thread,
            final GPData input,
            final ADFStack stack,
            final GPIndividual individual,
            final Problem problem) {
    	((FloatData)(input)).x = rd.x;
    }
}

