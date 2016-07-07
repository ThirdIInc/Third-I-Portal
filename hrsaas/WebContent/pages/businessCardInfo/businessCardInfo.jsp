<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="BusinessCardInfo" method="post"
	name="businessCardInfoForm" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="7">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Request
					for Business Card Application Form </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
		</tr>
		<s:hidden name="status" />
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="30%" id="ctrlHide" align="right"><font color="red">*</font>
					Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="commentFlag">
			<tr>
				<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0"
					class="formbg">
					<tr>
						<td><label class="set" name="comments" id="comments"
							ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
						</td>
						<td><s:textarea name="apprComments" value="%{apprComments}"
							rows="2" cols="70" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<!-- APPROVER DETAILS STARTS -->
		<s:if test="%{apprDtlLst == null || apprDtlLst.size() == 0}">
		</s:if>
		<s:else>
			<tr>
				<td>
				<table width="100%" border="0" cellpadding="1" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="6" class="formhead"><strong
							class="forminnerhead"> <label class="set"
							name="appr.heading" id="appr.heading"
							ondblclick="callShowDiv(this);"><%=label.get("appr.heading")%></label>
						</strong></td>
					</tr>
					<tr>
						<td class="formth" width="10%"><b> <label id="appr.srno"
							name="appr.srno" class="set" ondblclick="callShowDiv(this);"><%=label.get("appr.srno")%></label></b>
						</td>
						<td class="formth" width="15%"><b> <label
							id="appr.empName" name="appr.empName" class="set"
							ondblclick="callShowDiv(this)"><%=label.get("appr.empName")%></label></b>
						</td>
						<td class="formth" width="10%"><b> <label id="appr.date"
							name="appr.date" class="set" ondblclick="callShowDiv(this)"><%=label.get("appr.date")%></label></b>
						</td>
						<td class="formth" width="10%"><b> <label
							id="appr.status" name="appr.status" class="set"
							ondblclick="callShowDiv(this)"><%=label.get("appr.status")%></label></b>
						</td>
						<td class="formth" width="30%"><b> <label
							id="appr.comments" name="appr.comments" class="set"
							ondblclick="callShowDiv(this)"><%=label.get("appr.comments")%></label></b>
						</td>
					</tr>
					<s:iterator value="apprDtlLst">
						<tr>
							<td class="sortableTD" align="center"><s:property
								value="apprSrNoIt" /> <s:hidden name="apprSrNoIt"></s:hidden></td>
							<td class="sortableTD" align="center"><s:property
								value="apprNameIt" /> <s:hidden name="apprNameIt"></s:hidden></td>

							<td class="sortableTD" align="center"><s:property
								value="approvedDateIt" /> <s:hidden name="approvedDateIt"></s:hidden>
							</td>
							<td class="sortableTD" align="center"><s:property
								value="apprStatusIt" /> <s:hidden name="apprStatusIt"></s:hidden>
							</td>

							<td class="sortableTD" align="center"><s:property
								value="apprCommentsIt" /> <s:hidden name="apprCommentsIt"></s:hidden>
							</td>
						</tr>
					</s:iterator>

				</table>
				</td>
			</tr>
		</s:else>

		<!-- APPROVER DETAILS END -->
		<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="50%" nowrap="nowrap"><strong>The
					Approver(s) for this application :</strong></td>
					<td colspan="2" nowrap="nowrap"></td>
					<td align="right" width="11%" nowrap="nowrap"><strong>Keep
					Informed To : </strong></td>
					<td width="13%"><s:if test="keepInfoFlag">
						<s:hidden name="kiEmployeeId" />
						<s:hidden name="kiEmployeeToken" />
						<s:textfield name="kiEmployeeName" readonly="true"
							cssStyle="background-color: #F2F2F2;" />
					</s:if></td>
					<td width="5%" colspan="1"><s:if test="keepInfoFlag">
						<img src="../pages/common/css/default/images/search2.gif"
							class="iconImage" width="16" height="15"
							onclick="javascript:getKeepInformedEmp();" />
					</s:if></td>
					<td width="15%"><s:if test="keepInfoFlag">
						<s:submit name="" value=" Add" cssClass="add"
							action="BusinessCardInfo_addKeepInformedEmpList"
							onclick="return callKeepInformed();" />
					</s:if></td>
				</tr>
				<!-- APPROVER LIST  TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="3">
					<table width="100%" border="0" cellpadding="1" cellspacing="0">
						<tr>
							<%
							int y = 0;
							%>
							<%!int managerLength = 0;%>
							<s:iterator value="managerList">
								<tr>
									<td><s:hidden name="managerNameIt" /> <STRONG><s:property
										value="srNoIt" /></STRONG> <s:property value="managerNameIt" /></td>

								</tr>
								<%
								y++;
								%>
							</s:iterator>
							<%
							managerLength = y;
							%>
						</tr>
					</table>
					</td>
				</tr>

				<!-- APPROVER LIST  TABLE  ENDS -->
				<!-- KEEP INFORMED LIST TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="5">
					<table width="100%" border="0" cellpadding="1" cellspacing="0">
						<%int counter11 = 0; int counter2 = 0;%>
						<s:iterator value="keepInformedList" status="stat">
							<tr>
								<td width="80%"><%=++counter11%> <s:hidden
									name="serialNoIt" /> <s:hidden name="keepInformedEmpIdIt" />
								<s:hidden name="keepInformedEmpTokenIt" /> <s:property
									value="keepInformedEmpNameIt" /> <s:hidden
									name="keepInformedEmpNameIt" /></td>

								<s:if test="%{bean.keepInfoFlag}">
									<td width="20%"><a href="#"
										onclick="callForRemove(<%=counter11%>,'<s:property value="keepInformedEmpTokenIt"/>');">Remove</a>
									</td>
								</s:if>
							</tr>
							<%
							counter2 = counter11;
							 
							%>
						</s:iterator>
						<%
						counter2 = 0;
						%>
					</table>
					</td>
					<td></td>
				</tr>

			</table>
			</td>
		</tr>
		<!-- KEEP INFORMED LIST TABLE  ENDS -->
		<!-- Select employee filter -->
		<tr>
			<s:hidden name="empId" />
			<s:hidden name="busCardApplCode" />
			<td colspan="7">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="0" class="formbg">
				<tr>
					<td width="25%" colspan="1" class="formtext"><label
						class="set" name="employee" id="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					<font color="red">*</font> :</td>
					<td width="75%" colspan="3"><s:if test="generalFlag">
						<s:textfield name="empToken" value="%{empToken1}" size="25"
							theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" />
						<s:textfield name="empName" value="%{empName1}" size="25"
							theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" />
					</s:if><s:else>
						<s:textfield name="empToken" size="10" value="%{empToken1}"
							theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" />
						<s:textfield name="empName" size="50" value="%{empName1}"
							theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" />
						<s:if test="empFlag">
							<img src="../pages/images/recruitment/search2.gif" height="18"
								align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'BusinessCardInfo_f9Employee.action');">
						</s:if>
					</s:else></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Employee Information tr -->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="0" class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="1"
						cellspacing="0">
						<tr>
							<td colspan="2"><strong>Applicant Details in System</strong></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td><label class="set" id="empToken" name="empToken"
								ondblclick="callShowDiv(this);"><%=label.get("empToken")%></label>
							</td>

							<td><s:textfield name="empToken1" size="25" theme="simple"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="employeeName" name="employeeName"
								ondblclick="callShowDiv(this);"><%=label.get("employeeName")%></label>
							</td>
							<td><s:textfield name="empName1" size="25" theme="simple"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="desig1" name="desig1"
								onDblClick="callShowDiv(this);"><%=label.get("desig1")%></label>:
							</td>
							<td><s:textfield size="25" name="desig" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="dept1" name="dept1"
								onDblClick="callShowDiv(this);"><%=label.get("dept1")%></label>:
							</td>
							<td><s:textfield size="25" name="dept" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="companyName1" name="companyName1"
								onDblClick="callShowDiv(this);"><%=label.get("companyName1")%></label>
							:</td>
							<td><s:textfield size="25" name="companyName"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="branch1" name="branch1"
								onDblClick="callShowDiv(this);"><%=label.get("branch1")%></label>
							:</td>
							<td><s:textarea rows="2" cols="25" name="branch"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="pinCode1" name="pinCode1"
								onDblClick="callShowDiv(this);"><%=label.get("pinCode1")%></label>
							:</td>
							<td><s:textfield size="25" name="pinCode" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="location1" name="location1"
								onDblClick="callShowDiv(this);"><%=label.get("location1")%></label>
							:</td>
							<td><s:textfield size="25" name="location" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="officeNum1" name="officeNum1"
								onDblClick="callShowDiv(this);"><%=label.get("officeNum1")%></label>
							:</td>
							<td><s:textfield size="25" name="officeNum" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="mobile1" name="mobile1"
								onDblClick="callShowDiv(this);"><%=label.get("mobile1")%></label>
							:</td>
							<td><s:textfield size="25" name="mobile" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="fax" name="fax"
								onDblClick="callShowDiv(this);"><%=label.get("fax")%></label> :</td>
							<td><s:textfield size="25" name="faxNum" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>
						</tr>
						<tr>
							<td><label class="set" id="emailId1" name="emailId1"
								onDblClick="callShowDiv(this);"><%=label.get("emailId1")%></label>
							:</td>
							<td><s:textfield size="25" name="emailId" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td>

						</tr>
						<tr>
							<td><label class="set" id="webSite" name="webSite"
								onDblClick="callShowDiv(this);"><%=label.get("webSite")%></label>
							:</td>
							<td><s:textfield size="25" name="webSiteAddr"
								readonly="true" cssStyle="background-color: #F2F2F2;" /></td>

						</tr>
					</table>
					</td>
					<td style="background-color: #f9f9f9; height: 1px;">&nbsp;</td>
					<!-- ----------------------------  -->
					<td>
					<table width="100%" border="0" align="center" cellpadding="1"
						cellspacing="0">
						<tr>
							<td colspan="2"><strong>Business Card Information
							To Be Printed:</strong></td>
						</tr>
						<tr>
							<td><label class="set" id="cpySysInfo" name="cpySysInfo"
								ondblclick="callShowDiv(this);"><%=label.get("cpySysInfo")%></label>:
							</td>
							<td><s:checkbox theme="simple" name="sysInfoFlag"
								onclick="copyAllEmployeeInfo();" /></td>
						</tr>
						<tr>
							<td><label class="set" id="empToken" name="empToken"
								ondblclick="callShowDiv(this);"><%=label.get("empToken")%></label>
							:</td>
							<td><s:textfield name="empTokenp" size="25"
								value="%{empTokenp}" theme="simple" /></td>
						</tr>
						<tr>
							<td><label class="set" id="employeeName" name="employeeName"
								ondblclick="callShowDiv(this);"><%=label.get("employeeName")%></label><font
								color="red">*</font>:</td>
							<td><s:textfield name="empNamep" size="25"
								value="%{empNamep}" theme="simple" /></td>
						</tr>
						<tr>
							<td><label class="set" id="desig1" name="desig1"
								onDblClick="callShowDiv(this);"><%=label.get("desig1")%></label>:
							</td>
							<td><s:textfield size="25" name="desigp" /></td>
						</tr>
						<tr>
							<td><label class="set" id="dept1" name="dept1"
								onDblClick="callShowDiv(this);"><%=label.get("dept1")%></label>:
							</td>
							<td><s:textfield size="25" name="deptp" /></td>
						</tr>
						<tr>
							<td><label class="set" id="companyName1" name="companyName1"
								onDblClick="callShowDiv(this);"><%=label.get("companyName1")%></label>
							<font color="red">*</font>:</td>
							<td><s:textfield size="25" name="companyNamep" /></td>
						</tr>
						<tr>
							<td><label class="set" id="branch1" name="branch1"
								onDblClick="callShowDiv(this);"><%=label.get("branch1")%></label>
							<font color="red">*</font>:</td>
							<td><s:textarea rows="2" cols="25" name="branchp" /></td>
						</tr>
						<tr>
							<td><label class="set" id="pinCode1" name="pinCode1"
								onDblClick="callShowDiv(this);"><%=label.get("pinCode1")%></label>
							:</td>
							<td><s:textfield size="25" name="pinCodep" maxlength="10"
								onkeypress="return numbersonly1(this)" /></td>
						</tr>
						<tr>
							<td><label class="set" id="location1" name="location1"
								onDblClick="callShowDiv(this);"><%=label.get("location1")%></label>
							:</td>
							<td><s:textfield size="25" name="locationp" /></td>
						</tr>
						<tr>
							<td><label class="set" id="officeNum1" name="officeNum1"
								onDblClick="callShowDiv(this);"><%=label.get("officeNum1")%></label>
							<font color="red">*</font>:</td>
							<td><s:textfield size="25" name="officeNump" maxlength="15"
								onkeypress="return numbersonly1(this)" /></td>
						</tr>
						<tr>
							<td><label class="set" id="mobile1" name="mobile1"
								onDblClick="callShowDiv(this);"><%=label.get("mobile1")%></label>
							:</td>
							<td><s:textfield size="25" maxlength="15" name="mobilep"
								onkeypress="return numbersonly1(this)" /></td>
						</tr>
						<tr>
							<td><label class="set" id="fax" name="fax"
								onDblClick="callShowDiv(this);"><%=label.get("fax")%></label> :</td>
							<td><s:textfield size="25" name="faxNump" maxlength="15"
								onkeypress="return numbersonly1(this)" /></td>
						</tr>
						<tr>
							<td><label class="set" id="emailId1" name="emailId1"
								onDblClick="callShowDiv(this);"><%=label.get("emailId1")%></label>
							:</td>
							<td><s:textfield size="25" name="emailIdp" /></td>
						</tr>
						<tr>
							<td><label class="set" id="webSite" name="webSite"
								onDblClick="callShowDiv(this);"><%=label.get("webSite")%></label>
							:</td>
							<td><s:textfield size="25" name="webSiteAddrp" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="7">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong>Quantity and Comments</strong></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><label class="set" id="qtyOfCards1" name="qtyOfCards1"
						onDblClick="callShowDiv(this);"><%=label.get("qtyOfCards1")%></label>
					:</td>
					<td><s:textfield size="25" name="qtyOfCards"
						onkeypress="return numbersonly1(this)" /></td>
					<td><label class="set" id="reqDate1" name="reqDate1"
						onDblClick="callShowDiv(this);"><%=label.get("reqDate1")%></label>
					:</td>
					<td><s:textfield size="25" name="reqDate" /> <s:a
						href="javascript:NewCal('paraFrm_reqDate','DDMMYYYY');">
						<img class="iconImage" class="iconImage"
							src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td><label class="set" id="comment1" name="comment1"
						onDblClick="callShowDiv(this);"><%=label.get("comment1")%></label>
					:</td>
					<td><s:textarea rows="3" cols="50" name="comment" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="checkRemove" />
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

				</tr>
			</table>
			</td>
		</tr>
	</table>
	<s:hidden name="source" id="source" />
