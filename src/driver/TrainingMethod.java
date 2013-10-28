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
	protected List<DataPoint> data;
	protected final int k = 10;
	protected Map<Integer, List<DataPoint>> formattedData;

	public TrainingMethod(Network neuralNetwork, List<DataPoint> data) {
		this.neuralNetwork = neuralNetwork;
		this.data = data;
		networkOperations = new NetworkOperations(neuralNetwork);
	}

	public abstract void mainLoop();

	public abstract void train();

	public abstract void test(List<DataPoint> testSet);

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
