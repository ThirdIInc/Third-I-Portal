<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="java.util.HashMap"%>


<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="AssetReturn" validate="true" id="paraFrm" theme="simple">
	<s:hidden theme="simple" name="empId" />
	<s:hidden theme="simple" name="vendorId" />
	<s:hidden theme="simple" name="empTokenNo" />
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Asset	Return </strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2">
				<tr>
					<td width="78%"></td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td width="20%" colspan="1"><label class="set" id="employee1"
						name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					<font color="red">*</font>:</td>
					<td width="30%" colspan="1" align="left"><s:textfield
						size="30" name="empName" readonly="true"></s:textfield> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="return callEmp();">
					</td>
					<td width="20%" colspan="1"><label class="set" id="vendor1"
						name="vendor" ondblclick="callShowDiv(this);"><%=label.get("assetVendorName")%></label>
					<font color="red">*</font>:</td>
					<td width="30%" colspan="1" align="left"><s:textfield
						size="30" name="vendorName" readonly="true"></s:textfield> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="17" theme="simple"
						onclick="return callVendor();">
					</td>
				</tr>
				<tr>
				<td width="20%" colspan="1"><label class="set"
						id="assetStatus" name="assetStatus"
						ondblclick="callShowDiv(this);"><%=label.get("assetStatus")%></label>
					:<s:hidden name="warehouseCode" /></td>
					<td width="30%" colspan="1"><s:select name="status"
						cssStyle="width:100" theme="simple"
						list="#{'A':'All','Y':'Returned','N':'Not returned','L':'Lost','D':'Damaged'}" /></td>
					
				</tr>
				<tr>
					<td colspan="1"></td>
					<td align="left"><s:submit action="AssetReturn_show"
						cssClass="token" value="Show" onclick="return callSearch();" /> <s:submit
						action="AssetReturn_save" cssClass="save" value=" Save"
						onclick="return callSave(this);" /> <s:submit cssClass="reset"
						action="AssetReturn_reset" value=' Reset' /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>

			<td colspan="6" width="100%">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="1" class="formth" width="10%"><label class="set"
						id="assetSrNo" name="assetSrNo" ondblclick="callShowDiv(this);"><%=label.get("assetSrNo")%></label></td>
					<td colspan="1" class="formth" width="20%"><label class="set"
						id="assetCat" name="assetCat" ondblclick="callShowDiv(this);"><%=label.get("assetCat")%></label>
					</td>
					<td colspan="1" class="formth" width="20%"><label class="set"
						id="assetSubType" name="assetSubType"
						ondblclick="callShowDiv(this);"><%=label.get("assetSubType")%></label></td>
					<td colspan="1" class="formth" width="20%"><label class="set"
						id="assetInv" name="assetInv" ondblclick="callShowDiv(this);"><%=label.get("assetInv")%></label></td>
					<td colspan="1" class="formth" width="20%"><label class="set"
						id="assetRetDate" name="assetRetDate"
						ondblclick="callShowDiv(this);"><%=label.get("assetRetDate")%></label></td>
					<td colspan="1" class="formth" width="10%"><label class="set"
						id="assetIsRet" name="assetIsRet" ondblclick="callShowDiv(this);"><%=label.get("assetIsRet")%></label></td>
				</tr>

				<%
					int i = 0;
					int k = 0;
				%>
				<%!int kk = 0;%>
				<%
				int cnt = 0, z = 0;
				%>
				<s:iterator value="retList">
					<%
					++i;
					%>
					<tr>
						<td width="10%" align="center" class="td_bottom_border"><s:property
							value="rowNum" /><s:hidden name="rowNum" /> <s:hidden
							name="appCode" id='<%="appCode"+cnt%>' />
							
							<s:hidden name="hiddenAutoCodeItt" />
							</td>

						<td align="center" class="td_bottom_border"><s:hidden
							name="category" /><s:property value="category" /></td>

						<td align="center" class="td_bottom_border"><s:hidden
							name="subType" /><s:property value="subType" /></td>

						<td align="center" class="td_bottom_border"><s:hidden
							name="inventory" id='<%="inventory"+cnt%>' /><s:property
							value="inventory" /></td>

						<td align="center" class="td_bottom_border"><s:textfield
							name="hDate" id='<%="h"+cnt%>' size="8"
							onkeypress="return numbersWithHiphen();" maxlength="10" /> <a
							href="javascript:NewCal('<%="h"+cnt%>','DDMMYYYY');"> <img
							src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16"> </a></td>
						

						
						<td align="center" class="td_bottom_border"><s:if test="chk"><s:hidden name='returnStatus' />
							<s:select name='hiddenReturnStatus' disabled="true" id='<%="returnStatus"+cnt%>' list="#{'N':'Not-Returned','Y':'Returned','L':'Lost','D':'Damaged'}"></s:select>
								</s:if><s:else><s:select  name='returnStatus' id='<%="returnStatus"+cnt%>' onchange="return setReturnStatus(this);"  list="#{'N':'Not-Returned','Y':'Returned','L':'Lost','D':'Damaged'}"></s:select></s:else>
							
						</td>
								
						 <input type="hidden" name="disb" id="<%=i%>"
							value='<s:property	value="disb" />' /> <s:hidden
							name="masterCode" />
							
							 <s:hidden
							name="employeeOrVendorType" />
							
							 <s:hidden
							name="assetTypeCodeItt" />
							
							
							 <s:hidden
							name="assetSubTypeCodeItt" />
							
							 <s:hidden
							name="employeeOrVendorCode" />
							
					</tr>
					<%
					++cnt;
					%>
				</s:iterator>
				<%
				z = cnt;
				%>
				<%
				kk = i;
				%>
			</table>
			<input type="hidden" name="count" id="count" value="<%=cnt%>" /> <s:hidden
				name="showFlag" /></td>
		</tr>
	</table>
