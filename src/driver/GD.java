package driver;

import java.util.List;

import neural_net.Network;

/**
 * @author cauthon
 */
public class GD extends TrainingMethod {
	
	Double errorThreshold = 0.1;
	int maxIterations = 1000;

	public GD(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
	}

	@Override
	public void train(List<DataPoint> trainSet) {
		//TODO -- training
		List<Double> output, target;
		
		for (int exampleIndex = 0; exampleIndex < trainSet.size(); exampleIndex++) {
			DataPoint datapoint = trainSet.get(exampleIndex);
			Double error = Double.MAX_VALUE;
			int iteration = 0;
			while (error > errorThreshold && iteration < maxIterations) {
				output = networkOperations.feedForward(datapoint);
				target = datapoint.getOutputs();
				error = calculateError(target, output);
				iteration++;
			}
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
			classExpected = datapoint.getClassIndex();
			if (classFound == classExpected)
				correct++;
		}
		double performance = correct/testSet.size();
		return performance;
	}

}
