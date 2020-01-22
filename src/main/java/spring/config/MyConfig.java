package spring.config;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.entity.animal.Cat;
import spring.entity.animal.Dog;
import spring.entity.animal.Parrot;
import spring.entity.weekday.Monday;
import spring.entity.weekday.Sunday;
import spring.entity.weekday.Wednesday;
import spring.entity.weekday.WeekDay;

@Configuration
//@ComponentScan("spring.entity.animal")
public class MyConfig {

    @Bean
    public Cat getCat() {
        return new Cat("Kisa");
    }

    @Bean
    public Cat getCat(Parrot parrot) {
        return new Cat(parrot.getName() + "-killer");
    }

    @Bean
    public Dog getDog() {
        return new Dog();
    }

    @Bean("kesha")
    public Parrot weNeedMoreParrots() {
        return new Parrot();
    }

    @Bean
    public WeekDay getDay() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();

        switch (dayOfWeek) {
            case MONDAY:
                return new Monday();
            case WEDNESDAY:
                return new Wednesday();
            default:
                return new Sunday();
        }
    }
}
