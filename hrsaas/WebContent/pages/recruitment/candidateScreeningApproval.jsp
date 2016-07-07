 <%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="CandidateScreeningApproval" id="paraFrm" validate="true" theme="simple" target="main">
	<s:hidden name="year"/>

	<s:hidden name="saveCandFlag"/>
	<s:hidden name="myPage"/>
   <s:hidden name="hiddenStatusPage"/> 
	<s:hidden name="show"/>
	<s:hidden name="modeLength"/>
	<s:hidden name="filterFlag"/> 
	<s:hidden name="totalRecords"/> 
			   
    <s:hidden name="requisitionId" />
     <s:hidden name="positionId" />
     <s:hidden name="candCode1" />
     <s:hidden name="hrManagerId" />
     <s:hidden name="hiddengender"/>
     <s:hidden name="viewFilterFlag" />
     <s:hidden name="appliedFilterFlag" />
     <s:hidden name="viewEditFlag" />
     
     
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0"  class="formbg" >
	<tr>
			<td colspan="5" width="100%">
				 <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Candidate Screening Approval</strong></td>
						<td width="3%" valign="top" class="otxt">  <div align="right"><img	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>  </td>
					</tr>
				</table>
			</td>
		</tr> 
		<tr>
			<td width="100%" colspan="5" >
			<table width="100%" border="0" cellpadding="0" cellspacing="0"	class="formbg">
				 <tr>
					<td width="100%"  class="txt">
					<table width="100%">
					   <tr>
					     <td width="70%" >
						      <s:if test="viewFilterFlag"> <strong class="text_head">Applied filters.</strong>  </s:if> 
						 <s:else>
						   <strong class="text_head"><label  class = "set" name="searchApply.filter" id="searchApply.filter" ondblclick="callShowDiv(this);"><%=label.get("searchApply.filter")%></strong>
						</s:else>
					     </td>
					     	<td width="30%" class="txt" align="right">
							 <div id="showFilterId">
							   <input type="button" value="Show Filter" name="Show Filter" onclick="showFilter();">
							  </div>
							  <div id="hideFilterId">
							     <input type="button" value="Hide Filter" name="Hide" onclick="hideFilter();">
							   </div> 
						</td>
					   </tr>
					</table>
					</td> 
				</tr>
 <s:if test="viewFilterFlag">
				<s:hidden name="candGender"  />  <s:hidden name="requisitionName" />  <s:hidden name="positionName" /> 
		    	<s:hidden name="candidateName1"  />  <s:hidden name="year1"  />  <s:hidden name="month" />    
		    	<s:hidden name="managerName"  />    
		    	
				<% String [] filterArr = (String [])request.getAttribute("filterArr"); 
				if(filterArr!=null && filterArr.length>0)  {
				 int i =0,count =0;
				    if(filterArr.length%2==0)
				    {
				    	i=filterArr.length/2;
				    }else{
				    	i=(filterArr.length/2)+1;
				    }
				%>
					<tr>
	                 <td> 
	                 	<table width="100%" border="0" cellpadding="2"  cellspacing="2"> 
	                    <% for(int m=0;m <i;m++) { %>
	                       <tr>
	                        <%
	                         if(count < filterArr.length )
	                         { %>
		                       <td width="50%"> <%=filterArr[count] %></td>
		                       <% count++;  } %>
		                       
		                        <%
	                         if(count < filterArr.length )
	                         { %>
		                       <td width="50%" > <%=filterArr[count] %></td>
		                       <% count++;  } %> 
	                       </tr> 
	                       <%} %>
				       </table> 
				    </td>
			   </tr> 
			<%}%>
					<tr>
							<td width="100%" height="22" colspan="4" align="center">&nbsp;
							 <s:submit cssClass="reset" action="CandidateScreeningApproval_clear" theme="simple" value="Clear Filter " />  &nbsp;
							 <s:submit cssClass="token" action="CandidateScreeningApproval_search" theme="simple" value="Edit Filter " onclick="return callEditFilter();" />
							 
								</td>
							
						</tr>
		 </s:if>
 
  <s:else>
				<tr id="filterTR">

						<td>
							<table width="100%" border="0" cellpadding="2"  cellspacing="2"><!--Table 1-->	
								<tr>
									<td width="17%" colspan="1">
										<label  class = "set" name="reqs.code" id="reqs.code1" 
							            	ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%> :</td>
									<td width="16%"><s:textfield name="requisitionName" size="25" readonly="true" /> </td>
									
									<td width="12%" align="left">
									<img src="../pages/images/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'CandidateScreeningApproval_f9Requisition.action');"></td>
																
								
									<td width="15%"><label  class = "set" name="position" id="position1" 
			            	       	ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :</td>
									<td width="15%"><s:textfield name="positionName" size="25"
										readonly="true" /></td>
									<td width="15%">
											<img src="../pages/images/search2.gif"
											class="iconImage" height="16" align="absmiddle" width="16"
											onclick="javascript:callsF9(500,325,'CandidateScreeningApproval_f9Position.action');">
									</td> 
								</tr>


				 <tr>	
						         <td width="17%" >
						            <label  class = "set" name="candidatName" id="candidatName" 
						            	ondblclick="callShowDiv(this);"><%=label.get("candidatName")%></label> : </td>
						         <td width="16%" >       	<s:textfield name="candidateName1" size="25" maxlength="50"  readonly="true" onkeypress="return charactersOnly();"/>     	</td> 
								 <td width="12%" align="left">
										<span id="candName"><img class="iconImage" src="../pages/images/recruitment/search2.gif" width="16"
												height="16" onclick="javascript:callsF9(500,325,'CandidateScreeningApproval_f9CandidateAction.action');"/>
										</span>
								 </td>
						         <td width="15%" >
						            <label  class = "set" name="candExp" id="candExp" 
						            	ondblclick="callShowDiv(this);"><%=label.get("candExp")%></label> : </td>
						         <td width="30%" colspan="2">
						         	<s:textfield name="year1" size="2" maxlength="2" onkeypress="return numbersOnly();"/>Years
						         	<s:textfield name="month" size="2" maxlength="2" onkeypress="return numbersOnly();"/>Months</td>
						    	</tr>
						    	
						    				 <tr>
									
									<td width="17%"><label  class = "set" name="rec.name" id="rec.name" 
			            		ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label> :</td>
									<td width="16%"><s:textfield name="managerName" size="25" 	readonly="true" /></td>
										
								 <td width="12%" align="left">
										<img src="../pages/images/search2.gif" 	class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'CandidateScreeningApproval_f9Recruiter.action');"> 
								 </td>
									
										        						        
						         <td width="15%">
						            <label  class = "set" name="gender" id="gender" 
						            	ondblclick="callShowDiv(this);"><%=label.get("gender")%></label> : </td>
						         <td width="30%" colspan="2" >
						         		<s:select name="candGender" cssStyle="width:80"  
					            			list="#{'':'---Select---', 'M':'Male', 'F':'Female','O':'Others'}"/></td>
						      </tr>
				 <tr>	
						         <td width="100%" align="center" colspan="6">
						         	<s:submit cssClass="token" value="  Apply Filter  "  onclick=" return appliedFilterFun();" action="CandidateScreeningApproval_search" />&nbsp;
									 <input type="button" class="reset" theme="simple" value="  Reset  " onclick="callReset();" /> 
						         </td>
						      </tr>
			</table>
			</td>
		</tr>
		</s:else>
