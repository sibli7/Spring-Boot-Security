package spring_security.security.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
     private String name;

	private String student_id;

	private String department;

	private String batch;

     


     public String getName() {
          return this.name;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getStudent_id() {
          return this.student_id;
     }

     public void setStudent_id(String student_id) {
          this.student_id = student_id;
     }

     public String getDepartment() {
          return this.department;
     }

     public void setDepartment(String department) {
          this.department = department;
     }

     public String getBatch() {
          return this.batch;
     }

     public void setBatch(String batch) {
          this.batch = batch;
     }

     public Student name(String name) {
          setName(name);
          return this;
     }

     public Student student_id(String student_id) {
          setStudent_id(student_id);
          return this;
     }

     public Student department(String department) {
          setDepartment(department);
          return this;
     }

     public Student batch(String batch) {
          setBatch(batch);
          return this;
     }

    

     @Override
     public String toString() {
          return "{" +
               " name='" + getName() + "'" +
               ", student_id='" + getStudent_id() + "'" +
               ", department='" + getDepartment() + "'" +
               ", batch='" + getBatch() + "'" +
               "}";
     }


}
