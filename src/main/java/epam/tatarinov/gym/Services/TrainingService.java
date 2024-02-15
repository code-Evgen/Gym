package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TrainingDAO;
import epam.tatarinov.gym.Storages.TraineeStorage;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.models.TrainingType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainingService {
    private TrainingDAO trainingDAO;
    private static final Logger logger = Logger.getLogger(TrainingService.class);


    @Autowired
    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }


    public void createTraining(Training training){
        logger.info("creating training - type_" + training.getTrainingType());
        boolean loggingResult;

        loggingResult = trainingDAO.createTraining(training);

        logger.info("training created - " + loggingResult);
    }

    public Optional<Training> selectTraining(int id){
        return trainingDAO.selectTraining(id);
    }

    public void setTrainingType(int id, TrainingType trainingType){
        logger.info("setting training type - " + id);
        boolean loggingResult = false;

        Optional<Training> training = trainingDAO.selectTraining(id);
        if (training.isPresent()){
            training.get().setTrainingType(trainingType);
            loggingResult = true;
        }

        logger.info("training set - " + loggingResult);
    }
}
