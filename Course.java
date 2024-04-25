package coursesSystem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
class Course
{
    private String courseName;
    private int courseID;
    private int registerNumber = 0;
    private int registerMax;
    private String kind;
    public Course(String courseName, int courseID, int registerMax, String kind)
    {
        this.courseName = courseName;
        this.courseID = courseID;
        this.registerMax = registerMax;
        this.kind = kind;
    }

    public String getCourseName()
    {
        return courseName;
    }
    public int getRegisterMax()
    {
        return registerMax;
    }
    public boolean tryToRegister()
    {
        if(registerNumber < registerMax)
        {
            registerNumber++;
            return true;
        }
        return false;
    }
    public void cencelRegister()
    {
        if(registerNumber > 0)
        {
            if(registerNumber == registerMax)
            {
                registerNumber--;
                for(Observer observer: Subject.observers)
                {
                    if(observer instanceof Student)
                    {
                        Student student = (Student) observer;
                        if(student.getCoursesToRegisterWhenAvailable().contains(this))
                        {
                            observer.update(student.getName() + " A place has become available in " + courseName + " course, hurry up to register!");
                        }
                    }
                }
            }
        }
    }
}
class courseFactory
{
    private static Map<Integer, Course> courses = new HashMap<>();
    public static Course getCourse(String courseName, int courseID, int registerMax, String kind)
    {
        if (!courses.containsKey(courseID))
        { // if course doesn't exist in the map
            courses.put(courseID, new Course(courseName, courseID, registerMax, kind)); // create new course and add it to the map
        }
        return courses.get(courseID);
    }
}