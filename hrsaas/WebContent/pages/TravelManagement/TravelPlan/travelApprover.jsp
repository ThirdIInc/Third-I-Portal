    <%@ taglib prefix="s" uri="/struts-tags"%> 
<script language="JavaScript" type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<link rel="stylesheet" type="text/css" href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>
 <script type="text/javascript"> 
	var jourApp = new Array(); 
</script>
<s:form  action="TravelAppr" validate="true" id="paraFrm"
	validate="true" theme="simple">    
	
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg" >
	       
	        <tr>
	               <td colspan="3" valign="bottom" background="../pages/images/recruitment/lines.gif" class="txt"><img src="../pages/images/recruitment/lines.gif" width="16" height="1" /></td>
	        </tr> 
	        <tr>
			 <td colspan="3">
				<table width="100%" border="0" align="center" class="formbg">
					<tr>
						<td valign="bottom" class="txt" width="3%" colspan="1"><strong
							class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="97%" class="txt" colspan="2"><strong
							class="text_head">Travel Approval </strong></td>
					</tr>
				</table>
				</td>
			</tr>
            <tr> <td width="100%" colspan="3"  class="btn-middlebg" >
            <table height="18" border="0" cellpadding="0" cellspacing="0" class="formbg" width="100%">
						<tr>
		
							<td class="btn-middlebg">
							<div id="tabnav" style="vertical-align: bottom">
							<ul>
								<li><a href="javascript:callDivLoadApproval();">
									<div align="center"><span>Travel Application Approval</span> </div></a>
								</li>
								<li><a href="javascript:callDivLoadCancelApproval();">
									<div align="center"><span>Travel Cancel Approval</span></div></a>
								</li> 
							</ul>
							</div>
							</td>
						</tr>
					</table> 
          </td>
           </tr> 
           
            <tr> 
              <s:if test="%{trAppCanStatus}"><td width="100%" colspan="3"><strong class="text_head"> Travel Approval</strong></td> </s:if>
              <s:else> <td width="100%" colspan="3"><strong class="text_head">Travel Cancel Approval</strong></td>  </s:else>
		   </tr>
           
	        <tr>
	             <td height="5" colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
	        </tr>
	        <tr>
	          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
	            <tr>
	              <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
	                <tr>
	                  <td>
	                  <s:if test="%{trAppCanStatus}">
	                  <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	                      <tr>
	                        <td height="27" class="formtxt"> <a href="TravelApprNew_callStatus.action?status=P&trAppCanStatus=true">Pending List</a> |  <a href="TravelApprNew_callStatus.action?status=A&trAppCanStatus=true">Approved List</a> |  <a href="TravelApprNew_callStatus.action?status=R&trAppCanStatus=true">Rejected List</a> </td>
	                      </tr><s:hidden  name="apprflag"/><s:hidden name="listLength" />  <s:hidden name="trAppId" /> 
	                        <s:hidden name="levelApp" />  <s:hidden name="appStatus" />  
	                        <s:hidden name="empId" />   <s:hidden name="trAppCanStatus" />
	                  </table>
	                  </s:if>
	                  <s:else>
	                    <table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
	                      <tr>
	                        <td height="27" class="formtxt"> <a href="TravelApprNew_callStatus.action?status=P&trAppCanStatus=false">Pending List</a> |  <a href="TravelApprNew_callStatus.action?status=A&trAppCanStatus=false">Approved List</a> |  <a href="TravelApprNew_callStatus.action?status=R&trAppCanStatus=false">Rejected List</a> </td>
	                      </tr><s:hidden  name="apprflag"/><s:hidden name="listLength" />  <s:hidden name="trAppId" /> 
	                        <s:hidden name="levelApp" />  <s:hidden name="appStatus" />  
	                        <s:hidden name="empId" />   <s:hidden name="trAppCanStatus" />
	                  </table>
	                  </s:else>
	                  </td>
	                </tr>
	              </table></td>
	            </tr>
	            <tr>
	              <td width="78%"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /><br />
	              <s:if test="%{apprflag}"></s:if><s:else>
	              <s:if test="%{trAppCanStatus}"> <input name="Submit222" type="button" class="save" value="    Save " onclick="return saveValidate();" /> </s:if>
	              <s:else> <input name="Submit222" type="button" class="save" value="    Save " onclick="return saveCancel();" /> </s:else>
	              </s:else>
	             </td>
	              <td width="22%"><div align="right"><span class="style2"><font color="red">*</font></span> Indicates Required </div></td>
	            <s:hidden name="status" /></tr>
	          </table>            
	          <label></label></td>
	        </tr>
		        <tr>
		          <td colspan="3" height="2"> </td>
		        </tr>
        <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              <tr>
                <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <s:if test="%{pen}"><td height="27" class="formtxt"><strong>Pending List</strong></td></s:if>
                    <s:elseif test="%{app}"><td height="27" class="formtxt"><strong>Approved List</strong></td></s:elseif>
                    <s:elseif test="%{rej}"><td height="27" class="formtxt"><strong>Rejected List</strong></td></s:elseif>
                                       
                    <s:else><td height="27" class="formtxt"><strong>Pending List</strong></td></s:else>
                  </tr>                   
                  <tr>
                     <td width="100%" colspan="5">
                     <s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
					<%try
					{
						int totalPage = (Integer) request.getAttribute("totalPage");
						int pageNo = (Integer) request.getAttribute("pageNo");
					%>
                       <table width="98%">
						<tr>
							<td width="98%" align="right">           <s:if test="noData"> </s:if> <s:else><b>Page</b>   </s:else>
							<%	if(pageNo != 1)
								{
							%>		<a href="#" onclick="callPage('1');" >
										<img src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P')" >
										<img src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a>
							<%	}
								if(totalPage <= 5)
								{
									if(totalPage == 1)
									{
							%>			<b><u><%=totalPage%></u></b>
							<%		}
									else
									{
										for(int z = 1; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}
									}
								}
								else
								{
									if(pageNo == totalPage - 1 ||pageNo == totalPage)
									{									
										for(int z = pageNo - 2; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}
									}
									else if(pageNo <= 3)
									{
										for(int z = 1; z <= 5; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}						
									}
									else if(pageNo > 3)
									{
										for(int z = pageNo - 2; z <= pageNo + 2; z++)
										{
											if(pageNo == z)
											{
							%>					<b><u><%=z%></u></b>
							<%				}
											else
											{
							%>					&nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a>
							<%				}
										}					
									}
								}
								if(!(pageNo == totalPage))
								{
									if(totalPage == 0){}
									else
									{
							%>			....<%=totalPage%>
										<a href="#" onclick="callPage('N')" >
											<img src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>');" >
											<img src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
							<%		}
								}
							%>
							</td>
						</tr>
					</table>
                   </td>
                 </tr>    
	  
                  <tr>
                    <td><s:hidden name = "travelViewNo"/>    
                      <table width="100%" border="0" cellpadding="1" cellspacing="1"   class="formbg">
                        <tr >
                      		 <td width="5%" valign="top" class="formth">Sr.No.</td>
                        	<td width="22%" valign="top" class="formth">Employee Name</td>
                          <td width="22%" valign="top" class="formth">Travel Request Name</td>
                          <td width="15%" valign="top" class="formth">Application Date </td>                       
                          <td width="10%" valign="top" class="formth">Status</td>
                          <td width="15%" valign="top" class="formth">View Details</td>
                          <td width="25%" valign="top" class="formth">Remarks</td>
                        </tr>
                        <s:if test="noData">
			                <tr>
			                  <td width="100%" colspan="8" align="center"><font color="red">No Data To Display</font></td>
			                </tr>
	                    </s:if>
                        <%! int i = 0 ; %>
                        <% int k = 1; %>
                        
				       <s:iterator value="travelList"> 
					         <tr>	
							      <td class="sortableTD" width="5%"> <s:property value="dispSrNo"/>   <s:hidden name="travelEmpId"/>   <s:hidden name="dispSrNo"/> 
							       <s:hidden name="appDate"/> 
							            <s:hidden name="travelAppId"/> <s:hidden name="level"/> 
							            <input type="hidden" name="empName" id ="empName<%=k%>" value='<s:property value="empName"/>'/> 
							         <input type="hidden" name="trRequestName" id ="trRequestName<%=k%>" value='<s:property value="trRequestName"/>'/>
							           <input type="hidden" name="gradeJourneyId" id ="gradeJourneyId<%=k%>" value='<s:property value="gradeJourneyId"/>'/> 
							            <input type="hidden" name="gradeClassId" id ="gradeClassId<%=k%>" value="<s:property value="gradeClassId"/>"/> 
							          
							          <!-- for checking validation -->
							          <input type="hidden" name="alertTrFlag" id ="alertTrFlag<%=k%>" value="<s:property value="alertTrFlag"/>"/> 
							          <input type="hidden" name="alertTrMode" id ="alertTrMode<%=k%>" value="<s:property value="alertTrMode"/>"/> 
							           
							         <!-- for checking validation Hotel Type -->
							          <input type="hidden" name="alertHotelType" id ="alertHotelType<%=k%>" value="<s:property value="alertHotelType"/>"/> 
							          <input type="hidden" name="alertHotelFlag" id ="alertHotelFlag<%=k%>" value="<s:property value="alertHotelFlag"/>"/> 
							           
							           
							       </td>
							      <td class="sortableTD" width="22%"><s:property value="empName"/></td>
							       <td class="sortableTD" width="22%"> <s:property value="trRequestName"/> </td>  	  	
						           <td class="sortableTD" width="15%"><s:property value="appDate"/></td>	      
							       <td align="center" width="10%" class="sortableTD"> 
							       <s:if test="traApp.apprflag"><s:property value="statusNew"/></s:if>
							       <s:else> 
							       <s:select name="checkStatus" id="<%="check"+k %>"
											 cssStyle="width:100" theme="simple" list="#{'P':'Pending','A':'Approved','R':'Rejected'}" />  </s:else>
									 </td> 
							       <td class="sortableTD" width="15%" align="center">	     
							        <input type="button" name="view_Details" class="token" value="Details" onclick = "viewDetails('<s:property value="travelAppId"/>','<s:property value="level"/>','<s:property value="travelEmpId"/>')" />
							       </td>
							       <td class="sortableTD" width="20%"><s:if test="traApp.apprflag"><s:property value="remark"/></s:if><s:else>
							        
							         <textArea  name="remark"  id="remark<%=k%>"  value="%{remark}"  cols="20" rows="1" > </textArea>  </s:else>&nbsp;</td>
					             <script>
									jourApp[<%=k%>] = document.getElementById('paraFrm_appDate').value;
								</script>
								 
					      
					      </tr>   
					    <%k++; %>
					</s:iterator>
                    <% i=k ; %>      
                 </table>
                 </td>
                   <%}
					catch(Exception e)
					{
				    	e.printStackTrace();
					}%>
              </tr>
            
                <td colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
              </tr>
             <tr>
                <td colspan="3">
                   <s:if test="%{apprflag}"></s:if><s:else>
	              <s:if test="%{trAppCanStatus}"> <input name="Submit222" type="button" class="save" value="    Save " onclick="return saveValidate();" /> </s:if>
	              <s:else> <input name="Submit222" type="button" class="save" value="    Save " onclick="return saveCancel();" /> </s:else>
	              </s:else>
                </td>
             </tr> 
        </table>
      </td>
    </tr> 
   </table>
   </td>
  </tr>
 </table>
