
<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ConfigAuthorization" validate="true" id="paraFrm"
	theme="simple">
	<script type="text/javascript">
var listArray = new Array();
</script>
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td colspan="7" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt" colspan="2"><strong
						class="text_head">Misc Salary Earnings Upload
					Authorization </strong></td>
					<td width="3%" valign="top" class="otxt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" colspan="7">
			<table height="18" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td class="btn-middlebg">
					<div id="tabnav" style="vertical-align: bottom">
					<ul>
						<li><a href="javascript:callDivLoad('ID');" id="ID_L">
						<div align="center"><span>Module Authorization </span></div>
						</a></li>
						<li><a href="javascript:callDivLoad('HR');" id="HR_L">
						<div align="center"><span>Misc Salary Earnings Upload
						Authorization</span></div>
						</a></li>
					</ul>
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Misc Salary Earnings upload
					Authorization </strong></td>
				</tr>
				<tr>
					<s:hidden name="miscSalaryAuthId" />
					<td width="25%"><label class="set"
						name="configure.authorization" id="configure.authorization"
						ondblclick="callShowDiv(this);"><%=label.get("configure.authorization")%></label><font
						color="red"></font> :</td>

					<td width="25%"><s:select  cssStyle="width:135" name="configAuth" 
						list="#{'M':'Manager Authorization'}" onchange="return callShowManager();" /></td>

					<td width="25%"></td>
					<td width="25%"></td>
				</tr>
				<tr>
					<td class="formtext"><label name="division" id="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font
						color="red">*</font></td>
					<td nowrap="nowrap"><s:hidden name="divCode" /> <s:textfield
						name="divName" size="30" readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						class="iconImage" height="15"
						onclick="javascript:callsF9(500,325,'ConfigAuthorization_f9divaction.action');" />
					</td>
				</tr>
				<!--<tr>
					<td class="formtext"><label name="department" id="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:<font
						color="red"></font></td>
					<td nowrap="nowrap"><s:hidden name="deptCode" /> <s:textfield
						name="deptName" size="30" maxlength="30" readonly="true" /> <img
						class="iconImage" src="../pages/images/recruitment/search2.gif"
						height="16" width="15"
						onclick="javascript:callsF9(500,325,'ConfigAuthorization_f9deptaction.action');" /></td>
				</tr>
				-->
				<tr>
							<td class="formtext"><label  name="branch" id="branch"ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :<font color="red"></font></td>
							<td nowrap="nowrap"><s:hidden name="centerCode" />
							<s:textfield name="centerName" size="30" maxlength="30"
								readonly="true" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" width="15"
								onclick="javascript:callsF9(500,325,'ConfigAuthorization_f9centeraction.action');" />
							</td>
				</tr>
				
				
				<tr>
					<td class="formtext"><label name="pay.bill" id="pay.bill"
						ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
					:</td>
					<td nowrap="nowrap"><s:hidden name="payBillId" /> <s:textfield
						name="payBillName" size="30" maxlength="30" readonly="true" /> <img
						class="iconImage" src="../pages/images/recruitment/search2.gif"
						width="16" height="16"
						onclick="javascript:callsF9(500,325,'ConfigAuthorization_f9payBillaction.action');" /></td>
					<td></td>
				</tr>
				<tr id="managerNameTable">
					<td width="20%" class="formtext"><label
						id="manager.for.authorization" name="manager.for.authorization"
						ondblclick="callShowDiv(this);"><%=label.get("manager.for.authorization")%></label>
					:<font color="red" id='ctrlHide'>*</font></td>
					<td width="60%" colspan="3"><s:textfield name="managerToken"
						size="25" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:textfield
						name="managerName" size="60" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="managerCode" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id="ctrlHide"
						onclick="javascript:callsF9(500,325,'ConfigAuthorization_f9ManagerAuth.action');">
					</td>
				</tr>
				<tr>
					<td colspan="1" width="20%"><label id="credit.head"
						name="credit.head" ondblclick="callShowDiv(this);"><%=label.get("credit.head")%></label>
					:<font color="red"></font></td>
					<td width="30%"><s:hidden name="creditCode" /><s:textfield
						name="creditName"  theme="simple" readonly="true" maxlength="50"
						size="30" /> <input type="button" class="token" value=" >> "
						onclick="callCreditComponent();" /></td>
				</tr>
				
				<tr>
					<td colspan="1" width="20%"><label id="debit.head"
						name="debit.head" ondblclick="callShowDiv(this);"><%=label.get("debit.head")%></label>
					:<font color="red"></font></td>
					<td width="30%"><s:hidden name="debitCode" /><s:textfield
						name="debitName"  theme="simple" readonly="true" maxlength="50"
						size="30" /> <input type="button" class="token" value=" >> "
						onclick="callComponent();" /></td>
				</tr>
				
				<tr>
					<td colspan="4" align="center">
						<s:if test="insertRecordFlag">
						
						<input type="button" value=" Add to List " class="add"
							onclick="return addConfigAuth();" />
							
						
						</s:if>
						<s:if test="updateRecordFlag">
						<s:submit
							action="ConfigAuthorization_addConfigAuth" value=" Update"
							cssClass="token" title="Update " onclick="return addConfigAuth();" />
						</s:if>
					
						<s:submit action="ConfigAuthorization_reset" value=" Reset"
							cssClass="reset" title="Reset " />
					</td>
				</tr>
				<tr>
					<td width="100%" colspan="6">
					<table width="100%" class="formbg">
						<tr class="td_bottom_border">
							<td colspan="1" width="5%" class="formth">Sr.No.</td>
							<td colspan="1" class="formth" width="15%">Configure Authorization </td>
							<td colspan="1" class="formth" width="15%">Division</td>
							<td colspan="1" class="formth" width="10%">Branch</td>
							<!--<td colspan="1" class="formth" width="20%">Department</td>
							--><td colspan="1" class="formth" width="10%">Paybill</td>
							<td colspan="1" class="formth" width="20%">Manager for
							Authorization</td>
							<td colspan="1" class="formth" width="15%">Credit Head</td>
							<td colspan="1" class="formth" width="15%">Debit Head</td>
							
							<td colspan="1" class="formth" width="10%">Edit | Delete</td>
						</tr>
						<s:if test="miscSalaryUploadListPage">
							<%
								int count6 = 0;
							%>
							<%!int tr = 0;%>
							<%
								int i = 0; 
							%>
							<s:iterator value="miscSalaryUploadList">
								<tr onmouseover="javascript:newRowColor(this);"
																onmouseout="javascript:oldRowColor(this);">
									<td colspan="1" width="5%" class="sortableTD"><%=++i%></td>
									<td colspan="1" width="10%" class="sortableTD">&nbsp;<s:property
										value="miscSalConfigAuth" /></td>
									
									<td colspan="1" width="15%" class="sortableTD">&nbsp;<s:hidden
										name="miscSalaryUploadHiddenId" /> <s:hidden
										name="managerEmpAuthId" />
										 <s:hidden
										name="miscSalDivId" /> <s:property value="miscSalDivName" /></td>

									<!--<td colspan="1" width="20%" class="sortableTD">&nbsp;<s:hidden
										name="miscSalDeptId" /> <s:property value="miscSalDeptName" /></td>

									-->
									<td colspan="1" width="10%" class="sortableTD">&nbsp;<s:hidden
										name="miscSalCenterCode" /> <s:property value="miscSalCenterName" /></td>
									
									<td colspan="1" width="10%" class="sortableTD">&nbsp;<s:hidden
										name="miscSalPayBillId" /> <s:property
										value="miscSalPayBillName" /></td>

									<td colspan="1" width="20%" class="sortableTD">&nbsp;<s:property
										value="managerEmpName" /></td>
