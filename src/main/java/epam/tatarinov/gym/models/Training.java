package epam.tatarinov.gym.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "training")
public class Training {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "training_name")
    private String trainingName;

    @Column(name = "training_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date trainingDate;

    @Column(name = "training_duration")
    private int trainingDuration;

    @ManyToOne
    @JoinColumn(name = "trainee_id", referencedColumnName = "trainee_id")
    private Trainee trainee;

    @ManyToOne
    @JoinColumn(name = "trainer_id", referencedColumnName = "trainer_id")
    private Trainer trainer;

    @ManyToOne
    @JoinColumn(name = "training_type_id", referencedColumnName = "id")
    private TrainingType trainingType;



    public Training() {
    }


    public TrainingType getTrainingType() {
        return trainingType;
    }

    public Date getTrainingDate() {
        return trainingDate;
    }

    public int getTrainingDuration() {
        return trainingDuration;
    }

    public int getId() {
        return id;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public Trainer getTrainer() {
        return trainer;
    }


    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public void setTrainingDate(Date trainingDate) {
        this.trainingDate = trainingDate;
    }

    public void setTrainingDuration(int trainingDuration) {
        this.trainingDuration = trainingDuration;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    @Override
    public String toString() {
        return "Training{" +
//                "traineeId=" + traineeId +
//                ", trainerId=" + trainerId +
//                ", trainingName='" + trainingName + '\'' +
                ", trainingType=" + trainingType.getTrainingTypeName() +
                ", trainingDate=" + trainingDate +
                ", trainingDuration=" + trainingDuration +
                '}';
    }
}
