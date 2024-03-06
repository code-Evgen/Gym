package epam.tatarinov.gym.DAO;

import epam.tatarinov.gym.Storages.TraineeStorage;
import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.util.ClassType;
import epam.tatarinov.gym.util.StorageInitialization;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@StorageInitialization(CLASS_TYPE = ClassType.TRAINEE)
public class TraineeDAO {
    private TraineeStorage traineeStorage;

    private SessionFactory sessionFactory;


    @Autowired
    public void setTraineeStorage(TraineeStorage traineeStorage) {
        this.traineeStorage = traineeStorage;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void createTrainee(Trainee trainee){
        Session session = sessionFactory.getCurrentSession();
        session.persist(trainee);
    }

    public boolean deleteTraineeById(int id){
        Optional<Trainee> traineeOptional = selectTraineeById(id);
        if (traineeOptional.isPresent()) {
            Trainee trainee = traineeOptional.get();
            Session session = sessionFactory.getCurrentSession();
            session.remove(trainee);
            return true;
        }
        return false;
    }

    public boolean deleteTraineeByUsername(String username){
        Optional<Trainee> traineeOptional = selectTraineeByUsername(username);
        if (traineeOptional.isPresent()) {
            Trainee trainee = traineeOptional.get();
            Session session = sessionFactory.getCurrentSession();
            session.remove(trainee);
            return true;
        }
        return false;
    }


    public Optional<Trainee> selectTraineeById(int id){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Trainee t where t.traineeId = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        Object queryResult = query.getResultList().stream().findFirst().orElse(null);
        if (queryResult == null)
            return Optional.empty();
        return Optional.ofNullable((Trainee) queryResult);
    }

    public Optional<Trainee> selectTraineeByUsername(String username){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Trainee t where t.username = :username";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        Object queryResult = query.getResultList().stream().findFirst().orElse(null);
        if (queryResult == null)
            return Optional.empty();
        return Optional.ofNullable((Trainee) queryResult);
    }


    public void setTrainingToTraineeId(int id, Training training){
        Optional<Trainee> traineeOptional = selectTraineeById(id);
        if (traineeOptional.isPresent()){
            Trainee trainee = traineeOptional.get();
            trainee.getTrainingList().add(training);
        }
    }

    public void getTrainingByTraineeId(int id, Training training){
        Optional<Trainee> traineeOptional = selectTraineeById(id);
        if (traineeOptional.isPresent()){
            Trainee trainee = traineeOptional.get();
            trainee.getTrainingList().add(training);
        }
    }

//    public List<Training> getTrainingByTraineeUsername(String username) {
//        Optional<Trainee> traineeOptional = selectTraineeByUsername(username);
//        if (traineeOptional.isPresent()){
//            Trainee trainee = traineeOptional.get();
//            return trainee.getTrainingList();
//        }
//        return Collections.emptyList();
//    }

}
