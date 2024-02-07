package com.project.Callyia.repository;

import com.project.Callyia.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByDetailSchedule_dno(Long dno);

  void deleteByMember_email(String email);
}
