//global variables
var req;
var which;

function deleteRecord(url, formName) {
	try {
		url = url + getFieldValuesForDelete(formName) + '&' + Math.random();
	} catch(e) {
		alert(e);
	}
	if (window.XMLHttpRequest) { // Non-IE browsers
		req = new XMLHttpRequest(); // XMLHttpRequest object is created
	    req.onreadystatechange = processStateChangeForDelete; // XMLHttpRequest object is configured with a callback function
	    try {
	    	/**
	    	 * open("HTTP method", "URL", syn/asyn), for asyn-true
	    	 * if false, send operations are synchronous, browser doesn't accept any input/output
	    	**/
	    	req.open("GET", url, true);
	    } catch (e) {
			alert("Problem Communicating with Server\n"+e);
		}
		req.send(null);
	} else if (window.ActiveXObject) { // IE 
		req = new ActiveXObject("Microsoft.XMLHTTP");
	    if (req) {
	    	req.onreadystatechange = processStateChangeForDelete;
	      	req.open("GET", url, true);
	       	req.send();
	    }
	}
}

function getFieldValuesForDelete(formName) {
	var returnString = '';
	try {
		var formElements = document.forms[formName].elements;
		for (var i = formElements.length - 1; i >= 0; i--) {
 			if(formElements[i].name == 'month' || formElements[i].name == 'year' || formElements[i].name == 'deletedRecords' || 
 			formElements[i].name == 'attendanceCode') {
 				returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
 			}
 		}
	} catch(e) {
		alert(e);
	}
	return returnString;
}
	
function processStateChangeForDelete() {
	// 0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
	if(req.readyState == 4) { // Complete
		if (req.status == 200) { // OK response
	    	//responseXML: XML document of data returned from the server
	    	var res = req.responseText; // String version of data returned from the server
	    	alert(res);
		}
		parent.frames[2].name = 'main';
	}
}

function addRecord(url, formName) {
	try {
		url = url + getFieldValuesForAdd(formName) + '&' + Math.random();
	} catch(e) {
		alert(e);
	}
	if (window.XMLHttpRequest) { // Non-IE browsers
		req = new XMLHttpRequest(); // XMLHttpRequest object is created
	    req.onreadystatechange = processStateChangeForAdd; // XMLHttpRequest object is configured with a callback function
	    try {
	    	/**
	    	 * open("HTTP method", "URL", syn/asyn), for asyn-true
	    	 * if false, send operations are synchronous, browser doesn't accept any input/output
	    	**/
	    	req.open("GET", url, true);
	    } catch (e) {
			alert("Problem Communicating with Server\n"+e);
		}
		req.send(null);
	} else if (window.ActiveXObject) { // IE 
		req = new ActiveXObject("Microsoft.XMLHTTP");
	    if (req) {
	    	req.onreadystatechange = processStateChangeForAdd;
	      	req.open("GET", url, true);
	       	req.send();
	    }
	}
}

function getFieldValuesForAdd(formName) {
	var returnString = '';
	try {
		var formElements = document.forms[formName].elements;
		for (var i = formElements.length - 1; i >= 0; i--) {
 			if(formElements[i].name == 'month' || formElements[i].name == 'year' || formElements[i].name == 'newEmpId' || 
 			formElements[i].name == 'newEmpToken' || formElements[i].name == 'newEmpName' || formElements[i].name == 'newEmpBranchName' || 
 			formElements[i].name == 'newEmpBranch' || formElements[i].name == 'newEmpDiv' || formElements[i].name == 'newEmpDept' || 
 			formElements[i].name == 'newEmpDesg' || formElements[i].name == 'newEmpType' || formElements[i].name == 'newEmpShift' || 
 			formElements[i].name == 'newEmpJoinDate' || formElements[i].name == 'attendanceCode') {
 				returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
 			}
 		}
	} catch(e) {
		alert(e);
	}
	return returnString;
}

