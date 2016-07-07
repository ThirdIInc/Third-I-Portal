<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="PrejoiningActivitiesMonitor" validate="true"
	id="paraFrm" theme="simple">
	<s:hidden name="bgStatus" />
	<s:hidden name="prejoinCode" />
	<s:hidden name="candidateCode" />
	<s:hidden name="candidateName" />
	<s:hidden name="reqName" />
	<s:hidden name="reqCode" />
	<s:hidden name="checkListType" />
	<s:hidden name="checkListType" />
	<s:hidden name="DupcheckListType" />
	<s:hidden name="DupcheckListresponce" />
	<s:hidden name="offerDate" />
	<s:hidden name="joinDate" />
	<s:hidden name="reportingName" />
	<s:hidden name="reportingTo" />
	<s:hidden name="Appstatus" />
	<s:hidden name="show" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="bgcheckCode" />
	<s:hidden name="conduct"></s:hidden>
	<s:hidden name="bgpendinglistLength" />
	<s:hidden name="modeLength" />
	<s:hidden name="totalRecords" />
	<s:hidden name="positionId" />
	<s:hidden name="candCode1" />
	<s:hidden name="ChkSerch" />
	<s:hidden name="searchFlag" />
	<s:hidden name="LChkListLength" />	

<s:hidden name="applyFilterFlag"/>


	<table width="100%" 
		class="formbg">


		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Pre
					Joining Activity Monitoring</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
					<tr>
					<td align="left"><input type="hidden" name="noofrecords"
						id="noofrecords" value="<%=i-1%>" /> <s:hidden name="Chkreqcode"></s:hidden>
					<s:hidden name="Chkcandidatecode"></s:hidden> <s:hidden
						name="Chkoffercode"></s:hidden> <s:hidden name="conduct"></s:hidden>
					<s:if test="conduct">
						<s:submit cssClass="save" value=" Save"
							action="PrejoiningActivitiesMonitor_monitorsave"
							onclick="return Conducted();" />

					</s:if><s:else>

					</s:else></td>
				</tr>
		
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="30%" height="27" class="formtxt"><a href="#"
						onclick="callFun('P');">Pending List </a> | <a href="#"
						onclick="callFun('C');">Completed List </a></td>
					</tr></table></td></tr>
					
		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" cellspacing="0" class="formbg">
				<tr>

					<td colspan="2"nowrap="nowrap"><strong class="text_head"><s:if
						test="searchFlag">Applied Filter</s:if><s:else><label  class = "set" name="searchApply.filter" id="searchApply.filter" 
			            		ondblclick="callShowDiv(this);"><%=label.get("searchApply.filter")%></label></s:else></strong></td>

					<td id="showFilter" align="right" colspan="2"><input
						type="button" value="Show Filter" cssClass="token"
						onclick="return callShowFilter();"></td>

					<td id="hideFilter" align="right"><input type="button"
						value="Hide Filter" cssClass="token"
						onclick="return callHideFilter();"></td>
				</tr>

				<tr>
					<td colspan="4" width="100%">
					<div id="showFilterValue">
					<table  width="100%" border="0">

						<tr>
							<td width="18%"><label class="set" name="cand.name"
								id="cand.name" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
							:</td>
							<td width="25%"nowrap="nowrap"><s:textfield name="candidateName1"
								size="25" maxlength="50" onkeypress="return charactersOnly();" />
							<img class="iconImage"
								src="../pages/images/recruitment/search2.gif" width="15"
								height="16"
								onclick="javascript:callsF9(500,325,'PrejoiningActivities_f9CandidateAction.action');" />
							</td>

							<td width="14%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

							<td width="18%"><label class="set" name="position"
								id="position1" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:textfield name="positionName" size="25"
								readonly="true" /><img src="../pages/images/search2.gif"
								class="iconImage" height="16" align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'PrejoiningActivities_f9Position.action');"></td>
						</tr>

						<tr>
							<td width="18%"><label class="set" name="tarfDate"
								id="tarfDate" ondblclick="callShowDiv(this);"><%=label.get("tarfDate")%></label>
							:</td>
							<td width="25%" nowrap="nowrap"><s:textfield name="targetFDate" size="25"
								onkeypress="return numbersWithHiphen();" maxlength="10"
								readonly="false" /><s:a
								href="javascript:NewCal('paraFrm_targetFDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
							<td width="14%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
							<td width="18%"><label class="set" name="tartDate"
								id="tartDate" ondblclick="callShowDiv(this);"><%=label.get("tartDate")%></label> 
							:
							<td width="25%" nowrap="nowrap"><s:textfield name="targetTDate" size="25"
								onkeypress="return numbersWithHiphen();" maxlength="10"
								readonly="false" /><s:a
								href="javascript:NewCal('paraFrm_targetTDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
						</tr>


						<tr>
							<td width="100%" align="center" colspan="5"><s:submit
								cssClass="token" value=" Apply Filter  "
								onclick="return ApplyFilter();" />&nbsp; <input type="button"
								class="reset" theme="simple" onclick="return calReset();"
								value="Reset " /></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>

				<tr>
					<td width="100%">
					<% 
		     						    String [] dispArr = (String [])request.getAttribute("dispArr"); 
		     						  if(dispArr!=null && dispArr.length >0)
		     						  {
		     							 
		     						      int k =0;
		     						      int count =0;
		     						      if(dispArr.length % 2==0)
		     						      {
		     						    	   k =dispArr.length/2;
		     						    	  
		     						    	   
		     						      }else
		     						      {
		     						    	 k =(dispArr.length/2)+1;
		     						    	 
		     						      } 
		     						  %>

					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id="enableFilterValue">

						<% for(int m=0;m<k;m++) 
									    	  {%>

						<tr>
							<% if(count<dispArr.length){ %>

							<td width="50%" height="22" class="formtext"><%=dispArr [count]%>

							</td>
							<% count++;%>

							<%} %>


							<% if(count<dispArr.length){ %>
							<td width="50%" height="22" class="formtext"><%=dispArr [count]%>
							</td>
							<% count++;%>
							<%} %>
						</tr>
						<% }
		     						  } // end of iff
									      %>



						<tr id="clearId">

							<td align="center" colspan="2" width="100%">&nbsp;
							 <input type="button" class="reset" theme="simple" onclick="return callClear();" value="Clear Filter" /> &nbsp;
							 <input type="button" class="reset" theme="simple" onclick="return callShowFilter();" value="Edit Filter" /> </td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td height="27" class="formtxt"><strong> <%
			 	String status = (String) request.getAttribute("stat");
			 	if (status == "C") {
			 		out.println("Completed List");
			 	} else {
			 		out.println("Pending List");
			 	}
			 %> </strong></td>
				<td width="70%" align="right"><s:if test="modeLength">
						<b>Page:</b>
					</s:if> <%
						 	int totalPage = (Integer) request.getAttribute("abc");
						 	int pageNo = (Integer) request.getAttribute("xyz");
						 %> <%
						 		if (pageNo != 1) {
						 		if (pageNo > totalPage) {
						 		} else {
						 %> <a href="#" onclick="callPage('1');"> <img
						src="../pages/common/img/first.gif" width="10" height="10"
						class="iconImage" /> </a>&nbsp; <a href="#" onclick="previous();">
					<img src="../pages/common/img/previous.gif" width="10" height="10"
						class="iconImage" /> </a> <%
						 	}
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
						
						 		if (totalPage == 0 || pageNo > totalPage) {
						 		} else {
						 %> ....<%=totalPage%> <a href="#" onclick="next()"> <img
						src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; <a
						href="#" onclick="callPage('<%=totalPage%>');"> <img
						src="../pages/common/img/last.gif" width="10" height="10"
						class="iconImage" /> </a> <%
						 	}
						 	}
						 %><s:if test="modeLength">
						<select name="selectname" onchange="on()" id="selectname" />
					</s:if> <%
														for (int i = 1; i <= totalPage; i++) {
														%>

					<option value="<%=i%>"><%=i%></option>
					<%
														}
														%>
					</td>

				</tr>
			


			
						<td colspan="3">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="sortable">
								<!--Table 6-->


								<tr>

									<td width="5%" align="center" class="formth" nowrap="nowrap">


								<b>	<label class="set" name="serial.no" id="serial.no"
										ondblclick="callShowDiv(this);"> <%=label.get("serial.no")%></label></b>
									</td>
									<td width="20%" align="center" class="formth" nowrap="nowrap">

									<b><label class="set" name="cand.name" id="LcanName"
										ondblclick="callShowDiv(this);"> <%=label.get("cand.name")%></label></b>
									</td>
									<td width="20%" align="center" class="formth"><b><label
										class="set" name="position" id="Lposition"
										ondblclick="callShowDiv(this);"> <%=label.get("position")%></label></b>
									</td>
									<td nowrap="nowrap" align="center" class="formth"><b><label
										class="set" name="Tdate" id="Tdate"
										ondblclick="callShowDiv(this);"> <%=label.get("Tdate")%></label></b>

									</td>
									<td width="25%" align="center" class="formth"><!--  CheckList Activity -->

									<b><label class="set" name="Lchecklisttype" id="Lchecklisttype"
										ondblclick="callShowDiv(this);"> <%=label.get("Lchecklisttype")%></label></b>

									</td>

									<td width="15%" align="center" class="formth"><b><label
										class="set" name="Astatus" id="Astatus"
										ondblclick="callShowDiv(this);"> <%=label.get("Astatus")%></label></b>


									</td>
								</tr>
								<s:if test="noData">
									<tr>
										<td width="100%" colspan="7" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
								<s:else>
									<%!int i = 0; %>
									<%
												int k = 1;
			
												int cn = pageNo * 20 - 20;
											%>

									<s:iterator value="MonitorList">
										<tr>
											<td class="sortabletd" width="5%" nowrap="nowrap"><%=++cn%>
											</td>
											<td class="sortabletd" width="20%"><s:hidden
												name="Lreqcode" /> <s:hidden name="Loffercode" /> <s:hidden
												name="Lcancode" /> <s:property value="Lcandidate" /> <s:hidden
												name="Lchecklistdtlcode" /></td>



											<td class="sortabletd" width="20%"><s:property
												value="Lposition" /></td>
											<td class="sortabletd" align="center" width="10%"><s:property
												value="LtargetDate" /></td>
											<td class="sortabletd" width="20%"><s:property
												value="LcheckListname" /></td>

											<td class="sortabletd" align="center" width="15%"><s:if
												test="prebean.conduct">
												<s:select list=" #{'S':'Select','C':'Close'}"
													name="LactivityRequired"
													id='<%="LactivityRequired"+(k-1)%>' />


											</s:if> <s:else>
												<%
															if (status == "C") {
															out.println("Close");
														}
													%>
											</s:else></td>
										</tr>
										<%
												k++;
												%>
									</s:iterator>
									<%
												i = k;
												k = 1;
											%>
								</s:else>

							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>

					<td colspan="3" width="100%" align="right"><s:if
						test="modeLength">
						<b>Total No. of Records:</b>&nbsp;<s:property value="totalRecords" />
					</s:if></td>
				</tr>
				<tr>
				


				<tr>
					<td align="left"><input type="hidden" name="noofrecords"
						id="noofrecords" value="<%=i-1%>" /> <s:hidden name="Chkreqcode"></s:hidden>
					<s:hidden name="Chkcandidatecode"></s:hidden> <s:hidden
						name="Chkoffercode"></s:hidden> <s:hidden name="conduct"></s:hidden>
					<s:if test="conduct">
						<s:submit cssClass="save" value=" Save"
							action="PrejoiningActivitiesMonitor_monitorsave"
							onclick="return Conducted();" />

					</s:if><s:else>

					</s:else></td>
				</tr>

	</table>
