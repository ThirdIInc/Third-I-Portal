<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
   "http://www.w3.org/TR/html4/strict.dtd">
   
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ include file="commonValidations.jsp" %>
<%@include file="/pages/common/lib/dropdown.jsp"%>
<%@ include file="commonCSS.jsp" %>
	<%@page import="org.paradyne.model.admin.srd.OfficialDetailModel"
	import="org.paradyne.bean.common.LoginBean"%>

<style>

.rightTile
{
border-left: 1px solid #EBEBEB;
padding-left: 5px;
}

</style>

<script type="text/javascript" src="../pages/cssV2/js/rounded-corners.js"></script>
<script type="text/javascript" src="../pages/cssV2/js/form-field-tooltip.js"></script>
<script type="text/javascript" src="../pages/common/js/jquery.min.js"></script>
<script type="text/javascript" src="../pages/common/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="../pages/common/js/jquery.loader.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.2/jquery.min.js"></script>	
<link href="../pages/cssV2/css/mainstyle.css" rel="stylesheet" type="text/css" />
<link href="../pages/cssV2/css/form-field-tooltip.css" rel="stylesheet" type="text/css" />
<!--<script type="text/javascript" src="../pages/common/functions.js"></script>

--><%-- Show usage; Used in Header --%>
<tiles:importAttribute name="title" scope="request"/>
<html xmlns="http://www.w3.org/1999/xhtml"> 
    <head>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<link href="../pages/portal/images/style.css" rel="stylesheet"
	type="text/css" />
<title><tiles:getAsString name="title"/></title>      
    </head>
<body topmargin="0" leftmargin="0" class="main" 
	onload='parent.alertsize(document.body.scrollHeight);'>
	

<%try{ 
	String [][]hrmData=null;
%>
<%
try{
	hrmData=(String[][])session.getAttribute("hrmData");
}catch(Exception e){
	e.printStackTrace();
}
%>
<%if(hrmData==null ){
	OfficialDetailModel model=new OfficialDetailModel();
	model.initiate(config.getServletContext(),session);
	try{
		LoginBean loginBean=(LoginBean)session.getAttribute("loginBeanData");
		String code=loginBean.getProfileCode();
		String multipleCode= loginBean.getMultipleProfileCode();
		model.showHRM(multipleCode);
	}catch(Exception e){
		e.printStackTrace();
	}
	model.terminate();
	hrmData=(String[][])session.getAttribute("hrmData");
}
	%>
<script>
onerror=errorHandler;
function errorHandler(){

}

</script>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>
<table width="100%" border="0">
<tr>
<td>
<s:hidden name="generalFlag" />
<s:hidden value="profileEmpName"/>
<s:hidden  name="menuCode" id="paraFrm_menuCode" />
<fieldset><legend class="legend2"><table><tr><td>
				&nbsp;&nbsp;<img src="../pages/mypage/images/icons/myprofile.png" />
				</td>
				<td>&nbsp;<font size="4">
				<s:property value="profileEmpName"/>
				</font>
				</td></tr></table>
		</legend>
		<table width="100%" border="0">
		<tr>
			<td>
		<div style="float: left; width: 18%" class="backCol">
		<table border="0">
			<tr>
				<td height="20" colspan="2">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td rowspan="3" width="30%">
								<table width="70%" height="50" border="0" cellpadding="0"
								cellspacing="0" >
								<tr>
									<td bgcolor="#FFFFFF" align="center" style="padding: 3px;">
									<s:if test="%{flag}">
										<%
										%>
										<img src="../pages/images/employee/NoImage.jpg " height="100"
											align="middle" />
									</s:if>
									<s:else>
										<%
			                             String photoStr = (String) request.getAttribute("profilePhoto");
		
										%>
										<%if(photoStr.equals("NoImage.jpg"))   { %>
										<img src="../pages/images/employee/NoImage.jpg" height="100"
											align="middle" />


										<% } else  { %>

										<img
											src="../pages/images/<%=session.getAttribute("session_pool") %>/employee/<%=photoStr %>"
											height="100" align="middle"/>
										<%} %>
									</s:else>
									</td>
								</tr>
								<tr><td height="18" width="70" align="middle">
								<s:hidden name="uploadFileName"/>
									<s:if test="generalFlag">
									</s:if>
									<s:else>
								<a href="#" class="text1" onclick="uploadImageFile('uploadFileName');">Change
								Picture</a></s:else>
								</td>
							</tr>
							</table>


							</td>
						
						
						<!--<td width="80%" valign="center">
						<table width="92%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr><s:hidden name="uploadFileName"/>
								<td height="18" ><a href="#" class="text1" onclick="uploadImageFile('uploadFileName');">Change
								Picture</a></td>
							</tr>
							<tr>
								<td height="18"><a href="#" class="text1" >Update
								Profile</a></td>
							</tr>
						</table>
						<a href="#" class="text1"></a></td>
					--></tr>
				</table>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td height="20">&nbsp;</td>

			</tr>
				<%
         					for (int i = 0; i < hrmData.length; i++) {
        	 			%>
			<tr id="off"  >
				<td width="17%">
				<div align="center"><img
					src="../pages/mypage/images/icons/<%=hrmData[i][2]%>img.png" width="16"
					height="16" /></div>
				</td>
				<td width="100%" height="20" nowrap="nowrap"><a id="official"
					href="..<%=hrmData[i][1] %>" class="dashlink"><%=hrmData[i][0]%></a></td>
			</tr>
			<% } 
			%>
			

		</table>
		</div>
		<div style="float: left; width: 82%">
		<table width="100%" id="layoutTable" border="0" cellpadding="0"
			cellspacing="0" align="center">
			<tr>
				<td colspan="3"><tiles:insertAttribute name="header" /></td>
			</tr>
			<tr height="100%" WIDTH="100%">
				<td width="2" valign="top"
					><tiles:insertAttribute
					name="menu" /></td>
				<td valign="top" width="100%">
				<tiles:insertAttribute name="body" /></td>
				<td valign="top" id="righTileId" style="padding-top: 10px;"><tiles:insertAttribute
					name="rightTile" /></td>
			</tr>
			
		</table>
		</div>

		</td>
	</tr>
</table>
</fieldset>
</td>
</tr>
</table>
<br>
<div style="float:left;width:100%;height:1px; background-color:#FF6600"></div>
<table width="100%" border="0">
	<tr>
		<td valign="top"><tiles:insertAttribute name="footer" /></td>
	</tr>
</table>
<%}catch(Exception e){
	e.printStackTrace();
}
%>
</body>
<s:hidden name="actionMessage"/>
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
if(window.screen.width>1024)
{
document.getElementById('righTileId').className='rightTile';

}
</script>


<script>
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
	var message = document.getElementById("actionMessage").value;
	if(trim(message)!="" ) {
		alert(message);
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
}catch(e){
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

  function uploadImageFile(fieldName) {
	
		var path="images/<%=session.getAttribute("session_pool")%>/employee";
		window.open('../pages/common/uploadImage.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
      	document.getElementById('paraFrm').target="main";
} 


</script>
