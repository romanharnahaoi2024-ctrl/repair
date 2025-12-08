package travelapp.ui;

import org.junit.jupiter.api.Test;
import travelapp.service.PackageService;
import travelapp.storage.PackageRepository;

import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;

class CommandManagerTest {

    @Test
    void testMenuContainsCommands() throws Exception {
        PackageRepository repo = new PackageRepository(
                Files.createTempFile("p",".csv").toString(),
                Files.createTempFile("b",".csv").toString()
        );
        PackageService service = new PackageService(repo);
        CommandManager cm = new CommandManager(service);

        assertDoesNotThrow(cm::showMenu);
    }
}
