<%@ taglib uri="/struts-tags" prefix="s"%>

<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="AlertAdminSetting" method="post"
	name="AlertAdminSettings" validate="true" id="paraFrm" theme="simple">

	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">


		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="text_head"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>

					<td width="100%" class="txt"><strong class="formhead">Alert
				 Admin Setting </strong></td>



					<td width="3%" valign="top" class="txt" width="100%">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">


				<tr>
					<td colspan="4"><s:submit cssClass="add"
						action="AlertAdminSetting_save" theme="simple" value=" Save"
						onclick="return callSave(); " /> <s:submit cssClass="reset"
						action="AlertAdminSetting_reset" theme="simple" value=" Reset" />

					<input type="button" class="delete" value=" Delete"
						onclick="javascript:deleteFunction();" /> <input type="button"
						class="search" value=" Search" onclick="javascript:callPopup();" />

					</td>


					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td width="25%" colspan="1" height="22"><label class="set"
						name="modName" id="modName" ondblclick="callShowDiv(this);"><%=label.get("modName")%></label>
					:<font color="red">*</font></td>
					<td width="25%" colspan="1" height="22"><s:textfield
						theme="simple" name="moduleName" size="30" readonly="true" /> <img
						src="../pages/images/search2.gif" height="16" align="absmiddle"
						width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'AlertAdminSetting_f9ModuleName.action')">
					</td>
				</tr>

				<tr>
					<td width="20%"><label name="subject" id="subject"
						ondblclick="callShowDiv(this);"><%=label.get("subject")%></label>
					<font color="red">*</font>:</td>
					<td width="80%"><s:textfield name="subject" size="97" /></td>
				</tr>
				<!--<tr>
					<td width="5%" colspan="1" height="22" nowrap="nowrap"><label
						name="query" id="query" ondblclick="callShowDiv(this);"><%=label.get("query")%></label>
					:</td>
					<td width="75%" colspan="1" height="22"><s:textarea
						name="query" rows="10" cols="100" /></td>

				</tr>

	
				--><tr>
					<td width="20%"><label name="methodName" id="methodName"
						ondblclick="callShowDiv(this);"><%=label.get("methodName")%></label>
					 :</td>
					<td width="80%"><s:textfield name="methodName" size="97" /></td>
				</tr>
				
				<tr>
					<td width="20%"><label name="linkName" id="linkName"
						ondblclick="callShowDiv(this);"><%=label.get("linkName")%></label>
					 :</td>
					<td width="80%"><s:textfield name="link" size="97" /></td>
				</tr>
				
				<tr>
					<td width="20%"><label name="link.param" id="link.param"
						ondblclick="callShowDiv(this);"><%=label.get("link.param")%></label>
					 :</td>
					<td width="80%"><s:textfield name="linkParameter" size="97" /></td>
				</tr>
				
				<tr>
					<td width="20%"><label name="nooflink.param" id="nooflink.param"
						ondblclick="callShowDiv(this);"><%=label.get("nooflink.param")%></label>
					 :</td>
					<td width="80%"><s:textfield name="noOflinkParameter"  maxlength="2" size="97" onkeypress="return numbersOnly();" /></td>
				</tr>
				
				
				<tr>
					<td width="20%"><label name="link.paramval" id="link.paramval"
						ondblclick="callShowDiv(this);"><%=label.get("link.paramval")%></label>
					 :</td>
					<td width="80%"><s:textfield name="linkParameterValue" size="97" /></td>
				</tr>
				
				

				<tr>
					<td width="15%" colspan="1" height="22"><label
						name="alertType" id="alertType" ondblclick="callShowDiv(this);"><%=label.get("alertType")%></label><font
						color="red">*</font>:</td>
					<td width="85%" colspan="1" height="22">Email &nbsp;<s:checkbox
						name="emailCheck" /> Alert &nbsp;<s:checkbox name="alertCheck" />
					</td>
				</tr>


				<tr>
					<td width="25%" colspan="1" height="22"><label class="set"
						name="template" id="template" ondblclick="callShowDiv(this);"><%=label.get("template")%></label>
					: </td>
					<td width="25%" colspan="1" height="22"><s:hidden
						name="templateCode"></s:hidden> <s:textfield theme="simple"
						name="templateName" size="60" readonly="true" /> <img
						src="../pages/images/search2.gif" height="16" align="absmiddle"
						width="16" theme="simple"
						onclick="javascript:callsF9(500,325,'AlertAdminSetting_f9template.action')">
					</td>
				</tr>


				<tr>
					<td width="15%" colspan="1" height="22"><label
						name="query.type" id="query.type" ondblclick="callShowDiv(this);"><%=label.get("query.type")%></label>
						 :</td>
					<td width="85%" colspan="1" height="22"><s:select
						name="querytype" headerKey="1" headerValue="--Select--"
						list="#{'F':'Fixed','D':'Dynamic'}" /></td>
				</tr>

				<tr>
					<td width="15%" colspan="1" height="22"><label
						name="alert.type" id="alert.type" ondblclick="callShowDiv(this);"><%=label.get("alert.type")%></label> :</td>
					<td width="85%" colspan="1" height="22"><s:select
						name="alerttype" headerKey="1" headerValue="--Select--"
						list="#{'A':'Action','I':'Information'}" /></td>
				</tr>
				
				
			
				 


			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">


				<tr>
					<td colspan="4"><s:submit cssClass="add"
						action="AlertAdminSetting_save" theme="simple" value=" Save"
						onclick="return callSave(); " /> <s:submit cssClass="reset"
						action="AlertAdminSetting_reset" theme="simple" value=" Reset" />

					<input type="button" class="delete" value=" Delete"
						onclick="javascript:deleteFunction();" /> <input type="button"
						class="search" value=" Search" onclick="javascript:callPopup();" />

					</td>

					<td width="22%"></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>

	<s:hidden name="jobCode" />
	<s:hidden name="query" /> 
