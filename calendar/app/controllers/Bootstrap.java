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
    	user1 = new User("Dangerous Dan");
    	user2 = new User("TexasMan");
    	user3 = new User("Chuck Testa"); 
    	cals.add(user1.getCalendar());
    	cals.add(user2.getCalendar());
    	cals.add(user3.getCalendar());
    	addSomeEventsToCals();
    	addUsers();
    }

	private void addSomeEventsToCals() {
		Date startDate1 = Parser.parseStringToDate("18.11.11 14:00");
		Date endDate1 = Parser.parseStringToDate("18.11.11 17:30");
		Event event1 = new Event("Hunting with Dad", startDate1, endDate1, true);
		Date startDate2 = Parser.parseStringToDate("22.12.11 11:00");
		Date endDate2 = Parser.parseStringToDate("22.12.11 14:00");
		Event event2 = new Event("Resurrect Jesus", startDate2, endDate2, false);
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
	}
 
}