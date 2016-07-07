<style type="text/css">
	html{
		height:100%;
	}
	#f9_iframe{
		background-color:#F00;
		position:absolute;
		z-index:5;
		vertical-align:top;
	}
	form{
		display:inline;
	}
</style>
<script type="text/javascript">
var f9_iframe;
var showSearch='false';
function getDropdown(url,width, height,obj,event,showSearch) {
	var inputObject=document.getElementById(obj);	
	resText='';
	win_width=width;
	win_height=height;
	this.showSearch=showSearch;	
	if(event.type=="keyup") {
		if(inputObject.value.length <=2 ) {
			return;
		}else {
			f9SearchText=inputObject.value;
			url=url+"?f9SearchText="+f9SearchText+"&sid="+Math.random();
		}		
	}
   		if (document.getElementById('f9_iframe') == null) {
       		f9_iframe = document.createElement('IFRAME');
			f9_iframe.border='0';
			document.body.appendChild(f9_iframe);
		}
			f9_iframe.style.width = win_width;
			f9_iframe.style.height = win_height;
			f9_iframe.id = 'f9_iframe';
			f9_iframe.name = 'f9_iframe';
			f9_iframe.style.left = ajax_getLeftPos(inputObject)+'px';//ajax_optionDiv.style.left;
			f9_iframe.style.top = (ajax_getTopPos(inputObject)+inputObject.offsetHeight)+'px'; //ajax_optionDiv.style.top;
      if (window.ActiveXObject) { // IE
	      req = new ActiveXObject("Microsoft.XMLHTTP");
	      if (req) {
	        req.onreadystatechange = processStateChange;        
	        req.open("GET", url, true);
	        req.send();
	      }
    }  else if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
      
      
      req.onreadystatechange = processStateChange;
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    }   
  }
  
  function processStateChange() {
  	  if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response      	
			resText= req.responseText;
			if(req.responseText){
				showframe(resText);
			}		
			}
      } else {
        f9_iframe.contentWindow.document.write('<br><font size="2">Loading..</font>');
      }
    }  
    
    function showframe(resText) {
		document.all.f9_iframe.style.display='block';
		try{
			f9_iframe.contenmtWindow.document.close();
			f9_iframe.contentWindow.document.open();
		}catch(e) {
			f9_iframe.contentWindow.document.open();
		}			
		f9_iframe.contentWindow.document.write(resText);	
		f9_iframe.style.verticalalign="top";	
			if(showSearch=='false'){
				f9_iframe.contentWindow.document.getElementById('ShowSearch').style.display='none';
			}
			document.frames['f9_iframe'].document.body.style.overflow='hidden';
    }
       
  	function ajax_getLeftPos(inputObj)
	{
	  var returnValue = inputObj.offsetLeft;
	  while((inputObj = inputObj.offsetParent) != null)returnValue += inputObj.offsetLeft;

	  return returnValue;
	}
 
	function ajax_getTopPos(inputObj)
	{

	  var returnValue = inputObj.offsetTop;
	  while((inputObj = inputObj.offsetParent) != null){
	  	returnValue += inputObj.offsetTop;
	  }
	  return returnValue;
	}
</script>