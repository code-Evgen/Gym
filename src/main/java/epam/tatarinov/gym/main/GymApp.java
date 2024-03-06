package epam.tatarinov.gym.main;

import epam.tatarinov.gym.Services.TraineeService;
import epam.tatarinov.gym.Services.TrainerService;
import epam.tatarinov.gym.Services.TrainingService;
import epam.tatarinov.gym.Services.TrainingTypeService;
import epam.tatarinov.gym.config.SpringConfig;
import epam.tatarinov.gym.models.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

public class GymApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        TraineeService traineeService = context.getBean(TraineeService.class);

    }
}
