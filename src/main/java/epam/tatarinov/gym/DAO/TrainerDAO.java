package epam.tatarinov.gym.DAO;

import epam.tatarinov.gym.Storages.TrainerStorage;
import epam.tatarinov.gym.util.StorageInitialization;
import epam.tatarinov.gym.models.Trainer;
import epam.tatarinov.gym.util.ClassType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@StorageInitialization(CLASS_TYPE = ClassType.TRAINER)
public class TrainerDAO {
    private TrainerStorage trainerStorage;

    @Autowired
    public void setTrainerStorage(TrainerStorage trainerStorage) {
        this.trainerStorage = trainerStorage;
    }


    public boolean createTrainer(Trainer trainer){
        return trainerStorage.create(trainer);
    }


    public Optional<Trainer> selectTrainerByID(int id){
        return Optional.ofNullable(trainerStorage.selectByID(id));
    }

    public Optional<Trainer> selectTrainerByUsername(String username){
        return Optional.ofNullable(trainerStorage.selectByUsername(username));
    }
}
