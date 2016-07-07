
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@ taglib prefix="s" uri="/struts-tags"%>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>PeoplePower</title>


<script type="text/javascript" src="../pages/common/Ajax.js"></script>
<link href="../pages/portal/images/dropdown-menu.css" media="screen"
	rel="stylesheet" type="text/css" />


<link href="../pages/portal/images/style.css" rel="stylesheet"
	type="text/css" />
<link rel="stylesheet" type="text/css" title="default-theme"
	href="<%=request.getContextPath()%>/pages/common/commonCSS.jsp" />




</head>

<body>

<s:form action="menuAction" id="paraFrm" theme="simple" name="mainForm">
	<div id="MainDiv" onblur="self.focus()"></div>
	<!-- Code for Relogin window starts -->
	<input type="hidden" name="divMovementHidden" id="divMovementHidden" />
	<div id="optionDiv"
		style='position: absolute; z-index: 3; width: 470px; height: 120px; display: none; border: 2px solid; top: 150px; left: 250px; padding: 10px'
		class="formbg">



	<TABLE WIDTH=276 BORDER=0 align="center" CELLPADDING=0 CELLSPACING=0>
		<TR>
			<TD width="10" class="relogin"></TD>
			<TD width="262" class="relogin1"></TD>
			<TD width="10" class="relogin2"></TD>
		</TR>
		<%
			String pool = (String) session.getAttribute("poolId");
			String user = (String) session.getAttribute("lname");
		%>
		<TR>
			<TD class="relogin3">&nbsp;</TD>
			<TD class="reloginbg">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2">
				<tr>
					<td height="25" colspan="2">
					<div align="center"><span class="reloginstyle">
					<div id="timerDiv">Your session is about to expire in <span
						id="timerDiv2"></span>Min.<span id="timerDiv1"></span> Sec. Please
					Relogin</div>
					</span></div>
					</td>
				</tr>
				<tr>
					<td height="25"><span class="style1">Username</span></td>
					<td><input name="loginName" type="text" class="box" readonly
						value="<%=user%>" size="20" /> <input name="poolId" type="hidden"
						class="box" value="<%=pool%>" size="20" /></td>
				</tr>
				<tr>
					<td height="22" class="style1">Password</td>
					<td height="22" class="style1"><input name="loginPassword"
						type="password" class="box" value="" size="20" id="loginPassword" /></td>
				</tr>
				<tr>
					<td height="25" colspan="2">
					<div align="center"><input type="button" id="continue"
						value="Continue" class="token" onclick="return reLoginSubmit();" />
					<input type="button" onclick="logout();" value=" Logout "
						class="token" /></div>
					</td>
				</tr>
			</table>
			</TD>
			<TD class="relogin4"></TD>
		</TR>
		<TR>
			<TD class="relogin5">&nbsp;</TD>
			<TD class="relogin6">&nbsp;</TD>
			<TD class="relogin7">&nbsp;</TD>
		</TR>
	</TABLE>
	<input type="hidden" name="infoId" value="<%=pool%>" /> <s:hidden
		name="name" value="%{name}" /></div>

	<!-- Code for Relogin window ends -->

	<%
		String homeCode = "";
		try {
			if (session.getAttribute("homeCode") != null
			&& !("" + session.getAttribute("homeCode"))
					.equals("null"))
				homeCode = String.valueOf(session.getAttribute("homeCode"));
		} catch (Exception e) {

		}
	%>
	<input type="hidden" id="hdMenuId" name="hdMenuCode"
		value="<%=homeCode%>" />

	<%
		String cmpName = null;
		try {
			cmpName = (String) request.getAttribute("logo");
		} catch (Exception e) {

		}
	%>
	<table width="99%" border="0" align="center" cellpadding="0"
		cellspacing="0">
		<tr>
			<td valign="bottom">
			<div style="width: 100%">

			<div style="float: left; width: 40%"><img
				src="../pages/common/css/default/images/logo.jpg" /></div>

			<div style="float: right; width: 60%">
			<table width="80%" height="51" border="0" align="right"
				cellpadding="0" cellspacing="0">
				<tr>
					<td width="72%" align="right">
					<div align="right" class="emptext"><strong><%=request.getAttribute("empDivisionName")%></strong></div>
					</td>
					
					
				</tr>
			</table>
			</div>
			</div>
			<div
				style="height: 1px; background-color: #f2f2f2; float: left; width: 100%"></div>
			<div>

			<div style="float: left; width: 25%; margin: 0; padding: 0;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">

				<%
					String str = "..";
					String gender = "";
					try {
						gender = (String) request.getAttribute("gender");
						if (gender != null) {
							gender = gender.equals("M") ? "male.png" : "female.png";
						} else {
							gender = "empimage.gif";
						}
						str = (String) request.getAttribute("profilePhoto");
						if (str == null || str.equals("null") || str.equals(".."))
							str = "empimage.gif";
					} catch (Exception e) {
						str = "empimage.gif";
						e.printStackTrace();
					}
				%>
				<tr>
					<td width="16%">
					<table width="50" height="50" border="0" align="center"
						cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<%
							if (str.equals("empimage.gif")) {
							%> <img src="../pages/mypage/images/icons/<%=gender%>" width="40"
								height="40" /> <%
 } else {
 %> <img
								src="../pages/images/<%=session.getAttribute("session_pool") %>/employee/<%=str %>"
								width="40" height="40" /> <%
 }
 %>
							</td>
						</tr>
					</table>
					</td>
					<td width="84%" valign="top">
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td style="padding-top: 2px;" class="emptext"><strong><%=request.getAttribute("UserName")%></strong><br />
							<strong><%=request.getAttribute("UserID")%></strong></td>
						</tr>
						<tr>
							<td valign="bottom" style="padding-top: 4px;" class="emptext">
							<a href="javascript:void(0)" title="Settings"
								onclick="callSubMenu('415');callCenterPage('415','<%=request.getContextPath()%>/common/ConfigurationHome_input.action');"
								class="contlink">Settings</a> <span class="link">| </span><a
								title="Help" target="_blank" href="../WebHelp/My_Home_Page.htm"
								href="javascript:void(0)" class="contlink">Help</a> <span
								class="link">|</span> <a href="javascript:void(0)"
								title="Logout" onclick="logout();" class="contlink">Logout</a></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>
			<div style="float: right; width: 75%" id="menu">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td width="90%" height="5px">

									<table width="100%" cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td colspan="5">

											<ul id="navigation" class="nav-main">
												<li class="list"><a href="javascript:void(0)"
													onclick="callHome();">Home</a></li>
												<li class="list"><a href="javascript:void(0)"
													onclick="callMypageVal();">MyPage</a></li>
												<%
														try {
														String[][] d1MenuList = (String[][]) request
														.getAttribute("d1MenuList");
														if (d1MenuList != null && d1MenuList.length > 0) {
															for (int i = 0; i < d1MenuList.length; i++) {
												%>
												<li class="list"><a href="javascript:void(0)"
													id="d1menuid<%=(i+1)%>"
													onclick="callCurrent('<%=(i+1)%>','<%=d1MenuList.length%>');callCenterPage(<%=String.valueOf(d1MenuList[i][0]) %>,'<%=String.valueOf(d1MenuList[i][2]) %>');">
												<%=String.valueOf(d1MenuList[i][1])%></a></li>
												<%
														}
														}
													} catch (Exception e) {
														e.printStackTrace();
													}
												%>
												<%
														try {
														String[][] menuList = (String[][]) request
														.getAttribute("menuList");
														if (menuList != null && menuList.length > 0) {
												%>
												<li class="list"><a href="javascript:void(0)">Modules
												</a>
												<ul class="nav-sub">

													<%
													for (int i = 0; i < menuList.length; i++) {
													%>

													<li><a href="javascript:void(0)" id="menuid<%=(i+1)%>"
														onclick="callCurrent('<%=(i+1)%>','<%=menuList.length%>');callCenterPage(<%=String.valueOf(menuList[i][0]) %>,'<%=String.valueOf(menuList[i][2]) %>');callSubMenu(<%=String.valueOf(menuList[i][0]) %>);">
													<%=String.valueOf(menuList[i][1])%> </a></li>
													<%
													}
													%>
												</ul>
												</li>
											</ul>
											</td>
											<%
												}
												} catch (Exception e) {
													e.printStackTrace();
												}
											%>
										</tr>
									</table>
									</td>
									<td width="31%"><script>
										function setSearchFocus(){
												//alert('in setSearchFocus');
												if(window.event && window.event.keyCode == 13){
														getSearchData();
												}
										 }</script>
									<table width="39%" border="0" align="right" cellpadding="0"
										cellspacing="0">

										<tr>
											<td width="36%" align="absmiddle"><input
												name="searchText" id="searchText"
												onkeypress="setSearchFocus();" type="text" class="search"
												onfocus="clearText('searchText','Search Employee')"
												onblur="setText('searchText','Search Employee')" size="28" /><input
												type="hidden" id="menuoremployee" value="emp"
												name="menuoremployee"></td>
											<td width="64%"><img onClick="getSearchData()"
												style="cursor: pointer;"
												src="../pages/portal/images/search.gif" width="21"
												height="19" /></td>
										</tr>

									</table>
									</td>
								</tr>
								<tr>
									<td colspan="2" height="1px" bgcolor="#f2f2f2"></td>
								</tr>
								<tr>
									<td colspan="2" valign="bottom" class="emptext"
										style="padding-top: 7px; padding-left: 13px; padding-bottom: 3px;"><strong>Thought
									of the day:</strong> <span><%=request.getAttribute("thought")%></span></td>

								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</div>
			</div>

			<div
				style="background-color: #6979ac; float: left; width: 100%; margin: 0; padding: 0;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="25px" class="mainheader"></td>
				</tr>
			</table>
			</div>



			<div style="float: left; width: 100%">
			<%
				String homePage = "";
				if (session.getAttribute("homePage") != null)
					homePage = String.valueOf(session.getAttribute("homePage"));
				else
					homePage = "/common/HomePage_input.action";
			%>

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td valign="top"><iframe
								style="vertical-align: top; float: left; border: 0px solid;"
								name="contents" scrolling="no" marginwidth="0" height="450"
								id="leftFrame" width="0" marginheight="0" frameborder="0"
								vspace="0" style="float:top"> </iframe> <iframe
								src="<%=request.getContextPath()%>/pages/common/hiddenFrame.jsp"
								name="hiddenFrame" scrolling="no" marginwidth="0"
								marginheight="0" frameborder="0" vspace="0" width="0" height="0">
							</iframe></td>
							<td valign="top" align="left"><iframe id="myframe"
								frameborder="0"
								style="vertical-align: top; float: left; border: 0px solid;"
								allowtransparency="yes"
								src="<%=request.getContextPath()+homePage%>" scrolling="auto"
								marginwidth="0" marginheight="0" vspace="0" name="main"
								width="98%"> </iframe></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>

			</div>
			</td>
		</tr>
	</table>
