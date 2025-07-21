package assignment7;

public class GradStudent extends Student {
    /**
     * Represents a graduate student which extends the basic Student class with an additional bonus.
     */
    public GradStudent() {
        super(); //use Student's default constructor
        bonus = 0;
    }

    public GradStudent(String name, int id, int numOfCourses, double bonus) {
        super(name, id, numOfCourses); //use Student's class constructor
        if (bonus >= 0) //bonus variable must be positive
            this.bonus = bonus;
        else this.bonus = 0;
    }

    public GradStudent(GradStudent other) {
        super(other); //use Student's class copy constructor
        if (other != null)
            this.bonus = other.bonus;
        else
            bonus = 0;
    }

    //over-ride of copy method of Student class, returns new instance of GradStudent class (copy of this)
    @Override
    public GradStudent copy() {
        return new GradStudent(this);
    }

    public double getBonus() { //return bonus variable
        return bonus;
    }

    public void setBonus(double bonus) { //set bonus variable ,only not negative (0 or more) values are valid
        if (bonus >= 0)
            this.bonus = bonus;
    }

    //helper method for calculating weighted average
    //calculating the bonus for Bonus Student
    public double AddBonus() {
        if (getNumOfPoints() > 10) //if the student took more than 10 points of courses
            return bonus; //The bonus will be added to the student's average
        else //if the student took less than 10 points of courses
            return bonus * (-1); //The bonus decreases from the average instead of being added.


    }
    //over-riding getWeightedAverage method of class Student, uses the getWeightedAverage method and adds the bonus
    @Override
    public double getWeightedAverage(){
        double average =  super.getWeightedAverage() + AddBonus();
        if (average > 100) //if average bigger then 100
            return 100;
        else if (average < 0) //if negative average
            return 0;
        else  //valid average
            return average;
    }

    //bonus field for the weighted average calculation
    private double bonus;
}
