<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="BackGroundCheck" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="bgStatus" />
	<s:hidden name="dataPath" />
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
					<td width="93%" class="txt"><strong class="text_head">Back  
					Ground Check </strong></td>
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
				<s:hidden name="buttonpanelcode" />
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
							<td width="22%"></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						class="formbg" cellspacing="2">
						<s:hidden name="bgcheckCode" />
						<tr>
							<td valign="top" width="20%"><!-- Candidate Name --> <label
								class="set" name="cand.name" id="candidate.name"
								ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
							:</td>
							<td valign="top" width="25%"><s:hidden name="candidateCode" />
							<s:property value="candidateName" /></td>
							<td valign="top" width="25%"><!--Offer Status --> <label
								class="set" name="offer.status" id="offer.status"
								ondblclick="callShowDiv(this);"> <%=label.get("offer.status")%></label>
							:</td>
							<td valign="top" width="25%"><s:property value="offerStatus" /></td>
						</tr>
						<tr>
							<td valign="top" width="20%"><!--Requisition Code --> <label
								class="set" name="reqs.code" id="requi.code"
								ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
							:</td>
							<td valign="top" width="25%"><s:hidden name="reqCode" /><s:property
								value="reqName" /></td>
							<td valign="top" width="25%"><!--Background Check Type--> <label
								class="set" name="Background.type" id="Background.type"
								ondblclick="callShowDiv(this);"><%=label.get("Background.type")%></label>
							:</td>
							<td valign="top" width="25%">
							<!--  <input name="bgCheckType" type="radio" value="I" onclick="enableIndoorSource();" checked="true"/>&nbsp;
							In House&nbsp;<input name="bgCheckType"  type="radio" value="O" onclick="enableOutsource();" /> Out Source	
					--> <s:hidden name="bgCheckType" /> <s:hidden id="bgCheckType1" />
							<s:hidden id="bgCheckType2" /> <s:property
								value="DupbgCheckType" /></td>
						</tr>
						<tr>
							<td valign="top" width="20%"><!--Division --> <label
								class="set" name="division" id="bg.division"
								ondblclick="callShowDiv(this);"> <%=label.get("division")%></label>

							:</td>
							<td valign="top" width="25%"><s:property value="division" /></td>

							<td valign="top" width="25%">
							<div id="outSourceAgency2"><!--Select Outsource Agency -->

							<label class="set" name="OutsourceAgency" id="OutsourceAgency"
								ondblclick="callShowDiv(this);"> <%=label.get("OutsourceAgency")%></label>:
							</div>
							</td>

							<td valign="top" width="25%"><s:hidden
								name="outsourceAgencyName" />
							<div id="outSourceAgency1"><s:property
								value="outsourceAgencyName" /> <s:hidden
								name="outsourceAgencyCode" /></div>
							</td>

						</tr>
						<tr>
							<td valign="top" width="20%"><!--Branch --> <label
								class="set" name="branch" id="bg.branch"
								ondblclick="callShowDiv(this);"> <%=label.get("branch")%></label>

							:</td>
							<td valign="top" width="25%"><s:property value="branch" /></td>
							<td valign="top" width="25%"></td>
							<td valign="top" width="25%"></td>
						</tr>
						<tr>
							<td valign="top" width="20%"><!--Department --> <label
								class="set" name="department" id="bg.department"
								ondblclick="callShowDiv(this);"> <%=label.get("department")%></label>

							:</td>
							<td valign="top" width="25%"><s:property value="department" /></td>
							<td valign="top" width="25%"><!--Select Check List Type--> <label
								class="set" name="CheckListType" id="CheckListType"
								ondblclick="callShowDiv(this);"><%=label.get("CheckListType")%></label>
							:</td>
							<td valign="top" width="25%"><s:hidden name="checkListType" />
							<s:property value="DupcheckListType" /></td>
						</tr>
						<tr>
							<td valign="top" width="20%"><!--Position --> <label
								class="set" name="position" id="Position"
								ondblclick="callShowDiv(this);"> <%=label.get("position")%></label>
							:</td>
							<td valign="top" width="25%"><s:property value="position" /></td>
							<td valign="top" width="25%">&nbsp;</td>
							<td valign="top" width="25%"></td>


						</tr>
					</table>
					</td>
				</tr>


				<tr>
					<td><s:if test="checklistTable">

						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="formbg">

							<tr>
								<td width="10%" colspan="2" class="formtxt"><strong
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
 %> Check List Details:</strong></td>
								<s:hidden name="Chkstatus1" value='<%=status %>' />
								<s:hidden name="Chkstatus" />
							</tr>
							<tr>
								<td width="10%" valign="top" class="formth" nowrap="nowrap"
									align="center"><!--  Sr.No. --><b> <label class="set"
									name="serial.no" id="serial.no" ondblclick="callShowDiv(this);">
								<%=label.get("serial.no")%></label></b></td>
								<td width="20%" valign="top" class="formth" nowrap="nowrap"><b>
								<label class="set" name="checklistname" id="checklistname"
									ondblclick="callShowDiv(this);"> <%=label.get("checklistname")%></label></b>
								<!--  CheckList Name --></td>
								<td width="10%" valign="top" class="formth"><!--  Response --><b>
								<label class="set" name="response" id="response"
									ondblclick="callShowDiv(this);"> <%=label.get("response")%></label></b>
								</td>
								<td width="25%" valign="top" class="formth"><!--  Comments --><b>
								<label class="set" name="Listcomments" id="Listcomments"
									ondblclick="callShowDiv(this);"> <%=label.get("Listcomments")%></label></b>
								</td>

								<td width="10%" class="formth" colspan="1"><label
									class="set" name="document" id="document"
									ondblclick="callShowDiv(this);"><%=label.get("document")%></label></td>

							</tr>
							<s:if test="noData">
								<tr>
									<td width="100%" colspan="7" align="center"><font
										color="red">No Data To Display</font></td>
								</tr>
							</s:if>
							<%!int p = 1;%>
							<%
							int q = 1;
							%>
							<s:iterator value="ChkList">
								<tr>
									<td class="sortabletd" width="5%" nowrap="nowrap"
										align="center"><%=p%><s:hidden name="checkListitemcode" /></td>

									<td class="sortabletd" width="15%"><s:property
										value="checkListName" />&nbsp;</td>

									<td class="sortabletd" width="15%"><s:hidden
										name="checkListresponce" /> <s:property
										value="DupcheckListresponce" /> &nbsp;</td>
									<td class="sortabletd" width="15%"><s:property
										value="checkListComments" /> &nbsp;</td>

									<td class="sortabletd" width="15%"><s:hidden
										name="uploadLocFileName" /> <a href="#"
										onclick="viewUploadedFile('<s:property value="uploadLocFileName"/>');"
										title="Click here to view uploaded file"> <font
										color="blue"><u><s:property
										value="uploadLocFileName" /> </u></font></td>
									<td>
								</tr>
								<%
								p++;
								%>
							</s:iterator>
							<%
								q = p;
								p = 1;
							%>
						</table>
					</s:if> <s:hidden name="chkLength" value="%{chkLength}" /></td>
				</tr>


				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						class="formbg" cellspacing="2">
						<tr>
							<td valign="top" width="12%"><!-- Overall Background Check List Comments-->

							<label class="set" name="overallcomments" id="overallcomments"
								ondblclick="callShowDiv(this);"> <%=label.get("overallcomments")%></label>
							:</td>
							<td valign="top" width="45%"><s:property
								value="overallComments" /></td>
						</tr>

						<tr>
							<td></td>
							<td><s:hidden name="buttonpan">
							</s:hidden>
							<div id="saveForInDoor">
							<!--<s:submit value="Save"  onclick="return saveFun();"/>-->
							</div>

							<div id="forwardForOutDoor">
								<!--<s:submit value="Forward To Outsource Agency"  onclick="return saveFun();"  />-->
							</div>
							</td>
						</tr>
					</table>

					</td>
					<s:hidden name="Chkreqcode"></s:hidden>
					<s:hidden name="Chkcandidatecode"></s:hidden>
					<s:hidden name="Chkoffercode"></s:hidden>
				</tr>

				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="75%"><jsp:include
								page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
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

