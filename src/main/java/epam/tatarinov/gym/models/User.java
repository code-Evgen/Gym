package epam.tatarinov.gym.models;

import epam.tatarinov.gym.Services.TrainerService;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public abstract class User {
    private static int userID;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;
    private static final Logger logger = Logger.getLogger(User.class);


    private static Map<String, Integer> usernameCount;

    static {
        usernameCount = new HashMap<>();
    }

    public User() {
    }

    public User(String firstName, String lastName, String password, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.isActive = isActive;
    }

    public static int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return isActive;
    }



    public static void setUserID(int userID) {
        User.userID = userID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }



    public static String createUsername(String firstName, String lastName){
        setUserID(getUserID()+1);
        String username = firstName + "." + lastName;
        username = checkUserExistByUsername(username);
        return username;
    }

    private static String checkUserExistByUsername(String username){
        if (usernameCount.containsKey(username)){
            logger.debug("Username already exist - " + username);
            int count = usernameCount.get(username);
            username = username + ( count + 1);
            usernameCount.put(username, count + 1);
            logger.debug("Username created - " + username);
        }
        else {
            usernameCount.put(username, 0);
            logger.debug("Username is new - " + username);
        }
        return username;
    }

    public static String generatePassword(){
        Random random = new Random();
        StringBuilder passwordBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++){
            int randomValue = random.nextInt(127 - 33) + 33;
            passwordBuilder.append((char)randomValue);
        }
        logger.debug("Password generated. Length - " + passwordBuilder.length());
        return passwordBuilder.toString();
    }
}
