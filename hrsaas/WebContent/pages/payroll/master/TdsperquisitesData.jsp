<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:hidden name="tdsperquisitesname" />
<s:hidden name="perquisitesAbbr" />
<s:hidden name="variance" />
<s:hidden name="taxable" />
<s:form action="Tdsperquisites" validate="true" id="paraFrm"
	name="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Tds
					Perquisites   </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Tds Perquisites</strong></td>
				</tr>
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="perquisites.name" id="perquisites.name"
						ondblclick="callShowDiv(this);"><%=label.get("perquisites.name")%></label>
					<font color="red">*</font>:</td>
					<td colspan="2" width="55%"><s:textfield size="30"
						maxlength="55" theme="simple" name="tdsperquisitesname"
						onkeypress="return allCharacters();" /></td>
					<s:hidden name="tdsperquisitescode" />
				</tr>
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="perquisites.abbr" id="perquisites.abbr"
						ondblclick="callShowDiv(this);"><%=label.get("perquisites.abbr")%></label><font
						color="red">*</font>:</td>
					<td colspan="2" width="55%"><s:textfield maxlength="10"
						size="30" theme="simple" name="perquisitesAbbr"
						onkeypress="return allCharacters();" /></td>

				</tr>

				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="perquisites.period" id="perquisites.period"
						ondblclick="callShowDiv(this);"><%=label.get("perquisites.period")%></label>:</td>
					<td colspan="2" width="55%"><s:select name="variance"
						theme="simple"
						list="#{'M':'Monthly','Q':'Quarterly','A':'Annually','H':'Half Yearly'}" />
					</td>
				</tr>
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="perquisites.taxable" id="perquisites.taxable"
						ondblclick="callShowDiv(this);"><%=label.get("perquisites.taxable")%></label>:</td>
					<td colspan="2" width="55%"><s:select name="taxable"
						theme="simple" list="#{'Y':'YES','N':'NO'}" /></td>
				</tr>


				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="credit.exempttax" id="credit.exempttax"
						ondblclick="callShowDiv(this);"><%=label.get("credit.exempttax")%></label>:
					</td>
					<td colspan="2" width="55%"><s:select name="Creditexempt"
						theme="simple" headerKey=" " headerValue="--Select--"
						list="#{'Y':'Yes','N':'No'}" /></td>
				</tr>

				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="credit.exemptundersec" id="credit.exemptundersec"
						ondblclick="callShowDiv(this);"><%=label.get("credit.exemptundersec")%></label>:
					</td>
					<td colspan="2" width="55%"><s:textfield
						name="exemptSectionNo" readonly="true" size="35" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						id="ctrlHide" width="16"
						onclick="javascript:callsF9(500,325,'Tdsperquisites_f9taxaction.action');">

					<s:hidden name="taxCode" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
	function saveFun()
	{	
		try{
		var fieldName = ["paraFrm_tdsperquisitesname","paraFrm_perquisitesAbbr"];
		var lableName = ["perquisites.name","perquisites.abbr"];
		var badflag = ["enter","enter"];
		var fieldName1 = ["paraFrm_tdsperquisitesname","paraFrm_perquisitesAbbr"];
		
		var creditExemptUnderSect = document.getElementById('paraFrm_taxCode').value;
		
		if(!validateBlank(fieldName,lableName,badflag))
	        return false;
		if(!f9specialchars(fieldName1))
       		return false;
       		
       			if(document.getElementById('paraFrm_Creditexempt').value=="Y")
       	{
       		if(creditExemptUnderSect=="")
       		{
       				 
      	alert("Please select "+document.getElementById('credit.exemptundersec').innerHTML.toLowerCase()); 	
      		return false ;			
       		}
       	}

		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='Tdsperquisites_save.action';
		document.getElementById("paraFrm").submit();
		}catch(e)
		{
			alert("value of e"+e);
		}
		return true;
		
	}
	function editFun()
	{
		return true;
	}
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'Tdsperquisites_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function resetFun() {
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = "Tdsperquisites_reset.action";
		document.getElementById('paraFrm').submit();
  	}
  	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'Tdsperquisites_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
</script>