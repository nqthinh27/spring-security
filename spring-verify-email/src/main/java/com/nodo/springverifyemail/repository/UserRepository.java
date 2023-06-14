package com.nodo.springverifyemail.repository;

import com.nodo.springverifyemail.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from User u where u.email = :email")
    public User findByEmail(@Param("email") String email);
}
