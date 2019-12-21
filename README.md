## Avengers Academy

Simulates a school records system to demonstrate constructing objects and lists of objects from text files


### Overview
Reads from two text files containing Student and Course information respectively.
Builds two independent lists of students and courses, each with relevant information about each object, and adds students to their respective Course objects.
Multiple menu options to display individual or all students and courses, and the ability to create entries to the file directly from the menu.


#### How it works
A Student has attributes such as first and last names, student ID, enrolled courses, and grades.
A Course has attributes such as a course title, course ID, number of credit hours, and instructor.
Each text file is spliced by commas ( , ) so that a file scanner can read each attribute one at a time via delimiter.
Each attribute is read and stored in the appropriate variable, and then a new instance of either Student or Course is created.

For a new Student, the program must check to see if the Student is unenrolled. If so, a "Not Enrolled" tag is applied and the student's grade is defaulted to 0 as a placeholder.
In addition, the program must check to see if the Student already exists within the list. If so, the new courses and grades (if there are any) must be added as additional attributes to the existing student.
Through these checks, no duplicate students appear in the list, and any recurring appearances within the text file will not cause the program to add additional unnecessary information.

For a new Course, the process is much simpler. The program simply checks to see if the Course already exists in the list. If not, the new Course is added to the list.
After both lists have been built, the program cycles through the process of adding each existing Student to the Course he/she is enrolled in.
Finally, the program prompts the user with the selection screen, and from there the user can choose any number of options, listed below.

#### Menu
0. Exit the program
1. Print all students (and their relevant information) who are enrolled in a course
2. Print all courses currently being offered at the institution, including their course ID, instructor, enrollment, and credit hours
3. Given a Student's full name or student ID (prompted), print out all of that specific student's available information
4. Given a Course's full title or course ID (prompted), print out all of that specific course's available information
5. Print all students who are currently not enrolled in any courses
6. Print all information about every Course available and, for each Course, display all students enrolled in them
7. Create a new Student (in both the list and file) by prompting the user to fill in the necessary information
8. Create a new Course (in both the list and file) by prompting the user to fill in the necessary information

#### Future Plans
- Add a list of Instructors, with appropriate fields such as first and last names, what courses they teach, and perhaps more detailed information such as years worked, office hours, etc.
- Add times and schedules to each Course, and prevent students from registering from courses that would overlap.
- Some methods can likely be shortened/rewritten/moved. Specifically those regarding adding students to a specific course.
