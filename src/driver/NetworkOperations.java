package driver;

import java.util.List;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.Network;
import neural_net.Neuron;

/**
 * @author cauthon
 */
public class NetworkOperations {

	private Network neuralNetwork;

	public NetworkOperations(Network neuralNetwork) {
		this.neuralNetwork = neuralNetwork;
	}

	/**
	 * Feed forward method. First, we call the setInputs method to
	 * build inputs. Then, starting with the input layer, we loop
	 * through layers and feed the input values forward. The output is
	 * calculated in the end, and the 'most probable' output neuron is
	 * chosen. The index of the most probably neuron is returned.
	 * 
	 * @param dataPoint
	 *            data point we want to feed through the network,
	 *            getting output
	 * 
	 * @return index of highest scoring output neuron.
	 */
	public int feedForward(DataPoint dataPoint) {
		int outputNeuronIndex = -1;

		// set the inputs according to the feature values of the data
		// point we are feeding forward.
		setInputs(dataPoint.getFeatures());

		// loop through all layers, multiplying neurons and weights,
		// and finally activating neurons. We do NOT loop through the
		// output layer
		for (int layerNum = 0; layerNum < neuralNetwork.getLayers().size() - 1; layerNum++) {
			Layer currentLayer = neuralNetwork.getLayers().get(layerNum);

			// loop through all neurons in the current layer. We first
			// fire the neuron, then we perform operations on
			// downstream neurons.
			for (Neuron currentNeuron : currentLayer.getNeurons()) {
				currentNeuron.fire();

				// Loop through every outgoing connection from the
				// current neuron. Because of the map used to hold
				// this information, we build up neuron values at the
				// next layer. They are then activated in the next
				// layer loop.
				for (Connection c : currentLayer.getOutGoingConnections().get(currentNeuron)) {
					c.getToNeuron().appendNeuronValue(c.getFromNeuron().getNeuronValue() * c.getWeight());
				}
			}
		}

		// output layer operations here
		Layer outputLayer = neuralNetwork.getLayers().get(neuralNetwork.getLayers().size() - 1);
		double maxOutput = Double.MIN_VALUE;

		// loop through all neurons in the output layer, finding the
		// maximum value. The maximum value of used as a measure of
		// likelihood, meaning how likely the output neurons
		// successfully classified the inputs correctly.
		for (int neuronNum = 0; neuronNum < outputLayer.getNeurons().size(); neuronNum++) {
			Neuron currentNeuron = outputLayer.getNeurons().get(neuronNum);

			// max condition -- the index of the neuron with the max
			// value is returned.
			if (currentNeuron.getNeuronValue() > maxOutput) {
				maxOutput = currentNeuron.getNeuronValue();
				outputNeuronIndex = neuronNum;
			}
		}
		return outputNeuronIndex;
	}

	/**
	 * Method to set the values of the input neurons to the values of
	 * the features, from a data point.
	 * 
	 * @param features
	 *            list of all features in current data point
	 */
	public void setInputs(List<Double> features) {
		Layer inputLayer = neuralNetwork.getLayers().get(0);

		if (features.size() != inputLayer.getNeurons().size()) {
			throw new IllegalArgumentException("Number of features does not match input layer size");
		}

		// loop through all input neurons, assigning the corresponding
		// feature value to each neuron
		for (int neuronNum = 0; neuronNum < inputLayer.getNeurons().size(); neuronNum++) {
			inputLayer.getNeurons().get(neuronNum).setNeuronValue(features.get(neuronNum));
		}
	}

}
