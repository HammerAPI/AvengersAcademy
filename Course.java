/* Author: Daniel Hammer
 *
 * Course Class
 *
 * Contains fields needed to construct an ArrayList of independent courses
 * Also contains methods to add students to a particular course instance
 */

import java.util.ArrayList;

public class Course{
    



    /**********
     * FIELDS *
     * *******/

    // Fields are listed as they appear in the .txt file
    private String courseName;
    private String courseID;
    private int credits;
    private String instructor;
    private int enrollment;
    private ArrayList<Student> studentsEnrolled;




    /***************
     * CONSTRUCTOR *
     * ************/

    public Course(String courseName, String courseID,
            int cr, String in){
        this.courseName = courseName;
        this.courseID = courseID;
        this.credits = cr;
        this.instructor = in;
        
        // Give every course a unique ArrayList to hold the students enrolled
        this.studentsEnrolled = new ArrayList<Student>();
        // And set the enrollment to 0
        this.enrollment = 0;
    }




    /**********************
     * ALTERATION METHODS *
     * *******************/

    // Returns the course's name
    public String getCourseName(){
        return this.courseName;
    }

    // Returns the course's ID
    public String getCourseID(){
        return this.courseID;
    }

    // Returns the number of credits the course counts for
    public int getCourseCredits(){
        return this.credits;
    }
    
    // Returns the name of the instructor teaching the course
    public String getInstructor(){
        return this.instructor;
    }

    // Returns the number of students in the course
    public int getEnrollment(){
        return this.enrollment;
    }

    // Returns a list of the students enrolled in the course
    public ArrayList<Student> getStudentsEnrolled(){
        return this.studentsEnrolled;
    }
    
    // Adds a specified student to the course and increased the enrollment
    public void addStudentsEnrolled(Student s){
        this.studentsEnrolled.add(s);
        this.enrollment++;
    }




    /*****************
     * EQUALS METHOD *
     * **************/

    // Checks if the course is equal to the parameter being passed
    public boolean equals(Object other){

        // Checks to see if they are the same object
        if (this == other){
            return true;
        }
        
        // If not, create a new typecasted object and compare fields
        Course o = (Course) other;

        // Technically, since every course has a unique ID, 
        // only the courseIDs need to be compared
        // However, to illustrate the concept better,
        // multiple fields are being compared
        return this.courseName.equals(o.getCourseName())
            && this.courseID.equals(o.getCourseID())
            && this.credits == o.getCourseCredits()
            && this.instructor.equals(o.getInstructor());
    }




    /*******************
     * DISPLAY METHODS *
     * ****************/

    // Displays all of the information about the course
    public String toString(){
        return "\nCourse ID: " + this.courseID + 
            " | '" + this.courseName + "'" +
            " | " + this.credits + " credit hours" +
            " | Instructor: " + this.instructor + 
            " | " + this.enrollment + " students enrolled";
    }




    // Displays all students enrolled in the course
    public void displayAllEnrolled(){
        for (Student s : studentsEnrolled){
            System.out.println("\t\t" + s.showStudent());
        }
    }
}
