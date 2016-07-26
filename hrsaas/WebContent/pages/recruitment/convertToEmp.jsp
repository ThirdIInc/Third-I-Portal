<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="ConvertEmp" id="paraFrm" theme="simple" validate="true">
<table class="formbg"  width="100%"  border="0" align="right" >
<s:hidden name="myPage1" id="myPage1"/>
<s:hidden name="radioFlag" id="radioFlag"/>
<s:hidden name="radioChk" id="radioChk"  />
<s:hidden name="srNo"/> 
<s:hidden name="modeLength"/>
<s:hidden name="myPage"/>
<s:hidden name="requisitionId" />
<s:hidden name="positionId" />
<s:hidden name="candCode1" />
<s:hidden name="appointmentCode"/>			    
 <s:hidden name="empTypeId" />
<s:hidden name="ChkSerch"/>
<s:hidden name="searchFlag"/>
<s:hidden name="applyFilterFlag"/>
	
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Convert To Employee</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
      
            
       <tr>
				<td colspan="3" width="100%">
						<table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
						<tr>
                              <td width="100%" height="22" class="formtxt" nowrap="nowrap" > 
                        	<a href="ConvertEmp_input.action?status=N">Convert Employee Due List</a> |  
                        	<a href="ConvertEmp_showEmpList.action?status=Y">Converted Employee List</a></td>  
                        	
                         </tr>
                         </table></td></tr>
            <tr>
				<td colspan="3" width="100%">
						<table width="100%" border="0" cellpadding="1" cellspacing="0" class="formbg">             
               <s:if test="emplFlag"></s:if>
               <s:else>
                    <tr>
						<td colspan="3" width="100%">
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">	
						   
						      <tr>	
						         <td width="18%" height="22">
						            <label  class = "set" name="division" id="division" 
						            	ondblclick="callShowDiv(this);"><%=label.get("division")%></label>&nbsp;:<font color="red" size="2">*</font> </td>
						         <td width="25%" nowrap="nowrap" height="22"><s:hidden name="divisionCode"/><s:textfield name="divisionName" size="25" readonly="true"/>
						           
						           <img class="iconImage" src="../pages/images/recruitment/search2.gif" width="15"
											height="16" onclick="javascript:callsF9(500,325,'ConvertEmp_f9Division.action');"/>  	
						           </td>
						           
						         <td width="14%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 
						            
						         <td width="18%" height="22">
						            <label  class = "set" name="branch" id="branch" 
						            	ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>&nbsp;:<font color="red" size="2">*</font> </td>
						         <td width="25%" nowrap="nowrap" height="22"><s:hidden name="branchCode"/><s:textfield name="branchName" size="25" readonly="true"/>
						            
						            	 <img class="iconImage" src="../pages/images/recruitment/search2.gif" width="15"
											height="16" onclick="javascript:callsF9(500,325,'ConvertEmp_f9Branch.action');"/> 
						            </td>
						       
						       </tr>
						         
						   <tr>	
						         <td width="18%" height="22" nowrap="nowrap">
						            <label  class = "set" name="department" id="department" 
						            	ondblclick="callShowDiv(this);"><%=label.get("department")%></label>&nbsp;:<font color="red" size="2">*</font> </td>
						         <td width="25%" nowrap="nowrap" height="22"><s:hidden name="deptCode"/><s:textfield name="deptName" size="25" readonly="true"/>
						           
						            	<img class="iconImage" src="../pages/images/recruitment/search2.gif" width="15"
											height="16" onclick="javascript:callsF9(500,325,'ConvertEmp_f9Dept.action');"/> 
						            </td>
						               <td width="14%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td> 
						             <td width="18%" height="22" nowrap="nowrap">
						            <label  class = "set" name="designation" id="designation" 
						            	ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>&nbsp;:<font color="red" size="2">*</font> </td>
						         <td width="25%" nowrap="nowrap" height="22"><s:hidden name="desgCode"/><s:textfield name="desgName" size="25" readonly="true"/>
						           
						            	<img class="iconImage" src="../pages/images/recruitment/search2.gif" width="15"
											height="16" onclick="javascript:callsF9(500,325,'ConvertEmp_f9Desg.action');"/>  
						           </td>
						       
						       </tr>
						     
			      			</table>
			      		</td>
			      		 
			   		</tr>
              		         
			
             </s:else></table></td></tr>
          
              <tr>
					<td width="100%">
					<table width="100%" border="0"  cellpadding="0" cellspacing="0"  >
					<tr>
						<td align="left" colspan="1">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
					</td>
			            <td  align="right">	</td>
				</tr>	
			   <tr>
			     <td >
				<table width="100%" border="0"  cellpadding="0" cellspacing="0"	class="formbg" >
			    <td>  <strong class="forminnerhead"><%String status = (String)request.getAttribute("stat");
                    		String statusPass="";
	                    	if(status!=null){out.println(status);}
	                    	else{out.println("Convert Employee Due List");}
	                    	if(status==null){
	                    		statusPass="N";
	                    	}
	                    	else if (status.equals("Converted Employee List")) {
	                    		statusPass = "Y";
							}
	                    	
	                    	
	                    	%></strong></td>
			     <%
			     int totalPage;
			     int pageNo;
			     if(statusPass=="N" || statusPass==""){
			    	 totalPage = (Integer) request.getAttribute("totalPage");
			    	 pageNo= (Integer) request.getAttribute("PageNo");
			     }else{
			    	 totalPage = (Integer) request.getAttribute("totalPage1");
			    	 pageNo= (Integer) request.getAttribute("PageNo1");
			     }
					%>
					
                   <td width="40%" align="right">  <s:if test="data"> </s:if> <s:else> <b>Page:</b> 
								 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 		<a href="#" onclick="callPage('1','F','<%=statusPass %>');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P','<%=statusPass %>');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event,'<%=statusPass %>');return numbersOnly()"   maxlength="10" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N','<%=statusPass %>')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L','<%=statusPass %>');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
										</s:else>
							 
						</td>
    
      <tr>	
			       <td colspan="3"  >
			       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
				       	<s:if test="emplFlag">
				       	<tr>
		                    <td width="100%">
		                    
		                    
		                    <table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
		                    		<tr >
			                          <td width="5%"  valign="top" class="formth">
			                          	<label  class = "set" name="serial.no" id="serial.no" 
			            				ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
			            				<td width="15%" valign="top" class="formth">
			                          	<b><label  class = "set" name="reqs.code" id="requisition.code" 
			            				ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
			            				 <td width="15%" valign="top" class="formth">
			                          	<label  class = "set" name="employee.id" id="employee.id" 
			            				ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
			                          <td width="15%" valign="top" class="formth">
			                          	<label  class = "set" name="employee" id="employee" 
			            				ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
			                          <td width="12%" valign="top" class="formth">
			                          	<label  class = "set" name="branch" id="branch" 
			            				ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
			                          <td width="9%" valign="top" class="formth">
			                          	<label  class = "set" name="department" id="department" 
			            				ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
			                          <td width="8%" valign="top" class="formth">
			                          	<label  class = "set" name="designation" id="designation" 
			            				ondblclick="callShowDiv(this);"><%=label.get("designation")%></label></td>
			                          <td width="6%" valign="top" class="formth">
			                          	<label  class = "set" name="division" id="division" 
			            				ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
			                          <td width="12%"  valign="top" class="formth">
			                          	<label  class = "set" name="employee.type" id="employee.type" 
			            				ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label></td>
			                         
			                        </tr>
			                        
			                        <s:if test="data">
				                        <tr>
				                        	<td width="100%" colspan="8" align="center"><font color="red">There is no data to display</font></td>
				                        </tr>
				                    </s:if>
			                        
			                     
				  				<%!
				  				int k = 0;
				  				%>	<%
											int cnt = pageNo * 20 -20;
				  				int m=0;
											
									%>
			                        <s:iterator status="stat" value="empList">
			                        	<tr>	
			                        		<td width="5%" class="sortableTD" align="center"><%=++cnt%><%++m;
											%>&nbsp;</td>                  		       
											<td width="15%" class="sortabletd"><s:property value="reqsName"/></td>
			                        		<td width="10%" class="sortableTD"><s:property value="empTok"/>&nbsp;</td>
			                        		<td width="20%" class="sortableTD"><s:property value="empName"/>&nbsp;</td>
			                        		<td width="14%" class="sortableTD"><s:property value="empBrn"/>&nbsp;</td>
			                        		<td width="13%" class="sortableTD"><s:property value="empDept"/>&nbsp;</td>
			                        		<td width="13%" class="sortableTD"><s:property value="empDesg"/>&nbsp;</td>
			                        		<td width="13%" class="sortableTD"><s:property value="empDiv"/>&nbsp;</td>
			                        		<td width="12%" class="sortableTD"><s:property value="empTyp"/> &nbsp;</td>
			                        		
			                        		
			                        	</tr>
			                         <%
				                     m=k;
				                        %>
								</s:iterator>
								 
		                    	</table>
		                    	
		                    </td></tr>
		                    
		                    </s:if>
		                    
    
		                    
		                    <s:else>
		                    <tr><td width="100%">
		                    	<table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
		                    	
		                    		<tr >
		                    		  <td width="1%" class="formth">&nbsp;</td>
			                          <td width="5%"  valign="top" class="formth">
			                          	<b><label  class = "set" name="serial.no" id="serial.no" 
			            				ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
			            				 <td width="15%" valign="top" class="formth">
			                          	<b><label  class = "set" name="reqs.code" id="requisition.code" 
			            				ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
			                          <td width="20%" valign="top" class="formth">
			                          	<b><label  class = "set" name="cand.name" id="candidate.name" 
			            				ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b></td>
			                          <td width="20%" valign="top" class="formth">
			                          	<b><label  class = "set" name="position" id="position" 
			            				ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
			                          <td width="15%" valign="top" class="formth">
			                          	<b><label  class = "set" name="employee.type" id="empType" 
			            				ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label></b></td>
			                          <td width="20%" valign="top" class="formth">
			                          	<b><label  class = "set" name="hiring.mgr" id="hiring.manager" 
			            				ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
			                       	 <td width="6%" valign="top" class="formth">
			                          	<b><label  class = "set" name="view.reqs" id="view.reqs" 
			            				ondblclick="callShowDiv(this);"><%=label.get("view.reqs")%></label></b></td> 
			                          <td nowrap="nowrap"  valign="top" class="formth" width="5%">
			                          	<b><label  class = "set" name="view.cv" id="view.cv" 
			            				ondblclick="callShowDiv(this);"><%=label.get("view.cv")%></label></b></td>
			                         
			                        </tr>
			                        
			                        <s:if test="data">
				                        <tr>
				                        	<td width="100%" colspan="8" align="center"><font color="red">There is no data to display</font></td>
				                        </tr>
				                    </s:if>
			                        
			                       <%!int c = 0;%>
				  				<%
				  				int j = 1;
				  				%><%
											int cnt1 = pageNo *20-20;
				  							int n=0;
											int countRow=0;
									%>
			                        <s:iterator status="stat" value="list">
			                        	<tr <%if(countRow%2==0){
											%> class="tableCell1" <%}
			                        		 else{%>
												class="tableCell2" <%}
			                        		 countRow++; %>
												onmouseover="javascript:newRowColor(this);"
												onmouseout="javascript:oldRowColor(this,<%=countRow%2 %>);"
									 			ondblclick="return viewReqs('<s:property value="reqsCode" />','O','<%=j%>','<s:property value="candidateCode" />');" title="Double click for view Requisition " > 	
			                        	
			                        		<td width="1%" class="sortabletd">
			                        		    	<input type="radio" name="selCand" id="<%="selCand"+j%>"    value="<%=j-1 %>"  onclick="showRec('<s:property value="reqsCode"/>','<%=j %>','<s:property value="candidateCode"/>','<s:property value="appointmentCodeItr"/>');"/>
			                        		</td>
			                        		<td width="5%" class="sortabletd" align="center">
			                        		       
			                        		<%=++cnt1%><%++n;%>&nbsp;
			                        		
			                        		</td>
			                        		<td width="15%" class="sortabletd"><s:property value="reqsName"/></td>
			                        		<td width="20%" class="sortabletd"><s:property value="candidateName"/>
			                        			<s:hidden name="candidateName"/><s:hidden name="candidateCode"/>
			                        			<s:hidden name="reqsCode"/>
			                        			<s:hidden  name="appointmentCodeItr"/>
			                        			</td>
			                        		<td width="20%" class="sortabletd"><s:property value="position"/>&nbsp;</td>
			                        		<td width="15%" class="sortabletd"><s:property value="empType"/> &nbsp;</td>
			                        		<td width="20%" class="sortabletd"><s:property value="hireMan"/>&nbsp;</td>
			                        	 	<td width="6%" class="sortabletd" align="center">
			                        			<input type="button" class="token" value="View" onclick="viewReqs('<s:property value="reqsCode"/>','O','<%=j%>');"/></td> 
			                        			<s:hidden name="candResume"/>
			                        		<td width="5%" class="sortabletd" align="center">
			                        			<input type="button" class="token" value="View" onclick="showRecord('<s:property value="candResume"/>');"/></td>
			                        		
			                        	</tr>
			                         <%
				                        j++;
				                        %>
								</s:iterator>
								  <%
								  	c = j;
								  	j = 1;
								  	n=c;
								  %>
		                    	</table>
		                    	</td>
		                    	</tr>
		                    </s:else>
		                  
		           
			</table>
			</td>
			</tr>
			 
			</table>
			</td>
		</tr>
		 <tr>
					<td width="100%" colspan="3">
					<table width="100%" border="0"  cellpadding="0" cellspacing="0"  >
					<tr>
						<td align="left" colspan="2">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
					</td>
			            <td  align="right">	<s:if test="modeLength"><b>Total no. of records :</b>&nbsp;<s:property value="totalRecords" /></s:if></td>
					</tr>	
			</table>
			</td>
			</tr>
		</table>
		</td>
		</tr>
		</table>
		</s:form>
		 
 <script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>

