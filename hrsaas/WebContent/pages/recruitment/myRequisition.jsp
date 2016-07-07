<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="MyRequisition" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="listLength"></s:hidden>
	<s:hidden name="show" />
	<s:hidden name="modeLength" />
	<s:hidden name="requisitionId" />
	<s:hidden name="divId" />
	<s:hidden name="myPage" />
	<s:hidden name="branchId" />
	<s:hidden name="deptId" />
	<s:hidden name="hrManagerId" />
	<s:hidden name="positionId" />
	<s:hidden name="myStatus" />
	<s:hidden name="ChkSerch" />
	<s:hidden name="searchFlag" />
	<s:hidden name="applyFilterFlag" />
	<s:hidden name="fakeRankName"></s:hidden>
	<table width="100%" class="formbg">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My
					Requisition</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="2" class="formbg">
				<!--Table 4-->
				<tr>
					<td height="27" class="formtxt"><a href="#"
						onclick="callFun('O');">Open Requisition </a> | <a href="#"
						onclick="callFun('C');">Closed Requisition </a></td>
				</tr>

			</table>
			<!--Table 4--></td>

		</tr>
		<!--<tr>
			<td colspan="3">

			<table width="100%" border="0" cellspacing="0" color="red"
				class="formbg">
				<tr>

					<td colspan="2" nowrap="nowrap"><strong class="text_head"><s:if
						test="searchFlag">Applied Filter</s:if><s:else>
						<label class="set" name="searchApply.filter"
							id="searchApply.filter" ondblclick="callShowDiv(this);"></label>
					</s:else></strong></td>

					<td id="showFilter" align="right" colspan="2"><input
						type="button" value="Show Filter" cssClass="token"
						onclick="return callShowFilter();"></td>

					<td id="hideFilter" align="right"><input type="button"
						value="Hide Filter" cssClass="token"
						onclick="return callHideFilter();"></td>
				</tr>

				<tr>
					<td colspan="3">
					<div id="showFilterValue">
					<table width="100%" border="0">

						<tr>
							<td width="20%" colspan="1"><label class="set"
								name="reqs.code" id="reqs.code1" ondblclick="callShowDiv(this);">
							:</td>
							<td width="25%" nowrap="nowrap"><s:textfield
								name="requisitionName" size="25" readonly="true" /><img
								src="../pages/images/search2.gif" class="iconImage" height="16"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'MyRequisition_f9Requisition.action');"></td>
							<td width="10%"></td>
							<td width="15%"><label class="set" name="division"
								id="division" ondblclick="callShowDiv(this);">
							:</td>
							<td width="25%" nowrap="nowrap"><s:textfield name="divName"
								size="25" readonly="true" /><img
								src="../pages/images/search2.gif" class="iconImage" height="16"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'MyRequisition_f9Division.action');"></td>

						</tr>
						<tr>

							<td width="20%"><label class="set" name="branch" id="branch"
								ondblclick="callShowDiv(this);">
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="branchName" size="25" readonly="true" /><img
								src="../pages/images/search2.gif" class="iconImage" height="16"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'MyRequisition_f9Branch.action');"></td>
							<td width="5%"></td>

							<td width="15%"><label class="set" name="department"
								id="department" ondblclick="callShowDiv(this);">
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield name="deptName"
								size="25" readonly="true" /><img
								src="../pages/images/search2.gif" class="iconImage" height="16"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'MyRequisition_f9Department.action');"></td>

						</tr>

						<tr>

							<td width="20%"><label class="set" name="hiring.mgr"
								id="hiring.mgr1" ondblclick="callShowDiv(this);"></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="managerName" size="25" readonly="true" /><img
								src="../pages/images/search2.gif" class="iconImage" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'MyRequisition_f9HrManager.action');"></td>
							<td width="5%"></td>
							<td width="15%"><label class="set" name="position"
								id="position1" ondblclick="callShowDiv(this);"></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield
								name="positionName" size="25" readonly="true" /><img
								src="../pages/images/search2.gif" class="iconImage" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'MyRequisition_f9Position.action');"></td>

						</tr>

						<tr>

							<td width="20%"><label class="set" name="pubFDate"
								id="pubFDate" ondblclick="callShowDiv(this);"></label>
							:</td>
							<td width="20%" nowrap="nowrap"><s:textfield name="pubfDate"
								size="25" onkeypress="return numbersWithHiphen();"
								maxlength="10" readonly="false" /><s:a
								href="javascript:NewCal('paraFrm_pubfDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>
							<td width="5%"></td>
							<td width="15%"><label class="set" name="pubTDate"
								id="pubTDate" ondblclick="callShowDiv(this);"></label>
							:
							<td width="35%"><s:textfield name="pubtDate" size="25"
								onkeypress="return numbersWithHiphen();" maxlength="10"
								readonly="false" /><s:a
								href="javascript:NewCal('paraFrm_pubtDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />
							</s:a></td>


						</tr>


						<tr>
							<td width="100%" colspan="5" align="center"><s:submit
								cssClass="token" action="MyRequisition_search" theme="simple"
								value="Apply Filter" onclick="return chkDate();" /> <input
								type="button" class="reset" theme="simple"
								onclick="return calReset();" value="Reset " /></td>
							<td width="25%"></td>
						</tr>
					</table>-->
					<!--Table 1--></div>
				<!-- 	</td>
				</tr>


				<tr>
					<td colspan="2"> -->
					<%--<% 
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

							<td width="20%" height="22" class="formtext"><%=dispArr [count]%>

							</td>
							<% count++;%>

							<%} %>


							<% if(count<dispArr.length){ %>
							<td width="20%" height="22" class="formtext"><%=dispArr [count]%>
							</td>
							<% count++;%>
							<%} %>
						</tr>
						<% }
		     						  } // end of iff
									      %>



						<tr>

							<td align="center" colspan="5">&nbsp; <input type="button"
								class="reset" theme="simple" onclick="return callClear();"
								value="Clear Filter" /></td>

						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>--%>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">


						<tr>
							<td><img src="../pages/common/css/default/images/space.gif"
								width="5" height="2" /></td>
						</tr>

						<tr>
							<td width="100%">
							<table width="100%" border="0" cellpadding="0" cellspacing="0"
								class="formbg">
								<!--Table 3-->

								<tr>
									<td>
									<table width="100%" border="0" cellpadding="1" cellspacing="1">
										<!--Table 5-->
										<tr>
											<td height="27" class="tesxt_head"><strong> <% 	String status = (String) request.getAttribute("stat");
									String statPass="";
								 	if (status != null) {
								 		out.println(status);
								 	} else {
								 		out.println("Open Requisition");
								 	}
								 	if (status == null ) {
								 		statPass = "O";
									}// end of if
									else if (status.equals("Open Requisition")) {
										statPass = "O";
									}
									else if (status.equals("Closed Requisition")) {
										statPass = "C";
									}
 								%> </strong></td>
											<%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("PageNo");
							%>
											<s:if test="noData">
											</s:if>
											<s:else>
												<td align="right"><b>Page:</b> <input type="hidden"
													name="totalPage" id="totalPage" value="<%=totalPage%>">
												<a href="#" onclick="callPage('1','F','<%=statPass%>');">
												<img title="First Page" src="../pages/common/img/first.gif"
													width="10" height="10" class="iconImage" /> </a>&nbsp; <a
													href="#" onclick="callPage('P','P','<%=statPass%>');">
												<img title="Previous Page"
													src="../pages/common/img/previous.gif" width="10"
													height="10" class="iconImage" /> </a> <input type="text"
													name="pageNoField" id="pageNoField" theme="simple" size="3"
													value="<%= pageNo%>"
													onkeypress="callPageText(event,'<%=statPass%>');return numbersOnly()"
													maxlength="4" title="press Enter" /> of <%=totalPage%> <a
													href="#" onclick="callPage('N','N','<%=statPass%>')">
												<img title="Next Page" src="../pages/common/img/next.gif"
													class="iconImage" /> </a>&nbsp; <a href="#"
													onclick="callPage('<%=totalPage%>','L','<%=statPass%>');">
												<img title="Last Page" src="../pages/common/img/last.gif"
													width="10" height="10" class="iconImage" /> </a></td>
											</s:else>
										</tr>
									</table>
									</td>
								</tr>

								<tr>
									<td width="100%">
									<table width="100%" border="0" cellpadding="0" cellspacing="0"
										class="formbg">
										<tr>
											<td>
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0" class="sortable">
												<!--Table 6-->
												<tr>
													<td width="5%" valign="top" class="formth" nowrap="nowrap">
													<b><label class="set" name="serial.no" id="serial.no"
														ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
													<td width="10%" valign="top" class="formth"><b><label
														class="set" name="reqs.code" id="requisition.code"
														ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b></td>
													<td width="15%" valign="top" class="formth"><b><label
														class="set" name="position" id="position"
														ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b></td>
													<td width="20%" valign="top" class="formth" nowrap="nowrap">
													<b><label class="set" name="applied.by" id="applied.by"
														ondblclick="callShowDiv(this);"><%=label.get("applied.by")%></label></b></td>
													<td width="20%" valign="top" class="formth" nowrap="nowrap">
													<b><label class="set" name="hiring.mgr" id="hiring.mgr"
														ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label></b></td>
													<td width="5%" valign="top" class="formth" nowrap="nowrap">
													<b><label class="set" name="pub.date" id="pub.date"
														ondblclick="callShowDiv(this);"><%=label.get("pub.date")%></label></b></td>
													<td width="5%" valign="top" class="formth"><b><label
														class="set" name="no.vacancy" id="no.vacancy"
														ondblclick="callShowDiv(this);"><%=label.get("no.vacancy")%></label></b></td>
													<td width="10%" valign="top" class="formth"><b><label
														class="set" name="required.date" id="required.date"
														ondblclick="callShowDiv(this);"><%=label.get("required.date")%></label></b></td>
													
												</tr>
												<s:if test="noData">
													<tr>
														<td width="100%" colspan="9" align="center"><font
															color="red">No data to display</font></td>
													</tr>
												</s:if>
													<%
							int count = 0;
							%>
												<%
											int cnt = pageNo * 20 - 20;
											int m = 0;
									%>
												<%!int i = 0;%>
												<%
								int k = 1;
								%>
												<s:iterator value="list">
												<tr <%if(count%2==0){
															%> class="tableCell1" <%}else{%>
															class="tableCell2" <%	}count++; %>
															onmouseover="javascript:newRowColor(this);"
															onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
															title="Double click for view Requisition"
															ondblclick="javascript:viewRequisition('<s:property value="reqCode"/>','<%=statPass %>');">
														<td class="sortabletd" width="5%" nowrap="nowrap"
															align="center"><%=++cnt%> <%
											++m;
											%>
														</td>
														<td class="sortabletd" width="10%">&nbsp;<s:property
															value="reqName" /> <s:hidden name="reqCode" /></td>
														<td class="sortabletd" width="15%"><s:property
															value="position" /></td>
														<td class="sortabletd" width="20%">&nbsp;<s:property
															value="appliedBy" /></td>
														<td class="sortabletd" width="20%">&nbsp;<s:property
															value="hiringMgr" /></td>
														<td class="sortabletd" width="5%" align="center">&nbsp;<s:property
															value="publishDate" /></td>
														<td class="sortabletd" width="5%" align="center">&nbsp;<s:property
															value="noOfVacancies" /></td>
														<td class="sortabletd" width="10%" align="center" nowrap="nowrap">&nbsp;<s:property
															value="requiredDate" /></td>
														

													</tr>
													<%
									k++;
									%>
												</s:iterator>
												<%
									m=i;	
									i = k;
									k = 1;
										
									%>
											</table>
											</td>
										</tr>
									</table>
									<!--Table 5--></td>
								</tr>

							</table>
							<!--Table 3--></td>
						</tr>
						<tr align="left">
							<td colspan="3" width="100%" align="Right"><s:if
								test="modeLength">
								<b>Total No. of Records:</b>&nbsp;<s:property
									value="totalRecords" />
							</s:if></td>
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


 function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first';

	    }
	
	function oldRowColor(cell,val) {
	
	     cell.className='Cell_bg_second';
		
	}


	function callFun(status){
	      document.getElementById('paraFrm').target="main";
	     document.getElementById('paraFrm_myStatus').value=status;
		 
		 document.getElementById('paraFrm_myPage').value="";
		
	    document.getElementById("paraFrm").action='MyRequisition_callstatus.action?status='+status;
	    document.getElementById("paraFrm").submit();
	}
	
	function viewRequisition(reqCode,status){
	
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=MyRequisition_callstatus.action&statusKey='+status;
	    document.getElementById("paraFrm").submit();
	}
	 
	 function callPage(id,pageImg,status){  
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
		document.getElementById('paraFrm').action='MyRequisition_callstatus.action?status='+status;
		document.getElementById('paraFrm').submit(); 
	}	
	  
	  	function callPageText(id,status){   
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
		   
			document.getElementById('paraFrm').action='MyRequisition_callstatus.action?status='+status;
			document.getElementById('paraFrm').submit();
		}
		
	}	
	
 

