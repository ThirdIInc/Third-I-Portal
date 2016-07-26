<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp" %>
<jsp:include page="/pages/common/labelManagement.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet" type="text/css" title="default-theme"
	href="../pages/common/css/commonCSS.jsp" />
<%
String comanyName = (String) request.getAttribute("comanyName");
String pool_name="abc";
%>
<title><%=comanyName%></title>

</head>
<body topmargin="0" leftmargin="0" class="main">

<script type="text/javascript" src="../pages/common/Ajax.js"></script>
<script type="text/javascript">
/***********************************************
* IFrame SSI script II- © Dynamic Drive DHTML code library (http://www.dynamicdrive.com)
* Visit DynamicDrive.com for hundreds of original DHTML scripts
* This notice must stay intact for legal use
***********************************************/
var iframeids=["myframe"]
var divids=["con"];
var iframehide="yes"
var getFFVersion=navigator.userAgent.substring(navigator.userAgent.indexOf("Firefox")).split("/")[1]
var FFextraHeight=0;//parseFloat(getFFVersion)>=0.1? 16 : 0 //extra height in px to add to iframe in FireFox 1.0+ browsers
function resizeCaller() {
var dyniframe=new Array()
	for (i=0; i<iframeids.length; i++){
		if (document.getElementById)
			resizeIframe(iframeids[i])
		if ((document.all || document.getElementById) && iframehide=="no"){
			var tempobj=document.all? document.all[iframeids[i]] : document.getElementById(iframeids[i])
			tempobj.style.display="block"
		}
	}
}

function resizeIframe(frameid){
	var currentfr=document.getElementById(frameid)
if (currentfr && window.opera){
currentfr.style.display="block"
if (currentfr.contentDocument && currentfr.contentDocument.body.offsetHeight) {//ns6 syntax
////alert('//alert(currentfr.height);'+currentfr.height);

currentfr.height ="1000"; 
currentfr.scroll="yes";
//currentfr.height = currentfr.contentDocument.body.offsetHeight; 

////alert('offset height :'+currentfr.contentDocument.body.offsetHeight);
//alert('//alert(currentfr.height);'+currentfr.height);
}



if (currentfr.addEventListener){
//alert('readjustIframe  :'+currentfr.addEventListener);
//alert(currentfr.height);
currentfr.addEventListener("load", readjustIframe, false);
}

}
/***********************************/
if (currentfr && !window.opera){
currentfr.style.display="block"
if (currentfr.contentDocument && currentfr.contentDocument.body.offsetHeight) {
	currentfr.height="0";
	currentfr.height = currentfr.contentDocument.body.offsetHeight; 
 } else if (currentfr.Document && currentfr.Document.body.scrollHeight) {
	currentfr.height="0";
	currentfr.height = currentfr.Document.body.scrollHeight;
}

	if (currentfr.addEventListener){
		currentfr.addEventListener("load", readjustIframe, false);
	} else if (currentfr.attachEvent) {
		currentfr.detachEvent("onload", readjustIframe) // Bug fix line
		currentfr.attachEvent("onload", readjustIframe)
	}
}
}

function readjustIframe(loadevt) {
	var crossevt=(window.event)? event : loadevt
	var iframeroot=(crossevt.currentTarget)? crossevt.currentTarget : crossevt.srcElement
	if (iframeroot) {
		resizeIframe(iframeroot.id);		
	}
}

function loadintoIframe(iframeid, url) {
	if (document.getElementById)
	document.getElementById(iframeid).src=url
}


if (window.addEventListener) {
	window.addEventListener("load", resizeCaller, false);
 } else if (window.attachEvent) {
		window.attachEvent("onload", resizeCaller)
 } else {
	window.onload=resizeCaller
 }

</script>
	
