<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="BirthdayMailSettings" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="100%" class="txt"><strong class="text_head">Birthday
					Mail Settings </strong> <s:hidden name="hiddRandomCode" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">


				<tr>
					<td colspan="4"><s:submit cssClass="save"
						action="BirthdayMailSettings_save" theme="simple" value=" Save"
						onclick="return callSave(); " /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td width="25%">
						<label name="enable.autobdaymails" id="enable.autobdaymails" 
						 ondblclick="callShowDiv(this);"><%=label.get("enable.autobdaymails")%></label>
						:
					</td>
					<td width="10%">
						<s:checkbox name="autoFlag" id="autoFlag" />
					</td>
					<td width="25%">
						<label name="mailSentOn" id="mailSentOn" 
						 ondblclick="callShowDiv(this);"><%=label.get("mailSentOn")%></label>
						:
					</td>
					<td width="40%">
						<s:textfield name="mailSentOnTiming" onkeypress="return numbersWithColon();"/>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">

				<tr>
					<td class="formtext"><b>Send Mail To :</b></td>
				</tr>

				<tr>
					<td class="formtext" width="25%"><label name="ind.department"
						id="ind.department" ondblclick="callShowDiv(this);"><%=label.get("ind.department")%></label>
					:</td>
					<td nowrap="nowrap" width="10%"><s:checkbox name="indDptFlag"
						id="indDptFlag" onclick="toggle('DptFlag','0');" /></td>
					<td width="20%"><label name="all.department"
						id="all.department" ondblclick="callShowDiv(this);"><%=label.get("all.department")%></label>
					:</td>
					<td width="40%"><s:checkbox name="allDptFlag" id="allDptFlag"
						onclick="toggle('DptFlag','1');" /></td>
				</tr>

				<tr>
					<td width="25%"><label name="ind.branch" id="ind.branch"
						ondblclick="callShowDiv(this);"><%=label.get("ind.branch")%></label>
					:</td>
					<td width="10%"><s:checkbox name="indBrnFlag" id="indBrnFlag"
						onclick="toggle('BrnFlag','0');" /></td>
					<td width="20%"><label name="all.branch" id="all.branch"
						ondblclick="callShowDiv(this);"><%=label.get("all.branch")%></label>
					:</td>
					<td width="40%" height="22" colspan="1"><s:checkbox
						name="allBrnFlag" id="allBrnFlag" onclick="toggle('BrnFlag','1');" /></td>
				</tr>

				<tr>
					<td width="25%"><label name="ind.designation"
						id="ind.designation" ondblclick="callShowDiv(this);"><%=label.get("ind.designation")%></label>
					:</td>
					<td width="10%"><s:checkbox name="indDesgFlag"
						id="indDesgFlag" onclick="toggle('DesgFlag','0');" /></td>
					<td width="20%"><label name="all.designation"
						id="all.designation" ondblclick="callShowDiv(this);"><%=label.get("all.designation")%></label>
					:</td>
					<td width="40%" height="22" colspan="1"><s:checkbox
						name="allDesgFlag" id="allDesgFlag"
						onclick="toggle('DesgFlag','1');" /></td>
				</tr>

				<tr>
					<td width="25%"><label name="ind.division" id="ind.division"
						ondblclick="callShowDiv(this);"><%=label.get("ind.division")%></label>
					:</td>
					<td width="10%"><s:checkbox name="indDivFlag" id="indDivFlag"
						onclick="toggle('DivFlag','0');" /></td>
					<td width="20%"><label name="all.division" id="all.division"
						ondblclick="callShowDiv(this);"><%=label.get("all.division")%></label>
					:</td>
					<td width="40%"><s:checkbox name="allDivFlag" id="allDivFlag"
						onclick="toggle('DivFlag','1');" /></td>
				</tr>
				
					<tr>
					<td width="25%"><label name="individual.employee" id="individual.employee"
						ondblclick="callShowDiv(this);"><%=label.get("individual.employee")%></label>
					:</td>
					<td width="10%"><s:checkbox name="allEmpFlag" id="allEmpFlag"
						onclick="toggle('EmpFlag','2');" /></td>
					<!--  Updated by Anantha lakshmi -->
					<td width="25%"><label name="individual.groupId" id="individual.groupId"
						ondblclick="callShowDiv(this);"><%=label.get("individual.groupId")%></label>
					:</td>
					<td width="10%"><s:checkbox name="groupId" id="groupId"
						onclick="unCheckAll();" /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td width="10%">
						<s:textarea  rows="4" cols="50" name="groupMailId" id="mailId" />
					</td>	
				</tr>
			</table>
			
		</td>
	</tr>		
			
	
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td class="formtext" colspan="4"><b>Include information in template :</b></td>
				</tr>
				<tr>
					<td width="20%"><label name="desgLable" id="desgLable"
						ondblclick="callShowDiv(this);"><%=label.get("desgLable")%></label>
					:</td>
					<td width="20%"><s:checkbox name="designationFlag" id="designationFlag"
						 /></td>
					<td width="20%"><label name="deptLable" id="deptLable"
						ondblclick="callShowDiv(this);"><%=label.get("deptLable")%></label>
					:</td>
					<td width="40%" height="22"><s:checkbox
						name="departmentFlag" id="departmentFlag" /></td>
				</tr>
				
				<tr>
					<td width="20%"><label name="emailLable" id="emailLable"
						ondblclick="callShowDiv(this);"><%=label.get("emailLable")%></label>
					:</td>
					<td width="20%"><s:checkbox name="emailIDFlag" id="emailIDFlag"  /></td>
					<td width="20%"><label name="phoneLable" id="phoneLable"
						ondblclick="callShowDiv(this);"><%=label.get("phoneLable")%></label>
					:</td>
					<td width="40%" height="22"><s:checkbox
						name="phoneNumberFlag" id="phoneNumberFlag"   /></td>
				</tr>
				<tr>
					<td width="20%"><label name="title" id="title"
						ondblclick="callShowDiv(this);"><%=label.get("title")%></label>
					:</td>
					<td width="20%"><s:checkbox name="titleFlag" id="titleFlag"/></td>
					<td width="60%" colspan="2"></td>
				</tr>
			</table>
		</td>
	</tr>			
	
	
	<tr>
		<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">		
				<tr>
					<td colspan="4">
					<table>
						<tr>
							<td width="15%"><s:checkbox name="tempCheckBox"
								onclick="checkTempCond()" /> <label name="template"
								id="template" ondblclick="callShowDiv(this);"><%=label.get("template")%></label>
							:</td>
							<td colspan="2" width="40%"><s:textfield size="40"
								name="tempName" readonly="true" /> <s:hidden name="tempCode" />
							<img src="../pages/common/css/default/images/search2.gif"
								width="16" height="15" onclick="javascript:callPrevious();">&nbsp;&nbsp;&nbsp;
							<input type="button" name="preview" class="token" value="Preview"
								onclick="previewTemplate();"></td>
						</tr>

						<tr>
							<td width="15%"><s:checkbox name="randCheckBox"
								onclick="checkCond()" /> <label name="randomTemplate"
								id="randomTemplate" ondblclick="callShowDiv(this);"><%=label.get("randomTemplate")%></label>
							:</td>
							<td colspan="2" width="40%">&nbsp;</td>
						</tr>
						<tr>
							<td colspan="4">
							<div id="randId" style="height: 200; width :600; overflow: scroll;">
							<table width="100%" border="0">
								<%
								int k = 0;
								%>
								<%!int tot = 0;%>
								<s:iterator value="randItt">
									<tr>
										<td width="27%" colspan="1" align="right"><s:checkbox
											name="ittCheckBox" id="<%="chk"+k%>" onclick="callCheckBox()" />

										</td>
										<td colspan="2" width="33%"><s:property
											value="ittRandomTemp" /> <input type="hidden"
											value='<s:property value="ittTempCode"/>'
											id="ittTempCode<%=k%>" /></td>
										<td width="10%"><input type="button" name="preview" class="token"
											value="Preview"
											onclick="previewTemplateItt('<s:property value="ittTempCode"/>');">
										</td>
										
									</tr>
									<%
									k++;
									%>
								</s:iterator>
								<%
								tot = k;
								%>
							</table>
							</div>
							</td>	
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr><td>&nbsp;</td></tr>
		<tr>
			<s:hidden name="paraId"></s:hidden>
			<td>
				<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
				<tr>
					<td>Exception Employee</td>
				</tr>
				<tr>	
					<td>
					<table>
							<tr>
							    <td></td>
								<td>
								    <label name="employee" id="employee"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%>
									</label>
								</td>
								<td>
								    <s:textfield name="empTokenNo" size="20"/>
									<s:textfield size="40" name="empName"></s:textfield> 
									<img src="../pages/images/recruitment/search2.gif" height="16"
										 align="absmiddle" width="17" theme="simple"
										 onclick="javascript:callsF9(500,325,'BirthdayMailSettings_f9Emp.action');">
								</td>
								<td>
									<input type="button" value="Add" class="token" onclick="addEmployee()" /> 	  	  
								</td>
									<s:hidden name="empName"/>
									<s:hidden name="empTokenNo"/>
									<s:hidden name="empCode"/>	 
							</tr>
							
					</table>
					</td>
					
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
			<table class="formbg" width="100%">
				<tr>
					<td class="formth" width="10%">
						<label  name="sr.no" id="sr.no"	ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label>
					</td>
					<td class="formth" width="60%">
						<label name="empID"	id="empID" ondblclick="callShowDiv(this);"><%=label.get("empID")%></label>
					</td>
					<td class="formth" width="60%">
						<label name="remove" id="remove" ondblclick="callShowDiv(this);"><%=label.get("remove")%></label>
					</td>
				</tr>
				<%
				int count = 0;
				%>
				<s:iterator value="birthdayMailList">
					<tr>
						<td align="center"><%=++count%></td>
						<td align="center">
							<s:hidden name="empTokenNoIt" />
							<s:hidden name="empCodeIt" />
							<s:hidden name="empNameIt" />
							<s:property value="empNameIt" />
						 </td>
						 <td  align="center">
							<input type="button" class="rowDelete" title="Delete Record" onclick="callForDelete('<%=count %>');" />	
						</td>
				</s:iterator>
				<%
				count = 0;
				%>
			</table>
			</td>
		</tr>
		
		
	</table>
