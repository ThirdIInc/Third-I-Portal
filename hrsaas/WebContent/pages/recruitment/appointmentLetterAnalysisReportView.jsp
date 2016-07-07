  <%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="AppointLetterAnalysis" validate="true" id="paraFrm" theme="simple"> 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
	  <tr>
			<td colspan="3" width="100%">  <s:hidden name="searchFlag"/>
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Appointment Letter Analysis</strong></td>
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
			<td width="100%">  <s:hidden name="searchSetting"/>  <s:hidden name='aId'/> <s:hidden name='divId'/> <s:hidden name='backFlag'/> 
			 <s:hidden name="myPage"/> <s:hidden name="show"  />   <s:hidden name="checkedCount" value="12"/>
			 <s:hidden name="frmDate"/> <s:hidden name="toDate"  /> <s:hidden name="reqname"/> <s:hidden name="reqCode"  />
			  <s:hidden name="reportType"  />  <s:hidden name="hidSelectedReqName"/>
			 <s:hidden name="reqname1"/>   <s:hidden name="selectedReq"/> <s:hidden name="position"  />
			 <s:hidden name="positionId"/> <s:hidden name="dateFilter"  />  <s:hidden name="thenByOrder2"/> <s:hidden name="thenBy2"  />  
			 <s:hidden name="sortBy"/> <s:hidden name="sortByOrder"  /> <s:hidden name="thenBy1"/> <s:hidden name="thenByOrder1"  />
			  <s:hidden name="noVacAdvCom"/> <s:hidden name="offerDueAdvCom"  /> <s:hidden name="offerIssueAdvCom"/> <s:hidden name="offerAcceptAdvCom"  /> 
             <s:hidden name="offerRejectAdvCom"/> <s:hidden name="offerCancelAdvCom"  />  
             <s:hidden name="noVacAdvTxt"/> <s:hidden name="offerDueAdvTxt"  />  <s:hidden name="offerIssueAdvTxt"/> <s:hidden name="offerAcceptAdvTxt"  />
	          <s:hidden name="offerRejectAdvTxt"/> <s:hidden name="offerCancelAdvTxt"  /> 
              <s:hidden name="reqCodeChk"/> <s:hidden name="postionChk"  />  <s:hidden name="offerAccptedChk"/> 
              <s:hidden name="offerRejectedChk"/>  <s:hidden name="reqDateChk"/>  <s:hidden name="novacChk"  /> <s:hidden name="offerCancelChk"/> <s:hidden name="offerIssueChk"  />
              <s:hidden name="offerDueChk"/>   <s:hidden name="dataLength"  />      
              <s:hidden name="settingName"/> <s:hidden name='settingNameDup'/>  <s:hidden name='selectedReqName'/>   
              <s:hidden name="radioStatus"/>  <s:hidden name="radioReq"/>      <s:hidden name="radioRecr"/> <s:hidden name="radioHirMng"/> <s:hidden name="radioPosition"/>
              <s:hidden name="appointmentDate"/>    
              
				<table width="100%" border="0"  cellpadding="0" cellspacing="0" class="formbg"><!--Table 3-->
					    <tr>
						     <td>
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td height="27" class="text_head"><strong>Appointment Letter Information </strong></td>
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
									<td width="25%" valign="top" class="formth"><label  class = "set" name="reqs.code" id="reqs.code11" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></td>
									</s:if>
									<s:if test="postionChkFlag">
									<td width="25%" valign="top" class="formth"><label  class = "set" name="position" id="position11"  ondblclick="callShowDiv(this);"><%=label.get("position")%></label></td> 
			            			</s:if>
			            			<s:if test="reqDateChkFlag">
			            			<td  valign="top" class="formth"><label  class = "set" name="reqs.date" id="reqs.date11"  ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label></td> 
									</s:if> 
									
			            			<s:if test="novacChkFlag">
			            			<td  valign="top" class="formth"><label  class = "set" name="noOfVacLabel" id="noOfVacLabel"  ondblclick="callShowDiv(this);"><%=label.get("noOfVacLabel")%></label></td> 
									</s:if>    
									 <s:if test="offerDueChkFlag">
									<td width="10%" valign="top" class="formth" ><label  class = "set" name="offerDueLabel" id="offerDueLabel" ondblclick="callShowDiv(this);"><%=label.get("offerDueLabel")%></label></td>
									</s:if> 
			            	 	    <s:if test="offerIssueChkFlag">
			            	 	    <td width="15%" valign="top" class="formth" ><label  class = "set" name="offerIssueLabel" id="offerIssueLabel" ondblclick="callShowDiv(this);"><%=label.get("offerIssueLabel")%></label></td> 
			            		    </s:if>
			            		     <s:if test="offerAccptedChkFlag">
			            		    <td  valign="top" class="formth"  ><label  class = "set" name="offerAccptedLabel" id="offerAccptedLabel"  ondblclick="callShowDiv(this);"><%=label.get("offerAccptedLabel")%></label></td> 
				            	 	</s:if>
				            	 	 <s:if test="offerRejectedChkFlag">
									<td width="15%" valign="top" class="formth" ><label  class = "set" name="offerRejectedLabel" id="offerRejectedLabel" 	ondblclick="callShowDiv(this);"><%=label.get("offerRejectedLabel")%></label></td> 
			            	 	    </s:if> 
				            	 	 <s:if test="offerCancelChkFlag">
				            	 	 <td width="15%" valign="top" class="formth" ><label  class = "set" name="offerCancelLabel" id="offerCancelLabel"  ondblclick="callShowDiv(this);"><%=label.get("offerCancelLabel")%></label></td> 
				            		</s:if> 
				            		
				            		<td width="15%" valign="top" class="formth" ><label  class = "set" name="appointmentDateLabel" id="appointmentDateLabel"  ondblclick="callShowDiv(this);"><%=label.get("appointmentDateLabel")%></label></td>
				            		<td width="15%" valign="top" class="formth" ><label  class = "set" name="recruiterNameLabel" id="recruiterNameLabel"  ondblclick="callShowDiv(this);"><%=label.get("recruiterNameLabel")%></label></td>
				              	</tr>
								 
							 	<% int cnt=0; 
								   int pgNo = pageNo * 20 - 20;
								   %>  
	 
								<s:iterator value="openViewList">  
									<tr> 
									<td width="5%"  class="sortableTD" nowrap="nowrap">  <%++cnt;%> <%=++pgNo%>
										 <s:hidden name="ivReqCode"/>  <s:hidden name="ivPostion"/>  <s:hidden name="ivNoVac"/>
										 <s:hidden name="ivOfferDue"/>  <s:hidden name="ivOfferIssue"/>  <s:hidden name="ivOfferAccept"/>
										 <s:hidden name="ivOfferReject"/>  <s:hidden name="ivOfferCancel"/>  <s:hidden name="ivReqDate"/>  
									</td>  
	                                 <s:if test="reqCodeChkFlag">
									<td width="30%" class="sortableTD"  > <s:property value="ivReqCode"/> </td>
									</s:if>
									<s:if test="postionChkFlag">
									<td width="25%"  class="sortableTD"> <s:property value="ivPostion"/></td> 
									 </s:if> 
									 <s:if test="reqDateChkFlag">
									<td width="25%" align="center" class="sortableTD"> <s:property value="ivReqDate"/></td> 
									 </s:if>  
									 <s:if test="novacChkFlag">
									<td  class="sortableTD"  align="right"> <s:property value="ivNoVac"/> </td>
									</s:if>
									 <s:if test="offerDueChkFlag">
									<td width="20%"  align="right"  class="sortableTD"> <s:property value="ivOfferDue"/></td> 
									</s:if> 
									 <s:if test="offerIssueChkFlag">
									<td width="25%"  align="right"  class="sortableTD"> <s:property value="ivOfferIssue"/></td> 
									</s:if>
									 <s:if test="offerAccptedChkFlag">
									<td class="sortableTD"  align="right" > <s:property value="ivOfferAccept"/>   </td>
									</s:if>
									<s:if test="offerRejectedChkFlag">
									<td width="25%" class="sortableTD" align="right"  > <s:property value="ivOfferReject"/> </td>
									</s:if>
									 <s:if test="offerCancelChkFlag">
									<td width="25%"  class="sortableTD" align="right"> <s:property value="ivOfferCancel"/></td> 
									</s:if>  
									<td width="25%"  class="sortableTD" align="right"> <s:property value="ivAppointmentDate"/></td>
									<td width="25%"  class="sortableTD" align="right"> <s:property value="ivRecruiterName"/></td>
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
		   
			document.getElementById('paraFrm').action='AppointLetterAnalysis_viewOnScreen.action';
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	
function callReportForDisp(reportType)
{
 document.getElementById('paraFrm_reportType').value=reportType;
   callReport('AppointLetterAnalysis_report.action');    
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
  document.getElementById('paraFrm').action='AppointLetterAnalysis_callBack.action';
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
		document.getElementById('paraFrm').action='AppointLetterAnalysis_viewOnScreen.action';
		document.getElementById('paraFrm').submit(); 
	}	
	 
	 
 function onPage()
   {
  	var val= document.getElementById('selectname').value;
	document.getElementById('paraFrm_show').value=val;
	document.getElementById('paraFrm_myPage').value=eval(val);
	document.getElementById('selectname').value=val;
	document.getElementById('paraFrm').action="AppointLetterAnalysis_viewOnScreen.action";
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