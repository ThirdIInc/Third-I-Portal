
<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="org.paradyne.lib.Utility"%>







<%
	Object empSearchObj[][] = null;
	Object[][] menuSearchObj = null;
	Object[][] empDirConfObj = null;

	try {

		empSearchObj = (Object[][]) request
		.getAttribute("empSearchObj");
		menuSearchObj = (Object[][]) request
		.getAttribute("menuSearchObj");
		empDirConfObj = (Object[][]) request
		.getAttribute("employeeDirConf");
		

	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">
	<tr>
		<td colspan="2" valign="top" height="2px"></td>
	</tr>
	<tr>
		<td valign="top" width="10%">
		<%
		 String clientName =(String)session.getAttribute("session_pool");
		 
		%> <%
			if(clientName.equals("pool_fort"))
			{
		%> <%@include file="../common/leftEmployeePortalHome.jsp"%>
		<%} else{%>
		<%@include file="../common/menu.jsp"%>
		<%} %>
		</td>


		<td valign="top" width="90%"><s:form action="EmployeePortal"
			id="paraFrm" theme="simple" name="employeePortalForm">
			<table width="97%" border="0" cellspacing="0" cellpadding="0"
				align="left">


				<tr>
					<td height="5"></td>
				</tr>

				<s:if test="%{ismenuDataAvailable}">
					<tr>

						<td width="100%" class="headerDashlet">

						<ul class="red1 a1 border">
							<span class="eventheader">Menu</span>
							<li>
						</ul>
						</td>
					</tr>

					<tr>
						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<s:hidden name="myPageMenu" id="myPageMenu" />

								<td width="20%" class="formtxt"></td>

								<!-- Paging implementation -->
								<td id="showCtrl" width="80%" align="right"><s:if
									test="myMenuPageFlag">
									<%
										int totalPageMenu = (Integer) request.getAttribute("totalPageMenu");
										int pageNoMenu = (Integer) request.getAttribute("pageNoMenu");

										String searchType = "";
										String searchString = "";
										searchType = (String) request.getAttribute("searchType");
										searchString = (String) request.getAttribute("searchString");

										String url = "EmployeePortal_getHomeSearch.action?searchType="
												+ searchType + "&searchText=" + searchString + "&random="
												+ Math.random();
									%>
									<b>Page:</b>
									<a href="#"
										onclick="callPageMenu('1', 'F', '<%=totalPageMenu%>', '<%=url%>');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp;
						<a href="#"
										onclick="callPageMenu('P', 'P', '<%=totalPageMenu%>', '<%=url%>');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a>
									<input type="text" name="pageNoMenuField" id="pageNoMenuField"
										size="3" value="<%=pageNoMenu%>" maxlength="10"
										onkeypress="callPageTextMenu(event, '<%=totalPageMenu%>','<%=url%>');return numbersOnly();" /> of <%=totalPageMenu%>
									<a href="#"
										onclick="callPageMenu('N', 'N', '<%=totalPageMenu%>','<%=url%>')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp;
						<a href="#"
										onclick="callPageMenu('<%=totalPageMenu%>', 'L', '<%=totalPageMenu%>', '<%=url%>');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a>
								</s:if></td>

							</tr>
						</table>
						</td>
					</tr>

					<s:if test="myMenuPageFlag">
						<%
								if (menuSearchObj != null && menuSearchObj.length > 0) {
								for (int i = 0; i < menuSearchObj.length; i++) {
						%>

						<tr>
							<td height="20" valign="middle"><img align="absmiddle"
								src="../pages/portal/images/icons/arrow.jpg" /> <a
								href="javascript:void(0)"
								onclick="callMyAction('<%=menuSearchObj[i][1]%>')"> <%=menuSearchObj[i][0]%></a></td>

							</td>
						</tr>

						<%
							}
							}
						%>
					</s:if>
					<s:else>

						<tr align="center">
							<td colspan="7" class="border" width="100%"><font
								color="red"><strong>No Data to display</strong></font></td>
						</tr>
					</s:else>

				</s:if>


				<s:if test="empDataAvailable">

					<!-- emp table starts -->

					<tr>

						<td width="100%" class="headerDashlet">

						<ul class="red1 a1 border">
							<span class="eventheader">Employee Directory 1111111</span>

							<li>
						</ul>
						</td>
					</tr>


					<%
						String searchTypeEmp = "";
						String searchStringEmp = "";
						searchTypeEmp = (String) request.getAttribute("searchType");
						searchStringEmp = (String) request.getAttribute("searchString");

						String empurl = "EmployeePortal_getHomeSearch.action?searchType="
								+ searchTypeEmp + "&searchText=" + searchStringEmp + "&random="
								+ Math.random();
					%>
					<tr>
						<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<s:hidden name="myPage" id="myPage" />

								<td width="20%" class="formtxt"></td>

								<!-- Paging implementation -->
								<td id="showCtrl" width="80%" align="right"><s:if
									test="pageFlag">
									<%
										int totalPage = (Integer) request.getAttribute("totalPage");
										int pageNo = (Integer) request.getAttribute("pageNo");
									%>
									<b>Page:</b>
									<a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', '<%=empurl%>');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp;
						<a href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', '<%=empurl%>');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a>
									<input type="text" name="pageNoField" id="pageNoField" size="3"
										value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>','<%=empurl%>');return numbersOnly();" /> of <%=totalPage%>
									<a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>','<%=empurl%>')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp;
						<a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', '<%=empurl%>');">
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
								<td height="20" bgcolor="#f2f2f2" width="5%" class="orange"><strong>Sr.No</strong></td>
								<td bgcolor="#f2f2f2" width="15%" class="orange" nowrap="nowrap"><strong>Employee
								Id</strong></td>
								<td bgcolor="#f2f2f2" width="25%" class="orange"><strong>Employee
								Name </strong></td>
								<%
									if(empDirConfObj!=null && empDirConfObj.length>0)
									{
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][3])).equals("Y"))
										{
								%>
								<td bgcolor="#f2f2f2" width="15%" class="orange"><strong>Designation
								</strong></td>
								<%											
										}
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][7])).equals("Y"))
										{
								 	%>
								<td bgcolor="#f2f2f2" width="15%" class="orange"><strong>E-mail</strong></td>
								<%		
										}
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][9])).equals("Y")||Utility.checkNull(String.valueOf(empDirConfObj[0][10])).equals("Y"))
										{
									 %>
								<td bgcolor="#f2f2f2" width="15%" class="orange"><strong>Phone/Mobile
								</strong></td>
								<%	
										}
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][8])).equals("Y"))
										{
								 	%>
								<td bgcolor="#f2f2f2" width="10%" class="orange"><strong>Extension
								</strong></td>
								<%		
										}
									}
								%>




							</tr>
							<s:if test="pageFlag">

								<%int count=0; %>

								<%
									int srNo = 0;
									try {
										srNo = (Integer) request.getAttribute("loopStartIndex");
									} catch (Exception e) {
										e.printStackTrace();
									}

									if (empSearchObj != null && empSearchObj.length > 0) {
										for (int i = 0; i < empSearchObj.length; i++) {
								%>
								<tr height="20" <%if(count%2==0){
												%>
									class="tableCell1" <%}else{%> class="tableCell2"
									<%	}count++; %> onmouseover="javascript:newRowColor(this);"
									title="Double click for employee details" style="cursor: hand"
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
									ondblclick="callEmployeePage('<%=empSearchObj[i][11]%>');">

									<td height="20" align="center" width="5%" class="border"><%=++srNo%></td>
									<td class="border" width="15%"><%=Utility.checkNull(String
									.valueOf(empSearchObj[i][0]))%></td>
									<td class="border" width="25%"><%=Utility.checkNull(String
									.valueOf(empSearchObj[i][1]))%></td>
									<%
									if(empDirConfObj!=null && empDirConfObj.length>0)
									{
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][3])).equals("Y"))
										{
											System.out.println("Hi inside department ");
								%>
									<td class="border" width="15%"><%=Utility.checkNull(String
									.valueOf(empSearchObj[i][3]))%></td>
									<%											
										}
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][7])).equals("Y"))
										{
								 	%>
									<td class="border" width="15%"><%=Utility.checkNull(String
									.valueOf(empSearchObj[i][10]))%></td>
									<%		
										}
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][9])).equals("Y")||Utility.checkNull(String.valueOf(empDirConfObj[0][10])).equals("Y"))
										{
									 %>
									<%
												String Numbers = "";
												String Numbers1 = "";
												String Numbers2 = "";
												if (String.valueOf(empSearchObj[i][5]) != null
												&& !String.valueOf(empSearchObj[i][5]).equals("")) {
											Numbers += Utility.checkNull(String
													.valueOf(empSearchObj[i][5]));
												} else {
											//Numbers+="NA/";
												}

												if (String.valueOf(empSearchObj[i][6]) != null
												&& !String.valueOf(empSearchObj[i][6]).equals("")) {
											Numbers1 += ""
													+ Utility.checkNull(String
													.valueOf(empSearchObj[i][6]));
												} else {
											//Numbers+="NA/";
												}
												if (String.valueOf(empSearchObj[i][9]) != null
												&& !String.valueOf(empSearchObj[i][9]).equals("")) {
											Numbers2 += ""
													+ Utility.checkNull(String
													.valueOf(empSearchObj[i][9]));
												} else {
											//Numbers+="NA";
												}
									%>
									<td class="border" width="15%"><%=Utility.checkNull(Numbers)%>
									<br><%=Utility.checkNull(Numbers1)%><br><%=Utility.checkNull(Numbers2)%>
									</td>
									<%	
										}
										
										if(Utility.checkNull(String.valueOf(empDirConfObj[0][8])).equals("Y"))
										{
								 	%>
									<%
												String extensionNo = "";
												if (String.valueOf(empSearchObj[i][7]).equals("0")) {
													
													 
											extensionNo = "";
												} else {
												 
											extensionNo = String.valueOf(empSearchObj[i][7]);
												}
									%>
									<td class="border" width="10%">&nbsp; <%=Utility.checkNull(extensionNo)%></td>
									<%		
										}
									}
								%>





								</tr>

								<%
									}

									}
								%>
							</s:if>
							<s:else>
								<tr align="center">
									<td colspan="7" class="border" width="100%"><font
										color="red"><strong>No Data to display</strong></font></td>
								</tr>
							</s:else>
						</table>
						</td>

					</tr>
				</s:if>
			</table>

		</s:form></td>

	</tr>

