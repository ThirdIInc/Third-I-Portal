<!-- @author: Mangesh Jadhav 19 Jan 2011  -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="ReimbProcess" validate="true" id="paraFrm"
	validate="true" theme="simple">
<div align="center" id="overlay"
		style="z-index: 3; position: absolute; width: 790px; height: 920px; margin: 0px; left: 0; top: 100; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid :   DXImageTransform .   Microsoft .   alpha(opacity =   15); -moz-opacity: .1; opacity: .1; display: none;">
</div>
<div id="confirmationDiv"
style='position: absolute; z-index: 3; 100 px; height: 150px; visibility: hidden; top: 200px; left: 100px;'></div>
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Reimbursement Process</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
<tr>
      <td>
      		<table width="100%" border="0" cellpadding="2" cellspacing="2">         
          		<tr>
            		<td width="78%">
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
            		<td width="22%">
            			<div align="right"><font color="red">*</font> Indicates Required </div>
            		</td>
          		</tr>
        	</table>
      </td>
    </tr>
		<tr>
			<td colspan="10">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr valign="top" >
					<td width="22%" nowrap="nowrap"><label name="reimbHead"
						id="reimbHead" ondblclick="callShowDiv(this);"><%=label.get("reimbHead")%></label> :<font color="red">*</font></td>
					<td width="22%"><s:hidden name="reimbHeadCode" /> <s:textfield
						name="reimbHeadName" theme="simple" size="25"
						readonly="true" /></td>
					<td width="6%" ><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ReimbProcess_f9reimbHead.action');">
					</td>
					<td width="22%" ></td>
					<td width="22%"></td>
					<td width="6%" >
					</td>
					
				</tr>
				<tr>
				<td width="22%" nowrap="nowrap"><label name="division"
						id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> : <font color="red">*</font></td>
					<td width="22%"><s:hidden name="divisionCode" /> <s:textfield
						name="divisionName" theme="simple" size="25"
						readonly="true" /></td>
					<td width="6%" ><img id="ctrlHide"
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ReimbProcess_f9division.action');">
					</td>
					<td width="22%" ></td>
					<td width="22%"></td>
					<td width="6%" >
					</td>
				</tr>
				<tr id='TR_M'>
					<td width="22%" nowrap="nowrap"><label name="month"
						id="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font></td>
					<td width="22%"><s:select name="month" headerKey="" headerValue="--Select--" title="Select a month"
										list="#{'1':'January', '2':'Febuary', '3':'March', '4':'April', '5':'May', '6':'June', '7':'July', 
										'8':'August', '9':'September', '10':'October', '11':'November', '12':'December'}" /></td>
					<td width="6%" >
					</td>
					<td width="22%" nowrap="nowrap"><label name="year"
						id="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> : <font color="red">*</font></td>
					<td width="22%"><s:textfield name="year" maxlength="4"  theme="simple" size="10"/></td>
					<td width="6%" colspan="4">
					</td>
				</tr>
				
				<tr id='TR_Q'>
					<td width="22%" nowrap="nowrap"><label name="qurter"
						id="qurter" ondblclick="callShowDiv(this);"><%=label.get("qurter")%></label> :<font color="red">*</font></td>
					<td width="22%"><s:select name="quarter"
								theme="simple" headerKey=""
								list="#{'':'-------------Select-----------','4':'April-June','7':'July-September','10':'October-December','1':'January-March'}" /></td>
					<td width="6%" >
					</td>
					<td width="22%" nowrap="nowrap"><label name="year"
						id="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> : <font color="red">*</font></td>
					<td width="22%"><s:textfield name="quarterYear" maxlength="4"  theme="simple" size="10"/></td>
					<td width="6%" colspan="4">
					</td>
				</tr>
				
				<tr id='TR_H'>
					<td width="22%" nowrap="nowrap"><label name="month"
						id="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font></td>
					<td width="22%"><s:select name="halfMonth"
								theme="simple" headerKey=""
								list="#{'':'-------------Select-----------','4':'April-September','10':'October-March'}" /></td>
					<td width="6%" >
					</td>
					<td width="22%" nowrap="nowrap"><label name="year"
						id="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> : <font color="red">*</font></td>
					<td width="22%"><s:textfield name="halfYear" maxlength="4"  theme="simple" size="10"/></td>
					<td width="6%" colspan="4">
					</td>
				</tr>
				<tr id='TR_A'>
					
					<td width="22%" nowrap="nowrap"><label name="year"
						id="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> : <font color="red">*</font></td>
					<td width="22%"><s:textfield name="annYear" maxlength="4" theme="simple" size="10"/></td>
					<td width="6%" >
					</td>
					<td width="22%" nowrap="nowrap"></td>
					<td width="22%"></td>
					<td width="6%"></td>
				</tr>
				<tr id='TR_F'>
					
					<td width="22%" nowrap="nowrap"><label name="from.date"
						id="from.date" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label> : <font color="red">*</font></td>
					<td width="22%"><s:textfield theme="simple"
							name="fromDate" onkeypress="return numbersWithHiphen();"
							size="12" /></td>
					<td width="6%"><s:a
							href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16" id="ctrlHide">
						</s:a>
					</td>
						<td width="22%" nowrap="nowrap"><label name="to.date"
						id="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label> : <font color="red">*</font></td>
					<td width="22%"><s:textfield theme="simple"
							name="toDate" onkeypress="return numbersWithHiphen();"
							size="12" /></td>
					<td width="6%"><s:a
							href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="16" align="absmiddle" width="16" id="ctrlHide">
						</s:a>
					</td>
				</tr>

			</table>
			</td>
		</tr>
		<s:if test="%{processCode == ''}"></s:if><s:else>
			<tr>
				<td>
				<table width="100%" border="0">
					<%
						int totalPagePending = 0;
								int pageNoPending = 0;
					%>
					<s:if test="empProcessLength">
						<tr>
							<td width="30%"><b>Employee List</b></td>
							<td id="ctrlShow" width="70%" align="right"><b>Page:</b> <%
 	totalPagePending = (Integer) request.getAttribute("totalPagePending");
 				pageNoPending = (Integer) request.getAttribute("pageNoPending");
 %> <a href="#"
								onclick="callPage('1', 'F', '<%=totalPagePending%>', 'ReimbProcess_showProcessEmployee.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('P', 'P', '<%=totalPagePending%>', 'ReimbProcess_showProcessEmployee.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a> <input type="text"
								name="pageNoField" id="pageNoField" size="3"
								value="<%=pageNoPending%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPagePending%>', 'ReimbProcess_showProcessEmployee.action');return numbersOnly();" />
							of <%=totalPagePending%> <a href="#"
								onclick="callPage('N', 'N', '<%=totalPagePending%>', 'ReimbProcess_showProcessEmployee.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp; <a href="#"
								onclick="callPage('<%=totalPagePending%>', 'L', '<%=totalPagePending%>', 'ReimbProcess_showProcessEmployee.action');">
							<img title="Last Page" src="../pages/common/img/last.gif"
								width="10" height="10" class="iconImage" /> </a></td>
						</tr>
					</s:if>
				</table>
				</td>
			</tr>
			<tr>
				<td width="100%">
				<table width="100%" class="sorttable" border="0">
					<tr>
						<s:hidden name="myPage" id="myPage" />
						<td width="5%" class="formth"><label class="set" id="srno"
							name="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
						<td width="15%" class="formth"><label class="set" id="employee.id"
							name="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
						<td width="30%" class="formth"><label class="set"
							id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
						<td width="10%" class="formth"><label class="set"
							id="processed.amt" name="processed.amt" ondblclick="callShowDiv(this);"><%=label.get("processed.amt")%></label></td>

					</tr>
					<%int count = 0; %>
					<s:if test="empProcessLength">
						<%
							int p = pageNoPending * 20 - 20;
							
						%>
						<s:iterator value="empProcessList">
							<tr>
								<td width="5%" class="sortableTD" align="center"><%=++p%>
								<%++count;%>
								</td>
								<td width="15%" class="sortableTD" width="30%"><s:property
									value="empTokenList" /><s:hidden name="empTokenList" /></td>
								<td width="30%" class="sortableTD" width="25%"><s:property
									value="empNameList" /><s:hidden name="statusList" /><s:hidden name="empNameList" /><s:hidden name="empCodeList" /></td>
								<td width="10%" class="sortableTD" width="25%" align="right">
								<s:if test="statusList=='LOCK'">
								<s:textfield name='empReimbAmountList' size='10' onkeypress="return numbersWithDot();" cssStyle="background-color:  #C0EDFE; text-align: right"></s:textfield><s:hidden name="empReimbAmountList" />
								</s:if><s:else>
								<s:textfield name='empReimbAmountList' id='ctrlShow' size='10' onkeypress="return numbersWithDot();" cssStyle="background-color: #C0EDFE; text-align: right"></s:textfield>
								</s:else></td>
								
							</tr>

						</s:iterator>

					</s:if>
					<s:else>
						<tr align="center">
							<td colspan="4" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
					</s:else>
					<input type="hidden" name="count" id="count" value='<%=count%>' />
					</table></td></tr>
		</s:else>
		

		
		<s:hidden name="listType"></s:hidden>
		<s:hidden name="reimbPeriod"></s:hidden>
		<s:hidden name="processCode"></s:hidden>
		<s:hidden name="empProcessLength"></s:hidden>
		<s:hidden name="status"></s:hidden>
	</table>
