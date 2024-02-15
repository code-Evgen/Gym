package epam.tatarinov.gym.DAO;

import epam.tatarinov.gym.Storages.TrainingStorage;
import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.models.Trainer;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.models.User;
import epam.tatarinov.gym.util.ClassType;
import epam.tatarinov.gym.util.StorageInitialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@StorageInitialization(CLASS_TYPE = ClassType.TRAINING)
public class TrainingDAO {
    private TrainingStorage trainingStorage;

    @Autowired
    public void setTrainingStorage(TrainingStorage trainingStorage) {
        this.trainingStorage = trainingStorage;
    }

    public boolean createTraining(Training training){
        return trainingStorage.create(training);
    }

    public Optional<Training> selectTraining(int id){
        return Optional.ofNullable(trainingStorage.select(id));
    }

}
