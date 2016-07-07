// tabledeleterow.js version 1.2 2006-02-21
// mredkj.com

// CONFIG notes. Below are some comments that point to where this script can be customized.
// Note: Make sure to include a <tbody></tbody> in your table's HTML

var INPUT_NAME_PREFIX = 'inputName'; // this is being set via script
var RADIO_NAME = 'totallyrad'; // this is being set via script
var TABLE_NAME = 'tblAttn'; // this should be named in the HTML
var TABLE_NAME_QUAL = 'tblqual'; // this should be named in the HTML
var ROW_BASE = 1; // first number (for display)
var hasLoaded = true;

window.onload=fillInRows;

function fillInRows()
{
	hasLoaded = true;
//	addRowToTable();
//	addRowToTable();
}

// CONFIG:
// myRowObject is an object for storing information about the table rows
function myRowObject(one, two, three, four)
{
	this.one = one; // text object
	this.two = two; // input text object
	this.three = three; // input checkbox object
	this.four = four; // input radio object
}

/*
 * insertRowToTable
 * Insert and reorder
 */
function insertRowToTable()
{
	if (hasLoaded) {
		var tbl = document.getElementById(TABLE_NAME);
		var rowToInsertAt = tbl.tBodies[0].rows.length;
		for (var i=0; i<tbl.tBodies[0].rows.length; i++) {
			if (tbl.tBodies[0].rows[i].myRow &&  tbl.tBodies[0].rows[i].myRow.four.checked) {
				rowToInsertAt = i;
				break;
			}
		}
		addRowToTable(rowToInsertAt);
	//	reorderRows(tbl, rowToInsertAt);
	}
}

/*
 * addRowToTable
 * Inserts at row 'num', or appends to the end if no arguments are passed in. Don't pass in empty strings.
 */
