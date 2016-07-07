<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="TaskProject" method="post" name="TaskProject"
	validate="true" id="paraFrm" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="paraId"/>
	<s:hidden name="editFlag"/>
	<s:hidden name="showFlag"/>
	<s:hidden name="hiddenisActive"/>

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		
		<tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Task Project
			  </strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
				</tr>
				</table>
			</td>
		</tr>
		
	 	<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>

					
					<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
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
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">

						<tr>
							<s:hidden name="proCode" />

							<td width="10%" colspan="1"><label  name="projectname" id="projectname" ondblclick="callShowDiv(this);"><%=label.get("projectname")%></label> :<font color="red">*</font>

						
							<td colspan="2" width="55%"><s:textfield theme="simple"
								name="projName" size="25" maxlength="50" /></td>
						</tr>
						
						
						<tr>
							<td width="20%" height="22" class="formtext"><label name="is.active" class="set" id="is.active" 			ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label> :
							</td>
							<td>
								<s:select name="isActive" list="#{'Y':'Yes','N':'No'}" cssStyle="width:151;z-index:5;" />
							</td>
						</tr>

					</table></td></tr>
					<s:if test="editFlag">
					<tr>
					<td colspan="2">
						<table class="formbg" width="100%" cellspacing="0" cellspacing="1" border="0">		
					<tr>
						<td  class="txt"><strong class="text_head">Stakeholder
						  </strong></td>		
						
					</tr>
					<tr>
					<td colspan="1" width="20%" class="formtext"><label class="set"
						id="reporting.appr.name0" name="reporting.appr.name"
						ondblclick="callShowDiv(this);">Employee</label><font color="red">*</font>
					:</td>
					<s:hidden theme="simple"
						name="empCode" /> <s:hidden
						theme="simple" name="empToken" />
					<td colspan="1" width="80%"><s:textfield
						theme="simple" name="empName" size="50"
						readonly="true" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="18" theme="simple"
						onclick="javascript:callsF9(500,325,'TaskProject_f9actionEmp.action'); ">&nbsp;&nbsp;
					</td>
					</tr>
					<tr>
						<td align="center" colspan="2"><s:submit cssClass="add" onclick="return callStakeHolderAdd();"
							action="TaskProject_addItem" theme="simple"  value="   Add   " /></td>
					</tr>
					
					<tr>
						<td colspan="2">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
							<tr>
								<td class="formth" width="6%" height="22" valign="top"><label name="reporting.srno" class = "set"  id="reporting.srno" ondblclick="callShowDiv(this);">Sr No</label></td>
								<td class="formth" width="9%" height="22" valign="top"><label name="reporting.appr.id" class = "set"  id="reporting.appr.id" ondblclick="callShowDiv(this);">Employee Id</label></td>
								<td class="formth" width="19%" height="22" valign="top"><label name="reporting.appr.name" class = "set"  id="reporting.appr.name" ondblclick="callShowDiv(this);">Employee Name</label></td>
								<td class="formth" width="6%" height="22" valign="top"><label name="reporting.srno" class = "set"  id="reporting.srno" ondblclick="callShowDiv(this);">Remove</label></td>								
							</tr>
							<%							
							int count = 0;
							int k=1;
						%>
						<s:iterator value="stakeholderlist">
						<tr>
							<td width="6%" class="sortableTD"><%=++count %></td>
							<s:hidden name="ittrempempCode"/>
							<td width="9%" class="sortableTD"><s:hidden name="ittrempToken"/><s:property value="ittrempToken"/></td>
							<td width="22%" class="sortableTD"><s:hidden name="ittrempName"/><s:property value="ittrempName"/></td>
							<td width="6%" class="sortableTD" align="center"><input type="button" class="rowDelete" title="Delete Record"
								onclick="callForDelete(<%=k%>)" /></td>
						</tr>
						<%k++; %>
						</s:iterator>
							</table>
						</td>
					</tr>
					
				</table>
				</td>
					</tr>
					<!-- Project Status start -->
						<tr>
					<td colspan="2">
						<table class="formbg" width="100%" cellspacing="0" cellspacing="1" border="0">		
					<tr>
						<td  class="txt"><strong class="text_head">Project Status
						  </strong></td>		
						
					</tr>
					<tr>
					<td colspan="1" width="20%" class="formtext"><label class="set"
						id="reporting.appr.name0" name="reporting.appr.name"
						ondblclick="callShowDiv(this);">Staus</label><font color="red">*</font>
					:</td>
					
					<td colspan="1" width="80%"><s:textfield theme="simple"
								name="projStatus" size="25" maxlength="50" />
					</td>
					</tr>
					
					<tr>
						<td align="center" colspan="2"><s:submit cssClass="add" onclick="return callStatusAdd();"
							action="TaskProject_addStatus" theme="simple" value="   Add   " /></td>
					</tr>
					
					<tr>
						<td colspan="2">
							<table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
							<tr>
								<td class="formth" width="6%" height="22" valign="top"><label name="reporting.srno" class = "set"  id="reporting.srno" ondblclick="callShowDiv(this);">Sr No</label></td>								
								<td class="formth" width="19%" height="22" valign="top"><label name="reporting.appr.name" class = "set"  id="reporting.appr.name" ondblclick="callShowDiv(this);">Project Status</label></td>
								<td class="formth" width="6%" height="22" valign="top"><label name="reporting.srno" class = "set"  id="reporting.srno" ondblclick="callShowDiv(this);">Remove</label></td>								
							</tr>
							<%							
							int cnt = 0;
							int j=1;
						%>
						<s:iterator value="statuslist">
						<tr>
							<td width="6%" class="sortableTD"><%=++cnt %></td>							
							<td width="22%" class="sortableTD"><s:hidden name="ittrProjStatus"/><s:property value="ittrProjStatus"/></td>							
							<td width="6%" class="sortableTD" align="center"><input type="button" class="rowDelete" title="Delete Record"
								onclick="callForDeleteStatus(<%=j%>)" /></td>
						</tr>
						<%j++; %>
						</s:iterator>
							</table>
						</td>
					</tr>
					
				</table>
				</td>
					</tr>
					</s:if>
					</table></td></tr>
					<tr><td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td></tr>
					</table></s:form>
