package com.bercea.assigment2.mediator.impl;

import java.util.ArrayList;

import com.bercea.assigment2.handler.Handler;
import com.bercea.assigment2.mediator.Mediator;

public class MediatorImpl implements Mediator{

	private ArrayList<Handler> handlers;
	
	public MediatorImpl() {
		handlers = new ArrayList<Handler>();
	}
	
	@Override
	public void handle(String command, Handler handler) {
		for(Handler h:handlers) {
			if(h.getCommand().equals(command)) {
				h.execute(command);
			}
		}
	}

	@Override
	public void addHandler(Handler handler) {
		handlers.add(handler);
	}

}
