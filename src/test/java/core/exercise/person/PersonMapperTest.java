package core.exercise.person;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import core.exercise.personprofession.Person;
import core.exercise.personprofession.PersonMapper;

class PersonMapperTest {

    private final Collection<String> PROFESSIONS = Set.of("prograMMer", "PaiNter", "eNginEer", "Nurse", "Doctor", "Land " +
        "engineer", "Person12", "   ");

    @Test
    void groupByOccupation() {
        Map<String, Collection<Person>> actual = PersonMapper.groupByOccupation(PROFESSIONS);

        assertThat(actual)
            .isNotEmpty()
            .hasEntrySatisfying("P", value -> assertThat(value).containsExactlyInAnyOrder(new Person("Programmer"),
                new Person("Painter")))
            .hasEntrySatisfying("E", value -> assertThat(value).containsExactlyInAnyOrder(new Person("Engineer")))
            .hasEntrySatisfying("N", value -> assertThat(value).containsExactlyInAnyOrder(new Person("Nurse")))
            .hasEntrySatisfying("D", value -> assertThat(value).containsExactlyInAnyOrder(new Person("Doctor")))
            .hasEntrySatisfying("L", value -> assertThat(value).containsExactlyInAnyOrder(new Person("Land engineer")));
    }
}