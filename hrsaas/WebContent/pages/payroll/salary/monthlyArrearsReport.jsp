
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="MonthlyArrearsReport" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Arrears Report</strong></td>
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
	<s:hidden name="promotionReportCode" /> 
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		
		<tr>
			<td colspan="3" align="right" width="100%">
				<div align="right"><font color="red">*</font> Indicates Required</div>
			</td>
		</tr>
		
		
		<tr>
			<td >
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
					<s:if test="promotionReportCode!=''">
					<a href="#" onclick="callBackPromotion();">
							<img src="../pages/images/buttonIcons/back.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Back </a>&nbsp;&nbsp;
					</s:if>
					
						<a href="#" onclick="callReset();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
			<tr>
		<td>
		<div name="htmlReport" id='reportDiv'
			style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
		<iframe id="reportFrame" frameborder="0" onload=alertsize();
			style="vertical-align: top; float: left; border: 0px solid;"
			allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
			marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
			width="100%" height="200"></iframe> </div>
		</td>
		</tr>
		</table>
		</td>
		
		
    </tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg" id="reportBodyTable">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
					
						
						<tr>
							<td colspan="5">
								<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
								
								<tr>
							<td class="text_head" colspan="6"><strong
								class="forminnerhead">Select Period</strong></td>
						</tr>
								

						<tr>

							<td colspan="1" width="20%"><label  class = "set"  id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="monArrReport.month" cssStyle="width:152"
								list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>

							<td colspan="1" width="20%"><label  class = "set"  id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font color="red">*</font>:</td>
							<td><s:textfield name="monArrReport.year"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25" /></td>
						</tr>
								</table>
							</td>
						</tr>
						
						
						<tr>
							<td colspan="5">
								<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
								
								<tr>
									<td class="text_head" colspan="6"><strong
										class="forminnerhead">Select Filter Option</strong></td>
								</tr>
								<tr>

							<td colspan="1" width="15%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> <font color="red">*</font>: </td>
							<td colspan="1" width="83%"><s:hidden
								name="monArrReport.divCode" />
								
								 <s:textarea name="monArrReport.divName" cols="120" rows="1" readonly="true" />
								
							</td>
							<td width="2%">
							<s:if test="promotionReportCode==''">
							<img
								src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="17" theme="simple"
								onclick="javascript:callDropdown('paraFrm_monArrReport_divName',350,250,'MonthlyArrearsReport_f9div.action',event,'false','no','right')">
							</s:if>		
							

							</td>
							</tr>
							<tr>

							<td colspan="1" width="20%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td><s:hidden name="monArrReport.branchCode" /> 
								<s:textarea name="monArrReport.branchName" cols="120" rows="1" readonly="true" />
							</td>
							<td>
							<img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callDropdown('paraFrm_monArrReport_branchName',350,250,'MonthlyArrearsReport_f9centeraction.action',event,'false','no','right')">
							
									
								
							</td>
						</tr>



						<tr>

							<td colspan="1" width="20%"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden
								name="monArrReport.deptCode" /> 
								
								<s:textarea name="monArrReport.deptName" cols="120" rows="1" readonly="true" />
								
								</td>
								<td>
								<img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callDropdown('paraFrm_monArrReport_deptName',350,250,'MonthlyArrearsReport_f9deptaction.action',event,'false','no','right')">
							
								
								
							</td>
						</tr>
						 <tr>
		    
		     <td class="formtext"  colspan="1" height="20" class="formtext" width="15%">
		       	<label  class = "set" name="paybill"  id="paybill" ondblclick="callShowDiv(this);"><%=label.get("paybill")%></label> :
		     </td>
		     <td  colspan="1" height="20" class="formtext" width="83%">
		     <s:textarea name="paybillname" cols="120" rows="1" readonly="true" />
		        
		         <s:hidden name="paybillid" />
		      </td>   
		      <td width="2%">   
			  	<img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callDropdown('paraFrm_paybillname',350,250,'MonthlyArrearsReport_f9Paybill.action',event,'false','no','right')">		              
		     </td>
		  </tr>
		  
		   <tr>
		    <td class="formtext" colspan="1" height="20" class="formtext" width="15%">
		      	<label  class = "set" name="monthly.grade"  id="monthly.grade" ondblclick="callShowDiv(this);"><%=label.get("monthly.grade")%></label> :
		    </td>
		    <td  colspan="1" height="20" class="formtext" width="83%">
		     <s:textarea name="gradeName" cols="120" rows="1" readonly="true" />
		        
		        <s:hidden name="gradeId" />
		    </td>    
		     <td width="2%">   
				<img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callDropdown('paraFrm_gradeName',350,250,'MonthlyArrearsReport_f9Grade.action',event,'false','no','right')">
		    </td>
	 
		  </tr>
		   <tr>
		    <td class="formtext" colspan="1" height="20" class="formtext" width="15%">
		      	<label  class = "set" name="costcenter"  id="costcenter" ondblclick="callShowDiv(this);"><%=label.get("costcenter")%></label> :
		    </td>
		    <td  colspan="1" height="20" class="formtext" width="83%">
		     <s:textarea name="costcentername" cols="120" rows="1" readonly="true" />
		        
		        <s:hidden name="costcenterid" />
		    </td>    
		     <td width="2%">   
				<img
					src="../pages/images/recruitment/search2.gif" height="16"
					align="absmiddle" width="17" theme="simple"
					onclick="javascript:callDropdown('paraFrm_gradeName',350,250,'MonthlyArrearsReport_f9Costcenter.action',event,'false','no','right')">
		    </td>
	 
		  </tr>
						
							</table>
							</td>
						</tr>
						<tr>
							<td colspan="5">
							<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
								<tr>
									<td colspan="1" width="20%"><label  class = "set"  id="arrear.type" name="arrear.type" ondblclick="callShowDiv(this);"><%=label.get("arrear.type")%></label> <font color="red">*</font>
									:</td>
									<td colspan="3"><!-- <s:select theme="simple" name="monArrReport.arrearType"
										cssStyle="width:152"
										list="#{'0':'Select --','M':'Monthly','P':'Promotion'}" />-->
										<s:hidden name="monArrReport.arrearType" />
										<s:radio
										name="selectarrearType" 
										list="#{'M':'Monthly','P':'Promotion'}"
										onclick="chkRadio();" theme="simple" value="'P'" />
									</td>
								</tr>
								<tr>
									<s:hidden name="reportType" ></s:hidden>
				    				<s:hidden name="reportAction" value='MonthlyArrearsReport_report.action'/>
									<!-- <td colspan="1" width="20%"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> <font color="red">*</font>:</td>
									<td colspan="1" width="30%"><s:select theme="simple"
										name="monArrReport.report" cssStyle="width:152"
										list="#{'Pdf':'Pdf','Xls':'Xls'}" /></td> -->
								
									<td colspan="1" width="20%"><label id="cons.arrears" name="cons.arrears" ondblclick="callShowDiv(this);"><%=label.get("cons.arrears")%></label> :</td>
									<td colspan="1" width="30%"><input type="checkbox"
										name="checkFlag" id="checkFlag" onclick="callCheck();" /></td>
									<td></td>
									<td></td>
									<!--<td colspan="1" width="20%"><label  class = "set"  id="onhold" name="onhold" ondblclick="callShowDiv(this);"><%=label.get("onhold")%></label> :</td>
									<td colspan="1" width="30%"><s:select theme="simple"
										name="onHold" cssStyle="width:152"
										list="#{'A':'All','N':'No','Y':'Yes'}" />
										<input type="checkbox"
										name="onHold" id="onHold" onclick="callShowhold();" />
									</td>-->
								</tr>
							</table>
							</td>
						</tr>

						<tr>
							<td colspan="5">
							<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg" >
							<tr>
									<td class="text_head" colspan="6" ><strong
										class="forminnerhead">Select any/all of the following options to add in the report:</strong></td>
						</tr>
						<tr>
							<td width="15%" align="right"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td><td width="2%"><input type="checkbox" name="reportdivision"/></td>
							<td width="15%" align="right"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td><td width="2%"><input type="checkbox" name="reportbranch"/></td>
							<td width="15%" align="right"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td><td width="2%"><input type="checkbox" name="reportdept"/></td>
							<td width="15%" align="right"><label  class = "set"  id="micrCode" name="micrCode" ondblclick="callShowDiv(this);"><%=label.get("micrCode")%></label></td><td width="2%"><input type="checkbox" name="reportmicrcode"/></td>
							
						</tr>
						
						<tr>
							<td width="15%" align="right"><label  class = "set" name="monthly.grade"  id="monthly.grade" ondblclick="callShowDiv(this);"><%=label.get("monthly.grade")%></label></td>
							<td width="2%"><input type="checkbox" name="reportgrade"/></td>
							<td width="15%" align="right"><label  class = "set" name="accountNo"  id="accountNo" ondblclick="callShowDiv(this);"><%=label.get("accountNo")%></label></td>
							<td width="2%"><input type="checkbox" name="reportaccno"/></td>
							<td width="15%" align="right"><label  class = "set" name="bank"  id="bank" ondblclick="callShowDiv(this);"><%=label.get("bank")%></label></td>
							<td width="2%"><input type="checkbox" name="reportbank"/></td>
							<td width="15%" align="right"><label  class = "set" name="employeeType"  id="employeeType" ondblclick="callShowDiv(this);"><%=label.get("employeeType")%></label></td>
							<td width="2%"><input type="checkbox" name="reportempType"/></td>							
						</tr>
						
							</table>
							</td>
					</tr>
		  

						



						
						
						<!-- <tr>
							<td colspan="1" width="20%"><label  class = "set"  id="branch.wise" name="branch.wise" ondblclick="callShowDiv(this);"><%=label.get("branch.wise")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkBrn" id="checkBrn" onclick="callCheck('Brn');" /></td>
							<td colspan="1" width="20%"><label  class = "set"  id="dept.wise" name="dept.wise" ondblclick="callShowDiv(this);"><%=label.get("dept.wise")%></label> :</td>
							<td colspan="1" width="30%"><input type="checkbox"
								name="checkDept" id="checkDept" onclick="callCheck('Dept');" /></td>
						</tr> -->
						


						<tr>
							<td colspan="3">
							<CENTER></CENTER>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
		<td >
	<div id='bottomButtonTable'>
	
	</div>
		</td>
	</tr>
		<tr>
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>

	</table>
