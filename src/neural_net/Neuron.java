package neural_net;

/**
 * @author cauthon
 */
public class Neuron {

	private ActivationFunction neuronActivation;
	private double neuronInput;
	private double neuronOutput;
	private double neuronError;

	public Neuron(ActivationFunction neuronActivation) {
		this.neuronActivation = neuronActivation;
		neuronInput = 0;
		neuronOutput = 0;
		neuronError = 0;
	}

	public double getNeuronInput() {
		return neuronInput;
	}
	
	public double getNeuronOutput() {
		return neuronOutput;
	}

	public void setNeuronInput(double neuronInput) {
		this.neuronInput = neuronInput;
	}

	public double getNeuronError() {
		return neuronError;
	}

	public void setNeuronError(double neuronError) {
		this.neuronError = neuronError;
	}

	public void appendNeuronInput(double value) {
		this.neuronInput += value;
	}

	public double fire() {
		this.neuronOutput = neuronActivation.fire(this.neuronInput);
		return this.neuronOutput;
	}
	
	public double getOutput() {
		return this.neuronOutput;
	}
	
	public double gradient() {
		return neuronActivation.gradient(this.neuronInput, this.neuronOutput);
	}
}
