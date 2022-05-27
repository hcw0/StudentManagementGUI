package com.example.studentmanagementgui;

/**
 * This class represents a Student that has a webID
 * and a list of courses
 */

public class Student{
    private int webID;
    private String major;
    private String phoneNumber;
    private String name;

    /**
     * Default constructor used to create a Student object
     */
    public Student(){

    }

    public Student(int webID, String name, String major, String phoneNumber){
        this.webID = webID;
        this.major = major;
        this.phoneNumber = phoneNumber;
        this.name = name;

    }

    /**
     * Gets the webID of the Student
     * @return
     * A string representing the webID of the Student
     */
    public int getWebID(){
        return webID;
    }
    public String getMajor(){
        return major;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public String getName(){
        return name;
    }


    /**
     * Sets the webID of the Student to a specific webID
     * @param webID
     * The webID to be set to the Student
     */
    public void setWebID(int webID) {
        this.webID = webID;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setName(String name) {
        this.name = name;
    }



}
