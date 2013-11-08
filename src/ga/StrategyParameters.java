package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StrategyParameters {
	
	private double minValue = -0.3;
	private double maxValue = 0.3;
	List<Double> params;
	private static int seed = 1;
	
	public StrategyParameters(int numParams) {
		params = new ArrayList<>(numParams);
		Random rand = new Random(11235 * seed++);
		for (int geneNum = 0; geneNum < numParams; geneNum++) {
			double initValue = minValue + (rand.nextDouble() * (maxValue - minValue));
			params.add(new Double(initValue));
		}
	}
	
	public StrategyParameters(List<Double> params) {
		this.params = params;
	}
	
	public StrategyParameters copy() {
		List<Double> newParams = new ArrayList<Double>(params.size());
		for (Double param : params)
			newParams.add(new Double(param));
		return new StrategyParameters(newParams);
	}

}