function addRowToTable(num)
{
	var	eId = document.getElementById("newECode").value;
    var	eToken = document.getElementById("newEToken").value;
    var	eName = document.getElementById("newEName").value;
    var	attdnDays = "0";
    var	weeklyOffs = "0";
    var	compOffs = "0";
    var	holidays = "0";
    var	paidLevs = "0";
    var	unPaidLevs = "0";
    var	salDays = "0";
    
	if (hasLoaded) 
	{
		var tbl = document.getElementById(TABLE_NAME);
		var nextRow = tbl.tBodies[0].rows.length;
		alert(nextRow);
		var iteration = nextRow + ROW_BASE;
		/*if(num == null)
		{ 
			num = nextRow;		
		}
		else
		{
			iteration = num + ROW_BASE;			
		}*/
		
		var row = tbl.tBodies[0].insertRow(nextRow);		
		
		alert('hi');
		var cellEToken = row.insertCell(0);
		var txtEToken = document.createTextNode(eToken);
		cellEToken.appendChild(txtEToken);
		
		var cellEName = row.insertCell(1);
		var txtEName = document.createTextNode(eName);
		cellEName.appendChild(txtEName);
		
		var cellAttdnDays = row.insertCell(2);
		var elmAttdnDays = document.createElement('input');
		elmAttdnDays.setAttribute('type', 'text');
		elmAttdnDays.setAttribute('name', 'attdnDays');
		elmAttdnDays.setAttribute('maxLength', '5');
		elmAttdnDays.setAttribute('size', '2');
		elmAttdnDays.setAttribute('align', 'center');
		elmAttdnDays.style.setAttribute('cssText', 'text-align: right');
		elmAttdnDays.setAttribute('value', attdnDays);
		cellAttdnDays.appendChild(elmAttdnDays);
		
		var cellWeeklyOffs = row.insertCell(3);
		var elmWeeklyOffs = document.createElement('input');
		elmWeeklyOffs.setAttribute('type', 'text');
		elmWeeklyOffs.setAttribute('name', 'weeklyOffs');
		elmWeeklyOffs.setAttribute('maxlength', '5');
		elmWeeklyOffs.setAttribute('size', '2');
		elmWeeklyOffs.style.setAttribute('cssText', 'text-align: right');
		elmWeeklyOffs.setAttribute('value', weeklyOffs);
		cellWeeklyOffs.appendChild(elmWeeklyOffs);
		
		var cellCompOffs = row.insertCell(4);
		var elmCompOffs = document.createElement('input');
		elmCompOffs.setAttribute('type', 'text');
		elmCompOffs.setAttribute('name', 'compOffs');
		elmCompOffs.setAttribute('maxlength', '5');
		elmCompOffs.setAttribute('size', '2');
		elmCompOffs.setAttribute('style', 'text-align: right');
		elmCompOffs.setAttribute('value', compOffs);
		cellCompOffs.appendChild(elmCompOffs);
		
		var cellHolidays = row.insertCell(5);
		var elmHolidays = document.createElement('input');
		elmHolidays.setAttribute('type', 'text');
		elmHolidays.setAttribute('name', 'holidays');
		elmHolidays.setAttribute('maxlength', '5');
		elmHolidays.setAttribute('size', '2');
		elmHolidays.setAttribute('style', 'text-align: right');
		elmHolidays.setAttribute('value', holidays);
		cellHolidays.appendChild(elmHolidays);
		
		var cellPaidLevs = row.insertCell(6);
		var elmPaidLevs = document.createElement('input');
		elmPaidLevs.setAttribute('type', 'text');
		elmPaidLevs.setAttribute('name', 'paidLevs');
		elmPaidLevs.setAttribute('maxlength', '5');
		elmPaidLevs.setAttribute('size', '2');
		elmPaidLevs.setAttribute('style', 'text-align: right');
		elmPaidLevs.setAttribute('value', paidLevs);
		cellPaidLevs.appendChild(elmPaidLevs);
		
		var cellUnPaidLevs = row.insertCell(7);
		var elmUnPaidLevs = document.createElement('input');
		elmUnPaidLevs.setAttribute('type', 'text');
		elmUnPaidLevs.setAttribute('name', 'unPaidLevs');
		elmUnPaidLevs.setAttribute('maxlength', '5');
		elmUnPaidLevs.setAttribute('size', '2');
		elmUnPaidLevs.setAttribute('style', 'text-align: right');
		elmUnPaidLevs.setAttribute('value', unPaidLevs);
		cellUnPaidLevs.appendChild(elmUnPaidLevs);
		
		var cellSalDays = row.insertCell(8);
		var elmSalDays = document.createElement('input');
		elmSalDays.setAttribute('type', 'text');
		elmSalDays.setAttribute('name', 'salDays');
		elmSalDays.setAttribute('readonly', 'readonly');
		elmSalDays.setAttribute('size', '2');
		elmSalDays.setAttribute('style', 'text-align:center; border: none');
		elmSalDays.setAttribute('value', salDays);
		cellSalDays.appendChild(elmSalDays);	
		
		var cellRemRec = row.insertCell(9);
		var elmRemRec = document.createElement('input');
		elmRemRec.setAttribute('type', 'checkbox');
		elmRemRec.setAttribute('name', 'remRec');
		elmRemRec.setAttribute('size', '2');
		cellRemRec.appendChild(elmRemRec);
		
		var cellEId = row.insertCell(10);
		var elmEId = document.createElement('input');
		elmEId.setAttribute('type', 'hidden');
		elmEId.setAttribute('name', 'eId');
		elmEId.setAttribute('value', eId);
		cellEId.appendChild(elmEId);
		
		// Pass in the elements you want to reference later
		// Store the myRow object in each row
		row.myRow = new myRowObject(txtEToken, txtEName, elmAttdnDays, elmWeeklyOffs, elmCompOffs, elmHolidays, elmPaidLevs, elmUnPaidLevs, elmSalDays, elmRemRec, elmEId);
	}
}
function addRowFromTable(code,skill)
{
	if (hasLoaded) {
		var tbl = document.getElementById(TABLE_NAME);
		var nextRow = tbl.tBodies[0].rows.length;
		
		// add the row
		var row = tbl.tBodies[0].insertRow(nextRow);
		
		// CONFIG: requires classes named classy0 and classy1
		//row.className = 'classy' + (iteration % 2);
		row.className = 'border2';
		// CONFIG: This whole section can be configured
		
		// cell 0 - text
			
		var cell0 = row.insertCell(0);
		var textNode = document.createTextNode(code);
		cell0.appendChild(textNode);
		cell0.className = 'border2';
		
		// cell 1 - input text
		var cell1 = row.insertCell(1);
		var txtInp = document.createTextNode(skill);
		cell1.appendChild(txtInp);
		cell1.className = 'border2';
		
		// cell 2 - input button
			
			
		// cell 3 - input checkbox
		var cell3 = row.insertCell(3);
		var cbEl = document.createElement('input');
		cbEl.setAttribute('type', 'checkbox');
		cbEl.setAttribute('name', 'ggggg');
		cell3.className = 'border2';
		cell3.appendChild(cbEl);
		
		// cell 4 - input radio
		var cell4 = row.insertCell(4);
		var raEl = document.createElement('input');
		raEl.setAttribute('type', 'hidden');
		raEl.setAttribute('name', 'hiddenSkill');
		raEl.setAttribute('value', code); // iteration included for debug purposes
		cell4.className = 'border2';
		cell4.appendChild(raEl);
		
		var cell5 = row.insertCell(5);
		var txtInp = document.createTextNode("");
		cell5.appendChild(txtInp);
		cell5.className = 'border2';
		
		// Pass in the elements you want to reference later
		// Store the myRow object in each row
		row.myRow = new myRowObject(textNode, txtInp, cbEl, raEl);
	}
}

