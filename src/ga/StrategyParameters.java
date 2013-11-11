package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * An object that contains the list of strategy parameters 
 * that will be used by an individual in the ES algorithm.
 */
public class StrategyParameters {
	
	private double minValue = -0.3;
	private double maxValue = 0.3;
	List<Double> params;
	private static int seed = 1;
	
	/**
	 * Constructs the object that contains strategy 
	 * parameters for an individual using random values.
	 * 
	 * @param numParams	The number of strategy parameters that the individual will use.
	 */
	public StrategyParameters(int numParams) {
		params = new ArrayList<>(numParams);
		Random rand = new Random(11235 * seed++);
		// construct the list of strategy parameters, assigning random values to each
		for (int paramNum = 0; paramNum < numParams; paramNum++) {
			double initValue = minValue + (rand.nextDouble() * (maxValue - minValue));
			params.add(new Double(initValue));
		}
	}
	
	/**
	 * Constructs the object that contains strategy parameters 
	 * for an individual given a set of existing parameters.
	 * 
	 * @param params	The list of strategy parameters that should be used.
	 */
	public StrategyParameters(List<Double> params) {
		this.params = params;
	}
	
	/**
	 * @return	A copy of the strategy parameters object.
	 */
	public StrategyParameters copy() {
		List<Double> newParams = new ArrayList<Double>(params.size());
		// loop through each parameter, constructing a list
		for (Double param : params)
			newParams.add(new Double(param));
		// use the list to create an identical StrategyParameters object
		return new StrategyParameters(newParams);
	}
	
	/**
	 * @return	The list of strategy parameters in List form.
	 */
	public List<Double> getParameters() {
		return params;
	}

}
