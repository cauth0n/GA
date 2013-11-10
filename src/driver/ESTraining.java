package driver;

import ga.ES;
import ga.Individual;
import ga.fitness.FitnessDefault;
import ga.prune.Prune;
import ga.prune.PruneDefault;

import java.util.List;

import neural_net.Network;

/**
 * Driver for the Evolution Strategy training algorithm.
 * Provides an interface between the neural_net and ga packages.
 */
public class ESTraining extends TrainingMethod {
	
	private ES es;
	private double mutationProbability = 1.0;
	int maxIterations = 1000;
	private int mu = 20;
	private int lambda = 30;
	private Prune prune;
	private Individual best = null;

	/**
	 * Constructs a new instance of the ESTraining class.
	 * 
	 * @param neuralNetwork	The network that will be trained.
	 * @param data			The training data to use.
	 */
	public ESTraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
		fitnessMethod = new FitnessDefault();
		es = new ES(mu, lambda, neuralNetwork.size(), mutationProbability);
		es.setTypePlus();
		//es.setTypeComma();
		prune = new PruneDefault();
	}

	/**
	 * Trains the ES using the training set provided.
	 */
	public void train(List<DataPoint> trainSet) {
		
		// calculate fitness for entire population using the neural net and all training examples
		fitnessMethod.calculateFitness(es.getPopulation(), neuralNetwork, trainSet);
		es.getPopulation().sortPopulationByFitness();

		// loop through generations until the algorithm 
		// stops improving for a certain number of iterations
		for (int iteration = 0; iteration < maxIterations; iteration++) {
			
			// run the ES for the current generation
			es.runGeneration();
			
			// calculate fitness for entire population using the neural net and all training examples
			fitnessMethod.calculateFitness(es.getPopulation(), neuralNetwork, trainSet);
			es.getPopulation().sortPopulationByFitness();
			
			// prune the lambda or mu + lambda individuals down to a size of mu
			es.setPopulation(prune.prune(es.getPopulation(), mu));

			// retrieve the most fit from the current generation
			Individual mostFit = es.getPopulation().getMostFit();
			
			// compare this generations most fit with the best individual seen overall
			if (setBest(mostFit)) {
				// if current individual is the better than previous individuals
				// reset the iteration count so as to keep exploring.
				iteration = 0;
			}
			
			System.out.println(mostFit.getFitness());
			
		}

		neuralNetwork.setWeights(best.getGenes());
		System.out.println("BEST: "+best.getFitness());
		
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
	
	/**
	 * Print a string representation of the training method
	 * in the form of "(mu * lambda) - ES"
	 */
	public String toString() {
		return es.toString();
	}

}