</s:form>

<script>

function callPrevious(){
	if(!document.getElementById('paraFrm_tempCheckBox').checked){
		alert("Please click on "+document.getElementById('template').innerHTML.toLowerCase()+" checkBox");
		return false;
	}					
	callsF9(500,325,'BirthdayMailSettings_f9mailTemplate.action');
}
function callSave(){
	var temp="";
	var total=<%=tot%>;			 
	var count=0;
	
	
	var mailSentOnTiming = trim(document.getElementById('paraFrm_mailSentOnTiming').value);
	if(mailSentOnTiming !="") {
		 if(!validateTime('paraFrm_mailSentOnTiming', 'mailSentOn')) {
			document.getElementById('paraFrm_mailSentOnTiming').value = "";
			return false;
		}
	}
	
	for(var i=0;i<total;i++){
		if(document.getElementById('chk'+i).checked){
			count++;
		}
	}
	if(document.getElementById('paraFrm_randCheckBox').checked){
			temp="ss";
			//if(document.getElementById('paraFrm_hiddRandomCode').value==''){
			if(count==1 || count==0){
				alert("Please select atleast two "+document.getElementById('randomTemplate').innerHTML.toLowerCase());
				return false;
			}
	}
	if(document.getElementById('paraFrm_tempCheckBox').checked){
		temp="ss";
		if(document.getElementById('paraFrm_tempCode').value==''){
			alert("Please select "+document.getElementById('template').innerHTML.toLowerCase());
			return false;
		}
	}
	if(temp==''){
		alert("Please select "+document.getElementById('template').innerHTML.toLowerCase()+" OR "+document.getElementById('randomTemplate').innerHTML.toLowerCase());
		return false;
	}
	if(document.getElementById('mailId').value!=" "){
		 email=document.getElementById('mailId').value;
		 arrEmail=email.split(",");
		 for(var num=0;num<arrEmail.length;num++)
		 {	
		    document.getElementById('mailId').value=arrEmail[num];
			if(!validateEmail('mailId')){
				document.getElementById('mailId').value=email;   
		   	   	return false;
		   	}
		 }
	 }
	return true;
}

			function checkCond(){			
				if(document.getElementById('paraFrm_randCheckBox').checked){
				document.getElementById('paraFrm_tempCheckBox').checked=false;
				document.getElementById('paraFrm_tempCode').value="";	
				document.getElementById('paraFrm_tempName').value="";			
				document.getElementById('randId').style.display='';				
				}
				else{
				//document.getElementById('randId').style.display='';
				document.getElementById('paraFrm_hiddRandomCode').value="";
					var total=<%=tot%>;
					for(var i=0;i<total;i++){
			       	document.getElementById('chk'+i).checked=false;			
					}			
					}
					
				}
				
					function checkTempCond(){						
				if(document.getElementById('paraFrm_tempCheckBox').checked){				
				document.getElementById('paraFrm_randCheckBox').checked=false;
					document.getElementById('paraFrm_hiddRandomCode').value="";
					var total=<%=tot%>;
					for(var i=0;i<total;i++){
			       		document.getElementById('chk'+i).checked=false;			
						}			
						document.getElementById('randId').style.display='';				
					}
				else{			
						checkCond();
					}					
				}
			//tempCheckBox



			function callCheckBox(){
			var total=<%=tot%>;
			//alert('total :'+total);
			var hidCode="";
			for(var i=0;i<total;i++){
			if(document.getElementById('chk'+i).checked){
			document.getElementById('paraFrm_randCheckBox').checked=true;
			document.getElementById('paraFrm_tempCheckBox').checked=false;
			document.getElementById('paraFrm_tempCode').value="";	
				document.getElementById('paraFrm_tempName').value="";	
			var code=document.getElementById('ittTempCode'+i).value;
			
			hidCode +=code+",";
					}			
			}	
			hidCode=hidCode.substring(0,eval(hidCode.length)-1);		
			document.getElementById('paraFrm_hiddRandomCode').value=hidCode;
			return false;			
			}
	//

