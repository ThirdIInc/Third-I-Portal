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
  
  
  
  function displayModuleStatus(url,nameOfFormToPost,moduleName) {
 
    //get the (form based) params to push up as part of the get request
    try {
    /*****
    At the time of inserting the same poll option Cache object does'nt refresh,
    so Math.random() function is used
      	****/
       // 	alert("url  before ---------------"+url);
    //	alert("nameOfFormToPost   "+nameOfFormToPost);
      	 
   // url=url+getFormNameAsString(nameOfFormToPost)+'&xde='+Math.random()+'&moduleName='+moduleName;
      	
      		 url=url+'moduleName='+moduleName;
      			
  //  alert("url after -----------------"+url);
      	
     } catch (e) {
	     alert("firse exception  "+e);
     }
    //Do the Ajax call
    if (window.XMLHttpRequest) { // Non-IE browsers
      req = new XMLHttpRequest();
      req.onreadystatechange = displayModuleStatusData;
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    } else if (window.ActiveXObject) { // IE
    
      req = new ActiveXObject("Microsoft.XMLHTTP");
      if (req) {
        req.onreadystatechange = displayModuleStatusData;        
        req.open("GET", url, true);
        req.send();
       
        
      }
    }     
    
  }
  
  
  
  
   
   function displayModuleStatusData() {  
         
         
 // alert('req.readyState   '+req.readyState); 
  	  if (req.readyState == 4) { // Complete
  	  	
   //	 alert('req.status   '+req.status); 
      if (req.status == 200) { // OK response   
      	 try{
	 //alert("req.responseText---"+req.responseText); 
			var splitedStr ="--Select--#";
			splitedStr+=req.responseText;
			
			//alert("req.splitedStr---"+splitedStr); 
			 
			 
			 var splitVal = splitedStr.split("#");
			// alert("splitVal.length ----------------"+splitVal.length);
			 
			 var list  = document.getElementById('paraFrm_status');
			 
		//alert("list.length  "+list.length);
			
				while(list.length>0)
			 	{
			 	list.remove(0);
			 	}
			 	
				//alert("list.length after "+list.length);
				 document.getElementById('paraFrm_status').length=splitVal.length;
				 for ( var i = 0; i < splitVal.length; i++ )
				 {
				//alert("splitedStr[i] 11--44---------"+splitVal[i]);
				 	
					//alert("document.getElementById('paraFrm_status')  "+document.getElementById('paraFrm_status'));
				 	 try{
				 	 document.getElementById('paraFrm_status').options[i].text=splitVal[i];
				 	  document.getElementById('paraFrm_status').options[i].value=splitVal[i];
				 	 }catch(e)
				 	 {
				 	 	e.description;
				 	 }
				 }
			 }	catch(e)
      			{
      			 //alert("result==========  "+e);
      			}
      			
	     
      } else {
        //alert("Problem with server response:\n " + req.statusText);
      }
    }
    parent.frames[2].name="main";
  }
	
	