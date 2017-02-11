/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logAnything.tvShows;

/**
 *
 * @author robin
 */
public class TVShow {
	private int id;
	private String title;
	private String dayOfWeek;
	private int latestEpisode;
	private String downloadDate;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

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