function processStateChangeForAdd() {
	// 0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
	if(req.readyState == 4) { // Complete
		if (req.status == 200) { // OK response
	    	//responseXML: XML document of data returned from the server
	    	var addedRecord = req.responseText.split('@'); // String version of data returned from the server
	    	
	    	if(addedRecord[0] != 'N') {
	    		addRow(addedRecord[0]);
	    	}
	    	alert(addedRecord[1]);
	    	
	    	document.getElementById('paraFrm_newEmpId').value = '';
	    	document.getElementById('paraFrm_newEmpToken').value = '';
	    	document.getElementById('paraFrm_newEmpName').value = '';
	    	document.getElementById('paraFrm_newEmpBranchName').value = '';
	    	document.getElementById('paraFrm_newEmpBranch').value = '';
	    	document.getElementById('paraFrm_newEmpDiv').value = '';
	    	document.getElementById('paraFrm_newEmpDept').value = '';
	    	document.getElementById('paraFrm_newEmpDesg').value = '';
	    	document.getElementById('paraFrm_newEmpType').value = '';
	    	document.getElementById('paraFrm_newEmpShift').value = '';
	    	document.getElementById('paraFrm_newEmpJoinDate').value = '';
		}
		parent.frames[2].name = 'main';
	}
}

function addRow(attendanceRecord) {
	if(attendanceRecord != '') {
		var record = attendanceRecord.split(',');
		
		if(record.length > 0) {
			var recordNo = document.getElementById('recordNo').value;
		
			var tblAttendance = document.getElementById('tblAttendance');
			var lastRow = tblAttendance.rows.length;
			var row = tblAttendance.insertRow(lastRow);
			row.id = 'trRecord' + recordNo;
			row.style.background = '#33FFFF';
			
			// empId and empToken
			var empId = record[0];
			
			var cellEmpToken = row.insertCell(0);
			cellEmpToken.className ='sortableTD';
			cellEmpToken.align = 'left';
			cellEmpToken.noWrap = 'true';
			
			var hdnEmpId = document.createElement('input');
			hdnEmpId.type = 'hidden';
			hdnEmpId.name = 'empId';
			hdnEmpId.id = 'empId' + recordNo;
			hdnEmpId.value = empId;
			cellEmpToken.appendChild(hdnEmpId);
			
			var empToken = record[1];
			
			var hdnEmpToken = document.createElement('input');
			hdnEmpToken.type = 'hidden';
			hdnEmpToken.name = 'empToken';
			hdnEmpToken.id = 'empToken' + recordNo;
			hdnEmpToken.value = empToken;
			
			var lblEmpToken = document.createElement('label');
			lblEmpToken.id = 'lblEmpToken' + recordNo;
			lblEmpToken.innerHTML = empToken;
			cellEmpToken.appendChild(lblEmpToken);
			
			// empName
			var empName = record[2];
			
			var cellEmpName = row.insertCell(1);
			cellEmpName.className='sortableTD';
			cellEmpName.align = 'left';
			cellEmpName.noWrap = 'true';
			
			var hdnEmpName = document.createElement('input');
			hdnEmpName.type = 'hidden';
			hdnEmpName.name = 'empName';
			hdnEmpName.id = 'empName' + recordNo;
			hdnEmpName.value = empName;
			cellEmpName.appendChild(hdnEmpName);
			
			var lblEmpName = document.createElement('label');
			lblEmpName.id = 'lblEmpName' + recordNo;
			lblEmpName.innerHTML = empName;
			cellEmpName.appendChild(lblEmpName);
			
			// empBranch
			var empBranch = record[3];
			
			var cellEmpBranch = row.insertCell(2);
			cellEmpBranch.className='sortableTD';
			cellEmpBranch.align = 'left';
			
			var hdnEmpBranch = document.createElement('input');
			hdnEmpBranch.type = 'hidden';
			hdnEmpBranch.name = 'empBranch';
			hdnEmpBranch.id = 'empBranch' + recordNo;
			hdnEmpBranch.value = empBranch;
			cellEmpBranch.appendChild(hdnEmpBranch);			
			
			var lblEmpBranch = document.createElement('label');
			lblEmpBranch.id = 'lblEmpBranch' + recordNo;
			lblEmpBranch.innerHTML = empBranch;
			cellEmpBranch.appendChild(lblEmpBranch);
			
			// attendanceDays
			var attendanceDays = record[4];
			
			var cellAttendanceDays = row.insertCell(3);
			cellAttendanceDays.className='sortableTD';
			cellAttendanceDays.align = 'right';
			cellAttendanceDays.noWrap = 'true';
			
			var hdnAttendanceDays = document.createElement('input');
			hdnAttendanceDays.type = 'hidden';
			hdnAttendanceDays.name = 'attendanceDays';
			hdnAttendanceDays.id = 'attendanceDays' + recordNo;
			hdnAttendanceDays.value = attendanceDays;			
			cellAttendanceDays.appendChild(hdnAttendanceDays);
			
			var lblAttendanceDays = document.createElement('label');
			lblAttendanceDays.id = 'lblAttendanceDays' + recordNo;
			lblAttendanceDays.innerHTML = attendanceDays;
			cellAttendanceDays.appendChild(lblAttendanceDays);
			
			// weeklyOffs
			var weeklyOffs = record[5];
			
			var cellWeeklyOffs = row.insertCell(4);
			cellWeeklyOffs.className='sortableTD';
			cellWeeklyOffs.align = 'right';
			
			var hdnWeeklyOffs = document.createElement('input');
			hdnWeeklyOffs.type = 'hidden';
			hdnWeeklyOffs.name = 'weeklyOffs';
			hdnWeeklyOffs.id = 'weeklyOffs' + recordNo;
			hdnWeeklyOffs.value = weeklyOffs;
			cellWeeklyOffs.appendChild(hdnWeeklyOffs);
			
			var lblWeeklyOffs = document.createElement('label');
			lblWeeklyOffs.id = 'lblWeeklyOffs' + recordNo;
			lblWeeklyOffs.innerHTML = weeklyOffs;
			cellWeeklyOffs.appendChild(lblWeeklyOffs);
			
			// holidays
			var holidays = record[6];
			
			var cellHolidays = row.insertCell(5);
			cellHolidays.className='sortableTD';
			cellHolidays.align = 'right';
			
			var hdnHolidays = document.createElement('input');
			hdnHolidays.type = 'hidden';
			hdnHolidays.name = 'holidays';
			hdnHolidays.id = 'holidays' + recordNo;
			hdnHolidays.value = holidays;
			cellHolidays.appendChild(hdnHolidays);
			
			var lblHolidays = document.createElement('label');
			lblHolidays.id = 'lblHolidays' + recordNo;
			lblHolidays.innerHTML = holidays;
			cellHolidays.appendChild(lblHolidays);
			
			// lateMarks
			var lateMarks = record[7];
			
			var cellLateMarks = row.insertCell(6);
			cellLateMarks.className='sortableTD';
			cellLateMarks.align = 'right';
			cellLateMarks.noWrap = 'true';
			
			var hdnLateMarks = document.createElement('input');
			hdnLateMarks.type = 'hidden';
			hdnLateMarks.name = 'lateMarks';
			hdnLateMarks.id = 'lateMarks' + recordNo;
			hdnLateMarks.value = lateMarks;
			cellLateMarks.appendChild(hdnLateMarks);			
			
			var lblLateMarks = document.createElement('label');
			lblLateMarks.id = 'lblLateMarks' + recordNo;
			lblLateMarks.innerHTML = lateMarks;
			cellLateMarks.appendChild(lblLateMarks);
			
			// halfDays
			var halfDays = record[8];
			
			var cellHalfDays = row.insertCell(7);
			cellHalfDays.className='sortableTD';
			cellHalfDays.align = 'right';
			
			var hdnHalfDays = document.createElement('input');
			hdnHalfDays.type = 'hidden';
			hdnHalfDays.name = 'halfDays';
			hdnHalfDays.id = 'halfDays' + recordNo;
			hdnHalfDays.value = halfDays;
			cellHalfDays.appendChild(hdnHalfDays);
			
			var lblHalfDays = document.createElement('label');
			lblHalfDays.id = 'lblHalfDays' + recordNo;
			lblHalfDays.innerHTML = halfDays;
			cellHalfDays.appendChild(lblHalfDays);
			
			// paidLeaves
			var paidLeaves = record[9];
			
			var cellPaidLeaves = row.insertCell(8);
			cellPaidLeaves.className='sortableTD';
			cellPaidLeaves.align = 'right';
			cellPaidLeaves.noWrap = 'true';
			
			var hdnPaidLeaves = document.createElement('input');
			hdnPaidLeaves.type = 'hidden';
			hdnPaidLeaves.name = 'paidLeaves';
			hdnPaidLeaves.id = 'paidLeaves' + recordNo;
			hdnPaidLeaves.value = paidLeaves;
			cellPaidLeaves.appendChild(hdnPaidLeaves);			
			
			var lblPaidLeaves = document.createElement('label');
			lblPaidLeaves.id = 'lblPaidLeaves' + recordNo;
			lblPaidLeaves.innerHTML = paidLeaves;
			cellPaidLeaves.appendChild(lblPaidLeaves);
			
			// unPaidLeaves and systemUnPaidLeaves
			var unPaidLeaves = record[10];
			
			var cellUnPaidLeaves = row.insertCell(9);
			cellUnPaidLeaves.className='sortableTD';
			cellUnPaidLeaves.align = 'right';
			cellUnPaidLeaves.noWrap = 'true';
			
			var hdnUnPaidLeaves = document.createElement('input');
			hdnUnPaidLeaves.type = 'hidden';
			hdnUnPaidLeaves.name = 'unPaidLeaves';
			hdnUnPaidLeaves.id = 'unPaidLeaves' + recordNo;
			hdnUnPaidLeaves.value = unPaidLeaves;
			cellUnPaidLeaves.appendChild(hdnUnPaidLeaves);
			
			var lblUnPaidLeaves = document.createElement('label');
			lblUnPaidLeaves.id = 'lblUnPaidLeaves' + recordNo;
			lblUnPaidLeaves.innerHTML = unPaidLeaves;
			cellUnPaidLeaves.appendChild(lblUnPaidLeaves);
			
			var systemUnPaidLeaves = record[11];
			
			var hdnSystemUnPaidLeaves = document.createElement('input');
			hdnSystemUnPaidLeaves.type = 'hidden';
			hdnSystemUnPaidLeaves.name = 'systemUnPaidLeaves';
			hdnSystemUnPaidLeaves.id = 'systemUnPaidLeaves' + recordNo;
			hdnSystemUnPaidLeaves.value = systemUnPaidLeaves;
			cellUnPaidLeaves.appendChild(hdnSystemUnPaidLeaves);
			
			// salaryDays and totalAttendanceDays
			var salaryDays = record[12];
			
			var cellSalaryDays = row.insertCell(10);
			cellSalaryDays.className='sortableTD';
			cellSalaryDays.align = 'right';
			cellSalaryDays.noWrap = 'true';
			
			var hdnSalaryDays = document.createElement('input');
			hdnSalaryDays.type = 'hidden';
			hdnSalaryDays.name = 'salaryDays';
			hdnSalaryDays.id = 'salaryDays' + recordNo;
			hdnSalaryDays.value = salaryDays;
			cellSalaryDays.appendChild(hdnSalaryDays);
			
			var lblSalaryDays = document.createElement('label');
			lblSalaryDays.id = 'lblSalaryDays' + recordNo;
			lblSalaryDays.innerHTML = salaryDays;
			cellSalaryDays.appendChild(lblSalaryDays);
			
			var totalAttendanceDays = record[13];
			
			var hdnTotalAttendanceDays = document.createElement('input');
			hdnTotalAttendanceDays.type = 'hidden';
			hdnTotalAttendanceDays.name = 'totalAttendanceDays';
			hdnTotalAttendanceDays.id = 'totalAttendanceDays' + recordNo;
			hdnTotalAttendanceDays.value = totalAttendanceDays;
			cellSalaryDays.appendChild(hdnTotalAttendanceDays);
			
			// edit record
			var cellEdit = row.insertCell(11);
			cellEdit.className = 'sortableTD';
			cellEdit.align = 'center';
			
			var imgEdit = document.createElement('img');
			imgEdit.type = 'image';
			imgEdit.src = '../pages/common/css/icons/edit.png';
			imgEdit.style.cursor = 'hand';
			imgEdit.onclick = function() {
				var attendanceDetailsWindow = window.open('', 'attendanceDetailsWindow', 'width=1000, height=400, top=150, left=0, scrollbars=yes, resizable=yes, menubar=no, toolbar=no, status=yes');	 
				document.getElementById('paraFrm').target = "attendanceDetailsWindow";
				document.getElementById("paraFrm").action = 'ViewMonthAttendance_viewAttendanceDetails.action?empId=' + hdnEmpId.value + '&recordNo=' + recordNo;
			    document.getElementById("paraFrm").submit();
			    document.getElementById('paraFrm').target = 'main';
			    row.style.background = '#FDFBB0';
			};
			cellEdit.appendChild(imgEdit);
			
			// delete record
			var cellDelete = row.insertCell(12);
			cellDelete.className = 'sortableTD';
			cellDelete.align = 'center';
			
			var chkRecord = document.createElement('input');
			chkRecord.type = 'checkbox';
			chkRecord.id = 'chkRecord' + recordNo;
			chkRecord.onclick = function() {
				var empId = hdnEmpId.value;
				
				var deletedIds = document.getElementById('paraFrm_deletedRecords').value;
				var deletedNos = document.getElementById('paraFrm_deletedNo').value;
				if(this.checked) {
					deletedIds += ',' + empId;
					deletedNos += ',' + recordNo;
				} else {
					var deletedRecords = document.getElementById('paraFrm_deletedRecords').value.split(',');
					var deletedNo = document.getElementById('paraFrm_deletedNo').value.split(',');
					deletedIds = '';
					deletedNos = '';
					for(var i = 0; i < deletedRecords.length; i++) {
						if(deletedRecords[i] != '') {
							if(empId != deletedRecords[i]) {
								deletedIds += ',' + deletedRecords[i];
								deletedNos += ',' + deletedNo[i];
							}
						}
					}
				}
				document.getElementById('paraFrm_deletedRecords').value = deletedIds;
				document.getElementById('paraFrm_deletedNo').value = deletedNos;
			};
			cellDelete.appendChild(chkRecord);
			chkRecord.focus();
		}
	}
}

