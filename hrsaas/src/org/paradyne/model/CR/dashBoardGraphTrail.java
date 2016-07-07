package org.paradyne.model.CR;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.paradyne.bean.CR.DashBoard;
import org.paradyne.lib.ModelBase;

import antlr.collections.impl.Vector;

public class dashBoardGraphTrail extends ModelBase {

	static Logger logger = Logger.getLogger(dashBoardGraphTrail.class);
	DashBoard dashBoard;

	public void getResultbyComponentId(String CompnentID,
			HttpServletRequest request, String dashboardID, String nextGraph,
			DashBoard bean, String graphValue, String PreGarphValue) {
		try {
			System.out.println("Componet ID is" + CompnentID);
			System.out.println("Graph Value is" + graphValue);
			Object[][] parameter = (Object[][]) null;
			Object[][] tableData = (Object[][]) null;
			String[] Chartquery1 = new String[6];
			List chartQyery = new ArrayList();

			if (nextGraph != null && !nextGraph.equals("")) {

				parameter = getSqlModel("POOL_D1").getSingleResult(getQuery(1),
						new Object[] { CompnentID });
				request.setAttribute("tableHeader", String
						.valueOf(parameter[0][1]));
				bean.setComponentName(String.valueOf(parameter[0][1]));
				if (String.valueOf(parameter[0][2]).equals("T")) {
					String query = String.valueOf(parameter[0][0]);
					query
							.replace("'<pdate>'",
									" upper(replace(convert(char(11),getdate()-7,113),' ','-')) ");
					query = query.replace("<location>", graphValue);
					query = query.replace("<devicetype>", "");
					query = query.replace("<csm>", "");
					tableData = getTableCellData(query);
				}
				int nextGraph1 = Integer.parseInt(nextGraph);
				nextGraph1 = 1;
				if (graphValue == null) {
					graphValue = "";
				}
				chartQyery.add(getChartInfoTrial((String) parameter[0][0], 1,
						request, String.valueOf(parameter[0][2]), String
								.valueOf(nextGraph1), graphValue));
				int graphLevel = Integer.parseInt(nextGraph);
				for (int i = 1; graphLevel > i; i++) {
					if ((String) parameter[0][3] != null) {
						Object nextParaQuery[][] = getSqlModel("POOL_D1")
								.getSingleResult(
										getQuery(1),
										new Object[] { (String) parameter[0][3] });
						if (String.valueOf(nextParaQuery[0][2]).equals("G")) {
							String query = String.valueOf(nextParaQuery[0][0]);
							query
									.replace("'<pdate>'",
											" upper(replace(convert(char(11),getdate()-7,113),' ','-')) ");
							if (PreGarphValue != null
									&& !PreGarphValue.equals("")) {// for
								// second
								// dril down
								// because
								// dont want
								// to
								// replace
								// second
								// dril down
								query = query.replace("<location>",
										PreGarphValue);
							} else {
								query = query.replace("<location>", graphValue); // graphValue
								// privous
								// graph
								// value
							}
							query = query.replace("<devicetype>", "");
							query = query.replace("<csm>", "");
							chartQyery.add(getChartInfoTrial(query, 1, request,
									String.valueOf(parameter[0][2]), String
											.valueOf(i + 1), graphValue));
						} else {
							String query = String.valueOf(nextParaQuery[0][0]);
							query
									.replace("'<pdate>'",
											" upper(replace(convert(char(11),getdate()-7,113),' ','-')) ");
							query = query.replace("<location>", graphValue);
							query = query.replace("<devicetype>", "");
							query = query.replace("<csm>", "");
							tableData = getTableCellData(query);
						}

						if (nextParaQuery[0][3] != null) {
							parameter[0][3] = nextParaQuery[0][3];
						} else {
							break;
						}
					}// end for

				}// if end
				request.setAttribute("Chartquery", chartQyery);
				request.setAttribute("tableData", tableData);
			} else {
				parameter = getSqlModel("POOL_D1").getSingleResult(getQuery(1),
						new Object[] { CompnentID });

				request.setAttribute("tableHeader", String
						.valueOf(parameter[0][1]));
				chartQyery.add(getChartInfoTrial((String) parameter[0][0], 1,
						request, String.valueOf(parameter[0][2]), nextGraph,
						graphValue));
				// request.setAttribute("tableData", tableData);
				request.setAttribute("Chartquery", chartQyery);

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public Object[][] getTableCellData(String query) {
		/*
		 * query = query.replace("'<pdate>'", "
		 * upper(replace(convert(char(11),getdate()-7,113),' ','-')) ");
		 * query=query.replace("<location>", ""); query=query.replace("<devicetype>",
		 * ""); query=query.replace("<csm>", "");
		 */
		Object[][] Queryobj = getSqlModel("POOL_D1").getSingleResultWithCol(
				query);

		return Queryobj;
	}

	public String getChartInfoTrial(String Query, int displayNum,
			HttpServletRequest request, String chartType, String nextGraph,
			String graphValue) {
		String column3DQuery = Query;
		Object[][] column3DMap = getSqlModel("POOL_D1").getSingleResultWithCol(
				column3DQuery);
		Object[][] seriesHeader = new Object[column3DMap.length][column3DMap[0].length];
		// this loop for series header

		for (int r = 0; column3DMap.length > r; r++) {
			for (int c = 0; column3DMap[0].length > c; c++) {
				seriesHeader[r][c] = column3DMap[0][c];
			}
		}

		String replace = "";
		String column3DCharts = "";
		if (chartType.trim().equals("Pie2D")) {
			column3DCharts = "<chart enableSmartLabels='0' shownames='0' showValues='1' bgColor='ffffff,ffffff' showBorder='0' >";
			if ((column3DMap != null) && (column3DMap.length > 0)) {
				for (int i = 0; i < column3DMap[0].length; i++) {
					column3DCharts = column3DCharts + "<set label='"
							+ String.valueOf(column3DMap[0][i]) + "' value='"
							+ String.valueOf(column3DMap[1][i]) + "'/>";
				}
			}
			column3DCharts = column3DCharts + "</chart>";
			replace = column3DCharts.replace("null", "");
		} else {
			if (displayNum > 0) {
				column3DCharts = "<chart showvalues='1' legendAllowDrag='1' rotateNames='1' shownames='1' decimals='0' slantLabels='1' numberPrefix='' labelDisplay='WRAP' showExportDataMenuItem='1' exportEnabled='1' exportAtClient='1' exportHandler='C:\\img' html5ExportHandler='C:\\img'>";
			} else {
				column3DCharts = "<chart  showvalues='0' legendAllowDrag='1' rotateNames='1' shownames='1' decimals='0' slantLabels='1' numberPrefix='' labelDisplay='WRAP' showExportDataMenuItem='1' exportEnabled='1' exportAtClient='1' exportHandler='C:\\img' html5ExportHandler='C:\\img'>";
			}
			column3DQuery = column3DQuery
					.replace("'<pdate>'",
							" upper(replace(convert(char(11),getdate()-7,113),' ','-')) ");

			String categories = "<categories>";
			String dataset = "";
			String[] chartStr = new String[column3DMap[0].length];
			String[] datasetHdr = new String[column3DMap[0].length];

			for (int i = 0; i < column3DMap.length; i++) {
				if (i == 0)
					for (int j = 0; j < column3DMap[0].length; j++) {
						System.out.println("--------header----"
								+ String.valueOf(column3DMap[i][j]));
						datasetHdr[j] = String.valueOf(column3DMap[i][j]);
					}
				else {
					for (int j = 0; j < chartStr.length; j++) {
						if (j == 0) {
							if (String.valueOf(column3DMap[i][0]).trim()
									.length() > 15) {
								int tmp336_334 = j;
								String[] tmp336_332 = chartStr;
								tmp336_332[tmp336_334] = (tmp336_332[tmp336_334]
										+ "<category label='"
										+ String.valueOf(column3DMap[i][0])
												.trim().substring(0, 10) + "'/>");
							} else {
								int tmp392_390 = j;
								String[] tmp392_388 = chartStr;
								tmp392_388[tmp392_390] = (tmp392_388[tmp392_390]
										+ "<category label='"
										+ String.valueOf(column3DMap[i][0])
												.trim() + "'/>");
							}
						} else {
							int tmp442_440 = j;
							String[] tmp442_438 = chartStr;
							String graphLink = "";
							if (displayNum > 0) {

								/*
								 * for(int r=0;column3DMap.length>r;r++){
								 * for(int c=0;column3DMap[0].length>c;c++){
								 * seriesHeader[r][c]=column3DMap[0][c]; } }
								 */

								/*
								 * for ( int k=0;k<chartStr.length;){ int
								 * len=chartStr.length; for(int p=0;
								 * (column3DMap.length)>len;p++){
								 * seriesHeader[k]=datasetHdr[p]; k++; len--; } }
								 */

								String nextGraph1 = String.valueOf(
										column3DMap[i][0]).trim();

								if (graphValue != null
										&& !graphValue.equals("")) {

									graphValue = nextGraph1;
								} else {
									graphValue = nextGraph1;
								}

								// privous values
								graphLink = "' link=\'JavaScript:getGraph(\""
										+ String.valueOf(column3DMap[i][0])
												.trim() + "\",\""
										+ String.valueOf(seriesHeader[i][j])
										+ "\",\"" + nextGraph + "\",\""
										+ graphValue + "\")\' />";
							} else {
								graphLink = "/>";
							}
							tmp442_438[tmp442_440] = (tmp442_438[tmp442_440]
									+ "<set value='"
									+ String.valueOf(column3DMap[i][j]).trim() + graphLink);
						}
					}
				}

			}

			for (int i = 0; i < chartStr.length; i++) {
				System.out.println("Table Header----" + datasetHdr[i]);
				if (i == 0) {
					column3DCharts = column3DCharts + "<categories>"
							+ chartStr[i] + "</categories>";
				} else {
					column3DCharts = column3DCharts + "<dataset seriesName='"
							+ datasetHdr[i] + "'>" + chartStr[i] + "</dataset>";
				}
			}
			column3DCharts = column3DCharts + "</chart>";
			System.out.println("Chart String-----------" + column3DCharts);

			replace = column3DCharts.replace("null", "");
			System.out.println("column3DCharts:::::::" + replace.trim());
		}
		/*
		 * if(nextGraph!=null){ request.setAttribute("graph1", replace); }
		 */

		return replace;
	}

	public void getGraph_test(String CompnentID, String graphParameter,
			String nextParam, HttpServletRequest request) {
		try {
			System.out.println("Componet ID is :" + CompnentID);
			System.out.println("graphParameter :" + graphParameter);
			System.out.println("nextParam :" + nextParam);

			Object[][] componentData = (Object[][]) null;
			Object[][] tableData = (Object[][]) null;
			List chartQyery = new ArrayList();

			componentData = getSqlModel("POOL_D1").getSingleResult(getQuery(1),
					new Object[] { CompnentID });
			String query = String.valueOf(componentData[0][0]);
			String graphType = String.valueOf(componentData[0][2]);
			String nextComponentId = String.valueOf(componentData[0][3]);
			String isGraph = String.valueOf(componentData[0][4]);

			String graphParam = graphParameter;
			/*
			 * Find the components of the querty and replace parameters.
			 */
			Object[][] componentParameters = getSqlModel("POOL_D1")
					.getSingleResult(getQuery(2), new Object[] { CompnentID });
			System.out.println("component length--  "
					+ componentParameters.length);

			System.out.println("query before : " + query);

			if (componentParameters.length > 0) {
				for (int i = 0; i < componentParameters.length; i++) {
					// logic to remove duplicate parameters to be added
					// here.
					if (String.valueOf(componentParameters[i][1]).equals("Y")) {
						graphParam = graphParam + "1111"
								+ String.valueOf(componentParameters[i][0])
								+ "2222" + nextParam;
					} else {
						query = query.replaceAll("<" + String.valueOf(componentParameters[i][0]) + ">","");
					}
				}
				System.out.println("graphParameter--  " + graphParam);
				/*
				 * Build the query by replacing dynamic parameters
				 * 
				 */
				String[] completeParam = graphParam.split("1111");
				for (int i = 1; i < completeParam.length; i++) {
					String[] params = completeParam[i].split("2222");
					if (params.length > 0) {
						System.out.println("parameters replaced :"+params[0]+" : with value :"+params[1]);
						query = query.replaceAll("<" + params[0] + ">",
								params[1]);
					}
				}
			}
			System.out.println("query after : " + query);

			/*
			 * Verify if the component is graph or data. set the request
			 * parameter accordingly.
			 */
			if (isGraph.equals("Y")) {
				/*
				 * send the Chart data back to JSP through request.
				 */
				chartQyery.add(getChartData(query, // query
						graphType, // graph type
						nextComponentId, // next component id - one or
						// many
						graphParam, // graph parameters
						request));
				request.setAttribute("Chartquery", chartQyery);
			} else {
				/*
				 * The component is data
				 */
				tableData = getSqlModel("POOL_D1").getSingleResult(query);
				request.setAttribute("tableData", tableData);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public String getChartData(String query, String chartType,
			String nextComponentId, String graphParameters,
			HttpServletRequest request) {
		Object[][] column3DMap = getSqlModel("POOL_D1").getSingleResultWithCol(
				query);
		Object[][] seriesHeader = new Object[column3DMap.length][column3DMap[0].length];
		// this loop for series header

		for (int r = 0; column3DMap.length > r; r++) {
			for (int c = 0; column3DMap[0].length > c; c++) {
				seriesHeader[r][c] = column3DMap[0][c];
			}
		}

		String replace = "";
		String column3DCharts = "";
		if (chartType.trim().equals("Pie2D")) {
			column3DCharts = "<chart enableSmartLabels='0' shownames='0' showValues='1' bgColor='ffffff,ffffff' showBorder='0' >";
			if ((column3DMap != null) && (column3DMap.length > 0)) {
				for (int i = 0; i < column3DMap[0].length; i++) {
					column3DCharts = column3DCharts + "<set label='"
							+ String.valueOf(column3DMap[0][i]) + "' value='"
							+ String.valueOf(column3DMap[1][i]) + "'/>";
				}
			}
			column3DCharts = column3DCharts + "</chart>";
			replace = column3DCharts.replace("null", "");
		} else {

			column3DCharts = "<chart showvalues='1' legendAllowDrag='1' rotateNames='1' shownames='1' decimals='0' slantLabels='1' numberPrefix='' labelDisplay='WRAP' showExportDataMenuItem='1' exportEnabled='1' exportAtClient='1' exportHandler='C:\\img' html5ExportHandler='C:\\img'>";

			String categories = "<categories>";
			String dataset = "";
			String[] chartStr = new String[column3DMap[0].length];
			String[] datasetHdr = new String[column3DMap[0].length];

			for (int i = 0; i < column3DMap.length; i++) {
				if (i == 0)
					for (int j = 0; j < column3DMap[0].length; j++) {
						System.out.println("--------header----"
								+ String.valueOf(column3DMap[i][j]));
						datasetHdr[j] = String.valueOf(column3DMap[i][j]);
					}
				else {
					for (int j = 0; j < chartStr.length; j++) {
						if (j == 0) {
							if (String.valueOf(column3DMap[i][0]).trim()
									.length() > 15) {
								int tmp336_334 = j;
								String[] tmp336_332 = chartStr;
								tmp336_332[tmp336_334] = (tmp336_332[tmp336_334]
										+ "<category label='"
										+ String.valueOf(column3DMap[i][0])
												.trim().substring(0, 10) + "'/>");
							} else {
								int tmp392_390 = j;
								String[] tmp392_388 = chartStr;
								tmp392_388[tmp392_390] = (tmp392_388[tmp392_390]
										+ "<category label='"
										+ String.valueOf(column3DMap[i][0])
												.trim() + "'/>");
							}
						} else {
							int tmp442_440 = j;
							String[] tmp442_438 = chartStr;

							String graphLink = "' link=\'JavaScript:getGraph(\""
									+ nextComponentId
									+ "\",\""
									+ graphParameters
									+ "\",\""
									+ String.valueOf(column3DMap[i][0]).trim()
									+ "\")\' />";

							tmp442_438[tmp442_440] = (tmp442_438[tmp442_440]
									+ "<set value='"
									+ String.valueOf(column3DMap[i][j]).trim() + graphLink);
						}
					}
				}
			}

			for (int i = 0; i < chartStr.length; i++) {
				// System.out.println("Table Header----" + datasetHdr[i]);
				if (i == 0) {
					column3DCharts = column3DCharts + "<categories>"
							+ chartStr[i] + "</categories>";
				} else {
					column3DCharts = column3DCharts + "<dataset seriesName='"
							+ datasetHdr[i] + "'>" + chartStr[i] + "</dataset>";
				}
			}
			column3DCharts = column3DCharts + "</chart>";
			System.out.println("Chart String-----------" + column3DCharts);

			replace = column3DCharts.replace("null", "");
			System.out.println("column3DCharts:::::::" + replace.trim());
		}
		/*
		 * if(nextGraph!=null){ request.setAttribute("graph1", replace); }
		 */

		return replace;
	}

}