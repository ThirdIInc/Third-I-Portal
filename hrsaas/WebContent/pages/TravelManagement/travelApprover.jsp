
<%@ taglib prefix="s" uri="/struts-tags"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript"> 
	var jourApp = new Array(); 
</script>


<s:form action="TravelAppr" validate="true" id="paraFrm" validate="true"
	theme="simple">
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
						class="text_head">Travel Approval </strong></td>
				</tr>
			</table>
			</td>
		</tr>





		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>

					<td height="27" class="formtxt"><a
						href="TravelAppr_callStatus.action?status=P">Pending List</a> | <a
						href="TravelAppr_callStatus.action?status=A">Approved List</a> | <a
						href="TravelAppr_callStatus.action?status=R">Rejected List</a></td>
				</tr>
				<s:hidden name="apprflag" />
				<s:hidden name="listLength" />

			</table>
			</td>
		</tr>



		<tr>
			<td>
			<s:if test="%{apprflag}"></s:if><s:else>
				<input name="Submit222" type="button" class="save" value="    Save "
					onclick="return saveValidate();" />
			</s:else></td>
			<td width="22%">
			<div align="right"><span class="style2"><font
				color="red">*</font></span> Indicates Required</div>
			</td>
			<s:hidden name="status" />
		</tr>





		<tr>
			<td colspan="3">

			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class=formbg>
				<tr>
					<s:if test="%{pen}">
						<td height="27" class="formtxt"><strong>Pending List</strong></td>
					</s:if>
					<s:elseif test="%{app}">
						<td height="27" class="formtxt"><strong>Approved
						List</strong></td>
					</s:elseif>
					<s:elseif test="%{rej}">
						<td height="27" class="formtxt"><strong>Rejected
						List</strong></td>
					</s:elseif>

					<s:else>
						<td height="27" class="formtxt"><strong>Pending List</strong></td>
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
							<td width="98%" align="center">
							<%	if(pageNo != 1)
								{
							%> <a href="#" onclick="callPage('1');"> <img
								src="../pages/common/img/first.gif" width="10" height="10"
								class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P')">
							<img src="../pages/common/img/previous.gif" width="10"
								height="10" class="iconImage" /> </a> <%	}
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
					<td><s:hidden name="travelViewNo" />
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="sortable">
						<tr>
							<td width="5%" valign="top" class="formth">Sr.No.</td>
							<td width="12%" valign="top" class="formth">Employee ID</td>
							<td width="22%" valign="top" class="formth">Employee Name</td>
							<td width="15%" valign="top" class="formth">Application Date</td>
							<td width="10%" valign="top" class="formth">Status</td>
							<td width="15%" valign="top" class="formth">View Details</td>
							<td width="25%" valign="top" class="formth">Remarks</td>
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
								<td class="border2" width="5%"><%=k %><s:hidden
									name="travelEmpId" /> <s:hidden name="appDate" /> <s:hidden
									name="travelAppId" /> <s:hidden name="level" /> <input
									type="hidden" name="empName" id="empName<%=k%>"
									value='<s:property value="empName"/>' /> <input type="hidden"
									name="travelEmpToken" id="travelEmpToken<%=k%>"
									value='<s:property value="travelEmpToken"/>' /> <input
									type="hidden" name="gradeJourneyId" id="gradeJourneyId<%=k%>"
									value='<s:property value="gradeJourneyId"/>' /> <input
									type="hidden" name="gradeClassId" id="gradeClassId<%=k%>"
									value="<s:property value="gradeClassId"/>" /> <input
									type="hidden" name="alertFlag" id="alertFlag<%=k%>"
									value="<s:property value="alertFlag"/>" /> <input
									type="hidden" name="alertMode" id="alertMode<%=k%>"
									value="<s:property value="alertMode"/>" /> <input
									type="hidden" name="alertShowFlag" id="alertShowFlag<%=k%>"
									value="<s:property value="alertShowFlag"/>" /></td>
								<td class="border2" width="12%"><s:property
									value="travelEmpToken" /></td>
								<td class="border2" width="22%"><s:property value="empName" /></td>
								<td class="border2" width="15%"><s:property value="appDate" /></td>
								<td align="center" width="10%" class="border2"><s:if
									test="traApp.apprflag">
									<s:property value="statusNew" />
								</s:if> <s:else>
									<s:select name="checkStatus" id="<%="check"+k %>"
										cssStyle="width:100" theme="simple"
										list="#{'P':'Pending','A':'Approved','R':'Rejected'}" />
								</s:else></td>
								<td class="border2" width="15%" align="center"><input
									type="button" name="view_Details" class="token" value="Details"
									onclick="viewDetails('<s:property value="travelAppId"/>')" />
								</td>
								<td class="border2" width="20%"><s:if
									test="traApp.apprflag">
									<s:property value="remark" />
								</s:if><s:else>
									<s:textarea name="remark" value="%{remark}" rows="1" cols="20"></s:textarea>
								</s:else></td>
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

				
				
				
			</table>
			</td>
		</tr>
		<tr>
					<td colspan="3"><s:if test="%{apprflag}"></s:if><s:else>
						<input name="Submit222" type="button" class="save"
							value="    Save " onclick="return saveValidate();" />
					</s:else></td>
				</tr>
		
	</table>
	
</s:form>

<script>
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
		
		if(document.getElementById("check"+i).value=="A")
			{  
			//  var empId = document.getElementById("travelEmpToken"+i).value; 
			if(document.getElementById("alertShowFlag"+i).value=="true")
			{  
			  var empName = document.getElementById("empName"+i).value;  
			  var alertFlag =  document.getElementById("alertFlag"+i).value;  
			  var alertMode =  document.getElementById("alertMode"+i).value;  
			  if(alertFlag=="true")
			  {
		       var conf = confirm("Applicant No. "+i+" "+empName+" is not entitled to traveling by \n"+alertMode+" . \n Do you really want to approve the application ? ");
			     if(conf)
			    {
			    
			    }else
			    {
			     return false ;
			    }
			   } 
			    } 
			  }//end of if 
		    } //end of for 
  document.getElementById("paraFrm").action="TravelAppr_save.action";
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
	function viewDetails(travelAppId){ 
		document.getElementById('paraFrm_travelViewNo').value = travelAppId;
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "TravelAppr_callView.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "";
		 
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
		document.getElementById('paraFrm').action = "TravelAppr_callStatus.action";
		document.getElementById('paraFrm').submit();
	}
</script>