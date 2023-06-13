package com.nodo.springverifyemail.repository;

import com.nodo.springverifyemail.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
