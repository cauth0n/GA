package driver;

import java.util.List;
import java.util.ArrayList;

import neural_net.Connection;
import neural_net.Layer;
import neural_net.Network;
import neural_net.Neuron;

/**
 * @author cauthon
 */
public class GD extends TrainingMethod {
	
	Double errorThreshold = 0.1;
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
			for (int iteration = 0; sumError > errorThreshold && iteration < maxIterations; iteration++) {
				output = networkOperations.feedForward(datapoint);
				target = datapoint.getOutputs();
				backpropogate(target, output);
				sumError = calculateError(target, output);
			}
		}
	}
	
	private void backpropogate(List<Double> target, List<Double> output) {
		
		List<Layer> layers = neuralNetwork.getLayers();
		List<List<Double>> errors = new ArrayList<List<Double>>();
		int outIndex = layers.size() - 1;
		
		// calculate output errors
		List<Double> outputErrors = new ArrayList<>();
		Layer outputLayer = layers.get(outIndex);
		for (int neuronIndex = 0; neuronIndex < outputLayer.getNeurons().size(); neuronIndex++) {
			// calculate difference between expected and actual output
			Double diff = (target.get(neuronIndex).doubleValue() - output.get(neuronIndex).doubleValue());
			// multiply by gradient of neuron that is being updated
			Double error = outputLayer.getNeurons().get(neuronIndex).gradient() * diff;
			outputErrors.add(error);
		}
		errors.add(outputErrors);
		
		// update all hidden layers
		for (int hiddenLayer = outIndex - 1; hiddenLayer > 0; hiddenLayer--) {
			Layer currentLayer = layers.get(hiddenLayer);
			Layer nextLayer = layers.get(hiddenLayer + 1);
			List<Double> currentHiddenErrors = new ArrayList<>();
			for (int neuronIndex = 0; neuronIndex < currentLayer.getNeurons().size(); neuronIndex++) {
				Neuron currentNeuron = currentLayer.getNeurons().get(neuronIndex);
				List<Connection> connections = currentLayer.getOutGoingConnections().get(currentNeuron);
				Double sumWeightedError = 0.0;
				for (int nextNeuronIndex = 0; nextNeuronIndex < nextLayer.getNeurons().size(); nextNeuronIndex++) {
					sumWeightedError += errors.get(0).get(nextNeuronIndex) * connections.get(nextNeuronIndex).getWeight();
				}
				
				// multiply by gradient of neuron that is being updated
				Double error = outputLayer.getNeurons().get(neuronIndex).gradient() * sumWeightedError;
				currentHiddenErrors.add(error);
			}
			
			// prepend the errors to beginning of list
			errors.add(0, currentHiddenErrors);
			
		}
		
		// update all weights using errors
		for (int layer = 0; layer < layers.size(); layer++) {
			Layer currentLayer = layers.get(layer);
			List<Double> currentErrors = errors.get(layer);
			for (int neuronIndex = 0; neuronIndex < currentLayer.getNeurons().size(); neuronIndex++) {
				Neuron currentNeuron = currentLayer.getNeurons().get(neuronIndex);
				List<Connection> connections = currentLayer.getOutGoingConnections().get(currentNeuron);
				Double currentError = currentErrors.get(neuronIndex);
				// update rule (TODO: check that error is correct)
				Double update = (learningRate * currentNeuron.getOutput() * currentError);
				// update connection weight
				Connection connection = connections.get(neuronIndex); // FIXME: this is way wrong. there's something bad about this whole function
				connection.setWeight(update + (connection.getWeight() * momentum));
				connection.setWeightChange(update);
			}
		}
		
	}

	@Override
	public double test(List<DataPoint> testSet) {
		// TODO this might belong in the abstract class, since it shouldn't change between training methods
		int classFound, classExpected;
		int correct = 0;
		List<Double> outputs;
		for (int exampleIndex = 0; exampleIndex < testSet.size(); exampleIndex++) {
			DataPoint datapoint = testSet.get(exampleIndex);
			outputs = networkOperations.feedForward(datapoint);
			classFound = networkOperations.getMaxIndex(outputs);
			classExpected = datapoint.getClassIndex();
			if (classFound == classExpected)
				correct++;
		}
		double performance = correct/testSet.size();
		return performance;
	}

}
