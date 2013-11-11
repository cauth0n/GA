package ga.crossover;

import ga.Gene;
import ga.Individual;
import ga.StrategyParameters;

import java.util.ArrayList;
import java.util.List;

public class CrossoverUniform extends Crossover {

	double probability;
	
	/**
	 * Creates a crossover method with even probability of choosing either parent.
	 */
	public CrossoverUniform() {
		this.probability = 0.5;
	}
	
	/**
	 * Creates a crossover method with specified probability of choosing parent 1.
	 * 
	 * @param probability	Probability of choosing parent 1 for child 1 when selecting genes.
	 */
	public CrossoverUniform(double probability) {
		this.probability = probability;
	}
	
	/**
	 * Performs crossover given two parents.
	 * Each gene is taken from parent1 with a certain 
	 * probability, and parent2 otherwise.
	 * 
	 * @param parent1	The first parent to be used in crossover.
	 * @param parent2	The second parent to be used in crossover.
	 * @return			A list containing the two children that are created during crossover.
	 */
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
			// choose parent 1 for child 1
			if (random.nextDouble() < probability) {
				newChromosome1.add(chromosome1.get(gene));
				newChromosome2.add(chromosome2.get(gene));
			// choose parent 2 for child 1
			} else {
				newChromosome1.add(chromosome2.get(gene));
				newChromosome2.add(chromosome1.get(gene));
			}
		}
		
		// create children from constructed chromosome lists
		Individual child1 = new Individual(newChromosome1);
		Individual child2 = new Individual(newChromosome2);
		
		// perform crossover on strategy parameters if the individual has them
		if (individual1.hasStrategyParameters() && individual2.hasStrategyParameters()) {
			crossoverStrategyParameters(individual1, individual2, child1, child2);
		}
		
		// create two children with newly formed chromosomes
		children.add(child1);
		children.add(child2);
		
		// return this list of 2 children
		return children;
	}
	
	/**
	 * Performs same crossover method on strategy parameters if used.
	 * 
	 * @param parent1	The first parent to be used in crossover.
	 * @param parent2	The second parent to be used in crossover.
	 * @param child1	The first child created in the previous step of crossover.
	 * @param child2	The second child created in the previous step of crossover.
	 */
	private void crossoverStrategyParameters(Individual parent1, Individual parent2, Individual child1, Individual child2) {
		
		// get chromosomes from individuals
		List<Double> parameters1 = parent1.getStrategyParameters().getParameters();
		List<Double> parameters2 = parent2.getStrategyParameters().getParameters();
		
		// create two new chromosomes
		List<Double> newParameters1 = new ArrayList<Double>();
		List<Double> newParameters2 = new ArrayList<Double>();
		
		// for each strategy parameter, choose from each parent with a certain probability
		for (int param = 0; param < parameters1.size(); param++) {
			// choose parent 1 for child 1
			if (random.nextDouble() < probability) {
				newParameters1.add(parameters1.get(param));
				newParameters2.add(parameters2.get(param));
			// choose parent 2 for child 1
			} else {
				newParameters1.add(parameters2.get(param));
				newParameters2.add(parameters1.get(param));
			}
		}
		
		// add strategy parameters to children created from previous crossover step
		child1.setStrategyParameters(new StrategyParameters(newParameters1));
		child2.setStrategyParameters(new StrategyParameters(newParameters2));
		
	}

}

