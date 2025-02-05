package core.exercise.personprofession;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class PersonMapper {

    public static Map<String, Collection<Person>> groupByOccupation(final Collection<String> professions) {
        return professions.stream()
            .filter(p -> !p.isBlank() && !p.matches(".*\\d.*"))
            .map(p -> p.substring(0, 1).toUpperCase() + p.substring(1).toLowerCase())
            .distinct()
            .map(Person::new)
            .collect(Collectors.groupingBy(person -> person.profession().substring(0, 1),
                Collectors.toCollection(ArrayList::new)));
    }
}
