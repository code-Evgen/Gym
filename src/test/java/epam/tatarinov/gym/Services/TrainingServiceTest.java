package epam.tatarinov.gym.Services;

import epam.tatarinov.gym.DAO.TrainingDAO;
import epam.tatarinov.gym.Storages.TrainingStorage;
import epam.tatarinov.gym.config.SpringConfig;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.models.TrainingType;
import epam.tatarinov.gym.models.TrainingTypeName;
import org.junit.Assert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = SpringConfig.class,
        loader = AnnotationConfigContextLoader.class)
@Sql(scripts={"classpath:sql_training_before_all.sql"}, executionPhase = BEFORE_TEST_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TrainingServiceTest {

    @Autowired
    public TrainingService trainingService;

    @Autowired
    public TrainingDAO trainingDAO;

    @Autowired
    public TrainingStorage trainingStorage;

    @Autowired
    public TrainingTypeService trainingTypeService;

    @Test
    void getTrainingListByTraineeUsername() {
        List<Training> trainingList = trainingService.getTrainingListByTraineeUsername("username1");
        Assert.assertEquals(2, trainingList.size());
    }

    @Test
    void getTrainingListByTraineeUsernameFromDate() {
        List<Training> trainingList = null;
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/02/2024 18:00:00");
            trainingList = trainingService.getTrainingListByTraineeUsernameFromDate("username1", date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(1, trainingList.size());
    }

    @Test
    void getTrainingListByTraineeUsernameToDate() {
        List<Training> trainingList = null;
        try {
            trainingList = trainingService.getTrainingListByTraineeUsernameToDate("username1", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/02/2024 18:00:00"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(1, trainingList.size());
    }

    @Test
    void getTrainingListByTraineeUsernameAndTrainerName() {
        List<Training> trainingList = trainingService.getTrainingListByTraineeUsernameAndTrainerName("username1", "lastname3");
        Assert.assertEquals(1, trainingList.size());
    }

    @Test
    void getTrainingListByTraineeUsernameAndTrainingType() {
        TrainingType trainingType = trainingTypeService.selectTrainingTypeByName(TrainingTypeName.RUNNING).get();
        List<Training> trainingList = trainingService.getTrainingListByTraineeUsernameAndTrainingType("username1", trainingType);
        Assert.assertEquals(1, trainingList.size());
    }

    @Test
    void getTrainingListByTrainerUsername() {
        List<Training> trainingList = trainingService.getTrainingListByTrainerUsername("username4");
        Assert.assertEquals(2, trainingList.size());
    }

    @Test
    void getTrainingListByTrainerUsernameFromDate() {
        List<Training> trainingList = null;
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/04/2024 18:00:00");
            trainingList = trainingService.getTrainingListByTrainerUsernameFromDate("username4", date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(1, trainingList.size());
    }

    @Test
    void getTrainingListByTrainerUsernameToDate() {
        List<Training> trainingList = null;
        try {
            trainingList = trainingService.getTrainingListByTrainerUsernameToDate("username4", new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse("01/04/2024 18:00:00"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(1, trainingList.size());
    }

    @Test
    void getTrainingListByTrainerUsernameAndTrainerName() {
        List<Training> trainingList = trainingService.getTrainingListByTrainerUsernameAndTraineeName("username4", "lastname1");
        Assert.assertEquals(1, trainingList.size());
    }

    @Test
    void getTrainingListByTrainerUsernameAndTrainingType() {
        TrainingType trainingType = trainingTypeService.selectTrainingTypeByName(TrainingTypeName.BOXING).get();
        List<Training> trainingList = trainingService.getTrainingListByTrainerUsernameAndTrainingType("username4", trainingType);
        Assert.assertEquals(2, trainingList.size());
    }

}