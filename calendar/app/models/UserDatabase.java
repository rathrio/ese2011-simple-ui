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
	
	public static ArrayList<User> getUsersExcept(User user) {
		ArrayList<User> otherUsers = new ArrayList<User>(users);
		for (User u : otherUsers) {
			if (u.equals(user)) {
				otherUsers.remove(u);
				return otherUsers;
			}
		}
		throw new NoSuchEntityException(user.getName());
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
