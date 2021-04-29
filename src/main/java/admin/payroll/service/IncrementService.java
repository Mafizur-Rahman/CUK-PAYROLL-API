package admin.payroll.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import admin.payroll.entity.IncrementMainEntity;
import admin.payroll.models.CodeModel;
import admin.payroll.models.DniModel;
import admin.payroll.models.ResponseDTO;

@Service
public interface IncrementService {

	@Transactional
	ResponseDTO annualIncrementProducer(@Valid DniModel payload);
	
	@Transactional
	ResponseDTO getAllIncrementMain();
	
	@Transactional
	ResponseDTO getIncrementMainByClass(@Valid IncrementMainEntity payload);
}
