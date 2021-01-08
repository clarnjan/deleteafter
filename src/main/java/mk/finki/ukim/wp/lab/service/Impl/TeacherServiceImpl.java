package mk.finki.ukim.wp.lab.service.Impl;

import mk.finki.ukim.wp.lab.model.Teacher;
import mk.finki.ukim.wp.lab.repository.jpa.TeacherRepository;
import mk.finki.ukim.wp.lab.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<Teacher> findAll() {
        return teacherRepository.findAll();
    }
}
