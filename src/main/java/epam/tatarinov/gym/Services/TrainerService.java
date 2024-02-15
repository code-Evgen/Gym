package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TrainerDAO;
import epam.tatarinov.gym.models.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerService {
    private TrainerDAO trainerDAO;
    private static final Logger logger = Logger.getLogger(TrainerService.class);

    @Autowired
    public void setTrainerDAO(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    public void createTrainer(Trainer trainer){
        logger.info("creating trainer - " + trainer.getFirstName() + "_" + trainer.getLastName());
        int userId = User.getUserID();
        trainer.setUserId(userId);
        trainer.setUsername(User.createUsername(trainer.getFirstName(), trainer.getLastName()));
        trainer.setPassword(User.generatePassword());
        trainer.setActive(true);
        boolean loggingResult;
        loggingResult = trainerDAO.createTrainer(trainer);
        logger.info("trainer created - " + loggingResult);
    }


    public void updateTrainer(int id, Trainer trainer){
        logger.info("updating trainer - " + id);
        boolean loggingResult = false;
        Optional<Trainer> trainerForUpdateOptional = trainerDAO.selectTrainerByID(id);
        if (trainerForUpdateOptional.isPresent()) {
            Trainer trainerForUpdate = trainerForUpdateOptional.get();
            trainerForUpdate.setSpecialization(trainer.getSpecialization());
            trainerForUpdate.setFirstName(trainer.getFirstName());
            trainerForUpdate.setLastName(trainer.getLastName());
            trainerForUpdate.setActive(trainer.isActive());
            loggingResult = true;
        }
        logger.info("trainer updating - " + loggingResult);
    }

    public Optional<Trainer> selectTrainerById(int id){
        logger.info("select trainee - " + id);
        boolean loggingResult = false;

        Optional<Trainer> trainer = trainerDAO.selectTrainerByID(id);

        if (trainer.isPresent()){
            loggingResult = true;
        }
        logger.info("trainer selected - " + loggingResult);

        return trainer;
    }

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

    public void setTraining(int id, Training training){
        Optional<Trainer> trainer = trainerDAO.selectTrainerByID(id);
        if (trainer.isPresent()){
            trainer.get().setTraining(training);
        }
    }

    public void setTrainingType(int id, TrainingType trainingType){
        logger.info("setting training - " + id);
        boolean loggingResult = false;

        Optional<Trainer> trainer = trainerDAO.selectTrainerByID(id);
        if (trainer.isPresent()){
            trainer.get().setTrainingType(trainingType);
            loggingResult = true;
        }

        logger.info("training set - " + loggingResult);
    }

}
