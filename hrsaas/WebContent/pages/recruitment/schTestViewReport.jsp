 <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="SchTestAnalysis" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	  <tr>
			<td colspan="3" width="100%">  <s:hidden name="searchFlag"/>
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Schedule Test Analysis</strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>   
			</td>
		   </tr> 
		   
		   <tr>
	         <td colspan="3">
	           <table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
					    <td  nowrap="nowrap"> <input type="button" value="  Back  " class="token" onclick="callBack();">  
					     <s:if test="noData"> </s:if> <s:else>
					     <input type="button" value="Export In Pdf" class="token" onclick="callReportForDisp('P');"> 
					     <input type="button" value="Export In Xls" class="token" onclick="callReportForDisp('X');"> 
					    <input type="button" value="Export In Doc" class="token" onclick="callReportForDisp('T');">
					    <input type="button" class="token" value="View Summary" onclick="viewSummaryFun();">
					     </s:else>
					     </td>
					    
					    <td  width="20%" >  <s:if test="noData"> </s:if> <s:else> <label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll" id="exportAll" checked="checked"  onclick="callChkBox('exportAll');" > </s:else>  </td>
					</tr>
				</table>
			</td>
		  </tr>
		 
		    <tr>
			<td width="100%">  <s:hidden name="searchSetting"/>  <s:hidden name='aId'/> <s:hidden name='divId'/> <s:hidden name='backFlag'/> 
			 <s:hidden name="myPage"/>  <s:hidden name="myPageCandi"/> <s:hidden name="show"  />   <s:hidden name="checkedCount" value="13"/>
			 <s:hidden name="frmDate"/>    <s:hidden name="noDataCandi"/>  <s:hidden name="schStatusCom"/>  
			 <s:hidden name="toDate"  /> <s:hidden name="reqname"/> <s:hidden name="reqCode"  />    
			 <s:hidden name="recruiterName"/> <s:hidden name="recruiterCode"  /> <s:hidden name="recruiter"/> <s:hidden name="reportType"  />
			 <s:hidden name="reqname1"/> <s:hidden name="hiringManagerId"  /> <s:hidden name="selectedReq"/> <s:hidden name="position"  />
			 <s:hidden name="positionId"/> <s:hidden name="dateFilter"  />  <s:hidden name="thenByOrder2"/> <s:hidden name="thenBy2"  />  
			 <s:hidden name="sortBy"/> <s:hidden name="sortByOrder"  /> <s:hidden name="thenBy1"/> <s:hidden name="thenByOrder1"  />
			 <s:hidden name="totalVacAdvCom"/> <s:hidden name="asgnVacAdvCom"  /> <s:hidden name="openVacAdvCom"/> <s:hidden name="closeVacAdvCom"  /> 
             <s:hidden name="totalVacAdvTxt"/> <s:hidden name="asgnVacAdvTxt"  /> <s:hidden name="openVacAdvTxt"/> <s:hidden name="closeVacAdvTxt"  />
	         <s:hidden name="requirdeByDateTxt"/>    <s:hidden name="requirdeByDateCom"/>  
				<s:hidden name="candidateFlag"/>  <s:hidden name="screenFlag"/>  <s:hidden name="candidateName"/>  <s:hidden name="candidateCode"/>   
	<s:hidden name="hidSelectedReqName"/>
              <s:hidden name="candiCodeChk" value="on"/> <s:hidden name="interviewTimeChk"  /> <s:hidden name="recruiterChk"/> <s:hidden name="roundTypeChk"  /> 
              <s:hidden name="interviewVenueChk"/> <s:hidden name="schStatusChk"  /> <s:hidden name="interviewDateChk"/> <s:hidden name="userIdChk"  /> <s:hidden name="passwordChk"  />
              <s:hidden name="contactNumChk"/> <s:hidden name="emailIdChk"  />  <s:hidden name="interviewRoundTypeChk"  />    <s:hidden name="interviewRoundTypeChkFlag"  />  
              
               <s:hidden name="hiringMgrName"/> <s:hidden name="hiringMgrCode"  />
              
              
               <s:hidden name="dataLength"  />  <s:hidden name="requirdeByToDateTxt"  /> <s:hidden name="requirdeByFromDateTxt"  />  
              <s:hidden name="settingName"/> <s:hidden name='settingNameDup'/>  <s:hidden name='selectedReqName'/>   
                 <s:hidden name="radioStatus"/>  <s:hidden name="radioReq"/> <s:hidden name="hiringManagerName"/>    <s:hidden name="radioRecr"/> <s:hidden name="radioHirMng"/> <s:hidden name="radioPosition"/> 
				<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg"><!--Table 3-->
				<tr>
					<td width="100%" >
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="27" class="text_head"><strong class="text_head">Requisition Details</strong></td>
							<%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("PageNo"); 
							 int cnt=0; 
							 int pgNo = pageNo * 1 - 1; 
							%>
							<s:if test="noData">
							</s:if>
							<s:else>
								<td align="right"><b>Page:</b>
								 <input type="hidden" name="totalPage" id="totalPage" value="<%=totalPage%>">
								<a href="#" onclick="callPage('1','F');"> 
								 <img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" /> </a>&nbsp;
								  <a href="#" onclick="callPage('P','P');"> <img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> 
									<input type="text" name="pageNoField" id="pageNoField" theme="simple" size="3" value="<%= pageNo%>" 
									onkeypress="callPageText(event);return numbersOnly()" maxlength="4" title="press Enter" /> of <%=totalPage%>
									 <a ref="#" onclick="callPage('N','N')"> <img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('<%=totalPage%>','L');"> <img
									title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /> </a></td>
							</s:else>
						  </tr> 
						 </td>
						 </tr>
					 </table>
					 </td>
				 </tr>

				<tr>
					 <td width="100%">
					  <table width="100%" class="formbg" cellpadding="1" cellspacing="4">
					   <s:hidden name="vReqName"/> <s:hidden name="vReqDate"  /> <s:hidden name="vBranch"/> <s:hidden name="vDepartment"  />
                          <s:hidden name="vDivision"/> <s:hidden name="vPosition"  />  <s:hidden name="vHiringMgr"  />  <s:hidden name="vAppStatus"/> <s:hidden name="vReqStatus"  /> 
					     <s:hidden name="vReqCode"/>
					     <tr>
						  <td width="20%"><label  class = "set" name="reqs.code" id="reqs.code11"  ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> : </td>
						  <td width="30%">  <s:property value="vReqName"/> </td>
						  <td width="20%"> <label  class = "set" name="reqs.date" id="reqs.date"  ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label> : </td>
						  <td width="30%"> <s:property value="vReqDate"/> </td>
					   </tr> 
					   <tr>
						  <td width="20%"><label  class = "set" name="BranchLabel" id="BranchLabel"  ondblclick="callShowDiv(this);"><%=label.get("BranchLabel")%></label> :  </td>
						  <td width="30%">  <s:property value="vBranch"/> </td>
						  <td width="20%"> <label  class = "set" name="DepartmentLabel" id="DepartmentLabel"  ondblclick="callShowDiv(this);"><%=label.get("DepartmentLabel")%></label> : </td>
						  <td width="30%"> <s:property value="vDepartment"/> </td>
					   </tr>
					   
					   <tr>
						  <td width="20%"><label  class = "set" name="dividionLabel" id="dividionLabel"  ondblclick="callShowDiv(this);"><%=label.get("dividionLabel")%></label> :  </td>
						  <td width="30%">  <s:property value="vDivision"/> </td>
						  <td width="20%"> <label  class = "set" name="position" id="position"  ondblclick="callShowDiv(this);"><%=label.get("position")%></label> : </td>
						  <td width="30%"> <s:property value="vPosition"/> </td>
					   </tr>
					   <tr>
						  <td width="20%"><label  class = "set" name="hiring.mgr" id="hiring.mgr"  ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label> :  </td>
						  <td width="30%">  <s:property value="vHiringMgr"/> </td>
						  <td width="20%"> <label  class = "set" name="AppStatusLabel" id="AppStatusLabel"  ondblclick="callShowDiv(this);"><%=label.get("AppStatusLabel")%></label> : </td>
						  <td width="30%"> <s:property value="vAppStatus"/> </td>
					   </tr>
					   
					    <tr>
						  <td width="20%"><label  class = "set" name="reqStatusLabel" id="reqStatusLabel"  ondblclick="callShowDiv(this);"><%=label.get("reqStatusLabel")%></label> :  </td>
						  <td width="30%">  <s:property value="vReqStatus"/> </td>
						  <td width="20%">&nbsp; </td>
						  <td width="30%"> &nbsp; </td>
					   </tr>
					   
					  </table>
					 </td>
					</tr>
					
					<tr>
					<td width="100%" >
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td height="27" class="text_head"><strong class="text_head">Schedule Details</strong></td>
							<%
							  int totalPageCandi = (Integer) request.getAttribute("totalPageCandi"); 
							  int pageNoCandi = (Integer) request.getAttribute("PageNoCandi"); 
							 int cntCandi=0; 
							 int pgNoCandi = pageNoCandi * 20 - 20; 
							%>
							<s:if test="noDataCandi">
							</s:if>
							<s:else>
								<td align="right"><b>Page:</b>
								 <input type="hidden" name="totalPageCandi" id="totalPageCandi" value="<%=totalPageCandi%>">
								<a href="#" onclick="callPageCandi('1','F');"> 
								 <img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage" /> </a>&nbsp;
								  <a href="#" onclick="callPageCandi('P','P');"> <img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> 
									<input type="text" name="pageNoFieldCandi" id="pageNoFieldCandi" theme="simple" size="3" value="<%= pageNoCandi%>" 
									onkeypress="callPageTextCandi(event);return numbersOnly()" maxlength="4" title="press Enter" /> of <%=totalPageCandi%>
									 <a ref="#" onclick="callPageCandi('N','N')"> <img title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPageCandi('<%=totalPageCandi%>','L');"> <img
									title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /> </a></td>
							</s:else>
						  </tr> 
						 </td>
						 </tr>
					 </table>
					 </td>
				    </tr>  
						 
					<tr>
						<td width="100%"> 
				         <table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 5-->
			                     <tr> 
				                     <td colspan="3" width="100%">
										<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 6-->
											<tr>   
												<td width="5%" valign="top" class="formth" nowrap="nowrap"><label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
											    <td width="20%" valign="top" class="formth"><label  class = "set" name="cand.name" id="cand.name181" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></td>
											    <s:if test="emailIdChkFlag">
												<td width="25%" valign="top" class="formth"><label  class = "set" name="emailIDLabel" id="emailIDLabel"  ondblclick="callShowDiv(this);"><%=label.get("emailIDLabel")%></label></td> 
						            			</s:if>
						            			<s:if test="contactNumChkFlag">
						            			<td  valign="top" class="formth"><label  class = "set" name="ContactLabel" id="ContactLabel"  ondblclick="callShowDiv(this);"><%=label.get("ContactLabel")%></label></td> 
												</s:if>
												 <s:if test="roundTypeChkFlag">
												<td width="15%" valign="top" class="formth" ><label  class = "set" name="Interview.Round" id="Interview.Round" ondblclick="callShowDiv(this);"><%=label.get("Interview.Round")%></label></td>
												</s:if> 
						            	 	    <s:if test="interviewDateChkFlag">
						            	 	    <td width="15%" valign="top" class="formth" ><label  class = "set" name="interviewDate" id="interviewDate" ondblclick="callShowDiv(this);"><%=label.get("interviewDate")%></label></td> 
						            		    </s:if>
						            		     <s:if test="interviewTimeChkFlag">
						            		    <td  valign="top" class="formth"  ><label  class = "set" name="interviewTimeLabel" id="interviewTimeLabel"  ondblclick="callShowDiv(this);"><%=label.get("interviewTimeLabel")%></label></td> 
							            	 	</s:if>
							            	 	 <s:if test="interviewVenueChkFlag">
												<td width="15%" valign="top" class="formth" ><label  class = "set" name="interviewVenueLabel" id="interviewVenueLabel" 	ondblclick="callShowDiv(this);"><%=label.get("interviewVenueLabel")%></label></td> 
						            	 	    </s:if> 
							            	 	 <s:if test="userIdChkFlag">
							            	 	 <td width="15%" valign="top" class="formth" ><label  class = "set" name="userIdChkLabel" id="userIdChkLabel"  ondblclick="callShowDiv(this);"><%=label.get("userIdChkLabel")%></label></td> 
							            		</s:if>
							            		 <s:if test="passwordChkFlag">
							            	 	 <td width="15%" valign="top" class="formth" ><label  class = "set" name="passwordChkLabel" id="passwordChkLabel"  ondblclick="callShowDiv(this);"><%=label.get("passwordChkLabel")%></label></td> 
							            		</s:if>
							            		 <s:if test="recruiterChkFlag">
							            		 <td width="15%" valign="top" class="formth" ><label  class = "set" name="rec.name" id="rec.name11" ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label></td> 
							            	    </s:if>
							            	    <s:if test="interviewRoundTypeChkFlag">
							            	     <td width="15%" valign="top" class="formth" ><label  class = "set" name="interviewRoundTypeLabel" id="interviewRoundTypeLabel2" ondblclick="callShowDiv(this);"><%=label.get("interviewRoundTypeLabel")%></label></td> 
							                     </s:if>
							            	     <s:if test="schStatusChkFlag">
							            	     <td width="15%" valign="top" class="formth" ><label  class = "set" name="schStatusChkLabel" id="schStatusChkLabel" ondblclick="callShowDiv(this);"><%=label.get("schStatusChkLabel")%></label></td> 
							                     </s:if>
							              	</tr>
							              	
							              	<s:iterator value="candidateList">   
												<tr> 
												<td width="5%"  class="sortableTD" nowrap="nowrap">  <%++cntCandi;%> <%=++pgNoCandi%>
													 <s:hidden name="ivCandidateName"/>  <s:hidden name="ivEmailId"/>  <s:hidden name="ivContactNum"/>
													 <s:hidden name="ivRoundType"/>  <s:hidden name="ivInterviewDate"/>  <s:hidden name="ivInterviewTime"/>
													 <s:hidden name="ivVenue"/>  <s:hidden name="ivInterviewver"/>  <s:hidden name="ivRecruiter"/>
													 <s:hidden name="ivSchStatus"/>   
												</td>   
												<td width="25%" class="sortableTD"  > <s:property value="ivCandidateName"/> </td> 
												 <s:if test="emailIdChkFlag">
												<td width="25%"  class="sortableTD"> <s:property value="ivEmailId"/>&nbsp;</td> 
												 </s:if>
												<s:if test="contactNumChkFlag">
												<td  class="sortableTD" nowrap="nowrap"  > <s:property value="ivContactNum"/>&nbsp; </td>
												</s:if>
												 <s:if test="roundTypeChkFlag">
												<td width="25%"  class="sortableTD"> <s:property value="ivRoundType"/>&nbsp;</td> 
												</s:if> 
												  <s:if test="interviewDateChkFlag">
												<td  nowrap="nowrap" class="sortableTD"> <s:property value="ivInterviewDate"/>&nbsp;</td> 
												</s:if>
												<s:if test="interviewTimeChkFlag">
												<td class="sortableTD" nowrap="nowrap" > <s:property value="ivInterviewTime"/> &nbsp; </td>
												</s:if>
												 <s:if test="interviewVenueChkFlag">
												<td width="25%" class="sortableTD"   > <s:property value="ivVenue"/> &nbsp;</td>
												</s:if>
												 <s:if test="userIdChkFlag">
												<td width="25%"  class="sortableTD" > <s:property value="ivUserId"/>&nbsp;</td> 
												</s:if> 
												
												 <s:if test="passwordChkFlag">
												<td width="25%"  class="sortableTD" > <s:property value="ivPassword"/>&nbsp;</td> 
												</s:if> 
												  <s:if test="recruiterChkFlag">
												<td width="25%" class="sortableTD"  > <s:property value="ivRecruiter"/>&nbsp; </td>
												</s:if>
												
												 <s:if test="interviewRoundTypeChkFlag">
												<td width="25%" class="sortableTD"  > <s:property value="ivInterviewRoundType"/>&nbsp; </td>
												</s:if>
												
												  <s:if test="schStatusChkFlag">
												<td width="25%"  class="sortableTD" align="right"> <s:property value="ivSchStatus"/></td>  
												</s:if> 
											</tr>  
											</s:iterator>
											<s:if test="noDataCandi">
												<tr>
													<td width="100%" colspan="7" align="center"><font
														color="red">There is no data to display.</font></td>
												</tr>
											</s:if> 
											<s:else>
											  <tr>
													<td width="100%" colspan="11" align="right">  <b>Total records : <s:property value="dataLength"/></b>  </td>
											 </tr> 
											</s:else>
										</table>
					               </td>
					             </tr>		
					       </table><!--Table 5-->
						</td>
					</tr>	 
				  </table>  
			</td>
			
		</tr><!--end of vacancy listing--> 
		  <tr>
	         <td colspan="3">
	           <table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
					    <td  nowrap="nowrap"> <input type="button" value="  Back  " class="token" onclick="callBack();">   
			              <s:if test="noData"> </s:if> <s:else>
			            <input type="button" value="Export In Pdf" class="token" onclick="callReportForDisp('P');">  
					     <input type="button" value="Export In Xls" class="token" onclick="callReportForDisp('X');">  
					      <input type="button" value="Export In Doc" class="token" onclick="callReportForDisp('T');">
					      <input type="button" class="token" value="View Summary" onclick="viewSummaryFun();">
					       </s:else>
					       </td>
					    
					    <td  width="20%" >  <s:if test="noData"> </s:if> <s:else> <label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll_bot" id="exportAll_bot" checked="checked" onclick="callChkBox('exportAll_bot');" > </s:else>  </td>
					</tr>
				</table>
			</td>
		  </tr> 
	  </table>
