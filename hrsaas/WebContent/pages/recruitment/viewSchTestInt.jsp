<%@ taglib prefix="s" uri="/struts-tags"%>   
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="SchdInterviewList" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="reqsId" /><s:hidden name="type"/>
	<s:hidden name="fromDate"/><s:hidden name="toDate"/>
	<s:hidden name="candidateName"/><s:hidden name="intRound"/> 
	<s:hidden name="intTime"/><s:hidden name="intVenue"/>
	<s:hidden name="intDate"/><s:hidden name="interviewer"/>
	<s:hidden name="recr"/><s:hidden name="conduct"></s:hidden>
	<s:hidden name="reqCode"/><s:hidden name="candLen"/>
	<s:hidden name="recId"/><s:hidden name="candCode"/>
	<s:hidden name="candCheck"/>
	<s:hidden name="intStatusReport" /><s:hidden name="backFlag"/><s:hidden name="aId"/><s:hidden name="divId"/>
	<s:hidden name="intStatus"/>
		<s:hidden name="testStatus"/>
	<s:hidden name="intrvCode" />
	 <!-- flags for headings of Iterator  for test data-->
	 
  <s:hidden name="dateHd"/>
  <s:hidden name="candCheckHd"/>
  <s:hidden name="recruiterHd"/> 
 <s:hidden name="EmailCheckHd"/>
  <s:hidden name="contactCheckHd"/>
  <s:hidden name="testTimeHd"/>
  <s:hidden name="testVenueHd"/>
  <s:hidden name="testStatusHd"/>
  
  <s:hidden name="show" id="show"/>
  <s:hidden name="showReq" id="showReq"/>
  
  <!-- ----These are for  hideen fileds  for  check box- of test data--- -->
  <s:hidden name="hidcontactCheck" id="hidcontactCheck" /><s:hidden name="hidtimeCheck" id="hidtimeCheck" />
 <s:hidden name="hidtestvenueCheck" id="hidtestvenueCheck" /> <s:hidden name="hidrecruitNameCheck" id="hidrecruitNameCheck" />
 <s:hidden name="hidemailCheck" id="hidemailCheck" /><s:hidden name="hidtestStatusCheck" id="hidtestStatusCheck" />
  
  <!-- -------------Interview CheckBox Hidden Filed------------------------- -->
  <s:hidden name="hidintervewDateCheck" id="hidintervewDateCheck" /><s:hidden name="hidintRoundCheck" id="hidintRoundCheck" />
		<s:hidden name="hidIntervewtimeCheck" id="hidIntervewtimeCheck" />
		<s:hidden name="hidIntervenueCheck" id="hidIntervenueCheck" /><s:hidden name="hidInterviewerCheck" id="hidInterviewerCheck" />
		<s:hidden name="hidIntRecruiterCheck" id="hidIntRecruiterCheck" />	<s:hidden name="hidconductCheck" id="hidconductCheck" />
		<s:hidden name="intrvcandCheck" id="intrvcandCheck" />
		<s:hidden name="hidIntrvcandCheck" id="hidIntrvcandCheck" />
		
  
