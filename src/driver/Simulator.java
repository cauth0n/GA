package driver;

import java.util.List;

import neural_net.Network;
import neural_net.StructuralInfo;

public class Simulator {

	public static void main(String[] args) {

		// get input data
		Inputter inputter = new InputterBanknote();
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

		// Test GD
//		TrainingMethod gd = new GDTraining(neuralNetwork, data);
//		gd.mainLoop(10);

		// Test GA
		TrainingMethod ga = new GATraining(neuralNetwork, data);
		ga.mainLoop(10);

	}

	public static void printVector(List<Double> vector) {
		for (Double value : vector)
			System.out.print(value + " ");
		System.out.println();
	}

}