</table></td></tr>
			
		<tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
                <tr>
                  <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
                     <tr>
                        <td height="27" class="formtxt"><a href="#" onclick="showFilterLink('O')">
                        	Open List</a> | <a href="#" onclick="showFilterLink('R')">  
                        	Rejected List</a> | <a href="#" onclick="showFilterLink('T')"> Short List for Test</a>
                      |  <a href="#" onclick="showFilterLink('I')"> Short List for Interview</a>| <a href="#" onclick="showFilterLink('B')">   Short List for Test/Interview</a>	
                       <s:hidden name="status"/></td>
                      </tr>
                  </table></td>
                </tr>
              </table></td>
            </tr>
            <tr>
              <td width="78%">
	              <s:if test="apprFlag"></s:if>
	              <s:else>
		              <!--<img src="../pages/images/recruitment/space.gif" width="5" height="4" />
		              
		              --><br />
		                  <s:if test="saveCandFlag">    
		                <s:submit cssClass="save" theme="simple" value="    Save " action="CandidateScreeningApproval_save" onclick="return saveValid();"/>&nbsp;
		                    <input name="Submit3" theme="simple" type="button" class="reset" value="    Reset" onclick="resetStatus();"/>
	              	      </s:if><s:else></s:else>	
	               </s:else>
          		</td>
              <td width="22%"></td>
            </tr>
          </table>            
          <label></label></td>
        </tr>
        <tr>
          <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              <tr>
                <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="27" class="formtxt" w> <s:hidden name="selectname"  id="selectname" /> 
                    	<strong><%String status = (String)request.getAttribute("stat");
                    		String statusPass="";
	                    	if(status!=null){out.println(status);}
	                    	else{out.println("Open List");}
	                    	if(status==null){
	                    		statusPass="O";
	                    	}
	                    	else if (status.equals("Open List")) {
	                    		statusPass = "O";
							}
	                    	else if (status.equals("Rejected List")) {
	                    		statusPass = "R";
							}
	                    	else if (status.equals("Short List For Test")) {
	                    		statusPass = "T";
							}
	                    	else if (status.equals("Short List For Interview")) {
	                    		statusPass = "I";
							}
	                    	else if (status.equals("Short List For Test/Interview")) {
	                    		statusPass = "B";
							}
	                    	
	                    	%>
	                    	
	                    	
	                    	
	                    	</strong> </td>
	                    	<%
						int totalPage = (Integer) request.getAttribute("totalPage");
						int pageNo = (Integer) request.getAttribute("PageNo");
						%>
	                 <s:if test="modeLength"> 
			         <td align="right"><b>Page:</b> 
					 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F','<%=statusPass %>');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P','<%=statusPass %>');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event,'<%=statusPass %>');return numbersOnly()"   maxlength="4" title="press Enter" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N','<%=statusPass %>')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L','<%=statusPass %>');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a> 
					 </td> 	
					 </s:if>
                   </tr>
                  <tr>
                    <td colspan="2"><s:hidden name="hiddenStatus"/>
                    <table width="100%" border="0" cellpadding="1" cellspacing="1"   class="formbg">
                        <tr >