</s:form>		

	<script>
	
	
  function callDivLoadApproval()
	{
		document.getElementById('paraFrm_trAppCanStatus').value="true";
		document.getElementById('paraFrm').action = 'TravelApprNew_callStatus.action?status=P'; 
		document.getElementById('paraFrm').submit();
	}
	
	 function callDivLoadCancelApproval()
	{
		document.getElementById('paraFrm_trAppCanStatus').value="false";
		document.getElementById('paraFrm').action = 'TravelApprNew_callStatus.action?status=P'; 
		document.getElementById('paraFrm').submit();
	}
	
	 callOnload();
	
	function saveValidate(){    
	if(document.getElementById("paraFrm_listLength").value==0){
	alert("No record to save");
	return false;
	}
	else{    
	var recordFlag ="false";
	for(var i=1; i<jourApp.length;i++)
		{  
		if(document.getElementById("check"+i).value=="A" || document.getElementById("check"+i).value=="R")
			{  
			  recordFlag ="true";
			  break ;
			 }
	} //end of for
	
	if(recordFlag =="false")
	{
	 alert("Please change the status of atleast one application!");
	 return false;
	} 
	
	for(var i=1; i<jourApp.length;i++)
		{  
		if(document.getElementById("check"+i).value=="R")
			{ 
			 var comments = document.getElementById("remark"+i).value 
			  if(comments=="" || comments==" " )
				{   
				 alert("Please enter the comments for application no."+i);
				 return false;
				 }
			 }
	} //end of for
	
	
	for(var i=1; i<jourApp.length;i++)
		{ 
//========================== start of travel Mode==============
			if(document.getElementById("check"+i).value=="A")
				{   
				  var empName = document.getElementById("empName"+i).value;  
				  var alertTrFlag =  document.getElementById("alertTrFlag"+i).value;  
				  var alertTrMode =  document.getElementById("alertTrMode"+i).value;    
				  
				  var alertHotelType =  document.getElementById("alertHotelType"+i).value;  
				  var alertHotelFlag =  document.getElementById("alertHotelFlag"+i).value;  
				  
				  
				  if(alertTrFlag=="true")
				  {
			       var conf = confirm("Applicant No. "+i+" "+empName+" is not entitled to traveling by \n"+alertTrMode+" . \n Do you really want to approve the application ? ");
				     if(conf)
				    {
				    
				    }else
				    {
				       return false ;
				      }
				    } 
				    
				    
//============================ end of travel Mode==============
 
 //============================ start of hotel and room type==============

		    	  if(alertHotelFlag=="true")
				  {
			       var conf = confirm("Applicant No. "+i+" "+empName+" is not entitled to Lodging \n"+alertHotelType+" . \n Do you really want to approve the application ? ");
				     if(conf)
				    {
				    
				    }else
				    {
				       return false ;
				      }
				    }  
 //============================ end of  hotel and room type============== 
				    
				  }//end of if  
		  } //end of for loop jourApp 
  document.getElementById("paraFrm").action="TravelApprNew_save.action";
  document.getElementById("paraFrm").submit();  
   }  
 }
 
 
 	function saveCancel(){    
	if(document.getElementById("paraFrm_listLength").value==0){
	alert("No record to save");
	return false;
	}
	else{    
	var recordFlag ="false";
	for(var i=1; i<jourApp.length;i++)
		{  
		if(document.getElementById("check"+i).value=="A" || document.getElementById("check"+i).value=="R")
			{  
			  recordFlag ="true";
			  break ;
			 }
	} //end of for
	
	if(recordFlag =="false")
	{
	 alert("Please change the status of atleast one application!");
	 return false;
	} 
	
	for(var i=1; i<jourApp.length;i++)
		{  
		if(document.getElementById("check"+i).value=="R")
			{ 
			 var comments = document.getElementById("remark"+i).value 
			  if(comments=="" || comments==" " )
				{   
				 alert("Please enter the comments for application no."+i);
				 return false;
				 }
			 }
	} //end of for
 
	  document.getElementById("paraFrm").action="TravelApprNew_save.action";
	  document.getElementById("paraFrm").submit();  
   }  
 }
 
		 
