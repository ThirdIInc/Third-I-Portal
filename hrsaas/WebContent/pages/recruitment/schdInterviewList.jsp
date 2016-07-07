
<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="SchdInterviewList" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="myPage" />
	<s:hidden name="show" />
	<s:hidden name="modeLength" />
	<s:hidden name="DuprequisitionId" />
	<s:hidden name="candCode1" />
	<s:hidden name="intervId" />
	<s:hidden name="ChkSerch" />
	<s:hidden name="searchFlag" />
	<s:hidden name="appliedFilterFlag" />
	<s:hidden name="hideFilterFlag" />
	<s:hidden name="checkFilterFlag" />
	<s:hidden name="rescheduleFlag" />
	<s:hidden name="hidIntDtlCode" />
	<s:hidden name="cancelDtlCode" />
	<s:hidden name="checkStatusList" />
	<s:hidden name="interviewReschFlag" />
	<s:hidden name="hidReschFlag" />
	<s:hidden name="fakeRankName"></s:hidden>
	<s:hidden name="cancelStatus" />
	<table width="100%" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Interview
					Schedule List</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<!-- table 1 -->
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<!--Table 4-->
						<tr>
							<td height="27" class="formtxt"><a href="#"
								onclick="callFun('N');">Scheduled Interviews </a> | <a href="#"
								onclick="callIntStauts('Y');">Interview Status </a> | <a
								href="#" onclick="callCancelStatus('C')">Canceled Interviews</a>
							</td>
						</tr>
						<s:hidden name="listLength"></s:hidden>
					</table>
					<!--Table 4--></td>
				</tr>
			</table>
			<!-- table 1 --></td>
		</tr>



		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="formbg">
				<tr>

					<td colspan="2" nowrap="nowrap"><strong class="text_head"><s:if
						test="searchFlag">Applied Filter</s:if><s:else>
						<label class="set" name="searchApply.filter"
							id="searchApply.filter" ondblclick="callShowDiv(this);"><%=label.get("searchApply.filter")%>
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

							<td width="20%"><label class="set" name="reqs.code"
								id="reqs.code1" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%>
							:</td>
							<td width="20%" colspan="1"><s:textfield name="DupreqName"
								size="25" readonly="true" /></td>
							<td width="10%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'SchdInterviewList_f9dupRequisition.action');"></td>
							<td width="15%"><label class="set" name="cand.name"
								id="cand.name1" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
							:</td>
							<td width="25%"><s:textfield name="candidateName1" size="25"
								maxlength="50" onkeypress="return charactersOnly();" /></td>
							<td width="5%"><img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="16"
								align="absmiddle" height="16"
								onclick="javascript:callsF9(500,325,'SchdInterviewList_f9CandidateAction.action');" />
							</td>

						</tr>

						<tr>

							<td width="20%"><label class="set" name="interVType"
								id="interVType" ondblclick="callShowDiv(this);"><%=label.get("interVType")%>
							:</td>
							<td width="20%"><s:textfield name="intRound" size="25"
								maxlength="30" /></td>

							<td width="10%">&nbsp;&nbsp;</td>
							<td width="15%" colspan="1"><label class="set"
								name="intDate" id="intDate1" ondblclick="callShowDiv(this);"><%=label.get("intDate")%></label>
							:</td>
							<td width="25%"><s:textfield name="intervDate" size="25"
								onkeypress="return numbersWithHiphen();" maxlength="10"
								readonly="false" /></td>
							<td width="5%"><s:a
								href="javascript:NewCal('paraFrm_intervDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>

						</tr>
						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="Interviewer" id="Interviewer1"
								ondblclick="callShowDiv(this);"><%=label.get("Interviewer")%></label>
							:</td>
							<td width="20%"><s:textfield name="intervName" size="25"
								readonly="true" /></td>
							<td width="10%"><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(500,325,'SchdInterviewList_f9Interviewer.action');">
							</td>
						</tr>
						<tr>
							<td width="100%" height="22" colspan="5" align="center"><s:submit
								cssClass="token" value="  Apply Filter  "
								onclick=" return callAppliedFun();" />&nbsp; <input
								type="button" class="reset" theme="simple"
								onclick="return calReset();" value="Reset " />
							<td width="25%"></td>
						</tr>
					</table>
					<!--Table 1--></div>
					</td>
				</tr>
				<tr>
					<td colspan="3">
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
		     						  }
									      %>



						<tr id="clearId">

							<td align="center" colspan="2">&nbsp; <input type="button"
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
			<td align="left" colspan="3" width="100%"><s:if
				test="cancelStatus">
			</s:if> <s:else>
				<s:submit cssClass="token"
					action="InterviewSchedule_fromScheduleInterviewList" theme="simple"
					value="   Reschedule " onclick="return reschedule('<%=k %>');" />   &nbsp;
						<input type="button" class="token" value="Cancel schedule"
					onclick="return callCancelFun();">
			</s:else></td>
		</tr>

		<tr>
			<!-- tr option -->
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<!-- table option -->

				<tr>
					<td colspan="5">

					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<!--Table 3-->
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<!--Table 5-->
								<tr>
									<strong> <%
												String status = (String) request.getAttribute("stat");
												if (status != null) {
													out.println(status);
												} else {
													out.println("Scheduled Interviews");
												}
												%> </strong>
									<%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("PageNo");
							%>
									<s:if test="modeLength">
										<td align="right"><b>Page:</b> <input type="hidden"
											name="totalPage" id="totalPage" value="<%=totalPage%>">
										<a href="#" onclick="callPage('1','F','<%=status %>');">
										<img title="First Page" src="../pages/common/img/first.gif"
											width="10" height="10" class="iconImage" /> </a>&nbsp; <a
											href="#" onclick="callPage('P','P','<%=status %>');"> <img
											title="Previous Page" src="../pages/common/img/previous.gif"
											width="10" height="10" class="iconImage" /> </a> <input
											type="text" name="pageNoField" id="pageNoField"
											theme="simple" size="3" value="<%= pageNo%>"
											onkeypress="callPageText(event,'<%=status %>');return numbersOnly()"
											maxlength="10" /> of <%=totalPage%> <a href="#"
											onclick="callPage('N','N','<%=status %>')"> <img
											title="Next Page" src="../pages/common/img/next.gif"
											class="iconImage" /> </a>&nbsp; <a href="#"
											onclick="callPage('<%=totalPage%>','L','<%=status %>');">
										<img title="Last Page" src="../pages/common/img/last.gif"
											width="10" height="10" class="iconImage" /> </a></td>
									</s:if>
								</tr>


								<tr>
									<td width="100%">
									<table width="100%" border="0" class="sortable">
										<!--Table 6-->
										<tr>
											<td width="2%" valign="top" class="formth">&nbsp;</td>
											<td width="3%" valign="top" class="formth"><b><label
												class="set" name="serial.no" id="serial.no"
												ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
											<td width="10%" valign="top" class="formth"><b><label
												class="set" name="reqs.code" id="reqs.code"
												ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
											<td width="10%" valign="top" class="formth"><b><label
												class="set" name="cand.name" id="cand.name"
												ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
											<td width="10%" valign="top" class="formth"><b><label
												class="set" name="roundtype" id="roundtype"
												ondblclick="callShowDiv(this);"><%=label.get("roundtype")%></label></b></td>
											<td width="6%" valign="top" class="formth"><b><label
												class="set" name="intDate" id="intDate"
												ondblclick="callShowDiv(this);"><%=label.get("intDate")%></label></b></td>
											<td width="4%" valign="top" class="formth"><b><label
												class="set" name="inttime" id="inttime"
												ondblclick="callShowDiv(this);"><%=label.get("inttime")%></label></b></td>
											<td width="10%" valign="top" class="formth"><b><label
												class="set" name="Interviewer" id="Interviewer"
												ondblclick="callShowDiv(this);"><%=label.get("Interviewer")%></label></b></td>
											<td width="5%" valign="top" class="formth"><b><label
												class="set" name="Venue" id="Venue"
												ondblclick="callShowDiv(this);"><%=label.get("Venue")%></label></b></td>
											<!-- <td width="2%" valign="top" class="formth" align="center">&nbsp;</td> -->
											<td width="5%" valign="top" class="formth"><b><label
												class="set" name="Comments" id="Comments"
												ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label></b></td>
											<!-- 	<td width="2%" valign="top" class="formth" align="center">&nbsp;</td> --->
											<!-- 	<td width="10%" valign="top" class="formth"><b><label  class = "set" name="view.reqs" id="viewreq" 
											ondblclick="callShowDiv(this);"><%=label.get("view.reqs")%></label></b></td>  -->
										</tr>
										<s:if test="noData">
											<tr align="center">
												<td width="100%" colspan="9" align="center"><font
													color="red">There is no data to display</font></td>
											</tr>
										</s:if>
										<%
														int cnt = pageNo * 20 - 20;
														int m = 0;
														int countRow = 0;
												%>
										<%!int i = 0;%>
										<%
											int k = 1;
											%>
										<s:iterator value="list">
											<tr <%if(countRow%2==0){
									%> class="tableCell1"
												<%}else{%> class="tableCell2" <%	}countRow++; %>
												onmouseover="javascript:newRowColor(this);"
												onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
												ondblclick="viewRequisition('<s:property value="requisitionCode"/>');"
												title="Double click for view Requisition">
												<td class="sortableTD" width="2%" nowrap="nowrap"><input
													type="radio" name="radioButton" id="<%="radioButton"+k %>"
													value="<%=k %>" onclick="return callRadioFun('<%=k %>');" /></td>
												<td class="sortableTD" width="3%" align="center"
													nowrap="nowrap"><%=++cnt%> <%
													++m;
													%>
												</td>
												<td class="sortableTD" width="10%"><s:hidden
													name="requisitionId" /><s:property value="requisitionName" />
												<s:hidden name="requisitionCode" /> <s:hidden
													name="interviewCode" /><s:hidden name="interviewDtlCode"
													id='<%="interviewDtlCode"+k %>' /> &nbsp;</td>
												<td class="sortableTD" width="10%"><s:hidden
													name="candidateCode" /> <s:property value="candidateName" />
												&nbsp;</td>
												<td width="10%" class="sortableTD"><s:hidden
													name="intRoundType" /> <s:property
													value="intRoundTypeName" /> &nbsp;</td>
												<td width="6%" class="sortableTD" nowrap="nowrap"><s:property
													value="intDate" /> &nbsp;</td>
												<td class="sortableTD" width="4%"><s:property
													value="intTime" /> &nbsp;</td>
												<td class="sortableTD" width="10%"><s:property
													value="interviewer" /><s:hidden name="interviewerCode" />
												&nbsp;</td>
												<!--  <td class="sortableTD" width="5%"><s:textarea label="%{getText('Venue')}"
																theme="simple" cols="15" rows="2" name="venue" id='<%="venue"+k%>' /></td> -->
												<td class="sortableTD" width="5%" valign="bottom"><s:hidden
													name="venue" id='<%="venue"+k%>' /> <img
													src="../pages/images/zoomin.gif"
													title="Click for view venue" height="12" align="absmiddle"
													width="12" theme="simple"
													onclick="javascript:callWindow('<%="venue"+k%>','Venue','readonly','500','500');"></td>
												<!-- 	<td class="sortableTD" width="10%"><s:textarea label="%{getText('Comments')}"
																theme="simple" cols="15" rows="2" name="comments" id='<%="comments"+k%>' /></td> -->
												<td class="sortableTD" width="5%" valign="bottom"><s:hidden
													name="comments" id='<%="comments"+k%>' /> <img
													src="../pages/images/zoomin.gif"
													title="Click for view comments" height="12"
													align="absmiddle" width="12" theme="simple"
													onclick="javascript:callWindow('<%="comments"+k%>','Comments','readonly','500','500');"></td>
												<!-- 	<td class="sortableTD" width="10%"><input
													type="button" name="publish" onclick="viewRequisition('<s:property value="requisitionCode"/>')" class="token"
													value="View"/></td>		  -->
											</tr>
											<%
												k++;
												%>
										</s:iterator>
										<%
													i = k;
													k = 1;
													m=i;
												%>
									</table>
									</td>
								</tr>
							</table>
							<!--Table 5--></td>
						</tr>
					</table>
					<!--Table 3--></td>
				</tr>
				<!--end of vacancy listing-->
			</table>
			<!-- table option --></td>
		</tr>
		<!-- tr option -->
		<tr>
			<td align="left" nowrap="nowrap" colspan="2"><s:if
				test="cancelStatus">
			</s:if> <s:else>
				<s:submit cssClass="token"
					action="InterviewSchedule_fromScheduleInterviewList" theme="simple"
					value="   Reschedule " onclick="return reschedule('<%=k %>');" />   &nbsp;
				<input type="button" class="token" value="Cancel schedule"
					onclick="return callCancelFun();">
			</s:else></td>
			<td colspan="1" align="Right"><s:if test="modeLength">
				<b>Total Records:</b>&nbsp;<s:property value="totalRecords" />
			</s:if></td>
		</tr>

	</table>
	<!-- Final Table -->