</s:form>



<script type="text/javascript">
function callEmp()
	{
			document.getElementById("paraFrm_vendorName").value="";
			document.getElementById("paraFrm_vendorId").value="0";
		callsF9(500,325,'AssetReturn_f9Selectemp.action'); 
	}
	function callVendor()
	{
			document.getElementById("paraFrm_empId").value="0";
			document.getElementById("paraFrm_empTokenNo").value="";
			document.getElementById("paraFrm_empName").value="";
		callsF9(500,325,'AssetReturn_f9Selectvendor.action');
	}

  	function callChk(id){

 if(document.getElementById('chk'+id).checked==true)
 {
  document.getElementById(id).value='Y';
 }else  
  document.getElementById(id).value='N';
 } 




function chk()
{

var tot='<%=kk%>';
for(var i=1;i<=tot;i++){

 if(document.getElementById(i).value=='Y'){
 return true;
 }
}
return false;
}	


function callSearch()
{
	var labEmpName1=document.getElementById('employee1').innerHTML.toLowerCase();	
	var name=document.getElementById('paraFrm_empId').value
	
	var labVendorName1=document.getElementById('vendor1').innerHTML.toLowerCase();	
	var vendname=document.getElementById('paraFrm_vendorId').value
		if(name=="" || vendname=="")
		{
			alert("Please select "+labEmpName1+" or "+labVendorName1);
			return false;
		}
	return true;
}


function  callSave(obj)
{
	 
	var count =document.getElementById("count").value;
	
	var labEmpName1=document.getElementById('employee1').innerHTML.toLowerCase();	
	var name=document.getElementById('paraFrm_empId').value
	
	var labVendorName1=document.getElementById('vendor1').innerHTML.toLowerCase();	
	var vendname=document.getElementById('paraFrm_vendorId').value
		if(name=="" || vendname=="")
		{
			alert("Please select "+labEmpName1+" or "+labVendorName1);
			return false;
		}
	 
		if(document.getElementById("paraFrm_showFlag").value=="false")
			{
				alert("Please click on Show button.");
				return false;
			}
		if(count=="" || count=="0"){
				alert("No record to save.");
				return false;
			}
		for (var count1=0;count1<count;count1++){
			if(document.getElementById("returnStatus"+count1).value !='N')
			{
				if(document.getElementById("h"+count1).value=="")	{
					alert("Please enter "+document.getElementById('assetRetDate').innerHTML);
					document.getElementById("h"+count1).focus();
					return false;
				}
				else
				{
					//var checkDate="h"+count1;
					if(!dateCheckLessThanToday("h"+count1,"assetRetDate"))
					{
						document.getElementById("h"+count1).focus();
						return false;
					}
				}
			}
			if(document.getElementById("h"+count1).value!="")
			{
				var checkDate="h"+count1;
					if(!dateCheckLessThanToday("h"+count1,"assetRetDate"))
					{
						document.getElementById("h"+count1).focus();
						return false;
					}
				if(document.getElementById("returnStatus"+count1).value=="N"){
					alert("Please select "+document.getElementById('assetIsRet').innerHTML);
					document.getElementById("returnStatus"+count1).focus();
					return false;
				}
			}

		}
		obj.disabled = true;
		document.getElementById("paraFrm").action="AssetReturn_save.action";
		document.getElementById("paraFrm").submit();
}



</script>


<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>

