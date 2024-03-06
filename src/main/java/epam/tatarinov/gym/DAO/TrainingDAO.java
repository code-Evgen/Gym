package epam.tatarinov.gym.DAO;

import epam.tatarinov.gym.Storages.TrainingStorage;
import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.models.Trainer;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.util.ClassType;
import epam.tatarinov.gym.util.StorageInitialization;
import jakarta.persistence.Query;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@StorageInitialization(CLASS_TYPE = ClassType.TRAINING)
public class TrainingDAO {
    private SessionFactory sessionFactory;
    private TrainingStorage trainingStorage;

    @Autowired
    public void setTrainingStorage(TrainingStorage trainingStorage) {
        this.trainingStorage = trainingStorage;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createTraining(Training training){
        Session session = sessionFactory.getCurrentSession();
        session.persist(training);
    }

    public Optional<Training> selectTraining(int id){
        Session session = sessionFactory.getCurrentSession();
        Training training = session.get(Training.class, id);
        return Optional.ofNullable(training);
    }

    public void assignTraineeToTrainer(Trainee trainee, Trainer trainer){
        Session session = sessionFactory.getCurrentSession();
        trainee = (Trainee) session.merge(trainee);
        Hibernate.initialize(trainee.getTrainerList());
        trainee.getTrainerList().add(trainer);

        trainer = (Trainer) session.merge(trainer);
        Hibernate.initialize(trainer.getTraineeList());
        trainer.getTraineeList().add(trainee);
    }



    public List<Training> selectTrainingByTraineeUsernameFromDate(String username, Date date){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Training t join fetch t.trainee l where l.username = :username and t.trainingDate >= :date";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("date", date);
        List<Training> queryResult = query.getResultList();
        return queryResult;
    }

    public List<Training> selectTrainingByTraineeUsernameToDate(String username, Date date){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Training t join fetch t.trainee l where l.username = :username and t.trainingDate < :date";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("date", date);
        List<Training> queryResult = query.getResultList();
        return queryResult;
    }

    public List<Training> selectTrainingByTraineeUsernameAndTrainerName(String traineeUsername, String trainerName){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Training t join fetch t.trainee l where l.username = :traineeUsername and t.trainer.lastName = :trainerName";
        Query query = session.createQuery(hql);
        query.setParameter("traineeUsername", traineeUsername);
        query.setParameter("trainerName", trainerName);
        List<Training> queryResult = query.getResultList();
        return queryResult;
    }

    public List<Training> selectTrainingByTraineeUsernameAndTrainingType(String traineeUsername, int trainingType){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Training t join fetch t.trainee l where l.username = :traineeUsername and t.trainingType.id = :trainingType";
        Query query = session.createQuery(hql);
        query.setParameter("traineeUsername", traineeUsername);
        query.setParameter("trainingType", trainingType);
        List<Training> queryResult = query.getResultList();
        return queryResult;
    }



    public List<Training> selectTrainingByTrainerUsernameFromDate(String username, Date date){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Training t join fetch t.trainer l where l.username = :username and t.trainingDate >= :date";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("date", date);
        List<Training> queryResult = query.getResultList();
        return queryResult;
    }

    public List<Training> selectTrainingByTrainerUsernameToDate(String username, Date date){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Training t join fetch t.trainer l where l.username = :username and t.trainingDate < :date";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("date", date);
        List<Training> queryResult = query.getResultList();
        return queryResult;
    }

    public List<Training> selectTrainingByTrainerUsernameAndTraineeName(String trainerUsername, String traineeName){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Training t join fetch t.trainer l where l.username = :trainerUsername and t.trainee.lastName = :traineeName";
        Query query = session.createQuery(hql);
        query.setParameter("trainerUsername", trainerUsername);
        query.setParameter("traineeName", traineeName);
        List<Training> queryResult = query.getResultList();
        return queryResult;
    }

    public List<Training> selectTrainingByTrainerUsernameAndTrainingType(String trainerUsername, int trainingType){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Training t join fetch t.trainer l where l.username = :trainerUsername and t.trainingType.id = :trainingType";
        Query query = session.createQuery(hql);
        query.setParameter("trainerUsername", trainerUsername);
        query.setParameter("trainingType", trainingType);
        List<Training> queryResult = query.getResultList();
        return queryResult;
    }



    public boolean trainingRequiredFieldValidation(Training training){
        if (training.getTrainee() == null)
            return false;
        if (training.getTrainer() == null)
            return false;
        if (training.getTrainingName() == null)
            return false;
        if (training.getTrainingType() == null)
            return false;
        if (training.getTrainingDate() == null)
            return false;
        if (training.getTrainingDuration() == 0)
            return false;
        return true;
    }

}
