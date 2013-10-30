package driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
			
		}
	}

	public abstract void train(List<DataPoint> trainSet);

	public abstract double test(List<DataPoint> testSet);

	// TODO: I didn't see this, and I implemented my own. We'll just use one or another.
	public void kFoldCrossValidation() {
		formattedData = new HashMap<Integer, List<DataPoint>>();
		Random rand = new Random();
		for (DataPoint dataPoint : data) {
			int fold = rand.nextInt(k);
			if (formattedData.get(fold) == null) {
				formattedData.put(fold, new ArrayList<DataPoint>());
			}
			List<DataPoint> currentDataPoints = formattedData.get(fold);
			currentDataPoints.add(dataPoint);
		}
	}
}
