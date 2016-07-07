<%@page import="java.util.*,java.io.*" %>

 <LINK rel=stylesheet type=text/css href="../pages/portal/sliding.css">
 
<%
  
String themeName="globe";
String fontName="Arial";
String font_Size="11";
try{
 themeName=(String)session.getAttribute("themeName");
 fontName=(String)session.getAttribute("fontName");
 font_Size=(String)session.getAttribute("fontSize");
 
 System.out.println(font_Size+"_______________________"+font_Size);
}catch(Exception e){
	themeName="peoplePower";
	fontName="Arial";
	font_Size="11";
	
}
if(themeName==null || themeName.equals("null") ||themeName.equals(null)){
	themeName="peoplePower";
	}
if(fontName==null || fontName.equals("null") ||fontName.equals(null)){
	fontName="Arial";
}
if(font_Size==null || font_Size.equals("null") ||font_Size.equals(null)){
	font_Size="11";
}
	Properties pDefault = new Properties();
	// file object to load that file
	File file; 
	// InputStream for loading that file in property object
	FileInputStream fin; 
	ResourceBundle bundle=ResourceBundle.getBundle("globalMessages");
	
	String path= bundle.getString("data_path");
	
	try {
		try {
			file = new File(path+"/Themes/"
					+ themeName+ ".properties");
			fin = new FileInputStream(file);
			// loading file in property object
			pDefault.load(fin);
		} catch (Exception e) {
			// exception caught if file is not found
			pDefault = null;
			System.out.println(themeName+ ".properties File not found");
		}
	
	} catch (Exception e) {
		e.printStackTrace();
	
	}
	
	
 %>
 
 <%
 
 String fontFamily=fontName;
 String fontSize=font_Size;
 %>
 <style type="text/css">
.headerDashlet {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	font-weight: bold;
	color: #000000;
	text-decoration: none;
}

.contlink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	text-decoration: none;
	color: #000000;
	font-weight: lighter;
}

.orange {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: #000000;
	text-decoration: none;
}

.blacklink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	font-weight: lighter;
	color: #000000;
	text-decoration: none;
}

.portalOnLink {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: #EF6D1E;
	cursor: hand;
	text-decoration: none;
}

TD {  
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;

}
 
 .eventheader {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: #000000;
	font-weight: bold;
	text-decoration: none;
}



.border {
 	border-top-width: 1px;
	border-right-width: 1px;
	border-bottom-width: 1px;
	border-left-width: 1px;
	border-top-style: none;
	border-right-style: none;
	border-bottom-style: solid;
	border-left-style: none;
	border-top-color: #FF9900;
	border-right-color: #FF9900;
	border-bottom-color: #FF9900;
	border-left-color: #FF9900;
	font-family: <%=fontFamily%>;
	font-size: <%=fontSize%>px;
}

 /*Sliding*/
#portalTab{
	float: left;
	width: 100%;
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
}

#portalTab ul {
	padding-left: 0px;
	list-style: none;
	float: left;
	clear: left;
	margin:0px;

}

#portalTab ul.a1 {
	padding-left: 3px;
	list-style: none;
	float: left;
	clear: left;
	margin:0px;
	width:100%;


}

#portalTab ul li {
	float: left;
	display: inline; /*For ignore double margin in IE6*/
	margin: 0 5px;
}

#portalTab ul.a1 li {
	float: left;
	display: inline; /*For ignore double margin in IE6*/
	margin: 0 5px;

}


#portalTab ul li a {
	text-decoration: underline;
	float:left;
	color: #243277;
	cursor: pointer;
	font:  11px/22px <%=fontFamily%>;
		
}

 

#portalTab ul.a1 li a {
	text-decoration: underline;
	float:left;
	color: #243277;
	cursor: pointer;
	font:  12px/25px <%=fontFamily%>;
	text-decoration:none;

	
		
}

#portalTab ul li a span {
	margin: 0 5px 0 -8px;
	padding: 1px 1px 1px 6px;
	position: relative; /*To fix IE6 problem (not displaying)*/
	float:left;
		text-decoration: underline;
}

#portalTab ul.a1 li a span {
	margin: 0 5px 0 -8px;
	padding: 1px 1px 1px 6px;
	position: relative; /*To fix IE6 problem (not displaying)*/
	float:left;
		text-decoration: none;
		
}