<!--                        	class="formth"-->
                          <td width="5%" height="22" valign="top" align="center" class="formth" nowrap="nowrap">
                          
                           <b> <label class="set" name="serial.no" id="serial.no" ondblclick="callShowDiv(this);" ><%=label.get("serial.no")%></label> </b> </td>
                          <td width="9%" valign="top" align="center" class="formth" > <b><label  class = "set"  name="reqs.code" id="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> </b></td>
                          <td width="20%" valign="top" align="center" class="formth"> <b> <label  class = "set"  name="position" id="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b>  </td>
                          <td width="15%" valign="top" align="center" class="formth" nowrap="nowrap"> <b> <label  class = "set"  name="cand.name" id="cand.name" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label> </b> </td>
                            <td width="8%" valign="top" align="center" class="formth">  <b> <label  class = "set"  name="experience" id="experience" ondblclick="callShowDiv(this);"><%=label.get("experience")%></label> </b></td>
                          <td width="8%" valign="top" align="center" class="formth">  <b><label  class = "set"  name="gend" id="gend" ondblclick="callShowDiv(this);"><%=label.get("gend")%></label> </b> </td>
                          <td width="18%" valign="top" align="center" class="formth">  <b> <label  class = "set"  name="rec.name" id="rec.name" ondblclick="callShowDiv(this);"><%=label.get("rec.name")%></label> </b> </td>
                     		<s:if test="openFlag">
                           <td width="10%" valign="top" align="center" class="formth" nowrap="nowrap">  <b> <label  class = "set"  name="stat" id="stat" ondblclick="callShowDiv(this);"><%=label.get("stat")%></label> </b> </td>
                    		</s:if>    
                            <td width="20%" valign="top" align="center" class="formth">  <b> <label  class = "set"  name="rec.comments" id="rec.comments" ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label> </b></td>
                               <td width="20%" valign="top" align="center" class="formth">  <b><label  class = "set"  name="view.cv" id="view.cv" ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></b> </td>
                        </tr>
                        
                        <s:if test="data">
	                        <tr>
	                        	<td width="100%" colspan="12" align="center"><font color="red">There is no data to display</font></td>
	                        </tr>
	                    </s:if>
                        
                        <%//String statusIterator = (String)request.getAttribute("statusIterator");%>
                        <%! int i =0; %>
	       				<%int k = 1; int c=0;%>
	       				<%
											int cnt = pageNo * 20 - 20;
											int m = 0;
											int countRow = 0;
									%>
                        <s:iterator value="list" status="stat">
	                       	<tr  <%if(countRow%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}countRow++; %>
									onmouseover="javascript:newRowColor(this);"
									onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									  ondblclick="return showRequisition('<s:property value="resReqCode" />','<%=statusPass %>');" title="Double click for view Requisition" >
									
