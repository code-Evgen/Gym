package epam.tatarinov.gym.models;

import jakarta.persistence.*;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Entity
@Table(name = "user_table")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {
//    private static int userID;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "isactive")
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


    //    public static int getUserID() {
//        return userID;
//    }


    public int getId() {
        return id;
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



//    public static void setUserID(int userID) {
//        User.userID = userID;
//    }


    public void setId(int id) {
        this.id = id;
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
//        setUserID(getUserID()+1);
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
