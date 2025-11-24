package com.example._207059_cv;

import javafx.scene.image.Image;

import java.io.File;
import java.sql.*;

import java.util.List;
import java.util.ArrayList;

public class Databasehandler {

    private static final String DB_URL = "jdbc:sqlite:cv_data.db";
    private static Databasehandler handler = null;

    private Databasehandler() {
        createTable();
    }

    public static Databasehandler getInstance() {
        if (handler == null) {
            handler = new Databasehandler();
        }
        return handler;
    }
    private void createTable() {
        String TABLE_CV = "CREATE TABLE IF NOT EXISTS cv (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "fullName TEXT UNIQUE," +
                "email TEXT," +
                "phone TEXT," +
                "address TEXT," +
                "skills TEXT," +
                "education TEXT," +
                "experience TEXT," +
                "photoPath TEXT" +
                ");";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(TABLE_CV);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertCV(Getter_Setter cv) {
        Getter_Setter existing = getCVByName(cv.getFullName());
        if (existing != null) {
            cv.setId(existing.getId());
            return updateCV(existing.getId(), cv);
        }
        String sql = "INSERT INTO cv(fullName,email,phone,address,skills,education,experience,photoPath) " +
                "VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cv.getFullName());
            pstmt.setString(2, cv.getEmail());
            pstmt.setString(3, cv.getPhone());
            pstmt.setString(4, cv.getAddress());
            pstmt.setString(5, cv.getSkills());
            pstmt.setString(6, cv.getEducation());
            pstmt.setString(7, cv.getExperience());
            String path = null;
            if (cv.getApplicantPhoto() != null) {
                path = cv.getApplicantPhoto().getUrl().replace("file:", "");
            }
            pstmt.setString(8, path);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet keys = pstmt.getGeneratedKeys();
                if (keys.next()) {
                    cv.setId(keys.getInt(1));
                }
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;


    }
    public Getter_Setter getCV(int id) {
        String sql = "SELECT * FROM cv WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Getter_Setter cv = new Getter_Setter();
                cv.setId(rs.getInt("id"));  // important: track the CV's ID
                cv.setFullName(rs.getString("fullName"));
                cv.setEmail(rs.getString("email"));
                cv.setPhone(rs.getString("phone"));
                cv.setAddress(rs.getString("address"));
                cv.setSkills(rs.getString("skills"));
                cv.setEducation(rs.getString("education"));
                cv.setExperience(rs.getString("experience"));

                String path = rs.getString("photoPath");
                if (path != null && !path.isEmpty()) {
                    File file = new File(path);
                    if (file.exists()) {
                        cv.setApplicantPhoto(new Image(file.toURI().toString()));
                    }
                }

                return cv;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public boolean updateCV(int id, Getter_Setter cv) {
        String sql = "UPDATE cv SET fullName=?, email=?, phone=?, address=?, skills=?, education=?, experience=?, photoPath=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cv.getFullName());
            pstmt.setString(2, cv.getEmail());
            pstmt.setString(3, cv.getPhone());
            pstmt.setString(4, cv.getAddress());
            pstmt.setString(5, cv.getSkills());
            pstmt.setString(6, cv.getEducation());
            pstmt.setString(7, cv.getExperience());

            String path = null;
            if (cv.getApplicantPhoto() != null) {
                path = cv.getApplicantPhoto().getUrl().replace("file:", "");
            }
            pstmt.setString(8, path);

            pstmt.setInt(9, id);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Getter_Setter getCVByName(String fullName) {
        String sql = "SELECT * FROM cv WHERE fullName = ? LIMIT 1";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, fullName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Getter_Setter cv = new Getter_Setter();
                cv.setId(rs.getInt("id"));
                cv.setFullName(rs.getString("fullName"));
                cv.setEmail(rs.getString("email"));
                cv.setPhone(rs.getString("phone"));
                cv.setAddress(rs.getString("address"));
                cv.setSkills(rs.getString("skills"));
                cv.setEducation(rs.getString("education"));
                cv.setExperience(rs.getString("experience"));

                String path = rs.getString("photoPath");
                if (path != null && !path.isEmpty()) {
                    File file = new File(path);
                    if (file.exists()) {
                        cv.setApplicantPhoto(new Image(file.toURI().toString()));
                    }
                }

                return cv;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<String> getNamesByPartial(String partial) {
        List<String> result = new ArrayList<>();
        String sql = "SELECT DISTINCT fullName FROM cv WHERE fullName LIKE ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + partial + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                result.add(rs.getString("fullName"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
    public List<String> getNamesStartingWith(String prefix) {
        return getNamesByPartial(prefix);
    }




}
