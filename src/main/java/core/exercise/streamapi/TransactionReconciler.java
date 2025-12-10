package core.exercise.streamapi;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.Getter;

/**
 * Task from AgileEngine
 */
public class TransactionReconciler {

    Stream<PendingTransaction> reconcile(Stream<PendingTransaction> pending, Stream<Stream<ProcessedTransaction>> processed) {
        if (pending == null || processed == null) {
            return Stream.empty();
        }

        Set<Long> processedIds = processed
            .filter(Objects::nonNull)
            .flatMap(Function.identity())
            .filter(Objects::nonNull)
            .filter(t -> t.getId() != null && !t.getId().trim().isEmpty())
            .filter(t -> Optional.ofNullable(t.getStatus())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .filter("DONE"::equalsIgnoreCase)
                .isPresent())
            .map(ProcessedTransaction::getId)
            .map(Long::parseLong)
            .collect(Collectors.toSet());

        return pending.filter(t -> processedIds.contains(t.getId()));
    }

    @Getter
    private static class PendingTransaction {
        private Long id;
    }

    @Getter
    private static class ProcessedTransaction {
        private String id;
        private Optional<String> status;
    }
}
