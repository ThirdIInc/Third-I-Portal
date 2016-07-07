<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ReportSettings" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Layout
					Format/Report Configuration </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="3">
			<table width="100%">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>

					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<%-- <tr>
							<td height="22" width="25%" colspan="1"><label class="set"
								name="division" id="division"
								ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:<font color="red">*</font></td>
							<td width="75%" colspan="3">
								<s:hidden theme="simple" name="divCode" /> 
								<s:textfield size="25"	name="divname" readonly="true"/>
								<img src="../pages/images/search2.gif" height="16" align="bottom" width="16" theme="simple" 
										onclick="javascript:callsF9(800,525,'ReportSettings_f9Divaction.action');"  >
								
							</td>
							
						</tr>
						 --%>
						<tr>
							<td width="100%" colspan="4"><b><label class="set"
								name="PageMargin" id="PageMargin"
								ondblclick="callShowDiv(this);"><%=label.get("PageMargin")%></label>:</b></td>
						</tr>
						<tr>
							<td height="22" width="25%" colspan="1" align="right" ><label class="set"
								name="TopMargin" id="TopMargin"
								ondblclick="callShowDiv(this);"><%=label.get("TopMargin")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:textfield size="15"
								name="topmargin" onkeypress="return numbersOnly();" /></td>
							<td height="22" width="25%" colspan="1" align="right" ><label class="set"
								name="BottomMargin" id="BottomMargin"
								ondblclick="callShowDiv(this);"><%=label.get("BottomMargin")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:textfield size="15"
								name="bottommargin" onkeypress="return numbersOnly();" /></td>
						</tr>
						<tr>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="LeftMargin" id="LeftMargin"
								ondblclick="callShowDiv(this);"><%=label.get("LeftMargin")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:textfield size="15"
								name="leftmargin" onkeypress="return numbersOnly();" /></td>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="RightMargin" id="RightMargin"
								ondblclick="callShowDiv(this);"><%=label.get("RightMargin")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:textfield size="15"
								name="rightmargin" onkeypress="return numbersOnly();" /></td>
						</tr>
						<tr>
							<td width="100%" colspan="4"><b><label class="set"
								name="Logo" id="Logo"
								ondblclick="callShowDiv(this);"><%=label.get("Logo")%></label>:</b></td>
						</tr>
						<tr>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="LogoApplicable" id="LogoApplicable"
								ondblclick="callShowDiv(this);"><%=label.get("LogoApplicable")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:checkbox 
								name="logoapplicable" onclick="enable();" /></td>
							<td height="22" width="25%" colspan="1" align="right" id="logoalignlabel" ><label class="set"
								name="LogoAlign" id="LogoAlign"
								ondblclick="callShowDiv(this);"><%=label.get("LogoAlign")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;"
								name="logoalign" disabled="true" 
								list="#{'L':'LEFT','C':'CENTER','R':'RIGHT'}"
								/></td>
						</tr>
						<tr>
							<td width="100%" colspan="4"><b><label class="set"
								name="Companyname" id="Companyname"
								ondblclick="callShowDiv(this);"><%=label.get("Companyname")%></label>:</b></td>
						</tr>
						<tr>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="CompanyApplicable" id="CompanyApplicable"
								ondblclick="callShowDiv(this);"><%=label.get("CompanyApplicable")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:checkbox 
								name="companyapplicable" onclick="enable();" /></td>
							<td height="22" width="25%">&nbsp;</td>
							<td height="22" width="25%">&nbsp;</td>
						</tr>
						<tr id="CompanyApplicable_row" >
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="companyname_Fontface" id="companyname_Fontface"
								ondblclick="callShowDiv(this);"><%=label.get("companyname_Fontface")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;"
								name="companynamefontface"  disabled="true"
								list="#{'COURIER':'COURIER','HELVETICA':'HELVETICA','TIMES_ROMAN':'TIMES_ROMAN' }"
								/></td>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="companyname_Fontstyle" id="companyname_Fontstyle"
								ondblclick="callShowDiv(this);"><%=label.get("companyname_Fontstyle")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select  size="1" cssStyle="width:102px;"
								name="companynamefontstyle"  disabled="true" 
								list="#{'0':'NORMAL','1':'BOLD','2':'ITALIC','4':'UNDERLINE','8':'STRIKETHRU','3':'BOLDITALIC'}"
								/></td>
						</tr>
						<tr id="CompanyApplicable_row2" >
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="companyname_Fontsize" id="companyname_Fontsize"
								ondblclick="callShowDiv(this);"><%=label.get("companyname_Fontsize")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:textfield size="15"
								name="companynamefontsize"  disabled="true" onkeypress="return numbersOnly();" /></td>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="companyname_Fontalign" id="companyname_Fontalign"
								ondblclick="callShowDiv(this);"><%=label.get("companyname_Fontalign")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;"
								name="companynamefontalign"  disabled="true" 
								list="#{'L':'LEFT','C':'CENTER','R':'RIGHT'}"
								/></td>
						</tr>
						<tr>
							<td width="100%" colspan="4"><b><label class="set"
								name="CompanyAddress" id="CompanyAddress"
								ondblclick="callShowDiv(this);"><%=label.get("CompanyAddress")%></label>:</b></td>
						</tr>
						<tr>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="AddressApplicable" id="AddressApplicable"
								ondblclick="callShowDiv(this);"><%=label.get("AddressApplicable")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:checkbox 
								name="companyaddapplicable" onclick="enable();" /></td>
							<td height="22" width="25%">&nbsp;</td>
							<td height="22" width="25%">&nbsp;</td>
						</tr>
						<tr id="companyaddapplicable_row">
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="companyadd_Fontface" id="companyadd_Fontface"
								ondblclick="callShowDiv(this);"><%=label.get("companyadd_Fontface")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;"
								name="companyaddfontface"  disabled="true"
								list="#{'COURIER':'COURIER','HELVETICA':'HELVETICA','TIMES_ROMAN':'TIMES_ROMAN' }" 
								/></td>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="companyadd_Fontstyle" id="companyadd_Fontstyle"
								ondblclick="callShowDiv(this);"><%=label.get("companyadd_Fontstyle")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;" 
								name="companyaddfontstyle"  disabled="true" 
								list="#{'0':'NORMAL','1':'BOLD','2':'ITALIC','4':'UNDERLINE','8':'STRIKETHRU','3':'BOLDITALIC'}"
								/></td>
						</tr>
						<tr id="companyaddapplicable_row2">
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="companyadd_Fontsize" id="companyadd_Fontsize"
								ondblclick="callShowDiv(this);"><%=label.get("companyadd_Fontsize")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:textfield size="15"
								name="companyaddfontsize"  disabled="true" onkeypress="return numbersOnly();" /></td>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="companyadd_Fontalign" id="companyadd_Fontalign"
								ondblclick="callShowDiv(this);"><%=label.get("companyadd_Fontalign")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;"
								name="companyaddfontalign" disabled="true" 
								list="#{'L':'LEFT','C':'CENTER','R':'RIGHT'}"
								/></td>
						</tr>
						<tr>
							<td width="100%" colspan="4"><b><label class="set"
								name="ReportHeader" id="ReportHeader"
								ondblclick="callShowDiv(this);"><%=label.get("ReportHeader")%></label>:</b></td>
						</tr>
						<tr>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="Report_Fontface" id="Report_Fontface"
								ondblclick="callShowDiv(this);"><%=label.get("Report_Fontface")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;"
								name="reportfontface" 
								list="#{'COURIER':'COURIER','HELVETICA':'HELVETICA','TIMES_ROMAN':'TIMES_ROMAN' }"
								/></td>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="Report_Fontstyle" id="Report_Fontstyle"
								ondblclick="callShowDiv(this);"><%=label.get("Report_Fontstyle")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;"
								name="reportfontstyle" 
								list="#{'0':'NORMAL','1':'BOLD','2':'ITALIC','4':'UNDERLINE','8':'STRIKETHRU','3':'BOLDITALIC'}"
								/></td>
						</tr>
						<tr>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="Report_Fontsize" id="Report_Fontsize"
								ondblclick="callShowDiv(this);"><%=label.get("Report_Fontsize")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:textfield size="15"
								name="reportfontsize" onkeypress="return numbersOnly();" /></td>
							<td height="22" width="25%" colspan="1" align="right">&nbsp;</td>
							<td height="22" width="25%" colspan="1">&nbsp;</td>
						</tr>
						<tr>
							<td width="100%" colspan="4"><b><label class="set"
								name="TableHeader" id="TableHeader"
								ondblclick="callShowDiv(this);"><%=label.get("TableHeader")%></label>:</b></td>
						</tr>
						<tr>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="Table_Fontface" id="Table_Fontface"
								ondblclick="callShowDiv(this);"><%=label.get("Table_Fontface")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;"
								name="tableheaderfontface"
								list="#{'COURIER':'COURIER','HELVETICA':'HELVETICA','TIMES_ROMAN':'TIMES_ROMAN' }"
								/></td>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="Table_Fontstyle" id="Table_Fontstyle"
								ondblclick="callShowDiv(this);"><%=label.get("Table_Fontstyle")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;"
								name="tableheaderfontstyle" 
								list="#{'0':'NORMAL','1':'BOLD','2':'ITALIC','4':'UNDERLINE','8':'STRIKETHRU','3':'BOLDITALIC'}"
								/></td>
						</tr>
						<tr>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="Table_Fontsize" id="Table_Fontsize"
								ondblclick="callShowDiv(this);"><%=label.get("Table_Fontsize")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:textfield size="15"
								name="tableheaderfontsize" onkeypress="return numbersOnly();" /></td>
							<td height="22" width="25%" colspan="1" align="right">&nbsp;</td>
							<td height="22" width="25%" colspan="1" >&nbsp;</td>
						</tr>
						<tr>
							<td width="100%" colspan="4"><b><label class="set"
								name="TableBody" id="TableBody"
								ondblclick="callShowDiv(this);"><%=label.get("TableBody")%></label>:</b></td>
						</tr>
						<tr>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="TableBody_Fontface" id="TableBody_Fontface"
								ondblclick="callShowDiv(this);"><%=label.get("TableBody_Fontface")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;"
								name="tablebodyfontface" 
								list="#{'COURIER':'COURIER','HELVETICA':'HELVETICA','TIMES_ROMAN':'TIMES_ROMAN' }"
								/></td>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="TableBody_Fontstyle" id="TableBody_Fontstyle"
								ondblclick="callShowDiv(this);"><%=label.get("TableBody_Fontstyle")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:select size="1" cssStyle="width:102px;"
								name="tablebodyfontstyle" 
								list="#{'0':'NORMAL','1':'BOLD','2':'ITALIC','4':'UNDERLINE','8':'STRIKETHRU','3':'BOLDITALIC'}"
								/></td>
						</tr>
						<tr>
							<td height="22" width="25%" colspan="1" align="right"><label class="set"
								name="TableBody_Fontsize" id="TableBody_Fontsize"
								ondblclick="callShowDiv(this);"><%=label.get("TableBody_Fontsize")%></label>:</td>
							<td height="22" width="25%" colspan="1"><s:textfield size="15"
								name="tablebodyfontsize" onkeypress="return numbersOnly();" /></td>
							<td height="22" width="25%" colspan="1" align="right">&nbsp;</td>
							<td height="22" width="25%" colspan="1" >&nbsp;</td>
						</tr>
						<tr>
							<td width="100%" colspan="4">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>	
		<tr>
			<td width="100%" colspan="3">
			<table width="100%">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	
