package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TraineeDAO;
import epam.tatarinov.gym.Storages.TraineeStorage;
import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TraineeService {
    private TraineeDAO traineeDAO;
    private static final Logger logger = Logger.getLogger(TraineeStorage.class);


    @Autowired
    public void setTraineeDAO(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    public void createTrainee(Trainee trainee){
        logger.info("creating trainee - " + trainee.getFirstName() + "_" + trainee.getLastName());
        int userId = User.getUserID();
        trainee.setUserId(userId);
        trainee.setUsername(User.createUsername(trainee.getFirstName(), trainee.getLastName()));
        trainee.setPassword(User.generatePassword());
        trainee.setActive(true);
        boolean loggingResult;
        loggingResult = traineeDAO.createTrainee(trainee);
        logger.info("trainee created - " + loggingResult);
    }

    public void deleteTrainee(int id){
        logger.info("deleting trainee - " + id);
        boolean loggingResult;
        loggingResult = traineeDAO.deleteTrainee(id);
        logger.info("trainee deleted - " + loggingResult);
    }

    public void updateTrainee(int id, Trainee trainee){
        logger.info("updating trainee - " + id);
        boolean loggingResult = false;
        Optional<Trainee> traineeForUpdateOptional = traineeDAO.selectTraineeByID(id);
        if (traineeForUpdateOptional.isPresent()) {
            Trainee traineeForUpdate = traineeForUpdateOptional.get();
            traineeForUpdate.setAddress(trainee.getAddress());
            traineeForUpdate.setDateOfBirth(trainee.getDateOfBirth());
            traineeForUpdate.setFirstName(trainee.getFirstName());
            traineeForUpdate.setLastName(trainee.getLastName());
            traineeForUpdate.setActive(trainee.isActive());
            loggingResult = true;
        }
        logger.info("trainee updated - " + loggingResult);
    }

    public Optional<Trainee> selectTraineeByID(int id){
        logger.info("select trainee - " + id);
        boolean loggingResult = false;

        Optional<Trainee> trainee = traineeDAO.selectTraineeByID(id);

        if (trainee.isPresent()){
            loggingResult = true;
        }
        logger.info("trainee selected - " + loggingResult);

        return trainee;
    }

    public Optional<Trainee> selectTraineeByUsername(String username){
        logger.info("select trainee - " + username);
        boolean loggingResult = false;

        Optional<Trainee> trainee = traineeDAO.selectTraineeByUsername(username);

        if (trainee.isPresent()){
            loggingResult = true;
            logger.debug("trainee selected - " + trainee.get().getUsername());
        }
        logger.info("trainee selected - " + loggingResult);


        return trainee;
    }

    public void setTraining(int id, Training training){
        logger.info("setting training - " + id);
        boolean loggingResult = false;

        Optional<Trainee> trainee = traineeDAO.selectTraineeByID(id);
        if (trainee.isPresent()){
            trainee.get().setTraining(training);
            loggingResult = true;
        }

        logger.info("training set - " + loggingResult);
    }
}
