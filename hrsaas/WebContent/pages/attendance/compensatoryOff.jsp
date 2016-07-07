<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>

<script type="text/javascript">
	var eadd = new Array();
</script>

<s:form action="CompOff" validate="true" id="paraFrm" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Compensatory Off Application</strong></td>
						<td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
					</tr>
				</table>	
				<table width="100%">
					<tr>
						<td> 							 
							<s:if test="insertFlag"><s:submit cssClass="add" action="CompOff_save" theme="simple" value="Add New" onclick="return callSave();" /></s:if>
							<s:if test="%{updateFlag}"><s:submit cssClass="add" action="CompOff_save" theme="simple" value="Update" onclick="return callUpdate();" /></s:if>
							<s:if test="%{viewFlag}"><input type="button" class="search" onclick="javascript:callsF9(500,325,'CompOff_f9action.action');" value="Search" /></s:if>
							<s:submit cssClass="reset" action="CompOff_reset" theme="simple" value="Reset" />
					  		<s:if test="deleteFlag"><s:submit cssClass="delete" action="CompOff_delete" theme="simple" value="Delete" onclick="return deletefun();"  /></s:if>
					  		<s:if test="%{viewFlag}"><input type="button" class="token" onclick="callReport();"  value="Report" /></s:if>			  
						</td>
						<td width="22%"><div align="right"><font color="red">*</font>Indicates Required</div></td>
					</tr>
				</table>
				<table width="100%" cellspacing="2" class="formbg">
					<tr>
						<td colspan="5" class="formhead">
							<strong class="forminnerhead"><label  class = "set"  id="compenoff"  name="compenoff" ondblclick="callShowDiv(this);"><%=label.get("compenoff")%></label></strong>
						</td>
					</tr>				 
					<tr>
						<s:if test="%{generalFlag}">
							<td width="20%" colspan="1">
								<label  class = "set"  id="employee"  name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font color="red" >*</font>:
							</td>
							<td width="80%" colspan="5">
								<s:textfield name = "comp.empToken" size="20" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
								<s:textfield size="84%" name="comp.empName" readonly="true"  cssStyle="background-color: #F2F2F2;"/> 			   
								<s:hidden name = "comp.compId"/><s:hidden 	name="checkEdit"/><s:hidden name="comp.empId" /> 
							</td>
						</s:if>
						<s:else>
							<td width="20%" colspan="1">
								<label id="employee"  name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font color="red" >*</font>:
							</td>
							<td width="80%" colspan="4">
								<s:textfield name = "comp.empToken" size="20" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
								<s:textfield size="84%" name="comp.empName" readonly="true"  cssStyle="background-color: #F2F2F2;"/> 
						 		<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="14" width="14" 
						 		onclick="javascript:callsF9(500,325,'CompOff_f9Employee.action');">
								<s:hidden name = "comp.compId"/><s:hidden 	name="checkEdit"/><s:hidden name="comp.empId" /> 
							</td>
						</s:else>
					</tr> 
					<tr>
						<td width="20%" colspan="1"><label  class = "set"  id="branch"  name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
						<td width="35%" colspan="1"><s:textfield size="30"  name="comp.branchName" readonly="true"  cssStyle="background-color: #F2F2F2;" /></td>
						<td width="15%" colspan="1"><label  class = "set"  id="department"  name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
						<td width="20%" colspan="1"><s:textfield size="28" maxlength="40"  name="comp.dept" readonly="true"  cssStyle="background-color: #F2F2F2;" /></td>
					</tr>
					<tr>
						<td width="20%" colspan="1"><label  class = "set"  id="designation"  name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:</td>
						<td width="35%" colspan="1"><s:textfield size="30"   name="comp.desg" readonly="true"  cssStyle="background-color: #F2F2F2;" /></td>
						<td width="15%" colspan="1"><label  class = "set"  id="app.dateate"  name="app.dateate" ondblclick="callShowDiv(this);"><%=label.get("app.dateate")%></label><font color="red" >*</font>:</td>
						<td width="20%" colspan="1"><s:textfield size="12"  name="comp.appDate" onblur="return validateDate('paraFrm_comp_appDate','Date');" onkeypress="return numbersWithHiphen();" maxlength="10" />
							<s:a href="javascript:NewCal('paraFrm_comp_appDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18">
							</s:a>
						</td>
					</tr> 
					<tr>
						<td width="20%" colspan="1"><label  class = "set"  id="status"  name="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
						<td width="35%" colspan="1"> <s:select theme="simple" name="status" value="%{status}" list="#{'P':'Pending','A':'Approved','R':'Rejected'}" disabled="true"></s:select></td>
						<td width="15%" colspan="1"></td>
					</tr>
					<tr>
						<td width="20%" colspan="1"><label  class = "set"  id="commenet"  name="comment" ondblclick="callShowDiv(this);"><%=label.get("comment")%></label>:</td>
						<td  colspan="2" width="50%"> <s:textarea theme="simple" name="comments" value="%{comments}" cols="72" rows="3" wrap="wrap" onkeyup="return callLength('isappcount');" /></td>
							<td width="20%" colspan="1" nowrap="nowrap">
							<label id="remCha" name="remCha" ondblclick="callShowDiv(this);"><%=label.get("remCha")%></label> (Max:500)
							<s:textfield name="isappcount" readonly="true" size="5" />
										</td>
						
					</tr>
				</table>
				<table width="100%" class="formbg">
					<tr>
				    	<td colspan="5">
				    		<table width="100%">	    
								<tr>
									<td colspan="5" class="formhead"><strong class="forminnerhead"> <label  class = "set"  id="compoffdtl"  name="compoffdtl" ondblclick="callShowDiv(this);"><%=label.get("compoffdtl")%></label></strong> </td>
								</tr>
								<tr>
									<td width="17%" colspan="1"><label  class = "set"  id="purpose"  name="purpose" ondblclick="callShowDiv(this);"><%=label.get("purpose")%></label><font color="red" >*</font>:</td>
									<td width="30%" colspan="1"><s:textfield size="30"  name="comp.prName" maxlength="180" id="paraFrm_comp_prName" /></td>
									<td width="15%" colspan="1"> <label  class = "set"  id="compdate"  name="compdate" ondblclick="callShowDiv(this);"><%=label.get("compdate")%></label><font color="red" >*</font>:</td>
									<td width="20%" colspan="3"><s:textfield size="12"  name="comp.prDate" onblur="return validateDate('paraFrm_comp_prDate','CompOff Date');"  onkeypress="return numbersWithHiphen();" maxlength="10"/>
										<s:a href="javascript:NewCal('paraFrm_comp_prDate','DDMMYYYY');">
											<img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18">
										</s:a>
									</td>
								</tr>
								<tr>
									<td width="17%" colspan="1"><label  class = "set"  id="sTime"  name="sTime" ondblclick="callShowDiv(this);"><%=label.get("sTime")%></label> [HH24:MM]<font color="red" >*</font>:</td>
									<td width="30%" colspan="1"><s:textfield size="12" name="comp.startTime" onblur="return validateTime('paraFrm_comp_startTime','Start Time');" maxlength="5"   onkeypress="return numbersWithColon();"/></td>
									<td width="17%" colspan="1"> <label  class = "set"  id="eTime"  name="eTime" ondblclick="callShowDiv(this);"><%=label.get("eTime")%></label> [HH24:MM] <font color="red" >*</font>:</td>
									<td width="30%" colspan="3"><s:textfield size="12"   name="comp.endTime" onblur="return validateTime('paraFrm_comp_endTime','End Time');"  maxlength="5"  onkeypress="return numbersWithColon();" /></td>
								</tr>
						 		<tr>
									<td colspan="5" align="center">
										<s:submit cssClass="add" action="CompOff_add" value="   Add   " onclick="return callAdd();" />&nbsp;
						 			</td>
								</tr>
							</table>
						</td>
					</tr> 
					<tr>
						<td colspan="5">
							<s:hidden name="iteratorFlag"/>
							<s:if test="iteratorFlag">
								<table width="100%">
			  						<tr>
			  							<td class="formth" width="05%"><label id="sno"  name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
			  							<td class="formth" width="35%"><label id="purpose1"  name="purpose" ondblclick="callShowDiv(this);"><%=label.get("purpose")%></label></td>
										<td class="formth" width="14%" align="center"><label id="compdate1"  name="compdate" ondblclick="callShowDiv(this);"><%=label.get("compdate")%></label></td>
										<td class="formth" width="10%" align="center"><label id="sTime1"  name="sTime" ondblclick="callShowDiv(this);"><%=label.get("sTime")%></label></td>
										<td class="formth" width="10%" align="center"><label id="eTime1"  name="eTime" ondblclick="callShowDiv(this);"><%=label.get("eTime")%></label></td>
										<td class="formth" width="10%" align="center">Edit/Delete</td>
			  						</tr>
			  				  		<%	int srNo = 0; %>
			  						<s:iterator value="compList">
					 					<tr>		   
											<td class="sortableTD"><%=++srNo%></td>
										  	<td class="sortableTD" width="35%" nowrap="nowrap" align="left"> <s:property  value="hprojName"/>  </td>
										  	<td class="sortableTD" width="14%" nowrap="nowrap" align="center"><s:property  value="hDate"/></td>   
										  	<td class="sortableTD" width="10%" nowrap="nowrap" align="center"><s:property  value="hsTime"/></td>
										  	<td class="sortableTD" width="10%" nowrap="nowrap" align="center"><s:property  value="heTime"/>
										  		<s:hidden name ="hprojName" /><s:hidden  name ="hDate" id='<%="hDate"+srNo%>' />
										  		<s:hidden name ="hsTime"  /><s:hidden name ="heTime"  /><s:hidden  name ="serialNo" /> 
					  						</td>
											 <td class="sortableTD" width="10%" nowrap="nowrap">
											 	<input type="button"	class ="edit" value="Edit" onclick="return setEdit('<%=srNo-1%>','<s:property value="hprojName" />','<s:property 	value="hDate" />',
												'<s:property value="hsTime" />','<s:property value="heTime" />','<s:property value="hidMon" />','<s:property value="hyear" />');" /> 
												<input type="button" class ="delete" value="    Delete" onclick="return setSerialNo(<%=(srNo-1)%>);" />	 
												<script>
													eadd[<%=srNo%>] = document.getElementById('hDate'+<%=srNo%>).value; 
												</script>	
											</td>
					     				</tr> 
					    			</s:iterator>
			  					</table> 			
							</s:if>			
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type = "text/javascript" src = "../pages/common/datetimepicker.js"></script>

