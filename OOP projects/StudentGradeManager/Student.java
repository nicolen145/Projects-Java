package assignment3;

public class Student {
    /**
     * The Student class represents a student with a name, an ID, and an array of course reports.
     */
    public Student() { //default constructor, sets all variables to null and zeros
        name = null;
        courseReports = null;
        id = 0;
    }

    public Student(String name, int id, int numOfCourses) { //constructor with given variables
        this.name = name;
        this.id = id;
        if (numOfCourses >= 0) ////only positive number is valid
            this.courseReports = new CourseReport[numOfCourses]; //initialize an empty course reports array of size numOfCourses
        else
            this.courseReports = null; //if number of courses is bellow 0 there is no courses

    }

    public Student(Student other) { //copy constructor
        this();
        if (other != null) {
            this.CourseNum = other.CourseNum;
            this.name = other.name; //copy name of other student
            this.id = other.id; //copy id of other student
            if (other.courseReports == null) //copy course reports array
                this.courseReports = null;
            else
                this.courseReports = new CourseReport[other.getMaxNumOfCourses()]; //initialize new array
            //deep copy of the other student course reports:
            for (int i = 0; i < getMaxNumOfCourses(); i++) {
                //create new course report using the copy constructor
                this.courseReports[i] = new CourseReport(other.courseReports[i]);
            }
        }
    }

    public String getName() {
        return name; //return String value of the name (if null return null)
    }

    public void setName(String name) {
        if (name != null) //set name of given value if not null
            this.name = name;
    }

    public int getId() {
        return id; //return the id variable
    }

    public void setId(int id) {
        if (id > 0) //only positive id number is valid
            this.id = id;
    }

    public CourseReport[] getCourseReports() {
        return courseReports; //return the exact courseReports of the student (not a copy)
    }

    public void addCourse(CourseReport courseReport) {
        if (courseReport != null) //can't add a null course
            for (int i = 0; i < getMaxNumOfCourses(); i++){
                if (courseReports[i] == null) { // find an empty slot
                    //add a copy of the course Report
                    courseReports[i] = new CourseReport(courseReport);
                    CourseNum ++; //add to course num variable
                    break; //if a course has been added
                }
            }
    }


    public double getWeightedAverage() {
        double average = 0;
        if (this.courseReports != null) {
            double weightedGrades = 0; //sum of all grades multiply by points
            for (int i = 0; i < getMaxNumOfCourses(); i++) {
                if (this.courseReports[i] != null) {
                    weightedGrades += this.courseReports[i].GetWeightedGrade(); //add weighted grade of current course to the sum
                }
            }
            if (getNumOfPoints() != 0) { //can't be divided by 0
                average = ((weightedGrades / getNumOfPoints())); //the weighted average + bonus points for Bonus and Grad students
                if (average > 100)
                    return 100;
                else if (average < 0)
                    return 0;
                else return average; }
            else
                return 0; //if the courses array in not null but its empty
        }
        return 0; //if there is no courses
    }

    //helper method returns total number of points of all courses
    public int getNumOfPoints(){
        int points = 0;
        for (int i = 0; i < CourseNum; i ++){
            points += this.courseReports[i].getPoints();
        }
        return points;
    }


    //helper method, returns max number of courses.
    public int getMaxNumOfCourses(){
        if (courseReports != null)
            return courseReports.length;
        return 0; //if there is no courses
    }


    //helper method returns number of courses the student takes.
    public int getNumberOfCourses(){
        return CourseNum;
    }


    //declaration of variables of class Student
    private String name; //name of student
    private int id; //id of student
    private CourseReport[] courseReports; //array of courses reports
    //I added one more variable that counts the number of courses
    private int CourseNum;
}
