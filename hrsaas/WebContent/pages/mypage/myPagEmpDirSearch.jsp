<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.itextpdf.text.log.SysoLogger"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<%
	Object serviceMenuObj[][] = (Object[][]) request.getAttribute("serviceMenulist");
	HashMap groupMap = (HashMap) request.getAttribute("groupMap");
	String requestString=(String) request.getAttribute("searchString");
	String searchTypeEmp = (String) request.getAttribute("searchType");
	String searchResult=(String) request.getAttribute("searchResult");
	
%>


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


<div align="justify"></div>
<s:form action="MypageProcessManagerAlerts" method="post"
	validate="true" id="paraFrm" theme="simple">

	<div style="float: left; width: 98%"><!-- center div starts-->

	<div style="float: left; width: 100%">

	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		height="100%">

		<%
				if (groupMap != null && groupMap.size() > 0 && searchResult.equals("menu")) {
				Set keySet = null;
				Iterator itKeyList = null;
				keySet = groupMap.keySet();
				itKeyList = keySet.iterator();
				while (itKeyList.hasNext()) {
					String key = (String) itKeyList.next();
					System.out.println("Group Name :" + key);

					Vector vect = (Vector) groupMap.get(key);
		%>


		<tr>
			<td colspan="6" style="height: 1px;"><img
				src="../pages/mypage/images/icons/<%=key.split("#")[1]%>"
				<% System.out.print("\nImage......"+key.split("#")[1]);%> width="32"
				height="32" align="absmiddle" />&nbsp;&nbsp;&nbsp;<strong><font
				style="border-bottom: 1px; border-bottom-color: #EBEBEB; font-size: 12px;"><%=key.split("#")[0]%></font>

			<%
			System.out.print("\n Image......" + key.split("#")[0]);
			%> </strong></td>
		</tr>
		<tr>
			<td colspan="6" style="height: 1px; background-color: #EBEBEB;">
			</td>
		</tr>

		<%
				for (int m = 0; m < vect.size(); m++) {
				Object[] menuObj = (Object[]) vect.get(m);
				System.out.println("MenuName :"
						+ String.valueOf(menuObj[1]));
		%>
		<%
		if (m % 3 == 3 || m % 3 == 0) {
		%>
		<tr>
			<%
			}
			%>


			<td width="5%" valign="top" style="padding-top: 5px"></td>
			<td width="25%" valign="middle" style="padding-top: 5px"><img
				align="absmiddle" src="../pages/common/css/default/images/dot.gif"><a
				class="servicelink" href="javascript:void(0);"
				title="<%=String.valueOf(menuObj[1])%>"
				onclick="callMyAction('<%=Utility.checkNull(String.valueOf(menuObj[2]))%>'); ">
			<%=Utility.checkNull(String
												.valueOf(menuObj[1]))%></a><br>
			<span class="smalltext"><%=Utility.checkNull(String
												.valueOf(menuObj[4]))%></span></td>
			<%
			if (m % 3 == 2 || m == vect.size()) {
			%>
		</tr>
		<%
		}
		%>


		<%
		}
		%>



		<tr>
			<td colspan="6">&nbsp;</td>
		</tr>

		<%
				// for service group condition check
				}// For group loop
			}// for group loop if check
			else
				if(searchResult.equals("menu"))
			{ %>
				<tr align="center">
				
							<td colspan="7" class="sortableTD" width="100%"><font
								color="red"><strong>No results found for <%=requestString%></strong></font></td>
						</tr>
				
			<% }
		%>

	</table>

	</div>

	<!-- center div starts--> <!-- center div ends--></div>




	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		align="left">


		<s:if test="empDataAvailable">

			<!-- emp table starts -->
			<s:if test="pageFlag">

			<tr>
				<td valign="bottom" class="txt">
				<table width="100%" border="0" align="right" cellpadding="0"
					cellspacing="0" class="formbg">
					<tr>
						<td><strong class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Employee
						Directory </strong></td>
						<td width="3%" valign="top" class="txt">
						<div align="right"></div>
						</td>
					</tr>
				</table>
				</td>
			</tr></s:if>



			<%
				String searchStringEmp = "";
				String resultOfSearch=(String) request.getAttribute("searchResult");
				searchTypeEmp = (String) request.getAttribute("searchType");
				searchStringEmp = (String) request.getAttribute("searchString");
				String empurl = "EmployeePortal_getHomeSearch.action?resultOfSearch="+resultOfSearch+ "&searchType=" + searchTypeEmp+ "&searchText="+ searchStringEmp+ "&random=" + Math.random();
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
							<a href="javascript:void(0);"
								onclick="callPage('1', 'F', '<%=totalPage%>', '<%=empurl%>');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp;
						    <a href="javascript:void(0);"
								onclick="callPage('P', 'P', '<%=totalPage%>', '<%=empurl%>');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a>
							<input type="text" name="pageNoField" id="pageNoField" size="3"
								value="<%=pageNo%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage%>','<%=empurl%>');return numbersOnly();" /> of <%=totalPage%>
							<a href="javascript:void(0);"
								onclick="callPage('N', 'N', '<%=totalPage%>','<%=empurl%>')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp;
						<a href="javascript:void(0);"
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
				<table width="100%" class="formbg">
				
					<s:if test="pageFlag">
					<tr height="20">
						<td height="20" width="5%" class="formth"><strong>Sr.No</strong></td>
						<td width="15%" class="formth" nowrap="nowrap"><strong>Employee
						Id</strong></td>
						<td width="25%" class="formth"><strong>Employee Name
						</strong></td>
						<%
								if (empDirConfObj != null && empDirConfObj.length > 0) {
								if (Utility.checkNull(String.valueOf(empDirConfObj[0][3]))
								.equals("Y")) {
						%>
						<td width="15%" class="formth"><strong>Designation </strong></td>
						<%
								}
								if (Utility.checkNull(String.valueOf(empDirConfObj[0][7]))
								.equals("Y")) {
						%>
						<td width="15%" class="formth"><strong>E-mail</strong></td>
						<%
								}
								if (Utility.checkNull(String.valueOf(empDirConfObj[0][9]))
								.equals("Y")
								|| Utility.checkNull(
										String.valueOf(empDirConfObj[0][10])).equals(
										"Y")) {
						%>
						<td width="15%" class="formth"><strong>Phone/Mobile
						</strong></td>
						<%
								}
								if (Utility.checkNull(String.valueOf(empDirConfObj[0][8]))
								.equals("Y")) {
						%>
						<td width="10%" class="formth"><strong>Extension </strong></td>
						<%
							}
							}
						%>




					</tr>

						<%
						int count = 0;
						%>

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
							class="tableCell1" <%}else{%> class="tableCell2" <%	}count++; %>
							onmouseover="javascript:newRowColor(this);"
							onmouseout="javascript:oldRowColor(this,<%=count%2 %>);">

							<td height="20" align="center" width="5%" class="sortableTD"><%=++srNo%></td>
							<td class="sortableTD" width="15%"><a
								href="javascript:void(0)"
								title="Click to view employee information" class="contlink"
								onclick="callEmployeePage('<%=empSearchObj[i][11]%>');"> <%=Utility.checkNull(String
									.valueOf(empSearchObj[i][0]))%> </a></td>
							<td class="sortableTD" width="25%"><a
								href="javascript:void(0)"
								title="Click to view employee information" class="contlink"
								onclick="callEmployeePage('<%=empSearchObj[i][11]%>');"> <%=Utility.checkNull(String
									.valueOf(empSearchObj[i][1]))%></a></td>
							<%
									if (empDirConfObj != null && empDirConfObj.length > 0) {
									if (Utility.checkNull(
											String.valueOf(empDirConfObj[0][3]))
											.equals("Y")) {
							%>
							<td class="sortableTD" width="15%"><%=Utility.checkNull(String
											.valueOf(empSearchObj[i][3]))%></td>
							<%
									}
									if (Utility.checkNull(
											String.valueOf(empDirConfObj[0][7]))
											.equals("Y")) {
							%>
							<td class="sortableTD" width="15%"><%=Utility.checkNull(String
											.valueOf(empSearchObj[i][10]))%></td>
							<%
									}
									if (Utility.checkNull(
											String.valueOf(empDirConfObj[0][9]))
											.equals("Y")
											|| Utility.checkNull(
											String.valueOf(empDirConfObj[0][10]))
											.equals("Y")) {
							%>
							<%
										String Numbers = "";
										String Numbers1 = "";
										String Numbers2 = "";
										if (String.valueOf(empSearchObj[i][5]) != null
										&& !String.valueOf(empSearchObj[i][5])
												.equals("")) {
											Numbers += Utility.checkNull(String
											.valueOf(empSearchObj[i][5]));
										} else {
											//Numbers+="NA/";
										}

										if (String.valueOf(empSearchObj[i][6]) != null
										&& !String.valueOf(empSearchObj[i][6])
												.equals("")) {
											Numbers1 += ""
											+ Utility.checkNull(String
											.valueOf(empSearchObj[i][6]));
										} else {
											//Numbers+="NA/";
										}
										if (String.valueOf(empSearchObj[i][9]) != null
										&& !String.valueOf(empSearchObj[i][9])
												.equals("")) {
											Numbers2 += ""
											+ Utility.checkNull(String
											.valueOf(empSearchObj[i][9]));
										} else {
											//Numbers+="NA";
										}
							%>
							<td class="sortableTD" width="15%"><%=Utility.checkNull(Numbers)%>
							<br><%=Utility.checkNull(Numbers1)%><br><%=Utility.checkNull(Numbers2)%>
							</td>
							<%
									}

									if (Utility.checkNull(
											String.valueOf(empDirConfObj[0][8]))
											.equals("Y")) {
							%>
							<%
										String extensionNo = "";
										if (String.valueOf(empSearchObj[i][7]).equals("0")) {

											extensionNo = "";
										} else {

											extensionNo = String
											.valueOf(empSearchObj[i][7]);
										}
							%>
							<td class="sortableTD" width="10%">&nbsp; <%=Utility.checkNull(extensionNo)%></td>
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
							<td colspan="7" class="sortableTD" width="100%"><font
								color="red"><strong>No results found for  <%=requestString%></strong></font></td>
						</tr>
					</s:else>
				</table>
				</td>

			</tr>
		</s:if>
	</table>

</s:form>
</body>
</html>
<script>


function callMyAction_OLD(actionName)
{ 
 	// alert(actionName);
	document.getElementById('paraFrm').action ='<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_getServiceLinkMenu.action?actionName='+actionName;
	document.getElementById('paraFrm').submit();
}

function callMyAction(actionName)
	{
 //alert("In my code:"+actionName);
	try{
	document.getElementById('paraFrm').action= '<%=request.getContextPath()%>'+actionName;
	document.getElementById("paraFrm").target="_self";
	document.getElementById('paraFrm').submit();
	}
	catch(e)
	{
		alert("Exception:"+e);
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
	var searchType='emp';
	var searchText='';
	//document.getElementById('searchText').value;

	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_getEmployeeDetails.action?empCode='+empCode+'&searchType='+searchType+'&searchText='+searchText;
	document.getElementById('paraFrm').submit();
	}catch(e){
	alert(e);
	}
}

</script>
