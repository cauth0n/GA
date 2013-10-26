package ga;

public class FitnessDefault extends Fitness {
	
	public double getIndividualFitness(Individual individual) {
		double fitness = 0.0;
		// TODO: calculate fitness
		individual.setFitness(fitness);
		return fitness;
	}

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

