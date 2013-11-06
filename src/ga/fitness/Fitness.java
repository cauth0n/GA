package ga.fitness;

import ga.Individual;
import ga.Population;

import java.util.List;

import neural_net.Network;
import driver.DataPoint;

public abstract class Fitness {
	
	public abstract void calculateFitness(Population popoulation, Network neuralNetwork, List<DataPoint> testSet);

	/**
	 * Returns the sum of every fitness for entire population.
	 */
	public double getPopulationFitnessSum(Population population) {
		double fitness = 0.0;
		for (Individual individual : population.getIndividuals())
			fitness += individual.getFitness();
		return fitness;
	}
	
	/**
	 * Returns average fitness for entire population.
	 */
	public double getPopulationFitnessAverage(Population population) {
		return getPopulationFitnessSum(population) / population.size();
	}

}

