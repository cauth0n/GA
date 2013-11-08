package ga;

import ga.crossover.Crossover;
import ga.crossover.CrossoverUniform;
import ga.fitness.Fitness;
import ga.fitness.FitnessDefault;
import ga.initialize.Initialize;
import ga.initialize.InitializeWithStrategyParameters;
import ga.mutation.Mutate;
import ga.mutation.MutateNormalDistribution;
import ga.selection.Selection;
import ga.selection.SelectionRandom;

import java.util.List;
import java.util.Random;

import neural_net.Network;
import driver.DataPoint;

public class ES {

	private Random random = new Random(11235);;
	private Population population;
	private double mutationProbability;
	private Selection selection;
	private Fitness fitness;
	private Mutate mutate;
	private Crossover crossover;
	
	private int mu;
	private int lambda;
	private String type = "+";

	public ES(int mu, int lambda, int chromosomeSize, double mutationProbability) {
		this.mu = mu;
		this.lambda = lambda;
		checkParams();
		
		Initialize init = new InitializeWithStrategyParameters();
		this.population = init.initializePopulation(mu, chromosomeSize);
		this.mutationProbability = mutationProbability;
		this.fitness = new FitnessDefault();
		this.selection = new SelectionRandom(this.fitness);
		this.selection.setReturnSize(lambda);
		this.mutate = new MutateNormalDistribution();
		this.crossover = new CrossoverUniform();
	}

	public void runGeneration() {
		
		// choose p >= 2 parents for each lambda
		selection.select(population);
		
		// create 1 offspring through crossover
		Population children = crossover.crossOver(population, selection.getMatingPlan());
		
		// mutate the offspring (features & strategy parameters)
		children = mutate.mutate(children, mutationProbability);
		

		// construct pool of individuals based on algorithm type
		if (this.type.equals("+")) {
			population.add(children);
		} else if (this.type.equals(",")) {
			population = children;
		}

	}

	public Population getPopulation() {
		return population;
	}
	
	public void setPopulation(Population population) {
		this.population = population;
	}
	
	public void setTypePlus() {
		this.type = "+";
		checkParams();
	}
	
	public void setTypeComma() {
		this.type = ",";
		checkParams();
	}
	
	private void checkParams() {
		if (type.equals("+")) {
			if (mu < 1 || mu > lambda) {
				throw new IllegalArgumentException("Invalid parameter mu. Must be of the form: 1 <= mu <= lambda.");
			}
		} else if (type.equals(",")) {
			if (mu < 1 || mu >= lambda) {
				throw new IllegalArgumentException("Invalid parameter mu. Must be of the form: 1 <= mu < lambda.");
			}
		} else {
			throw new IllegalArgumentException("Invalid type.");
		}
	}
	
	public String toString() {
		return "("+mu+" "+type+" "+lambda+") - ES";
	}

}
