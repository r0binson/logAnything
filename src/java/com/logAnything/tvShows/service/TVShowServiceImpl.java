/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logAnything.tvShows.service;

import com.logAnything.tvShows.Episode;
import com.logAnything.tvShows.TVShow;
import static com.logAnything.tvShows.TvShowResponseCodes.SHOW_EXISTS;
import com.logAnything.tvShows.dao.EpisodesDao;
import com.logAnything.tvShows.dao.TVInfoDao;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 *
 * @author robin
 */
@Component("TVShowServiceImpl")
public class TVShowServiceImpl implements TVShowService {

	@Resource(name = "TVInfoDaoImpl")
	private TVInfoDao tvInfoDao;

	@Resource(name = "EpisodesDaoImpl")
	private EpisodesDao episodesDao;

	@Resource(name = "transactionManager")
	protected PlatformTransactionManager transactionManager;

	@Override
	public int createNewShow(TVShow tvShow) {
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		int responseCode = 0;
		
		//check if show title already exists.
		if (showExists(tvShow.getTitle())) {
			return SHOW_EXISTS;
		}
		
		try {
			//This might cause data integrity issues, but in single user instance it should not be a problem.
			int newTvId = tvInfoDao.getNextId();
			tvShow.setId(newTvId);
			tvInfoDao.createNewShow(tvShow);
			Episode episode = createEpisode(tvShow);
			episodesDao.createNewEpisode(episode);
			transactionManager.commit(status);
		} catch (Exception ex) {
			responseCode = -1;
			transactionManager.rollback(status);
		}
		return responseCode;
	}

	//currently show search should be an exact match otherwise it will not detect it exists.
	private boolean showExists(String title){
		List allShows = tvInfoDao.getShowsByTitle(title);
		return !allShows.isEmpty();
	}
	
	private Episode createEpisode(TVShow tvShow) {
		Episode episode = new Episode(
			tvShow.getId(),
			tvShow.getLatestEpisode(),
			tvShow.getDownloadDate());

		return episode;
	}

	@Override
	public int createNewEpisode(Episode episode) {
		episodesDao.createNewEpisode(episode);
		return 0;
	}

	@Override
	public List getAllShows() {
		List allShows = tvInfoDao.getAllShows();
		/* In the future, I might want to pull all episodes and display it individually just in case I missed an episode. 
		For now, just pull the latest episode in a single SQL */
//		Map allShowEpisodes = episodesDao.getAllEpisodes;
		return allShows;
	}

	@Override
	public List getShowsByTitle(String title) {
		return tvInfoDao.getShowsByTitle(title);
	}

	@Override
	public TVShow getShowById(String tvId) {
		return tvInfoDao.getShowById(tvId);
	}

	@Override
	public List getTodayShows() {
		return tvInfoDao.getTodayShows();
	}

	@Override
	public List getShowsByDayOfWeek(String dayOfWeek) {
		return tvInfoDao.getShowsByDayOfWeek(dayOfWeek);
	}

}
