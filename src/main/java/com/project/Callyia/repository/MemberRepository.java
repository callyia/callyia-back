package com.project.Callyia.repository;

import com.project.Callyia.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
  boolean existsByEmail(String email);
  Optional<Member> findByEmail(String email);
}
