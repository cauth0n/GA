package neural_net;

/**
 * @author cauthon
 */
public class Neuron {

	private ActivationFunction neuronActivation;
	private double neuronValue;
	private double neuronError;

	public Neuron(ActivationFunction neuronActivation) {
		this.neuronActivation = neuronActivation;
		neuronValue = 0;
		neuronError = 0;
	}

	public double getNeuronValue() {
		return neuronValue;
	}

	public void setNeuronValue(double neuronValue) {
		this.neuronValue = neuronValue;
	}

	public double getNeuronError() {
		return neuronError;
	}

	public void setNeuronError(double neuronError) {
		this.neuronError = neuronError;
	}

	public void appendNeuronValue(double value) {
		this.neuronValue += value;
	}

	public void fire() {
		this.neuronValue = neuronActivation.fire(this.neuronValue);
	}
}
