package driver;

import java.util.List;

import neural_net.Network;
import neural_net.StructuralInfo;

public class Simulator {

	public static void main(String[] args) {

		// get input data
		Inputter inputter = new InputterCar();
		inputter.parseFile();
		List<DataPoint> data = inputter.getData();

		// build network from input data.
		int[] hiddenLayer = new int[1];
		hiddenLayer[0] = 10;
		StructuralInfo structuralInfo = new StructuralInfo(inputter.getInputs(), inputter.getOutputs(), hiddenLayer);
		Network neuralNetwork = new Network(structuralInfo);
		neuralNetwork.constructNetwork();
		structuralInfo.describe();

		// Test GD
		TrainingMethod gd = new GDTraining(neuralNetwork, data);
		gd.mainLoop(10);

		// System.out.println(classValue);

		// DE de = new DE(10, 0.5, 0.2);
		// de.run();

		GDTraining mlp = new GDTraining(neuralNetwork, data);
	}

	public static void printVector(List<Double> vector) {
		for (Double value : vector)
			System.out.print(value + " ");
		System.out.println();
	}

}
