package models;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;

import play.db.jpa.Model;

public class Calendar implements Iterable<Event> {
	
	public User owner;
	public String name;
	public PriorityQueue<Event> events;
	public PriorityQueue<Event> publicEvents;
	
	public Calendar(User owner, String name) {
		this.owner = owner;
		this.name = name;
		this.events = new PriorityQueue<Event>();
		this.publicEvents = new PriorityQueue<Event>();
	}
	
	public void addEvent(Event event) {
		if (event.isPublic()) {
			this.publicEvents.add(event);
		}
		this.events.add(event);
		Arrays.sort(this.events.toArray());
		Arrays.sort(this.publicEvents.toArray());
	}

	public User getOwner() {
		return this.owner;
	}
	
	public String getName() {
		return this.name;
	}

	public PriorityQueue<Event> getEvents() {
		Arrays.sort(this.events.toArray());
		return this.events;
	}
	
	public PriorityQueue<Event> getPublicEvents() {
		Arrays.sort(this.publicEvents.toArray());
		return this.publicEvents;
	}

	public boolean isEmpty() {
		return this.events.isEmpty();
	}

	public Event getNextEvent() {
		return this.events.peek();
	}
	
	public boolean isOwner(User user) {
		return owner.equals(user);
	}

	@Override
	public Iterator<Event> iterator() {
		Arrays.sort(this.events.toArray());
		return this.events.iterator();
	}

	public Iterator<Event> getEventsAfter(User receivingUser, Date startingDate)  {
		ArrayList<Event> iterableEvents = new ArrayList<Event>();
		if (receivingUser.equals(this.owner)) {
			for (Event event : this.events) {
				if (!event.getStartDate().before(startingDate)) {
					iterableEvents.add(event);
				}
			}
		} else {
			for (Event event : this.publicEvents) {
				if (!event.getStartDate().before(startingDate)) {
					iterableEvents.add(event);
				}
			}
		}
		return iterableEvents.iterator();
	}

	public void createEvent(String eventName, String startDate, String endDate) {
		Date sDate = Parser.parseStringToDate(startDate);
    	Date eDate = Parser.parseStringToDate(endDate);
    	Event newEvent = new Event(eventName, sDate, eDate, true);
    	this.addEvent(newEvent);
	}
	
}