<!--	                        class="border2"-->
								
								<td class="sortableTD" width="3%"><%=++cnt%>
											<%
											++m;
											%></td>
								<td class="sortableTD" width="9%" ><s:hidden name="resReqCode"/><s:hidden name="resCode"/><s:hidden name="resCandCode"/>
								 	 <s:hidden name="itRecrruiterId"/>
								<s:property value="reqCode" /> &nbsp;
								
								
								</td>
								<td class="sortableTD" width="20%" align="left"><s:property value="position" /> &nbsp;</td>
								<td class="sortableTD" width="15%"><s:property value="candidateName" />&nbsp;</td>
								<td class="sortableTD" width="13%"><s:property value="exp" />&nbsp;</td>
								<!-- <td class="sortableTD" width="9%" align="center">&nbsp;<s:property value="ctc" /></td>  -->
								<td class="sortableTD" width="13%"><s:property value="gender" />&nbsp;</td>
								<td class="sortableTD" width="15%"><s:property value="recruiter" />&nbsp;</td>
								
								<s:if test="openFlag">
								<td class="sortableTD" width="10%">
						
								<s:select  name="resumeStatus" list="#{'':'Select','R':'Rejected','T':'Short List For Test',
										'I':'Short List For Interview','B':'Short List for Test/Interview'}"   
											id='<%="check"+k %>'  cssStyle="width:135" theme="simple"/>
								
											
								</td>
						    	</s:if>
							
								<td class="sortableTD" width="28%" align="left" nowrap="nowrap">
									<s:if test="screenFlag">
										<s:property value="%{comment}" />&nbsp;
									</s:if>
									<s:else>
									<s:hidden name="hidComment" /> 
									<s:if test="saveCandFlag">  
									 	<s:textarea theme="simple" cols="12"	rows="2" name="comment" id='<%="comm"+k %>' />
									 	<img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
										onclick="javascript:callWindow('<%="comm"+k %>','rec.comments','aa','500','500');" >
									 </s:if>
									 <s:else> <s:hidden name="comment" id='<%="comm"+k %>' /> 
									     <img src="../pages/images/zoomin.gif" height="12" align="absmiddle" width="12" theme="simple"
										  onclick="javascript:callWindow('<%="comm"+k %>','rec.comments','readonly','500','500');" > 
									   </s:else> 
									 </s:else>
								</td>
								  
								<td class="sortableTD" width="5%" align="center"> 
									<s:hidden name="resume"/>
									<input type="button" class="token" value=" View" onclick="return showRecord('<s:property value="resume" />');"/> 
								
							   </td>	
							
	                        </tr>
	                        <%k++; c++;%>
                        </s:iterator>
                        <%i=k; %>
                        <%m=i; %>
                        
                    </table>
                    <input type="hidden" name="count" id="count" value="<%=c%>"/>
                    </td>
                  </tr></table>
                </td>
              </tr> 
         
		        <tr>
		          <td colspan="3" width="100%" >  
		          <table width="100%">
		            <tr>
		               <td width="70%">
		                  <s:if test="apprFlag"></s:if>
			              <s:else><br />
				              <s:if test="saveCandFlag">              
				                <s:submit cssClass="save" theme="simple" value="    Save " action="CandidateScreeningApproval_save"  onclick="return saveValid();" />&nbsp;
				                  <input type="button" theme="simple" class="reset" value="    Reset" onclick="resetStatus();"/>
				                </s:if>
			               </s:else>
		               </td>
		               <td  width="30%" align="Right">	<s:if test="modeLength"><b>Total records:</b>&nbsp;<s:property value="totalRecords" /></s:if></td>
		               
		            </tr>
		          </table> 
		          </td>
		        </tr>
        
 
  <script language="JavaScript" type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
	      </table> 
	    </td>
     </tr>
</table>
</s:form>

<script> 
 
   function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first'; 
	    }
	
	function oldRowColor(cell,val) { 
	cell.className='Cell_bg_second'; 
	}
	
