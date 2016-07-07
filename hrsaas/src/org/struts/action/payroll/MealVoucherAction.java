package org.struts.action.payroll;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.paradyne.bean.payroll.MealVoucherBean;
import org.paradyne.model.payroll.MealVoucherModel;
import org.struts.lib.ParaActionSupport;

public class MealVoucherAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(MealVoucherAction.class);

	MealVoucherBean mealVoucher;

	public void prepare_local() throws Exception {
		try {
			// TODO Auto-generated method stub
			mealVoucher = new MealVoucherBean();
			mealVoucher.setMenuCode(1107);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return mealVoucher;
	}

	public MealVoucherBean getMealVoucher() {
		return mealVoucher;
	}

	public void setMealVoucher(MealVoucherBean mealVoucher) {
		this.mealVoucher = mealVoucher;
	}

	public void prepare_withLoginProfileDetails() throws Exception {

		try {
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			mealVoucher.setApplicationDate(sysDate);

			String fromYear = mealVoucher.getApplicationDate().substring(6, 10);
			mealVoucher.setFromYear(fromYear);

			int nextYear = Integer.parseInt(fromYear) + 1;

			mealVoucher.setToYear(String.valueOf(nextYear));

			MealVoucherModel model = new MealVoucherModel();
			model.initiate(context, session);
			model.setEmployeeDetails(mealVoucher);
			model.setMealVoucherDeclaredAmount(mealVoucher);
			model.setMealVoucherDtl(mealVoucher);

			String frequency = checkPeriodicity();
			if (frequency.equals("Y")) {
				mealVoucher.setCheckValidation("true");

			} else {
				mealVoucher.setCheckValidation("false");
			}
			// Set Declare Amt from Meal Voucher Configuaration
			model.setDeclareAmt(this.mealVoucher);

			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private String checkPeriodicity() {

		System.out
				.println("In checkPeriodicity----------------------------------------");
		String isAllowed = "N";
		try {

			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			int currentMonth = Integer.parseInt(sysDate.substring(3, 5));

			int savedMonth = 0;
			String periodicity = "";

			if (!mealVoucher.getPreviousDate().equals("")) {
				savedMonth = Integer.parseInt(mealVoucher.getPreviousDate()
						.substring(3, 5));
			}

			MealVoucherModel model = new MealVoucherModel();
			model.initiate(context, session);

			String query = "  select * from HRMS_MEAL_VOUCHER "
					+ " where MEAL_VOUCHER_EMPID="
					+ mealVoucher.getEmpCode().trim();

			Object dataObj[][] = model.getSqlModel().getSingleResult(query);

			Object creditObj[][] = model.getMealVoucherConfiguration();
			if (creditObj != null && creditObj.length > 0) {
				periodicity = String.valueOf(creditObj[0][1]);
				if (periodicity.equals("M")) {
					if (currentMonth > savedMonth) {
						isAllowed = "Y";
					}
					mealVoucher.setChangePeriodicity("Month");
					mealVoucher.setSaveFlag("false");
				}
				if (periodicity.equals("Q")) {
					if (currentMonth >= (savedMonth + 3)) {
						isAllowed = "Y";
					}
					mealVoucher.setChangePeriodicity("Quater");
					mealVoucher.setSaveFlag("false");
				}
				if (periodicity.equals("H")) {
					if (currentMonth >= (savedMonth + 6)) {
						isAllowed = "Y";
					}
					mealVoucher.setChangePeriodicity("Half Year");
					mealVoucher.setSaveFlag("false");
				}
				if (periodicity.equals("A")) {
					if (currentMonth >= (savedMonth + 12)) {
						isAllowed = "Y";
					}
					mealVoucher.setChangePeriodicity("Year");
					mealVoucher.setSaveFlag("false");
				}

			} else {
				isAllowed = "notconfigured";
			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAllowed;
	}

	public String reset() {
		try {
			// if (!mealVoucher.getCheckValidation().equals("0")) {
			mealVoucher.setMealVoucherCoupenAmt("");
			mealVoucher.setMealVoucherAmount("0");
			mealVoucher.setMealVoucherSplAllowanceAmount("0");
			mealVoucher.setCheckValidation("");
			mealVoucher.setChangePeriodicity("");
			// }

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("") || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}

	public String save() {
		try {

			MealVoucherModel model = new MealVoucherModel();
			model.initiate(context, session);

			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			String[]str=sysDate.split("-");
			int currentDate = Integer.parseInt(str[0]);
			String fromPeriod = checkNull(mealVoucher.getFromPeriod());
			String toPeriod = checkNull(mealVoucher.getToPeriod());
			String frequency = checkPeriodicity();
			if (frequency.equals("Y")) {
				mealVoucher.setCheckValidation("true");

				if (!fromPeriod.equals("") && !toPeriod.equals("")) {

					if (currentDate >= Integer.parseInt(fromPeriod)
							&& currentDate <= Integer.parseInt(toPeriod)) {
					} else {
						addActionMessage("Meal Voucher declaration is accepted within allowed date wise declaration period \n ( i.e. " + fromPeriod  + " to " +  toPeriod +" ).");
						return SUCCESS;
					}

				}

			} else if (frequency.equals("notconfigured")) {
				addActionMessage("Meal voucher setting not configured");
				mealVoucher.setCheckValidation("false");
				return SUCCESS;
			} else {

				if (currentDate >= Integer.parseInt(fromPeriod)
						&& currentDate <= Integer.parseInt(toPeriod)) {
				} else {
					addActionMessage("Meal Voucher declaration is accepted within allowed date wise declaration period.");
					return SUCCESS;
				}

				mealVoucher.setCheckValidation("false");
			}

			boolean result = model.save(mealVoucher);

			if (result) {
				addActionMessage(getMessage("save"));

			} else {
				addActionMessage(getMessage("save.error"));
			}

			model.setMealVoucherDtl(mealVoucher);
			model.terminate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

}
