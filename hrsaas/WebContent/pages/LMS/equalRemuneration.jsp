<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="ReturnAct" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" cellspacing="2" class="formbg" align="center">
		<s:hidden name="equalRemunerationBean.equalRemunerationID" value="1"/>
		<s:hidden name="equalRemunerationBean.equalRemunerationReturnID" value="1"/>
		<s:hidden name="pageToShow" value="childlabour" />
		<s:hidden name="previousPage" value="bonusrules" />
		<s:hidden name="orgId" /><s:hidden name="frequency" />
		<s:hidden name="fromPeriod" /><s:hidden name="toPeriod" />
<s:hidden name="orgName" />



		<tr>
			<td valign="bottom" class="txt" colspan="3">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Equal
					Remuneration Act </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td class="formtext" colspan="3">
			<table width="100%">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="30%">
					<div align="left"> <s:select
						name="actList" list="actMap" size="1" headerKey=""
						headerValue="--Select Act--" cssStyle="width:150" theme="simple" /><input type="submit" value="Go To"
						class="token" onclick="return goTo();"></div>
					</td>

				</tr>
			</table>
</td>
</tr>
<tr>
			<td class="formtext" colspan="3">
			<!-- Heading Section (Start) -->
			<table width="100%" style="border: 1px solid #565656;">
				<!-- English heading section (Begin) -->
				<tr>
					<td align="center">
						<label class="set" name="remuneration.heading"
							id="remuneration.heading" ondblclick="callShowDiv(this);"><%=label.get("remuneration.heading")%>
						</label><s:property value="fromPeriod" /> - <s:property value="toPeriod" /><br>
					</td>
				</tr>
				<td>&nbsp;<br>
				</td>
				<!-- English heading section (End) -->

				<!-- Marathi heading section (Begin) -->
				<tr>
					<td colspan="2" align="center">
						<label class="set" name="remuneration.marathi_heading1" id="remuneration.marathi_heading1"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.marathi_heading1")%>
						</label> <br>
					<s:property value="fromPeriod" /> - <s:property value="toPeriod" /><label class="set" name="remuneration.marathi_heading2"
						id="remuneration.marathi_heading2" ondblclick="callShowDiv(this);"><%=label.get("remuneration.marathi_heading2")%>
					</label></td>
				</tr>
				<td>&nbsp;<br>
				</td>
				<!-- Marathi heading section (End) -->
			</table>
			<!-- Heading Section (End) --> 
</td>
</tr>
<tr>
			<td class="formtext" colspan="3">
			<table width="100%" cellspacing="2"  style="border: 1px solid #565656;"	align="center">
				<tr>
					<td width="3%">&nbsp;</td>
					<td width="45%">&nbsp;</td>
					<td width="12%" align="left">
						<label class="set" name="remuneration.male_section_head" id="remuneration.male_section_head"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.male_section_head")%>
						</label>
					</td>
					<td width="14%" align="left">
						<label class="set" name="remuneration.female_section_head" id="remuneration.female_section_head"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.female_section_head")%>
						</label>
					</td>
					<td width="12%" align="left">
						<label class="set" name="remuneration.total_section_head" id="remuneration.total_section_head"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.total_section_head")%>
						</label>
					</td>
				</tr>
				<tr>
					<td>1</td>
					<td>
						<label class="set" name="remuneration.total_number_of_emp" id="remuneration.total_number_of_emp"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.total_number_of_emp")%>
						</label>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.Emp_Male" size="5"/> 
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.totalNumberOfEmployeesFemale" size="5"/>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.totalNumberOfEmployeesTotal" size="5"/>
					</td>
				</tr>
				<tr>
					<td>2</td>
					<td>
						<label class="set" name="remuneration.emp_having_same_job" id="remuneration.emp_having_same_job"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.emp_having_same_job")%>
						</label>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.employeeWithSameJobMale" size="5"/>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.employeeWithSameJobFeMale" size="5"/>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.employeeWithSameJobTotal" size="5"/>
					</td>
				</tr>
				<tr>
					<td>3</td>
					<td>
						<label class="set" name="remuneration.how_much_pay_for_female_worker" id="remuneration.how_much_pay_for_female_worker"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.how_much_pay_for_female_worker")%>
						</label>
					</td>
					<td align="left">&nbsp;</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.howMuchPayForFemaleWorker" size="5"/>
					</td>
					<td align="left">&nbsp;</td>
				</tr>
				<tr>
					<td>4</td>
					<td>
						<label class="set" name="remuneration.how_much_pay_for_male_worker" id="remuneration.how_much_pay_for_male_worker"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.how_much_pay_for_male_worker")%>
						</label>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.howMuchPayForMaleWorker" size="5"/>
					</td>
					<td align="left">&nbsp;</td>
					<td align="left">&nbsp;</td>
				</tr>
				<tr>
					<td>5</td>
					<td>
						<label class="set" name="remuneration.emp_having_same_job_receive_house_rent_allowance" id="remuneration.emp_having_same_job_receive_house_rent_allowance"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.emp_having_same_job_receive_house_rent_allowance")%>
						</label>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.maleEmpWithSameJobReceiveHRA" size="5"/>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.femaleEmpWithSameJobReceiveHRA" size="5"/>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.totalEmpWithSameJobReceiveHRA" size="5"/>
					</td>
				</tr>
				<tr>
					<td rowspan=2>6</td>
					<td>
						<label class="set" name="remuneration.emp_having_same_job_receive_allowance" id="remuneration.emp_having_same_job_receive_allowance"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.emp_having_same_job_receive_allowance")%>
						</label>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.maleEmpWithSameJobReceiveAllowance" size="5"/>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.femaleEmpWithSameJobReceiveAllowance" size="5"/>
					</td>
					<td align="left">
						<s:textfield name="equalRemunerationBean.totalEmpWithSameJobReceiveAllowance" size="5"/>
					</td>
				</tr>
				<tr>
					<td>
						<label class="set" name="remuneration.type_of_allowance" id="remuneration.type_of_allowance"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.type_of_allowance")%>
						</label>
					</td>
					<td colspan="3" align="left">
						<s:textarea name="equalRemunerationBean.typeOfAllowance" cols="72" rows="2" />
					</td>
				</tr>
			</table>
			</td>
