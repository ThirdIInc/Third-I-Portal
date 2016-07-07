
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.paradyne.lib.Utility"%>
<%
	String frm = "" + (String) request.getAttribute("viewFm");
	String grade = "" + (String) request.getAttribute("viewGrade");
	String temp = "";
	String tempGd = "";
	if (frm.equals("viewFm")) {
		temp = "inline";

	} else {
		temp = "none";
	}

	if (grade.equals("viewGrade")) {
		tempGd = "inline";

	} else {
		tempGd = "none";
	}
%>

<%@page import="com.lowagie.text.Document"%>
<script type="text/javascript" src="Ajax.js"></script>
<s:form action="EmpCrediteGov" id="paraFrm" validate="true"
	method="post" name="CreditForm" theme="simple" target="main">

	<s:hidden name="pfFlag" />

	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Credit Configuration</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<s:if test="generalFlag">
				</s:if>
				<s:else>
					<tr>
						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="78%"><s:if test="viewFlag">
									<input type="button" class="search"
										onclick="javascript:callsF9(500,325,'EmpCrediteGov_f9action.action');"
										value="Search" />
								</s:if> <s:if test="insertFlag">
									<s:submit cssClass="add" action="EmpCrediteGov_save"
										theme="simple" value="Save" onclick="return call()" />
										<s:submit cssClass="token"
									action="EmpCrediteGov_calculateCreditDebit" theme="simple"
									value="Formula Calculation" onclick="return call()" />
									<!--
											<s:submit cssClass="pagebutton" action="EmpCrediteGov_calculate" theme="simple" value="  Calculate  " />
									-->
								</s:if> <s:if test="viewFlag">
									<s:submit cssClass="reset" action="EmpCrediteGov_reset"
										theme="simple" value="Reset" />
								</s:if> <s:if test="deleteFlag">
									<s:submit cssClass="delete" action="EmpCrediteGov_delete"
										theme="simple" value="Delete"
										onclick="return callDelete('paraFrm_empId')" />
								</s:if> </td>

								<td width="22%">
								<div align="right"><font color="red">*</font> Indicates
								Required</div>
								</td>
							</tr>
						</table>
						<label></label></td>
					</tr>
				</s:else>
				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td colspan="5" class="formhead"><strong
										class="forminnerhead"><label class="set"
										id="empCredit" name="empCredit""><%=label.get("empCredit")%></label></strong></td>
								</tr>
								<s:if test="generalFlag">
									<tr>
										<td width="15%" class="formtext"><label class="set"
											id="employee" name="employee"><%=label.get("employee")%></label><font
											color="red"> *</font> :</td>
										<td width="35%"><s:property value="empToken" /> <s:property
											value="empName" /></td>
									</tr>

									<tr>
										<td width="15%"><label class="set" id="branch"
											name="branch"><%=label.get("branch")%></label>:</td>
										<td width="35%"><s:property value="empCenter" /> &nbsp;</td>

										<td width="15%"><label class="set" id="designation"
											name="designation"><%=label.get("designation")%></label>:</td>
										<td width="35%"><s:property value="empRank" /></td>
									</tr>
									<tr>
										<td width="15%"><label class="set" id="grade"
											name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
										:</td>
										<td width="35%"><s:property value="gradeName" /></td>
									</tr>
									<tr>
										<td width="15%"><label class="set" id="formula"
											name="formula" ondblclick="callShowDiv(this);"><%=label.get("formula")%></label>
										:</td>
										<td width="35%"><s:property value="frmName" /> <s:hidden
											name="frmId" /></td>
										<td width="15%"><label class="set" id="grsAmount"
											name="grsAmount" ondblclick="callShowDiv(this);"><%=label.get("grsAmount")%></label>:</td>
										<td width="35%"><s:property value="grsAmt" /></td>
									</tr>
								</s:if>
								<s:else>
									<tr>
										<td width="15%" class="formtext"><label class="set"
											id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font
											color="red"> *</font> :</td>
										<td colspan="3"><s:hidden name="emp_Id" /> <s:hidden
											name="empId" /> <s:textfield theme="simple" name="empToken"
											size="10" readonly="true" /><s:textfield theme="simple"
											name="empName" size="91" readonly="true" /><s:hidden
											theme="simple" name="EmpCrediteGov" /> <s:hidden
											name="empstatus"></s:hidden></td>
									</tr>

									<tr>
										<td width="15%"><label class="set" id="branch"
											name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
										</td>
										<td width="35%"><s:textfield theme="simple"
											readonly="true" name="empCenter" size="40" /> &nbsp;</td>

										<td width="15%"><label class="set" id="designation"
											name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
										<td width="35%"><s:textfield theme="simple" size="30"
											name="empRank" readonly="true" /></td>
									</tr>

									<tr>
										<td width="15%"><label class="set" id="grade"
											name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
										:</td>
										<td width="35%"><s:hidden name="gradeId" /><s:textfield
											theme="simple" readonly="true" size="20" name="gradeName" /><s:if
											test="viewFlag">
											<img src="../pages/images/search2.gif" class="iconImage"
												height="16" align="absmiddle" width="16"
												onclick="javascript:callsF9(800,525,'EmpCrediteGov_f9gradection.action');">
										</s:if></td>
									</tr>
									<tr>
										<td width="15%"><label class="set" id="formula"
											name="formula" ondblclick="callShowDiv(this);"><%=label.get("formula")%></label>
										:</td>
										<td width="35%"><s:textfield theme="simple"
											readonly="true" size="20" name="frmName" /> <s:hidden
											name="frmId" /> <s:if test="viewFlag">
											<img src="../pages/images/search2.gif" class="iconImage"
												height="16" align="absmiddle" width="16"
												onclick="javascript:callsF9(800,525,'EmpCrediteGov_f9frmaction.action');">
										</s:if></td>
										<td width="15%"><label class="set" id="grsAmount"
											name="grsAmount" ondblclick="callShowDiv(this);"><%=label.get("grsAmount")%></label>:</td>
										<td width="35%"><s:textfield theme="simple" size="12"
											name="grsAmt" onkeypress="return numbersOnly();" /> <s:submit
											cssClass="pagebutton" name="calculate"
											action="EmpCrediteGov_calCtc" value="Calculate"
											onclick="return callCal();" /></td>
									</tr>
								</s:else>
								<!-- generalflag=false -->
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" valign="bottom" class="txt"><img
						src="../pages/images/recruitment/space.gif" width="5" height="5" /></td>
				</tr>
				<s:if test="flagList">
					<tr>
						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">
							<tr>
								<td class="formtext" width="100%">
								<table width="100%" border="0" cellpadding="1" cellspacing="1">

									<%
										Object[][] rows = (Object[][]) request.getAttribute("rows");
										HashMap afdata = (HashMap) request.getAttribute("data");
										Object[][] mdata = (Object[][]) request.getAttribute("mdata");
										Object[][] qdata = (Object[][]) request.getAttribute("qdata");
										Object[][] hdata = (Object[][]) request.getAttribute("hdata");
										Object[][] adata = (Object[][]) request.getAttribute("adata");
										//Perq
										Object[][] mdataPerq = null;
										Object[][] qdataPerq = null;
										Object[][] hdataPerq = null;
										Object[][] adataPerq = null;
										if (request.getAttribute("mdataPerq") != null) {
											mdataPerq = (Object[][]) request.getAttribute("mdataPerq");
										}
										if (request.getAttribute("qdataPerq") != null) {
											qdataPerq = (Object[][]) request.getAttribute("qdataPerq");
										}
										if (request.getAttribute("hdataPerq") != null) {
											hdataPerq = (Object[][]) request.getAttribute("hdataPerq");
										}
										if (request.getAttribute("adataPerq") != null) {
											adataPerq = (Object[][]) request.getAttribute("adataPerq");
										}
										//System.out.println("sop --"+mdataPerq.length);

										int i = 0;
										int i2 = 0;
										int s = 1;
										int s2 = 1;
									%>
									<%!int p = 0, t = 0;%>
									<%!int p2 = 0;
	int t2 = 0;%>

									<%
									//	String audFlag = (String) afdata.get(String.valueOf(rows[i][1]));
									%>
									<tr>
										<td colspan="2" valign="top" width="50%">
										<table cellpadding="0" cellspacing="0" class="formbg">
											<tr>
												<td class="formth" width="30%"><label class="set"
													id="salary.header" name="salary.header"
													ondblclick="callShowDiv(this);"><%=label.get("salary.header")%></label></td>
												<td class="formth" width="10%" now><label class="set"
													id="period" name="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label></td>
												<td class="formth" width="10%" now><label class="set"
													id="amount" name="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></td>
											</tr>
											<!-- Credit-Monthly -->
											<s:iterator value="mIterator">
												<tr>
													<td class="sortableTD" width="30%"><s:label
														value="%{salHead}" theme="simple" /> <s:hidden
														name="credCode" /> &nbsp; <s:hidden name="salHead" /> <s:hidden
														name="period" value="Monthly" id='<%="period"+i%>' /></td>
													<td class="sortableTD" width="10%"><%=String.valueOf(mdata[i][2])%></td>
													<s:if test="generalFlag">
														<td class="sortableTD" width="10%" align="right"><%=Utility.twoDecimals(String.valueOf(mdata[i][3]))%>&nbsp;
														</td>
													</s:if>
													<s:else>
														<td class="sortableTD" width="10%"><input type="text"
															size="6" maxlength="15" name="amount" id="amount<%=i%>"
															onkeyup="sumAmt('period',<%=i%>)"
															value="<%=Utility.twoDecimals(String.valueOf(mdata[i][3]))%>"
															onkeypress="return numbersWithDot();" />&nbsp;</td>
													</s:else>
													<%
														i++;
														s++;
														p++;
													%>
												</tr>
											</s:iterator>
											<!-- Credit - Quarterly -->
											<%
											int x = 0;
											%>
											<s:iterator value="qIterator">
												<tr>
													<td class="sortableTD" width="30%"><s:label
														value="%{salHead}" theme="simple" /> <s:hidden
														name="credCode" /> &nbsp; <s:hidden name="salHead" /> <s:hidden
														name="period" value="Quarterly" id='<%="period"+i%>' /></td>
													<td class="sortableTD" width="10%"><%=String.valueOf(qdata[x][2])%></td>
													<s:if test="generalFlag">
														<td class="sortableTD" width="20%" align="right"><%=Utility.twoDecimals(String.valueOf(qdata[x][3]))%>&nbsp;
														</td>
													</s:if>
													<s:else>
														<td class="sortableTD" width="20%"><input type="text"
															size="6" maxlength="15" name="amount" id="amount<%=i%>"
															onkeyup="sumAmt('period',<%=i%>)"
															value="<%=Utility.twoDecimals(String.valueOf(qdata[x][3]))%>"
															onkeypress="return numbersWithDot();" />&nbsp;</td>
													</s:else>
													<%
														x++;
														i++;
														s++;
														p++;
													%>
												</tr>
											</s:iterator>
											<!-- Credit - Half Yearly -->
											<%
											x = 0;
											%>
											<s:iterator value="hIterator">
												<tr>
													<td class="sortableTD" width="30%"><s:label
														value="%{salHead}" theme="simple" /> <s:hidden
														name="credCode" /> &nbsp; <s:hidden name="salHead" /> <s:hidden
														name="period" value="Half Yearly" id='<%="period"+i%>' />
													</td>
													<td class="sortableTD" width="10%"><%=String.valueOf(hdata[x][2])%></td>
													<s:if test="generalFlag">
														<td class="sortableTD" width="20%" align="right"><%=Utility.twoDecimals(String.valueOf(hdata[x][3]))%>&nbsp;
														</td>
													</s:if>
													<s:else>
														<td class="sortableTD" width="20%"><input type="text"
															size="6" maxlength="15" name="amount" id="amount<%=i%>"
															onkeyup="sumAmt('period',<%=i%>)"
															value="<%=Utility.twoDecimals(String.valueOf(hdata[x][3]))%>"
															onkeypress="return numbersWithDot();" />&nbsp;</td>
													</s:else>
													<%
														x++;
														i++;
														s++;
														p++;
													%>
												</tr>
											</s:iterator>
											<!-- Credit - Annually -->
											<%
											x = 0;
											%>
											<s:iterator value="aIterator">
												<tr>
													<td class="sortableTD" width="30%"><s:label
														value="%{salHead}" theme="simple" /> <s:hidden
														name="credCode" /> &nbsp; <s:hidden name="salHead" /> <s:hidden
														name="period" value="Annually" id='<%="period"+i%>' /></td>
													<td class="sortableTD" width="10%"><%=String.valueOf(adata[x][2])%></td>
													<s:if test="generalFlag">
														<td class="sortableTD" width="20%" align="right"><%=Utility.twoDecimals(String.valueOf(adata[x][3]))%>&nbsp;
														</td>
													</s:if>
													<s:else>
														<td class="sortableTD" width="20%"><input type="text"
															size="6" maxlength="15" name="amount" id="amount<%=i%>"
															onkeyup="sumAmt('period',<%=i%>)"
															value="<%=Utility.twoDecimals(String.valueOf(adata[x][3]))%>"
															onkeypress="return numbersWithDot();" />&nbsp;</td>
													</s:else>
													<%
														x++;
														i++;
														s++;
														p++;
													%>
												</tr>
											</s:iterator>
										</table>
										</td>
										<td colspan="2" valign="top" width="50%">
										<table width="100%" cellpadding="0" cellspacing="0"
											class="formbg">


											<tr>
												<td class="formth" width="30%"><label class="set"
													id="perHead" name="perHead" ondblclick="callShowDiv(this);"><%=label.get("perHead")%></label></td>
												<td class="formth" width="10%"><label class="set"
													id="period" name="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label></td>
												<td class="formth" width="10%"><label class="set"
													id="amount" name="amount" ondblclick="callShowDiv(this);"><%=label.get("amount")%></label></td>
											</tr>
											<!-- Perq Monthly -->
											<s:iterator value="mIteratorPer">
												<tr>
													<td class="sortableTD" width="30%"><s:label
														value="%{perqHead}" theme="simple" /> <s:hidden
														name="perqCode" /> &nbsp; <s:hidden name="perqHead" />
													&nbsp;</td>
													<s:hidden name="periodPerq" value="Monthly"
														id='<%="periodPerq"+i2%>' />
													<td class="sortableTD"><%=String.valueOf(mdataPerq[i2][2])%></td>
													<s:if test="generalFlag">
														<td class="sortableTD" width="10%" align="right"><%=Utility.twoDecimals(String.valueOf(mdataPerq[i2][3]))%>&nbsp;
														</td>
													</s:if>
													<s:else>
														<td class="sortableTD" width="10%"><input type="text"
															size="6" maxlength="15" name="amountPerq"
															id="amountPerq<%=i2%>"
															onkeyup="sumAmt('periodPerq',<%=i2%>)"
															value="<%=Utility.twoDecimals(String.valueOf(mdataPerq[i2][3]))%>"
															onkeypress="return numbersWithDot();" />&nbsp;</td>
													</s:else>
													<%
														i2++;
														s2++;
														p2++;
													%>
												</tr>
											</s:iterator>
											<!-- Perq Quarterly -->
											<%
											x = 0;
											%>
											<s:iterator value="qIteratorPer">
												<tr>
													<td class="sortableTD" width="30%"><s:label
														value="%{perqHead}" theme="simple" /> <s:hidden
														name="perqCode" /> &nbsp; <s:hidden name="perqHead" />
													&nbsp;</td>
													<s:hidden name="periodPerq" value="Quarterly"
														id='<%="periodPerq"+i2%>' />
													<td class="sortableTD"><%=String.valueOf(qdataPerq[x][2])%></td>
													<s:if test="generalFlag">
														<td class="sortableTD" width="10%" align="right"><%=Utility.twoDecimals(String
									.valueOf(qdataPerq[x][3]))%>&nbsp;</td>
													</s:if>
													<s:else>
														<td class="sortableTD" width="10%"><input type="text"
															size="6" maxlength="15" name="amountPerq"
															id="amountPerq<%=i2%>"
															onkeyup="sumAmt('periodPerq',<%=i2%>)"
															value="<%=Utility.twoDecimals(String.valueOf(qdataPerq[x][3]))%>"
															onkeypress="return numbersWithDot();" />&nbsp;</td>
													</s:else>
													<%
														x++;
														i2++;
														s2++;
														p2++;
													%>
												</tr>
											</s:iterator>
											<!-- Perq Half Yearly -->
											<%
											x = 0;
											%>
											<s:iterator value="hIteratorPer">
												<tr>
													<!-- 	<td class="border2" width="10%"></td> place s variable here to get sr.no -->

													<td class="sortableTD" width="30%"><s:label
														value="%{perqHead}" theme="simple" /> <s:hidden
														name="perqCode" /> &nbsp; <s:hidden name="perqHead" />
													&nbsp;</td>
													<s:hidden name="periodPerq" value="Half Yearly"
														id='<%="periodPerq"+i2%>' />
													<td class="sortableTD"><%=String.valueOf(hdataPerq[x][2])%></td>
													<s:if test="generalFlag">
														<td class="sortableTD" width="10%" align="right"><%=Utility.twoDecimals(String
									.valueOf(hdataPerq[x][3]))%>&nbsp;</td>
													</s:if>
													<s:else>
														<td class="sortableTD" width="10%"><input type="text"
															size="6" maxlength="15" name="amountPerq"
															id="amountPerq<%=i2%>"
															onkeyup="sumAmt(('periodPerq',<%=i2%>)"
															value="<%=Utility.twoDecimals(String.valueOf(hdataPerq[x][3]))%>"
															onkeypress="return numbersWithDot();" />&nbsp;</td>
													</s:else>
													<%
														x++;
														i2++;
														s2++;
														p2++;
													%>
												</tr>
											</s:iterator>
											<!-- Perq Annually -->
											<%
											x = 0;
											%>
											<s:iterator value="aIteratorPer">
												<tr>
													<td class="sortableTD" width="30%"><s:label
														value="%{perqHead}" theme="simple" /> <s:hidden
														name="perqCode" /> &nbsp; <s:hidden name="perqHead" />
													&nbsp;</td>
													<s:hidden name="periodPerq" value="Annually"
														id='<%="periodPerq"+i2%>' />
													<td class="sortableTD"><%=String.valueOf(adataPerq[x][2])%></td>
													<s:if test="generalFlag">
														<td class="sortableTD" width="10%" align="right"><%=Utility.twoDecimals(String
									.valueOf(adataPerq[x][3]))%>&nbsp;</td>
													</s:if>
													<s:else>
														<td class="sortableTD" width="10%"><input type="text"
															size="6" maxlength="15" name="amountPerq"
															id="amountPerq<%=i2%>"
															onkeyup="sumAmt('periodPerq',<%=i2%>)"
															value="<%=Utility.twoDecimals(String.valueOf(adataPerq[x][3]))%>"
															onkeypress="return numbersWithDot();" />&nbsp;</td>
													</s:else>
													<%
														x++;
														i2++;
														s2++;
														p2++;
													%>
												</tr>
											</s:iterator>
											<%
														if (mdataPerq == null && qdataPerq == null && hdataPerq == null
														&& adataPerq == null) {
											%>
											<tr>
												<td colspan="3" class="sortableTD" align="center"><font
													color="red">No Perquisite Data</font></td>
											</tr>
											<%
											}
											%>
										</table>

										</td>


									</tr>
									<%
										t = p;
										p = 0;
										t2 = p2;
										p2 = 0;
									%>
									<tr>
										<td colspan="4">
										<hr>
										</td>
									</tr>
									<!-- 
							<tr>
								<td width="50%" colspan="2"><label  class = "set"  id="totMonth" name="totMonth" ondblclick="callShowDiv(this);"><%=label.get("totMonth")%></label>.
								<s:if test="generalFlag">
									<s:property value="totalamt"/>
								</s:if>
								<s:else>
								<s:textfield name="totalamt" theme="simple" size="6"
									readonly="true"  />
								</s:else>
								 &nbsp;&nbsp;&nbsp;<label  class = "set"  id="totAnnual" name="totAnnual" ondblclick="callShowDiv(this);"><%=label.get("totAnnual")%></label>.
								<s:if test="generalFlag">
									<s:property value="annualAmt"/>
								</s:if>
								<s:else>								
								<s:textfield name="annualAmt" theme="simple" size="6"
									readonly="true"  />
								</s:else>
								</td>
					<%if(mdataPerq != null || qdataPerq != null || hdataPerq != null || adataPerq != null ) { %>
								<td width="50%" colspan="2" align="right">
								<label  class = "set"  id="totalAmount" name="totalAmount" ondblclick="callShowDiv(this);"><%=label.get("totalAmount")%></label>.
								<s:if test="generalFlag">
									<s:property value="annualAmtPer"/>
								</s:if>
								<s:else>								
								<s:textfield name="annualAmtPer" readonly="true" size="6"
								theme="simple"  />
								</s:else>
								<s:hidden name="TotalAmt"/>
								</td>
					<%} %>
							</tr>
							-->
								</table>
								</td>
							</tr>
						</table>
						</td>
					</tr>

					<tr>
						<td colspan="3">
						<table width="100%" class="formbg" cellpadding="0" cellspacing="0"
							border="0">
							<!-- TOTAL MONTHLY SALARY -->
							<tr>
								<td width="10%"><label class="set" id="totMonth"
									name="totMonth" ondblclick="callShowDiv(this);"><%=label.get("totMonth")%></label>
								</td>
								<td width="25%"><s:if test="generalFlag">
									<s:property value="totalamt" />
								</s:if> <s:else>
									<s:textfield name="totalamt" theme="simple" size="8"
										readonly="true" cssStyle="background-color: #F2F2F2;" />
								</s:else></td>
							</tr>
							<!-- TOTAL ANNUAL SALARY -->
							<tr>
								<td width="10%"><label class="set" id="totAnnual"
									name="totAnnual" ondblclick="callShowDiv(this);"><%=label.get("totAnnual")%></label>
								</td>
								<td width="25%"><s:if test="generalFlag">
									<s:property value="annualAmt" />
								</s:if> <s:else>
									<s:textfield name="annualAmt" theme="simple" size="8"
										readonly="true" cssStyle="background-color: #F2F2F2;" />
								</s:else></td>
							</tr>
							<!-- TOTAL ANNUAL PERQUISITE -->
							<tr>
								<td width="10%"><label class="set" id="totalAmountPer"
									name="totalAmountPer" ondblclick="callShowDiv(this);"><%=label.get("totalAmountPer")%></label>.
								</td>
								<td width="25%"><s:if test="generalFlag">
									<s:property value="annualAmtPer" />
								</s:if> <s:else>
									<s:textfield name="annualAmtPer" readonly="true" size="8"
										theme="simple" cssStyle="background-color: #F2F2F2;" />
								</s:else> <s:hidden name="TotalAmt" /></td>
							</tr>
							<!-- PROVIDED FUND 
					<s:if test="pfFlag">
					<tr>
						<td width="10%">
							<label  class = "set"  id="providedfund" name="providedfund" ondblclick="callShowDiv(this);"><%=label.get("providedfund")%></label>.
						</td>
						<td width="25%">
							<s:if test="generalFlag">
								<s:property value="pfAmt"/>
							</s:if>
							<s:else>								
							<s:textfield name="pfAmt" readonly="true" size="8"
							theme="simple" cssStyle="background-color: #F2F2F2;" />
							</s:else>
						</td>
					</tr>
					</s:if>
					-->
							<!-- CTC -->
							<tr>
								<td width="10%"><label class="set" id="ctcamt"
									name="ctcamt" ondblclick="callShowDiv(this);"><%=label.get("ctcamt")%></label>.
								</td>
								<td width="25%"><s:if test="generalFlag">
									<s:property value="ctcAmt" />
								</s:if> <s:else>
									<s:textfield name="ctcAmt" readonly="true" size="8"
										theme="simple" cssStyle="background-color: #F2F2F2;" />
								</s:else></td>
							</tr>
						</table>
						</td>
					</tr>
				</s:if>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>  
 
 function callCal()
 {         var employee=document.getElementById('employee').innerHTML.toLowerCase();
           var formula=document.getElementById('formula').innerHTML.toLowerCase();
           var grsAmount=document.getElementById('grsAmount').innerHTML.toLowerCase();
           var emp= document.getElementById('paraFrm_empId').value
           var forname=document.getElementById('paraFrm_frmName').value
           var grossamount=document.getElementById('paraFrm_grsAmt').value
		         if(emp=="") 
		         
		         {
						alert("Please select the "+employee);
						return false;				
				}
			if(forname==" ")
			    {
				     alert("please select "+formula);				   
				     return false;
			    }
								
			    if(grossamount=="")
			    {
				     alert("please enter "+grsAmount);
				     document.getElementById('paraFrm_grsAmt').focus();
				     return false;
			    }
 }
  
