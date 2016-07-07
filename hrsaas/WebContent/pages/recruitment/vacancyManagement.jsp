
<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="VacancyManagement" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="show" />
	<s:hidden name="myPage" />
	<s:hidden name="modeLength" />
	<s:hidden name="vacancy" />
	<s:hidden name="publishButtonFlag" />
	<s:hidden name="closeVacancyFlag" />
	<s:hidden name="listLength"></s:hidden>
	<s:hidden name="status"></s:hidden>
	<s:hidden name="divId" />
	<s:hidden name="branchId" />
	<s:hidden name="deptId" />
	<s:hidden name="hrManagerId" />
	<s:hidden name="positionId" />
	<s:hidden name="ChkSerch" />
	<s:hidden name="searchFlag" />
	<s:hidden name="applyFilterFlag" />
	<s:hidden name="rePublishFlag" />

	<s:hidden name="hidReportView" />
	<s:hidden name="hidReportRadio" />
	<s:hidden name="reportType" />



	<table width="100%" class="formbg">
		<!-- Start header Table -->
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Vacancy
					Management </strong></td>
					<td width="3%" valign="top" class="otxt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="2">
						<tr>
							<td height="27" class="formtxt"><a href="#"
								onclick="callFun('O');">Open Vacancies </a> | <a href="#"
								onclick="callPublished('P');">Published Vacancies </a> | <a
								href="#" onclick="callPublished('C');">Closed Vacancies </a></td>
							<!--<td><input type="button" value=" Export In Xls " class="token"
							onclick="callReportForDisp('X');"></td>
								-->
						</tr>

					</table>
					<!--Table 4--></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>

					<td colspan="2" nowrap="nowrap"><strong class="text_head"><s:if
						test="searchFlag">Applied Filter</s:if><s:else>
						<label class="set" name="searchApply.filter"
							id="searchApply.filter" ondblclick="callShowDiv(this);"><%=label.get("searchApply.filter")%></label>
					</s:else></strong></td>

					<td id="showFilter" align="right" colspan="2"><input
						type="button" value="Show Filter" cssClass="token"
						onclick="return callShowFilter();"></td>

					<td id="hideFilter" align="right"><input type="button"
						value="Hide Filter" cssClass="token"
						onclick="return callHideFilter();"></td>
				</tr>
				<tr>
					<td colspan="3">
					<div id="showFilterValue">
					<table width="100%" border="0">
						<tr>
							<td width="20%"><label class="set" name="division"
								id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:textfield name="divName"
								size="25" readonly="true" /><img
								src="../pages/images/search2.gif" class="iconImage" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'VacancyManagement_f9Division.action');"></td>
							<td width="10">&nbsp;&nbsp;</td>
							<td width="15%"><label class="set" name="branch" id="branch"
								ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:textfield
								name="branchName" size="25" readonly="true" /><img
								src="../pages/images/search2.gif" class="iconImage" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'VacancyManagement_f9Branch.action');"></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" name="department"
								id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:textfield name="deptName"
								size="25" readonly="true" /><img
								src="../pages/images/search2.gif" class="iconImage" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'VacancyManagement_f9Department.action');"></td>
							<td width="10%">&nbsp;&nbsp;</td>
							<td width="15%"><label class="set" name="hiring.mgr"
								id="hiring.mgr1" ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:textfield
								name="managerName" size="25" readonly="true" /><img
								src="../pages/images/search2.gif" class="iconImage" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'VacancyManagement_f9HrManager.action');"></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" name="position"
								id="position1" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:textfield
								name="positionName" size="25" readonly="true" /><img
								src="../pages/images/search2.gif" class="iconImage" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'VacancyManagement_f9Position.action');"></td>
							<td width="10%">&nbsp;&nbsp;</td>
						</tr>
						<tr>
							<td width="20%"><label class="set" name="Req.fDate"
								id="Req.fDate" ondblclick="callShowDiv(this);"><%=label.get("Req.fDate")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:textfield name="fDate"
								size="25" onkeypress="return numbersWithHiphen();"
								maxlength="10" readonly="false" /><s:a
								href="javascript:NewCal('paraFrm_fDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
							<td width="10%">&nbsp;&nbsp;</td>
							<td width="18%"><label class="set" name="Req.tDate"
								id="Req.tDate" ondblclick="callShowDiv(this);"><%=label.get("Req.tDate")%></label>
							:</td>
							<td width="35%" nowrap="nowrap"><s:textfield name="tDate"
								size="25" onkeypress="return numbersWithHiphen();"
								maxlength="10" readonly="false" /><s:a
								href="javascript:NewCal('paraFrm_tDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>
						<tr>
							<td align="center" colspan="5"><s:submit cssClass="token"
								action="VacancyManagement_search" theme="simple"
								value="Apply Filter" onclick="return chkDate();" />&nbsp; <input
								type="button" class="reset" theme="simple"
								onclick="return calReset();" value="Reset " /></td>
						</tr>
					</table>
					<!--Table 1--></div>
					</td>
				</tr>

				<tr>
					<td colspan="2">
					<% 
		     						    String [] dispArr = (String [])request.getAttribute("dispArr"); 
		     						  if(dispArr!=null && dispArr.length >0)
		     						  {
		     							 
		     						      int k =0;
		     						      int count =0;
		     						      if(dispArr.length % 2==0)
		     						      {
		     						    	   k =dispArr.length/2;
		     						    	  
		     						    	   
		     						      }else
		     						      {
		     						    	 k =(dispArr.length/2)+1;
		     						    	 
		     						      } 
		     						  %>

					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id="enableFilterValue">

						<% for(int m=0;m<k;m++) 
									    	  {%>
						<tr>
							<% if(count<dispArr.length){ %>

							<td width="20%" height="22" class="formtext"><%=dispArr [count]%>
							</td>
							<% count++;%>
							<%} %>
							<% if(count<dispArr.length){ %>
							<td width="20%" height="22" class="formtext"><%=dispArr [count]%>
							</td>
							<% count++;%>
							<%} %>
						</tr>
						<% }
		     						  } // end of iff
									      %>
						<tr>

							<td align="center" colspan="5">&nbsp; <input type="button"
								class="reset" theme="simple" onclick="return callClear();"
								value="Clear Filter" /> <input type="button" class="token"
								theme="simple" onclick="return callEditFilter();"
								value="Edit Filter" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td class="formtxt"><strong>
							<%String status = (String)request.getAttribute("stat");
                     
	                    	if(status!=null){out.println(status);}
	                    	else{out.println("Open Vacancies");}%>
							</strong></td>
							<%
					int totalPage = (Integer) request.getAttribute("totalPage");
					int pageNo = (Integer) request.getAttribute("PageNo");
					%>
							<td align="right"><s:if test="modeLength">
								<b>Page:</b>
								<input type="hidden" name="totalPage" id="totalPage"
									value="<%=totalPage%>">
								<a href="#" onclick="callPage('1','F','<%=status %>');"> <img
									title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp;
									<a href="#" onclick="callPage('P','P','<%=status %>');"> <img
									title="Previous Page" src="../pages/common/img/previous.gif"
									width="10" height="10" class="iconImage" /> </a>
								<input type="text" name="pageNoField" id="pageNoField"
									theme="simple" size="3" value="<%= pageNo%>"
									onkeypress="callPageText(event,'<%=status %>');return numbersOnly()"
									maxlength="10" /> of <%=totalPage%>
								<a href="#" onclick="callPage('N','N','<%=status %>')"> <img
									title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp;
										<a href="#"
									onclick="callPage('<%=totalPage%>','L','<%=status %>');">
								<img title="Last Page" src="../pages/common/img/last.gif"
									width="10" height="10" class="iconImage" /> </a>
							</s:if></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" width="100%">
					<%
			try {
			%>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td colspan="3" width="100%">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="sortable">
								<tr>
									<td width="5%" valign="top" class="formth" nowrap="nowrap"><b><label
										class="set" name="serial.no" id="serial.no"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
									<td width="10%" valign="top" class="formth"><b><label
										class="set" name="reqs.code" id="reqs.code"
										ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
									<td width="20%" valign="top" class="formth"><b><label
										class="set" name="position" id="position"
										ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
									<!-- 	<td width="20%" valign="top" class="formth" nowrap="nowrap"><b><label  class = "set" name="applied.by" id="applied.by" 
			            		ondblclick="callShowDiv(this);"><%=label.get("applied.by")%></label></b></td>-->
									<td width="20%" valign="top" class="formth" nowrap="nowrap"><b><label
										class="set" name="hiring.mgr" id="hiring.mgr"
										ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
									<td width="5%" valign="top" class="formth"><b><label
										class="set" name="reqs.date" id="reqs.date"
										ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label></b></td>
									<td width="5%" valign="top" class="formth"><b><label
										class="set" name="noofvacan" id="noofvacan"
										ondblclick="callShowDiv(this);"><%=label.get("noofvacan")%></label></b></td>
									<td width="10%" valign="top" class="formth"><b><label
										class="set" name="required.date" id="required.date"
										ondblclick="callShowDiv(this);"><%=label.get("required.date")%></label></b></td>

									<td width="10%" valign="top" class="formth"><b><label
										class="set" name="Publish" id="Publish"
										ondblclick="callShowDiv(this);"><%=label.get("Publish")%></label></b></td>
									<s:if test="closeVacancyFlag"></s:if>
									<s:else>
										<td width="10%" valign="top" class="formth"><b><label
											class="set" name="action" id="action"
											ondblclick="callShowDiv(this);"><%=label.get("action")%></label></b>
										</td>
									</s:else>
								</tr>
								<s:if test="noData">
									<tr>
										<td width="100%" colspan="7" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
								<%
							int count = 0;
							%>
								<%!int d = 0;%>
								<%
									int cnt = pageNo * 20 - 20;
									int i = 0;
							%>
								<s:iterator value="list">
									<tr <%if(count%2==0){
									%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										title="Double click for view Requisition"
										ondblclick="javascript:viewRequisition('<s:property value="reqCode" />','<%=status %>');">
										<td class="sortableTD" width="5%" nowrap="nowrap"
											align="center"><%=++cnt%> <%
										++i;
										%>
										</td>
										<td width="10%" nowrap="nowrap" class="sortableTD">&nbsp;<s:property
											value="reqName" /><s:hidden name="reqCode" /></td>
										<td width="20%" nowrap="nowrap" class="sortableTD"><s:hidden
											name="vacanDtlCode" /> <s:hidden name="appliedEmpId" /><s:hidden
											name="hiringEmpId" /> <s:hidden name="recruitmentInternal" /><s:hidden
											name="recruitmentExternal" /><s:property value="position" /></td>
										<!-- 	<td class="sortableTD" width="20%">&nbsp;<s:property
											value="appliedBy" /></td>-->
										<td width="20%" nowrap="nowrap" class="sortableTD">&nbsp;<s:property
											value="hiringMgr" /></td>
										<td width="15%" nowrap="nowrap" class="sortableTD"
											align="center">&nbsp;<s:property value="reqDate" /></td>
										<td width="5%" nowrap="nowrap" class="sortableTD"
											align="center">&nbsp;<s:property value="noOfVacancies" /></td>
										<td width="15%" nowrap="nowrap" class="sortableTD"
											align="center">&nbsp;<s:property value="requiredDate" /></td>

										<s:if test="publishButtonFlag">
											<td class="sortableTD" width="10%" nowrap="nowrap"
												align="center"><input type="button" name="publish"
												class="token" value="View"
												onclick="viewModePublish('<s:property value="reqCode" />','<s:property
												value="position" />','<s:property value="appliedBy" />','<s:property
												value="hiringMgr" />','<s:property value="reqDate" />','<s:property
												value="noOfVacancies" />','<s:property
												value="requiredDate" />','<s:property value="vacanDtlCode" />','<s:property
												value="reqName" />','<s:property value="appliedEmpId"/>','<s:property value="hiringEmpId"/>','<%=status %>','<s:property value="publishButtonFlag"/>')" /></td>
											<s:if test="closeVacancyFlag"></s:if>
											<s:else>
												<s:hidden name="vacancyPublishCode" />
												<td><input type="button" name="cancelVacancy"
													class="token" value="Cancel Vacancies"
													onclick="viewCancelVacanciesList('<s:property value="reqCode" />','<s:property value="vacancyPublishCode"/>','<s:property value="vacanDtlCode" />')" />
												</td>
											</s:else>
										</s:if>
										<s:else>
											<td class="sortableTD" width="10%" nowrap="nowrap"
												align="center"><input type="button" name="publish"
												class="token" value="Publish"
												onclick="callPublish('<s:property value="reqCode" />','<s:property
												value="position" />','<s:property value="appliedBy" />','<s:property
												value="hiringMgr" />','<s:property value="reqDate" />','<s:property
												value="noOfVacancies" />','<s:property
												value="requiredDate" />','<s:property value="vacanDtlCode" />','<s:property
												value="reqName" />','<s:property value="appliedEmpId"/>','<s:property value="hiringEmpId"/>',
													'<s:property value="recruitmentInternal"/>','<s:property value="recruitmentExternal"/>','<%=status %>','<s:property value="publishButtonFlag"/>')" /></td>
											<td><input type="button" name="Close Vacancies"
												class="token" value="Close Vacancies"
												onclick="closeRequisition('<s:property value="reqCode" />','<s:property value="noOfVacancies" />')" />
											</td>
										</s:else>
									</tr>

								</s:iterator>
								<%
							d = i;
							%>
							</table>

							<%
						} catch (Exception e) {
						}
					%>
							</td>
						</tr>
					</table>
					<!--Table 5--></td>
				</tr>

				<!--end of vacancy listing-->
				<tr align="left">
					<td colspan="3" width="100%" align="Right"><s:if
						test="modeLength">
						<b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" />
					</s:if></td>
				</tr>
				</tabel>
			</table>
			</td>
		</tr>
	</table>