<script>

function callStakeHolderAdd()
{
	if(document.getElementById("paraFrm_empCode").value=="")
	{
		alert("Please select Employeee");
		return false;
	}
}
function callStatusAdd()
{
	if(document.getElementById("paraFrm_projStatus").value=="")
	{
		alert("Please enter Project Status");
		return false;
	}
}

var f9Fields=["paraFrm_projName"];
var fieldName = ["paraFrm_projName"];
var lableName = ["projectname"];
var type = ["enter"];
function saveFun()
 {
 
		/*if(!document.getElementById('paraFrm_proCode').value==""){
 		alert("Please click on 'Update' button");
 			return false;
 }*/
     	if(!(validateBlank(fieldName, lableName,type)))
        	return false;
         
    	if(!f9specialchars(f9Fields))
		return false;
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'TaskProject_save.action';
		document.getElementById('paraFrm').submit();
		return true;
}
 
function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'TaskProject_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TaskProject_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'TaskProject_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'TaskProject_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	

function editFun() {
	
		document.getElementById("paraFrm_showFlag").value="true";
		return true;
	}
	
function callForDelete(rowId)
{
	//alert("rowId :: "+rowId);
	var conf=confirm("Do you really want to remove this record ?");
  			if(conf) {
		document.getElementById("paraFrm_paraId").value=rowId;
		document.getElementById("paraFrm").action="TaskProject_remove.action";
	    document.getElementById("paraFrm").submit();
	    }
}	

function callForDeleteStatus(rowId)
{
	//alert("rowId :: "+rowId);
	var conf=confirm("Do you really want to remove this record ?");
  			if(conf) {
		document.getElementById("paraFrm_paraId").value=rowId;
		document.getElementById("paraFrm").action="TaskProject_removeStatus.action";
	    document.getElementById("paraFrm").submit();
	    }
}
	
</script>