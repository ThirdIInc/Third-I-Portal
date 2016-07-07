<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="org.paradyne.lib.Utility"%>



<div style="float: left; width: 100%">

<div style="float: left; width: 98%">
<%
	Object[][] empDirConfObj = null;
	Object empDtlObj[][] = (Object[][]) request
			.getAttribute("empDtlObj");
	empDirConfObj = (Object[][]) request
			.getAttribute("employeeDirConf");
%>

<table width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">

	<tr>


		<td valign="top" width="100%"><s:form action="EmployeePortal"
			id="paraFrm" theme="simple" name="employeePortalForm">

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="left">

				<tr>
					<td valign="bottom" class="txt">
					<table width="100%" border="0" align="right" cellpadding="0"
						cellspacing="0" class="formbg">
						<tr>
							<td width="3%" ><strong class="text_head"><img
								src="../pages/images/recruitment/review_shared.gif" width="25"
								height="25" /></strong></td>
							<td width="97%" class="txt"><strong class="text_head">Employee 
							Information </strong></td>
							 
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td valign="top">
					<div align="center">
					<table cellspacing="0" cellpadding="5" border="0" width="100%"
						id="table4">

						<tr>
							<td align="left" valign="top" width="100%">
							<table cellspacing="1" cellpadding="5" border="0" id="table5" width="100%">

								<%
										if (empDtlObj != null && empDtlObj.length > 0) {
										%>
								<tr bgcolor="#f2f2f2">
									<td colspan="3">
									<div align="center"><b>Personal Information</b></div>
									</td>
								</tr>


								<tr>
									<td width="211">Employee Id:</td>
									<td width="376"><%=Utility.checkNull(String.valueOf(empDtlObj[0][0]))%></td>
									<td align="middle" width="129" valign="center" rowspan="5">
									<table align="center" id="table6">
										<tbody>
											<tr>
												<td align="middle" valign="center">
												<%
																String strValue = "..";
																try {
																	strValue = (String) request.getAttribute("empPhotograph");
																	if (strValue.equals("..")||strValue==null ||strValue.equals("null"))
																		strValue = "empimage.gif";
																	System.out.println("________str_________" + strValue);
																} catch (Exception e) {
																	strValue = "empimage.gif";
																	e.printStackTrace();
																}
														%> <%
														if(empDirConfObj!=null && empDirConfObj.length>0)
														{
															if(Utility.checkNull(String.valueOf(empDirConfObj[0][11])).equals("Y"))

															{
																
															if(!strValue.equals("empimage.gif"))
															{
																							
 %> <img
													src="../pages/images/<%=session.getAttribute("session_pool") %>/employee/<%=strValue %>"
													width="74" height="78" /> <%
															}
															else
															{
	%> <img src="../pages/portal/images/empimage.gif" width="74"
													height="78" /> <%
																
														
															}
															}else {
 %> <img src="../pages/portal/images/empimage.gif" width="74"
													height="78" /> <%
 }
														}
 %>
												</td>
											</tr>
											<!--<tr><td align="center" valign="middle">
											<input name="Report Abuse" type="button" id="Report Abuse" style="width: 90px" onClick="reportabuse()" value="Report Abuse" width="1" alt="Let us know if you think the above pic is not appropriate!" title="Let us know if you think the above pic is not appropriate!">
									</td></tr>-->
										</tbody>
									</table>
									</td>
								</tr>
								<tr>
									<td width="211">Employee Name:</td>
									<td width="376"><%=Utility.checkNull(String.valueOf(empDtlObj[0][1]))%></td>
								</tr>
								<%
									if(empDirConfObj!=null && empDirConfObj.length>0)
									{
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][0])).equals("Y"))
										{
								%>
								<tr>
									<td width="211">Birth Day:</td>
									<td width="376"><%=Utility.checkNull(String.valueOf(empDtlObj[0][2]))%></td>
								</tr>
								<%
											
										}
									}
								%>

								<tr>
									<td width="211">Gender:</td>
									<td width="376"><%=Utility.checkNull(String.valueOf(empDtlObj[0][3]))%></td>
								</tr>
								<%
									if(empDirConfObj!=null && empDirConfObj.length>0)
									{
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][1])).equals("Y"))
										{
								%>
								<tr>
									<td width="211">Blood Group:</td>
									<td width="376"><%=Utility.checkNull(String.valueOf(empDtlObj[0][4]))%></td>
								</tr>
								<%			
										}
									}
								%>

								<tr bgcolor="#f2f2f2">
									<td colspan="3">
									<div align="center"><b>Company Profile</b></div>
									</td>
								</tr>
								<%
									if(empDirConfObj!=null && empDirConfObj.length>0)
									{
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][2])).equals("Y"))
										{
											
								%>
								<tr>
									<td width="211">Date of Joining:</td>
									<td colspan="2"><%=Utility.checkNull(String.valueOf(empDtlObj[0][5]))%></td>
								</tr>
								<%
										}
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][3])).equals("Y"))
										{
								 %>
								<tr>
									<td width="211">Department:</td>
									<td colspan="2"><%=Utility.checkNull(String.valueOf(empDtlObj[0][6]))%></td>
								</tr>
								<%	
										}
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][4])).equals("Y"))
										{
								%>
								<tr>
									<td width="211">Designation:</td>
									<td colspan="2"><%=Utility.checkNull(String.valueOf(empDtlObj[0][7]))%></td>
								</tr>
								<tr>
									<td width="211">Job Role:</td>
									<td colspan="2"><%=Utility.checkNull(String.valueOf(empDtlObj[0][8]))%></td>
								</tr>
								<%		
										}
								%>
								<tr>
									<td width="211">Immediate Manager:</td>
									<%
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][5])).equals("Y"))
										{
								%>

									<td colspan="2"><a href="javascript:void(0);"  
										onclick="callNextEmployee('<%=empDtlObj[0][9]%>');"> <u><%=Utility.checkNull(String.valueOf(empDtlObj[0][10]))%></u></a></td>

									<%	
										}
										else
										{
								
								%>
									<td colspan="2"><%=Utility.checkNull(String.valueOf(empDtlObj[0][10]))%></td>

									<%
										}
								%>
								</tr>
								<%
										
										
									}
								if(empDirConfObj!=null && empDirConfObj.length>0)
								{
									if(Utility.checkNull(String.valueOf(empDirConfObj[0][6])).equals("Y") ||Utility.checkNull(String.valueOf(empDirConfObj[0][7])).equals("Y") || Utility.checkNull(String.valueOf(empDirConfObj[0][8])).equals("Y") || Utility.checkNull(String.valueOf(empDirConfObj[0][9])).equals("Y") || Utility.checkNull(String.valueOf(empDirConfObj[0][10])).equals("Y") )
									{
								%>




								<tr bgcolor="#f2f2f2">
									<td colspan="3">
									<div align="center"><b>Contact Information</b></div>
									</td>
								</tr>
								<%
									}
								}
									if(empDirConfObj!=null && empDirConfObj.length>0)
									{
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][6])).equals("Y"))
										{
								%>
								<tr>
									<td width="211">Current Location / Branch :</td>
									<td colspan="2"><%=Utility.checkNull(String.valueOf(empDtlObj[0][11]))%></td>
								</tr>
								<% 			
										}
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][7])).equals("Y"))
										{
								%>
								<tr>
									<td width="211">Employee Email:</td>
									<td colspan="2"><a
										href="mailto:<%=Utility.checkNull(String.valueOf(empDtlObj[0][12]))%>">
									<%=Utility.checkNull(String.valueOf(empDtlObj[0][12]))%></a></td>
								</tr>
								<%		
										}
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][8])).equals("Y"))
										{
								%>
								<tr>
									<td width="211">Extension</td>
									<td colspan="2"><%=Utility.checkNull(String.valueOf(empDtlObj[0][13]))%></td>
								</tr>
								<tr>
									<td width="211">Alternative Extension:</td>
									<td colspan="2"><%=Utility.checkNull(String.valueOf(empDtlObj[0][14]))%></td>
								</tr>
								<%		
										}
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][9])).equals("Y"))//mobile no
										{
								%>
								<tr>
									<td width="211">Mobile Number:</td>
									<td colspan="2"><%=Utility.checkNull(String.valueOf(empDtlObj[0][15]))%></td>
								</tr>
								<%		
										}
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][10])).equals("Y")) //Phone no
										{
								%>
								<tr>
									<td width="211">Landline Number:</td>
									<td colspan="2"><%=Utility.checkNull(String.valueOf(empDtlObj[0][16]))%></td>
								</tr>
								<tr>
									<td width="211">Direct Number:</td>
									<td colspan="2"><%=Utility.checkNull(String.valueOf(empDtlObj[0][17]))%></td>
								</tr>
								<%		
										}
									}
											
								%>


								<tr bgcolor="#f2f2f2">
									<td colspan="3">&nbsp;</td>
								</tr>
								<%
										} else {
										%>
								<tr align="center">
									<td colspan="7" class="border" width="100%"><font
										color="red"><strong>No Data to display</strong></font></td>
								</tr>
								<%
										}
										%>

							</table>
							</td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
			</table>
		</s:form></td>
	</tr>
</table>
</div>
</div>



<script>

 
function callNextEmployee(empCode)
{ 
	try{
	
	//alert("empCode  "+empCode);
	var searchType='emp';
	var searchText='';

	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_getEmployeeDetails.action?empCode='+empCode+'&searchType='+searchType+'&searchText='+searchText;
	document.getElementById('paraFrm').submit();
	}catch(e){ 
	//alert("Error"+e);
	}
}


</script>



