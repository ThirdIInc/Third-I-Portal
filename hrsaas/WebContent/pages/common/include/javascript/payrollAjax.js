/**
 * payrollAjax.js
 *
 * Collection of Scripts to allow in page communication from browser to (struts) server
 * ie can reload part instead of full page
 *
 * How to use
 * ==========
 * 1) Call retrieveURL from the relevant event on the HTML page (e.g. onclick)
 * 2) Pass the url to contact (e.g. Struts Action) and the name of the HTML form to post
 * 3) When the server responds ...
 *		 - the script loops through the response , looking for <span id="name">newContent</span>
 * 		 - each <span> tag in the *existing* document will be replaced with newContent
 *
 * NOTE: <span id="name"> is case sensitive. Name *must* follow the first quote mark and end in a quote
 *		 Everything after the first '>' mark until </span> is considered content.
 *		 Empty Sections should be in the format <span id="name"></span>
 */

//global variables
  var req;
  var which;
  var formNameGlo;

  /**
   * Get the contents of the URL via an Ajax call
   * url - to get content from (e.g. /struts-ajax/sampleajax.do?ask=COMMAND_NAME_1) 
   * nodeToOverWrite - when callback is made
   * nameOfFormToPost - which form values will be posted up to the server as part 
   *					of the request (can be null)
   */
  
  function processStateChange_element(id) {
	   alert('req.readyState '+req.readyState);
  	  if (req.readyState == 4) { // Complete
		alert('inside ready state');
      if (req.status == 200) { // OK response      	
			resText= req.responseText;
			alert(resText);
       		document.getElementById(id).innerHTML = resText;         
      } else {
      //  alert("Problem with server response:\n " + req.statusText);
      }
    }    
  }
   
 

function retrieveURLOnHold(url,nameOfFormToPost,ledgerCode) {
    //get the (form based) params to push up as part of the get request
   formNameGlo = nameOfFormToPost;
    try {
      	url=url+getFormAsStringOnHold(nameOfFormToPost)+"&attCode="+ledgerCode;
      
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
    
      try {
      	req.open("POST", url, true); //was get 
      } catch (e) {
        alert("Session Expired Please Relogin");
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      // alert('process IE');
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChange;        
        req.open("POST", url, true);
        req.send();
      }
    }     
    
  }
  function retrieveURLOnHoldNew(url,nameOfFormToPost,ledgerCode) {
    //get the (form based) params to push up as part of the get request
   formNameGlo = nameOfFormToPost;
    try {
      	url=url+getFormAsStringOnHoldNew(nameOfFormToPost)+"&attCode="+ledgerCode;
      
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
    
      try {
      	req.open("POST", url, true); //was get 
      } catch (e) {
        alert("Session Expired Please Relogin");
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      // alert('process IE');
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChange;        
        req.open("POST", url, true);
        req.send();
      }
    }     
    
  }
  
  function retrieveURLTDS(url,nameOfFormToPost) {
    //get the (form based) params to push up as part of the get request
   formNameGlo = nameOfFormToPost;
    try {
      	url=url+getFormAsStringTDS(nameOfFormToPost);
      	//alert(url);
      
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChangeTDS;
    
      try {
      	req.open("POST", url, true); //was get 
      } catch (e) {
       
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      // alert('process IE');
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChangeTDS;        
        req.open("POST", url, true);
        req.send();
      }
    }     
    
  }
  
  
function retrieveURLRecal(url,nameOfFormToPost) {
    //get the (form based) params to push up as part of the get request
   formNameGlo = nameOfFormToPost;
 
    try {
      	url=url+getFormAsStringOnHold(nameOfFormToPost);
      	
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChangeRecal;
    
      try {
      	req.open("POST", url, true); //was get 
      } catch (e) {
       
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      // alert('process IE');
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChangeRecal;        
        req.open("POST", url, true);
        req.send();
      }
    }     
    
  }
  
  function retrieveURLRecalNew(url,nameOfFormToPost) {
    //get the (form based) params to push up as part of the get request
   formNameGlo = nameOfFormToPost;
    try {
      	url=url+getFormAsStringOnHoldNew(nameOfFormToPost);
      	
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChangeRecal;
    
      try {
      	req.open("POST", url, true); //was get 
      } catch (e) {
       
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      // alert('process IE');
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChangeRecal;        
        req.open("POST", url, true);
        req.send();
      }
    }     
    
  }
  

  
  
  
  function setValues(responseText)
  {
  	
  var resEmpSplit = responseText.split("@");
 //alert(resEmpSplit.length);
  returnString ="";
  
  if(resEmpSplit.length>1){
 	 for(var j=0; j<resEmpSplit.length;j++){
 		  setFormValues(resEmpSplit[j]);
	  }
	 }
  else{
  		setFormValues(responseText);
	}
	  
  }
  
  function setFormValues(responseText)
  {
  		//formElements=document.forms[formNameGlo].elements;
 		try{
 		var resStrSplit = responseText.split("#"); 		
 		
  		var empid =  resStrSplit[0];  			  		
  		for(var k=3; k<=resStrSplit.length-4;k++){  	
  			
  							
			document.getElementById(empid+'c'+k).value=resStrSplit[k];										 		 			
		}
		document.getElementById(empid+'saldays').value=resStrSplit[resStrSplit.length-3];
		}
		catch(e){
	
		}		
  }
  
  function setBackGround()
  {
  	
   var table = document.getElementById('thetable');   
   var rows = table.getElementsByTagName("tr");   
   for(i = 0; i < rows.length; i++){           
 		rows[i].style.background="";
   } 
 
  }
  
