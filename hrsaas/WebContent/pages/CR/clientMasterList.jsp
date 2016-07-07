<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="ClientMaster" id="paraFrm" validate="true" target="main"
	theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<!-- hiddencode used to get data back during edit function	-->
	<s:hidden name="modeLength" />
<s:hidden name="bean.clientUserNo" />
	<s:hidden name="myPageInProcess" id="myPageInProcess" />

	<!-- Table 1 Started	-->
	<table width="100%" border="0" class="formbg" align="right">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
	<!--	Table 2 Started  -->
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Client
					User</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			<!--	Table 2 Ended  -->
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
			<!--	Table 3 Started  -->
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<%
							int totalPage = 0;
							int pageNo = 0;
						%>
						
						<!--	Pagination code  -->
					<s:if test="modeLength">
						<td id="ctrlShow" width="30%" align="right" class=""><b>Page:</b>
						<%
								totalPage = (Integer) request.getAttribute("totalPage");
								pageNo = (Integer) request.getAttribute("pageNo");
							%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'ClientMaster_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'ClientMaster_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'ClientMaster_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'ClientMaster_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ClientMaster_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
				</tr>
			</table>
			<!--	Table 3 Ended  -->
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<!--	Table 4 Started  -->
			<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
				border="0">
				
				<tr>
					<td colspan="2" class="txt"><strong class="text_head">Client
					User</strong></td>
					<td align="right"><input type="button" id="ctrlShow"
						class="delete" value=" Delete" onclick="return chkDelete();" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<%
						try {
						%>
						<!--	Table 5 Started  -->
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td class="formtext">
							<!--	Table 6 Started  -->
							<table width="100%" border="0" cellpadding="1" cellspacing="1"
								class="sortableTD">
								<tr class="td_bottom_border">
									<s:hidden name="myPage" id="myPage" />
									<td class="formth" align="center"><label name="srno"
										id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
									<td class="formth" align="center"><label name="First.Name"
										id="First.Name" ondblclick="callShowDiv(this);"><%=label.get("First.Name")%></label></td>


									<td class="formth" align="center"><label name="Last.Name"
										id="Last.Name" ondblclick="callShowDiv(this);"><%=label.get("Last.Name")%></label></td>
									<td class="formth" align="center"><label name="user.id"
										id="user.id" ondblclick="callShowDiv(this);"><%=label.get("user.id")%></label>
									</td>
									<td class="formth" align="center" width="50%"><label
										class="set" name="Is.Active" id="Is.Active"
										ondblclick="callShowDiv(this);"><%=label.get("Is.Active")%></label></td>



									<s:if test="modeLength">
										<td align="center" class="formth" id="ctrlShow"
											nowrap="nowrap"><input type="checkbox" id="selAll"
											style="cursor: hand;" title="Select all records to remove"
											onclick="selectAllRecords(this);" /></td>
									</s:if>

									<s:if test="modeLength">
										<%
										int count = 0;
										%>
										<%!int d = 0;%>
										<%
												int i = 0;
												int cn = pageNo * 20 - 20;
										%>
									<!--	Client User List to display records stored in database  -->
										<s:iterator value="clientUserList">
											<tr class="sortableTD" <%if(count%2==0){
												%>
												class="tableCell1" <%}else{%> class="tableCell2"
												<%	}count++; %> onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callForEdit('<s:property value="ittClientUserNo"/>');">


												<td width="10%" align="center" class="sortableTD"><%=++cn%>
												<%
																		++i;
																		%>
												</td>
												<s:hidden value="%{ittClientUserNo}"
													id='<%="ittClientUserNo" + i%>'></s:hidden>
												<script type="text/javascript">
													records[<%=i%>] = document.getElementById('ittClientUserNo' + '<%=i%>').value;
												</script>
												<td width="30%" align="left" class="sortableTD"><s:property
													value="ittFirstName" /> <input type="hidden"
													name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>
												<td width="30%" align="left" class="sortableTD"><s:property
													value="ittLastName" /></td>
												<td width="50%" align="left" class="sortableTD"><s:property
													value="ittEmailId" /></td>
												<td width="40%" align="left" class="sortableTD"><s:property
													value="ittIsActive" /></td>



												<td id="ctrlShow" align="center" nowrap="nowrap"
													class="sortableTD"><input type="checkbox"
													class="checkbox" id="confChk<%=i%>" name="confChk"
													onclick="callForDelete1('<s:property value="ittClientUserNo"/>','<%=i%>')" /></td>

											</tr>

										</s:iterator>
										<!--	Client User List Ended  -->
										<%
										d = i;
										%>
									</s:if>
								</tr>

							</table>
							<!--	Table 6 Ended  -->
							<s:if test="modeLength"></s:if> <s:else>
								<table width="100%">
								<!--	Table 7 Started  -->
									<tr>
										<td align="center"><font color="red">No Data To
										Display</font></td>
									</tr>
								</table>
								<!--	Table 7 Ended  -->
							</s:else></td>
						</tr>
					</table>
					<!--	Table 5 Ended  -->
					<%
							} catch (Exception e) {
							}
						%>
					</td>
				</tr>
			</table>
			<!--	Table 4 Ended  -->
			</td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%">
			<!--	Table 8 Started  -->
				<td width="79%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td width="21%"><s:if test="modeLength">
					<b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" />
				</s:if></td>
			</table>
			<!--	Table 8 Ended  -->
			</td>
	</table>
	<!--	Table 1 Ended  -->

