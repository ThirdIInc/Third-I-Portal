<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="PensionConfiguration" validate="true" id="paraFrm" theme="simple">
	
	<div align="center" id="overlay"
		style="z-index: 3; position: absolute; width: 700px; margin: 0px; left: 0; top: 100; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid :   DXImageTransform .   Microsoft .   alpha(opacity =   15); -moz-opacity: .1; opacity: .1; display: none;">
	</div>
	<div id="confirmationDiv"
	style='position: absolute; z-index: 3; 100 px; height: 150px; visibility: hidden; top: 200px; left: 100px;'></div>
	<s:hidden name="sMode"></s:hidden>
	<s:hidden name="sPensionCode"></s:hidden>	
   	<s:hidden name="sPensionTypeCode"></s:hidden>
   	<s:hidden name="prePensionTypeCode"></s:hidden>
   	<s:hidden name="sPensionEffFrm"></s:hidden>	
  	<s:hidden name="sPensionMinService"></s:hidden>	
  	<s:hidden name="sPensionMaxService"></s:hidden>	
  	<s:hidden name="sPensionMinAmt"></s:hidden>	
  	<s:hidden name="sPensionEmpStatus"></s:hidden>	
  	<s:hidden name="sPensionEmolFormula"></s:hidden>	
  	<s:hidden name="sPensionEmolMonths"></s:hidden>	
  	<s:hidden name="sPensionAvgEmol"></s:hidden>	
  	<s:hidden name="sPensionFormula"></s:hidden>	
  	<s:hidden name="sPensionVolWeiyears"></s:hidden>	
  	<s:hidden name="sPensionCompFormula"></s:hidden>
  	
  	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg" >
    	<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2" cellspacing="1">

						<tr>
							<td valign="bottom" class="txt"><strong class="formhead"><img
								src="../pages/images/review_shared.gif" width="25" height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">Pension Configuration </strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/help.gif"
								width="16" height="16" /></div>
							</td>
						</tr>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
		  <td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="78%">
		            	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td align="right"> 
						<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
		  </td>
		</tr>
		
		<tr>
			<td colspan="3"><s:hidden name='aId' /> <s:hidden name='divId' />
			<s:hidden name='backFlag' /> <s:hidden name='settingNameDup' />

			<table border="0" cellpadding="0" cellspacing="0" class="formbg"
				width="100%">
				<tr>
					<td class="btn-middlebg">
					<div id="tabnav" style="vertical-align: bottom">
					<ul>
						<li><a id="superopt"
							href="javascript:callTab('superopt','Superannuation','T');">
						<div align="center"><span>Superannuation</span></div>
						</a></li>
						<li><a id="voluntaryopt"
							href="javascript:callTab('voluntaryopt','Voluntary','T');">
						<div align="center"><span>Voluntary</span></div>
						</a></li>

						<li><a id="compulsoryopt"
							href="javascript:callTab('compulsoryopt','Compulsory','T');">
						<div align="center"><span>Compulsory Retirement</span></div>
						</a></li>

						<li><a id="deathopt"
							href="javascript:callTab('deathopt','Death','T');">
						<div align="center"><span>Death</span></div>
						</a></li>
						
						<li><a id="pensionamtopt"
							href="javascript:callTab('pensionamtopt','PensionAmt','T');">
						<div align="center"><span>Pension Amount Split</span></div>
						</a></li>
					</ul>
					</div>
					</td>
				</tr>
			</table>

			<table id="SuperannuationDisp" width="100%" border="0"
				cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="100%" colspan="4" class="formtext"><b><label
								class="set" id="heading1" name="heading1"
								ondblclick="callShowDiv(this);"><%=label.get("heading1")%></label></b>
							</td>
						</tr>
						<tr>
							<td width="30%"><label class="set" id="pens.effective.from1"
								name="pens.effective.from" ondblclick="callShowDiv(this);"><%=label.get("pens.effective.from")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEffFrm1" size="20"
								onkeypress="return numbersWithHiphen();" maxlength="10"
								></s:textfield> <s:a
								href="javascript:NewCal('paraFrm_sPensionEffFrm1','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18">
							</s:a></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.min.service1"
								name="pens.min.service" ondblclick="callShowDiv(this);"><%=label.get("pens.min.service")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMinService1"
								size="20" onkeypress="return numbersOnly()" maxlength="2"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.max.service1"
								name="pens.max.service" ondblclick="callShowDiv(this);"><%=label.get("pens.max.service")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMaxService1"
								size="20" onkeypress="return numbersOnly()" maxlength="2"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.min.amount1"
								name="pens.min.amount" ondblclick="callShowDiv(this);"><%=label.get("pens.min.amount")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMinAmt1" size="20"
								onkeypress="return numbersWithDot()" maxlength="15"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.emp.status1"
								name="pens.emp.status" ondblclick="callShowDiv(this);"><%=label.get("pens.emp.status")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:select name="sPensionEmpStatus1"
								list="#{'1':'Superannuation','2':'Voluntary','3':'Compulsory Retirement','4':'Death'}"
								headerKey="" headerValue="--Select--" /></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="100%" colspan="4" class="formtext"><b><label
								class="set" id="sub.heading" name="sub.heading"
								ondblclick="callShowDiv(this);"><%=label.get("sub.heading")%></label> :</b>
							</td>
						</tr>

						<tr>
							<td width="30%"><label
								class="set" id="pens.emol.formula1" name="pens.emol.formula"
								ondblclick="callShowDiv(this);"><%=label.get("pens.emol.formula")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEmolFormula1"
								size="20" readonly="true" maxlength="50" />
								<input type="button" class="token" name="formCalc1" value=Calculator id='paraFrm_formCalc1'
								onclick="callFormulaBuilder('paraFrm_sPensionEmolFormula1');"/> </td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label
								class="set" id="pens.emol.months1" name="pens.emol.months"
								ondblclick="callShowDiv(this);"><%=label.get("pens.emol.months")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEmolMonths1" size="20"
								onkeypress="return numbersOnly()" maxlength="2"></s:textfield></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
						<tr>
							<td width="30%"><label
								class="set" id="pens.avg.emol1" name="pens.avg.emol"
								ondblclick="callShowDiv(this);"><%=label.get("pens.avg.emol")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionAvgEmol1" size="20" value="EF / MAE"
								maxlength="50" readonly="true" ></s:textfield></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
						<tr>
							<td width="30%"><label
								class="set" id="pens.comp.formula1" name="pens.comp.formula"
								ondblclick="callShowDiv(this);"><%=label.get("pens.comp.formula")%></label> :
								<font color="#FF0000">*</font>
							</td>
							<td width="30%"><s:hidden name="sPensionFormula1" value="Avg Emu*0.5*Qul Ser/Max Qul Ser"/>
								(Avg Emu * 0.5) * Qul Ser / Max Qul Ser
								<!-- <input type="button" class="token" name="formCalc2" value=Calculator id='paraFrm_formCalc2'
								onclick="callFormulaBuilderPension('paraFrm_sPensionFormula1');"/> -->
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
					</table>
					</td>
				</tr>
			</table>

			<table id="VoluntaryDisp" width="100%" border="0" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="100%" colspan="4" class="formtext"><b><label
								class="set" id="heading2" name="heading2"
								ondblclick="callShowDiv(this);"><%=label.get("heading2")%></label> :</b>
							</td>
						</tr>
						<tr>
							<td width="30%"><label class="set" id="pens.effective.from2"
								name="pens.effective.from" ondblclick="callShowDiv(this);"><%=label.get("pens.effective.from")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEffFrm2" size="20"
								onkeypress="return numbersWithHiphen();" maxlength="10"
								></s:textfield> <s:a
								href="javascript:NewCal('paraFrm_sPensionEffFrm2','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18">
							</s:a></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.min.service2"
								name="pens.min.service" ondblclick="callShowDiv(this);"><%=label.get("pens.min.service")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMinService2"
								size="20" onkeypress="return numbersOnly()" maxlength="2"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.max.service2"
								name="pens.max.service" ondblclick="callShowDiv(this);"><%=label.get("pens.max.service")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMaxService2"
								size="20" onkeypress="return numbersOnly()" maxlength="2"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.min.amount2"
								name="pens.min.amount" ondblclick="callShowDiv(this);"><%=label.get("pens.min.amount")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMinAmt2" size="20"
								onkeypress="return numbersWithDot()" maxlength="15"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.emp.status2"
								name="pens.emp.status" ondblclick="callShowDiv(this);"><%=label.get("pens.emp.status")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:select name="sPensionEmpStatus2"
								list="#{'1':'Superannuation','2':'Voluntary','3':'Compulsory Retirement','4':'Death'}"
								headerKey="" headerValue="--Select--" /></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
						<tr>
							<td width="30%"><label class="set" id="pens.vol.weiyears2"
								name="pens.vol.weiyears" ondblclick="callShowDiv(this);"><%=label.get("pens.vol.weiyears")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionVolWeiyears2" size="20"
								onkeypress="return numbersOnly()" maxlength="2"></s:textfield></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="100%" colspan="4" class="formtext"><b><label
								class="set" id="sub.heading" name="sub.heading"
								ondblclick="callShowDiv(this);"><%=label.get("sub.heading")%></label> :</b>
							</td>
						</tr>

						<tr>
							<td width="30%"><label
								class="set" id="pens.emol.formula2" name="pens.emol.formula"
								ondblclick="callShowDiv(this);"><%=label.get("pens.emol.formula")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEmolFormula2"
								size="20" readonly="true" maxlength="50" />
								<input type="button" class="token" name="formCalc1" value=Calculator id='paraFrm_formCalc1'
								onclick="callFormulaBuilder('paraFrm_sPensionEmolFormula2');"/> </td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label
								class="set" id="pens.emol.months2" name="pens.emol.months"
								ondblclick="callShowDiv(this);"><%=label.get("pens.emol.months")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEmolMonths2" size="20"
								onkeypress="return numbersOnly()" maxlength="2"></s:textfield></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
						<tr>
							<td width="30%"><label
								class="set" id="pens.avg.emol2" name="pens.avg.emol"
								ondblclick="callShowDiv(this);"><%=label.get("pens.avg.emol")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionAvgEmol2" size="20" value="EF / MAE"
								maxlength="50" readonly="true"></s:textfield></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
						<tr>
							<td width="30%"><label
								class="set" id="pens.comp.formula2" name="pens.comp.formula"
								ondblclick="callShowDiv(this);"><%=label.get("pens.comp.formula")%></label> :
								<font color="#FF0000">*</font>
							</td>
							<td width="30%"><s:hidden name="sPensionFormula2" value="Avg Emu*0.5*Qul Ser/Max Qul Ser"/>
								(Avg Emu * 0.5) * Qul Ser / Max Qul Ser
								<!-- <input type="button" class="token" name="formCalc2" value=Calculator id='paraFrm_formCalc2'
								onclick="callFormulaBuilderPension('paraFrm_sPensionFormula1');"/> -->
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
					</table>
					</td>
				</tr>
			</table>

			<table id="CompulsoryDisp" width="100%" border="0" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="100%" colspan="4" class="formtext"><b><label
								class="set" id="heading3" name="heading3"
								ondblclick="callShowDiv(this);"><%=label.get("heading3")%></label> :</b>
							</td>
						</tr>
						<tr>
							<td width="30%"><label class="set" id="pens.effective.from3"
								name="pens.effective.from" ondblclick="callShowDiv(this);"><%=label.get("pens.effective.from")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEffFrm3" size="20"
								onkeypress="return numbersWithHiphen();" maxlength="10"
								></s:textfield> <s:a
								href="javascript:NewCal('paraFrm_sPensionEffFrm3','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18">
							</s:a></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.min.service3"
								name="pens.min.service" ondblclick="callShowDiv(this);"><%=label.get("pens.min.service")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMinService3"
								size="20" onkeypress="return numbersOnly()" maxlength="2"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.max.service3"
								name="pens.max.service" ondblclick="callShowDiv(this);"><%=label.get("pens.max.service")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMaxService3"
								size="20" onkeypress="return numbersOnly()" maxlength="2"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.min.amount3"
								name="pens.min.amount" ondblclick="callShowDiv(this);"><%=label.get("pens.min.amount")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMinAmt3" size="20"
								onkeypress="return numbersWithDot()" maxlength="15"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.emp.status3"
								name="pens.emp.status" ondblclick="callShowDiv(this);"><%=label.get("pens.emp.status")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:select name="sPensionEmpStatus3"
								list="#{'1':'Superannuation','2':'Voluntary','3':'Compulsory Retirement','4':'Death'}"
								headerKey="" headerValue="--Select--" /></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="100%" colspan="4" class="formtext"><b><label
								class="set" id="sub.heading" name="sub.heading"
								ondblclick="callShowDiv(this);"><%=label.get("sub.heading")%></label> :</b>
							</td>
						</tr>

						<tr>
							<td width="30%"><label
								class="set" id="pens.emol.formula3" name="pens.emol.formula"
								ondblclick="callShowDiv(this);"><%=label.get("pens.emol.formula")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEmolFormula3"
								size="20" readonly="true" maxlength="50" />
								<input type="button" class="token" name="formCalc1" value=Calculator id='paraFrm_formCalc1'
								onclick="callFormulaBuilder('paraFrm_sPensionEmolFormula3');"/> </td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label
								class="set" id="pens.emol.months3" name="pens.emol.months"
								ondblclick="callShowDiv(this);"><%=label.get("pens.emol.months")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEmolMonths3" size="20"
								onkeypress="return numbersOnly()" maxlength="2"></s:textfield></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
						<tr>
							<td width="30%"><label
								class="set" id="pens.avg.emol3" name="pens.avg.emol"
								ondblclick="callShowDiv(this);"><%=label.get("pens.avg.emol")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionAvgEmol3" size="20" value="EF / MAE"
								maxlength="50" readonly="true" ></s:textfield></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
						<tr>
							<td width="30%"><label
								class="set" id="pens.comp.formula3" name="pens.comp.formula"
								ondblclick="callShowDiv(this);"><%=label.get("pens.comp.formula")%></label> :
								<font color="#FF0000">*</font>
							</td>
							<td width="30%"><s:hidden name="sPensionFormula3" value="Avg Emu*0.5*Qul Ser/Max Qul Ser"/>
								(Avg Emu * 0.5) * Qul Ser / Max Qul Ser
								<!-- <input type="button" class="token" name="formCalc2" value=Calculator id='paraFrm_formCalc2'
								onclick="callFormulaBuilderPension('paraFrm_sPensionFormula1');"/> -->
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
					</table>
					</td>
				</tr>
			</table>

			<table id="DeathDisp" width="100%" border="0" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="100%" colspan="4" class="formtext"><b><label
								class="set" id="heading4" name="heading4"
								ondblclick="callShowDiv(this);"><%=label.get("heading4")%></label> :</b>
							</td>
						</tr>
						<tr>
							<td width="30%"><label class="set" id="pens.effective.from4"
								name="pens.effective.from" ondblclick="callShowDiv(this);"><%=label.get("pens.effective.from")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEffFrm4" size="20"
								onkeypress="return numbersWithHiphen();" maxlength="10"
								></s:textfield> <s:a
								href="javascript:NewCal('paraFrm_sPensionEffFrm4','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="18" align="absmiddle" width="18">
							</s:a></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.min.service4"
								name="pens.min.service" ondblclick="callShowDiv(this);"><%=label.get("pens.min.service")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMinService4"
								size="20" onkeypress="return numbersOnly()" maxlength="2"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.max.service4"
								name="pens.max.service" ondblclick="callShowDiv(this);"><%=label.get("pens.max.service")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMaxService4"
								size="20" onkeypress="return numbersOnly()" maxlength="2"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.min.amount4"
								name="pens.min.amount" ondblclick="callShowDiv(this);"><%=label.get("pens.min.amount")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionMinAmt4" size="20"
								onkeypress="return numbersWithDot()" maxlength="10"></s:textfield>
							</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label class="set" id="pens.emp.status4"
								name="pens.emp.status" ondblclick="callShowDiv(this);"><%=label.get("pens.emp.status")%></label> :
							<font color="#FF0000">*</font></td>
							<td width="30%"><s:select name="sPensionEmpStatus4"
								list="#{'1':'Superannuation','2':'Voluntary','3':'Compulsory Retirement','4':'Death'}"
								headerKey="" headerValue="--Select--" /></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td width="100%" colspan="4" class="formtext"><b><label
								class="set" id="sub.heading" name="sub.heading"
								ondblclick="callShowDiv(this);"><%=label.get("sub.heading")%></label> :</b>
							</td>
						</tr>

						<tr>
							<td width="30%"><label
								class="set" id="pens.emol.formula4" name="pens.emol.formula"
								ondblclick="callShowDiv(this);"><%=label.get("pens.emol.formula")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEmolFormula4"
								size="20" readonly="true" maxlength="50" />
								<input type="button" class="token" name="formCalc1" value=Calculator id='paraFrm_formCalc1'
								onclick="callFormulaBuilder('paraFrm_sPensionEmolFormula4');"/> </td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>

						<tr>
							<td width="30%"><label
								class="set" id="pens.emol.months4" name="pens.emol.months"
								ondblclick="callShowDiv(this);"><%=label.get("pens.emol.months")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionEmolMonths4" size="20"
								onkeypress="return numbersOnly()" maxlength="2"></s:textfield></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
						<tr>
							<td width="30%"><label
								class="set" id="pens.avg.emol4" name="pens.avg.emol"
								ondblclick="callShowDiv(this);"><%=label.get("pens.avg.emol")%></label> :
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionAvgEmol4" size="20" value="EF / MAE" 
								maxlength="50" readonly="true"></s:textfield></td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
						
						
						<tr>
							<td width="30%"><label
								class="set" id="pens.comp.is.paid" name="pens.comp.is.paid"
								ondblclick="callShowDiv(this);"><%=label.get("pens.comp.is.paid")%></label> : 
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionFormula4" size="20" readonly="true"
								maxlength="50"></s:textfield>
								<input type="button" class="token" name="formCalc2" value=Calculator id='paraFrm_formCalc2'
								onclick="callFormulaBuilderPension('paraFrm_sPensionFormula4');"/>
								</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
						
						<tr>
							<td width="30%"><label
								class="set" id="pens.comp.is.notpaid" name="pens.comp.is.notpaid"
								ondblclick="callShowDiv(this);"><%=label.get("pens.comp.is.notpaid")%></label> : 
								<font color="#FF0000">*</font></td>
							<td width="30%"><s:textfield name="sPensionCompFormula4" size="20" readonly="true"
								maxlength="50"></s:textfield>
								<input type="button" class="token" name="formCalc2" value=Calculator id='paraFrm_formCalc2'
								onclick="callFormulaBuilderPension('paraFrm_sPensionCompFormula4');"/>
								</td>
							<td width="10%">&nbsp;</td>
							<td width="30%">&nbsp;</td>
						</tr>
						
					</table>
					</td>
				</tr>
			</table>
			
			<table id="PensionAmtDisp" width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
						<s:hidden name ="paraId"/>
						<!-- heading -->
						<tr>
							<td width="100%" colspan="2" class="formtext"><b><label
								class="set" id="pens.amt.header" name="pens.amt.header"
								ondblclick="callShowDiv(this);"><%=label.get("pens.amt.header")%></label> :</b>
							</td>
						</tr>
						<!-- Credit head -->
						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" id="credit.head" name="credit.head"
								ondblclick="callShowDiv(this);"><%=label.get("credit.head")%></label> <font color="#FF0000">*</font> : 
							</td>
							<td width="80%" colspan="1"><s:hidden name="creditHeadCode"/>
								<s:textfield name="creditHead" readonly="true" size="30"></s:textfield>
								<img 	src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18" onclick="javascript:callsF9(500,325,'PensionConfiguration_f9creditName.action');" id="ctrlHide">
							</td>
						</tr>
						
						
						<!-- Credit Formula -->
						<tr>
							<td width="20%" colspan="1" class="formtext"><label
								class="set" id="credit.formula" name="credit.formula"
								ondblclick="callShowDiv(this);"><%=label.get("credit.formula")%></label> <font color="#FF0000">*</font> :
							</td>
							<td width="80%" colspan="1">
								<s:select name="creditFormula" value="%{creditFormula}"  list="#{'-1':'---Select----','F':'Fixed','P':'Pension'}" />
							</td>
						</tr>
						
						<!--value-->
						<tr>
							<td width="20%" colspan="1" class="formtext"><label class="set" id="value" name="value" ondblclick="callShowDiv(this);"><%=label.get("value")%></label> <font color="#FF0000">*</font> : </td>
							<td width="80%" colspan="1"><s:textfield name="pensionValue" maxlength="10" size="15" onkeypress ="return isNumWithCharKey(event)"></s:textfield>
							</td>
						</tr>

						<tr align="center">
							<td colspan="4" align="center"><s:submit cssClass="add" action="PensionConfiguration_addData" theme="simple"
								value="   Add   " onclick="return callAdd();" /></td>
						</tr>

