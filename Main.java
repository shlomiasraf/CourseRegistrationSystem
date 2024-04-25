package coursesSystem;
public class Main
{
    public static void main(String[] args)
    {
        //create the Registration System.
        CourseRegistrationSystemFacade ArielUniversity = CourseRegistrationSystemFacade.getInstance();

        //create Lecturer and Practicer and register them.
        Lecturer drMoshe = ArielUniversity.getLecturer("Moshe",234524589,"Computer Science");
        ArielUniversity.signIn(drMoshe);
        //Lecturer create a course.
        Course algo = ArielUniversity.getCourse(drMoshe,"algo", 705843, 100, "Compulsory");
        Practicer mrAviv = ArielUniversity.getPracticer("Aviv",868654312,"Mathematics");
        ArielUniversity.signIn(mrAviv);
        //Practicer create a course.
        Course LinearAlgebra = ArielUniversity.getCourse(mrAviv,"Linear algebra", 643365, 2, "Elective");
        //Create two students and register them.
        Student Shlomi = ArielUniversity.getStudent("Shlomi", 207970289,"Computer Science", 2);
        ArielUniversity.signIn(Shlomi);
        Student Afik = ArielUniversity.getStudent("Afik", 265345211,"Computer Science", 2);
        ArielUniversity.signIn(Afik);
        //register students to course.
        ArielUniversity.registerStudentToCourse(Shlomi,LinearAlgebra);
        ArielUniversity.registerStudentToCourse(Afik,LinearAlgebra);
        // Attempt to register student for a full course
        Student Noam = ArielUniversity.getStudent("Noam", 335546678,"Computer Science", 2);
        ArielUniversity.signIn(Noam);
        ArielUniversity.registerStudentToCourse(Noam,LinearAlgebra);
        ArielUniversity.cencelRegisterToCourse(Afik,LinearAlgebra);
        ArielUniversity.notifyObservers("The registration system will not work on 14/8 from 12:00-14:00");
        ArielUniversity.printNotifications(Shlomi);
        ArielUniversity.printNotifications(Afik);
        ArielUniversity.printNotifications(Noam);
        ArielUniversity.signOut(Afik);
        //try to create Student with id that already exists with other details.
        Student Aviv = ArielUniversity.getStudent("Aviv", 265345211,"Computer Science", 2);

    }
}