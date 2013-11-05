package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GA {
	
	private Random random;
	private Population population;
	private double mutationProbability;
	private Selection selection;
	private Fitness fitness;
	private Mutate mutate;
	private Crossover crossover;
	
	public GA(int populationSize, int chromosomeSize, double mutationProbability) {
		Initialize init = new InitializeDefault();
		this.population = init.initializePopulation(populationSize, chromosomeSize);
		this.mutationProbability = mutationProbability;
		this.fitness = new FitnessDefault();
		this.selection = new SelectionFitnessProportionate(this.population, this.fitness);
		this.mutate = new MutateNormalDistribution();
		this.crossover = new CrossoverNPoint();
		random = new Random(11235);
	}

	public void runGeneration() {

		// create a new population of the same size as the current population
		List<Individual> newPopulation = new ArrayList<Individual>();
		while (newPopulation.size() < population.getSize()) {
			// select parents from current population
			List<Individual> parents = selection.select();
			Individual parent1 = parents.get(0);
			Individual parent2 = parents.get(1);
			// reproduce a single offspring
			Individual child = crossover.crossOverOneChild(parent1, parent2);
			// with some small probability, mutate child
			if (random.nextDouble() < mutationProbability)
				child = mutate.mutate(child, population);
			// add child to individual list for the new population
			newPopulation.add(child);
		}

		
		// create new population from list of child individuals
		population = new Population(newPopulation);

	}

	public Population getPopulation() {
		return population;
	}
	
	

}
