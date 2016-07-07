<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="BackGroundCheck" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="bgStatus" />
	<s:hidden name="candidateCode" />
	<s:hidden name="candidateName" />
	<s:hidden name="reqName" />
	<s:hidden name="reqCode" />
	<s:hidden name="checkListType" />
	<s:hidden name="bgCheckType" />
	<s:hidden name="DupcheckListType" />
	<s:hidden name="DupcheckListresponce" />
	<s:hidden name="outsourceAgencyName" />
	<s:hidden name="outsourceAgencyCode" />
	<s:hidden name="show" />
	<s:hidden name="candiFlag" />
	<s:hidden name="myPage" id="myPage" />

	<!-- addnewFlag  searchFlag saveFlag editFlag deleteFlag1 deleteFlag  loadFlag addFlag modFlag-->

	<s:hidden name="buttonpanelcode" />
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Back
					Ground Check </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="24%" align="left"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td colspan="5"><s:hidden name="bgcheckCode" />
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						class="formbg">
						<tr>
							<td height="27" class="formtxt"><a href="#"
								onclick="callFun('P');">Pending List </a> | <a href="#"
								onclick="callFun('C');">Conducted List </a></td>
							<%
							int totalPage = (Integer) request.getAttribute("abc");
							int pageNo = (Integer) request.getAttribute("xyz");
							%>
							<s:if test="noData"></s:if>
							<s:else>
								<td align="right"><b>Page:</b> <input type="hidden"
									name="totalPage" id="totalPage" value="<%=totalPage%>">
								<a href="#" onclick="callPage('1','F');"> <img
									title="First Page" src="../pages/common/img/first.gif"
									width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('P','P');"> <img title="Previous Page"
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /> </a> <input type="text" name="pageNoField"
									id="pageNoField" theme="simple" size="3" value="<%= pageNo%>"
									onkeypress="callPageText(event);return numbersOnly()"
									maxlength="4" title="press Enter" /> of <%=totalPage%> <a
									href="#" onclick="callPage('N','N')"> <img
									title="Next Page" src="../pages/common/img/next.gif"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="callPage('<%=totalPage%>','L');"> <img
									title="Last Page" src="../pages/common/img/last.gif" width="10"
									height="10" class="iconImage" /> </a></td>
							</s:else>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td colspan="5"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>


				<tr>
					<td colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td height="27" class="formtxt"><strong> <%
				 	String status = (String) request.getAttribute("stat");
				 	if (status == "C") {
				
				 		out.println("Conducted List");
				 	} else {
				 		status="P";
				 		out.println("Pending List");
				 	}
				 %> </strong></td>
						</tr>

						<tr>
							<td>
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortable">
								<!--Table 6-->
								<s:if test="conductflag">
									<tr>
										<td width="5%" align="center" class="formth" nowrap="nowrap"><b>
										<!--  Sr.No.--> <label class="set" name="serial.no"
											id="serial.no" ondblclick="callShowDiv(this);"> <%=label.get("serial.no")%></label></b>
										</td>
										<td width="25%" align="center" class="formth" nowrap="nowrap"><b>
										<!--  Candidate Name--> <label class="set" name="cand.name"
											id="LcanName" ondblclick="callShowDiv(this);"> <%=label.get("cand.name")%></label></b>
										</td>
										<td width="20%" align="center" class="formth"><!--  Requisition Code--><b>
										<label class="set" name="reqs.code" id="Lreqcode"
											ondblclick="callShowDiv(this);"> <%=label.get("reqs.code")%></label></b>
										</td>
										<td width="20%" align="center" class="formth"><!--  Position--><b>
										<label class="set" name="position" id="Lposition"
											ondblclick="callShowDiv(this);"> <%=label.get("position")%></label></b>
										</td>
										<td width="10%" align="center" class="formth"><!--  Offer Status-->
										<b><label class="set" name="Lofferstatus"
											id="Lofferstatus" ondblclick="callShowDiv(this);"> <%=label.get("Lofferstatus")%></label></b>
										</td>
										<td width="10%" align="center" class="formth"><b> <!--  View CV-->
										<label class="set" name="view.cv" id="Lviewcv"
											ondblclick="callShowDiv(this);"> <%=label.get("view.cv")%></label></b>
										</td>
										<td width="10%" align="center" class="formth"><!--  Check List Type-->
										<b><label class="set" name="Lchecklisttype"
											id="Lchecklisttype" ondblclick="callShowDiv(this);">
										<%=label.get("Lchecklisttype")%></label></b></td>
									</tr>
								</s:if>
								<s:else>
									<tr>
										<td width="5%" align="center" class="formth" nowrap="nowrap"><b>
										<label class="set" name="serial.no" id="serial.no"
											ondblclick="callShowDiv(this);"> <%=label.get("serial.no")%></label></b>
										</td>
										<td width="25%" align="center" class="formth" nowrap="nowrap"><b>
										<label class="set" name="cand.name" id="LcanName"
											ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label></b>
										</td>
										<td width="20%" align="center" class="formth"><b> <label
											class="set" name="reqs.code" id="Lreqcode"
											ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label></b>
										</td>
										<td width="20%" align="center" class="formth"><b> <label
											class="set" name="position" id="Lposition"
											ondblclick="callShowDiv(this);"><%=label.get("position")%></label></b>
										</td>
										<td width="20%" align="center" class="formth"><b> <label
											class="set" name="Lofferstatus" id="Lofferstatus"
											ondblclick="callShowDiv(this);"> <%=label.get("Lofferstatus")%></label></b>
										</td>

										<td width="10%" align="center" class="formth"><b> <label
											class="set" name="view.cv" id="Lviewcv"
											ondblclick="callShowDiv(this);"> <%=label.get("view.cv")%></label></b>
										</td>

									</tr>
								</s:else>


								<s:if test="noData">
									<tr>
										<td width="100%" colspan="7" align="center"><font
											color="red">No Data To Display</font></td>
									</tr>
								</s:if>
								<%!int i = 0;%>
								<%
						int k = 1,count=0;
						int cn= pageNo*20-20;
						int conductedCount = 0;
						int conductedpage = pageNo*20-20;
						%>

								
									<s:if test="conductflag">
										<s:iterator value="BgconductChkList"> 
										<tr <%if(conductedCount%2==0){%> class="tableCell1" <%}else{%>
											class="tableCell2" <%	}conductedCount++; %>
											onmouseover="javascript:newRowColor(this);"
											onmouseout="javascript:oldRowColor(this,<%=conductedCount%2 %>);"
											title="Double click to view background details"
											ondblclick="javascript:showBackgroundDetails('<s:property value="Lreqcode"/>','<s:property value="Lcancode"/>','<s:property value="backgroundCheckCode"/>','<s:property value="backgroundCheckListType"/>', '<s:property value="Loffercode"/>');">

											<td class="sortabletd" width="5%" nowrap="nowrap"
												align="center"><%=++conductedpage%>&nbsp;&nbsp;  
											</td>
											
											<td class="sortabletd" width="25%">
												<s:hidden name="backgroundCheckCode" />
												<s:hidden name="backgroundCheckListType" />
												<s:hidden name="Lreqcode"/> 
												<s:hidden name="Loffercode"/> 
												<s:hidden name="Lcancode"/> 
												<s:property value="Lcandidate" />
											</td>
											
											<td class="sortabletd" width="20%" nowrap="nowrap"><s:property
												value="LreqName" /></td>
											
											<td class="sortabletd" width="20%"><s:property
												value="Lposition" /> &nbsp;</td>
											
											<td class="sortabletd" width="10%"><s:property
												value="Lofferstatus" /> &nbsp;</td>


											<td align="center" class="sortabletd" width="10%"><s:hidden
												name="vacanDtlCode" /> <s:hidden name="appliedEmpId" /><s:hidden
												name="hiringEmpId" /> <s:property value="position" /> <input
												type="button" name="publish" class="token" value="View"
												onclick="return showRecord('<s:property value="resume" />');" />
											</td>

											<td class="sortabletd" width="10%"><s:property
												value="LchecklistType" />
											</td>
										</tr>
										</s:iterator>
										<tr><td colspan="7"  align="right"><b>Total Records : <s:property value="totalConductedRecords"/></b></td></tr>
									</s:if>
									<s:else>
										<s:iterator value="BgpendingChkList"> 
										<tr <%if(count%2==0){%> class="tableCell1" <%}else{%>
											class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											title="Double click for view Requisition"
											ondblclick="javascript:showRequisition('<s:property value="Lreqcode"/>','<%=status %>');">

											<td class="sortabletd" width="5%" nowrap="nowrap"
												align="center"><%=++cn%>&nbsp;&nbsp; <% if (status != "C") {%>
											<input name="bgCheck" id="<%="bgCheck"+k%>" type="radio"
												value="I" /> <%	}%>
											</td>
											<td class="sortabletd" width="25%"><s:hidden
												name="Lreqcode" id='<%="Lreqcode"+k%>' /> <s:hidden
												name="Loffercode" id='<%="Loffercode"+k%>' /> <s:hidden
												name="Lcancode" id='<%="Lcancode"+k%>' /> <s:property
												value="Lcandidate" /></td>
											<td class="sortabletd" width="20%" nowrap="nowrap"><s:property
												value="LreqName" /></td>
											<td class="sortabletd" width="20%"><s:property
												value="Lposition" /></td>
											<td class="sortabletd" width="20%"><s:property
												value="Lofferstatus" />&nbsp;</td>


											<td align="center" width="10%" class="sortabletd"><s:hidden
												name="vacanDtlCode" /> <s:hidden name="appliedEmpId" /><s:hidden
												name="hiringEmpId" /> <s:property value="position" /> <input
												type="button" name="publish" class="token" value="View"
												onclick="return showRecord('<s:property value="resume" />');" />


											</td>
										</tr>
										<%
											k++;
										%>
									</s:iterator>
										<%
											i = k;
											k = 1;
										%>
										
										<tr><td colspan="6" align="right"><b>Total Records : <s:property value="totalPendingRecords"/></b></td></tr>
									</s:else>
									

							</table>
							<s:hidden name="bgpendinglistLength" /></td>
						</tr>

					</table>
					</td>
				</tr>

				<tr>
					<td height="5" colspan="5"><img
						src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>
				</tr>


				<tr>
					<td colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="24%" align="left"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>

					<td align="center"><input type="hidden" name="noofrecords"
						id="noofrecords" value="<%=i-1%>" /> <s:hidden name="Chkreqcode"></s:hidden>
					<s:hidden name="Chkcandidatecode"></s:hidden> 
					<s:hidden name="Chkoffercode" />
					<s:hidden name="conduct"></s:hidden>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

</s:form>

<script>
//window.onload=   document.getElementById('pageNoField').focus();
function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first';

	    }
	
	function oldRowColor(cell,val) {
	
	     cell.className='Cell_bg_second';
		
	}

