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
    	ArrayList<User> users = UserDatabase.getUsersExcept(user);
        render(user, users);
    }
    
    public static void displayEvents(String name) {
    	User user = UserDatabase.getUserNamed(name);
    	Calendar cal = user.getCalendar();
    	Iterator<Event> eventsIterator = cal.iterator();
    	List<Event> events = new LinkedList<Event>();
    	while (eventsIterator.hasNext()) {
    		events.add(eventsIterator.next());
    	}
    	render(user, cal, events);
    }

}