package com.project.Callyia.service;

import com.project.Callyia.dto.DetailScheduleDTO;
import com.project.Callyia.dto.TipDTO;
import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Schedule;
import com.project.Callyia.entity.Tour;
import com.project.Callyia.repository.DetailScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DetailScheduleServiceImpl implements DetailScheduleService{
    private final DetailScheduleRepository detailScheduleRepository;

    @Override
    public DetailSchedule dtoToEntity(DetailScheduleDTO detailScheduleDTO) {
        Schedule schedule = Schedule.builder().sno(detailScheduleDTO.getSno()).build();
        Tour tour = Tour.builder().placeId(detailScheduleDTO.getPlace_id()).build();

        DetailSchedule detailSchedule = DetailSchedule.builder()
                .tip(detailScheduleDTO.getTip())
                .detailImages(detailScheduleDTO.getDetailImages())
                .day(detailScheduleDTO.getDay())
                .schedule(schedule)
                .tour(tour)
                .build();
        return detailSchedule;
    }

    @Override
    public DetailScheduleDTO entityToDTO(DetailSchedule detailSchedule) {
        DetailScheduleDTO detailScheduleDTO = DetailScheduleDTO.builder()
                .dno(detailSchedule.getDno())
                .tip(detailSchedule.getTip())
                .detailImages(detailSchedule.getDetailImages())
                .day(detailSchedule.getDay())
                .sno(detailSchedule.getSchedule().getSno())
                .place_id(detailSchedule.getTour().getPlaceId())
                .build();
        return detailScheduleDTO;
    }




    @Override
    public List<DetailScheduleDTO> getFormSno(Long sno) {
        List<DetailSchedule> detailScheduleList = detailScheduleRepository.findBySchedule_sno(sno);
        List<DetailScheduleDTO> detailScheduleDTOList = (List<DetailScheduleDTO>) detailScheduleList.stream()
                .map(detailSchedule -> entityToDTO(detailSchedule)).collect(Collectors.toList());
        

        return detailScheduleDTOList;

    }

    @Override
    public List<DetailScheduleDTO> getAllDetailSchedule() {
        List<DetailSchedule> detailScheduleList = detailScheduleRepository.findAll();
        List<DetailScheduleDTO> detailScheduleDTOList = (List<DetailScheduleDTO>) detailScheduleList.stream()
                .map(detailSchedule -> entityToDTO(detailSchedule)).collect(Collectors.toList());

        return detailScheduleDTOList;
    }

    @Override
    public void saveDetailSchedule(List<DetailScheduleDTO> detailScheduleDTOList, Long sno) {
        for(DetailScheduleDTO dto : detailScheduleDTOList) {
            dto.setSno(sno);
        }

        List<DetailSchedule> detailScheduleList = detailScheduleDTOList.stream().map(dto -> dtoToEntity(dto)).collect(Collectors.toList());
        detailScheduleRepository.saveAll(detailScheduleList);
    }

    @Override
    public DetailScheduleDTO findDetailScheduleFirst(Long sno) {
        DetailSchedule detailSchedule = detailScheduleRepository.findFirstBySchedule_sno(sno);
        return entityToDTO(detailSchedule);
    }

    @Override
    public Page<TipDTO> getTip(String placeId, Pageable pageable) {
        Page<Object[]> detailSchedulePage = detailScheduleRepository.findByPlaceId(placeId, pageable);
        return detailSchedulePage.map(objects -> new TipDTO(
            (Long) objects[0],
            (String) objects[1],
            (String) objects[2]
        ));
    }

    @Override
    public void deleteDetailScheduleBySno(Long sno) {
        detailScheduleRepository.deleteBySchedule_sno(sno);
    }
}