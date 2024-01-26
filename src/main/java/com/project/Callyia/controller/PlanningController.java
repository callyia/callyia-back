package com.project.Callyia.controller;

import com.project.Callyia.dto.*;
import com.project.Callyia.entity.Plan;
import com.project.Callyia.repository.PlanRepository;
import com.project.Callyia.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/planning")
public class PlanningController {
  private final PlanService planService;
  private final PlanDetailService planDetailService;
  private final TourService tourService;
  private final DetailScheduleService detailScheduleService;
  private final ScheduleService scheduleService;

  @GetMapping("/search")
  public ResponseEntity<List<TourDTO>> searchTour(@RequestParam String keyword) {
    log.info("keyword : " + keyword);
    List<TourDTO> tourList = tourService.getSearchTours(keyword);
    for(TourDTO dto : tourList) {
      log.info("dto : " + dto);
    }
    return new ResponseEntity<>(tourList, HttpStatus.OK);
  }

  @GetMapping("/getDB")
  public ResponseEntity<List<TourDTO>> getSavedDB(@RequestParam Long pno) {
    log.info("getDB: " + pno);
    List<PlanDetailDTO> planDetailDTOList = planDetailService.getFromPno(pno);
    for(PlanDetailDTO dto : planDetailDTOList) {
      log.info("getFromPno : " + dto);
    }

    List<TourDTO> tourDTOList = (List<TourDTO>) planDetailDTOList.stream()
        .map(planDetailDTO -> tourService.planDetailToTour(planDetailDTO)).collect(Collectors.toList());


    return new ResponseEntity<>(tourDTOList, HttpStatus.OK);
  }

  @GetMapping("/getDay")
  public ResponseEntity<List<Long>> getSavedDay(@RequestParam Long pno) {
    log.info("getDay: " + pno);
    List<PlanDetailDTO> planDetailDTOList = planDetailService.getFromPno(pno);
    for(PlanDetailDTO dto : planDetailDTOList) {
      log.info("getFromPno : " + dto);
    }

    List<Long> dayList = (List<Long>) planDetailDTOList.stream()
        .map(planDetailDTO -> planDetailDTO.getDay()).collect(Collectors.toList());

    return new ResponseEntity<>(dayList, HttpStatus.OK);
  }

  @GetMapping("/getPlan")
  public ResponseEntity<PlanDTO> getSavedTitle(@RequestParam Long pno) {
    log.info("GET PLAN 접근");
    PlanDTO planDTO = planService.getFromPno(pno);

    return new ResponseEntity<>(planDTO, HttpStatus.OK);
  }

  @PostMapping("/save")
  public ResponseEntity<Long> savePlan(@RequestBody PlanRequestDTO planRequestDTO) {
    log.info("SAVE 접근");
    PlanDetailDTO[] planDetailDTOs = planRequestDTO.getPlanDetailDTOs();
    PlanDTO planDTO = planRequestDTO.getPlanDTO();

    Long savedPno = planService.savePlan(planDTO);
    planDetailService.savePlanDetails(savedPno, planDetailDTOs);
    return new ResponseEntity<>(savedPno, HttpStatus.OK);
  }

  @PostMapping("/update")
  public ResponseEntity<Long> updatePlan(@RequestBody PlanRequestDTO planRequestDTO) {
    PlanDetailDTO[] planDetailDTOs = planRequestDTO.getPlanDetailDTOs();
    PlanDTO planDTO = planRequestDTO.getPlanDTO();

    log.info("planDTO : " + planDTO);
    Long savedPno = planService.updatePlan(planDTO);
    log.info("pno : " + savedPno);
    planDetailService.deleteByPno(savedPno);
    planDetailService.savePlanDetails(savedPno, planDetailDTOs);
    return new ResponseEntity<>(savedPno, HttpStatus.OK);
  }

  @PostMapping("/post")
  public ResponseEntity<Long> postPlan(@RequestBody ScheduleRequestDTO scheduleRequestDTO) {
    // 내일 와서 RequestBody List<DetailScheduleDTO>와 title 받아오는 구조로 고칠 것
    DetailScheduleDTO[] detailScheduleDTOList = scheduleRequestDTO.getDetailScheduleDTOs();
    ScheduleDTO scheduleDTO = scheduleRequestDTO.getScheduleDTO();

    for (DetailScheduleDTO dto : detailScheduleDTOList) {
      log.info(dto);
    }
    log.info(scheduleDTO);

    Long sno = scheduleService.saveSchedule(scheduleDTO);
    detailScheduleService.saveDetailSchedule(List.of(detailScheduleDTOList), sno);

    return new ResponseEntity<>(sno, HttpStatus.OK);
  }
}
