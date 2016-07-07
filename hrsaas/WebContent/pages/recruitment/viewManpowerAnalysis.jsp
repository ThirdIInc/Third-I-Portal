<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<script>
var reqCodeArray=new Array();
</script>
<s:form action="ManpowerRequisitionAnalysis" validate="true" id="paraFrm"
	validate="true" theme="simple"><s:hidden name="noData"/>
	<s:hidden name="dateChk"/><s:hidden name="reqChk"/><s:hidden name="closeChk"/><s:hidden name="vacChk"/>	<s:hidden name="overChk"/><s:hidden name="openChk"/>	
<s:hidden name="reqsnName"/><s:hidden name="positionName"/><s:hidden name="advOverDueOpt"/><s:hidden name="advOverDueVal"/>
<s:hidden name='aId'/> <s:hidden name='divId'/> <s:hidden name="backFlag"/> <s:hidden name="selectedReqName"/>
<s:hidden name="reqsnDate"/><s:hidden name="frmDate"/><s:hidden name="tDate"/><s:hidden name="hidSelectedReqName"/>
<s:hidden name="advVacOpt"/><s:hidden name="advVacVal"/><s:hidden name="firstRadio"/>
	<s:hidden name="checkFlag"/><s:hidden name="firstSort"/><s:hidden name="firstAsc"/><s:hidden name="firstDesc"/><s:hidden name="itrCloseVacFlag"/>
	<s:hidden name="thirdDesc"/><s:hidden name="thirdAsc"/><s:hidden name="thirdSort"/><s:hidden name="secDesc"/><s:hidden name="secAsc"/><s:hidden name="secSortBy"/>
	<s:hidden name="hidFirstAsc" /><s:hidden name="hidFirstDesc"/><s:hidden name="hidSecAsc"/><s:hidden name="hidSecDesc"/><s:hidden name="hidThirdAsc"/><s:hidden name="hidThirdDesc"/>
	<s:hidden name="myPage"/><s:hidden name="hidVacStatus" /><s:hidden name="hidOverDue" />
	<s:hidden name="selectedReq"/><s:hidden name="hidReqByDate" /><s:hidden name="reqsnStatus"/>
<s:hidden name="exportAllData"/><s:hidden name="totRecord"/><s:hidden name="hidPosition"/><s:hidden name="hidReqsStatus"/>
  <s:hidden name="hidNoOfVac" /><s:hidden name="hidOpenVac"/><s:hidden name="hidApprvStatus"/><s:hidden name="positionChk"/><s:hidden name="statusChk"/>
	<s:hidden name="reqsnCode" />	<s:hidden name="hidReqsnDate" /><s:hidden name="searchSetting"/><s:hidden name="apprvChk"/>
	<s:hidden name="positionId"/><s:hidden name="headerChk"/><s:hidden name="scrnReqCode"/><s:hidden name="secondRadio"/><s:hidden name="thirdRadio"/>
	<s:hidden name="editReqFlag"/><s:hidden name="radioFlag"/><s:hidden name="selectedReqFlag"/><s:hidden name="mraRepCode"/>
