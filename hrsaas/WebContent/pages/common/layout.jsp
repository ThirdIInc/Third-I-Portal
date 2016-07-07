  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
   "http://www.w3.org/TR/html4/strict.dtd">
   
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="commonValidations.jsp" %>
<%@include file="/pages/common/lib/dropdown.jsp"%>


<style>
.rightTile
{
border-left: 1px solid #EBEBEB ;
padding-left: 5px;
}
</style>

<script type="text/javascript" src="../pages/common/js/jquery.min.js"></script>
<script type="text/javascript" src="../pages/common/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../pages/common/js/jquery.loader.js"></script>

<!--<script type="text/javascript" src="../pages/common/functions.js"></script>

--><%-- Show usage; Used in Header --%>
<tiles:importAttribute name="title" scope="request"/>
<html xmlns="http://www.w3.org/1999/xhtml"> 
    <head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
  
  <%@ include file="commonCSS.jsp" %>

	<link href="../pages/portal/images/style.css" rel="stylesheet"
	type="text/css" />
	
    <title><tiles:getAsString name="title"/></title>      
    </head>
<body topmargin="0" leftmargin="0" class="main" onload='parent.alertsize(document.body.scrollHeight);'>
<script>
onerror=errorHandler;
function errorHandler(){

}

</script>
 <script type="text/javascript" src="../pages/common/Ajax.js"></script>
 <table width="100%" id="layoutTable"  border="0" cellpadding="0" cellspacing="0" valign="top" >
<tr>
<td colspan="3" >
<tiles:insertAttribute name="header"/>
</td></tr>
<tr height="100%" WIDTH="100%" >
<td width="2" valign="top" style="padding-top: 10px;"><tiles:insertAttribute name="menu" /></td>
<td valign="top" width="100%" style="padding-top: 10px;padding-left: 5px;padding-right:5px;">
<tiles:insertAttribute name="body"/>
</td>
<td valign="top" id="righTileId" style="padding-top: 10px;"><tiles:insertAttribute name="rightTile"/></td>
</tr>
<tr><td colspan="3" valign="top">
<tiles:insertAttribute name="footer"/>
</td></tr>
</table>

</body>
<s:hidden name="actionMessage"/>
<s:hidden  name="menuCode" id="paraFrm_menuCode" />
</html>

 <script>
 
 
 var BrowserDetect = {
	init: function () {
		this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
		this.version = this.searchVersion(navigator.userAgent)
			|| this.searchVersion(navigator.appVersion)
			|| "an unknown version";
		this.OS = this.searchString(this.dataOS) || "an unknown OS";
	},
	searchString: function (data) {
		for (var i=0;i<data.length;i++)	{
			var dataString = data[i].string;
			var dataProp = data[i].prop;
			this.versionSearchString = data[i].versionSearch || data[i].identity;
			if (dataString) {
				if (dataString.indexOf(data[i].subString) != -1)
					return data[i].identity;
			}
			else if (dataProp)
				return data[i].identity;
		}
	},
	searchVersion: function (dataString) {
		var index = dataString.indexOf(this.versionSearchString);
		if (index == -1) return;
		return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
	},
	dataBrowser: [
		{
			string: navigator.userAgent,
			subString: "Chrome",
			identity: "Chrome"
		},
		{ 	string: navigator.userAgent,
			subString: "OmniWeb",
			versionSearch: "OmniWeb/",
			identity: "OmniWeb"
		},
		{
			string: navigator.vendor,
			subString: "Apple",
			identity: "Safari",
			versionSearch: "Version"
		},
		{
			prop: window.opera,
			identity: "Opera",
			versionSearch: "Version"
		},
		{
			string: navigator.vendor,
			subString: "iCab",
			identity: "iCab"
		},
		{
			string: navigator.vendor,
			subString: "KDE",
			identity: "Konqueror"
		},
		{
			string: navigator.userAgent,
			subString: "Firefox",
			identity: "Firefox"
		},
		{
			string: navigator.vendor,
			subString: "Camino",
			identity: "Camino"
		},
		{		// for newer Netscapes (6+)
			string: navigator.userAgent,
			subString: "Netscape",
			identity: "Netscape"
		},
		{
			string: navigator.userAgent,
			subString: "MSIE",
			identity: "Explorer",
			versionSearch: "MSIE"
		},
		{
			string: navigator.userAgent,
			subString: "Gecko",
			identity: "Mozilla",
			versionSearch: "rv"
		},
		{ 		// for older Netscapes (4-)
			string: navigator.userAgent,
			subString: "Mozilla",
			identity: "Netscape",
			versionSearch: "Mozilla"
		}
	],
	dataOS : [
		{
			string: navigator.platform,
			subString: "Win",
			identity: "Windows"
		},
		{
			string: navigator.platform,
			subString: "Mac",
			identity: "Mac"
		},
		{
			   string: navigator.userAgent,
			   subString: "iPhone",
			   identity: "iPhone/iPod"
	    },
		{
			string: navigator.platform,
			subString: "Linux",
			identity: "Linux"
		}
	]

};
BrowserDetect.init();
  
if(navigator.appName=='Microsoft Internet Explorer' && Number(BrowserDetect.version) < 8)
	{
	
	document.getElementById('layoutTable').style.width="98%";
	 
	}
	
/*	
if(window.screen.width>1024)
{
document.getElementById('righTileId').className='rightTile';

}
*/
</script>


