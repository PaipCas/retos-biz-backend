package com.cesde.retosbiz.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesde.retosbiz.Dto.CompanyDto;
import com.cesde.retosbiz.Dto.UpdateCompanyDto;
import com.cesde.retosbiz.Exception.CompanyAlreadyExistException;
import com.cesde.retosbiz.Exception.CompanyNotFoundException;
import com.cesde.retosbiz.Repository.CompanyRepository;

@Service
public class CompanyService {
	private final CompanyRepository companyRepository;

	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	public List<CompanyDto> getAllCompanies() throws Exception {
		try {
			return this.companyRepository.getAllCompanies();
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public CompanyDto getCompanyById(String id) throws Exception {
		try {
			CompanyDto companyDto = this.companyRepository.getCompanyById(id);

			if (companyDto == null) {
				throw new CompanyNotFoundException(id);
			}

			return companyDto;
		} catch (CompanyNotFoundException error) {
			throw error;
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public CompanyDto createCompany(CompanyDto companyDto) throws Exception {
		try {
			if (this.companyRepository.existsByNit(companyDto.nit())) {
				throw new CompanyAlreadyExistException(companyDto.nit());
			}

			return this.companyRepository.createCompany(companyDto);
		} catch (CompanyAlreadyExistException error) {
			throw error;
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public CompanyDto updateCompany(String id, UpdateCompanyDto updateCompanyDto) throws Exception {
		try {
			if (!this.companyRepository.existsById(id)) {
				throw new CompanyNotFoundException(id);
			}

			return this.companyRepository.updateCompany(id, updateCompanyDto);
		} catch (CompanyNotFoundException error) {
			throw error;
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}

	public void deleteCompany(String id) throws Exception {
		try {
			if (!this.companyRepository.existsById(id)) {
				throw new CompanyNotFoundException(id);
			}

			this.companyRepository.deleteCompany(id);
		} catch (CompanyNotFoundException error) {
			throw error;
		} catch (Exception error) {
			throw new Exception(error.getMessage());
		}
	}
}