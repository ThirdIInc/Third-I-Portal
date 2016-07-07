<%@ taglib prefix="s" uri="/struts-tags"%>   
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="CandidateStatusReport" validate="true" id="paraFrm" theme="simple">


	<table width="100%" border="0" align="right" cellpadding="0" class="formbg" cellspacing="0">  
<s:hidden name="myReqPage"/><s:hidden name="myCandPage"/>  <s:hidden name="chkEmail"/><s:hidden name="chkCandScrnStat"/>
<s:hidden name="chkIntRnd"/><s:hidden name="chkOffStat"/><s:hidden name="chkAppntStat"/><s:hidden name="chkConEmp"/>	
<s:hidden name="chkTestRes"/><s:hidden name="itrHdrEmail"/><s:hidden name="exportAllData"/>
<s:hidden name="asc1"/><s:hidden name="sortBy"/><s:hidden name="radio1"/>
<s:hidden name="desc1"/><s:hidden name="firstBy"/><s:hidden name="radio2"/>
<s:hidden name="asce1"/><s:hidden name="secondBy"/><s:hidden name="radio3"/>
<s:hidden name="desce1"/><s:hidden name="radioFlag1"/>
<s:hidden name="ascc1"/><s:hidden name="radioFlag2"/>
<s:hidden name="descc1"/><s:hidden name="radioFlag3"/><s:hidden name="backFlag"/>
<s:hidden name="itrEmailFlag"/><s:hidden name="itrSrnFlag"/><s:hidden name="itrTestFlag"/><s:hidden name="itrConFlag"/>
<s:hidden name="itrInrvRndFlag"/><s:hidden name="itrOffStatFlag"/><s:hidden name="itrAppntStatFlag"/>
<s:hidden name="itrHdrSrn"/><s:hidden name="itrHdrTest"/><s:hidden name="itrHdrInrvRnd"/>
<s:hidden name="itrHdrOffStat"/><s:hidden name="itrHdrAppntStat"/><s:hidden name="itrHdrCon"/>
<s:hidden name="advScrn"/><s:hidden name="advIntRnd"/><s:hidden name="advOffStat"/><s:hidden name="advAppntStat"/>
<s:hidden name="reqname"/><s:hidden name="candidateCode"/><s:hidden name="candidateName"/><s:hidden name="rankName"/><s:hidden name="reqsDateCombo"/><s:hidden name="frmDate"/>
<s:hidden name="toDate"/><s:hidden name="reqsStatus"/><s:hidden name="selectedReqName"/><s:hidden name="selectedReq"/>
<s:hidden name="reqCode"/><s:hidden name="rankId"/><s:hidden name="divID" /><s:hidden name="aID" /><s:hidden name="pageFlag"/><s:hidden name="radioFlag"/>
<s:hidden name="asc"/><s:hidden name="desc"/><s:hidden name="asce"/><s:hidden name="desce"/><s:hidden name="ascc"/><s:hidden name="descc"/>
 <s:hidden name="chkEmail1"/><s:hidden name="chkCandScrnStat1"/>
