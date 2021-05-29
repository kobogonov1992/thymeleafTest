package kg.megacom.thymeleaf;

import kg.megacom.thymeleaf.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class Config {

    @Bean
    public List<User> getUsers() {
        return new ArrayList<>();
    }

    @Bean
    public Long getUserId() {
        return  ThreadLocalRandom.current().nextLong(100);
    }

}