callForIndoor();
function callForIndoor()
{
  document.getElementById('outSourceAgency1').style.display='none';
  document.getElementById('outSourceAgency2').style.display='none';
  try {
  		if(document.getElementById('paraFrm_outsourceAgencyName').value!="") {
  			document.getElementById('bgCheckType1').checked=true;
  			document.getElementById('outSourceAgency1').style.display='';
  			document.getElementById('outSourceAgency2').style.display='';
  		} else {
  			document.getElementById('bgCheckType2').checked=true;
  		}
   } catch(e) {
   		document.getElementById('bgCheckType1').checked=true;
   }
}

function enableOutsource(){ 
        document.getElementById('saveForInDoor').style.display='none';
        document.getElementById('forwardForOutDoor').style.display='';
        document.getElementById('outSourceAgency2').style.display='';
        document.getElementById('outSourceAgency1').style.display='';
}

function enableIndoorSource() {   
    		document.getElementById('saveForInDoor').style.display='';
            document.getElementById('forwardForOutDoor').style.display='none';
            document.getElementById('outSourceAgency1').style.display='none';
            document.getElementById('outSourceAgency2').style.display='none';
}

function checkList() {
       var listtype=document.getElementById('paraFrm_checkListType').value;
       if(document.getElementById('paraFrm_checkListType').value!="S") { 
       		return true;
		} else { 
			alert("Please Select Check List Type");
		  	return false;
		}
}
var fieldName = ["paraFrm_candidateCode","paraFrm_reqName","paraFrm_checkListType"];
var lableName = ["candidate.name","requi.code","CheckListType"];
var badflag = ["select","select","select"];


