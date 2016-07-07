  
/**************************************************************************************
  htmlDatePicker v0.1
  
  Copyright (c) 2005, Jason Powell
  All Rights Reserved

  Redistribution and use in source and binary forms, with or without modification, are 
    permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice, this list of 
      conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright notice, this list 
      of conditions and the following disclaimer in the documentation and/or other materials 
      provided with the distribution.
    * Neither the name of the product nor the names of its contributors may be used to 
      endorse or promote products derived from this software without specific prior 
      written permission.

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS 
  OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL 
  THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
  GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED 
  AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
  OF THE POSSIBILITY OF SUCH DAMAGE.
  
  -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-

  
***************************************************************************************/
// User Changeable Vars
var HighlightToday  = true;    // use true or false to have the current day highlighted
var DisablePast    = true;    // use true or false to allow past dates to be selectable
// The month names in your native language can be substituted below
var MonthNames = new Array("January","February","March","April","May","June","July","August","September","October","November","December");

// Global Vars
var now = new Date();
var dest = null;
var ny = now.getFullYear(); // Today's Date
var nm = now.getMonth();
var nd = now.getDate();
var sy = 0; // currently Selected date
var sm = 0;
var sd = 0;
var y = now.getFullYear(); // Working Date
var m = now.getMonth();
var d = now.getDate();
var l = 0;
var t = 0;
var MonthLengths = new Array(31,28,31,30,31,30,31,31,30,31,30,31);

/*
  Function: GetDate(control)

  Arguments:
    control = ID of destination control
*/
function NewCal(valueId,dataType) {

  EnsureCalendarExists();
  DestroyCalendar();
  // One arguments is required, the rest are optional
  // First arguments must be the ID of the destination control
  if(valueId == null || valueId == "") {
    // arguments not defined, so display error and quit
    alert("ERROR: Destination control required in funciton call GetDate()");
    return;
  } else {
    // copy argument
     dest = document.getElementById(valueId);
  }
  y = now.getFullYear();
  m = now.getMonth();
  d = now.getDate();
  sm = 0;
  sd = 0;
  sy = 0;
  var cdval = dest.value;
  if(/\d{1,2}.\d{1,2}.\d{4}/.test(dest.value)) {
    // element contains a date, so set the shown date
    if (cdval.indexOf("-") != -1) {
    var vParts = cdval.split("-"); // assume mm/dd/yyyy
	  if (vParts.length== 3) {
	    sm = vParts[1] - 1;
	    sd = vParts[0];
	    sy = vParts[2];
	    m=sm;
	    d=sd;
	    y=sy;
    
    }
    }
  }
  
//  l = dest.offsetLeft; // + dest.offsetWidth;
//  t = dest.offsetTop - 125;   // Calendar is displayed 125 pixels above the destination element
//  if(t<0) { t=0; }      // or (somewhat) over top of it. ;)

  /* Calendar is displayed 125 pixels above the destination element
  or (somewhat) over top of it. ;)*/
  ///l = dest.offsetLeft + dest.offsetParent.offsetLeft;
  ///t = getTopPos(dest)-115;
	
	l = getLeftPos(dest);
  t = getTopPos(dest)+100;
  
  if(t < 0) t = 0; // >
  DrawCalendar();
}

/*
  function DestoryCalendar()
  
  Purpose: Destory any already drawn calendar so a new one can be drawn
*/
function DestroyCalendar() {
  var cal = document.getElementById("dpCalendar");
  if(cal != null) {
    cal.innerHTML = null;
    cal.style.display = "none";
  }
  return
}

		function getTopPos(inputObj) {
		var returnValue = inputObj.offsetTop + inputObj.offsetHeight;	  
		while((inputObj = inputObj.offsetParent) != null) {
	  		returnValue += inputObj.offsetTop;
		}
		if(returnValue < 70) {
			return returnValue;
	  	}
		return returnValue - 100;
	}
function getLeftPos(inputObj)
		{
		  var returnValue = inputObj.offsetLeft;
		  while((inputObj = inputObj.offsetParent) != null)returnValue += inputObj.offsetLeft;

		  return returnValue;
		}

