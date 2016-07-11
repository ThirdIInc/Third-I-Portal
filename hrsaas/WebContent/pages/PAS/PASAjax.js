function finalizeEmployees(url,nameOfFormToPost)
   {
    	//Do the Ajax call
    	 try {
      		url=url+getFormAsStringLock(nameOfFormToPost);
     	}catch (e) {
	     	alert('bean problem');
    	 }
	    if (window.XMLHttpRequest){ // Non-IE browsers
		     req = new XMLHttpRequest();
		     req.onreadystatechange = processStatusChange;
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
		      	req.onreadystatechange = processStatusChange;
		       	req.open("GET", url, true);
		        req.send();
		     }
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
		 		if(formElements[i].name=='chkEmp')
		 		{ 		
		 			if(formElements[i].checked && !formElements[i].disabled)
		 			{
		 		 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value)+"&abcd="+Math.random();
		 			} 
		 		}
	 		}
		}
		catch(e)
		{
			alert(e.description);
		}
	return returnString;
}
function processStatusChange()
{
  	 if (req.readyState == 4) 
  	 { // Complete
      	if (req.status == 200)
      	{ // OK response 
        } 
        else 
        {
        	alert("Problem with server response:\n " + req.statusText);
      	}
     }
}
function recalculateEmployees(url,nameOfFormToPost)
   {
    	//Do the Ajax call
    	 try {
      		url=url+getFormAsStringRecalculate(nameOfFormToPost);
     	}catch (e) {
	     	alert('bean problem');
    	 }
	    if (window.XMLHttpRequest){ // Non-IE browsers
		     req = new XMLHttpRequest();
		     req.onreadystatechange = processStatusChangeRecal;
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
		      	req.onreadystatechange = processStatusChangeRecal;
		       	req.open("GET", url, true);
		        req.send();
		     }
	    } 
	}
	
	function getFormAsStringRecalculate(formName)
	{
	try
		{
			var returnString = "";
			var formElements = document.forms[formName].elements;
			for (var i = formElements.length - 1; i >= 0; i--)
	 		{
		 		if(formElements[i].name=='chkEmp')
		 		{ 		
		 			if(formElements[i].checked && !formElements[i].disabled)
		 			{
		 		 		returnString=returnString+"&"+escape(formElements[i].name)+"="+escape(formElements[i].value)+"&abcd="+Math.random();
		 			} 
		 		}
	 		}
		}
		catch(e)
		{
			alert(e.description);
		}
	return returnString;
}
function processStatusChangeRecal()
{
  	 if (req.readyState == 4) 
  	 { // Complete
      	if (req.status == 200)
      	{ // OK response 
      		setValues(req.responseText);
        } 
        else 
        {
        	alert("Problem with server response:\n " + req.statusText);
      	}
     }
}
function setValues(responseText)
  {
  var resEmpSplit = responseText.split("@");
  returnString ="";
  if(resEmpSplit.length>1){
 	 for(var j=0; j<resEmpSplit.length;j++){
 		setFormValues(resEmpSplit[j]);
	  }
  }else{
  		setFormValues(responseText);
	}
  }
function setFormValues(responseText)
  {
 	try{
 		var resStrSplit = responseText.split("#"); 		
 		var empid =  resStrSplit[0];  			  		
  		var id =  resStrSplit[5];
  		
  		document.getElementById("apprScore"+id).value=resStrSplit[1];
  		document.getElementById("apprFinalScore"+id).value=resStrSplit[1];
  		document.getElementById("adjustFactor"+id).value="0";
  		//document.getElementById("adjustFactor"+id).value=resStrSplit[3];
	}catch(e){}		
  }



//--------------------|ScoreBalancer Javascript for test|--------------------//
var fieldName  = ["paraFrm_apprCode"];
var lableName  = ["Appraisal Code"];
var flag	   = ["select"];
pgshow();

function searchFun(){
	document.getElementById("paraFrm_saveFlag").value='true';
	callsF9(500,325,'TemplateDefination_search.action');
}

function validateF9(action){
	alert("validateF9");
	
	if(document.getElementById("paraFrm_apprCode").value=="") {
		alert("Please select "+document.getElementById("appraisal.code").innerHTML.toLowerCase());
		document.getElementById("paraFrm_apprCode").focus();
		return false;
	}
			
	callsF9(500,325,'ScoreBalancer_'+action+'.action'); 
}

