<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="OutdoorVisit" validate="true" id="paraFrm" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Attendance Record </strong><s:hidden name="flagHrs" /></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td width="100%">
				<input type="button" name="Close" value="Close" class="cancel" onclick="closeWindowFunction();">
			</td>
		</tr>

		
		<!-- Attendance Record Begins -->
	<s:if test="dataAvailableAttendanceFlag">	
		<tr>
			<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"	class="formbg">
					<tr><td colspan="10"><b>Attendance Record : </b></td></tr>
					<tr>
						<td align="center" class="formth"><b> Date </b></td>
						<td align="center" class="formth"><b>Shift Name </b></td>
						<td align="center" class="formth"><b>In Time </b></td>
						<td align="center" class="formth"><b>Out Time </b></td>
						<td align="center" class="formth"><b>Working Hours </b></td>
						<td align="center" class="formth"><b>Extra Hours </b></td>
						<td align="center" class="formth"><b>Late Hours </b></td>
						<td align="center" class="formth"><b>Early Hours </b></td>
						<td align="center" class="formth"><b>Status One </b></td>
						<td align="center" class="formth"><b>Status Two </b></td>
					</tr>
					
					<tr>
						<td align="center" class="sortableTD">
							<input type="text" style="border: none;" readonly="readonly" name="attDate" value='<s:property value="attDate"/>' size="10%">&nbsp; 
						</td>
						<td align="center" class="sortableTD">
							<input type="text" readonly="readonly"  style="border: none;" name="attShiftName" value='<s:property value="attShiftName"/>' size="10%">&nbsp; 
						</td>
						<td align="center" class="sortableTD">
							<input type="text" name="attInTime" readonly="readonly"  style="border: none;" value='<s:property value="attInTime"/>' size="8%">&nbsp; 
						</td>
						<td align="center" class="sortableTD">
							<input type="text" style="border: none;" readonly="readonly" name="attOutTime" value='<s:property value="attOutTime"/>' size="8%">&nbsp; 
						</td>
						<td align="center" class="sortableTD">
							<input type="text" readonly="readonly" style="border: none;" name="attWorkingHours"  value='<s:property value="attWorkingHours"/>' size="8%">&nbsp; 
						</td>
						<td align="center" class="sortableTD">
							<input type="text" readonly="readonly"  style="border: none;" name="attExtraHours" value='<s:property value="attExtraHours"/>' size="8%">&nbsp; 
						</td>
						<td align="center" class="sortableTD">
							<input type="text" readonly="readonly" style="border: none;" name="attLateHours" value='<s:property value="attLateHours"/>' size="8%">&nbsp; 
						</td>
						<td align="center" class="sortableTD">
							<input type="text" readonly="readonly" style="border: none;" name="attEarlyHours" value='<s:property value="attEarlyHours"/>' size="8%">&nbsp; 
						</td>
						<td align="center" class="sortableTD">
							<input type="text" readonly="readonly" style="border: none;" name="attStatusOne" value='<s:property value="attStatusOne"/>' size="10%">&nbsp; 
						</td>
						<td align="center" class="sortableTD">
							<input type="text" readonly="readonly" style="border: none;" name="attStatusTwo" value='<s:property value="attStatusTwo"/>' size="10%">&nbsp; 
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</s:if>
		
	<s:if test="noDataAvailableAttendanceFlag">
		<tr>
			<td colspan="10" align="center">
				<font color="red" ><b>No record is available for <s:property value="attDate"/>. </b></font>
			</td>
		</tr>
	</s:if>
		<!-- Attendance Record Ends -->


		<tr>
			<td width="100%">
				<input type="button" name="Close" value="Close" class="cancel" onclick="closeWindowFunction();">
			</td>
		</tr>
	</table>
</s:form>



<script type="text/javascript">

function closeWindowFunction(){
	try{
		window.close();
	}catch(e){
		alert("Exception occured in hideAttendanceFunction() : "+e);
	}
}

</script>