function chkDate(){
      
          
		   var fromdate= document.getElementById('paraFrm_pubfDate').value;
  		   var todate= document.getElementById('paraFrm_pubtDate').value;
  		   var reqname=document.getElementById('paraFrm_requisitionName').value;
  		   var reqid=document.getElementById('paraFrm_requisitionId').value;
  		   var divName=document.getElementById('paraFrm_divName').value;
  		   var divId=document.getElementById('paraFrm_divId').value;
  		   var branchId=document.getElementById('paraFrm_branchId').value;
  		   var branchName=document.getElementById('paraFrm_branchName').value;
  		   var managerName=document.getElementById('paraFrm_managerName').value;
  		   var hrManagerId=document.getElementById('paraFrm_hrManagerId').value;
  		   var positionId=document.getElementById('paraFrm_positionId').value;
  		   var deptName=document.getElementById('paraFrm_deptName').value;
  		   var posName=document.getElementById('paraFrm_positionName').value;
  		   var pubfDate=document.getElementById('paraFrm_pubfDate').value;
  		   var pubtDate=document.getElementById('paraFrm_pubtDate').value;
  		   
			 
    if((reqname=="")&&(divName=="")&&(branchName=="")&&(deptName=="")&&(managerName=="")&&(posName=="")&&(pubfDate=="")&&(pubtDate==""))
  		
  		{
  		
  		alert('Please enter/select atleast one field to apply filter');
  		return false;
  		}
  		  if(fromdate!="")
  		     {
  		  if(!validateDate('paraFrm_pubfDate','pubFDate'))
			return false;
			}
			if(todate!="")
			{
			if(!validateDate('paraFrm_pubtDate','pubTDate'))
			return false;
			}
			if(fromdate!=""&& todate!="")
  		     {
  		     if(!dateDifferenceEqual(fromdate,todate,'paraFrm_pubtDate', 'pubFDate','pubTDate'))
				return false;
			 }
		  document.getElementById("paraFrm_searchFlag").value="true";
		  document.getElementById("showFilter").style.display='none';
		  document.getElementById("hideFilter").style.display='none';
  return true;
}




