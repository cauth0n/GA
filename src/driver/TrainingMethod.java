package driver;

import ga.fitness.Fitness;

import java.util.List;
import java.util.Map;

import neural_net.Layer;
import neural_net.Network;

/**
 * @author cauthon
 */
public abstract class TrainingMethod {
	
	protected Fitness fitnessMethod;
	public static NetworkOperations networkOperations;
	protected Network neuralNetwork;
	protected List<Layer> layers;
	protected int outIndex;
	private List<DataPoint> data;
	protected int k = 10;
	protected Map<Integer, List<DataPoint>> formattedData;
	protected KFolds kfolds;

	public TrainingMethod(Network neuralNetwork, List<DataPoint> data) {
		this.neuralNetwork = neuralNetwork;
		this.data = data;
		networkOperations = new NetworkOperations(neuralNetwork);
		this.layers = neuralNetwork.getLayers();
		this.outIndex = layers.size() - 1;
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
			
			// TODO: remove this break
			break;
			
		}
	}

	public abstract void train(List<DataPoint> trainSet);
	
	public double test(List<DataPoint> testSet) {
		// TODO this might belong in the abstract class, since it shouldn't change between training methods
		int classFound, classExpected;
		int correct = 0;
		List<Double> outputs;
		for (int exampleIndex = 0; exampleIndex < testSet.size(); exampleIndex++) {
			DataPoint datapoint = testSet.get(exampleIndex);
			outputs = networkOperations.feedForward(datapoint);
			classFound = networkOperations.getMaxIndex(outputs);
			classExpected = datapoint.getClassIndex();
			if (classFound == classExpected)
				correct++;
		}
		
		double performance = (double)correct / testSet.size();
		return performance;
	}

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