<script>
try{
parent.frames[1].location=parent.frames[1].location;
}
catch(e)
{
}
	function closethis() {
		document.getElementById('f9_div').style.display='none';
	}
	function newRowColor(cell) {
		cell.className = "Cell_bg_first";
	}
	
	function oldRowColor(cell) {
		cell.className = "Cell_bg_second"; 
	}

	function getRecord(ids,fields,values,submitFlag,methodName) {
	var multipleFlag=multipleFlagVar;
	//alert(2);
	//alert(multipleFlagVar);
		if(fields.length > 0) {			
			fieldList = fields.split(",");
			valuesList = values.split("***");
			for(i = 0; i < fieldList.length; i++) {
				fieldList[i] = fieldList[i].replace(".","_");
				fld = eval("document.getElementById('paraFrm_" + fieldList[i]+"')");
				if(valuesList[i] == "null") {
					fld.value = "";
				} else {	
					if(multipleFlag=='multiple'){
					//alert(1);
					//alert(fld.value.indexOf(';'));
					if(fld.value.indexOf(';')=='-1'){
						fld.value = valuesList[i]+';';
					}else{
						fld.value =fld.value.substring(0,fld.value.lastIndexOf(';')+1)+valuesList[i]+';';
					}
					//fld.value=trim(fld.value).substring(0,fld.value.length-1);
					//alert(fld.value);
					//if(fld.value.charAt(fld.value.length-1))
					
							///fld.value = fld.value+';'+valuesList[i];
							//alert('paraFrm_' + fieldList[i]);
					document.getElementById('paraFrm_' + fieldList[i]).style.height='15px';
   					document.getElementById('paraFrm_' + fieldList[i]).style.height = (document.getElementById('paraFrm_' + fieldList[i]).scrollHeight+5)+"px"
   	
						}	else{
							fld.value = valuesList[i];
						}									
				}
			}
		}
		if(submitFlag == 'true') {
				document.getElementById('paraFrm').target = "main";
				document.getElementById('paraFrm').action = methodName;
				document.getElementById('paraFrm').submit();
		}
		document.getElementById('f9_div').style.display='none';
	}

	function callDropdown(obj,width, height, action,event,showSearch,multiple,align) {
		getDropdown(action,width, height,obj,event,showSearch,multiple,align);
	}

	function callsF9(width, height, action) {	
		if(navigator.appName == 'Netscape') {
			var win = window.open('', 'win', 'top = 150, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		} else {
			var win = window.open('', 'win', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		document.getElementById("paraFrm").target = 'win';
		document.getElementById("paraFrm").action = action;
		document.getElementById("paraFrm").submit();
		document.getElementById("paraFrm").target = 'main';
	}

function closeOkMsgDiv() {
	document.getElementById("myMsgDiv").style.visibility="hidden";	
	return true;
}

function callMessage() {

try{

	var message = document.getElementById("actionMessage").value;
	
	if(trim(message)!="" ) {
		alert(message);
	}
	}catch(e){
		//alert(e);
	}
}
function callSelect(submitFlag, actionName,totalCount)
{
//alert('in callSelect'+totalCount);

 var count=1;
 var displayValue = '';
 var hiddenValue='';
 var chkBox;
 	try{
 	for(var z=0;z<totalCount;z++)
   {
   chkBox = document.getElementById('check'+z).checked;
   	//alert('z=='+z+' and chkBox=='+chkBox);
   	if(chkBox)
   		{
   			//displayValue+=count+". ";
   			displayValue+=document.getElementById('multiListName'+z).value+"; ";
   			hiddenValue+=document.getElementById('multiListCode'+z).value+",";
   		count++;
   		}
   		
   }
  	displayValue = displayValue.substring(0,displayValue.length-2);
    hiddenValue = hiddenValue.substring(0,hiddenValue.length-1);
    
   	document.getElementById(document.getElementById('textAreaField').value).value=displayValue;
   	document.getElementById(document.getElementById('textAreaField').value).style.height='15px';
   	document.getElementById(document.getElementById('textAreaField').value).style.height = (document.getElementById(document.getElementById('textAreaField').value).scrollHeight+5)+"px"
   	
   	
 	document.getElementById(document.getElementById('hiddenId').value).value=hiddenValue;
 	
   if(submitFlag == 'true') {
			document.getElementById('paraFrm').target = "";
			document.getElementById('paraFrm').action = actionName;
			document.getElementById('paraFrm').submit();
		}
}catch(e)
   			{
   				alert(e);
   			}
document.getElementById('f9_div').style.display='none';
}
function  selectAll(totalCount){
	checkValue=document.getElementById('checkAll').checked;
	for (var c=0;c<totalCount;c++){
		document.getElementById('check'+c).checked =checkValue; 
	}
}

function callFormulaBuilder(id){
window.open('','new','top=100,left=300,width=400,height=400,scrollbars=no,status=no,resizable=no');
	 		document.getElementById("paraFrm").target="new";
	 		document.getElementById("paraFrm").action='FormulaBuilder_formulaCalc.action?id='+id; 
	  		document.getElementById("paraFrm").submit();
	  		document.getElementById("paraFrm").target="main";
	  	 }

	
function trim(stringToTrim) {
	return stringToTrim.replace(/^\s+|\s+$/g,"");
}

callMessage();









</script>
 