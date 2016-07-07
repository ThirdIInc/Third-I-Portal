<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="PurchaseMisReport" method="post" id="paraFrm" >
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" class="formbg">
       <tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Purchase MIS Report
					Assignment </strong></td>
				</tr>
			</table>
			</td>
		</tr>
        <tr>
          <td colspan="3">
          <table width="100%" border="0" cellpadding="2" cellspacing="2">
				<tr>
				<td width="78%" colspan="3"><s:if test="%{viewFlag}"><input type="button" 
					class="token" onclick="return callReportMis();"	value=" Show Report" /></s:if>
				<s:submit cssClass="reset" action="PurchaseMisReport_reset"
					theme="simple" value="    Reset"  />
				</td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
						<tr>
							<td width="25%"   class="formtext"><label  class = "set"  id="employee"  name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td><s:hidden name="empCode" value="%{empCode}" />
							<td width="75%" colspan="3" ><s:textfield name="empToken"
								size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
								name="ename" size="50" value="%{ename}" theme="simple"
								readonly="true" /> 
								<img src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18" 
										onclick="javascript:callsF9(500,325,'PurchaseMisReport_f9actionEname.action');">
									</td>
						</tr>
							<tr>
							<td width="25%" class="formtext"> <label  class = "set"  id="frmdate"  name="frmdate" ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label>:</td>
							<td width="25%" nowrap="nowrap"><s:textfield name="frmDate" size="25" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" />
								<s:a href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" 
								height="16" align="absmiddle" width="16"> </s:a>
							</td>
							<td width="25%" class="formtext"> <label  class = "set"  id="todate"  name="todate" ondblclick="callShowDiv(this);"><%=label.get("todate")%></label>:</td>
							<td width="25%" nowrap="nowrap"><s:textfield name="toDate" size="25" onkeypress="return numbersWithHiphen();" theme="simple" maxlength="10" />
								<s:a href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');"><img src="../pages/images/recruitment/Date.gif"  class="iconImage" 
								height="16" align="absmiddle" width="16"> </s:a>
							</td>
						</tr>
							
							<tr>
								
							<td width="25%"  class="formtext"><label  class = "set"  id="designation"  name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :<s:hidden name="desigCode" /></td>
							<td width="25%" nowrap="nowrap"><s:textfield name="desigName" size="25" theme="simple" readonly="true" /> 
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
								onclick="javascript:callsF9(500,325,'PurchaseMisReport_f9actionDesig.action');"></td>
								
							
					
							<td width="25%"  class="formtext"><label  class = "set"  id="department"  name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :<s:hidden name="deptCode" /></td>
							<td width="25%" nowrap="nowrap"><s:textfield name="deptName" size="25" theme="simple" readonly="true" /> 
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
								onclick="javascript:callsF9(500,325,'PurchaseMisReport_f9actionDept.action');"></td>

						</tr>
						
						
							<tr>
						
							<td width="25%"  class="formtext"><label  class = "set"  id="branch"  name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :<s:hidden name="branchCode" /></td>
							<td width="25%" nowrap="nowrap"><s:textfield name="branchName" size="25" theme="simple" readonly="true" /> 
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
							onclick="javascript:callsF9(500,325,'PurchaseMisReport_f9actionBranch.action');"></td>
						
						
							<td width="25%"  class="formtext"><label  class = "set"  id="division"  name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<s:hidden name="divisioncode" /></td>
							<td width="25%" nowrap="nowrap"><s:textfield name="divisionName" size="25" theme="simple" readonly="true" /> 
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
							onclick="javascript:callsF9(500,325,'PurchaseMisReport_f9actionDiv.action');"></td>
						</tr>
						
							
							
							
								<tr>
						
							<td width="25%"  class="formtext"><label  class = "set"  id="ass.cat"  name="ass.cat" ondblclick="callShowDiv(this);"><%=label.get("ass.cat")%></label> :<s:hidden name="categoryCode" /></td>
							<td width="25%" nowrap="nowrap"><s:textfield name="categoryName" size="25" theme="simple" readonly="true" /> 
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
							onclick="javascript:callsF9(500,325,'PurchaseMisReport_f9actionCate.action');"></td>
							
							<td width="25%"  class="formtext"><label  class = "set"  id="subtype"  name="subtype" ondblclick="callShowDiv(this);"><%=label.get("subtype")%></label> :<s:hidden name="subtypeCode" /></td>
							<td width="25%" nowrap="nowrap"><s:textfield name="subtypeName" size="25" theme="simple" readonly="true" /> 
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
								onclick="javascript:callsF9(500,325,'PurchaseMisReport_f9actionSubtype.action');"></td>

						</tr>
							
							
							
							
							<tr>
							<td width="25%"  class="formtext"><label  class = "venName"  id="venName"  name="venName" ondblclick="callShowDiv(this);"><%=label.get("venName")%></label> :<s:hidden name="vendorCode" /></td>
							<td width="25%" nowrap="nowrap"><s:textfield name="vendorName" size="25" theme="simple" readonly="true" /> 
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="17" 
								onclick="javascript:callsF9(500,325,'PurchaseMisReport_f9actionVen.action');"></td>
							
							
							
							<td width="25%" class="formtext"><label  class = "set"  id="stat"  name="stat" ondblclick="callShowDiv(this);"><%=label.get("stat")%></label>:</td>
							<td width="25%" nowrap="nowrap"><s:select theme="simple" name="status"
								 cssStyle="width:130"
								list="#{'A':'Approved','P':'Pending','R':'Rejected','C':'Canceled'}" /></td>

							</tr>
							<tr>
							<td width="25%" class="formtext"><label  class = "set"  id="report.type"  name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>:</td>
							<td width="25%" nowrap="nowrap">
							<s:select name="type" headerKey="Pdf" headerValue="Pdf" theme="simple"
							list="#{'Xls':'Xls', 'Txt':'Text'}" />
								<s:hidden name="today"/>
							</td>
							<td width="25%"></td>
							<td width="25%"></td>
						</tr>
					</table>
					</td>
				</tr>
				</table>
				
			
