<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="CBTForm" name="CBTForm" id="paraFrm" theme="simple" validate="true">
	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
			  <table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="92%" class="txt"><strong class="text_head">CBT/Self Study Enrollment Form
					</strong></td>
					<td width="4%" valign="middle" align="right" class="txt"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</td>
				</tr>
			</table>
			</td>
		</tr>
			
		<tr>
			<td>
			  <table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="20%">
		 				<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			  </table>
			</td>
		</tr>
		
		
		<!-- Approver Comments Section Begins -->
		<s:if test="approverCommentsFlag">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td colspan="5"><b>Approver Comments</b></td>
					</tr>
					<tr>
						<td width="10%" class="formth">Sr. No.</td>
						<td width="25%" class="formth">Approver Name</td>
						<td width="40%" class="formth">Comments</td>
						<td width="15%" class="formth">Date</td>
						<td width="10%" class="formth">Status</td>
					</tr>
					<%
						int count = 0;
					%>
					<s:iterator value="approverCommentList">
						<tr>
							<td class="sortableTD"><%=++count%></td>
							<td class="sortableTD"><s:property value="apprName" /></td>
							<td class="sortableTD"><s:property value="apprComments" /></td>
							<td class="sortableTD" align="center"><s:property value="apprDate" /></td>
							<td class="sortableTD"><s:property value="apprStatus" /></td>
						</tr>
					</s:iterator>
					<%
						if(count == 0) {
					%>
					<tr>
						<td width="100%" colspan="5" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
					<%
						}
					%>
				</table>
			 </td>
		  </tr>
		  </s:if>
		<!-- Approver Comments Section Ends -->
		
		<tr>
			<td width="100%">
			  <table width="100%" align="center" cellpadding="2" cellspacing="1" class="formbg">
				<tr>
					<td colspan="4">
						<b><label class="set" name="requester.info" id="requester.info"
							ondblclick="callShowDiv(this);"><%=label.get("requester.info")%></label>
						</b>
					</td>
				</tr>

				<tr>
					<td width="20%"><label id="employee" name="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
							<font color="red">*</font> :</td>
					<td colspan="3">
							<s:textfield name="employeeToken" size="25" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;" />
							<s:textfield name="employeeName" size="65" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;" />
							<s:hidden name="employeeID" />
							<s:if test="%{status == '_D' || status == '_B'}">
							
							</s:if>		
							<s:else>
								<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" id='ctrlHide'
								onclick="javascript:callsF9(500,325,'CBTForm_f9Employee.action');">						
							</s:else>
					</td>
				</tr>
				
				<tr>
					<td width="20%"><label id="dept.name" name="dept.name"
						ondblclick="callShowDiv(this);"><%=label.get("dept.name")%></label>
					<font color="red">*</font> :</td>
					<td colspan="3">
						<s:hidden name="deptNo"></s:hidden>
						<s:textfield name="deptName" size="25" readonly="true"/>
						<img id='ctrlHide' src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="17" theme="simple"
							onclick="javascript:callsF9(500,325,'CBTForm_f9Department.action');">
					</td>

				<!-- 
					<td width="20%" align="left"><label id="dept.no" name="dept.no"
						ondblclick="callShowDiv(this);"><%=label.get("dept.no")%></label><font color="red">*</font> :</td>

					<td width="25%">
						<s:textfield name="deptNo" size="25" maxlength="30"/>
					</td>
				 -->	
				</tr>

				<tr>
					<td width="20%"><label id="off.location" name="course"
						ondblclick="callShowDiv(this);"><%=label.get("course")%></label>
					<font color="red">*</font> :</td>
					<td width="25%">
						<s:textfield name="courseNo" size="25" maxlength="40"/>
					<!-- 
							<s:hidden name="courseCode"></s:hidden>
							<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle" width="16" 
							id='ctrlHide' onclick="javascript:callsF9(500,325,'CBTForm_f9Course.action');">					
					 -->	
					
					</td>

					<td width="20%" align="left"><label id="course.desc" name="course.desc"
						ondblclick="callShowDiv(this);"><%=label.get("course.desc")%></label> <font color="red">*</font> :</td>
					<td width="25%">
						<s:textfield name="courseDesc" size="25" maxlength="40"/>
					</td>
				</tr>
				
				<tr>
					<td width="20%"><label id="applnStatus" name="applnStatus"
						ondblclick="callShowDiv(this);"><%=label.get("applnStatus")%></label> <font color="red">*</font> :</td>
					<td width="25%">
						<s:select id="ctrlShow" disabled="true" cssStyle="width:128"	name="applnStatus"
						list="#{'D':'Draft','P':'Pending','B':'Sent Back','A':'Approved','R':'Rejected',
						'N':'Canceled','F':'Forwarded','C':'Applied for cancelation','X':'Cancellation Approved','Z':'Cancellation Rejected'}"  />
					</td>

					<td width="20%"></td>
					<td width="25%">
					</td>
				</tr>
			</table>
		  </td>
		</tr>
		
		<!-- change.personal.infomation  -->
		<tr>
			<td>
			  <table width="100%" align="center" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="request.info" id="loc.info"
						ondblclick="callShowDiv(this);"><%=label.get("loc.info")%></label></b>
					</td>
				</tr>

				<tr>
					<td width="20%"><label id="location" name="location" class="set"
						ondblclick="callShowDiv(this);"><%=label.get("location")%></label>
					<font color="red">*</font> :</td>
					<td colspan="3">
						<s:radio name="locationName" value="%{locationName}" list="#{'us':'US'}" onclick="setData(this);"></s:radio>&nbsp;&nbsp;&nbsp;&nbsp;  
						<s:radio name="locationName" value="%{locationName}" list="#{'canada':'Canada'}" onclick="setData(this);"></s:radio>
					</td>
				</tr>
				
				<tr>
					<td width="20%"><label id="street.addr" name="street.addr"
						ondblclick="callShowDiv(this);"><%=label.get("street.addr")%></label>
					<font color="red">*</font> :</td>
					<td width="25%">
						<s:textfield name="address" size="25" maxlength="30" />
					</td>
					
					<td width="20%"><label id="city" name="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
					<font color="red">*</font> :</td>
					<td width="25%">
						<s:textfield name="city" size="25" maxlength="30"/>
					</td>
				</tr>

				<tr>
					<td width="20%" id="stateLabel"><label id="state" name="state"
						ondblclick="callShowDiv(this);"><%=label.get("state")%></label>
					<font color="red">*</font> :</td>
					<td width="25%" id="stateField">
						<s:textfield name="state" size="25" maxlength="30" />
					</td>
					
					<td width="20%" id="providenceLabel"><label id="providence" name="providence"
						ondblclick="callShowDiv(this);"><%=label.get("providence")%></label>
					<font color="red">*</font> :</td>
					<td width="25%" id="providenceField">
						<s:textfield name="providence" size="25" maxlength="30" />
					</td>
					
					<td width="20%"><label id="country" name="country"
						ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
					<font color="red">*</font> :</td>
					<td width="25%">
						<s:textfield name="country" size="25" maxlength="30"  />
					</td>
				</tr>
				
				<tr>
					<td width="20%"><label class="zip"
						id="zip" name="zip"
						ondblclick="callShowDiv(this);"><%=label.get("zip")%></label>
					<font color="red">*</font> :</td>
					<td width="25%">
						<s:textfield name="zip" size="25" maxlength="30" />
					</td>

					<td width="20%"><label class="ph.no"
						id="ph.no" name="ph.no"	ondblclick="callShowDiv(this);"><%=label.get("ph.no")%></label>
					<font color="red">*</font> :</td>
					<td width="25%">
						<s:textfield name="phNo" size="25"	maxlength="30"  />
					</td>
				</tr>

				<tr>
					<td width="20%"><label class="fax.no"
						id="fax.no" name="fax.no" ondblclick="callShowDiv(this);"><%=label.get("fax.no")%></label>
					<font color="red">*</font> :</td>
					<td width="25%">
						<s:textfield name="faxNo" size="25"	maxlength="30" />
					</td>

					<td width="20%"><label class="email"
						id="email" name="email" ondblclick="callShowDiv(this);"><%=label.get("email")%></label>
					<font color="red">*</font> :</td>
					<td width="25%">
						<s:textfield name="emailAddress" size="25"	maxlength="40"  />
					</td>
				</tr>
			</table>
		</td>
	</tr>

	
	<tr>
		<td>
		  <table width="100%" align="center" class="formbg">
			<tr>
				<td colspan="4"><b>Form Approval</b></td>
			</tr>
			
			<tr>
				<td width="20%"><label id="defaultManager" name="defaultManager"
					ondblclick="callShowDiv(this);"><%=label.get("defaultManager")%></label>
				:</td>
				<td colspan="3">
					<s:property value="defaultManagerToken"/>&nbsp;&nbsp;
					<s:property value="defaultManagerName"/>
					<s:hidden name="defaultManagerToken" />
					<s:hidden name="defaultManagerName" />
					<s:hidden name="defaultManagerID" />
				</td>
			</tr>
			
			<tr>
				<td width="20%"><label id="changeMyManager" name="changeMyManager"
					ondblclick="callShowDiv(this);"><%=label.get("changeMyManager")%></label>
				:</td>
				<td colspan="3">
					<s:textfield name="changeMyManagerToken" size="25" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/>&nbsp;&nbsp;
					<s:textfield name="changeMyManagerName" size="65" theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/>
					<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" id='ctrlHide'
								onclick="javascript:callsF9(500,325,'CBTForm_f9Approver.action');">
					<s:hidden name="changeMyManagerID" />
				</td>
			</tr>
		  </table>
		</td>
	</tr>	
	
	<tr>
		<td width="100%">
		  <table width="100%" align="center" cellpadding="2" cellspacing="1" class="formbg">
			<tr>
				<td width="20%"><b><label id="completedBy" name="completedBy"
					ondblclick="callShowDiv(this);"><%=label.get("completedBy")%></label>
				:</b></td>
				<td width="25%">					
					<s:property value="completedByName"/>
					<s:hidden name="completedByName" />
					<s:hidden name="completedByID" />
				</td>
				<td width="20%"><b><label id="completedOn" name="completedOn"
					ondblclick="callShowDiv(this);"><%=label.get("completedOn")%></label>
				:</b></td>
				<td width="25%">
					<s:property value="completedOn"/>
					<s:hidden name="completedOn" />
				</td>
			</tr>
		  </table>
		</td>
	</tr>	
	
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				</tr>
			</table>
		</td>
	</tr>
	<s:hidden name="cbtID" />
	<s:hidden name="status" />
	<input type="hidden" name="locationRadioValue" id="locationRadioValue" value='<s:property value="locationRadioValue"/>' />
	<s:hidden name="trackingNumber"/>
  </table>
