
<%@ 
taglib uri="/struts-tags" prefix="s"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<script>

function stopschedulingFun() {
		var stopScheduler = confirm('Do you want to stop the scheduler?');
		
		if(stopScheduler) {
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action = 'DashBoard_stopScheduling.action';
	  		document.getElementById('paraFrm').submit();
		}	
	}


function startschedulingFun() {

var hiddenJobCode =document.getElementById('paraFrm_hiddenJobCode').value;
  
  if(hiddenJobCode=="")
  {
  	alert("Please search record");
  		return false ;
  }
  
  
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'DashBoard_startScheduling.action';
	  	document.getElementById('paraFrm').submit();
	  	
	  	return true ;
	}
 

 
	 function callForDelete(id)
	   {
	 	 
	 	 // alert(id);
	 	  
	 	  var conf=confirm("Do you really want to delete this record ?");
		 	if(conf)
			{
			document.getElementById('paraFrm_hiddenEdit').value=id;
   		document.getElementById("paraFrm").action="AlertClientSetting_deleteData.action";
		document.getElementById("paraFrm").submit();
			}
			 else
			 {
			 return false; 
			 }
	 return true;
	 	 
	   
   		}

function callAdd()
{
var employee = document.getElementById('paraFrm_employeeCode').value;
	if(employee=="")
		{
		alert("Please select employee");
		return false;
		}

}

function callEmployee(){
callsF9(500,325,'AlertClientSetting_f9employee.action');
}

function callSearchFunction(){

	 
		callsF9(500,325,'AlertClientSetting_f9searchaction.action');
	 
	}
	
enableDivsOnLoad();	

function enableDivsOnLoad() 
{
	enableJobFields();
}

function getJobFields() {
		enableJobFields();
		
		var jobDuration = document.getElementById('paraFrm_jobDuration').value;
		
		if(jobDuration == 'Weekly') {
			document.getElementById('paraFrm_jobDayOfWeek').focus();
		} else if(jobDuration == 'Monthly') {
			document.getElementById('paraFrm_jobDayOfMonth').focus();
		}
	
		document.getElementById('paraFrm_jobDayOfWeek').value = "";
		document.getElementById('paraFrm_jobDayOfMonth').value = "";
	}


function enableJobFields() {
		var jobDuration = document.getElementById('paraFrm_jobDuration').value;

		if(jobDuration == '' || jobDuration == 'Daily') {
			document.getElementById('blank1').style.display = '';
			document.getElementById('blank2').style.display = '';
			document.getElementById('jobDayOfWeek1').style.display = 'none';
			document.getElementById('jobDayOfWeek2').style.display = 'none';
			document.getElementById('jobDayOfMonth1').style.display = 'none';
			document.getElementById('jobDayOfMonth2').style.display = 'none';
		} else if(jobDuration == 'Weekly') {
			document.getElementById('blank1').style.display = 'none';
			document.getElementById('blank2').style.display = 'none';
			document.getElementById('jobDayOfWeek1').style.display = '';
			document.getElementById('jobDayOfWeek2').style.display = '';
			document.getElementById('jobDayOfMonth1').style.display = 'none';
			document.getElementById('jobDayOfMonth2').style.display = 'none';
		} else if(jobDuration == 'Monthly') {
			document.getElementById('blank1').style.display = 'none';
			document.getElementById('blank2').style.display = 'none';
			document.getElementById('jobDayOfWeek1').style.display = 'none';
			document.getElementById('jobDayOfWeek2').style.display = 'none';
			document.getElementById('jobDayOfMonth1').style.display = '';
			document.getElementById('jobDayOfMonth2').style.display = '';
		}
	}
	
function callSave()
{
  
  var hiddenJobCode =document.getElementById('paraFrm_hiddenJobCode').value;
  alert
  
  if(hiddenJobCode=="")
  {
  	alert("Please search record");
  		return false ;
  }
    
  var jobDuration = document.getElementById('paraFrm_jobDuration').value;
  
   		
			if(jobDuration == '') {
				alert("Please select the " + document.getElementById("lbDuration").innerHTML);
				document.getElementById('paraFrm_jobDuration').focus();
				return false;
			}
			else if(jobDuration == 'Weekly') {
				var jobDayOfWeek = document.getElementById('paraFrm_jobDayOfWeek').value;
				
				if(jobDayOfWeek == '') {
					alert("Please select the " + document.getElementById("lbDayOfWeek").innerHTML);
					document.getElementById('paraFrm_jobDayOfWeek').focus();
					return false;
				}
			} else if(jobDuration == 'Monthly') {
				var jobDayOfMonth = document.getElementById('paraFrm_jobDayOfMonth').value;
			
				if(jobDayOfMonth > 31 || jobDayOfMonth == "0" || jobDayOfMonth == '') {
					alert("Please enter the " + document.getElementById("lbDayOfMonth").innerHTML + " between 1 and 31.");
					document.getElementById('paraFrm_jobDayOfMonth').focus();
					return false;
				}
			}
		
			if(trim(document.getElementById('paraFrm_jobStartTime').value) == "") {
				alert("Please enter " + document.getElementById("lbStartTime").innerHTML);
				document.getElementById('paraFrm_jobStartTime').focus();
				return false;
			}
			
			if(trim(document.getElementById('paraFrm_jobStartTime').value) == "00:00") {
				alert(document.getElementById("lbStartTime").innerHTML + " has to be greater than 00:00");
				document.getElementById('paraFrm_jobStartTime').focus();
				return false;
			}
			
			if(!validateTime("jobStartTime", 'lbStartTime')) { return false; }	 

return true;
}

