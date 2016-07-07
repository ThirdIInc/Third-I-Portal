<script>
	function toggle(e,id) {
		 if (e.style.display == "none" ) {
			 e.style.display = "";
			 setCookie(id,"1");
			 document.getElementById(id+'IMG').className="down";
		 } else {
			 e.style.display = "none";
			 setCookie(id,"0");
			 document.getElementById(id+'IMG').className="up";
		 }
	}

	function collapseAll(e,id) {
			 e.style.display = "none";
			 document.getElementById(id+'IMG').className="up";
			 setCookie(id,"0");
	}

	function showAll(e,id) {
			 e.style.display = "";
			 document.getElementById(id+'IMG').className="down";
			 setCookie(id,"1");
	}

	function showF9MultiParam( Q1, Q2 , PR , HD , FL , CI , wid , ht, submitFlag, hiddenAction) {
		paramValues="";
		if(PR.length>0) {
			paramlist=PR.split(',');
			for(i=0;i < paramlist.length; i++) {
				obj=eval("document.getElementById('paraFrm')."+paramlist[i]);
				paramValues=paramValues+obj.value+",";
			}
		}
		//var FI=document.getElementById('paraFrm').formIndex.value;
		window.open('F9FinalAction.do?Q1='+Q1+'&Q2='+Q2+'&PR='+paramValues+'&HD='+HD+'&FL='+FL+'&CI='+CI+'&submitFlag='+submitFlag+'&hiddenAction='+hiddenAction+'&formIndex=0','WINCDR','top=150,left=120,width='+ wid+',height='+ht+',scrollbars=no,status=yes,resizable=no');
	}
	
	function callDelete() {
		var agree=confirm("Do you really want to delete ?");
		if(agree) {
			document.getElementById('paraFrm').target="_top";
			document.getElementById('paraFrm').action.value='Delete';	
			document.getElementById('paraFrm').submit();
		}
	}
	
	function callDelete() {
		var agree=confirm("Do you really want to delete ?");
		if(agree) {
			document.getElementById('paraFrm').target="_top";
			document.getElementById('paraFrm').action.value='Delete';	
			document.getElementById('paraFrm').submit();
		}
	}
	
	function escapeVal(textarea,replaceWith) {
		textarea.value = escape(textarea.value) 
		for(i=0; i < textarea.value.length; i++) {
			if(textarea.value.indexOf("%0D%0A") > -1) {
			textarea.value=textarea.value.replace("%0D%0A",replaceWith)
			}
			else if(textarea.value.indexOf("%0A") > -1) {
				textarea.value=textarea.value.replace("%0A",replaceWith)
			}
			else if(textarea.value.indexOf("%0D") > -1) {		
				textarea.value=textarea.value.replace("%0D",replaceWith)
			}
		}
		textarea.value=unescape(textarea.value);
	}
	
	function callReset() {
			document.getElementById('paraFrm').target="_top";
			document.getElementById('paraFrm').action.value='Reset';			
			document.getElementById('paraFrm').submit();							
	}
	
	function callSave() {
			document.getElementById('paraFrm').target="_top";
			document.getElementById('paraFrm').action.value='Save';			
			document.getElementById('paraFrm').submit();							
	}
	
	function callSubmit(name) {
			document.getElementById('paraFrm').target="_top";
			document.getElementById('paraFrm').action.value=name;			
			if(name!='loginSubmit')
			 {
			  setCookie("FOCUS",name);
			 }
			document.getElementById('paraFrm').submit();							
	}
	function callSubmitConfiguration(name) {
			document.getElementById('paraFrm').target="_top";
			document.getElementById('paraFrm').action.value=name;			
			document.getElementById('paraFrm').submit();							
	}
	
	
	
	
	function callReport(name) {
			alert("in report");
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action.value=name;			
			document.getElementById('paraFrm').submit();							
	}
	
	function callNext(name) {
			document.getElementById('paraFrm').target="_top";
			document.getElementById('paraFrm').hiddenAction.value='getRecord';
			document.getElementById('paraFrm').action.value=name;			
			document.getElementById('paraFrm').submit();							
	}
	
	function handleError() {
//		alert('Error occured');
		return true;
    }
    
    function TwoDecimal(val) {
    	    
    }
    
   
   function setZero(obj)
   {
    	if(obj.value=='')
    	 obj.value="0";
    	 var num=obj.value;
    	 var len=num.length;
    	 var declen=num.indexOf('.')+1;
    	 if((num.indexOf('.')!=-1) 
    	    && (len>declen+2))
    	 {
	    	formatNumber(obj); 
	     }	
   }
   

  function onlyInteger(obj) {
    	if(obj.value==0)
    	 obj.value="";
    	
    	var nav4 = window.Event ? true : false;
    	var whichCode;
    	if (nav4) // Navigator 4.0x
    		whichCode = event.which;
		else
			whichCode = event.keyCode;	
			
		if((""+whichCode).length>0) {
			//alert(whichCode);
			if ( (whichCode >= 37 && whichCode <= 40) || (whichCode >= 48 && whichCode <= 57) || (whichCode >= 96 && whichCode <= 105) || (whichCode==8) || (whichCode==9) || (whichCode==46)) 
				event.returnValue = true;
			else
				event.returnValue = false;
		} else {
			return true;
		}
	}
	
	
	function formatCapital(obj) {
    	if(obj.value==0)
    	 obj.value="";
    	
    	var nav4 = window.Event ? true : false;
    	var whichCode;
    	if (nav4) // Navigator 4.0x
    		whichCode = event.which;
		else
			whichCode = event.keyCode;	
			
		if((""+whichCode).length>0) {
			//alert(whichCode);
			
			if(whichCode==65)
			   whichCode=97; 
			//if ( (whichCode >= 37 && whichCode <= 40) || (whichCode >= 48 && whichCode <= 57) || (whichCode >= 96 && whichCode <= 105) || (whichCode==8) || (whichCode==9) || (whichCode==46)) 
			//	event.returnValue = true;
			//else
			//	event.returnValue = false;
		} else {
			return true;
		}
	}

    function onlyNumber(obj) {
    	if(obj.value==0)
    	 obj.value="";
    	var nav4 = window.Event ? true : false;
    	var whichCode;
    	if (nav4) // Navigator 4.0x
    		whichCode = event.which;
		else
			whichCode = event.keyCode;	
			
		if((""+whichCode).length>0) {
			if ( (whichCode >= 37 && whichCode <= 40) || (whichCode >= 48 && whichCode <= 57) || (whichCode >= 96 && whichCode <= 105) || (whichCode==190) || (whichCode==110) || (whichCode==8) || (whichCode==9) || (whichCode==46)|| (whichCode==189) || (whichCode==109)) 
				event.returnValue = true;
			else
				event.returnValue = false;
		} else {
			return true;
		}
		
   	  var number=obj.value;
      var numlen=number.length;
      var lendec=number.indexOf('.')+1;
      if((whichCode==8) || (whichCode==136) || (whichCode==9)||(whichCode==46)||(whichCode==37)||(whichCode==39))
      {
      	event.returnValue = true;
      }
      else if(number.indexOf('.')!=-1)
		{
		  if((whichCode == 190) || (whichCode == 110))
		  {
		    event.returnValue=false;
		  }
		  
		}

	}
 
    function formatNumber(obj)
    {
    
   		obj.value=toDollarsAndCents(obj.value);
    }
 	function toDollarsAndCents(n) {
	  var s = "" + Math.round(n * 100) / 100
	  var i = s.indexOf('.')
	  //if (i < 0) return s + ".00"
	  var t = s.substring(0, i + 1) + s.substring(i + 1, i + 3)
	  if (i + 2 == s.length) t += "0"
	  return t
	}
 
 //remove other specify on key press
	function formatOther(obj)
	{
	
	  if(obj.value=='Others (pl specify)')
	   {
			obj.value="";		 		     
	   }
	}
	
 //set  other specify if blank
	function setOther(obj)
	{
	
	  if(obj.value=='')
	   {
			obj.value="Others (pl specify)";		 		     
	   }
	}