</s:form>

<SCRIPT LANGUAGE="JavaScript">

var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);

</SCRIPT>

<script>

function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
		//alert(1);
		document.getElementById('paraFrm_reportType').value=type;
			//callDropdown(obj.name,200,250,'ReimbursementBalanceReport_f9reportType.action','false');
			document.getElementById('paraFrm').action='MonthlyArrearsReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		}	
	}

function chkRadio(){
	var arreastypeval = "M"; 
	var ele = document.getElementsByName("selectarrearType");
	for(i=0; i < ele.length; i++ ){
		if(ele[i].checked)
			arreastypeval = ele[i].value;
	}
	document.getElementById("paraFrm_monArrReport_arrearType").value = arreastypeval;
} 
	chkRadio();
	
	if(document.getElementById("paraFrm_monArrReport_arrearType").value!='P'){
	setCurrentYear('paraFrm_monArrReport_year');
	}
//setCurrentYear('paraFrm_monArrReport_year');
function callReset(){
	document.getElementById('paraFrm').action="MonthlyArrearsReport_reset.action";	
	document.getElementById('paraFrm').submit();	
}


function callBackPromotion(){
	var hValue=document.getElementById('paraFrm_promotionReportCode').value;
	document.getElementById('paraFrm').action=hValue;	
	document.getElementById('paraFrm').submit();	
}

