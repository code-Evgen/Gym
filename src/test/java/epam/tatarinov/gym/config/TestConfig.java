package epam.tatarinov.gym.config;

import epam.tatarinov.gym.DAO.TraineeDAO;
import epam.tatarinov.gym.DAO.TrainerDAO;
import epam.tatarinov.gym.DAO.TrainingDAO;
import epam.tatarinov.gym.Services.TraineeService;
import epam.tatarinov.gym.Services.TrainerService;
import epam.tatarinov.gym.Services.TrainingService;
import epam.tatarinov.gym.Storages.TraineeStorage;
import epam.tatarinov.gym.Storages.TrainerStorage;
import epam.tatarinov.gym.Storages.TrainingStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.mockito.Mockito.mock;


//@Configuration
//@ComponentScan("epam.tatarinov.gym")
//@PropertySource({
//        "classpath:storageLocation.properties",
//        "classpath:database.properties",
//        "classpath:hibernate.properties"
//})
//@EnableTransactionManagement
public class TestConfig {

    @Bean
    public TrainerService trainerService(){
        return new TrainerService();
    }

    @Bean
    public TrainerDAO trainerDAO(){
        return new TrainerDAO();
    }

    @Bean
    public TrainerStorage trainerStorage(){
        return mock(TrainerStorage.class);
    }


    @Bean
    public TraineeService traineeService(){
        return new TraineeService();
    }

    @Bean
    public TraineeDAO traineeDAO(){
        return new TraineeDAO();
    }

    @Bean
    public TraineeStorage traineeStorage(){
        return mock(TraineeStorage.class);
    }

    @Bean
    public TrainingService trainingService(){
        return new TrainingService();
    }

    @Bean
    public TrainingDAO trainingDAO(){
        return new TrainingDAO();
    }

    @Bean
    public TrainingStorage trainingStorage(){
        return mock(TrainingStorage.class);
    }
}
