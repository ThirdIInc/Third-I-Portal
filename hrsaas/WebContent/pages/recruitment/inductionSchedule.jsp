<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="InductionSchedule" validate="true" id="paraFrm" theme="simple">
<s:hidden name="myPage" id="myPage" />

	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg"><!-- Final Table -->
		
		<tr>
	      <td colspan="3" width="100%">
	        <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt">
						<strong class="formhead">
							<img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" />
						</strong>
					</td>
					<td width="93%" class="txt">
						<strong class="text_head">Schedule Induction</strong>
					</td>
					<td width="3%" valign="top" class="txt">
					  <div align="right">
					  	<img	src="../pages/images/recruitment/help.gif" width="16" height="16" />
					  </div>  
					</td>
				</tr>
			</table>
		  </td>
		</tr>
		
		
		<tr>
          <td colspan="3" width="100%">
          		<table width="100%" border="0" cellpadding="0" cellspacing="0">
            	   <tr>
              		  <td width="78%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/></td>
              		  <td width="22%">&nbsp;</td>
            	  </tr>
               </table><label></label>
          </td>
        </tr>
        
				
		<tr><!-- tr option -->
			<td colspan="3" width="100%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"><!-- table option -->
					<tr>
						<td colspan="2">
							<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!-- table 1 -->
								<tr>
									<td>
										<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2"><!--Table 4-->
											<tr>
												<td height="27" class="formtxt">
													<a	href="#" onclick="callFun('N');">Induction Due List	</a> | 
													<a href="#" onclick="callScheduledInd('Y');">Schedule Induction	</a> 
												</td>
											</tr>
													<s:hidden name="listLength" />
													<s:hidden name="totalRecords"/> 
													<s:hidden name="modeLength"/>
													
										</table><!--Table 4-->
									</td>
								</tr>
							</table><!-- table 1 -->
						</td>
					</tr>
					
					<tr>
						<td colspan="3" width="100%">
							<table width="100%" border="0"  cellpadding="0" cellspacing="0" ><!--Table 3-->
								<tr>
									<td colspan="3" width="100%">
										<table width="100%" border="0"  cellpadding="1" cellspacing="1" class="sortable"><!--Table 5-->
											<tr>
												<td height="27" class="formtxt">
													<strong> 
														<% 
															String status = (String) request.getAttribute("stat");
															if (status != null) 
																{
												 					out.println(status);
												 				} 
															else
																{																	
																	out.println("Induction Due List");
												 				}
												 		%> 
												 	</strong>
								<!-- Add new paging -->
												 </td>												  		
														<% int totalPage = 0; 
															int pageNo = 0; 
														%>
												<td id="showCtrl" width="80%" align="right">
														
													<s:if test="modeLength"><b>Page:</b>
														<% 
												  			 totalPage = (Integer) request.getAttribute("totalPage");
														   	 pageNo = (Integer) request.getAttribute("PageNo");
														%>													
													<a href="#"	onclick="callPage('1', 'F', '<%=totalPage%>', 'InductionSchedule_input.action');">
														<img title="First Page" src="../pages/common/img/first.gif"	width="10" height="10" class="iconImage" /> 
													</a>&nbsp;
													<a href="#"	onclick="callPage('P', 'P', '<%=totalPage%>', 'InductionSchedule_input.action');">
														<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage" /> 
													</a>
													<input type="text" name="pageNoField" id="pageNoField" size="3"	value="<%=pageNo%>" maxlength="10" onkeypress="callPageText(event, '<%=totalPage%>', 'InductionSchedule_input.action');return numbersOnly();" /> of <%=totalPage%>
													<a href="#"	onclick="callPage('N', 'N', '<%=totalPage%>', 'InductionSchedule_input.action')">
														<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" /> 
													</a>&nbsp;
													<a href="#"	onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'InductionSchedule_input.action');">
														<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /> 
													</a>
													</s:if>
												</td>
												 
											</tr>
											<tr>
												<td colspan="3" width="100%">
													<table width="100%" border="0" cellpadding="1" cellspacing="1" class="sortable" id="tblStatus"><!--Table 6-->
														<tr>
															<td width="10%" valign="top" class="formth" nowrap="nowrap"><b> <label  class = "set" name="serial.no" id="serial.no" 
			            										ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
															<td width="30%" valign="top" class="formth"><b><label  class = "set" name="Employee.Name" id="Employee.Name" 
			            										ondblclick="callShowDiv(this);"><%=label.get("Employee.Name")%></label></b></td>
															<td width="15%" valign="top" class="formth"><b> <label  class = "set" name="division" id="division" 
			            										ondblclick="callShowDiv(this);"><%=label.get("division")%></label> </b> </td>
															<td width="15%" valign="top" class="formth"><b> <label  class = "set" name="branch" id="branch" 
			            										ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> </b> </td>
															<td width="20%" valign="top" class="formth" nowrap="nowrap"><b> <label  class = "set" name="department" id="department" 
			            										ondblclick="callShowDiv(this);"><%=label.get("department")%></label> </b></td>
															<td width="15%" valign="top" class="formth" nowrap="nowrap"><b> <label  class = "set" name="doj" id="doj" 
			            										ondblclick="callShowDiv(this);"><%=label.get("doj")%></label></b> </td>
															<td width="5%" valign="top" class="formth" abbr="right">
																<input class="checkbox"  type="checkbox" size="2" name="chkAll" id="chkAll" onclick="return callChkAll();">							
															</td>
														</tr>
														
														<%!int i = 0;%>														
														<%! int c=0; %>							
							  							<%int j=1; %>
							  							
							  								<%
																int cnt = pageNo * 20 - 20;
																int m = 0;
															%>
																<s:iterator value="employeeList">
								                        	<tr>
								                        		<td width="10%" class="sortableTD" align="center"><%=++cnt%><%	++m;%></td>
								                        		<td width="30%" class="sortableTD">&nbsp;<s:property value="employeeName"/>
								                        			<s:hidden name="employeeName" id='<%="employeeName"+j%>'/><s:hidden name="employeeCode" id='<%="employeeCode"+j%>'/></td>
								                        		<td width="15%" class="sortableTD">&nbsp;<s:property value="divisionName"/>
								                        			<s:hidden name="divisionName" id='<%="divisionName"+j%>'/><s:hidden name="divisionCode" id='<%="divisionCode"+j%>'/></td>
								                        		<td width="15%" class="sortableTD">&nbsp;<s:property value="branchName"/>
								                        			<s:hidden name="branchName" id='<%="branchName"+j%>'/><s:hidden name="branchCode" id='<%="branchCode"+j%>'/></td>
								                        		<td width="20%" class="sortableTD">&nbsp;<s:property value="deptName"/>
								                        			<s:hidden name="deptName" id='<%="deptName"+j%>'/><s:hidden name="deptCode" id='<%="deptCode"+j%>'/></td>
								                        		<td width="15%" class="sortableTD">&nbsp;<s:property value="dateOfJoining"/>
								                        			<s:hidden name="dateOfJoining" id='<%="dateOfJoining"+j%>'/></td>				
								                        		<td width="5%" class="sortableTD">
								                        			 <input type="hidden" name="hresumeChk" id="<%=j%>"  value="N"/>      
                   													 <input type="checkbox" class="checkbox" value="N" name="resumeChk" id="resumeChk<%=j%>"  onclick="callChk('<%=j%>')" />
								                        		</td>
								                        	</tr>
								                        <%j++;%>
													</s:iterator>
														  <%c=j;
														  c=m;
														  %>
													</table><!--Table 6-->
												 </td>
											</tr>		
										</table><!--Table 5-->
									</td>
								</tr>		
							</table><!--Table 3-->
						</td>
					</tr>		
				</table><!-- table option -->
			</td>
		</tr><!-- tr option -->		
		<tr>
          <td colspan="3" width="100%"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="78%">
              	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
              </td>
              <td width="22%" align="right">
              <s:if test="modeLength">
				<b> Total No. of Records:&nbsp;</b>
				<s:property value="totalRecords" />
			</s:if>
			</td>
            </tr>
          </table><label></label></td>
        </tr>
        
	</table><!-- Final Table -->	
	<s:hidden name="inductionName"/><s:hidden name="inductionCode"/><s:hidden name="inductionFrom"/><s:hidden name="inductionTo"/>
