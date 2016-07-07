<%@ taglib prefix="s" uri="/struts-tags"%>   
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="InterviewAnalysis" validate="true" id="paraFrm"
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
	<s:hidden name="intStatusReport" />
	<s:hidden name="intStatus"/>
	<s:hidden name="exportAllData"/>
	<s:hidden name='backFlag'/>
	<s:hidden name="aId"/><s:hidden name="divId"/>
	<s:hidden name="evalCode"/><s:hidden name="evalScore"/>
	
	
  
  <!-- -------------Interview CheckBox Hidden Filed------------------------- -->
                                    <s:hidden name="intRoundCheck" id="intRoundCheck" />
									<s:hidden name="statusCheck" id="statusCheck" />
									<s:hidden name="ratingCheck" id="ratingCheck" />
									<s:hidden name="scoreCheck" id="scoreCheck" />
									<s:hidden name="interviewerCheck" id="interviewerCheck" />
						    		<s:hidden name="candidateCheck" id="candidateCheck" />
						    		<s:hidden name="qualificationCheck" id="qualificationCheck" />
						    		<s:hidden name="technicalCheck" id="technicalCheck" />
						    		<s:hidden name="communicationCheck" id="communicationCheck" />
						    		<s:hidden name="mangmentCheck" id="mangmentCheck" />
						    		<s:hidden name="personalityCheck" id="personalityCheck" />
						    		<s:hidden name="relevantCheck" id="relevantCheck" />
						    		<s:hidden name="makeOffferCheck" id="makeOffferCheck" />
						    		<s:hidden name="nextRoundCheck" id="nextRoundCheck" />
						    		<s:hidden name="learningCheck" id="learningCheck" />
						    		<s:hidden name="suitablityCheck" id="suitablityCheck" />
						    		<s:hidden name="percentageCheck" id="percentageCheck" />
						    		
						    		
						    		
									<s:hidden name="quailificationFlag" id="quailificationFlag"/>
			      						<s:hidden name="technicalFlag" id="technicalFlag" />
			      						<s:hidden name="communicationFlag" id="communicationFlag"/>
			      						<s:hidden name="managmentFlag" id="managmentFlag"/>
								<s:hidden name="personalityFlag" id="personalityFlag" /><s:hidden name="learningFlag" id="learningFlag" />
								<s:hidden name="relevantFlag" id="relevantFlag" /><s:hidden name="sutablityFlag" id="sutablityFlag" />
  
