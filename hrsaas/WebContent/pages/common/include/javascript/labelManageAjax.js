// Function to call Ajax to update the label on user screen
var responseVal;
function changeLabel(url)
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

function restoreLabel(url)
{
    //Do the Ajax call
    if (window.XMLHttpRequest){ // Non-IE browsers
	     req = new XMLHttpRequest();
	     req.onreadystatechange = processRestoreLabel;
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
	     	req.onreadystatechange = processRestoreLabel;
	       	req.open("GET", url, true);
	        req.send();
	     }
    }     
}

function processRestoreLabel()
{
  	 if (req.readyState == 4) 
  	 { // Complete
      	if (req.status == 200)
      	{ // OK response 
      		if(trim(req.responseText) == "")
      			alert("Default value for this label is assigned to other label. So cannot restore this label");
      		else
      		{     
				var labelNames = document.getElementsByTagName('label');
			  	for(var i=0; i < labelNames.length; i++)
			  	{
			  	try{
			  		if(labelNames[i].getAttribute('name') == document.getElementById('changeLabelId').value)
						document.getElementById(labelNames[i].id).innerHTML=req.responseText;
					}catch(e)
					{
					}
				}
				document.getElementById('myNewLabel').value = "";
				document.getElementById('changeLabelId').value = "";
				document.getElementById('labelDiv').style.display = "none";
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
				var labelNames = document.getElementsByTagName('label');
				var newValue = document.getElementById('myNewLabel').value;
				if(newValue.length > 35)
					if(newValue.indexOf(" ") < 0)
						newValue = newValue.substr(0,30)+"- "+newValue.substr(30,newValue.length);
				for(var i=0; i < labelNames.length; i++)
				{
					try 
					{
						if(labelNames[i].getAttribute('name') == document.getElementById('changeLabelId').value)
							document.getElementById(labelNames[i].id).innerHTML=newValue;
					}catch(e)
						{
						}
				}
		  		document.getElementById('myNewLabel').value = "";
				document.getElementById('changeLabelId').value = "";
				document.getElementById('labelDiv').style.display = "none";
		  	}
		  	else
		  	{
		  		alert("Entered new label name is already defined. Please enter different name.");
		  	}
        } 
        else 
        {
        	alert("Problem with server response:\n " + req.statusText);
      	}
     }
}