</s:form>
<script type="text/javascript">

function copyAllEmployeeInfo(){
		if(document.getElementById('paraFrm_sysInfoFlag').checked){
			document.getElementById('paraFrm_empTokenp').value=document.getElementById('paraFrm_empToken1').value;
			document.getElementById('paraFrm_empNamep').value=document.getElementById('paraFrm_empName1').value;
			document.getElementById('paraFrm_desigp').value=document.getElementById('paraFrm_desig').value;
			document.getElementById('paraFrm_deptp').value=document.getElementById('paraFrm_dept').value;
			document.getElementById('paraFrm_companyNamep').value=document.getElementById('paraFrm_companyName').value;
			document.getElementById('paraFrm_branchp').value=document.getElementById('paraFrm_branch').value;
			document.getElementById('paraFrm_pinCodep').value=document.getElementById('paraFrm_pinCode').value;
			document.getElementById('paraFrm_locationp').value=document.getElementById('paraFrm_location').value;
			document.getElementById('paraFrm_officeNump').value=document.getElementById('paraFrm_officeNum').value;
			document.getElementById('paraFrm_mobilep').value=document.getElementById('paraFrm_mobile').value;
			document.getElementById('paraFrm_faxNump').value=document.getElementById('paraFrm_faxNum').value;
			document.getElementById('paraFrm_emailIdp').value=document.getElementById('paraFrm_emailId').value;
			document.getElementById('paraFrm_webSiteAddrp').value=document.getElementById('paraFrm_webSiteAddr').value;
		}else{
			document.getElementById('paraFrm_empTokenp').value="";
			document.getElementById('paraFrm_empNamep').value="";
			document.getElementById('paraFrm_desigp').value="";
			document.getElementById('paraFrm_deptp').value="";
			document.getElementById('paraFrm_companyNamep').value="";
			document.getElementById('paraFrm_branchp').value="";
			document.getElementById('paraFrm_pinCodep').value="";
			document.getElementById('paraFrm_locationp').value="";
			document.getElementById('paraFrm_officeNump').value="";
			document.getElementById('paraFrm_mobilep').value="";
			document.getElementById('paraFrm_faxNump').value="";
			document.getElementById('paraFrm_emailIdp').value="";
			document.getElementById('paraFrm_webSiteAddrp').value="";
		}		
	}
	
	
