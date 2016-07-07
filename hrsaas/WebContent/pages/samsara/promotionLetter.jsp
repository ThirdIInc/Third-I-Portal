<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>


<s:form action="PromotionReportAction" id="paraFrm" theme="simple">
	<table class="formbg" width="100%" cellpadding="2" cellspacing="1">


		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Promotion
					Letter </strong></td>
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
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="70%" colspan="3">
							<s:if test="%{viewFlag}">
								<input type="button" class="token"
									onclick="return callPromotionLetter();"
									value="    Generate Letter" />
							</s:if> <s:submit cssClass="reset" action="PromotionLetter_reset"
								theme="simple" value="    Reset" /></td>

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
									<td class="formtext"><label name="division" id="division"
										ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font
										color="red">*</font></td>
									<td nowrap="nowrap"><s:hidden name="divCode" />
									<s:textfield name="divName" size="30" readonly="true" />
									<img src="../pages/images/recruitment/search2.gif" width="16"
										class="iconImage" height="15"
										onclick="javascript:callsF9(500,325,'PromotionLetter_f9divaction.action');" />
									</td>
									
								</tr>

								<tr>
									<td width="15%" colspan="1"><label class="set"
										name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font
										color="red">* </font> :</td>
									<td width="85%" colspan="4"><s:textfield name="empToken"
										size="10" value="%{empToken}" readonly="true" /> <s:textfield
										name="empName" size="70" value="%{empName}" readonly="true" />
									<s:hidden name="empCode" value="%{empCode}" /><img src="../pages/images/recruitment/search2.gif" width="16"
										class="iconImage" height="15"
										onclick="javascript:callsF9(500,325,'PromotionLetter_f9emp.action');" /></td>
								</tr>

								<tr>
									<td width="15%" colspan="1"><label class="set"
										name="promotion.eff.date" id="promotion.eff.date"
										ondblclick="callShowDiv(this);"><%=label.get("promotion.eff.date")%></label>:</td>
									<td width="35%" colspan="1"><s:textfield size="10"
										theme="simple" name="effDate" /><s:a
										href="javascript:NewCal('paraFrm_effDate','DDMMYYYY');">
										<img class="iconImage"
											src="../pages/images/recruitment/Date.gif" width="16"
											height="16" border="0" align="absmiddle" />
									</s:a></td>
								</tr>
								<s:hidden name="promCode" />
								<s:hidden name="strength" />
								<s:hidden name="weakness" />
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>


			</table>
			</td>
		</tr>

	</table>
</s:form>
<script>
 
 function callPromotionLetter(){
  		
  		
  	  document.getElementById('paraFrm').target="_blank";
      document.getElementById('paraFrm').action="PromotionLetter_getPromotionLetter.action";
	  document.getElementById('paraFrm').submit();  
      document.getElementById('paraFrm').target="main";
  
   	
	
}
 
 
</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>