<s:if test="creditDetailsList">
<tr>
						<td>&nbsp;</td>
					</tr>
					<tr align="center">

						<td colspan="6">
						<table width="100%" cellspacing="2" class="formbg" border="0">
							<tr>
								<td width="10%" class="formth"><label class="set" id="sr.no" name="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
								<td width="35%" class="formth"><label class="set" id="credit.head" name="credit.head" ondblclick="callShowDiv(this);"><%=label.get("credit.head")%></label></td>
								<td width="20%" class="formth"><label class="set" id="credit.formula" name="credit.formula" ondblclick="callShowDiv(this);"><%=label.get("credit.formula")%></label></td>
								<td width="20%" class="formth"><label class="set" id="value" name="value" ondblclick="callShowDiv(this);"><%=label.get("value")%></label></td>
								<td width="15%" class="formth">Edit/Delete</td>
                                 
							</tr>
							<%
							int n =1;
							%>
			<s:iterator value="creditDetailsList" status="stat">
					<tr>
						<td width="10%" class="sortableTD" align="center"><%=n%></td>
						
						<td  width="35%" align="center" class="sortableTD">&nbsp;
							<s:property value="creditHead"/>

						</td>
						<td  width="20%"  align="center" class="sortableTD">&nbsp;
							<s:property value="creditFormula"/>
						</td>
						<td  width="20%"  align="center" class="sortableTD">&nbsp;
							<s:property value="pensionValue "/>
						</td>
						
						<td  width="15%" align="center"  class="sortableTD" id='ctrlHide' >
						<s:hidden name="editDataId"/>
						<input type="button" class="rowEdit" title="Edit Record" onclick="javascript:callForEditData('<s:property value="editDataId"/>');" />
							<input type="button" class="rowDelete"	theme="simple"  title="Delete Record" onclick="javascript:callDeleteData('<s:property value="editDataId"/>');" />
							
						</td>
					</tr>
					<%
					 n++;
				    %>
				</s:iterator>
					</table>
					</td>
				</tr></s:if>
			</table>		
			</td>
		</tr>
		
		<tr>
      		<td colspan="3">
	      		<table width="100%" border="0" cellpadding="2" cellspacing="1">
	         		<tr>
	            		<td width="78%">
	            			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
						</td>
	          		</tr>
	        	</table>
          	</td>
    	</tr>
		
		<s:if test="%{sPensionTypeCode != 5}">
		<tr>
	      <td colspan="3">
		      <table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
		          
		          <tr>
						<td width="90%" colspan="3"><strong class="forminnerhead">
						<s:if test="%{sPensionTypeCode == 1}">
	    					<label class="set" id="listHeading1" name="listHeading1" ondblclick="callShowDiv(this);"><%=label.get("listHeading1")%></label>
							
						</s:if>
						<s:elseif test="%{sPensionTypeCode == 2}">
							<label class="set" id="listHeading2" name="listHeading2" ondblclick="callShowDiv(this);"><%=label.get("listHeading2")%></label>
							
						</s:elseif> 
						<s:elseif test="%{sPensionTypeCode == 3}">
							<label class="set" id="listHeading3" name="listHeading3" ondblclick="callShowDiv(this);"><%=label.get("listHeading3")%></label>
							
						</s:elseif> 
						<s:elseif test="%{sPensionTypeCode == 4}">
							<label class="set" id="listHeading4" name="listHeading4" ondblclick="callShowDiv(this);"><%=label.get("listHeading4")%></label>
							
						</s:elseif> </strong>
						</td>						
								<%
								int totalPage = 0;
								int pageNo =0;
								try{
					 totalPage = (Integer) request.getAttribute("totalPage");
					pageNo = (Integer) request.getAttribute("PageNo");
					
								}catch(Exception e){
									
								}
					%> <s:if test="%{lstPensionConfiguration == null || lstPensionConfiguration.size() == 0}"></s:if><s:else>
						<td align="right" colspan="4" ><b>Page:</b> 
					 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="10" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
						</td>
						</s:else>
						</tr>
						
		          <tr>
		          	<s:hidden name="show" value="%{show}" />
		          	<s:hidden name="myPage" />
					<td class="formth" width="5%" align="center"> <label name="sr.no" class = "set" id="sr.no" ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label> :</td>				
					<td class="formth" width="15%" align="center"> <label name="pens.type.code" class = "set" id="pens.type.code" ondblclick="callShowDiv(this);"><%=label.get("pens.type.code")%></label> </td>
					<td class="formth" width="20%" align="left"> <label name="pens.min.service" class = "set"  id="pens.min.service" ondblclick="callShowDiv(this);"><%=label.get("pens.min.service")%></label> </td>
					<td class="formth" width="20%" align="center"> <label name="pens.max.service" class = "set"  id="pens.max.service" ondblclick="callShowDiv(this);"><%=label.get("pens.max.service")%></label> </td>
					<td class="formth" width="15%" align="left"> <label name="pens.comp.formula" class = "set" id="pens.comp.formula" ondblclick="callShowDiv(this);"><%=label.get("pens.comp.formula")%></label> </td>
					<td class="formth" width="15%" align="Center"> <label name="pens.min.amount" class = "set" id="pens.min.amount" ondblclick="callShowDiv(this);"><%=label.get("pens.min.amount")%></label> </td>
					<td class="formth" width="10%" align="center">
						<input type="button" name="delete" value="     Delete " class="delete" onClick="deleteMultipleRecordFun()">
					</td>
				  </tr>
		
				 <% int count = 0; %>
				 <%! int d = 0; %>
				 <%
				 	int cnt = 0;	
				 	int i = 0;
				 %>
				  
				  
				 <s:if test="%{lstPensionConfiguration == null || lstPensionConfiguration.size() == 0}">
					 <tr>
						<td width="100%" colspan="7" align="center">
							<font color="red">There is no data to display.</font>
						</td>
					 </tr>
				 </s:if> 
				  
				 <s:iterator value="lstPensionConfiguration">
					<tr
						<%if(count%2==0){%> class="tableCell1" <%}else{%>
					 	 class="tableCell2" <%	}count++; %>
						 onmouseover="javascript:newRowColor(this);"
						 title="Double click for edit"
						 onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
						 ondblclick="javascript:callForEdit('<s:property value="sPensionCode" />');" >
						 
							<td width="5%" align="center" class="sortableTD"><%=++cnt%><%++i;%></td>
							<td width="15%" align="center" class="sortableTD"><s:property value="sPensionTypeCode" /></td>
							<td width="20%" align="center" class="sortableTD"><s:property value="sPensionMinService" /></td>
							<td width="20%" align="center" class="sortableTD"><s:property value="sPensionMaxService" /></td>
							<s:if test="%{((sPensionFormula != null) && (sPensionFormula.length() > 0))}">
								<td width="15%" align="center" class="sortableTD"><s:property value="sPensionFormula" /></td>
							</s:if><s:else>	
								<td width="15%" align="center" class="sortableTD"><s:property value="sPensionCompFormula" /></td>
							</s:else>
							<td width="15%" align="right" class="sortableTD"><s:property value="sPensionMinAmt" /></td>
							<td width="10%" align="center" class="sortableTD">
								<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>" />
								<input type="checkbox" align="right" class="checkbox" id="confChk<%=i%>"
								name="confChk" onclick="callForDelete1('<s:property value="sPensionCode" />','<%=i%>')" />
							</td>
					</tr>
				 </s:iterator>
				 <% d = i; %>
		      </table>
	      </td>
	    </tr>
	    </s:if>
		
		</table>
		</td></tr></table>
