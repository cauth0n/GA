package neural_net;
/**
 *@author cauthon
 */
public class LogisticActivation extends ActivationFunction {
	
	private double stretch = 0.5;

	@Override
	public double fire(double input) {
		return (stretch / (stretch + Math.exp(-input)));
	}
	
	@Override
	public double gradient(double input, double output) {
		return fire(input) * (1 - fire(input));
		//return output * (1 - output);
	}
	

}