</s:form>

<script>

function callRadioFun(id)
{ 
  document.getElementById("paraFrm_hidIntDtlCode").value=id;
 }
function callCancelFun()
{
   document.getElementById("paraFrm_cancelDtlCode").value="";
   var id =  document.getElementById("paraFrm_hidIntDtlCode").value;
   if(id==""){
     alert("Please select the candidate.");
     return false;
   }
   
   	var con=confirm('Are you sure to cancel the schedule interview ? ')
	if(con){
	   document.getElementById("paraFrm_cancelDtlCode").value=  document.getElementById("interviewDtlCode"+id).value;  
	   document.getElementById("paraFrm").action="SchdInterviewList_cancelReq.action";
	   document.getElementById("paraFrm").submit();
	} else{
	     return false;
	}
	
   
 
}
  function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first'; 
	    }
	
	function oldRowColor(cell,val) { 
	cell.className='Cell_bg_second'; 
	}
	
window.onload=callOnLoadFun;
 function callOnLoadFun(){
  var rescheduleFlag=document.getElementById('paraFrm_rescheduleFlag').value; 
  if(rescheduleFlag=="true"){
   document.getElementById('paraFrm_rescheduleFlag').value="false";
   alert("Candidates rescheduled for interview successfully.");
   } 
 }
   function callAppliedFun(){   			
			
	       var reqname=document.getElementById('paraFrm_DupreqName').value;
  		   var candidateName1=document.getElementById('paraFrm_candidateName1').value;
  		   var intRound=document.getElementById('paraFrm_intRound').value;
  		   var intervDate=document.getElementById('paraFrm_intervDate').value;
  		   var intervName	=document.getElementById('paraFrm_intervName').value;
  		   
    if((reqname=="")&&(candidateName1=="")&&(intRound=="")&&(intervDate=="")&&(intervName==""))
  
  		{
	  		alert('Please enter/select atleast one field to apply filter');
	  		return false;
  		}
  		
  		if(!validateDate('paraFrm_intervDate', 'intDate'))
		          {  return false;
		        } 
		 	 document.getElementById("paraFrm_searchFlag").value="true";
		 	 document.getElementById('paraFrm_appliedFilterFlag').value ="true";
		 	 document.getElementById("paraFrm").action="SchdInterviewList_scheduledInterviews.action";
	   		 document.getElementById('paraFrm').target="main";
	         document.getElementById("paraFrm").submit();
	  
	}
	function callFun(status){//status is kept 'N'....it is for INT_CONDUCT_STATUS = 'N'
	   // href="SchdInterviewList_scheduledInterviews.action?status=N"
	   
	   		 calReset();
	         document.getElementById('paraFrm_searchFlag').value ="false";
	         document.getElementById('clearId').value ="none";
	         
	         
 			 document.getElementById('paraFrm_cancelStatus').value = false;
	         document.getElementById("paraFrm").action='SchdInterviewList_scheduledInterviews.action?status='+status;
	         document.getElementById('paraFrm').target="main";
	         document.getElementById("paraFrm").submit();
		
	
	}
	
	function scheduleLink(){
	
	if(document.getElementById('paraFrm_hideFilterFlag').value=="true"){
	
			document.getElementById('showFilterValue').style.display='none';
			document.getElementById('enableFilterValue').style.display='none';
			document.getElementById('hideFilter').style.display='none';
			document.getElementById('showFilter').style.display='';
			document.getElementById('clearRowId').style.display='none';
	      }
	}
	function callIntStauts(status){
			calReset();
	         document.getElementById('paraFrm_searchFlag').value ="false";
	         document.getElementById('clearId').value ="none";
	         
			document.getElementById('paraFrm_cancelStatus').value = false;
			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm_myPage').value="";
		    document.getElementById("paraFrm").action='SchdInterviewList_interviewStatus.action?status='+status;
		    document.getElementById("paraFrm").submit();
	}
	
	function callCancelStatus(status){
	         calReset();
			 document.getElementById('paraFrm_searchFlag').value ="false";
	         document.getElementById('clearId').value ="none";
			document.getElementById('paraFrm_cancelStatus').value = true;
			 document.getElementById("paraFrm").action='SchdInterviewList_scheduledInterviews.action?status='+status;
	         document.getElementById('paraFrm').target="main";
	         document.getElementById("paraFrm").submit();
	} //end of method
	
	function reschedule(id){
	
	document.getElementById('paraFrm_hidReschFlag').value="true";
	document.getElementById('paraFrm_interviewReschFlag').value="true";
		var rowCount = <%=i%>;
		var flag = false;
		
			for(var i=1;i<rowCount;i++){
				if(document.getElementById('radioButton'+i).checked){
					flag = true;
				}
			}
			if(!flag){
				alert('Please select at least one record');
				return false;
			}
		return true;
	}
	
	function viewRequisition(reqCode){
					document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=SchdInterviewList_scheduledInterviews.action';
				    document.getElementById("paraFrm").submit();
				}
	
 
 function callPage(id,pageImg,status1){   
  	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
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
		
	    document.getElementById("paraFrm").action='SchdInterviewList_scheduledInterviews.action?status=N';
	    document.getElementById("paraFrm").submit();
	}	
	
	
	function callPageText(id,status1){   
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
	    document.getElementById("paraFrm").action='SchdInterviewList_scheduledInterviews.action?status='+'N';
	    document.getElementById("paraFrm").submit();
		}
		
	}	 
 
 myduponload();
