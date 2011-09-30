package models;

import java.util.ArrayList;

import com.sun.tools.internal.ws.wsdl.framework.NoSuchEntityException;

public class UserDatabase {
	
	public static ArrayList<User> users = new ArrayList<User>();
	
	public static void addUser(User user) {
		users.add(user);
	}
	
	public static ArrayList<User> getUsers() {
		return users;
	}
	
	public static User getUserNamed(String name) {
		for (User u : users) {
			if (u.getName().equals(name)) {
				return u;
			}
		}
		throw new NoSuchEntityException(name);
	}

}
