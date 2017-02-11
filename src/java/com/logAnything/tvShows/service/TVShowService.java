/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logAnything.tvShows.service;

import com.logAnything.tvShows.Episode;
import com.logAnything.tvShows.TVShow;
import java.util.List;

/**
 *
 * @author robin
 */
public interface TVShowService {

	public int createNewShow(TVShow tvShow);

	public int createNewEpisode(Episode episode);

	public List getAllShows();

	public List getShowsByDayOfWeek(String dayOfWeek);

	public List getTodayShows();

	public List getShowsByTitle(String title);

	public TVShow getShowById(String id);

}
