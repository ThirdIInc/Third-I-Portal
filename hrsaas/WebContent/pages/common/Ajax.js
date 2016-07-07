/**
 * Ajax.js
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
var dashletId="";

var dashletIdEmpPortal="";

  /**
   * Get the contents of the URL via an Ajax call
   * url - to get content from (e.g. /struts-ajax/sampleajax.do?ask=COMMAND_NAME_1) 
   * nodeToOverWrite - when callback is made
   * nameOfFormToPost - which form values will be posted up to the server as part 
   *					of the request (can be null)
   */
  
   function retrieveURLContent(url,elementId) {
	  url='welcome.txt';
	  alert(url+elementId);
    //get the (form based) params to push up as part of the get request
    try {
      	//url=url+getFormAsString(nameOfFormToPost);
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
	  alert('hi in non-ie');
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange_element(elementId);
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
		alert('hi in ie');
      // alert('process IE');
		req = new ActiveXObject("Microsoft.XMLHTTP");
		alert('hi in ie  1');
      if (req) {
		try {			
			req.onreadystatechange = processStateChange_element(elementId);        			
			req.open("GET", url, true);			
			req.send();
		} catch (e) {
			alert("Problem Communicating with Server\n"+e);
		}
      }
    }     
    
  }

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
  
    function retrieveURLLeave(url,nameOfFormToPost) {
    //get the (form based) params to push up as part of the get request
    
   // alert('in retrieveURLLeave');
    try
    {
   //document.getElementById('addButton').disabled=true;
    
    var res =getData();
    if(res==false)
    {
    return false;
    }
    else
    {
    document.getElementById('addButton').value='  Processing ....';
    }
    }catch(e)
    {
    alert(e);
    }
    
    try {
      	url=url+getFormAsString(nameOfFormToPost);
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChangeLeave;
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
        req.onreadystatechange = processStateChangeLeave;        
        req.open("GET", url, true);
        req.send();
      }
    }     
    
  }
   
  function retrieveURL(url,nameOfFormToPost) {
    //get the (form based) params to push up as part of the get request
    try {
      	url=url+getFormAsString(nameOfFormToPost);
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
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
        req.onreadystatechange = processStateChange;        
        req.open("GET", url, true);
        req.send();
      }
    }     
    
  }

function retrieveURLOnHold(url,nameOfFormToPost) {
    //get the (form based) params to push up as part of the get request
   
    try {
      	url=url+getFormAsStringOnHold(nameOfFormToPost);
      	
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
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
        req.onreadystatechange = processStateChange;        
        req.open("GET", url, true);
        req.send();
      }
    }     
    
  }