function openFile()
{
alert("Please Right Click and save the file on local disk");
return false;
}


 //format Tel.No & mobile.
 function formatTel(date)
 {
     	//alert("inside");
     	//if(date.value==0)
    	 //date.value="";
       
     	var nav4 = window.Event ? true : false;
    	var whichCode;
    	if (nav4) // Navigator 4.0x
    		whichCode = event.which;
		else
			whichCode = event.keyCode;	
			
			if ( (whichCode >= 37 && whichCode <= 40) || (whichCode >= 48 && whichCode <= 57) || (whichCode >= 96 && whichCode <= 105) || (whichCode==190) || (whichCode==110) || (whichCode==8) || (whichCode==9) || (whichCode==46)|| (whichCode==189)|| (whichCode==109)|| (whichCode==107)|| (whichCode==187)) 
				event.returnValue = true;
			else
				event.returnValue = false;

 }
 
  //format date from ddmmyy to dd-mm-yyyy
 function formatDate(date) {
	 var numDate=date.value;
	 if(date.value=='')
	  {
	  
	     return;
	  }   

	else if(numDate.indexOf('/')>0)
		 {
			  if(numDate.length!=8 && numDate.length!= 10) { 
					 alert("Enter valid Date");
					 date.value='';
					 date.focus();
			 } else {
				   
				   numDate=numDate.replace('/','-');
				   numDate=numDate.replace('/','-');
				   
				   if(numDate.length==8)
				    {
				      numDate=numDate.substring(0,6)+'20'+numDate.substring(6);
				    }
				   date.value=numDate;
			  } 
	 
	 
	    }

	 else if(numDate.indexOf('-')>0)
	 {
	  
	   if(numDate.length!=8 && numDate.length!= 10) { 
					 alert("Enter valid Date");
					 date.value='';
					 date.focus();
			 } else {
				   
				   if(numDate.length==8)
				    {
				      numDate=numDate.substring(0,6)+'20'+numDate.substring(6);
				    }
				   date.value=numDate;
			  } 
	 
	  
	 }

	 else if(numDate.length!=6 && numDate.length!= 8) { 
			 alert("Enter valid Date");
			 date.value='';
			 date.focus();
	 } else {
		   if(numDate.length==6) {
			  numDate=numDate.substring(0,2)+'-'+numDate.substring(2,4)+'-20'+numDate.substring(4);
		   } else {
			 numDate=numDate.substring(0,2)+'-'+numDate.substring(2,4)+'-'+numDate.substring(4);
		   }	   
		   date.value=numDate;
	  } 
 }  	
 
 //format date from ddmmyy to dd-mm-yyyy
 function formatDate1(date) {
	 var numDate=date.value;
	 if(date.value=='')
	  {
	  
	     return;
	  }   

	else if(numDate.indexOf('-')>0)
		 {
			  if(numDate.length!=8 && numDate.length!= 10) { 
					 alert("Enter valid Date");
					 date.value='';
					 date.focus();
			 } else {
				   
				   numDate=numDate.replace('-','/');
				   numDate=numDate.replace('-','/');
				   
				   if(numDate.length==8)
				    {
				      numDate=numDate.substring(0,6)+'20'+numDate.substring(6);
				    }
				   date.value=numDate;
			  } 
	 
	 
	    }

	 else if(numDate.indexOf('/')>0)
	 {
	  
	   if(numDate.length!=8 && numDate.length!= 10) { 
					 alert("Enter valid Date");
					 date.value='';
					 date.focus();
			 } else {
				   
				   if(numDate.length==8)
				    {
				      numDate=numDate.substring(0,6)+'20'+numDate.substring(6);
				    }
				   date.value=numDate;
			  } 
	 
	  
	 }

	 else if(numDate.length!=6 && numDate.length!= 8) { 
			 alert("Enter valid Date");
			 date.value='';
			 date.focus();
	 } else {
		   if(numDate.length==6) {
			  numDate=numDate.substring(0,2)+'/'+numDate.substring(2,4)+'/20'+numDate.substring(4);
		   } else {
			 numDate=numDate.substring(0,2)+'/'+numDate.substring(2,4)+'/'+numDate.substring(4);
		   }	   
		   date.value=numDate;
	  } 
 }  	



