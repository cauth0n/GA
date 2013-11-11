package ga.mutation;

import ga.Gene;
import ga.Individual;
import ga.Population;

import java.util.Random;

/**
 * Implemented Uniform Distribution Mutation.
 */
public class MutateUniformDistribution extends Mutate {
	
	private static int seed = 1;
	
	/**
	 * Perform a uniform distribution mutation operation on an individual in the population.
	 * 
	 * @param individual	The individual that will be mutated.
	 * @param population	The population that this individual belongs to.
	 * @return				The individual produced after mutation.
	 */
	public Individual mutate(Individual individual, Population population) {
		// copy the individual
		Individual newIndividual = individual.copy();
		// loop through all genes in the copied individual
		for (Gene gene : newIndividual.getGenes()) {
			// get mutation value from uniform distribution
			double mutationValue = getMutationValue();
			// update gene value using "val = val + mutationValue"
			gene.setValue(gene.getValue() + mutationValue);
		}
		// return newly mutated individual
		return newIndividual;
	}
	
	/**
	 * @return	A random double retrieved from a uniform distribution.
	 */
	public double getMutationValue() {
		Random rand = new Random(11235 * seed++);
		// generate random value in the range (-spread , spread)
		double value = (-1 * spread) + (rand.nextDouble() * (spread + spread));
		// return generated value
		return value;
	}

}

