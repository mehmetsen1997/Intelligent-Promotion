package com.tez.service;

import java.util.List;

import org.apache.spark.ml.feature.MinMaxScaler;
import org.apache.spark.ml.feature.MinMaxScalerModel;
import org.apache.spark.ml.feature.StringIndexer;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tez.model.cassandra.Visit;
import com.tez.repository.VisitRepository;

@Service
public class DatasetService{
	
	@Autowired private VisitRepository vr;
	@Autowired private SparkSession spark;
	
	public DatasetService() {
		
	}
	

	public Dataset<Row> getDataset(){
		List<Visit> visits = vr.findAll();
		
		Dataset<Row> tempDataset = spark.createDataFrame(visits, Visit.class);
		Dataset<Row> visitDataset = tempDataset.drop("sessionId");
		Dataset<Row> preprocessed = preprocessDataset(visitDataset);
		return preprocessed;
	}
	
	public Dataset<Row> preprocessDataset(Dataset<Row> data){
		VectorAssembler assembler = new VectorAssembler()
				  .setInputCols(new String[]{"administrativePageview", "administrativeDuration", "informativePageview",
						  "informativeDuration", "productRelatedPageview", "productRelatedDuration", "bounceRate", "exitRate",
						  "pageValue", "specialDay"})
				  .setOutputCol("features");

		Dataset<Row> output = assembler.transform(data);
		Dataset<Row> indexed = indexLabel(output);
		//Dataset<Row> scaled = scaleFeatures(indexed);
		
		return indexed;
	}
	
	public Dataset<Row> indexLabel(Dataset<Row> data){
		StringIndexer indexer = new StringIndexer()
				  .setInputCol("revenue")
				  .setOutputCol("revenue_indexed");
		Dataset<Row> indexed = indexer.fit(data).transform(data);
		Dataset<Row> dataset = indexed.drop("revenue");
		return dataset;
	}
	
	public Dataset<Row> scaleFeatures(Dataset<Row> data) {
		MinMaxScaler scaler = new MinMaxScaler()
				  .setInputCol("features")
				  .setOutputCol("scaledFeatures");

		// Compute summary statistics and generate MinMaxScalerModel
		MinMaxScalerModel scalerModel = scaler.fit(data);
		// rescale each feature to range [min, max].
		Dataset<Row> scaledData = scalerModel.transform(data);
		return scaledData;
	}
	
	
	

}
