<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
 
 
<s:form action="ReportingStrNew" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">
<s:hidden name="groupDivision" />

<s:hidden name="defineSelectSource" />

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reporting
					  Structure </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	
	<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="2">
					<s:submit name="" value="Back" cssClass="back" action="ReportingStrNew_backToMain"></s:submit>
					</td>
					<td width="22%">
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

	<tr>
		<td colspan="3">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="formbg">
			<tr>
				<td>

				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">

					<tr>
						<td width="20%" colspan="1" height="27"><label class="set"
							name="mdoule.name" id="mdoule.name1"
							ondblclick="callShowDiv(this);"><%=label.get("mdoule.name")%></label><font
							color="red">*</font> :</td>
						<td height="27"><s:property value="moduleNameSelect" />
						
						<s:hidden name="moduleNameSelect"/> 
						<s:hidden name="moduleAbbrSelect" /></td>
						
						 
						<td> </td>
						<td></td>
								 
					</tr>
					 
					<tr>
						<td width="20%" colspan="1" height="27"><label class="set"
							name="new.group" id="new.group" ondblclick="callShowDiv(this);"><%=label.get("new.group")%></label><font
							color="red">*</font> :</td>
						<td height="27">
						
						<s:textfield name="groupName" maxlength="50" onkeypress="return charactersOnly();"/> <s:submit
							name="sub" value="Add" action="ReportingStrNew_addGroup"
							onclick="return callAddGroup();"></s:submit></td>
							<td></td>
							<td></td>
							 
					</tr>
 

				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>

<s:if test="showGroup">

	<tr>
		<td colspan="3">
		<table width="100%" border="0" cellpadding="0" cellspacing="0"
			class="formbg">
			<tr>
				<td width="100%">
				<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0">

					<tr>
						<td>
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">
							<tr>
								<td valign="top" class="formth" width="5%"><label
									name="srnum" id="srnum" ondblclick="callShowDiv(this);"><%=label.get("srnum")%></label></td>
								<td width="30%" valign="top" class="formth"><label
									name="group.name" id="group.name2"
									ondblclick="callShowDiv(this);"><%=label.get("group.name")%></label></td>
								<td width="10%" valign="top" class="formth"><label
									name="create.date" id="create.date"
									ondblclick="callShowDiv(this);"><%=label.get("create.date")%></label></td>
								<td width="10%" valign="top" class="formth"><label
									name="manger.approver" id="manger.approver"
									ondblclick="callShowDiv(this);"><%=label.get("manger.approver")%></label></td>
								<td width="10%" valign="top" class="formth"><label
									name="manage.team" id="manage.team"
									ondblclick="callShowDiv(this);"><%=label.get("manage.team")%></label></td>


								<td width="10%" valign="top" class="formth">Delete</td>
							</tr>

							<%
								int count = 0, p = 0;
								%>
							<s:iterator value="groupList" status="stat">
								<tr>
									<td><%=++p%></td>
									<td>
									<s:hidden name="groupIdItt" />
									<s:property value="groupNameItt" /></td>
									<td align="center"><s:property value="groupCreateDate" />
									</td>
									<td align="center"><a href="javascript:void(0);" onclick="defineManagerApprover('<s:property value="groupIdItt" />','<s:property value="groupNameItt" />');"><u>Manage
									Approver</u></a></td>
									<td align="center"><a href="javascript:void(0);"
									onclick="callmanageteammember('<s:property value="groupIdItt" />','<s:property value="groupNameItt" />');"
									><u>Manage
									TeamMember</u></a></td>
									<td align="center"><img title="Remove Group"
									onclick="removeGroup('<s:property value='groupIdItt'/>');"
										src="../pages/mypage/images/icons/delete.png"
										style="cursor: pointer;" /></td>

								</tr>

							</s:iterator>
							<%
								p = 0;
								%>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	
	</s:if>
	
	<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="2">
					<s:submit name="" value="Back" cssClass="back" action="ReportingStrNew_backToMain"></s:submit>
					</td>
					<td width="22%">
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>

</s:form>

<script>


function callmanageteammember(groupCode,groupName)
{

	var defineSelectSource =document.getElementById('paraFrm_defineSelectSource').value;
  var division = document.getElementById('paraFrm_groupDivision').value;
	 
	 var moduleAbbrSelect =document.getElementById('paraFrm_moduleAbbrSelect').value;
	 
	 var moduleNameSelect=document.getElementById('paraFrm_moduleNameSelect').value;
	 
document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/common/ReportingStrNew_manageTeamMember.action?groupCode='+groupCode+'&groupName='+groupName+'&divisionStr='+division+'&moduleAbbrSelectStr='+moduleAbbrSelect+'&moduleNameSelectStr='+moduleNameSelect+'&defineSelectSourceStr='+defineSelectSource;
		
		//alert(document.getElementById('paraFrm').action);
		document.getElementById('paraFrm').submit();

}


function defineManagerApprover(groupCode,groupName)
{
	try{
	
	var defineSelectSource =document.getElementById('paraFrm_defineSelectSource').value;
	
	 var division = document.getElementById('paraFrm_groupDivision').value;
	 
	 var moduleAbbrSelect =document.getElementById('paraFrm_moduleAbbrSelect').value;
	 
	 var moduleNameSelect=document.getElementById('paraFrm_moduleNameSelect').value;
	 
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/common/ReportingStrNew_defineManagerApprover.action?groupCode='+groupCode+'&groupName='+groupName+'&division='+division+'&moduleAbbrSelectStr='+moduleAbbrSelect+'&moduleNameSelectStr='+moduleNameSelect+'&defineSelectSourceStr='+defineSelectSource;
		
		//alert(document.getElementById('paraFrm').action);
		document.getElementById('paraFrm').submit();
}catch(e){alert("Error----- "+e);}

}

function callAddGroup()
{

var groupName =document.getElementById('paraFrm_groupName').value;

var fieldName = ["paraFrm_groupName"];
var lableName = ["new.group"];
var flag = ["enter"];

if(!(validateBlank(fieldName,lableName,flag))){
			return false;
        }
        
  
 
}

function removeGroup(id)
{

var conf=confirm("Do you really want to remove group. ?");
  			if(conf) {
			  	 
					document.getElementById("paraFrm").action="ReportingStrNew_deleteGroup.action?deleteGroupCodeStr="+id;
					document.getElementById("paraFrm").submit();	
				 
  			}else{
  				return false;
  			}


}


</script>
