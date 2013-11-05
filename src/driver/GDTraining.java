package driver;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

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

	//
	// private void backpropagate(List<Double> target, List<Double> output) {
	//
	// Map<Neuron, Double> errors = new HashMap<Neuron, Double>();
	//
	// // calculate output errors
	// calculateOutputErrors(errors, target, output);
	//
	// // calculate all hidden layers errors
	// for (int hiddenLayer = outIndex - 1; hiddenLayer > 0; hiddenLayer--) {
	// // error is received from downstream neurons
	// Layer currentLayer = layers.get(hiddenLayer);
	// Layer downstreamLayer = layers.get(hiddenLayer + 1);
	// // prepend the errors to beginning of list
	// calculateHiddenErrors(errors, currentLayer, downstreamLayer);
	// }
	//
	// // update all weights using errors
	// updateWeights(errors);
	//
	// }
	//
	// private void calculateOutputErrors(Map<Neuron, Double> errors,
	// List<Double> target, List<Double> output) {
	//
	// List<Double> outputErrors = new ArrayList<>();
	// Layer outputLayer = layers.get(outIndex);
	// for (int neuronIndex = 0; neuronIndex < outputLayer.getNeurons().size();
	// neuronIndex++) {
	// // calculate difference between expected and actual output
	// Double diff = (target.get(neuronIndex).doubleValue() - output.get(
	// neuronIndex).doubleValue());
	// // multiply by gradient of neuron that is being updated
	// Double error = outputLayer.getNeurons().get(neuronIndex).gradient()
	// * diff;
	// outputErrors.add(error);
	// errors.put(outputLayer.getNeurons().get(neuronIndex), error);
	// }
	//
	// }
	//
	// private void calculateHiddenErrors(Map<Neuron, Double> errors,
	// Layer currentLayer, Layer downstreamLayer) {
	//
	// List<Double> hiddenErrors = new ArrayList<>();
	// for (int neuronIndex = 0; neuronIndex < currentLayer.getNeurons()
	// .size(); neuronIndex++) {
	// Neuron currentNeuron = currentLayer.getNeurons().get(neuronIndex);
	// List<Connection> connections = currentLayer
	// .getOutGoingConnections().get(currentNeuron);
	// Double sumWeightedError = 0.0;
	// for (int nextNeuronIndex = 0; nextNeuronIndex < downstreamLayer
	// .getNeurons().size(); nextNeuronIndex++) {
	// Connection connection = connections.get(nextNeuronIndex);
	// sumWeightedError += errors.get(connection.getToNeuron())
	// * connection.getWeight();
	// // sumWeightedError += errors.get(0).get(nextNeuronIndex) *
	// connections.get(nextNeuronIndex).getWeight();
	// }
	//
	// // multiply by gradient of neuron that is being updated
	// Double error = currentLayer.getNeurons().get(neuronIndex)
	// .gradient()
	// * sumWeightedError;
	// errors.put(currentNeuron, error);
	// }
	//
	// }
	//
	// private void updateWeights(Map<Neuron, Double> errors) {
	// // update all weights using errors
	// for (int layer = 0; layer < layers.size() - 1; layer++) {
	// Layer currentLayer = layers.get(layer);
	// for (int neuronIndex = 0; neuronIndex < currentLayer.getNeurons().size();
	// neuronIndex++) {
	// Neuron currentNeuron = currentLayer.getNeurons().get(neuronIndex);
	// List<Connection> connections =
	// currentLayer.getOutGoingConnections().get(currentNeuron);
	// for (int nextNeuron = 0; nextNeuron < connections.size(); nextNeuron++) {
	// Connection connection = connections.get(nextNeuron);
	// Double currentError = errors.get(connection.getToNeuron());
	//
	// Double update = (learningRate * currentNeuron.getOutput() *
	// currentError);
	// // update connection weight
	// connection.setWeight(connection.getWeight() + update);
	// //connection.setWeight(update + (connection.getWeight() * momentum));
	// connection.setWeightChange(update);
	// }
	//
	// }
	// }
	// }

}
