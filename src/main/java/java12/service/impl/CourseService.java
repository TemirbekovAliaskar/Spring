package java12.service.impl;

import java12.entities.Course;
import java12.repository.GenericRepository;
import java12.service.GenericService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CourseService implements GenericService<Course,Long>  {
    @Qualifier("courseRepository")
    private final GenericRepository<Course,Long> genericService;

    public CourseService(GenericRepository<Course, Long> genericService) {
        this.genericService = genericService;
    }


    @Override

    public Course save(Course entity) {
       return genericService.save(entity);
    }

    @Override
    public Course findById(Long aLong) {
        return genericService.findById(aLong);
    }

    @Override
    public List<Course> getAll() {
        return genericService.getAll();
    }

    @Override
    public Course updateById(Long aLong, Course newEntity) {
        return genericService.updateById(aLong, newEntity);
    }

    @Override
    public void deleteById(Long aLong) {
        genericService.deleteById(aLong);
    }
}
