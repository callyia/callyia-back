package com.project.Callyia.repository;

import com.project.Callyia.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s, w " +
            " FROM Schedule s LEFT JOIN s.member w " +
            " WHERE s.sno = :sno")
    Object getScheduleBySno(@Param("sno") Long sno);
}
