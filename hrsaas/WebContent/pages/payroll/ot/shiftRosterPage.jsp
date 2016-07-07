<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="ShiftRoster" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<s:hidden name="totalRecords" />
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Shift
					Roster </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr valign="middle">
					<td><input type="button" class="add" value=" Save "
						onclick="return callSave();" /></td>
					<td>&nbsp;&nbsp;</td>
					<td nowrap="nowrap"><input type="button" class="add"
						value=" Reset " onclick="return resetFun();" /></td>

					<td width='100%' align="right"><b>Export :</b>&nbsp;</td>
					<td nowrap="nowrap"><a href="#" onclick="callReport('Pdf');">
					<img src="../pages/images/buttonIcons/file-pdf.png"
						class="iconImage" align="absmiddle" " title="PDF"><span
						style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
					<td nowrap="nowrap"><a href="#" onclick="callReport('Xls');">
					<img src="../pages/images/buttonIcons/file-xls.png"
						class="iconImage" align="absmiddle" onclick="callReport('Xls');"
						title="Excel"><span style="padding-left: 5px;">Excel</span></a>
					&nbsp;&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="right" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4" class="text_head"><strong
						class="forminnerhead">Define Shift for Period</strong></td>
				</tr>
				<tr>
					<td width="15%" class="formtext"><label class="set"
						id="from.date" name="from.date" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>:<font
						color="red">*</font></td>
					<td width="35%"><s:textfield name="fromDate" size="15"
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:a
						href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td> 
					<td width="10%" class="formtext"><label class="set"
						id="to.date" name="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>:<font
						color="red">*</font><s:hidden name="today" /></td>
					<td width="35%"><s:textfield name="toDate" size="15"
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <input type="button"
						class="add" value=" View " onclick="return callView();" /></td>
				</tr>
				<tr>
					<td width="15%" colspan="1"><label name="manager"
						id="manager" ondblclick="callShowDiv(this);"><%=label.get("manager")%></label>:<font
						color="red">*</font></td>
					<td width="35%" colspan="3"><s:hidden name="managerID" /><s:textfield
						name="managerToken" size="20" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /><s:textfield
						name="managerName" size="60" theme="simple" readonly="true" /> <s:if
						test="generalFlag"></s:if> <s:else>
						<img src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="18" theme="simple" id="ctrlHide"
							onclick="callsF9(500,325,'ShiftRoster_f9Manager.action');">
					</s:else></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="right" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td width="15%" colspan="1"><label name="employee"
						id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:<font
						color="red">*</font></td>
					<td width="80%" colspan="3"><s:hidden name="employeeID" /><s:textfield
						name="employeeToken" size="20" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /><s:textfield
						name="employeeName" size="60" theme="simple" readonly="true" /> <s:if
						test="generalFlag"></s:if> <s:else>
						<img id="ctrlHide" src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'ShiftRoster_f9employee.action');">
					</s:else></td>
				</tr>
				<tr>
					<td width="15%" colspan="1"><label name="shift" id="shift"
						ondblclick="callShowDiv(this);"><%=label.get("shift")%></label>:<font
						color="red">*</font></td>
					<td width="85%" colspan="2"><s:hidden name="shiftID" /> <s:textfield
						name="shiftName" readonly="true" size="25"
						cssStyle="background-color: #F2F2F2;" /> <s:if test="generalFlag"></s:if>
					<s:else>
						<img id="ctrlHide" src="../pages/images/recruitment/search2.gif"
							class="iconImage" height="18" align="absmiddle" width="18"
							onclick="javascript:callDropdown('paraFrm_shiftName',200,250,'ShiftRoster_f9shiftaction.action',event,'false','no','right')" />
					</s:else></td>
				</tr>
				<tr>
					<td width="15%" class="formtext"><label class="set"
						id="from.date" name="from.date" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>:<font
						color="red">*</font></td>
					<td width="35%"><s:textfield name="fromShiftDate" size="15" 
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:if test="generalFlag"></s:if> <s:else>
						<s:a href="javascript:NewCal('paraFrm_fromShiftDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16">
						</s:a>
					</s:else></td>
					<td width="10%" class="formtext"><label class="set"
						id="to.date" name="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>:<font
						color="red">*</font><s:hidden name="today" /></td>
					<td width="35%"><s:textfield name="toShiftDate" size="15"
						onkeypress="return numbersWithHiphen();" theme="simple"
						maxlength="10" /> <s:if test="generalFlag"></s:if> <s:else>
						<s:a href="javascript:NewCal('paraFrm_toShiftDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16">
						</s:a>
					</s:else></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4" align="center"><input type="button" class="add"
				value=" Assign " onclick="return callAdd();" /> <!--<input type="button" class="reset" value="    Reset "
									onclick="return callReset();" />--></td>
		</tr>
		<tr>
			<td colspan="8">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td colspan="7">
					<table width="100%" border="0" align="center" cellpadding="2"
						cellspacing="1">
						<tr>
							<td width="20%" align="left"><strong class="forminnerhead">Shift
							Details</strong></td>
							<td width="80%" align="right"><a href="javascript:void(0);"
								class="contlink" onclick="previousWeek();" title="Previous Week">
							[ Previous Week </a> <font color="orange"><strong>|</strong></font> <a
								href="javascript:void(0);" class="contlink"
								onclick="nextWeek();" title="Next Week"> Next Week ]</a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="sortable">
						<tr class="td_bottom_border">
							<td class="formth" width="10%"><label name="emp.id"
								id="emp.id" ondblclick="callShowDiv(this);"><%=label.get("emp.id")%></label></td>
							<td class="formth" width="20%"><label name="emp.name"
								id="emp.name" ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
							<%
								int count2 = 0;
							%>
							<s:iterator value="dateList">
								<%
									if (count2 == 5 || count2 == 6) {
								%>
								<td class="formth" width="10%"><font color="red"><s:hidden
									name="dayName" /><s:property value="dayName" /></font></td>
								<%
									} else {
								%>
								<td class="formth" width="10%"><font color="black"><s:hidden
									name="dayName" /><s:property value="dayName" /></font></td>
								<%
									}
								%>
								<%
									count2++;
								%>
							</s:iterator>
						</tr>
						<%
							int count3 = 0;
						%>
						<s:iterator value="iteratorlist" status="stat">
							<%
								int count1 = 0;
							%>
							<tr class="sortableTD">
								<td class="sortableTD" width="10%"><input type="hidden"
									name="ittShiftRosterID"
									id="paraFrm_ittShiftRosterID<%=count1%>"
									value="<s:property value="ittShiftRosterID"/>" /> <input
									type="hidden" name="ittEmployeeID"
									id="paraFrm_ittEmployeeID<%=count1%>"
									value="<s:property value="ittEmployeeID"/>" /> <s:property
									value="ittEmployeeToken" /></td>
								<td class="sortableTD" width="10%"><s:property
									value="ittEmployeeName" /></td>
								<s:iterator value="ittDateList">
									<%
										if (count1 == 5 || count1 == 6) {
									%>
									<td class="sortableTD" width="10%"><input type="hidden"
										name="ittShiftID<%=count3%>"
										id="paraFrm_ittShiftID<%=count3%><%=count1%>"
										value="<s:property value="ittShiftID"/>" /> <font
										color="red"><input type="text" size="10"
										title="Click here to change shift"
										style="border: none; cursor: pointer; color: red;"
										name="ittShiftName"
										id="paraFrm_ittShiftName<%=count3%><%=count1%>"
										value="<s:property value="ittShiftName"/>"
										onclick="javascript:callDropdown('paraFrm_ittShiftName<%=count3%><%=count1%>',200,250,'ShiftRoster_f9iTtshiftaction.action?aa=<%=count3%><%=count1%>',event,'false');">
									</font></td>
									<%
										} else {
									%>
									<td class="sortableTD" width="10%"><font color="black">
									<input type="hidden" name="ittShiftID<%=count3%>"
										id="paraFrm_ittShiftID<%=count3%><%=count1%>"
										value="<s:property value="ittShiftID"/>" /> <input
										type="text" size="10" title="Click here to change shift"
										style="border: none; cursor: pointer;" name="ittShiftName"
										id="paraFrm_ittShiftName<%=count3%><%=count1%>"
										value="<s:property value="ittShiftName"/>"
										onclick="javascript:callDropdown('paraFrm_ittShiftName<%=count3%><%=count1%>',200,250,'ShiftRoster_f9iTtshiftaction.action?aa=<%=count3%><%=count1%>',event,'false');">
									</font></td>
									<%
										}
									%>
									<%
										count1++;
									%>
								</s:iterator>

								</td>
							</tr>
							<%
								count3++;
							%>
						</s:iterator>
					</table>
					<s:if test="modeLength"></s:if> <s:else>
						<table width="100%">
							<tr>
								<td align="center"><font color="red">No Data To
								Display</font></td>
							</tr>
						</table>
					</s:else> <s:hidden name="paracode" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr valign="middle">
					<td><input type="button" class="add" value=" Save "
						onclick="return callSave();" /></td>
					<td>&nbsp;&nbsp;</td>
					<td nowrap="nowrap"><input type="button" class="add"
						value=" Reset " onclick="return resetFun();" /></td>
					<td width='100%' align="right"><b>Export :</b>&nbsp;</td>
					<td nowrap="nowrap"><a href="#" onclick="callReport('Pdf');">
					<img src="../pages/images/buttonIcons/file-pdf.png"
						class="iconImage" align="absmiddle" " title="PDF"><span
						style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
					<td nowrap="nowrap"><a href="#" onclick="callReport('Xls');">
					<img src="../pages/images/buttonIcons/file-xls.png"
						class="iconImage" align="absmiddle" onclick="callReport('Xls');"
						title="Excel"><span style="padding-left: 5px;">Excel</span></a>
					&nbsp;&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<!--<tr>
								<td colspan="5" align="center">
								
								<input type="button" class="add" value=" Save "
										onclick="return callSave();" />
								
								<input type="button" class="reset" value="    Reset "
									onclick="return callReset();" />
									
									</td>
							</tr>
							

	-->
	</table>
	<s:hidden name="rptType" />
