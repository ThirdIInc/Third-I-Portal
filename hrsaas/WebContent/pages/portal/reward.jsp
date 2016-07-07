
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="EventData" id="paraFrm" theme="simple"
	name="paraFrmName">
	<%!String defualtAwardType="1"; 
	int length= 0;%>
	<%
		Object[][] yearObj = null;
		String yearValue = null;
		String awardType= null;
		Object[][] awardTypeObj = null;
		Object[][] awardListObj = null;
		Object[][] awardTypeNameObj = null;
		String selectedStr = "";
		String selectedStrAward = "";
		
		

		try {
			yearObj = (Object[][]) request.getAttribute("yearObj");
			yearValue = request.getParameter("yearValue");
			awardTypeObj = (Object[][]) request.getAttribute("awardTypeObj");
			awardListObj = (Object[][]) request.getAttribute("awardListObj");
			awardTypeNameObj = (Object[][]) request.getAttribute("awardTypeNameObj");
			selectedStr = (String) request.getAttribute("selectedTab");
			selectedStrAward = (String) request.getAttribute("selectedStrAward");
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
	<table width="100%" border="0" align="left" cellpadding="0"
		bgcolor="white" cellspacing="0">
		<input type="hidden" id="yearValue" value="<%=yearValue%>" />
		<s:hidden name="checkSelectTab" id="checkSelectTab"
			value="<%=selectedStr%>" />
		<s:hidden name="checkSelectTabReward" id="checkSelectTabReward"
			value="<%=selectedStrAward%>" />
		<tr>
			<td width="57%" valign="top">
			<table width="97%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="100%" colspan="2">&nbsp;</td>
				</tr>
				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td width="100%">
					<div id="portalTab" style="vertical-align: bottom">
					<ul class="red1 a1 border">
						<%
								if (yearObj != null && yearObj.length > 0) {
								for (int i = 0; i < yearObj.length; i++) {
						%>

						<li><a href="javascript:void(0)" id="selectId<%=i%>"
							onclick="callCurrent('<%=i%>','<%=yearObj.length%>');callYearEventPage('<%=yearObj[i][0]%>');"
							title="reward <%=yearObj[i][0] + "-" + yearObj[i][1]%>"><span><%=yearObj[i][0] + "-" + yearObj[i][1]%></span></a></li>
						<%
							}
							}
						%>
					</ul>
					</div>
					</td>
				</tr>
				<tr>
					<td height="30" class="headerDashlet">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td><strong>Rewards</strong> &nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td height="4px" class="border1">&nbsp;</td>
						</tr>
					</table>
					</td>
				</tr>

				<script>
				 
				function callAwardType(awardType){
				 //document.getElementById('yearValue').value=yearValue;
				  var selectTabreward =document.getElementById('checkSelectTabReward').value;
				  var selectTab =document.getElementById('checkSelectTab').value;			 
				  document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/portal/EventData_getRewardData.action?awardType='+awardType+'&categoryCode=6&check='+selectTab+'&checkAward='+selectTabreward;
				  document.getElementById('paraFrm').submit();
				}					
				</script>
				<tr>
					<td valign="top"><%!int y =0; %> <%
							if (awardTypeObj != null && awardTypeObj.length > 0) {
								y=awardTypeObj.length;
							for (int i = 0; i < awardTypeObj.length; i++) {
								defualtAwardType=String.valueOf(awardTypeObj[0][0]);
					%><span style="cursor: hand" id="awardTypeid<%=i%>"
						onclick="callCurrentAward('<%=i%>','<%=awardTypeObj.length%>');callAwardType('<%=awardTypeObj[i][0]%>')"><strong><%=awardTypeObj[i][1]%></strong></span>
					<span><strong> <%
 			if (i == awardTypeObj.length - 1) {

 			} else {
 %> | </strong></span> <%
 		}
 		}
 	}
 %>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%">

						<tr height="28">
							<td height="28" class="headerDashlet" colspan="5">
							<%
									if (awardTypeNameObj != null && awardTypeNameObj.length > 0) {
									out.println(awardTypeNameObj[0][0]);
								}
							%>
							</td>
						</tr>
						<tr height="20">
							<td height="20" bgcolor="#f2f2f2" width="61" class="orange1"><strong>Photo</strong></td>
							<td bgcolor="#f2f2f2" class="orange1"><strong>Name</strong></td>
							<td bgcolor="#f2f2f2" class="orange1"><strong>Department</strong></td>
							<td bgcolor="#f2f2f2" class="orange1"><strong>Branch</strong></td>
							<td bgcolor="#f2f2f2" class="orange1"><strong>Designation</strong></td>
						</tr>

						<%
								if (awardListObj != null && awardListObj.length > 0) {
								for (int i = 0; i < awardListObj.length; i++) {
						%>
						<tr height="20">
							<td height="20" class="border" width="61">
							<%
							if (String.valueOf(awardListObj[i][4]).equals("nophoto")) {
								%> <img src="../pages/portal/images/empimage.gif" width="80"
								height="80" /> <%
							 } else {
							 %> <img
								src="../pages/images/<%=session.getAttribute("session_pool")%>/award/<%=awardListObj[i][4] %>"
								width="80" height="80" /> <%
							 			}
							%>
							</td>
							<td class="border"><a href="javascript:void(0)"
								onclick="return callNextEmployee('<%=String.valueOf(awardListObj[i][5])%>');"
								class="contlink"><%=awardListObj[i][0]%></a></td>
							<td class="border"><%=awardListObj[i][1]%></td>
							<td class="border"><%=awardListObj[i][2]%></td>
							<td class="border"><%=awardListObj[i][3]%></td>
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
</s:form>
<script>
function callNextEmployee(empCode){ 
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

onload();
function onload(){
	 	var total ='<%=y%>'
		var id = document.getElementById('checkSelectTab').value;
		var id1 = document.getElementById('checkSelectTabReward').value;
		document.getElementById('selectId'+id).className='li';
		document.getElementById('selectId'+id).className='current';		
		for(var i=0; i<total; i++){
				if(id1==i)
				{
				document.getElementById('awardTypeid'+i).className='portalOnLink';
				}
				else{
				document.getElementById('awardTypeid'+i).className='contlink';
				}		
		}			 	
}
function callCurrentAward(id,num){
		try{	  
	 	for(var i=0; i<num; i++) {	 	
	 		if(document.getElementById('checkSelectTabReward').value==i){
			document.getElementById('awardTypeid'+id).className='portalOnLink';			
			}
		 	else{
		 		document.getElementById('awardTypeid'+id).className='contlink';
		 	}
		}		
		document.getElementById('checkSelectTabReward').value=id;	  
	 	}catch(e){
	 	alert(e);
	 	}
}

function callCurrent(id,num) {	
	 	try{	  
	 		for(var i=1; i<num; i++) {	 	
	 		if(document.getElementById('checkSelectTab').value==i){	 		
			document.getElementById('selectId'+i).className='li';
			document.getElementById('selectId'+id).className='current';			 
			}		 
		}		
		document.getElementById('checkSelectTab').value=id;	  
	 	}catch(e){
	 	alert(e);
	 	}		
}

	function callYearEventPage(yearValue){
					var awardType='<%=defualtAwardType%>';
					callCurrentAward('0','<%=y%>');
					   var selectTabreward =document.getElementById('checkSelectTabReward').value;
				  	   var selectTab =document.getElementById('checkSelectTab').value;			 
				  	   document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/portal/EventData_getRewardData.action?awardType='+awardType+'&categoryCode=6&check='+selectTab+'&checkAward='+selectTabreward;
				  	   document.getElementById('paraFrm').submit();
					}

</script>