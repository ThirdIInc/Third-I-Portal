<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="org.paradyne.lib.Utility"%>


<%
	Object[][] holidayListObj = null;
	Object[][] birthdayListObj = null;
	Object[][] newJoineeListObj = null;
	Object[][] newRegListObj = null;
	 
	String type = "";

	try {
		type = (String) request.getAttribute("type");

		holidayListObj = (Object[][]) request
		.getAttribute("holidayListObj");
		birthdayListObj = (Object[][]) request
		.getAttribute("birthdayListObj");

		newJoineeListObj = (Object[][]) request
		.getAttribute("newJoineeListObj");

		newRegListObj= (Object[][]) request.getAttribute("resignedListObj");
		
	 

	} catch (Exception e) {
		e.printStackTrace();
		type = "";
	}
%>

<s:form action="EmployeePortal" id="paraFrm" theme="simple"
	name="employeePortalForm">

	<%
if (!type.equals("") && type.trim().equals("Birthday List")) {
%>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">

		<tr>
			<td width="100%" valign="top">


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="left">


				<tr>
					<td valign="bottom" class="txt">
					<table width="100%" border="0" align="right" cellpadding="0"
						cellspacing="0" class="pageHeader">
						<tr>
							<td><strong class="text_head"><img
								src="../pages/mypage/images/icons/birthdayHeader.png" width="25"
								height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">
							Birthday List </strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>


				<%
			String url = "EmployeePortal_showGeneralDashletList.action?type="+type;
			%>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<s:hidden name="myPage" id="myPage" />

							<td width="20%" class="formtxt">Month: <s:select
								name="MonthList"
								list="#{'':'--Select--','JAN':'January', 'FEB':'February', 'MAR':'March', 'APR':'April', 'MAY':'May', 'JUN':'June', 'JUL':'July', 'AUG':'August', 'SEP':'September', 'OCT':'October', 'NOV':'November', 'DEC':'December'}"
								onchange="callSearchDataFun(this.value)"></s:select></td>

							<!-- Paging implementation -->
							<td id="showCtrl" width="80%" align="right"><s:if
								test="birthdayPageFlag">
								<%
									int totalPage = (Integer) request.getAttribute("totalPage");
									int pageNo = (Integer) request.getAttribute("pageNo");
							%>
								<b>Page:</b>
								<a href="javascript:void(0);"
									onclick="callPage('1', 'F', '<%=totalPage%>', '<%=url%>');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp;
						<a href="javascript:void(0);"
									onclick="callPage('P', 'P', '<%=totalPage%>', '<%=url%>');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a>
								<input type="text" name="pageNoField" id="pageNoField" size="3"
									value="<%=pageNo%>" maxlength="10"
									onkeypress="callPageText(event, '<%=totalPage%>','<%=url%>');return numbersOnly();" /> of <%=totalPage%>
								<a href="javascript:void(0);"
									onclick="callPage('N', 'N', '<%=totalPage%>','<%=url%>')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp;
						<a href="javascript:void(0);"
									onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', '<%=url%>');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a>
							</s:if></td>

						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%">
						<tr height="20">
							<td height="20" width="5%" class="formth"><strong>Sr.No</strong></td>
							<td class="formth"><strong>Employee Code</strong></td>
							<td class="formth"><strong>Employee Name </strong></td>
							<td class="formth"><strong>Department </strong></td>
							<td class="formth"><strong>Branch</strong></td>
							<td class="formth"><strong>Birth Date </strong></td>
						</tr>

						<%
								int srNo = 0;
								try {
									srNo = (Integer) request
									.getAttribute("loopStartIndexBirthday");
								} catch (Exception e) {
									e.printStackTrace();
								}

								if (birthdayListObj != null && birthdayListObj.length > 0) {

									for (int i = 0; i < birthdayListObj.length; i++) {
						%>

						<tr height="20">
							<td height="20" align="center" width="5%" class="sortableTD"><%=++srNo%></td>
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(birthdayListObj[i][0]))%></td>
							<td class="sortableTD">&nbsp;
							<a href="javascript:void(0)"
								onclick="return callNextEmployee('<%=String.valueOf(birthdayListObj[i][4])%>');"
								class="contlink">
							<%=Utility.checkNull(String
										.valueOf(birthdayListObj[i][1]))%></a></td>
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(birthdayListObj[i][2]))%></td>
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(birthdayListObj[i][5]))%></td>			
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(birthdayListObj[i][3]))%></td>

						</tr>

						<%
								}
								} else {
						%>
						<tr>
							<td align="Center" width="100%" nowrap="nowrap"><font
								color="red">No data to be display</font></td>
						</tr>
						<%
						}
						%>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<%
	}
	%>


	<%
	if (!type.equals("") && type.trim().equals("Holiday")) {
	%>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">

		<tr>
			<td width="100%" valign="top">


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="left">


				<tr>
					<td valign="bottom" class="txt">
					<table width="100%" border="0" align="right" cellpadding="0"
						cellspacing="0" class="formbg">
						<tr>
							<td><strong class="text_head"><img
								src="../pages/images/recruitment/review_shared.gif" width="25"
								height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">Holiday
							List</strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%">
						<tr height="20">
							<td height="20" width="5%" class="formth"><strong>Sr.No</strong></td>
							<td class="formth"><strong>Date </strong></td>
							<td class="formth"><strong>Description </strong></td>

						</tr>

						<%
									if (holidayListObj != null && holidayListObj.length > 0) {
									for (int i = 0; i < holidayListObj.length; i++) {
						%>

						<tr height="20">
							<td height="20" align="center" width="5%" class="sortableTD"><%=i + 1%></td>
							<td class="sortableTD"><%=holidayListObj[i][0]%></td>
							<td class="sortableTD"><%=holidayListObj[i][1]%></td>


						</tr>

						<%
								}
								}
						%>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<%
	}
	%>



	<%
	if (!type.equals("") && type.trim().equals("New Joinee")) {
	%>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">

		<tr>
			<td width="100%" valign="top">


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="left">

				<tr>
					<td valign="bottom" class="txt">
					<table width="100%" border="0" align="right" cellpadding="0"
						cellspacing="0" class="pageHeader">
						<tr>
							<td><strong class="text_head"><img
								src="../pages/mypage/images/icons/newJoineeHeader.png" width="25"
								height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">New
							Joinee List</strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>



				<%
						String url = "EmployeePortal_showGeneralDashletList.action?type="
						+ type;
				%>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<s:hidden name="myPageNewJoinee" id="myPageNewJoinee" />

							<td width="20%" class="formtxt"></td>

							<!-- Paging implementation -->
							<td id="showCtrl" width="80%" align="right"><s:if
								test="newJoineePageFlag">
								<%
										int totalPageNewJoinee = (Integer) request
										.getAttribute("totalPageNewJoinee");
										int pageNoNewJoinee = (Integer) request
										.getAttribute("pageNoNewJoinee");
								%>
								<b>Page:</b>
								<a href="javascript:void(0);"
									onclick="callPageNewJoinee('1', 'F', '<%=totalPageNewJoinee%>', '<%=url%>');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp;
						<a href="javascript:void(0);"
									onclick="callPageNewJoinee('P', 'P', '<%=totalPageNewJoinee%>', '<%=url%>');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a>
								<input type="text" name="pageNoNewJoineeField"
									id="pageNoNewJoineeField" size="3" value="<%=pageNoNewJoinee%>"
									maxlength="10"
									onkeypress="callPageNewJoineeText(event, '<%=totalPageNewJoinee%>','<%=url%>');return numbersOnly();" /> of <%=totalPageNewJoinee%>
								<a href="javascript:void(0);"
									onclick="callPageNewJoinee('N', 'N', '<%=totalPageNewJoinee%>','<%=url%>')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp;
						<a href="javascript:void(0);"
									onclick="callPageNewJoinee('<%=totalPageNewJoinee%>', 'L', '<%=totalPageNewJoinee%>', '<%=url%>');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a>
							</s:if></td>

						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td>
					<table width="100%">
						<tr height="20">
							<td height="20" width="5%" class="formth"><strong>Sr.No</strong></td>
							<td class="formth"><strong>Joining Date</strong></td>
							<td class="formth"><strong>Employee Code </strong></td>
							<td class="formth"><strong>Employee Name </strong></td>
							<td class="formth"><strong>Designation </strong></td>
							<td class="formth"><strong>Department </strong></td>
							<td class="formth"><strong>Branch </strong></td>


						</tr>

						<%
								int srNo = 0;
								try {
									srNo = (Integer) request.getAttribute("loopStartIndex");
								} catch (Exception e) {
									e.printStackTrace();
								}
								if (newJoineeListObj != null && newJoineeListObj.length > 0) {
									for (int i = 0; i < newJoineeListObj.length; i++) {
						%>

						<tr height="20">
							<td height="20" align="center" width="5%" class="sortableTD"><%=++srNo%></td>
							<td class="sortableTD" nowrap="nowrap">&nbsp;<%=Utility.checkNull(String
										.valueOf(newJoineeListObj[i][0]))%></td>
							<td class="sortableTD">&nbsp;<a href="javascript:void(0)"
								onclick="return callNextEmployee('<%=String.valueOf(newJoineeListObj[i][6])%>');"
								class="contlink"><%=Utility.checkNull(String
										.valueOf(newJoineeListObj[i][1]))%></a></td>
							<td class="sortableTD">&nbsp;<a href="javascript:void(0)"
								onclick="return callNextEmployee('<%=String.valueOf(newJoineeListObj[i][6])%>');"
								class="contlink"><%=Utility.checkNull(String
										.valueOf(newJoineeListObj[i][2]))%></a></td>
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(newJoineeListObj[i][3]))%></td>
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(newJoineeListObj[i][4]))%></td>
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(newJoineeListObj[i][5]))%></td>


						</tr>

						<%
								}
								}
						%>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>



	<%
	}
	%>

	<!-- Added by janisha for resiged list on 7 Feb 2012 -->
	<%
	if (!type.equals("") && type.trim().equals("Resigned List")) {
	%>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">

		<tr>
			<td width="100%" valign="top">


			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				align="left">

				<tr>
					<td valign="bottom" class="txt">
					<table width="100%" border="0" align="right" cellpadding="0"
						cellspacing="0" class="pageHeader">
						<tr>
							<td><strong class="text_head"><img
								src="../pages/mypage/images/icons/resignHeader.png" width="25"
								height="25" /></strong></td>
							<td width="93%" class="txt"><strong class="text_head">Resigned
							List </strong></td>
							<td width="3%" valign="top" class="txt">
							<div align="right"></div>
							</td>
						</tr>
					</table>
					</td>
				</tr>



				<%
						String url = "EmployeePortal_showGeneralDashletList.action?type="
						+ type;
				%>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<s:hidden name="myPageResignDisplay" id="myPageResignDisplay" />

							<td width="20%" class="formtxt"></td>

							<!-- Paging implementation -->
							<td id="showCtrl" width="80%" align="right"><s:if
								test="resignPageFlag">
								<%
										int totalPageReg = (Integer) request
										.getAttribute("totalPageResigned");
										int pageNoReg = (Integer) request
										.getAttribute("pageNoResigned");
								%>
								<b>Page:</b>
								<a href="javascript:void(0);"
									onclick="callPageReg('1', 'F', '<%=totalPageReg%>', '<%=url%>');">
								<img title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp;
						<a href="javascript:void(0);"
									onclick="callPageReg('P', 'P', '<%=totalPageReg%>', '<%=url%>');">
								<img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a>
								<input type="text" name="pageNoRegField" id="pageNoRegField"
									size="3" value="<%=pageNoReg%>" maxlength="10"
									onkeypress="callPageRegText(event, '<%=totalPageReg%>','<%=url%>');return numbersOnly();" /> of <%=totalPageReg%>
								<a href="javascript:void(0);"
									onclick="callPageReg('N', 'N', '<%=totalPageReg%>','<%=url%>')">
								<img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp;
						<a href="javascript:void(0);"
									onclick="callPageReg('<%=totalPageReg%>', 'L', '<%=totalPageReg%>', '<%=url%>');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a>
							</s:if></td>

						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td>
					<table width="100%">
						<tr height="20">
							<td height="20" width="5%" class="formth"><strong>Sr.No</strong></td>
							<td class="formth"><strong>Date Of Leaving</strong></td>
							<td class="formth"><strong>Employee Code </strong></td>
							<td class="formth"><strong>Employee Name </strong></td>
							<td class="formth"><strong>Designation </strong></td>
							<td class="formth"><strong>Department </strong></td>
							<td class="formth"><strong>Branch </strong></td>


						</tr>

						<%
								int srNo = 0;
								try {
									srNo = (Integer) request.getAttribute("loopStartIndex");
								} catch (Exception e) {
									e.printStackTrace();
								}
								if (newRegListObj != null && newRegListObj.length > 0) {
									for (int i = 0; i < newRegListObj.length; i++) {
						%>

						<tr height="20">
							<td height="20" align="center" width="5%" class="sortableTD"><%=++srNo%></td>
							<td class="sortableTD" nowrap="nowrap">&nbsp;<%=Utility.checkNull(String
										.valueOf(newRegListObj[i][0]))%></td>
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(newRegListObj[i][1]))%></td>
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(newRegListObj[i][2]))%></td>
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(newRegListObj[i][3]))%></td>
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(newRegListObj[i][4]))%></td>
							<td class="sortableTD">&nbsp;<%=Utility.checkNull(String
										.valueOf(newRegListObj[i][5]))%></td>


						</tr>

						<%
								}
								}
						%>


					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>



	<%
	}
	%>
	<!--End Added by janisha for resiged list on 7 Feb 2012  -->


 
			
