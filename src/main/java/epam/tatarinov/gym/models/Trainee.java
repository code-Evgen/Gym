package epam.tatarinov.gym.models;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;

public class Trainee extends User{
    private LocalDate dateOfBirth;
    private String address;
    private int userId;
    private Training training;


    public Trainee() {
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public int getUserId() {
        return userId;
    }

    public Training getTraining() {
        return training;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTraining(Training training) {
        this.training = training;
    }


    @Override
    public String toString() {
        String date = "";
        if (dateOfBirth != null) {
            date = dateOfBirth.toString();
        }

        return "Trainee{" +
                "dateOfBirth=" + date +
                ", address='" + address + '\'' +
                ", userId=" + userId +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", isActive=" + super.isActive() +
                '}';
    }
}
