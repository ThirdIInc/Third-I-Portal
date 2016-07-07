<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="CeoConfiguration" id="paraFrm" validate="true"
	target="main" theme="simple">
	<s:hidden name="hiddenDelete" />
	<s:hidden name="ceoType" id="ceoType" />
	<s:hidden name="showCeo" id="showCeo" />
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">CEO
					Configuration </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="left" colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0" border="0"
				id="keepInformedTable" class="formbg">
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="senttoall" id="senttoall" ondblclick="callShowDiv(this);"><%=label.get("senttoall")%></label><font
						color="red">*</font> :</td>
					<td width="80%" colspan="3"><s:hidden name="empCode" /> <s:hidden
						name="empToken" /> <s:textfield name="empName" size="40"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'CeoConfiguration_f9employee.action');" />
					<s:submit name="" value=" Add" cssClass=" add"
						action="CeoConfiguration_addEmployee"
						onclick="return addEmployee();" /></td>
				</tr>
				<tr>

					<td width="20%" colspan="1"></td>
					<td width="80%" colspan="3"><s:if test="showCeo">
						<input type="radio" name="chkCs" value="link_cs"
							onclick="chkRadio('C');">CEO
					:&nbsp; </s:if><input type="radio" name="chkCs" value="uploadDoc_cs"
						onclick="chkRadio('M');">Message Administrator &nbsp;</td>
				</tr>
				<tr>
					<td width="20%"><label class="set" name="ceoDivision"
						id="ceoDivision" ondblclick="callShowDiv(this);"><%=label.get("ceoDivision")%></label>
					:</td>
					<td width="76%"><s:hidden name="ceoDivCode"></s:hidden> <s:textarea
						name="ceoDivName" cols="25" rows="2" readonly="true"></s:textarea>
					<img src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" theme="simple"
						onclick="javascript:callF9Function('paraFrm_ceoDivName','paraFrm_ceoDivCode'); ">
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
					<td colspan="5">
					<table width="100%">
						<tr>
							<td class="formth" width="10%"><label id="sno" name="sno"
								ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
							<td class="formth" width="30%" align="center"><label
								id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>

							<td class="formth" width="20%" nowrap="nowrap" align="left">
							CEO/Message Administrator</td>
							<td class="formth" width="20%" nowrap="nowrap" align="left">Division Configured</td>
							<td class="formth" width="10%" align="center">Delete</td>
						</tr>
						<%!int y = 0;%>
						<%
						int count = 0;
						%>
						<s:iterator value="list">
							<tr>
								<td class="sortableTD" width="10%"><%=++count%>&nbsp; <s:hidden
									name="srNo" value="%{<%=count%>}" /></td>
								<td class="sortableTD" width="30%" nowrap="nowrap" align="left">&nbsp;
								<s:hidden name="empTokenItt" /> <s:hidden name="empNameItt" />
								<s:hidden name="empCodeItt" /> <s:property value="empNameItt" /></td>
								<td class="sortableTD" width="20%" nowrap="nowrap" align="left">
								<s:hidden name="ceoTypeItt" /> <s:property
									value="ceoTypeValueItt" /> <s:hidden name="ceoTypeValueItt" /></td>
								<td><s:hidden name="CeoDivName"/>
								<s:property
									value="CeoDivName" /></td>
								<td class="sortableTD" width="10%" nowrap="nowrap"
									align="center"><input type="button" class="rowDelete"
									onclick="callForDelete('<s:property value="empCodeItt" />')" /></td>
							</tr>
						</s:iterator>
						<%
						y = count;
						%>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>

function addEmployee(){
	var employee  = document.getElementById('paraFrm_empCode').value;
	var type = document.getElementById('ceoType').value ;
	var showCeo =document.getElementById('showCeo').value;
	if(employee==""){
		alert("Please select employee");
		return false ; 
	}
	if(type==""){
		if(showCeo=='false'){
			alert("Please select Message Administrator.");
			return false ;
		}
		else{
			alert("Please select CEO/Message Administrator.");
			return false ;
		}	 
	}	
	return true ; 
 }

function callForDelete(id){
	var conf=confirm("Are you sure !\n You want to delete this record ?");
  	if(conf){
  		document.getElementById('paraFrm_hiddenDelete').value=id;
   		document.getElementById("paraFrm").action="CeoConfiguration_delete.action";
		document.getElementById("paraFrm").submit();
  	}
  }
 
function callF9Function(divName1,divCode1){    
   callsF9(500,325,'CeoConfiguration_f9actionDiv.action?divName='+divName1+'&divCode='+divCode1);
 }
 
 function chkRadio(id){
		document.getElementById('ceoType').value=id;
}
</script>