<!-- -------------Interview Itrator Heading Flag Filed Name------------------------- -->					 
				<s:hidden name="intrvCandidateHd" /> <s:hidden name="intRndTypeHd" /> 
				<s:hidden name="intrvDateHd" /> <s:hidden name="intrvTimeHd" />
      <s:hidden name="intrvVenueHd" /> <s:hidden name="intrviewerHd" />
      <s:hidden name="recruiterIntHd" /> <s:hidden name="conductHd" />
      <s:hidden name="itreqsiName"/> <s:hidden name="itconducted"/> <s:hidden name="itnonconducted"/> <s:hidden name="itcanceled"/> 
					 
					 
					
	<table width="100%" border="0" align="right" cellpadding="0" class="formbg"
		cellspacing="0">  
		
	 <tr>
		 <td colspan="3" width="100%">
			<table width="100%" border="0" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="2"><strong
						class="text_head">Scheduled Test/Interview </strong></td>
				</tr>
			</table>
		</td>
		</tr>
		<tr> 
		<td width="78%"> 
		   <s:if test="noData"></s:if><s:else>   
		   <input type="button"  class="back" value="Back" onclick="callBack();">
		   <input type="button"  class="report" value="Export Into Pdf" onclick="callPdfReport();">
		    <input type="button"  class="report" value="Export Into Xls" onclick="callXlsReport();">
		   <input type="button"  class="report" value="Export Into Doc" onclick="callTextReport();">
		  </s:else>
		    <td  width="20%" > <label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll" id="exportAll" checked="checked"  onclick="callChkBox('exportAll');" >   </td>
		</td> 
			
		 </tr>   
		 
          <tr>
          <td colspan="3"> 
           <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="sortable"  >
                 
                    
                     <s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
                       <s:hidden name="hdPageReq" id="hdPageReq" value="%{hdPageReq}" />
                      <s:hidden name="myPage" id="myPage" />
                       <s:hidden name="myPageReq" id="myPageReq" />
                      
              <s:if test="noData"></s:if>
              <s:else>       
					<%try
					{
						int totalPage = (Integer) request.getAttribute("totalPage");
						int pageNo = (Integer) request.getAttribute("pageNo");
						String reqCode = (String) request.getAttribute("reqCode");
						
					%>
                       
						<tr><td><strong class="text_head">Manpower Information :</strong></td>
						
							<td align="right">    <s:if test="noData"> </s:if><s:else> <b>Page:</b>
												
					 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPageM('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPageM('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoFieldReq" id="pageNoFieldReq" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageStatus(event);return numbersOnly()"   maxlength="10" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPageM('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPageM('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
						
						</s:else>
						
						
						</td>
							
							</td>
						</tr>
						<%}
					catch(Exception e)
					{
				    	e.printStackTrace();
					}%> 
					
					
					
					</s:else>
        </table>
      </td> 
  </tr>  
  
  <tr> 
  <td colspan="3">
     <table width="100%" border="0" class="formbg">
     	
				<!-- <tr>
                  		<strong class="text_head">Manpower Information :</strong>
                   		 <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                 </tr>-->
                 <s:if test="noData">
                 
                 <tr>
                 <td align="center"><font color="red">No data to display.</td>
                 
                 </tr>
                 
                 </s:if><s:else>
                 
		         <tr>
			          <td width="12%" colspan="1" nowrap="nowrap"><label  class = "set"  name="reqs.code" id="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> : </td>
			          <td width="30%" colspan="1"> <s:property value="viewReqsName"/>  </td>
			          <td width="12%" colspan="1"><label  class = "set"  name="reqs.date" id="reqs.date1" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label> : </td>
			          <td width="46%" colspan="1"><s:property value="viewReqsDate"/> </td> 
		         </tr> 
		         <tr>
			          <td width="12%" colspan="1"><label  class = "set"  name="position" id="position1" ondblclick="callShowDiv(this);"><%=label.get("position")%></label> : </td>
			          <td width="30%" colspan="1"> <s:property value="position"/> </td>
			          <td width="12%" colspan="1"><label  class = "set"  name="appstatus" id="appstatus1" ondblclick="callShowDiv(this);"><%=label.get("appstatus")%></label> : </td>
			          <td width="46%" colspan="1"><s:property value="apprvalStatus"/> </td> 
		         </tr> 
		         <tr>
			          <td width="12%" colspan="1"><label  class = "set"  name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> : </td>
			          <td width="30%" colspan="1"> <s:property value="div"/> </td>
			          <td width="12%" colspan="1" nowrap="nowrap"><label  class = "set"  name="reqstatus" id="reqstatus1" ondblclick="callShowDiv(this);"><%=label.get("reqstatus")%></label> : </td>
			          <td width="46%" colspan="1"><s:property value="status"/> </td> 
		         </tr>
		         
		        <tr>
			          <td width="12%" colspan="1"><label  class = "set"  name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> : </td>
			          <td width="30%" colspan="1"> <s:property value="brn"/> </td>
			          <td width="12%" colspan="1"><label  class = "set"  name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> : </td>
			          <td width="46%" colspan="1"><s:property value="dept"/> </td> 
		         </tr>
		         
		          <tr>
			          <td width="12%" colspan="1" nowrap="nowrap"><label  class = "set"  name="hiring.mgr" id="hiring.mgr" ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label> : </td>
			          <td width="30%" colspan="1"> <s:property value="hrngMgr"/> </td>
			          <td width="12%" colspan="1" nowrap="nowrap"> <input type="button" class="token" value=" View Scheduled Summary  " onclick="return callSummaryReport();"> </td> 
			          <td width="46%" colspan="1" valign="bottom">
			          
			          
			          
			          
			          
		         </tr>
        	</s:else>
      </table>
        <s:if test="noData"></s:if><s:else>
      <table  width="100%">
      <tr>
    
      
      
      <s:if test="flag">
      <td height="27" class="formtxt" ><strong class="text_head">Interview List :</strong></td>
      </s:if><s:else>
          <td height="27" class="formtxt" ><strong class="text_head">Test List :</strong></td>
      </s:else>
      
        
         
         <%
					int totalPage1 = (Integer) request.getAttribute("totalPage1");
					int pageNos = (Integer) request.getAttribute("PageNo1");
				
					%>
						<td align="right"><s:if test="modeLength"><b>Page:</b>
					
					 <input type="hidden" name="totalPage1"  id="totalPage1" value="<%=totalPage1%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNos%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="10" /> of <%=totalPage1%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage1%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
						
						</s:if> 
						
						
						</td>
      					
      
      </tr>
          
      </table>
     
      
       		
     
						
						<table class="formbg" width="100%">
						<s:if test="flag">
						<tr>
						<td width="5%"  valign="top" class="formth"  nowrap="nowrap" align="center"><label class="set" name="serial.no" id="serial.no"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
						
					 	<s:if test="intrvCandidateHd"></s:if>
						<s:else><td width="15%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="cand.name" id="cand.name"
										ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></td></s:else>
						
						<s:if test="intRndTypeHd"></s:if>				
						<s:else><td width="14%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="rndType" id="rndType"
										ondblclick="callShowDiv(this);"><%=label.get("rndType")%></label></td></s:else>
					
						<s:if test="intrvDateHd"></s:if>					
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="intDate" id="intDate1"
										ondblclick="callShowDiv(this);"><%=label.get("intDate")%></label></td></s:else>
						
						<s:if test="intrvTimeHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="intTime" id="intTime1"
										ondblclick="callShowDiv(this);"><%=label.get("intTime")%></label></td></s:else>
						
						<s:if test="intrvVenueHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="intven" id="intven"
										ondblclick="callShowDiv(this);"><%=label.get("intven")%></label></td></s:else>
						
						<s:if test="recruiterIntHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="intrvr" id="intrvr"
										ondblclick="callShowDiv(this);"><%=label.get("intrvr")%></label></td></s:else>
						
						<s:if test="intrviewerHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="rec.name" id="rec.name"
										ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label></td></s:else>
						<s:if test="conductHd"></s:if>
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="conduct" id="cunduct"
										ondblclick="callShowDiv(this);"><%=label.get("conduct")%></label></td></s:else>
						
						</tr>
					
                 <s:if test="noRecords">
                 
                 <tr>
                 <td width="100%" align="right" colspan="4"><font color="red">No data to display.</font></td>
                 
                 </tr>
                 
                 </s:if>
                 
                 
                
						
						
						<%!int k=0; %>
						<%int i=0; %>
						<%
											int cnt = pageNos * 10 - 10;
											
						%>
						<s:iterator value="intList">
						
					<tr>
					      <s:hidden name="intrvCandidateFlag" /> <s:hidden name="intRndTypeFlag" />
      					   <s:hidden name="intrvDateFlag" /> <s:hidden name="intrvTimeFlag" />
     					   <s:hidden name="intrvVenueFlag" /> <s:hidden name="intrviewerFlag" />
      						<s:hidden name="recruiterIntFlag" /><s:hidden name="conductFlag" />
					
					
							<td width="3%"  align="center" class="sortableTD"><%out.print(++cnt); %></td>
							<s:if test="intrvCandidateFlag"></s:if>
							<s:else><td width="15%" align="left" class="sortableTD"><s:property value="candidateName"/></td></s:else>
	                        
	                        <s:if test="intRndTypeFlag"></s:if>
	                        <s:else><td width="14%"  align="left" class="sortableTD">&nbsp;<s:property value="intRound"/></td></s:else>
	                        
	                        <s:if test="intrvDateFlag"></s:if>
	                       <s:else> <td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="intDate"/></td></s:else>
	                       
	                       <s:if test="intrvTimeFlag"></s:if>
	                       <s:else> <td width="3%"   align="left" class="sortableTD">&nbsp;<s:property value="intTime"/> </td></s:else>
	                        
	                        <s:if test="intrvVenueFlag"></s:if>
	                        <s:else><td width="11%" align="left" class="sortableTD">&nbsp; <s:property value="intVenue"/> </td></s:else>
	                        
	                        
	                       <s:if test="intrviewerFlag"></s:if>
	                       <s:else> <td width="19%" align="left" class="sortableTD">&nbsp; <s:property value="interviewer"/> </td></s:else>
	                       
	                       <s:if test="recruiterIntFlag"></s:if>
	                       <s:else> <td width="19%" align="left" class="sortableTD">&nbsp;<s:property value="recr"/> </td></s:else>
	                        
	                        <s:if test="conductFlag"></s:if>
	                        <s:else><td width="3%" align="left" class="sortableTD">&nbsp;	<s:property value="conduct"/></td></s:else>
	                                  
                    </tr>
					</s:iterator>
					<%i=k; %>
			</s:if><s:else>
						
					
					<tr>
					
					
						<td width="5%" valign="top" class="formth"  nowrap="nowrap" align="center"><label class="set" name="serial.no" id="serial.no1"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
										<s:if test="candCheckHd"></s:if>
										<s:else>
										<td width="15%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="cand.name" id="cand.name1"
										ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></td>		
										</s:else>
								<s:if test="EmailCheckHd"></s:if>
						        <s:else> <td width="15%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="email.id" id="email.id"
										ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label></td></s:else>
						
						<s:if test="contactCheckHd"></s:if>
						<s:else><td width="10%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="contact.no" id="contact.no"
										ondblclick="callShowDiv(this);"><%=label.get("contact.no")%></label></td></s:else>
						
						<s:if test="dateHd" ></s:if>
						<s:else><td width="13%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="test.date" id="test.date"
										ondblclick="callShowDiv(this);"><%=label.get("test.date")%></label></td></s:else>
						<s:if test="testTimeHd" ></s:if>				
						<s:else><td width="7%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="test.time" id="test.time"
										ondblclick="callShowDiv(this);"><%=label.get("test.time")%></label></td></s:else>
						<s:if test="testVenueHd" ></s:if>
						<s:else><td width="10%" valign="top" class="formth"  align="center"><label class="set" name="test.ven" id="test.ven"
										ondblclick="callShowDiv(this);"><%=label.get("test.ven")%></label></td></s:else>
						<s:if test="recruiterHd"></s:if>
						<s:else>
						
						<td width="15%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="rec.name" id="rec.name1"
										ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label></td></s:else>
						<s:if test="testStatusHd"></s:if>
						<s:else>
						
						<td width="20%" valign="top" class="formth"  align="center"><label class="set" name="testStat" id="testStat"
										ondblclick="callShowDiv(this);"><%=label.get("testStat")%></label></td></s:else>
					</tr>
					
					<s:if test="noRecords">
                 
                 <tr>
                 <td  align="right" colspan="4"><font color="red">No data to display.</td>
                 
                 </tr>
                 
                 </s:if>
					
					
					
						<%!int m=0; %>
						<%int t=0; %><%
											int cnt1 = pageNos * 10 - 10;
											
						%>
					<s:iterator value="testList">
						                              
						<tr>
							<s:hidden name="candidateFlag"/><s:hidden name="recruiterFlag"/><s:hidden name="dateFlag"/>
							<s:hidden name="testTimeFlag"/><s:hidden name="testVenueFlag"/>	<s:hidden name="contactFlag"/>
							<s:hidden name="EmailFlag"/><s:hidden name="testStatusFlag"/>
						
						<td width="5%" align="center" class="sortableTD"><%=++cnt1%><%++t;%></td>
						
						<s:if test="candidateFlag">	</s:if>
						       <s:else><td width="15%" align="left" class="sortableTD">&nbsp;
						       <s:property value="testCandName"/></td></s:else>
						
						<s:if test="EmailFlag">	</s:if>
                        <s:else><td width="15%" align="left" class="sortableTD">&nbsp;<s:property value="emailId"/></td></s:else>
                      
                       <s:if test="contactFlag"></s:if>
                        <s:else><td width="10%" align="left" class="sortableTD">&nbsp;<s:property value="contac"/></td></s:else>
                        
                         <s:if test="dateFlag"></s:if>
                        <s:else><td width="13%" align="left" class="sortableTD">&nbsp;<s:property value="testDate"/></td></s:else>
                       
                        <s:if test="testTimeFlag"></s:if>
                        <s:else><td width="7%" align="left" class="sortableTD">&nbsp; <s:property value="testTime"/></td></s:else>
                       
                       <s:if test="testVenueFlag"></s:if>
                       <s:else> <td width="15%" align="left" class="sortableTD">&nbsp; <s:property value="testVenue"/></td></s:else>
                       
                        <s:if test="recruiterFlag"></s:if><s:else>
                        <td width="15%" align="left" class="sortableTD">&nbsp;<s:property value="testRecr"/></td></s:else>
                        
                        <s:if test="testStatusFlag"></s:if><s:else>
                        <td width="20%" align="left" class="sortableTD">&nbsp;<s:property value="testStatus"/></td></s:else>
                        
                                  
                    </tr>
					</s:iterator>
						<%t=m; %>	
						
						
				</s:else>
					<tr>	<td align="right" width="80%"  colspan="9">
   	<s:if test="modeLength"><b>Total Number Of Candidates :<s:property value="candLen"/></b></s:if></td>
   	
   	
   	
   	</tr>
		</table>
					
			</s:else>	
			
	<s:if test="noData"></s:if><table><tr><td align="left" width="75%"><s:else> 
	<input type="button"  class="back" value="Back" onclick="callBack();">
		   <input type="button"  class="report" value="Export Into Pdf" onclick="callPdfReport();">
		    <input type="button"  class="report" value="Export Into Xls" onclick="callXlsReport();">
		   <input type="button"  class="report" value="Export Into Doc" onclick="callTextReport();"> </s:else> 
		   </td>
		   <td  width="15%" > <label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll_bot" id="exportAll_bot" checked="checked" onclick="callChkBox('exportAll_bot');" >   </td>
		 <!--   <td align="right" width="20%" nowrap="nowrap">
   	<s:if test="modeLength"><b>Total Number Of Candidates :<s:property value="candLen"/></b></s:if></td>-->
   	
   	
   	
   	</tr></table>
   	
      
  </td>
  </tr> 
  
    
  
 </table>
 
 </s:form>
			
	
	 <script> 
 
    function callPageM(id,pageImg){  
  
 	 pageNo =document.getElementById('pageNoFieldReq').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoFieldReq').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoFieldReq').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldReq').focus();
			 return false;
		    }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoFieldReq').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoFieldReq').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no any previous page.");
	         document.getElementById('pageNoFieldReq').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no any next page.");
	         document.getElementById('pageNoFieldReq').focus();
	         return false;
	        }  
       }  
       
		if(id=='P'){
		var p=document.getElementById('pageNoFieldReq').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoFieldReq').value;
		id=eval(p)+1;
		} 
		document.getElementById('myPage').value=id;
		document.getElementById('show').value=id;
		document.getElementById('paraFrm').action='TestInterviewReport_callJspView.action';
		document.getElementById('paraFrm').submit(); 
	}
 
  function callPageStatus(id){   
  

			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldReq').value;		
		 	totalPage =document.getElementById('totalPage').value;		
			var actPage = document.getElementById('myPage').value; 
		 	if(pageNo!="0"  & pageNo<totalPage & pageNo!="" ){	
			 document.getElementById('myPage').value=pageNo;
	        } 
	        if(pageNo==actPage){ 
		      document.getElementById('pageNoFieldReq').focus();
		      return false;
	        }
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldReq').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldReq').focus();
		     document.getElementById('pageNoFieldReq').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldReq').focus();
		     document.getElementById('pageNoFieldReq').value=actPage;  
			 return false;
		    }
		   
			document.getElementById('paraFrm').action='TestInterviewReport_callJspView.action';
			document.getElementById('paraFrm').submit();
		}
	} 
	
	function callPdfReport()
	{
	 document.getElementById('paraFrm').target ="_blank"; 
	 document.getElementById('paraFrm').action = "TestInterviewReport_generateReport.action?seltype=Pdf";
     document.getElementById('paraFrm').submit(); 
	}
	
	
	
	function callBack()
	{
	
//	document.getElementById('myPage').value="";
	document.getElementById('paraFrm_backFlag').value="true";   
	document.getElementById('paraFrm').action = "TestInterviewReport_callBack.action";
    document.getElementById('paraFrm').submit(); 
	}
	
	function callTextReport()
	{
	 document.getElementById('paraFrm').target ="_blank"; 
	 document.getElementById('paraFrm').action = "TestInterviewReport_generateReport.action?seltype=Txt";
     document.getElementById('paraFrm').submit(); 
	}
	function callXlsReport()
	{
	 document.getElementById('paraFrm').target ="_blank"; 
	 document.getElementById('paraFrm').action = "TestInterviewReport_generateReport.action?seltype=Xls";
     document.getElementById('paraFrm').submit(); 
	}
	
	 function callPageDisplay(id)
	  { 
	 
		if(id=='P'){
		var p=document.getElementById('myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('myPage').value;
		id=eval(p)+1;
		}
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm').target ="main"; 
		document.getElementById('paraFrm').action='TestInterviewReport_getInterviewData.action';
		document.getElementById('paraFrm').submit();
		
	} 
	
	function callSummaryReport(){
	   document.getElementById('paraFrm').target="wind";
	   var wind = window.open('','wind','width=600,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=150');	 
	   document.getElementById('paraFrm').action="TestInterviewReport_summaryReport.action";
	   document.getElementById('paraFrm').submit();
	   document.getElementById('paraFrm').target="";
 
	
	}
	
	
	
	
	
	function callPage(id,pageImg){  
	
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage1').value;	 
 	/*  var actPage = document.getElementById('myPage').value; 
	 if(pageNo!="0"  & pageNo<totalPage){	
		 document.getElementById('myPage').value=pageNo;
	   }
	       	
       if( pageImg !="F" & pageImg !="L" )
         { */
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     //document.getElementById('pageNoField').value=actPage;
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		    // document.getElementById('pageNoField').value=actPage;
		     document.getElementById('pageNoField').focus();
			 return false;
		   // }
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
	         alert("There is no any previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no any next page.");
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
		document.getElementById('myPage').value=id;
		document.getElementById('show').value=id;
		document.getElementById('paraFrm').action='TestInterviewReport_callJspView.action';
		document.getElementById('paraFrm').submit(); 
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
		 	totalPage =document.getElementById('totalPage1').value;	  
		    var actPage = document.getElementById('myPage').value; 
		 	if(pageNo!="0"  & pageNo<totalPage & pageNo!="" ){	
			 document.getElementById('myPage').value=pageNo;
	        } 
	        if(pageNo==actPage){ 
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
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
		    
		   
			document.getElementById('paraFrm').action='TestInterviewReport_callJspView.action';
			document.getElementById('paraFrm').submit();
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

	 </script>