package spring.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.config.MyConfig;
import spring.entity.animal.Cat;
import spring.entity.animal.Dog;
import spring.entity.animal.Parrot;
import spring.entity.weekday.WeekDay;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Cat cat = context.getBean(Cat.class);
        Dog dog = (Dog) context.getBean("getDog");
        Parrot parrot = context.getBean("kesha", Parrot.class);
        WeekDay weekDay = context.getBean(WeekDay.class);

        System.out.println(cat.getName());
        System.out.println(dog.getName());
        System.out.println(parrot.getName());
        System.out.println("It's " + weekDay.getWeekDayName() + " today!");
    }
}
