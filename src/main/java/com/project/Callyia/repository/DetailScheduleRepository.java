package com.project.Callyia.repository;

import com.project.Callyia.entity.DetailSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DetailScheduleRepository extends JpaRepository<DetailSchedule, Long> {
    @Query("SELECT d, s " +
            " FROM DetailSchedule d LEFT JOIN d.sno s " +
            " WHERE s.sno = :sno")
    List<Object[]> getDetailScheduleBySno(@Param("sno") Long sno);
}