// CONFIG: this entire function is affected by myRowObject settings
// If there isn't a checkbox in your row, then this function can't be used.
function deleteChecked()
{
	
	if (hasLoaded) {
		var checkedObjArray = new Array();
		var cCount = 0;
	
		var tbl = document.getElementById(TABLE_NAME);
		for (var i=0; i<tbl.tBodies[0].rows.length; i++) {
					
			if (tbl.tBodies[0].rows[i].myRow && tbl.tBodies[0].rows[i].myRow.three.getAttribute('type') == 'checkbox' && tbl.tBodies[0].rows[i].myRow.three.checked) {
				
				checkedObjArray[cCount] = tbl.tBodies[0].rows[i];
				cCount++;
			}
	
		//if (tbl.tBodies[0].rows[i].myRow.three.getAttribute('type') == 'checkbox') {
		//		alert('^^^^^^^^^^^^^^33^^^^^^^^^');
		//		checkedObjArray[cCount] = tbl.tBodies[0].rows[i];
		//		cCount++;
		//	}
	
	
		}
		if (checkedObjArray.length > 0) {
			var rIndex = checkedObjArray[0].sectionRowIndex;
			deleteRows(checkedObjArray);
		//	reorderRows(tbl, rIndex);
		}
	}
}

// If there isn't an element with an onclick event in your row, then this function can't be used.
function deleteCurrentRow(obj)
{
	if (hasLoaded) {
		var delRow = obj.parentNode.parentNode;
		var tbl = delRow.parentNode.parentNode;
		var rIndex = delRow.sectionRowIndex;
		var rowArray = new Array(delRow);
		deleteRows(rowArray);
	//	reorderRows(tbl, rIndex);
	}
}

function deleteRows(rowObjArray)
{
	if (hasLoaded) {
		for (var i=0; i<rowObjArray.length; i++) {
			var rIndex = rowObjArray[i].sectionRowIndex;
			rowObjArray[i].parentNode.deleteRow(rIndex);
		}
	}
}

function openInNewWindow(frm)//
{
	// open a blank window
	var aWindow = window.open('', 'TableAddRow2NewWindow',
	'scrollbars=yes,menubar=yes,resizable=yes,toolbar=no,width=400,height=400');
	
	// set the target to the blank window
	frm.target = 'TableAddRow2NewWindow';
	
	// submit
	frm.submit();
}

//--------for qualification==================