</s:form>

<script type="text/javascript">
	/* Call onload Event */
	callOnLoad ();
	
	function callOnLoad ()
	{
		var obj = document.getElementById('paraFrm_sPensionTypeCode').value;
		
		displayTab(obj);
	
	//alert("inside after11"+obj);
	}

function displayTab(tabId)
 	{ 	
 		//alert("inside displayId"+tabId);
 		document.getElementById('paraFrm_sPensionTypeCode').value=tabId ;
       
		document.getElementById('superopt').className = ''; 
		document.getElementById('voluntaryopt').className = ''; 
		document.getElementById('compulsoryopt').className = ''; 
		document.getElementById('deathopt').className = '';
		//alert("getTabId for "+tabId+"=="+getTabId(tabId)) ;
		document.getElementById(getTabId(tabId)).className = 'on';
		//document.getElementById('paraFrm_aId').value =  aId; 
		//document.getElementById('paraFrm_divId').value = tabId; 
     	//document.getElementById('paraFrm_divCount').value ="someThing"; 
		
		if(tabId == "1")
		{   
			document.getElementById('SuperannuationDisp').style.display='';
		   	document.getElementById('VoluntaryDisp').style.display='none'; 
		   	document.getElementById('CompulsoryDisp').style.display='none';  
		   	document.getElementById('DeathDisp').style.display='none';
		   	document.getElementById('PensionAmtDisp').style.display='none';
		}
	   
	  	else if(tabId == "2")
	  	{   
	   		document.getElementById('SuperannuationDisp').style.display='none'; 
	   		document.getElementById('VoluntaryDisp').style.display=''; 
	   		document.getElementById('CompulsoryDisp').style.display='none';  
	   		document.getElementById('DeathDisp').style.display='none';    
	   		document.getElementById('PensionAmtDisp').style.display='none';
	  	}
	  
	   	else if(tabId == "3")
	   	{ 
	   		document.getElementById('SuperannuationDisp').style.display='none';   
	   		document.getElementById('VoluntaryDisp').style.display='none';
	   		document.getElementById('CompulsoryDisp').style.display=''; 
	   		document.getElementById('DeathDisp').style.display='none';
	   		document.getElementById('PensionAmtDisp').style.display='none';
	  	} 
	  
	   	else if(tabId == "4")
	   	{ 
	   		document.getElementById('SuperannuationDisp').style.display='none';  
	   		document.getElementById('VoluntaryDisp').style.display='none';   
	   		document.getElementById('CompulsoryDisp').style.display='none';
	   		document.getElementById('DeathDisp').style.display='';
	   		document.getElementById('PensionAmtDisp').style.display='none';
	  	}
	  	else if(tabId == "5")
	   	{ 
	   		document.getElementById('SuperannuationDisp').style.display='none';  
	   		document.getElementById('VoluntaryDisp').style.display='none';   
	   		document.getElementById('CompulsoryDisp').style.display='none';
	   		document.getElementById('DeathDisp').style.display='none';
	   		document.getElementById('PensionAmtDisp').style.display='';
	  	}
	  	else
	  	{
	  		document.getElementById('SuperannuationDisp').style.display='';
		   	document.getElementById('VoluntaryDisp').style.display='none'; 
		   	document.getElementById('CompulsoryDisp').style.display='none';  
		   	document.getElementById('DeathDisp').style.display='none';
		   	document.getElementById('PensionAmtDisp').style.display='none';
	  	}
 	}
 	
 function callTab(aId,tabId,type)
 	{ 	
 		
 		document.getElementById('paraFrm_prePensionTypeCode').value=document.getElementById('paraFrm_sPensionTypeCode').value ;
       
		if(tabId=="Superannuation")
		{   
			document.getElementById('paraFrm_sPensionTypeCode').value = '1';   
		}
	  	if(tabId=="Voluntary")
	  	{   
	   		document.getElementById('paraFrm_sPensionTypeCode').value = '2';
	  	}
	  
	   	if(tabId=="Compulsory")
	   	{ 
	   		document.getElementById('paraFrm_sPensionTypeCode').value = '3';
	  	} 
	  
	   	if(tabId=="Death")
	   	{ 
	   		document.getElementById('paraFrm_sPensionTypeCode').value = '4';
	  	}
	  	
	  	if(tabId=="PensionAmt")
	   	{ 
	   		document.getElementById('paraFrm_sPensionTypeCode').value = '5';
	  	}
	  	
	  	if(document.getElementById('paraFrm_prePensionTypeCode').value!=document.getElementById('paraFrm_sPensionTypeCode').value ){
	  	displayConfirmDiv();
		document.getElementById('confirmationDiv').style.display='block';
		}else{
			document.getElementById('paraFrm_prePensionTypeCode').value="";
		}
		
	  	
 	}
 	
 function savePreviousTabData(){
	var prePensionType =document.getElementById("paraFrm_prePensionTypeCode").value;
	var nextPensionType =document.getElementById("paraFrm_sPensionTypeCode").value;
	
	if (ValidateData(prePensionType))
		{
			document.getElementById("paraFrm").action="PensionConfiguration_save.action";
			document.getElementById("paraFrm").submit();
			displayTab(nextPensionType);
				
		}else{
			document.getElementById("confirmationDiv").style.visibility = 'hidden';
			document.getElementById("overlay").style.display = "none";
			displayTab(prePensionType);
		}
}
function proceedWithSave(){

	try{
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	document.getElementById('confirmationDiv').style.display='none';
	document.getElementById("overlay").style.display = "none";
	enableBlockDiv();
	savePreviousTabData();
	
	}
	catch(e){
		//alert(e);
	}
}

