package epam.tatarinov.gym.models;

import java.util.List;

public class Trainer extends User{
    private String specialization;
    private int userId;
    private Training training;

    private TrainingType trainingType;


    public Trainer() {
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getUserId() {
        return userId;
    }

    public Training getTraining() {
        return training;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "specialization='" + specialization + '\'' +
                ", userId=" + userId +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", isActive=" + super.isActive() +
                '}';
    }
}
