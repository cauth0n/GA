package ga;

import ga.crossover.Crossover;
import ga.crossover.CrossoverNPoint;
import ga.fitness.Fitness;
import ga.fitness.FitnessDefault;
import ga.initialize.Initialize;
import ga.initialize.InitializeDefault;
import ga.mutation.Mutate;
import ga.mutation.MutateUniformDistribution;
import ga.selection.Selection;
import ga.selection.SelectionRankBasedElitist;

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
		this.population = init.initializePopulation(populationSize,
				chromosomeSize);
		this.mutationProbability = mutationProbability;
		this.fitness = new FitnessDefault();
		// this.selection = new SelectionTournament(this.population,
		// this.fitness, 5);
		// this.selection = new SelectionRankBased(this.population,
		// this.fitness);
		// this.selection = new
		// SelectionRankBasedExtremePreservation(this.population, this.fitness);
		this.selection = new SelectionRankBasedElitist(this.population,
				this.fitness);
		this.mutate = new MutateUniformDistribution();
		this.crossover = new CrossoverNPoint();
		random = new Random(11235);
	}

	public void runGeneration() {

		// ensure the selection is pointing at the correct population
		selection.setPopulation(population);

		// create a new population of the same size as the current population
		List<Individual> newPopulation = new ArrayList<Individual>();
		while (newPopulation.size() < population.getSize()) {

			// select parents from current population
			List<Individual> parents = selection.select();

			Individual parent1 = parents.get(0);
			Individual parent2 = parents.get(1);
			// reproduce a single offspring
			List<Individual> children = crossover.crossOverTwoChildren(parent1,
					parent2);
			// with some small probability, mutate child
			for (Individual child : children) {
				if (child.canMutate()) {
					if (random.nextDouble() < mutationProbability) {
						child = mutate.mutate(child, population);
					}
				}
				// add child to individual list for the new population
				newPopulation.add(child);
			}

		}

		// create new population from list of child individuals
		population = new Population(newPopulation);

	}

	public Population getPopulation() {
		return population;
	}

}