<s:form action="PartnerHomePage" id="paraFrm" theme="simple">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="top_env_bg">
				<tr>
					<td valign="top">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
										
						<tr>
									<td COLSPAN="2">
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
								       background="../pages/common/css/default/images/top_trans.png"
								       height="15px">
										<tr>
											<td width="33%" style="padding-left: 10px;">
												<font color="white">									
												 Welcome <%=(String)request.getAttribute("partnerName")%>
										   		</font>
											</td>
											<td width="47%"></td>
											<td width="16%" align="right">
												<a href="<%=request.getContextPath()%>/recruit/PartnerLogin_logout.action" target="_top"><font color="white"> Logout </a></font> 
											</td>
											<td width="4%"></td>
										</tr>
								     </table>
								</td>
							</tr>
						<tr>
							<td colspan="2">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								background="../pages/common/css/default/images/top_trans.png"
								height="15px">
								
							</table>
							</td>
						</tr>
						<tr>
							<td valign="top" align="left" class="header_height" ><img
								src="../pages/common/css/default/images/logo.png" /></td>
							<td valign="top" align="right">
							<%
				String cmpName = null;
				cmpName = (String) request.getAttribute("logo");
				System.out.println("String cmpName =" + cmpName);
				if (cmpName != null && !cmpName.equals("null")
						&& !cmpName.equals("")) {
			%>
							<img
				src="../pages/Logo/<%=session.getAttribute("session_pool")%>/<%=cmpName%>"
				 />
				 <%}else{ %>
							<img
								src="../pages/common/css/default/images/client_logo.gif" />
				<%}%>				
							
				</td>
						</tr>
						<tr>
							<td colspan="2" valign="bottom">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td valign="bottom">
									<%
									String[][] menuList = (String[][]) request.getAttribute("menuList");
									%>
									<div id="tabnav" style="vertical-align: bottom">
									<ul>
										<%
										try {
											
										%>
										<%
													if (menuList != null && menuList.length >0) {
													for (int i = 0; i < menuList.length; i++) {
										%>
										<li id="list<%=(i+1)%>"><a href="#" id="menuid<%=(i+1)%>"
											<%if(i+1==1){%> class="on" <%}%>
											onclick="callCurrent('<%=(i+1)%>','<%=menuList.length%>');callCenterPage(<%=String.valueOf(menuList[i][0]) %>,'<%=String.valueOf(menuList[i][2]) %>');callSubMenu(<%=String.valueOf(menuList[i][0]) %>);"><span><%=String.valueOf(menuList[i][1])%></span></a></li>
										<%
												}
												}
										%>
										<%
											} catch (Exception e) {
												e.printStackTrace();
											}
										%>
									</ul>
									</div>
									</td>
								</tr>
								<tr>
							<td colspan="2" valign="bottom">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="thought">
								<tr>
									<td width="80%"></td>
									<td width="20%" align="right" id="toplinks"></td>
								</tr>
							</table>
							</td>
						</tr>
							</table>
							</td>
						</tr>
						
					</table>
					</td>
				</tr>
			</table>
			
			
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="202" valign="top"><iframe frameborder="0" style="vertical-align: top; float: left;"		src="<%=request.getContextPath()%>/recruit/PartnerHomePage_create.action" name="contents" 
					scrolling="no" marginwidth="0" height="450"
						width="200"  marginheight="0" frameborder="0" vspace="0"
						style="float:top" > </iframe><iframe
						src="<%=request.getContextPath()%>/pages/common/hiddenFrame.jsp&app_random=<%=java.lang.Math.random()%>"
						name="hiddenFrame" scrolling="no" marginwidth="0" marginheight="0"
						frameborder="2" vspace="0" width="0" height="0" > </iframe></td>
					<td valign="top" align="left">
					<iframe id="myframe" frameborder="0" style="float: left" 
						allowtransparency="yes"
						
						scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
						name="main" width="800" height="0" style="vertical-align: top; float: right;">					
					</iframe></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="2" class="bottom_env_bg" height="100" align="center"
						valign="middle"><font color="#000000">&copy; 2004 -
					2007 Glodyne Technoserve Limited. All Rights Reserved.<br />
					Best Viewed In 1024 x 768 resolution. Please disable popup
					blockers.</font> </td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
<input type="hidden" id="hdMenuId" name="hdMenuCode" />
</s:form>
</body>


<script>
document.getElementById('myFrame').width=(window.screen.width-210);
function callSubMenu(menuCode) {

try{
//alert('callSubMenu -- menuCode : '+menuCode);
var width = (window.screen.width-210);
		document.getElementById('myframe').width=width;
		document.getElementById('hdMenuId').value=menuCode;
		document.getElementById("paraFrm").target="contents";
		document.getElementById("paraFrm").action='<%=request.getContextPath()%>/recruit/PartnerHomePage_create.action?menuCode='+menuCode;
		//alert('action Name  '+document.getElementById("paraFrm").action);
		document.getElementById("paraFrm").submit();	
}
catch(e)
{
	alert(e);
}
	
}

function callCenterPage(menuCode,menuLink){
 	//alert('callCenterPage -- menuLink : '+menuLink);
	//parent.frames[1].location=parent.frames[1].location;
	if(menuLink=='null') {
		menuLink='/recruit/PartnerHomePage_input.action';
	}
	document.getElementById('hdMenuId').value=menuCode;
	document.getElementById("paraFrm").target="main";
	document.getElementById("paraFrm").action=menuLink;
	document.getElementById("paraFrm").submit();
}

function callCurrent(id, num) {
	 //alert("callCurrent : "+id);
		for(var i=0; i<num; i++) {
			document.getElementById('menuid'+(i+1)).className='li';
		}
		document.getElementById('menuid'+id).className='on';
	}
</script>