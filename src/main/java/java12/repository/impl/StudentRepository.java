package java12.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import java12.entities.Student;
import java12.repository.GenericRepository;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository implements GenericRepository<Student ,Long> {
    @PersistenceContext
    private final EntityManagerFactory entityManagerFactory;

    public StudentRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Student save(Student entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return entity;
    }

    @Override
    public Student findById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student = null;
        try {
            entityManager.getTransaction().begin();
            student = entityManager.find(Student.class,aLong);
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return student;
    }

    @Override
    public List<Student> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Student> students = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            students = entityManager.createQuery("select s from Student  s", Student.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return students;
    }

    @Override
    public Student updateById(Long aLong, Student newEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Student student = null;
        try {
            entityManager.getTransaction().begin();
            student = entityManager.find(Student.class,aLong);
            student.setName(newEntity.getName());
            student.setEmail(newEntity.getEmail());
            student.setYearOfBirth(newEntity.getYearOfBirth());
            entityManager.merge(student);
            entityManager.getTransaction().commit();

        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return student;
    }

    @Override
    public void deleteById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Student student = entityManager.find(Student.class, aLong);
            entityManager.remove(student);
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }
}
