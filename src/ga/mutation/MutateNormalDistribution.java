package ga.mutation;

import ga.Gene;
import ga.Individual;
import ga.Population;

import java.util.List;
import java.util.Random;

public class MutateNormalDistribution extends Mutate {
	
	Random random = new Random(11235);
	double stepSize = 0.1;
	
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
			gene.setValue(value + random.nextGaussian()*param);
		}
	}
	
	public void mutateStrategyParameters(Individual individual) {
		
		List<Double> params = individual.getStrategyParameters().getParameters();
		for (Double param : params) {
			if (random.nextDouble() < 0.5) {
				param += stepSize;
			} else {
				param -= stepSize;
			}
		}
		
	}

}

