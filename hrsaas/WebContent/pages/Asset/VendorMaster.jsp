<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="VenueMaster" id="paraFrm" validate="true" target="main"
	theme="simple">

	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="vendorCode" />
	<s:hidden name="vendorName" />
	<s:hidden name="myPage" id="myPage" />
	<s:hidden name="vendorAdd" />
	<s:hidden name="vendorCon" />
	<s:hidden name="ctyId" />
	<s:hidden name="stateId" />
	<s:hidden name="vendorCty" />
	<s:hidden name="vendorState" />

	<table width="100%" border="0" class="formbg" align="right">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Vendor
					Master</strong></td>
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
			<table width="100%" align="left" cellspacing="0" cellpadding="0">
				<tr>
					<td width="70%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<%
						int totalPage = 0;
						int pageNo = 0;
					%>
					<s:if test="recordsLength">
						<td id="ctrlShow" width="30%" align="right"><b>Page:</b> <%
							totalPage = (Integer) request.getAttribute("totalPage");
							pageNo = (Integer) request.getAttribute("pageNo");
						%> <a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'VendorMaster_callPage.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'VendorMaster_callPage.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a> <input type="text"
							name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>"
							maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'VendorMaster_callPage.action');return numbersOnly();" />
						of <%=totalPage%> <a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'VendorMaster_callPage.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp; <a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'VendorMaster_callPage.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a></td>
					</s:if>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				class="formbg">
				<tr>
					<td class="txt" colspan="2"><strong class="text_head">Vendor
					Master</strong></td>
					<td align="right"><input type="button" id="ctrlShow"
						class="delete" value="  Delete" onclick="return chkDelete();" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<%
						try {
						%>
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td class="formtext">
							<table width="100%" border="0" cellpadding="1" cellspacing="1">
								<tr class="td_bottom_border">

									<td class="formth" align="center"><label class="set"
										name="vendor.srno" id="vendor.srno"
										ondblclick="callShowDiv(this);"><%=label.get("vendor.srno")%></label>

									</td>
									<td class="formth" align="center"><label class="set"
										name="vendor.name" id="vendor.name1"
										ondblclick="callShowDiv(this);"><%=label.get("vendor.name")%></label>
									</td>

									<td class="formth" align="center" width="05%"><label
										class="set" name="is.active" id="is.active1"
										ondblclick="callShowDiv(this);"><%=label.get("is.active")%></label>
									</td>



									<s:if test="recordsLength">
										<td align="center" class="formth" width="10%" id="ctrlShow">
										<input type="checkbox" style="cursor: hand;" id="selAll"
											title="Select all records to remove"
											onclick="selectAllRecords(this);" /></td>
									</s:if>

								</tr>

								<s:if test="recordsLength">
									<%
										int count = 0;
										%>
									<%!int d = 0;%>
									<%
												int i = 0;
												int cn = pageNo * 20 - 20;
										%>


									<s:iterator value="recordList">
										<tr class="sortableTD" <%if(count%2==0){
												%>
											class="tableCell1" <%}else{%> class="tableCell2"
											<%	}count++; %> onmouseover="javascript:newRowColor(this);"
											title="Double click for edit"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											ondblclick="javascript:callForEdit('<s:property value="VendorCode" />');">


											<td width="10%" align="center" class="sortableTD"><%=++cn%>
											<%
												++i;
												%> <s:hidden name="srNo" /></td>

											<td class="sortableTD" width="50%" nowrap="nowrap"
												align="left"><input type="hidden" name="hdeleteCode"
												id="hdeleteCode<%=i%>" /> <s:property value="VendorName" />
											<s:hidden value="%{VendorCode}" id='<%="VendorCode" + i%>'></s:hidden>
											</td>
											<script type="text/javascript">												
													records[<%=i-1%>] = document.getElementById('VendorCode' + '<%=i%>').value;
												</script>
											
											<td width="40%" align="left" class="sortableTD"><s:property
															value="isActive" /></td>
											
											<td align="center" nowrap="nowrap" class="sortableTD"
												id="ctrlShow"><input type="checkbox" class="checkbox"
												id="confChk<%=i%>" name="confChk"
												onclick="callForDelete1('<s:property value="VendorCode" />','<%=i%>')" />
											</td>


										</tr>

									</s:iterator>
									<%
												d = i;
												i = 0;
										%>
								</s:if>
							</table>
							</td>
						</tr>
						<s:if test="recordsLength"></s:if>
						<s:else>
							<tr>
								<td>
								<table width="100%">
									<tr>
										<td align="center"><font color="red">No Data To
										Display</font></td>
									</tr>
								</table>
								</td>
							</tr>
						</s:else>
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
			<table width="100%">
				<td width="79%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td align="right"><s:if test="recordsLength">
					<b>Total No. Of Records:</b>&nbsp;<s:property value="totalRecords" />
				</s:if></td>
			</table>
			</td>
		</tr>
	</table>


</s:form>

<script>
function addnewFun() {
	   
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'VendorMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='VendorMaster_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function reportFun() {
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = 'VendorMaster_report.action';
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
		cell.className='Cell_bg_first'; 
	//	document.getElementById(cell).backgroundColor="#FFA31D";
	}
		
  	function callForEdit(id){
  
  	callButton('NA', 'Y', 2);
	  	document.getElementById('paraFrm_hiddencode').value=id;
	   	document.getElementById("paraFrm").action="VendorMaster_calforedit.action";
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
	   document.getElementById('paraFrm').action="VendorMaster_delete1.action";
	   document.getElementById('paraFrm').target = "_self";
	    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
	    
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
	 var flag='<%=d %>';
	 
	try{
		if(object.checked) {
		
			for(var i = 1; i <= records.length; i++) {			
				document.getElementById('confChk'+i).checked = true;
				document.getElementById('hdeleteCode'+ i).value = records[i];
			}
		} else {
		
			for(var i = 1; i <= records.length; i++) {			
				document.getElementById('confChk' + i).checked = false;
				document.getElementById('hdeleteCode' + i).value = "";
			}
		}
		}catch(e){
		
		}
	}
	
	function callForDelete1(id,i)
	   {
	   
	   var flag='<%=d %>';
	  //alert('id----1-----'+id);
	   //alert('i----1-----'+i);
	
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
	   document.getElementById('paraFrm').action="VendorMaster_delete1.action";
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
	 alert('Please select atleast one record');
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
		document.getElementById("paraFrm").action = 'VendorMaster_'+action+'.action';
		document.getElementById("paraFrm").submit();
	}
	
	function numbersonly(myfield){
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}
	
	
function clearState()
{
document.getElementById('paraFrm_vendorState').value="";
javascript:callsF9(500,325,'VendorMaster_f9city.action');
}	
   	
  	</script>



