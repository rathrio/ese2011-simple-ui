package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;

import play.db.jpa.Model;

@Entity
public class User extends Model {
	
	public String name;
	@Transient public ArrayList<Calendar> cals;
	public String password;
	
	public User(String name) {
		this.name = name;
		this.cals = new ArrayList<Calendar>();
	}
	
	public void createEvent(Calendar cal) {
		String eventName = getStringInput();
		Date startDate = getDateInput();
		Date endDate = getDateInput();
		boolean isPublic = getBooleanInput();
		assert (startDate.before(endDate));
		Event event = new Event(eventName, startDate, endDate, isPublic);
		cal.addEvent(event);
	}

	public ArrayList<Event> getVisibleEventsOnSpecificDayFrom(User user, Date date) {
		ArrayList<Calendar> cals = user.getCalendars();
		ArrayList<Event> visibleEvents = new ArrayList<Event>();
		if (this.equals(user)) {
			for (Calendar cal : cals) {
				for (Event e : cal.getEvents()) {
					if (e.happensOn(date)) {
						visibleEvents.add(e);
					}
				}
			}
		} else {
			for (Calendar cal : cals) {
				for (Event e : cal.getPublicEvents()) {
					if (e.happensOn(date)) {
						visibleEvents.add(e);
					}
				}
			}
		}
		return visibleEvents;
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Calendar> getCalendars() {
		return this.cals;
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

	public void createCalendar(String name) {
		Calendar cal = new Calendar(this, name);
		this.cals.add(cal);
	}

	public Calendar getCalNamed(String calendarname) {
		for (Calendar c : this.cals) {
			if (c.getName().equals(calendarname)) {
				return c;
			}
		}
		throw new NoSuchEntityException(calendarname);
	}

}
