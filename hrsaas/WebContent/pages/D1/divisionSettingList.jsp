<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var records = new Array();
</script>
<s:form action="ApprovalSettings" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="divisionCode" />
	<s:hidden name="myPage1" id="myPage1" />
	<s:hidden name ="divId"/>
	<s:hidden name ="approvalDivision"/>
	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td colspan="2">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td colspan="1" width="5%"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td colspan="1" width="80%" class="txt"><strong
						class="text_head">Approval Setting </strong></td>
					<td width="5%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="2">
			<table width="100%" cellspacing="2" cellspacing="2" border="0">
				<td width="40%" colspan="1"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

				<%
					int totalPage = 0;
					int pageNo = 0;
				%>

				<s:if test="modeLength">
					<td id="ctrlShow" width="60%" align="right" class="" colspan="1"><b>Page:</b>
					<%
						totalPage = (Integer) request.getAttribute("totalPage");
						pageNo = (Integer) request.getAttribute("pageNo");
					%> <a href="#"
						onclick="callPage('1', 'F', '<%=totalPage%>', 'ApprovalSettings_callPage.action');">
					<img title="First Page" src="../pages/common/img/first.gif"
						width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('P', 'P', '<%=totalPage%>', 'ApprovalSettings_callPage.action');">
					<img title="Previous Page" src="../pages/common/img/previous.gif"
						width="10" height="10" class="iconImage" /> </a> <input type="text"
						name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
						maxlength="10"
						onkeypress="callPageText(event, '<%=totalPage%>', 'ApprovalSettings_callPage.action');return numbersOnly();" />
					of <%=totalPage%> <a href="#"
						onclick="callPage('N', 'N', '<%=totalPage%>', 'ApprovalSettings_callPage.action')">
					<img title="Next Page" src="../pages/common/img/next.gif"
						class="iconImage" /> </a>&nbsp; <a href="#"
						onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ApprovalSettings_callPage.action');">
					<img title="Last Page" src="../pages/common/img/last.gif"
						width="10" height="10" class="iconImage" /> </a></td>
				</s:if>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2" width="100%">
			<table class="formbg" width="100%" cellspacing="2" cellspacing="2"
				border="0">
				<tr>
					<td colspan="1" class="txt" width="60%"><strong
						class="text_head">Approval Setting</strong></td>
					<td colspan="1" align="right" width="40%"><input type="button"
						id="ctrlShow" class="delete" theme="simple" value=" Delete"
						onclick="return chkDelete();" /></td>
				</tr>


				<!-- start iteraor -->
				
					<tr>
						<td colspan="2">
						<%
						try {
						%>
						<table width="100%" border="0" class="formbg" cellspacing="2"
							cellspacing="2">
							<tr>

								<td class="formtext">
								<table width="100%" border="0" cellpadding="2" cellspacing="2">
									<tr>

										
										<td class="formth" align="center" width="10%" colspan="1"><label
											class="set" name="sno" id="sno"
											ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
										<td class="formth" align="center" width="90%" colspan="1"><label
											class="set" name="division" id="division"
											ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>

										<s:if test="modeLength">
											<td align="right" class="formth" id="ctrlShow"
												nowrap="nowrap" width="35%" colspan="1"><input
												type="checkbox" id="selAll" style="cursor: hand;"
												title="Select all records to remove"
												onclick="selectAllRecords(this);" /></td>
										</s:if>
									</tr>
									<s:if test="modeLength">

										<%
										int count = 0;
										%>
										<%!int d = 0;%>
										<%
												int i = 0;
												int cn = pageNo * 20 - 20;
										%>



										<s:iterator value="divisionList">
											<tr <%if(count%2==0){
												%> class="tableCell1"
												<%}else{%> class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callForEdit('<s:property value="divisionCode"/>');">


												<td width="10%" align="center" class="sortableTD"><%=++cn%>
												<%
												++i;
												%><s:hidden name="srNo" /></td>
												<s:hidden value="%{divisionCode}"
													id='<%="divisionCode" + i%>'></s:hidden>
												<script type="text/javascript">
													records[<%=i%>] = document.getElementById('divisionCode' + '<%=i%>').value;
												</script>
												<td width="90%" align="left" class="sortableTD" colspan="1"><s:property
													value="approvalDivision" /> <input type="hidden"
													name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
												<td id="ctrlShow" align="center" nowrap="nowrap"
													class="sortableTD"><input type="checkbox"
													class="checkbox" id="confChk<%=i%>" name="confChk"
													onclick="callForDelete1('<s:property value="divisionCode"/>','<%=i%>')" /></td>
											</tr>

										</s:iterator>
										<%
										d = i;
										%>
									</s:if>
								</table>

								<%
									} catch (Exception e) {
									}
								%>
								</td>
							</tr>


						</table>
						</td>
					</tr>
				
				<tr>
					<td width="100%">
					<table width="100%" border="0" cellpadding="2" cellspacing="2">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

							<td width="20%" align="right"><s:if test="modeLength">
								<b>Total No. Of Records:</b>&nbsp;<s:property
									value="totalNoOfRecords" />
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
function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ApprovalSettings_addNew.action';
		document.getElementById('paraFrm').submit();
	}


function searchFun() {
		
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='ApprovalSettings_f9action.action';
		document.getElementById("paraFrm").submit();
	}


function callForDelete1(id,i)
	   {
		   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   {
	   document.getElementById('hdeleteCode'+i).value="";
   }
}

function callForEdit(id){
	callButton('NA', 'Y', 2);
	  
	  
	  	document.getElementById('paraFrm_divisionCode').value=id;
	   	document.getElementById("paraFrm").action="ApprovalSettings_editData.action";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }

function selectAllRecords(object) {
		if(object.checked) {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = true;
				document.getElementById('hdeleteCode' + i).value = records[i];
			}
		} else {
			for(var i = 1; i <= records.length; i++) {
				document.getElementById('confChk' + i).checked = false;
				document.getElementById('hdeleteCode' + i).value = "";
			}
		}
	}


function chk(){
   
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	  // alert('11');  
	    return true;
	   }	   
	  }
	  return false;
	}

	function chkDelete()
	{
	 
	 if(chk()){
	
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	   document.getElementById('paraFrm').action="ApprovalSettings_deleteChkRecords.action";
	   document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	document.getElementById('selAll').checked = false;
	    var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	
	}
	     return false;
	 }
	 }
	 else {
	 alert('please select atleast one record');
	 return true ;
	 }
	
}


	function callPage(id, pageImg, totalPageHid, action) {		
		try
		{
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('myPage1').value; 
		
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField').value = actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField').value=actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPage1').value = id;
		document.getElementById('paraFrm').action = action;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').submit();
		 }catch(e){alert(e);}
	}

function callPageText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;
		 	var actPage = document.getElementById('myPage1').value;   
	     
		 	 
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
	        
	         document.getElementById('myPage1').value=pageNo;
		   
			document.getElementById('paraFrm').action=action;
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').submit();
		}
		}catch(e){alert(e);}
	}


</script>
