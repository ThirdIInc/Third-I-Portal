//global variables
var req;
var which;

//Month Attendance: Unlock Attendance 

function retrieveUnLockURL(url, formName)
{
	try
	{
		url = url + getFormAsStringUnLock(formName);
	}
	catch(e)
	{
		alert(e);
	}
	if (window.XMLHttpRequest) // Non-IE browsers
	{ 
		req = new XMLHttpRequest();//XMLHttpRequest object is created
	    req.onreadystatechange = processStateChangeUnLock;//XMLHttpRequest object is configured with a callback function
	    try 
	    {
	    	/**
	    	 * open("HTTP method", "URL", syn/asyn), for asyn-true
	    	 * if false, send operations are synchronous, browser doesn't accept any input/output
	    	**/
	    	req.open("GET", url, true);
	    }
	    catch (e) 
	    {
	      	alert("Problem Communicating with Server\n"+e);
	    }
	    req.send(null);
	} 
	else if (window.ActiveXObject) // IE 
	{
		req = new ActiveXObject("Microsoft.XMLHTTP");
	    if (req) 
	    {
	    	req.onreadystatechange = processStateChangeUnLock;        
	      	req.open("GET", url, true);
	       	req.send();
	    }
	}    
}

function getFormAsStringUnLock(formName)
{
	try
	{
		var returnString = "";
		var formElements = document.forms[formName].elements;
		for (var i = formElements.length - 1; i >= 0; i--)
 		{
 			if(formElements[i].name == 'attdnCode' || formElements[i].name == 'status' || formElements[i].name == 'lockFlag')
 			{
 				returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
 			}
 		}
	}
	catch(e)
	{
		alert(e.description);
	}
	return returnString;
}

function processStateChangeUnLock() 
{
	//0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
	if(req.readyState == 4) // Complete
	{
		if (req.status == 200) // OK response
	    {
	    	//responseXML:XML document of data returned from the server
	    	var res = req.responseText;//String version of data returned from the server
	    	document.getElementById('paraFrm_lockFlag').value = 'false';
	    	alert(res);
		}
		parent.frames[2].name='main';
	}
}





//Month Attendance: Remove Employees

function retrieveRemURL(url, formName)
{
	try
	{
		url = url + getFormAsStringRem(formName);
	}
	catch(e)
	{
		alert(e);
	}
	if (window.XMLHttpRequest) // Non-IE browsers
	{ 
		req = new XMLHttpRequest();
	    req.onreadystatechange = processStateChangeRem;
	    try 
	    {
	    	req.open("GET", url, true); //was get 
	    }
	    catch (e) 
	    {
	      	alert("Problem Communicating with Server\n"+e);
	    }
	    req.send(null);
	} 
	else if (window.ActiveXObject) // IE 
	{
		req = new ActiveXObject("Microsoft.XMLHTTP");
	    if (req) 
	    {
	    	req.onreadystatechange = processStateChangeRem;        
	      	req.open("GET", url, true);
	       	req.send();
	    }
	}    
}

function getFormAsStringRem(formName)
{
	try
	{
		var returnString = "";
		var formElements = document.forms[formName].elements;
		for (var i = formElements.length - 1; i >= 0; i--)
 		{
 			if(formElements[i].name == 'attdnCode' || formElements[i].name == 'remEmpList')
 			{
 				returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
 			}
 		}
	}
	catch(e)
	{
		alert(e.description);
	}
	return returnString;
}

function processStateChangeRem() 
{
	if(req.readyState == 4) // Complete 
	{
		alert(req.readyState);
		if (req.status == 200) // OK response
	    {
	    	alert(req.status);
	    	var res = req.responseText;
	    	alert(res);
		}
		parent.frames[2].name = 'main';
	}
}








//Month Attendance: Recalculate Attendance 
var eRecal;