function newRowColor(cell)
   		 {
		  cell.className='Cell_bg_first';

	    }
	
	function oldRowColor(cell,val) {
	
	     cell.className='Cell_bg_second';
		
	}

function cancelFun()
{

	
		document.getElementById('paraFrm').action="BackGroundCheck_input.action";
		document.getElementById('paraFrm').submit();
	
}	
	function conductFun(){
	
		document.getElementById("paraFrm_candiFlag").value="true";
		var countOffer = document.getElementById("noofrecords").value;
		var checkFlag  = false;
		
		if(countOffer == 0){
			alert("There is no record in the list");
			return false;
		}
		
		for(var i=1; i<=countOffer; i++){
			if(document.getElementById("bgCheck"+i).checked) {
			checkFlag = true;
			requistioncode=document.getElementById("Lreqcode"+i).value;
			candidatecode =document.getElementById("Lcancode"+i).value;
			offercode=document.getElementById("Loffercode"+i).value;
			document.getElementById("paraFrm_Chkreqcode").value=requistioncode;
			document.getElementById("paraFrm_Chkcandidatecode").value=candidatecode;
			document.getElementById("paraFrm_Chkoffercode").value=offercode;
			}
		}
		
		if(!checkFlag){
			alert("Please select a candidate");
			return false;
		} else {
			document.getElementById('paraFrm').action = "BackGroundCheck_conducted.action";
			document.getElementById('paraFrm').submit();
		}
		
}
function callFun(status){
      try {
      	 //document.getElementById('pageNoField').value="1";	 
       	 document.getElementById('myPage').value="1";	  
		 document.getElementById('paraFrm').target="main";
		 document.getElementById("paraFrm_bgStatus").value=status;
	   	 document.getElementById("paraFrm").action='BackGroundCheck_callstatus.action?status='+status;
	   	 document.getElementById("paraFrm").submit();
      } catch(e) {
      	alert("Unable to Display data ================>"+e);
      }
      
	}
	
	function showRecord(fileName)
	{
		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action = "BackGroundCheck_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target="main";
	}
	
	
	function showRequisition(reqCode,status){
		document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&menuCode=355&formAction=BackGroundCheck_callstatus.action&statusKey='+status;
	    document.getElementById("paraFrm").submit();
	}
	