</s:form>
<script>
	function callReport(type){
			document.getElementById('paraFrm_rptType').value=type;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='ShiftRoster_report.action';
			document.getElementById('paraFrm').submit();
	}
	function resetFun() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='ShiftRoster_reset.action';
		document.getElementById('paraFrm').submit();
	}

	function callView(){
			document.getElementById('paraFrm').target="main";		  
		   	document.getElementById("paraFrm").action="ShiftRoster_view.action";
		    document.getElementById("paraFrm").submit();
		    document.getElementById('paraFrm').target="main";
	}

	function callAdd(){
	try{
	///Date Shift for period start
		var fDate=document.getElementById('paraFrm_fromDate').value;
 		var tDate=document.getElementById('paraFrm_toDate').value;
 		if(trim(document.getElementById('paraFrm_fromDate').value) == "") {
				alert("Please select or enter "+document.getElementById('from.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
		}
		if(trim(document.getElementById('paraFrm_toDate').value) == "") {
				alert("Please select or enter "+document.getElementById('to.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_toDate').focus();
				return false;
		}
  		if(!validateDate("paraFrm_fromDate","from.date")){
  			return false;
  		
  		}
  		if(!validateDate("paraFrm_toDate","to.date")){
		return false;
		}
		
		if(!dateDifferenceEqual(fDate, tDate, 'paraFrm_toDate', 'from.date', 'to.date')){
		return false;
		}
	///Date Shift for period end
	
	var empCodeVar = document.getElementById('paraFrm_employeeID').value;
		if(empCodeVar=="")
		{
				alert("Please select Employee.");
		  		document.getElementById('paraFrm_employeeName').focus();
		 		return false;
		 }	
		 	
		var shiftCodeVar = document.getElementById('paraFrm_shiftID').value;
		if(shiftCodeVar=="")
		{
				alert("Please select Shift.");
		  		document.getElementById('paraFrm_shiftName').focus();
		 		return false;
		 }
		
	/// Assign Date Shift for period start
	var fDate=document.getElementById('paraFrm_fromShiftDate').value;
 		var tDate=document.getElementById('paraFrm_toShiftDate').value;
 
 		if(trim(document.getElementById('paraFrm_fromShiftDate').value) == "") {
				alert("Please select or enter "+document.getElementById('from.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromShiftDate').focus();
				return false;
		}
		
		if(trim(document.getElementById('paraFrm_toShiftDate').value) == "") {
				alert("Please select or enter "+document.getElementById('to.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_toShiftDate').focus();
				return false;
		}
		
  		if(!validateDate("paraFrm_fromShiftDate","from.date")){
  			return false;
  		
  		}
  		if(!validateDate("paraFrm_toShiftDate","to.date")){
		return false;
		}
		
		if(!dateDifferenceEqual(fDate, tDate, 'paraFrm_toShiftDate', 'from.date', 'to.date')){
		return false;
		}
	/// Assign Date Shift for period end
			
		var fromDate = fDate.split("-");	
		///	alert("from date="+fromDate[0]);
		///	alert("from month="+fromDate[1]);
			
		var toDate = tDate.split("-");	
		///	alert("to date="+toDate[0]);
		///	alert("to month="+toDate[1]);
		
		if(toDate[1]>fromDate[1]){
			var monthCount = toDate[1]-fromDate[1];
			
			if(monthCount!=1){
			
				alert("You can assign shift only for one month.");
				document.getElementById('paraFrm_toShiftDate').value="";
				document.getElementById('paraFrm_toShiftDate').focus();
				return false;
			}
			if(toDate[0]>fromDate[0]){
				alert("You can assign shift only for 30 days.");
				document.getElementById('paraFrm_toShiftDate').value="";
				document.getElementById('paraFrm_toShiftDate').focus();
				return false;
			}
			
		}	
			
				document.getElementById('paraFrm').target="main";		  
			   	document.getElementById("paraFrm").action="ShiftRoster_assignShift.action";
			    document.getElementById("paraFrm").submit();
			   document.getElementById('paraFrm').target="main";
		}catch(e){
		///alert(e);
		}
	}
 
	 function nextWeek(){
			 document.getElementById('paraFrm').target="main";	
			document.getElementById("paraFrm").action="ShiftRoster_nextWeek.action";
		    document.getElementById("paraFrm").submit();
		    document.getElementById('paraFrm').target="main";
	 }
 
	 function previousWeek(){
	 	 	document.getElementById('paraFrm').target="main";	
	 	 	document.getElementById("paraFrm").action="ShiftRoster_previousWeek.action";
		    document.getElementById("paraFrm").submit();
		    document.getElementById('paraFrm').target="main";
	 }
	 
	 function setFieldId(event,id,action,textFieldName){
		try{
				//document.getElementById('paraFrm_fieldName').value=id;
		        //callsF9(500,325,action);
		        callDropdown(textFieldName,200,250,action,event,'false');
		        }catch(e){alert(e);}
		}
	
	function callSave(){
		var con=confirm('Do you want to save the shift detail(s) ?');
		 if(con){
	
		 var mngCodeVar = document.getElementById('paraFrm_managerID').value;
			if(mngCodeVar=="")
			{
					alert("Please select Manager.");
			  		document.getElementById('paraFrm_managerName').focus();
			 		return false;
			 }
			 
			 var empCodeVar = document.getElementById('paraFrm_totalRecords').value;
			if(empCodeVar=="0")
			{
					alert("Please assign shift details first.");
			  		///document.getElementById('paraFrm_employeeName').focus();
			 		return false;
			 }
				
			 
			document.getElementById('paraFrm').target="main";		  
		   	document.getElementById("paraFrm").action="ShiftRoster_saveAssignShift.action";
		    document.getElementById("paraFrm").submit();
		    document.getElementById('paraFrm').target="main";
	    }
	}
	
</script>