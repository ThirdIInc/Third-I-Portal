<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
	var records = new Array();
</script>
<s:form action="ESICChallan" validate="true" id="paraFrm" theme="simple">
<s:hidden name="myPage" id="myPage"/>
<s:hidden name="month"/>
<s:hidden name="year"/>
<s:hidden name="division"/>
<s:hidden name="paymentDate"/>
<s:hidden name="esicCode"/>

	<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt">
						<strong	class="formhead"> 
						<img src="../pages/images/recruitment/review_shared.gif" width="25"	height="25" /></strong>
					</td>
					<td width="93%" class="txt"><strong class="text_head">ESIC Challan</strong></td>
					<td width="3%" valign="top" class="txt">
						<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<%
			int totalPage = 0;
			int pageNo = 0;
		%>
		<tr>
      		<td>
      			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
	         		<tr>
	            		<td nowrap="nowrap">
	            			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
	  					</td>
	  					<td id="ctrlShow" width="100%" align="right"><b>Page:</b>
							<%
								totalPage = (Integer) request.getAttribute("totalPage");
								pageNo = (Integer) request.getAttribute("pageNo");
							%> 
							<a href="#" onclick="callPage('1', 'F', '<%=totalPage%>', 'ESICChallan_input.action');">
							<img title="First Page" src="../pages/common/img/first.gif"	width="10" height="10" class="iconImage"/></a>&nbsp; 
							<a href="#"	onclick="callPage('P', 'P', '<%=totalPage%>', 'ESICChallan_input.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/></a> 
							<input type="text" name="pageNoField" id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage%>', 'ESICChallan_input.action');return numbersOnly();"/>
							of <%=totalPage%> <a href="#" onclick="callPage('N', 'N', '<%=totalPage%>', 'ESICChallan_input.action')">
							<img title="Next Page" src="../pages/common/img/next.gif" class="iconImage" /> </a>&nbsp; 
							<a href="#"	onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ESICChallan_input.action');">
							<img title="Last Page" src="../pages/common/img/last.gif" width="10" height="10" class="iconImage" /></a>
						</td>
	          		</tr>
          		</table>
            </td>
    	</tr>
    	<tr>
    		<td>
    			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
    				<tr>
    					<td class="formth">
    						<label id="srno" name="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label>
    					</td>
    					<td class="formth">
    						<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
    					</td>
    					<td class="formth">
    						<label id="division" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
    					</td>
    					<td class="formth">
    						<label id="months" name="months" ondblclick="callShowDiv(this);"><%=label.get("months")%></label>
    					</td>
    					<td class="formth">
    						<label id="years" name="years" ondblclick="callShowDiv(this);"><%=label.get("years")%></label>
    					</td>
    					<td class="formth">
    						<label id="earnintype" name="earnintype" ondblclick="callShowDiv(this);"><%=label.get("earnintype")%></label>
    					</td>
    					<td align="center" class="formth">
    						<input type="button" id="ctrlShow" class="token" value="Remove"	onclick="return chkDelete();" /> <br>
							<input type="checkbox" id="ctrlShow" style="cursor: pointer;" title="Select all records to remove"	onclick="selectAllRecords(this);" />
						</td>
    				</tr>
    				<%	int count = 0; %>
					<%!int d = 0;%>
					<%	int i = 0;
						int cn = pageNo * 20 - 20;
					%>
    				<s:iterator value="challanList">
    					<tr class="sortableTD" <%if(count%2==0){%>
							class="tableCell1" <%}else{%> class="tableCell2"
							<%	}count++; %> onmouseover="javascript:newRowColor(this);" title="Double click for edit" 
							onmouseout="javascript:oldRowColor(this,<%=count%2 %>);" ondblclick="javascript:callForEdit('<s:property value="esicCodeItt" />');">
						
							<td class="sortableTD" align="center">&nbsp;<%=++cn%><% ++i; %>
								<input type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>"/>
								<s:hidden value="%{esicCodeItt}" id='<%="esicCodeItt" + i%>'/>
							</td>
							<td class="sortableTD" nowrap="nowrap" align="left"><s:property value="divisionItt"/></td>
							<td class="sortableTD" nowrap="nowrap" align="left"><s:property value="branchItt"/></td>
							<td class="sortableTD" nowrap="nowrap" align="left"><s:property value="monthItt"/></td>
							<td class="sortableTD" nowrap="nowrap" align="center"><s:property value="yearItt"/></td>	
							<td class="sortableTD" nowrap="nowrap" align="left"><s:property value="earningTypeItt"/></td>	
	    						<script type="text/javascript">												
										records[<%=i-1%>] = document.getElementById('esicCodeItt' + '<%=i%>').value;
								</script>
							<td align="center" nowrap="nowrap" class="sortableTD" id="ctrlShow">
								<input type="checkbox" class="checkbox" id="confChk<%=i%>" name="confChk" onclick="checkSelection('<s:property value="esicCodeItt" />','<%=i%>')" />
							</td>
    					</tr>
    				</s:iterator>
    				<%	d = i;
						i = 0;	%>
    			</table>
    		</td>
    	</tr>
    	<tr>
       		<td>
       			<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/>
			</td>
		</tr>
	</table>
</s:form>
<script>
	function addnewFun(){
		document.getElementById('paraFrm').target="main";
		document.getElementById('paraFrm').action='ESICChallan_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action ='ESICChallan_f9action.action';
		document.getElementById("paraFrm").submit();
	}
	
	function oldRowColor(cell,val){
		if(val=='1'){
		 	cell.className='tableCell2';
		} else {
			cell.className='tableCell1';
		}
	}
	
	function newRowColor(cell){
		cell.className='Cell_bg_first'; 
	}
	
	function callForEdit(id){
	   	document.getElementById("paraFrm").action="ESICChallan_calForEdit.action?esicChallanId="+id;
	   	document.getElementById('paraFrm').target = "_self";
	    document.getElementById("paraFrm").submit();
   	}
   	
	function checkSelection(id,i){
		if(document.getElementById('confChk'+i).checked == true){	  
		document.getElementById('hdeleteCode'+i).value=id;
		} else {
		document.getElementById('hdeleteCode'+i).value="";
		}
	}
	
	function selectAllRecords(object) {
		var flag='<%=d %>';
		if(object.checked) {
			for(var i = 1; i <= records.length; i++) {			
				document.getElementById('confChk'+i).checked = true;
				document.getElementById('hdeleteCode'+ i).value = records[i];
			}
		}else{
			for(var i = 1; i <= records.length; i++) {			
				document.getElementById('confChk' + i).checked = false;
				document.getElementById('hdeleteCode' + i).value = "";
			}
		}
	}
	
	function chkDelete(){
		if(chk()){
	 		var con=confirm('Do you want to delete the record(s)?');
	 		if(con){
				document.getElementById('paraFrm').action="ESICChallan_deleteCheckedRecords.action";
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').submit();
			} else { 
				var flag='<%=d %>';
				for(var a=1;a<=flag;a++){
					document.getElementById('confChk'+a).checked = false;
					document.getElementById('hdeleteCode'+a).value="";
				}
				return false;
			}
		} else {
			alert('Please select atleast one record');
			return false;
		}
	}
	
	function chk(){
		var flag='<%=d %>';
		for(var a=1;a<=flag;a++){	
			if(document.getElementById('confChk'+a).checked == true){	
				return true;
		   	}	   
	  	}
		return false;
	}
</script>