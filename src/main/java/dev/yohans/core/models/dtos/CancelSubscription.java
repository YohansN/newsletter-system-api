package dev.yohans.core.models.dtos;

import jakarta.validation.constraints.Email;

public record CancelSubscription(@Email String email) {
}