</s:form>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script> 
window.onload=callPageCursor;
function callPageCursor()
{ 
 var screenFlag=  document.getElementById('paraFrm_screenFlag').value;   
	if(screenFlag=="true")
	{ 
	   document.getElementById('pageNoField').focus();
    }else{
     document.getElementById('pageNoFieldCandi').focus();
   }
 }
 
 function callChkBox(fieldName)
 {  
	 if(fieldName=="exportAll_bot")
	 { 
		  if(document.getElementById('exportAll_bot').checked){
		  
		     document.getElementById('exportAll').checked=true;
		   }else{
		      document.getElementById('exportAll').checked=false;
		   }
	 }else{
 
		    if(document.getElementById('exportAll').checked){
		     document.getElementById('exportAll_bot').checked=true;
		   }else{
		      document.getElementById('exportAll_bot').checked=false;
		   } 
	   } 
 }
	function callPageText(id){   
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
		    document.getElementById('paraFrm_screenFlag').value="true"; 
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
		   
			document.getElementById('paraFrm').action='SchTestAnalysis_viewOnScreen.action';
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	
	function callPageTextCandi(id){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldCandi').value;	 
		 	totalPage =document.getElementById('totalPageCandi').value;	
		 	document.getElementById('paraFrm_candidateFlag').value="true";	 
		 	var actPage = document.getElementById('paraFrm_myPageCandi').value;    
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldCandi').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldCandi').focus();
		     document.getElementById('pageNoFieldCandi').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldCandi').focus();
		     document.getElementById('pageNoFieldCandi').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldCandi').focus();
		      return false;
	        }
	        
	         document.getElementById('paraFrm_myPageCandi').value=pageNo;
		   
			document.getElementById('paraFrm').action='SchTestAnalysis_viewCandidate.action';
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
function callReportForDisp(reportType)
{
 document.getElementById('paraFrm_reportType').value=reportType;
   callReport('SchTestAnalysis_report.action');    
}
function callBack()
 {
   document.getElementById('paraFrm_radioReq').value="";
   document.getElementById('paraFrm_radioRecr').value="";
   document.getElementById('paraFrm_radioHirMng').value="";
   document.getElementById('paraFrm_radioPosition').value="";
   
   radioStatus =document.getElementById('paraFrm_radioStatus').value;
  if(radioStatus=="C")
  { 
   document.getElementById('paraFrm_radioReq').value="checked";
  } 
  if(radioStatus=="N")
  { 
   document.getElementById('paraFrm_radioRecr').value="checked";
  }
  
    if(radioStatus=="M")
  { 
   document.getElementById('paraFrm_radioHirMng').value="checked";
  } 
    if(radioStatus=="P")
    { 
   document.getElementById('paraFrm_radioPosition').value="checked";
  }
  
  document.getElementById('paraFrm_backFlag').value="true";
  document.getElementById('paraFrm_reportType').value="1";
  document.getElementById('paraFrm').action='SchTestAnalysis_callBack.action';
  document.getElementById('paraFrm').submit(); 
 }

 function callPageCandi(id,pageImg){  
 	 pageNo =document.getElementById('pageNoFieldCandi').value;	
 	 totalPage =document.getElementById('totalPageCandi').value;	 
 	 var actPage = document.getElementById('paraFrm_myPageCandi').value; 
	 if(pageNo!="0"  & pageNo<totalPage){	
		 document.getElementById('paraFrm_myPageCandi').value=pageNo;
	   }
	   
 	 	    	
       if( pageImg !="F" & pageImg !="L" )
         { 
	 	   if(pageNo==""){
		        alert("Please Enter Page Number.");
		        document.getElementById('pageNoFieldCandi').focus();
				 return false;
		        }
		  if(Number(pageNo)<=0)
			    {
			     alert("Page number should be greater than zero.");
			     document.getElementById('pageNoFieldCandi').value=actPage;
			     document.getElementById('pageNoFieldCandi').focus();
				 return false;
			    }
		  if(Number(totalPage)<Number(pageNo))
			    {
			     alert("Page number should not be greater than "+totalPage+".");
			      document.getElementById('pageNoFieldCandi').value=actPage;
			     document.getElementById('pageNoFieldCandi').focus();
				 return false;
			    }
		 }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoFieldCandi').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoFieldCandi').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoFieldCandi').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoFieldCandi').focus();
	         return false;
	        }  
       }  
       
		if(id=='P'){
		var p=document.getElementById('pageNoFieldCandi').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoFieldCandi').value;
		id=eval(p)+1;
		} 
		document.getElementById('paraFrm_myPageCandi').value=id; 
		document.getElementById('paraFrm').action='SchTestAnalysis_viewCandidate.action';
		document.getElementById('paraFrm').submit(); 
	}	
	
	
	function callPage(id,pageImg){  
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
		document.getElementById('paraFrm').action='SchTestAnalysis_viewOnScreen.action';
		document.getElementById('paraFrm').submit(); 
	}	
	 
	 
	 
 function onPage()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	document.getElementById('paraFrm_myPage').value=eval(val);
	document.getElementById('selectname').value=val;
	document.getElementById('paraFrm').action="SchTestAnalysis_viewOnScreen.action";
	document.getElementById('paraFrm').submit();
   }
   
   	pgshow();
  	function pgshow()
  	{
	  	var pgno=document.getElementById('paraFrm_show').value;  
	  	if(!(pgno=="")){
	  	 document.getElementById('selectname').value=pgno;
	  	 }
  	}
  	
  	function viewSummaryFun()
  	{
   document.getElementById('paraFrm').target="wind";
   var wind = window.open('','wind','width=700,height=450,scrollbars=yes,resizable=no,status=yes,menubar=no,top=200,left=150');	 
   document.getElementById('paraFrm').action="SchTestAnalysis_viewSummary.action";
   document.getElementById('paraFrm').submit();
   document.getElementById('paraFrm').target="";
   }

</script>