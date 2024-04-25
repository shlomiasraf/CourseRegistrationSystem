package coursesSystem;

public class CourseRegistrationSystemFacade extends Subject
{
    private static CourseRegistrationSystemFacade instance;
    // Static method to get the singleton instance
    public static CourseRegistrationSystemFacade getInstance()
    {
        if (instance == null)
        {
            instance = new CourseRegistrationSystemFacade();
        }
        return instance;
    }
    public Course getCourse(User user,String courseName, int courseNumber, int maxCapacity, String kind)
    {
        if((user instanceof Lecturer || user instanceof Practicer) && observers.contains(user))
        {
            return courseFactory.getCourse(courseName, courseNumber, maxCapacity, kind);
        }
        return null;
    }
    public Lecturer getLecturer(String name, int id, String faculty)
    {
        return LecturerFactory.getLecturer(name, id, faculty);
    }
    public Practicer getPracticer(String name, int id, String faculty)
    {
        return PracticerFactory.getPracticer(name, id, faculty);
    }
    public Student getStudent(String name, int id, String faculty, int yearInDegree)
    {
        return StudentFactory.getStudent(name, id, faculty, yearInDegree);
    }
    public void signIn(User user)
    {
            addObserver(user);
    }
    public void signOut(User user)
    {
            removeObserver(user);
    }
    public void printNotifications(User user)
    {
        user.printNotifications();
    }
    public void registerStudentToCourse(Student student, Course course)
    {
        if (observers.contains(student))
        {
            if(course != null)
            {
                boolean check = course.tryToRegister();
                if (check)
                {
                    student.getCourses().add(course);
                    student.update("You registered to course " + course.getCourseName());
                }
                else
                {
                    student.getCoursesToRegisterWhenAvailable().add(course);
                    student.update("The course " + course.getCourseName() + " is full and you will be notified when a place becomes available");
                }
            }
        }
    }
    public void cencelRegisterToCourse(Student student, Course course)
    {
        if (observers.contains(student))
        {
            if(course != null && student.getCourses().contains(course))
            {
                course.cencelRegister();
                student.getCourses().remove(course);
                student.update("You cancel the register to course " + course.getCourseName());
            }
        }
    }
}
