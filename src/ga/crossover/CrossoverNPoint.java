package ga.crossover;

import ga.Gene;
import ga.Individual;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public class CrossoverNPoint extends Crossover {
	
	private int n = 2;
	
	public CrossoverNPoint() {
		
	}
	
	CrossoverNPoint(int crossovers) {
		this.n = crossovers;
	}
	
	public List<Individual> crossOverTwoChildren(Individual individual1, Individual individual2) {
		
		// create list of children
		List<Individual> children = new ArrayList<Individual>(2);
		
		// get chromosomes from individuals
		List<Gene> chromosome1 = individual1.getGenes();
		List<Gene> chromosome2 = individual2.getGenes();
		
		// create two new chromosomes
		List<Gene> newChromosome1 = new ArrayList<Gene>();
		List<Gene> newChromosome2 = new ArrayList<Gene>();
		
		// create sorted set of crossover points
		Set<Integer> crossovers = new TreeSet<Integer>();
		
		// pick 'n' random crossover points in chromosome
		while (crossovers.size() < n)
			crossovers.add(random.nextInt(chromosome1.size()));
		
		// for each gene, choose from each parent based on which crossover we are on
		boolean useChomosome1 = true;
		for (int gene = 0; gene < chromosome1.size(); gene++) {
			// if this is a crossover location, toggle boolean
			if (crossovers.contains(gene))
				useChomosome1 = !useChomosome1;
			// use chromosome one for primary child
			if (useChomosome1) {
				newChromosome1.add(chromosome1.get(gene));
				newChromosome2.add(chromosome2.get(gene));
			// use chromosome two for primary child
			} else {
				newChromosome1.add(chromosome2.get(gene));
				newChromosome2.add(chromosome1.get(gene));
			}
		}
		
		Individual child1 = new Individual(chromosome1);
		Individual child2 = new Individual(chromosome2);
		
		// create two children with newly formed chromosomes
		children.add(child1);
		children.add(child2);
		
		return children;
	}

}

