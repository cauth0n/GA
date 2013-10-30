package driver;

import java.util.List;

import neural_net.Network;

/**
 * @author cauthon
 */
public class GD extends TrainingMethod {

	public GD(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
	}

	@Override
	public void train(List<DataPoint> trainSet) {
		//TODO -- training
		
	}

	@Override
	public double test(List<DataPoint> testSet) {
		// TODO -- testing

		return 0.0;
	}

}