<td colspan="1" width="15%" class="sortableTD">&nbsp;<s:hidden
										name="miscSalCreditCode" /> <s:property
										value="miscSalCreditHead" /></td>
									<td colspan="1" width="15%" class="sortableTD">&nbsp;<s:hidden
										name="miscSalComponentCode" /> <s:property
										value="miscSalComponentHead" /></td>
								

									<td width="10%" class="sortableTD" align="center" nowrap>
									<input type="button" class="rowEdit"
										onclick="callForEdit('<s:property value="miscSalaryUploadHiddenId"/>')" />
									| <input type="button" class="rowDelete"
										onclick="callForDelete('<s:property value="miscSalaryUploadHiddenId" />')" />
									</td>
								</tr>

							</s:iterator>
							<%
								tr = i;
							%>
						</s:if>

						<s:else>
							<td align="center" colspan="6" width="100%" nowrap="nowrap"><font
								color="red">There is no data to display</font></td>
						</s:else>
					</table>
					</td>
				</tr>
				<s:hidden name="paracode" />
			</table>
			</td>
		</tr>
		 <s:hidden name="hiddenfrmId" id="hiddenfrmId" />
		 <s:hidden name="hiddenCreditCodefrmId" id="hiddenCreditCodefrmId" />
		 
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script> 
onLoad();

