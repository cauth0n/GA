package driver;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

import neural_net.Network;

/**
 * @author cauthon
 */
public abstract class TrainingMethod {
	protected NetworkOperations networkOperations;
	protected Network neuralNetwork;
	private List<DataPoint> data;
	protected int k = 10;
	protected Map<Integer, List<DataPoint>> formattedData;
	protected KFolds kfolds;

	public TrainingMethod(Network neuralNetwork, List<DataPoint> data) {
		this.neuralNetwork = neuralNetwork;
		this.data = data;
		networkOperations = new NetworkOperations(neuralNetwork);
	}

	public void mainLoop(int folds) {
		// create a KFold class with a specified number of folds
		kfolds = new KFolds(data, folds);
		
		// used for timing the training algorithm
		long startTime, elapsedTime;
		
		// loop through all folds, training and testing on each
		while (kfolds.next()) {
			
			startTime = System.currentTimeMillis();
			train(kfolds.getTrainSet());
		    elapsedTime = System.currentTimeMillis() - startTime;
		    
			double performance = test(kfolds.getTestSet());
			System.out.println(performance);
			
		}
	}

	public abstract void train(List<DataPoint> trainSet);

	public abstract double test(List<DataPoint> testSet);

	public Double calculateError(List<Double> target, List<Double> actual) {
		
		if (target.size() != actual.size())
			throw new IllegalArgumentException("Target and Expected output vectors must be the same length.");
		
		// get average of the squared error for each output
		Double error = 0.0;
		for (int output = 0; output < target.size(); output++)
			error += Math.pow(target.get(output) - actual.get(output), 2);
		error /= target.size();
		
		return error;
	}
	
}
