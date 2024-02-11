package java12.service.impl;

import java12.entities.Lesson;
import java12.repository.GenericRepository;
import java12.service.GenericService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LessonService implements GenericService<Lesson,Long> {
    @Qualifier("lessonRepository")
    private final GenericRepository<Lesson,Long> lessons;
    public LessonService(GenericRepository<Lesson, Long> lesson) {
        this.lessons = lesson;
    }

    @Override
    public Lesson save(Lesson entity) {
        return lessons.save(entity);
    }

    @Override
    public Lesson findById(Long aLong) {
        return lessons.findById(aLong);
    }

    @Override
    public List<Lesson> getAll() {
        return lessons.getAll();
    }

    @Override
    public Lesson updateById(Long aLong, Lesson newEntity) {
        return lessons.updateById(aLong, newEntity);
    }

    @Override
    public void deleteById(Long aLong) {
        lessons.deleteById(aLong);
    }
}
