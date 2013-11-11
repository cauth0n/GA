package ga.fitness;

import ga.Individual;
import ga.Population;

import java.util.List;

import neural_net.Network;
import driver.DataPoint;
import driver.TrainingMethod;

public class FitnessDefault extends Fitness {
	
	/**
	 * Calculate the fitness based on how well the individuals' 
	 * performances at a specific task 
	 * (in this case training a neural network).
	 * 
	 * @param popoulation		The population of individuals whose fitnesses will be evaluated.
	 * @param neuralNetwork		The neural network that will be used to test fitness.
	 * @param testSet			The set of test data to evaluate fitness with.
	 */
	public void calculateFitness(Population population, Network neuralNetwork, List<DataPoint> testSet) {
		double fitness = 0.0;
		
		// loop through every individual in the population
		for (Individual individual : population.getIndividuals()){
		
			// convert the individual's genes to network weights
			neuralNetwork.setWeights(individual.getGenes());
			
			//feed test set forward in network
			List<Double> outputs;
			int correct = 0;
			// loop through all data points
			for (DataPoint dataPoint : testSet) {
				// feed data point through network
				outputs = TrainingMethod.networkOperations.feedForward(dataPoint);
				// get class found by neural network
				int classFound = TrainingMethod.networkOperations.getMaxIndex(outputs);
				// store class expected from data point
				int classExpected = dataPoint.getClassIndex();
				// increment count if classification was correct
				if (classFound == classExpected)
					correct++;
				outputs.clear();
			}
			
			// divide by testSet size to get % correct
			fitness = ((double) correct) / testSet.size();
			
			// set individual's fitness as % correct
			individual.setFitness(fitness);
		}
		
	}

}

