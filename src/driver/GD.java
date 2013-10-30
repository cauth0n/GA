package driver;

import java.util.List;

import neural_net.Network;

/**
 * @author cauthon
 */
public class GD extends TrainingMethod {

	public GD(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
	}

	@Override
	public void train(List<DataPoint> trainSet) {
		//TODO -- training
		List<Double> outputs;
		for (int exampleIndex = 0; exampleIndex < trainSet.size(); exampleIndex++) {
			outputs = networkOperations.feedForward(trainSet.get(exampleIndex));
		}
	}

	@Override
	public double test(List<DataPoint> testSet) {
		// TODO -- testing
		int classFound, classExpected;
		int correct = 0;
		List<Double> outputs;
		for (int exampleIndex = 0; exampleIndex < testSet.size(); exampleIndex++) {
			DataPoint datapoint = testSet.get(exampleIndex);
			outputs = networkOperations.feedForward(datapoint);
			classFound = networkOperations.getMaxIndex(outputs);
			classExpected = datapoint.getOutput();
			if (classFound == classExpected)
				correct++;
		}
		double performance = correct/testSet.size();
		return performance;
	}

}