function retrieveRecalURL(url, formName, empRecal)
{
	try
	{
		eRecal = empRecal;
		url = url + getFormAsStringRecal(formName) +'&'+ Math.random();
	}
	catch(e)
	{
		alert(e);
	}
	if (window.XMLHttpRequest) // Non-IE browsers
	{ 
		req = new XMLHttpRequest(); //XMLHttpRequest object is created
		
		//XMLHttpRequest object is configured with a callback function
	    req.onreadystatechange = processStateChangeRecal;
	    try 
	    {
	    	/**
	    	 * open("HTTP method", "URL", syn/asyn), for asyn-true
	    	 * if false, send operations are synchronous, browser doesn't accept any input/output
	    	**/
	    	req.open("POST", url, true);
	    }
	    catch (e) 
	    {
	      	alert("Problem Communicating with Server\n"+e);
	    }
	    req.send(null);
	} 
	else if (window.ActiveXObject) // IE 
	{
		req = new ActiveXObject("Microsoft.XMLHTTP");
	    if (req) 
	    {
	    	req.onreadystatechange = processStateChangeRecal;        
	      	req.open("POST", url, true);
	       	req.send();
	    }
	}    
}

function getFormAsStringRecal(formName)
{
	try
	{
		var returnString = "";
		var formElements = document.forms[formName].elements;
		for (var i = formElements.length - 1; i >= 0; i--)
 		{
 			if(formElements[i].name == 'attdnCode' || formElements[i].name == 'remEmps' || formElements[i].name == 'attConnFlag'
 			 || formElements[i].name == 'month' || formElements[i].name == 'year' || formElements[i].name == 'remDate' 
 			 || formElements[i].name == 'remLMs' || formElements[i].name == 'remHDs' || formElements[i].name == 'brnHDayFlag' 
 			 || formElements[i].name == 'levConnFlag')
 			{
 				returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
 			}
 		}
	}
	catch(e)
	{
		alert(e.description);
	}
	return returnString;
}

function processStateChangeRecal() 
{
	document.getElementById("overlay").style.visibility = "visible";
	document.getElementById("overlay").style.display = "block";
	document.getElementById("progressBar").style.visibility = "visible";
	document.getElementById("progressBar").style.display = "block";
	
	//0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
	if(req.readyState == 4) // Complete
	{
		if (req.status == 200) // OK response
	    {
	    	try
	    	{
	    		//responseXML:XML document of data returned from the server
		    	var res = req.responseText; //String version of data returned from the server
		    	
		    	if(res == "")
		    	{
		    		document.getElementById("overlay").style.visibility = "hidden";
					document.getElementById("overlay").style.display = "none";
					document.getElementById("progressBar").style.visibility = "hidden";
					document.getElementById("progressBar").style.display = "none";
		    	}
		    	else
		    	{
		    		splitRecordsEmpWise(res);
		    		document.getElementById("overlay").style.visibility = "hidden";
					document.getElementById("overlay").style.display = "none";
					document.getElementById("progressBar").style.visibility = "hidden";
					document.getElementById("progressBar").style.display = "none";
		    	}
	    	}
	    	catch(e)
	    	{
	    		document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";
	    	}
		}
		else
		{
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";
		}
		parent.frames[2].name='main';
	}
	else
	{
		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
	}
}