</s:form>
<script type="text/javascript">

function callReportMis(){
 	/*
 	var emp=document.getElementById("paraFrm_ename").value;
  	var frmDate=document.getElementById("paraFrm_frmDate").value;
  	var toDate=document.getElementById("paraFrm_toDate").value;
  	var status=document.getElementById("paraFrm_status").value;
  	var today=document.getElementById("paraFrm_today").value;
  	
  	var vendorCode=document.getElementById("paraFrm_vendorCode").value;
  	var subtypeCode=document.getElementById("paraFrm_subtypeCode").value;
  	var categoryCode=document.getElementById("paraFrm_categoryCode").value;
  	var desigCode=document.getElementById("paraFrm_desigCode").value;
  	var branchCode=document.getElementById("paraFrm_branchCode").value;
  	
  	var deptCode=document.getElementById("paraFrm_deptCode").value;
  	var divisioncode=document.getElementById("paraFrm_divisioncode").value;
  	
  	
  	strDate1 = today.split("-"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	strDate2 = frmDate.split("-"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	
  	
  	if((emp=="")&& (frmDate=="")&&(status=="")&& (emp=="")&& (toDate=="")&& (vendorCode=="") && (subtypeCode=="")&& (categoryCode=="")&& (desigCode=="")&& (branchCode=="")&& (deptCode=="")&& (divisioncode=="") ){
  	alert("Please select any/all search criteria to generate MIS report !");
  	return false;
  	}else if(frmDate!=""){
  	if(!validateDate("paraFrm_frmDate","From Date")){
		return false;
	}
  	}else if(toDate!=""){
  	if(!validateDate("paraFrm_toDate","To Date")){
		return false;
	}
  	}
  	
  	 if ((frmDate!="") && (toDate!="")) {
  	if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate', 'From Date', 'To Date'))
	{
	return false;
	}
	
	}
	
  	
  	
  	else if((frmDate!="") && (toDate=="")){
	if(endtime > starttime) 
	{ 
		alert("From Date should be less than Today's Date !");
		document.getElementById("paraFrm_frmDate").focus();
		return false;
	}
	}
	*/
	
		if(!document.getElementById('paraFrm_frmDate').value=="")
		{
					if(!validateDate('paraFrm_frmDate', 'frmdate'))
  			return false;
		}
		if(!document.getElementById('paraFrm_toDate').value=="")
		{
			if(!validateDate('paraFrm_toDate', 'todate'))
  				return false;
		}
  	 
  	
  	callReport('PurchaseMisReport_report.action');
   	
	
}
  	function autoDate () {
var tDay = new Date();
var tMonth = tDay.getMonth()+1;
var tDate = tDay.getDate();
if ( tMonth < 10) tMonth = "0"+tMonth;
if ( tDate < 10) tDate = "0"+tDate;

document.getElementById("paraFrm_today").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();

}
autoDate();
</script>
