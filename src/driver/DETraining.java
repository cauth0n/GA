package driver;

import ga.DE;
import ga.Individual;

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
	private double mutationProbability = 0.05;
	private double fitnessThreshold = 1.1;
	int maxIterations = 1000;

	public DETraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
	}

	public void train(List<DataPoint> trainSet) {
		
		fitnessMethod.calculateFitness(de.getPopulation(), neuralNetwork, trainSet);
		
		Individual mostFit = null;
		double mostFitValue = 0;
		
		//fitness < minFitness
		int count = 0;
		while(maxIterations > 0 && mostFitValue < fitnessThreshold){
			
			maxIterations--;

			de.runGeneration();
			
			fitnessMethod.calculateFitness(de.getPopulation(), neuralNetwork, trainSet);
			
			mostFit = de.getPopulation().getMostFit();
			mostFitValue = mostFit.getFitness();
			
			//ga.getPopulation().printDiversity();
			System.out.println(mostFitValue);
			count++;
			
		}
		
		neuralNetwork.setWeights(mostFit.getGenes());
		
	}

}
