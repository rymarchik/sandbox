package spring.entity.animal;

import org.springframework.stereotype.Component;

@Component
public class Dog {

    private String name = "Барсик";

    public String getName() {
        return name;
    }
}