</s:form>


<script>
loadRespectiveLocation();
function loadRespectiveLocation(){
try{
	var locationRadioValue = document.getElementById('locationRadioValue').value;
		if(locationRadioValue=='us'){
			document.getElementById('stateLabel').style.display = '';
			document.getElementById('stateField').style.display = '';
			
			document.getElementById('providenceLabel').style.display = 'none';
			document.getElementById('providenceField').style.display = 'none';
			document.getElementById('paraFrm_providence').value = '';
		}else if(locationRadioValue=='canada') {
			document.getElementById('providenceLabel').style.display = '';
			document.getElementById('providenceField').style.display = '';
			
			document.getElementById('stateLabel').style.display = 'none';
			document.getElementById('stateField').style.display = 'none';
			document.getElementById('paraFrm_state').value = '';
		}else{
			document.getElementById('stateLabel').style.display = 'none';
			document.getElementById('stateField').style.display = 'none';
			document.getElementById('paraFrm_state').value = '';
			document.getElementById('providenceLabel').style.display = 'none';
			document.getElementById('providenceField').style.display = 'none';
			document.getElementById('paraFrm_providence').value = '';
		}
	}catch(e){
		alert("Error occured in loadRespectiveLocation : "+e);
	}	
}


function sendforapprovalFun(){	
	
				var employeeIDVar = document.getElementById('paraFrm_employeeID').value;
				if(employeeIDVar==""){
					alert("Please select employee");
					document.getElementById(paraFrm_employeeToken).focus();
					return false;
				}
				
				var deptNameVar = trim(document.getElementById('paraFrm_deptName').value);
				if(deptNameVar==""){
					alert("Please enter department name.");
					document.getElementById('paraFrm_deptName').focus();
					return false;
				}
				
			/*
				var deptNoVar = trim(document.getElementById('paraFrm_deptNo').value);
				if(deptNoVar==""){
					alert("Please enter department number.");
					document.getElementById('paraFrm_deptNo').focus();
					return false;
				}
			*/
				
				var courseNoVar = trim(document.getElementById('paraFrm_courseNo').value);
				if(courseNoVar==""){
					alert("Please enter course number.");
					document.getElementById('paraFrm_courseNo').focus();
					return false;
				}
				
				var courseDescVar = trim(document.getElementById('paraFrm_courseDesc').value);
				if(courseDescVar==""){
					alert("Please enter course description.");
					document.getElementById('paraFrm_courseDesc').focus();
					return false;
				} 
				
				var locationRadioValueVar = trim(document.getElementById('locationRadioValue').value);
				if(locationRadioValueVar==""){
					alert("Please select location");					
					return false;
				}
				
				var addressVar = trim(document.getElementById('paraFrm_address').value);
				if(addressVar==""){
					alert("Please enter street address.");
					document.getElementById('paraFrm_address').focus();
					return false;
				}
				
				var cityVar = trim(document.getElementById('paraFrm_city').value);
				if(cityVar==""){
					alert("Please enter city.");
					document.getElementById('paraFrm_city').focus();
					return false;
				}
				
				if(locationRadioValueVar=="us"){
					var stateVar = trim(document.getElementById('paraFrm_state').value);
					if(stateVar==""){
						alert("Please enter state.");
						document.getElementById('paraFrm_state').focus();
						return false;
					}
				}else if(locationRadioValueVar=="canada"){
					var providenceVar = trim(document.getElementById('paraFrm_providence').value);
					if(providenceVar==""){
						alert("Please enter providence.");
						document.getElementById('paraFrm_providence').focus();
						return false;
					}
				}
				
				var countryVar = trim(document.getElementById('paraFrm_country').value);
				if(countryVar==""){
					alert("Please enter country.");
					document.getElementById('paraFrm_country').focus();
					return false;
				}
				
				var zipVar = trim(document.getElementById('paraFrm_zip').value);
				if(zipVar==""){
					alert("Please enter zip code.");
					document.getElementById('paraFrm_zip').focus();
					return false;
				}
				
				var phNoVar = trim(document.getElementById('paraFrm_phNo').value);
				if(phNoVar==""){
					alert("Please enter phone number.");
					document.getElementById('paraFrm_phNo').focus();
					return false;
				}
				
				var faxNoVar = trim(document.getElementById('paraFrm_faxNo').value);
				if(faxNoVar==""){
					alert("Please enter fax number.");
					document.getElementById('paraFrm_faxNo').focus();
					return false;
				}
				
				
				var emailAddressVar = trim(document.getElementById("paraFrm_emailAddress").value);
				if(emailAddressVar=="")
					{
						alert("Please enter email address.");
						document.getElementById('paraFrm_emailAddress').focus();
						return false;
					}else{			
					if(!validateEmail('paraFrm_emailAddress'))
   					{   
						return false;
					} 
				}
				
				var defaultManagerIDVar = trim(document.getElementById('paraFrm_defaultManagerID').value);
				if(defaultManagerIDVar==""){
					var changeMyManagerIDVar = document.getElementById('paraFrm_changeMyManagerID').value;
					if(changeMyManagerIDVar==""){
						alert("Please select approver.");
						document.getElementById('paraFrm_changeMyManagerID').focus();
						return false;
					}
				}	
			
	 	var con=confirm('Do you really want to send this application for approval?');
		 if(con)
	 	{
			document.getElementById('paraFrm_status').value='P'
			document.getElementById('paraFrm').target = "_self";
    		document.getElementById('paraFrm').action='CBTForm_sendForApproval.action';
			document.getElementById('paraFrm').submit();
		}		
	}

