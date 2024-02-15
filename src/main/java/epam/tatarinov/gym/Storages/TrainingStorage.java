package epam.tatarinov.gym.Storages;

import epam.tatarinov.gym.models.Training;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TrainingStorage {
    private static int currentTrainingId;
    private static Map<Integer, Training> trainings;

    static {
        trainings = new HashMap<>();
    }


    public Map<Integer, Training> getTrainees() {
        return trainings;
    }

    public void setTrainees(Map<Integer, Training> trainings) {
        this.trainings = trainings;
    }

    public Map<Integer, Training> getTrainings() {
        return trainings;
    }

    public boolean create(Training training){
        trainings.put(currentTrainingId, training);
        currentTrainingId++;
        return true;
    }

    public boolean delete(int id){
        if (trainings.containsKey(id)) {
            trainings.remove(id);
            return true;
        }
        return false;
    }

    public Training select(int id){
        return trainings.get(id);
    }

}
