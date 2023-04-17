package core.support;

import core.model.saver.DatabaseSaver;
import core.model.saver.FileSaver;
import core.model.saver.Saver;
import core.model.saver.ServerSaver;

import java.util.Map;
import java.util.function.Supplier;

public class SaverFactory {

    public static Saver createSaver(SaverType saverType) {
        Map<SaverType, Supplier<Saver>> map = Map.of(
                SaverType.DATABASE, DatabaseSaver::new,
                SaverType.FILE, FileSaver::new,
                SaverType.SERVER, ServerSaver::new);
        return map.getOrDefault(saverType, () -> {
            throw new IllegalArgumentException("Unknown instruction: " + saverType);
        }).get();
    }

    public enum SaverType {
        DATABASE,
        FILE,
        SERVER
    }
}
