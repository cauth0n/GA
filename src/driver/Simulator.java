package driver;

import java.util.List;

import neural_net.Network;
import neural_net.StructuralInfo;
import driver.inputter.*;

/**
 * Main driver used to test various neural network training algorithms.
 */
public class Simulator {

	public static void main(String[] args) {
		
		int maxDataSetSize = 1000;
		
		// tic-tac-toe
		// seeds
		// banknote

		// get input data
		Inputter inputter = new InputterBanknote();
		inputter.parseFile();
		inputter.truncate(maxDataSetSize);
		List<DataPoint> data = inputter.getData();

		// build network from input data.
		int[] hiddenLayer = new int[4];
		hiddenLayer[0] = 2;
		hiddenLayer[1] = 5;
		hiddenLayer[2] = 2;
		hiddenLayer[3] = 5;
		
		StructuralInfo structuralInfo = new StructuralInfo(inputter.getInputs(), inputter.getOutputs(), hiddenLayer);
		Network neuralNetwork = new Network(structuralInfo);
		neuralNetwork.constructNetwork();
		structuralInfo.describe();
		
		TrainingMethod train;

		// Test GD
		train = new GDTraining(neuralNetwork, data);
		train.mainLoop(10);

//		// Test GA
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