/*
   * Set as the callback method for when XmlHttpRequest State Changes 
   * used by retrieveUrl
  */
  function processStateChangeLeave() {  
//  alert('in processStateChangeLeave');
  	  if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response    
    //  alert('in processStateChangeLeave');  
	       	nkumar= splitTextIntoSpan(req.responseText);
       		replaceExistingWithNewHtml(nkumar);    
       		    try
    {
    document.getElementById('addButton').disabled=false;
     document.getElementById('addButton').value='  Add';
     
     callAdd();
    }catch(e)
    {
    alert(e);
    }
      } else {
      //  alert("Problem with server response:\n " + req.statusText);
      }
    }
    parent.frames[2].name="main";
  }
 
 /**
  * gets the contents of the form as a URL encoded String
  * suitable for appending to a url
  * @param formName to encode
  * @return string with encoded form values , beings with &
  */ 
 function getFormAsString(formName) {
 	//   alert(formName);
 	//Setup the return String
 	returnString ="";
 	
  	//Get the form values
 	formElements=document.forms[formName].elements;
 	//alert(formElements);
 	
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 		
 		if( formElements[i].name=='leaveApplication.empCode' || formElements[i].name=='leaveApplication.levCode'
 		||formElements[i].name=='levOpeningBalance'||formElements[i].name=='levClosingBalance'
 		||formElements[i].name=='leaveFromDtl'||formElements[i].name=='leaveToDtl' || formElements[i].name=='leaveApplication.zeroBalance')
 		{ 		
 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 		}
 	}
 	//return the values
 	return returnString; 
 }
 
 
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
 		 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 		} 
 		 }
 		 if(formElements[i].name=='nonIndustrialSalary.month' || formElements[i].name=='nonIndustrialSalary.year'
 		  || formElements[i].name=='nonIndustrialSalary.typeCode' || formElements[i].name=='nonIndustrialSalary.payBillNo' 
 		  || formElements[i].name=='nonIndustrialSalary.valueObj'){
 		 returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
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
 
 function leaveExtensionURL(url,nameOfFormToPost) {
    //get the (form based) params to push up as part of the get request
    try {
      	url=url+getFormAsStringLeaveExtension(nameOfFormToPost);
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processStateChange;
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
        req.onreadystatechange = processStateChange;        
        req.open("GET", url, true);
        req.send();
      }
    }     
    
  }
 function getFormAsStringLeaveExtension(formName) {
 	//   alert(formName);
 	//Setup the return String
 	returnString ="";
 	
  	//Get the form values
 	formElements=document.forms[formName].elements;
 	//alert(formElements);
 	
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 		
 		if( formElements[i].name=='leave.empCode' || formElements[i].name=='leave.levCode'
 		||formElements[i].name=='levOpeningBalance'||formElements[i].name=='levClosingBalance'
 		||formElements[i].name=='leaveFromDtl'||formElements[i].name=='leaveToDtl' || formElements[i].name=='leave.zeroBalance')
 		{ 		
 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 		}
 	}
 	//return the values
 	return returnString; 
 }
 
 
  function processPoll() {  
         
         
     
  	  if (req.readyState == 4) { // Complete
  	  	
      if (req.status == 200) { // OK response      
	   // alert('response666666');   
	       	 // refreshPoll();
	       	 
	       	   var message = req.responseXML.getElementsByTagName("message")[0];
				var value= message.childNodes[0].nodeValue;
				
	       if(value=='expiry'){
	       alert('Poll has Expired and Cannot be Submitted');
	       }
	       else if(value=='valid'){
	       alert('Poll submitted successfully');
	       }
	       else{
	       alert('Poll already submitted');
	       }
	       	reloadRSSData(dashletId);
      } else {
        alert("Problem with server response:\n " + req.statusText);
      }
    }
    parent.frames[2].name="main";
  }
 
 
 

  

 function retrievePoll(url,nameOfFormToPost,dashId) {
 dashletId = dashId;
    //get the (form based) params to push up as part of the get request
    try {
    /*****
    At the time of inserting the same poll option Cache object does'nt refresh,
    so Math.random() function is used
      	****/
      	
      	url=url+getPollFormAsString(nameOfFormToPost)+'&xde='+Math.random();
      	
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processPoll;
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
    
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processPoll;        
        req.open("GET", url, true);
        req.send();
       
        
      }
    }     
    
  }
  



function getPollFormAsString(formName) {
 	  // alert(formName);
 	//Setup the return String
 	returnString ="";
 	
  	//Get the form values
 	formElements=document.forms[formName].elements;
 	//alert(formElements);
 	
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 		
 		if( formElements[i].name=='optionValue' || formElements[i].name=='pollCode')
 		{ 	
 		//alert('getting form value');	
 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 		//alert(returnString);
 		}
 	}
 	//return the values
 	return returnString; 
 }
 
 
  function saveDefaultTab(url)
   {
    	//Do the Ajax call
	    if (window.XMLHttpRequest){ // Non-IE browsers
		     req = new XMLHttpRequest();
		     try 
		     {
		       	req.open("GET", url, true); //was get 
		     }catch (e){
		        alert("Problem communicating with server \n"+e);
		     }
		     req.send(null);
	    } 
	    else if (window.ActiveXObject){ // IE
		     req = new ActiveXObject("Microsoft.XMLHTTP");
		     if (req){
		       	req.open("GET", url, true);
		        req.send();
		     }
	    } 
	}
	
	
	checkGlodyneLoginPassword	