function validateFields() {	
	var month  =document.getElementById("paraFrm_monArrReport_month").value;
	var year   =document.getElementById("paraFrm_monArrReport_year").value;
	//var rep     =document.getElementById("paraFrm_monArrReport_report").value;
	var divNm   =document.getElementById("paraFrm_monArrReport_divName").value;
	var arrearType   =document.getElementById("paraFrm_monArrReport_arrearType").value;

	if(month =='0'){
		alert("Please Select "+document.getElementById('month').innerHTML.toLowerCase());
		return false;
	}
	if(year ==''){
		alert("Please Enter "+document.getElementById('year').innerHTML.toLowerCase());
		return false;
	}
	if( divNm ==''){
		alert("Please Enter "+document.getElementById('division').innerHTML.toLowerCase());
		return false;
	}
	if(arrearType =='0'){
		alert("Please Select "+document.getElementById('arrear.type').innerHTML.toLowerCase());
		return false;
	}
	
	//if(rep=='0'){
	//	alert("Please select the "+document.getElementById('report.type').innerHTML.toLowerCase());
	//	return false;
	//}
	//alert(document.getElementById("paraFrm_monArrReport_deptCode").value);
	 
	 /*document.getElementById('paraFrm').target="_blank";	
		document.getElementById('paraFrm').action="MonthlyArrearsReport_report.action";	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";*/
			
	return true;
} 

