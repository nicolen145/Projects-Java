package assignment7;

import java.io.*;
import java.util.Objects;

/**
 * The CourseReport class represents a report of a course, including its name,
 * the points it is worth, and the grade received.
 */
public class CourseReport implements Comparable<CourseReport>,Serializable {
    private static final long serialVersionUId = 1L;
    private String name; // name of course
    private int points; // points of course
    private double grade; // grade of course

    // Default constructor, sets all variables to null and zeros
    public CourseReport() {
        this.name = null;
        this.points = 0;
        this.grade = 0;
    }

    // Constructor with given variables
    public CourseReport(String name, int points, double grade) {
        this.name = name;
        if (points > 0)
            this.points = points;
        if (grade < 0)
            this.grade = 0;
        else if (grade > 100)
            this.grade = 100;
        else
            this.grade = grade;
    }

    // Copy constructor
    public CourseReport(CourseReport other) {
        this();
        if (other != null) {
            this.name = other.name; // copy name of other course
            this.points = other.points; // copy points of other course
            this.grade = other.grade; // copy grade of other course
        }
    }

    // Constructor that reads from a text file
    public CourseReport(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null) {
                line = line.substring(1, line.length() - 1).trim();//remove brackets and trim whitespace
                String[] parts = line.split(", ");
                if (Objects.equals(parts[0], "null"))
                    this.name = null;
                else
                    this.name = parts[0];
                this.points = Integer.parseInt(parts[1]);
                this.grade = Double.parseDouble(parts[2]);
            } else {
                throw new IOException("File is empty or in an invalid format.");
            }
        }
    }

    // Getter for grade
    public double getGrade() {
        return grade;
    }

    // Setter for grade, must be between 0 and 100
    public void setGrade(double grade) {
        if (grade <= 0)
            this.grade = 0; // if negative set to 0
        else if (grade >= 100)
            this.grade = 100; // if over 100 set to 100
        else // if valid
            this.grade = grade;
    }

    // Getter for course name
    public String getName() {
        return name; // if name is null the method returns null else a String value with the name
    }

    // Setter for course name
    public void setName(String name) {
        if (name != null) // set new name if not null
            this.name = name;
    }

    // Getter for course points
    public int getPoints() {
        return points; // return int value of points
    }

    // Setter for course points
    public void setPoints(int points) {
        if (points > 0) // set points only if given positive value
            this.points = points;
    }

    // Helper method for weighted average calculation in Student class
    // Returns the weight of the grade (grade multiplied by points)
    public double GetWeightedGrade() {
        return grade * points;
    }

    // Override equals method of Object class to compare two CourseReport objects
    @Override
    public boolean equals(Object o) {
        if (o instanceof CourseReport) { // checks if the other object is an instance of CourseReport
            if (this.name != null && ((CourseReport) o).name != null)
                return (Objects.equals(this.name, ((CourseReport) o).name) && this.points == ((CourseReport) o).points && this.grade == ((CourseReport) o).grade);
            else if (this.name == null && ((CourseReport) o).name == null)
                return (this.points == ((CourseReport) o).points && this.grade == ((CourseReport) o).grade);
            else
                return false;
        }
        return false;
    }

    // Override toString method to represent the CourseReport as a string
    @Override
    public String toString() {
        // return this string format: "[<name>, <points>, <grade>]"
        return "[" + name + ", " + points + ", " + grade + "]";
    }

    // Override the compareTo method to compare two CourseReport objects
    @Override
    public int compareTo(CourseReport o) {
        if (o != null) {
            if (this.equals(o))
                return 0;
            double difference = this.grade - o.grade;
            if (difference != 0)
                return (int) difference;
            difference = this.points - o.points;
            if (difference != 0)
                return (int) difference;
            if (this.name == null && o.name != null)
                return -1;
            if (o.name == null)
                return 1;
            else
                return this.name.compareTo(o.name);
        }
        return 1;
    }

    // Method to save the CourseReport to a text file
    public void saveToTextFile(File file) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(this.toString());
        }
    }
}
