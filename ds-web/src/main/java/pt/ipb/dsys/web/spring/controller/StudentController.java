package pt.ipb.dsys.web.spring.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ipb.dsys.web.spring.entities.Student;
import pt.ipb.dsys.web.spring.repo.StudentRepository;

import java.util.List;

@RestController
@RequestMapping("my/student")
public class StudentController {

    private final StudentRepository studentRepository;

    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping
    public List<Student> get() {
        return studentRepository.findAll();
    }

    @GetMapping("{number}")
    public Student get(@PathVariable String number) {
        return studentRepository.findById(number).orElse(null);
    }

    @PostMapping
    public void post(@RequestBody Student student) {
        studentRepository.save(student);
    }

    @DeleteMapping("{number}")
    public void delete(@PathVariable String number) {
        studentRepository.deleteById(number);
    }

}
