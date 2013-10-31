package driver;

import java.util.List;

import neural_net.Network;
import neural_net.StructuralInfo;

public class Simulator {
	

	public static void main(String[] args) {
		
		//get input data
		Inputter inputter = new InputterTicTacToe();
		inputter.parseFile();
		List<DataPoint> data = inputter.getData();

		
		// build network from input data. 
		int[] hiddenLayer = new int[2];
		hiddenLayer[0] = 100;
		hiddenLayer[1] = 50;
		StructuralInfo structuralInfo = new StructuralInfo(inputter.getInputs(), inputter.getOutputs(), hiddenLayer);
		Network neuralNetwork = new Network(structuralInfo);
		neuralNetwork.constructNetwork();
		
		// Test GD
		TrainingMethod gd = new GD(neuralNetwork, data);
		gd.mainLoop(10);

		//System.out.println(classValue);
		
		// DE de = new DE(10, 0.5, 0.2);
		// de.run();

		GD mlp = new GD(neuralNetwork, data);
	}

}
