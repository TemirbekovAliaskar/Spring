package java12.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java12.entities.Lesson;
import java12.repository.GenericRepository;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class LessonRepository implements GenericRepository<Lesson, Long> {
  @PersistenceContext
    private final EntityManagerFactory entityManagerFactory;

    public LessonRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Lesson save(Lesson entity) {
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
    public Lesson findById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Lesson lesson = null;
        try {
            entityManager.getTransaction().begin();
             lesson = entityManager.find(Lesson.class,aLong);
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return lesson;
    }

    @Override
    public List<Lesson> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Lesson> lessons = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            lessons = entityManager.createQuery("select l from Lesson l", Lesson.class).getResultList();
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
        return lessons ;
    }

    @Override
    public Lesson updateById(Long aLong, Lesson newEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Lesson lesson = null;
        try {
            entityManager.getTransaction().begin();
            lesson = entityManager.find(Lesson.class,aLong);
            lesson.setTitle(newEntity.getTitle());
            lesson.setDescription(newEntity.getDescription());
            lesson.setVideoLink(newEntity.getVideoLink());
            lesson.setPresentation(newEntity.isPresentation());
            lesson.setPublishedDate(newEntity.getPublishedDate());
            entityManager.merge(lesson);
            entityManager.getTransaction().commit();

        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return lesson;
    }

    @Override
    public void deleteById(Long aLong) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            Lesson lesson = entityManager.find(Lesson.class, aLong);
            entityManager.remove(lesson);
            entityManager.getTransaction().commit();
        }catch (HibernateException e){
            System.out.println(e.getMessage());
        }finally {
            entityManager.close();
        }
    }
}
