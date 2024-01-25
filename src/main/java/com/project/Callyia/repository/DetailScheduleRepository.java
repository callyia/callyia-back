package com.project.Callyia.repository;

import com.project.Callyia.entity.DetailSchedule;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface DetailScheduleRepository extends JpaRepository<DetailSchedule, Long> {

    //JPA에서 제공하는 키워드 findBy
    //findBy 뒤에는 실제 엔티티와 대응하는 이름으로 해야함.
    List<DetailSchedule> findBySchedule_sno(Long sno);

    DetailSchedule findFirstBySchedule_sno(Long sno);
}