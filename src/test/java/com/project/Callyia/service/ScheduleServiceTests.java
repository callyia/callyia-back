package com.project.Callyia.service;

import com.project.Callyia.dto.ScheduleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScheduleServiceTests {
    @Autowired
    private ScheduleService scheduleService;

    @Test
    public void testGet() {
        Long sno = 20L;

        ScheduleDTO scheduleDTO = scheduleService.get(sno);
        System.out.println(scheduleDTO);
    }
}