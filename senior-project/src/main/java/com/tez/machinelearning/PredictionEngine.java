package com.tez.machinelearning;

import java.util.Arrays;
import java.util.Collections;

import org.apache.spark.ml.classification.RandomForestClassificationModel;
import org.apache.spark.ml.classification.RandomForestClassifier;
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tez.model.cassandra.Visit;
import com.tez.service.DatasetService;

@Service
public class PredictionEngine{
	@Autowired private DatasetService ds;
	private RandomForestClassificationModel model;
	private RandomForestClassificationModel tempModel;
	private double f1;
	
	public PredictionEngine() {
		
	}

	//@PostConstruct
	@Scheduled(fixedRate = 2000000)
	public void train() {
		Dataset<Row> dataset = ds.getDataset();
		Dataset<Row>[] splits = dataset.randomSplit(new double[] {0.7, 0.3});
		Dataset<Row> training = splits[0];
		Dataset<Row> test = splits[1];

//		RandomForestClassifier rf = new RandomForestClassifier()
//				  .setLabelCol("revenue_indexed").setFeaturesCol("scaledFeatures").setPredictionCol("predictedLabel");
		RandomForestClassifier rf = new RandomForestClassifier()
				  .setLabelCol("revenue_indexed").setFeaturesCol("features").setPredictionCol("predictedLabel");
		this.tempModel = rf.fit(training);
		this.model = this.tempModel;
		Dataset<Row> predictions = this.model.transform(test);
		setF1(predictions);
		System.out.println("f1 = " + this.f1);
		System.out.println("trained");
	}

	public void update() {
		train();
	}
	
	public double[] predict(Visit visit) {
		Vector probability = this.model.predictProbability(visit.getFeatureVector());
		double[] probabilities = probability.toArray();
		double label = this.model.predict(visit.getFeatureVector());
		return new double[] {label, probabilities[1]};
	}


	public double getF1() {
		return f1;
	}


	public void setF1(Dataset<Row> predictions) {
		MulticlassClassificationEvaluator evaluator = new MulticlassClassificationEvaluator()
				  .setLabelCol("revenue_indexed")
				  .setPredictionCol("predictedLabel")
				  .setMetricName("f1");
		this.f1 = evaluator.evaluate(predictions);
	}
	
	
	
}
