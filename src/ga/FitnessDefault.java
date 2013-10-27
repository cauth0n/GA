package ga;

public class FitnessDefault extends Fitness {
	
	public double getIndividualFitness(Individual individual) {
		double fitness = 0.0;
		// TODO: calculate fitness
		individual.setFitness(fitness);
		return fitness;
	}

}

