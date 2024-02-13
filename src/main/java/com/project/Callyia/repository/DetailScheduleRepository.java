package com.project.Callyia.repository;

import com.project.Callyia.entity.DetailSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface DetailScheduleRepository extends JpaRepository<DetailSchedule, Long> {

    //JPA에서 제공하는 키워드 findBy
    //findBy 뒤에는 실제 엔티티와 대응하는 이름으로 해야함.
    List<DetailSchedule> findBySchedule_sno(Long sno);

    DetailSchedule findFirstBySchedule_sno(Long sno);

    @Query("SELECT s.sno, ds.tip, m.nickname FROM DetailSchedule ds JOIN ds.schedule s JOIN s.member m WHERE ds.tour.placeId = :placeId")
    Page<Object[]> findByPlaceId(String placeId, Pageable pageable);

    void deleteBySchedule_sno(Long sno);
}