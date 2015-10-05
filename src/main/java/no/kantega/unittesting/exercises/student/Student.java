package no.kantega.unittesting.exercises.student;

public class Student {

    private Long id;
    private String userName;
    private String givenName;
    private String surName;

    public Student() {
    }

    public Student(String userName, String givenName, String surName) {
        this.userName = userName;
        this.givenName = givenName;
        this.surName = surName;
    }

    public Student(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

}