callonLoad();

  function newRowColor(cell)
    {
		  cell.className='Cell_bg_first'; 
	    } 
	    
	function oldRowColor(cell,val) { 
	cell.className='Cell_bg_second'; 
	}

	function callonLoad()
	{ 
	var status=document.getElementById("radioFlag").value;
	if(status=="N" || status==""){
	      var id =document.getElementById("paraFrm_srNo").value;
	     
	      if(id!="" ){
	      document.getElementById("selCand"+id).checked=true; 
	      }else{
	        document.getElementById("selCand"+id).checked=false; 
	      }
	      document.getElementById("paraFrm_srNo").value="";  

  }
 } 


function chkField(){

           var reqname=document.getElementById('paraFrm_requisitionName').value;
  		   var reqid=document.getElementById('paraFrm_requisitionId').value;
  		   var positionId=document.getElementById('paraFrm_positionId').value;
  		   var posName=document.getElementById('paraFrm_positionName').value;
  		   var candidateName1=document.getElementById('paraFrm_candidateName1').value;
  		   var employeeName=document.getElementById('paraFrm_employeeName').value;
  		   
			 
    if((reqname=="")&&(posName=="")&&(candidateName1=="")&&(employeeName==""))
  		
  		{
  		
  		alert('Please enter/select atleast one field to apply filter');
  		return false;
  		}

}

	function callShowFilter(){//callShowFilter()
				document.getElementById('hideFilter').style.display='';
				document.getElementById('showFilter').style.display='none';
				document.getElementById('showFilterValue').style.display='inline';
				document.getElementById('enableFilterValue').style.display='none';
				document.getElementById('clearId').style.display='';
				
		   }
	
	function callHideFilter(){
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='';
				document.getElementById('clearId').style.display='none';
			//document.getElementById('enableFilterValue').style.display='inline';
		    }
	

