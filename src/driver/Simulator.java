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
		Inputter inputter;
//		inputter = new InputterAbalone();
//		inputter = new InputterBanknote();
//		inputter = new InputterBlood();
//		inputter = new InputterCar();
//		inputter = new InputterEEGEyeState();
//		inputter = new InputterGlass();
//		inputter = new InputterPoker();
//		inputter = new InputterSeeds();
//		inputter = new InputterTicTacToe();
		inputter = new InputterYeast();
		
		
		// parse data
		inputter.parseFile();
		inputter.truncate(maxDataSetSize);
		List<DataPoint> data = inputter.getData();

		// build network from input data.
		int[] hiddenLayer = new int[] { 20, 15 };
		
		StructuralInfo structuralInfo = new StructuralInfo(inputter.getInputs(), inputter.getOutputs(), hiddenLayer);
		Network neuralNetwork = new Network(structuralInfo);
		neuralNetwork.constructNetwork();
		structuralInfo.describe();
		
		TrainingMethod train;

		// Test GD
//		train = new GDTraining(neuralNetwork, data);

//		// Test GA
		train = new GATraining(neuralNetwork, data);
		
		//Test DE
//		train = new DETraining(neuralNetwork, data);
		
		// Test ES
//		train = new ESTraining(neuralNetwork, data);
//		System.out.println(train);
		
		train.mainLoop(10);

	}

	// Utility function used in debugging to print vectors.
	public static void printVector(List<Double> vector) {
		for (Double value : vector)
			System.out.print(value + " ");
		System.out.println();
	}

}
