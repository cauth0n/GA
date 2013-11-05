package driver;

import ga.GA;
import ga.Individual;
import ga.fitness.FitnessDefault;

import java.util.List;

import neural_net.Network;

/**
 * @author cauthon
 */
public class GATraining extends TrainingMethod {
	
	private GA ga;
	private int populationSize = 50;
	private double mutationProbability = 0.3;
	private double fitnessThreshold = 1.1;
	int maxIterations = 1000;

	public GATraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
		fitnessMethod = new FitnessDefault();
		ga = new GA(populationSize, neuralNetwork.size(), mutationProbability);
		
	}
	

	@Override
	public void train(List<DataPoint> trainSet) {
		
		fitnessMethod.calculateFitness(ga.getPopulation(), neuralNetwork, trainSet);
		
		Individual mostFit = null;
		double mostFitValue = 0;
		
		//fitness < minFitness
		int count = 0;
		while(maxIterations > 0 && mostFitValue < fitnessThreshold){
			
			maxIterations--;

			ga.runGeneration();
			
			fitnessMethod.calculateFitness(ga.getPopulation(), neuralNetwork, trainSet);
			
			mostFit = ga.getPopulation().getMostFit();
			mostFitValue = mostFit.getFitness();
			
			//ga.getPopulation().printDiversity();
			System.out.println(mostFitValue);
			count++;
			
		}
		
		neuralNetwork.setWeights(mostFit.getGenes());
		
	}
	
	private void geneticAlgorithm(List<Double> target, List<Double> output) {
		// TODO: GA
	}

}
