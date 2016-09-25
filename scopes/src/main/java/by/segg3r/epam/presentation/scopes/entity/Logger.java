package by.segg3r.epam.presentation.scopes.entity;

import java.util.List;

import com.google.common.collect.Lists;

public class Logger {

	private List<String> events;

	public Logger() {
		this.events = Lists.newArrayList();
	}
	
	public void addEvent(String event) {
		this.events.add(event);
	}
	
	public List<String> getEvents() {
		return this.events;
	}
	
}
