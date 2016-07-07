<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="AutoPresentAttendance" validate="true" id="paraFrm" theme="simple">
	<s:hidden name="show" /><s:hidden name="myPage" id="myPage" /><s:hidden name="rowId" /><s:hidden name="isNewrecord" />
	<table width="100%" class="formbg" align="right">
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Attendance AutoPresent</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="80%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td>
							<table width="100%">
								<tr>
									<td width="10%" nowrap="nowrap">
										<label id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :
										<font color="red">*</font>
									</td>
									<td>
										<s:hidden name="empId" />
										
										<s:textfield name="eToken" cssStyle="background-color: #F2F2F2;" size="20" />
										
										<s:textfield theme="simple" name="empName" readonly="true" size="50" cssStyle="background-color: #F2F2F2;" />
										
										<img id='ctrlHide' src="../pages/images/search2.gif" class="iconImage" height="18" align="absmiddle" width="18" 
										onclick="javascript:callsF9(500,325,'AutoPresentAttendance_f9empaction.action');">
									</td>
								</tr>
							</table>
							<table width="100%">
								<tr>
									<td width="10%">
										<label id="waive1" name="waive1" ondblclick="callShowDiv(this);"><%=label.get("waive1")%></label> :
									</td>
									<td width="30%">
										<s:hidden name="waiveOffLate" />
										
										<input type="checkbox" class="checkbox" name="waiveOff" id="waiveOff1" onclick="callCheck('1')" />
									</td>
									<td>
										<label id="waive2" name="waive2" ondblclick="callShowDiv(this);"><%=label.get("waive2")%></label> :
									</td>
									<td width="30%">
										<s:hidden name="waiveOffHalfday" />
										
										<input type="checkbox" class="checkbox" name="waiveOff" id="waiveOff2" onclick="callCheck('2')" />
									</td>
									<td>
										<label id="waive3" name="waive3" ondblclick="callShowDiv(this);"><%=label.get("waive3")%></label> :
									</td>
									<td>
										<s:hidden name="waiveOffAbsent" />
										
										<input type="checkbox" class="checkbox" name="waiveOff" id="waiveOff3" onclick="callCheck('3')" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	function callCheck(id) {
		if(id == "1") {
			if(document.getElementById("waiveOff" + id).checked == true) { 
				document.getElementById("paraFrm_waiveOffLate").value = "Y";
			} else {
				document.getElementById("paraFrm_waiveOffLate").value = "N";
				document.getElementById("waiveOff" + id).checked = false;
			}
		}
		
		if(id == "2") {
			if(document.getElementById("waiveOff" + id).checked == true) { 
				document.getElementById("paraFrm_waiveOffHalfday").value = "Y";
			} else {
		  		document.getElementById("paraFrm_waiveOffHalfday").value = "N";
		  		document.getElementById("waiveOff" + id).checked = false;
		  	}
	  	}
	  
	  	if(id == "3") {
			if(document.getElementById("waiveOff" + id).checked == true) {
				document.getElementById("paraFrm_waiveOffAbsent").value = "Y";
			} else {
				document.getElementById("paraFrm_waiveOffAbsent").value = "N";
		  		//document.getElementById("waiveOff" + id).checked = false;
		  	}
	  	}
	}
	
	myonload();

	function myonload() {
		try {
			if(document.getElementById('paraFrm_waiveOffLate').value == "Y") {
				document.getElementById('waiveOff1').checked = true;
			} else {
				document.getElementById('waiveOff1').checked = false;
			}
	
			if(document.getElementById('paraFrm_waiveOffHalfday').value == "Y") {
				document.getElementById('waiveOff2').checked = true;
			} else {
				document.getElementById('waiveOff2').checked = false;
			}
	
			if(document.getElementById('paraFrm_waiveOffAbsent').value == "Y") {
				document.getElementById('waiveOff3').checked = true;
			} else {
				document.getElementById('waiveOff3').checked = false;
			}
		} catch(e) {
			alert(e);
		}
	}

	function callAdd() {
		if(document.getElementById('paraFrm_empName').value == "") {
  			alert("Please select the employee.");
  			return false;
		}
			
		if(!(document.getElementById('paraFrm_waiveOffLate').checked || document.getElementById('paraFrm_waiveOffHalfday').checked || 
		document.getElementById('paraFrm_waiveOffAbsent').checked )) {
			alert("Please select atleast one waiveoff Option.");
			return false;
		}	

		return false;
	}

	function saveFun() {
 		if(document.getElementById('paraFrm_empName').value == "") {
			alert("Please select the employee.");
			return false;
		}
			
		if(!(document.getElementById('waiveOff1').checked || document.getElementById('waiveOff2').checked || 
		document.getElementById('waiveOff3').checked )) {
			alert("Please select atleast one waiveoff Option.");
			return false;
		}	
			
		if(document.getElementById('waiveOff1').checked) {
			document.getElementById('paraFrm_waiveOffLate').value = "Y";
		} else {
			document.getElementById('paraFrm_waiveOffLate').value = "N";
		}
	
		if(document.getElementById('waiveOff2').checked) {
			document.getElementById('paraFrm_waiveOffHalfday').value = "Y";
		} else {
			document.getElementById('paraFrm_waiveOffHalfday').value = "N";
		}
	
		if(document.getElementById('waiveOff3').checked) {
			document.getElementById('paraFrm_waiveOffAbsent').value = "Y";
		} else {
			document.getElementById('paraFrm_waiveOffAbsent').value = "N";
		}
	
		document.getElementById('paraFrm').target = "_self";
  	   	document.getElementById('paraFrm').action ='AutoPresentAttendance_save.action';
     	document.getElementById('paraFrm').submit(); 
	}
		
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';  	 
  	 	document.getElementById('paraFrm').action = 'AutoPresentAttendance_reset.action';
  	 	document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'AutoPresentAttendance_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
 		var con = confirm('Do you want to delete the record(s)?');
	 	if(con) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'AutoPresentAttendance_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function callSearch(action) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = 'AutoPresentAttendance_' + action + '.action';
		document.getElementById("paraFrm").submit();
	}
	
	function editFun() {
		return true;
	}
</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>