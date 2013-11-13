package ga.mutation;

import ga.Gene;
import ga.Individual;
import ga.Population;

import java.util.ArrayList;
import java.util.List;

public class MutateTrialVector extends Mutate {
	
	// large = big step sizes, small = small step sizes, empirical studies suggest 0.5
	double beta = 0.5;
	
	public Individual mutate(Individual individual, Population population) {
		
		Individual newIndividual = null;
		
		// assign random individuals from the population to two individuals
		Individual random1 = population.getRandomIndividual();
		Individual random2 = population.getRandomIndividual();
		
		// ensure random1 is not equal to original individual
		while (random1 == individual)
			random1 = population.getRandomIndividual();
		
		// ensure random2 is not equal to original individual or random1
		while (random2 == random1 || random2 == individual)
			random2 = population.getRandomIndividual();
		
		// get vector of each random individual and original individual
		List<Gene> vector0 = individual.getGenes();
		List<Gene> vector1 = random1.getGenes();
		List<Gene> vector2 = random2.getGenes();
		
		// loop through each element of vectors
		List<Gene> trial = new ArrayList<Gene>();
		for (int gene = 0; gene < vector1.size(); gene++) {
			// subtract vector2 from vector1
			Gene diff = vector1.get(gene).subtract(vector2.get(gene));
			// multiply each element by beta
			Gene product = diff.scalarMultiply(beta);
			// add product to original vector element to product trial vector element
			trial.add(vector0.get(gene).add(product));
		}
			
		
		return newIndividual;
	}

}

