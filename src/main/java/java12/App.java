package java12;

import java12.config.HibernateConfig;
import java12.entities.Course;
import java12.entities.Lesson;
import java12.entities.Student;
import java12.service.GenericService;
import java12.service.impl.CourseService;
import java12.service.impl.LessonService;
import java12.service.impl.StudentService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
//        HibernateConfig.getEntityManager();
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(HibernateConfig.class);
        CourseService courseService = context.getBean(CourseService.class);
        LessonService lessonService = context.getBean(LessonService.class);
        StudentService studentService = context.getBean(StudentService.class);


//        courseService.save(new Course("Ololo",15000,LocalDate.of(2020,1,10)));
//        System.out.println(courseService.findById(1L));
//        System.out.println(courseService.getAll());
//        courseService.updateById(1L,new Course("Udemy",23000,LocalDate.of(2010,2,4)));
//        courseService.deleteById(3L);

//        lessonService.save(new Lesson("Java","Good","QWQ",LocalDate.of(2023,2,3),true));
//        System.out.println(lessonService.findById(1L));
//        System.out.println(lessonService.getAll());
//        lessonService.updateById(1L,new Lesson("qwer","trew","HHH",LocalDate.of(2023,4,5),true));
//        lessonService.deleteById(1L);

//        studentService.save(new Student("Akmil","A@gmail.com",2000));
//        System.out.println(studentService.findById(1L));/\
//        System.out.println(studentService.getAll());
//        studentService.updateById(1L,new Student("Argen","Ar@gmail.com",2005));
//        studentService.deleteById(1L);
    }
}
