package spring_security.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import spring_security.security.entity.User;

public interface UserRepo extends JpaRepository<User,Integer>{
    public User findByEmail(String emaill);

	public User findByVerificationCode(String code);
	
}
