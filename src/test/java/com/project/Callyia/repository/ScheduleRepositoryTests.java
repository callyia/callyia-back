package com.project.Callyia.repository;

import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Schedule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootTest
public class ScheduleRepositoryTests {
    @Autowired
    private ScheduleRepository scheduleRepository;



    @Test
    public void insertSchedule(){
        IntStream.rangeClosed(1,50).forEach(i ->{
            Member member = Member.builder().id("asdasd" + i).build();
            long randomTotalDay = (long)(Math.random()*20)+1 ; // 1에서 20까지의 랜덤한 수 생성

            Schedule schedule = Schedule.builder()
                    .sName(i + "번 여행")
                    .totalDay(randomTotalDay)
                    .member(member)
                    .build();

            scheduleRepository.save(schedule);
        });
    }

    @Test
    public void testRead() {
        Object result = scheduleRepository.getScheduleBySno(20L);
        Object[] arr = (Object[]) result;

        System.out.println(Arrays.toString(arr));
    }
}
