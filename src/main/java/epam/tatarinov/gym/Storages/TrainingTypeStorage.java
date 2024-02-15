package epam.tatarinov.gym.Storages;

import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.models.TrainingType;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TrainingTypeStorage {
    private static int currentTrainingTypeId;

    private static Map<Integer, TrainingType> trainingTypes;

    static {
        trainingTypes = new HashMap<>();
    }

    public static Map<Integer, TrainingType> getTrainingTypes() {
        return trainingTypes;
    }

    public int create(TrainingType trainingType){
        trainingTypes.put(currentTrainingTypeId, trainingType);
        currentTrainingTypeId++;
        return currentTrainingTypeId - 1;
    }

    public boolean delete(int id){
        if (trainingTypes.containsKey(id)) {
            trainingTypes.remove(id);
            return true;
        }
        return false;
    }

    public TrainingType select(int id){
        return trainingTypes.get(id);
    }
}
