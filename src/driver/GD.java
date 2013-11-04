package driver;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.Network;
import neural_net.Neuron;

/**
 * @author cauthon
 */
public class GD extends TrainingMethod {
	
	Double errorThreshold = 0.0001;
	int maxIterations = 1000;
	Double learningRate = 0.9;
	Double momentum = 0.0;

	public GD(Network neuralNetwork, List<DataPoint> data) {
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
				backpropogate(target, output);
				sumError = calculateError(target, output);
//				System.out.println(iteration+":  "+sumError);
//				Simulator.printVector(target);
//				Simulator.printVector(output);
//				System.out.println();
			}
			
		}
	}
	
	private void backpropogate(List<Double> target, List<Double> output) {
		
		List<List<Double>> errors = new ArrayList<List<Double>>();
		
		// calculate output errors
		calculateOutputErrors(errors, target, output);
		
		// update all hidden layers
		for (int hiddenLayer = outIndex - 1; hiddenLayer > 0; hiddenLayer--) {
			// error is received from downstream neurons
			Layer currentLayer = layers.get(hiddenLayer);
			Layer downstreamLayer = layers.get(hiddenLayer + 1);
			// prepend the errors to beginning of list
			calculateHiddenErrors(errors, currentLayer, downstreamLayer);
		}
		
		// update all weights using errors
		updateWeights(errors);
		
	}
	
	private void calculateOutputErrors(List<List<Double>> errors, List<Double> target, List<Double> output) {
		
		List<Double> outputErrors = new ArrayList<>();
		Layer outputLayer = layers.get(outIndex);
		for (int neuronIndex = 0; neuronIndex < outputLayer.getNeurons().size(); neuronIndex++) {
			// calculate difference between expected and actual output
			Double diff = (target.get(neuronIndex).doubleValue() - output.get(neuronIndex).doubleValue());
			// multiply by gradient of neuron that is being updated
			Double error = outputLayer.getNeurons().get(neuronIndex).gradient() * diff;
			outputErrors.add(error);
		}
		errors.add(0, outputErrors);
	}

	private void calculateHiddenErrors(List<List<Double>> errors, Layer currentLayer, Layer downstreamLayer) {
		List<Double> hiddenErrors = new ArrayList<>();
		for (int neuronIndex = 0; neuronIndex < currentLayer.getNeurons().size(); neuronIndex++) {
			Neuron currentNeuron = currentLayer.getNeurons().get(neuronIndex);
			List<Connection> connections = currentLayer.getOutGoingConnections().get(currentNeuron);
			Double sumWeightedError = 0.0;
			for (int nextNeuronIndex = 0; nextNeuronIndex < downstreamLayer.getNeurons().size(); nextNeuronIndex++) {
				sumWeightedError += errors.get(0).get(nextNeuronIndex) * connections.get(nextNeuronIndex).getWeight();
			}
			
			// multiply by gradient of neuron that is being updated
			Double error = currentLayer.getNeurons().get(neuronIndex).gradient() * sumWeightedError;
			hiddenErrors.add(error);
		}
		errors.add(0, hiddenErrors);
	}
	
	private void updateWeights(List<List<Double>> errors) {
		// update all weights using errors
		for (int layer = 0; layer < layers.size() - 1; layer++) {
			Layer currentLayer = layers.get(layer);
			List<Double> currentErrors = errors.get(layer);
			for (int neuronIndex = 0; neuronIndex < currentLayer.getNeurons().size(); neuronIndex++) {
				Neuron currentNeuron = currentLayer.getNeurons().get(neuronIndex);
				List<Connection> connections = currentLayer.getOutGoingConnections().get(currentNeuron);
				for (int nextNeuron = 0; nextNeuron < connections.size(); nextNeuron++) {
					Connection connection = connections.get(nextNeuron);
					Double currentError = currentErrors.get(nextNeuron);
					// update rule (TODO: check that error is correct)
					Double update = (learningRate * currentNeuron.getOutput() * currentError);
					// update connection weight
					connection.setWeight(update + (connection.getWeight() * momentum));
					connection.setWeightChange(update);
				}
				
			}
		}
	}

}
