package ga;

public abstract class Fitness {
	
	protected abstract double getIndividualFitness(Individual individual);

	/**
	 * Returns the sum of every fitness for entire population.
	 */
	public double getPopulationFitnessSum(Population population) {
		double fitness = 0.0;
		for (Individual individual : population.getPopulation())
			fitness += individual.getFitness();
		return fitness;
	}
	
	/**
	 * Returns average fitness for entire population.
	 */
	public double getPopulationFitnessAverage(Population population) {
		return getPopulationFitnessSum(population) / population.size();
	}

}

