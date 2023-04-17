package core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person implements Comparable<Person> {

    private String name;

    private int age;

    @Override
    public int compareTo(Person p) {
        if (this.age == p.age) {
            return 0;
        }
        return this.age > p.age ? -1 : 1;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.age;
    }
}