<!-- -------------Interview Itrator Heading Flag Filed Name------------------------- -->	
			 
				<s:hidden name="statusHd" /> <s:hidden name="scoreHd" /> 
				<s:hidden name="ratingHd" /> <s:hidden name="intRndTypeHd" />
      <s:hidden name="intrviewerHd" /> <s:hidden name="CandidateHd" />
      <s:hidden name=" qualificationHd" id="qualificationHd" />  <s:hidden name="technicalHd" id="technicalHd"/>
      	 <s:hidden name="communicationHd"   id="communicationHd"/>	 <s:hidden name="managmentHd" id="managmentHd" />
      	  <s:hidden name="personalityHd" id="personalityHd " />
      	   <s:hidden name="learningHd" id="learningHd "/>
      	    <s:hidden name="relevantHd" id="relevantHd"/>
      	     <s:hidden name="makeOfferHd" id="makeOfferHd"/> <s:hidden name="nextRoundHd" id="nextRoundHd"/>
     
					
	<table width="100%" border="0"  cellpadding="0" class="formbg"
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
						class="text_head">Interview Analysis</strong></td>
				</tr>
		</table></td></tr>
			
		<tr> <td width="100%">
		<table width="100%">
		<td width="60%"> 
		
		
		
		
	<s:if test="noData"></s:if><table border="0"  width="100%"><tr><td align="left" width="90%">
	<input type="button"  class="back" value="Back" onclick="callBack();"> 
	<s:else> 		    
		   <input type="button"  class="report" value="Export Into Pdf" onclick="callPdfReport();">
		    <input type="button"  class="report" value="Export Into Xls" onclick="callXlsReport();">
		    <input type="button"  class="report" value="Export Into Text" onclick="callTextReport();"></s:else>
		   
		</td>  <td  width="10%" > <label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll" id="exportAll" checked="checked"  onclick="callChkBox('exportAll');" >   </td>
		</td> 
			
		 
		 </table>
		</td>
		</tr>
     <tr><td colspan="3">
      <table  width="100%" border="0" cellpadding="0" cellspacing="0" >
      <tr>     
      <td height="27" class="formtxt" ><strong class="text_head">Interview Report Details :</strong></td>
    
          <s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
                      <s:hidden name="myPage" id="myPage" />
     
      
        
         
         <%
					int totalPage1 = (Integer) request.getAttribute("totalPage1");
					int pageNos = (Integer) request.getAttribute("PageNo1");
				
					%>
						
						 
						 <td align="right"><b>Page:</b> 
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
						</td>
						
							
							
							
							
							
      					
      
      </tr>
          
      </table></td></tr>
				<tr>
				<td colspan="1" width="100%">
						<table class="formbg" width="100%">
						
						<tr>
						<td width="5%"  valign="top" class="formth"  nowrap="nowrap" align="center"><label class="set" name="serial.no" id="serial.no"
										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
						
					 	<s:if test="CandidateHd"></s:if>
						<s:else><td width="15%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="cand.name" id="cand.name"
										ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></td></s:else>
						
						<s:if test="intrviewerHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="intrvName" id="intrvName"
										ondblclick="callShowDiv(this);"><%=label.get("intrvName")%></label></td></s:else>
				
						<s:if test="intRndTypeHd"></s:if>				
						<s:else><td width="14%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="intvRound" id="intvRound"
										ondblclick="callShowDiv(this);"><%=label.get("intvRound")%></label></td></s:else>
					<!-- new added -->
						
						<s:if test="qualificationHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="qualification" id="qualification"
										ondblclick="callShowDiv(this);"><%=label.get("qualification")%></label></td></s:else>
										
										
							<s:if test="technicalHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="technical" id="technical"
										ondblclick="callShowDiv(this);"><%=label.get("technical")%></label></td></s:else>			
										
							<s:if test="communicationHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="communication" id="communication"
										ondblclick="callShowDiv(this);"><%=label.get("communication")%></label></td></s:else>				
										
					  <s:if test="managmentHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="managment" id="managment"
										ondblclick="callShowDiv(this);"><%=label.get("managment")%></label></td></s:else>				
		
					<s:if test="personalityHd"></s:if>	
					<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="personality" id="personality"
										ondblclick="callShowDiv(this);"><%=label.get("prsonality")%></label></td></s:else>				
											
						<s:if test="learningHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="learning" id="learning"
										ondblclick="callShowDiv(this);"><%=label.get("learning")%></label></td></s:else>
						<s:if test="relevantHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="relevant" id="relevant"
										ondblclick="callShowDiv(this);"><%=label.get("relevant")%></label></td></s:else>				
									<s:if test="suitablityHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="suitablity" id="suitablity"
										ondblclick="callShowDiv(this);"><%=label.get("suitablity")%></label></td></s:else>				
							
							<s:if test="makeOfferHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="makeOff" id="makeOff"
										ondblclick="callShowDiv(this);"><%=label.get("makeOff")%></label></td></s:else>				
													
								<s:if test="nextRoundHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="forwardNext" id="forwardNext"
										ondblclick="callShowDiv(this);"><%=label.get("forwardNext")%></label></td></s:else>				
													
								<!-- ---		-->
						
						
						
						<s:if test="scoreHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="score.Parameter" id="score.Parameter"
										ondblclick="callShowDiv(this);"><%=label.get("score.Parameter")%></label></td></s:else>
						<s:if test="percentageHd"></s:if>	
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="percentage" id="percentage"
										ondblclick="callShowDiv(this);"><%=label.get("percentage")%></label></td></s:else>
						
						
						<s:if test="statusHd"></s:if>
						<s:else><td width="11%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="status" id="status1"
										ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td></s:else>
										
						
						
						
						</tr>
						<s:if test="noData">
									<tr>
										<td width="100%" colspan="7" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
						<%!int k=0; %>
						<%int i=0; %>
						<%
											int cnt = pageNos * 20 - 20;
											
						%>
						<s:iterator value="interviewList">
						
					<tr>
					      <s:hidden name="CandidateFlag" /> <s:hidden name="intRndTypeFlag" />
      					   <s:hidden name="intrviewerFlag" /> <s:hidden name="scoreFlag" />
     					   <s:hidden name="ratingFlag" /> <s:hidden name="quailificationFlag" />
      						<s:hidden name="statusFlag" /><s:hidden name="technicalFlag" />
      						<s:hidden name="communicationFlag" /><s:hidden name="managmentFlag" />
					<s:hidden name="personalityFlag" /><s:hidden name="learningFlag" />
					<s:hidden name="relevantFlag" /><s:hidden name="sutablityFlag" />
					<s:hidden name="makeOfferFlag" /><s:hidden name="nextRoundFlag" />
					<s:hidden name="percentageFlag" /><s:hidden name="sutablityFlag" />
					
							<td width="3%"  align="center" class="sortableTD"><%out.print(++cnt); %></td>
							<s:if test="CandidateFlag"></s:if>
							<s:else><td width="15%" align="left" class="sortableTD"><s:property value="candidateName"/></td></s:else>
	                        
	                        <s:if test="intrviewerFlag"></s:if>
	                       <s:else> <td width="19%" align="left" class="sortableTD">&nbsp; <s:property value="interviewer"/> </td></s:else>
	                        
	                        <s:if test="intRndTypeFlag"></s:if>
	                        <s:else><td width="14%"  align="center" class="sortableTD">&nbsp;<s:property value="intRound"/></td></s:else>
	                        
	                      <!--   new added-->
	                      
	                      <s:if test="quailificationFlag"></s:if>
	                       <s:else> <td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="qualification"/></td></s:else>
	                       
	                       <s:if test="technicalFlag"></s:if>
	                       <s:else> <td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="technical"/></td></s:else>
	                       
	                      
	                       <s:if test="communicationFlag"></s:if>
	                       <s:else> <td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="communication"/></td></s:else>
	                      
	                        <s:if test="managmentFlag"></s:if>
	                       <s:else> <td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="managment"/></td></s:else>
	                       
	                   <s:if test="personalityFlag"></s:if>
	                       <s:else> <td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="personality"/></td></s:else>
	                          
	                       <s:if test="learningFlag"></s:if>
	                       <s:else> <td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="learning"/></td></s:else>
	                          
	                       <s:if test="relevantFlag"></s:if>
	                       <s:else> <td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="relevant"/></td></s:else>
	                           
	                       <s:if test="sutablityFlag"></s:if>
	                       <s:else> <td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="suitable"/></td></s:else>
	                           
	                           
	                           <s:if test="makeOfferFlag"></s:if>
	                       <s:else> <td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="makeOffer"/></td></s:else>
	                       
	                        <s:if test="nextRoundFlag"></s:if>
	                       <s:else> <td width="11%"  align="left" class="sortableTD">&nbsp;<s:property value="nextRound"/></td></s:else>
	                       <!--              -->
	                       
	                       
	                       
	                         <s:if test="scoreFlag"></s:if>
	                       <s:else> <td width="3%"   align="center" class="sortableTD">&nbsp;<s:property value="score"/> </td></s:else>
	                        
	                     
