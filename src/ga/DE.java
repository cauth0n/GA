package ga;

import ga.crossover.Crossover;
import ga.crossover.CrossoverNPoint;
import ga.fitness.Fitness;
import ga.fitness.FitnessDefault;
import ga.initialize.Initialize;
import ga.initialize.InitializeDefault;
import ga.mutation.Mutate;
import ga.mutation.MutateTrialVector;
import ga.selection.MatingPlan;
import ga.trialVector.TrialVector;
import ga.trialVector.TrialVectorBest;

import java.util.ArrayList;
import java.util.List;

import neural_net.Network;
import driver.DataPoint;

/**
 * Differential Evolution Algorithm.
 */
public class DE {

	private Population population;
	private double beta;
	private double probability;
	private Fitness fitness;
	private Mutate mutate;
	private Crossover crossover;

	/**
	 * Initializes parameters.
	 * 
	 * @param populationSize
	 *            The size of the population to construct
	 * @param beta
	 *            The beta value for the algorithm (empirical studies
	 *            suggest 0.5)
	 * @param probability
	 *            The probability for the algorithm (higher values
	 *            mean more diverse offspring)
	 */
	public DE(int populationSize, int chromosomeSize, double beta, double probability) {
		Initialize init = new InitializeDefault();
		this.population = init.initializePopulation(populationSize, chromosomeSize);
		this.beta = beta;
		this.probability = probability;
		this.fitness = new FitnessDefault();
		this.mutate = new MutateTrialVector();
		this.crossover = new CrossoverNPoint();
	}

	public void runGeneration(Network neuralNetwork, List<DataPoint> trainSet) {
		
		ArrayList<Individual> newPopulation = new ArrayList<Individual>();
		MatingPlan plan = new MatingPlan();

		for (Individual individual : population.getIndividuals()) {
			Individual bestInPop = population.getMostFit();

			Individual two = population.getRandomIndividual();
			while (!two.geneticallyEquals(bestInPop) && !two.geneticallyEquals(individual)) {
				two = population.getRandomIndividual();
			}
			Individual three = population.getRandomIndividual();
			while (!three.geneticallyEquals(bestInPop) && !three.geneticallyEquals(two) && !three.geneticallyEquals(individual)) {
				three = population.getRandomIndividual();
			}
			TrialVector trialVector = new TrialVectorBest(bestInPop, two, three, beta);
			plan.add(individual, trialVector.getTrialVector());
		}

		// need to make sure the individual objects in the population
		// are preserved.
		Population newPop = crossover.crossOver(population, plan);

		fitness.calculateFitness(newPop, neuralNetwork, trainSet);

		
		for (int i = 0; i < population.size(); i++) {
			Individual oldIndividual = population.getIndividuals().get(i);
			Individual newIndividual = newPop.getIndividuals().get(i);

			if (newIndividual.getFitness() > oldIndividual.getFitness()) {
				newPopulation.add(newIndividual);
			} else {
				newPopulation.add(oldIndividual);
			}

		}

		population = new Population(newPopulation);
	}

	public Population getPopulation() {
		return population;
	}

}
