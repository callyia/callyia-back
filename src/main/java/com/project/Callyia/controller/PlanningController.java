package com.project.Callyia.controller;

import com.project.Callyia.dto.PlanDTO;
import com.project.Callyia.dto.PlanDetailDTO;
import com.project.Callyia.dto.PlanRequestDTO;
import com.project.Callyia.dto.TourDTO;
import com.project.Callyia.repository.PlanRepository;
import com.project.Callyia.service.PlanDetailService;
import com.project.Callyia.service.PlanService;
import com.project.Callyia.service.TourService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/planning")
public class PlanningController {
  private final PlanService planService;
  private final PlanDetailService planDetailService;
  private final TourService tourService;

  //이따 지우고 수정할것
  private final PlanRepository planRepository;

  @GetMapping("/search")
  public ResponseEntity<List<TourDTO>> searchTour(@RequestParam String keyword) {
    log.info("keyword : " + keyword);
    List<TourDTO> tourList = tourService.getSearchTours(keyword);
    for(TourDTO dto : tourList) {
      log.info("dto : " + dto);
    }
    return new ResponseEntity<>(tourList, HttpStatus.OK);
  }

  @PostMapping("/save")
  public ResponseEntity<Long> savePlan(@RequestBody PlanRequestDTO planRequestDTO) {
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


}
