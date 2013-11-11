package neural_net;

/**
 * Linear Activation function for neurons.
 */
public class LinearActivation extends ActivationFunction {
	
	double slope = 1.0;

	@Override
	public double fire(double input) {
		return slope * input;
	}
	
	@Override
	public double gradient(double input, double output) {
		return slope;
	}

}
