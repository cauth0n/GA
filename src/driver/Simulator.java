package driver;

import java.util.List;

import neural_net.Network;
import neural_net.StructuralInfo;

/**
 * Main driver used to test various neural network training algorithms.
 */
public class Simulator {

	public static void main(String[] args) {

		// get input data
		Inputter inputter = new InputterCar();
		inputter.parseFile();
		List<DataPoint> data = inputter.getData();

		// build network from input data.
		int[] hiddenLayer = new int[2];
		hiddenLayer[0] = 50;
		hiddenLayer[1] = 25;
		StructuralInfo structuralInfo = new StructuralInfo(
				inputter.getInputs(), inputter.getOutputs(), hiddenLayer);
		Network neuralNetwork = new Network(structuralInfo);
		neuralNetwork.constructNetwork();
		structuralInfo.describe();
		
		TrainingMethod train;

		// Test GD
		train = new GDTraining(neuralNetwork, data);
		train.mainLoop(10);

		// Test GA
//		train = new GATraining(neuralNetwork, data);
//		train.mainLoop(10);
		
		//Test DE
//		train = new DETraining(neuralNetwork, data);
//		train.mainLoop(10);
		
		
		// Test ES
//		train = new ESTraining(neuralNetwork, data);
//		System.out.println(train);
//		train.mainLoop(10);

	}

	// Utility function used in debugging to print vectors.
	public static void printVector(List<Double> vector) {
		for (Double value : vector)
			System.out.print(value + " ");
		System.out.println();
	}

}
