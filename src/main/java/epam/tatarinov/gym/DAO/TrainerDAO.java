package epam.tatarinov.gym.DAO;

import epam.tatarinov.gym.Storages.TrainerStorage;
import epam.tatarinov.gym.models.Trainer;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.util.ClassType;
import epam.tatarinov.gym.util.StorageInitialization;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@StorageInitialization(CLASS_TYPE = ClassType.TRAINER)
public class TrainerDAO {
    private TrainerStorage trainerStorage;
    private SessionFactory sessionFactory;

    @Autowired
    public void setTrainerStorage(TrainerStorage trainerStorage) {
        this.trainerStorage = trainerStorage;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createTrainer(Trainer trainer){
        Session session = sessionFactory.getCurrentSession();
        session.persist(trainer);
    }


    public Optional<Trainer> selectTrainerById(int id){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Trainer t where t.trainerId = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        Object queryResult = query.getResultList().stream().findFirst().orElse(null);
        if (queryResult == null)
            return Optional.empty();
        return Optional.ofNullable((Trainer) queryResult);
    }

    public Optional<Trainer> selectTrainerByUsername(String username){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Trainer t where t.username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        Object queryResult = query.getResultList().stream().findFirst().orElse(null);
        if (queryResult == null)
            return Optional.empty();
        return Optional.ofNullable((Trainer) queryResult);
    }

    public void setTrainingToTrainerId(int id, Training training){
        Optional<Trainer> trainerOptional = selectTrainerById(id);
        if (trainerOptional.isPresent()){
            Trainer trainer = trainerOptional.get();
//            trainer.getTrainingList().add(training);
        }
    }

//    public List<Training> getTrainingByTrainerUsername(String username) {
//        Optional<Trainer> trainerOptional = selectTrainerByUsername(username);
//        if (trainerOptional.isPresent()){
//            Trainer trainer = trainerOptional.get();
//            return trainer.getTrainingList();
//        }
//        return Collections.emptyList();
//    }

    public List<Trainer> getTrainersListWithoutTraineeByTraineeUsername(String username){
        Session session = sessionFactory.getCurrentSession();

        String hql = "from Trainer t left join t.traineeList l except from Trainer t left join t.traineeList l where l.username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        List<Trainer> queryResult = query.getResultList();
        if (queryResult != null)
            return queryResult;
        return Collections.emptyList();
    }

    public boolean deleteTrainer(int id){
        Optional<Trainer> trainerOptional = selectTrainerById(id);
        if (trainerOptional.isPresent()) {
            Trainer trainer = trainerOptional.get();
            Session session = sessionFactory.getCurrentSession();
            session.remove(trainer);
            return true;
        }
        return false;
    }

}
