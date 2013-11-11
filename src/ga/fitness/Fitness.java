package ga.fitness;

import ga.Individual;
import ga.Population;

import java.util.List;

import neural_net.Network;
import driver.DataPoint;

/**
 * Abstract fitness calculation class.
 */
public abstract class Fitness {
	
	/**
	 * Calculate the fitness based on how well the individuals' 
	 * performances at a specific task 
	 * (in this case training a neural network).
	 * 
	 * @param popoulation		The population of individuals whose fitnesses will be evaluated.
	 * @param neuralNetwork		The neural network that will be used to test fitness.
	 * @param testSet			The set of test data to evaluate fitness with.
	 */
	public abstract void calculateFitness(Population popoulation, Network neuralNetwork, List<DataPoint> testSet);

}

