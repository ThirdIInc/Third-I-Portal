<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/lib/dropdown.jsp"%>

<s:form action="OtMISReport" method="post" id="paraFrm"
	theme="simple">
	<s:hidden name="report" />
	<s:hidden name="reportAction" value='OtMISReport_getReport.action'/>
		<s:hidden name="doubleOTflag" /> 
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">OT Calculation MIS Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Button Panel Start -->
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap"><a href="#" onclick="resetFun();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
					<td width="100%"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<div name="htmlReport" id='reportDiv'
				style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
			<iframe id="reportFrame" frameborder="0" onload=alertsize();
				style="vertical-align: top; float: left; border: 0px solid;"
				allowtransparency="yes" src="../pages/common/loading.jsp"
				scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
				name="htmlReport" width="100%" height="200"></iframe></div>
			</td>

		</tr>
	<!-- Button Panel End -->
	<tr>
			<td width="100%">
			<table width="100%"  border="0" id="reportBodyTable">	
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg" >

				<tr> 
					<td>
					<table width="100%" border="0" align="center" cellpadding="1" 
						cellspacing="2"> 
						<tr> 
							<td colspan="4" class="formhead"><strong
								class="forminnerhead">Select Period </strong></td>
						</tr>
						 
						<tr> 
							<td colspan="1" width="10%"><label id="month1" name="month"
								ondblclick="callShowDiv(this);"><%=label.get("month")%></label>:<font
								color="red">*</font></td>
							<td colspan="1"  width="25%"><s:select theme="simple"
								name="month" cssStyle="width:152"
								list="#{'0':'Select --','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
							</td> 
							<td colspan="1" width="10%"><label class="set" id="year"
								name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label>:<font
								color="red">*</font></td>
							<td  width="25%"><s:textfield name="year" 
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
					 			size="10" /></td>
							
						</tr>
					</table>
					</td> 
				</tr>
			</table>
			</td>
		</tr>
		
		<!-- section for select filters -->
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr> 
					<td> 
					<table width="100%" border="0" align="center" cellpadding="1"
						cellspacing="2">
						<tr>
							<td class="formhead"><strong
								class="forminnerhead">Select Filters </strong></td>
						</tr>
						
						<tr> 
							<td colspan="1" width="15%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font></td>
							<td colspan="2" width="25%"><s:hidden name="divisionID" /> <s:textfield
								name="divisionName" theme="simple" readonly="true" maxlength="50"
								size="25" />   
							
								<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
															
								onclick="javascript:callDropdown('paraFrm_divisionName',200,250,'OtMISReport_f9Division.action',event,'false','no','right')"/>
								
							</td>
							
						</tr>
						
						<tr>


							<td width="20%"><label class="set" name="branch" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
							</td>

							<td colspan="2"><s:hidden name="centerId" /> <s:textarea
								cols="100" rows="1" name="centerName" theme="simple"
								readonly="true" /></td>
							<td width="30%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_centerName',350,250,'OtMISReport_f9Branch.action',event,'false','no','right')">
							</td>

						</tr>
						
						<tr>
							<td width="20%"><label class="set" name="department"
								id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
							</td>

							<td colspan="2"><s:hidden name="deptCode" /> <s:textarea
								cols="100" rows="1" name="deptName" theme="simple"
								readonly="true" /></td>
							<td width="30%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_deptName',350,250,'OtMISReport_f9Department.action',event,'false','no','right')">
							</td>
						</tr>
						<tr>
							<td width="20%"><label class="set" name="designation"
								id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:
							</td>

							<td colspan="2"><s:hidden name="desgCode" /> <s:textarea
								cols="100" rows="1" name="desgName" theme="simple"
								readonly="true" /></td>
							<td width="30%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_desgName',350,250,'OtMISReport_f9Desg.action',event,'false','no','right')">
							</td>
						</tr>

						<tr>
							<td width="20%"><label class="set" name="paybill"
								id="paybill" ondblclick="callShowDiv(this);"><%=label.get("paybill")%></label>:
							</td>

							<td colspan="2"><s:hidden name="paybillCode" /> <s:textarea
								cols="100" rows="1" name="paybillName" theme="simple"
								readonly="true" /></td>
							<td width="30%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_paybillName',350,250,'OtMISReport_f9Paybill.action',event,'false','no','right')">
							</td>
						</tr>
						<tr>

							<td width="20%"><label class="set" name="Costcenter"
								id="Costcenter" ondblclick="callShowDiv(this);"><%=label.get("Costcenter")%></label>:
							</td>

							<td colspan="2"><s:hidden name="costCenterCode" /> <s:textarea
								cols="100" rows="1" name="costCenterName" theme="simple"
								readonly="true" /></td>
							<td width="30%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_costCenterName',350,250,'OtMISReport_f9Costcenter.action',event,'false','no','right')">
							</td>
						</tr>
						
						<tr>

							<td width="20%"><label class="set" name="manager"
								id="manager" ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>:
							</td>

							<td colspan="2"><s:hidden name="managerID" /> <s:textarea
								cols="100" rows="1" name="managerName" theme="simple"
								readonly="true" /></td>
							<td width="30%">
							<s:if test="generalFlag"></s:if> <s:else>
							 <img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_managerName',350,250,'OtMISReport_f9Manager.action',event,'false','no','right')">
							</s:else>
							
							</td>
						</tr>

						<!--<tr>
							<td width="20%"><label class="set" name="Shift" id="Shift"
								ondblclick="callShowDiv(this);"><%=label.get("Shift")%></label>:
							</td>

							<td colspan="2"><s:hidden name="shiftCode" /> <s:textarea
								cols="100" rows="1" name="shiftName" theme="simple"
								readonly="true" /></td>
							<td width="35%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_shiftName',350,250,'OtMISReport_f9shiftaction.action',event,'false','no','right')">
							</td>
						</tr>
						
					--></table>
				</td>
				</tr>
			</table>
		</td>
	</tr>
		<!-- section for select parameters -->
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg" >

				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="1"
						cellspacing="2">
						<tr>
							<td colspan="6" class="formhead"><strong
								class="forminnerhead">Select any/all of the following options to add in the report </strong></td>
						</tr>
						<tr>
						
						<td colspan="4">
							<table width="100%" border="0">
								<tr>
									<td width="10%"><label id="branch1" name="branch"
										ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
									<td width="10%" >
										<s:checkbox name="checkBrn" /> 
									</td>
									<td width="10%"><label id="department1" name="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
									<td width="10%">
										<s:checkbox name="checkDept" /> 
									</td>
									<td width="10%"><label id="grade" name="grade"
										ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
									<td width="10%">
										<s:checkbox name="checkEmpGrade" /> 
									</td>
									<td width="10%"></td>
								</tr>
								<tr>  
									<td><label id="accNo" name="accNo"
										ondblclick="callShowDiv(this);"><%=label.get("accNo")%></label>:</td>
									<td>
										<s:checkbox name="checkAccount" /> 
									</td>
										
									<td><label id="bank1" name="bank"
										ondblclick="callShowDiv(this);"><%=label.get("bank")%></label>:</td>
									<td> 
										<s:checkbox name="checkBank" /> 
									</td>
									
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
		</table>
	</td>
