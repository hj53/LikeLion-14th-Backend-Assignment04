package com.likelion.likelion_project.introduce.application;

import com.likelion.likelion_project.introduce.api.dto.request.IntroduceSaveRequestDto;
import com.likelion.likelion_project.introduce.api.dto.request.IntroduceUpdateRequestDto;
import com.likelion.likelion_project.introduce.api.dto.response.IntroduceInfoResponseDto;
import com.likelion.likelion_project.introduce.api.dto.response.IntroduceListResponseDto;
import com.likelion.likelion_project.introduce.domain.Introduce;
import com.likelion.likelion_project.introduce.domain.repository.IntroduceRepository;
import com.likelion.likelion_project.subscriber.domain.Subscriber;
import com.likelion.likelion_project.subscriber.domain.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IntroduceService {
    private final SubscriberRepository subscriberRepository;
    private final IntroduceRepository introduceRepository;

    // 게시물 저장
    @Transactional
    public void introduceSave(IntroduceSaveRequestDto introduceSaveRequestDto) {
        Subscriber subscriber = subscriberRepository.findById(introduceSaveRequestDto.subscribeId()).orElseThrow(IllegalArgumentException::new);

        Introduce introduce = Introduce.builder()
                .title(introduceSaveRequestDto.title())
                .contents(introduceSaveRequestDto.contents())
                .subscriber(subscriber)
                .build();

        introduceRepository.save(introduce);
    }

    public IntroduceListResponseDto introduceFindMember(Long subscribeId) {
        Subscriber subscriber = subscriberRepository.findById(subscribeId).orElseThrow(IllegalArgumentException::new);

        List<Introduce> posts = introduceRepository.findBySubscriber(subscriber);
        List<IntroduceInfoResponseDto> introduceInfoResponseDtos = posts.stream()
                .map(IntroduceInfoResponseDto::from)
                .toList();

        return IntroduceListResponseDto.from(introduceInfoResponseDtos);
    }

    // 게시물 수정
    @Transactional
    public void introduceUpdate(Long introduceId, IntroduceUpdateRequestDto introduceUpdateRequestDto) {
        Introduce introduce = introduceRepository.findById(introduceId)
                .orElseThrow(IllegalArgumentException::new);

        introduce.update(introduceUpdateRequestDto);
    }

    // 게시물 삭제
    @Transactional
    public void introduceDelete(Long introduceId) {
        Introduce introduce= introduceRepository.findById(introduceId)
                .orElseThrow(IllegalArgumentException::new);

        introduceRepository.delete(introduce);  //spring data jpa 기본구현 메서드
    }

}