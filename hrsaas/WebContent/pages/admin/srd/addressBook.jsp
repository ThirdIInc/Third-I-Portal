<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="AddressBook" method="post" id="paraFrm">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<s:hidden name="addressBook.empId" />
		<tr>
			<td colspan="3" valign="bottom" class="txt">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom"
				background="../pages/common/css/default/images/lines.gif"
				class="txt"><img
				src="../pages/common/css/default/images/lines.gif" width="16"
				height="1" /></td>
		</tr>
		<tr>
			<td colspan="3" valign="bottom" class="txt"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="5" /></td>
		</tr>
		<tr>
			<td valign="bottom" class="txt"><strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Address Book
			 </strong></td>
			<td width="3%" valign="top" class="txt"><!-- <div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
			</td>-->
		</tr>
		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">



				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%" colspan="1"><s:if
								test="%{addressBook.viewFlag}">
								<input type="button" class="token"
									onclick="return callMisReport();"
									value="    Address Book Report"  />
							</s:if> <s:submit cssClass="reset" action="AddressBook_clear"
								theme="simple" value="    Reset" /></td>
							
						</tr>
					</table>
					<label></label></td>
				</tr>





				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<!--  <tr>
							<td colspan="3">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">-->

						<!-- 	<tr colspan="4">
									<td width="14%">Select Employee :</td>
									<td width="70%" colspan="3"><s:textfield theme="simple"
										name="addressBook.token" size="16" readonly="true" /><s:textfield
										theme="simple" name="addressBook.empName" size="80"
										readonly="true" /><s:if test="%{addressBook.generalFlag}">
									</s:if> <s:else>
										<img src="../pages/images/recruitment/search2.gif" height="18"
											align="absmiddle" width="18"
											onclick="javascript:callsF9(500,325,'AddressBook_f9action.action');">
									</s:else></td>


								</tr>
								<tr colspan="4">
									<td width="13%">Department:</td>
									<td width="15%"><s:textfield theme="simple"
										name="addressBook.deptName" size="25" readonly="true" /></td>
									<td width="13%">Designation :</td>
									<td width="15%"><s:textfield theme="simple"
										name="addressBook.rank" size="25" readonly="true" /></td>
								</tr> -->




					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td>Select Branch :</td>
									<td><s:hidden name="addressBook.centerNo"
										value="%{addressBook.centerNo}" theme="simple" /> <s:textfield
										name="addressBook.centerName" theme="simple" size="25"
										readonly="true" /> 
									<img src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'AddressBook_f9center.action');">

									</td>
									<td>Select Division :</td>
									<td><s:hidden name="divCode" /> <s:textfield
										name="divsion" theme="simple" size="25" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'AddressBook_f9div.action');">
									</td>
								</tr>
								<tr>
									<td>Select Department :</td>
									<td><s:hidden name="addressBook.deptCode"
										value="%{addressBook.deptCode}" theme="simple" /> <s:textfield
										name="addressBook.deptName1" theme="simple" size="25"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'AddressBook_f9dept.action');">
									</td>
									<td>Select Designation :</td>
									<td><s:hidden name="addressBook.desgCode"
										value="%{addressBook.desgCode}" theme="simple" /> <s:textfield
										name="addressBook.desgName1" theme="simple" size="25"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="18"
										align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'AddressBook_f9desg.action');">
									</td>
								</tr>
								



								<tr>									
									<td>Report Type :</td>
									<td><s:select theme="simple" name="addressBook.reportType" 
									list="#{'Pdf':'Pdf','Xls':'Xls', 'Txt':'Text'}" /></td>
																								
									<td width="14%">Select Employee :</td>
									<td ><s:hidden theme="simple"
										name="addressBook.token" /><s:textfield
										theme="simple" name="addressBook.empName" size="25"
										readonly="true" />
										<img src="../pages/images/recruitment/search2.gif" height="18"
											align="absmiddle" width="18"
											onclick="javascript:callsF9(500,325,'AddressBook_f9action.action');">
																
									<td width="15%"><s:hidden theme="simple"
										name="addressBook.deptName"  /></td>
									
									<td width="15%"><s:hidden theme="simple"
										name="addressBook.rank"  /></td>
								</tr>
								
							</table>
							</td>
						</tr>


					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>




				<tr>
					<td colspan="3"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
				</tr>
			</table>
			<br />


			<label></label></td>
		</tr>
	</table>

</s:form>

<script type="text/javascript">

	function callMisReport(){
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='AddressBook_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	}
	

</script>

