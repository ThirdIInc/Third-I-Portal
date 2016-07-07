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
	       	reloadRSSData(5);
      } else {
        alert("Problem with server response:\n " + req.statusText);
      }
    }
    parent.frames[2].name="main";
  }
 


 function retrievePoll(url,nameOfFormToPost) {
 
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
	
	
		
	
function TravelCheckDupPolicy(url)
{
    //Do the Ajax call
  //alert("aIN");
    if (window.XMLHttpRequest){ // Non-IE browsers
	     req = new XMLHttpRequest();
	     req.onreadystatechange = processChangeTravelPolicy;
	     try 
	     {
	       	req.open("POST", url, true); //was get 
	     }catch (e){
	        alert("Problem Communicating with Server\n"+e);
	     }
	     req.send(null);
    } 
    else if (window.ActiveXObject){ // IE
	     req = new ActiveXObject("Microsoft.XMLHTTP");
	     if (req){
	     	req.onreadystatechange = processChangeTravelPolicy;
	       	req.open("POST", url, true);
	        req.send();
	     }
    }     
}

function processChangeTravelPolicy()
{
	try {
	
  	 if (req.readyState == 4) 
  	 { // Complete
  	// alert('y');
//alert('req.status'+req.status);
      	if (req.status == 200)
      	{ // OK response 
//alert('req.status111'+req.responseText);
      var rowCount= document.getElementById('rowCount').value;
      var flag=false;
	   			//alert("In save");
	   			//var gradeid=document.getElementById('empGradeId').value;
	   			
	     		for(i=0;i<rowCount;i++)
	     		{
   					if(document.getElementById('hidGradeStatus'+(eval(i)+1)).value=='Y')
   					{
   						flag=true;
   						break;
   					}
   					//alert('for....'+flag);
    	 		}
    	   // alert(flag);
		    	 if(!flag)
		    	 {
		    	 	alert("Please select grade(s) to save the policy");
		    	 	return false;
		    	 }
		   
      		if(trim(req.responseText) == "")
      		{
      			document.getElementById('paraFrm').action = document.getElementById('actionName').value+'overWrite=N';
				document.getElementById('paraFrm').submit();
      		}
      		else
      		{
      		//alert('x');
      			var val = req.responseText; 
      			val = val.substr(0,val.length-1);
      			document.getElementById('existGrades').value=val;
      			//alert(val);
      			var overWrite = "";
    			if(document.getElementById('existGrades').value != '')
    			{
    			//alert('in if');
    				var existGrd=document.getElementById('existGrades').value;
    				//alert('existGrd'+existGrd);
	    			for(i=0;i<rowCount;i++)
    				{
    				//alert('in for');
	    				try {
	    					var exGrdArr = existGrd.split(',');
	    					//alert("in if : "+exGrdArr);
	    					for(j=0;j<exGrdArr.length;j++)
	    					{
	    						if(exGrdArr[j]==document.getElementById('empGrade'+(eval(i)+1)).value 
	    							&& document.getElementById('gradeStatus'+(eval(i)+1)).checked)
	    						{
	    							overWrite+=document.getElementById('empGrade'+(eval(i)+1)).value+",";
	    							break;
	    						}
	    					} 
	    					//alert('overWrite11'+overWrite);
	    				}catch(e)
	    				{
	    					alert(e);
	    				}	
    				}	
    			}
		    	try {
		    	if(overWrite.length > 0)
		    		overWrite=overWrite.substr(0,overWrite.length-1);
		    	  //alert('overWrite'+overWrite);
		    	}catch(e)
		    	{
		    	}
		    	document.getElementById('existGrades').value=overWrite;
		    	afterGetResponse();
				}
		    }
		    else 
		    {
		        alert("Problem with server response:\n "+req.status);
		     }
		    }
		 }catch(e)
		 {
		    alert(e);
		  }
		     
}

function travelMonitor_isPolicyViolate(url)
{
	if (window.XMLHttpRequest){ // Non-IE browsers
	     req = new XMLHttpRequest();
	     req.onreadystatechange = policyViolateTravel;
	     try 
	     {
	       	req.open("POST", url, true); //was get 
	     }catch (e){
	        alert("Problem Communicating with Server\n"+e);
	     }
	     req.send(null);
    } 
    else if (window.ActiveXObject){ // IE
	     req = new ActiveXObject("Microsoft.XMLHTTP");
	     if (req){
	     	req.onreadystatechange = policyViolateTravel;
	       	req.open("POST", url, true);
	        req.send();
	     }
    }     
}

function policyViolateTravel()
{
	if (req.readyState == 4) 
  	 { // Complete
      	if (req.status == 200)
      	{ // OK response
      		var status = trim(req.responseText);
      		if(status!='')
      		{
      			var con = '';
      			try {
	      			var msg  = document.getElementById('mode_1_1_1').value;
	      			con = confirm('You are about to violate the policy. '+status);
      			}
      			catch(e)
      			{
      				con = confirm('You are about to violate the policy. '+status);
      			}
      			
      			if(con)
      				document.getElementById('violateFlag').value='Y';
      			else
      				document.getElementById('violateFlag').value='N';
      		}
      		afterAjax();
      	 }
      }
}

//new method for travel policy

// new methods added by sai


function TravelCheckDupPolicyNew(url)
{
    //Do the Ajax call
//alert("aIN");
    if (window.XMLHttpRequest){ // Non-IE browsers
	     req = new XMLHttpRequest();
	     req.onreadystatechange = processChangeTravelPolicyNew;
	     try 
	     {
	       	req.open("POST", url, true); //was get 
	     }catch (e){
	        alert("Problem Communicating with Server\n"+e);
	     }
	     req.send(null);
    } 
    else if (window.ActiveXObject){ // IE
	     req = new ActiveXObject("Microsoft.XMLHTTP");
	     if (req){
	     	req.onreadystatechange = processChangeTravelPolicy;
	       	req.open("POST", url, true);
	        req.send();
	     }
    }     
}



function processChangeTravelPolicyNew()
{
	try {
  	 if (req.readyState == 4) 
  	 { // Complete
      	if (req.status == 200)
      	{ // OK response 
      			var rowCount= document.getElementById('rowCount').value;
     			 var flag=false;
	   			if(rowCount>1)
	   			{
		     		for(i=0;i<rowCount;i++)
		     		{
		  					if(document.getElementById('hidGradeStatus'+(eval(i)+1)).value=='Y')
		  					{
		  						flag=true;
		  						break;
		  					}
		   	 		}
			    	 if(!flag)
			    	 {
			    	 	alert("Please select grade(s) to save the policy");
			    	 	return false;
			    	 }
		    	}
	     		if(trim(req.responseText) == "")
	     		{
	     			document.getElementById('paraFrm').action = document.getElementById('actionName').value+'overWrite=N';
				 	document.getElementById('paraFrm').submit();
	     		}
	      		else
	      		{
	      			var val = trim(req.responseText); 
	      			val = val.substr(0,val.length-1);
	      			document.getElementById('existGrades').value=val;
			    	if(val != "")
			    	{
			    			afterCallingAjax(); 
			    	}
				}
		   	}
		    else 
		    {
		        alert("Problem with server response:\n "+req.status);
		    }
		   }
		 }catch(e)
		 {
		    alert(e);
		 }
	}
	
	
	
	
	/*
	ADDED BY VISHWAMBHAR
	*/	
	
	function addCustomerToMaster(url,nameOfFormToPost,otherCustomer,applicationCode) {
 
    //get the (form based) params to push up as part of the get request
    try {
    /*****
    At the time of inserting the same poll option Cache object does'nt refresh,
    so Math.random() function is used
      	****/
      	// alert("otherCustomer   "+otherCustomer);
       // alert("applicationCode   "+applicationCode);
       //	alert("url   "+url);
    //  	alert("nameOfFormToPost   "+nameOfFormToPost);
      	 
     // url=url+getFormNameAsString(nameOfFormToPost)+'&xde='+Math.random()+'&otherProject='+otherProject;
      	
      		 url=url+'otherCustomer='+otherCustomer+'&applicationCode='+applicationCode;
      			
      		//	alert("url after "+url);
      	
     } catch (e) {
	     alert("firse exception  "+e);
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processCustomerMasterData;
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
    
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processCustomerMasterData;        
        req.open("GET", url, true);
        req.send();
       
        
      }
    }     
    
  }
  
  
   function processCustomerMasterData() {  
         
         
   // alert('req.readyState   '+req.readyState); 
  	  if (req.readyState == 4) { // Complete
  	  	
  	 //	 alert('req.status   '+req.status); 
      if (req.status == 200) { // OK response      
	   //alert('response666666');   
	       	  // refreshPoll();
	       	 
	       	 try{
	     
	       		var message = req.responseXML.getElementsByTagName('customermessage')[0];
	       		
	       			//  alert('message after  '+message);
	       			 
				var value= message.childNodes[0].nodeValue;
				
			// alert("value==========  "+value);
				 
				 if(!(value=='duplicate'))
				 {
			
				  document.getElementById('paraFrm_travelCustomer').value =value ;
		  	 document.getElementById('paraFrm_travelOtherCustomer').value ='';
		  	 	 alert("Record added successfully .");
				 }
				 
				else{
						alert("Record already exists.");
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
	
	
	
	
	 function addProjectToMaster(url,nameOfFormToPost,otherProject,applicationCode) {
 
    //get the (form based) params to push up as part of the get request
    try {
    /*****
    At the time of inserting the same poll option Cache object does'nt refresh,
    so Math.random() function is used
      	****/
      //	 alert("otherProject   "+otherProject);
      	 //	 alert("applicationCode   "+applicationCode);
       //	alert("url   "+url);
    //  	alert("nameOfFormToPost   "+nameOfFormToPost);
      	 
     // url=url+getFormNameAsString(nameOfFormToPost)+'&xde='+Math.random()+'&otherProject='+otherProject;
      	
      		 url=url+'otherProject='+otherProject+'&applicationCode='+applicationCode;
      			
      		//	alert("url after "+url);
      	
     } catch (e) {
	     alert("firse exception  "+e);
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processMasterData;
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
    
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processMasterData;        
        req.open("GET", url, true);
        req.send();
       
        
      }
    }     
    
  }
	 function checkForAdvanceAllow(url) {
  
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processAdvanceData;
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
    
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processAdvanceData;        
        req.open("GET", url, true);
        req.send();
       
        
      }
    }     
    
  }
  
  function processAdvanceData() {  
   	  if (req.readyState == 4) { // Complete
 
      if (req.status == 200) { // OK response      
	      
	       	 try{
	       		var message = req.responseXML.getElementsByTagName('message')[0];
				var value= message.childNodes[0].nodeValue;
				if(value=='allow'){
					document.getElementById('paraFrm').target = "_self";
	 				document.getElementById('paraFrm').action = 'TravelApplication_saveApplication.action';
					document.getElementById('paraFrm').submit();
				}
				else{
				alert(value);
				return false;
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
   
  }



  function processMasterData() {  
         
         
     // alert('req.readyState   '+req.readyState); 
  	  if (req.readyState == 4) { // Complete
  	  	
  	  //	 alert('req.status   '+req.status); 
      if (req.status == 200) { // OK response      
	 //   alert('response666666');   
	       	  // refreshPoll();
	       	 
	       	 try{
	  //   alert('message   '+message);
	       		var message = req.responseXML.getElementsByTagName('message')[0];
	       		
	       			 // alert('message after  '+message);
	       			 
				var value= message.childNodes[0].nodeValue;
				
			//	 alert("value==========  "+value);
				 
				 if(!(value=='duplicate'))
				 {
			
				  document.getElementById('paraFrm_travelProject').value =value ;
		  	 document.getElementById('paraFrm_travelOtherProject').value ='';
		  	 	 alert("Record added successfully .");
				 }
				 
				else{
						alert("Record already exists.");
				}
		 
		  	
				 	
      			}
      			catch(e)
      			{
      					 //alert("result==========  "+e);
      			}
	     
      } else {
        alert("Problem with server response:\n " + req.statusText);
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
 function getFormNameAsString(formName) {
 
 	try{
 	 alert("formName =============================    "+formName);
 	//Setup the return String
 	returnString ="";
 	
  	//Get the form values
 	formElements=document.forms[formName].elements;
 	 alert("formElements -------------          "+formElements);
 	
 	//loop through the array , building up the url
 	//in the form /strutsaction.do&name=value
 	
 	for ( var i=formElements.length-1; i>=0; --i ){
 		//we escape (encode) each value 		
 		if( formElements[i].name=='hiddenApplicationCode')
 		{ 		
 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 		}
 	}
 	
 		 alert("returnString           "+returnString);
 	//return the values
 	}catch(e){ alert("Exception in getFormNameAsString    "+e);}
 	  
 	return returnString; 
 }
 
	
