package epam.tatarinov.gym.DAO;

import epam.tatarinov.gym.Storages.TrainingTypeStorage;
import epam.tatarinov.gym.util.StorageInitialization;
import epam.tatarinov.gym.models.TrainingType;
import epam.tatarinov.gym.util.ClassType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
@StorageInitialization(CLASS_TYPE = ClassType.TRAINING_TYPE)
public class TrainingTypeDAO {
    private TrainingTypeStorage trainingTypeStorage;

    @Autowired
    public void setTrainingTypeStorage(TrainingTypeStorage trainingTypeStorage) {
        this.trainingTypeStorage = trainingTypeStorage;
    }

    public int createTrainingType(TrainingType trainingType){
        return trainingTypeStorage.create(trainingType);
    }

    public Optional<TrainingType> selectTrainingType(int id){
        return Optional.ofNullable(trainingTypeStorage.select(id));
    }

    public Optional<TrainingType> selectTrainingTypeByName(String name){
        Map<Integer, TrainingType> trainingTypeMap = TrainingTypeStorage.getTrainingTypes();
        for (Map.Entry<Integer, TrainingType> t : trainingTypeMap.entrySet()){
            if (t.getValue().getTrainingTypeName().equals(name)){
                return Optional.ofNullable(t.getValue());
            }
        }
        return Optional.empty();
    }

    public Optional<Integer> selectTrainingTypeIDByName(String name){
        Map<Integer, TrainingType> trainingTypeMap = TrainingTypeStorage.getTrainingTypes();
        for (Map.Entry<Integer, TrainingType> t : trainingTypeMap.entrySet()){
            if (t.getValue().getTrainingTypeName().equals(name)){
                return Optional.ofNullable(t.getKey());
            }
        }
        return Optional.empty();
    }
}
