package com.project.Callyia.service;

import com.project.Callyia.dto.DetailScheduleDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DetailScheduleServiceTests {
    @Autowired
    private DetailScheduleService detailScheduleService;

//    @Test
//    public void testGet() {
//        Long sno =20L;
//
//        List<DetailScheduleDTO> detailScheduleDTO = detailScheduleService.getDetailSchedule(sno);
//        System.out.println(detailScheduleDTO);
//    }
}