package org.example.localizationapplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalizationDatabase {
    private Connection connection;
    private final String url = "jdbc:mariadb://localhost:3306/localization_db";
    private final String user = "root";
    private final String password = "password";

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
    }

    public List<String> getJobTitles(String languageCode) throws SQLException {
        List<String> titles = new ArrayList<>();
        String query = "SELECT translation_text FROM translations WHERE Language_code = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, languageCode);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                titles.add(resultSet.getString("translation_text"));
            }
        }
        return titles;
    }

    public void addTranslation(String keyName, String translation, String languageCode) throws SQLException {
        String query = "INSERT INTO translations (Key_name, Language_code, translation_text) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, keyName);
            preparedStatement.setString(2, languageCode);
            preparedStatement.setString(3, translation);
            preparedStatement.executeUpdate();
        }
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
