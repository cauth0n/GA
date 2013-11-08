package driver;

import ga.ES;
import ga.Individual;
import ga.fitness.FitnessDefault;

import java.util.List;

import neural_net.Network;

/**
 * @author cauthon
 */
public class ESTraining extends TrainingMethod {
	
	private ES es;
	private int populationSize = 40;
	private double mutationProbability = 1.0;
	int maxIterations = 1000;
	private int mu = 1;
	private int lambda = 1;
	
	private Individual best;

	public ESTraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
		fitnessMethod = new FitnessDefault();
		es = new ES(mu, lambda, neuralNetwork.size(), mutationProbability);
		es.setTypePlus();
	}

	
	public void train(List<DataPoint> trainSet) {
		
		fitnessMethod.calculateFitness(es.getPopulation(), neuralNetwork, trainSet);
		es.getPopulation().sortPopulationByFitness();

		Individual best = null;
		double mostFitValue = 0;

		// fitness < minFitness
		int count = 0;
		while (maxIterations > 0) {

			maxIterations--;
			
			es.runGeneration();
			

			fitnessMethod.calculateFitness(es.getPopulation(), neuralNetwork, trainSet);
			es.getPopulation().sortPopulationByFitness();
			

			Individual mostFit = es.getPopulation().getMostFit();
			setBest(mostFit);
			System.out.println(mostFit.getFitness());
			
			count++;
		}

		neuralNetwork.setWeights(best.getGenes());
		
	}
	
	public void setBest(Individual mostFit) {
		if (best == null || mostFit.getFitness() >= best.getFitness())
			best = mostFit.copy();
	}
	
	public String toString() {
		return es.toString();
	}

}
