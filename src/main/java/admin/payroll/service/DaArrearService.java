package admin.payroll.service;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import admin.payroll.models.DAProcessModel;
import admin.payroll.models.FromToModel;
import admin.payroll.models.ResponseDTO;

@Service
public interface DaArrearService {

	@Transactional
	ResponseDTO daProcess(@Valid DAProcessModel payload);

	@Transactional
	ResponseDTO reportView(@Valid FromToModel payload);

	@Transactional
	ResponseDTO paymentStatement(@Valid FromToModel payload);

}