</s:form>

<script>

function callActionPage(actionName)
{ 
	document.getElementById('paraFrm').action = actionName;
	document.getElementById('paraFrm').submit();
}
function show(id)
		{
		 
		var wind = window.open(id,'_blank','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		} 
		
		
	function callPageNewJoinee(id, pageImg, totalPageHid, action) {		
		
		try{
			pageNo = document.getElementById('pageNoNewJoineeField').value;	
		actPage = document.getElementById('myPageNewJoinee').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoNewJoineeField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoNewJoineeField').value = actPage;
			    document.getElementById('pageNoNewJoineeField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoNewJoineeField').value=actPage;
			    document.getElementById('pageNoNewJoineeField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoNewJoineeField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoNewJoineeField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoNewJoineeField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoNewJoineeField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoNewJoineeField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoNewJoineeField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageNewJoinee').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
			alert(e);
		}
	
	}
	
	
		function callPageNewJoineeText(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoNewJoineeField').value;
		 	var actPage = document.getElementById('myPageNewJoinee').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoNewJoineeField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoNewJoineeField').focus();
		     document.getElementById('pageNoNewJoineeField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoNewJoineeField').focus();
		     document.getElementById('pageNoNewJoineeField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoNewJoineeField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageNewJoinee').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}	
		
		
		
		
	//Added by janisha for resiged list on 7 Feb 2012
		function callPageReg(id, pageImg, totalPageHid, action) {		
		
		try{
			pageNo = document.getElementById('pageNoRegField').value;	
		actPage = document.getElementById('myPageResignDisplay').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoRegField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoRegField').value = actPage;
			    document.getElementById('pageNoRegField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoRegField').value=actPage;
			    document.getElementById('pageNoRegField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoRegField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoRegField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoRegField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoRegField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoRegField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoRegField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageResignDisplay').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
			alert(e);
		}
	
	}
	
	
		function callPageRegText(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoRegField').value;
		 	var actPage = document.getElementById('myPageResignDisplay').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoRegField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoRegField').focus();
		     document.getElementById('pageNoRegField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoRegField').focus();
		     document.getElementById('pageNoRegField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoRegField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageResignDisplay').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}	
		
			
		//end Added by janisha for resiged list on 7 Feb 2012
		
	function callSearchDataFun(value){
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = 'EmployeePortal_searchBdayRecord.action?type=Birthday List';
		document.getElementById('paraFrm').submit();
	}	
	
	
	function callNextEmployee(empCode)
{ 
	try{
	
	// alert("empCode  "+empCode);
	var searchType='emp';
	var searchText='';

	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_getEmployeeDetails.action?empCode='+empCode+'&searchType='+searchType+'&searchText='+searchText;
	
	 
	document.getElementById('paraFrm').submit();
	}catch(e){ 
	 alert("Error"+e);
	}
}
	
		
		
		
		
</script>