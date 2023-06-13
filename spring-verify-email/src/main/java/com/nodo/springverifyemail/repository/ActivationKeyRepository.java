package com.nodo.springverifyemail.repository;

import com.nodo.springverifyemail.entity.ActivationKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivationKeyRepository extends JpaRepository<ActivationKey, Long> {
}
