package com.project.Callyia.repository;

import com.project.Callyia.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s, w " +
            " FROM Schedule s LEFT JOIN s.member w " +
            " WHERE s.sno = :sno")
    Object getScheduleBySno(@Param("sno") Long sno);

    @Query("SELECT s, m.nickname " +
            "FROM Schedule s " +
            "JOIN s.member m")
    List<Object[]> getNickname();

    List<Schedule> findByMember_email(String email);

//    @Query("SELECT s.sno, s.total_day, s.member_email, s.s_name, d.detail_images FROM schedule s INNER JOIN detail_schedule d ON s.sno = d.schedule_sno WHERE s.member_email=':email' GROUP BY s.sno")
//    List<Schedule> getScheduleWithImage(String email);
}
