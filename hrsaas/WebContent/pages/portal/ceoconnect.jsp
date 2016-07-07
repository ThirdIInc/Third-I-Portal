
<%@ taglib prefix="s" uri="/struts-tags"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Glodyne Employee Portal</title>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/pages/common/Ajax.js"></script>

<%
  
String themeName="globe";
String fontName="Arial";
String font_Size="11";

String fontFamily=fontName;
String fontSize=font_Size;
String aLink= "#000000";	
 %>
 <style type="text/css">
 
 
 A.contlink:link {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: none;
	font-weight: lighter;
}

A.contlink:visited  {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: none;
	font-weight: lighter;
}

A.contlink:active {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: none;
	font-weight: lighter;
}

A.contlink:hover {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: underline;
	font-weight: lighter;
}


 
.headerDashlet {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	font-weight: bold;
	color: #000000;
	text-decoration: none;
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
 
 

</style>



 

	<table width="910" border="0" align="left" cellpadding="0"  height="100%"
		cellspacing="0">
		<s:form action="EmployeePortal" id="paraFrm" theme="simple"
	name="employeePortalForm">
		<%
			String loginEmpCode = request.getParameter("loginEmpCode");
			String isceolink = request.getParameter("isceolink");
		%>

		<input type="hidden" name="loginEmpCode" value="<%=loginEmpCode %>"
			id="loginEmpCode" />
		<input type="hidden" name="isceolink" id="isceolink"
			value="<%=isceolink %>" />
		<tr>
			<td width="43%">&nbsp;</td>
			<td width="57%">&nbsp;</td>
		</tr>
		<tr>
			<td valign="top">
			<table cellpadding="0" cellspacing="0">

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
									<td width="86%" height="24"><a style="cursor: hand;"
										href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=1"
										class="blacklink">Glofest</a></td>
								</tr>
								<tr>
									<td height="18">
									<div align="center"><img
										src="<%=request.getContextPath()%>/pages/portal/images/gclicon.gif"
										width="11" height="17" /></div>
									</td>
									<td height="24"><a style="cursor: hand;"
										href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=2"
										class="blacklink">Glodyne Cricket League </a></td>
								</tr>
								<tr>
									<td height="18">
									<div align="center"><img
										src="<%=request.getContextPath()%>/pages/portal/images/careicon.gif"
										width="17" height="15" /></div>
									</td>
									<td height="24"><a style="cursor: hand;"
										href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=3"
										class="blacklink">Glodyne Care </a></td>
								</tr>
								<tr>
									<td height="18">
									<div align="center"><img
										src="<%=request.getContextPath()%>/pages/portal/images/ideaicon.gif"
										width="17" height="17" /></div>
									</td>
									<td height="24"><a style="cursor: hand;"
										href="javascript:void(0);"
										onClick="callPage('<%=request.getContextPath()%>/pages/portal/myidea.jsp?categoryCode=4');"
										class="blacklink">My Idea </a></td>
								</tr>
								<tr>
									<td height="18">
									<div align="center"><img
										src="<%=request.getContextPath()%>/pages/portal/images/connexicon.gif"
										width="18" height="17" /></div>
									</td>
									<td height="24"><a style="cursor: hand;"
										href="<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=5"
										class="blacklink">Connexions</a></td>
								</tr>
								<tr>
									<td height="18">
									<div align="center"><img
										src="<%=request.getContextPath()%>/pages/portal/images/rewardicon.gif"
										width="15" height="14" /></div>
									</td>
									<td height="24"><a style="cursor: hand;"
									onClick="callPage('<%=request.getContextPath()%>/portal/EventData_getRewardData.action?categoryCode=6');"
										class="blacklink">Rewards</a></td>
								</tr>
								<tr>
									<td height="18">
									<div align="center"><img
										src="<%=request.getContextPath()%>/pages/portal/images/bandicon.gif"
										width="13" height="17" /></div>
									</td>
									<td height="24"><a style="cursor: hand;"
											onClick="callPage('<%=request.getContextPath()%>/pages/portal/employeeband.jsp?categoryCode=7');"
										class="blacklink">Employee Band </a></td>
								</tr>
								<tr>
									<td height="18">
									<div align="center"><img
										src="<%=request.getContextPath()%>/pages/portal/images/helpicon.gif"
										width="16" height="15" /></div>
									</td>
									<td height="24"><a style="cursor: hand;"
												onClick="callPage('<%=request.getContextPath()%>/help/HelpDesk_input.action');"
										class="blacklink">Helpdesk </a></td>
								</tr>
								<tr>
									<td height="15">
									<div align="center"><img
										src="<%=request.getContextPath()%>/pages/portal/images/indcuicon.gif"
										width="19" height="15" /></div>
									</td>
									<td height="24"><a style="cursor: hand;"
									onClick="callPage('<%=request.getContextPath()%>/pages/portal/induction.jsp?categoryCode=9');"
										class="blacklink">Induction</a></td>
								</tr>
								<tr>
									<td height="18">
									<div align="center"><img
										src="<%=request.getContextPath()%>/pages/portal/images/tipsicon.gif"
										width="18" height="17" /></div>
									</td>
									<td height="24"><a style="cursor: hand;"
										onClick="callPage('<%=request.getContextPath()%>/pages/portal/knoledge.jsp?categoryCode=10');"
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

			</td>
			<td valign="top">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><a href="javascript:void(0);"><img
						src="images/banner.gif" alt="Banner Advertisement" border="0"
						hspace="0" name="bannerad" WIDTH="669" HEIGHT="91"></td>
				</tr>

				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td height="20">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><a href="javascript:void(0);" onclick="callMessage();"
								class="contlink">Message from CEO Desk</a></td>
							<td>
							<div align="right"><a href="javascript:void(0);"
								onclick="callConnectCeo()" class="contlink">Click here to
							Connect our CEO</a></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td>
					<table width="669" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="43%"><img src="images/sir.jpg" width="669"
								height="280" /></td>
							<td>&nbsp;</td>
						</tr>
						<table id="paraId" style="display: ;" cellpadding="0"
							cellspacing="0" border="0">
							<tr>
								<td colspan="2">
								<p class="orange"><strong> Hello Glodyners!</strong></p>
								<p align="justify"><span class="blacklink">Today, we
								complete our first teen year, yes we complete thirteen years of
								our journey, we complete thirteen years of our dream. It&rsquo;s
								such a fantastic feeling to be growing, learning, evolving and
								through our services impacting lives. I would like to take this
								opportunity to thank each one of you who are part of the Glodyne
								dream. We have a long way to go, and I look forward to moving
								ahead in our quest together shoulder to shoulder. </span><br />
								<br />
								<span class="blacklink">We are making relentless efforts
								to realise our vision and our success till date is attributed to
								our unique business model, sharp organizational focus
								and&nbsp;the strength of our people. </span></p>
								</td>
							</tr>
							<tr>
								<td colspan="2">
								<p align="justify" class="blacklink">In India, we have
								leveraged on our expertise and bundled our IMS offerings to
								create a whole new market for transformational IMS services. We
								identified opportunities and have built innovative IMS
								offerings, which has significantly increased the value
								proposition of our customers. The progress so far has been very
								satisfying. We have built strong capabilities and expertise in
								the Indian market and also have the strategy in place to capture
								substantial share of the North American IMS market where our
								presence has significantly expanded with the DecisionOne
								acquisition. We need to continue the focus and build our
								competencies, monitor the marketing environment to excel in the
								ever changing business.</p>
								<p align="justify"><span class="style1">At this
								point, I would like to share a story, which truly inspires me</span><span
									class="blacklink">. In 1883; a creative engineer named
								John Roebling was inspired to build a spectacular bridge
								connecting New York with the Long Island. However experts
								throughout the world thought that this was an impossible feat
								and told Roebling to forget the idea. But he didn&rsquo;t give
								up and he managed to convince his son Washington who was an
								engineer, that the bridge could be built.<br />
								<br />
								Working together, they developed concepts and with great
								excitement and inspiration, they began to build their dream
								bridge. The project started well, but when it was only a few
								months underway a tragic accident on the site took the life of
								John Roebling and injured Washington, which resulted in him not
								being able to walk or talk or even move.<br />
								Everyone felt that the project should be scrapped. In spite of
								his handicap Washington was never discouraged and he tried to
								inspire and pass on his enthusiasm to some of his friends, but
								they were too daunted by the task. As he lay on his bed in his
								hospital room, with the sunlight streaming through the windows,
								a gentle breeze blew the flimsy white curtains apart and he was
								able to see the sky and the tops of the trees outside for just a
								moment.<br />
								<br />
								It seemed that there was a message for him not to give up.
								Suddenly an idea hit him. All he could do was move one finger
								and he decided to make the best use of it. By moving this, he
								slowly developed a code of communication with his wife.<br />
								He touched his wife&rsquo;s arm with that finger, indicating to
								her that he wanted her to call the engineers again. Then he used
								the same method of tapping her arm to tell the engineers what to
								do. It seemed foolish but the project was under way again.<br />
								<br />
								For 13 years Washington tapped out his instructions with his
								finger on his wife&rsquo;s arm, until the bridge was finally
								completed. Today the </span><span class="style1">spectacular
								Brooklyn Bridge stands in all its glory as a tribute to the
								triumph of one man&rsquo;s indomitable spirit and his
								determination not to be defeated by circumstances</span><span
									class="blacklink"><strong>. </strong>The Brooklyn Bridge
								shows us that dreams that seem impossible can be realized with
								determination and persistence, no matter what the odds are.</span></p>
								<p align="justify"><span class="blacklink">What one
								requires is a vision, a dream to make a difference. I want each
								one of you to have dreams and when you do, feel free to express
								them. Don&rsquo;t fear criticism as all great ideas are
								initially mocked. Just keep striving and polish your skills, so
								you can achieve your goals.</span><br />
								<br />
								<span class="blacklink">&nbsp;Let us all join hands to
								make our company a dream company, to make it world&rsquo;s most
								respected IMS Company, because I believe that <strong>even
								the most distant dream can be realized with determination and
								persistence. </strong></span><br />
								<br />
								<span class="blacklink">The best ideas often don&rsquo;t
								just come out of strategic meeting but from employees who are
								day-in and day out interacting with the customers. Hence, I
								would like to know your thoughts and your ideas on how we can
								make a difference. If you have any queries, any ideas, any
								feedback do feel free to write to me directly at </span><a
									href="mailto:prem1977@gmail.com">prem1977@gmail.com</a>.
								<br />
								<br />
								<span class="blacklink">Wishing you a very happy Glodyne
								Foundation Day, keep spreading joy and celebrating the Glodyne
								spirit. <br />
								<br />
								Thank You.<br />
								</span><span class="style1">Annand Sarnaaik</span></p>
								<p align="justify" class="blacklink">&nbsp;</p>
								</td>
							</tr>
						</table>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td></td>
						</tr>

						<table width="100%"  id="div_Id" style="display: none;vertical-align: top;">


							<tr>
								<td colspan="3">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">
									<tr>
										<td>

										<table width="100%" border="0" align="center" cellpadding="0"
											cellspacing="0">


											<tr>
												<td nowrap="nowrap" class="contlink">Subject<font
													color="red">*</font> :</td>
												<td><s:textfield name="subject" theme="simple"
													id="subject" size="78" maxlength="40"
													onkeypress="return allCharacters();" /></td>
											</tr>
											<tr>
												<td colspan="2">&nbsp;</td>
											</tr>

											<tr>
												<td nowrap="nowrap" class="contlink">Description<font
													color="red">*</font> :</td>
												<td><s:textarea name="description" theme="simple"
													id="description"  rows="5" cols="60" /></td>
											</tr>
											<tr>
												<td nowrap="nowrap" class="contlink">Hide my identity:</td>
												<td><s:checkbox name="hideIdentity" id="hideIdentity"
													theme="simple"></s:checkbox></td>
											</tr>


										</table>
										</td>
									</tr>


								</table>
								</td>
							</tr>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td colspan="3">
								<table width="100%" border="0" cellpadding="0" cellspacing="0">



									<tr>
										<td colspan="4" align="center"><input type="button"
											class="blacklink" id="ctrlShow" value="Submit"
											onclick="callSubmit();" /> <input type="reset" id="ctrlShow"
											value="Clear" class="blacklink" /></td>

										<td width="22%"></td>
									</tr>


									</tr>

								</table>

								<label></label></td>
							</tr>
							<tr>
								<td height="5"></td>
							</tr>

						</table>


						<!--
						<table width="100%" id="div_Id" style="display: none;" class="formbg">
					 
					<tr>
								<td width="100%">
								<table width="100%">
									<tr>
										<td nowrap="nowrap">Subject: <font color="red">*</font></td>
										<td><s:textfield name="subject" theme="simple" size="70"
											maxlength="40" onkeypress="return allCharacters();" /></td>
									</tr>

									<tr>
										<td nowrap="nowrap">Description: <font color="red">*</font></td>
										<td><s:textarea name="description" theme="simple"
											cols="72" rows="5" /></td>
									</tr>

									<tr>
										<td nowrap="nowrap">Hide my identity:</td>
										<td><input type="checkbox" name="hideIdentity"
											id="hideIdentity" /></td>
									</tr>


								</table>
								</td>
							</tr>
							<tr>
								<td width="100%">
								<table width="100%">
									<tr>
										<td width="50%" align="right"><input type="button"
											id="ctrlShow" value="Submit" class="token"
											onclick="callSubmit();" />
											<input type="reset"
											id="ctrlShow" value="Clear" class="token"
											 />
											</td>
										<td></td>
									</tr>
								</table>
								</td>
							</tr>
						</table>

					-->
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>
</s:form>
	</table>

 

</body>



<script>

  
	function callConnectCeo(id)
{
	//var wind = window.open(id,'_blank','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	
	
	try{
	document.getElementById('subject').value='';
		
	document.getElementById('description').value='';
	 	
	document.getElementById('hideIdentity').checked =false;
		
	document.getElementById('div_Id').style.display = ''; 
	
	document.getElementById('paraId').style.display = 'none'; 
	
	
	}
	catch(e)
	{
		alert(e);
	}
	
	
}
 
 
 function onload2()
 {
 	try{
 		var isceolink =document.getElementById('isceolink').value;
 		 
 	if(isceolink=='true')
 	{
 		document.getElementById('div_Id').style.display = ''; 
 		document.getElementById('paraId').style.display = 'none'; 
	
 	}	
 	else
 	{
 		document.getElementById('div_Id').style.display = 'none';
 		document.getElementById('paraId').style.display = ''; 
	 
 	}
 	}
 	catch(e)
 	{
 		alert(e);
 	}
 
 }
 
	function callSubmit()
{
	try{
		var subject  = document.getElementById('subject').value;
		
		var description  = document.getElementById('description').value;
		
		var hideIdentity  = "";
	 
		if(document.getElementById('hideIdentity').checked)
		{
		hideIdentity='Y';
		}
		else{
		hideIdentity='N';
		}
		
		var loginEmpId =  document.getElementById('loginEmpCode').value;
		 
		
		if(subject == "")
		{
			alert("Please enter subject");
			return false ;
		}
		
		if(description == "")
		{
			alert("Please enter description");
			return false ;
		}
 		
		retrieveCeoConnect('<%=request.getContextPath()%>/portal/EmployeePortal_submitCeoDesc.action?random='+Math.random()+'&subject=' + document.getElementById('subject').value +'&description=' + description+'&hideIdentity='+hideIdentity+'&loginEmpId='+loginEmpId, 'employeePortalForm')
	 	 document.getElementById('div_Id').style.display = ''; 
	return true ;	
	}catch(e){ alert(e);}
	

}


function callMessage()
{
  	document.getElementById('div_Id').style.display = 'none'; 
	document.getElementById('paraId').style.display = '';
}


function callPage(actionName)
{ 

	//alert("in callpage");
	try{
	document.getElementById('paraFrm').action = actionName;
	document.getElementById('paraFrm').submit();
	}
	catch(e)
	{
		//alert("Error  "+e);
	}
}


</script>
