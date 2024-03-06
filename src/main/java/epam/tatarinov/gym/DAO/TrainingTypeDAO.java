package epam.tatarinov.gym.DAO;

import epam.tatarinov.gym.Storages.TrainingTypeStorage;
import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.models.TrainingTypeName;
import epam.tatarinov.gym.util.StorageInitialization;
import epam.tatarinov.gym.models.TrainingType;
import epam.tatarinov.gym.util.ClassType;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@StorageInitialization(CLASS_TYPE = ClassType.TRAINING_TYPE)
public class TrainingTypeDAO {
    private TrainingTypeStorage trainingTypeStorage;

    private SessionFactory sessionFactory;

    @Autowired
    public void setTrainingTypeStorage(TrainingTypeStorage trainingTypeStorage) {
        this.trainingTypeStorage = trainingTypeStorage;
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Optional<TrainingType> selectTrainingType(int id){
        return Optional.ofNullable(trainingTypeStorage.select(id));
    }

    public Optional<TrainingType> selectTrainingTypeByName(TrainingTypeName name){
        Session session = sessionFactory.getCurrentSession();
        String hql = "from TrainingType t where t.trainingTypeName = :trainingTypeName";
        Query query = session.createQuery(hql);
        query.setParameter("trainingTypeName", name);
        Object queryResult = query.getResultList().stream().findFirst().orElse(null);
        if (queryResult == null)
            return Optional.empty();
        return Optional.ofNullable((TrainingType)queryResult);
    }

    public Optional<Integer> selectTrainingTypeIDByName(TrainingTypeName name){
        Map<Integer, TrainingType> trainingTypeMap = TrainingTypeStorage.getTrainingTypes();
        for (Map.Entry<Integer, TrainingType> t : trainingTypeMap.entrySet()){
            if (t.getValue().getTrainingTypeName().equals(name)){
                return Optional.ofNullable(t.getKey());
            }
        }
        return Optional.empty();
    }
}
