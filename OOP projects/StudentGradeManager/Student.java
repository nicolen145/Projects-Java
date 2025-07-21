package assignment7;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * The Student class represents a student with a name, an ID, and an array of course reports.
 * It provides methods to add courses, calculate weighted averages, and compare students.
 */
public class Student implements Iterable<CourseReport>, Serializable {

    private static final long serialVersionUId = 1L;
    private String name; // Name of the student
    private transient int id; // ID of the student
    private CourseReport[] courseReports; // Array of course reports
    private int CourseNum; // Number of courses
    private int pointSum; // Sum of points

    // Default constructor
    public Student() {
        this.name = null;
        this.courseReports = null;
        this.id = 0;
        this.pointSum = 0;
        this.CourseNum = 0;
    }

    // Constructor with given variables
    public Student(String name, int id, int numOfCourses) {
        this.name = name;
        this.id = id;
        this.CourseNum = 0;
        this.pointSum = 0;
        if (numOfCourses >= 0) {
            this.courseReports = new CourseReport[numOfCourses];
        } else {
            this.courseReports = new CourseReport[0];
        }
    }

    // Copy constructor
    public Student(Student other) {
        this();
        if (other != null) {
            this.CourseNum = other.CourseNum;
            this.pointSum = other.pointSum;
            this.name = other.name;
            this.id = other.id;
            if (other.courseReports == null) {
                this.courseReports = null;
            } else {
                this.courseReports = new CourseReport[other.getMaxNumOfCourses()];
                for (int i = 0; i < getMaxNumOfCourses(); i++) {
                    this.courseReports[i] = new CourseReport(other.courseReports[i]);
                }
            }
        }
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Setter for ID
    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    // Getter for course reports array
    public CourseReport[] getCourseReports() {
        return courseReports;
    }

    // Method to add a course report to the student
    public void addCourse(CourseReport courseReport) {
        if (courseReport != null) {
            for (int i = 0; i < getMaxNumOfCourses(); i++) {
                if (courseReports[i] == null) {
                    courseReports[i] = new CourseReport(courseReport);
                    CourseNum++;
                    pointSum += courseReports[i].getPoints();
                    break;
                }
            }
        }
    }

    // Method to calculate the weighted average grade of the student
    public double getWeightedAverage() {
        double average;
        if (this.courseReports != null && CourseNum != 0) {
            double weightedGrades = 0;
            for (int i = 0; i < getMaxNumOfCourses(); i++) {
                if (this.courseReports[i] != null) {
                    weightedGrades += this.courseReports[i].GetWeightedGrade();
                }
            }
            if (getNumOfPoints() != 0) {
                average = (weightedGrades / getNumOfPoints());
                if (average > 100) {
                    return 100;
                } else if (average < 0) {
                    return 0;
                } else {
                    return average;
                }
            } else {
                throw new AverageCalcException(getName());
            }
        }
        throw new AverageCalcException(getName());
    }

    // Helper method to get the total number of points from all courses
    public int getNumOfPoints() {
        int points = 0;
        for (int i = 0; i < CourseNum; i++) {
            points += this.courseReports[i].getPoints();
        }
        return points;
    }

    // Helper method to get the maximum number of courses the student can take
    public int getMaxNumOfCourses() {
        if (courseReports != null) {
            return courseReports.length;
        }
        return 0;
    }

    // Override equals method to compare two students
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            return Objects.equals(this.name, ((Student) obj).name) && this.id == ((Student) obj).id && CourseReportEquals(((Student) obj));
        } else {
            return false;
        }
    }

    // Helper method to check equality of course reports between two students
    private boolean CourseReportEquals(Student other) {
        if (this.CourseNum != other.CourseNum) {
            return false;
        } else if (this.CourseNum == 0) {
            return true;
        } else {
            return CourseReportsInOther(other.courseReports) && other.CourseReportsInOther(courseReports);
        }
    }

    // Helper method to check if course reports are present in the other student's course reports
    private boolean CourseReportsInOther(CourseReport[] other) {
        for (int i = 0; i < getNumberOfCourses(); i++) {
            if (courseReports[i] != null) {
                boolean found = false;
                for (int j = 0; j < other.length; j++) {
                    if (courseReports[i].equals(other[j])) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    return false;
                }
            }
        }
        return true;
    }

    // Factory method to create a copy of the student
    public Student copy() {
        return new Student(this);
    }

    // Override toString method to represent the student as a string
    @Override
    public String toString() {
        String student_str = name + ": " + id + " [";
        if (CourseNum == 0) {
            student_str += "]";
        } else {
            for (int i = 0; i < CourseNum - 1; i++) {
                student_str += courseReports[i].toString() + ", ";
            }
            student_str += courseReports[CourseNum - 1].toString() + "]";
        }
        return student_str;
    }

    // Helper method to get the number of courses
    public int getNumberOfCourses() {
        return CourseNum;
    }

    // Getter for total points
    public int getPoints() {
        return pointSum;
    }

    // Implementation of the iterator to iterate over the course reports
    @Override
    public Iterator<CourseReport> iterator() {
        return new StudentIterator(this);
    }

    public int getSizeCourseReports() {
        return getMaxNumOfCourses();
    }

    // Inner class to implement the iterator for CourseReport
    public static class StudentIterator implements Iterator<CourseReport> {
        private int index = 0;
        private final Student student;

        public StudentIterator(Student student) {
            this.student = student;
        }

        @Override
        public boolean hasNext() {
            return index < student.CourseNum;
        }

        @Override
        public CourseReport next() {
            if (hasNext())
                return student.courseReports[index++];
            else
                throw new NoSuchElementException();
        }
    }

    // Comparator for comparing students by weighted average
    public static class AverageComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            if (s1 != null && s2 != null)
                return Double.compare(s1.getWeightedAverage(), s2.getWeightedAverage());
            else if (s1 == null && s2 == null)
                return 0;
            else if (s1 == null)
                return -1;
            else
                return 1;
        }
    }

    // Comparator for comparing students by total points
    public static class CoursePointsComparator implements Comparator<Student> {
        @Override
        public int compare(Student s1, Student s2) {
            if (s1 != null && s2 != null)
                return Integer.compare(s1.getPoints(), s2.getPoints());
            else if (s1 == null && s2 == null)
                return 0;
            else if (s1 == null)
                return -1;
            else
                return 1;
        }
    }
    // Custom serialization logic to exclude the ID field
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject(); // Perform the default serialization for all non-transient fields
        // ID is not serialized as it is marked transient
    }

    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject(); // Perform the default deserialization for all non-transient fields
        // ID will remain with its default value (0 in this case) after deserialization
    }

}