function splitRecordsEmpWise(res)
{
	var empRecs = res.split('@');
	for(var i = 0; i < empRecs.length; i++)
	{
		if(empRecs[i] != '')
		{
			var attnRecs = empRecs[i].split(',');
			
			for(var j = 0; j < eRecal.length; j++)
			{
				var eRec = eRecal[j].split(',');
				var row = eRec[0];
				var empCode = eRec[1];
				var eId = attnRecs[0];
				
				if(empCode == eId)
				{
					var attdnDays = attnRecs[1];
					var weeklyOffs = attnRecs[2];
					var holidays = attnRecs[3];
					var paidLevs = attnRecs[4];
					var unPaidLevs = attnRecs[5];
					var salDays = attnRecs[6];
					var lateMarks = attnRecs[7];
					var halfDays = attnRecs[8];					
					var reCal = attnRecs[9];
					var presentDays = attnRecs[10];
					var recoveryDays = attnRecs[11];
					
					document.getElementById('attdnDays'+row).value = attdnDays;
					document.getElementById('weeklyOffs'+row).value = weeklyOffs;
					document.getElementById('holidays'+row).value = holidays;
					document.getElementById('paidLevs'+row).value = paidLevs;
					document.getElementById('unPaidLevs'+row).value = unPaidLevs;
					document.getElementById('salDays'+row).value = salDays;
					document.getElementById('lateMarks'+row).value = lateMarks;
					document.getElementById('halfDays'+row).value = halfDays;
					document.getElementById('reCal'+row).value = reCal;
					document.getElementById('eSave'+row).value = reCal;
					document.getElementById('presentDays'+row).value = presentDays;
					document.getElementById('recoveryDays'+row).value = recoveryDays;
					
					document.getElementById('remRec'+row).checked = false;
					wrongRecs(row);
				}
			}
		}
	}
	document.getElementById('selectId').checked = false;
}

function wrongRecs(id)
{
	try
	{
		var wrong = 'false';
		var attdnDays = eval(document.getElementById('attdnDays'+id).value);
		var salDays = eval(document.getElementById('salDays'+id).value);
		var presentDays = eval(document.getElementById('presentDays'+id).value);
		var unPaidLevs = eval(document.getElementById('unPaidLevs'+id).value);
				
		if(attdnDays < 0 || salDays > presentDays || unPaidLevs < 0)
		{
			wrong = 'true';
		}
		
		if(wrong == 'true')
		{
			document.getElementById('eToken'+id).style.background = '#FF8383';
			document.getElementById('eName'+id).style.background = '#FF8383';
			document.getElementById('attdnDays'+id).style.background = '#FF8383';
			document.getElementById('weeklyOffs'+id).style.background = '#FF8383';
			document.getElementById('holidays'+id).style.background = '#FF8383';
			document.getElementById('paidLevs'+id).style.background = '#FF8383';
			document.getElementById('unPaidLevs'+id).style.background = '#FF8383';
			document.getElementById('recoveryDays'+i).style.background = '#FF8383';
			document.getElementById('salDays'+id).style.background = '#FF8383';
		}
		else
		{
			var eSearch = document.getElementById('eSearch'+id).value;
			
			if(eSearch == 'true')
			{
				document.getElementById('eToken'+id).style.background = '#FDFBB0';
				document.getElementById('eName'+id).style.background = '#FDFBB0';
				document.getElementById('attdnDays'+id).style.background = '#FDFBB0';
				document.getElementById('weeklyOffs'+id).style.background = '#FDFBB0';
				document.getElementById('holidays'+id).style.background = '#FDFBB0';
				document.getElementById('paidLevs'+id).style.background = '#FDFBB0';
				document.getElementById('unPaidLevs'+id).style.background = '#FDFBB0';
				document.getElementById('recoveryDays'+i).style.background = '#FDFBB0';
				document.getElementById('salDays'+id).style.background = '#FDFBB0';
			}
			else
			{
				document.getElementById('eToken'+id).style.background = '#F2F2F2';
				document.getElementById('eName'+id).style.background = '#F2F2F2';
				document.getElementById('attdnDays'+id).style.background = 'white';
				document.getElementById('weeklyOffs'+id).style.background = 'white';
				document.getElementById('holidays'+id).style.background = 'white';
				document.getElementById('paidLevs'+id).style.background = 'white';
				document.getElementById('unPaidLevs'+id).style.background = 'white';
				document.getElementById('recoveryDays'+i).style.background = 'white';
				document.getElementById('salDays'+id).style.background = '#F2F2F2';
			}		
		}
	} catch(e){alert(e.description);}
}