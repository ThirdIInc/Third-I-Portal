<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="TdsTaxChallan" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">TDS TAX Challan</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					<td><input type="button" class="token"
						onclick="check('TdsTaxChallan_report.action')" value=" Report" /> <input
						type="button" class="reset" onclick="resetField()"
						value="    Reset" /></td>

					<td>
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>

						<tr>

							<td colspan="1" width="20%"><label  class = "set"  id="frmMonth" name="frmMonth" ondblclick="callShowDiv(this);"><%=label.get("frmMonth")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="fromMonth" cssStyle="width:152"
								list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>

							<td colspan="1" width="20%"><label  class = "set"  id="frmYear" name="frmYear" ondblclick="callShowDiv(this);"><%=label.get("frmYear")%></label><font color="red">*</font>:</td>
							<td><s:textfield name="fromYear"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25" /></td>
						</tr>

						<tr>

							<td colspan="1" width="20%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:hidden name="divId" /> <s:textfield
								name="divName" theme="simple" readonly="true" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TdsTaxChallan_f9div.action');">
							</td>
							<td colspan="1" width="20%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden name="brnId" /> <s:textfield
								name="brnName" theme="simple" readonly="true" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TdsTaxChallan_f9brn.action');">

							</td>
														
						</tr>



						<tr>

							<td colspan="1" width="20%"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td><s:hidden name="deptId" /> <s:textfield name="deptName"
								theme="simple" readonly="true" maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TdsTaxChallan_f9dept.action');">
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden name="typeId" /> <s:textfield
								name="typeName" theme="simple" readonly="true" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'TdsTaxChallan_f9type.action');">

							</td>
							
						</tr>

						<tr>
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
			<td colspan="3"><img src="../pages/images/recruitment/space.gif"
				width="5" height="4" /></td>
		</tr>

	</table>
</s:form>
<script>
 
 function check(name){
   var fy=document.getElementById("paraFrm_fromYear").value;
   
 	if(document.getElementById("paraFrm_fromMonth").value=='0'){
 		alert("Please select the "+document.getElementById('frmMonth').innerHTML.toLowerCase());
 		return false;
 		}
 	
 		
 		
 	if(document.getElementById("paraFrm_fromYear").value==""){
 		alert("Please enter the "+document.getElementById('frmYear').innerHTML.toLowerCase());
 		return false;
 		}
 		
 	if(document.getElementById("paraFrm_divName").value==""){
 		alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
 		return false;
 		}	
 		
 	
 	document.getElementById('paraFrm').target="_blank";
	document.getElementById('paraFrm').action=name;	
	document.getElementById('paraFrm').submit();	
	document.getElementById('paraFrm').target="main";	
 
 }
 

	
	function resetField()
	{
	 document.getElementById("paraFrm_fromMonth").value='0';
	 
	 document.getElementById("paraFrm_typeName").value="";
	 document.getElementById("paraFrm_typeId").value="";
	 document.getElementById("paraFrm_divId").value="";
	 document.getElementById("paraFrm_divName").value="";
	 document.getElementById("paraFrm_brnId").value="";
	 document.getElementById("paraFrm_brnName").value="";
	 document.getElementById("paraFrm_deptName").value="";
	 document.getElementById("paraFrm_deptId").value="";
	 document.getElementById("paraFrm_typeName").value="";
	 document.getElementById("paraFrm_fromYear").value="";
	 
	}

</script>