<!-- Original:  Sandeep Tamhankar (stamhankar@hotmail.com) -->

<!-- This script and many more are available free online at -->
<!-- The JavaScript Source!! http://javascript.internet.com -->

<!-- Begin
/* Here's the list of tokens we support:
   m (or M) : month number, one or two digits.
   mm (or MM) : month number, strictly two digits (i.e. April is 04).
   d (or D) : day number, one or two digits.
   dd (or DD) : day number, strictly two digits.
   y (or Y) : year, two or four digits.
   yy (or YY) : year, strictly two digits.
   yyyy (or YYYY) : year, strictly four digits.
   mon : abbreviated month name (April is apr, Apr, APR, etc.)
   Mon : abbreviated month name, mixed-case (i.e. April is Apr only).
   MON : abbreviated month name, all upper-case (i.e. April is APR only).
   mon_strict : abbreviated month name, all lower-case (i.e. April is apr 
         only).
   month : full month name (April is april, April, APRIL, etc.)
   Month : full month name, mixed-case (i.e. April only).
   MONTH: full month name, all upper-case (i.e. APRIL only).
   month_strict : full month name, all lower-case (i.e. april only).
   h (or H) : hour, one or two digits.
   hh (or HH) : hour, strictly two digits.
   min (or MIN): minutes, one or two digits.
   mins (or MINS) : minutes, strictly two digits.
   s (or S) : seconds, one or two digits.
   ss (or SS) : seconds, strictly two digits.
   ampm (or AMPM) : am/pm setting.  Valid values to match this token are
         am, pm, AM, PM, a.m., p.m., A.M., P.M.
*/
// Be careful with this pattern.  Longer tokens should be placed before shorter
// tokens to disambiguate them.  For example, parsing "mon_strict" should 
// result in one token "mon_strict" and not two tokens "mon" and a literal
// "_strict".

