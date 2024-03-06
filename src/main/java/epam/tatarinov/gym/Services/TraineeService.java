package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TraineeDAO;
import epam.tatarinov.gym.DAO.UserDAO;
import epam.tatarinov.gym.Storages.TraineeStorage;
import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.models.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class TraineeService {
    private TraineeDAO traineeDAO;
    private UserDAO userDAO;
    private static final Logger logger = Logger.getLogger(TraineeStorage.class);


    @Autowired
    public void setTraineeDAO(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    public void createTrainee(Trainee trainee){
        logger.info("creating trainee - " + trainee.getFirstName() + "_" + trainee.getLastName());
        String username = userDAO.createUsername(trainee.getFirstName(), trainee.getLastName());
        trainee.setUsername(username);
        trainee.setPassword(User.generatePassword());
        trainee.setActive(true);

        if (userDAO.userRequiredFieldValidation(trainee)) {
            traineeDAO.createTrainee(trainee);
            logger.info("trainee created- " + trainee.getFirstName() + "_" + trainee.getLastName());
        }
        else{
            logger.info("user field validation fail" + trainee.getFirstName() + "_" + trainee.getLastName());
            logger.debug("user fields :" + trainee.getFirstName() + "_" + trainee.getLastName() + "_" + trainee.getUsername() + "_ password_length - " + trainee.getPassword());
        }
    }

    @Transactional
    public void deleteTraineeById(int id, User userForAuthentication){
        logger.info("deleting trainee - " + id);
        if (userDAO.usernamePasswordCheck(userForAuthentication.getUsername(), userForAuthentication.getPassword())) {
            boolean loggingResult;
            loggingResult = traineeDAO.deleteTraineeById(id);
            logger.info("trainee deleted - " + loggingResult);
        }
        else{
            logger.info("user authentication fail - " + userForAuthentication.getUsername());
        }
    }

    @Transactional
    public void deleteTraineeByUsername(String username, User userForAuthentication){
        if (userDAO.usernamePasswordCheck(userForAuthentication.getUsername(), userForAuthentication.getPassword())) {
        logger.info("deleting trainee - " + username);
        boolean loggingResult;
        loggingResult = traineeDAO.deleteTraineeByUsername(username);
        logger.info("trainee deleted - " + loggingResult);
        }
        else{
            logger.info("user authentication fail - " + userForAuthentication.getUsername());
        }
    }

    @Transactional
    public void updateTrainee(int id, Trainee trainee, User userForAuthentication){
        logger.info("updating trainee - " + id);
        if (userDAO.usernamePasswordCheck(userForAuthentication.getUsername(), userForAuthentication.getPassword())) {
            boolean loggingResult = false;

            if (userDAO.userRequiredFieldValidation(trainee) == false) {
                logger.info("user field validation fail" + trainee.getFirstName() + "_" + trainee.getLastName());
                logger.debug("user fields :" + trainee.getFirstName() + "_" + trainee.getLastName() + "_" + trainee.getUsername() + "_ password_length - " + trainee.getPassword());
            } else {
                logger.info("user fields validated - " + trainee.getFirstName() + "_" + trainee.getLastName());
                Optional<Trainee> traineeForUpdateOptional = traineeDAO.selectTraineeById(id);
                if (traineeForUpdateOptional.isPresent()) {
                    Trainee traineeForUpdate = traineeForUpdateOptional.get();
                    traineeForUpdate.setAddress(trainee.getAddress());
                    traineeForUpdate.setDateOfBirth(trainee.getDateOfBirth());
                    traineeForUpdate.setFirstName(trainee.getFirstName());
                    traineeForUpdate.setLastName(trainee.getLastName());
//            traineeForUpdate.setUsername(trainee.getUsername());
//            traineeForUpdate.setPassword(trainee.getPassword());
//            traineeForUpdate.setActive(trainee.isActive());
                    loggingResult = true;
                }
                logger.info("trainee updated - " + loggingResult);
            }
        }
        else{
            logger.info("user authentication fail - " + userForAuthentication.getUsername());
        }
    }

    @Transactional
    public Optional<Trainee> selectTraineeById(int id, User userForAuthentication){
        logger.info("select trainee - " + id);
        if (userDAO.usernamePasswordCheck(userForAuthentication.getUsername(), userForAuthentication.getPassword())) {
            boolean loggingResult = false;

            Optional<Trainee> trainee = traineeDAO.selectTraineeById(id);

            if (trainee.isPresent()) {
                loggingResult = true;
            }
            logger.info("trainee selected - " + loggingResult);

            return trainee;
        }
        else {
            logger.info("user authentication fail - " + userForAuthentication.getUsername());
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<Trainee> selectTraineeByUsername(String username, User userForAuthentication){
        logger.info("select trainee - " + username);
        if (userDAO.usernamePasswordCheck(userForAuthentication.getUsername(), userForAuthentication.getPassword())) {
            boolean loggingResult = false;

            Optional<Trainee> trainee = traineeDAO.selectTraineeByUsername(username);

            if (trainee.isPresent()) {
                loggingResult = true;
            }
            logger.info("trainee selected - " + loggingResult);

            return trainee;
        }
        else{
            logger.info("user authentication fail - " + userForAuthentication.getUsername());
        }
        return Optional.empty();
    }

    @Transactional
    public boolean usernamePasswordCheck(String username, String password){
        return userDAO.usernamePasswordCheck(username, password);
    }


    @Transactional
    public void changePassword(int id, String password, User userForAuthentication){
        logger.info("trainee password changing - " + id);
        if (userDAO.usernamePasswordCheck(userForAuthentication.getUsername(), userForAuthentication.getPassword())) {
        boolean loggingResult = false;
        Optional<Trainee> traineeForUpdateOptional = traineeDAO.selectTraineeById(id);
        if (traineeForUpdateOptional.isPresent()) {
            Trainee traineeForUpdate = traineeForUpdateOptional.get();
            traineeForUpdate.setPassword(password);
            loggingResult = true;
        }
        logger.info("trainee password changed - " + loggingResult);

        }
        else{
            logger.info("user authentication fail - " + userForAuthentication.getUsername());
        }
    }


    @Transactional
    public void activate(int id, User userForAuthentication){
        logger.info("trainee activating - " + id);
        if (userDAO.usernamePasswordCheck(userForAuthentication.getUsername(), userForAuthentication.getPassword())) {
            boolean loggingResult = false;
            Optional<Trainee> traineeForUpdateOptional = traineeDAO.selectTraineeById(id);
            if (traineeForUpdateOptional.isPresent()) {
                Trainee traineeForUpdate = traineeForUpdateOptional.get();
                traineeForUpdate.setActive(true);
                loggingResult = true;
            }
            logger.info("trainee activated - " + loggingResult);
        }
        else {
            logger.info("user authentication fail - " + userForAuthentication.getUsername());
        }
    }

    @Transactional
    public void deactivate(int id, User userForAuthentication){
        logger.info("trainee deactivating - " + id);
        if (userDAO.usernamePasswordCheck(userForAuthentication.getUsername(), userForAuthentication.getPassword())) {
            boolean loggingResult = false;
            Optional<Trainee> traineeForUpdateOptional = traineeDAO.selectTraineeById(id);
            if (traineeForUpdateOptional.isPresent()) {
                Trainee traineeForUpdate = traineeForUpdateOptional.get();
                traineeForUpdate.setActive(false);
                loggingResult = true;
            }
            logger.info("trainee deactivated - " + loggingResult);

        }
        else {
            logger.info("user authentication fail - " + userForAuthentication.getUsername());
        }
    }

    @Transactional
    public void setTrainingToTraineeId(int id, Training training){
        traineeDAO.setTrainingToTraineeId(id, training);
    }

//    @Transactional
//    public List<Training> getTrainingByTraineeUsername(String username){
//        return traineeDAO.getTrainingByTraineeUsername(username);
//    }

}
