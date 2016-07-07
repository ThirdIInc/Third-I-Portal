package org.paradyne.model.settings;

import org.paradyne.bean.settings.ReportSettings;
import org.paradyne.lib.ModelBase;

public class ReportSettingsModel extends ModelBase {

	public void getData(ReportSettings bean) {

		// Object addObj[][] = new Object[0][26];
		// Object addObj[] = new Object[1];
		// addObj[0] = bean.getDivCode();
		String sql = "select REP_MARGIN_LEFT, REP_MARGIN_RIGHT, REP_MARGIN_TOP, REP_MARGIN_BOTTOM, "
				+ "REP_IS_LOGO, REP_LOGO_ALIGN, REP_IS_COMPANY_NAME, REP_COMPANY_NAME_FONT_STYLE, "
				+ "REP_COMPANY_NAME_FONT_SIZE, REP_COMPANY_NAME_FONT_FACE, REP_COMPANY_NAME_ALIGN, "
				+ "REP_IS_COMPANY_ADRESS, REP_COMPANY_ADRESS_FONT_STYLE, REP_COMPANY_ADRESS_FONT_SIZE, "
				+ "REP_COMPANY_ADRESS_FONT_FACE, REP_HEADER_FONT_STYLE, REP_HEADER_SIZE, REP_HEADER_FONT_FACE, "
				+ "REP_TABLE_HEADER_FONT_STYLE, REP_TABLE_HEADER_SIZE, REP_TABLE_HEADER_FONT_FACE, "
				+ "REP_TABLE_BODY_FONT_STYLE, REP_TABLE_BODY_SIZE, REP_TABLE_BODY_FONT_FACE, REP_IS_COMPANY_NAME "
				+ "from HRMS_REPORT_CONF";
		Object[][] data = getSqlModel().getSingleResult(sql);// getQuery(3),
		// divid);
		if (data != null && data.length > 0) {

			if (checkNull(String.valueOf(data[0][0])).equals("")) {
				bean.setLeftmargin("");
			} else {
				bean.setLeftmargin(String.valueOf(data[0][0]));
			}
			if (checkNull(String.valueOf(data[0][1])).equals("")) {
				bean.setRightmargin("");
			} else {
				bean.setRightmargin(String.valueOf(data[0][1]));
			}
			if (checkNull(String.valueOf(data[0][2])).equals("")) {
				bean.setTopmargin("");
			} else {
				bean.setTopmargin(String.valueOf(data[0][2]));
			}
			if (checkNull(String.valueOf(data[0][3])).equals("")) {
				bean.setBottommargin("");
			} else {
				bean.setBottommargin(String.valueOf(data[0][3]));
			}
			if (checkNull(String.valueOf(data[0][4])).equals("")) {
				bean.setLogoapplicable("");
			} else {
				if (checkNull(String.valueOf(data[0][4])).equals("Y"))
					bean.setLogoapplicable(String.valueOf("true"));
			}
			if (checkNull(String.valueOf(data[0][5])).equals("")) {
				bean.setLogoalign("");
			} else {
				bean.setLogoalign(String.valueOf(data[0][5]));
			}
			if (checkNull(String.valueOf(data[0][6])).equals("")) {
				bean.setCompanyapplicable("");
			} else {
				if (checkNull(String.valueOf(data[0][6])).equals("Y"))
					bean.setCompanyapplicable(String.valueOf("true"));
			}
			if (checkNull(String.valueOf(data[0][7])).equals("")) {
				bean.setCompanynamefontstyle("");
			} else {
				bean.setCompanynamefontstyle(String.valueOf(data[0][7]));
			}
			if (checkNull(String.valueOf(data[0][8])).equals("")) {
				bean.setCompanynamefontsize("");
			} else {
				bean.setCompanynamefontsize(String.valueOf(data[0][8]));
			}
			if (checkNull(String.valueOf(data[0][9])).equals("")) {
				bean.setCompanynamefontface("");
			} else {
				bean.setCompanynamefontface(String.valueOf(data[0][9]));
			}
			if (checkNull(String.valueOf(data[0][10])).equals("")) {
				bean.setCompanynamefontalign("");
			} else {
				bean.setCompanynamefontalign(String.valueOf(data[0][10]));
			}
			if (checkNull(String.valueOf(data[0][11])).equals("")) {
				bean.setCompanyaddapplicable("");
			} else {
				if (checkNull(String.valueOf(data[0][11])).equals("Y"))
					bean.setCompanyaddapplicable(String.valueOf("true"));
			}
			if (checkNull(String.valueOf(data[0][12])).equals("")) {
				bean.setCompanyaddfontstyle("");
			} else {
				bean.setCompanyaddfontstyle(String.valueOf(data[0][12]));
			}
			if (checkNull(String.valueOf(data[0][13])).equals("")) {
				bean.setCompanyaddfontsize("");
			} else {
				bean.setCompanyaddfontsize(String.valueOf(data[0][13]));
			}
			if (checkNull(String.valueOf(data[0][14])).equals("")) {
				bean.setCompanyaddfontface("");
			} else {
				bean.setCompanyaddfontface(String.valueOf(data[0][14]));
			}
			if (checkNull(String.valueOf(data[0][15])).equals("")) {
				bean.setReportfontstyle("");
			} else {
				bean.setReportfontstyle(String.valueOf(data[0][15]));
			}
			if (checkNull(String.valueOf(data[0][16])).equals("")) {
				bean.setReportfontsize("");
			} else {
				bean.setReportfontsize(String.valueOf(data[0][16]));
			}
			if (checkNull(String.valueOf(data[0][17])).equals("")) {
				bean.setReportfontface("");
			} else {
				bean.setReportfontface(String.valueOf(data[0][17]));
			}
			if (checkNull(String.valueOf(data[0][18])).equals("")) {
				bean.setTableheaderfontstyle("");
			} else {
				bean.setTableheaderfontstyle(String.valueOf(data[0][18]));
			}
			if (checkNull(String.valueOf(data[0][19])).equals("")) {
				bean.setTableheaderfontsize("");
			} else {
				bean.setTableheaderfontsize(String.valueOf(data[0][19]));
			}
			if (checkNull(String.valueOf(data[0][20])).equals("")) {
				bean.setTableheaderfontface("");
			} else {
				bean.setTableheaderfontface(String.valueOf(data[0][20]));
			}
			if (checkNull(String.valueOf(data[0][21])).equals("")) {
				bean.setTablebodyfontstyle("");
			} else {
				bean.setTablebodyfontstyle(String.valueOf(data[0][21]));
			}
			if (checkNull(String.valueOf(data[0][22])).equals("")) {
				bean.setTablebodyfontsize("");
			} else {
				bean.setTablebodyfontsize(String.valueOf(data[0][22]));
			}
			if (checkNull(String.valueOf(data[0][23])).equals("")) {
				bean.setTablebodyfontface("");
			} else {
				bean.setTablebodyfontface(String.valueOf(data[0][23]));
			}
		} else {
			bean.setLeftmargin("");
			bean.setRightmargin("");
			bean.setTopmargin("");
			bean.setBottommargin("");
			bean.setLogoapplicable("");
			bean.setLogoalign("");
			bean.setCompanyapplicable("");
			bean.setCompanynamefontstyle("");
			bean.setCompanynamefontsize("");
			bean.setCompanynamefontface("");
			bean.setCompanynamefontalign("");
			bean.setCompanyaddapplicable("");
			bean.setCompanyaddfontstyle("");
			bean.setCompanyaddfontsize("");
			bean.setCompanyaddfontface("");
			bean.setReportfontstyle("");
			bean.setReportfontsize("");
			bean.setReportfontface("");
			bean.setTableheaderfontstyle("");
			bean.setTableheaderfontsize("");
			bean.setTableheaderfontface("");
			bean.setTablebodyfontstyle("");
			bean.setTablebodyfontsize("");
			bean.setTablebodyfontface("");
		}

	}

