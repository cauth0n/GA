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
import ga.selection.SelectionRankBasedExtremePreservation;

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
		//this.selection = new SelectionTournament(this.population, this.fitness, 5);
		// this.selection = new SelectionRankBased(this.population,
		// this.fitness);
		this.selection = new SelectionRankBasedExtremePreservation(this.fitness);
		//this.selection = new SelectionRankBasedElitist(this.population, this.fitness);
		this.mutate = new MutateUniformDistribution();
		this.crossover = new CrossoverNPoint();
		random = new Random(11235);
	}

	public void runGeneration() {

		// ensure the selection is pointing at the correct population
		selection.select(population);
		
		Population children = crossover.crossOver(population, selection.getMatingPlan());
		
		population = mutate.mutate(children);

	}

	public Population getPopulation() {
		return population;
	}

}
