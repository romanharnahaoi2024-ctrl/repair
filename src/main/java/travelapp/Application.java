package travelapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import travelapp.service.PackageService;
import travelapp.storage.PackageRepository;
import travelapp.ui.ConsoleMenu;

public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {

        logger.info("Application started");

        PackageRepository repo = new PackageRepository("data/packages.csv", "data/bookings.csv");
        PackageService service = new PackageService(repo);

        try {
            service.load();
            logger.info("Data loaded successfully");
        } catch (Exception e) {
            logger.error("Failed to load data", e);
        }

        try {
            new ConsoleMenu(service).run();
            logger.info("User session finished");
        } catch (Exception e) {
            logger.error("Critical crash in ConsoleMenu", e);
            // Відправка email відбудеться автоматично через Logback SMTPAppender
        }

        try {
            service.save();
            logger.info("Data saved successfully");
        } catch (Exception e) {
            logger.error("Failed to save data", e);
        }

        logger.info("Application finished");
    }
}
