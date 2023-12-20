package com.foody.foody.repository;




import com.foody.foody.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserById(Long id);

    User findByUsername(String name);

    User findByEmail(String email);
}