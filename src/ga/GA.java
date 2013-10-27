package ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GA {
	
	private Random random;
	private int maxIterations = 100;
	private double fitnessThreshold = 0.8;
	Population population;
	double mutationProbability = 0.05;
	double probability;
	Selection selection;
	Fitness fitness;
	Mutate mutate;
	Crossover crossover;
	
	public GA(int populationSize, double probability) {
		Initialize init = new InitializeDefault();
		this.population = init.initializePopulation(populationSize);
		this.probability = probability;
		this.fitness = new FitnessDefault();
		this.selection = new SelectionRankBased(this.population, this.fitness);
		this.mutate = new MutateNormalDistribution();
		this.crossover = new CrossoverNPointOneChild();
		random = new Random(11235);
	}

	public Individual run() {
		Individual mostFit;
		int timestep = 0;
		// continue until a fit enough individual or max iterations have been reached
		do {
			// create a new population of the same size as the current population
			List<Individual> newPopulation = new ArrayList<Individual>();
			while (newPopulation.size() < population.getSize()) {
				// select parents from current population
				List<Individual> parents = selection.select();
				Individual parent1 = parents.get(0);
				Individual parent2 = parents.get(1);
				// reproduce a single offspring
				Individual child = crossover.crossOver(parent1, parent2).get(0);
				// with some small probability, mutate child
				if (random.nextDouble() < mutationProbability)
					child = mutate.mutate(child, population);
				// add child to individual list for the new population
				newPopulation.add(child);
			}
			// create new population from list of child individuals
			population = new Population(newPopulation);
			// evaluate every individual's fitness
			population.evaluateFitness(fitness);
			// retrieve most fit individual
			mostFit = population.getMostFit();
			// move to next time step
			timestep++;
		} while (mostFit.getFitness() < fitnessThreshold && timestep < maxIterations);
		
		return mostFit;
	}

}
