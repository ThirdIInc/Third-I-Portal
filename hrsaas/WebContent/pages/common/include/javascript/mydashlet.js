// JavaScript Document
//function dashlet_top(number,name) {
//document.write("<div style='background-color: #0000AA'>");
//document.write("<ul class='noBullet' id='col0'><li id='hidden1b' style='height: 5px' class='noBullet'>&nbsp;&nbsp;&nbsp;</li><li class='noBullet' id='dashlet_0'><div id='dashlet_entire_0' ><table width='100%' cellpadding='0' cellspacing='0' border='0' class='bigheadcell'><tr><td><div  onmouseover='cursor_change(this)' id='dashlet_header_0' ><table width='100%' cellpadding='0' cellspacing='0' border='0' ><tr><td nowrap class='whitetxt' >Knowledge Management </td><td valign='bottom' nowrap ><table width='98%' cellspacing='0' cellpadding='0' border='0' ><tr><td width='68%' class='txt'>&nbsp;</td><td nowrap width='32%'><div style='width: 100%;text-align:right'><a href='#' onClick="hideSection('dashlet_body_0');" ><img src='themes/sugar/images/min.gif' width='21' height='18' title='Edit Dashlet' alt='Edit Dashlet'  border='0'  align='absmiddle'></a><a href='#' onClick="hideSection('dashlet_body_0');"><img width='21' height='18' border='0' align='absmiddle' title='Refresh Dashlet' alt='Refresh Dashlet' src='themes/sugar/images/max.gif'/></a><a href='#' onClick='dashlet_delete(0)'><img width='21' height='18' border='0' align='absmiddle' title='Delete Dashlet' alt='Delete Dashlet' src='themes/sugar/images/close.gif'/></a></div></td></tr></table></td></tr></table></div></td></tr><tr><td><div class='bggrad' id='dashlet_body_0'><table width='100%' border='0' cellpadding='0' cellspacing='0'><tr><td width='1%'><img src='images/search_01.gif' width='8' height='6' alt='' /></td><td width='97%' background='images/search_02.gif'><img src='images/search_02.gif' width='187' height='6' alt='' /></td><td width='2%'><img src='images/search_03.gif' width='11' height='6' alt='' /></td></tr><tr><td class='bgleft'>&nbsp;</td><td background='images/search_05.gif'>");
//}


//function dashlet_bottom() {
//document.write("</td><td class='bgright'>&nbsp;</td></tr><tr><td><img src='../pages/common/themes/sugar/images/search_07.gif' width='8' height='15' alt='' /></td><td background='../pages/common/themes/sugar/images/search_08.gif'><img src='../pages/common/themes/sugar/images/search_08.gif' width='187' height='15' alt='' /></td><td><img src='../pages/common/themes/sugar/images/search_09.gif' width='11' height='15' alt='' /></td></tr></table></div></td></tr></table></div><br/></li></ul>");
//document.write("</td><td class='bgright'>&nbsp;</td></tr><tr><td><img src='images/search_07.gif' width='8' height='15' alt='' /></td><td background='images/search_08.gif'><img src='images/search_08.gif' width='187' height='15' alt='' /></td><td><img src='images/search_09.gif' width='11' height='15' alt='' /></td></tr></table></div></td></tr></table></div><br></li></ul>");
//}


function dashlet(number,name,height,closeNumber,displayNumber) {
	document.write("<ul class='noBullet' id='col"+number+"'><li class='noBullet' id='dashlet_"+number+"'><div id='dashlet_entire_"+number+"'><table width='100%' cellpadding='0' cellspacing='0' border='0' ><tr ><td ><div  onmouseover='cursor_change(this)' id='dashlet_header_"+number+"'  ><table width='100%' cellpadding='0' cellspacing='0' border='0' ><tr><td nowrap class='bigheadcell' width='100%'>"+name+"</td><td valign='bottom' nowrap ><table  cellspacing='0' cellpadding='0' border='0' ><tr><td width='68%' class='txt'>&nbsp;</td><td nowrap width='32%'><div style='width: 100%;text-align:right'><a href='#' onClick=hideSection('dashlet_body_"+number+"') class='min'><img src='../pages/common/themes/sugar/images/trans.gif' width='21' height='18' title='Edit Dashlet' alt='Edit Dashlet'  border='0'  align='absmiddle'></a><a href='#' onClick=refreshSection('"+number+"') class='refersh'><img width='21' height='18' border='0' align='absmiddle' title='Refresh Dashlet' alt='Refresh Dashlet' src='../pages/common/themes/sugar/images/trans.gif'/></a>");

	if(closeNumber==1){
		document.write("<a href='#' onClick='dashlet_delete("+number+")'class='close'><img width='21' height='18' border='0' align='absmiddle' title='Delete Dashlet' alt='Delete Dashlet' src='../pages/common/themes/sugar/images/trans.gif'  /></a>");
	}
	document.write("</div></td></tr></table></td></tr></table></div></td></tr><tr><td><div id='dashlet_body_"+number+"' ><table width='100%' border='0' cellpadding='0' cellspacing='0'><tr><td class='search_01'></td><td width='97%' class='search_02'></td><td class='search_03'></td></tr><tr><td class='bgleft'>&nbsp;</td><td class='search_05'>");
	
	if(displayNumber==0) {
		document.write("<div id='dashlet_innermost_"+number+"' style='height:"+height+"px' class='bggrad'></div>");	
	}else {
		document.write("<iframe frameborder='0' name='dashlet_innermost_"+number+"' style='height:"+height+"px' width='100%'></iframe>");
	}	
	
	
	document.write("</td><td class='bgright'>&nbsp;</td></tr><tr><td class='search_07'></td><td class='search_08'></td><td class='search_09' height='15'><img src='../pages/common/themes/sugar/images/trans.gif' width='11' height='15'/></td></tr></td></tr></table></div></td></tr></table></div></li></ul>");
	if(displayNumber==1) {
		document.getElementById('col'+number).style.display='none';
	}	
}