</s:form>

<script>


function ApplyFilter()
{
 var candidateName1=document.getElementById('paraFrm_candidateName1').value;
      var positionName=document.getElementById('paraFrm_positionName').value;
      var targetFDate=document.getElementById('paraFrm_targetFDate').value;
      var targetTDate=document.getElementById('paraFrm_targetTDate').value;
      
     
  		   
			 
    if((candidateName1=="")&&(positionName=="")&&(targetFDate=="")&&(targetTDate==""))
  		
  		{
  		
  		alert('Please enter/select atleast one field to apply filter');
  		return false;
  		}    
  		
  		
  		if(targetFDate!="")
  		     {
  	    if(!validateDate('paraFrm_targetFDate','tarfDate'))
			return false;
			}
		if(targetTDate!="")
  		     {
  	    if(!validateDate('paraFrm_targetTDate','tartDate'))
			return false;
			}
		
		if(targetFDate!=""&& targetTDate!="")
  		     {
  	    if(!dateDifferenceEqual(targetFDate,targetTDate,'paraFrm_targetTDate', 'tarfDate','tartDate'))
				return false;
			 }

			document.getElementById("paraFrm_searchFlag").value="true";
			var status=document.getElementById("paraFrm_bgStatus").value;
			document.getElementById("paraFrm").action='PrejoiningActivitiesMonitor_prejoiningmonitor.action?bgStatus='+status;
			  document.getElementById("paraFrm").submit();

}