function draftFun(){
				var employeeIDVar = document.getElementById('paraFrm_employeeID').value;
				if(employeeIDVar==""){
					alert("Please select employee");
					document.getElementById(paraFrm_employeeToken).focus();
					return false;
				}
				
				var deptNameVar = trim(document.getElementById('paraFrm_deptName').value);
				if(deptNameVar==""){
					alert("Please enter department name.");
					document.getElementById('paraFrm_deptName').focus();
					return false;
				}
				
			/*
				var deptNoVar = trim(document.getElementById('paraFrm_deptNo').value);
				if(deptNoVar==""){
					alert("Please enter department number.");
					document.getElementById('paraFrm_deptNo').focus();
					return false;
				}
			*/	
				var courseNoVar = trim(document.getElementById('paraFrm_courseNo').value);
				if(courseNoVar==""){
					alert("Please enter course number.");
					document.getElementById('paraFrm_courseNo').focus();
					return false;
				}
				
				var courseDescVar = trim(document.getElementById('paraFrm_courseDesc').value);
				if(courseDescVar==""){
					alert("Please enter course description.");
					document.getElementById('paraFrm_courseDesc').focus();
					return false;
				} 
				
				var locationRadioValueVar = trim(document.getElementById('locationRadioValue').value);
				if(locationRadioValueVar==""){
					alert("Please select location");					
					return false;
				}
				
				var addressVar = trim(document.getElementById('paraFrm_address').value);
				if(addressVar==""){
					alert("Please enter street address.");
					document.getElementById('paraFrm_address').focus();
					return false;
				}
				
				var cityVar = trim(document.getElementById('paraFrm_city').value);
				if(cityVar==""){
					alert("Please enter city.");
					document.getElementById('paraFrm_city').focus();
					return false;
				}
				
				if(locationRadioValueVar=="us"){
					var stateVar = trim(document.getElementById('paraFrm_state').value);
					if(stateVar==""){
						alert("Please enter state.");
						document.getElementById('paraFrm_state').focus();
						return false;
					}
				}else if(locationRadioValueVar=="canada"){
					var providenceVar = trim(document.getElementById('paraFrm_providence').value);
					if(providenceVar==""){
						alert("Please enter providence.");
						document.getElementById('paraFrm_providence').focus();
						return false;
					}
				}
				
				var countryVar = trim(document.getElementById('paraFrm_country').value);
				if(countryVar==""){
					alert("Please enter country.");
					document.getElementById('paraFrm_country').focus();
					return false;
				}
				
				var zipVar = trim(document.getElementById('paraFrm_zip').value);
				if(zipVar==""){
					alert("Please enter zip code.");
					document.getElementById('paraFrm_zip').focus();
					return false;
				}
				
				var phNoVar = trim(document.getElementById('paraFrm_phNo').value);
				if(phNoVar==""){
					alert("Please enter phone number.");
					document.getElementById('paraFrm_phNo').focus();
					return false;
				}
				
				var faxNoVar = trim(document.getElementById('paraFrm_faxNo').value);
				if(faxNoVar==""){
					alert("Please enter fax number.");
					document.getElementById('paraFrm_faxNo').focus();
					return false;
				}
				
				
				
				var emailAddressVar = trim(document.getElementById("paraFrm_emailAddress").value);
				if(emailAddressVar=="")
					{
						alert("Please enter email address.");
						document.getElementById('paraFrm_emailAddress').focus();
						return false;
					}else{			
					if(!validateEmail('paraFrm_emailAddress'))
   					{   
						return false;
					} 
				}
				
				var defaultManagerIDVar = trim(document.getElementById('paraFrm_defaultManagerID').value);
				if(defaultManagerIDVar==""){
					var changeMyManagerIDVar = document.getElementById('paraFrm_changeMyManagerID').value;
					if(changeMyManagerIDVar==""){
						alert("Please select approver.");
						document.getElementById('paraFrm_changeMyManagerID').focus();
						return false;
					}
				}	
			
			document.getElementById('paraFrm_status').value='D'
			document.getElementById('paraFrm').target = "_self";
		  	document.getElementById('paraFrm').action = 'CBTForm_draftFunction.action';
			document.getElementById('paraFrm').submit();
	}

function backtolistFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CBTForm_back.action';
		document.getElementById('paraFrm').submit();
	}
	function printFun() {	
	window.print();
	}
	
function deleteFun() 
	{
		var con=confirm('Do you want to delete the record(s) ?');
	 	if(con)
	 	{
			document.getElementById('paraFrm').target = "_self";
      		document.getElementById('paraFrm').action = 'CBTForm_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}

function resetFun() {
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'CBTForm_reset.action';
     	document.getElementById('paraFrm').submit(); 
  	}
  	
function setData(id){
	var opt = document.getElementById('locationRadioValue').value =id.value;	
	var locationRadioValue = document.getElementById('locationRadioValue').value;
	
		if(locationRadioValue=='us')
		{
			document.getElementById('stateLabel').style.display = '';
			document.getElementById('stateField').style.display = '';
			
			document.getElementById('providenceLabel').style.display = 'none';
			document.getElementById('providenceField').style.display = 'none';
			document.getElementById('paraFrm_providence').value = '';
		}else if(locationRadioValue=='canada') {
			document.getElementById('providenceLabel').style.display = '';
			document.getElementById('providenceField').style.display = '';
			
			document.getElementById('stateLabel').style.display = 'none';
			document.getElementById('stateField').style.display = 'none';
			document.getElementById('paraFrm_state').value = '';
		}
	}
  	
</script>