</s:form>
</body>
</html>
<script>
function alertsize(pixels){
	document.body.style.offsetHeight="0px";
    pixels+=30;
 	//alert('before'+ document.getElementById('myframe').style.height);
    document.getElementById('myframe').style.height=pixels+"px";
    //alert('after'+ document.getElementById('myframe').style.height);    
}

function reLoginSubmit(){
  	try{
  			var pwd = document.getElementById('loginPassword').value; 
  			if(pwd ==""){
  			alert("Enter Password");
  			return false;
  			}
  		}catch(e){
  		alert(e);
  		}
  	 checkPassword('<%=request.getContextPath()%>/common/Login_checkReloginPassword.action?','mainForm');
  	
  	}

function callCurrent(id, num) {
	try{
		for(var i=0; i<num; i++) {
			document.getElementById('menuid'+(i+1)).className='li';
		}
		document.getElementById('menuid'+id).className='on';
		}catch(e){
			alert("Error   "+e);
		}
	}
	
	
function callCenterPage(menuCode,menuLink){
	try{
		parent.frames[1].location=parent.frames[1].location;
		if(menuLink=='null'){
		   menuLink='/common/HomePage_input.action';
	   }
	   	document.getElementById('hdMenuId').value=menuCode;
		document.getElementById("paraFrm").target="main";
		document.getElementById("paraFrm").action=menuLink;
		document.getElementById("paraFrm").submit();
	}
	catch(e){
		alert("Error  "+e);
	}  
}

