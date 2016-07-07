<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/attendance/Ajax.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<div align="center" id="overlay"
	style="z-index: 3; visibility: hidden; position: absolute; width: 776px; height: 200px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid :   DXImageTransform .   Microsoft .   alpha(opacity =   15); -moz-opacity: .1; opacity: .1;">
</div>
<s:form action="LeaveEntitle" method="post" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2" n
				cellspacing="2" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="100%" class="txt"><strong class="text_head">Leave
					Entitle Process </strong></td>
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
					<s:if test="%{entitle.generalFlag}">
						<td width="78%"><s:submit cssClass="reset"
							action="LeaveEntitle_clear" value=" Reset " /></td>
					</s:if>
					<s:else>
						<td width="78%"><s:submit cssClass="reset"
							action="LeaveEntitle_process" onclick="return callProcess();"
							value="  Process " /><s:submit cssClass="reset"
							action="LeaveEntitle_clear" value="  Reset " /></td>
					</s:else>
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
					<td width="15%" colspan="1" height="22"><label name="division" id="division"
						ondblclick="callShowDiv(this);"><%=label.get("division")%></label><span
						class="formtext"> </span><span class="style2"> <font
						color="red"> *</font> :</span></td>
					<td colspan="2" width="50%"><s:hidden name="divisionCode" />
					<s:textfield name="divisionName" readonly="true"
						cssStyle="background-color: #F2F2F2;" size="25"/> <img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage"
						onclick="javascript:callsF9(500,325,'LeaveEntitle_f9divisionaction.action');" />
					</td>
				</tr>
				<tr>
					<td width="15%" colspan="1" height="22"><label name="month" id="month"
						ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font
						color="red"> *</font>:</td>
					<td colspan="2" width="50%"><s:select theme="simple"
						name="entitle.month" cssStyle="width:140"
						list="#{'0':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" /></td>
				</tr>
				<tr>
					<td width="15%" colspan="1" height="22"><label name="year" id="year"
						ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font
						color="red"> *</font>:</td>
					<td colspan="2" width="50%"><s:textfield name="entitle.year"
						maxlength="4" onkeypress="return numbersOnly();" size="25"/></td>
				</tr>
				<tr>
					<td width="15%" colspan="1" height="22"><label name="entitledt" id="entitledt"
						ondblclick="callShowDiv(this);"><%=label.get("entitledt")%></label>
					:</td>
					<td colspan="2" width="50%"><s:textfield theme="simple" readonly="true"
						name="entitleDate" size="25" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
		if ((("0123456789").indexOf(keychar) > -1))
			return true;	
		else {
			myfield.focus();
			
			return false;
		}
	}
	
	
	
	function callProcess(){
		
		try
		{
		var month = document.getElementById('paraFrm_entitle_month').value;
		var year = document.getElementById('paraFrm_entitle_year').value;
		var division = document.getElementById('paraFrm_divisionName').value;
		
			
		var n=new Number(year);
		var str=n.toString();
		
		if(str=='NaN'){
	
			alert("Please enter only numbers in "+document.getElementById('year').innerHTML.toLowerCase());
			
			return false;
		}
		
		if(division==""){
		alert('Please select '+document.getElementById('division').innerHTML.toLowerCase());
		document.getElementById('paraFrm_divisionName').focus();
		return false;
		}
			
		if(month==0){
		alert('Please select '+document.getElementById('month').innerHTML.toLowerCase());
		document.getElementById('paraFrm_entitle_month').focus();
		return false;
		}
		if(year==""){
		alert('Please enter '+document.getElementById('year').innerHTML.toLowerCase());
		document.getElementById('paraFrm_entitle_year').focus();
		return false;
		}
		if(year.length < 4)
		{
		alert(document.getElementById('year').innerHTML.toLowerCase()+" should have atleast 4 digits");
		return false;
		}
			document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		
		return true;
		
		}
		catch(e)
		{
		}
	}
	
	
</script>