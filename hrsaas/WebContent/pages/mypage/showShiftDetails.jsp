<%@page import="org.paradyne.lib.Utility"%>
<%
Object data[][] = (Object[][]) request.getAttribute("data");
%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>



<link rel="stylesheet" type="text/css" title="default-theme"
	href="<%=request.getContextPath()%>/pages/common/commonCSS.jsp" />

<tr>
	<td width="100%">
	<div id="first">
	<table width="100%" class="formbg">
		<tr>
			<td width="50%"><b>Shift Details</b></td>
		</tr>
		<tr>
			<td width="50%" valign="top">
			<table width="100%" class="formbg">
				<tr>
					<td width="63%">Shift Name:</td>
					<td colspan="2"><%=(data != null && data.length > 0) ? Utility
					.checkNull(String.valueOf(data[0][0])) : ""%>

					</td>
				</tr>

				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][1]))
							.equals("")) {
				%>
				<tr>
					<td width="63%">Is Flexi time Allowed:</td>
					<td colspan="2"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][1])) : ""%>

					</td>
				</tr>
				<%
				}
				%>

				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][2]))
							.equals("")) {
				%>
				<tr>
					<td width="63%">Shift working hours</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][2])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>
				<%
				}
				%>

				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][3]))
							.equals("")) {
				%>
				<tr>
					<td width="63%">Shift Start Time</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][3])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>
				<%
				}
				%>
				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][4]))
							.equals("")) {
				%>
				<tr>
					<td width="63%">Reporting time-will be marked late after</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][4])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>
				<%
				}
				%>
				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][5]))
							.equals("")) {
				%>
				<tr>
					<td width="63%">Reporting time-will be marked half day after</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][5])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>
				<%
				}
				%>

				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][6]))
							.equals("")) {
				%>
				<tr>
					<td width="63%">Mark Late if working hours less than</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][6])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>

				<%
				}
				%>

				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][7]))
							.equals("")) {
				%>
				<tr>
					<td width="63%">Mark half day if working hours less than</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][7])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>
				<%
				}
				%>

				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][8]))
							.equals("")) {
				%>
				<tr>
					<td width="63%">Lunch break start time</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][8])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>
				<%
				}
				%>

				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][9]))
							.equals("")) {
				%>
				<tr>
					<td width="63%">Lunch break end time</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][9])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>
				<%
				}
				%>

				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][10])).equals(
							"")) {
				%>
				<tr>
					<td width="63%">Office leaving will be marked half day if left
					before</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][10])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>


				<%
				}
				%>
				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][11])).equals(
							"")) {
				%>
				<tr>
					<td width="63%">Office leaving will be marked early leaving if
					left before</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][11])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>


				<%
				}
				%>
				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][12])).equals(
							"")) {
				%>
				<tr>
					<td width="63%">Shift end time</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][12])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>

				<%
				}
				%>
				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][13])).equals(
							"")) {
				%>
				<tr>
					<td width="63%">Extra working hours start from</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][13])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>

				<%
				}
				%>
				<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][14])).equals(
							"")) {
				%>
				<tr>
					<td width="63%">Mark absent after this time</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][14])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>
			</table>
			</td>

		</tr>
		<%
		}
		%>
		<%
							if (data != null
							&& data.length > 0
							&& !Utility.checkNull(String.valueOf(data[0][15]))
							.equals("")) {
				%>
				<tr>
					<td width="63%">Mark Absent if working hours less than</td>
					<td width="7%"><%=(data != null && data.length > 0) ? Utility
						.checkNull(String.valueOf(data[0][15])) : ""%>
					</td>
					<td>(HH24:MI)</td>
				</tr>

				<%
				}
				%>
		<tr>
			<td width="50%"><font color="red">Note : -- Time Format
			should be hh:mm (24 hours)</font></td>
		</tr>
	</table>
	</div>
	</td>
</tr>