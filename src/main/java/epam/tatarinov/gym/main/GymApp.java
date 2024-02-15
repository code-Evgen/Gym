package epam.tatarinov.gym.main;

import epam.tatarinov.gym.Services.TraineeService;
import epam.tatarinov.gym.Services.TrainerService;
import epam.tatarinov.gym.Storages.TraineeStorage;
import epam.tatarinov.gym.Storages.TrainerStorage;
import epam.tatarinov.gym.Storages.TrainingStorage;
import epam.tatarinov.gym.Storages.TrainingTypeStorage;
import epam.tatarinov.gym.config.SpringConfig;
import epam.tatarinov.gym.models.Trainee;
import epam.tatarinov.gym.models.Trainer;
import epam.tatarinov.gym.models.Training;
import epam.tatarinov.gym.models.TrainingType;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class GymApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        TraineeService traineeService = context.getBean(TraineeService.class);
        Trainee trainee = new Trainee();
        trainee.setFirstName("Aaa");
        trainee.setLastName("Bbb");
        traineeService.createTrainee(trainee);


        TrainerService trainerService = context.getBean(TrainerService.class);


        Trainee trainee2 = new Trainee();
        trainee2.setFirstName("Mickey");
        trainee2.setLastName("Mouse");
        traineeService.createTrainee(trainee2);
        System.out.println("----------");
        System.out.println("Select by Username");
        System.out.println(traineeService.selectTraineeByUsername("Mickey.Mouse").get().getUsername());

        Trainer trainer = new Trainer();
        trainer.setFirstName("Arnold");
        trainer.setLastName("Schwarzenegger");
        trainer.setSpecialization("xxx");
        trainerService.createTrainer(trainer);

        Trainer trainer2 = new Trainer();
        trainer2.setFirstName("Arnold");
        trainer2.setLastName("Schwarzenegger");
        trainer2.setSpecialization("running");
        trainerService.createTrainer(trainer);


        System.out.println("----------");
        System.out.println("trainee");

        TraineeStorage traineeStorage = context.getBean(TraineeStorage.class);
        Map<Integer, Trainee> traineeMap = traineeStorage.getTrainees();
        for (Map.Entry<Integer, Trainee> t : traineeMap.entrySet()){
            System.out.println(t.getValue());
        }
        System.out.println("-----------");
        System.out.println("trainer");

        TrainerStorage trainerStorage = context.getBean(TrainerStorage.class);
        Map<Integer, Trainer> trainerMap = trainerStorage.getTrainers();
        for (Map.Entry<Integer, Trainer> t : trainerMap.entrySet()){
            System.out.println(t.getValue());
        }

        System.out.println("-----------");
        System.out.println("training");

        TrainingStorage trainingStorage = context.getBean(TrainingStorage.class);
        Map<Integer, Training> trainingMap = trainingStorage.getTrainings();
        for (Map.Entry<Integer, Training> t : trainingMap.entrySet()){
            System.out.println(t.getValue());
        }

        System.out.println("-----------");
        System.out.println("trainingType");

        TrainingTypeStorage trainingTypeStorage = context.getBean(TrainingTypeStorage.class);
        Map<Integer, TrainingType> trainingTypeMap = trainingTypeStorage.getTrainingTypes();
        for (Map.Entry<Integer, TrainingType> t : trainingTypeMap.entrySet()){
            System.out.println(t.getValue());
        }

    }
}
