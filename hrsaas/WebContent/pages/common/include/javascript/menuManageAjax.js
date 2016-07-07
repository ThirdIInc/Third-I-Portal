// Function to call Ajax to update the label on user screen
var responseVal;
function changeMenu(url)
{
    //Do the Ajax call
    if (window.XMLHttpRequest){ // Non-IE browsers
	     req = new XMLHttpRequest();
	     req.onreadystatechange = processChangeLabel;
	     try 
	     {
	       	req.open("GET", url, true); //was get 
	     }catch (e){
	        alert("Problem Communicating with Server\n"+e);
	     }
	     req.send(null);
    } 
    else if (window.ActiveXObject){ // IE
	     req = new ActiveXObject("Microsoft.XMLHTTP");
	     if (req){
	     	req.onreadystatechange = processChangeLabel;
	       	req.open("GET", url, true);
	        req.send();
	     }
    }   
}

function restoreMenu(url)
{
    //Do the Ajax call
    if (window.XMLHttpRequest){ // Non-IE browsers
	     req = new XMLHttpRequest();
	     req.onreadystatechange = processRestoreMenu;
	     try 
	     {
	       	req.open("GET", url, true); //was get 
	     }catch (e){
	        alert("Problem Communicating with Server\n"+e);
	     }
	     req.send(null);
    } 
    else if (window.ActiveXObject){ // IE
	     req = new ActiveXObject("Microsoft.XMLHTTP");
	     if (req){
	     	req.onreadystatechange = processRestoreMenu;
	       	req.open("GET", url, true);
	        req.send();
	     }
    }     
}

function processRestoreMenu()
{
  	 if (req.readyState == 4) 
  	 { // Complete
      	if (req.status == 200)
      	{ // OK response 
      		if(trim(req.responseText) == "")
      			alert("Cannot restore default value for this form");
      		else
      		{    var code=document.getElementById('changeMenuId').value; 
				document.getElementById('newMenuName'+code).innerHTML=req.responseText;
				document.getElementById('myNewName').value = "";
				document.getElementById('changeNameId').value = "";
				document.getElementById('nameDiv').style.display = "none";
			}
        } 
        else 
        {
        	alert("Problem with server response:\n " + req.statusText);
      	}
     }
}

function processChangeLabel()
{
  	 if (req.readyState == 4) 
  	 { // Complete
      	if (req.status == 200)
      	{ // OK response      
      		var p = trim(req.responseText);
      		if(p=="true")
      		{
      		    var code=document.getElementById('changeMenuId').value;
      			var newValue = document.getElementById('myNewName').value;
				document.getElementById('newMenuName'+code).innerHTML=newValue;
		  		document.getElementById('myNewName').value = "";
				document.getElementById('changeNameId').value = "";
				document.getElementById('nameDiv').style.display = "none";
		  	}
		  	else
		  	{
		  		alert("Entered new name is already defined. Please enter different name.");
		  	}
        } 
        else 
        {
        	alert("Problem with server response:\n " + req.statusText);
      	}
     }
}