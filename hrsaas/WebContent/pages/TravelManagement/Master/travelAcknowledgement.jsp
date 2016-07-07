
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript"> 
	<!--var jourApp = new Array(); -->
</script>
<s:form action="TravelAcknowledgement" validate="true" id="paraFrm"
	validate="true" theme="simple">
<table width="100%" border="0" align="right" class="formbg"	cellpadding="0" cellspacing="0">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="98%" class="txt"><strong class="text_head">Travel
					Acknowledgement  </strong></td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="99%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td height="27" class="formtxt"><a
										href="TravelAcknowledgement_callStatus.action?status=P&chkFlag=true">Pending
									List</a> | <a
										href="TravelAcknowledgement_callStatus.action?status=A&chkFlag=true">Accepted
									List</a> | <a
										href="TravelAcknowledgement_callStatus.action?status=R&chkFlag=true">Rejected
									List</a> |<a
										href="TravelAcknowledgement_callStatus.action?status=A&chkFlag=false">Cancelation
									Approval</a> |<a
										href="TravelAcknowledgement_callStatus.action?status=R&chkFlag=false">
									Cancelation Rejected </a></td>
								</tr>
								<s:hidden name="apprflag" />
								<s:hidden name="listLength" />
								<s:hidden name="trAppId" />
								<s:hidden name="levelApp" />
								<s:hidden name="empId" />
								<s:hidden name="chkFlag" />
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td width="78%"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /><br />
					<s:if test="%{apprflag}"></s:if><s:else>
						<input name="Submit" type="button" class="save" value=" Save "
							onclick="return saveValidate1();" />
					</s:else></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
					<s:hidden name="status" />
				</tr>
			</table>
			<label></label></td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td>
					<table width="99%" border="0" align="center" cellpadding="1"
						cellspacing="1">
						<tr>
							<s:if test="%{pen}">
								<td height="27" class="formtxt"><strong>Pending
								List</strong></td>
							</s:if>
							<s:elseif test="%{app}">
								<td height="27" class="formtxt"><strong>Accepted
								List</strong></td>
							</s:elseif>
							<s:elseif test="%{rej}">
								<td height="27" class="formtxt"><strong>Rejected
								List</strong></td>
							</s:elseif>
							<s:elseif test="%{cansApp}">
								<td height="27" class="formtxt"><strong>Cancelation
								Approval</strong></td>
							</s:elseif>
							<s:elseif test="%{cansRej}">
								<td height="27" class="formtxt"><strong>Cancelation
								Rejected</strong></td>
							</s:elseif>
							<s:else>
								<td height="27" class="formtxt"><strong>Pending
								List</strong></td>
							</s:else>
						</tr>
						<tr>
							<td width="100%" colspan="5"><s:hidden name="hdPage"
								id="hdPage" value="%{hdPage}" /> <%try
					{
						int totalPage = (Integer) request.getAttribute("totalPage");
						int pageNo = (Integer) request.getAttribute("pageNo");
					%>
							<table width="98%">
								<tr>
									<td width="98%" align="right">
									<s:if test="noData">
									</s:if>
									<s:else>Page :</s:else>
									
									
									
									<%	if(pageNo != 1)
								{
							%> <a href="#" onclick="callPage('1');"> <img
										src="../pages/common/img/first.gif" width="10" height="10"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('P')"> <img
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <%	}
								if(totalPage <= 5)
								{
									if(totalPage == 1)
									{
							%> <b><u><%=totalPage%></u></b> <%		}
									else
									{
										for(int z = 1; z <= totalPage; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
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
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}
									}
									else if(pageNo <= 3)
									{
										for(int z = 1; z <= 5; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}						
									}
									else if(pageNo > 3)
									{
										for(int z = pageNo - 2; z <= pageNo + 2; z++)
										{
											if(pageNo == z)
											{
							%> <b><u><%=z%></u></b> <%				}
											else
											{
							%> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%				}
										}					
									}
								}
								if(!(pageNo == totalPage))
								{
									if(totalPage == 0){}
									else
									{
							%> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img
										src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
									<a href="#" onclick="callPage('<%=totalPage%>');"> <img
										src="../pages/common/img/last.gif" width="10" height="10"
										class="iconImage" /> </a> <%		}
								}
							%>
									</td>
								</tr>
							</table>
							</td>
						</tr>

						<tr>
							<td width="100%" colspan="5"><s:hidden name="travelViewNo" />
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<tr>
									<td width="5%" valign="top" class="formth"><label
										class="set" id="sr.no" name="sr.no"
										ondblclick="callShowDiv(this);"><%=label.get("sr.no")%></label></td>
									<td width="22%" valign="top" class="formth"><label
										class="set" id="emp.name" name="emp.name"
										ondblclick="callShowDiv(this);"><%=label.get("emp.name")%></label></td>
									<td width="22%" valign="top" class="formth"><label
										class="set" id="travel.name" name="travel.name"
										ondblclick="callShowDiv(this);"><%=label.get("travel.name")%></label></td>
									<td width="15%" valign="top" class="formth"><label
										class="set" id="date" name="date"
										ondblclick="callShowDiv(this);"><%=label.get("date")%></label>
									</td>
									<td width="10%" valign="top" class="formth"><label
										class="set" id="status1" name="status"
										ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
									<td width="15%" valign="top" class="formth"><label
										class="set" id="detail" name="detail"
										ondblclick="callShowDiv(this);"><%=label.get("detail")%></label></td>
									<td width="25%" valign="top" class="formth"><label
										class="set" id="comment" name="comment"
										ondblclick="callShowDiv(this);"><%=label.get("comment")%></label></td>
								</tr>
								<s:if test="noData">
									<tr>
										<td width="100%" colspan="8" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
								<%! int i = 0 ; %>
								<% int k = 1; %>

								<s:iterator value="travelList">
									<tr>
										<td width="5%" class="sortableTD"><%=k %><s:hidden
											name="travelEmpId" /> <s:hidden name="appDate" /> <s:hidden
											name="empName" /> <s:hidden name="travelAppId" /> <s:hidden
											name="trRequestName" /> <s:hidden name="level" /> </td>
										<s:hidden name="testFlag" />
										<td width="22%" class="sortableTD"><s:property
											value="empName" /></td>
										<td width="22%" class="sortableTD"><s:property
											value="trRequestName" /></td>
										<td width="15%" class="sortableTD"><s:property
											value="appDate" /></td>
										<td align="center" width="10%" class="sortableTD"><s:if
											test="testFlag">
											<s:property value="statusNew" />
										</s:if> <s:else>
											<s:select name="checkStatus" id="<%="check"+k %>"
												cssStyle="width:100" theme="simple"
												list="#{'P':'Pending','A':'Accepted','R':'Rejected'}" />

										</s:else></td>
										<td width="10%" align="center" class="sortableTD"><input
											type="button" name="view_Details" class="token"
											value="Details"
											onclick="viewDetails('<s:property value='travelEmpId'/>','<s:property value='travelAppId'/>')" />
										</td>
										<td width="20%" class="sortableTD"><s:if
											test="travelAcknowledgement.apprflag">
											<s:property value="comment" />
										</s:if><s:else>
											<s:textarea name="comment" value="%{comment}" rows="1"
												cols="20"></s:textarea>
										</s:else></td>
						<script>
							//	jourApp[<%=k%>] = document.getElementById('paraFrm_appDate').value;
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

					</table>
					</td>
				</tr>
			</table>
		<tr>
			<td>
			<table border="0">
				<s:if test="%{apprflag}"></s:if>
				<s:else>
					<input name="Submit" type="button" class="save" value=" Save "
						onclick="return saveValidate1();" />
				</s:else>
			</table>
			</td>
		</tr>
		</td>
		</tr>

	</table>
</s:form>

<script>

	
	function saveValidate()
				{    
				
			     document.getElementById("paraFrm").action="TravelAcknowledgement_save.action";
			     document.getElementById("paraFrm").submit();  
			   
			    }
 function saveValidate1()
       {
         var rowCount = <%=i%>-1; 
	     if(rowCount==0)
			   {
			   alert("No records to save");
			   return false;
			   }  
	     else if(rowCount>0)
		      {
		        var recordFlag ="false";
		      for(var x=1; x<=rowCount;x++)
				   {  
					if(document.getElementById("check"+x).value=="A" || document.getElementById("check"+x).value=="R")
						{  		
						  recordFlag ="true";
						 
						}
			} //end of for
			if(recordFlag =="false")
			  {
			   alert("Please change the status of atleast one application!");
			   return false;
			  } 
			else{
			   	 document.getElementById("paraFrm").action="TravelAcknowledgement_save.action";
			             document.getElementById("paraFrm").submit(); 
			   	}
			   	}
			   	else{
			   	 document.getElementById("paraFrm").action="TravelAcknowledgement_save.action";
			             document.getElementById("paraFrm").submit(); 
			   	}
			   	}
 
function callOnload()
              {
	 	         var check =<%=i%>; 
	 		     if(document.getElementById("paraFrm_status").value=='A' || document.getElementById("paraFrm_status").value=='R'){
			     for(var k = 1;k <= check ;k++ ){
			     document.getElementById("check"+k).disabled=true;	
			  }
		     }
	        }
	
function viewDetails(travelEmpId,travelAppId)
		{ 		 
			document.getElementById('paraFrm').action ='TravelAcknowledgement_callView.action?empCode='+travelEmpId+'&appCode='+travelAppId; 
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
				document.getElementById('paraFrm').action = "TravelAcknowledegment_callStatus.action";
				document.getElementById('paraFrm').submit();
			}
			
			
</script>