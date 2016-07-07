 <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="OpenVacReport" validate="true" id="paraFrm" theme="simple">
<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
 
 <s:hidden name="reqCodeChk"/> <s:hidden name="interviewerChk"/> <s:hidden name="candidateNameChk"/>
  <s:hidden name="postionChk"/> <s:hidden name="statusChk"/> <s:hidden name="qualificationDtlChk"/>
   <s:hidden name="technicalSkillChk"/> <s:hidden name="communicationLevelChk"/> <s:hidden name="managementSkillChk"/>
    <s:hidden name="personalityChk"/> <s:hidden name="learningSkillChk"/> <s:hidden name="relevantExpChk"/>
     <s:hidden name="suitabilityAbiltiyChk"/> <s:hidden name="roundOfIntChk"/> <s:hidden name="evalScoreChk"/>
     <s:hidden name="myPage"/> <s:hidden name="evalPercantageChk"/> <s:hidden name="reportType"  />
	  <s:hidden name="checkedCount" value="18"/> <s:hidden name="reqCode"  /><s:hidden name="radioReq"/>	
	  <s:hidden name="radioInterviewer"/><s:hidden name="radioCandidate"/><s:hidden name="radioPosition"/>
	  <s:hidden name="sortByDsc"/><s:hidden name="sortByAsc"/><s:hidden name="thenByOrder1Asc"/>
	  <s:hidden name="thenByOrder1Dsc"/><s:hidden name="thenByOrder2Asc"/><s:hidden name="thenByOrder2Dsc"/>
	  <s:hidden name="radioStatus"/><s:hidden name='backFlag'/> <s:hidden name="reqname" />
	  <s:hidden name="interviewerId" /> <s:hidden name="interviewerName" />  <s:hidden name="candidateName" /> 
	   <s:hidden name="candidateCode" />  <s:hidden name="position" /> <s:hidden name="positionId" /> 
	   <s:hidden name="dateFilter" /><s:hidden name="frmDate" /> <s:hidden name="toDate" /> 
	   <s:hidden name="selectedReqName" /> <s:hidden name="selectedReq" /> <s:hidden name="sortBy"/>
	   <s:hidden name="thenBy1"/><s:hidden name="thenBy2"/> <s:hidden name="sortByOrder"  />
	    <s:hidden name="thenByOrder1"  /> <s:hidden name="thenByOrder2"  />
	    <s:hidden name='aId'/> <s:hidden name='divId'/>  <s:hidden name='settingName'/>  <s:hidden name='searchSetting'/> 
	   <s:hidden name='settingNameDup'/>  <s:hidden name="hidSelectedReqName"/> 
	   <s:hidden name="recruiterChk" />
	   <s:hidden name="recruiterChkFlag" /> 
	   
	<tr>
		<td colspan="3" width="100%">  <s:hidden name="searchFlag"/>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="3%" valign="bottom" >&nbsp;<strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Interview Evaluation Analysis</strong></td>
					<td width="4%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				</tr>
			</table>   
		</td>
	</tr>
	<tr>
	   <td colspan="3">
	      <table width="100%" border="0" cellpadding="0" cellspacing="0" >
			<tr>
				<td  width="70%" > <input type="button" value="  Back  " class="token" onclick="callBack();">  
					<s:if test="noData"> </s:if> <s:else>
					   <input type="button" value=" Export In Pdf " class="token" 
					   onclick="callReportForDisp('P');"> 
					   <input type="button" value=" Export In Xls " class="token" 
					   onclick="callReportForDisp('X');"> 
					   <input type="button" value=" Export In Doc " class="token" 
					   onclick="callReportForDisp('T');">
					 </s:else>
					   </td>
					   <td  width="30%" >  <s:if test="noData"> </s:if> 
						  <s:else> <label class="set" name="export.all" id="export.all" 
							    ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : 
							    <input type="checkbox" name="exportAll" id="exportAll" checked="checked"  
							    onclick="callChkBox('exportAll');" > 
						</s:else>  
					</td>
				</tr>
			</table>
		</td>
	</tr>
	 <tr><!-- Start of iterator -->
		<td width="100%">
			<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg"><!--Table iterator-->
				<tr>
				   <td>
				 	  <table width="100%" border="0" align="center" cellpadding="0"	cellspacing="0"><!-- table 1 -->
				 	  	<tr>
							<td height="27" class="text_head">
								<strong>Interview Evaluation List </strong>
							</td>
							<%
								int totalPage = (Integer) request.getAttribute("totalPage");
								int pageNo = (Integer) request.getAttribute("PageNo");
							%>
							<s:if test="noData"> </s:if> <s:else>
                                          <td align="right"><b>Page:</b>
							 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
							 
							</td>
					 </s:else>	  
						</tr>	
				 	  </table><!-- end table 1 -->
				 	</td>
				 </tr>	  
			</table><!--End Table iterator-->
		</td>
	</tr><!-- End of iterator -->
	<tr>
		<td width="100%"> 
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 2-->
				 <tr> 
					<td colspan="3" width="100%">
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable"><!--Table 3-->
							<tr>
								<td width="5%" valign="top" class="formth" nowrap="nowrap">
									<label class="set" name="serial.no" id="serial.no" 
									ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label>
								</td>
							<s:if test="reqCodeChkFlag">
								<td width="20%" valign="top" class="formth">
									<label  class = "set" name="reqs.code" id="reqs.code11" 
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
								</td>
							</s:if>
							<s:if test="postionChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="position" id="position11"  
									ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="interviewerChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="inteviewerName" id="inteviewerName11"  
									ondblclick="callShowDiv(this);"><%=label.get("inteviewerName")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="candidateNameChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="cand.name" id="cand.name11"  
									ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="roundOfIntChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="roundOfInt" id="roundOfInt"  
									ondblclick="callShowDiv(this);"><%=label.get("roundOfInt")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="qualificationDtlChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="qlfDtls" id="qlfDtls"  
									ondblclick="callShowDiv(this);"><%=label.get("qlfDtls")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="technicalSkillChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="techSkl" id="techSkl"  
									ondblclick="callShowDiv(this);"><%=label.get("techSkl")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="communicationLevelChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="comLvl" id="comLvl"  
									ondblclick="callShowDiv(this);"><%=label.get("comLvl")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="managementSkillChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="mgmtSkl" id="mgmtSkl"  
									ondblclick="callShowDiv(this);"><%=label.get("mgmtSkl")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="personalityChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="pers" id="pers"  
									ondblclick="callShowDiv(this);"><%=label.get("pers")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="learningSkillChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="lrnSkl" id="lrnSkl"  
									ondblclick="callShowDiv(this);"><%=label.get("lrnSkl")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="relevantExpChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="relExp" id="relExp"  
									ondblclick="callShowDiv(this);"><%=label.get("relExp")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="suitabilityAbiltiyChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="suit" id="suit"  
									ondblclick="callShowDiv(this);"><%=label.get("suit")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="evalScoreChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="evalScore" id="evalScore"  
									ondblclick="callShowDiv(this);"><%=label.get("evalScore")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="evalPercentageChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="evalPercentage" id="evalPercentage"  
									ondblclick="callShowDiv(this);"><%=label.get("evalPercentage")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="statusChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="status" id="status"  
									ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
								</td> 
			            	</s:if>
			            	<s:if test="recruiterChkFlag">
								<td width="25%" valign="top" class="formth">
									<label  class = "set" name="recruiterLabel" id="recruiterLabel"  
									ondblclick="callShowDiv(this);"><%=label.get("recruiterLabel")%></label>
								</td> 
			            	</s:if>
							</tr>
							<% int cnt=0; 
							   int pgNo = pageNo * 20 - 20;
							%> 
							<s:iterator value="openViewList">
							<tr>
								<td width="5%"  class="sortableTD" nowrap="nowrap">  <%++cnt;%> <%=++pgNo%>
								</td>
								<s:if test="reqCodeChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivReqName"/>
										<s:hidden name="ivReqCode"/> <s:hidden name="ivReqName"/> 
									</td>
								</s:if>
								<s:if test="postionChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivPosition"/> 
										<s:hidden name="ivPosition"/>
									</td>
								</s:if>
								<s:if test="interviewerChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivInterviewer"/>  
										<s:hidden name="ivInterviewer"/>
									</td>
								</s:if>
								<s:if test="candidateNameChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivCandidateName"/>   
										<s:hidden name="ivCandidateName"/>
									</td>
								</s:if>
								<s:if test="roundOfIntChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivRoundOfInterview"/>   
										<s:hidden name="ivRoundOfInterview"/>
									</td>
								</s:if>
								<s:if test="qualificationDtlChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivQualification"/>   
										<s:hidden name="ivQualification"/>
									</td>
								</s:if>
								<s:if test="technicalSkillChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivTechSkill"/>   
										<s:hidden name="ivTechSkill"/>
									</td>
								</s:if>
								<s:if test="communicationLevelChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivCommunication"/>   
										<s:hidden name="ivCommunication"/>
									</td>
								</s:if>
								<s:if test="managementSkillChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivManagement"/>   
										<s:hidden name="ivManagement"/>
									</td>
								</s:if>
								<s:if test="personalityChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivPersonality"/>   
										<s:hidden name="ivPersonality"/>
									</td>
								</s:if>
								<s:if test="learningSkillChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivLearning"/>   
										<s:hidden name="ivLearning"/>
									</td>
								</s:if>
								<s:if test="relevantExpChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivRelevant"/>   
										<s:hidden name="ivRelevant"/>
									</td>
								</s:if>
								<s:if test="suitabilityAbiltiyChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivSuitability"/>   
										<s:hidden name="ivSuitability"/>
									</td>
								</s:if>
								<s:if test="evalScoreChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivEvalScore"/>   
										<s:hidden name="ivEvalScore"/>
									</td>
								</s:if>
								<s:if test="evalPercentageChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivEvalPercentage"/>   
										<s:hidden name="ivEvalPercentage"/>
									</td>
								</s:if>
								<s:if test="statusChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivStatus"/>   
										<s:hidden name="ivStatus"/>
									</td>
								</s:if>
								 <s:if test="recruiterChkFlag">
									<td width="25%" class="sortableTD" > <s:property value="ivRecruiter"/>   
										<s:hidden name="ivRecruiter"/>
									</td>
								</s:if>
							</tr>
							</s:iterator>
							<s:if test="noData">
								<tr>
									<td width="100%" colspan="7" align="center"><font
									color="red">There is no data to display.</font></td>
								</tr>
							</s:if>
							<s:else>
								<tr>
									<td width="100%" colspan="11" align="right">  <b>Total records :
									 	<s:property value="dataLength"/><s:hidden name="dataLength"  /></b> 
									 </td>
								</tr> 
							</s:else> 
						</table><!--Table 3-->
					</td>
				</tr>		
			</table><!--Table 2-->
		</td>
	</tr>
	<tr>
	   <td colspan="3">
	      <table width="100%" border="0" cellpadding="0" cellspacing="0" >
			<tr>
				 <td  width="70%" > <input type="button" value="  Back  " class="token" onclick="callBack();">   
			       <s:if test="noData"> 
			       </s:if> 
			       <s:else>
			          <input type="button" value=" Export In Pdf " class="token" onclick="callReportForDisp('P');">  
					  <input type="button" value=" Export In Xls " class="token" onclick="callReportForDisp('X');">  
					  <input type="button" value=" Export In Doc " class="token" onclick="callReportForDisp('T');">
					</s:else>
					    </td>
					    <td  width="30%" >  
					    <s:if test="noData"> 
					    </s:if> 
						    <s:else> <label class="set" name="export.all" id="export.all"
							     ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : 
							     <input type="checkbox" name="exportAll_bot" id="exportAll_bot" checked="checked" 
							     onclick="callChkBox('exportAll_bot');" > 
						     </s:else>  
					     </td>
					</tr>
				</table>
			</td>
	</tr> 			 