<s:hidden name="advApprvStat"/><s:hidden name="totalCtcChk"/><s:hidden name="hiringMngrChk"/><s:hidden name="apprvrNameChk"></s:hidden><s:hidden name="recruitNameChk"></s:hidden><s:hidden name="closedDateChk"/><s:hidden name="chkClosedDate"/><s:hidden name="chkTotalCtc"/><s:hidden name="chkHiringMngr"></s:hidden><s:hidden name="hidHiringMngr"></s:hidden><s:hidden name="chkApprvrName"></s:hidden><s:hidden name="hidApprvrName"></s:hidden><s:hidden name="chkRecruitName"></s:hidden ><s:hidden name="hidRecruitName"></s:hidden><s:hidden name="hidTotalCtc"/>
<s:hidden name="hidClosedDate"/><s:hidden name="showReqsnFlag"/><s:hidden name="positionFlg"/><s:hidden name="reqsnFlg"/><s:hidden name="positionRadio"/>
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">
					Manpower Requisition Analysis</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>	
						<input type="button" class="back" value="   Back" onclick="callBack();"/>	
						<s:if test="noData">
						<INPUT TYPE="button" class="token" value="Export into Pdf" onclick="callReport('Pdf');" />
						<input type="button" class="token" value="Export into Xls" onclick="callReport('Xls');"/>
						<input type="button" class="token" value="Export into Doc" onclick="callReport('Txt');"/>
						&nbsp;&nbsp;&nbsp;<strong class="forminnerhead"><label
								class="set" id="export.all" name="export.all"
								ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label></strong><input type="checkbox" name="exportAll" id="exportAll" onclick="callReportAll();"/>
					</s:if>
					</td>
					<td width="22%"></td>
				</tr>
			</table>

			</td>
		</tr>
	<s:if test="noData">	
		 <tr>
          <td colspan="3">
          <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              <tr>
              <td width="100%">
              
              <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                	<tr>
                    <td height="27" class="text_head">
                    	<strong>Manpower Requisition List :</strong></td>
	                    	
	                    <%
					int totalPage = (Integer) request.getAttribute("totalPage");
					int pageNo = (Integer) request.getAttribute("PageNo");
					%>
						<td align="right"><b>Page:</b> 
						 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="10" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
						</td>
                  </tr>
                  </table>              
              </td>
		      </tr>
		      
		       <tr>
              <td width="100%">
              <table width="100%" border="0" cellpadding="0" cellspacing="0">
	                        <tr >
	                          <td width="5%"  valign="top" class="formth"><b>
	                          	<label class="set" name="serial.no" id="serial.no"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
	                          <td width="13%" valign="top" class="formth"><b>
	                          	<label  class = "set" name="reqs.code" id="requisition.code" 
						            ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
						            
						       <s:if test="hdrReqFlg">     
		                          <td width="11%" valign="top" class="formth" nowrap="nowrap"><b>
		                          	<label  class = "set" name="reqs.date" id="reqs.date" 
							            ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label></b></td>
						            </s:if>
						            
						 	<s:if test="hdrPosFlag">
	                          <td width="15%" valign="top" class="formth"><b>
	                          	<label  class = "set" name="position" id="position" 
						            ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
						            
						       </s:if> 
						       
						       <s:if test="hdrApprvStatFlag"> <td width="15%" valign="top" class="formth"><b>    
						      <label class="set" id="apprvStatus"
								name="apprvStatus" ondblclick="callShowDiv(this);"><%=label.get("apprvStatus")%>
						     </label></b></td>
						     </s:if>  
						         
						   <s:if test="hdrReqsStatFlag"> <td width="15%" valign="top" class="formth"><b>    
						       <label class="set" id="reqsStatus"
								name="reqsStatus" ondblclick="callShowDiv(this);"><%=label.get("reqsStatus")%>
						     </label></b></td>
						     </s:if> 
						     
						     
						    <s:if test="hdrTotalCtcFlag"> <td width="15%" valign="top" class="formth"><b>    
						       <label class="set" id="totCtc"
								name="totCtc" ondblclick="callShowDiv(this);"><%=label.get("totCtc")%>
						     </label></b></td>
						     </s:if> 
						     
						     
						     
						     
						       
						       <s:if test="hdrNoOfVacFlg">
	                          <td width="15%" valign="top" class="formth"><b>
	                          	<label  class = "set" name="noofvac" id="noofvac2" 
						            ondblclick="callShowDiv(this);"><%=label.get("noofvac")%></label></b></td></s:if>
	                        
	                          <s:if test="hdrReqByDt">
	                          <td width="15%" valign="top" class="formth"><b>
		                          <label  class = "set" name="reqdbydate" id="reqdbydate2" 
							            ondblclick="callShowDiv(this);"><%=label.get("reqdbydate")%></label></b></td></s:if>
							            
							   <s:if test="hdrClosedDateFlag"> <td width="6%"  valign="top" class="formth"><b>
	                          	<label  class = "set" name="clsdDt" id="clsdDt" 
							            ondblclick="callShowDiv(this);"><%=label.get("clsdDt")%></label></b></td></s:if>         
							            
	                         <s:if test="hdrOvrFlg"> <td width="6%"  valign="top" class="formth"><b>
	                          	<label  class = "set" name="overdue" id="overdue2" 
							            ondblclick="callShowDiv(this);"><%=label.get("overdue")%></label></b></td></s:if>
							   
	                        <s:if test="hdrHrngMngrFlag"> <td width="6%"  valign="top" class="formth"><b>
	                          	<label  class = "set" name="hiringMngr" id="hiringMngr2" 
							            ondblclick="callShowDiv(this);"><%=label.get("hiringMngr")%></label></b></td></s:if>
							    
							     <s:if test="hdrApprvrNameFlag"> <td width="6%"  valign="top" class="formth"><b>
	                          	<label  class = "set" name="ApprvrName" id="ApprvrName2" 
							            ondblclick="callShowDiv(this);"><%=label.get("apprvrName")%></label></b></td></s:if>
							               <s:if test="hdrRecruitNameFlag"> <td width="6%"  valign="top" class="formth"><b>
	                          	<label  class = "set" name="RecruitName" id="RecruitName2" 
							            ondblclick="callShowDiv(this);"><%=label.get("recruitName")%></label></b></td></s:if>
							    
	                          
	                        </tr>
	                        
	                       
	                        
		                    <%! int i = 0 ; %>
		                    <% int k = 1; int c=0;%>
		                    	<%
											int cnt = pageNo * 20 - 20;
											int m = 0,counter=0;
									%>
		                        
		                    <s:iterator value="list">
								<tr>
									<td width="5%" class="sortabletd" align="center"><%=++cnt%>
											<%
											++m;
											%></td>
									<td width="13%" class="sortabletd"><s:property value ="itReqName"/>	<s:hidden name="itReqCode"/></td>
									<script type="text/javascript">
							
													reqCodeArray[<%=counter%>] = '<s:property  value="itReqCode"/>';
							
											</script>	
												
								    <s:if test="itrReqFlag">
								    <td width="11%" class="sortableTD" align="center"><s:property value ="itReqDate"/></td></s:if>
									<s:if test="itrPosFlag">
									<td width="15%" class="sortableTD"><s:property value="itPosition"/>&nbsp;</td></s:if>
									<s:if test="itrApprvStatFlag">
									<td width="15%" class="sortableTD"><s:property value="irtApprvStatus"/>&nbsp;</td></s:if>
									<s:if test="itrReqsStatFlag">
									<td width="15%" class="sortableTD"><s:property value="itrReqsStatus"/>&nbsp;</td></s:if>
									
									<s:if test="itrTotalCtcFlag">
									<td width="15%" class="sortableTD"><s:property value="totalCtc"/>&nbsp;</td></s:if>
									
									<s:if test="itrNoOfVacFlg"><td width="15%" class="sortableTD"  align="right"><s:property value="itNoOfVac"/>&nbsp;</td></s:if>
									<s:if test="itrReqByFlgDt" ><td width="15%" class="sortableTD" nowrap="nowrap"><s:property value="itReqByDate "/>&nbsp;</td></s:if>
									<s:if test="itrClosedDateFlag"><td width="15%" align="right" class="sortableTD"><s:property value="closedDate"/>&nbsp;</td></s:if>
									
									<s:if test="itrOvrcFlg"><td width="6%" align="right" class="sortableTD"><s:property value="itOvrDue"/>&nbsp;</td></s:if>
								<s:if test="itrHrngMngrFlag"><td width="6%" align="right" class="sortableTD"><s:property value="itHrngMngr"/>&nbsp;</td></s:if>
								 <s:if test="itrApprvrNameFlag"><td width="6%" align="right" class="sortableTD"><s:property value="itApprvrName"/>&nbsp;</td></s:if>
								<s:if test="itrRecruitNameFlag"><td width="6%" align="right" class="sortableTD"><s:property value="itRecruitName"/>&nbsp;</td></s:if>
								</tr>
								<%k++; c++; ++counter;%>
							</s:iterator>
							
              				 <% i=k ;
              				 	m=i;
              				 %>        
                    </table>
                    	<input type="hidden" name="count" id="count" value="<%=c%>"/>
                   
              
              </td>
              </tr>
		      
		      
		      
		   </table>
		   </td>
		 </tr>
		 
	
		 
				 
		 
		 </s:if><s:else>
		 <tr><td width="100%" colspan="3">
		 	<table width="100%" class="formbg">
			 <tr >
	                          <td width="5%"  valign="top" class="formth"><b>
	                          	<label class="set" name="serial.no" id="serial.no"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
	                          <td width="13%" valign="top" class="formth"><b>
	                          	<label  class = "set" name="reqs.code" id="requisition.code" 
						            ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
						            
						       <s:if test="hdrReqFlg">     
		                          <td width="11%" valign="top" class="formth" nowrap="nowrap"><b>
		                          	<label  class = "set" name="reqs.date" id="reqs.date" 
							            ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label></b></td>
						            </s:if>
						            
						 	<s:if test="hdrPosFlag">
	                          <td width="15%" valign="top" class="formth"><b>
	                          	<label  class = "set" name="position" id="position" 
						            ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
						            
						       </s:if> 
						       
						       <s:if test="hdrApprvStatFlag"> <td width="15%" valign="top" class="formth"><b>    
						      <label class="set" id="apprvStatus"
								name="apprvStatus" ondblclick="callShowDiv(this);"><%=label.get("apprvStatus")%>
						     </label></b></td>
						     </s:if>  
						         
						   <s:if test="hdrReqsStatFlag"> <td width="15%" valign="top" class="formth"><b>    
						       <label class="set" id="reqsStatus"
								name="reqsStatus" ondblclick="callShowDiv(this);"><%=label.get("reqsStatus")%>
						     </label></b></td>
						     </s:if> 
						     
						     
						    <s:if test="hdrTotalCtcFlag"> <td width="15%" valign="top" class="formth"><b>    
						       <label class="set" id="totCtc"
								name="totCtc" ondblclick="callShowDiv(this);"><%=label.get("totCtc")%>
						     </label></b></td>
						     </s:if> 
						      <s:if test="hdrNoOfVacFlg">
	                          <td width="15%" valign="top" class="formth"><b>
	                          	<label  class = "set" name="noofvac" id="noofvac2" 
						            ondblclick="callShowDiv(this);"><%=label.get("noofvac")%></label></b></td></s:if>
	                        
	                          <s:if test="hdrReqByDt">
	                          <td width="15%" valign="top" class="formth"><b>
		                          <label  class = "set" name="reqdbydate" id="reqdbydate2" 
							            ondblclick="callShowDiv(this);"><%=label.get("reqdbydate")%></label></b></td></s:if>
							            
							   <s:if test="hdrClosedDateFlag"> <td width="6%"  valign="top" class="formth"><b>
	                          	<label  class = "set" name="clsdDt" id="clsdDt" 
							            ondblclick="callShowDiv(this);"><%=label.get("clsdDt")%></label></b></td></s:if>         
							            
	                         <s:if test="hdrOvrFlg"> <td width="6%"  valign="top" class="formth"><b>
	                          	<label  class = "set" name="overdue" id="overdue2" 
							            ondblclick="callShowDiv(this);"><%=label.get("overdue")%></label></b></td></s:if>
							   
	                           <s:if test="hdrHrngMngrFlag"> <td width="6%"  valign="top" class="formth"><b>
	                          	<label  class = "set" name="hiringMngr" id="hiringMngr2" 
							            ondblclick="callShowDiv(this);"><%=label.get("hiringMngr")%></label></b></td></s:if>
							   <s:if test="hdrApprvrNameFlag"> <td width="6%"  valign="top" class="formth"><b>
	                          	<label  class = "set" name="ApprvrName" id="ApprvrName2" 
							            ondblclick="callShowDiv(this);"><%=label.get("apprvrName")%></label></b></td></s:if>
							   <s:if test="hdrRecruitNameFlag"> <td width="6%"  valign="top" class="formth"><b>
	                          	<label  class = "set" name="RecruitName" id="RecruitName2" 
							            ondblclick="callShowDiv(this);"><%=label.get("recruitName")%></label></b></td></s:if>
							   
	                        </tr>
		                         <tr>
			 
			 					<td align="center" width="100%" nowrap="nowrap" colspan="11"><font color="red">There is no data to display.</font></td>
			 					</tr>
		 	
		 	
		 	
		 	</table>
		 	</td>
		 </tr>
		 
		 
		 
		 
		
		 
		 </s:else>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><input type="button" class="back" value="   Back" onclick="callBack();"/>	
					<s:if test="noData">
						<INPUT TYPE="button" class="token" value="Export into Pdf" onclick="callReport('Pdf');" />
						<input type="button" class="token" value="Export into Xls" onclick="callReport('Xls');"/>
						<input type="button" class="token" value="Export into Doc" onclick="callReport('Txt');"/>
					&nbsp;&nbsp;&nbsp;<strong class="forminnerhead"><label
								class="set" id="export.all" name="export.all"
								ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label></strong><input type="checkbox" name="exportAll1" id="exportAll1" onclick="callReportAll1();"/>
						
						</s:if>	
						
						</td>
					<td width="22%" align="right">
					
					 <s:if test="noData">   
					<b>Total No. of records :<s:property value="totRecord"/></b></s:if>
					</td>
				</tr>
			</table>

			</td>
		</tr>
	
		
		
	</table>