//showEnable();
function showEnable(){


if(document.getElementById("paraFrm_searchFlag").value=="true"){
document.getElementById("showFilter").style.display='none';
document.getElementById("hideFilter").style.display='none';
		document.getElementById("enableFilterValue").style.display='';
		
//document.getElementById("paraFrm_searchFlag").value="false";
}
}  

//callFilter();
	function callFilter(){
		    var chkSearch=document.getElementById('paraFrm_ChkSerch').value;
	if(chkSearch=="")
	        {  
	            document.getElementById('hideFilter').style.display='none';
				document.getElementById('showFilter').style.display='';
				document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='none';
	      }
	else
	      {
	            document.getElementById('showFilterValue').style.display='none';
				document.getElementById('enableFilterValue').style.display='';
	        }
	    }

function callShowFilter(){//callShowFilter()
	document.getElementById('showFilterValue').style.display='';
	document.getElementById('hideFilter').style.display='';
	document.getElementById('showFilter').style.display='none';
	
	document.getElementById('enableFilterValue').style.display='none';
	

}

function callHideFilter(){
	document.getElementById('showFilterValue').style.display='none';
	document.getElementById('hideFilter').style.display='none';
	document.getElementById('showFilter').style.display='';
	//document.getElementById('enableFilterValue').style.display='none';

}
function calReset(){
    document.getElementById('paraFrm_divId').value="";
    document.getElementById('paraFrm_divName').value="";
    document.getElementById('paraFrm_branchId').value="";
    document.getElementById('paraFrm_branchName').value="";
    document.getElementById('paraFrm_deptId').value="";
    document.getElementById('paraFrm_deptName').value="";
    document.getElementById('paraFrm_hrManagerId').value="";
    document.getElementById('paraFrm_managerName').value="";
    document.getElementById('paraFrm_positionId').value="";
    document.getElementById('paraFrm_positionName').value="";
    document.getElementById('paraFrm_pubfDate').value="";
    document.getElementById('paraFrm_pubtDate').value="";
    document.getElementById('paraFrm_requisitionId').value="";
    document.getElementById('paraFrm_requisitionName').value="";
   
   }
   
   
function callClear(){
 document.getElementById('showFilterValue').style.display='none';
 document.getElementById("paraFrm_searchFlag").value='false';
 document.getElementById("paraFrm").action='MyRequisition_clearFilter.action';
 document.getElementById("paraFrm").submit();
  
 	
 }

</script>

