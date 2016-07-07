<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="NonInventoryPurchaseAppr" id="paraFrm" validate="true"
	target="main" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="2" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
				<s:hidden name="nonInventoryCode" /> 
				<s:hidden name="flag" /> 
				<s:hidden name="hiddenValue" /> 
				<s:hidden name="status" /> 
				<s:hidden name="dataPath" />
				<s:hidden name="myPage" id="myPage" />
				<s:hidden name="myPage1" id="myPage1" />
				<s:hidden name="myPage2" id="myPage2" />
				<table width="100%" border="0" align="right" cellpadding="0"
					cellspacing="0" class="formbg">
					<tr>
						<td><strong class="text_head"><img
							src="../pages/images/recruitment/review_shared.gif" width="25"
							height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Non
						Inventory Purchase Approval </strong></td>
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
						<td width="80%"><jsp:include
							page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
	
						<td width="20%">
						<div align="right"><span class="style2"><font
							color="red">*</font></span> Indicates Required</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>


		<tr id="poAttachmentFlag">
			<td width="100%" height="22">
				<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
						<tr>
							<td><label id="ppo.attachment" name="ppo.attachment"
								ondblclick="callShowDiv(this);"><%=label.get("ppo.attachment")%></label>
							:</td>
							<td width="75%" id="ctrlShow"><s:textfield name="ppoNo"
								maxlength="100" /> <s:textfield name="ppo"
								onclick="uploadFile('ppo')" readonly="true" size="20"
								cssStyle="background-color: #F2F2F2;" /> <input type="button"
								value="Upload File" class="token" onclick="uploadFile('ppo')" />
							</td>
						</tr>
						<tr>
							<td></td>
							<td><a href="#" onclick="viewUploadedFile('ppo');"><font
								color="blue"><u>click here to view attached file </u></font></a>
							</td>
						</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="4">
			<table width="100%" cellspacing="2" cellpadding="2" class="formbg"
				border="0">

				<s:if test="%{flag == ''}">
					<tr>
						<td width="25%" colspan="2"><B>Comments By Approver</B></td>
						<td width="70%" colspan="2" id="ctrlShow"><s:textarea
							name="comments" cols="80" rows="3" id="comments"
							onkeypress="return imposeMaxLength(event, this, 900);" /></td>
					</tr>
				</s:if>


				<s:if test="listComment">
					<tr>
						<td class="formth" width="05%">Sr No</td>
						<td class="formth" width="15%">Approver Name</td>
						<td class="formth" width="40%" align="center">Comments</td>
						<td class="formth" width="15%">Application Date</td>
						<td class="formth" width="15%">Status</td>
					</tr>
					<%
					int tt = 1;
					%>

					<s:iterator value="listComment">
						<tr>
							<td class="sortableTD"><%=tt++%></td>
							<td class="sortableTD"><s:property value="ittApproverName" /></td>
							<td class="sortableTD"><s:property value="ittComments" /></td>
							<td class="sortableTD"><s:property
								value="ittApplicationDate" /></td>
							<td class="sortableTD"><s:property value="ittStatus" /></td>
						</tr>
					</s:iterator>
				</s:if>
			</table>
			</td>
		</tr>
		<!-- Tracking Number begins-->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
				<tr>
					<td colspan="1" width="18%">Tracking Number:</td>
					<td width="50%"><s:property value="fileheaderName" /><s:hidden
						name="fileheaderName" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Tracking Number end-->

		<!-- Request Information start -->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><label class="set" name="heade.mess"
						id="heade.mess" ondblclick="callShowDiv(this);"><%=label.get("heade.mess")%></label>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">

				<tr>
					<td colspan="4"><b><label class="set"
						name="request.infomation" id="request.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("request.infomation")%></label>
					:</b></td>
				</tr>

				<tr>

					<s:if test="bean.generalFlag">
						<td width="18%"><label id="prepared.by" name="prepared.by"
							ondblclick="callShowDiv(this);"><%=label.get("prepared.by")%></label>
						:<font color="red">*</font></td>
						<td width="50%" colspan="3"><s:textfield name="employeeToken"
							size="20" theme="simple" readonly="true" /><s:textfield
							name="employeeName" size="71" theme="simple" readonly="true" />
						<s:hidden name="employeeCode" /></td>
					</s:if>
					<s:else>
						<td width="18%"><label id="prepared.by" name="prepared.by"
							ondblclick="callShowDiv(this);"><%=label.get("prepared.by")%></label>
						:<font color="red">*</font></td>
						<td width="50%" colspan="3"><s:textfield
							cssStyle="background-color: #F2F2F2;" name="employeeToken"
							size="20" theme="simple" readonly="true" /><s:textfield
							name="employeeName" size="71" theme="simple" readonly="true"
							cssStyle="background-color: #F2F2F2;" /> <s:hidden
							name="employeeCode" /></td>
					</s:else>
				</tr>
				<tr>
					<td width="25%"><label id="preparer.phone"
						name="preparer.phone" ondblclick="callShowDiv(this);"><%=label.get("preparer.phone")%></label>
					:</td>
					<td width="25%"><s:textfield name="preparerPhone"
						readonly="true" size="20" cssStyle="background-color: #F2F2F2;" /></td>

					<td width="25%" align="left"><label id="extension"
						name="extension" ondblclick="callShowDiv(this);"><%=label.get("extension")%></label>
					:</td>

					<td width="25%"><s:textfield name="extension" readonly="true"
						size="20" cssStyle="background-color: #F2F2F2;" /></td>

				</tr>
				<tr>
					<td width="25%"><label id="preparer.fax" name="preparer.fax"
						ondblclick="callShowDiv(this);"><%=label.get("preparer.fax")%></label>
					:</td>
					<td width="25%" colspan="1"><s:textfield name="preparerFax"
						readonly="true" size="20" cssStyle="background-color: #F2F2F2;" /></td>


					<td colspan="2"><label id="department" name="department"
						ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:<s:textfield name="department" maxlength="100" readonly="true"
						size="15" cssStyle="background-color: #F2F2F2;" /><s:textfield
						name="departmentAbbr" size="25" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="departmentCode" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Request Information end -->

		<!-- Shiping Information start -->
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b><label class="set"
						name="shiping.infomation" id="shiping.infomation"
						ondblclick="callShowDiv(this);"><%=label.get("shiping.infomation")%></label></b>
					</td>
				</tr>
				<tr>
					<td width="25%"><label id="ship.to" name="ship.to"
						ondblclick="callShowDiv(this);"><%=label.get("ship.to")%></label>
					:</td>
					<td colspan="3"><s:textfield name="shipTo" /></td>
				</tr>
				<tr>
					<td width="25%"><label id="address" name="address"
						ondblclick="callShowDiv(this);"><%=label.get("address")%></label>
					:</td>
					<td colspan="3"><s:textarea name="address" cols="17"
						readonly="true" cssStyle="background-color: #F2F2F2;" rows="2" /></td>
				</tr>
				<tr>
					<td width="25%" colspan="1"><label name="city" id="city"
						ondblclick="callShowDiv(this);"><%=label.get("city")%></label>:</td>
					<td width="25%" colspan="1"><s:textfield size="20"
						readonly="true" cssStyle="background-color: #F2F2F2;"
						name="cityName" readonly="true" /> <s:hidden name="cityId" /></td>

					<td width="25%"><label id="state" name="state"
						ondblclick="callShowDiv(this);"><%=label.get("state")%></label> :</td>
					<td width="25%"><s:textfield size="30" name="stateName"
						readonly="true" cssStyle="background-color: #F2F2F2;"
						onkeypress="return charactersonly(this)" readonly="true" size="20"
						cssStyle="background-color: #F2F2F2;" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="country" name="country"
						ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
					:</td>
					<td width="25%"><s:textfield name="country" readonly="true"
						cssStyle="background-color: #F2F2F2;" size="20"
						cssStyle="background-color: #F2F2F2;" /></td>

					<td width="25%"><label id="zip" name="zip"
						ondblclick="callShowDiv(this);"><%=label.get("zip")%></label> :</td>
					<td width="25%"><s:textfield name="zip" size="20"
						readonly="true" cssStyle="background-color: #F2F2F2;"
						onkeypress="return numbersOnly();" maxlength="6" /></td>
				</tr>


				<tr>
					<td width="25%"><label id="attn" name="attn"
						ondblclick="callShowDiv(this);"><%=label.get("attn")%></label> :</td>
					<td colspan="1"><s:textfield name="attn" size="20"
						maxlength="100" /></td>

					<td width="25%"><label id="shippingPhone" name="shippingPhone"
						ondblclick="callShowDiv(this);"><%=label.get("shippingPhone")%></label>
					: <font color="red">*</font></td>
					<td colspan="1"><s:textfield name="shippingPhoneNumber"
						size="20" maxlength="100" /></td>
				</tr>

				<tr>
					<td width="25%"><label id="has.written.quote"
						name="has.written.quote" ondblclick="callShowDiv(this);"><%=label.get("has.written.quote")%></label>
					:</td>
					<td colspan="1"><s:textfield name="hasWrittenQuote" size="20"
						maxlength="100" /></td>

					<td width="25%"><label id="als.mps" name="als.mps"
						ondblclick="callShowDiv(this);"><%=label.get("als.mps")%></label>
					:</td>
					<td colspan="1"><s:textfield name="aSLMPS" size="20"
						maxlength="100" /></td>
				</tr>

				<tr>
					<td colspan="4"><label id="shipping.desc" name="shipping.desc"
						ondblclick="callShowDiv(this);"><%=label.get("shipping.desc")%></label></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Shiping Information end -->

		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><strong><label id="suggest.vendor"
						name="suggest.vendor" ondblclick="callShowDiv(this);"><%=label.get("suggest.vendor")%></label>
					</strong></td>
				</tr>

				<tr>
					<td colspan="1" width="25%"><label id="vendor.name"
						name="vendor.name" ondblclick="callShowDiv(this);"><%=label.get("vendor.name")%></label>
					</td>
					<td colspan="1" width="25%"><s:textfield name="vendorName"
						maxlength="200" /></td>
					<td colspan="1" width="25%"><label id="vendor.phone.no"
						name="vendor.phone.no" ondblclick="callShowDiv(this);"><%=label.get("vendor.phone.no")%></label>
					</td>
					<td colspan="1" width="25%"><s:textfield name="vendorPhoneNo"
						maxlength="30" /></td>
				</tr>

				<tr>
					<td colspan="1" width="25%"><label id="vendor.email.id"
						name="vendor.email.id" ondblclick="callShowDiv(this);"><%=label.get("vendor.email.id")%></label>
					</td>
					<td colspan="1" width="25%"><s:textfield name="vendorEmailId"
						maxlength="200" /></td>
					<td colspan="1" width="25%"><label id="ship.via"
						name="ship.via" ondblclick="callShowDiv(this);"><%=label.get("ship.via")%></label>
					</td>
					<td colspan="1" width="25%"><s:select name="vendorShipVia"
						theme="simple" list="#{'':'--Select--','G':'Ground','A':'Air'}"></s:select>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td><label id="file.attachment" name="file.attachment"
						ondblclick="callShowDiv(this);"><%=label.get("file.attachment")%></label>
					:</td>
					<td width="75%"><s:textfield name="uploadFileName"
						onclick="uploadFile('uploadFileName')" readonly="true" size="20"
						cssStyle="background-color: #F2F2F2;" /></td>
				</tr>
				<tr>
					<td></td>
					<td><a href="#" onclick="viewUploadedFile('uploadFileName');"><font
						color="blue"><u>click here to view file</u></font></a></td>

				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td><label id="request.type" name="request.type"
						ondblclick="callShowDiv(this);"><%=label.get("request.type")%></label>
					:</td>
					<td colspan="1"><s:checkbox name="stationary" id="stationary"
						onclick="callHideShow()" />Stationary</td>
					<td></td>
					<td colspan="1"><s:checkbox name="hardwareSoftware"
						id="hardwareSoftware" onclick="callHideShow()" />Non Inventory
					Items <s:hidden name="stationaryHidden" /> <s:hidden
						name="hardwareSoftwareHidden" /></td>
				</tr>
			</table>
			</td>
		</tr>


		<s:if test="%{stationaryHidden == 'true'}">

			<tr id="stationary1">
				<td width="100%" height="22">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">
					<tr>
						<td colspan="4"><font size="2"><strong><label
							name="stationary" id="stationary" ondblclick="callShowDiv(this);"><%=label.get("stationary")%></label></strong>
						</font></td>
					</tr>

					<!-- START:IMPRINT -->
					<tr>
						<td width="100%" height="22">
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" class="formbg">

							<tr>
								<td colspan="4"><b><label class="set"
									name="imprint.infomation" id="imprint.infomation"
									ondblclick="callShowDiv(this);"><%=label.get("imprint.infomation")%></label></b>
								</td>
							</tr>
							<tr>
								<td colspan="4">Complete the following : name, title, etc.
								This information will be imprinted on the stationary items
								requested. The imprint information block may also be used for
								the first set of business cards.For addtional Business cards see
								next page of this form. For additional stationary requiring
								different imprint information , please complete a separate form.</td>
							</tr>
							<tr>
								<td width="25%" colspan="1"><label name="imprint.type"
									id="imprint.type" ondblclick="callShowDiv(this);"><%=label.get("imprint.type")%></label>:
								</td>
								<td width="25%" colspan="1"><s:textfield size="20"
									name="imprintType" readonly="true" /> <s:hidden
									name="imprintId" /> <img
									src="../pages/images/recruitment/search2.gif" width="16"
									height="15" class="iconImage" id='ctrlHide' align="absmiddle"
									onclick="javascript:callsF9(500,325,'NonInventoryPurchase_f9imprintaction.action');"></td>

								<td width="25%"><label id="imprint.name"
									name="imprint.name" ondblclick="callShowDiv(this);"><%=label.get("imprint.name")%></label>
								:</td>
								<td width="25%"><s:textfield size="30" name="imprintName"
									size="20" /></td>

							</tr>

							<tr>

								<td><label id="company.name" name="company.name"
									ondblclick="callShowDiv(this);"><%=label.get("company.name")%></label>
								:</td>
								<td><s:textfield name="companyName" size="20" /></td>


								<td><label id="title" name="title"
									ondblclick="callShowDiv(this);"><%=label.get("title")%></label>
								:</td>
								<td><s:textfield name="title" size="20" maxlength="25" /></td>
							</tr>

							<tr>
								<td><label id="company.address" name="company.address"
									ondblclick="callShowDiv(this);"><%=label.get("company.address")%></label>
								:</td>
								<td colspan="1"><s:textarea name="companyAddress" cols="21"
									rows="3" /></td>
								<td><label id="comp.description" name="comp.description"
									ondblclick="callShowDiv(this);"><%=label.get("comp.description")%></label>
								:</td>
								<td><s:textfield name="compDescription" size="20"
									maxlength="100" /></td>

							</tr>
							<tr>
								<td colspan="1"><label name="company.city"
									id="company.city" ondblclick="callShowDiv(this);"><%=label.get("company.city")%></label>:</td>
								<td colspan="1"><s:textfield size="20" name="compCityName"
									readonly="true" /> <s:hidden name="compCityNameCode" /><img
									src="../pages/images/recruitment/search2.gif" width="16"
									height="15" class="iconImage" id='ctrlHide' align="absmiddle"
									onclick="javascript:callsF9(500,325,'NonInventoryPurchase_f9imprintcit.action');">
								</td>

								<td><label id="company.state" name="company.state"
									ondblclick="callShowDiv(this);"><%=label.get("company.state")%></label>
								:</td>
								<td><s:textfield size="30" name="companyState"
									onkeypress="return charactersonly(this)" size="20"
									readonly="true" /></td>

							</tr>

							<tr>

								<!--<td width="15%"><label id="country" name="country"
						ondblclick="callShowDiv(this);"><%=label.get("country")%></label>
					:</td>
					<td ><s:textfield name="country" 
						size="20"  /></td>


					-->
								<td><label id="zip" name="zip"
									ondblclick="callShowDiv(this);"><%=label.get("zip")%></label> :</td>
								<td><s:textfield name="companyZip" size="20"
									onkeypress="return numbersOnly();" maxlength="10" /></td>

								<td><label id="comp.phone.number" name="comp.phone.number"
									ondblclick="callShowDiv(this);"><%=label.get("comp.phone.number")%></label>
								:</td>
								<td><s:textfield name="compPhoneNumber" size="20"
									onkeypress="return numbersOnly();" maxlength="10" /></td>

							</tr>

							<tr>
								<td><label id="comp.fax.number" name="comp.fax.number"
									ondblclick="callShowDiv(this);"><%=label.get("comp.fax.number")%></label>
								:</td>
								<td><s:textfield name="compFaxNumber" size="20"
									onkeypress="return numbersOnly();" maxlength="10" /></td>

								<td><label id="comp.other.number" name="comp.other.number"
									ondblclick="callShowDiv(this);"><%=label.get("comp.other.number")%></label>
								:</td>
								<td><s:textfield name="compOtherNumber" size="20"
									onkeypress="return numbersOnly();" maxlength="10" /></td>

							</tr>

							<tr>
								<td><label id="internet" name="internet"
									ondblclick="callShowDiv(this);"><%=label.get("internet")%></label>
								:</td>
								<td colspan="3"><s:textfield name="internet" size="20"
									maxlength="100" /></td>

							</tr>
						</table>
						</td>
					</tr>
					<!-- END :IMPRINT -->

					<!-- START :STATIONARY -->
					<tr>
						<td width="100%" height="22">
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" class="formbg">
							<tr>
								<td colspan="4">Enter number of lots required on each line.
								One lot equals 500 pieces.</td>
							</tr>
							<tr>
								<td colspan="4">
								<table width="100%" border="0" align="center" cellpadding="2"
									cellspacing="1">
									<tr>
										<td colspan="1" width="25%"><label class="set"
											name="select.type" id="select.type"
											ondblclick="callShowDiv(this);"><%=label.get("select.type")%></label>
										: <font color="red">*</font></td>
										<td colspan="1" width="25%"><s:hidden
											name="selectTypeCode" /> <s:textfield name="selectTypeName"
											readonly="true" /><img
											src="../pages/images/recruitment/search2.gif" width="16"
											height="15" class="iconImage" id='ctrlHide' align="absmiddle"
											onclick="javascript:callsF9(500,325,'NonInventoryPurchase_f9selecttype.action');"></td>
										<td colspan="1" width="25%"><label class="set"
											name="nooflots" id="nooflots" ondblclick="callShowDiv(this);"><%=label.get("nooflots")%></label>
										: <font color="red">*</font></td>
										<td colspan="1" width="25%"><s:textfield name="noOfLots"
											onkeypress="return numbersOnly();" maxlength="20" /> <!--<s:submit value="Add" cssClass="add" id="ctrlHide" action="NonInventoryPurchase_addSelectTYpe" onclick="return callSelect();"/>
						
						--><input type="button" value="Add"
											onclick="addRowToTableSta('sTable','selectTypeName,noOfLots,selectTypeCode,img','S,H,S,img')"
											class="add"></td>
									</tr>

								</table>
								</td>
							</tr>

							<tr>
								<td colspan="1" width="60%">
								<table width="100%" border="0" align="center" cellpadding="2"
									cellspacing="1" class="formbg" id="sTable">
									<tr>
										<td colspan="1" width="50%" class="formth"><label
											class="set" name="description" id="description"
											ondblclick="callShowDiv(this);"><%=label.get("description")%></label>
										</td>
										<td colspan="1" width="25%" align="center" class="formth"><label
											class="set" name="lots" id="lots"
											ondblclick="callShowDiv(this);"><%=label.get("lots")%></label></td>

										<td colspan="1" width="25%" class="formth" id="ctrlHide">&nbsp;</td>
									</tr>
									<%
									int count = 0;
									%>
									<s:iterator value="selectList">
										<tr>
											<td width="50%" colspan="1" class="sortableTD"><s:property
												value="ittselectTypeName" /><s:hidden
												name="ittselectTypeCode" /> <s:hidden
												name="ittselectTypeName" /></td>

											<td width="25%" align="center" class="sortableTD"><s:property
												value="ittnoOfLots" /> <s:hidden name="ittnoOfLots" /></td>

											<td width="25%" align="center" class="sortableTD"
												id="ctrlHide"><img
												src="../pages/common/css/icons/delete.gif"
												onclick="deleteTechnician(this);" id="ctrlHide"></td>
										</tr>
									</s:iterator>
								</table>
								</td>
								<td colspan="2" width="60%">&nbsp;</td>
							</tr>

							<tr>
								<td colspan="2" width="75%">
								<table width="100%" border="0" align="center" cellpadding="2"
									cellspacing="1">

								</table>
								</td>
								<td colspan="2"></td>
							</tr>
						</table>
						</td>
					</tr>
					<!-- END :STATIONARY -->
					<!-- START :ADDITIONAL -->
					<tr>
						<td width="100%" height="22">
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" class="formbg">

							<tr>
								<td colspan="4"><b><label class="set"
									name="additional.infomation" id="additional.infomation"
									ondblclick="callShowDiv(this);"><%=label.get("additional.infomation")%></label></b>
								</td>
							</tr>

							<tr>
								<td width="25%" colspan="1"><label name="additional.name"
									id="additional.name" ondblclick="callShowDiv(this);"><%=label.get("additional.name")%></label>:</td>
								<td colspan="1"><s:textfield name="additionalName"
									size="20" readonly="true" cssStyle="background-color: #F2F2F2;"
									onkeypress="return numbersOnly();" maxlength="6" /></td>

								<td width="25%" colspan="1"><label name="additional.title"
									id="additional.title" ondblclick="callShowDiv(this);"><%=label.get("additional.title")%></label>:<font
									color="red" id='ctrlHide'></font></td>
								<td width="25%"><s:textfield name="additionalTitle"
									size="20" readonly="true" cssStyle="background-color: #F2F2F2;"
									maxlength="6" /></td>

							</tr>

							<tr>
								<td width="25%"><label id="additional.company.name"
									name="additional.company.name" ondblclick="callShowDiv(this);"><%=label.get("additional.company.name")%></label>
								:</td>
								<td width="25%" colspan="1"><s:textfield size="30"
									readonly="true" cssStyle="background-color: #F2F2F2;"
									name="additionalCompanyName" size="20" /></td>

								<td width="25%" colspan="1"><label
									name="additional.description" id="additional.description"
									ondblclick="callShowDiv(this);"><%=label.get("additional.description")%></label>:<font
									color="red" id='ctrlHide'></font></td>
								<td width="25%"><s:textfield name="additionalDesc"
									size="20" readonly="true" cssStyle="background-color: #F2F2F2;"
									maxlength="100" /></td>


							</tr>


							<tr>

								<td width="25%"><label id="additional.address"
									name="additional.address" ondblclick="callShowDiv(this);"><%=label.get("additional.address")%></label>
								:</td>
								<td colspan="1"><s:textarea name="additionalAddress"
									cols="17" readonly="true" cssStyle="background-color: #F2F2F2;"
									rows="2" /></td>
								<td width="25%"><label id="additional.internet"
									name="additional.internet" ondblclick="callShowDiv(this);"><%=label.get("additional.internet")%></label>
								:</td>
								<td width="25%"><s:textfield size="30" readonly="true"
									cssStyle="background-color: #F2F2F2;" name="additionalInternet"
									size="20" maxlength="10" /></td>
							</tr>


							<tr>
								<td width="25%" colspan="1"><label name="additional.city"
									id="additional.city" ondblclick="callShowDiv(this);"><%=label.get("additional.city")%></label>:<font
									color="red" id='ctrlHide'></font></td>
								<td width="25%"><s:textfield name="additionalCity"
									size="20" readonly="true" cssStyle="background-color: #F2F2F2;"
									maxlength="6" /> <s:hidden name="additionalCityCode" /></td>

								<td width="25%"><label id="additional.state"
									name="additional.state" ondblclick="callShowDiv(this);"><%=label.get("additional.state")%></label>
								:</td>
								<td width="25%"><s:textfield size="30"
									name="additionalState" readonly="true"
									cssStyle="background-color: #F2F2F2;" size="20" /></td>

							</tr>
							<tr>
								<td width="25%" colspan="1"><label name="additional.zip"
									id="additional.zip" ondblclick="callShowDiv(this);"><%=label.get("additional.zip")%></label>:<font
									color="red" id='ctrlHide'></font></td>
								<td width="25%"><s:textfield name="additionalZip" size="20"
									readonly="true" cssStyle="background-color: #F2F2F2;"
									maxlength="6" /></td>

								<td width="25%"><label id="additional.phone.number"
									name="additional.phone.number" ondblclick="callShowDiv(this);"><%=label.get("additional.phone.number")%></label>
								:</td>
								<td width="25%"><s:textfield size="30" readonly="true"
									cssStyle="background-color: #F2F2F2;"
									name="additionalPhoneNumber" size="20"
									onkeypress="return numbersOnly();" maxlength="10" /></td>

							</tr>

							<tr>
								<td width="25%" colspan="1"><label name="additional.fax"
									id="additional.fax" ondblclick="callShowDiv(this);"><%=label.get("additional.fax")%></label>:<font
									color="red" id='ctrlHide'></font></td>
								<td width="25%"><s:textfield name="additionalFax" size="20"
									readonly="true" cssStyle="background-color: #F2F2F2;"
									onkeypress="return numbersOnly();" maxlength="10" /></td>

								<td width="25%"><label id="additional.other.number"
									name="additional.other.number" ondblclick="callShowDiv(this);"><%=label.get("additional.other.number")%></label>
								:</td>
								<td width="25%"><s:textfield size="30" readonly="true"
									cssStyle="background-color: #F2F2F2;"
									name="additionalOtherNumber" size="20"
									onkeypress="return numbersOnly();" maxlength="10" /></td>
							</tr>
						</table>
						</td>
					</tr>
					<!-- END :ADDITIONAL -->
				</table>
				</td>
			</tr>
		</s:if>


		<!-- #################### -->

		<s:if test="%{hardwareSoftwareHidden == 'true'}">
			<tr id="hardwareSoftware1">
				<td width="100%" height="22">
				<table width="100%" border="0" align="center" cellpadding="2"
					cellspacing="1" class="formbg">
					<tr>
						<td colspan="4"><font size="2"><strong><label
							name="hardware.software" id="hardware.software"
							ondblclick="callShowDiv(this);"><%=label.get("hardware.software")%></label></strong>
						</font></td>
					</tr>
					<!-- START:PROJECT INFORMATION -->
					<tr>
						<td width="100%" height="22">
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" class="formbg">
							<tr>
								<td colspan="4"><strong>Project Information </strong></td>
							</tr>


							<tr>
								<td colspan="4">Decision one will be reviewing all request
								for hardware and software related purchases. To better expedite
								the process please include the following information when
								completing electronic CLEAR</td>
							</tr>
							<tr>
								<td colspan="4">&nbsp;</td>
							</tr>
							<tr>
								<td width="25%" colspan="1"><label name="project.desc"
									id="project.desc" ondblclick="callShowDiv(this);"><%=label.get("project.desc")%></label>:</td>
								<td colspan="1"><s:textfield name="projectDesc" size="20"
									maxlength="200" /></td>

								<td width="25%" colspan="1"><label name="business.obj"
									id="business.obj" ondblclick="callShowDiv(this);"><%=label.get("business.obj")%></label>:<font
									color="red" id='ctrlHide'></font></td>
								<td width="25%"><s:textfield name="businessObj" size="20"
									maxlength="100" /></td>

							</tr>

							<tr>
								<td width="25%"><label id="recommendations"
									name="recommendations" ondblclick="callShowDiv(this);"><%=label.get("recommendations")%></label>
								:</td>
								<td width="25%" colspan="1"><s:textfield size="30"
									name="recommendations" size="20" /></td>

								<td width="25%" colspan="1"><label name="business.benefits"
									id="business.benefits" ondblclick="callShowDiv(this);"><%=label.get("business.benefits")%></label>:<font
									color="red" id='ctrlHide'></font></td>
								<td width="25%"><s:textfield name="businessBenefits"
									size="20" maxlength="100" /></td>
							</tr>


							<tr>
								<td width="25%"><label id="resposible.person"
									name="resposible.person" ondblclick="callShowDiv(this);"><%=label.get("resposible.person")%></label>
								:</td>
								<td colspan="1"><s:textfield name="resposiblePersonName"
									size="20" maxlength="100" /> <s:hidden
									name="resposiblePersonCode" /> <s:hidden
									name="resposiblePersonToken" /> <img
									src="../pages/images/recruitment/search2.gif" height="16"
									align="absmiddle" width="16" id='ctrlHide'
									onclick="javascript:callsF9(500,325,'NonInventoryPurchase_f9responsible.action');">
								</td>
								<td width="25%"><label id="asset.information"
									name="asset.information" ondblclick="callShowDiv(this);"><%=label.get("asset.information")%></label>
								:</td>
								<td width="25%"><s:textfield name="assetInformation"
									size="20" maxlength="100" /></td>
							</tr>
							<tr>
								<td colspan="4"><label id="resposible.person.desc"
									name="resposible.person.desc" ondblclick="callShowDiv(this);"><%=label.get("resposible.person.desc")%></label>
								</td>
							</tr>
							<tr>
								<td width="25%"><label id="account" name="account"
									ondblclick="callShowDiv(this);"><%=label.get("account")%></label>
								:</td>
								<td colspan="3"><s:textfield name="account" size="20"
									maxlength="100" /></td>
							</tr>
							<tr>
								<td width="25%"><label id="comments" name="comments"
									ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
								:</td>
								<td colspan="3"><s:textarea name="comment" cols="97"
									rows="3"></s:textarea></td>
							</tr>

							<tr>
								<td width="25%"><label id="ship.via" name="ship.via"
									ondblclick="callShowDiv(this);"><%=label.get("ship.via")%></label>
								:</td>
								<td colspan="1"><s:textfield name="shipVia" size="20"
									maxlength="100" /></td>
								<td width="25%"><label id="cear" name="cear"
									ondblclick="callShowDiv(this);"><%=label.get("cear")%></label>
								:</td>
								<td width="25%"><s:textfield name="cear" size="20"
									maxlength="100" /></td>
							</tr>

							<tr>
								<td colspan="4"><label id="capital.equipment"
									name="capital.equipment" ondblclick="callShowDiv(this);"><%=label.get("capital.equipment")%></label>
								</td>
							</tr>

						</table>
						</td>
					</tr>
					<!-- END:PROJECT INFORMATION -->

					<tr>
						<td width="100%" height="22">
						<table width="100%" border="0" align="center" cellpadding="2"
							cellspacing="1" class="formbg">
							<tr>
								<td colspan="4"><strong>Product Information </strong></td>
							</tr>




							<tr>
								<td width="25%" colspan="1"><label name="qty" id="qty"
									ondblclick="callShowDiv(this);"><%=label.get("qty")%></label>:</td>
								<td colspan="1"><s:textfield name="qty" size="20"
									maxlength="200" /></td>

								<td width="25%" colspan="1"><label name="unit" id="unit"
									ondblclick="callShowDiv(this);"><%=label.get("unit")%></label>:<font
									color="red" id='ctrlHide'></font></td>
								<td width="25%"><s:textfield name="unit" size="20"
									maxlength="100" /></td>

							</tr>

							<tr>
								<td width="25%"><label id="price" name="price"
									ondblclick="callShowDiv(this);"><%=label.get("price")%></label>
								:</td>
								<td width="25%" colspan="1"><s:textfield size="30"
									name="price" size="20" /></td>

								<td width="25%" colspan="1"><label name="manufacture"
									id="manufacture" ondblclick="callShowDiv(this);"><%=label.get("manufacture")%></label>:<font
									color="red" id='ctrlHide'></font></td>
								<td width="25%"><s:textfield name="manufacture" size="20"
									maxlength="100" /></td>
							</tr>


							<tr>
								<td width="25%"><label id="mrf.part" name="mrf.part"
									ondblclick="callShowDiv(this);"><%=label.get("mrf.part")%></label>
								:</td>
								<td colspan="1"><s:textfield name="mrfPart" size="20"
									maxlength="100" /></td>
								<td width="25%"><label id="vendor.part" name="vendor.part"
									ondblclick="callShowDiv(this);"><%=label.get("vendor.part")%></label>
								:</td>
								<td width="25%"><s:textfield name="vendorPart" size="20"
									maxlength="100" /></td>
							</tr>
							<tr>
								<td width="25%"><label id="desc" name="desc"
									ondblclick="callShowDiv(this);"><%=label.get("desc")%></label>
								:</td>
								<td colspan="3"><s:textarea name="desc" cols="97" rows="3"></s:textarea>
								</td>
							</tr>

							<tr>
								<td colspan="4" align="center"><!--<s:submit value="Add To List" cssClass="add" id="ctrlHide" action="NonInventoryPurchase_addProductToList" onclick="return callAddToList();"/>
					
					--><input type="button" value="Add"
									onclick="addRowToTable('tableID','qty,unit,price,manufacture,mrfPart,vendorPart,img','S,S,S,S,S,S,img')"
									class="add"></td>
							</tr>

							<tr>
								<td colspan="4">
								<table width="100%" border="0" align="center" cellpadding="2"
									cellspacing="1" class="formbg" id="tableID">
									<tr>

										<td colspan="1" width="15%" class="formth"><label
											class="set" name="qty" id="qty1"
											ondblclick="callShowDiv(this);"><%=label.get("qty")%></label>
										</td>

										<td colspan="1" width="15%" align="center" class="formth">
										<label class="set" name="unit" id="unit1"
											ondblclick="callShowDiv(this);"><%=label.get("unit")%></label></td>

										<td colspan="1" width="15%" align="center" class="formth">
										<label class="set" name="price" id="price1"
											ondblclick="callShowDiv(this);"><%=label.get("price")%></label></td>

										<td colspan="1" width="15%" align="center" class="formth">
										<label class="set" name="manufacture" id="manufacture1"
											ondblclick="callShowDiv(this);"><%=label.get("manufacture")%></label></td>

										<td colspan="1" width="15%" align="center" class="formth">
										<label class="set" name="mrf.part" id="mrf.part1"
											ondblclick="callShowDiv(this);"><%=label.get("mrf.part")%></label></td>

										<td colspan="1" width="15%" align="center" class="formth">
										<label class="set" name="vendor.part" id="vendor.part1"
											ondblclick="callShowDiv(this);"><%=label.get("vendor.part")%></label></td>

										<td colspan="1" width="25%" align="center" class="formth">
										<label class="set" name="desc" id="desc1"
											ondblclick="callShowDiv(this);"><%=label.get("desc")%></label></td>
										<td colspan="1" width="15%" class="formth" id="ctrlHide">&nbsp;</td>
									</tr>
									<%
									int count1 = 0;
									%>
									<s:iterator value="productList">
										<tr>
											<td colspan="1" class="sortableTD"><s:property
												value="ittqty" />&nbsp;<s:hidden name="ittqty" /></td>
											<td colspan="1" class="sortableTD">&nbsp; <s:property
												value="ittunit" /><s:hidden name="ittunit" /></td>
											<td colspan="1" class="sortableTD">&nbsp; <s:property
												value="ittprice" /><s:hidden name="ittprice" /></td>
											<td colspan="1" class="sortableTD">&nbsp; <s:property
												value="ittmanufacture" /><s:hidden name="ittmanufacture" />
											</td>
											<td colspan="1" class="sortableTD">&nbsp; <s:property
												value="ittmrfPart" /><s:hidden name="ittmrfPart" /> <s:hidden
												name="ittcomment" /></td>
											<td colspan="1" class="sortableTD">&nbsp; <s:property
												value="ittvendorPart" /><s:hidden name="ittvendorPart" /></td>

											<td colspan="1" class="sortableTD">&nbsp; <s:property
												value="ittdesc" /><s:hidden name="ittdesc" /></td>

											<td width="25%" align="center" class="sortableTD"
												id="ctrlHide"><img
												src="../pages/common/css/icons/delete.gif"
												onclick="deleteTechnician(this);" id="ctrlHide"></td>
										</tr>
									</s:iterator>
								</table>

								</td>
							</tr>
						</table>
						</td>
					</tr>
					<!-- END:PRODUCT INFORMATION -->
				</table>
				</td>
			</tr>
		</s:if>

		<tr>
			<td width="100%" height="22">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><b>Form Approval</b></td>
				</tr>
				<tr>
					<td width="25%">Change My Manager :</td>
					<td colspan="3"><s:textfield name="approverToken" size="20"
						value="%{approverToken}" theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:textfield
						name="selectapproverName" size="70" value="%{selectapproverName}"
						theme="simple" readonly="true"
						cssStyle="background-color: #F2F2F2;" /> <s:hidden
						name="changeApproverCode" /></td>
				</tr>


				<tr valign="top">
					<td colspan="1" rowspan="3">My Manager :</td>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<%
						int ii = 0;
						%>
						<%!int count = 0;%>
						<s:iterator value="approverList">
							<tr>
								<td><s:textfield name="apprSrNo" readonly="true" size="20"
									cssStyle="background-color: #F2F2F2;" cssStyle="border:none;" />

								</td>
								<td><s:hidden name="approverCode" /> <s:textfield
									name="approverName" size="70" readonly="true"
									cssStyle="background-color: #F2F2F2;" cssStyle="border:none;" />
								</td>

							</tr>
							<%
							ii++;
							%>
							<%
							count = ii;
							%>
						</s:iterator>

					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td colspan="4">
			<table width="100%" cellspacing="1" cellpadding="1" class="formbg" border="0">
				<tr>
					<td width="25%" colspan="1"><strong><label
						class="set" name="completed.by" id="completed.by"
						ondblclick="callShowDiv(this);"> <%=label.get("completed.by")%></label></strong>
					:</td>
					<td width="20%" colspan="1"><s:property value="completedBy" />
					<s:hidden name="completedBy" /> <s:hidden name="completedOn" /></td>
					<td width="20%" colspan="1"><strong><label
						class="completed.on" name="completed.on" id="sin"
						ondblclick="callShowDiv(this);"> <%=label.get("completed.on")%></label></strong>
					:</td>
					<td width="25%" colspan="1"><s:property value="completedOn" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">	
		callMyFunction();
		
		function callMyFunction() {
			var appStatus = document.getElementById('paraFrm_status').value;
			if (appStatus == 'L') {
				document.getElementById('poAttachmentFlag').style.display = "";
			} else {
				document.getElementById('poAttachmentFlag').style.display = "none";
			}
		}
		
		function uploadFile(fieldName) {
			var path = document.getElementById("paraFrm_dataPath").value;
			window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		}
		
		function viewUploadedFile(id) {
			var uploadFileName = document.getElementById('paraFrm_'+id).value;
			if(uploadFileName == '') {
				alert('File not available...');
				return false;
			}
			document.getElementById('paraFrm').target = '_blank';
			document.getElementById('paraFrm').action = 'NonInventoryPurchaseAppr_viewUploadedFile.action?uploadFileName='+uploadFileName;
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target = 'main';
		}
		
		
	  function imposeMaxLength(Event, Object, MaxLen) {
        	return (Object.value.length <= MaxLen)||(Event.keyCode == 8 ||Event.keyCode==46||(Event.keyCode>=35&&Event.keyCode<=40))
	  }
	
	function callForDelete(id) {
		var vv=confirm("Do you really want to remove this application?");
		if(vv){
			document.getElementById('paraFrm_hiddenValue').value=id;
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'NonInventoryPurchaseAppr_removeSelectType.action';
			document.getElementById('paraFrm').submit();
			}else{
			return false;
			}
	}
	
	function callSelect() {
		if(document.getElementById('paraFrm_selectTypeCode').value==''){
			alert('Please Select Type');
			return false;
		}
		if(document.getElementById('paraFrm_noOfLots').value==''){
			alert('Please enter no of lots');
			return false;
		}
	}
	
	function printFun() {	
		window.print();
	}
		
	function backtolistFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'NonInventoryPurchaseAppr_back.action';
		document.getElementById('paraFrm').submit();
	}
	
	function approveFun() {
		var vv=confirm("Do you really want to approve this application?");
		if(vv){
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'NonInventoryPurchaseAppr_approve.action';
				document.getElementById('paraFrm').submit();
			}
	}
	
	function rejectFun() {
		var vv=confirm("Do you really want to reject this application?");
		if(vv){
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'NonInventoryPurchaseAppr_reject.action';
				document.getElementById('paraFrm').submit();
			}
	}
	
	function sendbackFun() {
		var vv=confirm("Do you really want to sendBack this application?");
		if(vv){
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'NonInventoryPurchaseAppr_sendBack.action';
				document.getElementById('paraFrm').submit();
			}
	}
	
	
	function deleteFun() {
		if(document.getElementById('paraFrm_employeeCode').value==''){
			alert('Please select prepared by');
			return false;
		}	
		var vv=confirm("Do you really want to delete this application?");
		if(vv){
				document.getElementById('paraFrm').target = "_self";
		      	document.getElementById('paraFrm').action = 'NonInventoryPurchaseAppr_delete.action';
				document.getElementById('paraFrm').submit();
		} else {
				return false;
		}
	}
	
	function closeFun() {
		window.close();
	}
		</script>


