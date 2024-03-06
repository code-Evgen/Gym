package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TrainerDAO;
import epam.tatarinov.gym.Storages.TrainerStorage;
import epam.tatarinov.gym.config.SpringConfig;
import epam.tatarinov.gym.config.TestConfig;
import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.models.Trainer;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
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
class TrainerServiceTest {

    @Autowired
    public TrainerService trainerService;

    @Autowired
    public TrainerDAO trainerDAO;

    @Autowired
    public TrainerStorage trainerStorage;



    @Test
    @Order(1)
    void createTrainerUsernameTest() {
        Trainer trainer = new Trainer();
        trainer.setFirstName("trainerName");
        trainer.setLastName("trainerLastName");

        trainerService.createTrainer(trainer);

        Assert.assertEquals("trainerName.trainerLastName", trainer.getUsername());

    }

    @Test
    @Order(2)
    void createTrainerUsernameDuplicateTest() {
        Trainer trainer = new Trainer();
        trainer.setFirstName("trainerName");
        trainer.setLastName("trainerLastName");

        trainerService.createTrainer(trainer);

        Assert.assertEquals("trainerName.trainerLastName1", trainer.getUsername());
    }

    @Test
    void createTrainerGeneratePasswordTest() {
        Trainer trainer = new Trainer();
        trainerService.createTrainer(trainer);
        String password = trainer.getPassword();

        Trainer trainer2 = new Trainer();
        trainerService.createTrainer(trainer2);
        String password2 = trainer2.getPassword();

        Assert.assertEquals(10, password.length());
        Assert.assertTrue(!password.equals(password2));
    }

    @Test
    void selectTrainerByIdTest() {
        Trainer trainer = trainerService.selectTrainerById(2).get();
        Assert.assertEquals("trainerName.trainerLastName1", trainer.getUsername());
    }

    @Test
    void selectTrainerByUsername() {
        Trainer trainer = trainerService.selectTrainerByUsername("trainerName.trainerLastName1").get();
        Assert.assertEquals(2, trainer.getTrainerId());
    }

    @Test
    void changePassword() {
        trainerService.changePassword(2, "newPassword");
        Trainer trainer = trainerService.selectTrainerById(2).get();
        Assert.assertEquals("newPassword", trainer.getPassword());
    }

    @Test
    void usernamePasswordCheck() {
        boolean checkResult = trainerService.usernamePasswordCheck("trainerName.trainerLastName1", "newPassword");
        Assert.assertEquals(true, checkResult);
    }


    @Test
    void deactivate() {
        trainerService.deactivate(2);
        Trainer trainer = trainerService.selectTrainerById(2).get();
        Assert.assertEquals(false, trainer.isActive());
    }

    @Test
    void activate() {
        trainerService.activate(2);
        Trainer trainer = trainerService.selectTrainerById(2).get();
        Assert.assertEquals(true, trainer.isActive());
    }


//    @Test
//    void selectTrainerTest() {
//        trainerService.selectTrainerById(0);
//        Mockito.verify(trainerStorage, Mockito.times(1)).selectByID(0);
//    }

}