var tokPat=new RegExp("^month_strict|month|Month|MONTH|yyyy|YYYY|mins|MINS|mon_strict|ampm|AMPM|mon|Mon|MON|min|MIN|dd|DD|mm|MM|yy|YY|hh|HH|ss|SS|m|M|d|D|y|Y|h|H|s|S");

// lowerMonArr is used to map months to their numeric values.

var lowerMonArr={jan:1, feb:2, mar:3, apr:4, may:5, jun:6, jul:7, aug:8, sep:9, oct:10, nov:11, dec:12}

// monPatArr contains regular expressions used for matching abbreviated months
// in a date string.

var monPatArr=new Array();
monPatArr['mon_strict']=new RegExp(/jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec/);
monPatArr['Mon']=new RegExp(/Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec/);
monPatArr['MON']=new RegExp(/JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC/);
monPatArr['mon']=new RegExp("jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec",'i');

// monthPatArr contains regular expressions used for matching full months
// in a date string.

var monthPatArr=new Array();
monthPatArr['month']=new RegExp(/^january|february|march|april|may|june|july|august|september|october|november|december/i);
monthPatArr['Month']=new RegExp(/^January|February|March|April|May|June|July|August|September|October|November|December/);
monthPatArr['MONTH']=new RegExp(/^JANUARY|FEBRUARY|MARCH|APRIL|MAY|JUNE|JULY|AUGUST|SEPTEMBER|OCTOBER|NOVEMBER|DECEMBER/);
monthPatArr['month_strict']=new RegExp(/^january|february|march|april|may|june|july|august|september|october|november|december/);

// cutoffYear is the cut-off for assigning "19" or "20" as century.  Any
// two-digit year >= cutoffYear will get a century of "19", and everything
// else gets a century of "20".

var cutoffYear=50;

// FormatToken is a datatype we use for storing extracted tokens from the
// format string.

function FormatToken (token, type) {
this.token=token;
this.type=type;
}

function parseFormatString (formatStr) {
var tokArr=new Array;
var tokInd=0;
var strInd=0;
var foundTok=0;
    
while (strInd < formatStr.length) {
if (formatStr.charAt(strInd)=="%" &&
(matchArray=formatStr.substr(strInd+1).match(tokPat)) != null) {
strInd+=matchArray[0].length+1;
tokArr[tokInd++]=new FormatToken(matchArray[0],"symbolic");
} else {

// No token matched current position, so current character should 
// be saved as a required literal.

if (tokInd>0 && tokArr[tokInd-1].type=="literal") {

// Literal tokens can be combined.Just add to the last token.

tokArr[tokInd-1].token+=formatStr.charAt(strInd++);
}
else {
tokArr[tokInd++]=new FormatToken(formatStr.charAt(strInd++), "literal");
      }
   }
}
return tokArr;
}