function checkPassword(url,nameOfFormToPost) {
	 
	    //get the (form based) params to push up as part of the get request
	    try {
	    /*****
	    At the time of inserting the same poll option Cache object does'nt refresh,
	    so Math.random() function is used
	      	****/
	      	//alert('checkPassword');
	      	url=url+getReloginFormAsString(nameOfFormToPost)+'&xde='+Math.random();
	      	
	     } catch (e) {
		     alert('bean problem');
	     }
	    //Do the Ajax call
	    if (window.XMLHttpRequest) { // Non-IE browsers
	      req = new XMLHttpRequest();
	      req.onreadystatechange = processReLogin;
	      try {
	      	req.open("GET", url, true); //was get 
	      } catch (e) {
	        alert("Problem Communicating with Server\n"+e);
	      }
	      req.send(null);
	    } else if (window.ActiveXObject) { // IE
	    
	      req = new ActiveXObject("Microsoft.XMLHTTP");
	      if (req) {
	        req.onreadystatechange = processReLogin;        
	        req.open("GET", url, true);
	        req.send();
	       
	        
	      }
	    }     
	    
	  }
	 function getReloginFormAsString(formName) {
	 	//   alert(formName);
	 	//Setup the return String
	 	returnString ="";
	 	
	  	//Get the form values
	 	formElements=document.forms[formName].elements;
	 	//alert(formElements);
	 	
	 	//loop through the array , building up the url
	 	//in the form /strutsaction.do&name=value
	 	
	 	for ( var i=formElements.length-1; i>=0; --i ){
	 		//we escape (encode) each value 		
	 		if( formElements[i].name=='loginName' || formElements[i].name=='loginPassword')
	 		{ 	
	 		//alert('getting form value');	
	 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
	 		//alert(returnString);
	 		}
	 	}
	 	//return the values
	 	return returnString; 
	 }
	 
	 function processReLogin() {       
  	  if (req.readyState == 4) { // Complete  	  	
      if (req.status == 200) { // OK response       
	       	 // refreshPoll();	       	 
	       var message = req.responseXML.getElementsByTagName("message")[0];
		   var value= message.childNodes[0].nodeValue;				
	       if(value=='success'){
	       	//alert('parent.frames[0]  ' +parent.frames[0]);
	       	//parent.frames[0].ShowTimeoutWarning();
	       	//parent.frames[0].setInterval('callTimer();',1000);
	       	//parent.frames[0].document.getElementById("timerDiv1").innerHTML=59;
	       	//alert(parent.frames[1].location);
	       	//parent.frames[0].ShowTimeoutWarning();
	       	//alert(parent.frames[1].location);
    		try{
    			parent.frames[1].location=parent.frames[1].location;
    		}catch(e){
    		 //alert(e);
    		}
    	 try{
    	 	try{
				parent.main.clearInterval();
			}catch(e){
				//alert(e);
			}     
	       	document.getElementById ('MainDiv').style.display='none';
    		document.getElementById ('MainDiv').style.visibility='hidden';
		    document.getElementById ('MainDiv').style.top='0px';
		    document.getElementById ('MainDiv').style.left='0px';
		    document.getElementById ('MainDiv').style.width= '0px';
		    document.getElementById ('MainDiv').style.height= '0px';
		    document.getElementById('optionDiv').style.display='none';
	       }catch(e){	       
	       alert(e);
	       }
	      	
	       }
	       else if(value=='invalid'){
	       	alert('Invalid Password');
	       
	       }
      } else {
        alert("Problem with server response:\n " + req.statusText);
      }
    }
    
  }
  
  
  /***          Get Last Access Time   ***/
  		 function getLastAccess(url,nameOfFormToPost) {
		 alert('getLastAccess');
	    //get the (form based) params to push up as part of the get request
	    try {
	    /*****
	    At the time of inserting the same poll option Cache object does'nt refresh,
	    so Math.random() function is used
	      	****/
	      	//alert('checkPassword');
	      	url=url+'?xde='+Math.random();
	      	
	     } catch (e) {
		     alert('bean problem');
	     }
	    //Do the Ajax call
	    if (window.XMLHttpRequest) { // Non-IE browsers
	      req = new XMLHttpRequest();
	      req.onreadystatechange = processLastAccessTime;
	      try {
	      	req.open("GET", url, true); //was get 
	      } catch (e) {
	        alert("Problem Communicating with Server\n"+e);
	      }
	      req.send(null);
	    } else if (window.ActiveXObject) { // IE
	    
	      req = new ActiveXObject("Microsoft.XMLHTTP");
	      if (req) {
	        req.onreadystatechange = processLastAccessTime;        
	        req.open("GET", url, true);
	        req.send();
	       
	        
	      }
	    }     
	    
	  }
	
	 
	 function processLastAccessTime() {  
         
         
     
  	  if (req.readyState == 4) { // Complete
  	  	
      if (req.status == 200) { // OK response      
	   // alert('response666666');   
	       	 // refreshPoll();
	       	 
	       	   var message = req.responseXML.getElementsByTagName("message")[0];
				var value= message.childNodes[0].nodeValue;
				//alert('difference  : '+value);
				alert('before diff  :'+parent.frames[1].document.getElementById('lastAcessDiff').innerHTML);
				document.getElementById('lastAcessDiff').value=value;
				alert('after diff  :'+parent.frames[1].document.getElementById('lastAcessDiff').innerHTML);
	       
      } else {
        alert("Problem with server response:\n " + req.statusText);
      }
    }
    
  }

