<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="com.itextpdf.text.log.SysoLogger"%>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<jsp:include page="/pages/CommonCssJS.jsp" ></jsp:include>
			<link rel="stylesheet" type="text/css" href="../pages/decisionOnePortal/Css/navigationMenu.css"/>
			<script type="text/javascript" src="../pages/common/Ajax.js"></script>
			<!--  <link rel="stylesheet" type="text/css" href="../pages/portal/images/dropdown-menu.css" media="screen" />--> 
			<!--  <link href="../pages/portal/images/style.css" rel="stylesheet" type="text/css" />-->
			<!--  <link rel="stylesheet" type="text/css" title="default-theme" href="<%=request.getContextPath()%>/pages/common/commonCSS.jsp" />-->
	
				
	
		<script>
		
			function openNav() {
    			document.getElementById("sidebar-wrapper").style.width = "200px";
    			$('.dropdown-submenu').hide();
    			
    			$('#moduals').on('click',function(){
    				
    				$( ".dropdown-submenu" ).toggle( "fast", function() {
    					var check = 'false';
    					return check;
    				});
    				if (check == 'false'){
    					
    					$( ".dropdown-submenu" ).hide( "slow", function() {
        					var check = 'true';
        					return check;
        				});
    				}
 				});
			}
			function closeNav() {
    			document.getElementById("sidebar-wrapper").style.width = "0";
    			
			}
			function closeMenu() {
    			document.getElementById("sidebar-wrapper").style.width = "0";
    			
			}
		
		</script>
	
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
			<title>PeoplePower</title>

	</head>
