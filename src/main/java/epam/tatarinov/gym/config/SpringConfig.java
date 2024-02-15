package epam.tatarinov.gym.config;

import epam.tatarinov.gym.util.StorageInitializationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("epam.tatarinov.gym")
@PropertySource("classpath:storageLocation.properties")
public class SpringConfig {

    @Bean
    StorageInitializationBeanPostProcessor storageInitializationBeanPostProcessor(){
        return new StorageInitializationBeanPostProcessor();
    }
}