</s:form>

<script>
function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ClientMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='ClientMaster_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'ClientMaster_report.action';
		document.getElementById('paraFrm').submit();
	}
	
	
		
function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
		//cell.style.backgroundColor="#ffffff";
	}
	
		function newRowColor(cell) {
	//	cell.style.backgroundColor="#E2F2FE";
		//cell.style.cursor='hand';
		cell.className='onOverCell';
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}
		
  	function callForEdit(id){
  
  	
  	callButton('NA', 'Y', 2);
  	document.getElementById('paraFrm_hiddencode').value=id;
	  	
	document.getElementById("paraFrm").action="ClientMaster_editClientUserLstDtl.action";
	   //	document.getElementById("paraFrm").target="main";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
	    
	    
	  
   }
   
    function callForDelete1(id,i)
	   {
	
	   var flag='<%=d %>';
	
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
   }
	function chkDelete()
	{
	 
	 if(chk()){
	
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	   document.getElementById('paraFrm').action="ClientMaster_deleteClientUserLstDtl.action";
	   document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	    
	    var flag='<%=d %>';
	    document.getElementById('selAll').checked=false;
	  for(var a=1;a<=flag;a++){	
	document.getElementById('confChk'+a).checked = false;
	document.getElementById('hdeleteCode'+a).value="";
	
	}
	     return false;
	 }
	 }
	 else {
	 alert('please select atleast one record');
	 return false;
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
	
	function callForDelete1(id,i)
	   {
	   
	   var flag='<%=d %>';
	 // alert('id----1-----'+id);
	  // alert('i----1-----'+i);
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
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
   
   	function newRowColor(cell) {
	//	cell.style.backgroundColor="#E2F2FE";
		//cell.style.cursor='hand';
		 cell.className='Cell_bg_first'; 
		//cell.className='onOverCell';
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}
	
	function oldRowColor(cell,val) {
	
	if(val=='1'){
	 cell.className='tableCell2';
	}
	else cell.className='tableCell1';
		//cell.style.backgroundColor="#ffffff";
	}
  	
  	function callDelete(id) {
		if(document.getElementById(id).value == "") {
			alert("Please select a record to delete");
			return false;
		}
      	var conf = confirm("Do you really want to delete this record ?");
		if(conf) {
			document.getElementById('paraFrm').target = "_self";
			return true;
  		}
	  	return false;
	}
	
   function callSearch(action) {
  // alert ('x');
		myWin = window.open('','myWin','top=260,left=250,width=700,height=400,scrollbars=no,toolbars=no,status=yes,resizable=yes');
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'ClientMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
   	
  	</script>