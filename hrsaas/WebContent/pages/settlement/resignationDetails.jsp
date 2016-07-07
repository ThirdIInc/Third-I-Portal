<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="ResignationDetails" validate="true" id="paraFrm"
	theme="simple">
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
					<td width="93%" class="txt"><strong class="text_head">Resignation
					Details</strong></td>
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

		<s:hidden name="hiddencode" />
		<s:hidden name="show" value="%{show}" />
		<s:if test="listFlag">
			<s:hidden name="empName" />
			<s:hidden name="resignDate" />
			<s:hidden name="regCode" value="%{regCode}" />
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr class="formbg">
						<td width="20%" class="formtxt"></td>
						<%
							int totalPage = (Integer) request.getAttribute("totalPage");
							int pageNo = (Integer) request.getAttribute("pageNo");
						%>
						<!-- Paging implementation -->
						<td id="showCtrl" width="80%" align="right"><s:if
							test="modeLength">
							<b>Page:</b>
							<a href="#"
								onclick="callPage('1', 'F', '<%=totalPage%>', 'ResignationDetails_input.action');">
							<img title="First Page" src="../pages/common/img/first.gif"
								width="10" height="10" class="iconImage" /> </a>&nbsp;
						<a href="#"
								onclick="callPage('P', 'P', '<%=totalPage%>', 'ResignationDetails_input.action');">
							<img title="Previous Page" src="../pages/common/img/previous.gif"
								width="10" height="10" class="iconImage" /> </a>
							<input type="text" name="pageNoField" id="pageNoField" size="3"
								value="<%=pageNo%>" maxlength="10"
								onkeypress="callPageText(event, '<%=totalPage%>', 'ResignationDetails_input.action');return numbersOnly();" /> of <%=totalPage%>
							<a href="#"
								onclick="callPage('N', 'N', '<%=totalPage%>', 'ResignationDetails_input.action')">
							<img title="Next Page" src="../pages/common/img/next.gif"
								class="iconImage" /> </a>&nbsp;
						<a href="#"
								onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'ResignationDetails_input.action');">
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
						<td width="5%" valign="top" class="formth"><label
							name="serial.no" id="serial.no" ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
						<td width="15%" valign="top" class="formth"><label
							name="employee.id" id="employee.id1"
							ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
						<td width="40%" valign="top" class="formth"><label
							name="employee" id="employee1" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
						<td width="30%" valign="top" class="formth" nowrap="nowrap">
						<label class="set" name="resigndt" id="resigndt1"
							ondblclick="callShowDiv(this);"><%=label.get("resigndt")%></label>
						</td>
						<td align="right" class="formth" nowrap="nowrap"><s:submit
							cssClass="delete" theme="simple" value=" Delete"
							onclick=" return chkDelete();" /></td>
					</tr>
					<s:if test="noData">
						<tr>
							<td width="100%" colspan="7" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
					</s:if>
					<%
					int count = 0;
					%>
					<%!int d = 0;%>
					<%
						int t = 0;
						int cn = pageNo * 20 - 20;
					%>
					<s:iterator value="tableList">

						<tr class="sortableTD" <%if(count%2==0){
							%>
							class="tableCell1" <%}else{%> class="tableCell2" <%	}count++; %>
							onmouseover="javascript:newRowColor(this);"
							title="Double click for edit"
							onmouseout="javascript:oldRowColor(this,<%=count%2 %>);"
							ondblclick="javascript:callForEdit('<s:property  value="resignCodeItt"/>');">
							<td class="sortableTD" width="5%"><%=++cn%> <%
 ++t;
 %>
							</td>
							<td class="sortableTD" width="15%"><s:property
								value="empTokenItt" /></td>
							<td class="sortableTD" width="40%"><s:property
								value="empNameItt" /></td>
							<td class="sortableTD" width="30%" nowrap="nowrap" align="center">
							<s:property value="resignDateItt" /></td>
							<td align="center" width="10%" class="sortableTD" nowrap="nowrap"
								id="ctrlShow"><input type="hidden"
								value='<s:property	value="empCodeItt" />' id="empCodeItt<%=t %>" />
							<input type="hidden" value='<s:property	value="resignDateItt" />'
								id="resignDateItt<%=t %>" /> <input type="hidden"
								value='<s:property	value="resignCodeItt" />'
								id="resignCodeItt<%=t %>" /> <input type="hidden"
								name="hdeleteCode" id="hdeleteCode<%=t%>" /> <input
								type="checkbox" class="checkbox" id="confChk<%=t%>"
								name="confChk"
								onclick="callForDelete1('<s:property  value="resignCodeItt"/>','<%=t%>')" />
							</td>

						</tr>
					</s:iterator>

					<tr>
						<td colspan="7" align="right"><strong>Total Records:
						</strong><s:property value="totalRecords" /></td>
					</tr>
					<%
					d = t;
					%>
				</table>
				</td>
			</tr>
		</s:if>




		<s:if test="listFlag"></s:if>
		<s:else>
			<s:hidden name="myPage" />

			<tr>
				<td colspan="2">
				<table width="750" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="750" border="0" align="center" cellpadding="0"
							cellspacing="2">

							<s:hidden name="regCode" value="%{regCode}" />
							<tr>
								<td width="180"><label name="employee" id="employee"
									ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
								:<font color="red">*</font></td>
								<td width="129"><s:textfield size="24" theme="simple"
									name="employeeToken" readonly="true" /></td>
								<td colspan="4"><s:textfield size="73" theme="simple"
									name="empName" readonly="true" /> <s:hidden name="empCode"
									value="%{empCode}" /></td>
								<td width="35"><s:if test="%{generalFlag}"></s:if> <s:else>
									<img class="iconImage" id="ctrlHide"
										src="../pages/images/recruitment/search2.gif" height="16"
										width="15" onclick="getEmployee()">

								</s:else></td>
							</tr>
							<tr>
								<td><label name="branch" id="branch"
									ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
								<td colspan="2"><s:textfield size="25" theme="simple"
									name="branch" value="%{branch}" readonly="true" /></td>
								<td width="44"></td>
								<td width="180"><label name="designation" id="designation"
									ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
								:</td>
								<td width="129" colspan="2"><s:textfield size="25"
									theme="simple" name="designation" value="%{designation}"
									readonly="true" /></td>
							</tr>
							<tr>
							
							    <td><label name="grade" id="grade"
									ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
								<td colspan="2"><s:textfield size="25" theme="simple"
									name="cadreName" value="%{cadreName}" readonly="true" /></td>
								<td width="44"></td>
								
								<td width="180"><label name="dateofjoin" id="dateofjoin"
									ondblclick="callShowDiv(this);"><%=label.get("dateofjoin")%></label>
								:</td>
								<td width="129" colspan="2"><s:textfield size="25"
									theme="simple" name="dateofjoin" value="%{dateofjoin}"
									readonly="true" /></td>

							</tr>
							<tr>
								<td><label name="resigndt" id="resigndt"
									ondblclick="callShowDiv(this);"><%=label.get("resigndt")%></label>:<font
									color="red">*</font></td>
								<td><s:textfield size="25" theme="simple" name="resignDate" /></td>
								<td><s:a
									href="javascript:NewCal('paraFrm_resignDate','DDMMYYYY');">
									<img class="iconImage" id="ctrlHide"
										src="../pages/images/recruitment/Date.gif" width="16"
										height="16" border="0" align="absmiddle" />
								</s:a></td>
								<td></td>
								<td><label name="sts" id="sts"
									ondblclick="callShowDiv(this);"><%=label.get("sts")%></label>:</td>
								<td colspan="2"><s:select theme="simple" name="status"
									cssStyle="width:150" onchange="callVisit()"
									list="#{'s':'--select--','m':'Mutual','d':'Dismissal','n':'Normal'}" /></td>
							</tr>
							<tr>
								<td><label name="acceptdt" id="acceptdt"
									ondblclick="callShowDiv(this);"><%=label.get("acceptdt")%></label>:<font
									color="red">*</font></td>
								<td><s:textfield size="25" theme="simple" name="acceptDate" /></td>
								<td><s:a
									href="javascript:NewCal('paraFrm_acceptDate','DDMMYYYY');">
									<img class="iconImage" id="ctrlHide"
										src="../pages/images/recruitment/Date.gif" width="16"
										height="16" border="0" align="absmiddle" />
								</s:a></td>
								<td></td>
								<td><label name="seperationdt" id="seperationdt"
									ondblclick="callShowDiv(this);"><%=label.get("seperationdt")%></label>
								:<font color="red">*</font></td>
								<td><s:textfield size="25" theme="simple" name="sepdate" /></td>
								<td><s:a
									href="javascript:NewCal('paraFrm_sepdate','DDMMYYYY');">
									<img class="iconImage" id="ctrlHide"
										src="../pages/images/recruitment/Date.gif" width="16"
										height="16" border="0" align="absmiddle" />
								</s:a></td>
							</tr>

							<tr>
								<td><label name="noticeperiodact" id="noticeperiodact"
									ondblclick="callShowDiv(this);"><%=label.get("noticeperiodact")%></label>
								:<font color="red">*</font></td>
								<td><s:textfield size="25" theme="simple"
									name="noticePeriodActual" onkeypress="return numbersOnly();"
									maxlength="2" onkeyup="setNoticePeriod();" /></td>
								<td colspan="5"><s:hidden name="noticePeriod" /><s:select
									theme="simple" name="noticeperiodselect" cssStyle="width:60"
									list="#{'D':'Days','M':'Month'}" /></td>
							</tr>

							<tr>
								<td><label name="waveoff" id="waveoff"
									ondblclick="callShowDiv(this);"><%=label.get("waveoff")%></label>:</td>
								<td><s:textfield size="25" theme="simple"
									name="waveOffPeriod" onkeypress="return numbersOnly();"
									onkeyup="setNoticePeriod();" maxlength="2" /></td>
								<td>Days</td>
								<td></td>
								<td><label name="waveoffappr" id="waveoffappr"
									ondblclick="callShowDiv(this);"><%=label.get("waveoffappr")%></label>:</td>
								<td><s:hidden name="apprToken" value="%{apprToken}" /><s:textfield
									size="25" theme="simple" name="apprName" readonly="true" /></td>
								<td><s:hidden name="apprCode" value="%{apprCode}" /><img
									class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/search2.gif" height="16"
									width="15" onclick="getApprover()"></td>
							</tr>

							<tr>
								<td><label name="accepted.by" id="accepted.by"
									ondblclick="callShowDiv(this);"><%=label.get("accepted.by")%></label>:</td>
								<td><s:hidden name="accByToken" value="%{accByToken}" /><s:textfield
									size="25" theme="simple" name="acceptedBy" readonly="true" /></td>
								<td colspan="5"><s:hidden name="accByCode"
									value="%{accByCode}" /><img class="iconImage" id="ctrlHide"
									src="../pages/images/recruitment/search2.gif" height="16"
									width="15" onclick="getAcceptedBy()"></td>
							</tr>

							<tr>
								<td><label name="reason" id="reason"
									ondblclick="callShowDiv(this);"><%=label.get("reason")%></label>:</td>
								<td colspan="4"><s:textarea name="reason" rows="5"
									cols="68" readonly="false"
									onkeyup="callLength('reason','descCnt1','500');" /><img
									src="../pages/images/zoomin.gif" height="12" align="absmiddle"
									width="12" theme="simple" id="ctrlHide"
									onclick="javascript:callWindow('paraFrm_reason','reason','','paraFrm_descCnt1','500');"></td>
								<td id="ctrlHide" colspan="2">Remaining chars <s:textfield
									name="descCnt1" readonly="true" size="5" /></td>

							</tr>
							
							<tr>
								<td><label name="remark" id="remark"
								ondblclick="callShowDiv(this);"><%=label.get("remark")%></label>:</td>
								<td colspan="4"><s:textarea name="remark"
								rows="5" cols="68" readonly="false"
								onkeyup="callLength('remark','descCnt','500');" /> <img
								src="../pages/images/zoomin.gif" height="12" align="absmiddle"
								width="12" theme="simple" id="ctrlHide"
								onclick="javascript:callWindow('paraFrm_remark','remark','','paraFrm_descCnt','500');"></td>
								<td id="ctrlHide" colspan="2">Remaining chars <s:textfield name="descCnt" readonly="true"
								size="5" /></td>

							</tr>
							
							<tr>
								<td><label name="hrComments" id="hrComments"
									ondblclick="callShowDiv(this);"><%=label.get("hrComments")%></label>:</td>
								<td colspan="4"><s:textarea name="hrComments" rows="5"
									cols="68" readonly="false"
									onkeyup="callLength('hrComments','descCnt2','500');" /><img
									src="../pages/images/zoomin.gif" height="12" align="absmiddle"
									width="12" theme="simple" id="ctrlHide"
									onclick="javascript:callWindow('paraFrm_hrComments','hrComments','','paraFrm_descCnt2','500');"></td>
								<td id="ctrlHide" colspan="2">Remaining chars <s:textfield
									name="descCnt2" readonly="true" size="5" /></td>

							</tr>
							
							<tr>
								<td><label name="withdraw"
								id="withdraw" ondblclick="callShowDiv(this);"><%=label.get("withdraw")%></label>
							:</td>
								<td colspan="6"><s:checkbox name="withDrawn"
								id="withDrawn" /></td>
							</tr>

						</table>
						</td>
					</tr>
				</table>
		</s:else>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="4"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
						</td>
				</tr>

			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="today" />
	<s:hidden name="lockFlag" />
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>
	window.onload = document.getElementById('pageNoField').focus();
	
	function addnewFun() {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = 'ResignationDetails_addNew.action';
		document.getElementById('paraFrm').submit();
	}
	
	function searchFun() {
		if(navigator.appName == 'Netscape') {
			var myWin = window.open('', 'myWin', 'top = 150, left = 200, width = 750, height = 475, scrollbars = no, status = no, resizable = yes');
		} else {
			var myWin = window.open('', 'myWin', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		}
		
		document.getElementById("paraFrm").target = 'myWin';
		document.getElementById("paraFrm").action = 'ResignationDetails_search.action';
		document.getElementById("paraFrm").submit();
	}
	
	function saveFun() {
		if(!formValidate()){
    		return false;
    	}
      
      	document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ResignationDetails_save.action';
		document.getElementById('paraFrm').submit();
      	
		return true;
	}
	
	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'ResignationDetails_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function cancelFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'ResignationDetails_input.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'ResignationDetails_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	function editFun(){
		return true;
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
	
	function callForEdit(resignCode){
		//alert(resignCode);
		callButton('NA', 'Y', 2);
	  	try{
	  	//var resignCode = document.getElementById('resignCodeItt'+id).value;
	  	document.getElementById('paraFrm_hiddencode').value=resignCode;
		document.getElementById("paraFrm").action="ResignationDetails_callforedit.action?resignCode="+resignCode; 
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
		  		document.getElementById('paraFrm').action="ResignationDetails_delete1.action";
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


	function setNoticePeriod(){
	
		var type = document.getElementById("paraFrm_noticeperiodselect").value;
		if(type=="D"){
			var actualperiod = document.getElementById("paraFrm_noticePeriodActual").value;
			var waveOff = document.getElementById("paraFrm_waveOffPeriod").value;
			var servedPeriod = document.getElementById("paraFrm_noticePeriod").value;
			if(waveOff==""){
				document.getElementById("paraFrm_noticePeriod").value=document.getElementById("paraFrm_noticePeriodActual").value;
			}else{
				servedPeriod = actualperiod-waveOff;
				if(servedPeriod < 0){
					alert(document.getElementById('waveoff').innerHTML.toLowerCase()+" cannot be greater than "+document.getElementById('noticeperiodact').innerHTML.toLowerCase());
					document.getElementById("paraFrm_waveOffPeriod").value=0;
					document.getElementById("paraFrm_noticePeriod").value=document.getElementById("paraFrm_noticePeriodActual").value;
					return false;
				}
				document.getElementById("paraFrm_noticePeriod").value = servedPeriod;
			}
		}else{
			return true;
		}
	}

function getEmployee(){

		var regCode =document.getElementById('paraFrm_regCode').value;
		if(regCode==""){
			callsF9(500,325,'ResignationDetails_f9action.action');
		}else{
			alert("You can't change or select "+document.getElementById('employee').innerHTML.toLowerCase()+" for this Application ! ")
		}
	}
	
	function getApprover(){

		callsF9(500,325,'ResignationDetails_f9appraction.action');
	}
	
	function getAcceptedBy(){

		callsF9(500,325,'ResignationDetails_f9accByAction.action');
	}
	
	function formValidate(){
	
  		var empName =document.getElementById('paraFrm_empName').value;
  		var resignDate =document.getElementById('paraFrm_resignDate').value;
  		var acceptDate =document.getElementById('paraFrm_acceptDate').value;
  		var sepdate =document.getElementById('paraFrm_sepdate').value;
  		var dateofjoin = document.getElementById('paraFrm_dateofjoin').value;
  		
  		var lockStatus = document.getElementById('paraFrm_lockFlag').value ;
  		var actualperiod = document.getElementById("paraFrm_noticePeriodActual").value;
		var waveOff = document.getElementById("paraFrm_waveOffPeriod").value;
		var servedPeriod = document.getElementById("paraFrm_noticePeriod").value;
		var acceptedBy = document.getElementById("paraFrm_acceptedBy").value;
	
	  		if(lockStatus =='Y')
			  	{
				  alert("Settlement is over so you can't modify the record");
			  	return false;
			 	 }
	  	
  	if(!validateDate('paraFrm_resignDate','resigndt'))
  	return false;
  		if(!validateDate('paraFrm_acceptDate', 'acceptdt'))
  	return false;
  		if(!validateDate('paraFrm_sepdate', 'seperationdt'))
  	return false;
  		if(empName == ""){
  			alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
  			return false;
  		}
  		if(resignDate == ""){
  			alert("Please select "+document.getElementById('resigndt').innerHTML.toLowerCase());
  			return false;
  		}
  		
  		if(acceptDate == ""){
  			alert("Please select "+document.getElementById('acceptdt').innerHTML.toLowerCase());
  			return false;
  		}
  		
  		if(sepdate == ""){
  			alert("Please select "+document.getElementById('seperationdt').innerHTML.toLowerCase());
  			return false;
  		}
  		
  		if(actualperiod == ""){
  			alert("Please enter "+document.getElementById('noticeperiodact').innerHTML.toLowerCase());
  			return false;
  		}
  		
  		/*if(acceptedBy == ""){
  			alert("Please select "+document.getElementById('accepted.by').innerHTML.toLowerCase());
  			return false;
  		}*/
  		
  		var reason =	document.getElementById('paraFrm_reason').value;
		var remark=document.getElementById('paraFrm_remark').value;
		
		var hrComments = document.getElementById('paraFrm_hrComments').value;
		
		if(reason != "" && reason.length >500){
			alert("Maximum length of "+ document.getElementById('reason').innerHTML.toLowerCase()+" is 500 characters.");
			return false;
	    }    
	    
	    if(remark != "" && remark.length >500){
			alert("Maximum length of "+ document.getElementById('remark').innerHTML.toLowerCase()+" is 500 characters.");
			return false;
	    }  
	    
	     if(hrComments != "" && hrComments.length >500){
			alert("Maximum length of "+ document.getElementById('hrComments').innerHTML.toLowerCase()+" is 500 characters.");
			return false;
	    } 
  		
  		var datdiffdateofjoin = dateDifference(dateofjoin,resignDate,'paraFrm_dateofjoin','dateofjoin','resigndt');
  		
  		if(!datdiffdateofjoin){
  		return false;
  		}
  		
  		
  		var datdiffresignDate =dateDifferenceEqual(resignDate,acceptDate,'paraFrm_resignDate','resigndt','acceptdt');
  		
  		if(!datdiffresignDate){
  		return false;
  		}
  		
  		var datdiffacceptDate = dateDifferenceEqual(acceptDate,sepdate,'paraFrm_acceptDate','acceptdt','seperationdt');
  		
  		if(!datdiffacceptDate){
  		return false;
  		}
  		
  		if(document.getElementById("paraFrm_noticePeriodActual").value==""){
  			document.getElementById("paraFrm_waveOffPeriod").value=0;
			document.getElementById("paraFrm_noticePeriod").value=0;
  		
  		}
  		/*if(eval(actualperiod) < eval(waveOff)){
  			document.getElementById("paraFrm_waveOffPeriod").value=0;
			document.getElementById("paraFrm_noticePeriod").value=0;
  		
  		}*/
  		return true;
  	 
  		}

function dateDifferenceEqual(fromDate, toDate, fieldName, fromLabName, toLabName){
	
	strDate1 = fromDate.split("-"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	strDate2 = toDate.split("-"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	
	if(endtime < starttime) 
	{ 
		alert(""+document.getElementById(toLabName).innerHTML.toLowerCase()+" should be greater than "+document.getElementById(fromLabName).innerHTML.toLowerCase());
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}

	function autoDate () {
var tDay = new Date();
var tMonth = tDay.getMonth()+1;
var tDate = tDay.getDate();
if ( tMonth < 10) tMonth = "0"+tMonth;
if ( tDate < 10) tDate = "0"+tDate;

document.getElementById("paraFrm_today").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
//getDays();
}
autoDate();




function noduesformFun()
{
  	try{
  	if(document.getElementById('paraFrm_regCode').value == "")
	{
  	  	alert("Please select an application to generate report !");
		return false;
  	}
	 	
		document.getElementById('paraFrm').target = "_blank";
  		document.getElementById('paraFrm').action = 'ResignationDetails_reportForDues.action';
  		document.getElementById('paraFrm').submit();
  		document.getElementById('paraFrm').target = "main";
	}catch(e){
		alert(e);
	}
}

function acceptanceletterFun(){
  		try{
  	if(document.getElementById('paraFrm_regCode').value == "")
	{
  	  	alert("Please select an application to generate report !");
		return false;
  	}
	if(document.getElementById('paraFrm_templateCode').value==""){
 		alert("Please select "+document.getElementById('template').innerHTML.toLowerCase());
 		return false;	
	}
	else{	
		document.getElementById('paraFrm').target = "_blank";
  		document.getElementById('paraFrm').action = "ResignationDetails_report.action";
  		document.getElementById('paraFrm').submit();
  		document.getElementById('paraFrm').target = "main";
	}
	}catch(e){
		alert(e);
	}
}

	function getDays(){
		var resignDate =document.getElementById('paraFrm_resignDate').value;
		alert('resignDate	:'+resignDate);
		strDate1 = resignDate.split("-"); 
		//starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]);
		alert('strDate1[2]	:'+strDate1[2]);alert('strDate1[1]-1	:'+strDate1[1]);
		alert('strDate1[0]	:'+strDate1[0]);
			//Set the two dates
		var startingdate=new Date(strDate1[2],strDate1[1],strDate1[0]); //Month is 0-11 in JavaScript
		var today=new Date()
		//Get 1 day in milliseconds
		
		var one_day=1000*60*60*24
		
		//Calculate difference btw the two dates, and convert to days
		document.write(Math.ceil((today.getTime()-startingdate.getTime())/(one_day))+
		" days has gone by since JavaScriptKit.com started!")


	}



</script>