function saveFun(){
		if(document.getElementById("paraFrm_apprCode").value==""){
			alert("Please select "+document.getElementById("appraisal.code").innerHTML)	;
			return false;	
		}
		var tableLength = document.getElementById("count").value;
		if(tableLength == 0 )
		{
			alert("No employees for this appraisal.");
			return false;
		}
		if(!chkAllFinalize())
   		{
   			alert("Appraisal scores for all the employees have been already finalised");
   			return false;
   		}
		document.getElementById("paraFrm").action="ScoreBalancer_save.action";
		document.getElementById("paraFrm").submit();
}
function callFinal(id){
	var apprScore = document.getElementById("apprScore"+id).value
	var finalScore = document.getElementById("apprFinalScore"+id).value
	var adjust = document.getElementById("adjustFactor"+id).value
	if(adjust == '')
		adjust=0;
	var aperand = document.getElementById("oprand"+id).value
	if(aperand == '+'){
		document.getElementById("apprFinalScore"+id).value = Math.round((eval(apprScore)+ eval(adjust)) * Math.pow(10, 2))/ Math.pow(10, 2);
	}
	if(aperand == '-'){
		document.getElementById("apprFinalScore"+id).value = Math.round((eval(apprScore)- eval(adjust)) * Math.pow(10, 2))/ Math.pow(10, 2);
	}
	if(document.getElementById("apprFinalScore"+id).value > eval(document.getElementById("paraFrm_maxRating").value))
	{
			alert(document.getElementById("scorebalancer.finalscore").innerHTML+' should not exceed Max Overall Rating '+document.getElementById("paraFrm_maxRating").value);
			document.getElementById("apprFinalScore"+id).value =apprScore;
			document.getElementById("adjustFactor"+id).value=0;
	}
}
function next()
   {
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1")
   	{	document.getElementById('myPage').value=2;
	 document.getElementById('paraFrm_show').value=2;
	 }
	 else{
	 document.getElementById('myPage').value=eval(pageno)+1;
	 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	 }
	   document.getElementById('paraFrm').action="ScoreBalancer_paging.action";
	   document.getElementById('paraFrm').submit();
}
	//-----function for previous
function previous()
   {
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="ScoreBalancer_paging.action";
	   document.getElementById('paraFrm').submit();
	   
   }
function on()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	 document.getElementById('myPage').value=eval(val);
	 document.getElementById('selectname').value=val;
	   document.getElementById('paraFrm').action="ScoreBalancer_paging.action";
	   
	   document.getElementById('paraFrm').submit();
   }
  
