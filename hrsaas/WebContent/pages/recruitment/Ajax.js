//global variables
var req;
var which;

//Month Attendance: Unlock Attendance 

function retrieveURL(url, formName)
{
	//alert("url----"+url);
	
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
 		
 			if(formElements[i].name == 'subject' || formElements[i].name == 'hidAjaxSubject'
 				|| formElements[i].name == 'subjectMap' || formElements[i].name == 'category'
 				|| formElements[i].name == 'categoryMap' || formElements[i].name == 'hiddenSubject')
 			{ 			
 				returnString = returnString + "&" + escape(formElements[i].name) + "=" + formElements[i].value;
 			}
 		}
	}
	catch(e)
	{
		alert(e.description);
	}
	
	return returnString+"&pr="+Math.random();
}

function processStateChangeUnLock() 
{
	//0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
	if(req.readyState == 4) // Complete
	{
		if (req.status == 200) // OK response
	    {	    	
	    	var res = req.responseText;//String version of data returned from the server
	    	getCategory(res);
	    	
		}
		parent.frames[2].name='main';
	}	
}


function getCategory(responseText)
{	
	try{
	var result=responseText;
	var result1=result.split("&");
	var catCode=result1[0];
	var catName=result1[1];
		
	var categoryCode=result1[0].split(",");
	//alert(catCode);
	//alert(catName);
	var categoryName=result1[1].split(",");
	
	document.getElementById('paraFrm_cCode').value=catCode;
	//alert(document.getElementById('paraFrm_cCode').value);
	
	for(i=0;i<document.getElementById('paraFrm_category').options.length;i++)
	{
		document.getElementById('paraFrm_category').options[i]=new Option(null,null);
	}
	document.getElementById('paraFrm_category').options.length=categoryCode.length;
	for(i=0;i<categoryCode.length;i++)
	{
		document.getElementById('paraFrm_category').options[i]=new Option(categoryName[i],categoryCode[i]);
	}		
	}catch(e)
	{}
}







//updated by Anantha lakshmi
//Updated by Nilesh on 7th Sept 2011--Add job code field.

 function addToMaster(url,JbDescName,JbDesc,RoleDesc,SpecialReq,PersoanlReq,jdCode) {
 	   try {
	    	  url=url+'&JbDescName='+JbDescName+'&JbDesc='+JbDesc+'&RoleDesc='+RoleDesc+'&SpecialReq='+SpecialReq+'&PersoanlReq='+PersoanlReq+'&jdCode='+jdCode;
	     } catch (e) {
		     alert("firse exception  "+e);
	     }
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
	    }//OUT IF CLOSE      
  }
  
  
  function processMasterData() {  
  	if (req.readyState == 4) { // Complete
	      if (req.status == 200) { // OK response      
		       	 try{
		       		var message = req.responseXML.getElementsByTagName('message')[0];
					var value= message.childNodes[0].nodeValue;
					 if(!(value=='duplicate')){
					  document.getElementById('paraFrm_jdDescName').value =value ;
			  	 	  alert("Record added successfully .");
					 }else{
							alert("Record already exists.");
					 }
	      		}catch(e){
	      		}
	      } else {
	        	alert("Problem with server response:\n " + req.statusText);
	      }
    }
    parent.frames[2].name="main";
  }
