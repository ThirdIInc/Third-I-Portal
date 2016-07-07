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
	var pullFrmId;
 
 
  function processLeaves() {  
         
         
     	//alert('req.readyState   '+req.readyState); 
  	  if (req.readyState == 4) { // Complete
  	  	
  	  	//alert('req.status   '+req.status); 
      if (req.status == 200) { // OK response      
	  // alert('response666666');   
	       	 // refreshPoll();
	       	 
	       	 try{
	       	 var ishalfPayLeave = document.getElementById('paraFrm_isHalfDayLeave').value;
	       	 
	       		var message = req.responseXML.getElementsByTagName("message")[0];
				var value= message.childNodes[0].nodeValue;
			//	alert('value  '+value);
				var prevBal=document.getElementById('adjustedPoolLevAmt'+pullFrmId).value;
				document.getElementById('adjustedPoolLevAmt'+pullFrmId).value=0;
				var poolLeaveBal = document.getElementById('poolLevBalance'+pullFrmId).value;
				
				if(ishalfPayLeave=='Y')
				{
				document.getElementById('poolLevBalance'+pullFrmId).value=eval(poolLeaveBal)-eval(prevBal*2);
				}
				else{
				document.getElementById('poolLevBalance'+pullFrmId).value=eval(poolLeaveBal)-eval(prevBal);
				}
				
				var availBal=document.getElementById('paraFrm_levOpeningBalance').value;
				document.getElementById('paraFrm_levOpeningBalance').value=eval(availBal)+eval(value);
								
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
 


 function pullLeaveFrom(url,nameOfFormToPost,pullFromId) {
 
    //get the (form based) params to push up as part of the get request
    try {
    /*****
    At the time of inserting the same poll option Cache object does'nt refresh,
    so Math.random() function is used
      	****/
      	//alert("pullFromId   "+pullFromId);
      //	alert("url   "+url);
      	//alert("nameOfFormToPost   "+nameOfFormToPost);
      	pullFrmId=pullFromId;
      	var leaveAdjust=document.getElementById('adjustedPoolLevAmt'+pullFromId).value;
      	var pullLevCode=document.getElementById('poolLevCode'+pullFromId).value;
      	
      	 	var pullLevCode=document.getElementById('poolLevCode'+pullFromId).value;
      	 	
      	 		var ishalfPayLeave = document.getElementById('paraFrm_isHalfDayLeave').value;
      	
      //	alert("pullLevCode   "+pullLevCode);
      //	alert("leaveAdjust   "+leaveAdjust);
      	url=url+getFormAsString(nameOfFormToPost)+'&xde='+Math.random()+'&pullLevCode='+pullLevCode+'&leaveAdjust='+leaveAdjust+'&ishalfPayLeave='+ishalfPayLeave;
      	
      	//alert(url);
     } catch (e) {
	     alert(e);
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = processLeaves;
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
    
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = processLeaves;        
        req.open("GET", url, true);
        req.send();
       
        
      }
    }     
    
  }
  



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
 		if( formElements[i].name=='leaveApplication.levCode' || formElements[i].name=='leaveApplication.empCode' )
 		{ 	
 		//alert('getting form value');	
 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value);
 		//alert(returnString);
 		}
 	}
 	//return the values
 	return returnString; 
 }
 
 



