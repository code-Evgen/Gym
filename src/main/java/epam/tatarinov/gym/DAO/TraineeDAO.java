package epam.tatarinov.gym.DAO;

import epam.tatarinov.gym.Storages.TraineeStorage;
import epam.tatarinov.gym.util.StorageInitialization;
import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.util.ClassType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@StorageInitialization(CLASS_TYPE = ClassType.TRAINEE)
public class TraineeDAO {
    private TraineeStorage traineeStorage;

    @Autowired
    public void setTraineeStorage(TraineeStorage traineeStorage) {
        this.traineeStorage = traineeStorage;
    }

    public boolean createTrainee(Trainee trainee){
        return traineeStorage.create(trainee);
    }

    public boolean deleteTrainee(int id){
        if (selectTraineeByID(id).isPresent()) {
            return traineeStorage.delete(id);
        }
        return false;
    }


    public Optional<Trainee> selectTraineeByID(int id){
        Trainee trainee = traineeStorage.selectByID(id);
        return Optional.ofNullable(trainee);
    }

    public Optional<Trainee> selectTraineeByUsername(String username){
        Trainee trainee = traineeStorage.selectByUsername(username);
        return Optional.ofNullable(trainee);
    }
}
