package java12.service.impl;

import java12.entities.Student;
import java12.repository.GenericRepository;
import java12.repository.impl.StudentRepository;
import java12.service.GenericService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentService implements GenericService<Student,Long> {
    @Qualifier("studentRepository")
    private final GenericRepository<Student,Long> students;


    public StudentService(GenericRepository<Student, Long> students) {
        this.students = students;
    }

    @Override
    public Student save(Student entity) {
        return students.save(entity);
    }

    @Override
    public Student findById(Long aLong) {
        return students.findById(aLong);
    }

    @Override
    public List<Student> getAll() {
        return students.getAll();
    }

    @Override
    public Student updateById(Long aLong, Student newEntity) {
        return students.updateById(aLong, newEntity);
    }

    @Override
    public void deleteById(Long aLong) {
        students.deleteById(aLong);
    }
}
