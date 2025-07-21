package assignment3;

public class CourseReport {
    /**
     * The CourseReport class represents a report of a course, including its name,
     * the points it is worth, and the grade received.
     */
    public CourseReport() { //default constructor, sets all varietals to null and zeros
        this.name = null;
        this.points = 0;
        this.grade = 0;
    }

    public CourseReport(String name, int points, double grade) { //constructor with given variables
        this.name = name;
        this.points = points;
        this.grade = grade;
    }

    public CourseReport(CourseReport other) { //copy constructor
        this();
        if (other != null) {
            this.name = other.name; //copy name of other student
            this.points = other.points; //copy points of other student
            this.grade = other.grade; //copy grade of other student
        }
    }

    public double getGrade() { //returns int of grade of course
        return grade;
    }

    public void setGrade(double grade) { //set grade for course (have to be between 0 and 100)
        if (grade <= 0)
            this.grade = 0; //if negative set to 0
        else if (grade >= 100)
            this.grade = 100; //if over 100 set to 100
        else //if valid
            this.grade = grade;
    }

    public String getName() {
        return name; //if name is null the method returns null else a String value with the name
    }

    public void setName(String name) {
        if (name != null) //set new name if not null
            this.name = name;
    }

    public int getPoints() {
        return points; //return int value of points
    }

    public void setPoints(int points) {
        if (points > 0) //set points only if given positive value
            this.points = points;
    }

    //helper method for weighted average calculation in Student class
    //returns the weight of the grade (grade multiplied by points)
    public double GetWeightedGrade (){
        return grade * points;
    }


    //declaration of variables of class CourseReport
    private String name; //name of course
    private int points; //points of course
    private double grade; //graded of course
}

