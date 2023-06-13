package com.nodo.springverifyemail.repository;

import com.nodo.springverifyemail.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
