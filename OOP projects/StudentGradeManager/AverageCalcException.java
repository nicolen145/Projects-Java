package assignment7;

public class AverageCalcException extends RuntimeException {

    // Field to store the name of the student for which the exception occurred
    private String studentName;

    // Constructor with only student name
    public AverageCalcException(String studentName) {
        this.studentName = studentName;
    }

    // Getter method to retrieve the name of the student for which the exception occurred
    public String getStudentName() {
        return studentName;
    }
}