package com.ontariotechu.sofe3980U;

import java.io.FileReader;
import java.util.List;
import com.opencsv.*;
import java.lang.Math;
import java.util.*;

/**
 * Evaluate Single Variable Continuous Regression
 *
 */
public class App {

	//
	public static double MSE(List<String[]> allData) {
		double mse = 0;
		int n = allData.size();

		for (String[] row : allData) {
			float x = Float.parseFloat(row[0]);
			float y = Float.parseFloat(row[1]);
			mse += Math.pow((x - y), 2);
		}
		return mse / n;
	}

	public static double MAE(List<String[]> allData) {
		double mae = 0;
		int m = allData.size();

		for (String[] row : allData) {
			float a = Float.parseFloat(row[0]);
			float b = Float.parseFloat(row[1]);
			mae += Math.abs((a - b));
		}
		return mae / m;
	}

	public static double MARE(List<String[]> allData) {
		double mare = 0;
		int o = allData.size();
		float epsilon = Math.ulp(1);
		for (String[] row : allData) {
			float c = Float.parseFloat(row[0]);
			float d = Float.parseFloat(row[1]);
			mare += Math.abs((c - d)) / (Math.abs(c) + epsilon);
		}
		return mare / o;

	}

	public static void main(String[] args) {
		String filePath = "model_1.csv";
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

		int count = 0;
		for (String[] row : allData) {
			float y_true = Float.parseFloat(row[0]);
			float y_predicted = Float.parseFloat(row[1]);
			// System.out.print(y_true + " \t " + y_predicted);
			// System.out.println();
			count++;
			if (count == 10) {
				break;
			}
		}

		double MSE1 = MSE(allData);
		double MAE1 = MAE(allData);
		double MARE1 = MARE(allData);

		filePath = "model_2.csv";
		try {
			filereader = new FileReader(filePath);
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			allData = csvReader.readAll();
		} catch (Exception e) {
			System.out.println("Error reading the CSV file");
			return;
		}

		count = 0;
		for (String[] row : allData) {
			float y_true = Float.parseFloat(row[0]);
			float y_predicted = Float.parseFloat(row[1]);
			// System.out.print(y_true + " \t " + y_predicted);
			// System.out.println();
			count++;
			if (count == 10) {
				break;
			}
		}

		double MSE2 = MSE(allData);
		double MAE2 = MAE(allData);
		double MARE2 = MARE(allData);

		filePath = "model_3.csv";
		try {
			filereader = new FileReader(filePath);
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			allData = csvReader.readAll();
		} catch (Exception e) {
			System.out.println("Error reading the CSV file");
			return;
		}

		count = 0;
		for (String[] row : allData) {
			float y_true = Float.parseFloat(row[0]);
			float y_predicted = Float.parseFloat(row[1]);
			// System.out.print(y_true + " \t " + y_predicted);
			// System.out.println();
			count++;
			if (count == 10) {
				break;
			}
		}

		double MSE3 = MSE(allData);
		double MAE3 = MAE(allData);
		double MARE3 = MARE(allData);

		System.out.println("for model.1.csv \n");
		System.out.println("MSE = " + MSE1 + "\n");
		System.out.println("MAE = " + MAE1 + "\n");
		System.out.println("MARE= " + MARE1 + "\n \n");

		System.out.println("for model_2.csv \n");
		System.out.println("MSE = " + MSE2 + "\n");
		System.out.println("MAE = " + MAE2 + "\n");
		System.out.println("MARE= " + MARE2 + "\n \n");

		System.out.println("for model_3.csv \n");
		System.out.println("MSE = " + MSE3 + "\n");
		System.out.println("MAE = " + MAE3 + "\n");
		System.out.println("MARE= " + MARE3 + "\n \n");

		// double best_MSE = (MSE1 < MSE2 && MSE1 < MSE3 ? "model_1.csv" : (MSE2 < MSE3
		// ? "model_2.csv" : "model_3.csv"));
		// double best_MAE = (MAE1 < MAE2 && MAE1 < MAE3 ? "model_1.csv" : (MAE2 < MAE3
		// ? "model_2.csv" : "model_3.csv"));
		// double best_MARE = (MARE1 < MARE2 && MARE1 < MARE3 ? "model_1.csv" : (MARE2 <
		// MARE3 ? "model_2.csv" : "model_3.csv"));

		System.out.println("According to MSE, The best model is "
				+ (MSE1 < MSE2 && MSE1 < MSE3 ? "model_1.csv" : (MSE2 < MSE3 ? "model_2.csv" : "model_3.csv")) + "\n");
		System.out.println("According to MAE, The best model is "
				+ (MAE1 < MAE2 && MAE1 < MAE3 ? "model_1.csv" : (MAE2 < MAE3 ? "model_2.csv" : "model_3.csv")) + "\n");
		System.out.println("According to MARE, The best model is "
				+ (MARE1 < MARE2 && MARE1 < MARE3 ? "model_1.csv" : (MARE2 < MARE3 ? "model_2.csv" : "model_3.csv"))
				+ "\n");

	}
}
