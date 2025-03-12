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
	public static double BCE(List<String[]> allData) {
		double bce = 0;
		int n = allData.size();

		for (String[] row : allData) {
			int m = Integer.parseInt(row[0]);
			float o = Float.parseFloat(row[1]);
			bce += (m * Math.log(o)) + ((1 - m) * Math.log(1 - o));
		}
		return (-bce / n);
	}

	public static void matrix(List<String[]> allData) {
		int tp = 0, fp = 0, tn = 0, fn = 0;
		int m = allData.size();

		for (String[] row : allData) {
			int a = Integer.parseInt(row[0]);
			float b = Float.parseFloat(row[1]);

			if (a == 1 && b >= 0.5) {
				tp++;
			}
			if (a == 1 && b <= 0.5) {
				fn++;
			}
			if (a == 0 && b >= 0.5) {
				fp++;
			}
			if (a == 0 && b <= 0.5) {
				tn++;
			}
		}

		System.out.println("Confusion matrix: \n");
		System.out.println("\ty=1\ty=0");
		System.out.println("y^=1\t" + tp + "\t" + fp);
		System.out.println("y^=0\t" + fn + "\t" + tn);

		double accuracy = (tp + tn) / (double) (tp + tn + fn + fp);
		double precision = (tp) / (double) (tp + fp);
		double recall = (tp) / (double) (tp + fn);
		double f1 = 2 * ((precision * recall) / (precision + recall));

		System.out.println("Accuracy = " + accuracy);
		System.out.println("Precision = " + precision);
		System.out.println("Recall = " + recall);
		System.out.println("f1 score = " + f1);
	}

	public static double AUC(List<String[]> allData) {
		int positive = 0, negative = 0;

		for (String[] row : allData) {
			int values = Integer.parseInt(row[0]);
			if (values == 1) {
				positive++;
			}
			if (values == 0) {
				negative++;
			}
		}

		double auc = 0;
		double[] x = new double[101];
		double[] y = new double[101];

		for (int i = 0; i <= 100; i++) {
			double th = i / 100.0;
			int tp = 0, fp = 0;

			for (String[] row : allData) {
				int c = Integer.parseInt(row[0]); // actual value
				float d = Float.parseFloat(row[1]);// predicted value

				if (c == 1 && d >= th) {
					tp++;
				}
				if (c == 0 && d >= th) {
					fp++;
				}
			}

			double tpr = tp / (double) positive;
			double fpr = fp / (double) negative;

			x[i] = fpr;
			y[i] = tpr;

			if (i > 0) {
				auc += (y[i] + y[i - 1]) * Math.abs(x[i] - x[i - 1]) / 2;
			}
		}

		return auc;

	}

	public static void main(String[] args) {
		// model1.csv
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
			int y_true = Integer.parseInt(row[0]);
			float y_predicted = Float.parseFloat(row[1]);
			// System.out.print(y_true + " \t " + y_predicted);
			// System.out.println();
			count++;
			if (count == 10) {
				break;
			}
		}

		double bce1 = BCE(allData);
		double auc1 = AUC(allData);
		System.out.println("for model_1.scv");
		System.out.println("BCE = " + bce1);
		matrix(allData);
		System.out.println("AUC ROC = " + auc1 + "\n\n");

		// model2.csv
		filePath = "model_2.csv";
		try {
			filereader = new FileReader(filePath);
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			allData = csvReader.readAll();
		} catch (Exception e) {
			System.out.println("Error reading the CSV file");
			return;
		}

		int count2 = 0;
		for (String[] row : allData) {
			int y_true = Integer.parseInt(row[0]);
			float y_predicted = Float.parseFloat(row[1]);
			// System.out.print(y_true + " \t " + y_predicted);
			// System.out.println();
			count2++;
			if (count2 == 10) {
				break;
			}
		}

		double bce2 = BCE(allData);
		double auc2 = AUC(allData);
		System.out.println("for model_2.scv");
		System.out.println("BCE = " + bce2);
		matrix(allData);
		System.out.println("AUC ROC = " + auc2 + "\n\n");

		// model3.csv
		filePath = "model_3.csv";
		try {
			filereader = new FileReader(filePath);
			CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
			allData = csvReader.readAll();
		} catch (Exception e) {
			System.out.println("Error reading the CSV file");
			return;
		}

		int count3 = 0;
		for (String[] row : allData) {
			int y_true = Integer.parseInt(row[0]);
			float y_predicted = Float.parseFloat(row[1]);
			// System.out.print(y_true + " \t " + y_predicted);
			// System.out.println();
			count3++;
			if (count3 == 10) {
				break;
			}
		}

		double bce3 = BCE(allData);
		double auc3 = AUC(allData);
		System.out.println("for model_3.scv");
		System.out.println("BCE = " + bce3);
		matrix(allData);
		System.out.println("AUC ROC = " + auc3 + "\n\n");

	}

}
