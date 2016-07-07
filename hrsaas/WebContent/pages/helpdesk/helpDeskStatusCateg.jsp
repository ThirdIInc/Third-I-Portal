<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="HelpDeskStatusCateg" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Status
					Category Master</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<!-- The Following code Denotes  Include JSP Page For Button Panel -->
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="22%">
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
							<table width="100%%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="22%" height="22" class="formtext"><label
										name="status.categ" class="set" id="status.categ"
										ondblclick="callShowDiv(this);"><%=label.get("status.categ")%></label>
									:<font color="red">*</font></td>
									<td width="17%" height="22">
									<s:textfield name="statusCateg" theme="simple" size="25"
										maxlength="20" onkeypress="return charactersOnly();"/></td>
									<td width="27%" height="22" class="formtext"></td>
									<td width="18%" height="22"><s:hidden name="statusCategCode" /></td>
								</tr>
								
								<tr>
									<td width="22%" height="22" class="formtext"><label
										name="status.abbrev" class="set" id="status.abbrev"
										ondblclick="callShowDiv(this);"><%=label.get("status.abbrev")%></label>
									:<font color="red">*</font></td>
									<td width="17%" height="22">
									<s:textfield name="statusAbbrev" theme="simple" size="25"
										maxlength="1" onkeypress="return charactersOnly();"/></td>
									<td width="27%" height="22" class="formtext"></td>
									<td width="18%" height="22"></td>
								</tr>
								
								<tr>
									<td width="22%" height="22" class="formtext"><label
										name="status.order" class="set" id="status.order"
										ondblclick="callShowDiv(this);"><%=label.get("status.order")%></label>
									:<font color="red">*</font></td>
									<td width="17%" height="22">
									<s:textfield name="statusOrder" theme="simple" size="25"
										maxlength="2" onkeypress="return numbersOnly();"/></td>
									<td width="27%" height="22" class="formtext"></td>
									<td width="18%" height="22"></td>
								</tr>
								
								<tr>
									<td width="22%" height="22" class="formtext"><label
										name="status.last" class="set" id="status.last"
										ondblclick="callShowDiv(this);"><%=label.get("status.last")%></label>
									:</td>
									<td width="17%" height="22"><s:checkbox theme="simple" 
										name="isStatusLast" /></td>
									<td width="27%" height="22" class="formtext"></td>
									<td width="18%" height="22"></td>
								</tr>
								
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="editHidcode" /><s:hidden name="hidDeptCode" />
		<s:hidden name="show" /><s:hidden name="hiddencode" />

		<tr>
			<td width="75%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>
								
<script type="text/javascript">
	function saveFun(){	
		var val=trim(document.getElementById('paraFrm_statusCateg').value);
		if(val==""){
			alert("Please enter "+document.getElementById('status.categ').innerHTML.toLowerCase());
			document.getElementById('paraFrm_statusCateg').focus();
			return false;
		}
		var abbr=trim(document.getElementById('paraFrm_statusAbbrev').value);
		if(abbr==""){
			alert("Please enter "+document.getElementById('status.abbrev').innerHTML.toLowerCase());
			document.getElementById('paraFrm_statusAbbrev').focus();
			return false;
		}
		var head=trim(document.getElementById('paraFrm_statusOrder').value);
		if(head==""){
			alert("Please enter "+document.getElementById('status.order').innerHTML.toLowerCase());
			document.getElementById('paraFrm_statusOrder').focus();
			return false;
		}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'HelpDeskStatusCateg_save.action';
		document.getElementById('paraFrm').submit();
	  	return true;
	}  

	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'HelpDeskStatusCateg_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HelpDeskStatusCateg_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		var con=confirm('Do you want to delete the record(s) ?');
		if(con){
			document.getElementById('paraFrm').target = "_self";
		   	document.getElementById('paraFrm').action = 'HelpDeskStatusCateg_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function editFun() {
		document.getElementById('paraFrm_editHidcode').value = '1';
		return true;
	}
</script>								