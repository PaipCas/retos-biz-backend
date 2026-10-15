package com.cesde.retosbiz.Dto;

import java.time.LocalDateTime;

public record RecordDto(
		String id,
		LocalDateTime registration_date,
		String observation,
		String status,
		String user_id,
		String challenge_id) {
}