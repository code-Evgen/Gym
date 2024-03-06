//package epam.tatarinov.gym.util;
//
//import epam.tatarinov.gym.DAO.TraineeDAO;
//import epam.tatarinov.gym.DAO.TrainerDAO;
//import epam.tatarinov.gym.DAO.TrainingDAO;
//import epam.tatarinov.gym.DAO.TrainingTypeDAO;
//import epam.tatarinov.gym.Services.TrainingTypeService;
//import epam.tatarinov.gym.models.Trainee;
//import epam.tatarinov.gym.models.Trainer;
//import epam.tatarinov.gym.models.Training;
//import epam.tatarinov.gym.models.TrainingType;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.context.ApplicationContext;
//import org.springframework.core.env.Environment;
//
//import java.io.*;
//import java.time.LocalDate;
//
//
//public class StorageInitializationBeanPostProcessor implements BeanPostProcessor {
//    @Autowired
//    private Environment environment;
//
//    private ApplicationContext applicationContext;
//
//    @Autowired
//    public void setApplicationContext(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }
//
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        StorageInitialization annotation = bean.getClass().getAnnotation(StorageInitialization.class);
//        if (annotation != null){
//            String source = annotation.CLASS_TYPE().toString().toLowerCase();
//            String sourceFile = environment.getProperty(source);
//
//            File file = new File(sourceFile);
//
//
//            try {
//                BufferedReader br = new BufferedReader(new FileReader(file));
//                String currentString;
//                while ((currentString = br.readLine()) != null){
//                    String[] currentStringMass = currentString.split(",");
//
//                    switch (source){
//                        case "trainee":{
//                            Trainee trainee = new Trainee();
//                            trainee.setFirstName(currentStringMass[0]);
//                            trainee.setLastName(currentStringMass[1]);
//                            trainee.setDateOfBirth(LocalDate.parse(currentStringMass[2]));
//                            trainee.setAddress(currentStringMass[3]);
//                            trainee.setUsername(currentStringMass[4]);
//                            trainee.setPassword(currentStringMass[5]);
//
//                            if (bean instanceof TraineeDAO) {
//                                TraineeDAO traineeDAO = (TraineeDAO) bean;
//                                traineeDAO.createTrainee(trainee);
//                            }
//                            break;
//                        }
//                        case "trainer":{
//                            Trainer trainer = new Trainer();
//                            trainer.setFirstName(currentStringMass[0]);
//                            trainer.setLastName(currentStringMass[1]);
//                            trainer.setSpecialization(currentStringMass[2]);
//                            trainer.setUsername(currentStringMass[3]);
//                            trainer.setPassword(currentStringMass[4]);
//
//                            if (bean instanceof TrainerDAO) {
//                                TrainerDAO trainerDAO = (TrainerDAO) bean;
//                                trainerDAO.createTrainer(trainer);
//                            }
//                            break;
//                        }
//                        case "training":{
//                            Training training = new Training();
//                            training.setTraineeId(Integer.parseInt(currentStringMass[0]));
//                            training.setTrainerId(Integer.parseInt(currentStringMass[1]));
//                            TrainingTypeService trainingTypeService = (TrainingTypeService) applicationContext.getBean("trainingTypeService");
//                            TrainingType trainingType = new TrainingType();
//                            trainingType.setTrainingTypeName(currentStringMass[2]);
//                            int id = trainingTypeService.createTrainingType(trainingType);
//                            training.setTrainingType(trainingTypeService.selectTrainingType(id).get());
//                            training.setTrainingDate(LocalDate.parse(currentStringMass[3]));
//                            training.setTrainingDuration(Integer.parseInt(currentStringMass[4]));
//
//                            if (bean instanceof TrainingDAO) {
//                                TrainingDAO trainingDAO = (TrainingDAO) bean;
//                                trainingDAO.createTraining(training);
//                            }
//                            break;
//                        }
//                        case "training_type":{
//                            TrainingType trainingType = new TrainingType();
//                            trainingType.setTrainingTypeName(currentStringMass[0]);
//
//                            if (bean instanceof TrainingTypeDAO) {
//                                TrainingTypeDAO trainingTypeDAO = (TrainingTypeDAO) bean;
//                                trainingTypeDAO.createTrainingType(trainingType);
//                            }
//                            break;
//                        }
//                    }
//
//                }
//
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//
//
//        }
//        return bean;
//    }
//
//}
