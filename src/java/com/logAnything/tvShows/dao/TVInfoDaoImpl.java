/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logAnything.tvShows.dao;

import com.logAnything.tvShows.TVShow;
import com.logAnything.tvShows.TVShowRowMapper;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author robin
 */
@Component("TVInfoDaoImpl")
public class TVInfoDaoImpl extends Dao implements TVInfoDao {

	@Override
	public int createNewShow(TVShow tvShow) {
		String SQL = "Insert into tv_info (id, title, dayOfWeek) values (?, ?, '')";
		Object[] params = new Object[]{
			tvShow.getId(),
			tvShow.getTitle()
		};
		return jdbcTemplate.update(SQL, params);
	}

	
	@Override
	public int getNextId() {
		return super.getNextValue("tv_info");
	}
	
	
	private String tvShowSQLBuilder(String parameter, String value) {
		StringBuilder SQL = new StringBuilder("select tv_info.id, title, dayName(downloadDate) as dayOfWeek, episode as latestEpisode, downloadDate as downloadDate, dateCreated from tv_info, episodes "
			+ "where tv_info.id = episodes.tvId and episodes.Id in (select max(id) from episodes group by tvId) ");

		if (!parameter.isEmpty() && !value.isEmpty()) {
			if(parameter.equalsIgnoreCase("id")){
				SQL.append("and ").append("tv_info.id").append(" = ").append(value).append(" ");
			} else {
				SQL.append("and ").append(parameter).append(" like '%").append(value).append("%' ");
			}
		}
		
		SQL.append("group by tv_info.id ");
		SQL.append("order by dateCreated desc");
		
		return SQL.toString();
	}

	
	@Override
	public List getAllShows() {
		String SQL = tvShowSQLBuilder("", "");
		return jdbcTemplate.query(SQL, new TVShowRowMapper());
	}

	
	@Override
	public List getShowsByTitle(String title) {
		String SQL = tvShowSQLBuilder("title", title);
		return jdbcTemplate.query(SQL, new TVShowRowMapper());
	}

	
	@Override
	public TVShow getShowById(String tvId) {
		String SQL = tvShowSQLBuilder("id", tvId);
		return jdbcTemplate.queryForObject(SQL, new TVShowRowMapper());
	}

	@Override
	public List getTodayShows() {
		Date today = Calendar.getInstance().getTime();
		String todayDayOfWeek = getDayOfWeek(today);
		String SQL = tvShowSQLBuilder("dayName(downloadDate)", todayDayOfWeek);
		return jdbcTemplate.query(SQL, new TVShowRowMapper());
	}
	
	private String getDayOfWeek (Date date) {
		SimpleDateFormat dayOfWeekSdf = new SimpleDateFormat("EEE");
		return 	dayOfWeekSdf.format(date);
	}

	@Override
	public List getShowsByDayOfWeek(String dayOfWeek) {
		String SQL = tvShowSQLBuilder("dayName(downloadDate)", dayOfWeek);
		return jdbcTemplate.query(SQL, new TVShowRowMapper());
	}

}
