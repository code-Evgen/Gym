package epam.tatarinov.gym.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table (name = "trainee")
@PrimaryKeyJoinColumn (name = "user_id")
//@DiscriminatorValue("trainee")
public class Trainee extends User{
//    @Id
    @Column (name = "trainee_id", insertable = false, updatable = false, columnDefinition="serial")
    private int traineeId;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "address")
    private String address;

    @ManyToMany
    @JoinTable(name = "trainee_trainer",
            joinColumns = @JoinColumn(name = "trainee_id", referencedColumnName = "trainee_id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id", referencedColumnName = "trainer_id"))
    private List<Trainer> trainerList;


    @OneToMany(mappedBy = "trainee", fetch = FetchType.LAZY)
    private List<Training> trainingList;


    public Trainee() {
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public int getTraineeId() {
        return traineeId;
    }

    public List<Trainer> getTrainerList() {
        return trainerList;
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }

    public void setTrainerList(List<Trainer> trainerList) {
        this.trainerList = trainerList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
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
//                ", userId=" + getId() +
//                ", firstName='" + super.getFirstName() + '\'' +
//                ", lastName='" + super.getLastName() + '\'' +
//                ", username='" + super.getUsername() + '\'' +
//                ", password='" + super.getPassword() + '\'' +
//                ", isActive=" + super.isActive() +
                '}';
    }
}
