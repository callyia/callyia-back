package com.project.Callyia.repository;

import com.project.Callyia.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
  List<Plan> findByMember_Email(String email);
}