/* buildDate does all the real work.It takes a date string and format string,
 tries to match the two up, and returns a Date object (with the supplied date
 string value).If a date string doesn't contain all the fields that a Date
 object contains (for example, a date string with just the month), all
 unprovided fields are defaulted to those characteristics of the current
 date. Time fields that aren't provided default to 0.Thus, a date string
 like "3/30/2000" in "%mm/%dd/%yyyy" format results in a Date object for that
 date at midnight.formatStr is a free-form string that indicates special
 tokens via the % character.Here are some examples that will return a Date
 object:

 buildDate('3/30/2000','%mm/%dd/%y') // March 30, 2000
 buildDate('March 30, 2000','%Mon %d, %y') // Same as above.
 buildDate('Here is the date: 30-3-00','Here is the date: %dd-%m-%yy')

 If the format string does not match the string provided, an error message
 (i.e. String object) is returned.Thus, to see if buildDate succeeded, the
 caller can use the "typeof" command on the return value.For example,
 here's the dateCheck function, which returns true if a given date is
 valid,and false otherwise (and reports an error in the false case):

 function dateCheck(dateStr,formatStr) {
 var myObj=buildDate(dateStr,formatStr);
 if (typeof myObj=="object") {
 // We got a Date object, so good.
 return true;
 } else {
 // We got an error string.
 alert(myObj);
 return false;
 }
 }

*/