function chkflag(val)
{
 
		if(val == 1)
		{
 
			if(document.getElementById('paraFrm_grdFlag').checked == true)
			{
			
				document.getElementById('grd_Flag').style.display = '';
				document.getElementById('frm_Flag').style.display = 'none';				
				document.getElementById('paraFrm_frmFlag').checked = false;
				document.getElementById('paraFrm_frmId').value="";
				document.getElementById('paraFrm_frmName').value="";
				document.getElementById('paraFrm_grsAmt').value="";
			
			}
			else 
			{
				
				document.getElementById('frm_Flag').style.display = '';
				document.getElementById('grd_Flag').style.display = 'none';
				document.getElementById('paraFrm_frmFlag').checked = true;
				document.getElementById('paraFrm_gradeId').value="";
				document.getElementById('paraFrm_gradeName').value="";	
			}
		}
	else
	{
		if(document.getElementById('paraFrm_frmFlag').checked == true)
		{
			
			document.getElementById('frm_Flag').style.display = '';
			document.getElementById('grd_Flag').style.display = 'none';
			document.getElementById('paraFrm_grdFlag').checked = false;
			document.getElementById('paraFrm_gradeId').value="";
			document.getElementById('paraFrm_gradeName').value="";	
			
		}
		else 
		{
		 
			document.getElementById('grd_Flag').style.display = '';
			document.getElementById('frm_Flag').style.display = 'none';
			document.getElementById('paraFrm_grdFlag').checked = true;
			document.getElementById('paraFrm_frmId').value="";
		    document.getElementById('paraFrm_frmName').value="";
			 document.getElementById('paraFrm_grsAmt').value="";
			
		}
	
 	}
	
} 