</s:form>
<script>

callExport();
function callExport(){
document.getElementById('exportAll1').checked=true;
document.getElementById('exportAll').checked=true;
document.getElementById('paraFrm_exportAllData').value="Y";
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
		 	document.getElementById('paraFrm_exportAllData').value="N";
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
	
		/*var code;
			if (id.keyCode) 
				code = id.keyCode;
			else
				if (id.which) 
					code = id.which;
					
			if(code==13) 
			{
			pageNo =document.getElementById('pageNoField').value;		
		 	totalPage =document.getElementById('totalPage').value;		
			document.getElementById('paraFrm_myPage').value=pageNo;
			document.getElementById('paraFrm_exportAllData').value="N";
	      
	        if(pageNo==""){
	        alert("Please enter page number");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than total page number");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }*/
		   
			document.getElementById('paraFrm').action='ManpowerRequisitionAnalysis_showPagewise.action';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
	function callPage(id,pageImg){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	   if(pageNo==""){
	        alert("Please enter page number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than total Page number.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
		if(id=='P'){
		var p=document.getElementById('paraFrm_myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('paraFrm_myPage').value;
		id=eval(p)+1;
		} 
		document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm_exportAllData').value="N";
		document.getElementById('paraFrm').action='ManpowerRequisitionAnalysis_showPagewise.action';
		document.getElementById('paraFrm').submit(); 
	}	

	
	function callReport(type){
	var str="";
	var str1="";
	if(reqCodeArray.length>0){
			for (var i=0;i<reqCodeArray.length;i++) {
				
					str +=reqCodeArray[i]+"," ;
				
			}
		 document.getElementById('paraFrm_scrnReqCode').value=str;
		
		
		
		 
	}
	    document.getElementById('paraFrm').action='ManpowerRequisitionAnalysis_showReport.action?reportType='+type;
		document.getElementById('paraFrm').submit();
	
	
	}
	
	
function callBack(){
		document.getElementById('paraFrm_myPage').value="";
		document.getElementById('paraFrm_exportAllData').value="Y";
 		document.getElementById('paraFrm_backFlag').value="true";
 		document.getElementById('paraFrm_scrnReqCode').value="";
 		//document.getElementById('paraFrm_selectedReq').value="";
 		document.getElementById('paraFrm').action='ManpowerRequisitionAnalysis_input.action';
		document.getElementById('paraFrm').submit();

}	
function callReportAll(){
		
			if(document.getElementById('exportAll').checked){
		 	document.getElementById('paraFrm_exportAllData').value="Y";
		 	document.getElementById('exportAll1').checked=true;
			}
			if(!document.getElementById('exportAll').checked){
		 	document.getElementById('paraFrm_exportAllData').value="N";
		 	document.getElementById('exportAll1').checked=false;
			}
			
			
	}	
	
function callReportAll1(){
		
			if(document.getElementById('exportAll1').checked){
			document.getElementById('exportAll').checked=true;
		 	document.getElementById('paraFrm_exportAllData').value="Y";
			}
			if(!document.getElementById('exportAll1').checked){
			document.getElementById('exportAll').checked=false;
		 	document.getElementById('paraFrm_exportAllData').value="N";
			}
			
	}		
	
	
	
	/*function callBack()
 {
 
  document.getElementById('paraFrm_backFlag').value="true";
  document.getElementById('paraFrm_reportType').value="1";
  document.getElementById('paraFrm').action='OpenVacReport_callBack.action';
  document.getElementById('paraFrm').submit(); 
 }	*/
		
	</script>