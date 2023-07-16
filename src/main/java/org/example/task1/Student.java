package org.example.task1;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable {
    private final String surname;
    private final String name;
    private final String lastName;
    private final Date dateOfBirth;
    private int grade;

    public Student(String surname, String name, String lastName, Date dateOfBirth, int grade) {
        this.surname = surname;
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.grade = grade;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", grade=" + grade +
                '}';
    }
}
