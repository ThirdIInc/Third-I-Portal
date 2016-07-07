<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
	<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="TaxSlab" validate="true" id="paraFrm" theme="simple">
<s:hidden name="show" value="%{show}" />
<s:hidden name="taxSlab.fromYear" />
<s:hidden name="taxSlab.toYear" />
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
					<td width="93%" class="txt"><strong class="text_head">TDS
					Slab </strong></td>
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
					<tr>
						<td width="70%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<% int totalPage = 0; int pageNo = 0; %>
						<s:if test="modeLength">
						<td id="ctrlShow" width="30%" align="right" class="">
							<b>Page:</b>
							<%	 totalPage = (Integer) request.getAttribute("totalPage");
								 pageNo = (Integer) request.getAttribute("pageNo");
							%>
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'TaxSlab_input.action');">
								<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
							</a>&nbsp;
							<a href="#" onclick="callPage('P', 'P', '<%=totalPage%>', 'TaxSlab_input.action');" >
								<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
							</a> 
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'TaxSlab_input.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'TaxSlab_input.action')" >
								<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
							</a>&nbsp;
							<a href="#" onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'TaxSlab_input.action');" >
								<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
							</a>
						</td>
						</s:if>
					</tr>
				</table>
			</td>			
		</tr>
	<tr>
			<td colspan="3" width="100%">
				<table class="formbg" width="100%" cellspacing="0" cellspacing="1" border="0">
					<tr>
						<td colspan="2" class="txt"><strong class="text_head">Tax Slab List </strong></td>		
						<td  align="right">
							<input type="button" id="ctrlShow" class="delete" value=" Delete" onclick="return chkDelete();" />
						</td>
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
								<table width="100%" border="0" cellpadding="1" cellspacing="1"
									class="sortableTD">
									<tr class="td_bottom_border">
									<s:hidden name="myPage" id="myPage" />
									<td class="formth"><label class="set" id="srNo"
												name="srNo" onDblClick="callShowDiv(this);"><%=label.get("taxation.Srno")%></label></td>
											<td class="formth"><label class="set" id="fromYear"
												name="fromYear" onDblClick="callShowDiv(this);"><%=label.get("taxation.FinYrFrm")%></label></td>
											<td class="formth"><label class="set" id="toYear"
												name="toYear" onDblClick="callShowDiv(this);"><%=label.get("taxation.FinYrTo")%></label></td>
											<!--<td class="formth"><label class="set" id="empType"
												name="empType" onDblClick="callShowDiv(this);"><%=label.get("taxation.SlabType")%></label></td>
											--><s:if test="modeLength">
										<td width="3%" align="right" class="formth" id="ctrlShow"
											nowrap="nowrap"><input type="checkbox" id="selAll"
											style="cursor: hand;" title="Select all records to remove"
											onclick="selectAllRecords(this);" /></td>
									</s:if>
									
								
									
									<s:if test="modeLength">	
									<%int count=0; int count4=0;%>
										<%!int d=0; %>
										<%
											
										int i= pageNo*20-20;
											%>
									
										<s:iterator value="iteratorlist">
