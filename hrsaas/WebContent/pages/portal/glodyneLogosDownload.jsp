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


<body>

<table width="98%" border="0" align="center" cellpadding="0"
	cellspacing="0">

	<tr>
		<td width="25%" valign="top">
		<table width="241" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td valign="top" width="10%"><%@include
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
		<td width="90%" valign="top"><s:form action="EmployeePortal"
			id="paraFrm" theme="simple" name="employeePortalForm">

			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td><a href="javascript:void(0);"></td>
				</tr>

				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td height="20">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="eventheader">Logos &amp; Templates</td>
							<td>
							<div align="right"><a href="javascript:void(0);"
								class="contlink"></a></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="5"></td>
				</tr>

				<tr>
					<td><span style="cursor: pointer" id="logo_class_id"
						onClick="callDiv('Logos')"><strong>Logos</strong> </span> <span
						class="blacklink">|</span> <span style="cursor: pointer"
						onClick="callDiv('Templates')" id="template_class_id"> <strong>Templates</strong></span></td>
				</tr>



				<table cellpadding="0" cellspacing="0" border="0" id="logoId">
					<tr>
						<td>
						<table width="100%" border="0" cellspacing="2" cellpadding="2">
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>

							<tr>
								<td><img
									src="<%=request.getContextPath()%>/pages/portal/images/logos/cl1.jpg"
									width="300" height="73" /></td>
								<td valign="bottom"><img
									src="<%=request.getContextPath()%>/pages/portal/images/logos/cl2.jpg"
									width="200" height="49" /></td>
							</tr>
							<tr>
								<td><span class="txt">Letterhead Color Logo : </span><span
									class="blacklink">Size 300 px X 73 px</span><span
									class="headerDashlet"> | </span><a
									href="<%=request.getContextPath()%>/pages/portal/images/logos/cl1.jpg"
									target="_blank" class="headerDashlet">Download</a></td>
								<td><span class="txt">PPT Color Logo : </span><span
									class="blacklink">Size 200 px X 49 px</span><span
									class="headerDashlet"> | </span><a
									href="<%=request.getContextPath()%>/pages/portal/images/logos/cl2.jpg"
									target="_blank" class="headerDashlet">Download</a></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><img
									src="<%=request.getContextPath()%>/pages/portal/images/logos/bw2.jpg"
									width="300" height="73" /></td>
								<td valign="bottom"><img
									src="<%=request.getContextPath()%>/pages/portal/images/logos/bw1.jpg"
									width="200" height="49" /></td>
							</tr>
							<tr>
								<td><span class="txt">Letterhead B/W Logo : </span><span
									class="blacklink">Size 300 px X 73 px</span><span class="txt">
								| </span><a
									href="<%=request.getContextPath()%>/pages/portal/images/logos/bw2.jpg"
									target="_blank" class="headerDashlet">Download</a></td>
								<td><span class="txt">PPT B/W Logo : </span><span
									class="blacklink">Size 200 px X 49 px</span><span
									class="headerDashlet"> | </span><a
									href="<%=request.getContextPath()%>/pages/portal/images/logos/bw1.jpg"
									target="_blank" class="headerDashlet">Download</a></td>
							</tr>
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td
											background="<%=request.getContextPath()%>/pages/portal/images/line.gif"><img
											src="<%=request.getContextPath()%>/pages/portal/images/line.gif"
											width="60" height="7" /></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td><img
									src="<%=request.getContextPath()%>/pages/portal/images/logos/smaarftech.jpg"
									width="300" height="75" /></td>
								<td><img
									src="<%=request.getContextPath()%>/pages/portal/images/logos/sarvalabh.jpg"
									width="300" height="65" /></td>
							</tr>
							<tr>
								<td><span class="txt">Standard Logo : </span><span
									class="blacklink">Size 300 px X 75 px <span class="txt">|
								</span><a
									href="<%=request.getContextPath()%>/pages/portal/images/logos/smaarftech.jpg"
									target="_blank" class="headerDashlet">Download</a></span></td>
								<td><span class="txt">Standard Logo : </span><span
									class="blacklink">Size 300 px X 65 px <span class="txt">|
								</span><a
									href="<%=request.getContextPath()%>/pages/portal/images/logos/sarvalabh.jpg"
									target="_blank" class="headerDashlet">Download</a></span></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2">
								<table width="100%" border="0" cellspacing="1" cellpadding="1">
									<tr>
										<td width="29%"><img
											src="<%=request.getContextPath()%>/pages/portal/images/logos/nrega.jpg"
											width="150" height="155" /></td>
										<td width="24%"><img
											src="<%=request.getContextPath()%>/pages/portal/images/logos/roojgar.jpg"
											width="150" height="142" /></td>
										<td width="47%"><img
											src="<%=request.getContextPath()%>/pages/portal/images/logos/eshakti.jpg"
											width="200" height="52" /></td>
									</tr>
									<tr>
										<td class="txt">NREGA Logo <span class="blacklink">|
										<a
											href="<%=request.getContextPath()%>/pages/portal/images/logos/smaarftech.jpg"
											target="_blank" class="headerDashlet">Download</a></span></td>
										<td><span class="txt">Eshakti Logo</span> <span
											class="blacklink">| <a
											href="<%=request.getContextPath()%>/pages/portal/images/logos/roojgar.jpg"
											target="_blank" class="headerDashlet">Download</a></span></td>
										<td><span class="txt">Eshakti Logo</span> <span
											class="blacklink">| <a
											href="<%=request.getContextPath()%>/pages/portal/images/logos/eshakti.jpg"
											target="_blank" class="headerDashlet">Download</a></span></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2">
								<table width="100%" border="0" cellspacing="1" cellpadding="1">
									<tr>
										<td width="30%"><img
											src="<%=request.getContextPath()%>/pages/portal/images/logos/biharsarkar.jpg"
											width="150" height="169" /></td>
										<td width="42%"><img
											src="<%=request.getContextPath()%>/pages/portal/images/logos/mahashramm.jpg"
											width="250" height="121" /></td>
										<td width="28%"><img
											src="<%=request.getContextPath()%>/pages/portal/images/logos/labourdept.jpg"
											width="150" height="130" /></td>
									</tr>
									<tr>
										<td><span class="txt">Bihar Goverment Logo <span
											class="blacklink">| <a
											href="<%=request.getContextPath()%>/pages/portal/images/logos/biharsarkar.jpg"
											target="_blank" class="headerDashlet">Download</a></span></span></td>
										<td><span class="txt">Mahashramm Logo <span
											class="blacklink">| <a
											href="<%=request.getContextPath()%>/pages/portal/images/logos/mahashramm.jpg"
											target="_blank" class="headerDashlet">Download</a></span></span></td>
										<td><span class="txt">Labour Department Logo <span
											class="blacklink">| <a
											href="<%=request.getContextPath()%>/pages/portal/images/logos/labourdept.jpg"
											target="_blank" class="headerDashlet">Download</a></span></span></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td colspan="2">&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td
											background="<%=request.getContextPath()%>/pages/portal/images/line.gif"><img
											src="<%=request.getContextPath()%>/pages/portal/images/line.gif"
											width="60" height="7" /></td>
									</tr>
								</table>
								</td>
							</tr>
							<tr>
								<td><img
									src="<%=request.getContextPath()%>/pages/portal/images/logos/peoplepower.jpg"
									width="300" height="66" /></td>
								<td valign="top"><img
									src="<%=request.getContextPath()%>/pages/portal/images/logos/whizible.jpg"
									width="150" height="98" /></td>
							</tr>
							<tr>
								<td><span class="txt">Peoplepower Logo : </span><span
									class="blacklink">Size 300px x 66px </span><span class="txt">
								<span class="blacklink">| <a
									href="<%=request.getContextPath()%>/pages/portal/images/logos/peoplepower.jpg"
									target="_blank" class="headerDashlet">Download</a></span></span></td>
								<td><span class="txt">Whizible Logo : </span><span
									class="blacklink">Size 150px x 98px </span><span class="txt">
								<span class="blacklink">| <a
									href="<%=request.getContextPath()%>/pages/portal/images/logos/whizible.jpg"
									target="_blank" class="headerDashlet">Download</a></span></span></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><img
									src="<%=request.getContextPath()%>/pages/portal/images/logos/schoolexcel.jpg"
									width="300" height="63" /></td>
								<td><img
									src="<%=request.getContextPath()%>/pages/portal/images/logos/collegexcel.jpg"
									width="300" height="58" /></td>
							</tr>
							<tr>
								<td><span class="txt">School Excel Logo : </span><span
									class="blacklink">Size 300px X 66px</span> <span
									class="blacklink">| <a
									href="<%=request.getContextPath()%>/pages/portal/images/logos/schoolexcel.jpg"
									target="_blank" class="headerDashlet">Download</a></span></td>
								<td><span class="txt">College Excel Logo : </span><span
									class="blacklink">Size 300px X 66px</span> <span
									class="blacklink">| <a
									href="<%=request.getContextPath()%>/pages/portal/images/logos/collegexcel.jpg"
									target="_blank" class="headerDashlet">Download</a></span></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td><img
									src="<%=request.getContextPath()%>/pages/portal/images/logos/univexcel.jpg"
									width="300" height="70" /></td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td colspan="2"><span class="txt">Univ Excel Logo :
								</span><span class="blacklink">Size 300px X 66px</span> <span
									class="blacklink">| <a
									href="<%=request.getContextPath()%>/pages/portal/images/logos/univexcel.jpg"
									target="_blank" class="headerDashlet">Download</a></span></td>
							</tr>
						</table>
						</td>
					</tr>

				</table>

				<div id="templateId" style="display: none;">
				<table cellpadding="0" cellspacing="0" border="0" width="100%"
					align="left">
					<tr>
						<td>
						<table width="100%" border="0" cellspacing="2" cellpadding="2"
							align="left">
							<tr>
								<td>
								<table cellspacing="2" cellpadding="2" border="0" width="100%"
									align="left">

									<tr>
										<td><img height="23" width="22"
											src="<%=request.getContextPath()%>/pages/portal/ppttemplate/ppt.jpg"></td>
										<td><a class="txt" target="_blank"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneCorporate.ppt">Glodyne
										Corporate.ppt</a></td>
										<td>
										<div align="left"><a class="headerDashlet"
											target="_blank"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneCorporate.ppt">Download</a></div>
										</td>
										<td></td>
										<td></td>

									</tr>
									<tr>
										<td><img height="23" width="22"
											src="<%=request.getContextPath()%>/pages/portal/images/ppt.jpg"></td>
										<td><a class="txt" target="_blank"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneInternalHR.ppt">Glodyne
										Internal HR.ppt</a></td>
										<td>
										<div align="left"><a class="headerDashlet"
											target="_blank"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneInternalHR.ppt">Download</a></div>
										</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><img height="23" width="22"
											src="<%=request.getContextPath()%>/pages/portal/images/ppt.jpg"></td>
										<td><a class="txt"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneSalesGovt.ppt"
											target="_blank">Glodyne Sales Government.ppt</a></td>
										<td>
										<div align="left"><a class="headerDashlet"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneSalesGovt.ppt"
											target="_blank">Download</a></div>
										</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><img height="23" width="22"
											src="<%=request.getContextPath()%>/pages/portal/images/ppt.jpg"></td>
										<td><a class="txt"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneSampleEducation.ppt"
											target="_blank">Glodyne Education.ppt</a></td>
										<td>
										<div align="left"><a class="headerDashlet"
											target="_blank"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneSampleEducation.ppt">Download</a></div>
										</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><img height="23" width="22"
											src="<%=request.getContextPath()%>/pages/portal/images/ppt.jpg"></td>
										<td><a class="txt"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneSampleIMS.ppt"
											target="_blank">Glodyne IMS.ppt</a></td>
										<td>
										<div align="left"><a class="headerDashlet"
											target="_blank"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneCorporate.ppt">Download</a></div>
										</td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td><img height="23" width="22"
											src="<%=request.getContextPath()%>/pages/portal/images/ppt.jpg"></td>
										<td><a class="txt" target="_blank"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneStandardTemplate.ppt">Glodyne
										Standard Template.ppt</a></td>
										<td>
										<div align="left"><a class="headerDashlet"
											target="_blank"
											href="<%=request.getContextPath()%>/pages/portal/ppttemplate/GlodyneStandardTemplate.ppt">Download</a></div>
										</td>
										<td></td>
										<td></td>
									</tr>
								</table>
								</td>
							</tr>

						</table>
						</td>
					</tr>

				</table>

				</div>

			</table>
		</s:form></td>
	</tr>

	</tr>
</table>


</body>



<script>
			onload();
			function onload()
			{
		 		document.getElementById('logo_class_id').className='portalOnLink';
		 		document.getElementById('template_class_id').className='contlink';
			 
			}
			function callDiv(id)
			{
			 
				if(id=='Logos')
				{
						document.getElementById('logoId').style.display='';
						document.getElementById('templateId').style.display='none';
						document.getElementById('logo_class_id').className='portalOnLink';
						document.getElementById('template_class_id').className='contlink';
				}
				else{
			 
				document.getElementById('logoId').style.display='none';
				document.getElementById('templateId').style.display='';
				document.getElementById('template_class_id').className='portalOnLink';
				document.getElementById('logo_class_id').className='contlink';
				}
			}
			
			</script>




