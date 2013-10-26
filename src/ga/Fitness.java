package ga;

public abstract class Fitness{
	protected abstract double getIndividualFitness(Individual individual);

	protected abstract double getPopulationFitness(Population population);

}