</s:form>

<script>
	function callFun(status)
	{                     /*status is kept 'N'....it is for INT_CONDUCT_STATUS = 'N'*/	
		try
		{
			document.getElementById('paraFrm').target="main";
			document.getElementById("myPage").value="";
	    	document.getElementById("paraFrm").action='InductionSchedule_getInducDueList.action?status='+status;
	    	document.getElementById("paraFrm").submit();
	    }
	    catch(e)
	    {
	    	alert("Error : "+e);
	    }	
	}
	
	function addnewFun(){
		document.getElementById("paraFrm").action="InductionSchedule_addNew.action";
	    document.getElementById("paraFrm").submit();
	}
	
	function callChkAll()  {
	 
		var tbl = document.getElementById('tblStatus');
		var rowLen = tbl.rows.length;
		
		if(document.getElementById('chkAll').checked){
		
		 for(i = 1; i < rowLen; i++){
		 
		 	  document.getElementById('resumeChk'+i).checked = true;
			  document.getElementById(i).value="Y";
		  }
			
	    }else{
	    
	     for(i = 1; i < rowLen; i++){
			   document.getElementById('resumeChk'+i).checked =false;
			   document.getElementById(i).value="";
		 }
	  }	
	
  }
  
  function searchFun(){
		callsF9(500,325,'InductionSchedule_f9Search.action');
	}
	
	function callScheduledInd(status)
	{
		try
		{
		document.getElementById('paraFrm').target="main";
		document.getElementById('myPage').value="";
	    document.getElementById("paraFrm").action='InductionSchedule_getScheduledInduction.action?status='+status;
	    document.getElementById("paraFrm").submit();
		}
		catch(e)
		{
			alert("e------"+e);
		}
		
	}
	



</script>
