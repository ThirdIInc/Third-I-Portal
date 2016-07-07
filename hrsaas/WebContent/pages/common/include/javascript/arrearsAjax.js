
var req;
var which;
var formNameGlo;

function retrieveURLArrears(url,nameOfFormToPost) {
    //get the (form based) params to push up as part of the get request
   formNameGlo = nameOfFormToPost;
    try {
      	url=url+getFormAsStringLock(nameOfFormToPost);
      
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChangeArrears;
    
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      // alert('process IE');
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChangeArrears;        
        req.open("GET", url, true);
        req.send();
      }
    }     
    
  }
  
    function retrieveURLArrearsRecal(url,nameOfFormToPost) {
    //get the (form based) params to push up as part of the get request
   formNameGlo = nameOfFormToPost;
    try {
      	url=url+getFormAsStringLock(nameOfFormToPost);
      
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChangeArrearsRecal;
    
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      // alert('process IE');
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChangeArrearsRecal;        
        req.open("GET", url, true);
        req.send();
      }
    }     
    
  }
  
  function retrieveURLMonArrearsRecal(url,nameOfFormToPost) {
    //get the (form based) params to push up as part of the get request
   formNameGlo = nameOfFormToPost;
    try {
      	url=url+getFormAsStringLock(nameOfFormToPost);
      
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChangeMonArrearsRecal;
    
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      // alert('process IE');
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChangeMonArrearsRecal;        
        req.open("GET", url, true);
        req.send();
      }
    }     
    
  }
  
  function retrieveURLCashArrRecal(url,nameOfFormToPost) {
    //get the (form based) params to push up as part of the get request
   formNameGlo = nameOfFormToPost;
    try {
      	url=url+getFormAsStringLock(nameOfFormToPost);
      
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChangeCashArrRecal;
    
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      // alert('process IE');
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChangeCashArrRecal;        
        req.open("GET", url, true);
        req.send();
      }
    }     
    
  }
  
  
  function setValuesArrears(responseText)
  {
  	 
  var resEmpSplit = responseText.split("@");
 //alert(resEmpSplit.length);
  returnString ="";
  
  if(resEmpSplit.length>1){
 	 for(var j=0; j<resEmpSplit.length;j++){
 		  setFormValuesArrears(resEmpSplit[j]);
	  }
	 }
  else{
  		setFormValuesArrears(responseText);
	}
	  
  }
  
  function setValuesArrearsRecal(responseText)
  {
  	 try{
  var resEmpSplit = responseText.split("@");
 //alert(resEmpSplit.length);
  returnString ="";
  
  if(resEmpSplit.length>1){
 	 for(var j=0; j<resEmpSplit.length;j++){
 		  setFormValuesArrearsRecal(resEmpSplit[j]);
	  }
	 }
  else{
  		setFormValuesArrearsRecal(responseText);
	}
	  }catch(e)
	  {
	  	alert(e);
	  }
  }
  function setValuesMonArrearsRecal(responseText)
  {
  	 try{
  var resEmpSplit = responseText.split("@");
 //alert(resEmpSplit.length);
  returnString ="";
  
  if(resEmpSplit.length>1){
 	 for(var j=0; j<resEmpSplit.length;j++){
 		  setFormValuesMonArrearsRecal(resEmpSplit[j]);
	  }
	 }
  else{
  		setFormValuesMonArrearsRecal(responseText);
	}
	  }catch(e)
	  {
	  	alert(e);
	  }
  }
  
   function setValuesCashArrRecal(responseText)
  {
  	 try{
  var resEmpSplit = responseText.split("@");
 //alert(resEmpSplit.length);
  returnString ="";
  
  if(resEmpSplit.length>1){
 	 for(var j=0; j<resEmpSplit.length;j++){
 		  setFormValuesCashArrRecal(resEmpSplit[j]);
	  }
	 }
  else{
  		setFormValuesCashArrRecal(responseText);
	}
	  }catch(e)
	  {
	  	alert(e);
	  }
  }
  
  function setFormValuesArrears(responseText)
  {
  		//formElements=document.forms[formNameGlo].elements;
 		var resStrSplit = responseText.split("#"); 		
  		var rowNo =  resStrSplit[0];  		
  		var x=1;
  		for(var k=8; k<resStrSplit.length-2;k++){  	
			document.getElementById(rowNo+'c'+x).value=resStrSplit[k];		
			x++;								 		 			
		}
		
  }
  
  function setFormValuesArrearsRecal(responseText)
  {
  		//formElements=document.forms[formNameGlo].elements;
 		var resStrSplit = responseText.split("#"); 		
  		var rowNo =  resStrSplit[0];  		
  		var x=1;
  		for(var k=8; k<resStrSplit.length-2;k++){  	
			document.getElementById(rowNo+'c'+x).value=resStrSplit[k];		
			x++;								 		 			
		}
		document.getElementById('salDays'+rowNo).value=resStrSplit[resStrSplit.length-2];
		document.getElementById('arrDays'+rowNo).value=resStrSplit[resStrSplit.length-1];
  }
  
  function setFormValuesMonArrearsRecal(responseText)
  {
  		//formElements=document.forms[formNameGlo].elements;
 		var resStrSplit = responseText.split("#"); 		
  		var rowNo =  resStrSplit[0];  		
  		var month =  resStrSplit[resStrSplit.length-4];
  		var x=3;
  		for(var k=4; k<resStrSplit.length-6;k++){  	
			document.getElementById(rowNo+'c'+x+month).value=resStrSplit[k];		
			x++;								 		 			
		}
		var rowId = eval(resStrSplit[resStrSplit.length-1]);
		document.getElementById('eligDays'+rowId).value = resStrSplit[resStrSplit.length-2];
		document.getElementById('orgArrDays'+rowId).value = eval(document.getElementById('saldays'+rowId).value);
  }
  
  function setFormValuesCashArrRecal(responseText)
  {
  		//formElements=document.forms[formNameGlo].elements;
 		var resStrSplit = responseText.split("#"); 		
  		var rowNo =  resStrSplit[0];  		
  		var x=1;
  		for(var k=5; k<resStrSplit.length-1;k++){  	
			document.getElementById(rowNo+'c'+x).value=resStrSplit[k];
			x++;								 		 			
		}
		document.getElementById('arrDays'+rowNo).value=resStrSplit[resStrSplit.length-1];
  }
  
  function processStateChangeArrears() { 
  	
  	document.getElementById("msgDiv").style.visibility='visible';
  	document.getElementById("msgDiv").innerHTML='';
  		
   	  if (req.readyState == 4) { // Complete
      	if (req.status == 200) { // OK response   
      			if(req.responseText=="Salary Locked"){
      				alert("Salary already Locked");
      			}
      			
      			else{
         		setValuesArrears(req.responseText);
              	nkumar= splitTextIntoSpan(req.responseText);
	       		replaceExistingWithNewHtml(nkumar);	  
	       		document.getElementById("msgDiv").style.visibility='hidden';     		  
	       		}       
       	} else {
       		alert("Session Expired Please Relogin");
      	}      
    } else {
			document.getElementById("msgDiv").innerHTML='<table width=300 height=100 border=0 class=formbg><tr><td width=300 height=80><b><center><font color=red>processing....Please wait</td></tr></table>';    	
    }
  }
  
  function processStateChangeArrearsRecal() { 
  	try{
  	document.getElementById("msgDiv").style.visibility='visible';
  	document.getElementById("msgDiv").innerHTML='';
  		
   	  if (req.readyState == 4) { // Complete
      	if (req.status == 200) { // OK response   
      			if(req.responseText=="Salary Locked"){
      				alert("Salary already Locked");
      			}
      			
      			else{
         		setValuesArrearsRecal(req.responseText);
              	nkumar= splitTextIntoSpan(req.responseText);
	       		replaceExistingWithNewHtml(nkumar);	  
	       		document.getElementById("msgDiv").style.visibility='hidden';     		  
	       		}       
       	} else {
       		alert("Session Expired Please Relogin");
      	}      
    } else {
			document.getElementById("msgDiv").innerHTML='<table width=300 height=100 border=0 class=formbg><tr><td width=300 height=80><b><center><font color=red>processing....Please wait</td></tr></table>';    	
    }
    }catch(e)
    {
    	alert(e);
    }
  }
  
  function processStateChangeMonArrearsRecal() { 
  	try{
  	document.getElementById("msgDiv").style.visibility='visible';
  	document.getElementById("msgDiv").innerHTML='';
  		
   	  if (req.readyState == 4) { // Complete
      	if (req.status == 200) { // OK response   
      			if(req.responseText=="Salary Locked"){
      				alert("Salary already Locked");
      			}
      			
      			else{
         		setValuesMonArrearsRecal(req.responseText);
              	nkumar= splitTextIntoSpan(req.responseText);
	       		replaceExistingWithNewHtml(nkumar);	  
	       		document.getElementById("msgDiv").style.visibility='hidden';     		  
	       		}       
       	} else {
       		alert("Session Expired Please Relogin");
      	}      
    } else {
			document.getElementById("msgDiv").innerHTML='<table width=300 height=100 border=0 class=formbg><tr><td width=300 height=80><b><center><font color=red>processing....Please wait</td></tr></table>';    	
    }
    }catch(e)
    {
    	alert(e);
    }
  }
  
  function processStateChangeCashArrRecal() { 
  	try{
  	document.getElementById("msgDiv").style.visibility='visible';
  	document.getElementById("msgDiv").innerHTML='';
  		
   	  if (req.readyState == 4) { // Complete
      	if (req.status == 200) { // OK response   
      			if(req.responseText=="Salary Locked"){
      				alert("Salary already Locked");
      			}
      			
      			else{
         		setValuesCashArrRecal(req.responseText);
              	nkumar= splitTextIntoSpan(req.responseText);
	       		replaceExistingWithNewHtml(nkumar);	  
	       		document.getElementById("msgDiv").style.visibility='hidden';     		  
	       		}       
       	} else {
       		alert("Session Expired Please Relogin");
      	}      
    } else {
			document.getElementById("msgDiv").innerHTML='<table width=300 height=100 border=0 class=formbg><tr><td width=300 height=80><b><center><font color=red>processing....Please wait</td></tr></table>';    	
    }
    }catch(e)
    {
    	alert(e);
    }
  }
  
  function getFormAsStringLock(formName)
{
	try
	{
		var returnString = "";
		var formElements = document.forms[formName].elements;
		for (var i = formElements.length - 1; i >= 0; i--)
 		{
	 		if(formElements[i].name=='chkEmp' || formElements[i].name=='chkEmp')
	 		{ 		
	 		if(formElements[i].checked){
	 		 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value)+"&abcd="+Math.random();
	 		} 
	 		 }
 			if(formElements[i].name == 'arrCode'||formElements[i].name == 'arrYear' )
 			{
 				returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
 			}
 			if(formElements[i].name == 'divEsicZone')
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

function processStateChangeLock() 
{
	if(req.readyState == 4) // Complete 
	{
		if (req.status == 200) // OK response
	    {
	    	var res = req.responseText;
	    	if(res == "ArrearsLocked")
	    	{
	    		document.getElementById('paraFrm_status').value = 'Locked';
	    		alert("Arrears locked successfully");
	    	}
	    	else if(res == "ArrearscannotLocked")
	    	{
	    		document.getElementById('paraFrm_status').value = 'Pending';
	    		alert("Arrears cannot be locked");
	    	}
	    	else if(res == "ArrearsUnlocked")
	    	{
	    		document.getElementById('paraFrm_status').value = 'Pending';
	    		alert("Arrears unlocked successfully");
	    	}
	    	else if(res == "ArrearscannotUnlocked")
	    	{
	    		document.getElementById('paraFrm_status').value = 'Locked';
	    		alert("Arrears cannot be unlocked");
	    	}
	    	
	    		
	    	//document.getElementById('paraFrm_remBtn').disabled = true;	    	
		}
	}
}

function retrieveLockURL(url, formName)
{
	try
	{
		url = url + getFormAsStringLock(formName);
	}
	catch(e)
	{
		alert(e);
	}
	if (window.XMLHttpRequest) // Non-IE browsers
	{ 
		req = new XMLHttpRequest();
	    req.onreadystatechange = processStateChangeLock;
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
	    	req.onreadystatechange = processStateChangeLock;        
	      	req.open("GET", url, true);
	       	req.send();
	    }
	}    
}
  
  
 function processStateChangeLock() 
{
	if(req.readyState == 4) // Complete 
	{
		if (req.status == 200) // OK response
	    {
	    	var res = req.responseText;
	    	if(res == "ArrearsLocked")
	    	{
	    		document.getElementById('paraFrm_status').value = 'Locked';
	    		alert("Arrears locked successfully");
	    	}
	    	else if(res == "ArrearscannotLocked")
	    	{
	    		document.getElementById('paraFrm_status').value = 'Pending';
	    		alert("Arrears cannot be locked");
	    	}
	    	else if(res == "ArrearsUnlocked")
	    	{
	    		document.getElementById('paraFrm_status').value = 'Pending';
	    		alert("Arrears unlocked successfully");
	    	}
	    	else if(res == "ArrearscannotUnlocked")
	    	{
	    		document.getElementById('paraFrm_status').value = 'Locked';
	    		alert("Arrears cannot be unlocked");
	    	}
	    	
	    		
	    	//document.getElementById('paraFrm_remBtn').disabled = true;	    	
		}
	}
}

 function splitTextIntoSpan(textToSplit){
 
  	//Split the document
 	returnElements=textToSplit.split("</span>")
 	 
 	//Process each of the elements 	
 	for ( var i=returnElements.length-1; i>=0; --i ){
 		
 		//Remove everything before the 1st span
 		spanPos = returnElements[i].indexOf("<span");
 		 
 		//if we find a match , take out everything before the span
 		if(spanPos>0){
 			subString=returnElements[i].substring(spanPos);
 			returnElements[i]=subString;
 		 
 		} 
 	}
 	 
 	return returnElements;
 }
 
 function replaceExistingWithNewHtml(newTextElements){
 
 	//loop through newTextElements
 	for ( var i=newTextElements.length-1; i>=0; --i ){
  
 		//check that this begins with <span
 		if(newTextElements[i].indexOf("<span")>-1){
 			
 			//get the name - between the 1st and 2nd quote mark
 			startNamePos=newTextElements[i].indexOf('"')+1;
 			endNamePos=newTextElements[i].indexOf('"',startNamePos);
 			name=newTextElements[i].substring(startNamePos,endNamePos);
 			
 			//get the content - everything after the first > mark
 			startContentPos=newTextElements[i].indexOf('>')+1;
 			content=newTextElements[i].substring(startContentPos);
 			
 			//Now update the existing Document with this element
 			
	 			//check that this element exists in the document
	 			if(document.getElementById(name)){
	 			
	 			//	alert("Replacing Element:"+name);
	 			//	document.getElementById('paraFrm').setAttribute('target','main');
	 				document.getElementById(name).innerHTML = content;
	 				
	 			} else {
	 				 
	 			}
 		}
 	}
 	//alert( "Records Modified Successfully ");
 }
  