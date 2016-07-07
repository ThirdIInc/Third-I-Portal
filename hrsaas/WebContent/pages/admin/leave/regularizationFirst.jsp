 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="Regularization" method="post" name="LeaveForm"
	id="paraFrm" theme="simple">

	<s:hidden name="myPageApproved" />
	<s:hidden name="status" />
	<s:hidden name="myPageRejected" />
	<s:hidden name="myPageCancelled" />
	<s:hidden name="myPageCancelRejected" />
	<s:hidden name="myPageCancelApproved" />
	
	<s:hidden name="actAddQuesPageSwipe" id="actAddQuesPageSwipe"/>
	<s:hidden name="actAddQuesPageLate" id="actAddQuesPageLate"/>
	<s:hidden name="actAddQuesPageRedressal" id="actAddQuesPageRedressal"/>
	<s:hidden name="actAddQuesPagePT" id="actAddQuesPagePT"/>
	
	<s:hidden name="pageSwipe" />
	<s:hidden name="pageLate" />
	<s:hidden name="pageRedressal" />
	<s:hidden name="pagePT" />
	
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Regularization Application</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
						</td>
						<td align="right">
							<span class="style2"><font color="red">*</font></span> Indicates Required
						</td>
					</tr>
				</table>				
			</td>			
		</tr>
		
		
		
		
		

		<!--------------------------------------  Draft LIST OF APPLICATIONS [BEGINS]----------------------------->
		<tr>
			<td>
			<table class="" width="100%">
				<tr>
					<script>
		    function setAction(listType){
		    
		     if(listType=="P"){
		     document.getElementById('paraFrm_status').value='P';
		      	document.getElementById('paraFrm').action='Regularization_onLoad.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }if(listType=="A"){
		     document.getElementById('paraFrm_status').value='A';
		      	document.getElementById('paraFrm').action='Regularization_approve.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		     if(listType=="B"){
		     document.getElementById('paraFrm_status').value='B';
		      	document.getElementById('paraFrm').action='Regularization_sendBack.action';
		      	document.getElementById('paraFrm').submit();
		      
		     }
		     
		     if(listType=="R"){
		        document.getElementById('paraFrm_status').value='R';
		      	document.getElementById('paraFrm').action='Regularization_reject.action';
		      	document.getElementById('paraFrm').submit();
		        
		     } 
		     
		    }
		   </script>
					<td><a href="#" onclick="setAction('P')">Pending
					Application</a> | <a href="#" onclick="setAction('B')">SendBack
					List</a> | <a href="#" onclick="setAction('A')">Approved
					List</a> |  <a href="#" onclick="setAction('R')">Rejected
					List</a></td>
				</tr>
			</table>
			</td>
		</tr>

		<!--------------------------------------  PENDING LIST OF APPLICATIONS [BEGINS]----------------------------->

		<s:if test="%{listType == 'pending'}">

			<!--------- SWIPE CARD MISS ------->
		<%!int totalSwipe = 0;%>
		<s:if test="swipeList">
			<tr>
				<td>
				<table width="100%" class="formbg">					
					<tr id="pageList">
							<td width="100%" colspan="4">
								<table width="99%" border="0" align="center" cellpadding="1"
								cellspacing="1">
									<tr>
										<%!int swipeAddListPages =0;%>
										<% try {
											swipeAddListPages =Integer.valueOf(String.valueOf(request.getAttribute("swipeTotalPages")));
											} catch (Exception e) {
												swipeAddListPages=0;
										 	}
										%>
										<td><b>Swipe Miss Application</b></td>
                                        <td align="right" colspan="3" ><b>Page:</b>
						 					<a href="#" onclick="callAddQuesPage('1','F','SR');"  >
											<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
											</a>&nbsp;
											<a href="#" onclick="callAddQuesPage('P','P','SR');" >
												<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
											</a> 
							   				 <input type ="text" name="addQuesPageNoFieldSwipe" id="addQuesPageNoFieldSwipe" theme="simple" size="3"    onkeypress="callPageAddQues(event);return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=swipeAddListPages %>
						 		 			<a href="#" onclick="callAddQuesPage('N','N','SR')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callAddQuesPage('<%=swipeAddListPages %>','L','SR');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
											</a>
										</td>
		      	 				 </tr> 
							</table>
						</td>
					</tr>
					<tr>
						<td>
						<table width="100%" class="sorttable">
							<tr>
								<td class="formth" width="5%" nowrap="nowrap"><b><label class="set"
									name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
								</td>
								<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
									name="employee.id" id="employee.id1"
									ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
								</td>
								<td class="formth" width="40%" nowrap="nowrap"><b><label class="set"
									name="employee.name" id="employee.name1"
									ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
								</td>
								<td class="formth" width="20%" nowrap="nowrap"><b><label class="set"
									name="appdate" id="appdate1" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
								</td>
								
								<td class="formth" nowrap="nowrap"><b><label class="set"
									name="applied.for" id="applied.for" ondblclick="callShowDiv(this);"><%=label.get("applied.for")%></label></b> </td>
								<td class="formth" width="20%" nowrap="nowrap"><b>View Application</b></td>

							</tr>

							<%
							int i = 1;
							%>
							<%
															int h1 = 1;
															int f1 = 0;
													%>
														
							<s:iterator value="swipeList">
								<tr id="swipeRow<%=f1%>" style="display: none;">
									<td class="sortableTD" width="5%" align="center"><%=i%></td>
									<td class="sortableTD" width="15%" align="center"><s:hidden
										name="levAppStatus" /> <s:property value="empToken" /></td>
									<td class="sortableTD" width="40%" ><s:property
										value="empName" /></td>
									<td class="sortableTD" width="20%" align="center"><s:property
										value="applicationDate" /></td>
									<td class="sortableTD">
									<s:select disabled="true" id="ctrlShow" name="applyForDecode" value="%{applyFor}" list="#{'1':'Forgot Flash card','2':'Forgotten to Flash Flash','3':'Special Sanction','4':'Flash Card Not Issued','5':'Swipe System Not Working On My System','6':'Swipe System Not Loading','7':'Swipe System Capturing Incorrect Data','8':'Swipe System - Forget To LOGIN / LOGOUT','9':'Swipe System - Forget To Bring Access Card','10':'Swipe System - Others','LR':'Late Regularization','RR':'Redressal'}">
									</s:select>
									</td>
									<td class="sortableTD" width="20%" align="center">
									<input
										type="button"  class="token" id="ctrlShow"
										value=" View Application"
										onclick="viewDetails('<s:property value="swipeAppCode"/>','<s:property value="applicationType"/>','SR')" /></td>
								</tr>
								<%
								i++;
								%>
								<%
																h1++;
																f1++;
														%>
							</s:iterator>

							<%totalSwipe = f1;%>
							<input type="hidden" name="count" id="count" value="<%=f1%>" />
							<%
							if (i == 1) {
							%>
							<tr align="center">
								<td colspan="6" class="sortableTD" width="100%"><font
									color="red">No Data to display</font></td>
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
			</s:if>
		<!--------- LATE REGULARIZATION ------->
			<%!int totalLate = 0;%>
			<s:if test="regList">
			
			<tr>
				<td>
				<table width="100%" class="formbg">					
					<tr >
							<td width="100%" colspan="4">
								<table width="99%" border="0" align="center" cellpadding="1"
								cellspacing="1">
									<tr>
										<%!int lateAddListPages =0;%>
										<% try {
											lateAddListPages =Integer.valueOf(String.valueOf(request.getAttribute("lateTotalPages")));
											} catch (Exception e) {
												lateAddListPages=0;
										 	}
										%>
										<td><b>Late Regularization Application</b></td>
                                        <td align="right" colspan="3" ><b>Page:</b>
						 					<a href="#" onclick="callAddQuesPageLate('1','F','LR');"  >
											<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
											</a>&nbsp;
											<a href="#" onclick="callAddQuesPageLate('P','P','LR');" >
												<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
											</a> 
							   				 <input type ="text" name="addQuesPageNoFieldLate" id="addQuesPageNoFieldLate" theme="simple" size="3"    onkeypress="callPageAddQuesLate(event);return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=lateAddListPages %>
						 		 			<a href="#" onclick="callAddQuesPageLate('N','N','LR')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callAddQuesPageLate('<%=lateAddListPages %>','L','LR');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
											</a>
										</td>
		      	 				 </tr> 
							</table>
						</td>
					</tr>
					
					
					
					<tr>
						<td width="100%">
						<table width="100%" class="sorttable">
							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%" nowrap="nowrap"><b><label class="set"
											name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
											name="employee.id" id="employee.id2"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%" nowrap="nowrap"><b><label class="set"
											name="employee.name" id="employee.name2"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%" nowrap="nowrap"><b><label class="set"
											name="appdate" id="appdate2" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%" nowrap="nowrap"><b>View Application</b></td>

									</tr>

									<%
									int me = 0;
									%>
									
									<s:iterator value="regList">
										<tr id="lateRow<%=me%>" style="display: none;">
											<td class="sortableTD" width="5%" align="center"><%=me+1%></td>
											<td class="sortableTD" width="15%" align="center"> <s:property
												value="empToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="empName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="applicationDate" /></td>
											<td class="sortableTD" width="20%" align="center"><input
												type="button" name="view_Details" class="token" id="ctrlShow"
												value=" View Application "
												onclick="viewDetails('<s:property value="lateAppCode"/>','<s:property value="applicationType"/>','LR')" /></td>
										</tr>
										<%
										me++;
										%>
									</s:iterator>
							<%totalLate = me;%>
							
									<%
									if (me == 0) {
									%>
									<tr align="center">
										<td colspan="6" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
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
				</td>
			</tr>
</s:if>
		<!--------- REDRESSAL APPLICATION ------->
		<%!int totalRedressal = 0;%>
		<s:if test="list">
			<tr>
				<td>
				<table width="100%" class="formbg">
					
					
						<tr >
							<td width="100%" colspan="4">
								<table width="99%" border="0" align="center" cellpadding="1"
								cellspacing="1">
									<tr>
										<%!int redressalAddListPages =0;%>
										<% try {
											redressalAddListPages =Integer.valueOf(String.valueOf(request.getAttribute("redressalTotalPages")));
											} catch (Exception e) {
												redressalAddListPages=0;
										 	}
										%>
										<td><b>Redressal Applications</b></td>
                                        <td align="right" colspan="3" ><b>Page:</b>
						 					<a href="#" onclick="callAddQuesPageRedressal('1','F','LR');"  >
											<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
											</a>&nbsp;
											<a href="#" onclick="callAddQuesPageRedressal('P','P','LR');" >
												<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
											</a> 
							   				 <input type ="text" name="addQuesPageNoFieldRedressal" id="addQuesPageNoFieldRedressal" theme="simple" size="3"    onkeypress="callPageAddQuesRedressal(event);return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=redressalAddListPages %>
						 		 			<a href="#" onclick="callAddQuesPageLate('N','N','LR')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callAddQuesPageRedressal('<%=redressalAddListPages %>','L','LR');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
											</a>
										</td>
		      	 				 </tr> 
							</table>
						</td>
					</tr>
					
					<tr>
						<td>
						<table width="100%" class="sorttable" border="0">

							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%" nowrap="nowrap"><b><label class="set"
											name="sno" id="sno3" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
											name="employee.id" id="employee.id3"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%" nowrap="nowrap"><b><label class="set"
											name="employee.name" id="employee.name3"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%" nowrap="nowrap"><b><label class="set"
											name="appdate" id="appdate3" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%" nowrap="nowrap"><b>View Application</b></td>

									</tr>

									<%
									int b = 1;
									int rrrr=0;
									%>
									<s:iterator value="list">
										<tr id="redressalRow<%=rrrr%>" style="display: none;">
											<td class="sortableTD" width="5%" align="center"><%=b%></td>
											<td class="sortableTD" width="15%" align="center"><s:property
												value="empToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="empName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="applicationDate" /></td>
											<td class="sortableTD" width="20%" align="center"><input
												type="button" name="view_Details" class="token" id="ctrlShow"
												value=" View Application "
												onclick="viewDetails('<s:property value="redressalAppCode"/>','<s:property value="applicationType"/>','RR')" /></td>
										</tr>
										<%
										b++;rrrr++;
										%>
									</s:iterator>
								<%totalRedressal = rrrr;%>
									<%
									if (b == 1) {
									%>
									<tr align="center">
										<td colspan="6" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
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
				</td>
			</tr>
			
			</s:if>
		<%!int totalPT = 0;%>	
			<s:if test="ptList">
			
			
			<tr>
				<td>
				<table width="100%" class="formbg">
					
					<tr >
							<td width="100%" colspan="4">
								<table width="99%" border="0" align="center" cellpadding="1"
								cellspacing="1">
									<tr>
										<%!int ptAddListPages =0;%>
										<% try {
											ptAddListPages =Integer.valueOf(String.valueOf(request.getAttribute("ptTotalPages")));
											} catch (Exception e) {
												ptAddListPages=0;
										 	}
										%>
										<td><b>Personal Time Applications</b></td>
                                        <td align="right" colspan="3" ><b>Page:</b>
						 					<a href="#" onclick="callAddQuesPagePT('1','F','LR');"  >
											<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
											</a>&nbsp;
											<a href="#" onclick="callAddQuesPagePT('P','P','LR');" >
												<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
											</a> 
							   				 <input type ="text" name="addQuesPageNoFieldPT" id="addQuesPageNoFieldPT" theme="simple" size="3"    onkeypress="callPageAddQuesPT(event);return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=ptAddListPages %>
						 		 			<a href="#" onclick="callAddQuesPageLate('N','N','LR')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
											</a>&nbsp;
											<a href="#" onclick="callAddQuesPagePT('<%=ptAddListPages %>','L','LR');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
											</a>
										</td>
		      	 				 </tr> 
							</table>
						</td>
					</tr>
					
					<tr>
						<td>
						<table width="100%" class="sorttable" border="0">

							<tr>
								<td>
								<table width="100%" class="sorttable">
									<tr>
										<td class="formth" width="5%" nowrap="nowrap"><b><label class="set"
											name="sno" id="sno3" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
										</td>
										<td class="formth" width="15%" nowrap="nowrap"><b><label class="set"
											name="employee.id" id="employee.id3"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
										</td>
										<td class="formth" width="40%" nowrap="nowrap"><b><label class="set"
											name="employee.name" id="employee.name3"
											ondblclick="callShowDiv(this);"><%=label.get("employee.name")%></label></b>
										</td>
										<td class="formth" width="20%" nowrap="nowrap"><b><label class="set"
											name="appdate" id="appdate3" ondblclick="callShowDiv(this);"><%=label.get("appdate")%></label></b>
										</td>
										<td class="formth" width="20%" nowrap="nowrap"><b>View Application</b></td>

									</tr>

									<%
									int pt = 1;
									int pp=0;
									%>
									<s:iterator value="ptList">
										<tr id="ptRow<%=pp%>" style="display: none;">
											<td class="sortableTD" width="5%" align="center"><%=pt%></td>
											<td class="sortableTD" width="15%" align="center"><s:property
												value="empToken" /></td>
											<td class="sortableTD" width="40%"><s:property
												value="empName" /></td>
											<td class="sortableTD" width="20%" align="center"><s:property
												value="applicationDate" /></td>
											<td class="sortableTD" width="20%" align="center"><input
												type="button" name="view_Details" class="token" id="ctrlShow"
												value=" View Application "
												onclick="viewDetails('<s:property value="personalTimeCode"/>','<s:property value="applicationType"/>','PT')" /></td>
										</tr>
										<%
										pt++;pp++;
										%>
									</s:iterator>
						
						<%totalPT = pp;%>	
						
									<%
									if (pt == 1) {
									%>
									<tr align="center">
										<td colspan="6" class="sortableTD" width="100%"><font
											color="red">No Data to display</font></td>
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
				</td>
			</tr>
			
			
			</s:if>
			
			
			
		</s:if>
		
		<s:if test="%{listType == 'approved'}">	
		</s:if>

		
		<s:if test="%{listType == 'cancelled'}">
			
		</s:if>
		
		<s:if test="%{listType == 'rejected'}">			

		</s:if>

		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td>
							<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
						</td>
						<td align="right">
						</td>
					</tr>
				</table>				
			</td>			
		</tr>
	
	</table>





</s:form>


<script>

var recCount = 10;


 		function viewDetails(appCode,status,type){ 	     	 		  
		      	 document.getElementById('paraFrm').action='Regularization_viewApplication.action?appCode='+appCode+'&status='+status+'&type='+type;
		       
		      	 document.getElementById('paraFrm').submit();
		    }



	function addapplicationFun() {
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'Regularization_addNewApplication.action';
		document.getElementById('paraFrm').submit();
  	}


try{
			onLoad();
	}
	catch(e){
	
	}		
			
			function onLoad(){
					var swipeCount = '<%=totalSwipe%>';
					var lateCount = '<%=totalLate%>';						
					var redressalCount = '<%=totalRedressal%>';	
						var ptCount = '<%=totalPT%>';						
					
					if(document.getElementById('paraFrm_pageSwipe').value=='true'){
						for(var i=0;i<eval(swipeCount);i++){
							if(i<recCount){
								document.getElementById("swipeRow"+i).style.display = '';
							}
						} 
						document.getElementById('addQuesPageNoFieldSwipe').value = 1;
						document.getElementById('actAddQuesPageSwipe').value = 1;
					}
					
					if(document.getElementById('paraFrm_pageLate').value=='true'){							
						for(var i=0;i<eval(lateCount);i++){
							if(i<recCount){
								document.getElementById("lateRow"+i).style.display = '';
							}
						} 
						document.getElementById('addQuesPageNoFieldLate').value = 1;
						document.getElementById('actAddQuesPageLate').value = 1;
					}	
				
					if(document.getElementById('paraFrm_pageRedressal').value=='true'){	
						for(var i=0;i<eval(redressalCount);i++){
							if(i<recCount){
								document.getElementById("redressalRow"+i).style.display = '';
							}
						} 
						
						document.getElementById('addQuesPageNoFieldRedressal').value = '1';
						document.getElementById('actAddQuesPageRedressal').value = '1';
					}	
					
					if(document.getElementById('paraFrm_pagePT').value=='true'){		
						for(var i=0;i<eval(ptCount);i++){
							if(i<recCount){
								document.getElementById("ptRow"+i).style.display = '';
							}
						} 
						
						document.getElementById('addQuesPageNoFieldPT').value = '1';
						document.getElementById('actAddQuesPagePT').value = '1';
					}	
						
			}


//==================Add question page numbers==================================//
function callPageAddQues(id){
	try{
	
		var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key!=13) 
			{
				return false; 
			}
	
		var pageNoField = document.getElementById('addQuesPageNoFieldSwipe').value;
		var trCount = '<%=totalSwipe%>';
		totalPage ='<%=swipeAddListPages %>';
		var actPage = document.getElementById('actAddQuesPageSwipe').value;
		if(Number(pageNoField)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('addQuesPageNoFieldSwipe').value = actPage;
			     document.getElementById('addQuesPageNoFieldSwipe').focus();
				 return false;
			    } //end of if
		  if(Number(totalPage)<Number(pageNoField))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			     document.getElementById('addQuesPageNoFieldSwipe').value = actPage;
			     document.getElementById('addQuesPageNoFieldSwipe').focus();
				 return false;
			    } //end of if
	 
		
		document.getElementById('actAddQuesPageSwipe').value = pageNoField;
		
		for(i=0;i<trCount;i++){
			document.getElementById("swipeRow"+i).style.display = 'none';
		}
		j=(pageNoField-1)*recCount+recCount;
		for(i=j-recCount;i<j;i++){
			if(i < trCount){
				document.getElementById("swipeRow"+i).style.display = '';
			} //end of if
		} //end of loop
		
	}catch(e){
	 alert(e);
	} //end of catch
} //end of method



			function callAddQuesPage(id,pageImg,type){
	 pageNo =document.getElementById('addQuesPageNoFieldSwipe').value;	
 	 totalPage ='<%=swipeAddListPages %>';
 	 var actPage = document.getElementById('actAddQuesPageSwipe').value;
 	  if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('addQuesPageNoFieldSwipe').focus();
				 return false;
		        } //end of if
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('addQuesPageNoFieldSwipe').value=actPage;
			     document.getElementById('addQuesPageNoFieldSwipe').focus();
				 return false;
			    } //end of if
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('addQuesPageNoFieldSwipe').value=actPage;
			     document.getElementById('addQuesPageNoFieldSwipe').focus();
				 return false;
			    } //end of if
		 } //end of if 	 
		 
		  if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('addQuesPageNoFieldSwipe').focus();
	         return false;
	        } 
	         document.getElementById('addQuesPageNoFieldSwipe').value = 1;
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('addQuesPageNoFieldSwipe').focus();
	         return false;
	        } 
	         document.getElementById('addQuesPageNoFieldSwipe').value = totalPage;
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('addQuesPageNoFieldSwipe').focus();
	         return false;
	        }  
	          document.getElementById('addQuesPageNoFieldSwipe').value = eval(pageNo)-eval(1);
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('addQuesPageNoFieldSwipe').focus();
	         return false;
	        }  
	          document.getElementById('addQuesPageNoFieldSwipe').value = eval(pageNo)+eval(1);
       }
      var pageNoField = document.getElementById('addQuesPageNoFieldSwipe').value;
      document.getElementById('actAddQuesPageSwipe').value = pageNoField;
        var trCount = '<%=totalSwipe%>';
		for(i=0;i<trCount;i++){
			document.getElementById("swipeRow"+i).style.display = 'none';
		}
		j=(pageNoField-1)*recCount+recCount;
		for(i=j-recCount;i<j;i++){
			if(i < trCount){
				document.getElementById("swipeRow"+i).style.display = '';
			} //end of if
		} //end of loop
        
} 