<s:hidden name="chkIntRnd1"/><s:hidden name="chkOffStat1"/><s:hidden name="chkAppntStat1"/><s:hidden name="chkConEmp1"/>
	 <tr>
		 <td colspan="3" width="100%">
			<table width="100%" border="0" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="2"><strong
						class="text_head">Candidate Status Report </strong></td>
				</tr>
			</table>
		</td>
		</tr>
		<tr> 
		<td width="100%"> 
		   
		   <input type="button"  class="back" value="Back" onclick="callBack();">
		   <input type="button"  class="report" value="Export Into Pdf" onclick="callReport('Pdf');">
		    <input type="button"  class="report" value="Export Into Xls" onclick="callReport('Xls');">
		   <input type="button"  class="report" value="Export Into Doc" onclick="callReport('Txt');">
		   &nbsp;&nbsp;&nbsp;<label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll" id="exportAll"   onclick="callReportAll();" >
		</td> 
			
		 </tr>   
		 
          <tr>
          <td colspan="3"> 
           <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                 
                    
                  
                      
            
					<%try
					{
						int totalPage = (Integer) request.getAttribute("totalPage");
					
						int pageNo = (Integer) request.getAttribute("PageNo");
						//String reqCode = (String) request.getAttribute("reqCode");
						
					%>
                       
						<tr><td><strong class="text_head">Manpower Information :</strong></td>
						
							<td align="right">   <b>Page:</b>
												
					 <input type="hidden" name="totalReqsPage"  id="totalReqsPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoFieldReq" id="pageNoFieldReq" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="4" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
						
						
						
						
						</td>
							
							</td>
						</tr>
						<%}
					catch(Exception e)
					{
				    	e.printStackTrace();
					}%> 
					
					
					
				
        </table>
      </td> 
  </tr>  
  
  <tr> 
  <td colspan="3">
     <table width="100%" border="0" class="formbg">
     	
			         
		         <tr>
			          <td width="15%" colspan="1" nowrap="nowrap"><b><label  class = "set"  name="reqs.code" id="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b> : </td>
			          <td width="10%" colspan="1"></td>
			          <td width="30%" colspan="1"><s:hidden name="viewReqsCode"/> <s:property value="viewReqsName"/>  </td>
			          <td width="45%" colspan="1"></td> 
		         </tr> 
		         
		         <tr>
			          <td width="15%" colspan="1" nowrap="nowrap"><b><label  class = "set"  name="reqs.date" id="reqs.date1" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label></b> : </td>
			          <td width="10%" colspan="1"></td>
			          <td width="30%" colspan="1"><s:property value="viewReqsDate"/></td>
			          <td width="45%" colspan="1"></td> 
		         </tr> 
		         
		         <tr>
			          <td width="15%" colspan="1"><b><label  class = "set"  name="position" id="position1" ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b> : </td>
			          <td width="10%" colspan="1"></td>
			          <td width="30%" colspan="1"> <s:property value="position"/> </td>
			          <td width="45%" colspan="1"></td> 
		         </tr> 
		         
		          <tr>
			          <td width="15%" colspan="1"><b><label  class = "set"  name="apprv" id="apprv" ondblclick="callShowDiv(this);"><%=label.get("apprv")%></label></b> :</td>
			          <td width="10%" colspan="1"></td>
			          <td width="30%" colspan="1"><s:property value="apprvalStatus"/></td>
			          <td width="45%" colspan="1"></td> 
		         </tr> 
		         
		         <tr>
			          <td width="15%" colspan="1"><b><label  class = "set"  name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></b> : </td>
			          <td width="10%" colspan="1"></td>
			          <td width="30%" colspan="1"> <s:property value="div"/> </td>
			          <td width="45%" colspan="1"></td> 
		         </tr>
		         
		          <tr>
			          <td width="12%" colspan="1" nowrap="nowrap"><b><label  class = "set"  name="reqs.stat" id="reqs.stat" ondblclick="callShowDiv(this);"><%=label.get("reqs.stat")%></label></b> : </td>
			          <td width="10%" colspan="1"></td>
			          <td width="30%" colspan="1"><s:property value="status"/></td>
			          <td width="45%" colspan="1"></td> 
		         </tr>
	  
	       	
      </table>
  </td></tr>
  <s:if test="noData">
  <tr>
  <td colspan="3" width="100%">
  
  
  <%try
				
  
  {
						int totalPage1 = (Integer) request.getAttribute("totalPage1");
					
						int pageNo1 = (Integer) request.getAttribute("PageNo1");
						//String reqCode = (String) request.getAttribute("reqCode");
						
					%>
  			<table width="100%"> 
  			<tr>
  			<td abbr="left" width="25%"><strong class="forminnerhead">
  										Candidate List:
  			
  									</strong></td>
  			  <td width="75%" align="right"> <b>Page:</b>
												
					 <input type="hidden" name="totalCandPage"  id="totalCandPage" value="<%=totalPage1%>">
						 	<a href="#" onclick="callPageCand('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPageCand('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoFieldCand" id="pageNoFieldCand" theme="simple" size="3"  value="<%= pageNo1%>"  onkeypress="callPageTextCand(event);return numbersOnly()"   maxlength="4" /> of <%=totalPage1%>
						 		 		<a href="#" onclick="callPageCand('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPageCand('<%=totalPage1%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
  			</td>
  			</tr>
  			
  			
  			</table>
  
  
  </td>
  
  </tr>
  
  
  <tr>
  <td width="100%" colspan="3">
  
  		<table width="100%" class="formbg">
  				<tr>
  					<td width="3%" class="formth" nowrap="nowrap"><b><label  class = "set"  name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
  					<td width="12%" class="formth"><b><label  class = "set"  name="candhead" id="candhead" ondblclick="callShowDiv(this);"><%=label.get("candhead")%></label></b></td>
  					
  					<s:if test="itrHdrEmail">
  					<td width="12%" class="formth"><b><label  class = "set"  name="email.id" id="email.id" ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label></b></td>
  					</s:if>
  					
  					<s:if test="itrHdrSrn">
  					<td width="13%" class="formth"><b><label  class = "set"  name="candscreeningstatus" id="candscreeningstatus" ondblclick="callShowDiv(this);"><%=label.get("candscreeningstatus")%></label></b></td>
  					</s:if>
  					
  					<s:if test="itrHdrTest">
  					<td width="8%" class="formth"><b><label  class = "set"  name="testresult" id="testresult" ondblclick="callShowDiv(this);"><%=label.get("testresult")%></label></b></td>
  					</s:if>
  					
  					<s:if test="itrHdrInrvRnd">
  					<td width="10%" class="formth"><b><label  class = "set"  name="intvround" id="intvround" ondblclick="callShowDiv(this);"><%=label.get("intvround")%></label></b></td>
  					</s:if>
  					
  					<s:if test="itrHdrOffStat">
  					<td width="15%" class="formth"><b><label  class = "set"  name="offer.status" id="offer.status" ondblclick="callShowDiv(this);"><%=label.get("offer.status")%></label></b></td>
  					</s:if>
  					
  					<s:if test="itrHdrAppntStat">
  					<td width="15%" class="formth"><b><label  class = "set"  name="appoint.status" id="appoint.status" ondblclick="callShowDiv(this);"><%=label.get("appoint.status")%></label></b></td>
  					</s:if>
  					<s:if test="itrHdrCon">
  					<td width="12%" class="formth"><b><label  class = "set"  name="convertemp" id="convertemp" ondblclick="callShowDiv(this);"><%=label.get("convertemp")%></label></b></td>
  					</s:if>
  					
  				
  				</tr>
  					<%
									int count = 0;
									int cn= pageNo1*20-20;
									%>
									<%!int d = 0;%>
									<%
									int i = 0,counter=0;
									%>
  				<s:iterator value="candList" >
  					<tr>
  					<td width="3%" align="center" class="sortableTD"><%++i;%><%=++cn%></td>
  					
  					<td width="12%" align="left" class="sortableTD"><s:property value="cand"/></td>
  					<s:if test="itrEmailFlag">
  					<td width="12%" align="left" class="sortableTD">&nbsp;<s:property value="email"/></td>
  					</s:if>
  					
  					<s:if test="itrSrnFlag">
  					<td width="13%" align="left" class="sortableTD">&nbsp;<s:property value="scrnStat"/></td>
  					</s:if>
  					
  					<s:if test="itrTestFlag">
  					<td width="8%" class="sortableTD" >&nbsp;<s:property value="testRes"/></td>
  					</s:if>
  					
  					<s:if test="itrInrvRndFlag">
  					<td width="10%" class="sortableTD" >&nbsp;<s:property value="intrvRnd"/></td>
  					</s:if>
  					
  					<s:if test="itrOffStatFlag">
  					<td width="15%" class="sortableTD" >&nbsp;<s:property value="offStat"/></td>
  					</s:if>
  					
  					<s:if test="itrAppntStatFlag">
  					<td width="15%" class="sortableTD" >&nbsp;<s:property value="appntStat"/></td>
  					</s:if>
  					
  					
  					<s:if test="itrConFlag">
  					<td width="12%" class="sortableTD" >&nbsp;<s:property value="convert"/></td>
  					</s:if>
  					
  					</tr>
  				 				
  				</s:iterator>
  				
  				<%
				 d = i;
				%>
  		
  		
  		</table>
	  </td>
  </tr>
  
  <%
					}catch(Exception e){
						e.printStackTrace();
					}
  
  %>
  
  </s:if><s:else>
  
  					 <tr>
  <td width="100%" colspan="3" >
  
  		<table width="100%" class="formbg">
  				<tr>
  					<td width="3%" class="formth" nowrap="nowrap"><b><label  class = "set"  name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
  					<td width="12%" class="formth"><b><label  class = "set"  name="candhead" id="candhead" ondblclick="callShowDiv(this);"><%=label.get("candhead")%></label></b></td>
  					
  					<s:if test="itrHdrEmail">
  					<td width="12%" class="formth"><b><label  class = "set"  name="email.id" id="email.id" ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label></b></td>
  					</s:if>
  					
  					<s:if test="itrHdrSrn">
  					<td width="13%" class="formth"><b><label  class = "set"  name="candscreeningstatus" id="candscreeningstatus" ondblclick="callShowDiv(this);"><%=label.get("candscreeningstatus")%></label></b></td>
  					</s:if>
  					
  					<s:if test="itrHdrTest">
  					<td width="8%" class="formth"><b><label  class = "set"  name="testresult" id="testresult" ondblclick="callShowDiv(this);"><%=label.get("testresult")%></label></b></td>
  					</s:if>
  					
  					<s:if test="itrHdrInrvRnd">
  					<td width="10%" class="formth"><b><label  class = "set"  name="intvround" id="intvround" ondblclick="callShowDiv(this);"><%=label.get("intvround")%></label></b></td>
  					</s:if>
  					
  					<s:if test="itrHdrOffStat">
  					<td width="15%" class="formth"><b><label  class = "set"  name="offer.status" id="offer.status" ondblclick="callShowDiv(this);"><%=label.get("offer.status")%></label></b></td>
  					</s:if>
  					
  					<s:if test="itrHdrAppntStat">
  					<td width="15%" class="formth"><b><label  class = "set"  name="appoint.status" id="appoint.status" ondblclick="callShowDiv(this);"><%=label.get("appoint.status")%></label></b></td>
  					</s:if>
  					<s:if test="itrHdrCon">
  					<td width="12%" class="formth"><b><label  class = "set"  name="convertemp" id="convertemp" ondblclick="callShowDiv(this);"><%=label.get("convertemp")%></label></b></td>
  					</s:if>
  				</tr>
  				<tr>
  				
  				<td width="100%" colspan="10" align="center" >
  				<font color="red">There is no data to display.</font>
  				</td>
  				
  				</tr>
  </table>
  </td></tr>
  </s:else>
  <tr><td>    
			
	<table><tr><td align="left" width="100%">
	<input type="button"  class="back" value="Back" onclick="callBack();">
		   <input type="button"  class="report" value="Export Into Pdf" onclick="callReport('Pdf');">
		    <input type="button"  class="report" value="Export Into Xls" onclick="callReport('Xls');">
		   <input type="button"  class="report" value="Export Into Doc" onclick="callReport('Txt');"> 
		  &nbsp;&nbsp;&nbsp;<label class="set" name="export.all" id="export.all" ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label> : <input type="checkbox" name="exportAll1" id="exportAll1"  onclick="callReportAll1();" >   </td>
		
   	
   	
   	
   	</tr></table>
   	
      
  </td></tr> 
  
    
  
 </table>
 
 </s:form>
