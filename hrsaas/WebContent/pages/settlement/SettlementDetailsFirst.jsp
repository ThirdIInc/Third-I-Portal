<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Vector"%>
<%@page import="org.paradyne.lib.Utility"%>

<%
int Settnet = 0;
%>
<%
	int netTotShortDays = 0;
	int netTotProc = 0;
	int netTotDays = 0;
	String[] payByFlags = null;
	int countBlock = 0;
%>

<s:form action="SettlmentDetails" validate="true" id="paraFrm"	theme="simple">
<s:hidden name="resignCode" value="%{resignCode}" />
<s:hidden name="settCode" />
<s:hidden name="settDtlCode" />
<s:hidden name="settFlag" />
<s:hidden name="lockFlag" />
<s:hidden name="status" />
<s:hidden name="processFlag" />
<s:hidden name="myPage" />
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Settlement Details</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="82%" nowrap="nowrap"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="18%" nowrap="nowrap">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" width="100%">
			<table border="0" class="formbg" width="100%" height="100px">
				<tr>
					<td colspan="4" width="100%">
					<table border="0" width="100%">
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="employee" id="employee1" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							:</td>
							<td width="12%" colspan="1"><s:textfield name="empToken"
								size="12" value="%{empToken}" readonly="true" /></td>
							<td width="68%" colspan="2"><s:textfield name="empName"
								size="68" value="%{empName}" readonly="true" /></td>
							<s:hidden name="empCode" value="%{empCode}"></s:hidden>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="4" width="100%">
					<table border="0" width="100%">
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="designation" id="designation1"
								ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
							:</td>
							<td width="30%" colspan="1"><s:textfield size="22"
								name="desgn" value="%{desgn}" readonly="true" /></td>
							<td width="20%" colspan="1"><label class="set" name="branch"
								id="branch1" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
							<td width="30%" colspan="1"><s:textfield size="22"
								name="branch" value="%{branch}" readonly="true" /></td>
							
						</tr>


						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="grade" id="grade"
								ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:
							</td>
							<td width="30%" colspan="1">
								<s:textfield 	name="cadreName" value="%{cadreName}" size="22" readonly="true" />
							</td>
							<td width="20%" colspan="1" class="formtext">
								<label	class="set" name="doj" id="doj" ondblclick="callShowDiv(this);"><%=label.get("doj")%></label>:</td>
							<td width="30%" colspan="1">
								<s:textfield name="dateOfJoin"	size="20" onkeypress="return numbersWithHiphen();"	
											 theme="simple" value="%{dateOfJoin}" maxlength="10"	readonly="true" />
							</td>
						</tr>
						
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="resignation.date" id="resignation.date1"
								ondblclick="callShowDiv(this);"><%=label.get("resignation.date")%></label><font
								color="red">*</font> :</td>
							<td width="30%" colspan="1"><s:textfield size="22"
								name="resignDate" readonly="true" /></td>
							<td width="20%" colspan="1"><label class="set"
								name="seperation.date" id="seperation.date1"
								ondblclick="callShowDiv(this);"><%=label.get("seperation.date")%></label><font
								color="red">*</font> :</td>
							<td width="30%" colspan="3"><s:textfield size="22"
								name="sepDate" readonly="true" /></td>
						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set" name="period"
								id="period1" ondblclick="callShowDiv(this);"><%=label.get("period")%></label><font
								color="red">*</font> :</td>
							<td width="80%" colspan="3"><s:textfield size="5"
								theme="simple" name="noticePeriod"
								onkeypress="return numbersOnly();" readonly="true" /><s:select
								theme="simple" name="noticePeriodStatus" cssStyle="width:67"
								list="#{'D':'Days','M':'Month'}" disabled="true" /><s:hidden
								name="noticeStatus" /> <s:hidden name="noticePeriodStatus" /></td>
						</tr>
					</table>
					</td>
			</table>
			</td>
		</tr>

		<%
			int crId = 0;
			int dbId = 0;
			int net = 0;
			int crNet = 0;
			int dbNet = 0;
			Object[][] rows1 = null;
			int netP = 0;
		%>

		<!--  calculates onhold months -->
		<s:if test="%{processFlag}">
			<%
				int maxTotal = 0;
				try {
					ArrayList<Object[][]> credit_List = (ArrayList<Object[][]>) request
					.getAttribute("credit_List");
					Vector<Object[][]> month_year = (Vector<Object[][]>) request
					.getAttribute("month_year");
					if (credit_List != null) {
						for (int k = 0; k < credit_List.size(); k++) {
					rows1 = (Object[][]) credit_List.get(k);
					Object[][] m_y = (Object[][]) month_year.get(k);

					String dispMonth = "";
					if (String.valueOf(m_y[0][0]).equals("1"))
						dispMonth = "January";
					else if (String.valueOf(m_y[0][0]).equals("2"))
						dispMonth = "February";
					else if (String.valueOf(m_y[0][0]).equals("3"))
						dispMonth = "March";
					else if (String.valueOf(m_y[0][0]).equals("4"))
						dispMonth = "April";
					else if (String.valueOf(m_y[0][0]).equals("5"))
						dispMonth = "May";
					else if (String.valueOf(m_y[0][0]).equals("6"))
						dispMonth = "June";
					else if (String.valueOf(m_y[0][0]).equals("7"))
						dispMonth = "July";
					else if (String.valueOf(m_y[0][0]).equals("8"))
						dispMonth = "August";
					else if (String.valueOf(m_y[0][0]).equals("9"))
						dispMonth = "September";
					else if (String.valueOf(m_y[0][0]).equals("10"))
						dispMonth = "October";
					else if (String.valueOf(m_y[0][0]).equals("11"))
						dispMonth = "November";
					else if (String.valueOf(m_y[0][0]).equals("12"))
						dispMonth = "December";
			%>
			<tr class="td_bottom_border">
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td width="55%" colspan="1"><strong> <%
 if (String.valueOf(m_y[0][6]).trim().equals("PD")) {
 %> Salary paid for <%=dispMonth%>-<%=String.valueOf(m_y[0][1])%> <%
 				} else if (String.valueOf(m_y[0][6]).trim()
 				.equals("OH")) {
 %> Salary onHold for <%=dispMonth%>-<%=String.valueOf(m_y[0][1])%> <%
 }
 %> <s:hidden name="month" value="<%=String.valueOf(m_y[0][0])%>" /> <s:hidden
							name="year" value="<%=String.valueOf(m_y[0][1])%>" /></strong> <s:hidden
							name="onholdType" value="<%=String.valueOf(m_y[0][6])%>" /></td>
					</tr>

					<tr class="td_bottom_border">
						<td colspan="1" width="50%" valign="top" align="left">
						<table width="100%" class="borderAttdn" cellpadding="0"
							cellspacing="0" align="left">
							<tr>
								<td width="60%" class="formth" colspan="1" height="22"><label
									class="set" name="credit.head" id="credit.head2"
									ondblclick="callShowDiv(this);"><%=label.get("credit.head")%></label></td>
								<td width="40%" class="formth" colspan="1" height="22"><label
									class="set" name="credit.amount" id="credit.amount2"
									ondblclick="callShowDiv(this);"><%=label.get("credit.amount")%></label></td>
							</tr>
							<%
										for (int a = 0; a < m_y.length; a++) {
										for (int l = 0; l < rows1.length; l++) {
							%>
							<tr class="td_bottom_border">
								<%
								if (!String.valueOf(rows1[l][0]).equals("")) {
								%>
								<td width="60%" colspan="1" class="sortableTD" height="22">
								<%
													if (String.valueOf(m_y[0][6]).trim()
													.equals("PD")) {
								%> <%=String.valueOf(rows1[l][0])%><s:hidden name="creditH"
									value="<%=String.valueOf(rows1[l][0])%>" /> <s:hidden
									name="creditC" value="<%=String.valueOf(rows1[l][2])%>" /></td>
								<td align="right" width="40%" colspan="1" class="sortableTD"
									height="22"><input type="text" name="creditA" size="16"
									readonly="true"
									value="<%=Utility.twoDecimals(Double.parseDouble(String.valueOf(rows1[l][1])))%>"
									id="crAmt<%=k%><%=l%>"
									style="background-color: #F2F2F2; text-align: right;"
									onkeyup="sumProcess(<%=k%>,<%=rows1.length%>)" /> <s:hidden
									value="creditA" /> <%
 					} else if (String.valueOf(m_y[0][6]).trim()
 					.equals("OH")) {
 %> <%=String.valueOf(rows1[l][0])%><s:hidden name="creditH"
									value="<%=String.valueOf(rows1[l][0])%>" /> <s:hidden
									name="creditC" value="<%=String.valueOf(rows1[l][2])%>" /></td>
								<td align="right" width="40%" colspan="1" class="sortableTD"
									height="22"><input readonly="readonly" type="text" name="creditA" size="16"
									value="<%=Utility.twoDecimals(Double.parseDouble(String.valueOf(rows1[l][1])))%>"
									id="crAmt<%=k%><%=l%>" style="text-align: right;"
									onkeypress="return numbersWithDot();"
									onkeyup="replaceBlankWithZero(this);sumProcess(<%=k%>,<%=rows1.length%>);" />
								<s:hidden value="creditA" /> <%
 }
 %> <%
 					crId += (int) (Double.parseDouble(String
 					.valueOf(rows1[l][1])));
 %>
								</td>
								<%
								} else {
								%>
								<td width="60%" colspan="1" class="sortableTD" height="22">&nbsp;</td>
								<td align="left" width="40%" colspan="1" class="sortableTD"
									height="22">&nbsp;</td>
								<%
								}
								%>

							</tr>
							<%
									}
									}
							%>

							<tr class="td_bottom_border">
								<td width="70%" class="sortableTD" height="22"><strong><label
									class="set" name="total.credit" id="total.credit"
									ondblclick="callShowDiv(this);"><%=label.get("total.credit")%></label></strong></td>
								<td width="30%" align="right" height="22" class="sortableTD">
								<input type="text" name="totCreAmt" theme="simple" size="16"
									id="totCreAmt<%=k %>"
									value='<%= Utility.twoDecimals(Double.parseDouble(String.valueOf(crId))) %>'
									style="background-color: #F2F2F2; text-align: right;"
									readonly="true" /> <%
 		crNet = crId;
 		crId = 0;
 %>
								</td>
							
								
								
							
							
							</tr>
						</table>
						</td>

						<td colspan="1" width="50%" valign="top" align="left">
						<table width="100%" class="borderAttdn" cellpadding="0"
							cellspacing="0" align="left">
							<tr>
								<td width="60%" class="formth" colspan="1" height="22"><label
									class="set" name="debit.head" id="debit.head1"
									ondblclick="callShowDiv(this);"><%=label.get("debit.head")%></label></td>
								<td width="40%" class="formth" colspan="1" height="22"><label
									class="set" name="debit.amount" id="debit.amount"
									ondblclick="callShowDiv(this);"><%=label.get("debit.amount")%></label></td>
							</tr>
							<%
										for (int a = 0; a < m_y.length; a++) {
										for (int l = 0; l < rows1.length; l++) {
							%>
							<tr class="td_bottom_border">
								<%
								if (!String.valueOf(rows1[l][3]).equals("")) {
								%>
								<td width="55%" colspan="1" class="sortableTD" height="22">
								<%
													if (String.valueOf(m_y[0][6]).trim()
													.equals("PD")) {
								%> <%=String.valueOf(rows1[l][3])%><s:hidden name="debitH"
									value="<%=String.valueOf(rows1[l][3])%>" /> <s:hidden
									name="debitC" value="<%=String.valueOf(rows1[l][5])%>" /></td>
								<td align="right" width="45%" colspan="1" class="sortableTD"
									height="22"><input type="text" name="debitA" size="15"
									readonly="true"
									value="<%=Utility.twoDecimals(Double.parseDouble(String.valueOf(rows1[l][4]))) %>"
									id="dbAmt<%=k%><%=l%>"
									style="background-color: #F2F2F2; text-align: right;"
									onkeyup="sumProcess(<%=k%>,<%=rows1.length%>)" /><s:hidden
									value="debitA" /> <%
 					} else if (String.valueOf(m_y[0][6]).trim()
 					.equals("OH")) {
 %> <%=String.valueOf(rows1[l][3])%><s:hidden name="debitH"
									value="<%=String.valueOf(rows1[l][3])%>" /> <s:hidden
									name="debitC" value="<%=String.valueOf(rows1[l][5])%>" /></td>
								<td align="right" width="45%" colspan="1" class="sortableTD"
									height="22"><input readonly="readonly" type="text" name="debitA" size="15"
									value="<%=Utility.twoDecimals(Double.parseDouble(String.valueOf(rows1[l][4]))) %>"
									id="dbAmt<%=k%><%=l%>" style="text-align: right;"
									onkeypress="return numbersWithDot();"
									onkeyup="replaceBlankWithZero(this);sumProcess(<%=k%>,<%=rows1.length%>);" /><s:hidden
									value="debitA" /> <%
 }
 %> <%
 					dbId += (int) Double.parseDouble(String
 					.valueOf(rows1[l][4]));
 %>
								</td>
								<%
								} else {
								%>
								<td width="55%" colspan="1" class="sortableTD" height="22">&nbsp;</td>
								<td align="right" width="45%" colspan="1" class="sortableTD"
									height="22">&nbsp;</td>
								<%
								}
								%>

							</tr>
							<%
									}
									}
							%>

							<tr class="td_bottom_border">
								<td width="70%" class="sortableTD" height="22"><strong><label
									class="set" name="total.debit" id="total.debit"
									ondblclick="callShowDiv(this);"><%=label.get("total.debit")%></label></strong></td>
								<td width="30%" align="right" height="22" class="sortableTD">
								<input type="text" name="totDbAmt"
									value='<%=Utility.twoDecimals(Double.parseDouble(String.valueOf(dbId)))%>'
									theme="simple" id="totDbAmt<%=k %>" size="15"
									style="background-color: #F2F2F2; text-align: right;"
									readonly="true" /> <%
 		dbNet = dbId;
 		dbId = 0;
 %>
								</td>
							
								
								
							
							
							</tr>
						</table>
						</td>
					</tr>
					<%
							net = (crNet) - (dbNet);
							netTotProc += net;
					%>
					<tr class="td_bottom_border">
						<td colspan="1" width="50%" valign="top" align="left">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							align="left">
							<tr>
								<td width="70%" colspan="1" class="sortableTD" height="22">
								<strong><label class="set" name="net.total"
									id="net.total" ondblclick="callShowDiv(this);"><%=label.get("net.total")%></label>
								</strong></td>

								<td align="right" width="30%" class="sortableTD" colspan="1"
									height="22"><input type="hidden" id="onholdType<%=k %>"
									value="<%=String.valueOf(m_y[0][6])%>" /> <input type="text"
									size="16" name="totNet" id="totNet<%=k %>" theme="simple"
									value=" <%=(net)%>"
									style="background-color: #F2F2F2; text-align: right;"
									readonly="readonly" /> <input type="hidden"
									id="paraFrm_hnetProc<%=k %>"></td>

							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<%
						maxTotal = k;
						}// for ends
					}// if ends
				} catch (Exception e) {
					e.printStackTrace();
				}
			%>
			<input type="hidden" name="netOnHold" />
			<input type="hidden" id="paraFrm_maxTotal" value="<%=maxTotal%>">
		</s:if>

		<!--  end of onhold months -->

		<!--  calculates eligible days -->

		<s:if test="debitHeader">
			<%
			try {
					Vector<Object[][]> my = (Vector<Object[][]>) request
					.getAttribute("mon_List");
					ArrayList<Object[][]> shortRows1 = (ArrayList<Object[][]>) request
					.getAttribute("rows_List");
					Object[][] credits = (Object[][]) request
					.getAttribute("creditLength");
					Object[][] debits = (Object[][]) request
					.getAttribute("debitLength");

					Object[][] shortRows = null;
					Object[][] monthYear = null;
					int colValShort = 0;
					countBlock = shortRows1.size();
	
					if (credits != null && shortRows1 != null) {
						int is = 0;
						payByFlags = new String[shortRows1.size()];
						for (int ks = 0; ks < shortRows1.size(); ks++) {
					shortRows = (Object[][]) shortRows1.get(ks);
					monthYear = (Object[][]) my.get(ks);

					colValShort = shortRows[0].length - 3;
					int shortDays = 0;
					payByFlags[ks] = String.valueOf(monthYear[0][3]);

					String dispSMonth = "";
					if (String.valueOf(monthYear[0][0]).equals("1"))
						dispSMonth = "January";
					else if (String.valueOf(monthYear[0][0]).equals("2"))
						dispSMonth = "February";
					else if (String.valueOf(monthYear[0][0]).equals("3"))
						dispSMonth = "March";
					else if (String.valueOf(monthYear[0][0]).equals("4"))
						dispSMonth = "April";
					else if (String.valueOf(monthYear[0][0]).equals("5"))
						dispSMonth = "May";
					else if (String.valueOf(monthYear[0][0]).equals("6"))
						dispSMonth = "June";
					else if (String.valueOf(monthYear[0][0]).equals("7"))
						dispSMonth = "July";
					else if (String.valueOf(monthYear[0][0]).equals("8"))
						dispSMonth = "August";
					else if (String.valueOf(monthYear[0][0]).equals("9"))
						dispSMonth = "September";
					else if (String.valueOf(monthYear[0][0]).equals("10"))
						dispSMonth = "October";
					else if (String.valueOf(monthYear[0][0]).equals("11"))
						dispSMonth = "November";
					else if (String.valueOf(monthYear[0][0]).equals("12"))
						dispSMonth = "December";
			%>
			<tr class="td_bottom_border">
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<s:hidden name="mm" value="<%=String.valueOf(monthYear[0][0])%>" />
						<s:hidden name="yy" value="<%=String.valueOf(monthYear[0][1])%>" />
						<s:hidden name="monthType"
							value="<%=String.valueOf(monthYear[0][3])%>" />
						<td width="55%" colspan="1"><strong> <%
 if (String.valueOf(monthYear[0][3]).trim().equals("EM")) {
 %> Pay by employee for <%=dispSMonth%>-<%=String.valueOf(monthYear[0][1])%>
						<%
						} else if (String.valueOf(monthYear[0][3]).equals("CO")) {
						%> Pay by company for <%=dispSMonth%>-<%=String.valueOf(monthYear[0][1])%>
						<%
						}
						%> </strong></td>
						<td width="45%" colspan="2">&nbsp;</td>
					</tr>
					<tr class="td_bottom_border">
						<%
						is = 3;
						%>

						<td colspan="1" width="50%" valign="top" align="left">
						<table width="100%" class="borderAttdn" cellpadding="0"
							cellspacing="0" align="left">
							<tr>
								<td width="60%" class="formth" colspan="1" height="22"><label
									class="set" name="credit.head" id="credit.head3"
									ondblclick="callShowDiv(this);"><%=label.get("credit.head")%></label></td>
								<td width="40%" class="formth" colspan="1" height="22"><label
									class="set" name="credit.amount" id="credit.amount3"
									ondblclick="callShowDiv(this);"><%=label.get("credit.amount")%></label></td>
							</tr>

							<s:iterator value="creditHeader">
								<tr class="td_bottom_border">
									<td width="55%" colspan="1" class="sortableTD" height="22"><s:property
										value="creditHead" /></td>
									<td align="right" width="45%" colspan="1" class="sortableTD"
										height="22"><input type="hidden" readonly="readonly"
										name="emp_idShort" value="<%=shortRows[shortDays][0] %>"
										id="<%=shortRows[shortDays][0]%>"> <input type="text"
										size="16" name="<%=ks+1%>"
										value="<%=String.valueOf(shortRows[shortDays][is]) %>"
										id="<%=monthYear[0][0]+"cs"+is+ks%>"
										onkeypress="return numbersWithDot();"
										onkeyup="replaceBlankWithZero(this);sumShort(<%=credits.length %>,<%=ks+1%>,<%=colValShort%>,'<%=monthYear[0][0]%>',<%=ks%>);"
										style="text-align: right"><input type="hidden"
										name="creditAmt" /></td>
								</tr>
								<%
								is++;
								%>
							</s:iterator>

							<tr class="td_bottom_border">
								<td width="70%" class="sortableTD" height="22"><strong><label
									class="set" name="total.credit" id="total.credit3"
									ondblclick="callShowDiv(this);"><%=label.get("total.credit")%></label></strong></td>
								<td width="30%" align="right" height="22" class="sortableTD"><input
									type="text" size="16" readonly="readonly"
									id="<%=monthYear[0][0]+"cs"+is+ks%>"
									name="totalCreditShort<%=shortDays%>"
									value="<%=shortRows[shortDays][is] %>"
									style="text-align: right"> <input type="hidden"
									name="daysCheck" value="<%=String.valueOf(monthYear[0][2])%>"
									id="<%=monthYear[0][0]+"days"+ks%>" /> <input type="hidden"
									name="typeCheck" value="<%=String.valueOf(monthYear[0][3])%>"
									id="<%=monthYear[0][0]+"type"+ks%>" /></td>
							</tr>
						</table>
						</td>

						<td colspan="1" width="50%" valign="top" align="left">
						<table width="100%" class="borderAttdn" cellpadding="0"
							cellspacing="0" align="left">
							<tr>
								<td width="60%" class="formth" colspan="1" height="22"><label
									class="set" name="debit.head" id="debit.head4"
									ondblclick="callShowDiv(this);"><%=label.get("debit.head")%></label></td>
								<td width="40%" class="formth" colspan="1" height="22"><label
									class="set" name="debit.amount" id="debit.amount1"
									ondblclick="callShowDiv(this);"><%=label.get("debit.amount")%></label></td>
							</tr>

							<s:iterator value="debitHeader">
								<tr class="td_bottom_border">
									<td width="55%" colspan="1" class="sortableTD"><s:property
										value="debitHead" /></td>
									<%
									is++;
									%>

									<td align="right" width="45%" colspan="1" class="sortableTD"
										height="22"><input type="text" size="15" name="<%=ks+1%>"
										value="<%=shortRows[shortDays][is] %>"
										id="<%=monthYear[0][0]+"cs"+is+ks%>"
										onkeypress="return numbersWithDot();"
										onkeyup="replaceBlankWithZero(this);sumShort(<%=credits.length %>,<%=ks+1%>,<%=colValShort%>,'<%=monthYear[0][0]%>',<%=ks%>);"
										style="text-align: right"><input type="hidden"
										name="debitAmt" /></td>
								</tr>
							</s:iterator>

							<tr class="td_bottom_border">
								<td width="70%" class="sortableTD" height="22"><strong><label
									class="set" name="total.debit" id="total.debit"
									ondblclick="callShowDiv(this);"><%=label.get("total.debit")%></label></strong></td>
								<%
										int iValShort = is;
										iValShort++;
								%>
								<td width="30%" align="right" height="22" class="sortableTD"><span
									id="<%=monthYear[0][0]+"totDebitShort"+shortDays+ks%>">
								<input type="text" size="15" readonly="readonly"
									name="totalDebitShort<%=shortDays%>"
									value="<%=shortRows[shortDays][is+1] %>"
									id="<%=monthYear[0][0]+"cs"+iValShort+ks%>"
									style="text-align: right"></span></td>
								<%
								iValShort++;
								%>
							</tr>
						</table>
						</td>
					</tr>
					<input type="hidden" name="daysCheck" />

					<tr>
						<td colspan="1" width="50%" valign="top" align="left">
						<table width="100%" border="0" cellspacing="0" cellpadding="0"
							align="left">
							<tr class="td_bottom_border">
								<td width="70%" colspan="1" class="sortableTD" height="22"><strong><label
									class="set" name="net" id="net3"
									ondblclick="callShowDiv(this);"><%=label.get("net")%></label></strong></td>

								<td align="right" width="30%" class="sortableTD" colspan="1"
									height="22"><input type="hidden"
									id="<%="block"+iValShort+ks%>"
									value="<%=String.valueOf(monthYear[0][3])%>" /> <input
									type="text" size="16" readonly="readonly" name="netPayShort"
									id="<%="cs"+iValShort+ks%>"
									style="background-color: #F2F2F2; text-align: right;" /> <s:hidden
									name="hnetPayShort" id="hnetPayShort" /> <script>
						      document.getElementById('<%="cs"+iValShort+ks%>').value =<%=(shortRows[shortDays][is+2])%>;
						      document.getElementById('hnetPayShort').value =<%=(shortRows[shortDays][is+2])%>;
						      					    
						</script></td>
								<%
										//System.out.println("type....."+String.valueOf(monthYear[0][3]));
										if (String.valueOf(monthYear[0][3]).trim().equals("EM")) {
											netTotShortDays -= (int) Double.parseDouble(String
											.valueOf(shortRows[shortDays][is + 2]));
											//System.out.println("netTotShortDaysE......"+netTotShortDays);
										} else {
											netTotShortDays += (int) Double.parseDouble(String
											.valueOf(shortRows[shortDays][is + 2]));
											//System.out.println("netTotShortDaysCO......"+netTotShortDays);
										}
								%>


							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<%
					}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
			%>
			<input type="hidden" id="netTotShortDays"
				value="<%=netTotShortDays%>" />
		</s:if>
		<!--  end of  eligible days -->

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="20%" height="15" colspan="1"><strong><label
						class="set" name="salary.amount" id="salary.amount1"
						ondblclick="callShowDiv(this);"><%=label.get("salary.amount")%></label></strong>:</td>
					<td width="80%" height="15" colspan="3"><s:textfield
						name="salaryAmt" size="20"
						cssStyle="background-color: #F2F2F2;text-align:right;"
						readonly="true" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="82%" nowrap="nowrap"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="18%" nowrap="nowrap">
					</td>
				</tr>

			</table>
			</td>
		</tr>

	</table>
</s:form>

<script type="text/javascript">
<!--

//-->

function roundNumber(num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}

/* Calculation of net pay on changing credit/debit for Short notice*/

function sumShort(cLen,k,colLen,empId,ks) {
	try{
		var blockCount=<%=countBlock%>;
		var formElements=document.getElementsByName(k);
		var creditAmount=0;
		var debitAmount=0;
		var flag= document.getElementById('paraFrm_processFlag').value;
 		try{
 		var type=trim(document.getElementById(empId+"type"+ks).value);
 		}catch(e){
 			//alert(e);
 		}
	 	//loop through the array  
	 	for (var i=formElements.length-1; i>=0; --i ){
 			if(i<cLen)
	 		{
	 			var values=formElements[i].value;
	 		 	if(values ==''){
	 		 		values =0;
	 		 	}
	 		 	if(values =="."){
	 		 		values =0;
	 		 	}
				creditAmount=creditAmount+eval((values*100)/100);
			}else
			{
				var values=formElements[i].value;
	 		 	if(values ==''){
	 		 		values =0;
	 		 	}
				debitAmount=debitAmount+eval((values*100)/100);
			} 		
	 	}
	 	//alert('colLen...'+colLen);
	 	//alert('cLen...'+cLen);
	 	var totalCredit=totalCredit+k;
	 	try{
		document.getElementById(empId+"cs"+eval(cLen+3)+ks).value=roundNumber(eval(creditAmount),2);
		document.getElementById(empId+"cs"+eval(colLen-2)+ks).value=roundNumber(eval(debitAmount),2);
		document.getElementById("cs"+eval(colLen-1)+ks).value=roundNumber((eval(creditAmount*100/100)-eval(debitAmount*100/100)),0);
		}catch(e){
			//alert(e);
		}
		document.getElementById('hnetPayShort').value = roundNumber((eval(creditAmount*100/100)-eval(debitAmount*100/100)),0);
		
		var maxTot = 0;
		try{
			maxTot = document.getElementById('paraFrm_maxTotal').value;
		}catch(e){
			//alert('maxtot...'+e);
		}
		var netP =0;
		try{  
			if(flag=="true"){
				for(var m=0;m<=maxTot;m++){
					if(trim(document.getElementById('onholdType'+m).value) != "PD")
			 			netP= eval(netP) + eval(document.getElementById('totNet'+m).value);
			  	}
			}
		}catch(e2){
			netP =0;
		}
		var eligibleDays=0;
		try{
			eligibleDays= document.getElementById('hnetPay').value;
		}catch(e2){
			eligibleDays =0;
		}
		
		var shortPay = eval(document.getElementById('hnetPayShort').value);
	    
	    var shortNet=0;
	    
		var sumSht = eval(eligibleDays) + eval(netP);
																	
		
		for(var co=0;co<=blockCount-1;co++){
			
			var flagPay=trim(document.getElementById("block"+eval(colLen-1)+co).value);
			if(co!=ks){
				if(flagPay=="CO"){
					sumSht=sumSht+ eval(document.getElementById("cs"+eval(colLen-1)+co).value);
				}
				if(flagPay=="EM"){
				sumSht=sumSht-eval(document.getElementById("cs"+eval(colLen-1)+co).value);
				}
			}
			if(flagPay=="CO"){
				shortNet=shortNet+ eval(document.getElementById("cs"+eval(colLen-1)+co).value);
			}
			if(flagPay=="EM"){
				shortNet=shortNet-eval(document.getElementById("cs"+eval(colLen-1)+co).value);
			}
			document.getElementById("netTotShortDays").value = roundNumber(eval(shortNet),0);
		 }
		  
		  
		if(type=="EM")
		{										
			document.getElementById('paraFrm_salaryAmt').value= roundNumber((eval(sumSht)- eval(shortPay)),0);
		}
		else
		{
		document.getElementById('paraFrm_salaryAmt').value= roundNumber((eval(sumSht)+ eval(shortPay)),0);
		}
	}catch(e){
	alert(e);			
	}
}

function sumProcess(cLen,k) {
	
	var leaveEncash=0;
	var sumCre=0,totNet=0,sumDb= 0;
	 
	        	
	try{
		
		for(var i=0;i<k;i++){
		
			try{
			sumCre += parseFloat(document.getElementById("crAmt"+cLen+i).value);
		
			}
			catch(e2){
			sumCre += eval(0);
		
			}
			
			try{
			
			sumDb += parseFloat(document.getElementById("dbAmt"+cLen+i).value);
			}
			catch(e){
			
			sumDb +=eval(0);
			}
			
		}
		}													
	catch(e1){
		//alert(' err....'+e1);
	}
		document.getElementById("totCreAmt"+cLen).value=roundNumber(eval(sumCre),2);
		document.getElementById("totDbAmt"+cLen).value=roundNumber(eval(sumDb),2);
		
		totNet = eval(sumCre) - eval(sumDb);
		document.getElementById("totNet"+cLen).value=roundNumber(eval(totNet),0);
	
	
		 var maxTot = document.getElementById('paraFrm_maxTotal').value;
		  var netP =0;
	  try{
		  for(var m=0;m<=maxTot;m++)
		  {
		  	if(trim(document.getElementById('onholdType'+m).value) != "PD")
		  		netP= eval(netP) + eval(document.getElementById('totNet'+m).value);
		  }
		  }
		  catch(e2)
		  {
		  netP =0;
		  }
	try{
	var netTotShortDays=document.getElementById('netTotShortDays').value;
	 }catch(e){
			//alert(e);
			netTotShortDays=0;
		}
	
		try{
		var onholdAmt = eval(netP) + eval(netTotShortDays);
		}catch(e){
			//alert(e);
			onholdAmt = 0;
		}
		
		document.getElementById('paraFrm_salaryAmt').value= roundNumber(eval(onholdAmt),0);
}


function sumNet()
{
	try{
		try{
		var maxTot = document.getElementById('paraFrm_maxTotal').value;
		}catch(e){
		 maxTot = 0;
		 //
		}
	  	var flag=document.getElementById('paraFrm_processFlag').value;
		var gratuity =parseFloat(document.getElementById('paraFrm_gratuity').value);
		var reimburse=parseFloat(document.getElementById('paraFrm_reimburse').value);
		var deduct=parseFloat(document.getElementById('paraFrm_deduct').value);
		var settAmt = document.getElementById('paraFrm_settleAmt').value;
		if(gratuity=="")
		{
			gratuity=0;
		}
		if(reimburse=="")
		{
			reimburse=0;
		}
		if(deduct=="")
		{
			deduct=0;
		}
		var leaveEncash=0;
	    try{
	    leaveEncash=document.getElementById('paraFrm_totalAmt').value;
	 	}catch(e){
	 	
	 	}
		var netP =0;
	  	var setVal =0;
	  	if(flag=="true")
	  	{
		  for(var m=0;m<=maxTot;m++)
		  {
		  	if(trim(document.getElementById('onholdType'+m).value) != "PD")
		  		netP= eval(netP) + eval(document.getElementById('totNet'+m).value);
		  } 
	  	}   
	 	var shortPay = 0;
		try{
			shortPay= document.getElementById('netTotShortDays').value;
		}
		catch(e1)
		{
			shortPay =<%= netTotShortDays%>;
		}
	
		
	    var netValue = eval(netP)+ eval(gratuity)+
											eval(reimburse)+
											eval(leaveEncash) - 
										eval(deduct);
		var result = eval(netValue)	+ eval(shortPay);
		document.getElementById('paraFrm_settleAmt').value= roundNumber(eval(result),0);
		
	}catch(e)
	{
		alert(e);   						 
	}
}

function replaceBlankWithZero(obj){
	if(obj.value==""){
		obj.value=0;
	}
	if(obj.value=="."){
		obj.value=0.0;
	}
	try{
	if(obj.value.indexOf(".")!=obj.value.lastIndexOf(".")){
		//more than one dot
		//alert("Only one dot is allowed");
		obj.value=obj.value.substring(0,obj.value.length-1);
	}
	}catch(e){
		alert(e);
	}
}

function saveandnextFun(){
	document.getElementById("paraFrm").action ='SettlmentDetails_saveAndNextSecond.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
}

function previousFun(){
	document.getElementById("paraFrm").action ='SettlmentDetails_previousSecond.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
}

function saveandpreviousFun(){
	document.getElementById("paraFrm").action ='SettlmentDetails_saveAndPreviousSecond.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
}

function nextFun(){
	document.getElementById("paraFrm").action ='SettlmentDetails_nextSecond.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
}

function saveFun(){
	/*try{
	var sCode=document.getElementById('paraFrm_resignCode').value;
	var lockFlag = document.getElementById('paraFrm_lockFlag').value;
	if(sCode !="" || trim(sCode.toString())!="null"){
		if(lockFlag=='Y'){  
		  	alert('Settlement has been already locked so you can not update !');
		  	return false;
	  	}
	}
	}catch(e){
		alert(e);
	}*/
	document.getElementById("paraFrm").action='SettlmentDetails_saveSecond.action';
	document.getElementById("paraFrm").submit();
	document.getElementById('paraFrm').target = "main";
}

/*function unlockFun(){
	 var lockFlag = document.getElementById('paraFrm_lockFlag').value;
	 if(lockFlag=='Y')
	 {
	  	var conf = confirm('Do you really want to unlock the settlement ?');
		if(conf){
			document.getElementById("paraFrm").action ='SettlmentDetails_unLock.action';
			document.getElementById("paraFrm").submit();
			document.getElementById('paraFrm').target = "main";
		}
		else
			return false;
	}else{
		alert('Please Lock the Settlement First!');
		return false;
	}
  	return true;
}*/

	function unlockFun() {
  		doAuthorisation('4', 'Settlement', 'U');
	}
	
	function doUnlock() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'SettlmentDetails_unLock.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}

function deleteFun()
{
	if(document.getElementById('paraFrm_settCode').value==""){
		alert("Please select "+document.getElementById('employee1').innerHTML.toLowerCase());
		return false;
	}
	
	var con=confirm('Do you really want to delete the record?')
	if(con){
		var del="SettlmentDetails_delete.action";
		document.getElementById('paraFrm').action=del;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	} else{
	     return false;
	}
}

function reportFun(){
	var settCode=document.getElementById('paraFrm_settCode').value;
	if(settCode=="" || trim(settCode.toString())=="null"){
		alert('Please Select a record to generate report');
		return false;
	}
	document.getElementById('paraFrm').target="_blank";
  	document.getElementById('paraFrm').action="SettlmentDetails_report.action";
  	document.getElementById('paraFrm').submit();  
  	document.getElementById('paraFrm').target="main"; 
}
</script>