</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>

function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first';

	    }
	
	function oldRowColor(cell,val) {
	
	cell.className='Cell_bg_second';
		
	}

	function viewModePublish(reqCode,position,appBy,hirManager,requiDt,noVacancy,reqDate,vacanDtlCode,reqName,appEmpId,hiringEmpId,status,flag){	
	  status=status.charAt(0);
	  document.getElementById("paraFrm").action='VacancyPublish_viewModePublish.action?code='+reqCode+'&position='+position+'&appliedBy='+appBy+'&hiringMgr='+hirManager+'&requiDate='+reqDate+'&noOfVacan='+noVacancy+'&reqDate='+requiDt+'&vacanDtlCode='+vacanDtlCode+'&reqName='+reqName+'&appEmpId='+appEmpId+'&hiringEmpId='+hiringEmpId+'&formAction=VacancyManagement_backPublish.action&statusKey='+status+'&flagVal='+flag;
	  document.getElementById("paraFrm").submit();
	}
	
	function viewCancelVacanciesList(requisitionCode,vacancyPublishCode,vacancyDetailCode){	
	  document.getElementById("paraFrm").action='VacancyPublish_viewCancelVacanciesList.action?requisitionCode='+requisitionCode+'&vacancyPublishCode='+vacancyPublishCode+'&vacancyDetailCode='+vacancyDetailCode;
	  document.getElementById("paraFrm").submit();
	}
	
	function closeRequisition(requisitionCode,noOfVacancies) {
	var con = confirm("Do you really want to close this requisition?");
		if(con){
			document.getElementById("paraFrm").action='VacancyManagement_updateRequisitionStatus.action?requisitionCode='+requisitionCode+'&noOfVacancies='+noOfVacancies;
	  		document.getElementById("paraFrm").submit();
		}else {
			return false;
		}
		
	}
	function callPublish(reqCode,position,appBy,hirManager,requiDt,noVacancy,reqDate,vacanDtlCode,reqName,appEmpId,hiringEmpId,recInternal,recExternal,status,flag){
	 
	 if(status=="null" || status==""){
	 	status="O"
	 }	else{	
	      // status="C"
	  status=status.charAt(0);
	}	
	  document.getElementById("paraFrm").action='VacancyPublish_viewPublish.action?code='+reqCode+'&position='+position+'&appliedBy='+appBy+'&hiringMgr='+hirManager+'&requiDate='+requiDt+'&noOfVacan='+noVacancy+'&reqDate='+reqDate+'&vacanDtlCode='+vacanDtlCode+'&reqName='+reqName+'&appEmpId='+appEmpId+'&hiringEmpId='+hiringEmpId+'&recInternal='+recInternal+'&recExternal='+recExternal+'&formAction=VacancyManagement_backPublish.action&statusKey='+status+'&flagVal='+flag;
	  document.getElementById("paraFrm").submit();
	 
	}
	
	function callFun(status){
	    
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm_myPage').value="";
		document.getElementById('paraFrm_vacancy').value=status;
		document.getElementById("paraFrm").action='VacancyManagement_callstatus.action?status='+status;
	    document.getElementById("paraFrm").submit();
	}
	
	function callPublished(status){
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm_myPage').value="";
		document.getElementById('paraFrm_vacancy').value=status;
	    document.getElementById("paraFrm").action='VacancyManagement_callPublishStatus.action?status='+status;
	    document.getElementById("paraFrm").submit();
	}
	
	
	function viewRequisition(reqCode,status){
		status=status.charAt(0);
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=VacancyManagement_input.action&statusKey='+status;
	  	document.getElementById("paraFrm").submit();
	}
	
		function callPageText(id,status1){ 
		 if(status1=="null" || status1=="" ){		
			status1="Open Vacancies";
		}  
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;	 
		 	totalPage =document.getElementById('totalPage').value;	
		 	var actPage = document.getElementById('paraFrm_myPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('paraFrm_myPage').value=pageNo;
		   
			if(status1=="Open Vacancies"){		
			document.getElementById('paraFrm').action='VacancyManagement_callstatus.action?status=O';
			document.getElementById('paraFrm').submit();
			}
			else if(status1=="Published Vacancies"){
				document.getElementById('paraFrm').action='VacancyManagement_callPublishStatus.action?status=P';
				document.getElementById('paraFrm').submit();
			}
			else if(status1=="Closed Vacancies"){
				document.getElementById('paraFrm').action='VacancyManagement_callstatus.action?status=C';
				document.getElementById('paraFrm').submit();
			 }
		}
		
	}	
	
	function callPage(id,pageImg,status1){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;
 	  if(status1=="null" || status1=="" ){		
			status1="Open Vacancies";
		}	 
 	 var actPage = document.getElementById('paraFrm_myPage').value; 
	 if(pageNo!="0"  & pageNo<totalPage){	
		 document.getElementById('paraFrm_myPage').value=pageNo;
	   }
	   
 	 	    	
       if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				 return false;
		        }
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('pageNoField').value=actPage;
			     document.getElementById('pageNoField').focus();
				 return false;
			    }
		 }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
		if(id=='P'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoField').value;
		id=eval(p)+1;
		} 
		document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
		if(status1=="Open Vacancies"){		
			document.getElementById('paraFrm').action='VacancyManagement_callstatus.action?status=O';
			document.getElementById('paraFrm').submit();
		}
		else if(status1=="Published Vacancies"){
			document.getElementById('paraFrm').action='VacancyManagement_callPublishStatus.action?status=P';
			document.getElementById('paraFrm').submit();
		}
		else if(status1=="Closed Vacancies"){
			document.getElementById('paraFrm').action='VacancyManagement_callstatus.action?status=C';
			document.getElementById('paraFrm').submit();
		 }
	}		
	
  function onPage(status1)
        {
     
     		if(document.getElementById("paraFrm_searchFlag").value=="true"){
					document.getElementById("showFilter").style.display='none';
					document.getElementById("hideFilter").style.display='none';
					document.getElementById("enableFilterValue").style.display='';
			
			}
     
     
	  	    var val= document.getElementById('selectname').value;
	  	       
	  		document.getElementById('paraFrm_show').value=val;
			document.getElementById('paraFrm_myPage').value=eval(val);	
			document.getElementById('selectname').value=val;
		 if(status1=="Published Requisition"){
			document.getElementById('paraFrm').action='VacancyManagement_callPublishStatus.action?status=P';
			document.getElementById('paraFrm').submit();
	     }else if(status1=="Closed Requisition"){
			document.getElementById('paraFrm').action='VacancyManagement_callstatus.action?status=C';
			document.getElementById('paraFrm').submit();
	   }else{		
			document.getElementById('paraFrm').action='VacancyManagement_callstatus.action?status=O';
			document.getElementById('paraFrm').submit();
	      }
	   
    }


