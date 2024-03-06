package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TrainingTypeDAO;
import epam.tatarinov.gym.Storages.TraineeStorage;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.models.TrainingType;
import epam.tatarinov.gym.models.TrainingTypeName;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class TrainingTypeService {
    private TrainingTypeDAO trainingTypeDAO;
    private static final Logger logger = Logger.getLogger(TrainingTypeService.class);

    @Autowired
    public void setTrainingTypeDAO(TrainingTypeDAO trainingTypeDAO) {
        this.trainingTypeDAO = trainingTypeDAO;
    }


    public Optional<TrainingType> selectTrainingType(int id){
        logger.info("select training type - " + id);
        boolean loggingResult = false;

        Optional<TrainingType> trainingType = trainingTypeDAO.selectTrainingType(id);

        if (trainingType.isPresent()) {
            loggingResult = true;
        }
        logger.info("training type selected - " + loggingResult);

        return trainingType;
    }

    @Transactional
    public Optional<TrainingType> selectTrainingTypeByName(TrainingTypeName trainingTypeName){
        logger.info("selecting training type");
        boolean loggingResult = false;

        Optional<TrainingType> trainingType = trainingTypeDAO.selectTrainingTypeByName(trainingTypeName);

        if (trainingType.isPresent()) {
            loggingResult = true;
        }
        logger.info("training type selected - " + loggingResult);

        return trainingType;
    }
}
