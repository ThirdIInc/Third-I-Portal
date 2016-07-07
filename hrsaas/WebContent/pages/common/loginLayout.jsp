  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
   "http://www.w3.org/TR/html4/strict.dtd">

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="commonValidations.jsp" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<%-- Show usage; Used in Header --%>
<tiles:importAttribute name="title" scope="request"/>
<html>
    <head>
    <title><tiles:getAsString name="title"/></title>
    <link rel="shortcut icon" href="../pages/images/favicon.ico">   
      <%@ include file="commonCSS.jsp" %> 

<script type="text/javascript">
<!--
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
// -->
</script>

</head>
<body leftmargin="0" topmargin="0" >
<script type="text/javascript">
var browserDetect='true';
if(BrowserDetect.browser=='Explorer' && Number(BrowserDetect.version) < 7){
browserDetect='false';
}
if(BrowserDetect.browser=='Firefox' && Number(BrowserDetect.version) < 4) {
browserDetect='false';
}
if(BrowserDetect.browser=='Chrome' && Number(BrowserDetect.version) < 16) {
browserDetect='false';
}
if(BrowserDetect.browser=='Safari' && Number(BrowserDetect.version) < 5) {
browserDetect='false';	
}
if(browserDetect=='false'){
document.write("<table><tr><td>&nbsp;&nbsp;&nbsp; <img src='../pages/mypage/images/icons/systemalert.png'/>	<b>System Alert</b>:<font size='2'> Your current version "+BrowserDetect.browser+"-"+ BrowserDetect.version+" is not compatible or not supported !</font>");
document.write('<br>&nbsp;&nbsp;&nbsp;<font size="2">For best performance, it is recommended to upgrade latest version.</font>');
if(BrowserDetect.browser=='Explorer')
{
document.write("&nbsp;&nbsp;&nbsp;<a  class='contlink' href='http://windows.microsoft.com/en-IN/internet-explorer/products/ie/home'>Internet Explorer</a>");
}
if(BrowserDetect.browser=='Firefox')
{
document.write("&nbsp;&nbsp;&nbsp;<a href='http://www.mozilla.org/en-US/products/download.html'>FireFox Mozilla</a>");
}
if(BrowserDetect.browser=='Chrome')
{
document.write("&nbsp;&nbsp;&nbsp;<a href='http://www.google.com/chrome/'>Google Chrome</a>");
}
if(BrowserDetect.browser=='Safari')
{
document.write("&nbsp;&nbsp;&nbsp;<a href='http://www.apple.com/safari/download/'>Apple Safari</a>");
}
document.write('&nbsp;&nbsp;&nbsp;<font size="2">to upgrade.</font>');
 
document.write('</td></tr></table>');
}
</script> 
<table id="loginLayout" width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111">
<tr>
<td >
<s:hidden name="actionMessage"/>
<tiles:insertAttribute name="header"/>
</td></tr>
<tr height="100%" WIDTH="100%">
<td valign="top">
<tiles:insertAttribute name="body"/>
</td>
</tr>
<tr><td >
<tiles:insertAttribute name="footer"/>
</td></tr>
</table>
<script>
function callMessage() {
	var message = document.getElementById("actionMessage").value;
	if(message !="" ){
		alert (message);
		}
}
callMessage();
</script>
</body>       
</html>