package coursesSystem;

public class CourseRegistrationSystemFacade extends Subject
{
    private courseFactory courseFactory;
    private static CourseRegistrationSystemFacade instance;
    // Static method to get the singleton instance
    public CourseRegistrationSystemFacade()
    {
        this.courseFactory = new courseFactory();
    }
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
        if(!observers.contains(user) && observers.size() < 100)
        {
            addObserver(user);
        }
    }
    public void signOut(User user)
    {
        if(!observers.isEmpty() && observers.contains(user))
        {
            removeObserver(user);
        }
    }
    public void printNotifications(User user)
    {
        System.out.println("\n" + user.getName() + " notifications:");
        for(String notification : user.notifications)
        {
            System.out.println(notification);
        }
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
                    System.out.println(student.getName() + " Registered to course " + course.getCourseName());
                }
                else
                {
                    student.getCoursesToRegisterWhenAvailable().add(course);
                    System.out.println(student.getName() + " The course " + course.getCourseName() + " is full and you will be notified when a place becomes available");
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
                System.out.println(student.getName() + " Cancel the register to course " + course.getCourseName());
            }
        }
    }
}