function buildDate(dateStr,formatStr) {
// parse the format string first.
var tokArr=parseFormatString(formatStr);
var strInd=0;
var tokInd=0;
var intMonth;
var intDay;
var intYear;
var intHour;
var intMin;
var intSec;
var ampm="";
var strOffset;

// Create a date object with the current date so that if the user only
// gives a month or day string, we can still return a valid date.

var curdate=new Date();
intMonth=curdate.getMonth()+1;
intDay=curdate.getDate();
intYear=curdate.getFullYear();

// Default time to midnight, so that if given just date info, we return
// a Date object for that date at midnight.

intHour=0;
intMin=0;
intSec=0;

// Walk across dateStr, matching the parsed formatStr until we find a 
// mismatch or succeed.

while (strInd < dateStr.length && tokInd < tokArr.length) {

// Start with the easy case of matching a literal.

if (tokArr[tokInd].type=="literal") {
if (dateStr.indexOf(tokArr[tokInd].token,strInd)==strInd) {

// The current position in the string does match the format 
// pattern.

strInd+=tokArr[tokInd++].token.length;
continue;
}
else {

// ACK! There was a mismatch; return error.

return "\"" + dateStr + "\" does not conform to the expected format: " + formatStr;
   }
}

// If we get here, we're matching to a symbolic token.
switch (tokArr[tokInd].token) {
case 'm':
case 'M':
case 'd':
case 'D':
case 'h':
case 'H':
case 'min':
case 'MIN':
case 's':
case 'S':

// Extract one or two characters from the date-time string and if 
// it's a number, save it as the month, day, hour, or minute, as
// appropriate.

curChar=dateStr.charAt(strInd);
nextChar=dateStr.charAt(strInd+1);
matchArr=dateStr.substr(strInd).match(/^\d{1,2}/);
if (matchArr==null) {

// First character isn't a number; there's a mismatch between
// the pattern and date string, so return error.

switch (tokArr[tokInd].token.toLowerCase()) {
case 'd': var unit="day"; break;
case 'm': var unit="month"; break;
case 'h': var unit="hour"; break;
case 'min': var unit="minute"; break;
case 's': var unit="second"; break;
}
return "Bad " + unit + " \"" + curChar + "\" or \"" + curChar +
nextChar + "\".";
}
strOffset=matchArr[0].length;
switch (tokArr[tokInd].token.toLowerCase()) {
case 'd': intDay=parseInt(matchArr[0],10); break;
case 'm': intMonth=parseInt(matchArr[0],10); break;
case 'h': intHour=parseInt(matchArr[0],10); break;
case 'min': intMin=parseInt(matchArr[0],10); break;
case 's': intSec=parseInt(matchArr[0],10); break;
}
break;
case 'mm':
case 'MM':
case 'dd':
case 'DD':
case 'hh':
case 'HH':
case 'mins':
case 'MINS':
case 'ss':
case 'SS':

// Extract two characters from the date string and if it's a 
// number, save it as the month, day, or hour, as appropriate.

strOffset=2;
matchArr=dateStr.substr(strInd).match(/^\d{2}/);
if (matchArr==null) {

// The two characters aren't a number; there's a mismatch 
// between the pattern and date string, so return an error
// message.

switch (tokArr[tokInd].token.toLowerCase()) {
case 'dd': var unit="day"; break;
case 'mm': var unit="month"; break;
case 'hh': var unit="hour"; break;
case 'mins': var unit="minute"; break;
case 'ss': var unit="second"; break;
}
return "Bad " + unit + " \"" + dateStr.substr(strInd,2) + 
"\".";
}
switch (tokArr[tokInd].token.toLowerCase()) {
case 'dd': intDay=parseInt(matchArr[0],10); break;
case 'mm': intMonth=parseInt(matchArr[0],10); break;
case 'hh': intHour=parseInt(matchArr[0],10); break;
case 'mins': intMin=parseInt(matchArr[0],10); break;
case 'ss': intSec=parseInt(matchArr[0],10); break;
}
break;
case 'y':
case 'Y':

// Extract two or four characters from the date string and if it's
// a number, save it as the year.Convert two-digit years to four
// digit years by assigning a century of '19' if the year is >= 
// cutoffYear, and '20' otherwise.

if (dateStr.substr(strInd,4).search(/\d{4}/) != -1) {

// Four digit year.

intYear=parseInt(dateStr.substr(strInd,4),10);
strOffset=4;
}
else {
if (dateStr.substr(strInd,2).search(/\d{2}/) != -1) {

// Two digit year.

intYear=parseInt(dateStr.substr(strInd,2),10);
if (intYear>=cutoffYear) {
intYear+=1900;
}
else {
intYear+=2000;
}
strOffset=2;
}
else {

// Bad year; return error.

return "Bad year \"" + dateStr.substr(strInd,2) + 
"\". Must be two or four digits.";
   }
}
break;
case 'yy':
case 'YY':

// Extract two characters from the date string and if it's a 
// number, save it as the year.Convert two-digit years to four 
// digit years by assigning a century of '19' if the year is >= 
// cutoffYear, and '20' otherwise.

if (dateStr.substr(strInd,2).search(/\d{2}/) != -1) {

// Two digit year.

intYear=parseInt(dateStr.substr(strInd,2),10);
if (intYear>=cutoffYear) {
intYear+=1900;
}
else {
intYear+=2000;
}
strOffset=2;
} else {
// Bad year; return error
return "Bad year \"" + dateStr.substr(strInd,2) + 
"\". Must be two digits.";
}
break;
case 'yyyy':
case 'YYYY':

// Extract four characters from the date string and if it's a 
// number, save it as the year.

if (dateStr.substr(strInd,4).search(/\d{4}/) != -1) {

// Four digit year.

intYear=parseInt(dateStr.substr(strInd,4),10);
strOffset=4;
}
else {

// Bad year; return error.

return "Bad year \"" + dateStr.substr(strInd,4) + 
"\". Must be four digits.";
}
break;
case 'mon':
case 'Mon':
case 'MON':
case 'mon_strict':

// Extract three characters from dateStr and parse them as 
// lower-case, mixed-case, or upper-case abbreviated months,
// as appropriate.

monPat=monPatArr[tokArr[tokInd].token];
if (dateStr.substr(strInd,3).search(monPat) != -1) {
intMonth=lowerMonArr[dateStr.substr(strInd,3).toLowerCase()];
}
else {

// Bad month, return error.

switch (tokArr[tokInd].token) {
case 'mon_strict': caseStat="lower-case"; break;
case 'Mon': caseStat="mixed-case"; break;
case 'MON': caseStat="upper-case"; break;
case 'mon': caseStat="between Jan and Dec"; break;
}
return "Bad month \"" + dateStr.substr(strInd,3) + 
"\". Must be " + caseStat + ".";
}
strOffset=3;
break;
case 'month':
case 'Month':
case 'MONTH':
case 'month_strict':

// Extract a full month name at strInd from dateStr if possible.

monPat=monthPatArr[tokArr[tokInd].token];
matchArray=dateStr.substr(strInd).match(monPat);
if (matchArray==null) {

// Bad month, return error.

return "Can't find a month beginning at \"" +
dateStr.substr(strInd) + "\".";
}

// It's a good month.

intMonth=lowerMonArr[matchArray[0].substr(0,3).toLowerCase()];
strOffset=matchArray[0].length;
break;
case 'ampm':
case 'AMPM':
matchArr=dateStr.substr(strInd).match(/^(am|pm|AM|PM|a\.m\.|p\.m\.|A\.M\.|P\.M\.)/);
if (matchArr==null) {

// There's no am/pm in the string.Return error msg.

return "Missing am/pm designation.";
}

// Store am/pm value for later (as just am or pm, to make things
// easier later).

if (matchArr[0].substr(0,1).toLowerCase() == "a") {

// This is am.

ampm = "am";
}
else {
ampm = "pm";
}
strOffset = matchArr[0].length;
break;
}
strInd += strOffset;
tokInd++;
}
if ((tokInd != tokArr.length || strInd != dateStr.length) && (dateStr.length>0) ) {

/* We got through the whole date string or format string, but there's 
 more data in the other, so there's a mismatch. */

return "\"" + dateStr + "\" is either missing desired information or has more information than the expected format: " + formatStr;
}

// Make sure all components are in the right ranges.

if (intMonth < 1 || intMonth > 12) {
return "Month must be between 1 and 12.";
}
if (intDay < 1 || intDay > 31) {
return "Day must be between 1 and 31.";
}

// Make sure user doesn't put 31 for a month that only has 30 days

if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && intDay == 31) {
return "Month "+intMonth+" doesn't have 31 days!";
}