showEnable();
function showEnable(){

			
			if(document.getElementById("paraFrm_searchFlag").value=="true"){
			document.getElementById("showFilter").style.display='none';
			document.getElementById("hideFilter").style.display='none';
	}
}  

callFilter();
	function callFilter(){
		        
	         var chkSearch=document.getElementById('paraFrm_searchFlag').value;
	if(document.getElementById('paraFrm_searchFlag').value=="true")
	        {  
	            document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='none';
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='';
	      }
	else
	      {
	      		document.getElementById('showFilter').style.display='';
	      		  document.getElementById('hideFilter').style.display='none';
	            document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='none';
	        }
	                
	        
	    }

function callShowFilter(){//callShowFilter()
     document.getElementById('hideFilter').style.display='';
	document.getElementById('showFilter').style.display='none';
	document.getElementById('showFilterValue').style.display='inline';
	document.getElementById('enableFilterValue').style.display='none';
	

}

function callHideFilter(){
calReset();
	document.getElementById('showFilterValue').style.display='none';
	document.getElementById('hideFilter').style.display='none';
	document.getElementById('showFilter').style.display='';
	
}
function calReset(){

			 document.getElementById('enableFilterValue').style.display='none';
			 document.getElementById('paraFrm_searchFlag').value="false";
			 document.getElementById('paraFrm_positionId').value="";
		 	 document.getElementById('paraFrm_positionName').value="";
		 	  document.getElementById('paraFrm_candCode1').value="";
		   	 document.getElementById('paraFrm_candidateName1').value="";
		     document.getElementById('paraFrm_targetFDate').value="";
    		document.getElementById('paraFrm_targetTDate').value="";
   }
   
   
