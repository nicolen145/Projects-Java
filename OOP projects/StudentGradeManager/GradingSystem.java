package assignment3;


public class GradingSystem {
    /**
     * The GradingSystem class represents a grading system with a name and a list of students.
     */
    public GradingSystem() { ////default constructor, sets all varietals to null
        name = null;
        students = null;
    }

    public GradingSystem(String name, int studentCount) { //constructor with given variables
        this.name = name;
        if (studentCount >= 0) //only positive number is valid
            this.students = new Student[studentCount]; //initialize an empty students array of size studentCount
        else
            this.students = null; //if studentCount is 0 or bellow, there is no students
    }

    public GradingSystem(GradingSystem other) { //copy constructor
        this();
        if (other != null) {
            this.name = other.name; //copy name of other system
            if (other.students == null) {
                this.students = null; //copy students array
            }
            else {
                //deep copy of other grading system's students array
                this.students = new Student[other.getMaxStudents()];
                for (int i = 0; i < getMaxStudents(); i++) {
                    if (other.students[i] != null)
                        //use copy constructor to add a new student
                        students[i] = new Student(other.students[i]);
                }
            }
        }

    }

    public String getName() {
        return name; //if name is null it returns null else returns String value of the name
    }

    public void setName(String name) {
        if (name != null)
            this.name = name; //set name if given a not null value
    }

    public Student[] getStudents() {
        return students; //returns the exact students of the GradingSystem (not a copy)
    }

    public void addStudent(Student student) {
        if (student != null) //else don't add a null student
            for(int i = 0; i < getMaxStudents(); i ++){
                if (students[i] == null) {
                    //create new student by using copy constructor and add
                    students[i] = new Student(student);
                    break; //if a student has been added to the array

                }
            }
    }

    public double getAverage() {
        double average = 0;
        if (students != null) { //check if not null
            double sum = 0; //int of weighted average sum
            int numOfStudents = 0; //int of num of student
            for (int i = 0; i < getMaxStudents(); i++) {
                //uf student != null and has minimum 1 course
                if (students[i] != null && students[i].getNumberOfCourses() > 0) {
                    //calculate the sum of weighted average of all student:
                    sum += students[i].getWeightedAverage();
                    //calculate number of students
                    numOfStudents++;
                }
            }
            if (numOfStudents != 0) {//can't be divided by 0
                average = (sum / numOfStudents); //average of weighted averages
                if (average > 100)
                    return 100;
                else if(average < 0)
                    return 0;
                else
                    return average;
            }
            return 0;//if the students array is not null but empty
        }
        return 0; //if there is no students in the grading system the average is 0
    }

    //helper method returns max number of students (students array length)
    public int getMaxStudents() {
        if (students != null)
            return students.length;
        return 0; //if there is no students
    }

    //declaration of variables of class GradingSystem
    private String name; //name of the system
    private Student[] students; //array of students
}
