package com.youthcon.start;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void 후기_조회_성공(){
        // 준비

        // 실행
        Review review = reviewRepository.findById(1L)
                .orElseThrow(RuntimeException::new);
        // 검증
        assertThat(review.getId()).isEqualTo(1L);
        assertThat(review.getContent()).isEqualTo("재밌어요");
        assertThat(review.getPhoneNumber()).isEqualTo("010-1111-2222");
    }




}