/****** not in use

function dashlet_top_menu(number,name,height) {
	document.write("<ul class='noBullet' id='col"+number+"'><li class='noBullet' id='dashlet_"+number+"'><div id='dashlet_entire_"+number+"' ><table width='100' cellpadding='0' cellspacing='0' border='0'><tr><td><div  onmouseover='cursor_change(this)' id='dashlet_header_"+number+"' ><table width='100%' cellpadding='0' cellspacing='0' border='0' ><tr><td nowrap class='smallheadcell' width='100%'>"+name+"</td><td valign='bottom' nowrap ><table width='98%' cellspacing='0' cellpadding='0' border='0' ><tr><td width='68%' class='txt'>&nbsp;</td><td nowrap width='32%'><div style='width: 100%;text-align:right'><a href='#' onClick=hideSection('dashlet_body_"+number+"')></a><a href='#' onClick=hideSection('dashlet_body_"+number+"')></a></div></td></tr></table></td></tr></table></div></td></tr><tr><td><div id='dashlet_body_"+number+"' ><table width='100%' border='0' cellpadding='0' cellspacing='0'><tr><td width='1%'><img src='../pages/common/themes/sugar/images/search_01.gif' width='8' height='6' alt='' /></td><td width='97%' background='../pages/common/themes/sugar/images/search_02.gif'><img src='../pages/common/themes/sugar/images/search_02.gif' width='187' height='6' alt='' /></td><td width='2%'><img src='../pages/common/themes/sugar/images/search_03.gif' width='11' height='6' alt='' /></td></tr><tr><td class='bgleft'>&nbsp;</td><td background='../pages/common/themes/sugar/images/search_05.gif'><div  style='height:"+height+"px' class='bggradmenu'>");
}

function dashlet_top_adv(number,name,height) {
	document.write("<ul class='noBullet' id='col"+number+"'><li class='noBullet' id='dashlet_"+number+"'><div id='dashlet_entire_"+number+"' ><table width='100' cellpadding='0' cellspacing='0' border='0'><tr><td><div  onmouseover='cursor_change(this)' id='dashlet_header_"+number+"' ><table width='100%' cellpadding='0' cellspacing='0' border='0' ><tr><td nowrap class='smallheadcell' width='100%'>"+name+"</td><td valign='bottom' nowrap ><table width='98%' cellspacing='0' cellpadding='0' border='0' ><tr><td width='68%' class='txt'>&nbsp;</td><td nowrap width='32%'><div style='width: 100%;text-align:right'><a href='#' onClick=hideSection('dashlet_body_"+number+"')></a><a href='#' onClick=hideSection('dashlet_body_"+number+"')></a></div></td></tr></table></td></tr></table></div></td></tr><tr><td><div id='dashlet_body_"+number+"' ><table width='100%' border='0' cellpadding='0' cellspacing='0'><tr><td width='1%'><img src='../pages/common/themes/sugar/images/search_01.gif' width='8' height='6' alt='' /></td><td width='97%' background='../pages/common/themes/sugar/images/search_02.gif'><img src='../pages/common/themes/sugar/images/search_02.gif' width='187' height='6' alt='' /></td><td width='2%'><img src='../pages/common/themes/sugar/images/search_03.gif' width='11' height='6' alt='' /></td></tr><tr><td class='bgleft'>&nbsp;</td><td background='../pages/common/themes/sugar/images/search_05.gif'><div  style='height:"+height+"px' class='bggradadv'>");
}

function dashlet_bottom(){
	document.write("</div></td><td class='bgright'>&nbsp;</td></tr><tr><td><img src='../pages/common/themes/sugar/images/search_07.gif' width='8' height='15' alt='' /></td><td background='../pages/common/themes/sugar/images/search_08.gif'><img src='../pages/common/themes/sugar/images/search_08.gif' width='187' height='15' alt='' /></td><td><img src='../pages/common/themes/sugar/images/search_09.gif' width='11' height='15' alt='' /></td></tr></table></div></td></tr></table></div></li></ul>");
}
******/



