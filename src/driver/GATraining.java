package driver;

import ga.FitnessDefault;
import ga.GA;
import ga.Individual;

import java.util.List;

import neural_net.Network;

/**
 * @author cauthon
 */
public class GATraining extends TrainingMethod {
	
	private GA ga;
	private int populationSize = 10;
	private double mutationProbability = 0.05;
	
	private double fitnessThreshold = 0.8;
	
	Double errorThreshold = 0.0001;
	int maxIterations = 1000;
	Double learningRate = 0.9;
	Double momentum = 0.0;

	public GATraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
		fitnessMethod = new FitnessDefault();
		ga = new GA(populationSize, neuralNetwork.size(), mutationProbability);
		
	}
	

	@Override
	public void train(List<DataPoint> trainSet) {
		//TODO -- training
		List<Double> output, target;
		
		fitnessMethod.calculateFitness(ga.getPopulation(), neuralNetwork, trainSet);
		
		System.out.println(ga.getPopulation().getPopulation().get(0).getFitness());
		
		Individual mostFit = null;
		double mostFitValue = 0;
		
		//fitness < minFitness
		while(maxIterations > 0 && mostFitValue < fitnessThreshold){
			

			ga.runGeneration();
			
			fitnessMethod.calculateFitness(ga.getPopulation(), neuralNetwork, trainSet);
			
			mostFit = ga.getPopulation().getMostFit();
			mostFitValue = mostFit.getFitness();
			
			maxIterations--;
		}
		
		neuralNetwork.setWeights(mostFit.getGenes());
		
	}
	
	private void geneticAlgorithm(List<Double> target, List<Double> output) {
		// TODO: GA
	}

}