//new method for travel policy


  //Added By VISHWAMBHAR for EMployee Portal
  
  
    function retrieveCeoConnect(url,nameOfFormToPost)
    {
    	 //get the (form based) params to push up as part of the get request
    try {
    /*****
    At the time of inserting the same poll option Cache object does'nt refresh,
    so Math.random() function is used
      	****/
      	// alert(url);
      // alert(nameOfFormToPost);
      	
      	
      	url=url+getCeoConnectFormAsString(nameOfFormToPost)+'&xde='+Math.random();
      	
     } catch (e) {
	     alert('bean problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processCeoConnect;
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
    
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processCeoConnect;        
        req.open("GET", url, true);
        req.send();
       
        
      }
    }     
  
  	}
  	
  	
  	function processCeoConnect() {  
    
  	  if (req.readyState == 4) { // Complete
  	  	
      if (req.status == 200) { // OK response      
	  // alert('response666666');   
	       	 // refreshPoll();
	          	 try{
	     
	       		var message = req.responseXML.getElementsByTagName('ceoresult')[0];
	       		
	       		// alert('message after  '+message);
	       			 
				var value= message.childNodes[0].nodeValue;
				
				// alert("value==========  "+value);
				 
				 if(value=='saved')
				 {
				 	 alert("Thank you for your message");
				 	 document.getElementById('div_Id').style.display = '';
				 	document.getElementById('subject').value='';
		
				document.getElementById('description').value='';
	 	
				document.getElementById('hideIdentity').checked =false;
				 }
				 
				else{
						alert("Data not submited");
				}
		 		}
      			catch(e)
      			{
      				// alert("result==========  "+e);
      			}
	       
      } else {
        alert("Problem with server response:\n " + req.statusText);
      }
    }
    parent.frames[2].name="main";
  }
  
  
  function getCeoConnectFormAsString(formName) {
 	  //alert('formName'+formName);
 	//Setup the return String
 	returnString ="";
 	
  	//Get the form values
 	formElements=document.forms[formName].elements;
   
 	
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 		
 		if( formElements[i].name=='subject' || formElements[i].name=='description')
 		{ 	
 		// alert('getting form value');	
 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 	//  alert("returnString   "+returnString);
 		}
 	}
 	//return the values
 	return returnString; 
 }
 
 
 
 
  
   
  function retrievePollForEmployeePortal(url,nameOfFormToPost) {
  
 // alert("url----------------"+url);
 // alert("nameOfFormToPost----nameOfFormToPost-----------"+nameOfFormToPost);
    //get the (form based) params to push up as part of the get request
    try {
    /*****
    At the time of inserting the same poll option Cache object does'nt refresh,
    so Math.random() function is used
      	****/
      	try{
      	url=url+getPollFormAsStringForEmployeePortal(nameOfFormToPost)+'&xde='+Math.random();
      	
      //	alert("url------after----------"+url);
      	
      	}catch(e){
      	alert("Error   "+e);
      	}
      	
      	
     } catch (e) {
	     alert('bean 999999999999 problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processPollEmployeePortal;
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
    
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processPollEmployeePortal;        
        req.open("GET", url, true);
        req.send();
       
        
      }
    }     
    
  }
  
  
  
