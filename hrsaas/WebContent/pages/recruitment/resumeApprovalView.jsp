<%@ taglib prefix="s" uri="/struts-tags"%>

<%@include file="/pages/common/labelManagement.jsp"%>
<s:hidden name="radioEmp" />
<s:form action="ResumeApproval" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="refFlag" />
	<s:hidden name="qualiFlag" />
	<s:hidden name="skillFlag" />
	<s:hidden name="expFlag" />
	<s:hidden name="domFlag" />
	<s:hidden name="myPage" />
	<s:hidden name="checkStatus"></s:hidden>
	<table width="100%" align="right" class="formbg">
		<!--
        <tr>
          <td colspan="3" valign="bottom" class="txt">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" background="../pages/common/css/default/images/lines.gif" class="txt"><img src="../pages/common/css/default/images/lines.gif" width="16" height="1" /></td>
        </tr>
        -->
		<!--<tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/common/css/default/images/space.gif" width="5" height="5" /></td>
        </tr>
        -->
		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Resume Approval </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!--
    	  <tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/common/css/default/images/space.gif" width="5" height="5" /></td>
        </tr>
        -->
		<!--
        <tr>
          <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt"><strong class="formhead">Candidate DataBank </strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
        </tr>
        -->
		<!--<tr>
          <td height="5" colspan="3">
          <img src="../pages/common/css/default/images/space.gif" width="5" height="7" />
          </td>
        </tr>   -->
		<s:hidden name="candCode" />
		<s:hidden name="candName" />
		<s:hidden name="experience" />
		<s:hidden name="postingDate" />
		<s:hidden name="shortStatus" />
		<s:hidden name="cityCode1" />
		<s:hidden name="stateCode1" />
		<s:hidden name="countryCode1" />
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>
		<!--
         <tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/common/css/default/images/space.gif" width="5" height="5" /></td>
        </tr>
         -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="7" /></td>
						</tr>
						<tr>
							<strong class="formhead">Candidate Details:</strong>
							<td colspan="5" class="formhead"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="7" /></td>
						</tr>

						<tr>
							<td class="formtext" height="22"><label class="set"
								name="title" id="title" ondblclick="callShowDiv(this);"><%=label.get("title")%></label>
							:</td>
							<td width="27%" height="22"><s:property value="titleName" />
							</td>
							<td height="22" class="formtext" valign="top"><label
								name="photograph" id="photograph"
								ondblclick="callShowDiv(this);"><%=label.get("photograph")%></label>
							:</td>
							<td height="22" rowspan="6">
							<table width="5" height="100" border="0" cellpadding="0"
								cellspacing="0" class="border">
								<tr>
									<td bgcolor="#FFFFFF" align="center" style="padding: 3px;">
									<%
									String str ="";
										try{
											str = (String) request.getAttribute("photo");
											 
										}
									catch(Exception e)
									{
										str ="";
									}
										//out.println("str......."+str);
									%> <%
 if (str.equals("") || str.equals("null") || str.equals(" ")) {
 %>
									<img src="../pages/images/employee/NoImage.jpg" height="150"
										width="150" align="middle" /> <%
 } else {
 %> <img
										src="../pages/images/<%=session.getAttribute("session_pool") %>/databankphoto/<%=str %>"
										height="150" width="150" /> <%
 }
 %>
									</td>
								</tr>
							</table>

							</td>
						</tr>
						<tr>

							<td height="22" class="formtext"><label class="set"
								name="first.name" id="firstname" ondblclick="callShowDiv(this);"><%=label.get("first.name")%></label><span
								class="style3"></span> :</td>
							<td height="22"><s:property value="firstName" /></td>
						</tr>
						<tr>

							<td height="22" class="formtext"><label name="middle.name"
								id="middlename" ondblclick="callShowDiv(this);"><%=label.get("middle.name")%></label><span
								class="style3"></span> :</td>
							<td height="22"><s:property value="middleName" /></td>
						</tr>
						<tr>

							<td height="22" class="formtext"><label name="last.name"
								id="lastname" ondblclick="callShowDiv(this);"><%=label.get("last.name")%></label>
							:</td>
							<td height="22"><s:property value="lastName" /></td>
						</tr>
						<tr>
							<td width="20%" height="22" class="formtext" nowrap="nowrap"><label
								name="dob" id="dob" ondblclick="callShowDiv(this);"><%=label.get("dob")%></label>
							:</td>
							<td height="22"><s:property value="dob" />
						<tr>

							<td height="22" class="formtext"><label name="gend"
								id="gender" ondblclick="callShowDiv(this);"><%=label.get("gend")%></label>
							:</td>
							<td height="22"><s:property value="genderView" /></td>
						</tr>

						<tr>
							<td height="22" class="formtext"><label
								name="marital.status" id="marital.status"
								ondblclick="callShowDiv(this);"><%=label.get("marital.status")%></label>
							:</td>
							<td height="22"><s:property value="marriageView" /></td>

						</tr>

						<tr>
							<td height="22" class="formtext"><label name="experience"
								id="experience" ondblclick="callShowDiv(this);"><%=label.get("experience")%></label>
							:</td>
							<td height="22"><s:property value="expView" /></td>

						</tr>
						<tr>
							<td height="22" class="formtext" valign="top"><label
								name="address" id="address" ondblclick="callShowDiv(this);"><%=label.get("address")%></label>
							:</td>
							<td height="22" valign="top"><s:property value="address" />
							</td>

							<td height="22" class="formtext" valign="top"><label
								name="address1" id="address1" ondblclick="callShowDiv(this);"><%=label.get("address1")%></label>
							:</td>
							<td height="22" valign="top"><s:property value="address1" /></td>

						</tr>

						<tr>
							<td height="22" class="formtext"><label name="city"
								id="city" ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
							:</td>
							<td height="22"><s:property value="city" /> <s:hidden
								name="cityCode" /> <s:hidden name="stateCode" /> <s:hidden
								name="countryCode" /></td>

							<td height="22" class="formtext"><label name="city"
								id="city1" ondblclick="callShowDiv(this);"><%=label.get("city")%></label>
							:</td>
							<td height="22"><s:property value="city1" /></td>

						</tr>
						<tr>
							<td height="22" class="formtext"><label name="state"
								id="state" ondblclick="callShowDiv(this);"><%=label.get("state")%></label>
							:</td>
							<td height="22"><s:property value="state" /></td>

							<td height="22" class="formtext"><label name="state"
								id="state1" ondblclick="callShowDiv(this);"><%=label.get("state")%></label>
							:</td>
							<td height="22"><s:property value="state1" /></td>
						</tr>
						<tr>
							<td height="22" class="formtext"><label name="country"
								id="country" ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
							:</td>
							<td height="22"><s:property value="country" /></td>
							<td height="22" class="formtext"><label name="country"
								id="country1" ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
							:</td>
							<td height="22"><s:property value="country1" /></td>
						<tr>

							<td height="22" class="formtext"><label name="pincode"
								id="pincode" ondblclick="callShowDiv(this);"><%=label.get("pincode")%></label>
							:</td>
							<td height="22"><s:property value="pincode" /></td>


							<td height="22" class="formtext"><label name="pincode"
								id="pincode1" ondblclick="callShowDiv(this);"><%=label.get("pincode")%></label>
							:</td>
							<td height="22"><s:property value="pincode1" /></td>
						</tr>
						<tr>
							<td height="22" class="formtext"><label name="resphone"
								id="resphone" ondblclick="callShowDiv(this);"><%=label.get("resphone")%></label>
							:</td>
							<td height="22"><s:property value="resPhone" /></td>
							<td height="22" class="formtext"><label name="mobile"
								id="mobile" ondblclick="callShowDiv(this);"><%=label.get("mobile")%></label>
							:</td>
							<td height="22"><s:property value="mobile" /></td>
						</tr>
						<tr>
							<td height="22" class="formtext"><label name="offphone"
								id="offphone" ondblclick="callShowDiv(this);"><%=label.get("offphone")%></label>
							:</td>
							<td height="22"><s:property value="offPhone" /></td>
							<td height="22" class="formtext"><label name="email.id"
								id="email.id" ondblclick="callShowDiv(this);"><%=label.get("email.id")%></label>
							:</td>
							<td height="22"><s:property value="email" /></td>
						</tr>

						<tr>
							<td height="22" class="formtext"><label name="current.ctc"
								id="current.ctc" ondblclick="callShowDiv(this);"><%=label.get("current.ctc")%></label>
							:</td>
							<td height="22"><s:property value="currentCtc" /></td>
							<td height="22" class="formtext"><label name="exp.ctc"
								id="exp.ctc" ondblclick="callShowDiv(this);"><%=label.get("exp.ctc")%></label>
							:</td>
							<td height="22"><s:property value="expectedCtc" /></td>
						</tr>

						<tr>
							<td height="22" class="formtext"><label name="passport.no"
								id="passportno" ondblclick="callShowDiv(this);"><%=label.get("passport.no")%></label>
							:</td>
							<td height="22"><s:property value="passport" /></td>
							<td height="22" class="formtext"><label name="pan.no"
								id="pan.no" ondblclick="callShowDiv(this);"><%=label.get("pan.no")%></label>
							:</td>
							<td height="22"><s:property value="panNo" /></td>
						</tr>

						<!-- ADDED BY DHANASHREE BEGINS -->
						<tr>
							<td height="22" class="formtext"><label
								name="candidate.company" id="candidateCompany1"
								ondblclick="callShowDiv(this);"><%=label.get("candidate.company")%></label>
							:</td>
							<td height="22"><s:property value="candidateCompany" /></td>
							<td height="22" class="formtext"><label
								name="candidate.position" id="candidatePosition1"
								ondblclick="callShowDiv(this);"><%=label.get("candidate.position")%></label>
							:</td>
							<td height="22"><s:property value="candidatePosition"  /></td>
						</tr>
						<!-- ADDED BY DHANASHREE ENDS -->
						
						
							<!-- ADDED BY Vishwambhar BEGINS -->
						<tr>
							<td height="22" class="formtext"><label
								name="candidate.location" id="candidate.location1"
								ondblclick="callShowDiv(this);"><%=label.get("candidate.location")%></label>
							:</td>
							<td height="22"><s:property value="currentLocation" /></td>
							<td height="22" class="formtext"></td>
							<td height="22"></td>
						</tr>
						<!-- ADDED BY Vishwambhar ENDS -->

						<tr>
							<td height="22" class="formtext" valign="top"><label
								name="info" id="info" ondblclick="callShowDiv(this);"><%=label.get("info")%></label>
							:</td>
							<td height="22" valign="top"><s:property value="otherInfo" /></td>

							<td height="22" class="formtext" valign="top"></td>
							<td height="22"></td>

						</tr>
						<tr>
							<td height="22" class="formtext" valign="top"><label
								name="view" id="view" ondblclick="callShowDiv(this);"><%=label.get("view")%></label>
							:</td>
							<td height="22" valign="top"><s:hidden name="uploadFileName" />
							<input type="button" class="token" theme="simple" value="View"
								onclick="showRecord('<s:property value="uploadFileName" />');" /></td>

							<td height="22" class="formtext" valign="top"></td>
							<td height="22"></td>

						</tr>



						<tr>

							<td height="22" width="26%" class="formtext"><label
								name="doyou" id="doyou" ondblclick="callShowDiv(this);"><%=label.get("doyou")%></label>
							:</td>
							<td><s:hidden name="radioFlag" /> <s:if test="radioFlag">
								<s:radio name="doYou" disabled="true" value="%{'Y'}"
									list="#{'Y':'Yes','N':'No'}" onclick="showEmpId();"></s:radio>
							</s:if> <s:else>
								<s:radio name="doYou" disabled="true" value="%{'N'}"
									list="#{'Y':'Yes','N':'No'}" onclick="showEmpId();"></s:radio>
							</s:else></td>
						</tr>

						<s:if test="radioFlag">
							<tr>

								<td height="22" class="formtext" width="26%"><label
									name="ref" id="ref" ondblclick="callShowDiv(this);"><%=label.get("ref")%></label>
								:</td>
								<td><s:property value="refEmpName" /></td>

							</tr>
						</s:if>



						<tr>


							<td height="22" class="formtext" width="26%" align="left"><label
								name="wheyouemp" id="wheyouemp" ondblclick="callShowDiv(this);"><%=label.get("wheyouemp")%></label>
							:</td>
							<td><s:hidden name="radioFlag1" /> <s:if test="radioFlag1">
								<s:radio name="wheYouEmp" disabled="true" value="%{'Y'}"
									list="#{'Y':'Yes','N':'No'}"></s:radio>
							</s:if> <s:else>
								<s:radio name="wheYouEmp" disabled="true" value="%{'N'}"
									list="#{'Y':'Yes','N':'No'}"></s:radio>
							</s:else></td>


						</tr>



						<tr>

							<td height="22" class="formtext" width="26%"><label
								name="relocate" id="relocate" ondblclick="callShowDiv(this);"><%=label.get("relocate")%></label>
							:</td>

							<td><s:hidden name="radioFlag2" /> <s:if test="radioFlag2">
								<s:radio name="relocate" disabled="true" value="%{'Y'}"
									list="#{'Y':'Yes','N':'No'}"></s:radio>
							</s:if> <s:else>
								<s:radio name="relocate" disabled="true" value="%{'N'}"
									list="#{'Y':'Yes','N':'No'}"></s:radio>
							</s:else></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!--   	
         <tr>
          <td height="5" colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
        </tr>
         -->
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td><strong class="text_head">Reference Details :
									</strong></td>

								</tr>
								<tr>
									<td colspan="5">
									<table width="100%" id="tblRef">
										<tr>
											<td valign="top" class="formth" nowrap="nowrap"><b><label
												class="set" name="sr" id="sr1"
												ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="refName" id="refName" ondblclick="callShowDiv(this);"><%=label.get("refName")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="prof" id="prof" ondblclick="callShowDiv(this);"><%=label.get("prof")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="cont" id="cont" ondblclick="callShowDiv(this);"><%=label.get("cont")%></label></b></td>
											<td valign="top" class="formth" align="center"><b><label
												name="rec.comments" id="rec.comments"
												ondblclick="callShowDiv(this);"><%=label.get("rec.comments")%></label></b></td>
										</tr>






										<s:iterator value="refList">
											<tr>
												<td align="center" class="sortableTD"><s:property
													value="srNo" /></td>
												<td align="left" class="sortableTD">&nbsp; <s:hidden
													name="refDtlCode" /> <s:property value="refName" /></td>
												<td align="left" class="sortableTD">&nbsp; <s:property
													value="profession" /></td>
												<td align="left" class="sortableTD">&nbsp; <s:property
													value="contactDet" /></td>
												<td align="left" class="sortableTD">&nbsp; <s:property
													value="refComment" /></td>

											</tr>

										</s:iterator>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<s:if test="refFlag"></s:if>
				<s:else>
					<tr>
						<td align="center" width="100%"><font color="red">There
						is no data to display.</font></td>

					</tr>



				</s:else>

			</table>
			</td>
		</tr>


		<tr>
			<td width="78%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
			<td width="22%">
			<div align="right"><span class="style3"></span><strong>Page
			1 of 2</strong></div>
			</td>
		</tr>
	</table>
</s:form>

<script>
function nextFun(){

		document.getElementById("paraFrm").action="ResumeApproval_nextPageView.action";
	    document.getElementById("paraFrm").submit();
	    return false;

}

function editFun(){
		document.getElementById("paraFrm").action="ResumeApproval_editFirst.action";
	    document.getElementById("paraFrm").submit();
       return false;


}

function deleteFun(){
	var conf=confirm("Do you really want to delete this record ?");
	  if(conf){
		document.getElementById("paraFrm").action="ResumeApproval_deleteRec.action";
	    document.getElementById("paraFrm").submit();
	
	  }
	   return false;
	}
	
	
function cancelFun(){


		document.getElementById("paraFrm").action="ResumeApproval_input.action";
	    document.getElementById("paraFrm").submit();
	    return false;
}	

function showRecord(fileName)
	{	document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action = "ResumeApproval_viewCV.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target='main';
		
	}

function exportpdfreportFun(){
		document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action = "ResumeApproval_getReportInPdf.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target='main';

}

function exporttextreportFun(){
		document.getElementById('paraFrm').target='_blank';
		document.getElementById('paraFrm').action = "ResumeApproval_getReportInText.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target='main';

}

</script>
