 <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="OpenVacReport" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	  <tr>
			<td colspan="3" width="100%">  <s:hidden name="searchFlag"/>
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Open Vacancy Analysis</strong></td>
						<td width="3%" valign="top" class="otxt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
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
					     <input type="button" value=" Export In Pdf " class="token" onclick="callReportForDisp('P');"> 
					     <input type="button" value=" Export In Xls " class="token" onclick="callReportForDisp('X');"> 
					    <input type="button" value=" Export In Doc " class="token" onclick="callReportForDisp('T');">
					     </s:else>
					     </td>
					    
					    <td  width="30%" >  <s:if test="noData"> </s:if> <s:else> <label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll" id="exportAll" checked="checked"  onclick="callChkBox('exportAll');" > </s:else>  </td>
					</tr>
				</table>
			</td>
		  </tr>
		 
		    <tr>
			<td width="100%">   <s:hidden name="hidSelectedReqName"/>  <s:hidden name="searchSetting"/>  <s:hidden name='aId'/> <s:hidden name='divId'/> <s:hidden name='backFlag'/> 
			 <s:hidden name="myPage"/> <s:hidden name="show"  />   <s:hidden name="checkedCount" value="14"/>
			 <s:hidden name="frmDate"/> <s:hidden name="toDate"  /> <s:hidden name="reqname"/> <s:hidden name="reqCode"  />
			 <s:hidden name="recruiterName"/> <s:hidden name="recruiterCode"  /> <s:hidden name="recruiter"/> <s:hidden name="reportType"  />
			 <s:hidden name="reqname1"/> <s:hidden name="hiringManagerId"  /> <s:hidden name="selectedReq"/> <s:hidden name="position"  />
			 <s:hidden name="positionId"/> <s:hidden name="dateFilter"  />  <s:hidden name="thenByOrder2"/> <s:hidden name="thenBy2"  />  
			 <s:hidden name="sortBy"/> <s:hidden name="sortByOrder"  /> <s:hidden name="thenBy1"/> <s:hidden name="thenByOrder1"  />
			 <s:hidden name="totalVacAdvCom"/> <s:hidden name="asgnVacAdvCom"  /> <s:hidden name="openVacAdvCom"/> <s:hidden name="closeVacAdvCom"  /> 
             <s:hidden name="totalVacAdvTxt"/> <s:hidden name="asgnVacAdvTxt"  /> <s:hidden name="openVacAdvTxt"/> <s:hidden name="closeVacAdvTxt"  />
	         <s:hidden name="requirdeByDateTxt"/>    <s:hidden name="requirdeByDateCom"/> 
              <s:hidden name="reqCodeChk"/> <s:hidden name="postionChk"  /> <s:hidden name="dateChk"/> <s:hidden name="hiringMangerChk"  /> 
             <s:hidden name="totalVacChk"/> <s:hidden name="recruiterChk"  /> <s:hidden name="requiredDateChk"/> <s:hidden name="vacAssignChk"  />
              <s:hidden name="openVacChk"/> <s:hidden name="closedvacChk"  />   <s:hidden name="closedDateChk"/> <s:hidden name="overDueDayChk"  /><s:hidden name="approvarChk" />
               <s:hidden name="dataLength"  />  <s:hidden name="requirdeByToDateTxt"  /> <s:hidden name="requirdeByFromDateTxt"  />  
              <s:hidden name="settingName"/> <s:hidden name='settingNameDup'/>  <s:hidden name='selectedReqName'/>   
                 <s:hidden name="radioStatus"/>  <s:hidden name="radioReq"/> <s:hidden name="hiringManagerName"/>    <s:hidden name="radioRecr"/> <s:hidden name="radioHirMng"/> <s:hidden name="radioPosition"/> 
				<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg"><!--Table 3-->
					    <tr>
						     <td>
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="27" class="text_head"><strong>Open Vacancy List </strong></td>
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
									<s:if test="reqCodeChkFlag">
									<td width="20%" valign="top" class="formth"><label  class = "set" name="reqs.code" id="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></td>
									</s:if>
									<s:if test="postionChkFlag">
									<td width="25%" valign="top" class="formth"><label  class = "set" name="position" id="position"  ondblclick="callShowDiv(this);"><%=label.get("position")%></label></td> 
			            			</s:if>
			            			<s:if test="dateChkFlag">
			            			<td  valign="top" class="formth"><label  class = "set" name="reqs.date" id="reqs.date"  ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label></td> 
									</s:if>
									 <s:if test="hiringMangerChkFlag">
									<td width="15%" valign="top" class="formth" ><label  class = "set" name="hiring.mgr" id="HiringmanagerLabel" ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></td>
									</s:if> 
			            	 	    <s:if test="recruiterChkFlag">
			            	 	    <td width="15%" valign="top" class="formth" ><label  class = "set" name="rec.name" id="RecruiternameLabel" ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label></td> 
			            		    </s:if>
			            		     <s:if test="requiredDateChkFlag">
			            		    <td  valign="top" class="formth"  ><label  class = "set" name="RequiredDateLabel" id="RequiredDateLabel"  ondblclick="callShowDiv(this);"><%=label.get("RequiredDateLabel")%></label></td> 
				            	 	</s:if>
				            	 	 <s:if test="totalVacChkFlag">
									<td width="15%" valign="top" class="formth" ><label  class = "set" name="TotalvacancyLabel" id="TotalvacancyLabel" 	ondblclick="callShowDiv(this);"><%=label.get("TotalvacancyLabel")%></label></td> 
			            	 	    </s:if>
			            	 	    
				            	 	 <s:if test="vacAssignChkFlag">
				            	 	 <td width="15%" valign="top" class="formth" ><label  class = "set" name="vacLabelAssigned" id="vacLabelAssigned"  ondblclick="callShowDiv(this);"><%=label.get("vacLabelAssigned")%></label></td> 
				            		</s:if>
				            		 <s:if test="openVacChkFlag">
				            		 <td width="15%" valign="top" class="formth" ><label  class = "set" name="OpenvacancyLabel" id="OpenvacancyLabel" ondblclick="callShowDiv(this);"><%=label.get("OpenvacancyLabel")%></label></td> 
				            	    </s:if>
				            	     <s:if test="closedvacChkFlag">
				            	     <td width="15%" valign="top" class="formth" ><label  class = "set" name="ClosedvacancyLabel" id="ClosedvacancyLabel" ondblclick="callShowDiv(this);"><%=label.get("ClosedvacancyLabel")%></label></td> 
				                     </s:if>
				                     
				                     <s:if test="closedDateChkFlag">
				            	     <td width="15%" valign="top" class="formth" ><label  class = "set" name="ClosedDateLabel" id="ClosedDateLabel" ondblclick="callShowDiv(this);"><%=label.get("ClosedDateLabel")%></label></td> 
				                     </s:if>
				                     
				                     <s:if test="overDueDayChkFlag">
				            	     <td width="15%" valign="top" class="formth" ><label  class = "set" name="overDueDayLabel" id="overDueDayLabel" ondblclick="callShowDiv(this);"><%=label.get("overDueDayLabel")%></label></td> 
				                     </s:if>
				                       
				                     <s:if test="approvarChkFlag">
				            	     <td width="15%" valign="top" class="formth" ><label  class = "set" name="ApprovarLabel2" id="ApprovarLabel2" ondblclick="callShowDiv(this);"><%=label.get("approvar.name")%></label></td> 
				                     </s:if>
				              	</tr>
								 
							 	<% int cnt=0; 
								   int pgNo = pageNo * 20 - 20;
								   %> 
								<s:iterator value="openViewList">  
									<tr> 
									<td width="5%"  class="sortableTD" nowrap="nowrap">  <%++cnt;%> <%=++pgNo%>
										 <s:hidden name="ivReqCode"/>  <s:hidden name="ivPostion"/>  <s:hidden name="ivReqDate"/>
										 <s:hidden name="ivHiringManager"/>  <s:hidden name="ivTotalVac"/>  <s:hidden name="ivRecruiterName"/>
										 <s:hidden name="ivRequiredDate"/>  <s:hidden name="ivAssgnVac"/>  <s:hidden name="ivOpenVac"/>
										 <s:hidden name="ivCloseVac"/>  <s:hidden name="ivApprovarName"/>     
									</td>  
	                                 <s:if test="reqCodeChkFlag">
									<td width="25%" class="sortableTD"  > <s:property value="ivReqCode"/> </td>
									</s:if>
									<s:if test="postionChkFlag">
									<td width="25%"  class="sortableTD"> <s:property value="ivPostion"/></td> 
									 </s:if>
									 <s:if test="dateChkFlag">
									<td  class="sortableTD" nowrap="nowrap"  > <s:property value="ivReqDate"/> </td>
									</s:if>
									 <s:if test="hiringMangerChkFlag">
									<td width="25%"  class="sortableTD"> <s:property value="ivHiringManager"/></td> 
									</s:if> 
									 <s:if test="recruiterChkFlag">
									<td width="25%"  class="sortableTD"> <s:property value="ivRecruiterName"/></td> 
									</s:if>
									 <s:if test="requiredDateChkFlag">
									<td class="sortableTD" nowrap="nowrap" > <s:property value="ivRequiredDate"/> &nbsp; </td>
									</s:if>
									<s:if test="totalVacChkFlag">
									<td width="25%" class="sortableTD" align="right"  > <s:property value="ivTotalVac"/> </td>
									</s:if>
									 <s:if test="vacAssignChkFlag">
									<td width="25%"  class="sortableTD" align="right"> <s:property value="ivAssgnVac"/></td> 
									</s:if> 
									 <s:if test="openVacChkFlag">
									<td width="25%" class="sortableTD" align="right" > <s:property value="ivOpenVac"/> </td>
									</s:if>
									 <s:if test="closedvacChkFlag">
									<td width="25%"  class="sortableTD" align="right"> <s:property value="ivCloseVac"/></td>  
									</s:if> 
									
									<s:if test="closedDateChkFlag">
									<td nowrap="nowrap"  class="sortableTD" align="right"> <s:property value="ivClosedDate"/>  &nbsp;</td>  
									</s:if> 
									
									<s:if test="overDueDayChkFlag">
									<td width="25%"  class="sortableTD" align="right"> <s:property value="ivOverDueDay"/></td>  
									</s:if> 
									
										<s:if test="approvarChkFlag">
									<td width="25%"  class="sortableTD" align="right"> <s:property value="ivApprovarName"/></td>  
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
										<td width="100%" colspan="11" align="right">  <b>Total records : <s:property value="dataLength"/></b>  </td>
								 </tr> 
								</s:else>
							</table>
							</td>
							</tr>		
							</table><!--Table 5-->
						</td>
					</tr>					
				</table><!--Table 3-->
			</td>
		</tr><!--end of vacancy listing--> 
		  <tr>
	         <td colspan="3">
	           <table width="100%" border="0" cellpadding="0" cellspacing="0" >
					<tr>
					    <td  width="70%" > <input type="button" value="  Back  " class="token" onclick="callBack();">   
			              <s:if test="noData"> </s:if> <s:else>
			            <input type="button" value=" Export In Pdf " class="token" onclick="callReportForDisp('P');">  
					     <input type="button" value=" Export In Xls " class="token" onclick="callReportForDisp('X');">  
					      <input type="button" value=" Export In Doc " class="token" onclick="callReportForDisp('T');">
					       </s:else>
					       </td>
					    
					    <td  width="30%" >  <s:if test="noData"> </s:if> <s:else> <label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll_bot" id="exportAll_bot" checked="checked" onclick="callChkBox('exportAll_bot');" > </s:else>  </td>
					</tr>
				</table>
			</td>
		  </tr> 
	  </table>
</s:form>
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script> 
window.onload=   document.getElementById('pageNoField').focus();
 
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
		   
			document.getElementById('paraFrm').action='OpenVacReport_viewOnScreen.action';
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	
function callReportForDisp(reportType)
{
 document.getElementById('paraFrm_reportType').value=reportType;
   callReport('OpenVacReport_report.action');    
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
  document.getElementById('paraFrm').action='OpenVacReport_callBack.action';
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
		document.getElementById('paraFrm').action='OpenVacReport_viewOnScreen.action';
		document.getElementById('paraFrm').submit(); 
	}	
	 
	 
 function onPage()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	document.getElementById('paraFrm_myPage').value=eval(val);
	document.getElementById('selectname').value=val;
	document.getElementById('paraFrm').action="OpenVacReport_viewOnScreen.action";
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

</script>