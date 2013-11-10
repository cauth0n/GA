package driver;

import ga.fitness.Fitness;

import java.util.List;
import java.util.Map;

import neural_net.Layer;
import neural_net.Network;

/**
 * Abstract Training Method class. Implementations are capable
 * of training a neural network using various algorithms.
 */
public abstract class TrainingMethod {
	
	protected Fitness fitnessMethod;
	public static NetworkOperations networkOperations;
	protected Network neuralNetwork;
	protected List<Layer> layers;
	protected int outIndex;
	private List<DataPoint> data;
	protected Map<Integer, List<DataPoint>> formattedData;
	protected KFolds kfolds;

	/**
	 * Constructs a generic TrainingMethod class.
	 * 
	 * @param neuralNetwork	The neural network that the algorithm will train.
	 * @param data			The data that will be used to train the network.
	 */
	public TrainingMethod(Network neuralNetwork, List<DataPoint> data) {
		this.neuralNetwork = neuralNetwork;
		this.data = data;
		networkOperations = new NetworkOperations(neuralNetwork);
		this.layers = neuralNetwork.getLayers();
		this.outIndex = layers.size() - 1;
	}

	/**
	 * The main loop that performs all work related to training and testing the network.
	 * 
	 * @param folds	The number of folds to use in k-folds cross validation test.
	 */
	public void mainLoop(int folds) {
		
		// create a KFold class with a specified number of folds
		kfolds = new KFolds(data, folds);
		
		// used to store total performance and elapsed time
		double totalPerformance = 0.0;
		long totalElapsedTime = 0;
		
		// used for timing the training algorithm
		long startTime, elapsedTime;
		
		// loop through all folds, training and testing on each
		//System.out.println("Performance \t TrainingTime");
		while (kfolds.next()) {
			
			startTime = System.currentTimeMillis();
			train(kfolds.getTrainSet());
		    elapsedTime = System.currentTimeMillis() - startTime;
		    
			double performance = test(kfolds.getTestSet());
			System.out.println(performance+"\t"+elapsedTime);
			
			// add to running total
			totalPerformance += performance;
			totalElapsedTime += elapsedTime;
			
			// TODO: remove this break
			break;
			
		}
		
		// calculate and display average performance and train time
		totalPerformance /= folds;
		totalElapsedTime /= folds;
		System.out.println();
		System.out.println(totalPerformance+"\t"+totalElapsedTime);
		
	}

	/**
	 * The train method will be different for each implementation.
	 * 
	 * @param trainSet	The set of data to use for training.
	 */
	public abstract void train(List<DataPoint> trainSet);
	
	/**
	 * Tests the trained network using a test set.
	 * 
	 * @param testSet	The set of data to test the neural network on.
	 * @return			The performance (right/wrong) of the trained net.
	 */
	public double test(List<DataPoint> testSet) {
		int classFound, classExpected;
		int correct = 0;
		List<Double> outputs;
		// loop through all datapoints in the test set
		for (int exampleIndex = 0; exampleIndex < testSet.size(); exampleIndex++) {
			DataPoint datapoint = testSet.get(exampleIndex);
			// get the actual outputs of the network
			outputs = networkOperations.feedForward(datapoint);
			// get the chosen class based on the output neuron that fired with the most certainty
			classFound = networkOperations.getMaxIndex(outputs);
			// get the expected class from the datapoint
			classExpected = datapoint.getClassIndex();
			// if the expected and actual class are equal, increase count for number correct
			if (classFound == classExpected)
				correct++;
		}
		
		// calculate performance as number of correct out of total number of datapoints
		double performance = (double)correct / testSet.size();
		return performance;
	}

	/**
	 * Calculates the error given a target and actual output vector.
	 * 
	 * @param target	The vector of target values for the datapoint.
	 * @param actual	The vector of actual output values from the network.
	 * @return			The mean squared error for vectors.
	 */
	public Double calculateError(List<Double> target, List<Double> actual) {
		
		// ensure target and actual vectors match
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