function callPopup(){
		callsF9(500,325,'AlertAdminSetting_f9searchaction.action');
	 
	}
	
	function backFun() {

		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DashBoard_input.action';
		document.getElementById('paraFrm').submit();
	}

</script>
<s:form action="DashBoardAction" method="post"
	name="DashBoardAction" validate="true" id="paraFrm" theme="simple">
<s:textfield name="reportName"></s:textfield>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		class="formbg">


		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="text_head"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>

					<td width="100%" class="txt"><strong class="formhead">Dash Board Email Scheduler
					 </strong></td>
					


					<td width="3%" valign="top" class="txt" width="100%">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

<tr>
		<td colspan="3" width="100%" valign="top"><strong>Periodic Report: <s:property value="reportName"/></strong></td>
</tr>

		<tr>
			
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">


				<tr>
					<td colspan="4">
					<s:submit cssClass="add" action="DashBoard_save" theme="simple" value=" Save" onclick="return callSave(); " />
					<s:if test="startShedulerFlag">
					<input type="button" class="token" onclick="startschedulingFun();"
							theme="simple" value=" Start Sheduler" />
						
					</s:if>
					<s:if test="stopShedulerFlag">
					<input type="button" class="token" onclick="stopschedulingFun();"
							theme="simple" value=" Stop Sheduler" />
							</s:if>
					<s:submit cssClass="reset" action="DashBoard_reset"
						theme="simple" value=" Reset" />
					<input type="button" class="back" value=" Back  "
						onclick="return backFun();" />
						
						

<!--							<s:submit cssClass="add" action="DashBoard_save" theme="simple" value=" Save" onclick="return callSave(); " />-->
<!--						<s:if test="%{dashBoardSchedular.startShedulerFlag}">-->
<!--						<input type="button" class="token" onclick="startschedulingFun();"-->
<!--							theme="simple" value=" Start Sheduler" />-->

<!--					</s:if> -->
<!--					<s:if test="stopShedulerFlag">-->

					


<!--					</s:if> -->
					
						<!--<input type="button"
						class="search" value=" Search"
						onclick="javascript:callSearchFunction();" /> -->
						</td>

					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>

			</table>
			</td>
		</tr>





		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				
