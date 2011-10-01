package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;
import models.Calendar;

@With(Secure.class)
public class Application extends Controller {

    public static void index() {
    	User user = UserDatabase.getUserNamed(Security.connected());
    	ArrayList<Calendar> cals = user.getCalendars();
    	ArrayList<User> users = UserDatabase.getUsersExcept(user);
        render(user, cals, users);
    }
    
    public static void displayCalendars(String username) {
    	User user = UserDatabase.getUserNamed(username);
    	ArrayList<Calendar> cals = user.getCalendars();
    	render(user, cals);
    }
    
    public static void displayEvents(String username, String calendarname) {
    	User user = UserDatabase.getUserNamed(username);
    	Calendar cal = user.getCalNamed(calendarname);
    	Iterator<Event> eventsIterator = cal.iterator();
    	List<Event> events = new LinkedList<Event>();
    	while (eventsIterator.hasNext()) {
    		events.add(eventsIterator.next());
    	}
    	render(cal, events);
    }

}