function onLoad(){
	document.getElementById('HR_L').className='on';
	callShowManager();
}
function callDivLoad(id)
	{
		if(id == 'ID'){
			document.getElementById('paraFrm').action = "ConfigAuthorization_viewModuleAuth.action";
			document.getElementById('paraFrm').target = "main";
			document.getElementById('paraFrm').submit();
		} 
		document.getElementById('HR_L').className='on';
	}
	function callComponent()
{
try{
	var hCode=document.getElementById('paraFrm_debitCode').value;
	document.getElementById('hiddenfrmId').value = hCode; 
	//alert(hCode);
	window.open('','new','top=100,left=300,width=700,height=500,scrollbars=yes,status=no,resizable=no');
	document.getElementById("paraFrm").target="new";
	document.getElementById("paraFrm").action='ConfigAuthorization_getComponentHead.action';//?id='+id+'&hCode='+hCode+'&id1='+id1; 
	document.getElementById("paraFrm").submit();
	document.getElementById("paraFrm").target="main";
	}
	catch(e)
	{
	alert(e);
	}
}

	
	function callForEdit(id){
    try{
    
    //alert(id);
	
	   	document.getElementById("paraFrm").action="ConfigAuthorization_edit.action";
	  	document.getElementById('paraFrm_paracode').value=id;
	  	//document.getElementById('paraFrm_bankId').value=id;
	  	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   	}catch (e)
	{
	alert(e);
	}
	}
	
	function callForDelete(id){
		var conf=confirm("Are you sure to delete this record?");   
   		if(conf) {
   		  		  
	   	document.getElementById("paraFrm").action="ConfigAuthorization_delete.action";
	  	document.getElementById('paraFrm_paracode').value=id;
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
   }else {
   		return false;
   
   }
 }
 function callShowManager()
			{ 			
				 var configAuth = document.getElementById('paraFrm_configAuth').value;
				// alert(invType);
				if(configAuth=='M'){
				 document.getElementById('managerNameTable').style.display='';
				} else
				{
					 document.getElementById('managerNameTable').style.display='none';
				}
				
			}
			
	function callCreditComponent()
{
try{
	var hCreditCode=document.getElementById('paraFrm_creditCode').value;
	document.getElementById('hiddenCreditCodefrmId').value = hCreditCode; 
	//alert(hCode);
	window.open('','new','top=100,left=300,width=700,height=500,scrollbars=yes,status=no,resizable=no');
	document.getElementById("paraFrm").target="new";
	document.getElementById("paraFrm").action='ConfigAuthorization_getCreditHead.action';//?id='+id+'&hCode='+hCode+'&id1='+id1; 
	document.getElementById("paraFrm").submit();
	document.getElementById("paraFrm").target="main";
	}
	catch(e)
	{
	alert(e);
	}
}		
		
	function addConfigAuth(){

		 var divName = document.getElementById('paraFrm_divName').value;
   
		if(divName == '') {
			alert('Please select Division');
			document.getElementById('paraFrm_divName').focus();
			return false;
		}
		
		var mngName = document.getElementById('paraFrm_managerName').value;
   
		if(mngName == '') {
			alert('Please select Manager for Authorization.');
			document.getElementById('paraFrm_managerName').focus();
			return false;
		}
		
		
			document.getElementById('paraFrm').target = "_self";
  			document.getElementById('paraFrm').action = 'ConfigAuthorization_addConfigAuth.action';
			document.getElementById('paraFrm').submit();
		
			
	}		
			
</script>