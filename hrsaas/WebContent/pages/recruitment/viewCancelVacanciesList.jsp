<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="VacancyPublish" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" align="right" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Cancel
					Vacancy List</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><input type="button"
				name="Back To Publish List" class="token"
				value="Back To Publish List" onclick="backToPublishList();">
			</td>
		</tr>
		<tr>
			<td>
			<table class="formbg" width="100%" cellspacing="1" cellspadding="0"
				border="0">
				<tr>
					<td colspan="2">
					<table width="100%" border="0" class="formbg">
						<tr>
							<td class="formtext">
							<table width="100%" border="0">
								<tr>
									<td width="10%" class="formth" align="center"><label
										class="set" name="serialNo" id="serialNo"
										ondblclick="callShowDiv(this);"> <%=label.get("serialNo")%></label>
									</td>

									<td class="formth" align="center" width="20%"><label
										class="set" name="requisitionName" id="requisitionName"
										ondblclick="callShowDiv(this);"> <%=label.get("requisitionName")%></label>
									</td>

							<!-- 
											<td class="formth" align="center" width="30%"><label
										class="set" name="vacancyNumber" id="vacancyNumber"
										ondblclick="callShowDiv(this);"> <%=label.get("vacancyNumber")%></label>
									</td>
							 -->	
							

									<td class="formth" align="center" width="20%"><label
										class="set" name="offerGiven" id="offerGiven"
										ondblclick="callShowDiv(this);"> <%=label.get("offerGiven")%></label>
									</td>

									<td class="formth" align="center" width="20%"><label
										class="set" name="appointmentGiven" id="appointmentGiven"
										ondblclick="callShowDiv(this);"> <%=label.get("appointmentGiven")%></label>
									</td>

									<td class="formth" align="center" width="20%"><label
										class="set" name="vacancyStatus" id="vacancyStatus"
										ondblclick="callShowDiv(this);"> <%=label.get("vacancyStatus")%></label>
									</td>

									<td class="formth" align="center" width="%20"><label
										class="set" name="action" id="action"
										ondblclick="callShowDiv(this);"> <%=label.get("action")%></label>
									</td>
								</tr>

								<s:if test="vacancyListAvailable">
									<%
										int i = 0;									
									%>
									<s:iterator value="cancelVacancyIteratorList">
										<tr>
											<td align="center" class="sortableTD"><%=++i%></td>
											<s:hidden name="requisitionCodeItr" />
											<td class="sortableTD">&nbsp; <s:property
												value="requisitionNameItr" />
											<s:hidden name = "vacancyNumberItr"/>
											<s:hidden name="vacancyDetailCodeItr" />
											</td>

										<!-- 
												<td class="sortableTD" align="left">&nbsp; <s:property
												value="vacancyNumberItr" /></td>
										 -->

											<td class="sortableTD" align="center">&nbsp; <s:property
												value="offerGivenItr" /></td>

											<td class="sortableTD" align="center">&nbsp; <s:property
												value="appointmentGivenItr" /></td>

											<td class="sortableTD" align="center">&nbsp; <s:property
												value="vacancyStatusItr" /></td>
											<s:if test="isVacancyClose">
												<td class="sortableTD" align="center"><b><font
													color="red">This vacancy is closed </font> </b></td>
											</s:if>
											<s:else>
												<s:hidden name="vacancyPublishCodeItr" />
												<td class="sortableTD" align="center">&nbsp; <input
													type="button" name="Cancel Vacancy" value="Cancel Vacancy"
													class="token"
													onclick="cancelVacancy('<s:property value="requisitionCodeItr"/>','<s:property value="vacancyNumberItr"/>','<s:property value="vacancyPublishCodeItr"/>',
																			'<s:property value="vacancyDetailCodeItr" />','<s:property value="appointmentGivenItr" />','<s:property value="offerGivenItr" />');" />
												</td>
											</s:else>
										</tr>
									</s:iterator>
								</s:if>

								<s:else>
									<td align="center" colspan="7" nowrap="nowrap"><font
										color="red">There is no data to display</font></td>
								</s:else>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="vacancyListAvailable"> 
			<tr>
				<td colspan="2" align="right">
					<b>Total No. Of Records: <s:property value="totalNumberOfRecords"/></b>
				</td>
			</tr>
		</s:if>
		<tr>
			<td width="100%"><input type="button"
				name="Back To Publish List" class="token"
				value="Back To Publish List" onclick="backToPublishList();">
			</td>
		</tr>
	</table>
</s:form>

<script>
function cancelVacancy(requisitionCode, vacancyCode, vacancyPublishCodeItr, vacancyDetailCodeItr, appointmentGivenItr, offerGivenItr)
{
  	/*
  	if(offerGivenItr=='Yes' || appointmentGivenItr=='Yes'){
  	    alert("You cannot cancel this vacancy.");
  	    return false;
  	}else{ */
  		var con = confirm("Do you really want to cancel this vacancy?")
  	   if(con){
  	   		document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action ='VacancyPublish_cancelVacancy.action?requisitionCode='+requisitionCode+'&vacancyCode='+vacancyCode+'&vacancyPublishCodeItr='+vacancyPublishCodeItr+'&vacancyDetailCodeItr='+vacancyDetailCodeItr;
			document.getElementById('paraFrm').submit();
  	   }else {
  	   	return false;
  	   }
  	//}
}

function backToPublishList() {
	document.getElementById('paraFrm').target = '_self';
	document.getElementById('paraFrm').action ='VacancyManagement_input.action?status=P';
	document.getElementById('paraFrm').submit();
}
</script>
