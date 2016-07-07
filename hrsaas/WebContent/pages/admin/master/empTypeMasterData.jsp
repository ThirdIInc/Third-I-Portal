<!--Bhushan Dasare-->
<!--Aug 19, 2009-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="EmpTypeMaster" name="EmpTypeMaster" id="paraFrm"
	validate="true" target="main" theme="simple">
	<s:hidden name="typMaster.esiZone" value="Y" />
	<s:hidden value="Y" name="typMaster.ptZone" />
	<s:hidden value="Y" name="typMaster.pfZone" />
	<s:hidden name="show" value="%{show}" />

	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee
					Type</strong></td>
					<td width="3%" valign="top" class="txt" align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" class="formbg">
			<table width="100%">
				<tr>
					<s:hidden name="typMaster.typeID" />
					<td colspan="1" width="15%" class="formtext"><label
						name="employee.type" id="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label><font
						color="red">*</font>:</td>
					<td colspan="2" width="55%"><s:textfield theme="simple"
						maxlength="50" name="typMaster.typeName"
						onkeypress="return allCharacters();" /></td>
				</tr>
				<tr>
					<td><label name="typeabbr" id="typeabbr"
						ondblclick="callShowDiv(this);"><%=label.get("typeabbr")%></label>:
					</td>

                     <td width="27%"><s:textfield theme="simple" maxlength="6"
						name="typMaster.typeAbbr" onkeypress="return allCharacters();" />
					</td>
				</tr>


<tr>
					<td width="10%" height="22" class="formtext"><label
						name="is active" class="set" id="is active"
						ondblclick="callShowDiv(this);"><%=label.get("is active")%></label>
					:</td>
					<td><s:select name="isActive" list="#{'Y':'Yes','N':'No'}"
						cssStyle="width:130;z-index:1;" /></td>
					<td height="10" class="formtext"></td>
					<td height="10"></td>
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
	setFocus();
	
	function setFocus() {
		document.getElementById('paraFrm_typMaster_typeName').focus();
	}

	function saveFun() {
		var fieldName = ["paraFrm_typMaster_typeName"];
		var lableName = ["employee.type"];
		var flag = ["enter"];
		var fieldName1 = ["paraFrm_typMaster_typeName", "paraFrm_typMaster_typeAbbr"];

		if(!validateBlank(fieldName, lableName, flag)) {
			return false;
        }
        
		if(!f9specialchars(fieldName1)) {
			return false;
		}
      
      	document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'EmpTypeMaster_save.action';
		document.getElementById('paraFrm').submit();
      	
		return true;
	}
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'EmpTypeMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'EmpTypeMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'EmpTypeMaster_delete.action';
			document.getElementById('paraFrm').submit();
		}
			
	}
	
	function editFun() {
		return true;
	}	
</script>