package ga.mutation;

import ga.Gene;
import ga.Individual;
import ga.Population;

import java.util.List;
import java.util.Random;

/**
 * Implemented Normal Distribution Mutation.
 */
public class MutateNormalDistribution extends Mutate {
	
	Random random = new Random(11235);
	double stepSize = 0.1;		// the amount by which strategy parameters are changed
	
	/**
	 * Perform a normal distribution mutation operation on an individual in the population.
	 * 
	 * @param individual	The individual that will be mutated.
	 * @param population	The population that this individual belongs to.
	 * @return				The individual produced after mutation.
	 */
	public Individual mutate(Individual individual, Population population) {
		// create a copy of the individual
		Individual newIndividual = individual.copy();
		// mutate the genes of the copied individual
		mutateGenes(newIndividual);
		// mutate the strategy parameters of the individual
		mutateStrategyParameters(newIndividual);
		// return the new individual
		return newIndividual;
	}
	
	/**
	 * Mutates the genes using Gaussian noise with strategy parameters.
	 * 
	 * @param individual	The individual whose genes will be modified.
	 */
	public void mutateGenes(Individual individual) {
		
		// store genes and strategy parameters for the individual
		List<Gene> genes = individual.getGenes();
		List<Double> params = individual.getStrategyParameters().getParameters();
		
		// loop through genes/strategy parameters
		for (int index = 0; index < genes.size(); index++) {
			// get gene from list
			Gene gene = genes.get(index);
			// get corresponding strategy parameter
			double param = params.get(index);
			// get current value of gene
			double value = gene.getValue();
			// update gene's value using "val = val + N(0, param)"
			gene.setValue(value + random.nextGaussian()*param);
		}
	}
	
	/**
	 * Updates the strategy parameters randomly.
	 * (May also use the 1/5th rule)
	 * 
	 * @param individual	The individual whose strategy parameters will be modified.
	 */
	public void mutateStrategyParameters(Individual individual) {
		
		// get the strategy parameters for the individual
		List<Double> params = individual.getStrategyParameters().getParameters();
		// loop through all strategy parameters
		for (Double param : params) {
			// randomly increase strategy parameter
			if (random.nextDouble() < 0.5) {
				param += stepSize;
			// randomly decrease strategy parameter
			} else {
				param -= stepSize;
			}
		}
		
	}

}

