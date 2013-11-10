package driver;

import ga.DE;
import ga.Individual;
import ga.fitness.FitnessDefault;
import java.util.List;
import neural_net.Network;

/**
 * Driver for the Differential Evolution training algorithm.
 * Provides an interface between the neural_net and ga packages.
 */
public class DETraining extends TrainingMethod {

	private DE de;
	private int populationSize = 50;
	private double beta = 0.5;
	private double mutationProbability = 0.05;
	int maxIterations = 1000;
	private Individual best;

	/**
	 * Constructs a new instance of the DETraining class.
	 * 
	 * @param neuralNetwork	The network that will be trained.
	 * @param data			The training data to use.
	 */
	public DETraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
		// use default fitness method
		fitnessMethod = new FitnessDefault();
		// create a new instance of the differential evolution algorithm with specified params
		de = new DE(populationSize, neuralNetwork.size(), beta, mutationProbability);
	}

	/**
	 * Trains the DE using the training set provided.
	 */
	public void train(List<DataPoint> trainSet) {

		// calculate fitness for entire population using the neural net and all training examples
		fitnessMethod.calculateFitness(de.getPopulation(), neuralNetwork, trainSet);
		de.getPopulation().sortPopulationByFitness();

		Individual mostFit = null;

		// loop through generations until the algorithm 
		// stops improving for a certain number of iterations
		for (int iteration = 0; iteration < maxIterations; iteration++) {

			// run the DE for the current generation
			de.runGeneration(neuralNetwork, trainSet);

			// calculate fitness for entire population using the neural net and all training examples
			fitnessMethod.calculateFitness(de.getPopulation(), neuralNetwork, trainSet);
			de.getPopulation().sortPopulationByFitness();

			// retrieve the most fit from the current generation
			mostFit = de.getPopulation().getMostFit();
			
			// compare this generations most fit with the best individual seen overall
			if (setBest(mostFit)) {
				// if current individual is the better than previous individuals
				// reset the iteration count so as to keep exploring.
				iteration = 0;
			}

			System.out.println(mostFit.getFitness());
			
		}

		// set the neural network to the solution found during training
		neuralNetwork.setWeights(best.getGenes());

	}
	
	/**
	 * Sets the individual with the max fitness as the new best.
	 * 
	 * @param mostFit	The individual to compare with the best.
	 * @return			True if the specified individual is the new best.
	 */
	public boolean setBest(Individual mostFit) {
		boolean better = false;
		// if fitness is greater, set the mostFit that was passed in as the new best
		if (best == null || mostFit.getFitness() > best.getFitness()) {
			best = mostFit.copy();
			better = true;
		}
		return better;
	}
	
}
