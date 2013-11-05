package driver;

import java.util.List;

import neural_net.Layer;
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
<<<<<<< HEAD
		TrainingMethod gd = new GDTraining(neuralNetwork, data);
		gd.mainLoop(10);

		// System.out.println(classValue);

		// DE de = new DE(10, 0.5, 0.2);
		// de.run();

		GDTraining mlp = new GDTraining(neuralNetwork, data);
=======
//		TrainingMethod gd = new GDTraining(neuralNetwork, data);
//		gd.mainLoop(10);
		
		// Test GA
		TrainingMethod ga = new GATraining(neuralNetwork, data);
		ga.mainLoop(10);
		
>>>>>>> e1e02fe180c8dfe71449ebde21bcf8260226a457
	}

	public static void printVector(List<Double> vector) {
		for (Double value : vector)
			System.out.print(value + " ");
		System.out.println();
	}

}
