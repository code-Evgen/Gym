package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TraineeDAO;
import epam.tatarinov.gym.DAO.TrainerDAO;
import epam.tatarinov.gym.DAO.TrainingDAO;
import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.models.Trainer;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.models.TrainingType;
import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingService {
    private TrainingDAO trainingDAO;
    private TraineeDAO traineeDAO;
    private TrainerDAO trainerDAO;
    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(TrainingService.class);


    @Autowired
    public void setTrainingDAO(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

    @Autowired
    public void setTraineeDAO(TraineeDAO traineeDAO) {
        this.traineeDAO = traineeDAO;
    }

    @Autowired
    public void setTrainerDAO(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void createTraining(Training training){
        logger.info("creating training - type_" + training.getTrainingType());

        if (trainingDAO.trainingRequiredFieldValidation(training)) {
            trainingDAO.createTraining(training);

            Trainee trainee = training.getTrainee();
            trainee = (Trainee) sessionFactory.getCurrentSession().merge(trainee);
            Trainer trainer = training.getTrainer();
            Hibernate.initialize(trainee);
            boolean alreadyExist = false;
            for (Trainer t : trainee.getTrainerList()){
                if (trainer.getId() == t.getId()){
                    alreadyExist = true;
                    break;
                }
            }
            if (!alreadyExist) {
                trainingDAO.assignTraineeToTrainer(trainee, trainer);
            }

            logger.info("training created");
        }
        else {
            logger.info("training field validation fail");
            logger.debug("training fields :" + training.getTrainee() + "_" + training.getTrainer() + "_" + training.getTrainingName() + "_" + training.getTrainingType() + "_" + training.getTrainingDate() + "_" + training.getTrainingDuration());
        }
    }

    @Transactional
    public Optional<Training> selectTraining(int id){
        return trainingDAO.selectTraining(id);
    }


    @Transactional
    public List<Training> getTrainingListByTraineeUsername (String username){
        Optional<Trainee> traineeOptional = traineeDAO.selectTraineeByUsername(username);
        if (traineeOptional.isPresent()){
            Trainee trainee = traineeOptional.get();
            Hibernate.initialize(trainee.getTrainingList());
            return trainee.getTrainingList();
        }
        return Collections.emptyList();
    }

    @Transactional
    public List<Training> getTrainingListByTraineeUsernameFromDate (String username, Date date){
        Optional<Trainee> traineeOptional = traineeDAO.selectTraineeByUsername(username);
        if (traineeOptional.isPresent()){
            return trainingDAO.selectTrainingByTraineeUsernameFromDate(username, date);
        }
        return Collections.emptyList();
    }

    @Transactional
    public List<Training> getTrainingListByTraineeUsernameToDate (String username, Date date){
        Optional<Trainee> traineeOptional = traineeDAO.selectTraineeByUsername(username);
        if (traineeOptional.isPresent()){
            return trainingDAO.selectTrainingByTraineeUsernameToDate(username, date);
        }
        return Collections.emptyList();
    }

    @Transactional
    public List<Training> getTrainingListByTraineeUsernameAndTrainerName (String traineeUsername, String trainerName){
        Optional<Trainee> traineeOptional = traineeDAO.selectTraineeByUsername(traineeUsername);
        if (traineeOptional.isPresent()){
            return trainingDAO.selectTrainingByTraineeUsernameAndTrainerName(traineeUsername, trainerName);
        }
        return Collections.emptyList();
    }

    @Transactional
    public List<Training> getTrainingListByTraineeUsernameAndTrainingType (String traineeUsername, TrainingType trainingType){
        Optional<Trainee> traineeOptional = traineeDAO.selectTraineeByUsername(traineeUsername);
        if (traineeOptional.isPresent()){
            return trainingDAO.selectTrainingByTraineeUsernameAndTrainingType(traineeUsername, trainingType.getId());
        }
        return Collections.emptyList();
    }





    @Transactional
    public List<Training> getTrainingListByTrainerUsername (String username){
        Optional<Trainer> trainerOptional = trainerDAO.selectTrainerByUsername(username);
        if (trainerOptional.isPresent()){
            Trainer trainer = trainerOptional.get();
            Hibernate.initialize(trainer.getTrainingList());
            return trainer.getTrainingList();
        }
        return Collections.emptyList();
    }

    @Transactional
    public List<Training> getTrainingListByTrainerUsernameFromDate (String username, Date date){
        Optional<Trainer> trainerOptional = trainerDAO.selectTrainerByUsername(username);
        if (trainerOptional.isPresent()){
            return trainingDAO.selectTrainingByTrainerUsernameFromDate(username, date);
        }
        return Collections.emptyList();
    }

    @Transactional
    public List<Training> getTrainingListByTrainerUsernameToDate (String username, Date date){
        Optional<Trainer> trainerOptional = trainerDAO.selectTrainerByUsername(username);
        if (trainerOptional.isPresent()){
            return trainingDAO.selectTrainingByTrainerUsernameToDate(username, date);
        }
        return Collections.emptyList();
    }

    @Transactional
    public List<Training> getTrainingListByTrainerUsernameAndTraineeName(String trainerUsername, String traineeName){
        Optional<Trainer> trainerOptional = trainerDAO.selectTrainerByUsername(trainerUsername);
        if (trainerOptional.isPresent()){
            return trainingDAO.selectTrainingByTrainerUsernameAndTraineeName(trainerUsername, traineeName);
        }
        return Collections.emptyList();
    }

    @Transactional
    public List<Training> getTrainingListByTrainerUsernameAndTrainingType (String trainerUsername, TrainingType trainingType){
        Optional<Trainer> trainerOptional = trainerDAO.selectTrainerByUsername(trainerUsername);
        if (trainerOptional.isPresent()){
            return trainingDAO.selectTrainingByTrainerUsernameAndTrainingType(trainerUsername, trainingType.getId());
        }
        return Collections.emptyList();
    }

}
