package com.project.Callyia.repository;

import com.project.Callyia.dto.ScheduleStarDTO;
import com.project.Callyia.entity.ScheduleStar;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface ScheduleStarRepository extends JpaRepository<ScheduleStar, Long> {


    List<ScheduleStar> findBySchedule_sno(Long sno);

    ScheduleStar findByMember_emailAndSchedule_sno(String email, Long sno);
}
