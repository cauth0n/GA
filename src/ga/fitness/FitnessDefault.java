package ga.fitness;

import ga.Individual;
import ga.Population;

import java.util.List;

import neural_net.Network;
import driver.DataPoint;
import driver.TrainingMethod;

public class FitnessDefault extends Fitness {
	
	public void calculateFitness(Population population, Network neuralNetwork, List<DataPoint> testSet) {
		double fitness = 0.0;
		
		for (Individual individual : population.getIndividuals()){
		
			//individual genes to network weights
			neuralNetwork.setWeights(individual.getGenes());
			
			
			//feed test set forward in network
			List<Double> outputs;
			int correct = 0;
			for (DataPoint dataPoint : testSet){
				outputs = TrainingMethod.networkOperations.feedForward(dataPoint);
				int classFound = TrainingMethod.networkOperations.getMaxIndex(outputs);
				int classExpected = dataPoint.getClassIndex();
				if (classFound == classExpected)
					correct++;
				outputs.clear();
			}
			
			//return % correct
			
			fitness = ((double) correct) / testSet.size();
			
			individual.setFitness(fitness);
		}
		
	}

}

