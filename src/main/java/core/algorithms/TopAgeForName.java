package core.algorithms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

import core.model.Person;

/**
 * SELECT name, age from Person GROUP BY name HAVING MAX(age)
 */
public class TopAgeForName {

    /**
     * My dilettantish solution using Map
     */
    public static List<Person> getTopAgeForNameUsingMap(List<Person> persons) {
        Map<String, Integer> resultMap = new HashMap<>();
        for (Person p : persons) {
            if (!resultMap.containsKey(p.getName()) || resultMap.get(p.getName()) < p.getAge()) {
                resultMap.put(p.getName(), p.getAge());
            }
        }
        return resultMap.entrySet().stream()
                .map(entry -> new Person(entry.getKey(), entry.getValue()))
                .toList();
    }

    /**
     * My dilettantish solution using Set
     */
    public static List<Person> getTopAgeForNameUsingSet(List<Person> persons) {
        Set<Person> resultSet = new HashSet<>();
        Set<String> uniqueNames = new HashSet<>();
        for (Person person : persons) {
            if (uniqueNames.contains(person.getName())) {
                continue;
            }
            Person oldestPerson = persons.stream()
                    .filter(p -> p.getName().equals(person.getName()))
                    .sorted()
                    .findFirst()
                    .orElseThrow();
            resultSet.add(oldestPerson);
            uniqueNames.add(oldestPerson.getName());
        }
        return new ArrayList<>(resultSet);
    }

    /**
     * Solutions suggested by ChatGPT
     */
    public static List<Person> getTopAgeForNameByChatGPT(List<Person> persons) {
        List<Person> resultListOption1 = persons.stream()
                .collect(Collectors.toMap(Person::getName, Person::getAge, Integer::max))
                .entrySet().stream()
                .map(entry -> new Person(entry.getKey(), entry.getValue()))
                .toList();

        List<Person> resultListOption2 = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparingInt(Person::getAge)), Optional::get)))
                .values().stream()
                .toList();

        // My favourite
        List<Person> resultListOption3 = persons.stream()
                .collect(Collectors.toMap(Person::getName, Function.identity(),
                        BinaryOperator.maxBy(Comparator.comparingInt(Person::getAge))))
                .values().stream()
                .toList();

        return resultListOption3;
    }

    public static void testTopAgeForName() {
        List<Person> persons = List.of(
            new Person("alex", 10),
            new Person("dima", 13),
            new Person("alex", 17),
            new Person("leha", 22),
            new Person("dima", 15),
            new Person("alex", 9),
            new Person("alex", 20),
            new Person("dima", 2));
        List<Person> topAgeForName = getTopAgeForNameByChatGPT(persons);
        for (Person p : topAgeForName) {
            System.out.println(p);
        }
    }
}