function autoDate() {
		var tDay = new Date();
		var tMonth = tDay.getMonth()+1;
		var tDate = tDay.getDate();
		var tHours =tDay.getHours();
		var tMinutes =tDay.getMinutes();
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10) tDate = "0"+tDate;	
			if ( tHours < 10) tHours = "0"+tHours;	
			if ( tMinutes < 10) tMinutes = "0"+tMinutes;
			var then =tDate+"-"+tMonth+"-"+tDay.getFullYear();
			
		  	 return then;				
  }

function draftFun(){
		var managerLength ='<%=managerLength%>';
		if(managerLength ==0){
			alert("Reporting structure not define for Employee / Please configure Admin");
			return false;
		}
		var fieldName = ["paraFrm_empName","paraFrm_empNamep","paraFrm_companyNamep","paraFrm_branchp","paraFrm_officeNump"];
		var lableName = ["employee","employeeName","companyName1","branch1","officeNum1"];
		var flag = ['enter','enter','enter','enter','enter'];
		
		if(!validateBlank(fieldName, lableName,flag)){
           return false;
        }
        if(!document.getElementById('paraFrm_emailIdp').value ==""){
			var abc=validateEmail('paraFrm_emailIdp');
		  	if(!abc){
		  		return false;
		  	}
	  	}
	  	if(!document.getElementById('paraFrm_webSiteAddrp').value ==""){
	  		var website=validateWebSite('paraFrm_webSiteAddrp');
			if(!website){
				return false;
			}
	  	}
	  	///Req Date Validation
	  	var currentDate =autoDate();
	  	var requiredDate =document.getElementById('paraFrm_reqDate').value; 	
  		var datdiffresignDate =dateDifferenceEqual(currentDate,requiredDate,'paraFrm_reqDate');
  		if(!datdiffresignDate){
  		return false;
  		}	
		var conf=confirm("Do you really want to draft  ?");
  		if(conf) {
  			document.getElementById('paraFrm_status').value="D";
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "BusinessCardInfo_saveBusinessCardInfo.action";
			document.getElementById('paraFrm').submit();
		}	
	}
	
	
