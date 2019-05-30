package com.bercea.assigment2.handler;

import org.springframework.stereotype.Component;

@Component
public interface Handler {
	public void execute(String command);
	public String getCommand();
}
