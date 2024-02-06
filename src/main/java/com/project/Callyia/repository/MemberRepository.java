package com.project.Callyia.repository;

import com.project.Callyia.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
  boolean existsByEmail(String email);

  boolean existsByNickname(String nickname);

  boolean existsByPhone(String phone);

  Optional<Member> findByEmail(String email);

  Optional<Member> findByNickname(String nickname);

  Optional<Member> findByPhone(String phone);

  @EntityGraph(attributePaths = {"roleSet"}, type = EntityGraph.EntityGraphType.LOAD)
  Optional<Member> findById(String email);

  @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.email = :email AND m.phone = :phone")
  boolean NoModifyPhone(String phone, String email);

  @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.email = :email AND m.nickname = :nickname")
  boolean NoModifyNickname(String nickname, String email);
}
