package coursesSystem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

abstract class User extends Observer {
    private String name;
    private int id;
    private String faculty;

    public User(String name, int id, String faculty)
    {
        this.name = name;
        this.id = id;
        this.faculty = faculty;
    }
    public String getName() {
        return this.name;
    }

    public int getID() {
        return this.id;
    }
    public String getFaculty() {
        return this.faculty;
    }
    public void printNotifications()
    {
        System.out.println("\n" + this.name + " notifications:");
        for(String notification : notifications)
        {
            System.out.println(notification);
        }
    }
}
class Lecturer extends User
{
    public Lecturer(String name, int id, String faculty)
    {
        super(name, id, faculty);
    }
}
class LecturerFactory
{
    private static final Map<Integer, Lecturer> lecturers = new HashMap<>();
    public static Lecturer getLecturer(String name, int id, String faculty)
    {
        if (!lecturers.containsKey(id))
        {
            lecturers.put(id, new Lecturer(name, id, faculty));
        }
        else if(!lecturers.get(id).getName().equals(name) || !lecturers.get(id).getFaculty().equals(faculty))
        {
            System.out.println("\nThis id: " + id + " already exists with other details.");
            return null;
        }
        return lecturers.get(id);
    }
}
class Practicer extends User
{
    public Practicer(String name, int id, String faculty)
    {
        super(name, id, faculty);
    }
}
class PracticerFactory
{
    private static final Map<Integer, Practicer> practicers = new HashMap<>();
    public static Practicer getPracticer(String name, int id, String faculty)
    {
        if (!practicers.containsKey(id))
        {
            practicers.put(id, new Practicer(name, id, faculty));
        }
        else if(!practicers.get(id).getName().equals(name) || !practicers.get(id).getFaculty().equals(faculty))
        {
            System.out.println("\nThis id: " + id + " already exists with other details.");
            return null;
        }
        return practicers.get(id);
    }
}
class Student extends User
{
    private int yearInDegree;
    private ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Course> coursesToRegisterWhenAvailable = new ArrayList<>();
    public Student(String name, int id, String faculty, int yearInDegree)
    {
        super(name, id, faculty);
        this.yearInDegree = yearInDegree;
    }
    public int getYearInDegree()
    {
        return this.yearInDegree;
    }
    public ArrayList<Course> getCoursesToRegisterWhenAvailable()
    {
        return coursesToRegisterWhenAvailable;
    }
    public ArrayList<Course> getCourses()
    {
        return courses;
    }
}
class StudentFactory
{
    private static final Map<Integer, Student> students = new HashMap<>();
    public static Student getStudent(String name, int id, String faculty, int yearInDegree)
    {
        if (!students.containsKey(id))
        {
            students.put(id, new Student(name, id, faculty, yearInDegree));
        }
        else if(!students.get(id).getName().equals(name) || !students.get(id).getFaculty().equals(faculty) || students.get(id).getYearInDegree() != yearInDegree)
        {
            System.out.println("\nThis id: " + id + " already exists with other details.");
            return null;
        }
        return students.get(id);
    }
}