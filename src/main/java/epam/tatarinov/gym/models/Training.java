package epam.tatarinov.gym.models;

import java.time.LocalDate;
import java.util.Date;

public class Training {
    private int traineeId;
    private int trainerId;
//    private String trainingName;
    private TrainingType trainingType;
    private LocalDate trainingDate;
    private int trainingDuration;

    public Training() {
    }


    public int getTraineeId() {
        return traineeId;
    }

    public int getTrainerId() {
        return trainerId;
    }

//    public String getTrainingName() {
//        return trainingName;
//    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public LocalDate getTrainingDate() {
        return trainingDate;
    }

    public int getTrainingDuration() {
        return trainingDuration;
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

//    public void setTrainingName(String trainingName) {
//        this.trainingName = trainingName;
//    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public void setTrainingDate(LocalDate trainingDate) {
        this.trainingDate = trainingDate;
    }

    public void setTrainingDuration(int trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    @Override
    public String toString() {
        return "Training{" +
                "traineeId=" + traineeId +
                ", trainerId=" + trainerId +
//                ", trainingName='" + trainingName + '\'' +
                ", trainingType=" + trainingType.getTrainingTypeName() +
                ", trainingDate=" + trainingDate +
                ", trainingDuration=" + trainingDuration +
                '}';
    }
}
