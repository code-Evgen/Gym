package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TrainingTypeDAO;
import epam.tatarinov.gym.Storages.TraineeStorage;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.models.TrainingType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TrainingTypeService {
    private TrainingTypeDAO trainingTypeDAO;
    private static final Logger logger = Logger.getLogger(TrainingTypeService.class);

    @Autowired
    public void setTrainingTypeDAO(TrainingTypeDAO trainingTypeDAO) {
        this.trainingTypeDAO = trainingTypeDAO;
    }

    public int createTrainingType(TrainingType trainingType){
        logger.info("creating training type - " + trainingType.getTrainingTypeName());

        Optional<TrainingType> trainingTypeExist = trainingTypeDAO.selectTrainingTypeByName(trainingType.getTrainingTypeName());
        if (!trainingTypeExist.isPresent()) {
            int trainingTypeID = -1;
            trainingTypeID = trainingTypeDAO.createTrainingType(trainingType);
            logger.info("training type created");
            return trainingTypeID;
        }
        logger.info("training type already exist");
        return trainingTypeDAO.selectTrainingTypeIDByName(trainingType.getTrainingTypeName()).get();
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
}