function callShowhold()
{
	 var checkDefault = document.getElementById('onHold').checked;
	 if(checkDefault){
 			document.getElementById('onHold').value="Y";
 	
 	}
 	else
 	{
 			document.getElementById('onHold').value="N";
 	}
}
function callCheck(){
 var checkDefault = document.getElementById('checkFlag').checked; 
 if(checkDefault){
 			document.getElementById('checkFlag').value="Y";
 	
 	}
 	else
 	{
 		document.getElementById('checkFlag').value="Y";
 	}
}
/*function callCheck(type){
 var checkDefault = document.getElementById('checkFlag').checked; 	
if(type=="Brn"){
 var chk = document.getElementById('checkBrn').checked;

 if(chk){
 			document.getElementById('checkBrn').value="Y";
 			document.getElementById('checkDept').value="N";
 			document.getElementById('checkDept').checked =false;
 			document.getElementById('paraFrm_monArrReport_branchName').value="";
 			document.getElementById('paraFrm_monArrReport_branchCode').value="";
 			
 	}else{
 			document.getElementById('checkBrn').value="N";
 	}
 }else{
 
 var chk = document.getElementById('checkDept').checked;
 if(chk){
 			document.getElementById('checkBrn').value="N";
 			document.getElementById('checkDept').value="Y";
 			document.getElementById('checkBrn').checked =false;
 			document.getElementById('paraFrm_monArrReport_deptName').value="";
 			document.getElementById('paraFrm_monArrReport_deptCode').value="";
 	}else{
 			document.getElementById('checkDept').value="N";
 	}
 }
 //alert(checkDefault);
 if(checkDefault){
 			document.getElementById('checkFlag').value="Y";
 	
 	}
 	//alert(document.getElementById('checkFlag').value);
 }*/
 
 
 function callReport(type)
	{
		//alert("type : "+type);
		document.getElementById('paraFrm_reportType').value=type;
		try
		{	
		if(!validateFields()){
			return false;
		} else {
			//document.getElementById('paraFrm').action="ReimbursementReport_getReport.action";
	 		//document.getElementById('paraFrm').submit();
	 		callReportCommon(type);
			return true;
		}	
		
	 	}
	 	catch(e)
	 	{
	 		alert("Exception occurred in reportFun() ======> "+e);
	 		return false;
	 	}	
		
	}	
 
</script>

