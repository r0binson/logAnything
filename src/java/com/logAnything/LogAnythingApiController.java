/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logAnything;

import com.logAnything.tvShows.Episode;
import com.logAnything.tvShows.TVShow;
import static com.logAnything.tvShows.TvShowResponseCodes.SHOW_EXISTS;
import static com.logAnything.tvShows.TvShowResponseCodes.SUCCESS;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.logAnything.tvShows.service.TVShowService;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Checklist for building a rest web service in net beans template 1. Check
 * dispatcher servlet make sure you have the following entries e.g.
 * <mvc:annotation-driven />
 * <context:component-scan base-package="com.cosrev.inventory" />
 * 2. Make sure that the name spaces are declared -
 * xmlns:context="http://www.springframework.org/schema/context" -
 * xmlns:mvc="http://www.springframework.org/schema/mvc" 3. Make sure the schema
 * location are also declared. - http://www.springframework.org/schema/mvc
 * http://www.springframework.org/schema/mvc/spring-mvc-4.0.xsd -
 * http://www.springframework.org/schema/context
 * http://www.springframework.org/schema/context/spring-context-4.0.xsd
 */
//Todo: Robin Checkout http://www.concretepage.com/spring/spring-mvc/spring-mvc-exception-handling-with-exceptionhandler-responseStatus-handlerexceptionresolver-example-global-exception
/**
 *
 * @author robin
 */
@RestController
public class LogAnythingApiController {

	@Resource(name = "TVShowServiceImpl")
	private TVShowService tvShowService;
	@Resource(name = "transactionManager")
	protected PlatformTransactionManager transactionManager;
	
	@RequestMapping(value = "/**")
	@ResponseBody
	public ResponseEntity unknownResourceError() {
		return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
	}

	/*
	Notes for POST methods: 
	1. Incomimg Json object will always be a String.
	2. Don't forget the @RequestBody for your incoming JSON Object. 
	3. Use Jackson's object mapper to map the incoming JSON Object to your Java Object.
	4. If method returns something, it should return an object inside the ResponseEntity object.
	 */
	@RequestMapping(value = "/tvShows", method = RequestMethod.POST,
		headers = "Accept=application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity createShow(@RequestBody String tvShow) throws Exception {
		String message = "[Show Title]";
		ObjectMapper mapper = new ObjectMapper();
		try {
			TVShow newTvShow = mapper.readValue(tvShow, TVShow.class);
			int responseCode = tvShowService.createNewShow(newTvShow);

			switch (responseCode) {
				case SUCCESS: 
					message = newTvShow.getTitle() + " created"; 
					break;
				case SHOW_EXISTS:
					message = "Show already exists. Search show to add Episodes";
					break;
				default:
					message = "Failed to create Show.";
			}
			
		} catch (JsonGenerationException e) {
			throw new Exception("LogAnything Controller --> Json Generation ");
		} catch (JsonMappingException e) {
			throw new Exception("LogAnything Controller --> Json Mapping ");
		} catch (IOException e) {
			throw new Exception("LogAnything Controller --> IO Exception ");
		}
		return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
	}

	@RequestMapping(value = "/tvShows/today", method = RequestMethod.GET,
		headers = "Accept=application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity getTodayShows() {
		List allShows = tvShowService.getTodayShows();	
		return new ResponseEntity<>(allShows, HttpStatus.OK);
	}

	@RequestMapping(value = "/tvShows/dayOfWeek/{dayOfWeek}", method = RequestMethod.GET,
		headers = "Accept=application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity getShowsByDayOfWeek(@PathVariable String dayOfWeek) {
		List allShows = tvShowService.getShowsByDayOfWeek(dayOfWeek);	
		return new ResponseEntity<>(allShows, HttpStatus.OK);
	}

	@RequestMapping(value = "/tvShows", method = RequestMethod.GET,
		headers = "Accept=application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity getAllShows() {
		List allShows = tvShowService.getAllShows();	
		return new ResponseEntity<>(allShows, HttpStatus.OK);
	}

	@RequestMapping(value = "/tvShows/title/{title}", method = RequestMethod.GET,
		headers = "Accept=application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity getShows(@PathVariable String title) {
		List shows = tvShowService.getShowsByTitle(title);		
		return new ResponseEntity(shows, HttpStatus.OK);
	}


	@RequestMapping(value = "/tvShows/id/{tvId}", method = RequestMethod.GET,
		headers = "Accept=application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity getShowById(@PathVariable String tvId) {
		TVShow tvShow = tvShowService.getShowById(tvId);
		return new ResponseEntity(tvShow, HttpStatus.OK);
	}

	@RequestMapping(value = "tvShows/episodes", method = RequestMethod.POST,
		headers = "Accept=application/json", produces = "application/json")
	@ResponseBody
	public ResponseEntity createNewEpisode(@RequestBody String episode) throws Exception {
		
		String message = "Failed to create new episode log.";
		ObjectMapper mapper = new ObjectMapper();
		try {
			Episode newEpisode = mapper.readValue(episode, Episode.class);
			int responseCode = tvShowService.createNewEpisode(newEpisode);
			if (responseCode == SUCCESS) {	
				message = "New Episode Created";
			} else {
				message = "Failed to create new episode log.";
			}
		} catch (JsonGenerationException e) {
			throw new Exception("LogAnything Controller --> Json Generation ");
		} catch (JsonMappingException e) {
			throw new Exception("LogAnything Controller --> Json Mapping ");
		} catch (IOException e) {
			throw new Exception("LogAnything Controller --> IO Exception ");
		}
		return new ResponseEntity<>(new MessageResponse(message), HttpStatus.OK);
	}

}
