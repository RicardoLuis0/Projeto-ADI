package com.ricardoluis.eventhandling;

import java.util.ArrayList;

public class EventManager {
	static private EventManager manager=new EventManager();

	public static EventManager getInstance() {
		return manager;
	}

	private ArrayList<EventHandler> handlers;

	private EventManager() {
		handlers= new ArrayList<>();
	}
	
	public void registerHandler(EventHandler e) {
		handlers.add(e);
	}

	public void sendEvent(Event e) {
		for(EventHandler h:handlers) {
			h.handleEvent(e);
		}
	}
	
}