<body>
	<%
		String hiddenFrame = "";//for dashboard table's rows
	%>
	<s:form action="menuAction" id="paraFrm" theme="simple" name="mainForm" onclick = "closeMenu()">
	<s:hidden id="screenWidth" name="screenWidth"/>

		<%
			try {
				hiddenFrame = (String) request.getAttribute("isClientFlag");
			}//End of try 
			catch (Exception e) {
				e.printStackTrace();
			}//End of Catch
		%>
	
	
		<div id="MainDiv" onblur="self.focus()"></div>
			<!-- Code for Relogin window starts -->
			<input type="hidden" name="divMovementHidden" id="divMovementHidden" />
	
			<input type="hidden" name="isClientFlag" id="isClientFlag" value="<%=request.getAttribute("isClientFlag")%>" />
	
			<div id="optionDiv"	style='position: absolute; z-index: 3; width: 470px; height: 120px; display: none; border: 2px solid; top: 150px; left: 250px; padding: 10px' class="formbg">
				
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
							<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2">
								<tr>
									<td height="25" colspan="2">
										<div align="center">
											<span class="reloginstyle">
												<div id="timerDiv">Your session is about to expire in <span id="timerDiv2"></span>Min.<span id="timerDiv1"></span> Sec. Please Relogin</div>
												</span>
										</div>
									</td>
								</tr>
								<tr>
									<td height="25"><span class="style1">Username</span></td>
									<td><input name="loginName" type="text" class="box" value="<%=user%>" size="20" /> <input name="poolId" type="hidden" class="box" value="<%=pool%>" size="20" /></td>
								</tr>
								<tr>
									<td height="22" class="style1">Password</td>
									<td height="22" class="style1"><input name="loginPassword" type="password" class="box" value="" size="20" id="loginPassword" /></td>
								</tr>
								<tr>
									<td height="25" colspan="2">
										<div align="center"><input type="button" id="continue"	value="Continue" class="token" onclick="return reLoginSubmit();" />
										<input type="button" onclick="logout();" value=" Logout " class="token" /></div>
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
				<input type="hidden" name="infoId" value="<%=pool%>" /> 
					<s:hidden name="name" value="%{name}" />
		</div>

		<!-- Code for Relogin window ends -->

		<%
			String homeCode = "";
			try {
				if (session.getAttribute("homeCode") != null && !("" + session.getAttribute("homeCode")).equals("null")){
					homeCode = String.valueOf(session.getAttribute("homeCode"));
				}
			} catch (Exception e) {
				}
		%>
		<input type="hidden" id="hdMenuId" name="hdMenuCode" value="<%=homeCode%>" />

		<%
			String cmpName = null;
			try {
				cmpName = (String) request.getAttribute("logo");
			} catch (Exception e) {
				}
			System.out.println("String cmpName =" + cmpName);
		%>
	
		<div class="container-fluid" >
			<div class="header-main" >
				<div onclick="closeMenu()">
				<div class="col" style="float: left; margin-top:5px;margin-bottom:5px;">
					<img src="../pages/common/css/default/images/3ilogo.png" style="width:150px;"  />
				</div>
				
				<div class= "col-md-7" style="padding-left: 28%; padding-top:38px;">				
				<div class="form-inline" style="margin-bottom:3px;">
					<div class="form-group">
						<input type="text"	name="searchText" id="searchText" onkeypress="setSearchFocus();"  class="form-control input-sm"
								onfocus="clearText('searchText','Search In')" onblur="setText('searchText','Search In')" >
								<input type="hidden" id="menuoremployee" value="emp" name="menuoremployee"></input>
						</input>
					</div>
					<div class="form-group">			 
					   <select	id="dropdown" class="cd-select" name="dropdown" size="1" style = "height:30px;border-radius:2px;">
							<option value="emp" class="icon-tux">Employee</option>
							<option value="menu" class="icon-finder">Menu</option>
						</select>
					</div>
					<div class="form-group">
						<input type="button" name="search" value="Search" onclick="javascript:getSearchData();" ></input>
							</div>							   		
				</div>
				</div>
				
				<div class="col" style="float: right;margin-top:5px;" >
					<div align="right" class="emptext"><strong><%=request.getAttribute("empDivisionName")%></strong></div>
					<%
						//check for menu list Empty or not
						if (String.valueOf(hiddenFrame).equals("true")) {
					%>
						<div style="width:24%; align:right;">
							<img src="../pages/common/css/default/images/d1logo.jpg"/></div>	
					<%} else {%>	
					<%		}	//end icol	%>
			
				
			<div class="user-profile">
			
			
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
					
							<%
							if (str.equals("empimage.gif")) {
							%> <img src="../pages/mypage/images/icons/<%=gender%>" width="25"
								height="25" />
							 <%
                            } else { %> 
                            <img src="../pages/images/<%=session.getAttribute("session_pool") %>/employee/<%=str %>" width="25" height="25" /> 
                            <%
                             }%>

							<strong><%=request.getAttribute("UserName")%>
							<%=request.getAttribute("UserID")%></strong>
					
						<div style="padding-top: 2px;margin-left: 13px;" class="emptext">
							 
							<a href="javascript:void(0)" title="Settings"
								onclick="callCenterPage('415','<%=request.getContextPath()%>/common/ConfigurationHome_getConfigureMenu.action?menuType=CC');"
								class="contlink">Settings</a> <span class="link">| </span><a
								title="Help" target="_blank" href="../WebHelp/My_Home_Page.htm"
								href="javascript:void(0)" class="contlink">Help</a> <span
								class="link">|</span> <a href="javascript:void(0)"
								title="Logout" onclick="logout();" class="contlink">Logout</a>
						</div>
						
					</div>
				
				</div>
				</div>
				<button type="button"  id="menu-btn" class="hamburger is-closed" data-toggle="offcanvas" onclick="openNav()" style= "margin-top:3%;">
                	<span class="hamb-top"></span>
    				<span class="hamb-middle"></span>
					<span class="hamb-bottom"></span>
           		</button>
				
			</div>
			
				<div class="sidenav" id="sidebar-wrapper">
				<div class="overlay"></div>
				<a href="javascript:void(0)" class="closebtn" onclick="closeNav()"><i class="fa fa-times" aria-hidden="true"></i></a>
					<nav class="navbar"  role="navigation">
					
						<ul class="nav sidebar-nav">
							<li><a href="javascript:void(0)" onclick="callHome();">Home</a></li>
							<li><a href="javascript:void(0)" onclick="callMypageVal();">MyPage</a></li>
						
							<% try {
						
								String[][] d1MenuList = (String[][]) request.getAttribute("d1MenuList");
								if (d1MenuList != null && d1MenuList.length > 0) {
													
									for (int i = 0; i < d1MenuList.length; i++) { %>
										<li>
											<a href="javascript:void(0)" id="d1menuid<%=(i+1)%>" onclick="callCurrent('<%=(i+1)%>','<%=d1MenuList.length%>');callCenterPage(<%=String.valueOf(d1MenuList[i][0]) %>,'<%=String.valueOf(d1MenuList[i][2]) %>'); callDecision();">
												
											<%=String.valueOf(d1MenuList[i][1])%></a></li>
									<% }
											
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
								%>
									
							<% try {
												
								String[][] menuList = (String[][]) request.getAttribute("menuList");
								if (menuList != null && menuList.length > 0) {
								%>	<li class = "dropdown">
										<a href="javascript:void(0)" class="dropdown-toggle" data-toggle="dropdown" id="moduals">Modules <span class="caret"></span></a>
										  <ul class="dropdown-submenu" role="menu" >
											
											<% for (int i = 0; i < menuList.length; i++) { %>
												<li>
													<a href="javascript:void(0)" id="menuid<%=(i+1)%>" onclick="callCurrent('<%=(i+1)%>','<%=menuList.length%>');callCenterPage(<%=String.valueOf(menuList[i][0]) %>,'<%=String.valueOf(menuList[i][2]) %>');callSubMenu(<%=String.valueOf(menuList[i][0]) %>);">
														<%=String.valueOf(menuList[i][1])%>
														<%// System.out.println("------++++----++++-----" +String.valueOf(menuList[i][1]));%>
											 		</a>
												</li>
												
											<% } %>
										</ul>
									</li>
					
						</ul>
							
					</nav><div class="thought1" style="margin-top:8px;">
								<div><strong>Thought	of the day:</strong> 
								<span><%=request.getAttribute("thought")%></span></div>
							</div>
				</div>
											
							<% }

						} catch (Exception e) {
							e.printStackTrace();
							} %>
									
	<script>
		function setSearchFocus(){
			if(window.event && window.event.keyCode == 13){
				getSearchData();
			}
		}
	</script>
						
								   
			
			<div id="clientNameDtls" style="background-color: #6979ac; float: left; width: 100%; margin: 0; padding: 0;">
			 
				<%
				//check for menu list Empty or not
				if (String.valueOf(hiddenFrame).equals("true")) {
			%>
				
				<div class="mainheader" style="height:25px; align:right;">
					<%
						String CandName="";
						try{
						
							CandName=(String)request.getAttribute("UserName");
						}catch(Exception e) {
							
							CandName="";
						}
					%> <b> &nbsp;Welcome <%=CandName %> !</b>
				</div>  
				
				<div class="mainheader" style="height:25px; align:right;" >   
					
					<a  title="Change Password" onclick="callLink('<%=request.getContextPath()%>/cr/D1AnalyticHome_changePassJsp.action');" 
						href="#" class="mainheader" ><b><font color="blue">Change Password</font></b></a>&nbsp;|&nbsp;
					
					
					<a  title="Logout"
						href="javascript:void(0)"
								title="Logout" onclick="logout();"
				target="_top" class="mainheader" ><b><font color="white">Logout&nbsp;&nbsp;&nbsp;</font></b></a>
			   </div>
			<%	} else {	%>	
			
					<div class="mainheader" height="25px"></div>
				
				<% }//end icol	%>
			</div>
			<div>
			<%
				String homePage = "";
				if (session.getAttribute("homePage") != null)
					homePage = String.valueOf(session.getAttribute("homePage"));
				else
					homePage = "/common/HomePage_decisionHome.action";
			%>
					
							<% //check for menu list Empty or not
								if (String.valueOf(hiddenFrame).equals("true")) {
							%>
						
							
							<div valign="top"><iframe id="myframe"
								frameborder="0"
								style="vertical-align: top; float: center; border: 0px solid;"
								src="<%=request.getContextPath()+homePage %> "
								scrolling="auto" marginwidth="0" marginheight="0" 
								name="main" width="98%"> </iframe></div>
							<%
							} else {
							%>
							<div>
								<iframe	style="vertical-align: top; float: none; border: 0px solid;float:top;"
								name="contents" scrolling="no" marginwidth="0" height="0px"
								id="leftFrame" width="0" marginheight="0" frameborder="0"> </iframe> 
								
								<iframe src="<%=request.getContextPath()%>/pages/common/hiddenFrame.jsp"
								name="hiddenFrame" scrolling="no" marginwidth="0"
								marginheight="0" frameborder="0"  width="0" height="0"></iframe>
							</div>	
							<div class="row" style=" align:center;">
								<iframe id="myframe" frameborder="0" style="vertical-align: top; float: none; border: 0px solid; height: 600px;"
								src="<%=request.getContextPath()+homePage%>"
								scrolling="auto" marginwidth="0" marginheight="0" 
								name="main" width="100%"> </iframe>
							</div>
						<% }//end icol%>
					
			</div>
	</div>
	
</s:form>
</body>
</html>

<script>
onload();

function onload(){
	var isclient = document.getElementById('isClientFlag').value; 
	if(isclient=="true"){
		document.getElementById('menu').style.display="none";
	}else{
		document.getElementById('menu').style.display="true";
	}
}

function alertsize(pixels){
	
	
	try
	{
	document.body.style.offsetHeight="0px";
    pixels+=30;
 	//alert('before'+ document.getElementById('myframe').style.height);
    document.getElementById('myframe').style.height=pixels+"px";
   // alert('after'+ document.getElementById('myframe').style.height);
   }
   catch(e)
   {
   alert(e);
   }    
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
function callDecision(){

 document.getElementById('leftFrame').style.width='0px';
 document.getElementById('myframe').style.width='100%';
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
		menuLink='/common/HomePage_decisionHome.action';
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
	//alert(menuCode);
   if(menuCode=='3'||menuCode=='410' || menuCode=='15' || menuCode=='415'|| menuCode=='1' ||  menuCode=='409' || menuCode=='412' || menuCode=='1169'||menuCode=='5059'){
      //document.getElementById('paraFrm').target = "_self";
      if(menuCode=='3'){
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/common/PayrollHome_getPayrollMenu.action?menuType=PP&mymessage_random='+Math.random();
	  }
	  else if(menuCode=='410'){
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/common/HrmHome_getHrmMenu.action?menuType=HP&mymessage_random='+Math.random();
	  }
	  else if(menuCode=='15'){
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/common/LeaveHome_getLeaveAttendanceMenu.action?menuType=LAP&mymessage_random='+Math.random();
	  }
	  else if(menuCode=='1'){
	 	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/AdminHome_getAdminMenu.action?menuType=AP&mymessage_random='+Math.random();
	  }
	  else if(menuCode=='412'){
	 	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/PerformanceHome_getPasMenu.action?menuType=PAP&mymessage_random='+Math.random();
	  }
	  else if(menuCode=='415'){
	 	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/ConfigurationHome_getConfigureMenu.action?menuType=CC&mymessage_random='+Math.random();
	 }
	  else if(menuCode=='409'){
	 	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/RecruitmentHome_getRecruitmentMenu.action?menuType=RP&mymessage_random='+Math.random();
	  }
	  else if(menuCode=='1169'){
	 	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/HomePage_getDecisionOneMenu.action?menuType=D1A&mymessage_random='+Math.random();
	  }else if(menuCode=='5059'){
	 	document.getElementById('paraFrm').action='<%=request.getContextPath()%>/common/TrainingHome_getTrainingMenu.action?menuType=TC&mymessage_random='+Math.random();
	  }
	  document.getElementById('paraFrm').submit();
  	  document.getElementById('leftFrame').style.width='0px';
	  document.getElementById('myframe').style.width='100%';
   }else{  		
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
 }
 
function getSearchData(){
	try{
	   var searchType=document.getElementById('menuoremployee').value;
	   var searchText=document.getElementById('searchText').value;
	   var resultOfSearch=document.getElementById('dropdown').value;	
	   if(searchText=="" || searchText=='Search In'){
			document.getElementById('searchText').focus();
			alert("Please enter minimum 3 chars to search");
			return false;
	   }else{
			if(searchText.length<3){
				alert("Please enter minimum 3 chars to search");
				document.getElementById('searchText').focus();
				return false;
			}
	   }
	     document.getElementById('leftFrame').style.width='0px';
	   document.getElementById('myframe').style.width=window.screen.width;	 
	   document.getElementById('myframe').src="<%=request.getContextPath()%>/common/EmployeePortal_getHomeSearch.action?resultOfSearch="+resultOfSearch+"&searchType="+searchType+"&searchText="+searchText+"&random="+Math.random(); 
	}
	catch(e){
	}
 };

function callSettingPage(){
	try{
	      //document.getElementById('leftFrame').style.width='0px';
	      //document.getElementById('myframe').style.width='100%';
	      document.getElementById('myframe').src="<%=request.getContextPath()%>/common/ConfigurationHome_input.action";
		}
	catch(e){			 
	}
 }

setText();
function callHome(){
  try{	  
	 document.getElementById('leftFrame').style.width='0px';
	 document.getElementById('myframe').style.width='100%';
	 document.getElementById('myframe').src="<%=request.getContextPath()%>/common/HomePage_decisionHome.action";
  }catch(e){
  }
 }

function callMypageCRVal(){
  try{
	 document.getElementById('leftFrame').style.width='0px';
	 document.getElementById('myframe').style.width='100%';
	 document.getElementById('myframe').src="<%=request.getContextPath()%>/cr/PerformanceMetrics_input.action";
  }catch(e){
  }
 }

function callMypageDOVal(){
  try{			  
	 document.getElementById('leftFrame').style.width='0px';
	 document.getElementById('myframe').style.width='100%';
	 document.getElementById('myframe').src="<%=request.getContextPath()%>/common/HomePage_decisionHome.action";
  }catch(e){
  }
}

function callMypageWBTVal(){
  try{			  
	 document.getElementById('leftFrame').style.width='0px';
	 document.getElementById('myframe').style.width='100%';
	 document.getElementById('myframe').src="<%=request.getContextPath()%>/common/HomePage_decisionHome.action";
  }catch(e){
  }
}
	
function setText(){	
	if(document.getElementById('searchText').value == '' ){
		document.getElementById('searchText').value ='Search In'; 
		document.getElementsByName('searchText').value ='Search In';
		document.getElementById('searchText').style.color = '#ACACAC';
	}else if(document.getElementById('searchText').value=='Search In'){
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
		}
}
 
function callLink(action){
	// alert(action) ;
	document.getElementById('myframe').style.width='100%';
	document.getElementById('myframe').src=action+"?random_app="+Math.random();
} 
 
function logout(){
	try{alert("logout click");
		document.getElementById("paraFrm").target="_self";
		document.getElementById("paraFrm").action='<%=request.getContextPath()%>/common/Login_input.action';
		document.getElementById("paraFrm").submit();
	}catch(e){
	}
 }
</script>