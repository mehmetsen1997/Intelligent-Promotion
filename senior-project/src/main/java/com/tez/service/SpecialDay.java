package com.tez.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tez.analytics.PageInfoRetriever;

public enum SpecialDay {

	//Year info doesn'matter, because below special days are repeating in every year 
	HappyValentinesDay(LocalDate.of(1,Month.FEBRUARY,14)),
	HappyFathersDay(LocalDate.of(1,Month.MAY,12)),
	NewYear(LocalDate.of(1,Month.JANUARY,1));
	
	private LocalDate date;
	
	SpecialDay(LocalDate date) {
		this.date = date;
	}
	
	/**
	 * The method that returns special day value by looking all special days.
	 * If current date is between two or more special day, special day value will be calculated for all of these days and maks value
	 * will be returned. 
	 * @param date Visitor's current visit date.
	 * @return
	 */
	public static Double getDistance(LocalDate date) {
		List<Double> possibleValues = new ArrayList<>();
		possibleValues.add(0.0); // If current date is after all special days, 0.0 will be returned.
		
		for(SpecialDay specialDay: SpecialDay.values()) {
			// updatedSpecialDay is used for editing special day's year info due to date subtraction operations.
			// special days' years are specified as 1, because they happen in every year and year info doesn't matter until
			// subtracting dates from each other.
			LocalDate updatedSpecialDay;
			if(specialDay == SpecialDay.NewYear) {
				// New year is the special case, because of this we update the year as visitor' year plus one
				// Actually, this can be applied any special day between 1-14 January.
				updatedSpecialDay = LocalDate.of(date.getYear()+1, specialDay.getDate().getMonth(), 
												 specialDay.getDate().getDayOfMonth());
			}
			else {
				updatedSpecialDay = LocalDate.of(date.getYear(), specialDay.getDate().getMonth(), 
						 specialDay.getDate().getDayOfMonth());
			}
			
			// If current date is after special day then skip that special day
			if(date.isAfter(updatedSpecialDay)) {
				continue;
			}
			
			int differenceDay = (int) ChronoUnit.DAYS.between(date, updatedSpecialDay);
			System.out.println(differenceDay);

			possibleValues.add(calculateDistanceValue(differenceDay));
		}
		return Collections.max(possibleValues);
	}
	
	// special day value is calculated according to below formula.
	// It takes maks value if the distance to the special day is equal to 6.
	// And it cares only between range 1-14 days distance to the special day.
	public static Double calculateDistanceValue(int differenceDay) {
		if(differenceDay > 1 && differenceDay < 14) {
			return Double.valueOf( Math.abs(1-(differenceDay - 6)*0.2) );
		}
		return Double.valueOf(0);
	}
	
	public static void main(String[] args) {
		Double result = SpecialDay.getDistance(LocalDate.now());
		System.out.println(result);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
