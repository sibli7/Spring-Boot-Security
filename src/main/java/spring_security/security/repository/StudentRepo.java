package spring_security.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring_security.security.entity.Student;

@Repository
// public interface UserRepo extends JpaRepository<User,Integer>
public interface StudentRepo extends JpaRepository<Student,Integer> {
     

}
