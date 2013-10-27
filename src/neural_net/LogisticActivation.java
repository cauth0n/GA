package neural_net;
/**
 *@author cauthon
 */
public class LogisticActivation extends ActivationFunction {

	@Override
	public double fire(double input) {
		return (1.0 / (1 + Math.exp(-1 * input)));
	}
	

}