<script>
	function setSerialNo(code) { 
 		if(!(document.getElementById('paraFrm_status').value == "P")) {
			alert("You can not delete approved or rejected application !");
			return false;
		}
    	
    	var conf = confirm("Do you really want to delete this record ?");
  	 	if(conf) {  		 
			document.getElementById('paraFrm_serialNo').value = code;  			 
	        document.getElementById('paraFrm').action = 'CompOff_delItem.action';	        
	        document.getElementById('paraFrm').submit();		         
			return true;
  		} else {
			return false;
		}      
		return true;
	}

	function callSave() { 
  		var empId = document.getElementById('paraFrm_comp_empId').value;
  		var appDate = document.getElementById('paraFrm_comp_appDate').value;
    	var compId = document.getElementById('paraFrm_comp_compId').value;
	 
	 	if(compId != "") {
	   		alert("Please click on 'Update' button");
	   		return false;
	 	}
   
	 	if(empId == "") {
	   		alert("Please select " + document.getElementById('employee').innerHTML.toLowerCase());	   
	   		return false;
	 	}
	 	
	 	if(appDate == "") {
	   		alert("Please enter " + document.getElementById('app.dateate').innerHTML.toLowerCase());
	   		return false;
	 	}
	 
	   	if(eadd.length <= 0) {
	   		alert("Please add atleast one Record !");
	   		return false;
	 	}
	 	
	 		var cmt =document.getElementById('paraFrm_comments').value;
	 		if(cmt!="")
	 		{
				var remain = 500 - eval(cmt.length);
					
				if(eval(remain)< 0){
				
				alert("Maximum characters for Comments field is 500 Remove Extra "+remain+"Characters.");
				return false;
				}
				
	 		
	 		}
	 	
 		return true;
	}

	function callUpdate() {
		var empId = document.getElementById('paraFrm_comp_empId').value;
     	var appDate = document.getElementById('paraFrm_comp_appDate').value;
	 	var compId = document.getElementById('paraFrm_comp_compId').value;
	 	
	 	if(compId == "") {
	   		alert("Please select the record to update !");
	   		return false;
	 	}
	 
	 	if(!(document.getElementById('paraFrm_status').value == "P" || document.getElementById('paraFrm_status').value == "")) {
	  		alert("You can not update approved or rejected application !");
			return false;
		}
	   
	 	if(empId == "") {
	   		alert("Please select " + document.getElementById('employee').innerHTML.toLowerCase());	   
	   		return false;
	 	}
	 	
	 	if(appDate == "") {
	   		alert("Please enter " + document.getElementById('app.dateate').innerHTML.toLowerCase());
	   		return false;
	 	}
	 
	   	if(eadd.length <= 0) {
	   		alert("Please add atleast one Record !");
	   		return false;
	 	}
	 	
	 	var cmt =document.getElementById('paraFrm_comments').value;
	 		if(cmt!="")
	 		{
				var remain = 500 - eval(cmt.length);
					
				if(eval(remain)< 0){
				
				alert("Maximum characters for Comments field is 500 Remove Extra "+remain+"Characters.");
				return false;
				}
				
	 		
	 		}
	 	return true;
	}

	function callAdd() {
	try{
    	var empId = document.getElementById('paraFrm_comp_empId').value;
  		var prName = document.getElementById('paraFrm_comp_prName').value;
  		var prDate = document.getElementById('paraFrm_comp_prDate').value;
  		var startTime = document.getElementById('paraFrm_comp_startTime').value;
  		var endTime = document.getElementById('paraFrm_comp_endTime').value;
  		var prDate = document.getElementById('paraFrm_comp_prDate').value;
  		var checkEdit = document.getElementById("paraFrm_checkEdit").value;
  
  		if(!(document.getElementById('paraFrm_status').value == "P")) {
			alert("You can not modify approved or rejected application !");
			return false;
		}
   		if(empId == "") {
	   		alert("Please select "+document.getElementById('employee').innerHTML.toLowerCase());
	   		return false;
	 	}
  		var purpose = trim(prName);
		if(purpose == "") {
			alert("Please enter "+document.getElementById('purpose').innerHTML.toLowerCase());
			document.getElementById('paraFrm_comp_prName').value = "";
			document.getElementById('paraFrm_comp_prName').focus();
			return false;
		}
		if(prDate == "") {
			alert("Please enter " + document.getElementById('compdate').innerHTML.toLowerCase());
			document.getElementById('paraFrm_comp_prDate').focus();
			return false;
		}
		
		if(!validateDate('paraFrm_comp_prDate','compdate')) {
			return false;
		}
		
		if(startTime == "") {
			alert("Please enter " + document.getElementById('sTime').innerHTML.toLowerCase());
			document.getElementById('paraFrm_comp_startTime').focus();
			return false;
		}
		if(!validateTime('paraFrm_comp_startTime','sTime')) {
			return false;
		}
	
		if(endTime == "") {
			alert("Please enter " + document.getElementById('eTime').innerHTML.toLowerCase());
			document.getElementById('paraFrm_comp_endTime').focus();
			return false;
		}
		if(!validateTime('paraFrm_comp_endTime','eTime')) {
			return false;
		}
		
		if(!timeDifference(startTime,endTime, 'paraFrm_comp_endTime', 'sTime', 'eTime')) {
			return false;
		}
		
		
		if(eadd.toString() != "") {
			var chk = eval(checkEdit)+ 1;
			if((checkEdit == "")) {
				for(var i = 1; i <eadd.length; i++) {
					if(eadd[i] == prDate) {
						alert("Comp Off is already added for this date! Please choose another date.");
						return false;
					}
				} 
			} else {
				for(var i = 1; i <eadd.length; i++) {   
					if(i != chk) {  
						if(eadd[i] == prDate) {
							alert("Comp Off is already added for this date! Please choose another date.");
							return false;
						}
					}   
				}  
			 }
		}
	}
		catch(e)
		{
		
		}
		
		
		return true;
	}

	function setEdit(id, prName, hDate, hsTime, heTime, hidMonth, hyear) {
		document.getElementById("paraFrm_comp_prName").value = prName;
		document.getElementById("paraFrm_comp_prDate").value = hDate;
	  	document.getElementById("paraFrm_comp_startTime").value = hsTime;
	  	document.getElementById("paraFrm_comp_endTime").value = heTime;
	  	
	  	
	  	
	  	 document.getElementById("paraFrm_checkEdit").value=id;	 
	  
	  		}
	 
	function callTime() {
		var startTime = document.getElementById('paraFrm_comp_startTime').value;
       	var endTime = document.getElementById('paraFrm_comp_endTime').value;
	    var start = startTime.split(":");
	    var end = endTime.split(":");	    
	    var first = eval(start[0])* eval(start[1]);
	    var second = eval(end[0])* eval(end[1]);
	}
	 
	function callReport() {
		var id = document.getElementById('paraFrm_comp_compId').value;
	 	if(id == "") {
	   		alert(" Please Select the Record !");
	   		return false;
	 	} else {
		 document.getElementById('paraFrm').target ='_blank';	 
		 document.getElementById('paraFrm').action = "CompOff_report.action";
		 document.getElementById('paraFrm').submit();
		 document.getElementById('paraFrm').target ="";
	 	}
	}
	 
	function autoDate () {
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
		if(tMonth < 10) { tMonth = "0" + tMonth; }
		if(tDate < 10) { tDate = "0" + tDate; }
		if(document.getElementById('paraFrm_comp_appDate').value == "") {
			document.getElementById("paraFrm_comp_appDate").value = tDate + "-" + tMonth + "-" + tDay.getFullYear();
		}
	}
	
	autoDate();
 
	function deletefun() {
  		if(document.getElementById('paraFrm_comp_compId').value == "") {
  			alert("Please select a record to delete !");
  			return false;	
  		}
  		if(!(document.getElementById('paraFrm_status').value == "P" || document.getElementById('paraFrm_status').value == "")) {
			alert("You can not delete approved or rejected compesatory off application !");
			return false;
		}
	}
	
	function callLength(type){ 

 if(type=='isappcount'){
	
			var cmt =document.getElementById('paraFrm_comments').value;
			var remain = 500- eval(cmt.length);
			document.getElementById('paraFrm_isappcount').value = remain;
			
				if(eval(remain)< 0){
			document.getElementById('paraFrm_comments').style.background = '#FFFF99';
			
			}else document.getElementById('paraFrm_comments').style.background = '#FFFFFF';
		
		}
 
 
 }
 
</script>