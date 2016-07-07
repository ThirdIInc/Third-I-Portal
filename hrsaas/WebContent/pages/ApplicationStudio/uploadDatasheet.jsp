<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="UploadDatasheet" target="main" theme="simple"
	validate="true" id="paraFrm">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">UploadDatasheet</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
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
				<!-- <tr>
					<td width="78%"><s:if test="%{upload.viewFlag}"><s:submit cssClass="token"
						action="UploadCredit_uploadReport" value="Download Excel Sheet" />
					<s:submit cssClass="  reset" action="UploadCredit_reset"
						value="    Reset" /></s:if></td> -->
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

						<tr>
							<td>
								<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								
									<tr align="center">
										<td  width="100%">Select Data Sheet<font color="red">*</font>:
										&nbsp;&nbsp;&nbsp;&nbsp;<s:textfield name="uploadFileName" size="20"  readonly="true" />
                     <input name="Browse" type="button" class="token" value="Upload" onclick="uploadFile('uploadFileName');" /></td>
									</tr>
									
									<tr>
										<td colspan="3">&nbsp;</td>
									</tr>
									
									<tr align="center">
										<td  width="100%"><s:submit cssClass="token"
										action="UploadDatasheet_generate" onclick="return check();" value=" Generate Data Sheet " />
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
	

function uploadFile(fieldName) 
{
	//var path="images/Datasheet";
	var path="oo/<%=session.getAttribute("session_pool")%>/upload";
	//alert('===Upload Path===='+path);
	window.open('../pages/common/uploadFile.jsp?path='+path+'&field='+""+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}

function check(){
	var upload = document.getElementById('paraFrm_uploadFileName').value;
			if(upload==""){
			alert('Please Upload the Xls File');
			return false;
		}
	}

</script>

