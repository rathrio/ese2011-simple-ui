package models;

import java.util.ArrayList;

public class UserDatabase {
	
	public ArrayList<User> users;
	
	public UserDatabase() {
		this.users = new ArrayList<User>();
	}
	
	public void addUser(User user) {
		this.users.add(user);
	}

}
