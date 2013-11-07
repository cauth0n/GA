package driver;

import ga.ES;
import ga.fitness.FitnessDefault;

import java.util.List;

import neural_net.Network;

/**
 * @author cauthon
 */
public class ESTraining extends TrainingMethod {
	
	private ES es;
	private int populationSize = 40;
	private double mutationProbability = 0.5;
	int maxIterations = 1000;
	
	private String type = "+";
	private double mu;
	private double lambda;

	public ESTraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
		fitnessMethod = new FitnessDefault();
		es = new ES(populationSize, neuralNetwork.size(), mutationProbability);
		checkParams();
	}

	
	public void train(List<DataPoint> trainSet) {
		
		
		
	}
	
	public String toString() {
		return "("+mu+" "+type+" "+lambda+") - ES";
	}
	
	public void describe() {
		System.out.println(toString());
	}
	
	private void checkParams() {
		if (type.equals("+")) {
			if (mu < 1 || mu > lambda) {
				throw new IllegalArgumentException("Invalid parameter mu. Must be of the form: 1 <= mu <= lambda.");
			}
		} else if (type.equals(",")) {
			if (mu < 1 || mu >= lambda) {
				throw new IllegalArgumentException("Invalid parameter mu. Must be of the form: 1 <= mu < lambda.");
			}
		} else {
			throw new IllegalArgumentException("Invalid type.");
		}
	}

}
