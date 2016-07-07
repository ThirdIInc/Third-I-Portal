<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var records = new Array();
</script>
<s:form action="VendorMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">

	<s:hidden name="vendorCode" />
	<s:hidden name="vendorName" />
	<s:hidden name="einNumber" />
	<s:hidden name="postboxAddress" />
	<s:hidden name="zip" />
	<s:hidden name="city" />
	<s:hidden name="phoneNumber" />
	<s:hidden name="contactName" />
	<s:hidden name="sendPO" />
	
	<s:hidden name="remitName" />
	<s:hidden name="remitAddress" />
	<s:hidden name="remitCity" />
	<s:hidden name="remitState" />
	<s:hidden name="discPercent" />
    <s:hidden name="netDays" />
	<s:hidden name="comments1" />
	<s:hidden name="classCode" />
	<s:hidden name="minOrder" />
	<s:hidden name="freightMessage" />
	<s:hidden name="taxMessage" />
	<s:hidden name="shipVia" />
	<s:hidden name="fob" />
	<s:hidden name="comments2" />

	<table width="100%" border="0" align="right" class="formbg">

		<tr>
			<td valign="bottom" class="txt" colspan="5">
			<table width="100%" border="0" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Vendor
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
			<td colspan="5">
			<table width="100%">
				<tr>
					<td width="30%" colspan="1"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<%
						int totalPage = 0;
						int pageNo = 0;
					%>
					<s:if test="modeLength">
						<td id="ctrlShow" width="70%" align="right" class="" colspan="4"><b>Page:</b>
						<%
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

		<td colspan="5">
		<table class="formbg" width="100%" cellspacing="0" cellspacing="1"
			border="0">
			<tr>
				<td colspan="1" class="txt"><strong class="text_head">Vendors
				List</strong></td>
				<td align="right" colspan="4"><input type="button"
					id="ctrlShow" class="delete" theme="simple" value=" Delete"
					onclick="return chkDelete();" /></td>
			</tr>
			<!-- start iteraor -->



			<tr>
				<td colspan="5">
				<%
					try {
					%>
				<table width="100%" border="0" class="formbg">
					<tr>

						<td class="formtext">
						<table width="100%" border="0">
							<tr>

								<s:hidden name="myPage" id="myPage" />
								<td class="formth" align="center" width="5%" colspan="1"><label
									class="set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>

								<td class="formth" align="center" width="15%" colspan="1"><label
									class="set" name="ein" id="ein" ondblclick="callShowDiv(this);"><%=label.get("ein")%></label></td>

								<td class="formth" align="center" width="50%" colspan="1"><label
									class="set" name="vendor_name" id="vendor_name"
									ondblclick="callShowDiv(this);"><%=label.get("vendor_name")%></label></td>

								<td class="formth" align="center" width="25%" colspan="1"><label
									class="set" name="app_date" id="app_date"
									ondblclick="callShowDiv(this);"><%=label.get("app_date")%></label></td>

								<s:if test="modeLength">
									<td align="right" class="formth" id="ctrlShow" nowrap="nowrap"
										width="5%" colspan="1"><input type="checkbox" id="selAll"
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
									<s:iterator value="vendorsList">
										<tr <%if(count%2==0){
												%> class="tableCell1"
											<%}else{%> class="tableCell2" <%	}count++; %>
											onmouseover="javascript:newRowColor(this);"
											title="Double click for edit"
											onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
											ondblclick="javascript:callForEdit('<s:property value="vendorCode"/>');">


											<td width="5%" align="center" class="sortableTD" colspan="1"><%=++cn%>
											<%
												++i;
												%><s:hidden name="srNo" /></td>
											<s:hidden value="%{vendorCode}" id='<%="vendorCode" + i%>'></s:hidden>
											<script type="text/javascript">
													records[<%=i%>] = document.getElementById('vendorCode' + '<%=i%>').value;
												</script>
											<td width="15%" align="left" class="sortableTD" colspan="1"><s:property
												value="einNumber" /> <input type="hidden"
												name="hdeleteCode" id="hdeleteCode<%=i%>" /></td>

											<td class="sortableTD" width="50%" colspan="1">&nbsp; <s:property
												value="vendorName" /></td>

											<td class="sortableTD" width="25%" colspan="1">&nbsp; <s:property
												value="vendorAppDate" /></td>

											<td id="ctrlShow" align="center" nowrap="nowrap" colspan="1"
												class="sortableTD" width="5%"><input type="checkbox"
												class="checkbox" id="confChk<%=i%>" name="confChk"
												onclick="callForDelete1('<s:property value="vendorCode"/>','<%=i%>')" /></td>
										</tr>

									</s:iterator>
									<%
										d = i;
										%>
								</s:if>
						</table>
						</td>
					</tr>

					<%
								} catch (Exception e) {
								}
							%>



				</table>
				</td>
			</tr>

			<tr>
				<td colspan="5">
				<table border="0" width="100%">
					<tr>
						<td width="30%" colspan="1"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="70%" colspan="4" align="right"><s:if test="modeLength">
							<b>Total No. Of Records:</b>&nbsp;<s:property
								value="totalNoOfRecords" />
						</s:if></td>

					</tr>
				</table>
				</td>
			</tr>

		</table>
		</td>
	</table>
</s:form>
<script>

function callForEdit(id){
	/*alert("id"+id);*/
	callButton('NA', 'Y', 2);
	  	document.getElementById('paraFrm_vendorCode').value=id;
	   	document.getElementById("paraFrm").action="VendorMaster_editVendorApp.action";
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   }



function callForDelete1(id,i)
	   {
	 /*alert("i am in dete fun in regionMasterInput jsp");*/
	   /** var flag='<%=d %>';*/
	/*alert('id----1-----'+id);*/
	/*alert('i----1-----'+i);*/
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   {
	   document.getElementById('hdeleteCode'+i).value="";
   }
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
	/*alert("chkDelete");*/
	 
	 if(chk()){
	
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	   document.getElementById('paraFrm').action="VendorMaster_deleteChkRecords.action";
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

function addnewFun() {
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'VendorMaster_addNew.action';
		document.getElementById('paraFrm').submit();
	}


function appEdit(id)
{ 
/*alert("id"+id);*/
		
	    document.getElementById('paraFrm_vendorCode').value =id;
		document.getElementById('paraFrm').action ='VendorMaster_editVendorApp.action';		
		document.getElementById('paraFrm').submit();

}


</script>