function callReset()
{ 
     
 document.getElementById('paraFrm_filterFlag').value="";
 document.getElementById("paraFrm_requisitionId").value="";
 document.getElementById("paraFrm_positionId").value="";
 document.getElementById("paraFrm_candCode1").value="";
 document.getElementById("paraFrm_hrManagerId").value=""; 
 document.getElementById("paraFrm_hiddengender").value="";  
 document.getElementById("paraFrm_requisitionName").value="";
 document.getElementById("paraFrm_positionName").value="";
 document.getElementById("paraFrm_candidateName1").value="";
 document.getElementById("paraFrm_year1").value=""; 
 document.getElementById("paraFrm_month").value=""; 
 document.getElementById("paraFrm_managerName").value=""; 
 document.getElementById("paraFrm_candGender").value="";
 
}
function chkSize(){
try
{
	var row=<%=i%>
	for(var c=1;c<=row;c++){
	    var com=document.getElementById('comm'+c).value;
	    if(com.length >500){
	    	alert("Maximum length of "+document.getElementById('rec.comments').innerHTML.toLowerCase()+" is 500 characters");
	    	document.getElementById('comm'+c).focus;
	    	document.getElementById('comm'+c).style.background = '#FFFF99';
	    	return false;
	      }
	 
	  } 
 }catch(e)
  {  
 }
 var conf=confirm("Do you really want to save the records?");
	if(conf){ 
	 return true;   
	 }  else
	 {
	  return false;
	 } 
}