</s:form>

<script>
calOnload();
function calOnload(){
	var reimbPeriod=document.getElementById('paraFrm_reimbPeriod').value;
	//alert(reimbPeriod);
	document.getElementById('TR_F').style.display='none';
	document.getElementById('TR_A').style.display='none';
	document.getElementById('TR_H').style.display='none';
	document.getElementById('TR_M').style.display='none';
	document.getElementById('TR_Q').style.display='none';
	document.getElementById('TR_'+reimbPeriod).style.display='';
}
function processFun(){
	var reimbPeriod=document.getElementById('paraFrm_reimbPeriod').value;
	var fields;
	var labels;
	var flags;
	
	if(reimbPeriod=='A'){
		fields =['paraFrm_reimbHeadName','paraFrm_divisionName','paraFrm_annYear'];
		labels=['reimbHead','division','year'];
		flags=['select','select','enter'];
	}else 
	if(reimbPeriod=='H'){
		fields =['paraFrm_reimbHeadName','paraFrm_divisionName','paraFrm_halfMonth','paraFrm_halfYear'];
		labels=['reimbHead','division','month','year'];
		flags=['select','select','select','enter'];
	}else 
	if(reimbPeriod=='Q'){
		fields =['paraFrm_reimbHeadName','paraFrm_divisionName','paraFrm_quarter','paraFrm_quarterYear'];
		labels=['reimbHead','division','quarter','year'];
		flags=['select','select','select','enter'];
	}else 
	if(reimbPeriod=='M'){
		fields =['paraFrm_reimbHeadName','paraFrm_divisionName','paraFrm_month','paraFrm_year'];
		labels=['reimbHead','division','month','year'];
		flags=['select','select','select','enter'];
	}else 
	if(reimbPeriod=='F'){
		fields =['paraFrm_reimbHeadName','paraFrm_divisionName','paraFrm_fromDate','paraFrm_toDate'];
		labels=['reimbHead','division','from.date','to.date'];
		flags=['select','select','select','select'];
	}else {
		fields =['paraFrm_reimbHeadName','paraFrm_divisionName'];
		labels=['reimbHead','division'];
		flags=['select','select'];
	}
	if(!validateBlank(fields,labels,flags)){
		return false;
		
	}
	var conf=confirm('Do you really want to process the reimbursement?');
			 	if(!conf){
			 	return false;
			 	}
	document.getElementById('paraFrm').action='ReimbProcess_processReimb.action';
	document.getElementById('paraFrm').submit();
}
function saveFun(){
		document.getElementById('paraFrm').action="ReimbProcess_save.action";
	 	document.getElementById('paraFrm').submit();
}
function deleteFun(){
		var conf=confirm('Do you really want to delete the process record?');
			 	if(!conf){
			 	return false;
			 	}
		document.getElementById('paraFrm').action="ReimbProcess_delete.action";
	 	document.getElementById('paraFrm').submit();
}
function lockFun(){
		var conf=confirm('Do you really want to lock the process record?');
			 	if(!conf){
			 	return false;
			 	}
		document.getElementById('paraFrm').action="ReimbProcess_lockProcess.action";
	 	document.getElementById('paraFrm').submit();
}
function resetFun(){
		resetFields();
		document.getElementById('paraFrm').action="ReimbProcess_reset.action";
	 	document.getElementById('paraFrm').submit();
}
function  resetFields(){
		document.getElementById('paraFrm_reimbHeadCode').value="";
		document.getElementById('paraFrm_reimbHeadName').value="";
		document.getElementById('paraFrm_divisionCode').value="";
		document.getElementById('paraFrm_divisionName').value="";
		
		document.getElementById('paraFrm_fromDate').value="";
		document.getElementById('paraFrm_toDate').value="";
		document.getElementById('paraFrm_month').value="";
		document.getElementById('paraFrm_year').value="";
		document.getElementById('paraFrm_quarter').value="";
		document.getElementById('paraFrm_quarterYear').value="";
		document.getElementById('paraFrm_halfYear').value="";
		document.getElementById('paraFrm_halfMonth').value="";
		document.getElementById('paraFrm_annYear').value="";
		document.getElementById('paraFrm_reimbPeriod').value="";
}
function backtolistFun(){
	resetFields();
	var listType=document.getElementById('paraFrm_listType').value;
	if(listType=="pending"){
	document.getElementById('paraFrm').action="ReimbProcess_input.action";
	}
	else{
		document.getElementById('paraFrm').action='ReimbProcess_getLockedList.action';
	}
	document.getElementById('paraFrm').submit();
}