function getPollFormAsStringForEmployeePortal(formName) {
  
  try{
   //alert(formName);
 	//Setup the return String
 	returnString ="";
 	
  	//Get the form values
 	formElements=document.forms[formName].elements;
  //alert(formElements);
 	
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 		
 		if( formElements[i].name=='optionValue' || formElements[i].name=='pollCode')
 		{ 	
 		// alert('getting form value');	
 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 	 //alert(returnString);
 		}
 	}
 	//return the values
 	
 	 //alert("returnString  "+returnString);
 	 
 	 
 	return returnString; 
 	
 	
 	
 	}catch(e){
 	//alert("Error 3333333333"+e);
 	}
 	
 }
 
 
 
 function processPollEmployeePortal() {  
 
         
         
     
  	  if (req.readyState == 4) { // Complete
  	  	
      if (req.status == 200) { // OK response      
	  // alert('response666666');   
	       	 // refreshPoll();
	       	 
	       	   var message = req.responseXML.getElementsByTagName("message")[0];
				var value= message.childNodes[0].nodeValue;
				
	       if(value=='expiry'){
	       alert('Poll has Expired and Cannot be Submitted');
	       }
	       else if(value=='valid'){
	       alert('Poll submitted successfully');
	       }
	       else{
	       alert('Poll already submitted');
	       }
	       
      } else {
        alert("Problem with server response:\n " + req.statusText);
      }
    }
    parent.frames[2].name="main";
  }
 
  
