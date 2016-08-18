package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPProblem;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;

@SuppressWarnings("serial")
public class RegressionProblem extends GPProblem implements SimpleProblemForm  {

	public static final String P_DATA = "data";
	
	public float currentX;
	public ArrayList<Float> inputX = new ArrayList<Float>();
	public ArrayList<Float> inputY = new ArrayList<Float>();

    public void setup(final EvolutionState state,
                      final Parameter base) {
        // very important, remember this
        super.setup(state,base);

        // verify our input is the right class (or subclasses from it)
        if (!(input instanceof FloatData)) {
            state.output.fatal("GPData class must subclass from " + FloatData.class,
                base.push(P_DATA), null);
        }
        
        readFromFile();
        
    }
    
    public void evaluate(final EvolutionState state, 
            final Individual ind, 
            final int subpopulation,
            final int threadnum) {
		if (!ind.evaluated) {  // don't bother reevaluating
		   
		   FloatData input = (FloatData)(this.input);
		
		   int hits = 0;
		   float sum = 0;
		   float expectedResult;
		   float result;
		   for (int i = 0; i < inputX.size(); i++) {
			   currentX = inputX.get(i);
		       expectedResult = inputY.get(i);
		       ((GPIndividual)ind).trees[0].child.eval(
		           state,threadnum,input,stack,((GPIndividual)ind),this);
		
		       result = Math.abs(expectedResult - input.x);
		       if (result <= 0.01) hits++;
		       sum += result;                  
		   }
		
		   // the fitness better be KozaFitness!
		   KozaFitness f = ((KozaFitness)ind.fitness);
		   f.setStandardizedFitness(state, sum);
		   f.hits = hits;
		   ind.evaluated = true;
		}
	}
    
    @Override
    public void describe(EvolutionState state, Individual ind,
    		int subpopulation, int threadnum, int log) {
    	super.describe(state, ind, subpopulation, threadnum, log);
 		   
 		   FloatData input = (FloatData)(this.input);
 		
 		   int hits = 0;
 		   float sum = 0;
 		   float expectedResult;
 		   float result;
 		   for (int i = 0; i < inputX.size(); i++) {
 			   currentX = inputX.get(i);
 		       expectedResult = inputY.get(i);
 		       ((GPIndividual)ind).trees[0].child.eval(
 		           state,threadnum,input,stack,((GPIndividual)ind),this);
 		
 		       result = Math.abs(expectedResult - input.x);
 		       if (result <= 0.01) hits++;
 		       sum += result;                  
 		   }
 		
 		   // the fitness better be KozaFitness!
 		   KozaFitness f = ((KozaFitness)ind.fitness);
 		   f.setStandardizedFitness(state, sum);
 		   f.hits = hits;
    }

    private void readFromFile() {
        Scanner scan;
        File file = new File("src/main/data.txt");
        try {
            scan = new Scanner(file);
            int indexOfNumberInFile = 0;
            while(scan.hasNextFloat())
            {
            	float floatNumber = scan.nextFloat();
            	if (indexOfNumberInFile % 2 == 0) {
            		inputX.add(floatNumber);
            	} else {
            		inputY.add(floatNumber);
            	}
            	indexOfNumberInFile++;
            }

        } catch (FileNotFoundException e1) {
                e1.printStackTrace();
        }
    }
    
}
