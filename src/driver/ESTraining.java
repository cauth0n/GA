package driver;

import ga.ES;
import ga.Individual;
import ga.fitness.FitnessDefault;
import ga.prune.Prune;
import ga.prune.PruneDefault;

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
	private int mu = 20;
	private int lambda = 30;
	private Prune prune;
	
	private Individual best = null;
	private double mostFitValue = 0;

	public ESTraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
		fitnessMethod = new FitnessDefault();
		es = new ES(mu, lambda, neuralNetwork.size(), mutationProbability);
		es.setTypePlus();
		//es.setTypeComma();
		prune = new PruneDefault();
	}

	
	public void train(List<DataPoint> trainSet) {
		
		fitnessMethod.calculateFitness(es.getPopulation(), neuralNetwork, trainSet);
		es.getPopulation().sortPopulationByFitness();

		// fitness < minFitness
		int count = 0;
		while (maxIterations > 0 && mostFitValue < 0.96) {

			maxIterations--;
			
			es.runGeneration();
			

			fitnessMethod.calculateFitness(es.getPopulation(), neuralNetwork, trainSet);
			
			
			es.getPopulation().sortPopulationByFitness();
			es.setPopulation(prune.prune(es.getPopulation(), mu));
			

			Individual mostFit = es.getPopulation().getMostFit();
			setBest(mostFit);
			System.out.println(mostFit.getFitness());
			
			count++;
		}

		neuralNetwork.setWeights(best.getGenes());
		System.out.println("BEST: "+best.getFitness());
		
	}
	
	public void setBest(Individual mostFit) {
		if (best == null || mostFit.getFitness() >= best.getFitness())
			best = mostFit.copy();
		mostFitValue = best.getFitness();
	}
	
	public String toString() {
		return es.toString();
	}

}