function previewTemplate()
{
	tempCode = document.getElementById('paraFrm_tempCode').value;
	if(tempCode == "")
	{
		alert("Please select "+document.getElementById('template').innerHTML.toLowerCase());
		return false;
	}
	window.open("BdayTemplate_setTemplateDataForPreview.action?tempCode="+tempCode,"","width=700,height=500,scrollbars=yes,status=yes,resizable=yes");
}

function previewTemplateItt(tempCode){
	
	if(tempCode == "")
	{
		alert("Please select "+document.getElementById('template').innerHTML.toLowerCase());
		return false;
	}
	window.open("BdayTemplate_setTemplateDataForPreview.action?tempCode="+tempCode,"","width=700,height=500,scrollbars=yes,status=yes,resizable=yes");
}



function toggle(id,type)
{
	if(type==0)
	{	document.getElementById('allEmpFlag').checked=false;
		if(document.getElementById('ind'+id).checked)
			document.getElementById('all'+id).checked=false;
		else
			document.getElementById('all'+id).checked=true;
	}
	if(type==2)
	{
		if(document.getElementById('all'+id).checked){
			document.getElementById('indDptFlag').checked=false;
			document.getElementById('allDptFlag').checked=false;
			
			document.getElementById('indBrnFlag').checked=false;
			document.getElementById('allBrnFlag').checked=false;
			
			document.getElementById('indDesgFlag').checked=false;
			document.getElementById('allDesgFlag').checked=false;
			
			document.getElementById('indDivFlag').checked=false;
			document.getElementById('allDivFlag').checked=false;			
			}
		
	}
	else
	{	document.getElementById('allEmpFlag').checked=false;
		if(document.getElementById('all'+id).checked)
			document.getElementById('ind'+id).checked=false;
		else
			document.getElementById('ind'+id).checked=true;
	}
}
function callForDelete(id){
 var con=confirm('Do you  really want to delete this record ?');
	    if(con){
		document.getElementById("paraFrm_paraId").value =id;
		document.getElementById("paraFrm").action="BirthdayMailSettings_deleteEmp.action";
		document.getElementById("paraFrm").submit();
		}else 
		return false;
}
function unCheckAll(){
	if(document.getElementById('groupId').checked){
		document.getElementById('indDptFlag').checked=false;
		document.getElementById('allDptFlag').checked=false;
		document.getElementById('indBrnFlag').checked=false;
		document.getElementById('allBrnFlag').checked=false;
		document.getElementById('indDesgFlag').checked=false;
		document.getElementById('allDesgFlag').checked=false;
		document.getElementById('indDivFlag').checked=false;
		document.getElementById('allDivFlag').checked=false;
		document.getElementById('allEmpFlag').checked=false;
		
		document.getElementById('indDptFlag').disabled=true;
		document.getElementById('allDptFlag').disabled =true;
		document.getElementById('indBrnFlag').disabled =true;
		document.getElementById('allBrnFlag').disabled =true;
		document.getElementById('indDesgFlag').disabled =true;
		document.getElementById('allDesgFlag').disabled =true;
		document.getElementById('indDivFlag').disabled =true;
		document.getElementById('allDivFlag').disabled =true;
		document.getElementById('allEmpFlag').disabled=true;
	}
	else{
		document.getElementById('indDptFlag').disabled=false;
		document.getElementById('allDptFlag').disabled=false;
		document.getElementById('indBrnFlag').disabled=false;
		document.getElementById('allBrnFlag').disabled=false;
		document.getElementById('indDesgFlag').disabled=false;
		document.getElementById('allDesgFlag').disabled=false;
		document.getElementById('indDivFlag').disabled=false;
		document.getElementById('allDivFlag').disabled=false;
		document.getElementById('allEmpFlag').disabled=false;
	}
}
function addEmployee(){
		document.getElementById("paraFrm").action="BirthdayMailSettings_addEmp.action";
		document.getElementById("paraFrm").submit();
}
unCheckAll();
</script>
