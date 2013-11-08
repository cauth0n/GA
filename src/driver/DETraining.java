package driver;

import ga.DE;
import ga.GA;
import ga.Individual;
import ga.fitness.FitnessDefault;

import java.util.List;
import java.util.Map;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.Network;
import neural_net.Neuron;

/**
 * @author cauthon
 */
public class DETraining extends TrainingMethod {

	private DE de;
	private int populationSize = 50;
	private double beta = 0.5;
	private double mutationProbability = 0.05;
	private double fitnessThreshold = 1.1;
	int maxIterations = 1000;

	public DETraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
		fitnessMethod = new FitnessDefault();
		de = new DE(populationSize, neuralNetwork.size(), beta, mutationProbability);
	}

	public void train(List<DataPoint> trainSet) {

		fitnessMethod.calculateFitness(de.getPopulation(), neuralNetwork, trainSet);

		Individual mostFit = null;
		double mostFitValue = 0;

		// fitness < minFitness
		int count = 0;
		while (maxIterations > 0 && mostFitValue < fitnessThreshold) {

			maxIterations--;

			de.runGeneration(neuralNetwork, trainSet);

			fitnessMethod.calculateFitness(de.getPopulation(), neuralNetwork, trainSet);

			mostFit = de.getPopulation().getMostFit();
			mostFitValue = mostFit.getFitness();

			//System.out.println(de.getPopulation().getAverageFitness());
			System.out.println(mostFitValue + "   Average: " + de.getPopulation().getAverageFitness());
			count++;

		}

		neuralNetwork.setWeights(mostFit.getGenes());

	}
}
