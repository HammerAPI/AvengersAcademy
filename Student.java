/* Author: Daniel Hammer
 *
 * Student Class
 *
 * Contains fields needed to construct an ArrayList of independent students
 */

import java.util.ArrayList;

public class Student{

    /**********
     * FIELDS *
     * *******/

    // Fields are listed as they appear in the .txt file
    private String firstName;
    private String lastName;
    private int studentID;
    private ArrayList<String> coursesEnrolled;
    private ArrayList<Integer> grades;


    

    /***************
     * CONSTRUCTOR *
     * ************/

    public Student(String firstName, String lastName, int studentID,
            String course, int grade){

        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;

        // Create two unique ArrayLists for every student
        // One holds every course the student is enrolled in
        // The other holds the student's grade for that course
        this.coursesEnrolled = new ArrayList<String>();
        this.grades = new ArrayList<Integer>();

        // Add the passed values to the newly created ArrayLists
        // Note that coursesEnrolled holds STRINGS and not course OBJECTS
        // This is because students are built by reading a text file
        this.coursesEnrolled.add(course);
        this.grades.add(grade);
    }
    


    
    /**********************
     * ALTERATION METHODS *
     * *******************/

    // Returns the student's first name
    public String getFirstName(){
        return this.firstName;
    }

    // Returns the student's last name
    public String getLastName(){
        return this.lastName;
    }

    // Returns the student's unique ID
    public int getStudentID(){
        return this.studentID;
    }

    // Returns the list of the ID of every course the student is enrolled in
    public ArrayList<String> getAllCourses(){
        return this.coursesEnrolled;
    }
    
    // Returns a specific course ID, if the student has it, via list index
    public String getACourse(int index){
        return this.coursesEnrolled.get(index);
    }

    // Adds a course ID to the list of courses the student is enrolled in
    public void addEnrolledCourses(String c){
        this.coursesEnrolled.add(c);
    }

    // Returns a list of all grades the student has
    public ArrayList<Integer> getAllGrades(){
        return this.grades;
    }

    // Returns a specific grade of the student via list index
    public int getAGrade(int index){
        return this.grades.get(index);
    }
    
    // Adds a grade to the list of grades the student has
    public void addGrade(int g){
        this.grades.add(g);
    }

    


    /*****************
     * EQUALS METHOD *
     * **************/

    // Checks if the student is equal to the parameter being passed
    public boolean equals(Object other){

        // Checks to see if they are the same object
        if (this == other){
            return true;
        }
        
        // If not, create a new typecasted object and compare fields
        Student o = (Student) other;

        // Technically, since every student has a unique ID, 
        // only the studentIDs need to be compared
        // However, to illustrate the concept better,
        // the names and ID are being compared
        return this.lastName.equals(o.getLastName())
            && this.firstName.equals(o.getFirstName())
            && this.studentID == o.getStudentID();
    }




    /*******************
     * DISPLAY METHODS *
     * ****************/

    // Displays all of the information about the student
    public String toString(){
        return "\nStudent #" + this.studentID
            + "\n\tName: " + this.firstName + " "
            + this.lastName + "\n\tEnrolled in: "
            + this.coursesEnrolled + " | With grades: "
            + this.grades;
    }




    // Displays only the student's first name, last name, and student ID
    public String showStudent(){
        return this.lastName + ", " + this.firstName
            + " | Student #" + this.studentID;
    }
}
