package admin.payroll.models;

import java.util.Date;

import lombok.Data;

@Data
public class SaveEDCodeModel {

	private String earningDeduction;

	private String shortDesc;

	private String longDesc;

	private int allocation;

	private String payCommission;

	private String isItProj;

	private String isItHead;

	private String itHead;

	private String daCalculation;

	private String grossPt;

	private String grossBonus;

	private String grossPf;

	private String gross;

	private String carry;

	private String localityClass;

	private String basicDependent;

	private String praRateType;

	private String rateToBeEntered;

	private String miscellaneous;

	private String installment;

	private String fixed;

	private String active;

	private String logUser;

	private String logIp;

}