<%count4++; %>
											<tr <%if(count%2==0){
									%> class="tableCell1"
												<%}else{%> class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callEdit('<s:property value="taxFromYearItt" />','<s:property value="taxToYearItt" />');">
												<td class="sortableTD" width="5%"> <%=++i%>
												<s:hidden name="srNo" /></td>
												
												
													
												
												<td class="sortableTD" align="left"><s:property
													value="taxFromYearItt" /></td>
												<td class="sortableTD" align="left"><s:property
													value="taxToYearItt" />
													
													<input type="hidden" name="taxToYearItt"
													id="taxToYearItt<%=count4%>" value='<s:property value="taxToYearItt" />'/>
													<input type="hidden" name="taxFromYearItt"
													id="taxFromYearItt<%=count4%>" value='<s:property value="taxFromYearItt" />'/>
													</td>
												<!--<td class="sortableTD" align="center"><s:property
													value="taxEmpTypeItt" /> 
												 <s:hidden name="taxEmpGenderItt"/>
												--><input type="hidden" name="hdeleteCodeFromYear"
													id="hdeleteCodeFromYear<%=count4%>" />
													
												
													
												<input type="hidden" name="hdeleteCodeToYear"
													id="hdeleteCodeToYear<%=count4%>" />
													
													
												<!--<input type="hidden" name="hdeleteCodeEmpType"
													id="hdeleteCodeEmpType<%=i%>" />
												--><td align="center" class="sortableTD" id="ctrlShow">
										<input type="checkbox"
											   class="sortableTD" id="checkedBox<%=count4%>" name="checkedBox"
											   onclick="callForDelete1('<s:property value="taxFromYearItt" />','<s:property value="taxToYearItt" />','<%=count4%>')" /></td>
											</tr>
										</s:iterator>
										<%d=count4; %>
										</s:if>
								</tr>
									
								</table>
								<s:if test="modeLength"></s:if>
								<s:else>
									<table width="100%">
										<tr>
											<td align="center"><font color="red">No Data To Display</font></td>
										</tr>
									</table>
								</s:else>
								</td>
							</tr>
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



		


		
	</table>
	



</s:form>
<script>
	function addnewFun() {
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").action="TaxSlab_addNew.action";
		document.getElementById("paraFrm").submit();
	}
	
	
	function searchFun() {
	
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action="TaxSlab_f9taxslab.action";
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
	
	function callEdit(fromYearCode, toYearCode, empTypeCode){
	 callButton('NA', 'Y', 2);
	 	document.getElementById("paraFrm").action="TaxSlab_callForListEdit.action?fromYearCode="+fromYearCode+"&toYearCode="+toYearCode+"&empTypeCode="+empTypeCode;
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
		 
	}
	
	function callForDelete1(fromYearCode, toYearCode,i) {
		try{
		if(document.getElementById('checkedBox'+i).checked == true)
		{
			document.getElementById('hdeleteCodeFromYear'+i).value=fromYearCode;
			document.getElementById('hdeleteCodeToYear'+i).value=toYearCode;
			//document.getElementById('hdeleteCodeEmpType'+i).value=empTypeCode;
		} else {
			document.getElementById('hdeleteCodeFromYear'+i).value="";
			document.getElementById('hdeleteCodeToYear'+i).value="";
			//document.getElementById('hdeleteCodeEmpType'+i).value="";
		}
		} catch(e)
		{
			alert(e);
		}
	}
	
	function chkDelete() {
		var selected=false;
			var flag = <%=d%>;
			
			for(var check=1;check <= flag;check++) {
				
				 var fromYearCode = document.getElementById('taxFromYearItt'+check).value;
				  var toYearCode = document.getElementById('taxToYearItt'+check).value;
				  
				if(document.getElementById('checkedBox'+check).checked == true)
		{
			document.getElementById('hdeleteCodeFromYear'+check).value=fromYearCode;
			document.getElementById('hdeleteCodeToYear'+check).value=toYearCode;
			selected=true;
			//document.getElementById('hdeleteCodeEmpType'+check).value=empTypeCode;
		} else {
			document.getElementById('hdeleteCodeFromYear'+check).value="";
			document.getElementById('hdeleteCodeToYear'+check).value="";
			//document.getElementById('hdeleteCodeEmpType'+i).value="";
		}
			}
			if(selected){
				var con=confirm('Do you really want to delete the records ?');
	 				if(con){
	 					document.getElementById('paraFrm').target = "_self";
						document.getElementById("paraFrm").action="TaxSlab_deleteMultipleRecordsFromTaxSlabList.action";
						document.getElementById("paraFrm").submit();
					}
					}else{
						alert("Select atleast one record to delete");
						return false;
					}
		
	}
	function selectAllRecords(object) {
		try{
		var flag = <%=d%>;
			//alert(flag);
			for(var check=1;check <= flag;check++) {
				
				 var fromYearCode = document.getElementById('taxFromYearItt'+check).value;
				  var toYearCode = document.getElementById('taxToYearItt'+check).value;
				  
				if(document.getElementById('selAll').checked == true)
		{
		document.getElementById('checkedBox'+check).checked = true;
		
			document.getElementById('hdeleteCodeFromYear'+check).value=fromYearCode;
			document.getElementById('hdeleteCodeToYear'+check).value=toYearCode;
		
		} else {
			document.getElementById('hdeleteCodeFromYear'+check).value="";
			document.getElementById('hdeleteCodeToYear'+check).value="";
			document.getElementById('checkedBox'+check).checked = false;
		}
		}
		} catch(e)
		{
			alert(e);
		}
	}
	
	
</script>