function call(){
       var employee=document.getElementById('employee').innerHTML.toLowerCase();
       var emp=document.getElementById('paraFrm_empId').value
				if(emp=="") {
				alert("Please select the "+employee);
				return false;
				
				}
				
}

function callDel(){
				var conf=confirm("Are you sure to delete this record?");
				if(conf) {
				return true;
				
				}else {
				return false;
			}
				
				
	}
				






function sumPreAmt(s)
{
var totalrow = <%=t%> ;
var count=0;
	for(var row = 0;row < totalrow ;row++)
	{
	var values=document.getElementById('preCommAmt'+row).value;
	if(values =="" || values=='.'){
		values =0;
	}
	values =eval(values*100/100);
	
	count=eval(count)+eval(values);
	
	}
//var debit=document.getElementById('preCommAmt'+s).value;
document.getElementById('totalPreCommisionamt').value=count;
}
function sumAmt(p,s) 
{
	var value=document.getElementById('amount'+s).value;
	if(value!='.' && isNaN(value)){
		alert("Only single dot is allowed");
		document.getElementById('amount'+s).value=0;
		value=0;
	}
	if(p=='period')
	{
		var totalrow = <%=t%> ;
		var a='amount';
	}
	else
	{
		var totalrow = <%=t2%> ;
		var a='amountPerq';
	}
	var count=0;
	var count1=0;
	var count2=0;
	var count3=0;
	var count4=0;
	for(var row = 0;row < totalrow ;row++)
	{
	if(document.getElementById(p+row).value=="Monthly"){
	var values=document.getElementById(a+row).value;
	if(values =="" || values=='.'){
		values =0;
	}
	values =eval(values*100/100);
	count=eval(count)+eval(values);
	count1=eval(count1)+eval(values)*12;
	}
	else if(document.getElementById(p+row).value=="Half Yearly"){
			var values=document.getElementById(a+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
			
				count2=eval(count2)+(eval(values)*2);
	}
	
	else if(document.getElementById(p+row).value=="Annually"){
		var values=document.getElementById(a+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
				count3=eval(count3)+eval(values);
	
	
		}
	else if(document.getElementById(p+row).value=="Quarterly"){
		var values=document.getElementById(a+row).value;
			if(values =="" || values=='.'){
			values =0;
			}
				values =eval(values*100/100);
				count4=eval(count4)+eval(values)*4;
		}
	
	
	
	
	}
	var amount = 0;
	var amount1 = 0;
	if(p=='period'){
		document.getElementById('paraFrm_totalamt').value=roundNumber(count,2);	
		document.getElementById('paraFrm_annualAmt').value=roundNumber(count1+count2+count3+count4,2);
		amount1 = document.getElementById('paraFrm_annualAmtPer').value;
	}
	else{
		document.getElementById('paraFrm_annualAmtPer').value=roundNumber(count1+count2+count3+count4,2);
		amount1 = document.getElementById('paraFrm_annualAmt').value;
	}
	var amount = 0;
	var p = document.getElementById('paraFrm_pfAmt').value;
	amount = ((eval(p)+eval(amount1)+eval(count1)+eval(count2)+eval(count3)+eval(count4))*100)/100;
	document.getElementById('paraFrm_ctcAmt').value = Math.round(amount);

}	

function roundNumber(num, dec) {
	var result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
	return result;
}

function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
			
		if ((("0123456789.").indexOf(keychar) > -1))
			{
			return true;	
			}
		else {
			myfield.focus();	
			return false;
		}
	}

 function callChk(id){

 if(document.getElementById(id).value=='Y'){
 alert('The Amount will be set to 0')
  document.getElementById(id).value='N';
 }else  if(document.getElementById(id).value=='N'){
  document.getElementById(id).value='Y';
 } 
} 

function valAmt(ctcfieldname,ctclabelname,period,i)
{

	var amount=document.getElementById(ctcfieldname).value;
	
	if(trim(amount)!=""){
	if(isNaN(amount)) { 
	alert("Single dot is allowed in "+ctclabelname+" field.");
	document.getElementById(ctcfieldname).value='0';
	sumAmt(period,i);
	document.getElementById(ctcfieldname).focus();
	return false;
	
	}	
	}
	 return true;
}	
  </script>
