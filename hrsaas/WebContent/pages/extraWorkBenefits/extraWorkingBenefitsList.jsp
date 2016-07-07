<!-- @author: Reeba Joseph @date: 22 OCT 2009 -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="ExtraWorkingBenefits" method="post" id="paraFrm" validate="true"
	target="main" theme="simple">
	<s:hidden name="show" value="%{show}" />
	<s:hidden name="hiddencode" />
	<s:hidden name="benefitsFor" />
	<s:hidden name="benefitsType" />
	<s:hidden name="benefitsID" /><s:hidden name="divisionName" />
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Extra
					Working Benefits </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr class="formbg">
					<td width="20%" class="formtxt"></td>
					<%	int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("pageNo");
						%>
					<!-- Paging implementation -->
					<td id="showCtrl" width="80%" align="right"><s:if
						test="modeLength">
						<b>Page:</b>
						<a href="#"
							onclick="callPage('1', 'F', '<%=totalPage%>', 'ExtraWorkingBenefits_input.action');">
						<img title="First Page" src="../pages/common/img/first.gif"
							width="10" height="10" class="iconImage" /> </a>&nbsp;
						<a href="#"
							onclick="callPage('P', 'P', '<%=totalPage%>', 'ExtraWorkingBenefits_input.action');">
						<img title="Previous Page" src="../pages/common/img/previous.gif"
							width="10" height="10" class="iconImage" /> </a>
						<input type="text" name="pageNoField" id="pageNoField" size="3"
							value="<%=pageNo%>" maxlength="10"
							onkeypress="callPageText(event, '<%=totalPage%>', 'ExtraWorkingBenefits_input.action');return numbersOnly();" /> of <%=totalPage%>
						<a href="#"
							onclick="callPage('N', 'N', '<%=totalPage%>', 'ExtraWorkingBenefits_input.action')">
						<img title="Next Page" src="../pages/common/img/next.gif"
							class="iconImage" /> </a>&nbsp;
						<a href="#"
							onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ExtraWorkingBenefits_input.action');">
						<img title="Last Page" src="../pages/common/img/last.gif"
							width="10" height="10" class="iconImage" /> </a>
					</s:if></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="sortable">
				<tr class="td_bottom_border">
					<s:hidden name="myPage" id="myPage" />
					<td width="10%" valign="top" class="formth"><label
						name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
					<td width="35%" valign="top" class="formth"><label
						name="benefit.for" id="benefit.for" ondblclick="callShowDiv(this);"><%=label.get("benefit.for")%></label></td>
					<td width="35%" valign="top" class="formth"><label
						name="benefit.type" id="benefit.type"
						ondblclick="callShowDiv(this);"><%=label.get("benefit.type")%></label></td>
					<s:if test="modeLength">
						<td align="center" class="formth" nowrap="nowrap"><input
							type="button" id="ctrlShow" class="token" value="Remove"
							onclick="return chkDelete();" /> <br>
						<input type="checkbox" id="ctrlShow" style="cursor: hand;"
							title="Select all records to remove"
							onclick="selectAllRecords(this);" /></td>
					</s:if><s:else>
						<td align="center" class="formth" nowrap="nowrap">Remove</td>
					</s:else>
				</tr>
				<s:if test="noData">
					<tr>
						<td width="100%" colspan="7" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
				</s:if>
				<%int count=0; %>
				<%!	int d = 0;%>
				<%	int t = 0; int cn = pageNo * 20 - 20;
				%>
				<s:iterator value="tableList">

					<tr class="sortableTD" <%if(count%2==0){
							%>
						class="tableCell1" <%}else{%> class="tableCell2" <%	}count++; %>
						onmouseover="javascript:newRowColor(this);"
						title="Double click for edit"
						onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
						ondblclick="javascript:callForEdit('<s:property  value="benefitsIDItt"/>');">
						<td class="sortableTD" width="10%"><%=++cn%>
						<%++t;%> <input type="hidden"
							value='<s:property	value="benefitsIDItt" />' id="benefitsIDItt<%=t %>" />
						</td>
						<script type="text/javascript">
							records[<%=t%>] = document.getElementById('benefitsIDItt' + '<%=t%>').value;
						</script>
						<td class="sortableTD" width="35%"><s:property
							value="benefitsForItt" /></td>
						<td class="sortableTD" width="35%"><s:property
							value="benefitsTypeItt" /><input type="hidden"
							name="hdeleteCode" id="hdeleteCode<%=t%>" /></td>
						<td id="ctrlShow" align="center" nowrap="nowrap" width="20%"
							class="sortableTD"><input type="checkbox" class="checkbox"
							id="confChk<%=t%>" name="confChk"
							onclick="callForDelete1('<s:property  value="benefitsIDItt"/>','<%=t%>')" />
						</td>
					</tr>
				</s:iterator>

				<tr>
					<td colspan="7" align="right"><strong>Total Records:
					</strong><s:property value="totalRecords" /></td>
				</tr>
				<%d = t;%>
			</table>
			</td>
		</tr>

	</table>
</s:form>

<script>
	window.onload = document.getElementById('pageNoField').focus();
	
	function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ExtraWorkingBenefits_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'ExtraWorkingBenefits_search.action';
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
	
	function callForEdit(benefitsCode){
		//alert(benefitsCode);
		callButton('NA', 'Y', 2);
	  	try{
	  	document.getElementById('paraFrm_hiddencode').value=benefitsCode;
		document.getElementById("paraFrm").action="ExtraWorkingBenefits_callforedit.action?benefitsCode="+benefitsCode; 
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
	    }catch(e){
	    	alert(e);
	    }
	}

	function callForDelete1(id,i){
		if(document.getElementById('confChk'+i).checked == true){	  
			document.getElementById('hdeleteCode'+i).value=id;
		}else
	    	document.getElementById('hdeleteCode'+i).value="";
	}
  	
	function chkDelete(){
		var flag='<%=d %>';
		if(chk()){
			 var con=confirm('<%= label.get("confirm.delete")%>');
			 if(con){
		  		document.getElementById('paraFrm').action="ExtraWorkingBenefits_delete1.action";
		  		document.getElementById('paraFrm').submit();
		 	 }else
		     	return false;
		} else {
		 alert('Please select atleast one record to delete');
		 return false;
		}
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
	
	function selectAllRecords(object) {
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
		}catch(e){
			//alert(e);
		}
	}
	
</script>