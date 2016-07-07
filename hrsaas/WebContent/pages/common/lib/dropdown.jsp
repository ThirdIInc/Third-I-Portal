<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<style type="text/css">
html {
	height: 100%;
}

#f9_iframe {
	position: absolute;
	z-index: 5;
	vertical-align: top;
}
	#f9page_div {
		top: 50px;
		left: 300px;
		position:absolute;
		z-index:10;
		vertical-align:top;
		border: 1px solid #000000;
		background-color: #cccccc;
	}
#f9_div {
	position: absolute;
	z-index: 5;
	vertical-align: top;
}

form {
	display: inline;
}

</style>
<script type="text/javascript">
var f9_iframe;
var f9_div;
var showSearch='false';
var inputObject;
var multipleFlagVar;
var ent;

function callPageDisplay(url,width, height,showFrame,e,topVar,leftVar) {
try{
	win_width=width;
	win_height=height;

	if(showFrame=='true') {
   		if (document.getElementById('f9frame_page') == null) {
       		f9_iframe = document.createElement('IFRAME');
			f9_iframe.border='0';
			document.body.appendChild(f9_iframe);
		}
			f9_iframe.style.width = win_width;
			f9_iframe.style.height = win_height;
			f9_iframe.id = 'f9frame_page';
			f9_iframe.name = 'f9frame_page';
			f9_iframe.style.left = 200;//ajax_getLeftPos(inputObject)+'px';//ajax_optionDiv.style.left;
			f9_iframe.style.top = 100;//(ajax_getTopPos(inputObject)+inputObject.offsetHeight)+'px'; //ajax_optionDiv.style.top;
	} else {
   		if (document.getElementById('f9page_div') == null) {
       		f9_div = document.createElement('DIV');
       		f9_div.style.zIndex = "5";
			f9_div.border='0';
			document.body.appendChild(f9_div);
		}	
			f9_div.style.width = win_width;
			f9_div.id = 'f9page_div';
			f9_div.name = 'f9page_div';
			f9_div.style.left = 200;//ajax_getLeftPos(inputObject)+'px';//ajax_optionDiv.style.left;
			if(topVar!=null){
				f9_div.style.top = 100+eval(topVar);
			}else{ 
				f9_div.style.top = 100;//(ajax_getTopPos(inputObject)+inputObject.offsetHeight)+'px'; //ajax_optionDiv.style.top;
			}	
	}
      if (window.ActiveXObject) { // IE
	     
	      req = new ActiveXObject("Microsoft.XMLHTTP");
	      if (req) {
	      try{
		        req.onreadystatechange = toshowPage;        
		        req.open("GET", url, true);
		        req.send();
	        }catch(e){alert(e);}        
	      }
	      
	      
    } else if (window.XMLHttpRequest) { // Non-IE browsers
    
      	req = new XMLHttpRequest();
      	req.onreadystatechange = toshowPage;
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    }   
    
  }catch(e){alert('the error:'+e);}  
    
  }
  
  function toshowPage() {
  	  if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response      	
			resText= req.responseText;
			if(req.responseText){
				showPageframe(resText);
			}		
		}
      } else {
        	//f9_iframe.contentWindow.document.write('<br><font size="2">Loading..</font>');
      }
    }  
  
  
function showPageframe(resText) {
	    if(showSearch=='true') {	
			document.all.f9frame_page.style.display='block';
			try{
				f9frame_page.contenmtWindow.document.close();
				f9frame_page.contentWindow.document.open();
			}catch(e) {
				f9frame_page.contentWindow.document.open();
			}
			f9frame_page.contentWindow.document.write(resText);	
			f9frame_page.style.verticalalign="top";
		} else {
		
			var topText="<div style='padding-left: 5px;'><a href='#' onClick=closePage()>Close</a></div>";
			document.getElementById('f9page_div').style.display='block';
			document.getElementById('f9page_div').innerHTML=topText+resText;	
			
		}
	}
	
function closePage() {
	document.getElementById('f9page_div').style.display='none';
}	

