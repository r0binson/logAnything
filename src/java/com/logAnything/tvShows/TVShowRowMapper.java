/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logAnything.tvShows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author robin
 */
public class TVShowRowMapper implements RowMapper<TVShow>{

	@Override
	public TVShow mapRow(ResultSet rs, int i) throws SQLException {
		TVShow tvShow = new TVShow();
		tvShow.setId(rs.getInt("id"));
		tvShow.setTitle(rs.getString("title"));
		tvShow.setDayOfWeek(rs.getString("dayOfWeek"));
		tvShow.setDownloadDate(formatDate(rs.getDate("downloadDate")));
		tvShow.setLatestEpisode(rs.getInt("latestEpisode"));
		return tvShow;
	}
	
	private String formatDate (Date downloadDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("M/dd/yyyy");
		return sdf.format(downloadDate);
	}
}