</tr>
<tr>
			<td class="formtext" colspan="3">
			<table width="100%" cellspacing="2"  style="border: 1px solid #565656;"	align="center">
				<tr>
					<td width="3%">7</td>
					<td width="66%">
						<label class="set" name="remuneration.Do_emp_having_same_job_receive_house_rent_allowance" id="remuneration.Do_emp_having_same_job_receive_house_rent_allowance"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.Do_emp_having_same_job_receive_house_rent_allowance")%>
						</label>
					</td>
					<td width="30%" align="left"> 
						<s:checkbox name="equalRemunerationBean.doEmpWithSameJobReceiveSameHRA" id="equalRemunerationBean.doEmpWithSameJobReceiveSameHRA" /> 
							Yes / &#2361;&#2379;&#2351;
					</td>
				</tr>
				<tr>
					<td>8</td>
					<td>
						<label class="set" name="remuneration.Do_emp_having_same_job_receive_other_allowance" id="remuneration.Do_emp_having_same_job_receive_other_allowance"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.Do_emp_having_same_job_receive_other_allowance")%>
						</label>
					</td>
					<td align="left"> 
						<s:checkbox name="equalRemunerationBean.doEmpWithSameJobReceiveOtherAllowance" id="equalRemunerationBean.doEmpWithSameJobReceiveOtherAllowance" />
						Yes / &#2361;&#2379;&#2351;
					</td>
				</tr>
			</table>
			</td>
</tr>
<tr>
			<td class="formtext" colspan="3">
			<table width="100%" cellspacing="2"  style="border: 1px solid #565656;"	align="center">
				<tr>
					<td width="3%">9</td>
					<td width="66%">
						<label class="set" name="remuneration.male_female_having_same_job_Not_same_pay" id="remuneration.male_female_having_same_job_Not_same_pay"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.male_female_having_same_job_Not_same_pay")%>
						</label>
					</td>
					<td width="30%" align="left">
						<s:textarea name="equalRemunerationBean.empWithSameJobNotSamePay" cols="43" rows="2"/> 
					</td>
				</tr>
				<tr>
					<td>10</td>
					<td>
						<label class="set" name="remuneration.male_female_having_same_job_Not_same_allowance" id="remuneration.male_female_having_same_job_Not_same_allowance"
							ondblclick="callShowDiv(this);"><%=label.get("remuneration.male_female_having_same_job_Not_same_allowance")%>
						</label>
					</td>
					<td align="left">
						<s:textarea name="equalRemunerationBean.empWithSameJobNotSameAllowance" cols="43" rows="2"/>
					</td>
				</tr>
			</table>
			</td>
</tr>
<tr>
			<td class="formtext" colspan="3">
			<table width="100%" cellspacing="2" align="center">
				<tr>
					<td class="formtext" colspan="3">
					<table width="100%">
						<tr>
							<td width="78%">
								<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>

	function goTo() {
		var combo = trim(document.getElementById('paraFrm_actList').value);
		var pageValue='';
		if(combo==''){
			alert('Please select act');
			return false;
		}
		if(combo=='POG'){
			pageValue='gratuityrules';
		}
		else if(combo=='MHRA'){
			pageValue='rentallowance';
		}
		else if(combo=='POB'){
			pageValue='bonusrules';
		}
		else if(combo=='CL'){
			pageValue='childlabour';
		}
		else if(combo=='ER'){
			pageValue='equalrenumeration';
		}else if(combo=='MB'){
			pageValue='maternitybenefits';
		}else if(combo=='DISH'){
			pageValue='annualfactory';
		}
		document.getElementById('paraFrm_pageToShow').value = pageValue;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_next.action';
		document.getElementById('paraFrm').submit();
	}
	function returntolistFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_callReturnList.action';
		document.getElementById('paraFrm').submit();
	}
function nextFun()  {		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_next.action';
		document.getElementById('paraFrm').submit();
	
	}
function previousFun()  {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
function saveandnextFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ReturnAct_equalRemunerationSaveAndNextRecords.action';
		document.getElementById('paraFrm').submit();
}	
</script>