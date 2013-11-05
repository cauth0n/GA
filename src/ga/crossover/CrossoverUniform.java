package ga.crossover;

import ga.Gene;
import ga.Individual;

import java.util.ArrayList;
import java.util.List;

public class CrossoverUniform extends Crossover {

	double probability = 0.5;	// large numbers favor individual1, small favors individual2
	
	CrossoverUniform() {
		
	}
	
	CrossoverUniform(double probability) {
		this.probability = probability;
	}
	
	public List<Individual> crossOverTwoChildren(Individual individual1, Individual individual2) {
		
		// create list of children
		List<Individual> children = new ArrayList<Individual>();
		
		// get chromosomes from individuals
		List<Gene> chromosome1 = individual1.getGenes();
		List<Gene> chromosome2 = individual2.getGenes();
		
		// create two new chromosomes
		List<Gene> newChromosome1 = new ArrayList<Gene>();
		List<Gene> newChromosome2 = new ArrayList<Gene>();
		
		// for each gene, choose from each parent with a certain probability
		for (int gene = 0; gene < chromosome1.size(); gene++) {
			if (random.nextDouble() < probability) {
				newChromosome1.add(chromosome1.get(gene));
				newChromosome2.add(chromosome2.get(gene));
			} else {
				newChromosome1.add(chromosome2.get(gene));
				newChromosome2.add(chromosome1.get(gene));
			}
		}
		
		// create two children with newly formed chromosomes
		children.add(new Individual(chromosome1));
		children.add(new Individual(chromosome2));
		
		return children;
	}

}

