package neural_net;
/**
 *@author cauthon
 */
public class LogisticActivation extends ActivationFunction {

	@Override
	public double fire(double input) {
		return (1.0 / (1 + Math.exp(-input)));
	}
	
	@Override
	public double gradient(double input, double output) {
		return fire(input) * (1 - fire(input));
		//return output * (1 - output);
	}
	

}
