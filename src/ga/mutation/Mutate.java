package ga.mutation;
import ga.Individual;
import ga.Population;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstract Mutate class.
 */
public abstract class Mutate {

	protected double spread = 0.3;		// the extent to which the individual is mutated
	protected Random random = new Random(11235);
	
	/**
	 * Mutate each individual in the population with a certain probability.
	 * 
	 * @param population	The population of individuals that will be mutated.
	 * @param probability	The probability that a given individual will be mutated.
	 * @return				The population after the mutation operation has occured.
	 */
	public Population mutate(Population population, Double probability) {
		List<Individual> newPopulation = new ArrayList<Individual>();
		
		// loop through all individuals in the population
		for (Individual individual : population.getIndividuals()) {
			
			// if the individual is allowed to mutate, and they have been randomly chosen...
			if (individual.canMutate() && random.nextDouble() < probability) {
				// mutate the individual and add it to the population
				Individual mutated = mutate(individual, population);
				newPopulation.add(mutated);
			// add the unaffected individual to the population
			} else {
				newPopulation.add(individual);
			}
			
		}
		
		// return the new population of individuals, some of which may have been mutated
		return new Population(newPopulation);
	}
	
	/**
	 * Perform the mutation operation on an individual in the population.
	 * 
	 * @param individual	The individual that will be mutated.
	 * @param population	The population that this individual belongs to.
	 * @return				The individual produced after mutation.
	 */
	public abstract Individual mutate(Individual individual, Population population);

}

