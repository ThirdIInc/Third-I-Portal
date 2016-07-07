<!-- Nilesh Dhandare -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%
	String addressSource = "";
	String proofSource = "";
	if (request.getAttribute("addressSource") != null) {
		addressSource = (String) request.getAttribute("addressSource");
	} else {
		addressSource = "";
	}
	if (request.getAttribute("proofSource") != null) {
		proofSource = (String) request.getAttribute("proofSource");
	} else {
		proofSource = "";
	}
	System.out.println("addressSource---" + addressSource);
%>
<s:form action="CDRomRequest" name="CDRomRequest" id="paraFrm"
	theme="simple" validate="true">


	<s:hidden name="addressSource" id="addressSource"
		value="<%=addressSource%>"></s:hidden>
	<s:hidden name="proofSource" id="proofSource" value="<%=proofSource%>"></s:hidden>
	<s:hidden name="hiddenCode" />
	<s:hidden name="readOnlyDetails" />
	<s:hidden name="level" />

	<s:hidden name="dataPathAddressing" />
	<s:hidden name="dataPathAdditional" />
	<s:hidden name="cdRomID" />
	<s:hidden name="uploadFileNameFlag" />
	<s:hidden name="uploadFileAdditionalInfoDocFlag" />
	<s:hidden name="forApproval" id="forApproval" />
	<input type="hidden" id="labelCount" />
	<s:hidden name="applnStatus" />

	<s:hidden name="authorizedToken" />

	<!--  Files hidden Fields added by nilesh-->
	<s:hidden name="requestDetailFile" />
	<s:hidden name="addressInfoFile1" />
	<s:hidden name="addressInfoFile2" />
	<s:hidden name="dataPath" />
	<s:hidden name="dataPath1" />
	<s:hidden name="dataPath2" />



	<table width="100%" class="formbg" align="right">
		<s:if test="forApproval">
			<tr>
				<td colspan="5">
				<table width="100%" class="formbg">
					<tr>
						<td width="5%" valign="bottom" class="txt"><strong
							class="formhead"> <img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /> </strong></td>
						<td width="92%" class="txt"><strong class="text_head">CDROM
						Request Application </strong></td>
						<td width="4%" valign="middle" align="right" class="txt"><img
							src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="100%" colspan="5"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td colspan="5">
				<table width="100%" class="formbg">
					<tr>
						<td colspan="1" width="5%" valign="bottom" class="txt"><strong
							class="formhead"> <img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /> </strong></td>
						<td colspan="1" width="90%" class="txt"><strong
							class="text_head">CDROM Request Application </strong></td>
						<td colspan="2" width="5%" valign="middle" align="right"
							class="txt"><img src="../pages/images/recruitment/help.gif"
							width="16" height="16" /></td>
					</tr>
				</table>
				</td>
			</tr>


			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="1" width="80%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td colspan="4" width="20%">
						<div align="right"><span class="style2"></span><font
							color="red">*</font>Indicates Required</div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:else>
		<s:if test="draftDataDisplyFlag">
			<s:if test="approvalCommentsFlag">
				<tr>
					<td width="100%" colspan="4">
					<table width="100%" class="formbg">
						<tr>
							<td width="20%" colspan="1"><b>Approver Comments</b><font
								id='ctrlHide' color="red">*</font></td>
							<td width="80%" colspan="3"><s:textarea theme="simple"
								cols="70" rows="3" name="approverComments" id="approverComments" /></td>
						</tr>


					</table>
					</td>
				</tr>
			</s:if>
			<s:else>
				<tr>
					<td width="10%" class="formth">Sr. No.</td>
					<td width="25%" class="formth">Approver Name</td>
					<td width="40%" class="formth">Comments</td>
					<td width="15%" class="formth">Approved Date</td>
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
						<td class="sortableTD" align="center"><s:property
							value="apprDate" /></td>
						<td class="sortableTD"><s:property value="apprStatus" /></td>
					</tr>
				</s:iterator>
				<%
				if (count == 0) {
				%>
				<tr>
					<td width="100%" colspan="4" align="center"><font color="red">No
					Data To Display</font></td>
				</tr>
				<%
				}
				%>
			</s:else>
		</s:if>
		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="5" width="100%"><b><label class="set"
						name="cd.info" id="cd.info" ondblclick="callShowDiv(this);"><%=label.get("cd.info")%></label></b>
					</td>
				</tr>

				<tr>

					<s:if test="bean.generalFlag">
						<td width="25%" colspan="1"><label id="employee"
							name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						:</td>
						<td width="75%" colspan="4"><s:textfield name="employeeToken"
							size="20" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><s:textfield
							name="employeeName" size="70" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:hidden
							name="employeeCode" /></td>
					</s:if>
					<s:else>
						<td width="25%" colspan="1"><label id="employee"
							name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						:</td>
						<td width="75%" colspan="4"><s:textfield name="employeeToken"
							size="20" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /><s:textfield
							name="employeeName" size="70" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:hidden
							name="employeeCode" /> <s:if test="draftFlag">
						</s:if> <s:else>
							<img src="../pages/images/recruitment/search2.gif" height="16"
								align="absmiddle" width="16" id='ctrlHide'
								onclick="javascript:callsF9(500,325,'CDRomRequest_f9Employee.action');">
						</s:else></td>
					</s:else>


				</tr>
				<tr>
					<td width="25%" colspan="1"><label id="dept.name"
						name="dept.name" ondblclick="callShowDiv(this);"><%=label.get("dept.name")%></label>
					:</td>
					<td width="25%" colspan="1"><s:hidden name="deptId" /><s:textfield
						name="deptName" readonly="true" size="20" /><img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="16" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'CDRomRequest_f9Dept.action');"></td>

					<td width="20%" colspan="1"><label id="off.location"
						name="off.location" ondblclick="callShowDiv(this);"><%=label.get("off.location")%></label>
					:</td>
					<td width="30%" colspan="1"><s:textfield name="officeLocation"
						maxlength="100" size="20" /></td>
					<td colspan="1">&nbsp;</td>
					<!-- 	<td width="20%" align="left" colspan="1"><label id="dept.no" name="dept.no"
						ondblclick="callShowDiv(this);"><%=label.get("dept.no")%></label> :</td>

					<td width="20%" colspan="1"><s:textfield name="deptNo" readonly="true"
						size="20" cssStyle="background-color: #F2F2F2;" /></td> -->

				</tr>
				<tr>
					<td width="25%" align="left" colspan="1"><label id="ph.no"
						name="ph.no" ondblclick="callShowDiv(this);"><%=label.get("ph.no")%></label>
					:</td>

					<td width="25%" colspan="1"><s:textfield name="phNo" size="20"
						maxlength="15" /></td>

					<td width="20%" colspan="1"><label id="status" name="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
					<td width="30%" colspan="1"><s:select disabled="true"
						cssStyle="width=130" name="applnStatus"
						list="#{'D':'Draft','P':'PENDING','B':'Sent Back','A':'APPROVED','R':'REJECTED',
						'N':'CANCELLED','F':'FORWARDED','C':'APPLIED FOR CANCELLATION','X':'Cancellation Approved','Z':'Cancellation Rejected'}" />
					</td>
					<td colspan="1">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>


		<!-- change.personal.infomation  -->
		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" width="100%"><b><label class="set"
						name="request.info" id="request.info"
						ondblclick="callShowDiv(this);"><%=label.get("request.info")%></label></b>
					</td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label id="request.type"
						name="request.type" ondblclick="callShowDiv(this);"><%=label.get("request.type")%></label>
					:</td>
					<td width="25%" colspan="1"><s:select headerKey=""
						headerValue="---------Select----------" name="requestName"
						list="#{'A':'Authoring from file','C':'Copying value '}"
						cssStyle="width=130" /></td>

					<td width="20%" colspan="1"><label id="no.master"
						name="no.master" ondblclick="callShowDiv(this);"><%=label.get("no.master")%></label>
					:</td>
					<td width="30%" colspan="1"><s:textfield name="noMaster"
						size="20" maxlength="5" onkeypress="return isNumberKey(event)" /></td>
					<td colspan="1">&nbsp;</td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label id="no.copy" name="no.copy"
						ondblclick="callShowDiv(this);"><%=label.get("no.copy")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textfield name="noCopy"
						size="20" maxlength="5" onkeypress="return isNumberKey(event)" /></td>

					<td width="20%" colspan="1"><label class="pak.type"
						id="pak.type" name="pak.type" ondblclick="callShowDiv(this);"><%=label.get("pak.type")%></label>
					:</td>
					<td width="30%" colspan="1"><s:select headerKey=""
						headerValue="---------Select----------" name="pakName"
						list="#{'P':'PAPER SLEEVE ','S':'SLIM-LINE JEWEL CASE ','J':'JEWEL CASE'}"
						cssStyle="width=130" /></td>
					<td colspan="1">&nbsp;</td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label id="source" name="source"
						ondblclick="callShowDiv(this);"><%=label.get("source")%></label> :</td>
					<td width="25%" colspan="1"><s:select headerKey=""
						headerValue="---------Select----------" name="sourceName"
						list="#{'T':'TEXT AS FOLLOW ','G':'GRAPHICS ATTACHED ','S':'GRAPHIC BEING SENT','L':'LAYOUT BEING SENT'}"
						cssStyle="width=130" /></td>


					<!--<td colspan="1" nowrap="nowrap" width="20%"><s:hidden
						name="uploadFileSrNo" /> <s:textfield name="uploadFileName"
						size="20" readonly="true" cssStyle="background-color: #F2F2F2;" />&nbsp;</td>

					-->
					<!--<td width="15%" colspan="1"><input type="button" class="token"
						theme="simple" value="Select File"
						onclick="uploadFile('uploadFileName');" /></td>

				-->
					<s:if test="statusDraftBack">
						<td width="20%" colspan="1"><label id="attachment"
							name="attachment" ondblclick="callShowDiv(this);"><%=label.get("attachment")%>:</label></td>

						<td height="22" width="30%" colspan="1"><s:hidden
							name="uploadFileSrNo" /> <s:textfield name="uploadFileName"
							readonly="true" size="30" /> <br>
						<a href="#"
							onclick="showRecord('<s:property value="uploadFileName" />');"><font
							color="blue"><s:property value="uploadFileName" /></font></a> <input
							type="button" class="token" theme="simple" value="Select File"
							align="center" onclick="uploadFile('uploadFileName');" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="button" class="token" theme="simple" value="Attach File"
							title="Attach File" onclick="attachFile();" /></td>

						<td colspan="1">&nbsp;</td>
					</s:if>

					<s:else>
						<td><a href="#"
							onclick="showRecord('<s:property value="uploadFileName" />');"><font
							color="blue"><s:property value="uploadFileName" /></font></a></td>
					</s:else>

				</tr>
				<tr>
					<td width="25%" colspan="1">
					<td width="25%" colspan="1">
					<td width="20%" colspan="1">File Attached:</td>
					<td width="30%" colspan="1"><a href="#" id="ctrlShow"
						onclick="openAttachedFile()" style="cursor: hand;"> <label
						id="attachedFile" /></a></td>
					<td colspan="1">&nbsp;</td>
				</tr>



				<tr>
					<td width="25%" colspan="1"><label id="attachement"
						name="attachement" ondblclick="callShowDiv(this);"><%=label.get("attachement")%></label>
					:</td>
					<td width="75%" colspan="4"><s:textarea
						name="attachementRequestDesc" cols="22" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_attachementRequestDesc','attachement','', '', '2000','2000');"></td>



				</tr>

				<!--<s:if test="uploadFileNameFlag">
					<tr>
						<td width="20%" colspan="1"></td>
						<td width="25%" colspan="1"></td>
						<td width="20%" colspan="1"></td>
						<td width="20%" colspan="1"><a href="#"
							onclick="showRecord('<s:property value="uploadFileName" />');"><s:property
							value="uploadFileName" /></a></td>
						<td width="15%" colspan="1"></td>

					</tr>
				</s:if>
			-->
			</table>
			</td>
		</tr>



		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" width="100%"><b><label class="set"
						name="delivery.info" id="delivery.info"
						ondblclick="callShowDiv(this);"><%=label.get("delivery.info")%></label>
					</b></td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label id="delivery.date"
						name="delivery.date" ondblclick="callShowDiv(this);"><%=label.get("delivery.date")%></label>
					<font color="red" id="ctrlHide">*</font>:</td>
					<td colspan="1" width="25%" nowrap="nowrap"><s:textfield
						maxlength="10" name="deliveryDate" size="20"
						value="%{deliveryDate}" theme="simple"
						onkeypress="return numbersWithHiphen(); " /><s:a
						href="javascript:NewCal('paraFrm_deliveryDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18" id="ctrlHide">
					</s:a></td>


					<td width="20%" colspan="1"><label id="delivery.via"
						name="delivery.via" ondblclick="callShowDiv(this);"><%=label.get("delivery.via")%></label>
					:</td>
					<td width="30%" colspan="1"><s:select headerKey=""
						headerValue="---------Select----------" name="deliveryVia"
						list="#{'I':'INTEROFFICE','U':'US MAIL ','N':'OVERNIGHT','O':'OTHER'}"
						cssStyle="width=130" /></td>
					<td colspan="1">&nbsp;</td>
				</tr>

				<tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="5">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="5" width="100%"><b><label class="set"
						name="address.info" id="address.info"
						ondblclick="callShowDiv(this);"><%=label.get("address.info")%></label>
					</b></td>
				</tr>
				<tr>
					<td colspan="5" width="100%">Labels can be printed for you by
					providing a word document in an Avery 5161 Address Label
					format.Or,provide an Excel spreadsheet using the column heading of
					Name,Address1,Address2,City,State, and Zip</td>
				</tr>

				<tr>
					<td width="25%" colspan="1"><label id="source.address"
						name="source.address" ondblclick="callShowDiv(this);"><%=label.get("source.address")%></label>
					:</td>

					<td colspan="4" width="75%"><s:select headerKey=""
						headerValue="---------Select----------" name="sourceAddress"
						list="#{'T':'TEXT ADDRESS AS FOLLOW','S':'SPREEDSHEET ATTACHED ','W':'WORD DOC ATTACHED','P':'PRINTED LABELS BEING SENT'}"
						onchange=" return callValue('sourceAddress');"
						cssStyle="width=130" /></td>

				</tr>

				<tr style="display: none" id="addrId">
					<td width="25%" colspan="1"><label id="enter.address"
						name="enter.address" ondblclick="callShowDiv(this);"><%=label.get("enter.address")%></label>
					:</td>
					<td width="75%" colspan="4"><s:textarea name="addressDocument"
						cols="22" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_addressDocument','enter.address','', '', '2000','2000');"></td>

				</tr>

				<tr id="downloadID" style="display: none">
					<td width="25%" colspan="1"><label id="attachment"
						name="attachment" ondblclick="callShowDiv(this);"><%=label.get("attachment")%>:</label></td>

					<s:if test="statusDraftBack">

						<td height="22" width="25%" colspan="1"><s:textfield
							name="uploadFileAddress" size="20" readonly="true" />&nbsp;&nbsp;&nbsp;
						<br>
						<a href="#"
							onclick="showAddressingRecord('<s:property value="uploadFileAddress" />');"><font
							color="blue"><s:property value="uploadFileAddress" /></font></a></td>
						<td width="25%" colspan="1"><input type="button"
							class="token" theme="simple" value="Select File"
							onclick="uploadAddressingFile('uploadFileAddress');" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="button" class="token" theme="simple" value="Attach File"
							title="Attach File" onclick="attachFile1();" /></td>
					</s:if>
					<s:else>
						<td width="25%" colspan="1"><a href="#"
							onclick="showAddressingRecord('<s:property value="uploadFileAddress" />');"><font
							color="blue"><s:property value="uploadFileAddress" /></font></a></td>
					</s:else>



					<td width="25%"><label id="downLoad.template"
						name="downLoad.template" ondblclick="callShowDiv(this);"><%=label.get("downLoad.template")%>:</label></td>

					<td width="25%"><input type="button" class="token"
						value=" Download"
						onclick="callDownload('PeoplePower_D1_Addressing_Details.xls')" /></td>


				</tr>
				<tr>
					<td width="25%" colspan="1">
					<td width="25%" colspan="1">
					<td width="20%" colspan="1">File Attached:</td>
					<td width="30%" colspan="1"><a href="#" id="ctrlShow"
						onclick="openAttachedFile1()" style="cursor: hand;"> <label
						id="attachedFile1" /></a></td>
					<td colspan="1">&nbsp;</td>
				</tr>



				<!--<s:if test="uploadFileAddressFlag">
					<tr>
						<td width="20%" colspan="1"></td>
						<td width="25%" colspan="1"><a href="#"
							onclick="showAddressingRecord('<s:property value="uploadFileAddress" />');"><s:property
							value="uploadFileAddress" /></a></td>
						<td width="20%" colspan="1"></td>
						<td width="20%" colspan="1"></td>

						<td width="15%" colspan="1">
					</tr>
				</s:if>

				-->
				<tr>
					<td width="25%" colspan="1"><label id="proof" name="proof"
						ondblclick="callShowDiv(this);"><%=label.get("proof")%>:</label></td>

					<td colspan="4" width="75%"><s:select headerKey=""
						headerValue="---------Select----------" name="proof"
						list="#{'Y':'Yes','N':'No'}" cssStyle="width=130"
						onchange=" return callProofValue('proof');" /></td>

					</td>

				</tr>

				<tr style="display: none" id="proofId">

					<td width="25%" colspan="1"><label id="additional.info"
						name="additional.info" ondblclick="callShowDiv(this);"><%=label.get("additional.info")%></label></td>
					<td width="25%" colspan="1"><s:textarea
						name="additionalInfoDoc" cols="22" rows="3"
						onkeypress="return enterMaxLength(event, this, 2000);" /><img
						id='ctrlHide' src="../pages/images/zoomin.gif" height="12"
						align="bottom" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_additionalInfoDoc','additional.info','', '', '2000','2000');"></td>
					<td width="25%" colspan="1"><label id="attachment"
						name="attachment" ondblclick="callShowDiv(this);"><%=label.get("attachment")%>:</label></td>

					<!--<td colspan="1" nowrap="nowrap" width="20%"><s:textfield
						name="uploadFileAdditionalInfoDoc" size="20" readonly="true"
						cssStyle="background-color: #F2F2F2;" />&nbsp;&nbsp;&nbsp; <input
						type="button" class="token" theme="simple" value="Select File"
						onclick="uploadAdditionalFile('uploadFileAdditionalInfoDoc');" />
					</td>

					-->
					<s:if test="statusDraftBack">
						<td height="22" width="25%" colspan="1"><s:textfield
							name="uploadFileAdditionalInfoDoc" size="20" readonly="true" />&nbsp;&nbsp;&nbsp;
						<br>
						<a href="#"
							onclick="showAdditionalRecord('<s:property value="uploadFileAdditionalInfoDoc" />');"><font
							color="blue"><s:property
							value="uploadFileAdditionalInfoDoc" /></font></a></td>
						<td><input type="button" class="token" theme="simple"
							value="Select File"
							onclick="uploadAdditionalFile('uploadFileAdditionalInfoDoc');" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
							type="button" class="token" theme="simple" value="Attach File"
							title="Attach File" onclick="attachFile2();" /></td>
					</s:if>
					<s:else>
						<td width="25%" colspan="1"><a href="#"
							onclick="showAdditionalRecord('<s:property value="uploadFileAdditionalInfoDoc" />');"><font
							color="blue"><s:property
							value="uploadFileAdditionalInfoDoc" /></font></a></td>
					</s:else>

				</tr>

				<tr>
					<td width="25%" colspan="1">
					<td width="25%" colspan="1">
					<td width="20%" colspan="1">File Attached:</td>
					<td width="30%" colspan="1"><a href="#" id="ctrlShow"
						onclick="openAttachedFile2()" style="cursor: hand;"> <label
						id="attachedFile2" /></a></td>
				</tr>
				<!--<tr>
					<td width="20%" colspan="1"></td>

					<td width="25%" colspan="1"></td>
					<td width="20%" colspan="1"></td>
					<td width="20%" colspan="1"><a href="#" id="ctrlShow"
						onclick="showAdditionalRecord('<s:property value="uploadFileAdditionalInfoDoc" />');"><s:property
						value="uploadFileAdditionalInfoDoc" /></a></td>
					<td width="15%" colspan="1"></td>
				</tr>


			-->
			</table>
			</td>
		</tr>


		<s:if test="forApproval">
			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="25%" colspan="1"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td colspan="4" width="75%"></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td width="100%" height="22" colspan="5">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">
					<tr>
						<td width="100%" colspan="5"><b>Form Approval</b></td>
					</tr>


					<tr>
						<td colspan="1" width="25%"><b><label class="set"
							name="first_app" id="first_app" ondblclick="callShowDiv(this);"><%=label.get("first_app")%></label></b>
						:</td>

						<td width="75%" colspan="4"><s:hidden
							name="firstApproverCode" /> <s:property
							value="firstApproverToken" /> &nbsp;&nbsp;&nbsp; <s:property
							value="firstApproverName" /></td>
					</tr>
					<s:if test="secondAppFlag">
						<tr>
							<td colspan="1" width="25%"><b><label class="set"
								name="second_app" id="second_app"
								ondblclick="callShowDiv(this);"><%=label.get("second_app")%></label></b>
							:</td>

							<td width="75%" colspan="4"><s:hidden
								name="secondApproverCode" /> <s:property
								value="secondApproverToken" />&nbsp;&nbsp;&nbsp; <s:property
								value="secondApproverName" /></td>
						</tr>
					</s:if>
					<tr>
						<td colspan="1" width="25%">Change My Manager :</td>
						<td width="75%" colspan="4"><s:textfield name="approverToken"
							size="20" theme="simple" readonly="true" /> <s:textfield
							name="approverName" size="70" theme="simple" readonly="true" />
						<s:hidden name="approverCode" /> <img
							src="../pages/images/recruitment/search2.gif" height="16"
							align="absmiddle" width="16" id='ctrlHide'
							onclick="javascript:callsF9(500,325,'CDRomRequest_f9Approver.action');">
						</td>
					</tr>

				</table>
				</td>
			</tr>

			<tr>
				<td colspan="5">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">
					<tr>
						<td width="25%" colspan="1"><b><label class="set"
							name="by" id="by" ondblclick="callShowDiv(this);"><%=label.get("by")%></label></b>
						:</td>
						<td width="25%" colspan="1"><s:hidden name="completedByCode"></s:hidden>
						<s:hidden name="completedBy"></s:hidden> <s:property
							value="completedBy" /></td>
						</td>

						<td width="25%" colspan="1"><b><label class="set"
							name="on" id="on" ondblclick="callShowDiv(this);"><%=label.get("on")%></label></b>
						:</td>
						<td width="25%" colspan="1"><s:hidden name="completedDate"></s:hidden>
						<s:property value="completedDate" /></td>
						<td colspan="1">&nbsp;</td>
					</tr>

				</table>
				</td>
			</tr>

			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="5">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="100%" colspan="5"><jsp:include
									page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							</tr>
						</table>
						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:else>
	</table>



