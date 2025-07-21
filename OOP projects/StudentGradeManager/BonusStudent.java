package assignment7;


public class BonusStudent extends Student {
    /**
     * Represents a bonus student which extends the basic Student class with an additional multiplier.
     */
    public BonusStudent() {
        super(); //use Student's default constructor
        mult = 1;
    }

    public BonusStudent(String name, int id, int numOfCourses, double mult) {
        super(name, id, numOfCourses); //use Student's class constructor
        if (mult > 0)
            this.mult = mult;
        else this.mult = 1;
    }

    public BonusStudent(BonusStudent other) { //copy constructor
        super(other); //use Student's class copy constructor
        if (other != null)
            this.mult = other.mult;
        else
            mult = 1;
    }
    //over-ride of copy method of Student class, returns new instance of BonusStudent class (copy of this)
    @Override
    public BonusStudent copy() {
        return new BonusStudent(this);
    }

    public double getMult() { //returns mult variable
        return mult;
    }

    public void setMult(double mult) { //set mult variable ,only positive (1 or more) values are valid
        if (mult > 0)
            this.mult = mult;
    }

    //helper method for calculating weighted average
    //calculating the bonus for Bonus Student
    public double AddBonus() {
        return Math.floor(getNumOfPoints()/10.0) * mult; //this result will be added to the student's average
    }

    //over-riding getWeightedAverage method of class Student, uses the getWeightedAverage method and adds the bonus
    @Override
    public double getWeightedAverage(){
        double average = super.getWeightedAverage() + AddBonus();
        if (average > 100) //if average bigger then 100
            return 100;
        else if (average < 0) //if negative average
            return 0;
        else  //valid average
            return average;
    }

    //multiplier field for weighted average calculation
    private double mult;
}
