package ga;

public abstract class Fitness {
	
	protected abstract double getIndividualFitness(Individual individual);

	/**
	 * Returns average fitness for entire population.
	 */
	public double getPopulationFitness(Population population) {
		double fitness = 0.0;
		for (Individual individual : population.getPopulation())
			fitness += individual.getFitness();
		fitness /= population.size();
		return fitness;
	}

}

