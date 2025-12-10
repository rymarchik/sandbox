package core.exercise;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import core.exercise.streamapi.PersonMapper;

class PersonMapperTest {

    private final Collection<String> PROFESSIONS = Set.of("prograMMer", "PaiNter", "eNginEer", "Nurse", "Doctor", "Land " +
        "engineer", "Person12", "   ");

    @Test
    void groupByOccupation() {
        Map<String, Collection<PersonMapper.Person>> actual = PersonMapper.groupByOccupation(PROFESSIONS);

        assertThat(actual)
            .isNotEmpty()
            .hasEntrySatisfying("P",
                value -> assertThat(value).containsExactlyInAnyOrder(new PersonMapper.Person("Programmer"),
                    new PersonMapper.Person("Painter")))
            .hasEntrySatisfying("E",
                value -> assertThat(value).containsExactlyInAnyOrder(new PersonMapper.Person("Engineer")))
            .hasEntrySatisfying("N",
                value -> assertThat(value).containsExactlyInAnyOrder(new PersonMapper.Person("Nurse")))
            .hasEntrySatisfying("D",
                value -> assertThat(value).containsExactlyInAnyOrder(new PersonMapper.Person("Doctor")))
            .hasEntrySatisfying("L",
                value -> assertThat(value).containsExactlyInAnyOrder(new PersonMapper.Person("Land engineer")));
    }
}