/*
   * Set as the callback method for when XmlHttpRequest State Changes 
   * used by retrieveUrl
  */
  function processStateChangeRecal() { 
  	
  	//document.getElementById("msgDiv").style.visibility='visible';
  	//document.getElementById("msgDiv").innerHTML='';
  	document.getElementById("overlay").style.visibility = "visible";
	document.getElementById("overlay").style.display = "block";
	document.getElementById("progressBar").style.visibility = "visible";
	document.getElementById("progressBar").style.display = "block";
	  		
   	  if (req.readyState == 4) { // Complete
      	if (req.status == 200) { // OK response   
      			if(req.responseText=="Salary Locked"){
      				alert("Salary already Locked");
      			}
      			
      			else{
      			try{
      			
      				if(req.responseText==""){
	      				document.getElementById("overlay").style.visibility = "hidden";
						document.getElementById("overlay").style.display = "none";
						document.getElementById("progressBar").style.visibility = "hidden";
						document.getElementById("progressBar").style.display = "none";
      				}
      				else{
		         		setValues(req.responseText);
		              	nkumar= splitTextIntoSpan(req.responseText);
			       		replaceExistingWithNewHtml(nkumar);	  
			       		//document.getElementById("msgDiv").style.visibility='hidden';   
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
       	} else {
       		//alert("Session Expired Please Relogin");
      	}      
    } else {
			//document.getElementById("msgDiv").innerHTML='<table width=300 height=100 border=0 class=formbg><tr><td width=300 height=80><b><center><font color=red>processing....Please wait</td></tr></table>';
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";    	
    }
    parent.frames[2].name="main";
  }
  
  function processStateChange() {  
  
    	//document.getElementById("msgDiv").style.visibility='visible';
  		//document.getElementById("msgDiv").innerHTML='';
  		document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
		  
   	  if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response   
      try{
      			 var chkStatus=req.responseText;
      			
      		      if(chkStatus=="Not Performed"){
      				 alert("Please Save Processed Salary");
      				  //setBackGround();
      				  document.getElementById("overlay").style.visibility = "hidden";
						document.getElementById("overlay").style.display = "none";
						document.getElementById("progressBar").style.visibility = "hidden";
						document.getElementById("progressBar").style.display = "none";  
      			 }
      			 else if(chkStatus=="Salary locked"){
      				 alert("Salary already locked");
      				  //setBackGround();
      			 }
      			
              	nkumar= splitTextIntoSpan(req.responseText);
	       		replaceExistingWithNewHtml(nkumar);
	       		//document.getElementById("msgDiv").style.visibility='hidden';
	       		document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";  
	     }
	     catch(e){
	     alert(e);
	     }       
       } else {
      // alert("Session Expired Please Relogin");
      }
    } else {
    //document.getElementById("msgDiv").innerHTML='<table width=300 height=100 border=0 class=formbg><tr><td width=300 height=80><b><center><font color=red>processing....Please wait</td></tr></table>';
    	document.getElementById("overlay").style.visibility = "visible";
		document.getElementById("overlay").style.display = "block";
		document.getElementById("progressBar").style.visibility = "visible";
		document.getElementById("progressBar").style.display = "block";
    }
    parent.frames[2].name="main";
  }
  
  function processStateChangeTDS() {  
  
    	
  
   	  if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response   
      try{
      			//alert(req.responseText);
      			var resultText = req.responseText;
      			var resultSplit = resultText.split("@");
              	nkumar= splitTextIntoSpan(req.responseText);
	       		replaceExistingWithNewHtml(nkumar);
	       		for(var i=0; i<resultSplit.length - 3;i++){
	       		var slabTax= resultSplit[i].split("#");
	       		document.getElementById("x"+i).value = slabTax[0];
	       		document.getElementById("y"+(i+1)).value = slabTax[1];
	       		}
	       		
	       		
	       		var tax = resultSplit[resultSplit.length-1].split("#");
	       		var sur = resultSplit[resultSplit.length-2].split("#");
	       		var edu = resultSplit[resultSplit.length-3].split("#");
	       		document.getElementById("taxOnIncome").value = edu[0];
	       		document.getElementById("eduCess").value = edu[1];
	       		document.getElementById("surCharge").value = sur[0];
	       		document.getElementById("netTax").value = sur[1];
	       		document.getElementById("taxPerMonth").value = tax[0];
	       
	     }
	     catch(e){
	     alert(e);
	     }       
       } else {
       //alert("Session Expired Please Relogin");
      }
    } else {
     }
    parent.frames[2].name="main";
  }
 
 /**
  * gets the contents of the form as a URL encoded String
  * suitable for appending to a url
  * @param formName to encode
  * @return string with encoded form values , beings with &
  */ 
 
  function getFormAsStringOnHold(formName) {
 	//   alert(formName);
 	//Setup the return String
 	returnString ="";
 	
  	//Get the form values
  
  	
 	formElements=document.forms[formName].elements;
 	
 
 	
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 
 			
 		if(formElements[i].name=='onHoldFlag' || formElements[i].name=='OnHoldChkFlag')
 		{ 		
 		if(formElements[i].checked){
 		 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value)+"&abcd="+Math.random();
 		} 
 		 }
 		 if(formElements[i].name=='nonIndustrialSalary.month' || formElements[i].name=='nonIndustrialSalary.year'
 		  || formElements[i].name=='nonIndustrialSalary.typeCode' || formElements[i].name=='nonIndustrialSalary.payBillNo' 
 		   || formElements[i].name=='nonIndustrialSalary.branchCode'  || formElements[i].name=='nonIndustrialSalary.deptCode' 
 		    || formElements[i].name=='nonIndustrialSalary.divisionCode' ){
 		 returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 		 }
 	}
 	
 	//return the values
 	return returnString; 
 }
 
  function getFormAsStringOnHoldNew(formName) {
 	//   alert(formName);
 	//Setup the return String
 	returnString ="";
 	
  	//Get the form values
  
 	formElements=document.forms[formName].elements;
 	
 
 	
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 
 			
 		if(formElements[i].name=='onHoldFlag' || formElements[i].name=='OnHoldChkFlag')
 		{ 		
 		if(formElements[i].checked){
 		 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value)+"&abcd="+Math.random();
 		} 
 		 }
 		 if(formElements[i].name=='month' || formElements[i].name=='year'
 		  || formElements[i].name=='employeeTypeId' || formElements[i].name=='payBillId' 
 		   || formElements[i].name=='branchId'  || formElements[i].name=='deptId' 
 		    || formElements[i].name=='divisionId' || formElements[i].name=='vpfFlag'  || formElements[i].name=='ledgerCode' 
 		     || formElements[i].name=='recoveryFlag'  || formElements[i].name=='profHandiFLag'  || formElements[i].name=='incomeTaxFlag' 
 		      || formElements[i].name=='lwfFlag'  || formElements[i].name=='lwfDebitCode'  || formElements[i].name=='lwfCreditCode'
 		      || formElements[i].name=='creditRound'|| formElements[i].name=='totalCreditRound'|| formElements[i].name=='totalDebitRound'
 		      || formElements[i].name=='netPayRound'|| formElements[i].name=='dataFlag' || formElements[i].name=='ledgerStatus'){
 		 returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 		 }
 	}
 	
 	//return the values
 	return returnString; 
 }
 
 function getFormAsStringTDS(formName) {
 	//   alert(formName);
 	//Setup the return String
 	returnString ="";
 	
  	//Get the form values
  
  	
 	formElements=document.forms[formName].elements;
 	
 
 	
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 
 		
 		 if(formElements[i].name=='empID' || formElements[i].name=='empGender'
 		  || formElements[i].name=='empAge' || formElements[i].name=='fromYear' 
 		   || formElements[i].name=='toYear'  || formElements[i].name=='netTaxableIncome' 
 		    || formElements[i].name=='taxOnIncome'  || formElements[i].name=='eduCess'
 		     || formElements[i].name=='surCharge'  || formElements[i].name=='netTax'
 		      || formElements[i].name=='taxPerMonth' || formElements[i].name=='taxPaid'
 		      || formElements[i].name=='remainMonths'  ){
 		 returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value)+"&abcd="+Math.random();
 		 }
 	}
 	
 	//return the values
 	return returnString; 
 }
 
 
 
 /**
 * Splits the text into <span> elements
 * @param the text to be parsed
 * @return array of <span> elements - this array can contain nulls
 */
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
 
 /*
  * Replace html elements in the existing (ie viewable document)
  * with new elements (from the ajax requested document)
  * WHERE they have the same name AND are <span> elements
  * @param newTextElements (output of splitTextIntoSpan)
  *					in the format <span id=name>texttoupdate
  */
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
 
 function retrieveURLRemoveOnHold(url,nameOfFormToPost,ledgerCode) {
    //get the (form based) params to push up as part of the get request
   formNameGlo = nameOfFormToPost;
    try {
      	url=url+getFormAsStringRemoveOnHold(nameOfFormToPost)+"&attCode="+ledgerCode;
      //alert('retrieveURLOnHold -- '+url);
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
    
      try {
      	req.open("POST", url, true); //was get 
      } catch (e) {
        alert("Session Expired Please Relogin");
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      // alert('process IE');
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChange;        
        req.open("POST", url, true);
        req.send();
      }
    }     
    
  }
  
   function retrieveURLRemoveOnHoldNew(url,nameOfFormToPost,ledgerCode) {
    //get the (form based) params to push up as part of the get request
   formNameGlo = nameOfFormToPost;
    try {
      	url=url+getFormAsStringRemoveOnHoldNew(nameOfFormToPost)+"&attCode="+ledgerCode;
      //alert('retrieveURLOnHold -- '+url);
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
    
      try {
      	req.open("POST", url, true); //was get 
      } catch (e) {
        alert("Session Expired Please Relogin");
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
      // alert('process IE');
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processStateChange;        
        req.open("POST", url, true);
        req.send();
      }
    }     
    
  }
 
 function getFormAsStringRemoveOnHoldNew(formName) {
 	   ///alert(formName);
 	//Setup the return String
 	returnString ="";
  	//Get the form values
 	formElements=document.forms[formName].elements;
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 
 		if(formElements[i].name=='onHoldFlag' || formElements[i].name=='OnHoldChkFlag')
 		{ 		
 		if(formElements[i].checked){
 		 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value)+"&abcd="+Math.random();
 		} 
 		 }
 		 if(formElements[i].name=='month' || formElements[i].name=='year'
 		  || formElements[i].name=='employeeTypeId' || formElements[i].name=='payBillId' 
 		   || formElements[i].name=='branchId'  || formElements[i].name=='departmentId' 
 		    || formElements[i].name=='divisionId' || formElements[i].name=='vpfFlag'  || formElements[i].name=='ledgerCode' 
 		     || formElements[i].name=='recoveryFlag'  || formElements[i].name=='profHandiFLag'  || formElements[i].name=='incomeTaxFlag' 
 		      || formElements[i].name=='lwfFlag'  || formElements[i].name=='lwfDebitCode'  || formElements[i].name=='lwfCreditCode'
 		      || formElements[i].name=='creditRound'|| formElements[i].name=='totalCreditRound'|| formElements[i].name=='totalDebitRound'
 		      || formElements[i].name=='netPayRound'|| formElements[i].name=='dataFlag'|| formElements[i].name=='ledgerStatus'){
 		 returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 		 }
 	}
 	//return the values
 	return returnString; 
 }
 
  function getFormAsStringRemoveOnHold(formName) {
 	//   alert(formName);
 	//Setup the return String
 	returnString ="";
  	//Get the form values
 	formElements=document.forms[formName].elements;
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 
 		if(formElements[i].name=='onHoldFlag' || formElements[i].name=='OnHoldChkFlag')
 		{ 		
 		if(formElements[i].checked){
 		 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value)+"&abcd="+Math.random();
 		} 
 		 }
 		 if(formElements[i].name=='month' || formElements[i].name=='year'
 		  || formElements[i].name=='employeeTypeId' || formElements[i].name=='payBillId' 
 		   || formElements[i].name=='branchId'  || formElements[i].name=='departmentId' 
 		    || formElements[i].name=='divisionId' ){
 		 returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 		 }
 	}
 	//return the values
 	return returnString; 
 }
 



 
 
 
 
 