showEnable();
function showEnable(){ 
 
if(document.getElementById("paraFrm_searchFlag").value=="true"){
document.getElementById("showFilter").style.display='none';
document.getElementById("hideFilter").style.display='none';
		document.getElementById("enableFilterValue").style.display='';
		
//document.getElementById("paraFrm_searchFlag").value="false";
}
}  


function chkDate(){
           
              
			var fromdate= document.getElementById('paraFrm_fDate').value;
	  		var todate= document.getElementById('paraFrm_tDate').value;
  		  
  		  
  		   var divName=document.getElementById('paraFrm_divName').value;
  		   var divId=document.getElementById('paraFrm_divId').value;
  		   var branchId=document.getElementById('paraFrm_branchId').value;
  		   var branchName=document.getElementById('paraFrm_branchName').value;
  		   var managerName=document.getElementById('paraFrm_managerName').value;
  		   var hrManagerId=document.getElementById('paraFrm_hrManagerId').value;
  		   var positionId=document.getElementById('paraFrm_positionId').value;
  		   var deptName=document.getElementById('paraFrm_deptName').value;
  		   var posName=document.getElementById('paraFrm_positionName').value;
  		   var pubfDate=document.getElementById('paraFrm_fDate').value;
  		   var pubtDate=document.getElementById('paraFrm_tDate').value;
  		   
			 
    if((divName=="")&&(branchName=="")&&(deptName=="")&&(managerName=="")&&(posName=="")&&(pubfDate=="")&&(pubtDate==""))
  		
  		{
  		
  		alert('Please enter/select atleast one field to apply filter');
  		return false;
  		}
  		  
  		  
  		if(fromdate!="")
  		     {
  	    if(!validateDate('paraFrm_fDate','Req.fDate'))
			return false;
			}
		if(todate!="")
			{
		if(!validateDate('paraFrm_tDate','Req.tDate'))
			return false;
			}
		if(fromdate!=""&& todate!="")
  		     {
  	    if(!dateDifferenceEqual(fromdate,todate,'paraFrm_tDate', 'Req.fDate','Req.tDate'))
				return false;
			 }
			
			 document.getElementById("paraFrm_searchFlag").value="true";
			document.getElementById("showFilter").style.display='none';
			document.getElementById("hideFilter").style.display='none';
			
  return true;
}

	callFilter();
	function callFilter(){
		    var chkSearch=document.getElementById('paraFrm_ChkSerch').value; 
	if(chkSearch=="")
	        {  
	            document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='';
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='none';
	      }
	else
	      {
	            document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='';
	        }
	    }
	function callShowFilter(){//callShowFilter()
				document.getElementById('hideFilter').style.display='';
				document.getElementById('showFilter').style.display='none';
				document.getElementById('showFilterValue').style.display='inline';
				document.getElementById('enableFilterValue').style.display='none';
		   }
		   
		   
   	function callEditFilter(){//callShowFilter()
   		 
		document.getElementById('hideFilter').style.display='';
		document.getElementById('showFilter').style.display='none';
		document.getElementById('showFilterValue').style.display='';
		document.getElementById('enableFilterValue').style.display='none';
   }
		   
  
	
	function callHideFilter(){
	
	 try
	 { 
	
		 		calReset();  
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='';
				}catch(e)
				{
				  alert(e);
				}
				//document.getElementById('enableFilterValue').style.display='inline';
		    }
	
		function callClear(){
		
			  document.getElementById('showFilterValue').style.display='none';
			  	document.getElementById("paraFrm_searchFlag").value='false';
			  document.getElementById("paraFrm").action='VacancyManagement_clearFilter.action';
			  document.getElementById("paraFrm").submit();
		   }
	 
	 function calReset(){
	 		 document.getElementById('paraFrm_ChkSerch').value="";
	 		 document.getElementById("paraFrm_searchFlag").value="true";
		     document.getElementById('paraFrm_divId').value="";
		     document.getElementById('paraFrm_divName').value="";
		     document.getElementById('paraFrm_branchId').value="";
		     document.getElementById('paraFrm_branchName').value="";
		     document.getElementById('paraFrm_deptId').value="";
		     document.getElementById('paraFrm_deptName').value="";
		     document.getElementById('paraFrm_hrManagerId').value="";
			 document.getElementById('paraFrm_managerName').value="";
			 document.getElementById('paraFrm_positionId').value="";
			 document.getElementById('paraFrm_positionName').value="";
			 document.getElementById('paraFrm_fDate').value="";
			 document.getElementById('paraFrm_tDate').value="";
	 }
	 
	 function callReportForDisp(reportType)
{ //alert(reportType);
 document.getElementById('paraFrm_reportType').value=reportType;
   callReport('VacancyManagement_report.action');    
}

</script>