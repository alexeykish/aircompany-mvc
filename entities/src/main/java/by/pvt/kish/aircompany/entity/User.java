/**
 * 
 */
package by.pvt.kish.aircompany.entity;

import by.pvt.kish.aircompany.enums.UserType;

/**
 * Описывает сущность пользователя системы
 * Пользователь может быть двух типов: администратор или диспетчер
 *
 * @author Kish Alexey
 */
public class User {
	private int uid;
	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private String email;
	private UserType userType;

	public User() {
	}

	/**
	 * @param uid - user id
	 * @param firstName - user firstname
	 * @param lastName - user lastname
	 * @param login - user login
	 * @param password - user password
	 * @param userType - user type (Administrator or dispatcher)
	 */
	public User(int uid, String firstName, String lastName, String login, String password, UserType userType) {
		super();
		this.uid = uid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.login = login;
		this.password = password;
		this.userType = userType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (uid != user.uid) return false;
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
		if (login != null ? !login.equals(user.login) : user.login != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		return userType == user.userType;

	}

	@Override
	public int hashCode() {
		int result = uid;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (userType != null ? userType.hashCode() : 0);
		return result;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
