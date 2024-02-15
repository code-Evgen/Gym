package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TraineeDAO;
import epam.tatarinov.gym.DAO.TrainingDAO;
import epam.tatarinov.gym.Storages.TraineeStorage;
import epam.tatarinov.gym.Storages.TrainingStorage;
import epam.tatarinov.gym.config.TestConfig;
import epam.tatarinov.gym.models.Training;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class)
class TrainingServiceTest {

    @Autowired
    public TrainingService trainingService;

    @Autowired
    public TrainingDAO trainingDAO;

    @Autowired
    public TrainingStorage trainingStorage;


    @Test
    void createTraining() {
        Training training = new Training();
        trainingService.createTraining(training);
        Mockito.verify(trainingStorage, Mockito.times(1)).create(training);
    }

    @Test
    void selectTraining() {
        trainingService.selectTraining(0);
        Mockito.verify(trainingStorage, Mockito.times(1)).select(0);
    }
}