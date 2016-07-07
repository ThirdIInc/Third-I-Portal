<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="ETds" target="main" theme="simple"
	validate="true" id="paraFrm">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">ETds</strong></td>
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
				<tr>
					<td width="78%"><s:if test="%{upload.viewFlag}"><s:submit cssClass="token"
						action="UploadCredit_uploadReport" value="Download Excel Sheet" />
					<s:submit cssClass="  reset" action="UploadCredit_reset"
						value="    Reset" /></s:if></td>
					<td width="22%">
					<div align="right"><span class="style2">*</span> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>

		</tr>

		<tr>
			<td colspan="3"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
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
								
									<tr>
										<td  width="40%" align="right"><label  class = "set" name="taxUploadFrm24" 
											id="taxUploadFrm24" ondblclick="callShowDiv(this);"><%=label.get("taxUploadFrm24")%></label> 
											<font color="red">*</font>:
										</td>
										<td colspan="2" width="20%">
											<s:textfield name="uploadFileName" id="uploadFileName"
											size="25"  readonly="true" />
										</td>
										<td colspan="3">
											<s:if test="%{eTds.viewFlag}">
	                    					 <input name="Browse" type="button" class="token" value="Upload" 
	                    					 onclick="uploadFile('uploadFileName');" />
	                    					 </s:if>
										</td>	
	                    				
									</tr>
									<tr>
										<td  width="40%" align="right"><label class = "set" name="uploadChallanFile" 
											id="uploadChallanFile" ondblclick="callShowDiv(this);"><%=label.get("uploadChallanFile")%></label> 
											:
										</td>
										<td colspan="2" width="20%">
											<s:textfield name="uploadChallanFileName" id="uploadChallanFileName"
											size="25"  readonly="true" />
										</td>
										<td colspan="3">
											<s:if test="%{eTds.viewFlag}">
	                    					 <input name="Browse" type="button" class="token" value="Upload" 
	                    					 onclick="uploadFile('uploadChallanFileName');" />
	                    					 </s:if>
										</td>	
	                    				
									</tr>
									
									
									<tr>
										<td colspan="5">&nbsp;</td>
									</tr>
									
									<tr align="center">
										<td colspan="5"><s:if test="%{eTds.insertFlag}"><s:submit cssClass="token"
										action="ETds_generate" onclick="return check();" value=" Generate ASCII " /></s:if>
									</tr>
								
								</table>
							</td>
						</tr>



						<tr>
							<td colspan="5"><img
								src="../pages/common/css/default/images/space.gif" width="5"
								height="7" /></td>
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
	var path="oo/<%=session.getAttribute("session_pool")%>/pay";
	window.open('../pages/common/multipleUploadFile.jsp?path='+path+'&field='+""+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}

function ReplaceAll(stringToFind,stringToReplace){
var temp = document.getElementById('uploadChallanFileName').value;

    var index = temp.indexOf(stringToFind);

        while(index != -1){

            temp = temp.replace(stringToFind,stringToReplace);

            index = temp.indexOf(stringToFind);

        }

        return temp;

    }

 


function check(){
	var upload = document.getElementById('uploadFileName').value;
	var uploadChallanFile = document.getElementById('uploadChallanFileName').value;
		
		if(upload==""){
			alert('Please Upload the Xls File');
			return false;
		}
		if(uploadChallanFile==""){
			return true;
		}
		else{
			uploadChallanFile = ReplaceAll(".","*");
			fieldList = uploadChallanFile.split("*");
			var count =  fieldList[fieldList.length - 1];
			if(count!= 'csi'){
				alert("Challan Input File shhould have csi extension");
				return false;
			}
		}
	}

</script>