</s:form>

<script type="text/javascript">
function enable(){
	var frm = document.getElementById("paraFrm");
	if(frm.logoapplicable.checked){
		frm.logoapplicable.value="true"; 
		frm.logoalign.disabled=false;
		frm.logoalign.style.display = "";
		document.getElementById("logoalignlabel").style.display = "";
	}
	else{
		frm.logoapplicable.value="false";
		frm.logoalign.disabled=true;
		frm.logoalign.style.display = "none";
		document.getElementById("logoalignlabel").style.display= "none";
	}
	
	if(frm.companyapplicable.checked){
		frm.companyapplicable.value="true";
		frm.companynamefontface.disabled=false;
		frm.companynamefontstyle.disabled=false;
		frm.companynamefontsize.disabled=false;
		frm.companynamefontalign.disabled=false;
		document.getElementById("CompanyApplicable_row").style.display= "";
		document.getElementById("CompanyApplicable_row2").style.display= "";
	}else{
		frm.companyapplicable.value="false";
		frm.companynamefontface.disabled=true;
		frm.companynamefontstyle.disabled=true;
		frm.companynamefontsize.disabled=true;
		frm.companynamefontalign.disabled=true;
		document.getElementById("CompanyApplicable_row").style.display= "none";
		document.getElementById("CompanyApplicable_row2").style.display= "none";
	}
	if(frm.companyaddapplicable.checked){
		frm.companyaddapplicable.value="true";
		frm.companyaddfontface.disabled=false;
		frm.companyaddfontstyle.disabled=false;
		frm.companyaddfontsize.disabled=false;
		frm.companyaddfontalign.disabled=false;
		document.getElementById("companyaddapplicable_row").style.display= "";
		document.getElementById("companyaddapplicable_row2").style.display= "";
	}else{
		frm.companyaddapplicable.value="false";
		frm.companyaddfontface.disabled=true;
		frm.companyaddfontstyle.disabled=true;
		frm.companyaddfontsize.disabled=true;
		frm.companyaddfontalign.disabled=true;
		document.getElementById("companyaddapplicable_row").style.display= "none";
		document.getElementById("companyaddapplicable_row2").style.display= "none";
	}  
}

function saveFun(){
	var frm = document.getElementById("paraFrm");
	
	frm.action="ReportSettings_saveData.action";
	frm.submit();
}
function resetFun(){
	var frm = document.getElementById("paraFrm");
	frm.action="ReportSettings_reset.action";
	frm.submit();
}

enable();
</script>
