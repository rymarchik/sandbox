package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.entity.animal.Cat;
import spring.entity.animal.Dog;
import spring.entity.animal.Parrot;

@Configuration
public class MyConfig {

    @Bean
    public Cat getCat() {
        return new Cat("Kisa");
    }

    @Bean
    public Cat getCatKiller(Parrot parrot) {
        return new Cat(parrot.getName() + "-killer");
    }

    @Bean
    public Dog getDog() {
        return new Dog();
    }

    @Bean("dodo")
    public Parrot weNeedMoreParrots() {
        return new Parrot("Dodo");
    }
}