function sendforapprovalFun(){	
		var managerLength ='<%=managerLength%>';		
		if(managerLength ==0){
			alert("Reporting structure not define for Employee / Please configure Admin");
			return false;
		}
		var fieldName = ["paraFrm_empName","paraFrm_empNamep","paraFrm_companyNamep","paraFrm_branchp","paraFrm_officeNump"];
		var lableName = ["employee","employeeName","companyName1","branch1","officeNum1"];
		var flag = ['enter','enter','enter','enter','enter'];		
		if(!validateBlank(fieldName, lableName,flag)){
           return false;
        }
        if(!document.getElementById('paraFrm_emailIdp').value ==""){
			var abc=validateEmail('paraFrm_emailIdp');
		  	if(!abc){
		  		return false;
		  	}
	  	}
	  	if(!document.getElementById('paraFrm_webSiteAddrp').value ==""){
	  		var website=validateWebSite('paraFrm_webSiteAddrp');
			if(!website){
				return false;
			}
	  	}
		///Req Date Validation	  	
	  	var currentDate =autoDate();
	   	var requiredDate =document.getElementById('paraFrm_reqDate').value;	  
  		var datdiffresignDate =dateDifferenceEqual(currentDate,requiredDate,'paraFrm_reqDate');  		
  		if(!datdiffresignDate){
  		return false;
  		}	  	
		var conf=confirm("Do you really want to send for approval  ?");
  		if(conf) {
			document.getElementById('paraFrm_status').value="P";
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "BusinessCardInfo_saveBusinessCardInfo.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
function dateDifferenceEqual(fromDate, toDate, fieldName){
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	if(endtime < starttime) { 
		alert("Required date should be greater or equal to current Date");		
		return false;
	}
	return true;
   }

function deleteFun(){
		var fieldName = ["paraFrm_empName","paraFrm_empNamep","paraFrm_companyNamep","paraFrm_branchp","paraFrm_officeNump"];
		var lableName = ["employee","employeeName","companyName1","branch1","officeNum1"];
		var flag = ['enter','enter','enter','enter','enter'];
		
		if(!validateBlank(fieldName, lableName,flag)){
           return false;
        }
		var conf=confirm("Do you really want to delete ?");
  		if(conf){
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "BusinessCardInfo_delete.action";
			document.getElementById('paraFrm').submit();
		}	
	}
	
function reportFun(){
		var fieldName = ["paraFrm_empName","paraFrm_empNamep","paraFrm_companyNamep","paraFrm_branchp","paraFrm_officeNump"];
		var lableName = ["employee","employeeName","companyName1","branch1","officeNum1"];
		var flag = ['enter','enter','enter','enter','enter'];
		
		if(!validateBlank(fieldName, lableName,flag)){
           return false;
        }
        if(!document.getElementById('paraFrm_emailIdp').value ==""){
			var abc=validateEmail('paraFrm_emailIdp');
		  	if(!abc){
		  		return false;
		  	}
	  	}
	  	if(!document.getElementById('paraFrm_webSiteAddrp').value ==""){
	  		var website=validateWebSite('paraFrm_webSiteAddrp');
			if(!website){
				return false;
			}
	  	}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "BusinessCardInfo_report.action";
		document.getElementById('paraFrm').submit();
	}
	
	
function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "BusinessCardInfo_reset.action";
		document.getElementById('paraFrm').submit();
	}
	
function approveFun(){
		var conf=confirm("Do you really want to  approve ?");
	  	if(conf){	
	  		document.getElementById('paraFrm_status').value="A";
			document.getElementById('paraFrm').target="_self";
			document.getElementById("paraFrm").action="BusinessCardInfo_saveApproverDataBusinessCardInfo.action";
			document.getElementById("paraFrm").submit();
		}				
	}
	
function rejectFun(){
		var conf=confirm("Do you really want to  rejected ?");
	  	if(conf){	
	  		document.getElementById('paraFrm_status').value="R";
			document.getElementById('paraFrm').target="_self";
			document.getElementById("paraFrm").action="BusinessCardInfo_saveApproverDataBusinessCardInfo.action";
			document.getElementById("paraFrm").submit();
		}				
	}
	
function sendbackFun(){
		var conf=confirm("Do you really want to  send back ?");
	  	if(conf){	
	  		document.getElementById('paraFrm_status').value="B";
			document.getElementById('paraFrm').target="_self";
			document.getElementById("paraFrm").action="BusinessCardInfo_saveApproverDataBusinessCardInfo.action";
			document.getElementById("paraFrm").submit();
		}				
	}
	
	
function backFun(){
	document.getElementById('paraFrm').target = "_self";
	if(document.getElementById('source').value=='mymessages'){
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
	}
	else if(document.getElementById('source').value=='myservices'){
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
	}
	document.getElementById('paraFrm').submit();
	}
	
	
function callKeepInformed(){
		 var emp =document.getElementById('paraFrm_kiEmployeeName').value;
		 if(emp==""){
			 alert("Please select Keep Informed To ");
			 return false;
		 }
		return true;
	}
	
function getKeepInformedEmp(){
		try{ 
			var empcode=document.getElementById('paraFrm_empToken').value;
			if(empcode==""){
				alert("Please  "+document.getElementById('employee').innerHTML.toLowerCase());
				return false;
			}else{
					callsF9(500,325,'BusinessCardInfo_f9KeepInformedEmployee.action');
			}
		}catch(e){alert(e);} 
	}
	
function callForRemove(id,empCode){
			document.getElementById('paraFrm_checkRemove').value=id;
		    var conf=confirm("Are you sure !\n You want to Remove this record ?");
	  		if(conf){	
				document.getElementById('paraFrm').target="_self";
				document.getElementById("paraFrm").action='BusinessCardInfo_removeKeepInformed.action?kiInfrEmployeeCode='+empCode;
				document.getElementById("paraFrm").submit();
			}	
			else{
				return false;
			}
		return true;			
   }
   
function numbersonly1(myfield){
		 var pinCode=document.getElementById('paraFrm_pinCodep').value		
		 var officeNum=document.getElementById('paraFrm_officeNump').value		
		 var mobileNum=document.getElementById('paraFrm_mobilep').value		
		 var faxNum=document.getElementById('paraFrm_faxNump').value		
		 var qtyCards=document.getElementById('paraFrm_qtyOfCards').value 
		  
		 if(!pinCode==""){
		    var iChars = "0123456789-/ ";
		   	for (var i = 0; i < pinCode.length; i++) {			
			  	if (!(iChars.indexOf(pinCode.charAt(i)) != -1)) {
				  	alert ("Only Numerics Allowed in Pincode");
				  	document.getElementById('paraFrm_pinCodep').value="";
				  	return false;
				}
  			}//for loop close
  		 }
  		 if(!mobileNum==""){
		    var iChars = "0123456789-/ ";
		   	for (var i = 0; i < mobileNum.length; i++) {			
			  	if (!(iChars.indexOf(mobileNum.charAt(i)) != -1)) {
				  	alert ("Only Numerics Allowed in Mobile number");
				  	document.getElementById('paraFrm_mobilep').value="";
				  	return false;
				}
  			}//for loop close
  		 }
  		 if(!officeNum==""){
		    var iChars = "0123456789-/ ";
		   	for (var i = 0; i < officeNum.length; i++) {			
			  	if (!(iChars.indexOf(officeNum.charAt(i)) != -1)) {
				  	alert ("Only Numerics Allowed in Office number");
				  	document.getElementById('paraFrm_officeNump').value="";
				  	return false;
				}
  			}//for loop close
  		 }
  		 if(!faxNum==""){
		    var iChars = "0123456789-/ ";
		   	for (var i = 0; i < faxNum.length; i++) {			
			  	if (!(iChars.indexOf(faxNum.charAt(i)) != -1)) {
				  	alert ("Only Numerics Allowed in Fax number");
				  	document.getElementById('paraFrm_faxNump').value="";
				  	return false;
				}
  			}//for loop close
  		 }
  		 if(!qtyCards==""){
		    var iChars = "0123456789-/ ";
		   	for (var i = 0; i < qtyCards.length; i++) {			
			  	if (!(iChars.indexOf(qtyCards.charAt(i)) != -1)) {
				  	alert ("Only Numerics Allowed in Quantity of Cards");
				  	document.getElementById('paraFrm_qtyOfCards').value="";
				  	return false;
				}
  			}//for loop close
  		 }
	}
	
function mailtovendorFun(){	
		document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="BusinessCardInfo_vendorDetails.action";
		document.getElementById("paraFrm").submit();
	}
	
	
function cancelFun(){
 		document.getElementById('paraFrm').target = "_self";
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages'){
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices'){
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		document.getElementById('paraFrm').submit();
 }
</script>
