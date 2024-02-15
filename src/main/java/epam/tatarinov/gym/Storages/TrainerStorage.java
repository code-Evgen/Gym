package epam.tatarinov.gym.Storages;

import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.models.Trainer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TrainerStorage {
    private static int currentTrainerId;
    private static Map<Integer, Trainer> trainers;

    static {
        trainers = new HashMap<>();
    }

    public static int getCurrentTrainerId() {
        return currentTrainerId;
    }

    public Map<Integer, Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(Map<Integer, Trainer> trainers) {
        this.trainers = trainers;
    }

    public boolean create(Trainer trainer){
        trainers.put(currentTrainerId, trainer);
        currentTrainerId++;
        return true;
    }

    public boolean delete(int id){
        if (trainers.containsKey(id)) {
            trainers.remove(id);
            return true;
        }
        return false;
    }

    public Trainer selectByID(int id){
        return trainers.get(id);
    }

    public Trainer selectByUsername(String username){
        for(Map.Entry<Integer, Trainer> trainer : trainers.entrySet()){
            if (trainer.getValue().getUsername().equals(username)){
                return trainer.getValue();
            }
        }
        return null;
    }
}