function hideSection(id) {
if(document.getElementById(id).style.display==='none'){
//document.getElementById(id).style.visibility='hidden'; 
document.getElementById(id).style.display='block';
}else{
document.getElementById(id).style.display='none';
}
return false;
}


function hideSection1(id,path) {
//document.getElementById(id).style.visibility='hidden'; 
document.getElementById('col'+id).style.display='block';
dashletLoaders[id]=path;
refreshSection1(id,path);
document.getElementById('col'+id).frameborder='0';
}


function cursor_change(obj) {
obj.style.cursor='move';
}

function dashlet_delete(id) {
if(document.getElementById('col'+id).style.display=='none'){
//document.getElementById(id).style.visibility='hidden'; 
document.getElementById('col'+id).style.display='block';
}else{
document.getElementById('col'+id).style.display='none';
}
return false;
}

function dashlet_delete123(number) {
SUGAR.sugarHome.deleteDashlet(number); 
return false;
}

function loader(id) {
document.getElementById(id).innerHTML='';
return false;
}

function submitSection(id,url,postData) {
try{	
	document.getElementById('dashlet_innermost_'+id).innerHTML="<table height='100%' width='100%'><tr><td class='load' height='100%' width='100%'>Loading .... Please wait<br><img src='../pages/images/ajax-loader.gif' width='20' height='20'/></td></tr></table>";
}catch(e) {
	//alert('refresh : '+e);	
}
try{ 
	var postData = "";
	YAHOO.util.Connect.asyncRequest('POST', url, {success:handleSuccess,failure: handleFailure,argument: ['dashlet_innermost_'+id]}, postData);
}catch(e){alert('error in dashlet'+e);}
}

function refreshSection1(id,path) {
try{
	frames['dashlet_innermost_'+id].location.href=path;
}catch(e) {
	//alert('refresh : '+e);	
}
}

function refreshSection(id) {
try{
	document.getElementById('dashlet_innermost_'+id).innerHTML="<table height='100%' width='100%'><tr><td class='load' height='100%' width='100%'>Loading .... Please wait<br><img src='../pages/images/ajax-loader.gif' width='20' height='20'/></td></tr></table>";
}catch(e) {
	//alert('refresh : '+e);	
}

try{ 
var postData = "";
YAHOO.util.Connect.asyncRequest('POST', dashletLoaders[id], {success:handleSuccess,failure: handleFailure,argument: ['dashlet_innermost_'+id]}, postData);
}catch(e){alert('error in dashlet'+e);}
}

var handleSuccess = function(o){ 	 
	    if(o.responseText !== undefined){ 
	        //document.getElementById(o.argument).innerHTML = "Transaction id: " + o.tId; 
	        //document.getElementById(o.argument).innerHTML+= "HTTP status: " + o.status; 
	        //document.getElementById(o.argument).innerHTML+= "Status code message: " + o.statusText; 
	        //document.getElementById(o.argument).innerHTML += "<li>HTTP headers: <ul>" + o.getAllResponseHeaders + "</ul></li>";
	        //alert(o.responseText); 
	        document.getElementById(o.argument).innerHTML = o.responseText; 
	        //document.getElementById(o.argument).innerHTML += "Argument object: " + o.argument; 
	    } 
}

var handleFailure = function(o){ 	 
	    if(o.responseText !== undefined){ 
	        //document.getElementById(o.argument).innerHTML = "fail Transaction id: " + o.tId; 
	        //document.getElementById(o.argument).innerHTML+= "fail HTTP status: " + o.status; 
	       // document.getElementById(o.argument).innerHTML+= "fail Status code message: " + o.statusText; 
	       // document.getElementById(o.argument).innerHTML += "<li>fail HTTP headers: <ul>" + o.getAllResponseHeaders + "</ul></li>"; 
	        document.getElementById(o.argument).innerHTML = "<span class='load'>Response Failure : <br>" + o.responseText+'</span>'; 
	       // document.getElementById(o.argument).innerHTML += "fail Argument object: " + o.argument; 
	    } 
}