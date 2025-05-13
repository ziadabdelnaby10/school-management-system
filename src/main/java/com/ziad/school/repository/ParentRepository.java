package com.ziad.school.repository;

import com.ziad.school.model.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ParentRepository extends JpaRepository<Parent, UUID> {
    Optional<Parent> findByEmail(String email);
}