function callSubMenu(menuCode){
  if(menuCode!='3'){
      	var width = (window.screen.width-210)+'px';
   		//alert(document.getElementById('leftFrame').style.width);
	    document.getElementById('leftFrame').style.width = '210px';
		//alert('leftFrame '+document.getElementById('leftFrame').style.width);
		//alert('width'+width);
		try{
			document.getElementById('myFrame').style.width = width;
		}catch(e){
		}
		document.getElementById('myframe').style.width=width;
		document.getElementById('hdMenuId').value=menuCode;
		document.getElementById("paraFrm").target="contents";
		document.getElementById("paraFrm").action='<%=request.getContextPath()%>/common/menuAction_create.action?leftPagemenuCode='+menuCode;
		document.getElementById("paraFrm").submit();	
		if(menuCode=='5031'){
	 		document.getElementById('leftFrame').style.width='0px';
	 		document.getElementById('myframe').style.width='100%';
	 	}
  	}
  	else{
  		//document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/common/PayrollHome_getPayrollMenu.action?menuType=PP&mymessage_random='+Math.random();
		document.getElementById('paraFrm').submit();
  		document.getElementById('leftFrame').style.width='0px';
		document.getElementById('myframe').style.width='100%';
  }
}
 
function getSearchData(){
	try{
	   var searchType=document.getElementById('menuoremployee').value;
	   var searchText=document.getElementById('searchText').value;
	
	   if(searchText=="" || searchText=='Search Employee'){
		 document.getElementById('searchText').focus();
		 alert("Please enter minimum 3 chars to search");
		 return false;
	   }
	   else{
			if(searchText.length<3){
				alert("Please enter minimum 3 chars to search");
				document.getElementById('searchText').focus();
				return false;
			}
	  }	
	 url="EmployeePortal_getHomeSearch.action?searchType="+searchType+"&searchText="+searchText+"&random="+Math.random();	
	 document.getElementById('leftFrame').style.width='0px';
	 document.getElementById('myframe').style.width=window.screen.width;
	 document.getElementById('myframe').src=url;
	}
	catch(e){
		alert(e);
	}	
}



