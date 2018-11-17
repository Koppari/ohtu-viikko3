package ohtu.authentication;

import ohtu.data_access.UserDao;
import ohtu.domain.User;
import ohtu.util.CreationStatus;

public class AuthenticationService {

    private UserDao userDao;

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public CreationStatus createUser(String username, String password, String passwordConfirmation) {
        CreationStatus status = new CreationStatus();

        if (userDao.findByName(username) != null) {
            status.addError("username is already taken");
        }

        if (!(username.length() >= 3 && username.matches("[a-z]+"))) {
            status.addError("username should have at least 3 characters");
        }

        String digitsAndSpecials = "(^.*(?=.{8,})(?=.*[!@#$%^&*_0-9]).*$)";
        if (!(password.length() >= 8 && password.matches(digitsAndSpecials))) {
            status.addError("password should have at least 8 characters");
            status.addError("password should have at least 1 digit or special character");
        }

        if (!(password.equals(passwordConfirmation))) {
            status.addError("password and password confirmation do not match");
        }

        if (status.isOk()) {
            userDao.add(new User(username, password));
        }

        return status;
    }

}