/*GREEN*/


/*RED*/
#portalTab ul.red li a:hover {
 	color: #fff;
	background: url(<%=request.getContextPath()%>/pages/portal/images/red.png) no-repeat top right;

}

#portalTab ul.red1 li a:hover {
 	color: #fff;
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top right;

}



#portalTab ul.red li a:hover span {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top left;
	text-decoration: none;
		
}

#portalTab ul.red1 li a:hover span {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top left;
	text-decoration: none;
	 
	
}

#portalTab ul.red li a.current {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top right;
 	color: #fff;
	text-decoration: none;
}

#portalTab ul.red1 li a.current {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top right;
 	color: #fff;
	text-decoration: none;
}


#portalTab ul.red li a.current span {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top left;
		text-decoration: none;
		
}


#portalTab ul.red1 li a.current span {
	background: url(<%=request.getContextPath()%>/pages/portal/images/red1.png) no-repeat top left;
		text-decoration: none;
		
}


	

</style>

<%
	String categoryCode = "1";
	try {
		categoryCode = request.getParameter("categoryCode");
	} catch (Exception e) {
		
	}
%>

 

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td colspan="2">&nbsp;</td>

	</tr>

	<tr>
		<td>
		<%
		if (categoryCode.equals("1")) {
		%>
		<table id="Table_01" width="95%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="3%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_01.gif"
					width="11" height="11" alt="" /></td>
				<td width="93%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_02.gif"
					width="100%" height="11" alt="" /></td>
				<td width="4%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_03.gif"
					width="12" height="11" alt="" /></td>
			</tr>
			<tr>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"
					width="11" height="200" alt="" /></td>
				<td valign="top">
				<table width="80%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>
						<div align="center"><span class="headerDashlet">Glofest</span><br />
						<span class="orange"><strong>Celebrating Glodyne
						Spirit!!</strong></span></div>
						</td>
					</tr>
					<tr>
						<td height="22" class="txt"></td>
					</tr>
					<tr>
						<td height="18" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/aboutglogest.jsp?categoryCode=1"
							class="contlink" target="_self">About Glofest </a></td>
					</tr>
					<tr>
						<td height="18" class="txt"><a
							href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=1"
							class="contlink">Events</a></td>
					</tr>
					<tr>
						<td height="18" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/management_team.jsp?categoryCode=1"
							class="contlink">Management Team</a></td>
					</tr>
					<tr>
						<td height="18" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/feedbackForm.jsp?categoryCode=1"
							class="contlink">Feedback</a></td>
					</tr>

					<tr>
						<td height="18"><span class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/contactus.jsp?categoryCode=1"
							class="contlink">Contact Us</a></span></td>
					</tr>
				</table>
				</td>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"
					width="12" height="200" alt="" /></td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_07.gif"
					width="11" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_08.gif"
					width="100%" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_09.gif"
					width="12" height="13" alt="" /></td>
			</tr>
		</table>
		<%
		}
		%> <%
 if (categoryCode.equals("2")) {
 %>


		<table id="Table_01" width="95%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="3%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_01.gif"
					width="11" height="11" alt="" /></td>
				<td width="93%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_02.gif"
					width="100%" height="11" alt="" /></td>
				<td width="4%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_03.gif"
					width="12" height="11" alt="" /></td>
			</tr>
			<tr>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"
					width="11" height="200" alt="" /></td>
				<td valign="top">
				<table width="80%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>
						<div align="center"><span class="headerDashlet">GCL</span><br />
						<span class="orange"><strong>Glodyne Cricket
						League</strong></span></div>
						</td>
					</tr>
					<tr>
						<td height="22" class="txt"></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/aboutgcl.jsp?categoryCode=2"
							class="contlink">About GCL</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=2"
							class="contlink">Events</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/schedule.jsp?categoryCode=2"
							class="contlink">Schedule</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/management_team_gcl.jsp?categoryCode=2"
							class="contlink">Management Team</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/feedbackForm.jsp?categoryCode=2"
							class="contlink">Feedback</a></td>
					</tr>
					<tr>
						<td height="22"><span class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/contactus_gcl.jsp?categoryCode=2"
							class="contlink">Contact Us</a></span></td>
					</tr>
				</table>
				</td>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"
					width="12" height="200" alt="" /></td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_07.gif"
					width="11" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_08.gif"
					width="100%" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_09.gif"
					width="12" height="13" alt="" /></td>
			</tr>
		</table>
		<%
		}
		%> <%
 if (categoryCode.equals("3")) {
 %>


		<table id="Table_01" width="95%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="3%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_01.gif"
					width="11" height="11" alt="" /></td>
				<td width="93%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_02.gif"
					width="100%" height="11" alt="" /></td>
				<td width="4%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_03.gif"
					width="12" height="11" alt="" /></td>
			</tr>
			<tr>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"
					width="11" height="200" alt="" /></td>
				<td valign="top">
				<table width="80%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td height="22">
						<div align="center" class="headerDashlet">
						<div align="left">Glodyne Care</div>
						</div>
						</td>
					</tr>
					<tr>
						<td height="22" class="txt"></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/aboutcare.jsp?categoryCode=3"
							class="contlink">About Glodyne Care </a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=3"
							class="contlink">Events</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/teaserMailers.jsp?categoryCode=3"
							class="contlink">Teaser Mailers</a></td>
					</tr>
					
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/1to1help.jsp?categoryCode=3"
							class="contlink">About 1 TO 1 Help</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/feedbackForm.jsp?categoryCode=3"
							class="contlink">Feedback</a></td>
					</tr>
					<tr>
						<td height="22"><span class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/contactus_care.jsp?categoryCode=3"
							class="contlink">Contact Us</a></span></td>
					</tr>
				</table>
				</td>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"
					width="12" height="200" alt="" /></td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_07.gif"
					width="11" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_08.gif"
					width="100%" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_09.gif"
					width="12" height="13" alt="" /></td>
			</tr>
		</table>

		<%
		}
		%> <%
 if (categoryCode.equals("4")) {
 %>


		<table id="Table_01" width="95%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="3%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_01.gif"
					width="11" height="11" alt="" /></td>
				<td width="93%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_02.gif"
					width="100%" height="11" alt="" /></td>
				<td width="4%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_03.gif"
					width="12" height="11" alt="" /></td>
			</tr>
			<tr>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"
					width="11" height="200" alt="" /></td>
				<td valign="top">
				<table width="80%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><span class="headerDashlet">My Idea </span></td>
					</tr>
					<tr>
						<td height="22" class="txt"></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/myidea.jsp?categoryCode=4"
							class="contlink">About My Idea </a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/bestidea.jsp?categoryCode=4"
							class="contlink">Best Idea of the month </a></td>
					</tr>

					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/bonuspoint.jsp?categoryCode=4"
							class="contlink">Bonus Point Table </a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/policy.jsp?categoryCode=4"
							class="contlink">My Idea Policy </a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/broucher.pdf"
							target="_blank" class="contlink">Broucher</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/management_idea.jsp?categoryCode=4"
							class="contlink">Management Team</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/feedbackForm.jsp?categoryCode=4"
							class="contlink">Feedback</a></td>
					</tr>
					<tr>
						<td height="22"><span class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/contact_idea.jsp?categoryCode=4"
							class="contlink">Contact Us</a></span></td>
					</tr>
				</table>
				</td>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"
					width="12" height="200" alt="" /></td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_07.gif"
					width="11" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_08.gif"
					width="100%" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_09.gif"
					width="12" height="13" alt="" /></td>
			</tr>
		</table>


		<%
		}
		%> <%
 if (categoryCode.equals("5")) {
 %>


		<table id="Table_01" width="95%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="3%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_01.gif"
					width="11" height="11" alt="" /></td>
				<td width="93%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_02.gif"
					width="100%" height="11" alt="" /></td>
				<td width="4%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_03.gif"
					width="12" height="11" alt="" /></td>
			</tr>
			<tr>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"
					width="11" height="200" alt="" /></td>
				<td valign="top">
				<table width="80%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><span class="headerDashlet">Glodyne Connexion </span></td>
					</tr>
					<tr>
						<td height="22" class="txt"></td>
					</tr>

					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/aboutconnexions.jsp?categoryCode=5"
							class="contlink">About Connexion</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=5"
							class="contlink">Events</a></td>
					</tr>

					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/management_team_conn.jsp?categoryCode=5"
							class="contlink">Management Team</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/feedbackForm.jsp?categoryCode=5"
							class="contlink">Feedback</a></td>
					</tr>

					<tr>
						<td height="22"><span class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/contact_conn.jsp?categoryCode=5"
							class="contlink">Contact Us</a></span></td>
					</tr>
				</table>
				</td>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"
					width="12" height="200" alt="" /></td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_07.gif"
					width="11" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_08.gif"
					width="100%" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_09.gif"
					width="12" height="13" alt="" /></td>
			</tr>
		</table>

		<%
		}
		%> <%
 if (categoryCode.equals("6")) {
 %>


		<table id="Table_01" width="95%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="3%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_01.gif"
					width="11" height="11" alt="" /></td>
				<td width="93%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_02.gif"
					width="100%" height="11" alt="" /></td>
				<td width="4%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_03.gif"
					width="12" height="11" alt="" /></td>
			</tr>
			<tr>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"
					width="11" height="200" alt="" /></td>
				<td valign="top">
				<table width="80%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><span class="headerDashlet">Rewards </span></td>
					</tr>
					<tr>
						<td height="22" class="txt"></td>
					</tr>

					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/aboutRewards.jsp?categoryCode=6"
							class="contlink">About Rewards</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/portal/EventData_getRewardData.action?categoryCode=6"
							class="contlink">Rewards</a></td>
					</tr>

					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/management_team_rewards.jsp?categoryCode=6"
							class="contlink">Management Team</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/feedbackForm.jsp?categoryCode=6"
							class="contlink">Feedback</a></td>
					</tr>


					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/contactRewards.jsp?categoryCode=6"
							class="contlink">Contact Us</a></td>
					</tr>

				</table>
				</td>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"
					width="12" height="200" alt="" /></td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_07.gif"
					width="11" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_08.gif"
					width="100%" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_09.gif"
					width="12" height="13" alt="" /></td>
			</tr>
		</table>

		<%
		}
		%> <%
 if (categoryCode.equals("7")) {
 %>


		<table id="Table_01" width="95%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="3%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_01.gif"
					width="11" height="11" alt="" /></td>
				<td width="93%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_02.gif"
					width="100%" height="11" alt="" /></td>
				<td width="4%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_03.gif"
					width="12" height="11" alt="" /></td>
			</tr>
			<tr>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"
					width="11" height="200" alt="" /></td>
				<td valign="top">
				<table width="80%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><span class="headerDashlet">Color Band </span></td>
					</tr>
					<tr>
						<td height="22" class="txt"></td>
					</tr>
					<tr>
						<td height="20" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/employeeband.jsp?categoryCode=7"
							class="contlink">Employee Band </a></td>
					</tr>
					<tr>
						<td height="20" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/feedbackForm.jsp?categoryCode=7"
							class="contlink">Feedback</a></td>
					</tr>

				</table>
				</td>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"
					width="12" height="200" alt="" /></td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_07.gif"
					width="11" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_08.gif"
					width="100%" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_09.gif"
					width="12" height="13" alt="" /></td>
			</tr>
		</table>

		<%
		}
		%> <%
 if (categoryCode.equals("8")) {
 %>


		<table id="Table_01" width="95%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="3%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_01.gif"
					width="11" height="11" alt="" /></td>
				<td width="93%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_02.gif"
					width="100%" height="11" alt="" /></td>
				<td width="4%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_03.gif"
					width="12" height="11" alt="" /></td>
			</tr>
			<tr>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"
					width="11" height="200" alt="" /></td>
				<td valign="top">
				<table width="80%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><span class="headerDashlet">Help Desk </span></td>
					</tr>
					<tr>
						<td height="22" class="txt"></td>
					</tr>

					<tr>
						<td height="20" class="txt"><a
							href="<%=request.getContextPath()%>/portal/EventData_getHelpDeskSection.action?categoryCode=8"
							class="contlink">Post a ticket </a></td>
					</tr>
					<tr>
						<td height="20" class="txt"><a
							href="<%=request.getContextPath()%>/portal/EventData_getHelpDeskApproval.action?categoryCode=8"
							class="contlink">Approve tickets</a></td>
					</tr>
					<tr>
						<td height="20" class="txt"><a
							href="<%=request.getContextPath()%>/portal/EventData_getHelpDeskConsole.action?categoryCode=8"
							class="contlink">Helpdesk Console</a></td>
					</tr>



				</table>
				</td>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"
					width="12" height="200" alt="" /></td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_07.gif"
					width="11" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_08.gif"
					width="100%" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_09.gif"
					width="12" height="13" alt="" /></td>
			</tr>
		</table>

		<%
		}
		%> <%
 if (categoryCode.equals("9")) {
 %>


		<table id="Table_01" width="95%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="3%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_01.gif"
					width="11" height="11" alt="" /></td>
				<td width="93%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_02.gif"
					width="100%" height="11" alt="" /></td>
				<td width="4%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_03.gif"
					width="12" height="11" alt="" /></td>
			</tr>
			<tr>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"
					width="11" height="200" alt="" /></td>
				<td valign="top">
				<table width="80%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><span class="headerDashlet">Induction </span></td>
					</tr>
					<tr>
						<td height="22" class="txt"></td>
					</tr>
					<tr>
						<td height="22" class="txt" nowrap="nowrap"><a
							href="<%=request.getContextPath()%>/pages/portal/induction.jsp?categoryCode=9"
							class="contlink">Induction Program</a></td>
					</tr>


				</table>
				</td>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"
					width="12" height="200" alt="" /></td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_07.gif"
					width="11" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_08.gif"
					width="100%" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_09.gif"
					width="12" height="13" alt="" /></td>
			</tr>
		</table>


		<%
		}
		%> <%
 if (categoryCode.equals("10")) {
 %>


		<table id="Table_01" width="95%" border="0" cellpadding="0"
			cellspacing="0">
			<tr>
				<td width="3%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_01.gif"
					width="11" height="11" alt="" /></td>
				<td width="93%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_02.gif"
					width="100%" height="11" alt="" /></td>
				<td width="4%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_03.gif"
					width="12" height="11" alt="" /></td>
			</tr>
			<tr>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"
					width="11" height="200" alt="" /></td>
				<td valign="top">
				<table width="80%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td><span class="headerDashlet">Tips</span></td>
					</tr>
					<tr>
						<td height="22" class="txt"></td>
					</tr>

					<tr>
						<td height="22" class="txt" nowrap="nowrap"><a
							href="<%=request.getContextPath()%>/pages/portal/knowledfeForum1.jsp?categoryCode=10"
							class="contlink">Business Communication Etiquette</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/knowledfeForum2.jsp?categoryCode=10"
							class="contlink">Office Etiquette</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/knowledfeForum3.jsp?categoryCode=10"
							class="contlink">Stress Management at Workplace</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/knowledfeForum4.jsp?categoryCode=10"
							class="contlink">Time Management</a></td>
					</tr>
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/pages/portal/knowledfeForum5.jsp?categoryCode=10"
							class="contlink">Workplace Etiquette</a></td>
					</tr>
					
					<tr>
						<td height="22" class="txt"><a
							href="<%=request.getContextPath()%>/portal/TipsSetting_homePageTips.action?categoryCode=10"
							class="contlink">Tips for you</a></td>
					</tr>

					


				</table>
				</td>
				<td
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"
					width="12" height="200" alt="" /></td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_07.gif"
					width="11" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_08.gif"
					width="100%" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_09.gif"
					width="12" height="13" alt="" /></td>
			</tr>
		</table>

		<%
		}
		%>
		</td>
	</tr>


	<tr>
		<td>
		<table id="Table_01" width="95%" height="194" border="0"
			cellpadding="0" cellspacing="0">
			<tr>
				<td width="3%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_01.gif"
					width="11" height="11" alt="" /></td>
				<td width="93%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_02.gif"
					width="100%" height="11" alt="" /></td>
				<td width="4%"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_03.gif"
					width="12" height="11" alt="" /></td>
			</tr>
			<tr>
				<td height="170" valign="top"
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_04.gif"
					width="11" height="10" alt="" /></td>
				<td valign="top">
				<table width="97%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td height="30" colspan="2" class="headerDashlet">Glodyne
						Initiatives</td>
					</tr>
					<tr>
						<td height="22" class="txt"></td>
					</tr>
					<tr>
						<td width="14%" height="18" class="orange">
						<div align="center"><img
							src="<%=request.getContextPath()%>/pages/portal/images/glofesticon.gif"
							width="10" height="17" /></div>
						</td>
						<td width="86%" height="24"><a
							href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=1"
							class="blacklink">Glofest</a></td>
					</tr>
					<tr>
						<td height="18">
						<div align="center"><img
							src="<%=request.getContextPath()%>/pages/portal/images/gclicon.gif"
							width="11" height="17" /></div>
						</td>
						<td height="24"><a
							href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=2"
							class="blacklink">Glodyne Cricket League </a></td>
					</tr>
					<tr>
						<td height="18">
						<div align="center"><img
							src="<%=request.getContextPath()%>/pages/portal/images/careicon.gif"
							width="17" height="15" /></div>
						</td>
						<td height="24"><a
							href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=3"
							class="blacklink">Glodyne Care </a></td>
					</tr>
					<tr>
						<td height="18">
						<div align="center"><img
							src="<%=request.getContextPath()%>/pages/portal/images/ideaicon.gif"
							width="17" height="17" /></div>
						</td>
						<td height="24"><a
							href="<%=request.getContextPath()%>/pages/portal/myidea.jsp?categoryCode=4"
							class="blacklink">My Idea </a></td>
					</tr>
					<tr>
						<td height="18">
						<div align="center"><img
							src="<%=request.getContextPath()%>/pages/portal/images/connexicon.gif"
							width="18" height="17" /></div>
						</td>
						<td height="24"><a
							href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=5"
							class="blacklink">Connexions</a></td>
					</tr>
					<tr>
						<td height="18">
						<div align="center"><img
							src="<%=request.getContextPath()%>/pages/portal/images/rewardicon.gif"
							width="15" height="14" /></div>
						</td>
						<td height="24"><a
							href="<%=request.getContextPath()%>/portal/EventData_getRewardData.action?categoryCode=6"
							class="blacklink">Rewards</a></td>
					</tr>
					<tr>
						<td height="18">
						<div align="center"><img
							src="<%=request.getContextPath()%>/pages/portal/images/bandicon.gif"
							width="13" height="17" /></div>
						</td>
						<td height="24"><a
							href="<%=request.getContextPath()%>/pages/portal/employeeband.jsp?categoryCode=7"
							class="blacklink">Employee Band </a></td>
					</tr>
					<tr>
						<td height="18">
						<div align="center"><img
							src="<%=request.getContextPath()%>/pages/portal/images/helpicon.gif"
							width="16" height="15" /></div>
						</td>
						<td height="24"><a
							href="<%=request.getContextPath()%>/portal/EventData_getHelpDeskSection.action?categoryCode=8"
							class="blacklink">Helpdesk </a></td>
					</tr>
					<tr>
						<td height="15">
						<div align="center"><img
							src="<%=request.getContextPath()%>/pages/portal/images/indcuicon.gif"
							width="19" height="15" /></div>
						</td>
						<td height="24"><a
							href="<%=request.getContextPath()%>/pages/portal/induction.jsp?categoryCode=9"
							class="blacklink">Induction</a></td>
					</tr>
					<tr>
						<td height="18">
						<div align="center"><img
							src="<%=request.getContextPath()%>/pages/portal/images/tipsicon.gif"
							width="18" height="17" /></div>
						</td>
						<td height="24"><a
							href="<%=request.getContextPath()%>/pages/portal/knoledge.jsp?categoryCode=10"
							class="blacklink">Tips </a></td>
					</tr>
				</table>
				</td>
				<td valign="top"
					background="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_06.gif"
					width="12" height="132" alt="" /></td>
			</tr>
			<tr>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_07.gif"
					width="11" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_08.gif"
					width="100%" height="13" alt="" /></td>
				<td><img
					src="<%=request.getContextPath()%>/pages/portal/images/lefttab_09.gif"
					width="12" height="13" alt="" /></td>
			</tr>
		</table>
		</td>
	</tr>
</table>