<script type="text/javascript">

function callBack(){
			
			document.getElementById('paraFrm_backFlag').value="true";
			document.getElementById('paraFrm').action='CandidateStatusReport_input.action';
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

function callPageText(id){  
	  
		     document.getElementById('paraFrm_myCandPage').value="";
		    var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldReq').value;	
			
		 	totalPage =document.getElementById('totalReqsPage').value;	
		 	var actPage = document.getElementById('paraFrm_myReqPage').value;   
	     
		 	 
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
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldReq').focus();
		      return false;
	        }
	       
	         document.getElementById('paraFrm_myReqPage').value=pageNo;
		 
			document.getElementById('paraFrm').action='CandidateStatusReport_showReqsnList.action';
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	
	
	
 function callPage(id,pageImg){  
 
  document.getElementById('paraFrm_myCandPage').value="";
 	 pageNo=document.getElementById('pageNoFieldReq').value;	
 	 totalPage=document.getElementById('totalReqsPage').value;	 
 	 
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
	         alert("There is no previous page.");
	         document.getElementById('pageNoFieldReq').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
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
		
		document.getElementById('paraFrm_myReqPage').value=id;
		
		document.getElementById('paraFrm').action='CandidateStatusReport_showReqsnList.action';
		document.getElementById('paraFrm').submit(); 
	}	
	
	
	
	
	
	
	function callPageCand(id,pageImg){  
	
 	 pageNo=document.getElementById('pageNoFieldCand').value;	
 	 totalPage=document.getElementById('totalCandPage').value;	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoFieldCand').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoFieldCand').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldCand').focus();
			 return false;
		    }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoFieldCand').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoFieldCand').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoFieldCand').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoFieldCand').focus();
	         return false;
	        }  
       }  
       
       
     
		if(id=='P'){
		var p=document.getElementById('pageNoFieldCand').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('pageNoFieldCand').value;
		id=eval(p)+1;
		} 
		document.getElementById('paraFrm_myCandPage').value=id;
		document.getElementById('paraFrm').action='CandidateStatusReport_showCandidateList.action';
		document.getElementById('paraFrm').submit(); 
	}		
	
	
	function callPageTextCand(id){  
	   
		    
		    var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoFieldCand').value;	 
		 	totalPage =document.getElementById('totalCandPage').value;	
		 	var actPage = document.getElementById('paraFrm_myCandPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoFieldCand').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoFieldCand').focus();
		     document.getElementById('pageNoFieldCand').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoFieldCand').focus();
		     document.getElementById('pageNoFieldCand').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoFieldCand').focus();
		      return false;
	        }
	        
	         document.getElementById('paraFrm_myCandPage').value=pageNo;
		   
			document.getElementById('paraFrm').action='CandidateStatusReport_showCandidateList.action';
			document.getElementById('paraFrm').submit();
		}
		
	}	

callExport();
function callExport(){
document.getElementById('exportAll1').checked=true;
document.getElementById('exportAll').checked=true;
document.getElementById('paraFrm_exportAllData').value="Y";
}	

function callReport(type){
document.getElementById('paraFrm').action='CandidateStatusReport_generateReport.action?reportType='+type;
document.getElementById('paraFrm').submit();

}

		
</script>			
	
	 