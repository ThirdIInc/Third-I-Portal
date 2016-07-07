<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@include
	file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp"%>

<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="PromotionMaster" id="paraFrm" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Promotion
					</strong></td>
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
			<table width="100%">
				<tbody>
					<tr>
						<td width="70%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<%
							int totalPage = 0;
							int pageNo = 0;
						%>
						<td width="30%" align="right" class="" id="ctrlShow"><b>Page:</b>
						<%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
							
						%> <a
							onclick="callPage('1', 'F', '<%=totalPage%>', 'PromotionMaster_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/first.gif" title="First Page"> </a>&nbsp;
						<a
							onclick="callPage('P', 'P', '<%=totalPage%>', 'PromotionMaster_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/previous.gif" title="Previous Page">
						</a>
						 <!--<input type="text"
							onkeypress="callPageText(event, '1', 'PromotionMaster_input.action');return numbersOnly();"
							maxlength="10" value='<%=pageNo%>' size="3" id="pageNoField"
							name="pageNoField"> of <%=totalPage%> 
							
								--><input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'PromotionMaster_input.action');return numbersOnly();" /> of <%=totalPage%>
							
							<a
							onclick="callPage('N', 'N', '<%=totalPage%>', 'PromotionMaster_input.action')"
							href="#"> <img class="iconImage"
							src="../pages/common/img/next.gif" title="Next Page"> </a>&nbsp;
						<a
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'PromotionMaster_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/last.gif" title="Last Page"> </a></td>
					</tr>
					<tr>
						<td colspan="3">
						<table width="100%" cellspacing="2" cellpadding="2" border="0"
							class="formbg">
							<tbody>
								<tr>
									<td class="formth"><label class="set" id="srNo"
										name="srNo" onDblClick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
									<td class="formth"><label class="set" id="employeeid"
										name="employeeid" onDblClick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
									<td class="formth"><label class="set" id="employee"
										name="employee" onDblClick="callShowDiv(this);"><%=label.get("employee")%></label></td>
									<td class="formth"><label class="set" id="effective.date"
										name="effective.date" onDblClick="callShowDiv(this);"><%=label.get("effective.date")%></label></td>
									<td class="formth"><label class="set" id="status"
										name="status" onDblClick="callShowDiv(this);"><%=label.get("status")%></label></td>
									<td width="15%" no="" class="formth"><input type="button"
										name="delete" value="Delete" class="Delete"
										onclick="callDeletePromotion();"></td>
									
								</tr>
								<%
									int count = 0;
									int cn = pageNo * 20 - 20;
									%>
								<%
										int k = 1;
										int c = 0;
										int d = 0;
										
									%>

								<!------------- Iterator Starts --------->

								<s:iterator value="iteratorlist">

									<tr <%if(count%2==0){
									%> class="tableCell1"
										<%}else{%> class="tableCell2" <%	}count++; %>
										onmouseover="javascript:newRowColor(this);"
										title="Double click for edit"
										onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
										ondblclick="javascript:callEdit('<s:property value="pramotionCodeItt" />','<s:property value="statusItt" />');">
										<td class="sortableTD" width="5%"><%=++cn%><s:hidden
											name="srNo" /></td>
										<td class="sortableTD" align="left"><s:hidden
											name="pramotionCodeItt" /> <s:property value="employeeIdItt" /></td>
										<td class="sortableTD" align="left"><s:property
											value="empNameItt" /></td>
										<td class="sortableTD" align="center"><s:property
											value="effectiveDateItt" /></td>
										<td class="sortableTD" align="center"><s:property
											value="statusItt" /></td>
											
									<input type="hidden"
															name="hdeleteCode" id="hdeleteCode<%=c%>" />		
											
										<s:if test="%{statusItt == 'Locked'}">

											<td align="center" class="sortableTD" id="ctrlShow"><input type="checkbox"
												name="chkPromotion" class="sortableTD" id="<%="checkedBox"+c%>"
												disabled="disabled"></td>
										</s:if>
										<s:else>
										
											<td align="center" class="sortableTD" id="ctrlShow">
											
											<input type="checkbox"
															class="sortableTD" id="checkedBox<%=c%>" name="checkedBox"
															onclick="callForDelete1('<s:property value="pramotionCodeItt"/>','<%=c%>')" />
										 
												</td>

										</s:else>
										<%
											k++;
											c++;
											 
										%>

									</tr>
								</s:iterator>
								<!------------- Iterator Ends --------->
								<input type="hidden" id="count" name="count" value="<%=c%>" />
								<%
									if (c == 0) {
									%>
								<tr align="center">
									<td colspan="6" class="sortableTD" width="100%"><font
										color="red">No Data to display</font></td>
								</tr>
								<%
									}
									%>
						</table>
						</td>
					</tr>

					<tr>
						<td width="70%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						
					</tr>
			</table>
		</tr>

	</table>
	<s:hidden name="empToken" />
	<s:hidden name="empName" />
	<s:hidden name="effDate" />
	<s:hidden name="lockStatus" />
	<s:hidden name="empCode" />
	<s:hidden name="branch" />
	<s:hidden name="dept" />
	<s:hidden name="desg" />
	<s:hidden name="grade" />
	<s:hidden name="div" />
	<s:hidden name="joinDate" />
	<s:hidden name="promDate" />
	<s:hidden name="branchCode" />
	<s:hidden name="deptCode" />
	<s:hidden name="desgCode" />
	<s:hidden name="gradeCode" />
	<s:hidden name="divCode" />
	<s:hidden name="repToCode" />
	<s:hidden name="promCode" />
	<s:hidden name="prBranCode" />
	<s:hidden name="proBranch" />
	<s:hidden name="prDeptCode" />
	<s:hidden name="proDept" />
	<s:hidden name="prDesgCode" />
	<s:hidden name="proDesg" />
	<s:hidden name="prGrdCode" />
	<s:hidden name="proGrade" />
	<s:hidden name="prRepCode" />
	<s:hidden name="currentRole" />
	<s:hidden name="myPage" id="myPage"/>
	
</s:form>

<script>

	function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'PromotionMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}

	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action="PromotionMaster_f9proCode.action";
		document.getElementById("paraFrm").submit();
	}
	
	function callEdit(autoCode,status){
	 callButton('NA', 'Y', 2);
	 	document.getElementById("paraFrm").action="PromotionMaster_callForEdit.action?autoCode="+autoCode+"&status="+status;
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
		 
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
	
	function callCheckStatus(promoCode,obj) {
		return promoCode; 
	}
	
	function callForDelete1(id,i) {
		 
		if(document.getElementById('checkedBox'+i).checked == true)
		{
			document.getElementById('hdeleteCode'+i).value=id;
		}
		else
		{
			document.getElementById('hdeleteCode'+i).value="";
		}
	}
	
	function callDeletePromotion(){
	var selected=false;
		try {
			var count = document.getElementById('count').value;
			
			for(var check=0;check < count;check++) {
				if(document.getElementById('checkedBox'+check).checked){
				
					selected = true;
					
				}else{
					
				}
			}
			if(selected){
					document.getElementById("paraFrm").action="PromotionMaster_deletePromotionRecord.action";
					document.getElementById("paraFrm").submit();
					}else{
					alert("Select atleast one record to delete");
					return false;
					}
		} catch(e){alert(e);}
	}
	
</script>

