package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import play.db.jpa.Model;

@Entity
public class User extends Model {
	
	public String name;
	@Transient public Calendar cal;
	public String password;
	
	public User(String name) {
		this.name = name;
		this.cal = new Calendar(this, "Home");
	}
	
	public void createEvent() {
		String eventName = getStringInput();
		Date startDate = getDateInput();
		Date endDate = getDateInput();
		boolean isPublic = getBooleanInput();
		assert (startDate.before(endDate));
		Event event = new Event(eventName, startDate, endDate, isPublic);
		cal.addEvent(event);
	}

	public ArrayList<Event> getVisibleEventsOnSpecificDayFrom(User user, Date date) {
		Calendar cal = user.getCalendar();
		ArrayList<Event> visibleEvents = new ArrayList<Event>();
		if (this.equals(user)) {
			for (Event e : cal.getEvents()) {
				if (e.happensOn(date)) {
					visibleEvents.add(e);
				}
			}
		} else {
			for (Event e : cal.getPublicEvents()) {
				if (e.happensOn(date)) {
					visibleEvents.add(e);
				}
			}
		}
		return visibleEvents;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Calendar getCalendar() {
		return this.cal;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	private boolean getBooleanInput() {
		String input = ""; //implement input method for user interaction
		return input.equalsIgnoreCase("y");
	}

	private String getStringInput() {
		//TODO
		String input = ""; //implement input method for user interaction
		return input;
	}

	private Date getDateInput() {
		//TODO
		String input = ""; //implement input method for user interaction
		Date inputDate = Parser.parseStringToDate(input);
		return inputDate;
	}

}