function callPage(id, pageImg, totalPageHid, action) {
		
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('myPage').value; 
		
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
		document.getElementById('myPage').value = id;
		
		 if(document.getElementById('paraFrm_status').value=="LOCK"){
				document.getElementById('paraFrm').action=action;
				document.getElementById('paraFrm').target = '_self';
				document.getElementById('paraFrm').submit();
		   }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
	}

		function callPageText(id, totalPage, action){   
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;
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
		   if(document.getElementById('paraFrm_status').value=="LOCK"){
				document.getElementById('paraFrm').action=action;
				document.getElementById('paraFrm').target = '_self';
				document.getElementById('paraFrm').submit();
		   }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
		}
		
	}
		function displayConfirmDiv(){
			document.getElementById("confirmationDiv").style.visibility = 'visible';
			 document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		 			+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		 			+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		 			+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		 			+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
		 	document.getElementById("overlay").style.display = "block";
		///JSFX_FloatTopDiv();
}
	function proceedWithSave(){


	try{
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	document.getElementById('confirmationDiv').style.display='none';
	document.getElementById("overlay").style.display = "none";
	enableBlockDiv();
	document.getElementById('paraFrm').action="ReimbProcess_save.action";
	document.getElementById('paraFrm').submit();
	}
	catch(e){
		//alert(e);
	}
}
function proceedWithoutSave(){
	try{
	enableBlockDiv();
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	document.getElementById('confirmationDiv').style.display='none';
	document.getElementById("overlay").style.display = "none";
	document.getElementById('paraFrm').action="ReimbProcess_showProcessEmployee.action";
	document.getElementById('paraFrm').submit();
	}
	catch(e){
		//alert(e);
	}
}
function cancel(){
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	document.getElementById("overlay").style.display = "none";
	
}

function enableBlockDiv(){
	  		
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "visible";
				document.getElementById("overlay").style.display = "block";
			}
			catch(e){
			}
	  }
	  function disableBlockDiv(){
	  		
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
			catch(e){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
	  }	


</script>