function callOnload(){ 
	 	var check =<%=i%>; 
	 		 if(document.getElementById("paraFrm_status").value=='A' || document.getElementById("paraFrm_status").value=='R'){
			for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;	
			}
		}
	}
	function viewDetails(travelAppId,level,empId){   
	    var trAppCanStatus = document.getElementById('paraFrm_trAppCanStatus').value ;
	    document.getElementById('paraFrm_trAppCanStatus').value = trAppCanStatus; 
		document.getElementById('paraFrm_trAppId').value = travelAppId; 
		document.getElementById('paraFrm_levelApp').value = level; 
		document.getElementById('paraFrm_appStatus').value ="A"; 
		document.getElementById('paraFrm_empId').value = empId;   
		document.getElementById('paraFrm').action = "TravelApplication_callViewAppr.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		 
	}
	
	function callPage(id)
	{

		if(id == 'P')
		{
			var p = document.getElementById('hdPage').value;
			id = eval(p) - 1;
		}
		if(id == 'N')
		{
			var p = document.getElementById('hdPage').value;
			id = eval(p) + 1;
		}
		document.getElementById('hdPage').value = id;
		document.getElementById('paraFrm').action = "TravelApprNew_callStatus.action";
		document.getElementById('paraFrm').submit();
	}
</script>