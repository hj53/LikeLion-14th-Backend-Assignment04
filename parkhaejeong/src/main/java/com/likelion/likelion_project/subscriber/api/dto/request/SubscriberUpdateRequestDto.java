package com.likelion.likelion_project.subscriber.api.dto.request;

public record SubscriberUpdateRequestDto(
        String name,
        int age,
        String residence
) {
}
