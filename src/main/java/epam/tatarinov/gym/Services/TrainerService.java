package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TraineeDAO;
import epam.tatarinov.gym.DAO.TrainerDAO;
import epam.tatarinov.gym.DAO.TrainingTypeDAO;
import epam.tatarinov.gym.DAO.UserDAO;
import epam.tatarinov.gym.models.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TrainerService {
    private TrainerDAO trainerDAO;
    private UserDAO userDAO;
    private TrainingTypeDAO trainingTypeDAO;
    private TraineeDAO traineeDAO;

    private static final Logger logger = Logger.getLogger(TrainerService.class);

    @Autowired
    public void setTrainerDAO(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }
    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
    @Autowired
    public void setTrainingTypeDAO(TrainingTypeDAO trainingTypeDAO) {
        this.trainingTypeDAO = trainingTypeDAO;
    }
    @Autowired
    public void setTraineeDAO(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    @Transactional
    public void createTrainer(Trainer trainer){
        logger.info("creating trainer - " + trainer.getFirstName() + "_" + trainer.getLastName());
        String username = userDAO.createUsername(trainer.getFirstName(), trainer.getLastName());
        trainer.setUsername(username);
        trainer.setPassword(User.generatePassword());
        trainer.setActive(true);

        if (userDAO.userRequiredFieldValidation(trainer)) {
            trainerDAO.createTrainer(trainer);
            logger.info("trainee created- " + trainer.getFirstName() + "_" + trainer.getLastName());
        }
        else{
            logger.info("user field validation fail" + trainer.getFirstName() + "_" + trainer.getLastName());
            logger.debug("user fields :" + trainer.getFirstName() + "_" + trainer.getLastName() + "_" + trainer.getUsername() + "_ password_length - " + trainer.getPassword());
        }

    }


    @Transactional
    public void updateTrainer(int id, Trainer trainer){
        logger.info("updating trainer - " + id);
        boolean loggingResult = false;

        if (userDAO.userRequiredFieldValidation(trainer) == false) {
            logger.info("user field validation fail" + trainer.getFirstName() + "_" + trainer.getLastName());
            logger.debug("user fields :" + trainer.getFirstName() + "_" + trainer.getLastName() + "_" + trainer.getUsername() + "_ password_length - " + trainer.getPassword());
        }
        else {
            logger.info("user fields validated - " + trainer.getFirstName() + "_" + trainer.getLastName());

            Optional<Trainer> trainerForUpdateOptional = trainerDAO.selectTrainerById(id);
            if (trainerForUpdateOptional.isPresent()) {
                Trainer trainerForUpdate = trainerForUpdateOptional.get();
                trainerForUpdate.setSpecialization(trainer.getSpecialization());
                trainerForUpdate.setFirstName(trainer.getFirstName());
                trainerForUpdate.setLastName(trainer.getLastName());
//            trainerForUpdate.setActive(trainer.isActive());
                loggingResult = true;
            }
            logger.info("trainer updating - " + loggingResult);
        }
    }

    @Transactional
    public Optional<Trainer> selectTrainerById(int id){
        logger.info("select trainee - " + id);
        boolean loggingResult = false;

        Optional<Trainer> trainer = trainerDAO.selectTrainerById(id);

        if (trainer.isPresent()){
            loggingResult = true;
        }
        logger.info("trainer selected - " + loggingResult);

        return trainer;
    }

    @Transactional
    public Optional<Trainer> selectTrainerByUsername(String username){
        logger.info("select trainee - " + username);
        boolean loggingResult = false;

        Optional<Trainer> trainer = trainerDAO.selectTrainerByUsername(username);

        if (trainer.isPresent()){
            loggingResult = true;
            logger.debug("trainee selected - " + trainer.get().getUsername());
        }
        logger.info("trainer selected - " + loggingResult);

        return trainer;
    }

    public void setTrainingType(int id, TrainingType trainingType){
        logger.info("setting training - " + id);
        boolean loggingResult = false;

        Optional<Trainer> trainer = trainerDAO.selectTrainerById(id);
        if (trainer.isPresent()){
            trainer.get().setSpecialization(trainingType);
            loggingResult = true;
        }

        logger.info("training set - " + loggingResult);
    }


    @Transactional
    public boolean usernamePasswordCheck(String username, String password){
        return userDAO.usernamePasswordCheck(username, password);
    }


    @Transactional
    public void changePassword(int id, String password){
        logger.info("trainer password changing - " + id);
        boolean loggingResult = false;
        Optional<Trainer> trainerForUpdateOptional = trainerDAO.selectTrainerById(id);
        if (trainerForUpdateOptional.isPresent()) {
            Trainer trainerForUpdate = trainerForUpdateOptional.get();
            trainerForUpdate.setPassword(password);
            loggingResult = true;
        }
        logger.info("trainer password changed - " + loggingResult);
    }


    @Transactional
    public void activate(int id){
        logger.info("trainer activating - " + id);
        boolean loggingResult = false;
        Optional<Trainer> trainerForUpdateOptional = trainerDAO.selectTrainerById(id);
        if (trainerForUpdateOptional.isPresent()) {
            Trainer trainerForUpdate = trainerForUpdateOptional.get();
            trainerForUpdate.setActive(true);
            loggingResult = true;
        }
        logger.info("trainer activated - " + loggingResult);
    }

    @Transactional
    public void deactivate(int id){
        logger.info("trainer deactivating - " + id);
        boolean loggingResult = false;
        Optional<Trainer> trainerForUpdateOptional = trainerDAO.selectTrainerById(id);
        if (trainerForUpdateOptional.isPresent()) {
            Trainer trainerForUpdate = trainerForUpdateOptional.get();
            trainerForUpdate.setActive(false);
            loggingResult = true;
        }
        logger.info("trainer deactivated - " + loggingResult);
    }

    @Transactional
    public void setSpecialization (Trainer trainer, TrainingTypeName trainingTypeName){
        Optional<TrainingType> trainingType = trainingTypeDAO.selectTrainingTypeByName(trainingTypeName);
        if (trainingType.isPresent())
            trainer.setSpecialization(trainingType.get());
    }

    @Transactional
    public void setTrainingToTrainerId(int id, Training training){
        trainerDAO.setTrainingToTrainerId(id, training);
    }

//    @Transactional
//    public List<Training> getTrainingByTrainerUsername(String username){
//        return trainerDAO.getTrainingByTrainerUsername(username);
//    }

    @Transactional
    public List<Trainer> getTrainersListWithoutTraineeByTraineeUsername(String username){
        return trainerDAO.getTrainersListWithoutTraineeByTraineeUsername(username);
    }

    @Transactional
    public void deleteTrainer(int id){
        logger.info("deleting trainer - " + id);
        boolean loggingResult;
        loggingResult = trainerDAO.deleteTrainer(id);
        logger.info("trainer deleted - " + loggingResult);
    }
}