function myduponload(){

     if(document.getElementById('paraFrm_searchFlag').value=="true")  {  
		            document.getElementById('hideFilter').style.display='none';
					document.getElementById('showFilter').style.display='none';
					document.getElementById('showFilterValue').style.display='none';
					document.getElementById('enableFilterValue').style.display='';
	           }
	      else{
				    document.getElementById('showFilterValue').style.display='';
				    document.getElementById('showFilter').style.display='';
				    document.getElementById('hideFilter').style.display='none';
				    document.getElementById('enableFilterValue').style.display='none';
	            }
	    }
function callClear(){
					 document.getElementById('showFilterValue').style.display='none';
					 document.getElementById("paraFrm_searchFlag").value='false';
					 document.getElementById("paraFrm").action='SchdInterviewList_filterClear.action';
					 document.getElementById("paraFrm").submit();
				 }
 
function calReset(){ 
				    document.getElementById('paraFrm_DuprequisitionId').value="";
				    document.getElementById('paraFrm_DupreqName').value=""; 
				    document.getElementById('paraFrm_candCode1').value="";
				    document.getElementById('paraFrm_candidateName1').value="";  
				    document.getElementById('paraFrm_intervDate').value="";  
				    document.getElementById('paraFrm_intervName').value=""; 
				    document.getElementById('paraFrm_intervId').value="";  
				    document.getElementById('paraFrm_intRound').value=""; 
               }
showEnable();
function showEnable(){
				if(document.getElementById("paraFrm_searchFlag").value=="true"){
				document.getElementById("showFilter").style.display='none';
				document.getElementById("hideFilter").style.display='none';
	    }
      }  

    callFilter();
	function callFilter(){
	             var chkSearch=document.getElementById('paraFrm_searchFlag').value;
	if(document.getElementById('paraFrm_searchFlag').value=="true")
	        {  
	            document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='none';
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='';
	      }
	else
	      {
	      		document.getElementById('showFilter').style.display='';
	      		  document.getElementById('hideFilter').style.display='none';
	            document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='none';
	        }
	    }

function callShowFilter(){

				document.getElementById('hideFilter').style.display='';
				document.getElementById('showFilter').style.display='none';
				document.getElementById('showFilterValue').style.display='inline';
				document.getElementById('enableFilterValue').style.display='none';
	         }
	         
   function  callEditFilter(){  
	document.getElementById('hideFilter').style.display='';
	document.getElementById('showFilter').style.display='none';
	document.getElementById('showFilterValue').style.display='';
	document.getElementById('enableFilterValue').style.display='none';
       }
 

 
function callHideFilter(){
 				calReset();
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='';
	
             }
   
	
	
</script>