function callClear(){
 document.getElementById('showFilterValue').style.display='none';
 document.getElementById("paraFrm_searchFlag").value='false';
 document.getElementById("paraFrm").action='PrejoiningActivitiesMonitor_clearFilter.action';
 document.getElementById("paraFrm").submit();
  
 	
 }

	
//callFun(P);
function callFun(status){		
		document.getElementById("paraFrm_bgStatus").value=status;
		document.getElementById("paraFrm").action='PrejoiningActivitiesMonitor_prejoiningmonitor.action?bgStatus='+status;
	    document.getElementById('paraFrm').target="main";
	    document.getElementById("paraFrm").submit();
	}

   function Conducted(){
		var countOffer = document.getElementById("noofrecords").value;
		if(countOffer == 0){
			alert("There is no record in the list");
			return false;
		}
		
		for(var i=0;i<countOffer;i++)
		{
		
		if(document.getElementById("LactivityRequired"+i).value=="C")
		{
		  return true;
		}		
		}
		alert("Please change atleast one application status.");
		return false;
		
	}
    	  function callPage(id){
    	  if(document.getElementById("paraFrm_searchFlag").value=="true"){
					document.getElementById("showFilter").style.display='none';
					document.getElementById("hideFilter").style.display='none';
					document.getElementById("enableFilterValue").style.display='';
			
			}
     
     
  
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="PrejoiningActivitiesMonitor_callPage.action";
	   	document.getElementById('paraFrm').submit();
}



function next(){
   var pageno=	document.getElementById('myPage').value;
   	if(pageno=="1"){
   		document.getElementById('myPage').value=2;
	    document.getElementById('paraFrm_show').value=2;
    } else{
				 document.getElementById('myPage').value=eval(pageno)+1;
				 document.getElementById('paraFrm_show').value=eval(pageno)+1;
	    }
	   document.getElementById('paraFrm').action="PrejoiningActivitiesMonitor_callPage.action";
	   document.getElementById('paraFrm').submit();
}	
   
function on(){
if(document.getElementById("paraFrm_searchFlag").value=="true"){
					document.getElementById("showFilter").style.display='none';
					document.getElementById("hideFilter").style.display='none';
					document.getElementById("enableFilterValue").style.display='';
			
			}
     
     
		var val= document.getElementById('selectname').value;
		document.getElementById('paraFrm_show').value=val;
		document.getElementById('myPage').value=eval(val);
		document.getElementById('selectname').value=val;
		document.getElementById('paraFrm').action="PrejoiningActivitiesMonitor_callPage.action";
		document.getElementById('paraFrm').submit();
}
   
 
  	
  	function previous(){
   var pageno=	document.getElementById('myPage').value;
   	
	 document.getElementById('myPage').value=eval(pageno)-1;
	 document.getElementById('paraFrm_show').value=eval(pageno)-1;
	   document.getElementById('paraFrm').action="PrejoiningActivitiesMonitor_callPage.action";
	   document.getElementById('paraFrm').submit();
}  
 pgshow();

function pgshow()
  	{
  	var pgno=document.getElementById('paraFrm_myPage').value;
  	if(document.getElementById('paraFrm_show').value=="")
  	  pgno=0;
  	//alert("pgno"+pgno);
  	 
  	if(eval(pgno)!="")
  	 document.getElementById('selectname').value=eval(pgno);
  	 else
  	  document.getElementById('selectname').value=1;
  	 
  	}
  	function Conducted(){
		var countOffer = document.getElementById("paraFrm_LChkListLength").value;
		
		if(countOffer == 0){
			alert("There is no record in the list");
			return false;
		}
		
		for(var i=0;i<countOffer;i++)
		{
		
		if(document.getElementById("LactivityRequired"+i).value=="C")
		{
		  return true;
		}		
		}
		alert("Please change atleast one activity status.");
		return false;
		
	}
    	  function callPage(id){
  
	   	document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="PrejoiningActivitiesMonitor_callPage.action";
	   	document.getElementById('paraFrm').submit();
}
    	
    	</script>