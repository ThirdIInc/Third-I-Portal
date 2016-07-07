<%@ taglib prefix="s" uri="/struts-tags"%>


<%
  
String themeName="globe";
String fontName="Arial";
String font_Size="11";

String fontFamily=fontName;
String fontSize=font_Size;
String aLink= "#000000";	
 %>
 
 
 <style type="text/css">
  

 .orange {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontFamily%>;
	color: #FFFFFF;
	text-decoration: none;
}
 
 
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

 .eventheader {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	color: #000000;
	font-weight: bold;
	text-decoration: none;
}

.headerDashlet {
	font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>px;
	font-weight: bold;
	color: #000000;
	text-decoration: none;
}

 .txt {
font-Family:<%=fontFamily%>;
	FONT-SIZE: <%=fontSize%>;
	color: <%=aLink %>;
	text-decoration: none;
}
 
 </style>




<table width="80%" border="0" cellpadding="0" cellspacing="0">
	 
	<tr>
		<td valign="top" width="25%">
		<table width="241" border="0" cellspacing="0" cellpadding="0">

			 
			<tr>
				<td valign="top"><%@include
					file="../common/leftEmployeePortalHome.jsp"%>
				</td>
			</tr>
			<tr>
				<td>
				<div align="right"><!-- SSL Seal Starts--> <!-- SSL Seal Ends-->
				&nbsp;&nbsp;&nbsp;&nbsp;</div>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
		</table>
		</td>
		<td valign="top">
		<table width="75%" border="0" cellspacing="0" cellpadding="0">
			<s:form action="EmployeePortal" id="paraFrm" theme="simple"
				name="employeePortalForm">
				<tr>
					<td><a href="javascript:ChangePage()"></td>
				</tr>

			 
				<tr>
					<td height="20">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="eventheader">E-greetings &amp; Desktop
							Wallpaper</td>
							<td>
							
							<div align="right"><a href="javascript:void(0);"
								class="contlink"></a></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="12"></td>
				</tr>

				<tr>
					<td><span style="cursor: pointer" onClick="callDiv('Greetings')"
						id="greeting_class_id"> <strong>E-Greetings</strong> </span> <span
						class="blacklink">|</span> <span style="cursor: pointer"
						onClick="callDiv('Wallpaper')" id="wall_class_id"> <strong>Desktop
					Wallpaper</strong></span></td>
				</tr>

				<tr>
					<td valign="top">
					<table width="97%" border="0" cellpadding="2" cellspacing="2"
						style="display: " id="GreetingsId">
						<tr>
							<td class="headerDashlet">&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="25" colspan="3" class="headerDashlet">Diwali
							Greetings !!!</td>
						</tr>
						<tr>
							<td width="33%" height="180">
							<div align="center"><img
								src="<%=request.getContextPath()%>/pages/portal/images/diwali/diwali1.jpg"
								width="200" height="167" /></div>
							</td>
							<td width="33%">
							<div align="center"><img
								src="<%=request.getContextPath()%>/pages/portal/images/diwali/diwali2.jpg"
								width="200" height="167" /></div>
							</td>
							<td width="33%">
							<div align="center"><img
								src="<%=request.getContextPath()%>/pages/portal/images/diwali/diwali3.jpg"
								width="200" height="167" /></div>
							</td>
						</tr>
						<tr>
							<td>
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/diwali/diwali1b.jpg"
								target="_blank" class="headerDashlet">Download</a></div>
							</td>
							<td>
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/diwali/diwali2b.jpg"
								target="_blank" class="headerDashlet">Download</a></div>
							</td>
							<td>
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/diwali/diwali3b.jpg"
								target="_blank" class="headerDashlet">Download</a></div>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><span class="headerDashlet">Christmas &amp; New
							Year Greetings !!! </span></td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/christmas&amp;newyear/christmass2.jpg"><img
								src="<%=request.getContextPath()%>/pages/portal/images/christmas&amp;newyear/christmass2.jpg"
								width="200" height="167" border="0" /></a></div>
							</td>
							<td>
							<div align="center"><img
								src="<%=request.getContextPath()%>/pages/portal/images/christmas&amp;newyear/christmass4.jpg"
								width="200" height="167" /></div>
							</td>
							<td>
							<div align="center"><img
								src="<%=request.getContextPath()%>/pages/portal/images/christmas&amp;newyear/christmass3.jpg"
								width="200" height="167" /></div>
							</td>
						</tr>
						<tr>
							<td>
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/christmas&amp;newyear/christmas2.jpg"
								target="_blank" class="headerDashlet">Download</a></div>
							</td>
							<td>
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/christmas&amp;newyear/christmas4.jpg"
								target="_blank" class="headerDashlet">Download</a></div>
							</td>
							<td>
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/christmas&amp;newyear/christmas3.jpg"
								target="_blank" class="headerDashlet">Download</a></div>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="headerDashlet">Dhanteras !!!</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="headerDashlet">
							<div align="center"><img
								src="<%=request.getContextPath()%>/pages/portal/images/diwali/dhans1.jpg"
								width="200" height="167" /></div>
							</td>
							<td>
							<div align="center"><img
								src="<%=request.getContextPath()%>/pages/portal/images/diwali/dhans2.jpg"
								width="200" height="167" /></div>
							</td>
							<td>
							<div align="center"><img
								src="<%=request.getContextPath()%>/pages/portal/images/diwali/dhans3.jpg"
								width="200" height="167" /></div>
							</td>
						</tr>
						<tr>
							<td class="headerDashlet">
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/diwali/dhan1.jpg"
								target="_blank" class="headerDashlet">Download</a></div>
							</td>
							<td>
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/diwali/dhan2.jpg"
								target="_blank" class="headerDashlet">Download</a></div>
							</td>
							<td>
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/diwali/dhan3.jpg"
								target="_blank" class="headerDashlet">Download</a></div>
							</td>
						</tr>
						<tr>
							<td class="headerDashlet">&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="headerDashlet">Holy !!!</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="headerDashlet">
							<div align="center"><img
								src="<%=request.getContextPath()%>/pages/portal/images/diwali/holi1.jpg"
								width="200" height="167" /></div>
							</td>
							<td>
							<div align="center"><img
								src="<%=request.getContextPath()%>/pages/portal/images/diwali/holi2s.jpg"
								width="200" height="167" /></div>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td class="headerDashlet">
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/diwali/holi.jpg"
								target="_blank" class="headerDashlet">Download</a></div>
							</td>
							<td>
							<div align="center"><a
								href="<%=request.getContextPath()%>/pages/portal/images/diwali/holi2.jpg"
								target="_blank" class="headerDashlet">Download</a></div>
							</td>
							<td>&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>


				<td valign="top">
				<table cellspacing="2" cellpadding="2" border="0" width="97%"
					id="WallpaperId" style="display: none;">
					<tbody>
						<tr>
							<td class="headerDashlet">&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td height="25" class="headerDashlet" colspan="2">Desktop
							Wallpaper</td>
						</tr>
						<tr>
							<td height="180" width="33%">
							<div align="center"><img height="225" width="300"
								src="<%=request.getContextPath()%>/pages/portal/images/wallpaper/Extreme1.jpg"></div>
							</td>
							<td width="33%">
							<div align="center"><img height="225" width="300"
								src="<%=request.getContextPath()%>/pages/portal/images/wallpaper/fifa.jpg"></div>
							</td>
						</tr>
						<tr>
							<td>
							<div align="center"><a class="headerDashlet"
								target="_blank"
								href="<%=request.getContextPath()%>/pages/portal/images/wallpaper/Extreme.jpg">Download</a></div>
							</td>
							<td>
							<div align="center"><a class="headerDashlet"
								target="_blank"
								href="<%=request.getContextPath()%>/pages/portal/images/wallpaper/FIFA_World_Cup.jpg">Download</a></div>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
							<div align="center"><img height="225" width="300"
								src="<%=request.getContextPath()%>/pages/portal/images/wallpaper/Letworld1.jpg"></div>
							</td>
							<td>
							<div align="center"><img height="225" width="300"
								src="<%=request.getContextPath()%>/pages/portal/images/wallpaper/Mechanism1.jpg"></div>
							</td>
						</tr>
						<tr>
							<td>
							<div align="center"><a class="headerDashlet"
								target="_blank"
								href="<%=request.getContextPath()%>/pages/portal/images/wallpaper/Letworld.jpg">Download</a></div>
							</td>
							<td>
							<div align="center"><a class="headerDashlet"
								target="_blank"
								href="<%=request.getContextPath()%>/pages/portal/images/wallpaper/Mechanism.jpg">Download</a></div>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
							<div align="center"><img height="225" width="300"
								src="<%=request.getContextPath()%>/pages/portal/images/wallpaper/mota1.jpg"></div>
							</td>
							<td>
							<div align="center"><img height="225" width="300"
								src="<%=request.getContextPath()%>/pages/portal/images/wallpaper/Parachuting1.jpg"></div>
							</td>
						</tr>
						<tr>
							<td>
							<div align="center"><a class="headerDashlet"
								target="_blank"
								href="<%=request.getContextPath()%>/pages/portal/images/wallpaper/mota.jpg">Download</a></div>
							</td>
							<td>
							<div align="center"><a class="headerDashlet"
								target="_blank"
								href="<%=request.getContextPath()%>/pages/portal/images/wallpaper/Parachuting.jpg">Download</a></div>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
							<div align="center"><img height="225" width="300"
								src="<%=request.getContextPath()%>/pages/portal/images/wallpaper/sachin.jpg"></div>
							</td>
							<td>
							<div align="center"><img height="225" width="300"
								src="<%=request.getContextPath()%>/pages/portal/images/wallpaper/sky1.jpg"></div>
							</td>
						</tr>
						<tr>
							<td>
							<div align="center"><a class="headerDashlet"
								target="_blank"
								href="<%=request.getContextPath()%>/pages/portal/images/wallpaper/Sachin_Tendulkar.jpg">Download</a></div>
							</td>
							<td>
							<div align="center"><a class="headerDashlet"
								target="_blank"
								href="<%=request.getContextPath()%>/pages/portal/images/wallpaper/sky-and-flowers.jpg">Download</a></div>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
							<div align="center"><img height="225" width="300"
								src="<%=request.getContextPath()%>/pages/portal/images/wallpaper/snowwall1.jpg"></div>
							</td>
							<td>
							<div align="center"><img height="225" width="300"
								src="<%=request.getContextPath()%>/pages/portal/images/wallpaper/sport1.jpg"></div>
							</td>
						</tr>
						<tr>
							<td>
							<div align="center"><a class="headerDashlet"
								target="_blank"
								href="<%=request.getContextPath()%>/pages/portal/images/wallpaper/snowwall.jpg">Download</a></div>
							</td>
							<td>
							<div align="center"><a class="headerDashlet"
								target="_blank"
								href="<%=request.getContextPath()%>/pages/portal/images/wallpaper/Sport.jpg">Download</a></div>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
							<div align="center"><img height="225" width="300"
								src="<%=request.getContextPath()%>/pages/portal/images/wallpaper/spring1.jpg"></div>
							</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>
							<div align="center"><a class="headerDashlet"
								target="_blank"
								href="<%=request.getContextPath()%>/pages/portal/images/wallpaper/Spring.jpg">Download</a></div>
							</td>
							<td>&nbsp;</td>
						</tr>
				</table>

				</td>

			</s:form>
		</table>
		</td>

	</tr>

</table>



<script>
			onload();
			function onload()
			{
		 		document.getElementById('greeting_class_id').className='orange';
		 		document.getElementById('wall_class_id').className='contlink';
			 
			}
			function callDiv(id)
			{
			 
				if(id=='Greetings')
				{
						document.getElementById('GreetingsId').style.display='';
						document.getElementById('WallpaperId').style.display='none';
						document.getElementById('greeting_class_id').className='portalOnLink';
						document.getElementById('wall_class_id').className='contlink';
				}
				else{
			 
				document.getElementById('GreetingsId').style.display='none';
				document.getElementById('WallpaperId').style.display='';
				document.getElementById('wall_class_id').className='portalOnLink';
				document.getElementById('greeting_class_id').className='contlink';
				}
			}
			
			</script>