function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno==""))
  	 document.getElementById('selectname').value=pgno;
  	 onLoad();
  	}
  	
  	function callPage(id){
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		document.getElementById('paraFrm').action="ScoreBalancer_paging.action";
		document.getElementById('paraFrm').submit();
   }
   
   function onLoad()
   {
   		if(document.getElementById('count').value=="" ||document.getElementById('count').value==0)
   			return false;
   		for(i=1;i<=document.getElementById('count').value;i++)
			if(document.getElementById('hoprand'+i).value == "")
				document.getElementById('hoprand'+i).value = '+';
				
   		for(i=1;i<=document.getElementById('count').value;i++)
   			if(!document.getElementById('chkEmp'+i).checked)
   			{
   				document.getElementById('checkAll').checked=false;
   				return false;
   			}
   		document.getElementById('checkAll').checked=true;
   		document.getElementById('checkAll').disabled=true;
   }
   function chkEmployee(id)
   {
   		if(document.getElementById('count').value=="" ||document.getElementById('count').value==0)
   			return false;
   		
   		document.getElementById('chkEmp'+id).value=document.getElementById('apprScore'+id).value
   		+'&'+document.getElementById('adjustFactor'+id).value
   		+'&'+document.getElementById('oprand'+id).value
   		+'&'+document.getElementById('apprFinalScore'+id).value
   		+'&'+document.getElementById('empId'+id).value
   		+'&'+document.getElementById('templateCode'+id).value
   		+'&'+id;
   		document.getElementById('chkEmp'+id).value=document.getElementById('chkEmp'+id).value.replace('+','P');
   		document.getElementById('chkEmp'+id).value=document.getElementById('chkEmp'+id).value.replace('-','M');
   		
   		if (document.getElementById('chkEmp'+id).checked == true)
   		{
   			document.getElementById('oprand'+id).disabled=true;	
   			document.getElementById('adjustFactor'+id).readOnly=true;
   		}
   		else 
   		{
   			document.getElementById('oprand'+id).disabled=false;	
   			document.getElementById('adjustFactor'+id).readOnly=false;
   		}
   		
   		for(i=1;i<=document.getElementById('count').value;i++)
   			if(!document.getElementById('chkEmp'+i).checked)
   			{
   				document.getElementById('checkAll').checked=false;
   				return false;
   			}
   		document.getElementById('checkAll').checked=true;
   }
   function chkAllEmp()
   {
   		for(i=1;i<=document.getElementById('count').value;i++)
   		{
   			if(!document.getElementById('chkEmp'+i).disabled)
   				document.getElementById('chkEmp'+i).checked=document.getElementById('checkAll').checked;

	   		document.getElementById('chkEmp'+i).value=document.getElementById('apprScore'+i).value
	   		+'&'+document.getElementById('adjustFactor'+i).value
	   		+'&'+document.getElementById('oprand'+i).value
	   		+'&'+document.getElementById('apprFinalScore'+i).value
	   		+'&'+document.getElementById('empId'+i).value
	   		+'&'+document.getElementById('templateCode'+i).value
	   		+'&'+i;
			
	   		document.getElementById('chkEmp'+i).value=document.getElementById('chkEmp'+i).value.replace('+','P');
	   		document.getElementById('chkEmp'+i).value=document.getElementById('chkEmp'+i).value.replace('-','M');
	   		
	   		if (document.getElementById('chkEmp'+i).checked == true)
	   		{
	   			document.getElementById('oprand'+i).disabled=true;	
	   			document.getElementById('adjustFactor'+i).readOnly=true;
	   		}
	   		else 
	   		{
	   			document.getElementById('oprand'+i).disabled=false;	
	   			document.getElementById('adjustFactor'+i).readOnly=false;
	   		}
   		}
   }
   function finalizeFun()
   {
   		if(document.getElementById("paraFrm_apprCode").value==""){
			alert("Please select "+document.getElementById("appraisal.code").innerHTML)	;
			return false;	
		}
   		var tableLength = document.getElementById("count").value;
		if(tableLength == 0 )
		{
			alert("No employees for this appraisal.");
			return false;
		}
   		
   		var flag=false;
   		if(!chkAllFinalize())
   		{
   			alert("Appraisal scores for all the employees have been already finalised");
   			return false;
   		}
   		for(i=1;i<=document.getElementById('count').value;i++)
   			if(document.getElementById('chkEmp'+i).checked && !document.getElementById('chkEmp'+i).disabled)
   			{
   				flag=true;
   				break;
   			}
   		if(!flag)
   		{
   			alert("Please select employees to finalize");
   			return false;
   		}
   		if(!confirm("Are you sure want to finalize these record(s)?"))
   			return false;
   		var apprId = document.getElementById('paraFrm_apprId').value;
   		finalizeEmployees('ScoreBalancer_finalizeEmployees.action?apprId='+apprId+'&abc='+Math.random(),'paraFrm');
   		alert("Appraisal score for selected employees has been finalized");
   		var flag=0;
   		for(i=1;i<=document.getElementById('count').value;i++)
   			if(document.getElementById('chkEmp'+i).checked)
   			{
   				document.getElementById('chkEmp'+i).disabled=true;
   				document.getElementById('apprScore'+i).readOnly=true;
   				document.getElementById('adjustFactor'+i).readOnly=true;
   				document.getElementById('oprand'+i).disabled=true;
   				document.getElementById('apprFinalScore'+i).readOnly=true;
   			}
   			else if(flag==0)
   				flag=2;
   		if(flag==0)
   			document.getElementById('checkAll').disabled=true;
	  }
   
   function setHoprand(obj)
   {
   		var id=obj.id.replace("oprand","");
   		document.getElementById('hoprand'+id).value=document.getElementById('oprand'+id).value;
   		//alert(id);
   		callFinal(id);
   }
   
   function chkAllFinalize()
   {
   		for(i=1;i<=document.getElementById('count').value;i++)
   			if(!document.getElementById('chkEmp'+i).disabled){
   				return true;
   			}
   		return false;
   }

	
	function resetFun()
	{
		document.getElementById('paraFrm_startDate').value='';
		document.getElementById('paraFrm_endDate').value='';
		document.getElementById('paraFrm_apprId').value='';
		document.getElementById("paraFrm_apprCode").value='';
		document.getElementById('paraFrm_apprCode').value='';
		document.getElementById('paraFrm_divCode').value='';
		document.getElementById('paraFrm_divName').value='';
		document.getElementById('paraFrm_branchCode').value='';
		document.getElementById('paraFrm_branchName').value='';
		document.getElementById('paraFrm_deptCode').value='';
		document.getElementById('paraFrm_deptNameUp').value='';
		document.getElementById('paraFrm_apprEmpCode').value='';
		document.getElementById('paraFrm_apprEmpName').value='';
		document.getElementById("paraFrm").action="ScoreBalancer_input.action";
		document.getElementById("paraFrm").submit();
		document.getElementById("paraFrm").target = 'main';
	}
	function recalculateFun(){
		
		if(document.getElementById("paraFrm_apprCode").value==""){
			alert("Please select "+document.getElementById("appraisal.code").innerHTML)	;
			return false;	
		}
		var tableLength = document.getElementById("count").value;
	
		if(tableLength == 0 )
			alert("No employees for this appraisal.");
			return false;
		}
   		
   		var flag=false;
   		if(!chkAllFinalize())
   		{
   			alert("Appraisal scores for all the employees have been already finalised");
   			
   		}
   		for(i=1;i<=document.getElementById('count').value;i++)
   			if(document.getElementById('chkEmp'+i).checked && !document.getElementById('chkEmp'+i).disabled)
   			{
   				flag=true;
   				break;
   			}
   		if(!flag)
   		{
   			alert("Please select employees to recalculate");
   			return false;
   		}
   		if(!confirm("Are you sure want to recalculate these record(s)?"))
   			return false;
   		var apprId = document.getElementById('paraFrm_apprId').value;
   		recalculateEmployees('ScoreBalancer_recalEmployees.action?apprId='+apprId,'paraFrm');
   		
	}




