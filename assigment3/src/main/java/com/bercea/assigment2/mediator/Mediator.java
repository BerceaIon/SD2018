package com.bercea.assigment2.mediator;

import org.springframework.stereotype.Component;

import com.bercea.assigment2.handler.Handler;

@Component
public interface Mediator {
	public void handle (String command, Handler handler);
	public void addHandler(Handler handler);
}
