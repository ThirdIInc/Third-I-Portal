<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="UploadDocuments" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%" cellpadding="0" cellspacing="0">


		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Manage
					My Documents </strong></td>
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
					<td width="75%"><input type="button" value="Save"
						onclick="return callSave();" class="token" />
						<input type="button" value="Back" onclick="return callback();"
						class="token" />		
						</td>

				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" colspan="4"><s:if test="true">


				<table width="100%" border="0" cellpadding="1" cellspacing="1"
					class="formbg">
					<tr>
						<td width="100%" colspan="5" class="formtxt"><strong
							class="formhead"> <%
 	String status = "";
 	try {
 %> <%
 		status = (String) request.getAttribute("listname");
 		if (status.equalsIgnoreCase("J")) {
 			out.println("Joining");
 		}
 		if (status.equalsIgnoreCase("M")) {
 			out.println("Medical");
 		}
 		if (status.equalsIgnoreCase("T")) {
 			out.println("Transfer");
 		}
 		if (status.equalsIgnoreCase("I")) {
 			out.println("Interview");
 		}
 		if (status.equalsIgnoreCase("B")) {
 			out.println("Background Verification");
 		}
 		if (status.equalsIgnoreCase("P")) {
 			out.println("Prejoining");
 		}
 %> <%
 	} catch (Exception e) {

 	}
 %> Check List Details:</strong> <s:hidden name="Chkstatus1" value='<%=status %>' />
						<s:hidden name="Chkstatus" /></td>

					</tr>
					<tr>
						<td width="10%" valign="top" class="formth" nowrap="nowrap"><label
							class="set" name="serial.no" id="serial.no"
							ondblclick="callShowDiv(this);"> <%=label.get("serial.no")%></label>
						</td>
						<td width="30%" valign="top" class="formth" nowrap="nowrap"><label
							class="set" name="checklistname" id="checklistname"
							ondblclick="callShowDiv(this);"> <%=label.get("checklistname")%></label>
						</td>


						<td width="60%" class="formth" colspan="1"><label class="set"
							name="document" id="document" ondblclick="callShowDiv(this);"><%=label.get("document")%></label></td>
					</tr>
					<s:if test="noData">
						<tr>
							<td width="100%" colspan="6" align="center"><font
								color="red">No Data To Display</font></td>
						</tr>
					</s:if>
					<%!int p = 1;%>
					<%
					int q = 1, z = 0;
					%>
					<s:iterator value="ChkList">
						<tr>
							<td class="sortabletd" nowrap="nowrap"><%=p%><s:hidden
								name="checkListitemcode" /></td>

							<td class="sortabletd"><s:property value="checkListName" />&nbsp;</td>



							<td>
							<table>
								<tr>
									<td><input type="text" name="uploadLocFileName" size="20"
										readonly="true" id="paraFrm_uploadLocFileName<%=p%>"
										value='<s:property value="uploadLocFileName" />' size="20" />

									</td>
									<td><input type="button" name="uploadLoc"
										value="Select Doc." class="token"
										onclick="uploadFile('paraFrm_uploadLocFileName<%=p%>');" /></td>
									<td><input type="button" name="show" value="Show"
										class="token"
										onclick="showRecord('paraFrm_uploadLocFileName<%=p%>');" /></td>
								</tr>
							</table>
							</td>

						</tr>
						<%
							p++;
							z++;
						%>
					</s:iterator>
					<%
						q = p;
						p = 1;
						z = 0;
					%>
				</table>
			</s:if> <s:hidden name="chkLength" value="%{chkLength}" /></td>
		</tr>


		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="75%"><input type="button" value="Save"
						onclick="return callSave();" class="token" /> <input
						type="button" value="Back" onclick="return callback();"
						class="token" /></td>

				</tr>
			</table>
			</td>
		</tr>

	</table>

	<s:hidden name="dataPath" />
	<s:hidden name="hiddenCode" />
	<s:hidden name="requisitionCode" />
	
</s:form>

<script>

function callback()
{
document.getElementById("paraFrm").action='CandidateJobBoard_input.action';
	    document.getElementById("paraFrm").submit();
}


function callSave()
{

  var conf=confirm("Do you really want to save this record ?");
  			if(conf) 
  			{
  			    document.getElementById('paraFrm').action="UploadDocuments_save.action";
				document.getElementById('paraFrm').submit(); 
  				return true;
  			}
	  		else
	  		{
	  			 return false;
	  		}
	  		   

}


 function uploadFile(fieldName)
{
	try
	{
		var path = document.getElementById("paraFrm_dataPath").value;
		//alert("UPLOADED PATH===========>"+path);
		window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
	}
	catch(e)
	{
		alert("Error occurred in uploadFile() ===> "+e);
	}
}


 function showRecord(fieldName)
	{
		var fileName =document.getElementById(fieldName).value;
		if(fileName=="")
		{
			alert("Please upload file.");
			return false ; 
		}
		document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "UploadDocuments_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		return true ; 
	}
</script>