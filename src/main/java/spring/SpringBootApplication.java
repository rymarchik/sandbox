package spring;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.config.MyConfig;
import spring.entity.animal.Cat;
import spring.entity.animal.Dog;
import spring.entity.animal.Parrot;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SpringBootApplication {

    public static void main(String[] args) {
        ApplicationContext appContext = SpringApplication.run(SpringBootApplication.class, args);

        Arrays.stream(appContext.getBeanDefinitionNames())
            .sorted()
            .forEach(System.out::println);

        ApplicationContext myContext = new AnnotationConfigApplicationContext(MyConfig.class);

        Arrays.stream(myContext.getBeanDefinitionNames())
            .sorted()
            .forEach(System.out::println);

        Dog dog = (Dog) myContext.getBean("getDog");
        Dog sameDog = myContext.getBean(Dog.class);
        Parrot dodoParrot = myContext.getBean(Parrot.class); //only 1 visible in this context
        Parrot keshaParrot = appContext.getBean("parrot", Parrot.class); //case-sensitive component name
        Cat kisa = myContext.getBean("getCat", Cat.class);
        Cat dodoKiller = myContext.getBean("getCatKiller", Cat.class);
        Cat keshaKiller = appContext.getBean("getCatKiller", Cat.class);
    }

}
