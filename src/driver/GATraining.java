package driver;

import ga.GA;
import ga.Individual;
import ga.fitness.FitnessDefault;

import java.util.List;

import neural_net.Network;

/**
 * Driver for the Genetic Algorithm training algorithm.
 * Provides an interface between the neural_net and ga packages.
 */
public class GATraining extends TrainingMethod {

	private GA ga;
	private int populationSize = 40;
	private double mutationProbability = 0.9;
	int maxIterations = 100;
	private Individual best = null;

	/**
	 * Constructs a new instance of the GATraining class.
	 * 
	 * @param neuralNetwork	The network that will be trained.
	 * @param data			The training data to use.
	 */
	public GATraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
		fitnessMethod = new FitnessDefault();
		ga = new GA(populationSize, neuralNetwork.size(), mutationProbability);

	}

	/**
	 * Trains the GA using the training set provided.
	 */
	public void train(List<DataPoint> trainSet) {

		// calculate fitness for entire population using the neural net and all training examples
		fitnessMethod.calculateFitness(ga.getPopulation(), neuralNetwork, trainSet);
		ga.getPopulation().sortPopulationByFitness();

		// loop through generations until the algorithm 
		// stops improving for a certain number of iterations
		for (int iteration = 0; iteration < maxIterations; iteration++) {
			
			// run the GA for the current generation
			ga.runGeneration();
			
			// calculate fitness for entire population using the neural net and all training examples
			fitnessMethod.calculateFitness(ga.getPopulation(), neuralNetwork, trainSet);
			ga.getPopulation().sortPopulationByFitness();
			
			// retrieve the most fit from the current generation
			Individual mostFit = ga.getPopulation().getMostFit();
			
			// compare this generations most fit with the best individual seen overall
			if (setBest(mostFit)) {
				// if current individual is the better than previous individuals
				// reset the iteration count so as to keep exploring.
				iteration = 0;
			}

			//System.out.println(mostFit.getFitness());

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
