package com.likelion.likelion_project.introduce.api;

import com.likelion.likelion_project.introduce.api.dto.request.IntroduceSaveRequestDto;
import com.likelion.likelion_project.introduce.api.dto.request.IntroduceUpdateRequestDto;
import com.likelion.likelion_project.introduce.api.dto.response.IntroduceListResponseDto;
import com.likelion.likelion_project.introduce.application.IntroduceService;
import com.likelion.likelion_project.introduce.domain.Introduce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class IntroduceController {
    private final IntroduceService introduceService;

    // 게시물 저장
    @PostMapping()
    public ResponseEntity<String> introduceSave(@RequestBody IntroduceSaveRequestDto introduceSaveRequestDto) {
        introduceService.introduceSave(introduceSaveRequestDto);
        return new ResponseEntity<>("자기소개서 저장!", HttpStatus.CREATED);
    }

    // 사용자 id를 기준으로 해당 사용자가 작성한 게시글 목록 조회
    @GetMapping("/{subscriberId}")
    public ResponseEntity<IntroduceListResponseDto> myPostFindAll(@PathVariable("subscriberId") Long subscriberId) {
        IntroduceListResponseDto introduceListResponseDto = introduceService.introduceFindMember(subscriberId);
        return new ResponseEntity<>(introduceListResponseDto, HttpStatus.OK);
    }
    // 게시물 id를 기준으로 사용자가 작성한 게시물 수정
    @PatchMapping("/{introduceId}")
    public ResponseEntity<String> introduceUpdate(
            @PathVariable("introduceId") Long introduceId,
            @RequestBody IntroduceUpdateRequestDto introduceUpdateRequestDto) {
        introduceService.introduceUpdate(introduceId, introduceUpdateRequestDto);
        return new ResponseEntity<>("자기소개서 수정", HttpStatus.OK);
    }

    // 게시물 id를 기준으로 사용자가 작성한 게시물 삭제
    @DeleteMapping("/{introduceId}")
    public ResponseEntity<String> introduceDelete(
            @PathVariable("introduceId") Long introduceId) {
        introduceService.introduceDelete(introduceId);
        return new ResponseEntity<>("자기소개서 삭제", HttpStatus.OK);
    }

}