function callSettingPage(){
	try{
			 //document.getElementById('leftFrame').style.width='0px';
			//document.getElementById('myframe').style.width='100%';
			document.getElementById('myframe').src="<%=request.getContextPath()%>/common/ConfigurationHome_input.action";
	 }catch(e){
	 }
}
setText();
function callHome(){
		try{
			  
			 	document.getElementById('leftFrame').style.width='0px';
				document.getElementById('myframe').style.width='100%';
				document.getElementById('myframe').src="<%=request.getContextPath()%>/common/HomePage_input.action";
		}
		catch(e){
			  
		}	 
}
	
function setText(){
	if(document.getElementById('searchText').value == '' ){
		document.getElementById('searchText').value ='Search Employee'; 
		document.getElementsByName('searchText').value ='Search Employee';
		document.getElementById('searchText').style.color = '#ACACAC';
	}else if(document.getElementById('searchText').value=='Search Employee'){
			document.getElementById('searchText').style.color = '#ACACAC';
	}
} 


function callMypageVal(){
	try{
		document.getElementById('leftFrame').style.width='0px';
		document.getElementById('myframe').style.width='0px';
		document.getElementById('myframe').src='<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action?isClickOn=Input&hiddenMypageStatus=Inbox&mypage_random='+Math.random();
		document.getElementById('myframe').style.width='100%';
	}catch(e){
			//alert("Error:---- "+e);
	}
}
 
function logout(){
	try{
		document.getElementById("paraFrm").target="_self";
	  	document.getElementById("paraFrm").action='<%=request.getContextPath()%>/common/Login_logout.action';
		
		document.getElementById("paraFrm").submit();
	}
	catch(e){
			alert("Error   "+e);
	}
}
</script>