package com.project.Callyia.service;


import com.project.Callyia.dto.ReplyDTO;
import com.project.Callyia.dto.ScheduleDTO;
import com.project.Callyia.dto.ScheduleStarDTO;
import com.project.Callyia.entity.Reply;
import com.project.Callyia.entity.Schedule;
import com.project.Callyia.entity.ScheduleStar;
import com.project.Callyia.repository.ScheduleStarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ScheduleStarServiceImpl implements ScheduleStarService{

    private final ScheduleStarRepository scheduleStarRepository;

    @Override
    public List<ScheduleStarDTO> getScheduleStarList(Long sno) {
        List<ScheduleStar> ScheduleStarList = scheduleStarRepository.findBySchedule_sno(sno);
        List<ScheduleStarDTO> ScheduleStarDTOList = (List<ScheduleStarDTO>) ScheduleStarList.stream()
                .map(scheduleStar -> entityToDTO(scheduleStar)).collect(Collectors.toList());
        return ScheduleStarDTOList;
    }

    @Override
    public long registerStar(ScheduleStarDTO scheduleStarDTO) {
        ScheduleStar scheduleStar = dtoToEntity(scheduleStarDTO);
        scheduleStarRepository.save(scheduleStar);
        return scheduleStar.getStarNum();
    }

    @Override
    public ScheduleStarDTO getStarMember(String email, Long sno) {
        ScheduleStar scheduleStar = scheduleStarRepository.findByMember_emailAndSchedule_sno(email, sno);
        return entityToDTO(scheduleStar);
    }

    @Override
    public void modify(ScheduleStarDTO scheduleStarDTO) {
        ScheduleStar scheduleStar = scheduleStarRepository.findById(scheduleStarDTO.getStarNum()).orElse(null);

        scheduleStar.setStarScore(scheduleStarDTO.getStarScore());
        scheduleStarRepository.save(scheduleStar);
    }

    @Override
    public List<ScheduleStarDTO> getAllStar() {
        List<ScheduleStar> scheduleStarList = scheduleStarRepository.findAll();
        List<ScheduleStarDTO> scheduleStarDTOList = (List<ScheduleStarDTO>) scheduleStarList.stream()
                .map(scheduleStar -> entityToDTO(scheduleStar)).collect(Collectors.toList());

        return scheduleStarDTOList;
    }
}
