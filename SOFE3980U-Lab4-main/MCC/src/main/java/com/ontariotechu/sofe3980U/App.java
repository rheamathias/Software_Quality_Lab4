package com.ontariotechu.sofe3980U;

import java.io.FileReader;
import java.util.List;
import com.opencsv.*;

/**
 * Evaluate Multi-Class Classification Problem
 *
 */
public class App {
	public static void main(String[] args) {
		String filePath = "model.csv";
		FileReader filereader;
		List<String[]> allData;

		try {
			filereader = new FileReader(filePath);
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			allData = csvReader.readAll();
		} catch (Exception e) {
			System.out.println("Error reading the CSV file");
			return;
		}

		int m = 5;
		int n = allData.size();
		double ce = 0.0;
		int[][] confusionMatrix = new int[m][m];

		for (String[] row : allData) {
			int y_true = Integer.parseInt(row[0]) - 1;
			float[] y_predicted = new float[m];

			for (int i = 0; i < m; i++) {
				y_predicted[i] = Float.parseFloat(row[i + 1]);
			}

			ce -= Math.log(y_predicted[y_true]);

			int predictedClass = getMaxIndex(y_predicted);
			confusionMatrix[y_true][predictedClass]++;
		}

		ce /= n;

		System.out.println("CE = " + ce);

		System.out.println("Confusion matrix");
		System.out.println("\t y=1 \t y=2 \t y=3 \t y=4 \t y=5");
		for (int i = 0; i < m; i++) {
			System.out.print("y^=" + (i + 1) + "\t");
			for (int j = 0; j < m; j++) {
				System.out.print(confusionMatrix[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private static int getMaxIndex(float[] y_predicted) {
		int maxIndex = 0;
		for (int i = 1; i < y_predicted.length; i++) {
			if (y_predicted[i] > y_predicted[maxIndex]) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}
}