function chkStatus(){
var row=<%=i%>
var count=0;
for(var idx=1;idx<row;idx++){
	
			var statusVal=document.getElementById('check'+idx).value;
			//alert("aa"+statusVal);
			if(!(statusVal=="")){
				 count++;
			  }//else{
			  	//return true;
			 // }
			   
			  
	   }
		  
	
if(count==0){
return false;
}else{
return true;
}
}
	
	function resetStatus(){
	
 document.getElementById("paraFrm_requisitionId").value="";
 document.getElementById("paraFrm_positionId").value="";
 document.getElementById("paraFrm_candCode1").value="";
 document.getElementById("paraFrm_hrManagerId").value=""; 
 document.getElementById("paraFrm_hiddengender").value="";  
 document.getElementById("paraFrm_requisitionName").value="";
 document.getElementById("paraFrm_positionName").value="";
 document.getElementById("paraFrm_candidateName1").value="";
 document.getElementById("paraFrm_year1").value=""; 
 document.getElementById("paraFrm_month").value=""; 
 document.getElementById("paraFrm_managerName").value=""; 
 document.getElementById("paraFrm_candGender").value="";
 
	 
	    document.getElementById('paraFrm_viewFilterFlag').value="false" 
	    document.getElementById('paraFrm_filterFlag').value="";
	    document.getElementById('showFilterId').style.display='';
	    document.getElementById('hideFilterId').style.display='none';   
	   
		document.getElementById('paraFrm').action = "CandidateScreeningApproval_input.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	function saveValid(){
	

		//status
		if(document.getElementById("count").value==0){
			alert("There is no record to save.");
			return false;
		}else if(!(chkStatus())){
			alert("Please change the status of atleast one record");
			return false;
		}else if(!chkSize()){
			return false;
		}
		
		
	}	
	
	
	
	function showRecord(fileName)
	{	
		document.getElementById('paraFrm').target ="_blank";
		document.getElementById('paraFrm').action = "CandidateScreeningApproval_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target ="main";
	}
	
	
	function showRequisition(reqCode,status){
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=CandidateScreeningApproval_ckeckdata.action&statusKey='+status;
	    document.getElementById("paraFrm").submit();
	}
	
 
	 function callPage(id,pageImg,status1){  
	 
	    if(document.getElementById('paraFrm_viewFilterFlag').value=="")
			  {
			         document.getElementById('showFilterId').style.display='none';
				     document.getElementById('hideFilterId').style.display='none';
			  }
			  
		var con;
		if(status1=="null" || status1=="" ){		
			status1="O";
		}
		
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
	    document.getElementById('paraFrm').action='CandidateScreeningApproval_ckeckdata.action?status='+status1;
		document.getElementById('paraFrm').submit();
	}	
	
	
	function on(status1){ 
	  if(document.getElementById('paraFrm_viewFilterFlag').value=="")
			  {
			         document.getElementById('showFilterId').style.display='none';
				     document.getElementById('hideFilterId').style.display='none';
			  }
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm_myPage').value=val; 
		document.getElementById('paraFrm').action='CandidateScreeningApproval_ckeckdata.action?status='+status1;
		document.getElementById('paraFrm').submit();
}
 
  pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_show').value;
  
  	if(!(pgno=="")){ 
  			 document.getElementById('selectname').value=document.getElementById('paraFrm_myPage').value; 
  	   }
  	}
	
  	
  	
  	  function showFilterLink(status)
	  {     
		 
         if(document.getElementById('paraFrm_viewFilterFlag').value=="true")
			  {
			         document.getElementById('showFilterId').style.display='none';
				     document.getElementById('hideFilterId').style.display='none';
			  }
			  document.getElementById('selectname').value="1";
        document.getElementById('paraFrm').action='CandidateScreeningApproval_ckeckdata.action?status='+status;
		document.getElementById('paraFrm').submit();
	}
	
 	if(document.getElementById('paraFrm_filterFlag').value=="")
	{
	  onLoadFun();
	}
	function onLoadFun()
	{
	 
	 document.getElementById('filterTR').style.display='none';
	 document.getElementById('showFilterId').style.display='';
	 document.getElementById('hideFilterId').style.display='none'; 
	}
	
   function showFilter()
	{   
	  document.getElementById('paraFrm_filterFlag').value="true";
	  document.getElementById('filterTR').style.display=''; 
	  document.getElementById('showFilterId').style.display='none';
	  document.getElementById('hideFilterId').style.display='';  
	}
	
	
	 function callEditFilter()
	{   
	  document.getElementById('paraFrm_viewEditFlag').value="true";
	 document.getElementById('paraFrm_filterFlag').value="true"; 
	 document.getElementById('filterTR').style.display=''; 
	  document.getElementById('showFilterId').style.display='none';
	  document.getElementById('hideFilterId').style.display='';  
	}
	
	
  window.onload=callMeOnLoad;
	function callMeOnLoad()
	 {
	 
		 if(document.getElementById('paraFrm_viewEditFlag').value=="true")
		 {
		  document.getElementById('showFilterId').style.display='none';
		  document.getElementById('hideFilterId').style.display='';  
		 }
	}
	
 
 function hideFilter()
	{ 
	  
		 document.getElementById("paraFrm_requisitionId").value="";
		 document.getElementById("paraFrm_positionId").value="";
		 document.getElementById("paraFrm_candCode1").value="";
		 document.getElementById("paraFrm_hrManagerId").value=""; 
		 document.getElementById("paraFrm_hiddengender").value="";  
		 document.getElementById("paraFrm_requisitionName").value="";
		 document.getElementById("paraFrm_positionName").value="";
		 document.getElementById("paraFrm_candidateName1").value="";
		 document.getElementById("paraFrm_year1").value=""; 
		 document.getElementById("paraFrm_month").value=""; 
		 document.getElementById("paraFrm_managerName").value=""; 
		 document.getElementById("paraFrm_candGender").value="";
 
	  document.getElementById('paraFrm_filterFlag').value="true";
	  document.getElementById('filterTR').style.display='none'; 
	  document.getElementById('showFilterId').style.display='';
	  document.getElementById('hideFilterId').style.display='none';   
	}
	
   callFilterHideButton();
   function callFilterHideButton()
    {
	   if(document.getElementById('paraFrm_filterFlag').value=="true"){
		     document.getElementById('showFilterId').style.display='none';
			 document.getElementById('hideFilterId').style.display='none'; 
	     }
    }
    
   function appliedFilterFun()
    { 
        document.getElementById('paraFrm_viewEditFlag').value="false";
       document.getElementById('paraFrm_appliedFilterFlag').value="true";
	   requisitionId= document.getElementById('paraFrm_requisitionId').value;
	   positionId= document.getElementById('paraFrm_positionId').value; 
	   candCode1= document.getElementById('paraFrm_candCode1').value;
	   hrManagerId= document.getElementById('paraFrm_hrManagerId').value;  
	   year1= document.getElementById('paraFrm_year1').value; 
	   month= document.getElementById('paraFrm_month').value; 
	   candGender= document.getElementById('paraFrm_candGender').value;
         
         if(requisitionId=="" && positionId =="" && candCode1 =="" && hrManagerId =="" &&  year1 =="" &&  month =="" && candGender=="" )
          {
           alert("Please enter/select atleast one field to apply filter.");
           return false;
         }
         if(month!=""){
            if(eval(month)>11){
             alert("Please enter month less than 12.");
             return false;
            } 
         }
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
	    
		document.getElementById('paraFrm').action='CandidateScreeningApproval_ckeckdata.action?status='+status1;
		document.getElementById('paraFrm').submit();
		}
		
	}	
	
</script>
 