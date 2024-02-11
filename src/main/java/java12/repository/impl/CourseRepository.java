package java12.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import java12.entities.Course;
import java12.repository.GenericRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class CourseRepository implements GenericRepository<Course,Long> {

    @PersistenceContext
   private final EntityManagerFactory entityManagerFactory ;

    public CourseRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Course save(Course entity) {
        try {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return entity;
    }

    @Override
    public Course findById(Long aLong) {
        Course course = null;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            course = entityManager.find(Course.class,aLong);
            entityManager.getTransaction().commit();
            entityManager.close();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public List<Course> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Course> courses = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
             courses = entityManager.createQuery("select c from Course c", Course.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return courses;
    }

    @Override
    public Course updateById(Long aLong, Course newEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Course course = null;
        try {
            entityManager.getTransaction().begin();
            course = entityManager.find(Course.class,aLong);
            course.setName(newEntity.getName());
            course.setPrice(newEntity.getPrice());
            entityManager.merge(course);
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return course;
    }

    @Override
    public void deleteById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Course course = entityManager.find(Course.class, aLong);
            entityManager.remove(course);
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }
}