function DrawCalendar() {



  DestroyCalendar();
  cal = document.getElementById("dpCalendar");
  cal.style.left = l + "px";
  cal.style.top = t + "px";
    cal.style.zIndex = "10";
   
  var sCal = "<table border=1 cellpadding=1 cellspacing=1 width='100%' align=\"center\" valign=\"top\"><tr>"+
    "<td class=\"cellMonth\" align=\"center\" colspan=\"3\"> <select name=\"month\" id=\"month\" onchange=\"javascript: changeMonth();\"><option value=\"0\">Jan</option><option value=\"1\">Feb</option><option value=\"2\">Mar</option><option value=\"3\">Apr</option><option value=\"4\">May</option><option value=\"5\">Jun</option><option value=\"6\">Jul</option><option value=\"7\">Aug</option><option value=\"8\">Sep</option><option value=\"9\">Oct</option><option value=\"10\">Nov</option><option value=\"11\">Dec</option></select></td>"+
    "<td class=\"cellMonth\" align=\"left\" colspan=\"4\"><a href=\"javascript: PrevYear();\" title=\"Previous Year\">&lt;</a>&nbsp;  "+y+"&nbsp;<a href=\"javascript: NextYear();\" title=\"Next Year\">&gt;</a>  </td>   </tr>"+
    "<tr><td class=\"cellMonth\" colspan=\"7\" align=\"center\"> "+MonthNames[m]+"&nbsp;"+y+"&nbsp;</td></tr><tr class=\"cellMonth\" ><td class=\"cellMonth\" >S</td><td class=\"cellMonth\" >M</td><td class=\"cellMonth\" >T</td><td class=\"cellMonth\" >W</td><td class=\"cellMonth\" >T</td><td class=\"cellMonth\" >F</td><td class=\"cellMonth\" >S</td></tr>";
  var wDay = 1;
  var wDate = new Date(y,m,wDay);
  if(isLeapYear(wDate)) {
    MonthLengths[1] = 29;
  } else {
    MonthLengths[1] = 28;
  }
  var dayclass = "";
  var isToday = false;
  for(var r=1; r<7; r++) {
    sCal = sCal + "<tr>";
    for(var c=0; c<7; c++) {
      var wDate = new Date(y,m,wDay);
      if(wDate.getDay() == c && wDay<=MonthLengths[m]) {
      		
      	
        if(wDate.getDate()==sd && wDate.getMonth()==sm && wDate.getFullYear()==sy) {
          dayclass = "cellSelected";
          isToday = true;  // only matters if the selected day IS today, otherwise ignored.
        } else if(wDate.getDate()==nd && wDate.getMonth()==nm && wDate.getFullYear()==ny && HighlightToday) {
          dayclass = "cellToday";
          isToday = true;
        } 
        else if(c==0){
      		 dayclass = "cellSunday";
      		}
			else if(c==6){
      		 dayclass = "cellSaturday";
      		}
        else {
          dayclass = "cellDay";
          isToday = true;
        }
        if(((now > wDate) && !DisablePast) || (now <= wDate) || isToday) { // >
          // user wants past dates selectable
          sCal = sCal + "<td class=\""+dayclass+"\"><a href=\"javascript: ReturnDay("+wDay+");\">"+wDay+"</a></td>";
        } else {
          // user wants past dates to be read only
          sCal = sCal + "<td class=\""+dayclass+"\"><a href=\"javascript: ReturnDay("+wDay+");\">"+wDay+"</a></td>";
        }
        
        
        wDay++;
      } else {
        sCal = sCal + "<td class=\"unused\"></td>";
      }
    }
    sCal = sCal + "</tr>";
  }
  sCal = sCal + "<tr><td colspan=\"4\" class=\"unused\"></td><td colspan=\"3\" class=\"cellCancel\"><a href=\"javascript: DestroyCalendar();\">Cancel</a></td></tr></table>"
  cal.innerHTML = sCal; // works in FireFox, opera
  cal.style.display = "inline";
  
  document.getElementById("month").value=m;
}

function PrevMonth() {
  m--;
  if(m==-1) {
    m = 11;
    y--;
  }
  DrawCalendar();
}
function changeMonth() {
 m=document.getElementById("month").value; 
  DrawCalendar();
  
}


function PrevYear() { 
    y--;  
  DrawCalendar();
}
function NextMonth() {
  m++;
  if(m==12) {
    m = 0;
    y++;
  }
  DrawCalendar();
}

function NextYear() {
      y++;
    DrawCalendar();
}

function ReturnDay(day) {
  cDest = document.getElementById(dest);
  m++;

	if(m<10){
		m='0'+m;
	}

	if(day<10){
		day='0'+day;
	}

  dest.value = day+"-"+m+"-"+y;
  DestroyCalendar();
}

function EnsureCalendarExists() {
  if(document.getElementById("dpCalendar") == null) {
    var eCalendar = document.createElement("div");
    eCalendar.setAttribute("id", "dpCalendar");
    document.body.appendChild(eCalendar);
  }
}

function isLeapYear(dTest) {
  var y = dTest.getYear();
  var bReturn = false;
  
  if(y % 4 == 0) {
    if(y % 100 != 0) {
      bReturn = true;
    } else {
      if (y % 400 == 0) {
        bReturn = true;
      }
    }
  }
  
  return bReturn;
} 