package ga.crossover;

import ga.Gene;
import ga.Individual;
import ga.StrategyParameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CrossoverNPoint extends Crossover {

	private int n = 1;

	public CrossoverNPoint() {

	}

	CrossoverNPoint(int crossovers) {
		this.n = crossovers;
	}

	protected List<Individual> crossOverParents(Individual individual1, Individual individual2) {
		
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
		while (crossovers.size() < n) {
			int split = random.nextInt(chromosome1.size());
			crossovers.add(split);
		}
		
		// for each gene, choose from each parent based on which crossover we are on
		boolean useChromosome1 = true;
		for (int gene = 0; gene < chromosome1.size(); gene++) {
			// if this is a crossover location, toggle boolean
			if (crossovers.contains(gene))
				useChromosome1 = !useChromosome1;
			// use chromosome one for primary child
			if (useChromosome1) {
				newChromosome1.add(chromosome1.get(gene));
				newChromosome2.add(chromosome2.get(gene));
			// use chromosome two for primary child
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
	
	public void crossoverStrategyParameters(Individual parent1, Individual parent2, Individual child1, Individual child2) {
		
		// get strategy parameters from individuals
		List<Double> params1 = parent1.getStrategyParameters().getParameters();
		List<Double> params2 = parent2.getStrategyParameters().getParameters();
		
		// create two new strategy parameters
		List<Double> newParams1 = new ArrayList<Double>();
		List<Double> newParams2 = new ArrayList<Double>();
		
		// create sorted set of crossover points
		TreeSet<Integer> crossovers = new TreeSet<Integer>();
		
		// pick 'n' random crossover points in strategy parameters
		while (crossovers.size() < n) {
			int split = random.nextInt(params1.size());
			crossovers.add(split);
		}
		
		// for each strategy parameter, choose from each parent based on which crossover we are on
		boolean useParamList1 = true;
		for (int param = 0; param < params1.size(); param++) {
			// if this is a crossover location, toggle boolean
			if (crossovers.contains(param))
				useParamList1 = !useParamList1;
			// use strategy parameter one for primary child
			if (useParamList1) {
				newParams1.add(params1.get(param));
				newParams2.add(params2.get(param));
			// use strategy parameter two for primary child
			} else {
				newParams1.add(params2.get(param));
				newParams2.add(params1.get(param));
			}
		}
		
		StrategyParameters strategyParameters1 = new StrategyParameters(newParams1);
		StrategyParameters strategyParameters2 = new StrategyParameters(newParams2);
		
		child1.setStrategyParameters(strategyParameters1);
		child2.setStrategyParameters(strategyParameters2);
		
	}
}
