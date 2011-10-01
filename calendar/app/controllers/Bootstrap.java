package controllers;

import java.util.ArrayList;
import java.util.Date;

import play.*;
import play.jobs.*;
import play.test.*;
 
import models.*;
 
@OnApplicationStart
public class Bootstrap extends Job {
	
	User user1, user2, user3;
	ArrayList<Calendar> cals = new ArrayList<Calendar>();
	
    public void doJob() {
    	user1 = new User("DangerousDan");
    	user2 = new User("TexasMan");
    	user3 = new User("ChuckTesta"); 
    	createCals();
    	addSomeEventsToCals();
    	addUsers();
    }

	private void createCals() {
		user1.createCalendar("Dangerous Plan");
		user2.createCalendar("Redneck Stuff");
		user3.createCalendar("Hunting");
		cals.addAll(user1.getCalendars());
    	cals.addAll(user2.getCalendars());
    	cals.addAll(user3.getCalendars());
	}

	private void addSomeEventsToCals() {
		Date startDate1 = Parser.parseStringToDate("22.11.11 14:00");
		Date endDate1 = Parser.parseStringToDate("22.11.11 17:30");
		Event event1 = new Event("Hunting with Dad", startDate1, endDate1, true);
		Date startDate2 = Parser.parseStringToDate("14.12.11 11:00");
		Date endDate2 = Parser.parseStringToDate("14.12.11 14:00");
		Event event2 = new Event("Lunch with Mom", startDate2, endDate2, false);
		for (Calendar c : cals) {
			c.addEvent(event1);
			c.addEvent(event2);
		}
		assert (event1 != null);
	}
	
	private void addUsers() {
		UserDatabase.addUser(user1);
		UserDatabase.addUser(user2);
		UserDatabase.addUser(user3);
		user1.setPassword("dangerousdan");
		user2.setPassword("texasman");
		user3.setPassword("nopeitschucktesta");
		user1.save();
		user2.save();
		user3.save();
	}
 
}