</tr>
	<!-- Button Panel Start -->
		<tr>
		<td >
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<!-- Button Panel End -->
		
		</table>
			
</s:form>    
			
			
			
		<script>
getYear();

function getYear(){

	var current = new Date();
	 var year =current.getFullYear();

	 var yr =document.getElementById("paraFrm_year").value;
	 if(yr==''){
	  	document.getElementById("paraFrm_year").value =year;
	  }
	  var paidYr =document.getElementById("paraFrm_paidYear").value;
	 if(paidYr==''){
	  	document.getElementById("paraFrm_paidYear").value =year;
	  }
}

	function validateFields(){
		var name = ['paraFrm_year'];
		var label = ['year'];
		var flag = ["enter"];
		
		if(!validateBlank(name, label, flag)) {
			return false;
		}
		
		var month  =document.getElementById("paraFrm_month").value;
		var mont=document.getElementById('month1').innerHTML.toLowerCase();
			if(month =='0'){
		 	alert("Select "+mont);
		 	return false;
		 	}
		var divNm   =document.getElementById("paraFrm_divisionID").value;
		if(divNm==""){
	 	alert("Please select the Division");
	 	document.getElementById('paraFrm_divisionName').focus();
	 	return false;
		 }
		
		return true;
	}
	
	function resetFun() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='OtMISReport_reset.action';
		document.getElementById('paraFrm').submit();
	}

	function callReport(type){
	try{
	if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm_report').value=type;
				callReportCommon(type);
			}
			} catch(e){
				alert(e);
			}
}
function generateReport(type)
 {	
	try{
	//document.getElementById('paraFrm_report').value=type;
		if(!validateFields()){
				return false;
			} else {
			document.getElementById('paraFrm_report').value=type;
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="OtMISReport_getReport.action";	
				document.getElementById('paraFrm').submit();
			}
	 }catch (e)
	 {
	 	alert(e);
	 }	
}
	function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm_report').value=type;
			document.getElementById('paraFrm').action='OtMISReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		}	
	}
	
	
 
</script>