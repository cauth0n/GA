package driver;

import java.util.List;
import java.util.Map;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.Network;
import neural_net.Neuron;

/**
 * @author cauthon
 */
public class DETraining extends TrainingMethod {
	
	Double errorThreshold = 0.0001;
	int maxIterations = 1000;
	Double learningRate = 0.9;
	Double momentum = 0.0;

	public DETraining(Network neuralNetwork, List<DataPoint> data) {
		super(neuralNetwork, data);
	}

	@Override
	public void train(List<DataPoint> trainSet) {
		//TODO -- training
		List<Double> output, target;
		
		for (int exampleIndex = 0; exampleIndex < trainSet.size(); exampleIndex++) {
			DataPoint datapoint = trainSet.get(exampleIndex);
			Double sumError = Double.MAX_VALUE;
			target = datapoint.getOutputs();
			
			// TESTING
			Layer layer1 = neuralNetwork.getLayers().get(0);
			Map<Neuron, List<Connection>> outgoing = layer1.getOutGoingConnections();
			List<Connection> connections = outgoing.get(layer1.getNeurons().get(0));
			Double weight = connections.get(0).getWeight();
//			System.out.println("WEIGHT: "+weight);
			
			// backpropogate until error is small enough or too many iterations have passed
			for (int iteration = 0; sumError > errorThreshold && iteration < maxIterations; iteration++) {
				output = networkOperations.feedForward(datapoint);
				differentialEvolution(target, output);
				sumError = calculateError(target, output);
//				System.out.println(iteration+":  "+sumError);
//				Simulator.printVector(target);
//				Simulator.printVector(output);
//				System.out.println();
			}
			
		}
		
	}
	
	private void differentialEvolution(List<Double> target, List<Double> output) {
		// TODO: DE
	}

}
