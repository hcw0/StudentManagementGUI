package com.example.studentmanagementgui;

import java.sql.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Vector;


public class ControllerDatabase {
    Connection connection;
    PreparedStatement insertStatement;
    Statement retrieveStatement;

    @FXML
    private Button SignOut;

    @FXML
    private Button add;

    @FXML
    private Button remove;
    @FXML
    private TextField name;
    @FXML
    private TextField major;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, Integer> idColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> majorColumn;
    @FXML
    private TableColumn<Student, String> phoneNumberColumn;




    public void updateTable(){
        try{
            ObservableList<Student> observableList = FXCollections.observableArrayList();
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdatabase", "root", "password");
            retrieveStatement = connection.createStatement();
            ResultSet resultSet = retrieveStatement.executeQuery("select * from students");

            while(resultSet.next()){
                Student student = new Student(Integer.parseInt(resultSet.getString("WebID")), resultSet.getString("Name"),
                        resultSet.getString("Major"), resultSet.getString("PhoneNumber"));
                observableList.add(student);
            }
            idColumn.setCellValueFactory(new PropertyValueFactory<>("webID"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));
            phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            studentTable.setItems(observableList);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void addStudent(ActionEvent event) throws SQLException {
        String studentName = name.getText();
        String studentMajor = major.getText();
        String phoneNumberString = phoneNumber.getText();

        if (studentName.length() == 0 || studentMajor.length() == 0 || phoneNumberString.length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Missing fields");
            alert.setContentText("Please provide inputs for all fields");
            alert.setHeaderText("Missing fields");
            alert.showAndWait();
        } else if(isValidNumber(phoneNumberString)){
            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdatabase", "root", "password");
                insertStatement = connection.prepareStatement("INSERT INTO students(Name, Major, PhoneNumber) values(?, ?, ?)");
                insertStatement.setString(1, studentName);
                insertStatement.setString(2, studentMajor);
                insertStatement.setString(3, phoneNumberString);
                insertStatement.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Success!");
                alert.setContentText("Student added successfully");
                alert.setHeaderText("Success!");
                alert.showAndWait();

                name.setText("");
                major.setText("");
                phoneNumber.setText("");
                name.requestFocus();

                updateTable();
            } catch (SQLException e){
                e.printStackTrace();
            }


        }

    }

    private boolean isValidNumber(String s){
        try{
            int n = Integer.parseInt(s);
            if (s.length() == 9){
                return true;
            } else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid phone number");
                alert.setContentText("Please input a 9 digit number");
                alert.setHeaderText("Invalid phone number");
                alert.showAndWait();
                return false;
            }
        } catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid phone number");
            alert.setContentText("Please input numbers only");
            alert.setHeaderText("Invalid phone number");
            alert.showAndWait();
            return false;
        }
    }

    public void removeStudent(ActionEvent event){
        try{
            Student student = studentTable.getSelectionModel().getSelectedItem();
            int id = student.getWebID();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Selection confirmation");
            alert.setContentText("Are you sure you want to delete this student?");
            ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
            ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);
            alert.getButtonTypes().setAll(yesButton, noButton);
            alert.showAndWait().ifPresent(type -> {
                if (type == yesButton) {
                    try {
                        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdatabase", "root", "password");
                        insertStatement = connection.prepareStatement("DELETE FROM students WHERE webid=?");
                        System.out.println("osd");
                        insertStatement.setInt(1, id);
                        insertStatement.executeUpdate();

                        Alert success = new Alert(Alert.AlertType.CONFIRMATION);
                        success.setTitle("Success!");
                        success.setContentText("Student added successfully");
                        success.setHeaderText("Success!");
                        success.showAndWait();

                        name.setText("");
                        major.setText("");
                        phoneNumber.setText("");
                        name.requestFocus();

                        updateTable();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                } else {

                }
            });
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No student selected");
            alert.setContentText("Please select the row of the student to be removed");
            alert.setHeaderText("No student selected");
            alert.showAndWait();
        }

    }


    public void signOut(ActionEvent event) throws IOException {
        SceneController s = new SceneController();
        s.switchToLoginScene(event);
    }





}