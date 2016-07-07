package org.paradyne.model.admin.srd;

import java.util.ArrayList;

import org.paradyne.bean.admin.srd.ProfReferences;
import org.paradyne.lib.ModelBase;

public class ProfReferencesModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ProfReferencesModel.class);

	public String add(ProfReferences bean) {
		try {
			Object addObj[][] = new Object[1][19];
			addObj[0][0] = bean.getEmpId();
			addObj[0][1] = bean.getProfFname();
			addObj[0][2] = bean.getProfMname();
			addObj[0][3] = bean.getProfLname();
			addObj[0][4] = bean.getAddress1();
			addObj[0][5] = bean.getAddress2();
			addObj[0][6] = bean.getAddress3();
			addObj[0][7] = bean.getPhoneNo();
			addObj[0][8] = bean.getMobileNo();
			addObj[0][9] = bean.getOccupation();
			addObj[0][10] = bean.getExtension();
			addObj[0][11] = bean.getFaxNo();
			addObj[0][12] = bean.getEmailId();
			addObj[0][13] = bean.getCityName();
			addObj[0][14] = bean.getCompanyName();
			addObj[0][15] = bean.getPinCode();
			addObj[0][16] = bean.getOtherInfo();
			addObj[0][17] = bean.getStateName();
			addObj[0][18] = bean.getCountryName();
			boolean result = getSqlModel().singleExecute(getQuery(1), addObj);
			if (result) {
				return "Record saved Successfully";
			} else
				return "Record can't be added.Duplicate record Found.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Record can't be added";
		}
	}

	public String mod(ProfReferences bean) {
		try {
			Object modObj[][] = new Object[1][20];
			modObj[0][0] = bean.getEmpId();
			modObj[0][1] = bean.getProfFname();
			modObj[0][2] = bean.getProfMname();
			modObj[0][3] = bean.getProfLname();
			modObj[0][4] = bean.getAddress1();
			modObj[0][5] = bean.getAddress2();
			modObj[0][6] = bean.getAddress3();
			modObj[0][7] = bean.getPhoneNo();
			modObj[0][8] = bean.getMobileNo();
			modObj[0][9] = bean.getOccupation();
			modObj[0][10] = bean.getExtension(); // bean.getProfRefId();
			modObj[0][11] = bean.getFaxNo();
			modObj[0][12] = bean.getEmailId();
			modObj[0][13] = bean.getPinCode();
			modObj[0][14] = bean.getCompanyName();
			modObj[0][15] = bean.getOtherInfo();
			modObj[0][16] = bean.getCountryName();
			modObj[0][17] = bean.getCityName();
			modObj[0][18] = bean.getStateName();
			modObj[0][19] = bean.getParaId();
			boolean result = getSqlModel().singleExecute(getQuery(4), modObj);
			if (result) {
				return "Record updated Successfully";
			} else {
				return "Record can't be updated.Duplicate record Found.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Record can't be updated";
		}
	}

	public boolean deleteProfRecord(ProfReferences bean) {
		try {
			Object obj[][] = new Object[1][1];
			obj[0][0] = bean.getParaId();
			return getSqlModel().singleExecute(getQuery(3), obj);
		} catch (Exception e) {
			return false;
		}
	}

	public void profReferencesRecord(ProfReferences profbean, String EmpId) {
		if (EmpId != null) {
			Object[] empObj = new Object[1];
			empObj[0] = EmpId;
			ArrayList<ProfReferences> list = new ArrayList<ProfReferences>();
			Object[][] data = getSqlModel()
					.getSingleResult(getQuery(2), empObj);
			if (data != null && data.length > 0) {
				profbean.setNewFlag("true");
				profbean.setNoData("false");
				for (int i = 0; i < data.length; i++) {
					ProfReferences bean = new ProfReferences();
					bean.setEmpId(EmpId);
					bean.setProfrefName(checkNull(String.valueOf(data[i][0])));
					bean.setProfMname(checkNull(String.valueOf(data[i][1])));
					bean.setProfLname(checkNull(String.valueOf(data[i][2])));
					bean.setAddress1(checkNull(String.valueOf(data[i][3])));
					if (bean.getAddress1().length() > 25) {
						bean.setAbbrAdd1(bean.getAddress1().substring(0, 24)
								+ "...");
					} else {
						bean.setAbbrAdd1(bean.getAddress1());
					}
					bean.setAddress2(checkNull(String.valueOf(data[i][4])));
					if (bean.getAddress2().length() > 25) {
						bean.setAbbrAdd2(bean.getAddress2().substring(0, 24)
								+ "...");
					} else {
						bean.setAbbrAdd2(bean.getAddress2());
					}
					bean.setAddress3(checkNull(String.valueOf(data[i][5])));
					if (bean.getAddress3().length() > 25) {
						bean.setAbbrAdd1(bean.getAddress3().substring(0, 24)
								+ "...");
					} else {
						bean.setAbbrAdd3(bean.getAddress3());
					}
					bean.setPhoneNo(checkNull(String.valueOf(data[i][6])));
					bean.setMobileNo(checkNull(String.valueOf(data[i][7])));
					bean.setOccupation(checkNull(String.valueOf(data[i][8])));
					if (bean.getOccupation().length() > 25) {
						bean.setAbbrOccupation(bean.getOccupation().substring(
								0, 24)
								+ "...");
					} else {
						bean.setAbbrOccupation(bean.getOccupation());
					}
					bean.setProfRefId(checkNull(String.valueOf(data[i][9])));
					bean.setExtension(checkNull(String.valueOf(data[i][10])));
					bean.setFaxNo(checkNull(String.valueOf(data[i][11])));
					bean.setEmailId(checkNull(String.valueOf(data[i][12])));
					if (bean.getEmailId().length() > 25) {
						bean.setAbbrEmailId(bean.getEmailId().substring(0, 24)+ "...");
					} else {
						bean.setAbbrEmailId(bean.getEmailId());
					}
					bean.setPinCode(checkNull(String.valueOf(data[i][13])));
					bean.setCompanyName(checkNull(String.valueOf(data[i][14])));
					if (bean.getCompanyName().length() > 25) {
						bean.setAbbrCompany(bean.getCompanyName().substring(0,
								24)
								+ "...");
					} else {
						bean.setAbbrCompany(bean.getCompanyName());
					}
					bean.setOtherInfo(checkNull(String.valueOf(data[i][15])));
					if (bean.getOtherInfo().length() > 25) {
						bean.setAbbrOtherInfo(bean.getOtherInfo().substring(0,
								24)
								+ "...");
					} else {
						bean.setAbbrOtherInfo(bean.getOtherInfo());
					}
					bean.setCountryName(checkNull(String.valueOf(data[i][16])));
					bean.setCityName(checkNull(String.valueOf(data[i][17])));
					bean.setStateName(checkNull(String.valueOf(data[i][18])));
					list.add(bean);
				}
				profbean.setProfList(list);
			} else {
				profbean.setNoData("true");
				profbean.setProfList(null);
			}
		}
	}

	public void callProfRecord(ProfReferences bean) {
		try {
			Object[] paramObj = new Object[1];
			paramObj[0] = bean.getParaId();
			Object[][] data = getSqlModel().getSingleResult(getQuery(5),
					paramObj);
			if (data != null && data.length > 0) {
				bean.setProfFname(checkNull(String.valueOf(data[0][0])));
				bean.setProfMname(checkNull(String.valueOf(data[0][1])));
				bean.setProfLname(checkNull(String.valueOf(data[0][2])));
				bean.setAddress1(checkNull(String.valueOf(data[0][3])));
				bean.setAddress2(checkNull(String.valueOf(data[0][4])));
				bean.setAddress3(checkNull(String.valueOf(data[0][5])));
				bean.setPhoneNo(checkNull(String.valueOf(data[0][6])));
				bean.setMobileNo(checkNull(String.valueOf(data[0][7])));
				bean.setOccupation(checkNull(String.valueOf(data[0][8])));
				bean.setProfRefId(checkNull(String.valueOf(data[0][9])));
				bean.setExtension(checkNull(String.valueOf(data[0][10])));
				bean.setFaxNo(checkNull(String.valueOf(data[0][11])));
				bean.setEmailId(checkNull(String.valueOf(data[0][12])));
				bean.setPinCode(checkNull(String.valueOf(data[0][13])));
				bean.setCompanyName(checkNull(String.valueOf(data[0][14])));
				bean.setOtherInfo(checkNull(String.valueOf(data[0][15])));
				bean.setCountryName(checkNull(String.valueOf(data[0][16])));
				bean.setCityName(checkNull(String.valueOf(data[0][17])));
				bean.setStateName(checkNull(String.valueOf(data[0][18])));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public void getImage(ProfReferences profbean) {
		try {
			String query = "SELECT EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') FROM HRMS_EMP_OFFC WHERE EMP_ID="
					+ profbean.getEmpId();
			Object[][] myPics = getSqlModel().getSingleResult(query);
			profbean.setUploadFileName(String.valueOf(myPics[0][0]));
			profbean.setProfileEmpName(String.valueOf(myPics[0][1]) + " "
					+ String.valueOf(myPics[0][2]) + " "
					+ String.valueOf(myPics[0][3]));
			System.out
					.println("persDetail.setUploadFileName( String.valueOf(myPics[0][0]))----"
							+ profbean.getUploadFileName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
