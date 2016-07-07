<%@ taglib uri="/struts-tags" prefix="s" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="ESIMaster" validate="true" id="paraFrm" name="paraFrm"
	theme="simple">
	<s:hidden name="esiMaster.esiCode" />
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
						<td width="93%" class="txt"><strong class="text_head">ESI Parameter
						  </strong></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="5" class="text_head"><strong
							class="forminnerhead">ESI Parameter</strong></td>
							<s:hidden name="esiCode" /> 
					</tr>
<!-- ---------------- -->


			<tr>
			<td  width="25%"><!-- ESI Date --><label  class = "set" name="esi.date" id="esi.date" ondblclick="callShowDiv(this);"><%=label.get("esi.date")%></label>
			<font color="red">*</font>:</td>
			<td width="25%">
			<s:textfield name="esiMaster.esiDate" theme="simple"  onkeypress="return numbersWithHiphen();" />
		 	<s:a href="javascript:NewCal('paraFrm_esiMaster_esiDate','DDMMYYYY');">
		 	<img src="../pages/images/Date.gif"   class="iconImage" height="16" align="absmiddle" width="16" id ="ctrlHide"></s:a>				
			
			<td  width="25%"><label  class = "set" name="esi.grossupto" id="esi.grossupto" ondblclick="callShowDiv(this);"><%=label.get("esi.grossupto")%></label>
			<font color="red">*</font>:</td>
			<td width="25%">
			<s:textfield 
				theme="simple" name="esiMaster.esiGross" maxlength="15"  onkeypress="return checkNumbersWithDot(this);" />	
			</td>
		</tr>
		
			<tr>
			<td  width="25%"><label  class = "set" name="esi.employee" id="esi.employee" ondblclick="callShowDiv(this);"><%=label.get("esi.employee")%></label> %
			<font color="red">*</font>:</td>
			<td width="25%"><s:textfield 
				theme="simple" name="esiMaster.esiEmp" maxlength="6"  onkeypress="return checkNumbersWithDot(this);" /></td>
			<td  width="25%"><label  class = "set" name="esi.company" id="esi.company" ondblclick="callShowDiv(this);"><%=label.get("esi.company")%></label>%
			<font color="red">*</font>:</td>
			<td width="25%"><s:textfield 
				theme="simple" name="esiMaster.esiComp" maxlength="6"  onkeypress="return checkNumbersWithDot(this);" /></td>
		</tr>
		<tr>
			<td  width="25%"><label  class = "set" name="esi.startmonth" id="esi.startmonth" ondblclick="callShowDiv(this);"><%=label.get("esi.startmonth")%></label>
			<font color="red">*</font>:</td>
			<td width="25%">
			<s:select name="startmonth" list="#{'':'----------Select--------','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
		
			</td>
			<td  width="25%"><label  class = "set" name="esi.endmonth" id="esi.endmonth" ondblclick="callShowDiv(this);"><%=label.get("esi.endmonth")%></label>
			<font color="red">*</font>:</td>
			<td width="25%">
			<s:select name="endmonth" list="#{'':'----------Select--------','1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
			</td>
	
		</tr>
		
			<tr>
			<td  width="25%"><label  class = "set" name="esi.debitCode" id="esi.debitCode" ondblclick="callShowDiv(this);"><%=label.get("esi.debitCode")%></label>
			<font color="red">*</font>:</td>
			<td width="25%"><s:hidden name="esiMaster.esiDebitCode"></s:hidden>
			<s:textfield name="esiMaster.esiDebitName" readonly="true"></s:textfield>	
					<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" id ="ctrlHide"
				width="18" theme="simple" onclick="javascript:callsF9(500,325,'ESIMaster_f9debitaction.action'); ">
		
			</td>
		
			<td width="25%"><s:hidden
				name="esiMaster.esiFormula"  /></td>
			
		</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript">
function checkNumbersWithDot(obj) {
	var count = 0;
	var txtNo = obj.value;
	
	for(var i = 0; i < txtNo.length; i++) {
		if(txtNo.charAt(i) == '.') {
			count = count + 1;
		}
	}
	
	if(count > 0) {
		if(!numbersOnly()) {
			return false;
		}
	} else if(!numbersWithDot()) {
		return false;
	}
	return true;
}
	function saveFun()
	{	
		var fieldName = ["paraFrm_esiMaster_esiDate","paraFrm_esiMaster_esiGross","paraFrm_esiMaster_esiEmp","paraFrm_esiMaster_esiComp","paraFrm_startmonth","paraFrm_endmonth","paraFrm_esiMaster_esiDebitName"];
		var lableName = ["esi.date","esi.grossupto","esi.employee","esi.company","esi.startmonth","esi.endmonth","esi.debitCode"];
		var flag=["enter","enter","enter","enter","select","select","select"];
/*		var fieldName1 = ["paraFrm_creditMaster_CreditName","paraFrm_creditMaster_CreditAbbr"];

		if(!f9specialchars(fieldName1))
		   return false;*/
		if(!validateBlank(fieldName,lableName,flag))
		   return false; 
		if(!validateDate('paraFrm_esiMaster_esiDate','esi.date'))
		{
			return false;
		}
		if(!checkPercentage('paraFrm_esiMaster_esiEmp',"esi.employee"))
		{
			document.getElementById('paraFrm_esiMaster_esiEmp').value=" ";
			return false;
		}	
		if(!checkPercentage('paraFrm_esiMaster_esiComp',"esi.company"))
		{
			document.getElementById('paraFrm_esiMaster_esiComp').value=" ";
			return false;
		}	
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='ESIMaster_save.action';
		document.getElementById("paraFrm").submit();
		
		return true;
		
	}
	function editFun()
	{
		return true;
	}
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ESIMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function resetFun() {
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'ESIMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
  	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ESIMaster_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
</script>