</table>



<script>

function callMyAction(actionName)
{ 
//alert(actionName);
	document.getElementById('paraFrm').action ='<%=request.getContextPath()%>/portal/EmployeePortal_getMenuSearch.action?actionName='+actionName;
	document.getElementById('paraFrm').submit();
}


	// Paging
	
	function callPageMenu(id, pageImg, totalPageHid, action) {		
		
		pageNo = document.getElementById('pageNoMenuField').value;	
		actPage = document.getElementById('myPageMenu').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoMenuField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoMenuField').value = actPage;
			    document.getElementById('pageNoMenuField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoMenuField').value=actPage;
			    document.getElementById('pageNoMenuField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoMenuField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoMenuField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoMenuField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoMenuField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoMenuField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoMenuField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageMenu').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 
	}
	

	 
function callPageTextMenu(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoMenuField').value;
		 	var actPage = document.getElementById('myPageMenu').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoMenuField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoMenuField').focus();
		     document.getElementById('pageNoMenuField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoMenuField').focus();
		     document.getElementById('pageNoMenuField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoMenuField').focus();
		      return false;
	        }
	        
	         document.getElementById('myPageMenu').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		
	}




function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
		//cell.style.backgroundColor="#ffffff";
	}
	
		function newRowColor(cell) {
	//	cell.style.backgroundColor="#E2F2FE";
		//cell.style.cursor='hand';
		 cell.className='Cell_bg_first'; 
		//cell.className='onOverCell';
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}


function callEmployeePage(empCode)
{ 
	try{
	var searchType=document.getElementById('menuoremployee').value;
	var searchText=document.getElementById('searchText').value;

	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/portal/EmployeePortal_getEmployeeDetails.action?empCode='+empCode+'&searchType='+searchType+'&searchText='+searchText;
	document.getElementById('paraFrm').submit();
	}catch(e){ e.description;}
}

</script>

