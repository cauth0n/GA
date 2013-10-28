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
	public void mainLoop() {
		int testingPointer = 9;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < k; j++) {
				//TODO -- fill out k fold cross validation here.
				//
				if (j == testingPointer) {
					continue;
				}
			}
			// test
			testingPointer--;
		}

	}

	@Override
	public void train() {
		//TODO -- training
	}

	@Override
	public void test(List<DataPoint> testSet) {
		// TODO -- testing

	}

}
