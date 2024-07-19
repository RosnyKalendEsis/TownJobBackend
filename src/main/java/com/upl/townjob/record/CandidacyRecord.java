package com.upl.townjob.record;

import java.util.UUID;

public record CandidacyRecord(String name, String mail, String motivation, UUID jobOfferId,UUID userId) {
}