function retrieveLockURL(url, formName, lockFlag) {
	try {
		url = url + getFormAsStringLock(formName) + '&' + 'lockFlag=' + lockFlag + '&' + Math.random();
	} catch(e) {
		alert(e);
	}
	if (window.XMLHttpRequest) { // Non-IE browsers
		req = new XMLHttpRequest(); // XMLHttpRequest object is created
	    req.onreadystatechange = processStateChangeLock;//XMLHttpRequest object is configured with a callback function
	    try {
	    	/**
	    	 * open("HTTP method", "URL", syn/asyn), for asyn-true
	    	 * if false, send operations are synchronous, browser doesn't accept any input/output
	    	**/
	    	req.open("GET", url, true);
	    } catch (e) {
	      	alert("Problem Communicating with Server\n"+e);
	    }
	    req.send(null);
	} else if (window.ActiveXObject) { // IE 
		req = new ActiveXObject("Microsoft.XMLHTTP");
	    if (req) {
	    	req.onreadystatechange = processStateChangeLock;        
	      	req.open("GET", url, true);
	       	req.send();
	    }
	}
}

function getFormAsStringLock(formName) {
	try {
		var returnString = "";
		var formElements = document.forms[formName].elements;
		for (var i = formElements.length - 1; i >= 0; i--) {
 			if(formElements[i].name == 'month' || formElements[i].name == 'year' || formElements[i].name == 'attendanceCode'
 			|| formElements[i].name == 'status') {
 				returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
 			}
 		}
	} catch(e) {
		alert(e);
	}
	return returnString;
}
	
function processStateChangeLock() {
	// 0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
	if(req.readyState == 4) { // Complete
		if (req.status == 200) { // OK response
	    	//responseXML: XML document of data returned from the server
	    	var result = req.responseText.split(','); // String version of data returned from the server
	    	
	    	document.getElementById('paraFrm_lockAttendance').value = result[1]; // set whether attendance has been locked or not
	    	
	    	if(result[1] == 'false') {
				document.getElementById('btnLock').style.display = 'block';
				document.getElementById('btnUnlock').style.display = 'none';
			} else {
				document.getElementById('btnLock').style.display = 'none';
				document.getElementById('btnUnlock').style.display = 'block';
				
				alert(result[0]);
			}
			
			document.getElementById('paraFrm_status').value = result[2]; // set current status of attendance
		}
		parent.frames[2].name = 'main';
	}
}