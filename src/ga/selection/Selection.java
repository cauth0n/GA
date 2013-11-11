package ga.selection;

import ga.Individual;
import ga.Population;
import java.util.Random;

public abstract class Selection {
	
	protected Population population;
	private Random random = new Random(11235);
	protected MatingPlan plan;
	protected int returnSize = 0;
	
	/**
	 * Abstract selection method.
	 * This sets the population and resets the mating plan
	 * for each time this method is called. Implemented
	 * classes handle actual selection process.
	 * 
	 * @param population	The population to select from.
	 */
	public void select(Population population) {
		this.population = population;
		this.plan = new MatingPlan();
		// if a returnSize (# of children) has not been 
		// specified, keep population size constant
		if (returnSize < 1)
			this.returnSize = population.size();
	}
	
	/**
	 * Set the return size for the algorithm assuming a one child crossover method.
	 * In mu + lambda, this is lambda.
	 * 
	 * @param returnSize	The number of children to produce.
	 */
	public void setReturnSize(int returnSize) {
		this.returnSize = returnSize;
	}
	
	/**
	 * Chooses parents based on the fitness proportionate, or roulette wheel principal.
	 * (Individuals with higher fitness are more likely to be chosen)
	 * 
	 * @param sum	The sum of all individual fitness values.
	 * @return		The individual chosen with weighted probability.
	 */
	public Individual selectParentProportionate(double sum) {
		
		// set parent to first individual initially
		Individual parent = null;
		
		// choose a random number between 0 and the sum of all fitness values
		double select = sum * random.nextDouble();
		
		// based on selection, choose individual that has been chosen, giving a proportional probability based on fitness
		double runningFitnessTotal = 0.0;
		for (Individual individual : population.getIndividuals()) {
			// this is the chosen random individual, break from loop
			if (select > runningFitnessTotal && select < runningFitnessTotal + individual.getFitness()) {
				parent = individual;
				break;
			}
			// we have not yet reached the chosen one, continue
			runningFitnessTotal += individual.getFitness();
		}
		
		return parent;
		
	}
	
	/**
	 * @return	The MatingPlan formed by the select() method.
	 */
	public MatingPlan getMatingPlan() {
		return plan;
	}

}

