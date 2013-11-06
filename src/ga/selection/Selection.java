package ga.selection;

import ga.Individual;
import ga.Population;
import ga.fitness.Fitness;

import java.util.List;
import java.util.Random;

public abstract class Selection {
	
	protected Population population;
	protected Fitness fitness;
	private Random random = new Random(11235);
	
	public abstract List<Individual> select();
	
	public Selection(Population population, Fitness fitness) {
		setPopulation(population);
		this.fitness = fitness;
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
		for (Individual individual : population.getPopulation()) {
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
	
	public void setPopulation(Population population) {
		this.population = population.copy();
	}

}

