package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TrainerDAO;
import epam.tatarinov.gym.Storages.TrainerStorage;
import epam.tatarinov.gym.config.TestConfig;
import epam.tatarinov.gym.models.Trainer;
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
class TrainerServiceTest {

    @Autowired
    public TrainerService trainerService;

    @Autowired
    public TrainerDAO trainerDAO;

    @Autowired
    public TrainerStorage trainerStorage;



    @Test
    void createTrainerUsernameTest() {
        Trainer trainer = new Trainer();
        trainer.setFirstName("trainerName");
        trainer.setLastName("trainerLastName");

        trainerService.createTrainer(trainer);

        Assert.assertEquals("trainerName.trainerLastName", trainer.getUsername());
        Mockito.verify(trainerStorage, Mockito.times(1)).create(trainer);
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
    void selectTrainerTest() {
        trainerService.selectTrainerById(0);
        Mockito.verify(trainerStorage, Mockito.times(1)).selectByID(0);
    }

}