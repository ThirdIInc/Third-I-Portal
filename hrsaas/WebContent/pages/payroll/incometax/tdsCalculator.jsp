<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<style>
.formbgTDS {
	background-image: url(../pages/common/css/default/images/grad.gif);
	background-repeat: repeat-x;
	padding: 5px;
}
</style>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/payrollAjax.js">

</script>

<s:form action="TdsCalculator" target="main" theme="simple"
	validate="true" id="paraFrm">
	<table class="formbg" border="0" width="100%"> <!-- outermost table -->
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">TDS Calculator</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0"> <!-- 2nd inner table -->
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>

		</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="1"
						class="formbg">
						<!-- employee official details table -->
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="1"
								cellspacing="2">
								<tr>
									<td width="24%"><label class="set" id="employee"
										name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><span
										class="formtext"> :</span><font color="red">*</font></td>
									<td height="22" colspan="4"><s:hidden name="empID"
										theme="simple" /><s:hidden name="empGender" theme="simple" /><s:hidden
										name="empAge" /><s:hidden name="empJoinedDate" /> <s:hidden
										name="centerId" theme="simple" /> <s:textfield theme="simple"
										readonly="true" size="15" name="empToken" /> <s:textfield
										label="%{getText('empName')}" theme="simple" size="77"
										readonly="true" name="empName" /> <s:if
										test="%{isShowButton}"></s:if> <s:elseif
										test="%{tdscal.generalFlag}"></s:elseif> <s:else>
										<img src="../pages/common/css/default/images/search2.gif"
											width="16" height="15"
											onclick="javascript:callsF9(500,325,'TdsCalculator_f9action.action');">
									</s:else></td>
								</tr>

								<tr>
									<td><label class="set" id="branch" name=branch
										ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td width="27%" height="22"><s:textfield theme="simple"
										size="25" readonly="true" name="empCenter" /></td>
									<td width="15%" height="22" class="formtext"><label
										class="set" id="designation" name="designation"
										ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
									<td width="34%" height="22"><s:textfield theme="simple"
										size="25" readonly="true" name="empRank" /></td>
								</tr>

								<tr>
									<td><label class="set" id="taxFinYrFrm" name="taxFinYrFrm"
										ondblclick="callShowDiv(this);"><%=label.get("taxFinYrFrm")%></label>:<font
										color="red">*</font></td>
									<td width="27%" height="22"><s:textfield theme="simple"
										size="25" name="fromYear" maxlength="4"
										onkeypress="return numbersOnly();" onblur="add()" /></td>
									<td width="15%" height="22" class="formtext"><label
										class="set" id="taxFinYrTo" name="taxFinYrTo"
										ondblclick="callShowDiv(this);"><%=label.get("taxFinYrTo")%></label>:</td>
									<td width="34%" height="22"><s:textfield theme="simple"
										size="25" readonly="true" name="toYear" /></td>
								</tr>
								<tr>
									<td colspan="5"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>

								<tr><s:hidden name="isShowButton"></s:hidden><s:hidden name="viewFlag"></s:hidden>
									<td colspan="5" align="center"><s:if
										test="%{isShowButton}"></s:if><s:else>
										<s:if test="%{viewFlag}">
											<s:submit cssClass="search"
												action="TdsCalculator_viewCalculator" theme="simple"
												value=" View " onclick="return callView()" />
											<s:submit cssClass="reset" action="TdsCalculator_reset"
												value=" Reset " />
										</s:if>
									</s:else></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					<!-- employee official details table ends --></td>
				</tr>


				<tr>
					<!-- Starting tr for Gross Income table.     -->

					<td colspan="6">

					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbgTDS">
						<!-- employee taxation details outermost table -->
						
						<tr>
							<td colspan="6">
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="1">
								<tr>
									<td colspan="6" width="100%" class="formhead"><strong
										class="forminnerhead"><label class="set"
										id="taxGrsSalCalc" name="taxGrsSalCalc"
										ondblclick="callShowDiv(this);"><%=label.get("taxGrsSalCalc")%></label></strong></td>
								</tr>
								<tr>
									<td colspan="3" height="5"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="4" /></td>
								</tr>
								<tr>
									<td colspan="3" width="50%" class="formhead"><strong
										class="forminnerhead">Credits Components</strong></td>
									<td colspan="3" width="50%" class="formhead"><strong
										class="forminnerhead">Perquisites</strong></td>
								</tr>
							</table>
							</td>
						</tr>

						<tr>
							<td colspan="3" width="50%" class="formtext" valign="top">
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<!-- employee credits details table -->
								<tr>
									<td width="20%" class="formth"><label class="set"
										id="taxSrNo" name="taxSrNo" ondblclick="callShowDiv(this);"><%=label.get("taxSrNo")%></label></td>
									<td width="60%" class="formth"><label class="set"
										id="taxCrdtName" name="taxCrdtName"
										ondblclick="callShowDiv(this);"><%=label.get("taxCrdtName")%></label></td>
									<td width="20%" class="formth"><label class="set"
										id="taxAmt" name="taxAmt" ondblclick="callShowDiv(this);"><%=label.get("taxAmt")%></label></td>
								</tr>

								<s:if test="noGrossData">
									<tr>
										<td width="100%" colspan="3" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
								<%
								int y = 1;
								%>
								<%!int z = 0;%>
								<s:iterator value="empGrossSalList">
									<s:hidden name="creditId" value="%{creditId}" />
									<tr>
										<td width="10%" class="border2" align="center"><%=y%></td>
										<td width="70%" class="border2"><s:property
											value="creditName" /></td>
										<td width="10%"><input type="text" name="creditAmt"
											id="creditAmt<%=y%>" value='<s:property value="creditAmt"/>'
											maxlength="12" size="20" theme="simple"
											onkeypress="return numbersOnly();" onkeyup="addGross(<%=y%>)" /></td>
									</tr>

									<%
									y++;
									%>

								</s:iterator>
								<%
								z = y;
								%>

								<tr>
									<td colspan="3" height="5"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>
							</table>
							</td>

							<td colspan="3" width="50%" class="formtext" valign="top">
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<!-- perqs details table -->
								<tr>
									<td width="20%" class="formth"><label class="set"
										id="taxSrNo1" name="taxSrNo" ondblclick="callShowDiv(this);"><%=label.get("taxSrNo")%></label></td>
									<td width="60%" class="formth"><label class="set"
										id="taxPreReqName" name="taxPreReqName"
										ondblclick="callShowDiv(this);"><%=label.get("taxPreReqName")%></label></td>
									<td width="20%" class="formth"><label class="set"
										id="taxAmt1" name="taxAmt" ondblclick="callShowDiv(this);"><%=label.get("taxAmt")%></label></td>
								</tr>

								<s:if test="noPerqData">
									<tr>
										<td width="100%" colspan="3" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
								<%
								int m = 1;
								%>
								<%!int n = 0;%>

								<s:iterator value="empPerqList">
									<s:hidden name="perqId" value="%{perqId}" />
									<tr>
										<td width="10%" align="center" class="border2"><%=m%></td>
										<td width="70%" class="border2"><s:property
											value="perqName" /></td>
										<td width="10%"><input type="text" name="perqAmt"
											id="perqAmt<%=m%>" value='<s:property value="perqAmt"/>'
											maxlength="12" size="20" theme="simple"
											onkeypress="return numbersOnly();" onkeyup="addPerq(<%=m%>)" /></td>
									</tr>

									<%
									m++;
									%>

								</s:iterator>
								<%
								n = m;
								%>
								<tr>
									<td colspan="3"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>



							</table>
							<!-- perqs table end --></td>

						</tr>

						<tr>
							<td colspan="6" width="100%" class="formtext" valign="top">
							<table width="100%" border="0" class="formbg" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td width="40%" colspan="2" align="right"><label
										class="set" id="taxToTcrdtAmt" name="taxToTcrdtAmt"
										ondblclick="callShowDiv(this);"><%=label.get("taxToTcrdtAmt")%></label>
									:</td>
									<td width="10%" height="10"><s:textfield theme="simple"
										readonly="true" name="totalCreditAmt" id="totalCreditAmt"
										size="20" cssStyle="background-color: #F2F2F2" /></td>
									<td width="40%" colspan="2" align="right"><label class="set"
										id="taxTotPreAmt" name="taxTotPreAmt"
										ondblclick="callShowDiv(this);"><%=label.get("taxTotPreAmt")%></label>
									:</td>
									<td width="40%" height="10"><s:textfield theme="simple"
										readonly="true" name="totalPerqAmt" id="totalPerqAmt"
										size="20" cssStyle="background-color: #F2F2F2" /></td>
								</tr>

								<tr>
									<td width="40%" align="right" colspan="2"><label class="set"
										id="leave.encash.add.income" name="leave.encash.add.income"
										ondblclick="callShowDiv(this);"><%=label.get("leave.encash.add.income")%></label>
									:</td>
									<td width="10%" height="10"><s:textfield theme="simple"
										readonly="true" name="leaveEncashAddedIncome"
										id="leaveEncashAddedIncome" size="20"
										cssStyle="background-color: #F2F2F2" /></td>
									<td width="40%" align="right" colspan="2"><label class="set"
										id="taxProTaxAmt" name="taxProTaxAmt"
										ondblclick="callShowDiv(this);"><%=label.get("taxProTaxAmt")%></label>
									:</td>
									<td height="10%"><s:textfield theme="simple"
										name="pTaxAmt" id="pTaxAmt" maxlength="12" size="20"
										onkeypress="return numbersOnly();"
										cssStyle="background-color: #F2F2F2" onkeyup="addNetInv()" /></td>
								</tr>
							</table>
							</td>
						</tr>
						
						


					</table>



					</td>

				</tr>
				<!-- end of credit & perqs table -->

			
				<!-- other income table end-->
				<s:hidden name="prevEmployerFlag" value="true"></s:hidden>
				<s:if test="prevEmployerFlag">
					<tr>
						<!-- previous employer income table -->
						<td>
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							id="tblSample" class="sortable">

							<tr>
								<td colspan="1" valign="top"><strong><label
									class="set" id="preEmployerLabel" name="preEmployerLabel"
									ondblclick="callShowDiv(this);"><%=label.get("preEmployerLabel")%></label>
								:</strong></td>
							</tr>
							<tr>
								<td width="30%"><label class="set" id="preNetTaxableIncome"
									name="preNetTaxableIncome" ondblclick="callShowDiv(this);"><%=label.get("preNetTaxableIncome")%></label>
								:</td>
								<td height="10"><s:textfield theme="simple" readonly="true"
									name="preNetTaxableIncomeAmt" id="preNetTaxableIncomeAmt"
									size="20" cssStyle="background-color: #F2F2F2" /></td>
								<td width="30%"><label class="set" id="preTaxPaid"
									name="preTaxPaid" ondblclick="callShowDiv(this);"><%=label.get("preTaxPaid")%></label>
								:</td>
								<td height="10"><s:textfield theme="simple" readonly="true"
									name="preTaxPaidAmt" id="preTaxPaidAmt" size="20"
									cssStyle="background-color: #F2F2F2" /></td>
							</tr>

							<tr>
								<td width="30%"><label class="set" id="prePF" name="prePF"
									ondblclick="callShowDiv(this);"><%=label.get("prePF")%></label>
								:</td>
								<td height="10"><s:textfield theme="simple" readonly="true"
									name="prePFAmt" id="prePFAmt" size="20"
									cssStyle="background-color: #F2F2F2" /></td>
								<td width="30%"><label class="set" id="prePTax"
									name="prePTax" ondblclick="callShowDiv(this);"><%=label.get("prePTax")%></label>
								:</td>
								<td height="10"><s:textfield theme="simple" readonly="true"
									name="prePTaxAmt" id="prePTaxAmt" size="20"
									cssStyle="background-color: #F2F2F2" /></td>
							</tr>
							<tr>
								<td colspan="5"><img
									src="../pages/common/css/default/images/space.gif" width="5"
									height="7" /></td>
							</tr>

						</table>
						</td>

					</tr>
					<!-- previous employer income ends -->
				</s:if>
				<s:else><!-- previous employer income hidden fields -->
					<s:hidden name="preNetTaxableIncomeAmt" id="preNetTaxableIncomeAmt"></s:hidden>
					<s:hidden name="prePFAmt" id="prePFAmt"></s:hidden>
					<s:hidden name="prePTaxAmt" id="prePTaxAmt"></s:hidden>
					<s:hidden name="preTaxPaidAmt" id="preTaxPaidAmt"></s:hidden>
				</s:else>

				<!-- Ending tr for Gross Income table.     -->


		<tr>
			<!-- Starting tr for Investment Declaration table.     -->

			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="1" class="formbgTDS">   <!-- investment outer table -->
				
						<tr>
							<td colspan="4" class="formhead"><strong
								class="forminnerhead"><label  class = "set"  id="taxInvstDecl" name="taxInvstDecl" ondblclick="callShowDiv(this);"><%=label.get("taxInvstDecl")%></label></strong></td>
						</tr>
						<tr>
							<td colspan="5"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="7" /></td>
						</tr>

						<!--Investment for EXEMPT -->

						<tr>
							<!--1st Start -->

							<td width="50%" class="formtext" valign="top">
							<table width="100%" border="0" cellpadding="1" cellspacing="1">
								
											<tr>
													<td colspan="4" valign="top" class="formth"><strong><label  class = "set"  id="taxExeUndrSec" name="taxExeUndrSec" ondblclick="callShowDiv(this);"><%=label.get("taxExeUndrSec")%></label>:</strong></td>
												</tr>

												<tr>
													<td width="5%" class="formth"><label  class = "set"  id="taxSrNo3" name="taxSrNo" ondblclick="callShowDiv(this);"><%=label.get("taxSrNo")%></label></td>
													<td width="85%" class="formth"><label  class = "set"  id="taxInvstName" name="taxInvstName" ondblclick="callShowDiv(this);"><%=label.get("taxInvstName")%></label></td>
													<td width="5%" class="formth"><label  class = "set"  id="taxInvstLimit" name="taxInvstLimit" ondblclick="callShowDiv(this);"><%=label.get("taxInvstLimit")%></label></td>
													<td width="5%" class="formth"><label  class = "set"  id="taxInvstAmt" name="taxInvstAmt" ondblclick="callShowDiv(this);"><%=label.get("taxInvstAmt")%></label></td>
												</tr>
												<s:if test="noData">
													<tr>
														<td width="100%" colspan="7" align="center"><font
															color="red">No Data To Display</font></td>
													</tr>
												</s:if>
												<%
												int i = 1;
												%>
												<%!int k1 = 0;%>
												<s:iterator value="empExemptInvList">
													<s:hidden name="invExemptId" value="%{invId}" />
													<tr>
														<td width="5%" class="border2" align="center"><%=i%></td>
														<td width="85%" class="border2"><s:property
															value="invExemptName" /></td>
														<td width="5%"><input type="text" style="background-color: #F2F2F2"
															name="invExemptLimit" id="invExemptLimit<%=i%>"
															value='<s:property value="invExemptLimit"/>'
															maxlength="12" size="10" theme="simple" readonly="true" /></td>
														<td width="5%"><input type="text" name="invExemptAmt"
															id="invExemptAmt<%=i%>"
															value='<s:property value="invExemptAmt"/>' maxlength="12"
															size="10" theme="simple"
															onkeypress="return numbersOnly();"
															onkeyup="addInv('Exempt')" /></td>
													</tr>

													<%
													i++;
													%>

												</s:iterator>
												<%
												k1 = i;
												%>
												<tr>
													<td colspan="5"><img
														src="../pages/common/css/default/images/space.gif"
														width="5" height="7" /></td>
												</tr>

												<tr>
													<td colspan="4">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0" class="formbg">


														<tr>

															<td>
															<table width="100%" border="0" align="center"
																cellpadding="0" cellspacing="2">


																<tr>
																	<td width="10%">&nbsp;</td>
																	<td width="50%">&nbsp;</td>
																	<td width="30%"><label  class = "set"  id="taxTotAmt2" name="taxTotAmt" ondblclick="callShowDiv(this);"><%=label.get("taxTotAmt")%></label> :</td>
																	<td height="10"><s:textfield theme="simple"
																		readonly="true" name="totalExemptInvAmt"
																		id="totalExemptInvAmt" size="10" cssStyle="background-color: #F2F2F2" /></td>
																</tr>
															</table>
															</td>
														</tr>
													</table>
													</td>
												</tr>
											
									
							</table>
							</td>
							
							
							<td width="50%" class="formtext" valign="top">
							
									<table width="100%" border="0" cellpadding="1" cellspacing="1">

												<tr>
													<td colspan="4" valign="top" class="formth"><strong><label  class = "set"  id="taxDedUndrChaptr" name="taxDedUndrChaptr" ondblclick="callShowDiv(this);"><%=label.get("taxDedUndrChaptr")%></label> :</strong></td>
												</tr>

												<tr>
													<td width="5%" class="formth"><label  class = "set"  id="taxSrNo4" name="taxSrNo" ondblclick="callShowDiv(this);"><%=label.get("taxSrNo")%></label></td>
													<td width="85%" class="formth"><label  class = "set"  id="taxInvstName1" name="taxInvstName" ondblclick="callShowDiv(this);"><%=label.get("taxInvstName")%></label></td>
													<td width="5%" class="formth"><label  class = "set"  id="taxInvstLimit1" name="taxInvstLimit" ondblclick="callShowDiv(this);"><%=label.get("taxInvstLimit")%></label></td>
													<td width="5%" class="formth"><label  class = "set"  id="taxInvstAmt1" name="taxInvstAmt" ondblclick="callShowDiv(this);"><%=label.get("taxInvstAmt")%></label></td>
												</tr>
												<s:if test="noRebateData">
													<tr>
														<td width="100%" colspan="7" align="center"><font
															color="red">No Data To Display</font></td>
													</tr>
												</s:if>
												<%
												int g = 1;
												%>
												<%!int h1 = 0;%>
												<s:iterator value="empRebateInvList">
													<s:hidden name="invRebateId" value="%{invId}" />

													<tr>
														<td width="5%" class="border2" align="center"><%=g%></td>
														<td width="85%" class="border2"><s:property
															value="invRebateName" /></td>
														<td width="15%"><input type="text" style="background-color: #F2F2F2"
															name="invRebateLimit" id="invRebateLimit<%=g%>"
															value='<s:property value="invRebateLimit"/>'
															maxlength="8" size="10" theme="simple" readonly="true" /></td>
														<td width="5%"><input type="text" name="invRebateAmt"
															id="invRebateAmt<%=g%>"
															value='<s:property value="invRebateAmt"/>' maxlength="12"
															size="10" theme="simple"
															onkeypress="return numbersOnly();"
															onkeyup="addInv('Rebate')" /></td>
													</tr>

													<%
													g++;
													%>

												</s:iterator>
												<%
												h1 = g;
												%>
												<tr>
													<td colspan="5"><img
														src="../pages/common/css/default/images/space.gif"
														width="5" height="7" /></td>
												</tr>

												<tr>
													<td colspan="4">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0" class="formbg">


														<tr>

															<td>
															<table width="100%" border="0" align="center"
																cellpadding="0" cellspacing="2">


																<tr>
																	<td width="30%"><label  class = "set"  id="taxSecLmt80c" name="taxSecLmt80c" ondblclick="callShowDiv(this);"><%=label.get("taxSecLmt80c")%></label> :</td>
																	<td width="36%"><s:textfield theme="simple"
																		readonly="true" name="rebateLimit"
																		id="rebateLimit" size="10"
																		cssStyle="background-color: #F2F2F2" /></td>
																	<td width="30%"><label  class = "set"  id="taxTotAmt3" name="taxTotAmt" ondblclick="callShowDiv(this);"><%=label.get("taxTotAmt")%></label></td>
																	<td height="10"><s:textfield theme="simple"
																		readonly="true" name="totalRebateInvAmt"
																		id="totalRebateInvAmt" size="10"
																		onchange="addNetInv()" cssStyle="background-color: #F2F2F2" /></td>
																</tr>
															</table>
															</td>
														</tr>
													</table>
													</td>
												</tr>
											</table>
											
									
							</td>

							
						</tr>
						<tr>
							<!--1st Start -->

							<td width="50%" class="formtext" valign="top">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								id="tblSample" class="formbgTDS">

								<tr>
									<td colspan="4" valign="top" class="formth"><strong><label
										class="set" id="taxDedUndrChptr6a" name="taxDedUndrChptr6a"
										ondblclick="callShowDiv(this);"><%=label.get("taxDedUndrChptr6a")%></label>
									:</strong></td>
								</tr>

								<tr>
									<td width="5%" class="formth"><label class="set"
										id="taxSrNo5" name="taxSrNo" ondblclick="callShowDiv(this);"><%=label.get("taxSrNo")%></label></td>
									<td width="85%" class="formth"><label class="set"
										id="taxInvstName2" name="taxInvstName"
										ondblclick="callShowDiv(this);"><%=label.get("taxInvstName")%></label></td>
									<td width="5%" class="formth"><label class="set"
										id="taxInvstLimit2" name="taxInvstLimit"
										ondblclick="callShowDiv(this);"><%=label.get("taxInvstLimit")%></label></td>
									<td width="5%" class="formth"><label class="set"
										id="taxInvstAmt2" name="taxInvstAmt"
										ondblclick="callShowDiv(this);"><%=label.get("taxInvstAmt")%></label></td>
								</tr>
								<s:if test="noVIAData">
									<tr>
										<td width="100%" colspan="7" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
								<%
								int e = 1;
								%>
								<%!int f = 0;%>
								<s:iterator value="empVIAInvList">
									<s:hidden name="invVIAId" value="%{invId}" />
									<tr>
										<td width="5%" class="border2" align="center"><%=e%></td>
										<td width="85%" class="border2"><s:property
											value="invVIAName" /></td>
										<td width="5%"><input type="text" name="invVIALimit"
											style="background-color: #F2F2F2" id="invVIALimit<%=e%>"
											value='<s:property value="invVIALimit"/>' maxlength="8"
											size="10" theme="simple" readonly="true" /></td>
										<td width="5%"><input type="text" name="invVIAAmt"
											id="invVIAAmt<%=e%>" value='<s:property value="invVIAAmt"/>'
											maxlength="12" size="10" theme="simple"
											onkeypress="return numbersOnly();" onkeyup="addInv('VIA')" /></td>
									</tr>

									<%
									e++;
									%>

								</s:iterator>
								<%
								f = e;
								%>
								<tr>
									<td colspan="5"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>

								<tr>
									<td colspan="4">
									<table width="100%" border="0" cellpadding="0" cellspacing="0"
										class="formbg">


										<tr>

											<td>
											<table width="100%" border="0" align="center" cellpadding="0"
												cellspacing="2">


												<tr>
													<td width="10%">&nbsp;</td>
													<td width="50%">&nbsp;</td>
													<td width="30%"><label class="set" id="taxTotAmt4"
														name="taxTotAmt" ondblclick="callShowDiv(this);"><%=label.get("taxTotAmt")%></label>
													:</td>
													<td height="10"><s:textfield theme="simple"
														readonly="true" name="totalVIAInvAmt" id="totalVIAInvAmt"
														size="10" cssStyle="background-color: #F2F2F2" /></td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>


							<td width="50%" class="formtext" valign="top">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="formbgTDS">


								<tr>
									<td colspan="5" valign="top" class="formth"><strong><label
										class="set" id="taxOtherIncm" name="taxOtherIncm"
										ondblclick="callShowDiv(this);"><%=label.get("taxOtherIncm")%></label>
									:</strong></td>
								</tr>

								<tr>
									<td width="5%" class="formth"><label class="set"
										id="taxSrNo2" name="taxSrNo" ondblclick="callShowDiv(this);"><%=label.get("taxSrNo")%></label></td>
									<td width="75%" class="formth"><label class="set"
										id="taxOTherIncmName" name="taxOTherIncmName"
										ondblclick="callShowDiv(this);"><%=label.get("taxOTherIncmName")%></label></td>
									<td width="10%" class="formth"><label class="set"
										id="taxType" name="taxType" ondblclick="callShowDiv(this);"><%=label.get("taxType")%></label></td>
									<td width="5%" class="formth"><label class="set"
										id="taxLimit" name="taxLimit" ondblclick="callShowDiv(this);"><%=label.get("taxLimit")%></label></td>
									<td width="5%" class="formth"><label class="set"
										id="taxAmt2" name="taxAmt" ondblclick="callShowDiv(this);"><%=label.get("taxAmt")%></label></td>
								</tr>
								<s:if test="noOtherData">
									<tr>
										<td width="100%" colspan="7" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
								<%
								int a = 1;
								%>
								<%!int b = 0;%>
								<s:iterator value="empOtherInvList">
									<s:hidden name="invOtherId" value="%{invId}" />
									<tr>
										<td width="5%" class="border2" align="center"><%=a%></td>
										<td width="75%" class="border2"><s:property
											value="invOtherName" /></td>
										<td width="10%" class="border2"><s:property
											value="otherDecodeFlag" /></td>
										<td width="5%"><input type="text"
											style="background-color: #F2F2F2" name="invOtherLimit"
											id="invOtherLimit<%=a%>"
											value='<s:property value="invOtherLimit"/>' maxlength="8"
											size="8" theme="simple" readonly="true" /></td>
										<input type="hidden" name="otherFlag" id="otherFlag<%=a%>"
											value='<s:property value="otherFlag"/>' />
										<td width="5%"><input type="text" name="invOtherAmt"
											id="invOtherAmt<%=a%>"
											value='<s:property value="invOtherAmt"/>' maxlength="12"
											size="8" theme="simple" onkeypress="return numbersOnly();"
											onkeyup="addOtherInv(<%=a%>)" /></td>

									</tr>

									<%
									a++;
									%>

								</s:iterator>
								<%
								b = a;
								%>
								<tr>
									<td colspan="5" height="1px"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="1" /></td>
								</tr>

								<tr>

									<td colspan="5">
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="2" class="formbg">
										<tr>
											<td align="right" width="95%" colspan="4"><label
												class="set" id="taxTotAmt1" name="taxTotAmt"
												ondblclick="callShowDiv(this);"><%=label.get("taxTotAmt")%></label>
											:</td>
											<td height="10"><s:textfield theme="simple"
												readonly="true" name="totalOtherInvAmt"
												id="totalOtherInvAmt" size="8"
												cssStyle="background-color: #F2F2F2" /></td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>

						</tr>


					</table>   <!-- investment outer table --></td>
		</tr>
				<tr>
					<!-- Starting tr for Net Taxable Income table.     -->

					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="2"
						class="formbg">


						<tr height="5">
							<td width="33%"><label class="set" id="taxTotGrsAmt"
								name="taxTotGrsAmt" ondblclick="callShowDiv(this);"><%=label.get("taxTotGrsAmt")%></label>
							:</td>
							<td width="67%" colspan="3" height="10"><s:textfield
								theme="simple" readonly="true" name="totalGrossAmt"
								id="totalGrossAmt" size="20"
								cssStyle="background-color: #F2F2F2" /></td>

						</tr>

						<tr height="5">
							<td width="40%"><label class="set" id="taxNetTotInvstAmt"
								name="taxNetTotInvstAmt" ondblclick="callShowDiv(this);"><%=label.get("taxNetTotInvstAmt")%></label>
							:</td>
							<td  width="10%"><s:textfield theme="simple" readonly="true"
								name="totalNetInvAmt" id="totalNetInvAmt" size="20"
								cssStyle="background-color: #F2F2F2" /></td>
							
						</tr>

						<tr height="5">
							<td class="formhead" width="40%"><strong
								class="forminnerhead"><label class="set"
								id="taxNetTaxbleAmt" name="taxNetTaxbleAmt"
								ondblclick="callShowDiv(this);"><%=label.get("taxNetTaxbleAmt")%></label></strong></td>
							<td width="10%"><s:textfield theme="simple" readonly="true"
								name="netTaxableIncome" id="netTaxableIncome" size="20"
								cssStyle="background-color: #F2F2F2" /></td>

						</tr>
					</table>
					</td>
				</tr>
				<!-- Ending tr for Net Taxable Income table.     -->

		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="7" /></td>
		</tr>

		<tr>
			<!-- Starting tr for TDS Slab Calculation table.     -->
				<td width="100%" class="formtext" valign="top" colspan="3">
							
									<table width="100%" border="0" align="center" class="formbg" cellpadding="0" cellspacing="2">
										<tr>
											<td colspan="5" class="formhead" width="100%"><strong
												class="forminnerhead"><label  class = "set"  id="taxTdsSlabCal" name="taxTdsSlabCal" ondblclick="callShowDiv(this);"><%=label.get("taxTdsSlabCal")%></label></strong></td>
										</tr>

										<tr>
											<td width="20%" class="formth"><label  class = "set"  id="taxFrmAmt" name="taxFrmAmt" ondblclick="callShowDiv(this);"><%=label.get("taxFrmAmt")%></label></td>
											<td width="20%" class="formth"><label  class = "set"  id="taxToAmt" name="taxToAmt" ondblclick="callShowDiv(this);"><%=label.get("taxToAmt")%></label></td>
											<td width="20%" class="formth"><label  class = "set"  id="taxTaxRate" name="taxTaxRate" ondblclick="callShowDiv(this);"><%=label.get("taxTaxRate")%></label></td>
											<td width="20%" class="formth"><label  class = "set"  id="taxAmt3" name="taxAmt" ondblclick="callShowDiv(this);"><%=label.get("taxAmt")%></label></td>
											<td width="20%" class="formth"><label  class = "set"  id="taxTax" name="taxTax" ondblclick="callShowDiv(this);"><%=label.get("taxTax")%></label></td>
										</tr>

										<s:if test="noSlabData">
											<tr>
												<td width="100%" colspan="7" align="center"><font
													color="red">No Data To Display</font></td>
											</tr>
										</s:if>
					<%
					int v = 0;
					%>
					<%!int slab = 0;%>
										<s:iterator value="tdslabList">

											<tr>
												<td width="20%" class="border2"><s:property  
													value="slabFrmAmt" /><input type="hidden" name="slabFrmAmt" value='<s:property  
													value="slabFrmAmt" />' id="slabFrmAmt<%=v %>"/></td>
												<td width="20%" class="border2"><s:property
													value="slabToAmt" /><input type="hidden" name="slabToAmt" value='<s:property  
													value="slabToAmt" />' id="slabToAmt<%=v %>"/></td>
												<td width="20%" class="border2"><s:property
													value="slabTaxRate" /><input type="hidden" name="slabTaxRate" value='<s:property  
													value="slabTaxRate" />' id="slabTaxRate<%=v %>"/></td>
												<td width="20%" class="border2">
												 <input type="text" style="background-color: #F2F2F2" readonly="true" id="<%="x"+v%>" name="salbAmt<%=v %>"  value="<s:property
													 value="slabAmt"  />"  /></td>
												<td width="20%" class="border2">
												 <input type="text" style="background-color: #F2F2F2" readonly="true"  id="<%="y"+(v+1)%>" name="slabTax<%=v %>" value="<s:property
													value="slabTax" />" /></td>
											</tr>
											
											<%
																						v++;
																						%>

										</s:iterator>
			<%
			slab = v;
			%>



									</table>
									</td>

		</tr>
		<!-- Ending tr for TDS Slab Calculation table.     -->
		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="3" /></td>
		</tr>

		<tr>
			<!-- Starting tr for Tax on Income table.     -->

					<td>
					<table width="100%" border="0" align="center" class="formbg" cellpadding="0"
						cellspacing="2">
						
						<tr>
							<td width="25%"><strong class="forminnerhead"><label  class = "set"  id="taxExemptSec87" name="taxExemptSec87" ondblclick="callShowDiv(this);"><%=label.get("taxExemptSec87")%></label></strong></td>
							<td width="25%" height="22">
							<s:hidden name="taxExemptionIncomeLimit" id="taxExemptionIncomeLimit"/>
							<s:hidden name="maxTaxExemption" id="maxTaxExemption" />
							<s:textfield theme="simple"
								readonly="true" name="taxExemptionSection87" id="taxExemptionSection87" size="20" cssStyle="background-color: #F2F2F2" />
																
							<td width="25%" height="22" class="formtext"></td>
							<td width="25%" height="22"></td>
							
						</tr>
						<tr>
							<td><strong class="formtext"><label  class = "set"  id="taxTaxOnIncm" name="taxTaxOnIncm" ondblclick="callShowDiv(this);"><%=label.get("taxTaxOnIncm")%></label></strong></td>
							<td  height="22"><s:textfield theme="simple"
								readonly="true" name="taxOnIncome" id="taxOnIncome" size="20" cssStyle="background-color: #F2F2F2" /></td>
							<td  height="22" class="formtext"><strong
								class="formtext"><label  class = "set"  id="taxSurChrg" name="taxSurChrg" ondblclick="callShowDiv(this);"><%=label.get("taxSurChrg")%></label></strong></td>
							<td  height="22"><s:textfield theme="simple"
								readonly="true" name="surCharge" id="surCharge" size="20" cssStyle="background-color: #F2F2F2" /></td>
							
						</tr>
						<tr>
							
							<td height="22" class="formtext"><strong
								class="forminnerhead"><label  class = "set"  id="taxEduCess" name="taxEduCess" ondblclick="callShowDiv(this);"><%=label.get("taxEduCess")%></label></strong></td>
							<td height="22"><s:textfield theme="simple"
								readonly="true" name="eduCess" id="eduCess" size="20" cssStyle="background-color: #F2F2F2" /></td>
							
							<td><strong class="forminnerhead"><label  class = "set"  id="taxNetTax" name="taxNetTax" ondblclick="callShowDiv(this);"><%=label.get("taxNetTax")%></label></strong></td>
							<td  height="22"><s:textfield theme="simple"
								readonly="true" name="netTax" id="netTax" size="20" cssStyle="background-color: #F2F2F2" /></td>
							
						</tr>
						<tr>
							<td  height="22" class="formtext"><strong
								class="forminnerhead"><label  class = "set"  id="taxTaxPaid" name="taxTaxPaid" ondblclick="callShowDiv(this);"><%=label.get("taxTaxPaid")%></label></strong></td>
							<td height="22"><s:textfield theme="simple"
								readonly="true" name="taxPaid" id="taxPaid" size="20" cssStyle="background-color: #F2F2F2"  /></td>
							
							<td><strong class="forminnerhead"><label  class = "set"  id="taxRemMonts" name="taxRemMonts" ondblclick="callShowDiv(this);"><%=label.get("taxRemMonts")%></label></strong></td>
							<td height="22"><s:textfield theme="simple"
								 name="remainMonths" id="remainMonths" size="20" onkeyup="calRemainMonth()" /></td>	
						</tr>
						<tr>
							<td><strong class="forminnerhead"><label  class = "set"  id="taxTaxPerMon" name="taxTaxPerMon" ondblclick="callShowDiv(this);"><%=label.get("taxTaxPerMon")%></label></strong></td>
							<td  height="22"><s:textfield theme="simple"
								readonly="true" name="taxPerMonth" id="taxPerMonth" size="20" cssStyle="background-color: #F2F2F2" /></td>
						</tr>
						



					</table>
					</td>
		</tr>
		<!-- Ending tr for Tax on Income  table.     -->

		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="3" /></td>
		</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%"><s:if test="%{isShowButton}"></s:if><s:else>

								<s:submit cssClass="reset" action="TdsCalculator_reset"
									value="   Reset" />
							</s:else></td>
						</tr>

						<s:hidden name="taxParaSur" id="taxParaSur"></s:hidden>
						<s:hidden name="taxParaEdu" id="taxParaEdu"></s:hidden>
						<s:hidden name="taxParaSurLimit" id="taxParaSurLimit"></s:hidden>
						<label></label>
						</td>

						</tr>


					</table>

					</td>
				</tr>
			</table><!-- employee taxation details outermost table ends-->
