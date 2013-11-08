package ga.crossover;

import ga.Gene;
import ga.Individual;
import ga.StrategyParameters;

import java.util.ArrayList;
import java.util.List;

public class CrossoverUniform extends Crossover {

	double probability = 0.5;	// large numbers favor individual1, small favors individual2
	
	public CrossoverUniform() {
		
	}
	
	public CrossoverUniform(double probability) {
		this.probability = probability;
	}
	
	protected List<Individual> crossOverParents(Individual individual1, Individual individual2) {
		
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
		
		Individual child1 = new Individual(newChromosome1);
		Individual child2 = new Individual(newChromosome2);
		
		if (individual1.hasStrategyParameters() && individual2.hasStrategyParameters()) {
			crossoverStrategyParameters(individual1, individual2, child1, child2);
		}
		
		// create two children with newly formed chromosomes
		children.add(child1);
		children.add(child2);
		
		return children;
	}
	
	private void crossoverStrategyParameters(Individual parent1, Individual parent2, Individual child1, Individual child2) {
		
		// get chromosomes from individuals
		List<Double> parameters1 = parent1.getStrategyParameters().getParameters();
		List<Double> parameters2 = parent2.getStrategyParameters().getParameters();
		
		// create two new chromosomes
		List<Double> newParameters1 = new ArrayList<Double>();
		List<Double> newParameters2 = new ArrayList<Double>();
		
		// for each gene, choose from each parent with a certain probability
		for (int param = 0; param < parameters1.size(); param++) {
			if (random.nextDouble() < probability) {
				newParameters1.add(parameters1.get(param));
				newParameters2.add(parameters2.get(param));
			} else {
				newParameters1.add(parameters2.get(param));
				newParameters2.add(parameters1.get(param));
			}
		}
		
		child1.setStrategyParameters(new StrategyParameters(newParameters1));
		child2.setStrategyParameters(new StrategyParameters(newParameters2));
		
	}

}

