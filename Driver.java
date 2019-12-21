/* Author: Daniel Hammer
 *
 * Driver to build Course and Student ArrayLists
 * 
 * Scans two text files and builds each list indepedently
 * Prompts the user with options of what to display
 * Also allows user to create new entries, written to the file
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

public class Driver{




    /**********
     * FIELDS *
     * *******/
    
    // Scanner for user input
    private Scanner sc = new Scanner(System.in);

    // ArrayLists to hold the students and the courses
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Course> courses = new ArrayList<Course>();

    // These fields are used to build each Student object
    private String lastName;
    private String firstName;
    private int studentID;
    private String enrolledCourseID;
    private int grade;

    // These fields are used to build each Course object
    private String courseName;
    private String courseID;
    private int credits;
    private String instructor;




    /*******************
     * LOGICAL METHODS *
     * ****************/

    // Builds the list of courses by scanning the course.txt file
    public void buildCourseList() throws FileNotFoundException{

        try {
            // Creates a scanner to scan courses.txt
            Scanner courseScanner = new Scanner(new File("courses.txt"));
            // Assigns a delimiter of ',' to the scanner
            courseScanner.useDelimiter(",");

            // New Course to be constructed
            Course newCourse;

            // While the scanner has a String to scan
            while(courseScanner.hasNext()){

                // Assign each consecutive string to the appropriate variable
                courseName = courseScanner.next().trim();
                courseID = courseScanner.next().trim();
                credits = Integer.parseInt(courseScanner.next().trim());
                instructor = courseScanner.next().trim();

                // Construct the course from the supplied information
                newCourse = new Course(courseName, courseID, credits, instructor);

                // Add the new course to the ArrayList if not already present
                if (!(courses.contains(newCourse))){
                    courses.add(newCourse);
                }
                
                // Move the scanner to the next line of the file
                courseScanner.nextLine();
            }

            // Since the list is built, close the scanner
            courseScanner.close();
        }
        // If the file is not found, print an exception
        catch (Exception e){
            System.out.println("You don't have the required file");
        }
    }




    // Builds the list of students by scanning the students.txt file
    public void buildStudentList() throws FileNotFoundException{

        try {
            // Creates a scanner to scan students.txt
            Scanner studentScanner = new Scanner(new File("students.txt"));
            // Assigns a delimiter of ',' to the scanner
            studentScanner.useDelimiter(",");

            // New Student to be constructed
            Student newKid;

            // While the scanner has a String to scan
            while (studentScanner.hasNext()){

                // Assign each consecutive string to the appropriate variable
                lastName = studentScanner.next().trim();
                firstName = studentScanner.next().trim();
                studentID = Integer.parseInt(studentScanner.next());
                enrolledCourseID = studentScanner.next();


                // If the student does has no course,
                // Assign an unenrolled tag and a grade of 0
                if (enrolledCourseID.equals("")){
                    enrolledCourseID = "Not Enrolled";
                    grade = 0;
                    
                    // Since the grade variable was manually edited,
                    // skip the next item in the file
                    studentScanner.next();
                }
                else{
                    // If the student WAS enrolled in a course,
                    // assign the next item to the grade variable
                    grade = Integer.parseInt(studentScanner.next());
                }

                // Construct the new student
                newKid = new Student(firstName, lastName, studentID, enrolledCourseID, grade);

                // If the student list already contains the newKid
                // Add the course enrolled and grade to the existing Student
                if (students.contains(newKid)){

                    // Temporary Student equal to the existing student
                    Student existing = getAStudent(newKid.getStudentID());

                    // If the existing student does not already have
                    // the new course, add it
                    if (!(existing.getAllCourses().contains(enrolledCourseID))){
                        existing.addEnrolledCourses(enrolledCourseID);
                        existing.addGrade(grade);
                    }
                }
                // If the student list does not already contain the newKid
                // add the newKid
                else{
                    students.add(newKid);
                }
                
                // Move the scanner to the next line of the file
                studentScanner.nextLine();
            }

            // Since the list is built, close the scanner
            studentScanner.close();
        }
        // If the file is not found, print an exception
        catch (Exception e){
            System.out.println("You don't have the required file");
        }

    }




    // Adds each student to the course he/she is enrolled in
    public void addStudentsToCourses(){

        // For every course, check every student
        for (Course c : courses){
            for (Student s : students){
                
                // Check every course that the student is enrolled in
                // If the student is enrolled in the course,
                // add that student to the course
                // But only if the student is not already enrolled!
                if (s.getAllCourses().contains(c.getCourseID()) 
                        && !(c.getStudentsEnrolled().contains(s))){
                    c.addStudentsEnrolled(s);
                }
            }
        }
    }




    // Creates a new student entry in the system
    // Writes the information to students.txt
    public void makeAStudent() throws IOException{

        System.out.println("Enrolling a new student in the database:");

        // Create a fileWriter and a String to add to the file
        FileWriter studentWriter = new FileWriter("students.txt", true);
        String toAppend;

        // Hold the values for each variable
        System.out.print("Enter a last name\n> ");
        lastName = sc.nextLine().trim();

        System.out.print("Enter a first name\n> ");
        firstName = sc.nextLine().trim();

        System.out.print("Enter a student ID\n> ");
        studentID = Integer.parseInt(sc.nextLine());

        System.out.println("Enter a course ID to enroll the student in");
        System.out.println("Available Courses:");
        for (Course c : courses){
            System.out.println("\t" + c.getCourseID() + " | "
                    + c.getCourseName() + " | " + c.getEnrollment() + " enrolled");
        }
        System.out.print("\n> ");
        enrolledCourseID = sc.nextLine().trim();
        
        // If the student is not going to be enrolled,
        // set the rest of fields to null and create a new entry string
        if (enrolledCourseID.equals("")){
            toAppend = lastName + "," +
                firstName + "," +
                studentID + "," +
                enrolledCourseID + ",,";
        }
        else{
            System.out.print("Enter a grade for that course\n> ");
            grade = Integer.parseInt(sc.nextLine());

            // Create a new entry string
            toAppend = lastName + "," +
                firstName + "," +
                studentID + "," +
                enrolledCourseID + "," +
                grade + ",";
        }


        // Add the string to the file and close the fileWriter
        studentWriter.write(toAppend + "\n");
        studentWriter.close();
        
        // Construct a new Student object from the supplied information
        Student newKid = new Student(firstName, lastName, studentID, enrolledCourseID, grade);

        // If the student list already contains the newKid
        // Add the course enrolled and grade to the existing Student
        if (students.contains(newKid)){

            // Temporary Student equal to the existing student
            Student existing = getAStudent(newKid.getStudentID());

            // If the existing student does not already have
            // the new course, add it
            if (!(existing.getAllCourses().contains(enrolledCourseID))){
                existing.addEnrolledCourses(enrolledCourseID);
                existing.addGrade(grade);
            }
        }
        // If the student list does not already contain the newKid,
        // add the newKid
        else{
            students.add(newKid);
        }

        // Finally, add the new student to the respective course
        addStudentsToCourses();
    }




    // Creates a new course entry in the system
    // Writes the information to students.txt
    public void makeACourse() throws IOException{

        System.out.println("Creating a new course in the databse");

        // Create a fileWriter and a String to add to the file
        FileWriter courseWriter = new FileWriter("courses.txt", true);
        String toAppend;

        // Hold the values for each variable
        System.out.print("Enter a descriptive title for the course \n> ");
        courseName = sc.nextLine().trim();

        System.out.print("Enter the desired course ID \n> ");
        courseID = sc.nextLine().trim();

        System.out.print("Enter the number of credit hours this course will count towards \n> ");
        credits = Integer.parseInt(sc.nextLine());

        System.out.print("Enter the name of the instructor who will teach this course\n> ");
        instructor = sc.nextLine().trim();

        // Create a new entry string
        toAppend = courseName + "," +
            courseID + "," +
            credits + "," +
            instructor + ",";

        // Add the string to the file and close the fileWriter
        courseWriter.write(toAppend + "\n");
        courseWriter.close();

        // Construct the course from the supplied information
        Course newCourse = new Course(courseName, courseID, credits, instructor);

        // Add the new course to the ArrayList if not already present
        if (!(courses.contains(newCourse))){
            courses.add(newCourse);
        }

        // In the event that a student is enrolled in this new course,
        // add that student to the newly made course
        addStudentsToCourses();
    }




    // Given a student's fullname or ID, return that Student object
    public Student selectStudent(){

        String input;
        System.out.print("Enter a student's full name OR student ID:\n> ");
        input = sc.nextLine().toLowerCase().trim();

        for (Student s : students){
            
            // First, check to see if a student matching names exists
            if ((s.getFirstName().toLowerCase() + " "
                        + s.getLastName()).toLowerCase().equals(input)){
                return s;
            }
            // If not, check if a student matching IDs exists
            else {
                try{
                    if (s.getStudentID() == Integer.parseInt(input)){
                        return s;
                    }
                } catch (NumberFormatException e){
                }
            }
        }
        return null;
    }
    



    // Given a course's name or ID, return that Course object
    public Course selectCourse(){

        String input;
        System.out.print("Enter a course's full title OR it's course ID:\n> ");
        input = sc.nextLine().toLowerCase().trim();

        for (Course c : courses){
            
            // Check if a course matching names or IDs exists
            if (c.getCourseName().toLowerCase().equals(input) || 
                    c.getCourseID().toLowerCase().equals(input.replaceAll(" ", ""))){
                return c;
            }
        }
        return null;
    }




    // Returns a student object given a specific student ID
    public Student getAStudent(int id){

        for (Student s : students){
            if (s.getStudentID() == id){
                return s;
            }
        }
        return null;
    }

    


    /*****************
     * PRINT METHODS *
     * **************/

    // Print all students in the records system
    public void printAllStudents(){
        
        System.out.println("\nDisplaying all students enrolled in this facility:\n");
        for (Student s : students){
            System.out.println(s.toString());
        }
    }
    



    // Print all courses in the records system
    public void printAllCourses(){

        System.out.println("\nDisplaying all courses offered at this facility:\n");
        for (Course c : courses){
            System.out.println(c.toString());
        }
    }




    // Given a student ID or full name, print all information about the student
    public void printAStudent(){

        Student stu = selectStudent();

        System.out.println("\nDisplaying a single student:\n");
        System.out.println("\t"+ stu.showStudent());

        System.out.println("\tCurrently enrolled in:");
        for (int x = 0; x < stu.getAllCourses().size(); x++){
            System.out.println("\t\t" + stu.getACourse(x) + " | Grade: " + stu.getAGrade(x));
        }

    }




    // Given a course ID or course name, print all the information about the course
    public void printACourse(){
    
        Course co = selectCourse();

        System.out.println("\nDisplaying a single course:\n");
        System.out.println("\t" + co.toString());

        System.out.println("\n\tStudents in " + co.getCourseID() + ":");
        co.displayAllEnrolled();
    }




    // Print all of the students who are not enrolled in a course
    public void printAllUnenrolled(){
    
        System.out.println("\nStudents who are unenrolled:\n");
        for (Student s : students){
            if (s.getACourse(0).equals("Not Enrolled")){
                System.out.println("\t" + s.getLastName() + ", " + 
                        s.getFirstName() + " | ID: " + 
                        s.getStudentID());
            }
        }
    }




    // Print all courses and every student enrolled in them
    public void printEverything(){

        System.out.println("\nDisplaying all courses available and all students enrolled in them:\n");
        for (Course c : courses){
            System.out.print("\n" + c.toString());
            for (Student s : c.getStudentsEnrolled()){
                System.out.print("\n\t" + s.showStudent());
            }
        }
    }




    /****************
     * MAIN METHODS *
     * *************/

    // Selection menu
    // Prompts the user to enter one of many options
    // Loops continually until the user pressed 0 to exit
    public void selection() throws IOException{

        int input;

        System.out.println("\nWelcome to Avengers Academy. You are now viewing facility records.");

        do{
            System.out.println("\n==================================================");
            System.out.print("Menu options:" +
                    "\n\t0 - Exit records menu" +
                    "\n\t1 - Display all enrolled students" + 
                    "\n\t2 - Display all available courses" + 
                    "\n\t3 - Display a single student's records" + 
                    "\n\t4 - Display a specific course's information" +
                    "\n\t5 - Display all students who are currently unenrolled" +
                    "\n\t6 - Display all courses and all students in them" +
                    "\n\t7 - Enroll a new student" +
                    "\n\t8 - Create a new class" +
                    "\n> ");
            input = Integer.parseInt(sc.nextLine());

            switch (input){
                case 0: break;
                case 1: printAllStudents();
                        break;
                case 2: printAllCourses();
                        break;
                case 3: printAStudent();
                        break;
                case 4: printACourse();
                        break;
                case 5: printAllUnenrolled();
                        break;
                case 6: printEverything();
                        break;
                case 7: makeAStudent();
                        break;
                case 8: makeACourse();
                        break;
            }
            
        } while (input != 0);
        System.out.println("Thank you for using HammerAPI Records Systems");
        sc.close();
    }




    // Builds the course list, then the student list
    // Necessary due to students needing existing Course objects to be added to
    // Followed by a call to add the students to existing courses
    // Then the recurring selection menu
    public void go() throws FileNotFoundException, IOException{
        
        buildCourseList();
        buildStudentList();
        addStudentsToCourses();
        selection();
    }




    // Main method
    public static void main(String[] args) throws FileNotFoundException, IOException{

        Driver d = new Driver();

        d.go();
    }
}
