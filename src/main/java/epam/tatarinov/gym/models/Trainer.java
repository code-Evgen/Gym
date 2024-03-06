package epam.tatarinov.gym.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "trainer")
@PrimaryKeyJoinColumn(name = "user_id")
public class Trainer extends User{
    @Column (name = "trainer_id", insertable = false, updatable = false, columnDefinition="serial")
    private int trainerId;

    @ManyToOne
    @JoinColumn(name = "specialization", referencedColumnName = "id")
    private TrainingType specialization;

    @ManyToMany(mappedBy = "trainerList")
    private List<Trainee> traineeList;

    @OneToMany(mappedBy = "trainer", fetch = FetchType.LAZY)
    private List<Training> trainingList;




    public Trainer() {
    }


    public int getTrainerId() {
        return trainerId;
    }

    public List<Trainee> getTraineeList() {
        return traineeList;
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public TrainingType getSpecialization() {
        return specialization;
    }


    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public void setTraineeList(List<Trainee> traineeList) {
        this.traineeList = traineeList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }

    public void setSpecialization(TrainingType trainingType) {
        this.specialization = trainingType;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "specialization='" + specialization + '\'' +
//                ", userId=" + userId +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", isActive=" + super.isActive() +
                '}';
    }
}
