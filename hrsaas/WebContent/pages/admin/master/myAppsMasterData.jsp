<!-- @author: Prajakta.Bhandare -->
<!-- @date: 11 Feb 2013 -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="MyAppsMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
<s:hidden name="linkId" />
<s:hidden name="show" value="%{show}" />
<s:hidden name="hiddencode"  />
<s:hidden name="modeLength"/>
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
					<td width="93%" class="txt"><strong class="text_head">My Apps 
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

					<td width="20%"><label class="set" name="Name"
						id="Name" ondblclick="callShowDiv(this);"><%=label.get("Name")%></label>
					:<font color="red">*</font></td>
					<td  width="23%"><s:textfield theme="simple"
						name="linkName" id="linkName" size="33" maxlength="50" /></td>
					

				</tr>
				<tr>
					<td width="20%"><label class="set" name="url"
						id="url" ondblclick="callShowDiv(this);"><%=label.get("url")%></label>
					:<font color="red">*</font></td>
					<td  width="23%"><s:textfield theme="simple"
						name="linkUrl" id="linkUrl" size="33" maxlength="100" /></td>
					<td width="2%"></td>
					<td width="55%"></td>	
				</tr>
				<tr>
				<td width="20%"><label class="set" name="link.image" id="link.image"
						ondblclick="callShowDiv(this);"><%=label.get("link.image")%></label>:<font color="red">*</font></td>
					<td  width="35%"><s:textfield theme="simple"
						name="linkImage" size="33" readonly="true" />
					<input type="button" class="token" name="Browse" value="Browse" onclick="uploadFile('linkImage');" /></td>
				<td width="45%"></td>	
				</tr>
				<tr>
					<td width="20%"><label class="set" name="sequence" id="sequence"
						ondblclick="callShowDiv(this);"><%=label.get("sequence")%></label>
					:</td>
					<td width="23%"><s:textfield size="33" theme="simple"
						name="linkSeq" maxlength="50" /></td>
					<td width="2%"></td>
					<td width="55%"></td>
				</tr>
				<tr>
					<td width="20%"><label name="div" class="set" id="div"
						ondblclick="callShowDiv(this);"><%=label.get("div")%>
					</label>:</td>
					<s:hidden name="linkDivCode"></s:hidden>
					<td width="30%"><s:textarea name="linkDiv" cols="30"
						rows="2" onkeyup="callLength('descCnt');" readonly="true" />
						<img id='ctrlHide' align="absmiddle" src="../pages/common/css/default/images/search2.gif" onclick="javascript:callF9Function('paraFrm_linkDiv','paraFrm_linkDivCode');">
					</td>
					<td width="55%"></td>
				</tr>
				<tr>
					<td width="20%"><label class="set" name="is.active"
						id="is.active" ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>:
					</td>
					<td width="23%"><s:select cssStyle="width:100"
						list=" #{'Y':'Yes','N':'No'}" name="isActive" /></td>
					<td width="2%"></td>
					<td width="55%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	
	</table>
	
</s:form>


<script type="text/javascript">
	
function saveFun(){
	if(trim(document.getElementById("linkName").value)==""){
	alert("Please enter "+document.getElementById("Name").innerHTML.toLowerCase());
	return false;
	}//end of if
	if(trim(document.getElementById("linkUrl").value)==""){
	alert("Please enter "+document.getElementById("url").innerHTML.toLowerCase());
	return false;
	}//end of if
	if(document.getElementById("paraFrm_linkImage").value==""){
	alert("Please select "+document.getElementById("link.image").innerHTML.toLowerCase());
	return false;
	}//end of if
  	document.getElementById('paraFrm').target = "_self";
  	document.getElementById('paraFrm').action = 'MyAppsMaster_save.action';
	document.getElementById('paraFrm').submit();
  	return true ;
  			
  }
  
  function callF9Function(linkDiv1,linkDivCode1)
 {   
   callsF9(350,250,'MyAppsMaster_f9applDiv.action?linkDiv='+linkDiv1+'&linkDivCode='+linkDivCode1);
 }

	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'MyAppsMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'MyAppsMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'MyAppsMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function editFun() {
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'MyAppsMaster_getRecords.action';
		document.getElementById('paraFrm').submit();
		return true;
	}
function uploadFile(fieldName) 
	{
		var path="../pages/images/icons/portal/";
		window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+"paraFrm_"+fieldName+'&image='+"image",'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}  	
</script>