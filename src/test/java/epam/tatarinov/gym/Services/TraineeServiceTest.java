package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TraineeDAO;
import epam.tatarinov.gym.config.SpringConfig;
import epam.tatarinov.gym.models.Trainee;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertThrows;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class,
        loader = AnnotationConfigContextLoader.class)
@Sql(scripts={"classpath:sql_before_all.sql"}, executionPhase = BEFORE_TEST_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TraineeServiceTest {
    @Autowired
    public TraineeService traineeService;

    @Autowired
    public TraineeDAO traineeDAO;

    private static Trainee baseTrainee = new Trainee();

    static {
        baseTrainee.setUsername("baseusername");
        baseTrainee.setPassword("Password123");
    }


    @Test
    @Order(1)
    void createTraineeUsernameTest() {
        Trainee trainee = new Trainee();
        trainee.setFirstName("traineeName");
        trainee.setLastName("traineeLastName");
        trainee.setPassword("Password123");

        traineeService.createTrainee(trainee);

        Assert.assertEquals("traineeName.traineeLastName", trainee.getUsername());

    }

    @Test
    @Order(2)
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
    void selectTraineeByIdTest() {
        Trainee trainee = traineeService.selectTraineeById(2, baseTrainee).get();
        Assert.assertEquals("traineeName.traineeLastName1", trainee.getUsername());
    }

    @Test
    void selectTraineeByUsername() {
        Trainee trainee = traineeService.selectTraineeByUsername("traineeName.traineeLastName1", baseTrainee).get();
        Assert.assertEquals(2, trainee.getTraineeId());
    }

    @Test
    void changePassword() {
        traineeService.changePassword(2, "newPassword", baseTrainee);
        Trainee trainee = traineeService.selectTraineeById(2, baseTrainee).get();
        Assert.assertEquals("newPassword", trainee.getPassword());
    }

    @Test
    void usernamePasswordCheck() {
        boolean checkResult = traineeService.usernamePasswordCheck("traineeName.traineeLastName1", "newPassword");
        Assert.assertEquals(true, checkResult);
    }


    @Test
    void deactivate() {
        traineeService.deactivate(2, baseTrainee);
        Trainee trainee = traineeService.selectTraineeById(2, baseTrainee).get();
        Assert.assertEquals(false, trainee.isActive());
    }

    @Test
    void activate() {
        traineeService.activate(2, baseTrainee);
        Trainee trainee = traineeService.selectTraineeById(2, baseTrainee).get();
        Assert.assertEquals(true, trainee.isActive());
    }

    @Test
    @Sql("classpath:sql_trainee_for_delete.sql")
    void deleteTraineeTest() {
        Trainee trainee = traineeService.selectTraineeByUsername("username", baseTrainee).get();
        int traineeId = trainee.getId();
        Assert.assertEquals("username", trainee.getUsername());

        traineeService.deleteTraineeById(traineeId, baseTrainee);
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            traineeService.selectTraineeById(traineeId, baseTrainee).get();
        });

        String expectedMessage = "No value present";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    @Sql("classpath:sql_trainee_for_delete.sql")
    void deleteTraineeByUsernameTest() {
        Trainee trainee = traineeService.selectTraineeByUsername("username", baseTrainee).get();
        Assert.assertEquals("username", trainee.getUsername());

        traineeService.deleteTraineeByUsername("username", baseTrainee);
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            traineeService.selectTraineeByUsername("username", baseTrainee).get();
        });

        String expectedMessage = "No value present";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));
    }


//    @Test
//    void deleteNotExistingTraineeTest() {
//        traineeService.deleteTrainee(0);
//        Mockito.verify(traineeDAO, Mockito.times(0)).deleteTrainee(0);
//    }
}