<s:if test="percentageFlag"></s:if>
	                       <s:else> <td width="3%"   align="center" class="sortableTD">&nbsp;<s:property value="percentage"/> </td></s:else>
	                        




     
	                        <s:if test="statusFlag"></s:if>
	                        <s:else><td width="11%" align="left" class="sortableTD">&nbsp; <s:property value="status"/> </td></s:else>
	                        
	                        
	                       
	                       
	                      
                    </tr>
					</s:iterator>
					<%i=k; %>
			<tr>
			
			<td  width="100%" align="right">
				<s:if test="modeLength"><td colspan="14" width="80%" align="right"></td>	<td width="20%" colspan="6" ><b>Total Number Of Records :<s:property value="candidateLength"/></b></td></s:if>		
		</tr>
		</table>
		
					
			</td>
  </tr> 
  <tr><td  width="100%">
	<s:if test="noData"></s:if><table border="0"  width="100%"><tr><td align="left" width="90%">
	<input type="button"  class="back" value="Back" onclick="callBack();"> 
	<s:else> 
		   <input type="button"  class="report" value="Export Into Pdf" onclick="callPdfReport();">
		    <input type="button"  class="report" value="Export Into Xls" onclick="callXlsReport();">
		     <input type="button"  class="report" value="Export Into Doc" onclick="callTextReport();"> </s:else> 
		   
		   </td>
		   <td  width="15%" > <label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll_bot" id="exportAll_bot" checked="checked" onclick="callChkBox('exportAll_bot');" >   </td>
  
   	</tr></table></td></tr>
   	
      
  
  
    
  
 </table></td></tr></table>
 
 </s:form>
			
	
	 <script> 
	 
	 function callPageText(id){ 
	
		var code;
			if (id.keyCode) 
				code = id.keyCode;
			else
				if (id.which) 
					code = id.which;
					
			if(code==13) 
			{
			pageNo =document.getElementById('pageNoField').value;		
		 	totalPage =document.getElementById('totalPage1').value;		
			document.getElementById('myPage').value=pageNo;
		
	      
	        if(pageNo==""){
	        alert("Please Enter Page Number.");
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
		     alert("Page number should not be greater than totalPage number.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    document.getElementById('paraFrm').action = "InterviewAnalysis_callJspView.action";
		
			document.getElementById('paraFrm').submit();
		}
		
	}
	function callPage(id,pageImg){  
	
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage1').value;	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
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
		     alert("Page number should not be greater than total page number.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no  previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no  next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
		if(id=='P'){
		var p=document.getElementById('myPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('myPage').value;
		id=eval(p)+1;
		} 
		document.getElementById('myPage').value=id;
		 document.getElementById('paraFrm').action = "InterviewAnalysis_callJspView.action";
		
		document.getElementById('paraFrm').submit(); 
	}	
	 
	
	
	function callPdfReport()
	{
	 document.getElementById('paraFrm').target ="_blank"; 
	 document.getElementById('paraFrm').action = "InterviewAnalysis_generateReport.action?seltype=Pdf";
     document.getElementById('paraFrm').submit(); 
	}
	
	function callBack()
	{
	
	
	document.getElementById('paraFrm_backFlag').value="true";   
	document.getElementById('paraFrm').target =""; 
	 document.getElementById('paraFrm').action = "InterviewAnalysis_input.action";
     document.getElementById('paraFrm').submit(); 
	}
	
	function callTextReport()
	{
	 document.getElementById('paraFrm').target ="_blank"; 
	 document.getElementById('paraFrm').action = "InterviewAnalysis_generateReport.action?seltype=Txt";
     document.getElementById('paraFrm').submit(); 
	}
	function callXlsReport()
	{
	 document.getElementById('paraFrm').target ="_blank"; 
	 document.getElementById('paraFrm').action = "InterviewAnalysis_generateReport.action?seltype=Xls";
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
		document.getElementById('paraFrm').action='InterviewAnalysis_getInterviewData.action';
		document.getElementById('paraFrm').submit();
		
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