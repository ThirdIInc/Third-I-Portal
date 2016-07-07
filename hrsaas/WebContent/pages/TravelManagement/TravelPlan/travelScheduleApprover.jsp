
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="TravelSchAppr" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<s:hidden name="noData" />
	<s:hidden name="stat" />
	<s:hidden name="trvAppId" /> 
    <s:hidden name="schAppComment" /> 
    
    
	<s:hidden name="altSchEmpToken" />	<s:hidden name="altSchEmpName" />	<s:hidden name="altSchEmpId" />
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">



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
						class="text_head">Travel Schedule Approver</strong></td>
				</tr>
			</table>
			</td>
		</tr>









		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class=formbg>
				<tr>
					<td height="27" class="formtxt"><a
						href="TravelSchAppr_callStatus.action?status=P">Pending List</a> |
					<a href="TravelSchAppr_callStatus.action?status=A">Approved
					List</a> | <a href="TravelSchAppr_callStatus.action?status=R">Rejected
					List</a></td>
				</tr>
			</table>
			</td>

		</tr>





		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><img
						src="../pages/images/recruitment/space.gif" width="5" height="4" /><br />
					<s:if test="%{apprflag}"></s:if><s:else>
						<input name="Submit222" type="button" class="save"
							value="Save " onclick="return  saveValidate();" />
					</s:else></td>

					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>





		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class=formbg>
				<tr>

					<s:if test="%{pen}">
						<td height="27" class="formtxt"><strong>Pending List</strong></td>
					</s:if>

					<s:elseif test="%{apprved}">
						<td height="27" class="formtxt"><strong> Approved
						List</strong></td>
					</s:elseif>
					<s:elseif test="%{regcted}">
						<td height="27" class="formtxt"><strong>Rejected
						List</strong></td>
					</s:elseif>

					<s:else>
						<td height="27" class="formtxt"><strong>Pending List</strong></td>
					</s:else>


				</tr>

				<tr>
					<td width="100%" colspan="5"><s:hidden name="hdPage"
						id="hdPage" value="%{hdPage}" /> <%
 	int totalPage = (Integer) request.getAttribute("totalPage");
 	int pageNo = (Integer) request.getAttribute("pageNo");
 %>


					<table width="100%" class="formbg">
						<tr>
							<td width="98%" align="right">    <s:if test="noData"> </s:if><s:else> <b>Page:</b></s:else>
							<%
							if (pageNo != 1) {
							%> <a href="#" onclick="callPage('1');"> <img
								src="../pages/common/img/first.gif" width="10" height="10"
								class="iconImage" /> </a>&nbsp; <a href="#" onclick="callPage('P')">
							<img src="../pages/common/img/previous.gif" width="10"
								height="10" class="iconImage" /> </a> <%
 	}
 	if (totalPage <= 5) {
 		if (totalPage == 1) {
 %> <b><u><%=totalPage%></u></b> <%
 			} else {
 			for (int z = 1; z <= totalPage; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> <a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	} else {
 		if (pageNo == totalPage - 1 || pageNo == totalPage) {
 			for (int z = pageNo - 2; z <= totalPage; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (pageNo <= 3) {
 			for (int z = 1; z <= 5; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		} else if (pageNo > 3) {
 			for (int z = pageNo - 2; z <= pageNo + 2; z++) {
 		if (pageNo == z) {
 %> <b><u><%=z%></u></b> <%
 } else {
 %> &nbsp;<a href="#" onclick="callPage('<%=z%>');"><%=z%></a> <%
 			}
 			}
 		}
 	}
 	if (!(pageNo == totalPage)) {
 		if (totalPage == 0) {
 		} else {
 %> ....<%=totalPage%> <a href="#" onclick="callPage('N')"> <img
								src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>');"> <img
								src="../pages/common/img/last.gif" width="10" height="10"
								class="iconImage" /> </a> <%
 	}
 	}
 %>
							</td>
						</tr>
					</table>
					</td>
				</tr>







				<!-- iterator -->


	 



				<tr>
					<td width="100%">
					<table width="100%" cellpadding="1" cellspacing="1" class="formbg">
						<tr>
							<td width="5%" valign="top" class="formth"><label class="set" id="tms.trvlScheduleSrNo"	name="tms.trvlScheduleSrNo" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlScheduleSrNo")%></label></td>
							<td width="22%" valign="top" class="formth"><label class="set" id="employee"	name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
							<td width="22%" valign="top" class="formth"><label class="set" id="tms.trvlTrvlReqName"	name="tms.trvlTrvlReqName" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlTrvlReqName")%></label></td>
							<td width="15%" valign="top" class="formth"><label class="set" id="tms.trvlAppDate"	name="tms.trvlAppDate" ondblclick="callShowDiv(this);"><%=label.get("tms.trvlAppDate")%></label></td>
							<td width="10%" valign="top" class="formth"><label class="set" id="tms.Status"	name="tms.Status" ondblclick="callShowDiv(this);"><%=label.get("tms.Status")%></label></td>
   							 <s:if test="trvlSchlApr.apprflag"> 
   							 </s:if>
   							 <s:else> <td width="10%" valign="top" class="formth"> <label class="set" id="tms.forward"	name="tms.forward" ondblclick="callShowDiv(this);"><%=label.get("tms.forward")%></label></td></s:else>
  							<td width="10%" valign="top" class="formth"><label class="set" id="tms.ViewDtls"	name="tms.ViewDtls" ondblclick="callShowDiv(this);"><%=label.get("tms.ViewDtls")%></label></td>
							<td width="25%" valign="top" class="formth"><label class="set" id="tms.Comments"	name="tms.Comments" ondblclick="callShowDiv(this);"><%=label.get("tms.Comments")%></label></td>
						</tr>



						<s:if test="noData">
							<tr>
								<td width="100%" colspan="8" align="center"><font
									color="red">No Data To Display</font></td>
							</tr>
						</s:if>




						<%!int i = 0;%>
						<%
						int k = 1;
						%>




						<s:iterator value="travelSchApprList">
							<tr>
								<td class="sortableTD" width="5%"><%=k%><s:hidden
									name="travelEmpId" /> <s:hidden name="ForwardFlag" />  <s:hidden name="appDate" /> <s:hidden
									name="travelAppId" /> <input type="hidden" name="empName"
									id="empName<%=k%>" value='<s:property value="empName"/>' /> <input
									type="hidden" name="trRequestName" id="trRequestName<%=k%>"
									value='<s:property value="trRequestName"/>' /></td>
								<td class="sortableTD" width="22%"><s:property
									value="empName" />&nbsp;</td>

								<td class="sortableTD" width="22%"><s:property
									value="trRequestName" />&nbsp;</td>

								<td class="sortableTD" width="15%"><s:property
									value="appDate" />&nbsp;</td>

								<td align="center" width="10%" class="sortableTD"><s:hidden
									name="trvlSchlApr.apprflag" /> <s:if
									test="trvlSchlApr.apprflag">
									<s:property value="apprStatus" />&nbsp;
								</s:if> <s:else>

									<s:select name="checkStatus" id="<%="check"+k %>"
										  theme="simple"
										list="#{'P':'Pending','A':'Approved','R':'Rejected'}" />
								</s:else></td>
								    <s:if test="trvlSchlApr.apprflag"> 
   								    </s:if><s:else> 
									<td  class="sortableTD" > <s:if test="ForwardFlag"> <input type="button" value="Forward" onclick="callForward('<s:property value="travelAppId"/>');"> </s:if>  </td>
							 	   </s:else>   
								<td class="sortableTD" nowrap="nowrap" align="center"><input
									type="button" name="view_Details" class="token" value=" View "
									onclick="viewDetails('<s:property value="travelAppId"/>')" />
								</td>
						


								<td class="sortableTD" width="20%"><s:if
									test="trvlSchlApr.apprflag">
									<s:property value="comment" />&nbsp;
								</s:if> <s:else>

									<s:textarea name="comment" id="<%="comment"+k %>"  value="%{comment}" rows="1"
										cols="20">

									</s:textarea>
								</s:else></td>
								<script>
								
								</script>

							</tr>
							<%
							k++;
							%>
						</s:iterator>
						<%
						i = k;
						%>
					</table>
					</td>

				</tr>


				<!-- iterator -->



				<tr>
					<td colspan="3"><s:if test="%{apprflag}"></s:if><s:else>
						<input name="Submit222" type="button" class="save"
							value="Save " onclick="return saveValidate();" />
					</s:else></td>
				</tr>

			</table>
			</td>
		</tr>
	</table>


</s:form>



<script>
	
	
	function callSchedule(Id){ 
	document.getElementById('paraFrm_trvAppId').value = Id;
	document.getElementById('paraFrm').action = "TrvlSchl_callSchedule.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "";
		 
	}
	
	
	function callView(Id){ 	
	//alert(Id);
	document.getElementById('paraFrm_trvAppId').value = Id;
	//alert(document.getElementById('paraFrm_trvAppId').value);
	document.getElementById('paraFrm').action = "TrvlSchl_callSchedule.action";
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = "";
		 
	}
	
	
	
	
	
	
	
	
	
	function callForward(id){   
		var conf=confirm("Do you really want to forward the application ?");
          if(conf){   
          var schApprCom = document.getElementById('comment'+id).value;
		document.getElementById('paraFrm_trvAppId').value = id; 
	 	document.getElementById('paraFrm_schAppComment').value = schApprCom;  
		   callsF9(500,325,'TravelSchAppr_f9AlterSchApproval.action?trvAppId='+id);   
	}
  }
	</script>





<script>
	 
	
	

	
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
		document.getElementById('paraFrm').action = "TravelSchAppr_callStatus.action";
		document.getElementById('paraFrm').submit();
	}
</script>


<script>
	function saveValidate(){    
	
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
	  else
	  {
	   document.getElementById("paraFrm").action="TravelSchAppr_save.action";
  	   document.getElementById("paraFrm").submit();  
	  }
   
   }   
   
  else
  {
  document.getElementById("paraFrm").action="TravelSchAppr_save.action";
  document.getElementById("paraFrm").submit();  
  }
  
 }  



function viewDetails(travelAppId,level,empId){   
	  
		document.getElementById('paraFrm_trvAppId').value = travelAppId; 
		//alert(document.getElementById('paraFrm_trvAppId').value);
		//document.getElementById('paraFrm_appStatus').value ="A"; 
		document.getElementById('paraFrm').action = "TravelSchAppr_callView.action";
	   // document.getElementById('paraFrm').action="TrvlSchl_callSchedule.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		 

}

</script>
