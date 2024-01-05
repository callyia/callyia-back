package com.project.Callyia.repository;

import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Schedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.stream.IntStream;

@SpringBootTest
public class ScheduleRepositoryTests {
    @Autowired
    private ScheduleRepositoryTests scheduleRepositoryTests;



    @Test
    public void insertSchedule(){
        IntStream.rangeClosed(1,10).forEach(i ->{
            Schedule schedule = Schedule.builder()
                    .sName(i + "번 여행스케줄")

                    .build();
        });
    }
}
