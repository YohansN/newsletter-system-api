package dev.yohans.models.dtos;

import jakarta.validation.constraints.Email;

public record CancelSubscription(@Email String email) {
}
