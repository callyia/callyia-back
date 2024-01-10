package com.project.Callyia.repository;

import com.project.Callyia.entity.PlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlanDetailRepository extends JpaRepository<PlanDetail, Long> {
  @Transactional
  void deleteByPlan_Pno(Long pno);

  List<PlanDetail> findByPlan_Pno(Long pno);
}