	public boolean save(ReportSettings bean) {

		boolean result = false;
		Object addObj[][] = new Object[1][24];

		try {
			/*
			 * String sql = "delete from HRMS_REPORT_CONF where DIV_ID=" +
			 * bean.getDivCode(); result = getSqlModel().singleExecute(sql);
			 */

			String sql = "update HRMS_REPORT_CONF set "
					+ "REP_MARGIN_LEFT=?, REP_MARGIN_RIGHT=?, REP_MARGIN_TOP=?, REP_MARGIN_BOTTOM=?, REP_IS_LOGO=?, "
					+ "REP_LOGO_ALIGN=?, REP_IS_COMPANY_NAME=?, REP_COMPANY_NAME_FONT_STYLE=?, "
					+ "REP_COMPANY_NAME_FONT_SIZE=?, REP_COMPANY_NAME_FONT_FACE=?, REP_COMPANY_NAME_ALIGN=?, "
					+ "REP_IS_COMPANY_ADRESS=?, REP_COMPANY_ADRESS_FONT_STYLE=?, REP_COMPANY_ADRESS_FONT_SIZE=?, "
					+ "REP_COMPANY_ADRESS_FONT_FACE=?, REP_HEADER_FONT_STYLE=?, REP_HEADER_SIZE=?, REP_HEADER_FONT_FACE=?, "
					+ "REP_TABLE_HEADER_FONT_STYLE=?, REP_TABLE_HEADER_SIZE=?, REP_TABLE_HEADER_FONT_FACE=?, "
					+ "REP_TABLE_BODY_FONT_STYLE=?, REP_TABLE_BODY_SIZE=?, REP_TABLE_BODY_FONT_FACE=? ";

			addObj[0][0] = checkNull(bean.getLeftmargin()).trim();
			addObj[0][1] = checkNull(bean.getRightmargin()).trim();
			addObj[0][2] = checkNull(bean.getTopmargin()).trim();
			addObj[0][3] = checkNull(bean.getBottommargin()).trim();
			addObj[0][4] = getCheckBoxValue(bean.getLogoapplicable()).trim();
			addObj[0][5] = checkNull(bean.getLogoalign()).trim();
			addObj[0][6] = getCheckBoxValue(bean.getCompanyapplicable()).trim();
			addObj[0][7] = checkNull(bean.getCompanynamefontstyle()).trim();
			addObj[0][8] = checkNull(bean.getCompanynamefontsize()).trim();
			addObj[0][9] = checkNull(bean.getCompanynamefontface()).trim();
			addObj[0][10] = checkNull(bean.getCompanynamefontalign()).trim();
			addObj[0][11] = getCheckBoxValue(bean.getCompanyaddapplicable())
					.trim();
			addObj[0][12] = checkNull(bean.getCompanyaddfontstyle()).trim();
			addObj[0][13] = checkNull(bean.getCompanyaddfontsize()).trim();
			addObj[0][14] = checkNull(bean.getCompanyaddfontface()).trim();
			addObj[0][15] = checkNull(bean.getReportfontstyle()).trim();
			addObj[0][16] = checkNull(bean.getReportfontsize()).trim();
			addObj[0][17] = checkNull(bean.getReportfontface()).trim();
			addObj[0][18] = checkNull(bean.getTableheaderfontstyle()).trim();
			addObj[0][19] = checkNull(bean.getTableheaderfontsize()).trim();
			addObj[0][20] = checkNull(bean.getTableheaderfontface()).trim();
			addObj[0][21] = checkNull(bean.getTablebodyfontstyle()).trim();
			addObj[0][22] = checkNull(bean.getTablebodyfontsize()).trim();
			addObj[0][23] = checkNull(bean.getTablebodyfontface()).trim();

			result = getSqlModel().singleExecute(sql, addObj);
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}

		return result;
	}

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	public String getCheckBoxValue(String sFieldValue) {
		String sResult = "";
		try {
			if ((sFieldValue != null) && (sFieldValue.equals("true")))
				sResult = "Y";
			else
				sResult = "N";

		} catch (Exception e) {
			sResult = "N";
		}
		return sResult;
	}

	public void reset(ReportSettings bean) {
		bean.setDivCode("");
		bean.setDivname("");
		bean.setTopmargin("");
		bean.setBottommargin("");
		bean.setLeftmargin("");
		bean.setRightmargin("");
		bean.setLogoapplicable("");
		bean.setLogoalign("");
		bean.setCompanyapplicable("");
		bean.setCompanynamefontface("");
		bean.setCompanynamefontstyle("");
		bean.setCompanynamefontsize("");
		bean.setCompanynamefontalign("");
		bean.setCompanyaddapplicable("");
		bean.setCompanyaddfontface("");
		bean.setCompanyaddfontstyle("");
		bean.setCompanyaddfontsize("");
		bean.setCompanyaddfontalign("");
		bean.setReportfontface("");
		bean.setReportfontstyle("");
		bean.setReportfontsize("");
		bean.setReportfontalign("");
		bean.setTableheaderfontface("");
		bean.setTableheaderfontstyle("");
		bean.setTableheaderfontsize("");
		bean.setTableheaderfontalign("");
		bean.setTablebodyfontface("");
		bean.setTablebodyfontstyle("");
		bean.setTablebodyfontsize("");
		bean.setTablebodyfontalign("");
	}
}
