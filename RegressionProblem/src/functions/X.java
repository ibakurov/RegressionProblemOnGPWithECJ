package functions;

import main.FloatData;
import main.RegressionProblem;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

@SuppressWarnings("serial")
public class X extends GPNode {

	public String toString() { return "x"; }

    public int expectedChildren() { return 0; }
	
    @Override
    public void eval(final EvolutionState state,
            final int thread,
            final GPData input,
            final ADFStack stack,
            final GPIndividual individual,
            final Problem problem) {
    	FloatData rd = ((FloatData)(input));
    	rd.x = ((RegressionProblem)problem).currentX;
    }
}
