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
	private int populationSize = 40;
	private double mutationProbability = 0.5;
	private double fitnessThreshold = 0.95;
	int maxIterations = 1000;

	public GATraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
		fitnessMethod = new FitnessDefault();
		ga = new GA(populationSize, neuralNetwork.size(), mutationProbability);

	}

	@Override
	public void train(List<DataPoint> trainSet) {

		fitnessMethod.calculateFitness(ga.getPopulation(), neuralNetwork,
				trainSet);

		Individual mostFit = null;
		double mostFitValue = 0;

		// fitness < minFitness
		int count = 0;
		while (maxIterations > 0 && mostFitValue < fitnessThreshold) {

			maxIterations--;
			
			ga.getPopulation().sortPopulationByFitness();
			ga.runGeneration();
			

			fitnessMethod.calculateFitness(ga.getPopulation(), neuralNetwork, trainSet);
			ga.getPopulation().sortPopulationByFitness();
			

			
			Individual best = ga.getPopulation().getMostFit();
			if (count == 0)
				mostFit = best.copy();
			
			if (best.getFitness() >= mostFit.getFitness()) {
				mostFit = best.copy();
			}


			mostFitValue = best.getFitness();

			System.out.println(mostFitValue);


			 //ga.getPopulation().printDiversity();
			
			count++;
		}

		neuralNetwork.setWeights(mostFit.getGenes());

	}

}
