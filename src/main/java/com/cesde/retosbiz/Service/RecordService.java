package com.cesde.retosbiz.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cesde.retosbiz.Dto.RecordDto;
import com.cesde.retosbiz.Model.RecordModel;
import com.cesde.retosbiz.Repository.RecordRepository;

@Service
public class RecordService {
	private final RecordRepository recordRepository;

	public RecordService(RecordRepository recordRepository) {
		this.recordRepository = recordRepository;
	}

	public List<RecordDto> getAllRecords() {
		return this.recordRepository.findAll().stream().map(this::toDto).toList();
	}

	public RecordDto getRecordById(String id) {
		return toDto(this.recordRepository.findById(id).orElse(null));
	}

	public List<RecordDto> getRecordsByStatus(String status) {
		return this.recordRepository.findByEstado(status).stream().map(this::toDto).toList();
	}

	public List<RecordDto> getRecordsByUser(String userId) {
		return this.recordRepository.findByUserId(userId).stream().map(this::toDto).toList();
	}

	public List<RecordDto> getRecordsByChallenge(String challengeId) {
		return this.recordRepository.findByChallengeId(challengeId).stream().map(this::toDto).toList();
	}

	public RecordDto createRecord(RecordDto recordDto) {
		RecordModel recordModel = new RecordModel();
		copyData(recordModel, recordDto);

		// Si no se envia fecha, se usa la fecha y hora actuales
		if (recordModel.getFecha_registro() == null) {
			recordModel.setFecha_registro(LocalDateTime.now());
		}
		return toDto(this.recordRepository.save(recordModel));
	}

	public RecordDto updateRecord(String id, RecordDto recordDto) {
		RecordModel recordModel = this.recordRepository.findById(id).orElse(null);

		if (recordModel == null) {
			return null;
		}
		copyData(recordModel, recordDto);
		return toDto(this.recordRepository.save(recordModel));
	}

	public boolean deleteRecord(String id) {
		if (!this.recordRepository.existsById(id)) {
			return false;
		}
		this.recordRepository.deleteById(id);
		return true;
	}

	// Conversion entre Model y Dto
	private RecordDto toDto(RecordModel recordModel) {
		if (recordModel == null) {
			return null;
		}
		return new RecordDto(
				recordModel.getId(),
				recordModel.getFecha_registro(),
				recordModel.getObservacion(),
				recordModel.getEstado(),
				recordModel.getId_usuario(),
				recordModel.getId_reto());
	}

	private void copyData(RecordModel recordModel, RecordDto recordDto) {
		// La fecha solo se cambia si viene en la peticion (asi no se borra al modificar)
		if (recordDto.registration_date() != null) {
			recordModel.setFecha_registro(recordDto.registration_date());
		}
		recordModel.setObservacion(recordDto.observation());
		recordModel.setEstado(recordDto.status());
		recordModel.setId_usuario(recordDto.user_id());
		recordModel.setId_reto(recordDto.challenge_id());
	}
}