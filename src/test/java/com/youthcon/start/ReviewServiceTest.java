package com.youthcon.start;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ReviewServiceTest {

    private ReviewRepository reviewRepository = mock(ReviewRepository.class);
    private ReviewService reviewService = new ReviewService(reviewRepository);

    @Test
    void 후기_조회_성공(){
        // 준비
        given(reviewRepository.findById(1L))
                .willReturn(Optional.of(new Review(1L, "재밌어요", "010-1111-2222")));

        // 실행
        Review review = reviewService.getById(1L);

        // 검증
        assertThat(review.getId()).isEqualTo(1);
        assertThat(review.getContent()).isEqualTo("재밌어요");
        assertThat(review.getPhoneNumber()).isEqualTo("010-1111-2222");

    }

    @Test
    void 후기_조회_실패(){
        // 준비
        given(reviewRepository.findById(1000L))
                .willReturn(Optional.empty());

        // 검증
        assertThatThrownBy(() -> reviewService.getById(1000L))
                .isInstanceOf(ReviewNotFoundException.class);

    }


}