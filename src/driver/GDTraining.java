package driver;

import java.util.List;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.Network;
import neural_net.Neuron;

/**
 * @author cauthon
 */
public class GDTraining extends TrainingMethod {

	private Double errorThreshold = 0.0001;
	private int maxIterations = 10000;
	//0.01 best
	private Double learningRate = 0.01;
	private Double momentum = 0.0;

	public GDTraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
	}

	@Override
	public void train(List<DataPoint> trainSet) {
		List<Double> output, target;

		for (int exampleIndex = 0; exampleIndex < trainSet.size(); exampleIndex++) {
			DataPoint datapoint = trainSet.get(exampleIndex);
			Double sumError = Double.MAX_VALUE;
			target = datapoint.getOutputs();

			// backpropogate until error is small enough or too many iterations
			// have passed
			for (int iteration = 0; sumError > errorThreshold
					&& iteration < maxIterations; iteration++) {
				output = networkOperations.feedForward(datapoint);
				backpropagate(target, output);
				sumError = calculateError(target, output);
			}
		}
	}

	public void backpropagate(List<Double> target, List<Double> output) {
		backpropagateError(target);
		backPropagateWeightErrors();
		updateWeights();
	}

	public void calculateOutputErrorSignals(Layer layer, List<Double> targetOutput) {
		for (int neuronNum = 0; neuronNum < layer.getNeurons().size(); neuronNum++) {
			Neuron neuron = layer.getNeurons().get(neuronNum);
			double thisNeuronTarget = targetOutput.get(neuronNum);
			
			double delta = -1 * ((thisNeuronTarget - neuron.getNeuronOutput()) * neuron.gradient());
			neuron.setNeuronError(delta);
		}
	}

	public void calculateHiddenErrorSignals(Layer layer) {
		for (Neuron neuron : layer.getNeurons()) {
			double runningSum = 0.0;
			for (Connection connection : layer.getOutGoingConnections().get(
					neuron)) {
				runningSum += connection.getToNeuron().getNeuronError()
						* connection.getWeight();
			}
			runningSum *= neuron.gradient();
			neuron.setNeuronError(runningSum);
		}
	}

	public void backpropagateError(List<Double> targetOutput) {
		for (int i = neuralNetwork.getLayers().size() - 1; i > 0; i--) {
			Layer layer = neuralNetwork.getLayers().get(i);
			if (i == neuralNetwork.getLayers().size() - 1) { // output layer
				calculateOutputErrorSignals(layer, targetOutput);
			} else {
				calculateHiddenErrorSignals(layer);
			}
		}
	}

	public void backPropagateWeightErrors() {
		for (int i = neuralNetwork.getLayers().size() - 2; i > 0; i--) {
			Layer layer = neuralNetwork.getLayers().get(i);
			for (Neuron neuron : layer.getNeurons()) {
				for (Connection connection : layer.getOutGoingConnections()
						.get(neuron)) {
					double value = (-1 * learningRate
							* connection.getToNeuron().getNeuronError() * connection
							.getFromNeuron().getNeuronOutput());
					connection.setWeightChange(value);
				}
			}
		}
	}

	public void updateWeights() {
		for (int i = neuralNetwork.getLayers().size() - 2; i > 0; i-- ) {
			Layer layer = neuralNetwork.getLayers().get(i);
			for (Neuron neuron : layer.getNeurons()) {
				for (Connection connection : layer.getOutGoingConnections()
						.get(neuron)) {
					connection.appendWeight();
				}
			}
		}
	}

}