function proceedWithoutSave(){
	try{
	enableBlockDiv();
	
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	document.getElementById('confirmationDiv').style.display='none';
	document.getElementById("overlay").style.display = "none";
	//alert("b4=="+document.getElementById('paraFrm_sPensionTypeCode').value);
	displayTab(document.getElementById('paraFrm_sPensionTypeCode').value);
	document.getElementById('paraFrm').action="PensionConfiguration_proceedWithoutSave.action";
	document.getElementById('paraFrm').submit();
	}
	catch(e){
		//alert(e);
	}
}
function cancel(){
	document.getElementById('paraFrm_sPensionTypeCode').value=document.getElementById('paraFrm_prePensionTypeCode').value;
	document.getElementById('paraFrm_prePensionTypeCode').value="";
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	document.getElementById("overlay").style.display = "none";
	
}
 	
function callForDelete1(id,i)
	{
	   var flag='<%=d %>';
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	   		document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   {
	   		document.getElementById('hdeleteCode'+i).value="";
	   	}
   	}
   	
   	
   	 	function deleteMultipleRecordFun()
	{
		 if(chk())
		 {
		 	var con=confirm('Do you want to  delete the record ?');
			if(con)
			{
				document.getElementById('paraFrm').action="PensionConfiguration_deleteMultipleRecords.action";
			    document.getElementById('paraFrm').submit();
			}
			else
			    return true;
		 }
		 else 
		 {
		  		alert('Please select atleast one record to delete');
		 	 	return false;
		 }
	}
	
	function chk() 
	{
		var flag = '<%=d %>';
	  	for (var a=1;a<=flag;a++)
	  	{	
		   	if(document.getElementById('confChk'+a).checked == true)
		   	{	
		 	    return true;
		   	}	   
	  	}
	  	return false;
	}
	
	function deleteFun()
	{	
			if(callDelete("paraFrm_sPensionCode")){
			
			document.getElementById("paraFrm").action="PensionConfiguration_delete.action";
			document.getElementById("paraFrm").submit();
			
			}else{
				return false;
			}
		}
	
	function searchFun() {
		callsF9(500,325,'PensionConfiguration_f9action.action');
		return false;
	}
	function resetFun() {
		var sPensionType = document.getElementById("paraFrm_sPensionTypeCode").value;
		
		if (sPensionType == 5) {
			 document.getElementById('paraFrm_sPensionAmtCreditCode1').value = '';
			 document.getElementById('paraFrm_sPensionAmtCreditHead1').value = '';	
			 document.getElementById('paraFrm_sPensionAmtCreditValue1').value = '';
			 
			 document.getElementById('paraFrm_sPensionAmtCreditCode2').value = '';
			 document.getElementById('paraFrm_sPensionAmtCreditHead2').value = '';	
			 document.getElementById('paraFrm_sPensionAmtCreditValue2').value = '';
			 
			 document.getElementById('paraFrm_sPensionAmtCreditCode3').value = '';
			 document.getElementById('paraFrm_sPensionAmtCreditHead3').value = '';	
			 document.getElementById('paraFrm_sPensionAmtCreditValue3').value = '';
		} else {
			document.getElementById("paraFrm").action="PensionConfiguration_reset.action";
			document.getElementById("paraFrm").submit();
		}
	}
 	
 	function cancelFun() {
		document.getElementById("paraFrm").action="PensionConfiguration_input.action";
		document.getElementById("paraFrm").submit();	
		return false;
	}
	
	function saveFun() {
		var pensionType=document.getElementById('paraFrm_sPensionTypeCode').value
		if (ValidateData(pensionType))
		{
			document.getElementById("paraFrm").action="PensionConfiguration_save.action";
			document.getElementById("paraFrm").submit();	
		}
		return false;
	}
	
	function callFormulaBuilderPension(id) {
		window.open('','new','top=100,left=300,width=400,height=400,scrollbars=no,status=no,resizable=no');
 		document.getElementById("paraFrm").target="new";
 		document.getElementById("paraFrm").action='FormulaBuilderPension_formulaCalc.action?id='+id; 
  		document.getElementById("paraFrm").submit();
  		document.getElementById("paraFrm").target="main";
  		
  		var PensionType = document.getElementById('paraFrm_sPensionTypeCode').value;
  		//if (PensionType == 4) {
		//	document.getElementById('paraFrm_sPensionFormula4').value = '';
		//	document.getElementById('paraFrm_sPensionCompFormula4').value = '';
  		//}
  	}
  	
  	function callForEdit(id)
	{
	  	document.getElementById('paraFrm_sPensionCode').value=id;
	  	//document.getElementById('paraFrm_sMode').value='update';
	  
	   	document.getElementById("paraFrm").action="PensionConfiguration_edit.action"; 
	    document.getElementById("paraFrm").submit();
    }
  	
  	function ValidateData(PensionType)
  	{
  		
  		if (PensionType == 1) {
  		
  		var fields1=['paraFrm_sPensionEffFrm1','paraFrm_sPensionMinService1','paraFrm_sPensionMaxService1','paraFrm_sPensionMinAmt1',
  						'paraFrm_sPensionEmpStatus1','paraFrm_sPensionEmolFormula1','paraFrm_sPensionEmolMonths1','paraFrm_sPensionFormula1'];
  						
  		var lables1=['pens.effective.from1','pens.min.service1','pens.max.service1','pens.min.amount1','pens.emp.status1','pens.emol.formula1',
  						'pens.emol.months1','pens.comp.formula1'];
  						
  		var flag1=['select','enter','enter','enter','select','select','enter','enter'];	
  		
  		if(!validateBlank(fields1,lables1,flag1)){
  			return false;
  		}
  		if(!validateDate('paraFrm_sPensionEffFrm1', 'pens.effective.from')){
  			return false;
  		}
  		
  		
  			
  			
  		} else if (PensionType == 2) {
  		
  		var fields2=['paraFrm_sPensionEffFrm2','paraFrm_sPensionMinService2','paraFrm_sPensionMaxService2','paraFrm_sPensionMinAmt2',
  						'paraFrm_sPensionEmpStatus2','paraFrm_sPensionVolWeiyears2','paraFrm_sPensionEmolFormula2','paraFrm_sPensionEmolMonths2','paraFrm_sPensionFormula2'];
  						
  		var lables2=['pens.effective.from2','pens.min.service2','pens.max.service2','pens.min.amount2','pens.emp.status2','pens.vol.weiyears2','pens.emol.formula2',
  						'pens.emol.months2','pens.comp.formula2'];
  		
  		var flag2=['select','enter','enter','enter','select','select','enter','enter','enter'];
  			
  		if(!validateBlank(fields2,lables2,flag2)){
  			return false;
  		}	
  		if(!validateDate('paraFrm_sPensionEffFrm2', 'pens.effective.from')){
  			return false;
  		}
  			
  		} else if (PensionType == 3) {
  			var fields3=['paraFrm_sPensionEffFrm3','paraFrm_sPensionMinService3','paraFrm_sPensionMaxService3','paraFrm_sPensionMinAmt3',
  						'paraFrm_sPensionEmpStatus3','paraFrm_sPensionEmolFormula3','paraFrm_sPensionEmolMonths3','paraFrm_sPensionFormula3'];
  			
  			var lables3=['pens.effective.from3','pens.min.service3','pens.max.service3','pens.min.amount3','pens.emp.status3','pens.emol.formula3',
  						'pens.emol.months3','pens.comp.formula3'];
  						
  			var flag3=['select','enter','enter','enter','select','select','enter','enter'];
  			
  			
  			if(!validateBlank(fields3,lables3,flag3)){
  					return false;
  			}
  			
  			if(!validateDate('paraFrm_sPensionEffFrm3', 'pens.effective.from')){
  					return false;
  			}
  			
  			
  		} else if (PensionType == 4) {
  			var fields4=['paraFrm_sPensionEffFrm4','paraFrm_sPensionMinService4','paraFrm_sPensionMaxService4','paraFrm_sPensionMinAmt4',
  						'paraFrm_sPensionEmpStatus4','paraFrm_sPensionEmolFormula4','paraFrm_sPensionEmolMonths4'];
  			
  			var lables4=['pens.effective.from4','pens.min.service4','pens.max.service4','pens.min.amount4','pens.emp.status4','pens.emol.formula4',
  						'pens.emol.months4'];
  			
  			var flag4=['select','enter','enter','enter','select','select','enter'];
  					
  			if(!validateBlank(fields4,lables4,flag4)){
  					return false;
  			}	
  			
  			if(!validateDate('paraFrm_sPensionEffFrm4', 'pens.effective.from')){
  				return false;
  			}		
  			
  			if ((document.getElementById('paraFrm_sPensionFormula4').value == "") && (document.getElementById('paraFrm_sPensionCompFormula4').value == ""))	{
				alert ("Please select 'Pension Formula'");
				document.getElementById('paraFrm_sPensionFormula4').focus(); 
				return false; 			
  			}
  			
  		} else if (PensionType == 5) {
  			
  			var fields4=['paraFrm_sPensionAmtCreditCode1','paraFrm_sPensionAmtCreditValue1','paraFrm_sPensionAmtCreditCode2','paraFrm_sPensionAmtCreditValue2',
  						'paraFrm_sPensionAmtCreditCode3','paraFrm_sPensionAmtCreditValue3'];
  			
  			var lables4=['pens.amt.subheading1','pens.amt.subtitle1','pens.amt.subheading2','pens.amt.subtitle2','pens.amt.subheading3','pens.amt.subtitle3'];
  			
  			var flag4=['select','enter','select','enter','select','enter'];
  					
  			if(!validateBlank(fields4,lables4,flag4)) {
  					return false;
  			}
  			
  			var head1 = document.getElementById('paraFrm_sPensionAmtCreditCode1').value;
  			var head2 = document.getElementById('paraFrm_sPensionAmtCreditCode2').value;
  			var head3 = document.getElementById('paraFrm_sPensionAmtCreditCode3').value;
  			
  			if ((head1 == head2) || (head1 == head3) || (head2 == head3)) {
  				alert ("Please select different Credit Head, Same Credit Head is not allow.");
  				return false;
  			}
  			
  				
  			return true;
  		}
  		
  		var minYears=document.getElementById('paraFrm_sPensionMinService'+PensionType).value;
  		var maxYears=document.getElementById('paraFrm_sPensionMaxService'+PensionType).value;
  		
  		if(eval(minYears) >eval(maxYears)){
  			alert(document.getElementById('pens.max.service'+PensionType).innerHTML+" should be greater than or equal to "+
  			document.getElementById('pens.min.service'+PensionType).innerHTML);
  			document.getElementById('paraFrm_sPensionMaxService'+PensionType).focus();
  			return false;
  		}
  		
  		
  		return true;
	}
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
		//cell.style.backgroundColor="#ffffff";
	}
	
		function newRowColor(cell) {
		
		cell.style.cursor='pointer';
		cell.className='Cell_bg_first';
	}
	
	function callPageText(id,status1){
 			 
	   if(status1=="null" || status1=="" ){		
				status1="P";
			}
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;		
		 	totalPage =document.getElementById('totalPage').value;		
			document.getElementById('paraFrm_myPage').value=pageNo;
	      
	        if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		 	document.getElementById('paraFrm').action="PensionConfiguration_input.action"
			document.getElementById('paraFrm').submit();
		}
		
		
	}
	 function callPage(id,pageImg){
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
         var p=document.getElementById('pageNoField').value;
		if(id=='P'){
		id=eval(p)-1;
		}
		if(id=='N'){
		id=eval(p)+1;
		} 
		
		document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm').action="PensionConfiguration_input.action"
		document.getElementById('paraFrm').submit();
		 
	}	

