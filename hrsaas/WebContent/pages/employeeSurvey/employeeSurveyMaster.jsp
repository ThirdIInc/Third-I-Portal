<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>

<s:form action="EmployeeSurveyMaster" id="paraFrm" theme="simple">
	<table class="formbg" width="100%" cellpadding="2" cellspacing="1">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head"><label
						class="set" name="survey.master" id="survey.master"
						ondblclick="callShowDiv(this);"><%=label.get("survey.master")%></label></strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="paraId" />
		<s:hidden name="sectionId" />
		<s:hidden name="sectionFlag" />
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="70%" colspan="3"><input type="button"
								name="Search" value="Search" class="search"
								onclick="callSearch();" /> <input type="button" name="Save"
								value="Save" class="save" onclick="callSave();" /> <input
								type="button" name="Delete" value="Delete" class="delete"
								onclick="callDelete();" /> <input type="button" name="Reset"
								value="Reset" class="reset" onclick="callReset();" /></td>

							<td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					<label></label></td>

				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td width="15%" colspan="1"><label class="set"
										name="survey.name" id="survey.name"
										ondblclick="callShowDiv(this);"><%=label.get("survey.name")%></label>
									<font color="red">*</font> :</td>
									<td width="85%" colspan="4"><s:hidden name="surveyCode" />
									<s:textfield name="surveyName" size="40" value="%{surveyName}"
										onkeypress="return alphaNumeric();" /></td>
								</tr>



								<tr>
									<td width="15%" colspan="1"><label class="set"
										name="from.date" id="from.date"
										ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>
									<font color="red">*</font> :</td>
									<td width="35%" colspan="1"><s:textfield
										cssStyle="width:130" size="8" theme="simple" name="frmDate" /><s:a
										href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16">
									</s:a></td>
									<td width="10%" colspan="1"><label class="set"
										name="to.date" id="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label><font
										color="red">*</font> :</td>
									<td width="30%" colspan="3"><s:textfield
										cssStyle="width:130" size="8" theme="simple" name="toDate" /><s:a
										href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16">
									</s:a></td>
								</tr>

								<tr>
									<td width="15%" colspan="1"><label class="set"
										name="survey.section" id="survey.section"
										ondblclick="callShowDiv(this);"><%=label.get("survey.section")%></label>
									<font color="red">*</font> :</td>
									<td width="35%" colspan="1"><s:textfield
										name="sectionName" size="40" value="%{sectionName}" onkeypress="return alphaNumeric();"/></td>
									<td width="30%" colspan="3"><input type="button"
										class="add" value=" Add " onclick="return callAdd();" /></td>
								</tr>

							</table>
							</td>
						</tr>

						<%!int i = 0;%>

						<s:if test="sectionFlag">
							<tr>
								<td>

								<table width="100%" border="0" cellpadding="1" cellspacing="1"
									id="tblOption" class="formbg">
									<tr>
										<td align="left" class="txt" colspan="5"><strong
											class="text_head">Section List :</strong></td>
									</tr>
									<tr>
										<td class="formth" align="center" width="10%" nowrap="nowrap"><b><label
											class="set" name="serial.no" id="serial.no"
											ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></b></td>
										<td class="formth" align="center" width="50%"><b><label
											class="set" name="survey.section" id="survey.section1"
											ondblclick="callShowDiv(this);"><%=label.get("survey.section")%></label></b></td>
										<td class="formth" align="center" width="20%">Edit|Remove</td>



									</tr>

									<%
									int j = 0;
									%>
									<%
									int k = 0;
									%>
									<s:iterator value="sectionList">


										<tr>
											<td width="10%" align="center" class="sortableTD"><%=++j%><s:hidden
												name="srNo" /> <input type="hidden" name="sectionNameItt"
												id="sec<%=j %>"
												value="<s:property value="sectionNameItt" />" /></td>
											<td width="50%" class="sortableTD"><s:property
												value="sectionNameItt" /><s:hidden name="sectionCodeItt" /></td>
											<input type="hidden" name="hdeleteOp<%=j%>"
												id="hdeleteOp<%=j%>" />
											<td width="20%" align="center" class="sortableTD"
												nowrap="nowrap"><input type="button" class="edit"
												value="Edit"
												onclick="callforedit(document.getElementById('sec<%=j%>').value,'<%=j%>','<s:property value="sectionCodeItt"/>');" />
											<input type="button" class="delete" value="Remove"
												onclick="callfordel('<%=j%>','<s:property value="sectionCodeItt"/>');" />
											</td>
										</tr>

										<%
										k++;
										%>
									</s:iterator>
									<%
									i = k;
									%>

								</table>

								</td>
							</tr>
						</s:if>

					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>

	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script>
	function callSave(){
		var surveyName = trim(document.getElementById('paraFrm_surveyName').value);
		if(surveyName==""){
			alert("Please enter "+document.getElementById('survey.name').innerHTML.toLowerCase());
			return false;
		}
		
		var frmDate=document.getElementById('paraFrm_frmDate').value;
  		var toDate=document.getElementById('paraFrm_toDate').value;
  		
  		if(frmDate==""){
			alert("Please enter "+document.getElementById('from.date').innerHTML.toLowerCase());
			return false;
		}
		
		if(toDate==""){
			alert("Please enter "+document.getElementById('to.date').innerHTML.toLowerCase());
			return false;
		}
  		
    	if((frmDate!="") && (toDate!="")) {
   			if(!validateDate('paraFrm_frmDate','from.date')){
            	return false;
		    }
	    	if(!validateDate('paraFrm_toDate','to.date')){
	             return false;	 
	        }
	        var datdiff = dateDifferenceEqual(frmDate,toDate,'paraFrm_frmDate','from.date','to.date');
  	  		if(!datdiff){
		  		return false;
	  		}
	   	}
	   	
	   	var flag =<%=i%>;
		if(flag==0){
			alert("Please add sections");
			return false;
		}
	  	document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="EmployeeSurveyMaster_save.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	function callDelete(){
		var surveyCode = trim(document.getElementById('paraFrm_surveyCode').value);
		if(surveyCode==""){
			alert("Please select a record to delete ");
			return false;
		}
		var con=confirm('Do you want to delete the record(s) ?');
		if(con){
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action="EmployeeSurveyMaster_delete.action";
		  	document.getElementById('paraFrm').submit();  
      	}
	}
	
	function callReset(){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="EmployeeSurveyMaster_reset.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	function callSearch() {
		var myWinDiv1 = window.open('', 'myWinDiv1', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv1';
		document.getElementById("paraFrm").action = 'EmployeeSurveyMaster_search.action';
		document.getElementById("paraFrm").submit();
	}
	
	function callAdd(){
		var sectionName=trim(document.getElementById('paraFrm_sectionName').value);
		if(sectionName==""){
			alert('Please enter the '+document.getElementById('survey.section').innerHTML.toLowerCase());
			return false;
		}else {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action='EmployeeSurveyMaster_addSections.action'; 
			document.getElementById('paraFrm').submit();
		}
	}
	
	function callforedit(id,id1,id3){
	try{
		document.getElementById('paraFrm_paraId').value=id1;
		document.getElementById('paraFrm_sectionName').value=id;
	}catch(e){
		alert(e);
	}
	}
	
	 function callfordel(id,id1){
		var conf=confirm("Do you really want to remove this record ?");
		if(conf) {
			document.getElementById("paraFrm_paraId").value=id;
			document.getElementById("paraFrm_sectionId").value=id1;
			document.getElementById("paraFrm").action="EmployeeSurveyMaster_deleteForSection.action";
		    document.getElementById("paraFrm").submit();
	    alert("Record Removed Successfully");
	    return false;
	    }
	}
 
 
</script>