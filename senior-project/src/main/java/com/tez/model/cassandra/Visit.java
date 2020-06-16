package com.tez.model.cassandra;


import java.time.LocalDateTime;

import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.ml.linalg.Vectors;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import com.tez.dto.ClickstreamDataDto;
import com.tez.dto.PageInfo;
import com.tez.model.postgresql.Content;

/** The class is used to store session data in cassandra database.
 * 
 * @author onurs
 *
 */

@Table(value = "visit")
public class Visit {
	
	@PrimaryKeyColumn(value = "session_id", type = PrimaryKeyType.PARTITIONED)
	//@Column("session_id")
	private String sessionId;
	@Column("administrative_pageview")
	private Integer administrativePageview;
	@Column("administrative_duration")
	private Double administrativeDuration;
	@Column("informative_pageview")
	private Integer informativePageview;
	@Column("informative_duration")
	private Double informativeDuration;
	@Column("product_related_pageview")
	private Integer productRelatedPageview;
	@Column("product_related_duration") 
	private Double productRelatedDuration;
	@Column("bounce_rate") 
	private Double bounceRate;
	@Column("exit_rate")
	private Double exitRate;
	@Column("page_value")
	private Double pageValue;
	@Column("special_day")
	private Double specialDay;
	@Column("revenue") 
	private String revenue;
	@Transient
	private Double totalTime;
	@Transient
	private Double purchaseProbability;
	@Transient
	private LocalDateTime lastInformation;
	@Transient
	private String currentPage;
	@Transient
	private Integer contentId;
	@Transient
	private Double threshold;
	
	public Visit() {
		
	}
	
	
	
	public Visit(String sessionId, Integer administrativePageview, Double administrativeDuration,
			Integer informativePageview, Double informativeDuration, Integer productRelatedPageview,
			Double productRelatedDuration, Double bounceRate, Double exitRate, Double pageValue, Double specialDay,
			String revenue) {
		this.sessionId = sessionId;
		this.administrativePageview = administrativePageview;
		this.administrativeDuration = administrativeDuration;
		this.informativePageview = informativePageview;
		this.informativeDuration = informativeDuration;
		this.productRelatedPageview = productRelatedPageview;
		this.productRelatedDuration = productRelatedDuration;
		this.bounceRate = bounceRate;
		this.exitRate = exitRate;
		this.pageValue = pageValue;
		this.specialDay = specialDay;
		this.revenue = revenue;
	}



	public Visit(ClickstreamDataDto cd, PageInfo pi, Double specialDay) {
		this.sessionId = cd.getSessionId();
		this.administrativePageview = cd.getAdministrative();
		this.administrativeDuration = cd.getAdministrativeDuration();
		this.informativePageview = cd.getInformative();
		this.informativeDuration = cd.getInformativeDuration();
		this.productRelatedPageview = cd.getProductRelated();
		this.productRelatedDuration = cd.getProductRelatedDuration();
		this.bounceRate = pi.getBounceRate();
		this.exitRate = pi.getExitRate();
		this.pageValue = pi.getPageValue();
		this.specialDay = specialDay;
		this.revenue = cd.getRevenue();
		this.currentPage = cd.getCurrentPage();
		setTotalTime();
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getAdministrativePageview() {
		return administrativePageview;
	}
	public void setAdministrativePageview(Integer administrativePageview) {
		this.administrativePageview = administrativePageview;
	}
	public Double getAdministrativeDuration() {
		return administrativeDuration;
	}
	public void setAdministrativeDuration(Double administrativeDuration) {
		this.administrativeDuration = administrativeDuration;
	}
	public Integer getInformativePageview() {
		return informativePageview;
	}
	public void setInformativePageview(Integer informativePageview) {
		this.informativePageview = informativePageview;
	}
	public Double getInformativeDuration() {
		return informativeDuration;
	}
	public void setInformativeDuration(Double informativeDuration) {
		this.informativeDuration = informativeDuration;
	}
	public Integer getProductRelatedPageview() {
		return productRelatedPageview;
	}
	public void setProductRelatedPageview(Integer productRelatedPageview) {
		this.productRelatedPageview = productRelatedPageview;
	}
	public Double getProductRelatedDuration() {
		return productRelatedDuration;
	}
	public void setProductRelatedDuration(Double productRelatedDuration) {
		this.productRelatedDuration = productRelatedDuration;
	}
	public Double getBounceRate() {
		return bounceRate;
	}
	public void setBounceRate(Double bounceRate) {
		this.bounceRate = bounceRate;
	}
	public Double getExitRate() {
		return exitRate;
	}
	public void setExitRate(Double exitRate) {
		this.exitRate = exitRate;
	}
	public Double getPageValue() {
		return pageValue;
	}
	public void setPageValue(Double pageValue) {
		this.pageValue = pageValue;
	}
	public Double getSpecialDay() {
		return specialDay;
	}
	public void setSpecialDay(Double specialDay) {
		this.specialDay = specialDay;
	}
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
	
	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer content) {
		this.contentId = content;
	}

	public Double getPurchaseProbability() {
		return purchaseProbability;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setPurchaseProbability(Double purchaseProbability) {
		this.purchaseProbability = purchaseProbability;
	}

	public LocalDateTime getLastInformation() {
		return lastInformation;
	}



	public void setLastInformation(LocalDateTime lastInformation) {
		this.lastInformation = lastInformation;
	}



	public Double getTotalTime() {
		return totalTime;
	}



	private void setTotalTime() {
		this.totalTime = administrativeDuration + informativeDuration + productRelatedDuration;
	}

	

	public Double getThreshold() {
		return threshold;
	}



	public void setThreshold(Double threshold) {
		this.threshold = threshold;
	}



	public Vector getFeatureVector() {
		double[] features = new double[] { Double.valueOf(getAdministrativePageview()), getAdministrativeDuration(),
										   Double.valueOf(getInformativePageview()), getInformativeDuration(),
								           Double.valueOf(getProductRelatedPageview()), getProductRelatedDuration(),	
										   getBounceRate(), getExitRate(), getPageValue(), getSpecialDay()};
		return Vectors.dense(features);
	}
	


	@Override
	public String toString() {
		return "Visit [sessionId=" + sessionId + ", administrativePageview=" + administrativePageview
				+ ", administrativeDuration=" + administrativeDuration + ", informativePageview=" + informativePageview
				+ ", informativeDuration=" + informativeDuration + ", productRelatedPageview=" + productRelatedPageview
				+ ", productRelatedDuration=" + productRelatedDuration + ", bounceRate=" + bounceRate + ", exitRate="
				+ exitRate + ", pageValue=" + pageValue + ", specialDay=" + specialDay + ", revenue=" + revenue + "]";
	}
	
	
	
	
	
}
