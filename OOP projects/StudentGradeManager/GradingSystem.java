package assignment7;

public class GradingSystem {
    /**
     * The GradingSystem class represents a grading system with a name and a list of students.
     */

    // Default constructor, sets all variables to null
    public GradingSystem() {
        name = null;
        students = null;
        StudentNum = 0;
    }

    // Constructor with given variables
    public GradingSystem(String name, int studentCount) {
        this.name = name;
        StudentNum = 0;
        if (studentCount >= 0) { // Only a positive number is valid
            this.students = new Student[studentCount]; // Initialize an empty students array of size studentCount
        } else {
            this.students = null; // If studentCount is 0 or below, there are no students
        }
    }

    // Copy constructor
    public GradingSystem(GradingSystem other) {
        this();
        if (other != null) {
            this.name = other.name; // Copy the name of the other system
            StudentNum = other.StudentNum;
            if (other.students == null) {
                this.students = null; // If the other students array is null, copy it as null
            } else {
                // Deep copy of the other grading system's students array
                this.students = new Student[other.getMaxStudents()];
                for (int i = 0; i < getMaxStudents(); i++) {
                    if (other.students[i] != null) {
                        // Use the copy method to add a new student, for every type of student it uses the copy method of its own.
                        students[i] = other.students[i].copy();
                        StudentNum++;
                    }
                }
            }
        }
    }

    // Getter for the name
    public String getName() {
        return name; // If name is null it returns null, else returns the String value of the name
    }

    // Setter for the name
    public void setName(String name) {
        if (name != null)
            this.name = name; // Set name if given a non-null value
    }

    // Getter for the students array
    public Student[] getStudents() {
        return students; // Returns the exact students of the GradingSystem (not a copy)
    }

    // Method to add a student to the students array
    public void addStudent(Student student) {
        if (student != null) { // Do not add a null student
            for (int i = 0; i < getMaxStudents(); i++) {
                if (students[i] == null) {
                    // Create a new student by using the copy constructor and add
                    students[i] = student.copy();
                    StudentNum++;
                    break; // If a student has been added to the array, exit the loop
                }
            }
        }
    }

    // Method to calculate the average weighted score of all students
    public double getAverage() {
        try {
            double average = 0;
            if (students != null && StudentNum != 0) { // Check if students array is not null and there is at least one student
                double sum = 0; // Sum of weighted averages
                for (int i = 0; i < getMaxStudents(); i++) {
                    // If student is not null and has at least one course
                    if (students[i] != null) {
                        // Calculate the sum of weighted averages of all students
                        sum += students[i].getWeightedAverage();
                    }
                }
                average = (sum / StudentNum); // Calculate the average of weighted averages
                if (average > 100)
                    return 100; // Ensure the average does not exceed 100
                else if (average < 0)
                    return 0; // Ensure the average is not below 0
                else
                    return average; // Return the calculated average
            }
            return -1; // If there are no students in the grading system, return -1
        } catch (AverageCalcException ex) {
            return -1; // If there is an exception in calculating the average, return -1
        }
    }

    // Helper method that returns the maximum number of students (students array length)
    public int getMaxStudents() {
        if (students != null)
            return students.length;
        return 0; // If there are no students, return 0
    }

    // Declaration of variables of class GradingSystem
    private String name; // Name of the system
    private Student[] students; // Array of students
    private int StudentNum; // Number of students currently in the array
}
