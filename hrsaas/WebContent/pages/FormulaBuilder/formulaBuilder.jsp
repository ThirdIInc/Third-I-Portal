<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
	var records = new Array();
</script>

<s:form action="FormulaBuilder" id="paraFrm" validate="true" name="paraFrm" theme="simple">
<s:hidden name="myPage" id="myPage" />
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
					<td width="93%" class="txt"><strong class="text_head">Formula
					Builder </strong></td>
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
							onclick="callPage('1', 'F', '<%=totalPage%>', 'FormulaBuilder_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/first.gif" title="First Page"> </a>&nbsp;
						<a
							onclick="callPage('P', 'P', '<%=totalPage%>', 'FormulaBuilder_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/previous.gif" title="Previous Page">
						</a> <input type="text"
							onkeypress="callPageText(event, '<%=totalPage%>', 'FormulaBuilder_input.action');return numbersOnly();"
							maxlength="10" value='<%=pageNo%>' size="3" id="pageNoField"
							name="pageNoField"> of <%=totalPage%> <a
							onclick="callPage('N', 'N', '<%=totalPage%>', 'FormulaBuilder_input.action')"
							href="#"> <img class="iconImage"
							src="../pages/common/img/next.gif" title="Next Page"> </a>&nbsp;
						<a
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'FormulaBuilder_input.action');"
							href="#"> <img height="10" width="10" class="iconImage"
							src="../pages/common/img/last.gif" title="Last Page"> </a></td>
					</tr>
					<tr>
						<td colspan="3">
						<table width="100%" cellspacing="2" cellpadding="2" border="0"
							class="formbg">
							<tr>
							<td>
							<strong class="text_head">Formula Builder </strong></td>
							<td align="right"><input
												type="button" name="delete" value="Delete" class="Delete"
												onclick="chkDelete();"></td>
							</tr>
							<tr>
								<td colspan="3">
								<table width="100%" cellspacing="2" cellpadding="2" border="0"
									class="formbg">
									<tbody>

										<tr>
											<td class="formth"><label class="set" id="srNo"
												name="srNo" onDblClick="callShowDiv(this);"><%=label.get("srNo")%></label></td>
											<td class="formth"><label class="set" id="formulaName"
												name="formulaName" onDblClick="callShowDiv(this);"><%=label.get("for.name")%></label></td>
											<td width="15%" no="" class="formth"><input type="checkbox"
												onclick="selectAllRecords(this);"
												title="Select all records to remove" style="" id="selAll">
											</td>
										</tr>
										<%
										int count = 0;
										%>
										<%!int d = 0;%>
										<%
											int cnt = pageNo * 20 - 20;
											int i = 0;
										%>

										<!------------- Iterator Starts --------->

										<s:iterator value="iteratorlist">

											<tr <%if(count%2==0){
									%> class="tableCell1"
												<%}else{%> class="tableCell2" <%	}count++; %>
												onmouseover="javascript:newRowColor(this);"
												title="Double click for edit"
												onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
												ondblclick="javascript:callEdit('<s:property value="formulaIdItt" />');">
												<td class="sortableTD" width="5%"><%=++cnt%> <%
 ++i;
 %><s:hidden
													name="srNo" /> <s:hidden name="formulaIdItt"
													id='<%="formulaIdItt"+i%>' /></td>
												<script type="text/javascript">
													records[<%=i%>] = document.getElementById('formulaIdItt' + '<%=i%>').value;									
												</script>
												<td class="sortableTD" align="left"><s:property
													value="formulaNameItt" /></td>

												<input type="hidden" name="hdeleteCode"
													id="hdeleteCode<%=i%>" />
												<td align="center" class="sortableTD" id="ctrlShow"><input
													type="checkbox" class="checkbox" id="confChk<%=i%>"
													name="confChk"
													onclick="callSingleDelete('<s:property value="formulaIdItt"/>','<%=i%>')" />
												</td>



											</tr>
										</s:iterator>
										<%
										d = i;
										%>
										<!------------- Iterator Ends --------->

										<%
										if (i == 0) {
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
				</tbody>
			</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
	<tr>
		<td width="70%"><jsp:include
			page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
	</tr>
	</table>

	<s:hidden name="frmbuildCode" />
	<s:hidden name="frmbuildName" />
	<s:hidden name="salCode" />
	<s:hidden name="salHead" />
	<s:hidden name="salprior" />
</s:form>
<script>

	function addnewFun() {
		
		document.getElementById('paraFrm').target = "_self";
		document.getElementById("paraFrm").action="FormulaBuilder_addNew.action";
		document.getElementById("paraFrm").submit();
	}
	
	function searchFun() {
	
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action="FormulaBuilder_f9action.action";
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
	
	function callEdit(autoCode){
	
		callButton('NA', 'Y', 2);
	 	document.getElementById("paraFrm").action="FormulaBuilder_callForEdit.action?autoCode="+autoCode;
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
	}
	
	function selectAllRecords(object){
		try{
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
			}catch(e){//alert(e)
			}
	}
	
	function chkDelete(){
	
	 if(chk()){
	 var con=confirm('Do you really want to delete the records ?');
	 if(con){
	   document.getElementById('paraFrm').action="FormulaBuilder_deleteFormula.action";
	    document.getElementById('paraFrm').submit();
	    }
	        else
	    {     
	    document.getElementById('selAll').checked=false;
	    var flag='<%=d %>';
	    for(var a=1;a<=flag;a++){	
	    document.getElementById('confChk'+a).checked =false;
	    document.getElementById('hdeleteCode'+a).value="";
	 	}
	     return false;
	     }
	     
	 }
	 else {
	 alert('Please Select Atleast one Record To Delete');
	 	 return false;
	 }
	 
	}
	function callSingleDelete(formulaId, i){
	
		if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=formulaId;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
	}
	
	function chk(){
	 var flag='<%=d %>';
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}
	
	
	
</script>