</s:form>


<script>
	
	/* this function called at onload time for displaying attachments*/
	callOnLoadFunctions();
	
	function callOnLoadFunctions()
	{
		try{
	callValue('addressSource');
	setAddedFile();
    setAddressFile();
    setDetailInfo();
    callProofValue('proofSource');
	}catch(e){alert(e);}
	}
	
	
	function approveFun() {
		var conf = confirm('Do you want to approve the application?');
		if(conf) {
			document.getElementById('paraFrm').action="CDRomRequest_approve.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	function rejectFun() {
		var conf = confirm('Do you want to reject the application?');
		if(conf) {
			document.getElementById('paraFrm').action="CDRomRequest_reject.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	function sendbackFun() {
		var conf = confirm('Do you want to send back the application?');
		if(conf) {
			document.getElementById('paraFrm').action="CDRomRequest_sendBack.action";
			document.getElementById('paraFrm').submit();
		}
	}

	callAddressType();
	function callAddressType(){
	var addressCode= document.getElementById('paraFrm_sameAddressType').value;
	
		if(addressCode=='N'){
		
		
		
		document.getElementById('relationAddress').readOnly=''; 
			document.getElementById('relationAddress').style.background='white';
		
		}else{
			
			document.getElementById('relationAddress').value='';
			document.getElementById('relationAddress').readOnly='true'; 
			 document.getElementById('relationAddress').style.background='#F2F2F2'; 
		}
	}
function sendforapprovalFun()

{	
try{
	var empName=document.getElementById('paraFrm_employeeName').value
				if(trim(empName)==""){
					alert("Please select " + document.getElementById('employee').innerHTML.toLowerCase());
					document.getElementById('paraFrm_employeeName').focus();
					return false;
				}
			
	
	
 var deliveryDate = trim(document.getElementById('paraFrm_deliveryDate').value);
				
				
				if(deliveryDate == "") {
				alert("Please select or enter/select the Delivery Date Required");
				//	alert("Please select "+document.getElementById('start_date').innerHTML.toLowerCase());
					document.getElementById('paraFrm_deliveryDate').focus();
					return false;
				}else { 
					if(!validateDate('paraFrm_deliveryDate',"delivery.date")){
							document.getElementById('paraFrm_deliveryDate').focus();
							return false;   	
	   					}
				}
	
	
			
		var firstApproverCode = document.getElementById('paraFrm_firstApproverCode').value;
		
		if(firstApproverCode=="")
		{
			var selectapproverNameVar = trim(document.getElementById('paraFrm_approverName').value);
			if(selectapproverNameVar == "")
			{
				alert("Please Select Approver.");
		  		document.getElementById('paraFrm_approverName').focus();
		 		return false;
		 	}	
		}
				
	 
	 if(!document.getElementById('paraFrm_noMaster').value==''){
		if(isNaN(document.getElementById('paraFrm_noMaster').value)){
			alert('Please enter Number Of Masters as a number');
			document.getElementById('paraFrm_noMaster').focus();
			return false;			
			}
			}
	 
	 
	 
	 if(!document.getElementById('paraFrm_noCopy').value==''){
		if(isNaN(document.getElementById('paraFrm_noCopy').value)){
			alert('Please enter Number Of Copies as a number');
			document.getElementById('paraFrm_noCopy').focus();
			return false;			
			}
	}
		} 
	catch(e)
  {
alert(e);
   }			
	
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm_applnStatus').value='P'
    	document.getElementById('paraFrm').action='CDRomRequest_sendForApproval.action';
		document.getElementById('paraFrm').submit();
			
}

	function draftFun(){
			
				var empName=document.getElementById('paraFrm_employeeName').value
				if(trim(empName)==""){
					alert("Please select " + document.getElementById('employee').innerHTML.toLowerCase());
					document.getElementById('paraFrm_employeeName').focus();
					return false;
				}
			
			/*	if(trim(document.getElementById('paraFrm_deliveryDate').value) == "") {
					alert("Please select or enter/select the " + document.getElementById('delivery.date').innerHTML.toLowerCase());
					document.getElementById('paraFrm_deliveryDate').focus();
					return false;
				} */
			
			
	var deliveryDate = trim(document.getElementById('paraFrm_deliveryDate').value);
				
				
				if(deliveryDate == "") {
				alert("Please select or enter/select the Delivery Date Required");
				//	alert("Please select "+document.getElementById('start_date').innerHTML.toLowerCase());
					document.getElementById('paraFrm_deliveryDate').focus();
					return false;
				}else { 
					if(!validateDate('paraFrm_deliveryDate',"delivery.date")){
							document.getElementById('paraFrm_deliveryDate').focus();
							return false;   	
	   					}
				}
			
			/*	if(!dateCheckWithToday('paraFrm_deliveryDate', 'delivery.date')) {
					document.getElementById('paraFrm_deliveryDate').focus();
					return false;
				}*/
				
				/*	var sourceAddress=document.getElementById('paraFrm_sourceAddress').value
				
				var temp=document.getElementById('paraFrm_uploadFileAddress').value;
				
				var temp1=temp.replace(".",",");
				
				var temp2=temp1.split(",");
		if(sourceAddress=="S"){
					if(temp2[1]!=".xls"){
						alert("Please select only .xls file for source of address")
						return false;
					}
				}
				
				if(sourceAddress=="W"){
					if(temp2[1]!="doc"){
						alert("Please select only .doc file for source of address")
						return false;
					}
				}*/
				
				
				var firstApproverCode = document.getElementById('paraFrm_firstApproverCode').value;
		///alert(firstApproverCode);
		if(firstApproverCode=="")
		{
			var selectapproverNameVar = trim(document.getElementById('paraFrm_approverName').value);
			if(selectapproverNameVar == "")
			{
				alert("Please Select Approver.");
		  		document.getElementById('paraFrm_approverName').focus();
		 		return false;
		 	}	
		}
				
	 
	 if(!document.getElementById('paraFrm_noMaster').value==''){
		if(isNaN(document.getElementById('paraFrm_noMaster').value)){
			alert('Please enter Number Of Masters as a number');
			document.getElementById('paraFrm_noMaster').focus();
			return false;			
			}
			}
	 
	 
	 
	 if(!document.getElementById('paraFrm_noCopy').value==''){
		if(isNaN(document.getElementById('paraFrm_noCopy').value)){
			alert('Please enter Number Of Copies as a number');
			document.getElementById('paraFrm_noCopy').focus();
			return false;			
			}
	}
			
	
				document.getElementById('paraFrm').target = "_self";
				//document.getElementById('paraFrm_status').value='D'
				document.getElementById('paraFrm_applnStatus').value = 'D';	
		  		document.getElementById('paraFrm').action = 'CDRomRequest_save.action?status='+status;
				document.getElementById('paraFrm').submit();
		  
	}

	function backtolistFun() {
		document.getElementById('paraFrm').target = "_self";
		
		var forApproval = document.getElementById('forApproval').value;
		
		if(forApproval == 'true') {
			document.getElementById('paraFrm').action="CDRomApproval_input.action";
		} else {
			document.getElementById('paraFrm').action="CDRomRequest_back.action";
		}
      	
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
      	document.getElementById('paraFrm').action = 'CDRomRequest_delete.action';
		document.getElementById('paraFrm').submit();
	}
	
	}
	function reportFun() 
{
	alert("No Record To Display Report ");
}

	function cancelApprovedFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="PersonalDataChange_cancel.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
		/*function uploadFile(fieldName) {
		//var path = "images/<%=session.getAttribute("session_pool")%>/attendance";
		//window.open('<%=request.getContextPath()%>/pages/common/uploadFile.jsp?path=' + path + '&field=' + fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	} */
	
	function uploadFile(fieldName) {
		//alert(fieldName);
		var dataPath = document.getElementById('paraFrm_dataPath').value;
		window.open('<%=request.getContextPath()%>/pages/helpdesk/fileUpload.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
		
		}
		
			function openAttachedFile() {
		document.getElementById('paraFrm').action = 'CDRomRequest_viewAttachedProof.action';  
		document.getElementById('paraFrm').submit();
	}
	
	function openAttachedFile1() {
		document.getElementById('paraFrm').action = 'CDRomRequest_viewAttachedProofAddressing.action';  
		document.getElementById('paraFrm').submit();
	}
	
	function openAttachedFile2() {
		document.getElementById('paraFrm').action = 'CDRomRequest_viewAttachedProofAdditional.action';  
		document.getElementById('paraFrm').submit();
	}
	
	
	
	

	
function setAddedFile() {
  	try
  	{
		var requestDetailFile = document.getElementById('paraFrm_requestDetailFile').value;
		//alert("requestDetailFile");
		
		document.getElementById('attachedFile').innerHTML =requestDetailFile;
	}
	
	catch(e)
    {
      alert(e);
    }	

}
		
	
		
function setAddressFile() {
	   try
	   {
		var addressInfoFile1 = document.getElementById('paraFrm_addressInfoFile1').value;
		/*alert(hotelFile);*/
		document.getElementById('attachedFile1').innerHTML =addressInfoFile1;
	    }
		
      catch(e)
    {
      alert(e);
    }		
		
}	
				
 
		
function setDetailInfo() {
	  try
	  {	
		var addressInfoFile2 = document.getElementById('paraFrm_addressInfoFile2').value;
		/*alert(hotelFile);*/
		document.getElementById('attachedFile2').innerHTML =addressInfoFile2;
	    }	

      catch(e)
     {
       alert(e);
     }
}	
		
		function uploadAddressingFile(fieldName) {
		//alert(fieldName);
		var extType="";
		var val =document.getElementById('paraFrm_sourceAddress').value;
		if(val=='S')
		{
			extType=".xls";
		}
		else{
			extType=".doc";
		}
		
		
		var dataPath = document.getElementById('paraFrm_dataPathAddressing').value;
		window.open('<%=request.getContextPath()%>/pages/helpdesk/fileUpload.jsp?path=' + dataPath + '&field='+
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
		
		}
		
		function uploadAdditionalFile(fieldName) {
		//alert(fieldName);
		var dataPath = document.getElementById('paraFrm_dataPathAdditional').value;
		//alert("dataPath---"+dataPath);
		window.open('<%=request.getContextPath()%>/pages/helpdesk/fileUpload.jsp?path=' + dataPath + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
		
		}
		function callDownload(templateName) {
	  	document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'CDRomRequest_downloadTemplate.action?templateName=' + templateName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	
	
	function resetFun() {
		
		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'CDRomRequest_reset.action';
     	document.getElementById('paraFrm').submit(); 
     	    
  	}
  	
  	
  	function showRecord(fileName)
	{
	
    //alert(111);
	
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "CDRomRequest_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function showAddressingRecord(fileName)
	{
	
	//alert(222);
	
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "CDRomRequest_viewAttachedProofAddressing.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function showAdditionalRecord(fileName)
	{
	
	//alert(333);
	
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "CDRomRequest_viewAttachedProofAdditional.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	 
	 
	function callValue(id){
	 	try{
	 	
		 var opt =document.getElementById('paraFrm_'+id).value ;
		if(opt=="T"){
			document.getElementById('addrId').style.display="";
		}else{
			document.getElementById('addrId').style.display="none";
			document.getElementById('paraFrm_addressDocument').value='';
		}
		
		if(opt=="S" || opt=="W"){
			document.getElementById('downloadID').style.display="";
			document.getElementById('paraFrm_addressDocument').value='';
		}else{
			document.getElementById('downloadID').style.display="none";
			document.getElementById('paraFrm_uploadFileAddress').value='';
		}
		
	  ///  callProofValue('proofSource');
		}catch(e){
		
		}
	}
	
	
	function callProofValue(id1){
		
		try{
			var opt =document.getElementById('paraFrm_'+id1).value;	
			if(opt=="Y"){
				document.getElementById('proofId').style.display="";
			}else{
				document.getElementById('proofId').style.display="none";
				document.getElementById('paraFrm_additionalInfoDoc').value='';
				document.getElementById('paraFrm_uploadFileAdditionalInfoDoc').value='';
			}
			
			
		}catch(e){}
	}
	
	
	function attachFile() {
	try{
	
	
		var uploadFileName = document.getElementById('paraFrm_uploadFileName').value;
	
	
		if(uploadFileName == '') {
			alert('Please select file.');
		} else {
			document.getElementById('attachedFile').innerHTML = uploadFileName;
			document.getElementById('paraFrm_requestDetailFile').value = uploadFileName;
			document.getElementById('paraFrm_uploadFileName').value = '';
		}
		
		} catch(e){
		//alert(e);
		
		}
	}
	
	function attachFile1() {
	try{
	
	
		var uploadFileAddress = document.getElementById('paraFrm_uploadFileAddress').value;
	
	
		if(uploadFileAddress == '') {
			alert('Please select file.');
		} else {
			document.getElementById('attachedFile1').innerHTML = uploadFileAddress;
			document.getElementById('paraFrm_addressInfoFile1').value = uploadFileAddress;
			document.getElementById('paraFrm_uploadFileAddress').value = '';
		}
		
		} catch(e){
		alert(e);
		
		}
	}
	
	function attachFile2() {
	try{
	
	
		var uploadFileAdditionalInfoDoc = document.getElementById('paraFrm_uploadFileAdditionalInfoDoc').value;
	
	
		if(uploadFileAdditionalInfoDoc == '') {
			alert('Please select file.');
		} else {
			document.getElementById('attachedFile2').innerHTML = uploadFileAdditionalInfoDoc;
			document.getElementById('paraFrm_addressInfoFile2').value = uploadFileAdditionalInfoDoc;
			document.getElementById('paraFrm_uploadFileAdditionalInfoDoc').value = '';
		}
		
		} catch(e){
		alert(e);
		
		}
	}
		
	/* Numbers Only Function*/
function isNumberKey(evt)
      {
      
         var charCode = (evt.which) ? evt.which : event.keyCode
           
            if(charCode!=46)
            {
             if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;
             }
             return true;
      
      }		 
	
	
	
	/* phone validations*/
	function isphoneNumber(evt)
	{
	//alert("hello");
	var charCode = (evt.which) ? evt.which : event.keyCode
            
            if(charCode!=39)
            {
              if (charCode > 31 && (charCode < 40 || charCode > 57))
                return false;
             }
             return true;
	}
	
	 //Text Area 
	 function enterMaxLength(Event, Object, MaxLen)
	{
	
        return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	}
	
</script>