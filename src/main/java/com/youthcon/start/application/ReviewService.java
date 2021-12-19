package com.youthcon.start.application;

import com.youthcon.start.DuplicateSendGiftException;
import com.youthcon.start.SendGiftInternalException;
import com.youthcon.start.domain.Review;
import com.youthcon.start.errors.ReviewNotFoundException;
import com.youthcon.start.infra.GiftApi;
import com.youthcon.start.infra.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private final GiftApi giftApi;
    private final ReviewRepository reviewRepository;

    public ReviewService(GiftApi giftApi, ReviewRepository reviewRepository) {
        this.giftApi = giftApi;
        this.reviewRepository = reviewRepository;
    }

    @Transactional(readOnly = true)
    public Review getById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("no review id : " + id));
    }

    @Transactional
    public Review sendGift(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("no review id : " + id));

        if (review.getSent()){
            throw new DuplicateSendGiftException();
        }

        if (!giftApi.send(review.getPhoneNumber())){
            throw new SendGiftInternalException();
        }

        review.makeTrue();

        return review;
    }
}
