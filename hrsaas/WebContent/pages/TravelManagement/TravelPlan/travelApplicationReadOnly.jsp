
<script type="text/javascript">
 var cityArr = new Array();
  var travelArr = new Array();
  var localArr = new Array();
  var lodgArr = new Array();
</script>

<%@ taglib prefix="s" uri="/struts-tags"%>  
<%@ include file="/pages/common/labelManagement.jsp"%>

 <%
  String dataLodg =""+request.getAttribute("dataLodg");
  String dataLocal =""+request.getAttribute("dataLocal"); 
 if(dataLodg!=null && !dataLodg.equals("null") )
 {  
 }else
 {
	 dataLodg="none";
 }
 if(dataLocal!=null && !dataLocal.equals("null") )
 {  
 }else
 {
	 dataLocal="none";
 }
 System.out.println("dataLodg==="+dataLodg+" ====== dataLocal==="+dataLocal);
 %>

<s:form action="TravelApplication" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" class="formbg"
		cellspacing="0">  
		
	 <tr>
		 <td colspan="3">
			<table width="100%" border="0" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="2"><strong
						class="text_head">Travel Application </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			 
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
	 </tr>
			 
		 <tr>
	          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
	            <tr>
	              
	                      <tr>
	                        <td height="27" class="formtxt"> <a href="TravelApplication_callStatus.action?status=N">New Requisition</a> | <a href="TravelApplication_callStatus.action?status=P">Pending List</a> |  <a href="TravelApplication_callStatus.action?status=A">Approved List</a> |  <a href="TravelApplication_callStatus.action?status=R">Rejected List</a> </td>
	                      </tr><s:hidden  name="apprflag"/><s:hidden name="listLength" />  
	                        <s:hidden name="levelApp" />  <s:hidden name="appStatus" /> 
	                       <%String status = (String)request.getAttribute("status"); %>
	                           <s:hidden name="pageStatus" value="<%=status%>" />   
	            </tr>
	            </table>            
	          <label></label>
	          <s:hidden name="empName" />
								<s:hidden name="travelRequest" /> 
								<s:hidden name="appDate" /> 
								<s:hidden name="hidStatus" />
								<s:hidden name="trAppId" /> 
								<s:hidden name="branchName" /> 
								<s:hidden name="department" /> 
								<s:hidden name="designation" />
								 <s:hidden name="empId" />  
								 <s:hidden name="empGrade" /> 
								<s:hidden name="empGradeId" />
								 <s:hidden name="empDob" /> 
								  <s:hidden name="empToken" /> 
								   <s:hidden name="loadFlag" />   <s:hidden name="addNewFlag" />
			<s:hidden name="editFlag" />   <s:hidden name="saveFlag" /> <s:hidden name="hidFlag" />
		
	          
	          </td>
	        </tr>  
 
          <tr>
          <td colspan="3"> 
           <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg" class="sortable"  >
                  <tr>
                    <s:if test="%{reqNew}"><td height="27" class="formtxt"><strong>New Requisition</strong></td></s:if>
                    <s:elseif test="%{pen}"><td height="27" class="formtxt"><strong>Pending List</strong></td></s:elseif>
                    <s:elseif test="%{app}"><td height="27" class="formtxt"><strong>Approved List</strong></td></s:elseif>
                    <s:elseif test="%{rej}"><td height="27" class="formtxt"><strong>Rejected List</strong></td></s:elseif>
                     <s:else><td height="27" class="formtxt"><strong>New Requisition</strong></td></s:else>
                  </tr>                   
                  <tr>
                    
                     <s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
					<%try
					{
						int totalPage = (Integer) request.getAttribute("totalPage");
						int pageNo = (Integer) request.getAttribute("pageNo");
					%>
                       
						<tr>
							<td width="100%" align="right">    <s:if test="noData"> </s:if><s:else> <b>Page:</b></s:else>
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
					   
	  
                  <tr>
                    <td width="100%"><s:hidden name = "travelViewNo"/>    
                      <table width="100%" border="0" cellpadding="1" cellspacing="1"   class="formbg" >
                        <tr >
                      		 <td width="5%" valign="top" class="formth">Sr.No.</td>
                        	<td width="40%" valign="top" class="formth">Employee Name</td>
                          <td width="40%" valign="top" class="formth">Travel Request Name</td>
                          <td nowrap="nowrap"  valign="top" class="formth">Application Date </td>     
                        </tr>
                        <s:if test="noData">
			                <tr>
			                  <td width="100%" colspan="8" align="center"><font color="red">No Data To Display</font></td>
			                </tr>
	                    </s:if>
                        <%! int i = 0 ,count =0; %>
                        <% int k = 1; %>
                        	 <script>
		   function disp(id,empId)
			 {
		          document.getElementById('paraFrm_trAppId').value=id;
		          document.getElementById('paraFrm_empId').value=empId;
			     document.getElementById('paraFrm').action = "TravelApplication_callAppDtl.action";
				 document.getElementById('paraFrm').submit();
				 document.getElementById('paraFrm').target ="main"; 
			 }
			 
	  </script>
				       <s:iterator value="statusList"> 
					         <tr  <%if(count%2==0){
									%> class="tableCell1" <%}else{%>
									class="tableCell2" <%	}count++; %>
									onmouseover="javascript:newRowColor(this);" 
									onmouseout="javascript:oldRowColor(this,<%=count%2 %>);" 
										ondblclick="javascript:disp('<s:property value="itTrAppId" />','<s:property value="itEmpId" />');" 	  >
							      <td class="sortableTD" width="5%"> <s:property value="serialNo"/><s:hidden name="itEmpId"/> 
							       <s:hidden name="itAppDate"/>    <s:hidden name="itHidStatus"/>   <s:hidden name="itStatus"/> 
							            <s:hidden name="itTrAppId"/> <s:hidden name="level"/> <s:hidden name="serialNo"/>
							            <input type="hidden" name="itEmpName" id ="itEmpName<%=k%>" value='<s:property value="itEmpName"/>'/> 
							             <input type="hidden" name="itRequestName" id ="itRequestName<%=k%>" value='<s:property value="itRequestName"/>'/>
							               </td>
							      <td class="sortableTD" width="22%"><s:property value="itEmpName"/></td>
							       <td class="sortableTD" width="22%"> <s:property value="itRequestName"/> </td>  	  	
						           <td class="sortableTD" width="15%"><s:property value="itAppDate"/></td>	    
					      
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
             
        </table>
      </td>
     
  </tr>   
 </table>
 </s:form>
			
	
	 <script>
 
	 
function addnewFun()
     {  
         document.getElementById('paraFrm').action = "TravelApplication_addNew.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="main"; 
    }
    
     function searchFun()
     {   
         
          callsF9(500,325,'TravelApplication_f9action.action'); 
		  
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
		var status = document.getElementById('paraFrm_pageStatus').value 
		document.getElementById('paraFrm').action = "TravelApplication_callStatus.action?status="+status;
		document.getElementById('paraFrm').submit();
	}
 
    
    function newRowColor(cell){
			cell.className='onOverCell';

}
	
function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}else {
	cell.className='tableCell1';
		}
}
	function callForEdit()
	{
	   
	}


	 </script>