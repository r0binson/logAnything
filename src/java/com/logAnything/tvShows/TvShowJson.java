/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logAnything.tvShows;

/**
 * Reminders: When accepting a JSON object 
 * 1. All Json fields/key should be represented in a Java Object. 
 * In other words Json objects should map field for field in your Java Object.
 * 2. You can have more fields in your java object than the expected JSon object and not less. 
 * 3. You should use an object Mapper to map the incoming Json object to you Java object.
 * 4. Remember that Json Objects will be coming in as a String.
 * 
 * @author robin
 */
public class TvShowJson {
	private String title;
	private String dayOfWeek;
	private int latestEpisode;
	private String downloadDate;


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the dayOfWeek
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * @return the latestEpisode
	 */
	public int getLatestEpisode() {
		return latestEpisode;
	}

	/**
	 * @param latestEpisode the latestEpisode to set
	 */
	public void setLatestEpisode(int latestEpisode) {
		this.latestEpisode = latestEpisode;
	}

	/**
	 * @return the downloadDate
	 */
	public String getDownloadDate() {
		return downloadDate;
	}

	/**
	 * @param downloadDate the downloadDate to set
	 */
	public void setDownloadDate(String downloadDate) {
		this.downloadDate = downloadDate;
	}
	
}
