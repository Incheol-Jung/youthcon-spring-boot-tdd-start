package com.youthcon.start;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ReviewControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    void 후기_조회_성공() throws Exception {
        // 준비
        given(reviewService.getById(1L)).willReturn(new Review(1L, "재밌어요", "010-1111-2222"));

        // 실행
        ResultActions perform = mockMvc.perform(get("/reviews/1"));

        // 검증
        perform
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("content").value("재밌어요"))
                .andExpect(jsonPath("phoneNumber").value("010-1111-2222"));
    }

    @Test
    void 후기_조회_실패() throws Exception{
        // 준비
        given(reviewService.getById(1000L)).willThrow(RuntimeException.class);

        // 실행
        ResultActions perform = mockMvc.perform(get("/reviews/1000"));

        // 검증
        perform
                .andExpect(status().isNotFound());
    }



}