//LATE REGULARIZATION======================

	function callPageAddQuesLate(id){
	try{
	
		var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key!=13) 
			{
				return false; 
			}
	
		var pageNoField = document.getElementById('addQuesPageNoFieldLate').value;
		var trCount = '<%=totalLate%>';
		totalPage ='<%=lateAddListPages %>';
		var actPage = document.getElementById('actAddQuesPageLate').value;
		if(Number(pageNoField)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('addQuesPageNoFieldLate').value = actPage;
			     document.getElementById('addQuesPageNoFieldLate').focus();
				 return false;
			    } //end of if
		  if(Number(totalPage)<Number(pageNoField))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			     document.getElementById('addQuesPageNoFieldLate').value = actPage;
			     document.getElementById('addQuesPageNoFieldLate').focus();
				 return false;
			    } //end of if
	 
		
		document.getElementById('actAddQuesPageLate').value = pageNoField;
		
		for(i=0;i<trCount;i++){
			document.getElementById("lateRow"+i).style.display = 'none';
		}
		j=(pageNoField-1)*recCount+recCount;
		for(i=j-recCount;i<j;i++){
			if(i < trCount){
				document.getElementById("lateRow"+i).style.display = '';
			} //end of if
		} //end of loop
		
	}catch(e){
	 alert(e);
	} //end of catch
} //end of method
	
	
	
	
	
	function callAddQuesPageLate(id,pageImg,type){
	 pageNo =document.getElementById('addQuesPageNoFieldLate').value;	
 	 totalPage ='<%=lateAddListPages%>';
 	 var actPage = document.getElementById('actAddQuesPageLate').value;
 	  if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('addQuesPageNoFieldLate').focus();
				 return false;
		        } //end of if
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('addQuesPageNoFieldLate').value=actPage;
			     document.getElementById('addQuesPageNoFieldLate').focus();
				 return false;
			    } //end of if
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('addQuesPageNoFieldLate').value=actPage;
			     document.getElementById('addQuesPageNoFieldLate').focus();
				 return false;
			    } //end of if
		 } //end of if 	 
		 
		  if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('addQuesPageNoFieldLate').focus();
	         return false;
	        } 
	         document.getElementById('addQuesPageNoFieldLate').value = 1;
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('addQuesPageNoFieldLate').focus();
	         return false;
	        } 
	         document.getElementById('addQuesPageNoFieldLate').value = totalPage;
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('addQuesPageNoFieldLate').focus();
	         return false;
	        }  
	          document.getElementById('addQuesPageNoFieldLate').value = eval(pageNo)-eval(1);
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('addQuesPageNoFieldLate').focus();
	         return false;
	        }  
	          document.getElementById('addQuesPageNoFieldLate').value = eval(pageNo)+eval(1);
       }
      var pageNoField = document.getElementById('addQuesPageNoFieldLate').value;
      document.getElementById('actAddQuesPageLate').value = pageNoField;
        var trCount = '<%=totalLate%>';
		for(i=0;i<trCount;i++){
			document.getElementById("lateRow"+i).style.display = 'none';
		}
		j=(pageNoField-1)*recCount+recCount;
		for(i=j-recCount;i<j;i++){
			if(i < trCount){
				document.getElementById("lateRow"+i).style.display = '';
			} //end of if
		} //end of loop
        
}

	//REDRESSAL APPLICATIONS
	
	function callPageAddQuesRedressal(id){
	try{
	
		var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key!=13) 
			{
				return false; 
			}
	
		var pageNoField = document.getElementById('addQuesPageNoFieldRedressal').value;
		var trCount = '<%=totalRedressal%>';
		totalPage ='<%=redressalAddListPages %>';
		var actPage = document.getElementById('actAddQuesPageRedressal').value;
		if(Number(pageNoField)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('addQuesPageNoFieldRedressal').value = actPage;
			     document.getElementById('addQuesPageNoFieldRedressal').focus();
				 return false;
			    } //end of if
		  if(Number(totalPage)<Number(pageNoField))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			     document.getElementById('addQuesPageNoFieldRedressal').value = actPage;
			     document.getElementById('addQuesPageNoFieldRedressal').focus();
				 return false;
			    } //end of if
	 
		
		document.getElementById('actAddQuesPageRedressal').value = pageNoField;
		
		for(i=0;i<trCount;i++){
			document.getElementById("redressalRow"+i).style.display = 'none';
		}
		j=(pageNoField-1)*recCount+recCount;
		for(i=j-recCount;i<j;i++){
			if(i < trCount){
				document.getElementById("redressalRow"+i).style.display = '';
			} //end of if
		} //end of loop
		
	}catch(e){
	 alert(e);
	} //end of catch
} //end of method
	
	
	
	
	
	function callAddQuesPageRedressal(id,pageImg,type){
	 pageNo =document.getElementById('addQuesPageNoFieldRedressal').value;	
 	 totalPage ='<%=redressalAddListPages%>';
 	 var actPage = document.getElementById('actAddQuesPageRedressal').value;
 	  if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('addQuesPageNoFieldRedressal').focus();
				 return false;
		        } //end of if
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('addQuesPageNoFieldRedressal').value=actPage;
			     document.getElementById('addQuesPageNoFieldRedressal').focus();
				 return false;
			    } //end of if
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('addQuesPageNoFieldRedressal').value=actPage;
			     document.getElementById('addQuesPageNoFieldRedressal').focus();
				 return false;
			    } //end of if
		 } //end of if 	 
		 
		  if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('addQuesPageNoFieldRedressal').focus();
	         return false;
	        } 
	         document.getElementById('addQuesPageNoFieldRedressal').value = 1;
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('addQuesPageNoFieldRedressal').focus();
	         return false;
	        } 
	         document.getElementById('addQuesPageNoFieldRedressal').value = totalPage;
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('addQuesPageNoFieldRedressal').focus();
	         return false;
	        }  
	          document.getElementById('addQuesPageNoFieldRedressal').value = eval(pageNo)-eval(1);
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('addQuesPageNoFieldRedressal').focus();
	         return false;
	        }  
	          document.getElementById('addQuesPageNoFieldRedressal').value = eval(pageNo)+eval(1);
       }
      var pageNoField = document.getElementById('addQuesPageNoFieldRedressal').value;
      document.getElementById('actAddQuesPageRedressal').value = pageNoField;
        var trCount = '<%=totalRedressal%>';
		for(i=0;i<trCount;i++){
			document.getElementById("redressalRow"+i).style.display = 'none';
		}
		j=(pageNoField-1)*recCount+recCount;
		for(i=j-recCount;i<j;i++){
			if(i < trCount){
				document.getElementById("redressalRow"+i).style.display = '';
			} //end of if
		} //end of loop
        
}


		//PERSONAL TIME APPLICATION
		
		function callPageAddQuesPT(id){
	try{
	
		var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key!=13) 
			{
				return false; 
			}
	
		var pageNoField = document.getElementById('addQuesPageNoFieldPT').value;
		var trCount = '<%=totalPT%>';
		totalPage ='<%=ptAddListPages %>';
		var actPage = document.getElementById('actAddQuesPagePT').value;
		if(Number(pageNoField)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('addQuesPageNoFieldPT').value = actPage;
			     document.getElementById('addQuesPageNoFieldPT').focus();
				 return false;
			    } //end of if
		  if(Number(totalPage)<Number(pageNoField))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			     document.getElementById('addQuesPageNoFieldPT').value = actPage;
			     document.getElementById('addQuesPageNoFieldPT').focus();
				 return false;
			    } //end of if
	 
		
		document.getElementById('actAddQuesPagePT').value = pageNoField;
		
		for(i=0;i<trCount;i++){
			document.getElementById("ptRow"+i).style.display = 'none';
		}
		j=(pageNoField-1)*recCount+recCount;
		for(i=j-recCount;i<j;i++){
			if(i < trCount){
				document.getElementById("ptRow"+i).style.display = '';
			} //end of if
		} //end of loop
		
	}catch(e){
	 alert(e);
	} //end of catch
} //end of method
	
	
	
	
	
	function callAddQuesPagePT(id,pageImg,type){
	 pageNo =document.getElementById('addQuesPageNoFieldPT').value;	
 	 totalPage ='<%=ptAddListPages%>';
 	 var actPage = document.getElementById('actAddQuesPagePT').value;
 	  if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('addQuesPageNoFieldPT').focus();
				 return false;
		        } //end of if
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('addQuesPageNoFieldPT').value=actPage;
			     document.getElementById('addQuesPageNoFieldPT').focus();
				 return false;
			    } //end of if
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('addQuesPageNoFieldPT').value=actPage;
			     document.getElementById('addQuesPageNoFieldPT').focus();
				 return false;
			    } //end of if
		 } //end of if 	 
		 
		  if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('addQuesPageNoFieldPT').focus();
	         return false;
	        } 
	         document.getElementById('addQuesPageNoFieldPT').value = 1;
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('addQuesPageNoFieldPT').focus();
	         return false;
	        } 
	         document.getElementById('addQuesPageNoFieldPT').value = totalPage;
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('addQuesPageNoFieldPT').focus();
	         return false;
	        }  
	          document.getElementById('addQuesPageNoFieldPT').value = eval(pageNo)-eval(1);
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('addQuesPageNoFieldPT').focus();
	         return false;
	        }  
	          document.getElementById('addQuesPageNoFieldPT').value = eval(pageNo)+eval(1);
       }
      var pageNoField = document.getElementById('addQuesPageNoFieldPT').value;
      document.getElementById('actAddQuesPagePT').value = pageNoField;
        var trCount = '<%=totalPT%>';
		for(i=0;i<trCount;i++){
			document.getElementById("ptRow"+i).style.display = 'none';
		}
		j=(pageNoField-1)*recCount+recCount;
		for(i=j-recCount;i<j;i++){
			if(i < trCount){
				document.getElementById("ptRow"+i).style.display = '';
			} //end of if
		} //end of loop
        
}
</script>










 