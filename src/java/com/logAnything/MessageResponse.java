/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logAnything;

/**
 *
 * @author robin
 */
public class MessageResponse {
	private String message;
	
	public MessageResponse(String message){
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
}