function displayConfirmDiv(){
			document.getElementById("confirmationDiv").style.visibility = 'visible';
			 document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		 			+'<tr><td class="txt"><b><center>Do you want to save the changes made in <%=request.getAttribute("tabName")%> ? </td></tr>'
		 			+'<tr><td><b><center><input type="button" value=" Yes " onclick="proceedWithSave();" class="token"/>'
		 			+'&nbsp;<input type="button" class="token" value=" No " onclick="proceedWithoutSave();"/>'
		 			+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
		 	document.getElementById("overlay").style.display = "block";
		JSFX_FloatTopDiv();
}	
	
function disableBlockDiv(){
	  		alert('disableBlockDiv');
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
			catch(e){
			alert('disableBlockDiv'+e);
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
	  }

function enableBlockDiv(){
	  		
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "visible";
				document.getElementById("overlay").style.display = "block";
			}
			catch(e){
			}
	  }	
	  var verticalpos="fromtop"
	if (!document.layers)
	//document.write('</div>');
	function JSFX_FloatTopDiv()
{
	var startX = 250,
	startY = 200;
	var ns = (navigator.appName.indexOf("Netscape") != -1);
	var d = document;
	function ml(id)
	{
		var el=d.getElementById?d.getElementById(id):d.all?d.all[id]:d.layers[id];
		if(d.layers)el.style=el;
		el.sP=function(x,y){this.style.left=x;this.style.top=y;};
		el.x = startX;
		if (verticalpos=="fromtop")
		el.y = startY;
		else{
		el.y = ns ? pageYOffset + innerHeight : parent.document.body.scrollTop + parent.document.body.clientHeight;
		el.y -= startY;
		}
		return el;
	}
	parent.frames[2].stayTopLeft=function()
	{
		if (verticalpos=="fromtop"){
		var pY = ns ? pageYOffset : parent.document.body.scrollTop;
		ftlObj.y += (pY + startY - ftlObj.y)/8;
		}
		else{
		var pY = ns ? pageYOffset + innerHeight : parent.document.body.scrollTop + parent.document.body.clientHeight;
		ftlObj.y += (pY - startY - ftlObj.y)/8;
		}
		ftlObj.sP(ftlObj.x, ftlObj.y);
		setTimeout("stayTopLeft()", 10);
	}
	ftlObj = ml("confirmationDiv");
	stayTopLeft();
}
 	function getTabId(typeCode){
		switch(eval(typeCode)){
			case 1: return "superopt";
			case 2: return "voluntaryopt";
			case 3: return "compulsoryopt";
			case 4: return "deathopt";
			case 5: return "pensionamtopt";
			default: return "superopt";
		}
	}
	
	function validateF9(action) {
		javascript:callsF9(500,325,'PensionConfiguration_'+action+'.action'); 
	}
	
	/** Added by Nilesh */  
	function callAdd()
	{
	var credithead = document.getElementById('paraFrm_creditHead').value;
	
	var formula = document.getElementById('paraFrm_creditFormula').value;
	
	var valueper = document.getElementById('paraFrm_pensionValue').value;
	
	if(credithead == "")
	{
	alert("Please Select Credit Head");
	return false;
	}
	
	if(formula == "-1")
  {
    alert("Please select Credit Formula");
    return false;
  }	
  
 if(valueper == "")
{
alert("Please enter value");
return false;
}	
	
if( (valueper >100)  && (formula == "P")  )
{
alert("Your value is greater than 100 , select different formula");
return false;
}	
	
	
	return true;
	
	}
	

 
 
   function callForEditData(editCode)
{
	document.getElementById("paraFrm").action= "PensionConfiguration_editData.action?editCode="+editCode;
	document.getElementById('paraFrm_paraId').value=editCode;
	document.getElementById("paraFrm").submit();
}
   
   function callDeleteData(deleteCode){
 
    
       var conf=confirm("Are you sure to delete this record ? ");
        if(conf) {
     
       document.getElementById("paraFrm").action="PensionConfiguration_deleteData.action?deleteCode= "+deleteCode;
   	   document.getElementById('paraFrm_paraId').value=deleteCode;
   	   document.getElementById("paraFrm").submit();
   }
   }  	
   
   function isNumWithCharKey(evt)
	 {
	/* alert("Only Numbers and Special Characters are allowed here!");*/
         var charCode = (evt.which) ? evt.which : event.keyCode
            ///alert("charCode"+charCode);
        if((charCode!=46) )
            {
             if (charCode > 31 && (charCode < 48 || charCode > 57) || (charCode==44) && (charCode==46))
                return false;
             }
             return true;
      
      
      }		
   
   
   
 
   
</script>