function callClear(){
		
			  document.getElementById('showFilterValue').style.display='none';
			  document.getElementById("paraFrm_searchFlag").value='false';
			  document.getElementById("paraFrm").action='ConvertEmp_clearFilter.action';
			  document.getElementById("paraFrm").submit();
		   }
 function calReset(){

 		     document.getElementById('paraFrm_requisitionId').value="";
 		     document.getElementById('paraFrm_requisitionName').value="";
		     document.getElementById('paraFrm_positionId').value="";
		     document.getElementById('paraFrm_positionName').value="";
		     document.getElementById('paraFrm_candCode1').value="";
		     document.getElementById('paraFrm_candidateName1').value="";
		     document.getElementById('paraFrm_empTypeId ').value="";		     
		     document.getElementById('paraFrm_employeeName').value="";

}

function callPageText(id,status1){   
	
		
			var key; //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode;
			else
			   	key = id.which;
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;		
		 	totalPage =document.getElementById('totalPage').value;	
		 	var actPage="";
		 	if(status1=="N" || status1=="")
		       actPage = document.getElementById('paraFrm_myPage').value;   
	      	else
	      	   actPage = document.getElementById('myPage1').value;
	      
	        if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').value = actPage;
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').value = actPage;
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		  
		  
		   
		   if(status1=="N" || status1==""){		
		      document.getElementById('paraFrm_myPage').value=pageNo;
			  document.getElementById('paraFrm').action='ConvertEmp_input.action';
			  document.getElementById('paraFrm').submit();
			}
			else {
			    document.getElementById('myPage1').value=pageNo;
				document.getElementById('paraFrm').action='ConvertEmp_showEmpList.action?status=Y';
				document.getElementById('paraFrm').submit();
			}
		   
		}
		
	}	

	function callPage(id,pageImg,status1){  

 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 
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
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
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
		if(status1=="N" || status1==""){		
			document.getElementById('paraFrm_myPage').value=id;
			document.getElementById('paraFrm').action='ConvertEmp_input.action';
			document.getElementById('paraFrm').submit();
		}
		else {
			document.getElementById('myPage1').value=id;
			document.getElementById('paraFrm').action='ConvertEmp_showEmpList.action?status=Y';
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
	function showRecord(fileName)
	{
		document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action = "ConvertEmp_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target='main';
	}
	
	
	function viewReqs(reqCode,status,id,candCode){
		document.getElementById("paraFrm_srNo").value= id;
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=ConvertEmp_showCandList.action&statusKey='+status+'&candCode='+candCode;
	  
	    document.getElementById("paraFrm").submit();
	}
	
	function converttoemployeeFun()
	{
	document.getElementById('radioChk').value = 'false';
		var rowCount = <%= c%>;
		var flag = "false";
		for(i=1;i<rowCount;i++)
		{
			if(document.getElementById('selCand'+i).checked == true)
			{
				flag="true";
				break;
			}
		}
		if(flag=="false")
		{
			alert("Please select a record");
			return false;
		}
		
		
		
		/*
		var fieldName = ["paraFrm_divisionName"];
		var lableName = ["division"];
		var flag      = ["select"];
		
		if(!validateBlank(fieldName, lableName, flag)) return false;
		
		
		
		var fieldName = ["paraFrm_deptName"];
		var lableName = ["department"];
		var flag      = ["select"];
		
		if(!validateBlank(fieldName, lableName, flag)) return false;
		
		var fieldName = ["paraFrm_branchName"];
		var lableName = ["branch"];
		var flag      = ["select"];
		
		if(!validateBlank(fieldName, lableName, flag)) return false;
		
		var fieldName = ["paraFrm_desgName"];
		var lableName = ["designation"];
		var flag      = ["select"];
		if(!validateBlank(fieldName, lableName, flag)) return false;
		*/
		var con=confirm('Do you really want to convert this candidate as employee ?');
		 if(con){
			document.getElementById('paraFrm').action = 'ConvertEmp_convertEmp.action';
			document.getElementById('paraFrm').submit();
		}
		else{
			return false;
		}
	}
	
	function clearFun(){
	
	       //     document.getElementById('showFilterValue').style.display='none';
			document.getElementById("paraFrm_searchFlag").value='false';
	
			document.getElementById("paraFrm").action='ConvertEmp_reset.action';
	    	document.getElementById("paraFrm").submit();
	 
	}
	
	function showRec(reqCode,val,candCode, publishCodeItr){ 
       document.getElementById("paraFrm_srNo").value=val;
       document.getElementById("radioChk").value='true';
	   document.getElementById("paraFrm").action='ConvertEmp_showRec.action?reqCode='+reqCode+'&candCode='+candCode+'&publishCodeItr='+publishCodeItr+'&flag=true';
       document.getElementById("paraFrm").submit();
	}
	
	
	
</script>