//Added by vishwambhar for glodyne relogin check password starts
function checkGlodyneLoginPassword(url,nameOfFormToPost) {
	 
	    //get the (form based) params to push up as part of the get request
	    try {
	    /*****
	    At the time of inserting the same poll option Cache object does'nt refresh,
	    so Math.random() function is used
	      	****/
	      	//alert('checkPassword');
	      	url=url+getGlodyneReloginFormAsString(nameOfFormToPost)+'&xde='+Math.random();
	      	
	     } catch (e) {
		     alert('bean problem');
	     }
	    //Do the Ajax call
	    if (window.XMLHttpRequest) { // Non-IE browsers
	      req = new XMLHttpRequest();
	      req.onreadystatechange = processGlodyneReLogin;
	      try {
	      	req.open("GET", url, true); //was get 
	      } catch (e) {
	        alert("Problem Communicating with Server\n"+e);
	      }
	      req.send(null);
	    } else if (window.ActiveXObject) { // IE
	    
	      req = new ActiveXObject("Microsoft.XMLHTTP");
	      if (req) {
	        req.onreadystatechange = processGlodyneReLogin;        
	        req.open("GET", url, true);
	        req.send();
	       
	        
	      }
	    }     
	    
	  }
	  
	  
	  
	  	 function getGlodyneReloginFormAsString(formName) {
	 	//   alert(formName);
	 	//Setup the return String
	 	returnString ="";
	 	
	  	//Get the form values
	 	formElements=document.forms[formName].elements;
	 	//alert(formElements);
	 	
	 	//loop through the array , building up the url
	 	//in the form /strutsaction.do&name=value
	 	
	 	for ( var i=formElements.length-1; i>=0; --i ){
	 		//we escape (encode) each value 		
	 		if( formElements[i].name=='loginName' || formElements[i].name=='loginPassword')
	 		{ 	
	 		//alert('getting form value');	
	 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
	 		//alert(returnString);
	 		}
	 	}
	 	//return the values
	 	return returnString; 
	 }
	 
	 
	 
	 
	 
	function processGlodyneReLogin() {       
  	  if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response      
	       	 // refreshPoll();
	  var message = req.responseXML.getElementsByTagName("message")[0];
	  var value= message.childNodes[0].nodeValue;
	  if(value=='success'){
	       	//alert('parent.frames[0]  ' +parent.frames[0]);
	       	//parent.frames[0].ShowTimeoutWarning();
	       	//parent.frames[0].setInterval('callTimer();',1000);
	       	//parent.frames[0].document.getElementById("timerDiv1").innerHTML=59;
	       	//alert(parent.frames[1].location);
	       	//parent.frames[0].ShowTimeoutWarning();
	       	//alert(parent.frames[1].location);
    		parent.frames[1].location=parent.frames[1].location;
    		//alert("Success");
    	 try{
    	 	try{
				parent.main.clearInterval();
			}catch(e){
			}	       
	       	document.getElementById ('MainDiv').style.display='none';
    		document.getElementById ('MainDiv').style.visibility='hidden';
		    document.getElementById ('MainDiv').style.top='0px';
		    document.getElementById ('MainDiv').style.left='0px';
		    document.getElementById ('MainDiv').style.width= '0px';
		    document.getElementById ('MainDiv').style.height= '0px';
		    document.getElementById('optionDiv').style.display='none';
	       
	       }catch(e){
	       alert(e);
	       }
	      	
	       }
	       else if(value=='invalid'){
	       	alert('Invalid Password');
	       
	       }
      } else {
        alert("Problem with server response:\n " + req.statusText);
      }
    }
    
  }
  
	 
	 
	 
//Added by vishwambhar for glodyne relogin check password ends


 function processPollRightPage() {  
         
         
     
  	  if (req.readyState == 4) { // Complete
  	  	
      if (req.status == 200) { // OK response      
	   // alert('response666666');   
	       	 // refreshPoll();
	       	 
	       	   var message = req.responseXML.getElementsByTagName("message")[0];
				var value= message.childNodes[0].nodeValue;
				
	       if(value=='expiry'){
	       alert('Poll has Expired and Cannot be Submitted');
	       }
	       else if(value=='valid'){
	       alert('Poll submitted successfully');
	       }
	       else{
	       alert('Poll already submitted');
	       }
	       	reloadRSSData(dashletId);
      } else {
        alert("Problem with server response:\n " + req.statusText);
      }
    }
    parent.frames[2].name="main";
  }
 
 
 
  
function getPollFormAsStringForRightPage(formName) {
  
  try{
   //alert(formName);
 	//Setup the return String
 	returnString ="";
 	
  	//Get the form values
 	formElements=document.forms[formName].elements;
  //alert(formElements);
 	
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 		
 		if( formElements[i].name=='optionValue' || formElements[i].name=='pollCode')
 		{ 	
 		// alert('getting form value');	
 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 	 //alert(returnString);
 		}
 	}
 	//return the values
 	
 	// alert("returnString  "+returnString);
 	 
 	 
 	return returnString; 
 	
 	
 	
 	}catch(e){alert("Error 3333333333"+e);}
 	
 }
 

  function retrievePollForRightPage(url,nameOfFormToPost) {
  
    //get the (form based) params to push up as part of the get request
    try {
    /*****
    At the time of inserting the same poll option Cache object does'nt refresh,
    so Math.random() function is used
      	****/
      	try{
      	url=url+getPollFormAsStringForRightPage(nameOfFormToPost)+'&xde='+Math.random();
       	}catch(e){
      	alert("Error   "+e);
      	}

     } catch (e) {
	    // alert('bean 999999999999 problem');
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processPollEmployeePortal;
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
    
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processPollRightPage;        
        req.open("GET", url, true);
        req.send();
       
        
      }
    }     
    
  }