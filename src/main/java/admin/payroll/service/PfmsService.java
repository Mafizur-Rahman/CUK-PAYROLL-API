package admin.payroll.service;

import javax.validation.Valid;

import org.springframework.transaction.annotation.Transactional;

import admin.payroll.models.ResponseDTO;
import admin.payroll.models.SavePfmsTempModel;

public interface PfmsService {

	@Transactional
	ResponseDTO uploadDbt01ToTemp(@Valid SavePfmsTempModel payload);

	@Transactional
	ResponseDTO validateDbt01();

}
