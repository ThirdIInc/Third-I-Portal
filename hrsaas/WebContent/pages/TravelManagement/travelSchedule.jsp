   <%@ taglib prefix="s" uri="/struts-tags"%> 
<script language="JavaScript" type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
<s:form  action="TravelSchdule" validate="true" id="paraFrm"
	validate="true" theme="simple">
	
		<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">

	       
	       
	       
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt" colspan="2"><strong
						class="text_head">Travel Schedule </strong></td>
				</tr>
			</table>
			</td>
		</tr>
	       
	       
	    
	    
	    
	    
		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>

					<td height="27" class="formtxt">
					<a href="TravelSchdule_callStatus.action?status=U">Unschedule List</a>  | 
					<a href="TravelSchdule_callStatus.action?status=S">Schedule List</a> </td>
				</tr>
				         <s:hidden name="status" /><s:hidden  name="apprflag"/><s:hidden name="listLength" /> 

			</table>
			</td>
		</tr>


      
        <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              <tr>
                <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr> <s:hidden name ="scheduled"/>  
                    <s:if test="scheduled"><td height="27" class="formtxt"><strong>Schedule List</strong></s:if>
                    <s:else> 
                    <td height="27" class="formtxt"><strong>Unschedule List</strong></td>
                    </s:else>
                     
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
							<td width="98%" align="center">
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
                    <td width="100%" colspan="5"><s:hidden name = "traSchViewNo"/> 
                      <table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
                        <tr >
                      		 <td width="10%" valign="top" class="formth">Sr.No.</td>
                        	<td width="20%" valign="top" class="formth">Employee ID</td>
                          <td width="35%" valign="top" class="formth">Employee Name</td>
                          <td width="15%" valign="top" class="formth">Application Date </td>    
                          <td width="15%" valign="top" class="formth">&nbsp;</td> 
                        </tr>
                        <s:if test="noData">
			                <tr>
			                  <td width="100%" colspan="8" align="center"><font color="red">No Data To Display</font></td>
			                </tr>
	                    </s:if>
                        <%! int i = 0 ; %>
                        <% int k = 1; %>
                        
				       <s:iterator value="travelSchList"> 
					         <tr>	
							      <td class="border2" width="10%"><%=k %><s:hidden name="travelEmpId"/> <s:hidden name="empName"/> <s:hidden name="appDate"/>  <s:hidden name="travelEmpToken"/>
							         <s:hidden name="traSchAppId"/> <s:hidden name="level"/>
							       </td>
							      <td class="border2" width="20%"><s:property value="travelEmpToken"/></td>
							       <td class="border2" width="35%"><s:property value="empName"/></td>  	  	
						           <td class="border2" width="15%"><s:property value="appDate"/></td>	 
							       <td class="border2" width="15%" >	    
							        <input type="button" name="view_Details" class="token" value="schedule" onclick = "viewDetails('<s:property value="traSchAppId"/>')" />
							       </td>
							       
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
                  <tr>
                     <td  class="cellbg">
                      <table width="130" border="0" align="right" cellpadding="0" cellspacing="0">
	                        <tr>
	                          <td width="100%"></td>
	                           
	                       </tr>  
	                      <tr>
	                         <td ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
	                        </tr>
                      </table>
                   </td>
                 </tr>  
             <tr>
                <td colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
              </tr>
             <tr>
                <td colspan="3">
                   
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
	 callOnload();
	
	function saveValidate(){
	 
	if(document.getElementById("paraFrm_listLength").value==0){
	alert("No record to save");
	return false;
	}else{
	document.getElementById("paraFrm").action="TravelAppr_save.action";
 	document.getElementById("paraFrm").submit();
 		}
	}
		 
	 function callOnload(){
	 	var check =<%=i%>; 
	 		 if(document.getElementById("paraFrm_status").value=='S' ){
			for(var k = 1;k < check ;k++ ){
			document.getElementById("check"+k).disabled=true;	
			}
		}
	}
	function viewDetails(traSchAppId){ 
		//document.getElementById('paraFrm_trApp_travelAppId').value = 1;  
		 var statusFlag = document.getElementById('paraFrm_status').value; 
		document.getElementById('paraFrm').action = "TravelAppMngt_travelScheduleApp.action?travelAppFlag='false'&travelAppId ="+traSchAppId+"&statusSch="+statusFlag; 
		document.getElementById('paraFrm').submit(); 
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
		document.getElementById('paraFrm').action = "TravelSchdule_callStatus.action";
		document.getElementById('paraFrm').submit();
	}
</script>