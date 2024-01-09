package com.project.Callyia.repository;

import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Schedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class DetailScheduleRepositoryTests {
    @Autowired
    DetailScheduleRepository detailScheduleRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    public void insertDetailSchedule(){
        IntStream.rangeClosed(1,150).forEach(i -> {
            long sno = (long)(Math.random()*50)+1;

            Schedule schedule = Schedule.builder().sno(sno).build();

            long totalDays = scheduleRepository.findById(schedule.getSno()).orElseThrow().getTotalDay();

            DetailSchedule detailSchedule = DetailSchedule.builder()
                    .schedule(schedule)
                    .day((long)(Math.random()*totalDays)+1)
                    .place("장소"+i)
                    .content("일정내용" + i)
                    .lat(35.15319914298131 + i)
                    .lng(129.11874363377248 + i)
                    .detailImages("이미지링크" + i)
                    .build();

            detailScheduleRepository.save(detailSchedule);
        });

    }
}