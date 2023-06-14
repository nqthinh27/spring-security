package com.nodo.springverifyemail.repository;

import com.nodo.springverifyemail.entity.ActivationKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivationKeyRepository extends JpaRepository<ActivationKey, Long> {
    public ActivationKey findActivationKeyByUserId(Long userId);
    public ActivationKey findByActiveKey(String key);
}
