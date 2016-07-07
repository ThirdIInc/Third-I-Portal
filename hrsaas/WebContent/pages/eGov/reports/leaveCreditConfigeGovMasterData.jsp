<!--Dilip Dewangan-->
<!--Aug 26, 2009-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="LeaveCreditConfigeGov" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<s:hidden name="hiddencode"/>
<s:hidden  name="leaveConfigId" />

	<table width="100%" border="0" align="right" class="formbg" cellpadding="2"
				cellspacing="2">

		<tr>
			<td width="100%" valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Leave Credit Configuration 
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
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="25%" align="right"><span class="style2"><font
						color="red">*</font></span>Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
				<tr>
							<td colspan="1" width="20%"><label id="leave.type" name="leave.type" ondblclick="callShowDiv(this);"><%=label.get("leave.type")%></label> :<font color="red">*</font></td>
							<td><s:hidden name="leaveId" /> <s:textfield
								name="leaveName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <img  id="ctrlHide"
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'LeaveCreditConfigeGov_f9leaveType.action');">
							</td>
							</tr>
							<tr>
							<td colspan="1" width="20%"><label id="head" name="head" ondblclick="callShowDiv(this);"><%=label.get("head")%></label> :<font color="red">*</font></td>
							<td><s:hidden name="debitCode" /> <s:textfield
								name="debitName" theme="simple" readonly="true"
								maxlength="50" size="25" /> <!--<img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'LeaveCreditConfigeGov_f9rankaction.action');">
								
								
								--><input type="button" class="token" value=" >> " onclick="callLeaveInterval();" />
							</td>
							
						</tr>
					
			</table>
			</td>
		</tr>
		
		<tr>
				<td>
			<s:hidden name="hiddenfrmId" id="hiddenfrmId" />
			<jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	
	</table>
	
</s:form>


<script type="text/javascript">
	
function saveFun(){
		try {
		
		if(document.getElementById('paraFrm_leaveName').value==""){
			alert("Please select a Leave Type");
			document.getElementById('paraFrm_leaveName').focus();
  			return false;
		}
		if(document.getElementById('paraFrm_debitName').value==""){
			alert("Please select a Head");
			document.getElementById('paraFrm_debitName').focus();
  			return false;
		}
		
		} catch(e) 
		{
			alert("Exception occured in draft function : "+e);
		}
		
  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'LeaveCreditConfigeGov_save.action';
		document.getElementById('paraFrm').submit();
  	  	
  			
  }

	function resetFun() {
	//	document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'LeaveCreditConfigeGov_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LeaveCreditConfigeGov_back.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'LeaveCreditConfigeGov_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearch(action) {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'LeaveCreditConfigeGov_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
		function editFun() {
		
		return true;
		
	}		
	
	function callLeaveInterval()
{
	var hCode=document.getElementById('paraFrm_debitCode').value;
	document.getElementById('hiddenfrmId').value = hCode; 
	window.open('','new','top=100,left=300,width=700,height=700,scrollbars=yes,status=no,resizable=no');
	document.getElementById("paraFrm").target="new";
	document.getElementById("paraFrm").action='LeaveCreditConfigeGov_getLeaveHead.action';//?id='+id+'&hCode='+hCode+'&id1='+id1; 
	document.getElementById("paraFrm").submit();
	document.getElementById("paraFrm").target="main";
	
}
</script>