</table>
</s:form>
<script>
window.onload=   document.getElementById('pageNoField').focus();

function callReportForDisp(reportType)
{
 document.getElementById('paraFrm_reportType').value=reportType;
 callReport('InterviewEvaluationReport_report.action');    
}

function callBack()
 {
 try{
   document.getElementById('paraFrm_radioReq').value="";
   document.getElementById('paraFrm_radioInterviewer').value="";
   document.getElementById('paraFrm_radioCandidate').value="";
   document.getElementById('paraFrm_radioPosition').value="";
   
   radioStatus =document.getElementById('paraFrm_radioStatus').value;
  if(radioStatus=="C")
  { 
   document.getElementById('paraFrm_radioReq').value="checked";
  } 
  if(radioStatus=="N")
  { 
   document.getElementById('paraFrm_radioInterviewer').value="checked";
  }
  
    if(radioStatus=="M")
  { 
   document.getElementById('paraFrm_radioCandidate').value="checked";
  } 
    if(radioStatus=="P")
    { 
   document.getElementById('paraFrm_radioPosition').value="checked";
  }
  
  document.getElementById('paraFrm_backFlag').value="true";
  document.getElementById('paraFrm_reportType').value="1";
  document.getElementById('paraFrm').action='InterviewEvaluationReport_callBack.action';
  document.getElementById('paraFrm').submit(); 
  }catch(e){
  	alert(e);
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
		   
			document.getElementById('paraFrm').action='InterviewEvaluationReport_viewOnScreen.action';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
	 function callPage(id,pageImg){  
	 try{
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
		}catch(e)
		{ 
		 
		}
		document.getElementById('paraFrm').action='InterviewEvaluationReport_viewOnScreen.action';
		document.getElementById('paraFrm').submit(); 
	}		



</script> 