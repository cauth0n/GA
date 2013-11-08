package ga.mutation;

import ga.Gene;
import ga.Individual;
import ga.Population;

import java.util.List;

public class MutateNormalDistribution extends Mutate {
	
	public Individual mutate(Individual individual, Population population) {
		Individual newIndividual = individual.copy();
		
		mutateGenes(newIndividual);
		mutateStrategyParameters(newIndividual);
		
		return newIndividual;
	}
	
	public void mutateGenes(Individual individual) {
		if (!individual.hasStrategyParameters())
			return;
		
		List<Gene> genes = individual.getGenes();
		List<Double> params = individual.getStrategyParameters().getParameters();
		for (int index = 0; index < genes.size(); index++) {
			Gene gene = genes.get(index);
			double param = params.get(index);
			double value = gene.getValue();
			// TODO: not sure if this is right. should it be a random value in the distribution?
			gene.setValue(value + normalDistribution(value, 0, param));
		}
	}
	
	public void mutateStrategyParameters(Individual individual) {
		
		List<Individual> parents = individual.getParents();
		// do not mutate params if this is the first iteration
		if (parents == null)
			return;
		
		System.out.println(individual.getFitness() + " vs " + parents.get(0).getFitness());
		
	}
	
	public double normalDistribution(double input, double mu, double sigma) {
		return Math.exp((-1/2) * ((input - mu) / sigma));
	}

}