</td>
</tr>
</table>
</td>
</tr>
</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>

function callView(){
 	var empName = document.getElementById('paraFrm_empName').value;
 	var year = document.getElementById('paraFrm_fromYear').value;
 	 var employee=document.getElementById('employee').innerHTML.toLowerCase();
	 var finYrFrm=document.getElementById('taxFinYrFrm').innerHTML.toLowerCase(); 
 
 	if(empName==""){
 		alert("Please Select "+employee+"." );
 		return false;
 	}
 	
 	if(year==""){
 		alert("Please Enter "+finYrFrm+".");
 		return false;
 	}
}

function calRemainMonth(){
	var remianMon = document.getElementById('remainMonths').value;
	var nettax = document.getElementById('netTax').value;
    var taxpaid = document.getElementById('taxPaid').value;
	if(remianMon==""){
		document.getElementById('taxPerMonth').value = 0;
	}
	if(eval(remianMon)>12 || eval(remianMon)<=0){
		alert('Invalid month entered');	
		document.getElementById('remainMonths').value=0;
		document.getElementById('taxPerMonth').value=0;
	}
	else{
		document.getElementById('taxPerMonth').value =  Math.round((eval(nettax) - eval(taxpaid))/eval(remianMon));	
	}
}


function add()
   {
    var from = document.getElementById('paraFrm_fromYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_toYear').value=x;
	  }
   }
   
   
   function addInv(name){
   		var value;
   		
   		if(name=='Exempt'){
   			 value='<%=k1%>';
   		}
   		else if(name=='Other'){
   			value='<%=b%>';
   		}
   		else if(name=='Para'){
   			value='<%=b%>';
   		}
   		else if(name=='VIA'){
   			value='<%=f%>';
   		}
   		else if(name=='Rebate'){
   			value='<%=h1%>';
   		}
 
   		var totalAmt = 0;
   		//alert(value);
   		
   		for(var i=1;i<value;i++){
   			var invAmt =  document.getElementById('inv'+name+'Amt'+i).value;
   			if(invAmt==""){
   				invAmt=0;
   			}
   		}
   	
   		for(var i=1;i<value;i++){
      	var limitAmt =  document.getElementById('inv'+name+'Limit'+i).value;
      	//alert(limitAmt);
      	
      	var invAmt =  document.getElementById('inv'+name+'Amt'+i).value;
      	//alert(invAmt);
      	
      	if(invAmt==""){
   				//alert('amt null');
   				invAmt=0;
   			}
      	
      		if(eval(invAmt)>eval(limitAmt)){
      		if(limitAmt!=0){
      		
      	
      			document.getElementById('inv'+name+'Amt'+i).value = limitAmt;
      			invAmt = limitAmt;
      			}
      		}
      		totalAmt = eval(totalAmt)+eval(invAmt)*100/100;
   }
   		
   		//alert(totalAmt);
   		if(name=='Rebate'){
   			var rebateInvLimit = document.getElementById('rebateLimit').value;
   			//alert('rebateInvLimit'+rebateInvLimit);
   			if(eval(totalAmt) >= eval(rebateInvLimit)){
   				 document.getElementById('total'+name+'InvAmt').value=rebateInvLimit;
   			}
   			else{
   				document.getElementById('total'+name+'InvAmt').value=totalAmt;
   			}
   		}
   		else{
   		 	document.getElementById('total'+name+'InvAmt').value=totalAmt;
   		 }
   			addNetInv(); //this method is to calculate Net Total Investment Amount...
   			netTaxableIncome(); // this method is to calculate Net Taxable Income..
   }
   
   function addGross(id){
   		 var value='<%=z%>';
   		// alert(value);
   		 var creditAmt1=0;
   		 var amt=0;
   		 var totalPerqAmt =  document.getElementById('totalPerqAmt').value;
   		 var totalOtherAmt = document.getElementById('totalOtherInvAmt').value;
   		 
   		 for(var q=1;q<value;q++){
   		 	amt = document.getElementById('creditAmt'+q).value;
   		 //	alert(amt);
   		 		if(amt==""){
   		 			//alert('in amt blank');
   		 			amt=0;
   		 			// document.getElementById('creditAmt'+q).value= '0';
   		 		}
   		 }
   		 
   		 	for(var q=1;q<value;q++){
   		 		amt = document.getElementById('creditAmt'+q).value;
   		 		
   		 		creditAmt1 = eval(creditAmt1*100/100)+ eval(amt*100/100);
   		 	//	alert('creditAmt1'+creditAmt1);
   		 	}
   		// var processAmt = document.getElementById('processedAmt').value;
   		// alert('-tttttttttttttttt----'+processAmt);
   		 var total = eval(creditAmt1);
   		 document.getElementById('totalCreditAmt').value = total;
   		 document.getElementById('totalGrossAmt').value = eval(totalPerqAmt) + eval(total) + eval(totalOtherAmt);	
   		 netTaxableIncome();// this method is to calculate Net Taxable Income..
   
   }
   
   function addPerq(id){
   		 var value='<%=n%>';
   		// alert(value);
   		 var perqAmt1=0;
   		 var amt=0;
   		 var totalCreditAmt = document.getElementById('totalCreditAmt').value;
   		  var totalOtherAmt = document.getElementById('totalOtherInvAmt').value;
   		 for(var q=1;q<value;q++){
   		 	amt = document.getElementById('perqAmt'+q).value;
   		 //	alert(amt);
   		 		if(amt==""){
   		 			//alert('in amt blank');
   		 			amt=0;
   		 			// document.getElementById('creditAmt'+q).value= '0';
   		 		}
   		 }
   		 
   		 	for(var q=1;q<value;q++){
   		 		amt = document.getElementById('perqAmt'+q).value;
   		 		
   		 		perqAmt1 = eval(perqAmt1*100/100)+ eval(amt*100/100);
   		 	//	alert('creditAmt1'+creditAmt1);
   		 	}
   		// var processAmt = document.getElementById('processedAmt').value;
   		// alert('-tttttttttttttttt----'+processAmt);
   		 var total = eval(perqAmt1);
   		 document.getElementById('totalPerqAmt').value = total;
   		document.getElementById('totalGrossAmt').value = eval(totalCreditAmt) + eval(total) + eval(totalOtherAmt);	
   		 netTaxableIncome();// this method is to calculate Net Taxable Income..
   }
   
     function addOtherInv(id){
   		var value='<%=b%>';
   		var othFlag=0;
   		var totalAmt=0;
   		var totOtherAmt=0;
   		var totalCreditAmt = document.getElementById('totalCreditAmt').value;
   		var perquisiteAmt = document.getElementById('totalPerqAmt').value;
   		//alert(value);
   		
   		for(var i=1;i<value;i++){
   			var invAmt =  document.getElementById('invOtherAmt'+i).value;
   			if(invAmt==""){
   				invAmt=0;
   			}
   		}
   	
   		for(var i=1;i<value;i++){
   		 othFlag = document.getElementById('otherFlag'+i).value;
      	var limitAmt =  document.getElementById('invOtherLimit'+i).value;
      	//alert(limitAmt);
      	
      	var invAmt =  document.getElementById('invOtherAmt'+i).value;
      	//alert(invAmt);
      	
      	if(invAmt==""){
   				//alert('amt null');
   				invAmt=0;
   			}
      	
      		if(eval(invAmt)>eval(limitAmt)){
      		if(limitAmt!=0){
      		
      	
      			document.getElementById('invOtherAmt'+i).value = limitAmt;
      			invAmt = limitAmt;
      			}
      		}
      			if(othFlag=="D"){
      				totalAmt = eval(totalAmt) - (invAmt*100/100);
      			}
      			else{
      				totalAmt = eval(totalAmt)+(eval(invAmt)*100/100);
      			}
      	
   }
   		 	 document.getElementById('totalOtherInvAmt').value=totalAmt;
   			 document.getElementById('totalGrossAmt').value = eval(totalCreditAmt) + eval(totalAmt) + eval(perquisiteAmt);
   			 
   		  	 netTaxableIncome();// this method is to calculate Net Taxable Income..
   }
   
   
   function addNetInv(){
   		var amt = document.getElementById('totalExemptInvAmt').value;
   		var amt3 = document.getElementById('totalVIAInvAmt').value;
   		//alert('totalVIAInvAmt'+amt3);
   		var amt4 = document.getElementById('totalRebateInvAmt').value;
   	//	alert('totalRebateInvAmt'+amt4);
   		var amt5 = document.getElementById('pTaxAmt').value;
   			//alert('pTaxAmt'+amt5);
   			if(amt5==''){
   				amt5=0;
   			}
   			document.getElementById('totalNetInvAmt').value = eval(amt)+eval(amt3)+eval(amt4)+eval(amt5); //+eval(amt2)
  		netTaxableIncome();
   }
   
   function netTaxableIncome(){
   //alert('netTaxableIncome');
   		var grossAmt = document.getElementById('totalGrossAmt').value;
   		//alert('totalGrossAmt'+grossAmt);
   		var invAmt = document.getElementById('totalNetInvAmt').value;
   		var prevNetTaxableAmt = document.getElementById('preNetTaxableIncomeAmt').value;
   		var prevPfAmt = document.getElementById('prePFAmt').value;
   		var prevPTAXAmt = document.getElementById('prePTaxAmt').value;
   		var prevAmt = eval(prevNetTaxableAmt)- eval(prevPfAmt) - eval(prevPTAXAmt);
   		var check = eval(grossAmt) - eval(invAmt)+ eval(prevAmt);
   		//alert(check);
   		var z = check;
   		
   		var mod= eval(check)%10;					
				
					if(mod >0)
					{
						z=(eval(z)-eval(mod))+10;
					}
					
					if(z<0)
					{
						document.getElementById('netTaxableIncome').value = 0;
					}
					else
						document.getElementById('netTaxableIncome').value = z;
   		
   			   			
   		var slabList = '<%=slab%>';
   		var diff = 0;
   		var total = document.getElementById('netTaxableIncome').value;
   		var remain = total;
   		var taxAmt=0;
   		var totalTaxOnIncome=0;
   		var totalTaxOnIncomeElse=0;
   		for(var k =0;k<slabList;k++){
   		
	   		var frmAmt = document.getElementById('slabFrmAmt'+k).value;
	   		var toAmt = document.getElementById('slabToAmt'+k).value;
	   		var taxRate = document.getElementById('slabTaxRate'+k).value;   	
   			
   			if(eval(total) >= eval(toAmt)){
   				if(k==0){
   					diff = eval(toAmt) - eval(frmAmt);
   				} else {
   					diff = eval(toAmt) - eval(eval(frmAmt)-1);
   				}
   				document.getElementById('x'+k).value = Math.round(diff);
   				remain = eval(remain)-eval(diff);
   				taxAmt = eval(diff) * eval(eval(taxRate)/100);
   				document.getElementById('y'+(eval(k)+1)).value = Math.round(taxAmt);
   				diff=0;
   				totalTaxOnIncome = eval(totalTaxOnIncome) + Math.round(taxAmt); 
   			}
   			else
			{
			document.getElementById('x'+k).value = Math.round(remain);
				taxAmt= eval(eval(remain) * eval(eval(taxRate)/100));
				document.getElementById('y'+(eval(k)+1)).value = Math.round(taxAmt);
				totalTaxOnIncomeElse = eval(totalTaxOnIncomeElse) + Math.round(taxAmt);
				remain = 0;
			}
   		}
   		
   		totalTaxOnIncomeElse = Math.round(eval(totalTaxOnIncomeElse) + eval(totalTaxOnIncome));    

		var taxExemptionIncomeLimit = document.getElementById('taxExemptionIncomeLimit').value;
   		var	maxTaxExemption = document.getElementById('maxTaxExemption').value;
   		var taxExemptionSection87=0;
   		
   		var totalTax = totalTaxOnIncomeElse; //document.getElementById('taxOnIncome').value;
   		
		if(eval(total) <= eval(taxExemptionIncomeLimit)) {
			if(eval(totalTax) <= eval(maxTaxExemption)) {
				taxExemptionSection87=totalTax;
				totalTax=0;				
			}else {
				totalTax = totalTax-maxTaxExemption;
				taxExemptionSection87=maxTaxExemption;
			}
		}
		
		document.getElementById('taxExemptionSection87').value=taxExemptionSection87;   		
   		document.getElementById('taxOnIncome').value = totalTax; //Math.round(totalTaxOnIncomeElse)-eval(taxExemptionSection87);
 		
 			
   		var paraSur = document.getElementById('taxParaSur').value;
   		var paraEdu = document.getElementById('taxParaEdu').value;
   		var surLimit = document.getElementById('taxParaSurLimit').value; 
   		var surcharge=0;
   		var eduCationCess=0;
   		//alert('surLimit'+surLimit);
   		if(!(surLimit=="")){
   			if(eval(total) > eval(surLimit)){
   				//alert('total'+total);
   				//alert('paraSur'+paraSur);
   				//alert('totalTax'+totalTax);
   				surcharge = eval(totalTax) * eval(eval(paraSur)/100);
   				document.getElementById('surCharge').value = Math.round(surcharge);
   				//alert('surcharge'+surcharge);
   			}
   			else{
   				document.getElementById('surCharge').value ='0';
   				//alert('in else surcharge'+surcharge);
   			}
   		
   		}
   		else{
   			document.getElementById('surCharge').value ='0';
   			//alert('in else surcharge'+surcharge);
   		}
   		
   		if(totalTax>0){
   			eduCationCess = (eval(totalTax)+eval(surcharge)) * eval(eval(paraEdu)/100);
   			//alert('in  eduCationCess'+eduCationCess);
   			document.getElementById('eduCess').value =  Math.round(eduCationCess);
   		}
   		else{
   			document.getElementById('eduCess').value ='0';
   		}
   		
   		document.getElementById('netTax').value = Math.round(eval(totalTax)) + Math.round(eduCationCess) + Math.round(surcharge); 
   		var nettax = document.getElementById('netTax').value;
   		var taxpaid = document.getElementById('taxPaid').value;
   		var remianMon = document.getElementById('remainMonths').value;
   		document.getElementById('taxPerMonth').value =  Math.round((eval(nettax) - eval(taxpaid))/eval(remianMon));
   		//if(eval(document.getElementById('taxPerMonth').value ) <0){
   			//document.getElementById('taxPerMonth').value =0;
   		//}	
   }
   

</script>


