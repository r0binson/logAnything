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
public class Episode {

	private int tvId;
	private int episodeNumber;
	private String downloadDate;

	public Episode(){
		super();
	}
	
	public Episode(int tvId, int episodeNumber, String downloadDate) {
		this.tvId = tvId;
		this.episodeNumber = episodeNumber;
		this.downloadDate = downloadDate;
	}

	/**
	 * @return the episodeNumber
	 */
	public int getEpisodeNumber() {
		return episodeNumber;
	}

	/**
	 * @return the downloadDate
	 */
	public String getDownloadDate() {
		return downloadDate;
	}

	/**
	 * @return the tvId
	 */
	public int getTvId() {
		return tvId;
	}


	/**
	 * @param tvId the tvId to set
	 */
	public void setTvId(int tvId) {
		this.tvId = tvId;
	}

	/**
	 * @param episodeNumber the episodeNumber to set
	 */
	public void setEpisodeNumber(int episodeNumber) {
		this.episodeNumber = episodeNumber;
	}

	/** 
	 * @param downloadDate the download date
	 */
	public void setDownloadDate(String downloadDateStr) {
		this.downloadDate = downloadDateStr;
	}
	
}
