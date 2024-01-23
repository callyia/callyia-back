package com.project.Callyia.repository;

import com.project.Callyia.dto.ScheduleDTO;
import com.project.Callyia.service.ScheduleService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ScheduleRepositoryTests {

    @Autowired
    private ScheduleService scheduleService;


//    List<ScheduleDTO> result = scheduleService.getAllSchedule();
//    @Test
//    void getNickname() {
//        log.info(result);
//    }


//    @Test
//    void test() {
//        scheduleService.getSchedulesByEmailWithImage("test@t.t");
//    }
}