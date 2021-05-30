package pt.ipb.dsys.web.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pt.ipb.dsys.web.spring.entities.Student;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, String> {
}
