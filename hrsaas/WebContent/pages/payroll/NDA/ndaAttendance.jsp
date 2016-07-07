<%@ taglib prefix="s" uri="/struts-tags"%>


<s:form action="NdaAttend" id="paraFrm" validate="true" target="main" theme="simple" >
	<table width="100%">
		<tr>
			<td width="100%" colspan="4" class="pageHeader">
			<p align="center">Night Duty Allowance Attendance
			</td>
		</tr>
		<tr>
		<td></td>
		<td colspan="2">
			<table width="100%" align="center">
				<tr>
				<td align="center" colspan="2" width="25%">
					Date <font color="red">*</font>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:textfield
						label="%{getText('ndaDate')}" maxlength="10" theme="simple"
						name="ndaDate" onkeypress="return numbersonly(this);"  /> <s:a
						href="javascript:NewCal('ndaDate','DDMMYYYY');">
						<img src="../pages/images/cal.gif"  class="iconImage" height="16" align="absmiddle"
							width="16">
					</s:a></td>

				</tr>

				<tr>
				<td align="center" colspan="2" width="25%">
					Pay bill Id<font color="red">*</font>:
					<s:hidden
						theme="simple" name="pbgrp" /> <s:textfield
						label="%{getText('pbId')}" readonly="true" theme="simple"
						name="pbId" />
						<img src="../pages/images/search.gif" class="iconImage" height="18"
						align="absmiddle" width="18"
						onclick="javascript:callsF9(500,400,'NdaAttend_f9action.action');">
						</td>

				</tr>
				<tr>
				
					<td align="center" colspan="2" width="25%">
					
					<s:if test="viewFlag">
					<s:submit cssClass="pagebutton"
						action="NdaAttend_setList" theme="simple"  onclick="return datecheck();" value="  View  " />
					 
						</s:if>
						<s:if test="savFlag">
					<s:if test="insertFlag">
					<s:submit cssClass="pagebutton"
						action="NdaAttend_save" theme="simple" value="  Save  " /></s:if>
						
						<input type ="button"  
						onclick="callreport('NdaAttend_report.action')" 	value="Generate Report" />
					
					</s:if>
						
						<s:submit cssClass="pagebutton"
						action="NdaAttend_reset" theme="simple"   value="  Reset  " />
						</td>

				</tr>
			</table>
			</td>
			<td></td>
			</tr>
		
		<tr>
		<td colspan="4">

			<table width="100%">
			<s:if test="savFlag">
				<tr>
					<td class="headerCell">Sr No</td>
					<td class="headerCell">Emp Token</td>
					<td class="headerCell">Emp Name</td>
					<td class="headerCell">Shift</td>
					<td class="headerCell">Approved Nda hrs</td>

				</tr>
	         </s:if>

				<%
				int i = 1;
				%>

				<s:iterator value="iterat">


					<tr>



						<td><%=i%> <s:hidden name="empId" /> </td>
						<td><s:label value="%{empToken}" theme="simple" /></td>


						<td><s:label value="%{empName}" theme="simple" /></td>

						<td><s:label value="%{shift}" theme="simple" /></td>

						<td><s:textfield theme="simple"
							value="%{getText(ndaApprove)}" name="ndaApprove" onkeypress="return numbersonly(this);"/></td>


					</tr>

					<%
					i++;
					%>
				</s:iterator>

			</table>
			</td>

		</tr>
	</table>


</s:form>
<script>
function callreport(name)
{
document.getElementById('paraFrm').target="_blank";
				document.getElementById('paraFrm').action=name;	
				document.getElementById('paraFrm').submit();	
				document.getElementById('paraFrm').target="main";
}
function validateDate(fld) {
    var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[-](0?[13578]|1[02])[-]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[-](0?[13456789]|1[012])[-]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[-]0?2[-]((1[6-9]|[2-9]\d)?\d{2}))|(29[-]0?2[-]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
        	
    if (!((fld.match(RegExPattern)) && (fld!=''))){
        
        return false;
    
    }
    return true;
   }

function numbersonly(myfield)
	{
		var key;
		var keychar;
		if (window.event)
			key = window.event.keyCode;
		else
			return true;
		
		keychar = String.fromCharCode(key);	
		if ((("0123456789-.").indexOf(keychar) > -1))
			return true;	
		else {
			myfield.focus();
			return false;
		}
	}
	function datecheck()
	{	
	 
	
	var frmDate = document.getElementById('ndaDate').value;
	var pbId = document.getElementById('pbId').value;
	var checkFromDate = validateDate(frmDate);
	
	//==========balaji===
					if(frmDate=="")
					{
					 alert("Please Enter Date");
					 return false;
					}
					if(pbId=="")
					{
					alert("Please Select Pay bill Id");
					 return false;
					}
	//===================================				
					
	
	if(!frmDate=="")
	{
	if(!checkFromDate){
		alert (" Please enter valid  Date !\nDate should be DD-MM-YYYY format");
		return false;
	}
	}
	}
</script>
	<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
