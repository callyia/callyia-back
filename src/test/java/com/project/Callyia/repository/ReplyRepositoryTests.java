package com.project.Callyia.repository;

import com.project.Callyia.entity.DetailSchedule;
import com.project.Callyia.entity.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
class ReplyRepositoryTests {
    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertReply(){
        IntStream.rangeClosed(1, 300).forEach(i -> {
            long dno = (long)(Math.random()*150)+1;

            DetailSchedule detailSchedule = DetailSchedule.builder().dno(dno).build();

            Reply reply = Reply.builder()
                    .replyer("replyer.." + i)
                    .replyContents("ReplyConetents.." + i)
                    .detailSchedule(detailSchedule)
                    .build();
            replyRepository.save(reply);
        });
    }
}