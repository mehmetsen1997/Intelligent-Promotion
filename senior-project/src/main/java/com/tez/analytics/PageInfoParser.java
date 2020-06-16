package com.tez.analytics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.api.services.analyticsreporting.v4.model.DateRangeValues;
import com.google.api.services.analyticsreporting.v4.model.Report;
import com.google.api.services.analyticsreporting.v4.model.ReportRow;

/**
 * The class that is responsible for parsing Google Analytics Page data.
 * @author onurs
 *
 */
public class PageInfoParser {

	private List<Double> parseMetrics(List<DateRangeValues> metrics) {
        List<Double> metricList = new ArrayList<>();
      
        for (int j = 0; j < metrics.size(); j++) {
          DateRangeValues values = metrics.get(j);
          for (int k = 0; k < values.getValues().size(); k++) {
        	  Double metricDouble = Double.parseDouble(values.getValues().get(k));
        	  metricList.add(metricDouble);
          }
        }
        return metricList;
		
	}
	
	public Map<String, List<Double>> parsePageInfos(Report report){
		
		Map<String, List<Double>> pageInfos = new HashMap<>();
		
		List<ReportRow> rows = report.getData().getRows();
		if (rows == null) {
		    System.out.println("No data found");
		}
		
		for (ReportRow row: rows) {
			List<String> dimensions = row.getDimensions();
		    List<DateRangeValues> metrics = row.getMetrics();
		         
		    String dimension = dimensions.get(0);
		    pageInfos.put(dimension, parseMetrics(metrics));
		}
		return pageInfos;
	}
	
}
