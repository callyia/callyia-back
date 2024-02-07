package com.project.Callyia.service;

import com.project.Callyia.dto.ReplyDTO;
import com.project.Callyia.entity.Reply;

import java.util.List;

public interface ReplyService {

    //entityToDTO
    ReplyDTO entityToDTO(Reply reply);

    //dtoToEntity
    Reply dtoToEntity(ReplyDTO replyDTO);

    List<ReplyDTO> getFormDno(Long dno);


    //댓글 새로고침

    //댓글 등록
    long register(ReplyDTO replyDTO);

    //댓글 수정
    void modify(ReplyDTO replyDTO);

    //댓글 삭제
    void remove(Long rno);

  void deleteReplyByEmail(String email);
}