function addRowForQual(num)
{   	var	code=document.getElementById("req.qulCode").value;
      	var	skill=document.getElementById("req.qulName").value;
      	if(code==""){
      	alert('Please Select Qualification');
      	return false;
      	}
      	   	else{
	if (hasLoaded) {
		var tbl = document.getElementById(TABLE_NAME_QUAL);
		var nextRow = tbl.tBodies[0].rows.length;
		var iteration = nextRow + ROW_BASE;
		if (num == null) { 
			num = nextRow;
		
		} else {
			iteration = num + ROW_BASE;			
		}		
		var row = tbl.tBodies[0].insertRow(num);
		row.className = 'border2';			
		// cell 0 - text		
		var cell0 = row.insertCell(0);
		var textNode = document.createTextNode(code);
		cell0.appendChild(textNode);
		cell0.className = 'border2';		
		// cell 1 - input text
		var cell1 = row.insertCell(1);
		var txtInp = document.createTextNode(skill);
		cell1.appendChild(txtInp);
		cell1.className = 'border2';
		// cell 2 - input button
		var cell2 = row.insertCell(2);
		cell2.className = 'border2';
		var btnEl ;//= document.createElement('input');				
		// cell 4 - input radio
		var cell3 = row.insertCell(3);
		var raEl = document.createElement('input');
		raEl.setAttribute('type', 'hidden');
		raEl.setAttribute('name', 'hiddenQual');
		raEl.setAttribute('value', code); // iteration included for debug purposes
		cell3.className = 'border2';
		cell3.appendChild(raEl);			
		// cell 3 - input checkbox
		var cell4 = row.insertCell(4);
		var cbEl = document.createElement('input');
		cbEl.setAttribute('type', 'checkbox');
		cell4.className = 'border2';
		cell4.appendChild(cbEl);
		row.myRow = new myRowObject(textNode, txtInp, cbEl, raEl);
		document.getElementById("req.qulCode").value="";
    	document.getElementById("req.qulName").value="";
	}
	}
}


function deleteCheckedQual()
{		if (hasLoaded) {
		var checkedObjArray = new Array();
		var cCount = 0;
		var tbl = document.getElementById(TABLE_NAME_QUAL);
		for (var i=0; i<tbl.tBodies[0].rows.length; i++) {
			if (tbl.tBodies[0].rows[i].myRow && tbl.tBodies[0].rows[i].myRow.three.getAttribute('type') == 'checkbox' && tbl.tBodies[0].rows[i].myRow.three.checked) {
				checkedObjArray[cCount] = tbl.tBodies[0].rows[i];
				cCount++;
			}	
		}
		if (checkedObjArray.length > 0) {
			var rIndex = checkedObjArray[0].sectionRowIndex;
			deleteRows(checkedObjArray);		
		}
	}
}
//---
function addRowFromTableQual(code,qual)
{   	
	if (hasLoaded) {
		var tbl = document.getElementById(TABLE_NAME_QUAL);
		var nextRow = tbl.tBodies[0].rows.length;
		
		// add the row
		var row = tbl.tBodies[0].insertRow(nextRow);
		
		// CONFIG: requires classes named classy0 and classy1
		//row.className = 'classy' + (iteration % 2);
		row.className = 'border2';
		// CONFIG: This whole section can be configured
		
		// cell 0 - text
			
		var cell0 = row.insertCell(0);
		var textNode = document.createTextNode(code);
		cell0.appendChild(textNode);
		cell0.className = 'border2';
		
		// cell 1 - input text
		var cell1 = row.insertCell(1);
		var txtInp = document.createTextNode(qual);
		cell1.appendChild(txtInp);
		cell1.className = 'border2';
		
		// cell 2 - input button
			
			
		// cell 3 - input checkbox
		var cell3 = row.insertCell(3);
		var cbEl = document.createElement('input');
		cbEl.setAttribute('type', 'checkbox');
		cbEl.setAttribute('name', 'ggggg');
		cell3.className = 'border2';
		cell3.appendChild(cbEl);
		
		// cell 4 - input radio
		var cell4 = row.insertCell(4);
		var raEl = document.createElement('input');
		raEl.setAttribute('type', 'hidden');
		raEl.setAttribute('name', 'hiddenQual');
		raEl.setAttribute('value', code); // iteration included for debug purposes
		cell4.className = 'border2';
		cell4.appendChild(raEl);
		
		var cell5 = row.insertCell(5);
		var txtInp = document.createTextNode("");
		cell5.appendChild(txtInp);
		cell5.className = 'border2';
		
		// Pass in the elements you want to reference later
		// Store the myRow object in each row
		row.myRow = new myRowObject(textNode, txtInp, cbEl, raEl);
	}
}
