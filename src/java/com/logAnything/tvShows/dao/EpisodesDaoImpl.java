/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logAnything.tvShows.dao;

import com.logAnything.tvShows.Episode;
import org.springframework.stereotype.Component;

/**
 *
 * @author robin
 */
@Component("EpisodesDaoImpl")
public class EpisodesDaoImpl extends Dao implements EpisodesDao {

	@Override
	public int createNewEpisode(Episode episode){
		String SQL = "Insert into episodes (tvId, episode, downloadDate) values (?, ?, ?)";
		Object[] params = new Object[]{
			episode.getTvId(),
			episode.getEpisodeNumber(),
			episode.getDownloadDate()
		};
		return jdbcTemplate.update(SQL, params);
	}	
}