function getDropdown(url,width, height,obj,event,showSearch,multipleFlag,align) {
	var inputObject=document.getElementById(obj);	
	resText='';
	win_width=width;
	win_height=height;
	this.showSearch=showSearch;
	this.multipleFlagVar=multipleFlag;
	
	if (window.event){
	    event = window.event;
	    }
	else{
	   	event= event;
	   	}
	   	
	   		
	if(event.type=="keyup") {				
		if(event.keyCode==9) {
			try	{
				inputObject.focus();
			}catch(e){}
		}
		if(event.keyCode==27) {
			try{
				if(showSearch=='false') {
					document.getElementById('f9_div').style.display='none';
				}else {
					document.all.f9_iframe.style.display='none';
				}
				if(inputObject.value.indexOf(';') != -1) {
					inputObject.value=inputObject.value.substring(0,inputObject.value.lastIndexOf(';')+1);
				} else {
					inputObject.value='';
				}
				return;
			}catch(e){}
		}
		if(event.keyCode==40) {
			try{
				document.getElementById('f9_div').focus();				
			}catch(e){}
		}		
		
		
		if(trim(inputObject.value).length <=2 ) {
			return;
		}else {
			f9SearchText=trim(inputObject.value);
			//alert(1);
			//alert(multipleFlagVar);
			if(multipleFlag=='multiple'){
			
			var f9SearchTexSplit=f9SearchText.split(';');
			//alert(f9SearchText.split(';')[f9SearchText.split(';').length-1]);
			if(f9SearchText.indexOf(';')=='-1'){
						f9SearchText = f9SearchText;
					}else{
						f9SearchText =f9SearchText.substring(fld.value.lastIndexOf(';')+1,fld.value.length);
					}
					f9SearchText=trim(f9SearchText);
					if(f9SearchText.length <=2 ) {
						return;
					}	
				//f9SearchText=f9SearchText.split(';')[f9SearchText.split(';').length-1];
			}
			//alert('f9SearchText=='+f9SearchText);
			if(url.indexOf("?") != -1){
				url=url+"&f9SearchText="+f9SearchText+"&sid="+Math.random();
			}
			else{
				url=url+"?f9SearchText="+f9SearchText+"&sid="+Math.random();
			}
			
		}		
	}
	
	if(showSearch=='true') {
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
	} else {
   		if (document.getElementById('f9_div') == null) {
       		f9_div = document.createElement('DIV');
			f9_div.border='0';
			f9_div.style.margin='0';
			f9_div.style.padding='0';
			document.body.appendChild(f9_div);
		}
			f9_div.style.width = win_width;
			f9_div.id = 'f9_div';
			f9_div.name = 'f9_div';
			
			if(align=='right')			
			f9_div.style.left = (ajax_getLeftPos(inputObject)+inputObject.offsetWidth-win_width)+'px';//ajax_optionDiv.style.left;
			else
			f9_div.style.left = ajax_getLeftPos(inputObject)+'px';//ajax_optionDiv.style.left;
			
			f9_div.style.top = (ajax_getTopPos(inputObject)+inputObject.offsetHeight)+'px'; //ajax_optionDiv.style.top;	
	}
      if (window.ActiveXObject) { // IE
	      req = new ActiveXObject("Microsoft.XMLHTTP");
	      if (req) {
	      try{
		        req.onreadystatechange = toshowf9;        
		        req.open("GET", url, true);
		        req.send();
	        }catch(e){alert(e);}        
	      }
    }  else if (window.XMLHttpRequest) { // Non-IE browsers
      	req = new XMLHttpRequest();
      	req.onreadystatechange = toshowf9;
      
      try {
      	req.open("GET", url, true); //was get 
      } catch (e) {
        alert("Problem Communicating with Server\n"+e);
      }
      req.send(null);
    }   
  }
  
  function toshowf9() {
  	  if (req.readyState == 4) { // Complete
      if (req.status == 200) { // OK response      	
			resText= req.responseText;
			if(req.responseText){
				showframe(resText);
			}		
		}
      } else {
        	//f9_iframe.contentWindow.document.write('<br><font size="2">Loading..</font>');
      }
    }  
    
    function showframe(resText) {
	    if(showSearch=='true') {	
			document.all.f9_iframe.style.display='block';
			try{
				f9_iframe.contenmtWindow.document.close();
				f9_iframe.contentWindow.document.open();
			}catch(e) {
				f9_iframe.contentWindow.document.open();
			}
			f9_iframe.contentWindow.document.write(resText);	
			f9_iframe.style.verticalalign="top";
			f9_iframe.contentWindow.document.getElementById('scrollDiv').style.cssClass="scrollf9";	
			//f9_iframe.style.height=f9_iframe.contentWindow.document.getElementById('entireDiv').scrollHeight;
		} else {
			document.getElementById('f9_div').style.display='block';
			document.getElementById('f9_div').innerHTML=resText;	
			document.getElementById('ShowSearch').style.display='none';
			document.getElementById('f9BottomInfo').style.display='none';
			document.getElementById('scrollDiv').style.height=document.getElementById('scrollDiv').scrollHeight;			
			try{
				inputObject.focus();
			}catch(e){}
		}
	}
    
    function setHeight() { 
    	alert('scroll height'+f9_iframe.contentWindow.document.scrollHeight);
		f9_iframe.height = f9_iframe.contentWindow.document.offsetHeight;
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
	function trim(stringToTrim) {
		return stringToTrim.replace(/^\s+|\s+$/g,"");
	}
	
	function callSearchText(){
	var sText=document.getElementById('searchText').value;
	var searchText1=document.getElementById('searchText1').value;
	var tbl = document.getElementById('tableID');
	var lastRow = tbl.rows.length;
	var aaa=(parseInt(searchText1)-1);
		for(k = 0; k < lastRow; k++) {
			if(sText==''){
			document.getElementById('TR'+k).style.display = "";
			}
			else{
				var value1=document.getElementById('nameText'+k+aaa).value;
				document.getElementById('TR'+k).style.display = "none";
				fieldList = value1.split(""+sText+"");
				if((value1.toUpperCase()).indexOf((sText.toUpperCase()))!='-1'){
				document.getElementById('TR'+k).style.display = "";
				}			
			}	
		}
	}
	
	function generateCrystalReport(reportcode,accCode,inFromWeek,inToWeek,inlistAccountID,method_action,publishDatevalue,msg){
	try{
	
	
		var DivPopUp = document.getElementById('f9page_div'); 
		var dispFlag="Yes";
		var reportcodeValue=document.getElementById(reportcode).value;
		var accCodeValue=document.getElementById(accCode).value;
		var fromWeekValue=document.getElementById(inFromWeek).value;
		var toWeekValue=document.getElementById(inToWeek).value;
		if(fromWeekValue==''){
			alert('Please select from date');
			return false;
		}
		if(toWeekValue==''){
			alert('Please select to date');
			return false;
		}
		
		var inlistAccountIDValue=document.getElementById(inlistAccountID).value;
		
		if(msg=='client'){
			var pubDateValue=document.getElementById(publishDatevalue).value;
			
			if(pubDateValue==''){
				alert('Data not available.');
				return false;
			}
			
		///	if(fromWeekValue >= pubDateValue && toWeekValue >= pubDateValue){
		///		alert('Data not available.');
		///		return false;
		///	}
			
			var strDate1 = toWeekValue.split("-"); 
			var strDate2 = pubDateValue.split("-");
			var strDate3 = fromWeekValue.split("-");
			
			var dtoWeekValue=new Date(strDate1[2],strDate1[1]-1,strDate1[0]);// To DAte
			var dpubDateValue=new Date(strDate2[2],strDate2[1]-1,strDate2[0]);// Publish Date
			var dFromWeekValue=new Date(strDate3[2],strDate3[1]-1,strDate3[0]);// From Date
			
//			alert(strDate1);
//			alert(starttime);
			if(dFromWeekValue > dpubDateValue){
				alert("Data is available upto " +pubDateValue+ ". Please change the Date Range.");
				return false;
			}
			
			if(dtoWeekValue > dpubDateValue ){
				dispFlag="No";
				var con=confirm("Data is available upto " +pubDateValue+ ". Do you really want to proceed.?");
			}
			
			
			
			if(dtoWeekValue < dpubDateValue){
				 toWeekValue=document.getElementById(inToWeek).value;
			}else{
			 	toWeekValue=document.getElementById(publishDatevalue).value;
			}  
			 
			 	//added for hide div when generate report button is clicked start
				if(fromWeekValue!='' && toWeekValue!=''){
					DivPopUp.style.display = "none";
				}else{
					DivPopUp.style.display = "block";
				}
				//added for hide div when generate report button is clicked end
			
			 if(con||dispFlag=="Yes") {
				 	reportcodeValue=reportcodeValue+'_'+accCodeValue+'_'+fromWeekValue+'_'+toWeekValue+'_'+inlistAccountIDValue;
					document.getElementById('paraFrm').target = "_blank";
					document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/cr/'+method_action+'?reportcodeValue12='+reportcodeValue;
					document.getElementById('paraFrm').submit();
					document.getElementById('paraFrm').target = 'main';
			 }
				
		} else{
		
			//added for hide div when generate report button is clicked start
			if(fromWeekValue!='' && toWeekValue!=''){
				DivPopUp.style.display = "none";
			}else{
				DivPopUp.style.display = "block";
			}
			//added for hide div when generate report button is clicked end
			
			
			reportcodeValue=reportcodeValue+'_'+accCodeValue+'_'+fromWeekValue+'_'+toWeekValue+'_'+inlistAccountIDValue;
			document.getElementById('paraFrm').target = "_blank";
			document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/cr/'+method_action+'?reportcodeValue12='+reportcodeValue;
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main';
		
		}
		
		
		
	} catch(e){
	 alert(e);
	}
		}
		
</script>