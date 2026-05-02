package com.likelion.likelion_project.introduce.api.dto.request;

public record IntroduceSaveRequestDto(
        Long subscribeId,
        String title,
        String contents
) {
}
