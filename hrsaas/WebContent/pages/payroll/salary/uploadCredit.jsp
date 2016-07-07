<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="UploadCredit" target="main" theme="simple"
	validate="true" id="paraFrm">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Upload
					Credit</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="78%"><s:if test="%{upload.viewFlag}">
								<s:submit cssClass="token" action="UploadCredit_uploadReport"
									value="Download Excel Sheet" />
								<s:submit cssClass="  reset" action="UploadCredit_reset"
									value="    Reset" />
							</s:if></td>
							<td width="22%">
							<div align="right"><span class="style2">*</span> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					</td>

				</tr>



				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">

								<tr align="center">
									<td width="100%"><label  class = "set"  id="months" name="months" ondblclick="callShowDiv(this);"><%=label.get("months")%></label><font color="red">*</font>: <s:select
										name="month" headerKey="-1" headerValue="--Select--"
										list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<label  class = "set"  id="years" name="years" ondblclick="callShowDiv(this);"><%=label.get("years")%></label><font color="red">*</font>: <s:textfield name="year"
										size="5" maxlength="4" cssStyle="text-align: right"
										onkeypress="return numbersOnly();" /></td>
								</tr>
								<tr>
									<td colspan="3">&nbsp;</td>
								</tr>
								<tr align="center">
									<td width="100%"><label  class = "set"  id="selCredit" name="selCredit" ondblclick="callShowDiv(this);"><%=label.get("selCredit")%></label><font color="red">*</font>:
									<s:textfield label="%{getText('creditName')}" theme="simple"
										size="30" readonly="true" name="creditName" /> <s:hidden
										name="creditCode" /> <s:if test="%{upload.generalFlag}">
									</s:if><s:else>
										<img src="../pages/images/search.gif" class="iconImage"
											height="18" align="absmiddle" width="18"
											onclick="javascript:callsF9(500,325,'UploadCredit_f9CreditAction.action');">
									</s:else></td>
								</tr>

								<tr>
									<td>
									<table width="100%" border="0" align="center" cellpadding="0"
										cellspacing="2">

										<tr align="center">
											<td width="100%"><label  class = "set"  id="upload" name="upload" ondblclick="callShowDiv(this);"><%=label.get("upload")%></label> <font color="red">*</font>:
											&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="uploadFileName"
												size="20" readonly="true" /> <s:if
												test="%{upload.viewFlag}">
												<input name="Browse" type="button" class="token"
													value="Upload" onclick="uploadFile('uploadFileName');" />
											</s:if></td>
										</tr>

										<tr>
											<td colspan="3">&nbsp;</td>
										</tr>

										<tr align="center">
											<td width="100%"><s:if test="%{upload.insertFlag}">
												<s:submit cssClass="token"
													action="UploadCredit_displayExcel"
													onclick="return check();" value=" Update " />
											</s:if>
										</tr>

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
			</td>
		</tr>


	</table>



</s:form>

<script>
	
setCurrentYear('paraFrm_year');
function uploadFile(fieldName) 
{
	var path="oo/<%=session.getAttribute("session_pool")%>/pay";
	window.open('../pages/common/uploadFile.jsp?path='+path+'&field='+""+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}

function check(){
	var month = document.getElementById('paraFrm_month').value;
	var year = document.getElementById('paraFrm_year').value;
	var upload = document.getElementById('paraFrm_uploadFileName').value;
    var credithead = document.getElementById('paraFrm_creditCode').value;
   
		if(month=="-1"){
			alert("Please select "+document.getElementById('months').innerHTML.toLowerCase());
			return false;
		}
		if(year==""){
			alert("Please Select "+document.getElementById('years').innerHTML.toLowerCase());
			return false;
		}
		if(credithead==""){
			alert("Please select "+document.getElementById('selCredit').innerHTML.toLowerCase());
			return false;
		}
		
		if(upload==""){
			alert("Please "+document.getElementById('selCredit').innerHTML.toLowerCase()+" the Xls File");
			return false;
		}else
		{
		//alert("with in else....!!");
		var sub=upload.substring(upload.length-4,upload.length);

		if(!(sub==".xls"||sub==".Xls")){
		document.getElementById('paraFrm_uploadFileName').value="";
		alert("Please Upload Only Xls File with .xls Extension");
		 return false;
		}
		
	   
		}
		

}

</script>

