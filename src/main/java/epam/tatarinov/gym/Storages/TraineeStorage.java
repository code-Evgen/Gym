package epam.tatarinov.gym.Storages;

import epam.tatarinov.gym.models.Trainee;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class TraineeStorage {
    private static int currentTraineeId;
    private static Map<Integer, Trainee> trainees;


    static {
        trainees = new HashMap<>();
    }

    public Map<Integer, Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(Map<Integer, Trainee> trainees) {
        this.trainees = trainees;
    }

    public boolean create(Trainee trainee){
        trainees.put(currentTraineeId, trainee);
        currentTraineeId++;
        return true;
    }

    public boolean delete(int id){
        if (trainees.containsKey(id)) {
            trainees.remove(id);
            return true;
        }
        return false;
    }

    public Trainee selectByID(int id){
        return trainees.get(id);
    }

    public Trainee selectByUsername(String username){
        for(Map.Entry<Integer, Trainee> trainee : trainees.entrySet()){
            if (trainee.getValue().getUsername().equals(username)){
                return trainee.getValue();
            }
        }
        return null;
    }
}
