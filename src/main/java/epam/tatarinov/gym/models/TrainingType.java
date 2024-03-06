package epam.tatarinov.gym.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "training_type")
public class TrainingType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @Column(name = "training_type_name")
    @Enumerated(EnumType.STRING)
    @Column(name = "training_type_name")
    private TrainingTypeName trainingTypeName;

    @OneToMany(mappedBy = "specialization", fetch = FetchType.LAZY)
    private List<Trainer> trainerList;

    @OneToMany(mappedBy = "trainingType", fetch = FetchType.LAZY)
    private List<Training> trainingList;


    public TrainingType() {
    }

    public TrainingTypeName getTrainingTypeName() {
        return trainingTypeName;
    }

    public int getId() {
        return id;
    }

    //    public void setTrainingTypeName(TrainingTypeName trainingTypeName) {
//        this.trainingTypeName = trainingTypeName;
//    }

    @Override
    public String toString() {
        return "TrainingType{" +
                "trainingTypeName='" + trainingTypeName + '\'' +
                '}';
    }
}
