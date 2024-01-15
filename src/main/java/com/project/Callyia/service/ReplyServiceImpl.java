package com.project.Callyia.service;

import com.project.Callyia.dto.DetailScheduleDTO;
import com.project.Callyia.dto.ReplyDTO;
import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Member;
import com.project.Callyia.entity.Reply;
import com.project.Callyia.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepository;

    @Override
    public Reply dtoToEntity(ReplyDTO replyDTO) {
        DetailSchedule detailSchedule = DetailSchedule.builder().dno(replyDTO.getDetailSchedule()).build();
        Member member = Member.builder().email(replyDTO.getMember()).build();

        Reply reply = Reply.builder()
                .rno(replyDTO.getRno())
                .replyContents(replyDTO.getReplyContents())
                .detailSchedule(detailSchedule)
                .member(member)
                .build();
        return reply;
    }

    @Override
    public ReplyDTO entityToDTO(Reply reply) {
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(reply.getRno())
                .replyContents(reply.getReplyContents())
                .detailSchedule(reply.getDetailSchedule().getDno())
                .member(reply.getMember().getEmail())
                .build();
        return replyDTO;
    }

    @Override
    public List<ReplyDTO> getFormDno(Long dno) {
        List<Reply> replyList = replyRepository.findByDetailSchedule_dno(dno);
        List<ReplyDTO> replyDTOList = (List<ReplyDTO>) replyList.stream()
                .map(reply -> entityToDTO(reply)).collect(Collectors.toList());

        return replyDTOList;
    }


    @Override
    public long register(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
        return reply.getRno();
    }

    @Override
    public void modify(ReplyDTO replyDTO) {
        Reply reply = dtoToEntity(replyDTO);
        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }
}
