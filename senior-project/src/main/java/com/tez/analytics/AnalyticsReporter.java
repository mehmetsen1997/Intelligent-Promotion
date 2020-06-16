package com.tez.analytics;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
import com.google.api.services.analyticsreporting.v4.model.DateRange;
import com.google.api.services.analyticsreporting.v4.model.Dimension;
import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.Metric;
import com.google.api.services.analyticsreporting.v4.model.Report;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;

@SuppressWarnings("deprecation")
public class AnalyticsReporter {
  private static final String APPLICATION_NAME = "Intelligent Promotion Reporting";
  private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
  private static final String KEY_FILE_LOCATION = "client_secrets.json";
  private static final String VIEW_ID = "215536978";
  private AnalyticsReporting analyticsReporting;
  
  /**
   * Initializes an Analytics Reporting API V4 service object.
   *
   * @return An authorized Analytics Reporting API V4 service object.
   * @throws IOException
   * @throws GeneralSecurityException
   */
  public AnalyticsReporter() {
	  try {
		  this.analyticsReporting = initializeAnalyticsReporting();
	  } catch (GeneralSecurityException | IOException e) {
			System.out.println("Analytics Reporting service couldn't be initiated!");
	  }
  }
  
//  @PostConstruct
//  public void init() {
//	  try {
//		  this.analyticsReporting = initializeAnalyticsReporting();
//	  } catch (GeneralSecurityException | IOException e) {
//			System.out.println("Analytics Reporting service couldn't be initiated!");
//	  }
//  }
  private AnalyticsReporting initializeAnalyticsReporting() throws GeneralSecurityException, IOException {

    HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
    GoogleCredential credential = GoogleCredential
        .fromStream(new FileInputStream(KEY_FILE_LOCATION))
        .createScoped(AnalyticsReportingScopes.all());

    // Construct the Analytics Reporting service object.
    return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
        .setApplicationName(APPLICATION_NAME).build();
  }

  /**
   * Queries the Analytics Reporting API V4.
   *
   * @param service An authorized Analytics Reporting API V4 service object.
   * @return GetReportResponse The Analytics Reporting API V4 response.
   * @throws IOException
   */
  private GetReportsResponse getReportResponse(AnalyticsReporting service) throws IOException {
    // Create the DateRange object.
    DateRange dateRange = new DateRange();
    dateRange.setStartDate("90DaysAgo");
    dateRange.setEndDate("today");
    
    Metric bounceRate = new Metric()
            .setExpression("ga:bounceRate")
            .setAlias("Bounce Rate");

    Metric exitRate = new Metric()
            .setExpression("ga:exitRate")
            .setAlias("Exit Rate");
    
    Metric pageValue = new Metric()
            .setExpression("ga:pageValue")
            .setAlias("Page Value");
    
    
    Dimension pagePath = new Dimension().setName("ga:pagePath");


    // Create the ReportRequest object.
    ReportRequest request = new ReportRequest()
        .setViewId(VIEW_ID)
        .setDateRanges(Arrays.asList(dateRange))
        .setMetrics(Arrays.asList(bounceRate,exitRate,pageValue))
        .setDimensions(Arrays.asList(pagePath));

    ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
    requests.add(request);

    // Create the GetReportsRequest object.
    GetReportsRequest getReport = new GetReportsRequest()
        .setReportRequests(requests);

    // Call the batchGet method.
    GetReportsResponse response = service.reports().batchGet(getReport).execute();

    // Return the response.
    return response;
  }
  
  
  public Report getReport() {
	  Report report = null;
	  try {
		GetReportsResponse response = getReportResponse(analyticsReporting);
		report = response.getReports().get(0);
	  } catch (IOException e) {
		  e.printStackTrace();
		  System.out.println("Error occured while getting response!");
	  }
	  return report;
  }
}
