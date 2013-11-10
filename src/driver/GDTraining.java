package driver;

import java.util.List;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.Network;
import neural_net.Neuron;

/**
 * Driver for the backward propogation of errors algorithm (backprop).
 * This is a gradient descent training algorithm.
 */
public class GDTraining extends TrainingMethod {

	private Double errorThreshold = 0.0001;
	private int maxIterations = 10000;
	private Double learningRate = 0.01;

	/**
	 * Constructs a new instance of the GDTraining class.
	 * 
	 * @param neuralNetwork	The network that will be trained.
	 * @param data			The training data to use.
	 */
	public GDTraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
	}

	/**
	 * Trains the GD using the training set provided.
	 */
	public void train(List<DataPoint> trainSet) {
		List<Double> output, target;

		// loop through all training examples
		for (int exampleIndex = 0; exampleIndex < trainSet.size(); exampleIndex++) {
			
			// retrieve the current datapoint and store the target vector
			DataPoint datapoint = trainSet.get(exampleIndex);
			Double sumError = Double.MAX_VALUE;
			target = datapoint.getOutputs();

			// backpropogate until error is small enough or too many iterations have passed
			for (int iteration = 0; sumError > errorThreshold && iteration < maxIterations; iteration++) {
				// store the current output from the network
				output = networkOperations.feedForward(datapoint);
				// backpropogate error calculated from target and actual output vectors
				backpropagate(target, output);
				// calculate the error produced by the network for this example
				sumError = calculateError(target, output);
			}
			
		}
	}

	/**
	 * Calls all methods required to do backpropogation.
	 * 
	 * @param target	The target vector that the network should produce.
	 * @param output	The actual vector that the network did produce.
	 */
	private void backpropagate(List<Double> target, List<Double> output) {
		backpropagateError(target);
		backPropagateWeightErrors();
		updateWeights();
	}

	/**
	 * Calculates the error for the output layer.
	 * 
	 * @param layer			The reference to the output layer.
	 * @param targetOutput	The target outputs for the output layer.
	 */
	private void calculateOutputErrorSignals(Layer layer, List<Double> targetOutput) {
		// loop through all neurons in the specified layer
		for (int neuronNum = 0; neuronNum < layer.getNeurons().size(); neuronNum++) {
			Neuron neuron = layer.getNeurons().get(neuronNum);
			double thisNeuronTarget = targetOutput.get(neuronNum);
			
			// calculate error as (target - output) * gradient
			double delta = -1 * ((thisNeuronTarget - neuron.getNeuronOutput()) * neuron.gradient());
			// store error for neuron
			neuron.setNeuronError(delta);
		}
	}

	/**
	 * Calculates the error for a specified hidden layer.
	 * 
	 * @param layer	The hidden layer that error will be calculated for.
	 */
	private void calculateHiddenErrorSignals(Layer layer) {
		
		// loop through all neurons in the specified hidden layer
		for (Neuron neuron : layer.getNeurons()) {
			double runningSum = 0.0;
			// sum the error from all downstream neurons
			for (Connection connection : layer.getOutGoingConnections().get(neuron)) {
				runningSum += connection.getToNeuron().getNeuronError() * connection.getWeight();
			}
			// multiply the error sum by the gradient
			runningSum *= neuron.gradient();
			// store the error for the neuron
			neuron.setNeuronError(runningSum);
		}
		
	}

	/**
	 * Propogate error from output layer backward to all hidden layers.
	 * 
	 * @param targetOutput	The target output of the network.
	 */
	private void backpropagateError(List<Double> targetOutput) {
		// loop backward through all layers until the input layer is reached
		for (int i = neuralNetwork.getLayers().size() - 1; i > 0; i--) {
			Layer layer = neuralNetwork.getLayers().get(i);
			// if output layer, calculate error using output error rule
			if (i == neuralNetwork.getLayers().size() - 1) { // output layer
				calculateOutputErrorSignals(layer, targetOutput);
			// if hidden layer, calculate error using hidden error rule
			} else {
				calculateHiddenErrorSignals(layer);
			}
		}
	}

	/**
	 * Calculate weight changes using previously calculated errors.
	 */
	private void backPropagateWeightErrors() {
		// loop backward through layers
		for (int i = neuralNetwork.getLayers().size() - 2; i > 0; i--) {
			Layer layer = neuralNetwork.getLayers().get(i);
			// loop through all neurons in the current layer
			for (Neuron neuron : layer.getNeurons()) {
				// loop through all connections from the current neuron to the next neurons
				for (Connection connection : layer.getOutGoingConnections().get(neuron)) {
					// weight update rule: eta * error_j * output_i
					double value = (-1 * learningRate * connection.getToNeuron().getNeuronError() * 
							connection.getFromNeuron().getNeuronOutput());
					// apply weight update
					connection.setWeightChange(value);
				}
			}
		}
	}

	/**
	 * Update weights using previously calculated weight changes.
	 */
	public void updateWeights() {
		// loop backward through layers
		for (int i = neuralNetwork.getLayers().size() - 2; i > 0; i-- ) {
			Layer layer = neuralNetwork.getLayers().get(i);
			// loop through all neurons in the current layer
			for (Neuron neuron : layer.getNeurons()) {
				// loop through all connections from the current neuron to the next neurons
				for (Connection connection : layer.getOutGoingConnections().get(neuron)) {
					// update the weight for the neuron using the weight change that was calculated
					connection.appendWeight();
				}
			}
		}
	}

}