/*
       
 function forwardToOutsource()
 {
	  if(!checkMandatory(fieldName,lableName,badflag))
        return false;  
        
         if(document.getElementById('paraFrm_outsourceAgencyCode').value==""){
		alert("Please select Outsource Agency");
		return false;
		}
        
        if(document.getElementById('paraFrm_chkLength').value==""){
		alert("Please enter checkList details !");
		return false;
		}
        return true;
 }      
    */  
   
function addnewFun()
{
	document.getElementById('paraFrm').action="BackGroundCheck_addNew.action";
	document.getElementById('paraFrm').submit();
}



function saveFun()
{
	 if(!validateBlank(fieldName,lableName,badflag))
        return false;  
        
       if(document.getElementById('paraFrm_chkLength').value=="" ||document.getElementById('paraFrm_chkLength').value=="0"){
		alert("Please click on Show List.");
		return false;
		}
	document.getElementById('paraFrm').action="BackGroundCheck_save.action";
	document.getElementById('paraFrm').submit();
	return true;
}

//For Edit Button

function editFun()
{
	document.getElementById('paraFrm').action="BackGroundCheck_edit.action";
	document.getElementById('paraFrm').submit();
}

//For Delete Button

function deleteFun()
{
	var con=confirm('Do you really want to delete this record?')
	if(con){
		   document.getElementById('paraFrm').action="BackGroundCheck_delete.action";
		   document.getElementById('paraFrm').submit();
	} else{
	     return false;
	}

}

//For F9Window

function searchFun()
{
	callsF9(500,300,"BackGroundCheck_f9search.action");
	
}

//For Cancel Button
function cancelFun()
{
	 
	   if(document.getElementById('paraFrm_buttonpanelcode').value=="2")
	  {
	   document.getElementById('paraFrm').action="BackGroundCheck_cancelSec.action";
	  }else if(document.getElementById('paraFrm_buttonpanelcode').value=="3")
	  {
	   document.getElementById('paraFrm').action="BackGroundCheck_cancelThrd.action";
	  }
	  else
	  {
	  document.getElementById('paraFrm').action="BackGroundCheck_cancelFirst.action";
	  }
	  document.getElementById('paraFrm').submit();
	  return true;
} 	
   
function viewUploadedFile(attachedFileName) {
	document.getElementById('paraFrm').target = "_blank";
	document.getElementById("paraFrm").action="BackGroundCheck_viewAttachedFile.action?attachedFileName="+attachedFileName;
	document.getElementById("paraFrm").submit();	
	document.getElementById('paraFrm').target = "main";
}		
    	
    	
    	</script>