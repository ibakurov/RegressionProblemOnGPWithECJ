package functions;

import main.FloatData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

@SuppressWarnings("serial")
public class Mul extends GPNode {
	
	public String toString() { return "*"; }

	public int expectedChildren() { return 2; }

	public void eval(final EvolutionState state,
                 final int thread,
                 final GPData input,
                 final ADFStack stack,
                 final GPIndividual individual,
                 final Problem problem) {
	    float result;
	    FloatData rd = ((FloatData)(input));
	
	    children[0].eval(state,thread,input,stack,individual,problem);
	    result = rd.x;
	
	    children[1].eval(state,thread,input,stack,individual,problem);
	    rd.x = result * rd.x;
    }
}