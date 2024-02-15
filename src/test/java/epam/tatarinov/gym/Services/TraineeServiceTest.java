package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TraineeDAO;
import epam.tatarinov.gym.Storages.TraineeStorage;
import epam.tatarinov.gym.config.TestConfig;
import epam.tatarinov.gym.models.Trainee;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class)
class TraineeServiceTest {


    @Autowired
    public TraineeService traineeService;

    @Autowired
    public TraineeDAO traineeDAO;

    @Autowired
    public TraineeStorage traineeStorage;

    @Test
    void createTraineeUsernameTest() {
        Trainee trainee = new Trainee();
        trainee.setFirstName("traineeName");
        trainee.setLastName("traineeLastName");

        traineeService.createTrainee(trainee);

        Assert.assertEquals("traineeName.traineeLastName", trainee.getUsername());
        Mockito.verify(traineeStorage, Mockito.times(1)).create(trainee);
    }

    @Test
    void createTraineeUsernameDuplicateTest() {
        Trainee trainee = new Trainee();
        trainee.setFirstName("traineeName");
        trainee.setLastName("traineeLastName");

        traineeService.createTrainee(trainee);

        Assert.assertEquals("traineeName.traineeLastName1", trainee.getUsername());
    }

    @Test
    void createTraineeGeneratePasswordTest() {
        Trainee trainee = new Trainee();
        traineeService.createTrainee(trainee);
        String password = trainee.getPassword();

        Trainee trainee2 = new Trainee();
        traineeService.createTrainee(trainee2);
        String password2 = trainee2.getPassword();

        Assert.assertEquals(10, password.length());
        Assert.assertTrue(!password.equals(password2));
    }

    @Test
    void selectTraineeTest() {
        traineeService.selectTraineeByID(0);
        Mockito.verify(traineeStorage, Mockito.times(1)).selectByID(0);
    }

    @Test
    void deleteNotExistingTraineeTest() {
        traineeService.deleteTrainee(0);
        Mockito.verify(traineeStorage, Mockito.times(0)).delete(0);
    }
}