function addnewFun()
{
    document.getElementById("paraFrm_candiFlag").value="false";
	document.getElementById('paraFrm').action="BackGroundCheck_addNew.action";
	document.getElementById('paraFrm').submit();
}


function searchFun()
{
	callsF9(500,300,"BackGroundCheck_f9search.action");
	return false;
	
} 
 
function callPage(id,pageImg){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 var actPage = document.getElementById('myPage').value; 
	 if(pageNo!="0"  & pageNo<totalPage){	
		 document.getElementById('myPage').value=pageNo;
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
		document.getElementById('myPage').value=id;
		document.getElementById('paraFrm_show').value=id;
	    document.getElementById('paraFrm').action="BackGroundCheck_callPage.action";
		document.getElementById('paraFrm').submit(); 
	}	
	
	
	function callPageText(id){   
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
		 	var actPage = document.getElementById('myPage').value;   
	     
		 	 
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
	        
	        document.getElementById('myPage').value=pageNo; 
			document.getElementById('paraFrm').action="BackGroundCheck_callPage.action";
			document.getElementById('paraFrm').submit();
		}
		
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
	   document.getElementById('paraFrm').action="BackGroundCheck_callPage.action";
	   document.getElementById('paraFrm').submit();
}	
   
   
function previous(){
   	var pageno=	document.getElementById('myPage').value;
	 	document.getElementById('myPage').value=eval(pageno)-1;
	 	document.getElementById('paraFrm_show').value=eval(pageno)-1;
	    document.getElementById('paraFrm').action="BackGroundCheck_callPage.action";
	    document.getElementById('paraFrm').submit();
}

function showBackgroundDetails(requisitionCode, candidateCode, backgroundCheckCode, backgroundCheckListType, offerCode) {
	document.getElementById('paraFrm').action="BackGroundCheck_showBackGroundDetails.action?requisitionCode="+requisitionCode+"&candidateCode="+candidateCode+"&backgroundCheckCode="+backgroundCheckCode+"&backgroundCheckListType="+backgroundCheckListType+"&offerCode="+offerCode;
	document.getElementById('paraFrm').submit();
}  
</script>