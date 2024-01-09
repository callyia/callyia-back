package com.project.Callyia.controller;

import com.project.Callyia.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/Schedule/")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

}
