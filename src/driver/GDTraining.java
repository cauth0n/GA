package driver;

import java.util.HashMap;
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
public class GDTraining extends TrainingMethod {
	
	Double errorThreshold = 0.0001;
	int maxIterations = 10;
	Double learningRate = 0.3;
	Double momentum = 0.0;

	public GDTraining(Network neuralNetwork, List<DataPoint> data) {
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
			
			//break;
			
		}
	}
	
	private void backpropogate(List<Double> target, List<Double> output) {
		
		Map<Neuron, Double> errors = new HashMap<Neuron, Double>();
		
		// calculate output errors
		calculateOutputErrors(errors, target, output);
		
		// calculate all hidden layers errors
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
	
	private void calculateOutputErrors(Map<Neuron, Double> errors, List<Double> target, List<Double> output) {
		
		List<Double> outputErrors = new ArrayList<>();
		Layer outputLayer = layers.get(outIndex);
		for (int neuronIndex = 0; neuronIndex < outputLayer.getNeurons().size(); neuronIndex++) {
			// calculate difference between expected and actual output
			Double diff = (target.get(neuronIndex).doubleValue() - output.get(neuronIndex).doubleValue());
			// multiply by gradient of neuron that is being updated
			Double error = outputLayer.getNeurons().get(neuronIndex).gradient() * diff;
			outputErrors.add(error);
			errors.put(outputLayer.getNeurons().get(neuronIndex), error);
		}
		
	}

	private void calculateHiddenErrors(Map<Neuron, Double> errors, Layer currentLayer, Layer downstreamLayer) {
		
		List<Double> hiddenErrors = new ArrayList<>();
		for (int neuronIndex = 0; neuronIndex < currentLayer.getNeurons().size(); neuronIndex++) {
			Neuron currentNeuron = currentLayer.getNeurons().get(neuronIndex);
			List<Connection> connections = currentLayer.getOutGoingConnections().get(currentNeuron);
			Double sumWeightedError = 0.0;
			for (int nextNeuronIndex = 0; nextNeuronIndex < downstreamLayer.getNeurons().size(); nextNeuronIndex++) {
				Connection connection = connections.get(nextNeuronIndex);
				sumWeightedError += errors.get(connection.getToNeuron()) * connection.getWeight();
				//sumWeightedError += errors.get(0).get(nextNeuronIndex) * connections.get(nextNeuronIndex).getWeight();
			}
			
			// multiply by gradient of neuron that is being updated
			Double error = currentLayer.getNeurons().get(neuronIndex).gradient() * sumWeightedError;
			errors.put(currentNeuron, error);
		}
		
	}
	
	private void updateWeights(Map<Neuron, Double> errors) {
		// update all weights using errors
		for (int layer = 0; layer < layers.size() - 1; layer++) {
			Layer currentLayer = layers.get(layer);
			for (int neuronIndex = 0; neuronIndex < currentLayer.getNeurons().size(); neuronIndex++) {
				Neuron currentNeuron = currentLayer.getNeurons().get(neuronIndex);
				List<Connection> connections = currentLayer.getOutGoingConnections().get(currentNeuron);
				for (int nextNeuron = 0; nextNeuron < connections.size(); nextNeuron++) {
					Connection connection = connections.get(nextNeuron);
					Double currentError = errors.get(connection.getToNeuron());
					
					Double update = (learningRate * currentNeuron.getOutput() * currentError);
					// update connection weight
					connection.setWeight(connection.getWeight() + update);
					//connection.setWeight(update + (connection.getWeight() * momentum));
					connection.setWeightChange(update);
				}
				
			}
		}
	}

}
