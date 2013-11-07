package ga.mutation;

import ga.Gene;
import ga.Individual;
import ga.Population;

import java.util.Random;

public class MutateUniformDistribution extends Mutate {
	
	private static int count = 1;
	
	public Individual mutate(Individual individual, Population population) {
		
		Individual newIndividual = individual.copy();
		
		for (Gene gene : newIndividual.getGenes()){
			double mutationValue = getMutationValue();
			gene.setValue(gene.getValue() + mutationValue);
		}
		
		return newIndividual;
	}
	
	public double getMutationValue(){
		Random rand = new Random(11235 * count++);
		double value = (-1 * spread) + (rand.nextDouble() * (spread + spread));
		return value;
	}

}

