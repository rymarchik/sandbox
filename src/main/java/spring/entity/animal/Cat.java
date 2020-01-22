package spring.entity.animal;

import org.springframework.stereotype.Component;

//@Component
public class Cat {

    private String name;

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
