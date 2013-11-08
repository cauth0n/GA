package neural_net;

/**
 * @author cauthon
 */
public abstract class ActivationFunction {

	/**
	 * Firing function for activation function.
	 * 
	 * @param input
	 *            input to whatever function we are using
	 * @return value outputted by function
	 */
	public abstract double fire(double input);

	/**
	 * this takes output as a param because it can more efficiently calculate
	 * the derivative without redoing the fire() function.
	 * 
	 * @param input
	 *            not used.
	 * @param output
	 *            input to the gradient of the function we are using.
	 * @return value generated.
	 */
	public abstract double gradient(double input, double output);

}