// Check for February date validity (including leap years) 

if (intMonth == 2) {

// figure out if "year" is a leap year; don't forget that
// century years are only leap years if divisible by 400

var isleap=(intYear%4==0 && (intYear%100!=0 || intYear%400==0));
if (intDay > 29 || (intDay == 29 && !isleap)) {
return "February " + intYear + " doesn't have " + intDay + 
" days!";
   }
}

// Check that if am/pm is not provided, hours are between 0 and 23.

if (ampm == "") {
if (intHour < 0 || intHour > 23) {
return "Hour must be between 0 and 23 for military time.";
   }
}
else {

// non-military time, so make sure it's between 1 and 12.

if (intHour < 1|| intHour > 12) {
return "Hour must be between 1 and 12 for standard time.";
   }
}

// If user specified amor pm, convert intHour to military.

if (ampm=="am" && intHour==12) {
intHour=0;
}
if (ampm=="pm" && intHour < 12) {
intHour += 12;
}
if (intMin < 0 || intMin > 59) {
return "Minute must be between 0 and 59.";
}
if (intSec < 0 || intSec > 59) {
return "Second must be between 0 and 59.";
}
return new Date(intYear,intMonth-1,intDay,intHour,intMin,intSec);
}
function dateCheck(dateStr,formatStr) {
var myObj = buildDate(dateStr,formatStr);
if (typeof myObj == "object") {

// We got a Date object, so good.

return true;
}
else {

// We got an error string.

alert(myObj);
return false;
   }
}
//  End -->

function getCookie(name) {
    var dc = document.cookie;
    var prefix = name + "=";
    var begin = dc.indexOf("; " + prefix);
    if (begin == -1) {
        begin = dc.indexOf(prefix);
        if (begin != 0) return null;
    } else {
        begin += 2;
    }
    var end = document.cookie.indexOf(";", begin);
    if (end == -1) {
        end = dc.length;
    }
    return unescape(dc.substring(begin + prefix.length, end));
}
function deleteCookie(name, path, domain) {
    if (getCookie(name)) {
        document.cookie = name + "=" +
            ((path) ? "; path=" + path : "") +
            ((domain) ? "; domain=" + domain : "") +
            "; expires=Thu, 01-Jan-70 00:00:01 GMT";
    }
}


function setFocusCookie(value) {
    document.cookie= 'focus' + "=" + escape(value);
}

window.onerror = handleError;
//Common Validation
</script>