</s:form>


<script>

function deleteFunction()
{

	var jobCode =document.getElementById('paraFrm_jobCode').value;
	
	if(jobCode=="")
	{
		alert("Please search record.");
		return false;
	}
  var conf=confirm("Do you really want to delete this record ?");
		 	if(conf)
			{
		document.getElementById("paraFrm").action="AlertAdminSetting_delete.action";
		document.getElementById("paraFrm").submit();
			}
			 else
			 {
			 return false; 
			 }
	 return true;

}

function callSave()
{
  	 
	var fieldName = ["paraFrm_moduleName","paraFrm_subject"];
		var lableName = ["modName","subject"];
		var flag = ["select","enter"];
		
	
		
	if(!(validateBlank(fieldName,lableName,flag))){
			return false;
        }	
        
       if(document.getElementById('emailCheck').checked==true)
       {
       			var templateCode = document.getElementById('paraFrm_templateCode').value;
       			
       			if(templateCode=="")
       			{
       			alert("Please select "+document.getElementById('template').innerHTML.toLowerCase());
			return false;
       			}
       }
		
		    var queryType = document.getElementById("paraFrm_querytype").value;
      /* 	if(queryType=="1")
		{
	 
		alert("Please select "+document.getElementById('query.type').innerHTML.toLowerCase());
			return false;
		}
		
		*/
		 
		  if(document.getElementById('alertCheck').checked==true)
       {
       			 var alertType = document.getElementById("paraFrm_alerttype").value;
		  	if(alertType=="1")
		{
	 
		alert("Please select "+document.getElementById('alert.type').innerHTML.toLowerCase());
			return false;
		}
       
       }
		 
     
		 

return true;
}

function callPopup(){
		callsF9(500,325,'AlertAdminSetting_f9searchaction.action');
	 
	}

</script>