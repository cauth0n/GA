package neural_net;
/**
 *@author cauthon
 */
public class LinearActivation extends ActivationFunction {

	@Override
	public double fire(double input) {
		return input;
	}
	
	@Override
	public double gradient(double input, double output) {
		return 1;
	}

}
