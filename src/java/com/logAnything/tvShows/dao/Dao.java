/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logAnything.tvShows.dao;

import javax.annotation.Resource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author robin
 */
public class Dao {

	@Resource(name = "jdbcTemplate")
	protected JdbcTemplate jdbcTemplate;

	protected void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	protected int getNextValue (String table) {
		StringBuilder SQL = new StringBuilder("SELECT Auto_increment FROM information_schema.tables WHERE table_name=");
		SQL.append("'").append(table).append("'");
		return jdbcTemplate.queryForObject(SQL.toString(), Integer.class);
	}
}
