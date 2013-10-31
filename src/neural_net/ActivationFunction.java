package neural_net;
/**
 *@author cauthon
 */
public abstract class ActivationFunction {
	
	public abstract double fire(double input);
	
	// this takes output as a param because it can more efficiently
	// calculate the derivative without redoing the fire() function.
	public abstract double gradient(double input, double output);

}
