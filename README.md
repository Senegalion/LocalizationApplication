# Localization Application with MariaDB Database

This project is a simple JavaFX application that allows managing translations in different languages. The data is stored in a MariaDB database, and the application uses Docker for easy environment setup.

## Technologies

* **JavaFX:** For creating the user interface.
* **JDBC:** For communication with the MariaDB database.
* **MariaDB:** Database for storing translations.
* **Docker:** For containerizing the database and simplifying the launch process.

## Requirements

* **Docker:** Must be installed on your system.
* **Java Development Kit (JDK):** Version 8 or newer.
* **Maven (optional):** For managing dependencies and building the project.

## Project Structure

* `src/main/java/org/example/localizationapplication/`: Contains the Java source code of the application.
    * `HelloController.java`: JavaFX controller handling the application logic.
    * `JobTitle.java`: Class representing a job title.
    * `LocalizationDatabase.java`: Class handling database connection and data operations.
* `src/main/resources/org/example/localizationapplication/hello-view.fxml`: FXML file defining the user interface.
* `src/main/resources/messages.properties`: Translation files for different languages.
* `docker-compose.yml`: Docker configuration file for running the database.
* `pom.xml`: Maven configuration file (if using Maven).
* `README.md`: This file.

## Running the Application

1.  **Start Docker:** Ensure Docker is running on your system.
2.  **Run the MariaDB database:**
    * Open a terminal and navigate to the project directory.
    * Run the database using the command:
        ```bash
        docker-compose up -d
        ```
        This command will start the MariaDB container with the data defined in `docker-compose.yml`.
3.  **Run the JavaFX application:**
    * If you are using Maven, run the application using the command:
        ```bash
        mvn javafx:run
        ```
    * If you are not using Maven, compile and run the application using your IDE (e.g., IntelliJ IDEA, Eclipse).
4.  **Database connection:** The application will automatically connect to the MariaDB database running in the Docker container.

## Database Configuration

* The MariaDB database is configured in the `docker-compose.yml` file.
* The default root password is `password`.
* The default database name is `localization_db`.

## Database Structure

The application expects a table named `translations` with the following columns:

* `Key_name`: Translation key (e.g., "job_title_manager").
* `Language_code`: Language code (e.g., "en", "fr", "es", "zh").
* `translation_text`: Translation text.

## Additional Information

* The application allows adding and updating translations for different languages.
* The user interface allows selecting a language and displaying translations.
* All changes to translations are saved in the MariaDB database.

## Cleaning Up

To stop and remove the Docker container, use the command:

```bash
docker-compose down