<!--				<tr>
					 
					<td width="20%" colspan="1"><label class="set" name="subject" id="subject"
						ondblclick="callShowDiv(this);">subject</label></td>
					<td width="80%" colspan="2"><s:property value="subject" /> <s:hidden
						name="subject" /></td>
					 
			 
				</tr> 
				<tr>
					<td width="20%"><label class="set" name="modName" id="modName"
						ondblclick="callShowDiv(this);">modulr Name</label></td>
					<td width="5%"><s:property value="moduleName" /> <s:hidden
						name="moduleName" /></td>
					<td width="25%"></td>
					<td width="20%"></td>
					<td></td>
				</tr>-->
 

				<tr>
					<td width="20%"><label id="lbDuration" name="lbDuration"
						ondblclick="callShowDiv(this);">Duration</label>
					:<font color="RED">*</font></td>
					<td width="5%"><s:select name="jobDuration" headerKey=""
						headerValue="--Select--" onchange="getJobFields();"
						list="#{'Daily' : 'Daily', 'Weekly' : 'Weekly', 'Monthly' : 'Monthly'}" />
					</td>
					<td width="25%"></td>
					<td id="blank1" width="20%"></td>
					<td id="blank2"></td>
					<td id="jobDayOfWeek1" width="20%" style="display: none;"><label
						id="lbDayOfWeek" name="lbDayOfWeek"
						ondblclick="callShowDiv(this);">Day Of Week</label>
					:<font color="RED">*</font></td>
					<td id="jobDayOfWeek2" style="display: none;"><s:select
						name="jobDayOfWeek" headerKey="" headerValue="--Select--"
						list="#{'Sunday' : 'Sunday', 'Monday' : 'Monday', 'Tuesday' : 'Tuesday', 'Wednesday' : 'Wednesday', 'Thursday' : 'Thursday', 
							'Friday' : 'Friday', 'Saturday' : 'Saturday'}" />
					</td>
					<td id="jobDayOfMonth1" width="20%" style="display: none;"><label
						id="lbDayOfMonth" name="lbDayOfMonth"
						ondblclick="callShowDiv(this);">Day Of Month</label>
					:<font color="RED">*</font></td>
					<td id="jobDayOfMonth2" style="display: none;"><s:textfield
						name="jobDayOfMonth" size="8" maxlength="2"
						cssStyle="text-align: right;" onkeypress="return numbersOnly();"
						onblur="callValidateMonthDay();" /></td>
				</tr>

				<tr>
					<td width="20%"><label id="lbStartTime" name="lbStartTime"
						ondblclick="callShowDiv(this);">Start Time</label>
					:<font color="RED">*</font></td>
					<td width="5%"><s:textfield name="jobStartTime" size="9"
						maxlength="5" onkeypress="return numbersWithColon();" /></td>
					<td width="25%">HH24:MI</td>
					<td width="20%"></td>
					<td></td>
				</tr>




			</table>
			</td>
		</tr>


		<s:if test="showFlag">


			<tr>
				<td>
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="2" class="formbg">
					<tr>
						<td colspan="3" class="text_head"><strong
							class="forminnerhead">Add Employee</strong></td>
					</tr>

					<tr>
						<td colspan="1" width="20%"><label class="set"
							name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
						<td colspan="3" width="80%"><s:hidden name="employeeCode"
							value="%{employeeCode}" /><s:textfield readonly="true" size="15"
							name="employeeToken" /> <s:textfield size="50"
							name="employeeName" label="%{getText('employeeName')}"
							readonly="true" /> <img class="iconImage" id="ctrlHide"
							src="../pages/images/recruitment/search2.gif" width="16"
							height="15" border="0" onclick="javascript:callEmployee();" /> <s:if
							test="true">
							<s:submit name="add" cssClass="add"
								action="AlertClientSetting_addEmployee" value=" Add"
								onclick="return callAdd();" />
						</s:if></td>

					</tr>



				</table>
				</td>
			</tr>



		</s:if>



		<s:if test="addFlag">
			<tr>
				<td colspan="6">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">

							<tr>
								<td width="5%" class="formth" nowrap="nowrap">Sr No.</td>
								<td width="15%" class="formth" nowrap="nowrap">Employee Id</td>
								<td width="80%" class="formth" nowrap="nowrap">Employee
								Name</td>

								<s:if test="true">
									<td align="right" width="10%" class="formth" nowrap="nowrap">
									Remove</td>
								</s:if>
							</tr>
							<%
								int i = 0;
								%>

							<%! int t=0; %>

							<s:iterator value="list">

								<tr class="sortableTD">
									<td width="5%" class="sortableTD"><%=i + 1%><input
										type="hidden" name="srNo" value="<%=i + 1%>" /></td>
									<td width="10%" class="sortableTD"><s:property
										value="empToken" /><s:hidden name="empToken" /> <s:hidden
										name="employeeId" /></td>
									<td width="25%" class="sortableTD"><s:property
										value="empName" /><s:hidden name="empName" /></td>

									<s:if test="true">
										<td width="10%" align="center" class="sortableTD"><input
											type="button" class="rowDelete"
											onclick="callForDelete('<%=i + 1 %>')" /></td>
									</s:if>
								</tr>
								<%
									i++;
						 		%>

							</s:iterator>

							<%	t=i; %>

							<%
								if (i == 0) {
								%>
							<tr>
								<td colspan="6" align="center"><font color="red"> No
								data to display</font></td>
							</tr>
							<%
								}
								%>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>


		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">

					<tr>
					<td colspan="4">
					<s:submit cssClass="add" action="DashBoard_save" theme="simple" value=" Save" onclick="return callSave(); " />
					<s:if test="startShedulerFlag">
					<input type="button" class="token" onclick="startschedulingFun();"
							theme="simple" value=" Start Sheduler" />
					</s:if>
					<s:if test="stopShedulerFlag">
					<input type="button" class="token" onclick="stopschedulingFun();"
							theme="simple" value=" Stop Sheduler" />
							</s:if>
					<s:submit cssClass="reset" action="DashBoard_reset"
						theme="simple" value=" Reset" />
					<input type="button" class="back" value=" Back  "
						onclick="return backFun();" />	
				
							
<!--						<s:if test="startShedulerFlag">-->
<!--						-->
<!---->
<!--					</s:if> -->
<!--					<s:if test="stopShedulerFlag">-->
<!--						-->
<!---->
<!---->
<!--					</s:if> -->
<!--						<input type="button"
						class="search" value=" Search"
						onclick="javascript:callSearchFunction();" /> -->
						</td>

					<td width="22%">
					 
					</td>
				</tr>

			</table>
			</td>
		</tr>



	</table>
	<s:hidden name="hiddenJobCode" /> 
	<s:hidden name="hiddenEdit"></s:hidden>

	<s:hidden name="jobQueryType"></s:hidden>
	

</s:form>


