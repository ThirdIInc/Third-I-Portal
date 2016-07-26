<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="BackGroundCheck" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="bgStatus" />
	<s:hidden name="DupcheckListType" />
	<s:hidden name="DupcheckListresponce" />
	<s:hidden name="DupbgCheckType" />
	<s:hidden name="buttonpanelcode" />
	<s:hidden name="partnertype" />
	<s:hidden name="hiddenstatus" />
	<s:hidden name="candiFlag" />

	<s:hidden name="dataPath" />
	<s:hidden name="checkRemoveUpload" />
	<s:hidden name="toEamilAddress" />

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
					Ground Check  </strong></td>
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
					<td width="75%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%" colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%" colspan="4" class="txt"><strong
						class="forminnerhead">Back Ground Check</strong></td>
				</tr>

				<tr>
					<td><s:hidden name="bgcheckCode" />
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<!--Table 1-->
						<tr>
							<td width="20%"><label class="set" name="cand.name"
								id="candidate.name" ondblclick="callShowDiv(this);"><%=label.get("cand.name")%></label>
							:<font color="red">*</font></td>
							<td width="25%" nowrap="nowrap"><s:hidden
								name="candidateCode" /> <s:textfield name="candidateName"
								size="25" readonly="true" /> <s:if test="candiFlag"></s:if> <s:else>
								<img src="../pages/images/search2.gif" height="16"
									align="absmiddle" width="16" theme="simple"
									onclick="javascript:callsF9(500,325,'BackGroundCheck_f9Candidate.action')">
							</s:else></td>
							<td width="25%"><label class="set" name="offer.status"
								id="offer.status" ondblclick="callShowDiv(this);"><%=label.get("offer.status")%></label>
							:</td>
							<td width="25%"><s:textfield name="offerStatus" size="25"
								readonly="true" /></td>
						</tr>
						<tr>
							<td width="20%"><!-- Requisition Code --> <label class="set"
								name="reqs.code" id="requi.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
							:<font color="red">*</font></td>
							<td width="25%" nowrap="nowrap"><s:hidden name="reqCode" /><s:textfield
								name="reqName" size="25" readonly="true" /> <s:if
								test="candiFlag"></s:if> <s:else>
								<img src="../pages/images/search2.gif" height="16"
									align="absmiddle" width="16" theme="simple"
									onclick="javascript:callsF9(500,325,'BackGroundCheck_f9Requistion.action')">
							</s:else></td>
							<td width="25%"><!--Background Check Type --> <label
								class="set" name="Background.type" id="Background.type"
								ondblclick="callShowDiv(this);"><%=label.get("Background.type")%></label>
							:</td>
							<td width="25%"><input name="bgCheckType" type="radio"
								value="I" onclick="enableIndoorSource();" id="bgCheckType2" />&nbsp;InHouse
							<!--  <label class="set" name="InHouse" id="InHouse"
								ondblclick="callShowDiv(this);"></label>
								--> &nbsp; <input name="bgCheckType" type="radio" value="O"
								id="bgCheckType1" onclick="enableOutsource();" />Out Source <!-- 
							<label class="set" name="OutSource" id="OutSource"
								ondblclick="callShowDiv(this);"></label> --> <!--<s:radio
											name="bgCheckType"
											list="#{'I':'In House', 'O':'Out Source'}"
											onclick="enableOutsource(this);" theme="simple" />
								
								--></td>
						</tr>
						<tr>
							<td width="20%"><!--Division --> <label class="set"
								name="division" id="bg.division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:</td>
							<td width="25%"><s:textfield name="division" size="25"
								readonly="true" /></td>

							<td width="25%">
							<div id="outSourceAgency2"><!--Select Outsource Agency -->
							<label class="set" name="OutsourceAgency" id="OutsourceAgency"
								ondblclick="callShowDiv(this);"><%=label.get("OutsourceAgency")%></label>
							:</div>
							</td>

							<td width="25%">
							<div id="outSourceAgency1"><s:hidden
								name="outsourceAgencyCode" /> <s:textfield
								name="outsourceAgencyName" size="25" readonly="true" /><img
								src="../pages/images/search2.gif" height="16" align="absmiddle"
								width="16" theme="simple"
								onclick="javascript:callsF9(500,325,'BackGroundCheck_f9outsource.action')">
							</div>
							</td>

						</tr>
						<tr>
							<td width="20%"><!--Branch --> <label class="set"
								name="branch" id="bg.branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td width="25%"><s:textfield name="branch" size="25"
								readonly="true" /></td>
							<td width="25%"></td>
							<td width="25%"></td>
						</tr>
						<tr>
							<td width="20%"><!--Department --> <label class="set"
								name="department" id="bg.department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td width="25%"><s:textfield name="department" size="25"
								readonly="true" /></td>
							<td width="25%"><!--Select Check List Type --> <label
								class="set" name="CheckListType" id="CheckListType"
								ondblclick="callShowDiv(this);"><%=label.get("CheckListType")%></label>
							:<font color="red">*</font></td>
							<td width="25%"><!--<s:hidden name="requisitionCode"
									id="requisitionCode" /><s:textfield name="requisitionName"
									size="25" readonly="true" /><img
									src="../pages/images/search2.gif" height="16" align="absmiddle"
									width="16" theme="simple"
									onclick="javascript:callsF9(500,325,'BackGroundCheck_f9CntPerson.action')">
								
								-->
							<!-- 
								<s:select theme="simple" name="checkListType" headerKey="S"
									onchange="return callChange();" headerValue="--Select--"
									list="#{'J':'Joining CheckList','M':'Medical CheckList','T':'Transfer CheckList','I':'Interview CheckList','P':'Prejoining CheckList','B':'Background Verification CheckList'}" />
							 -->	
								<s:select theme="simple" name="checkListType" headerKey="S"
									onchange="return callChange();" headerValue="--Select--"
									list="#{'B':'Background Verification CheckList'}" />
							</td>
						</tr>
						<tr>
							<td width="20%"><!-- Position --> <label class="set"
								name="position" id="Position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
							:<font color="red">*</font></td>
							<td width="25%"><s:textfield name="position" size="25" /></td>
							<td width="25%">&nbsp;</td>
							<td width="25%"><s:submit cssClass="token" value="Show List"
								action="BackGroundCheck_showCheckList"
								onclick="return checkList();" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="4"><s:if test="checklistTable">

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
						<td width="10%" valign="top" class="formth" nowrap="nowrap">
						 <label class="set" name="serial.no" id="serial.no"
							ondblclick="callShowDiv(this);"> <%=label.get("serial.no")%></label>
						</td>
						<td width="30%" valign="top" class="formth" nowrap="nowrap">
						 <label class="set" name="checklistname"
							id="checklistname" ondblclick="callShowDiv(this);"> <%=label.get("checklistname")%></label>
						</td>
						<td width="20%" valign="top" class="formth"> 
						<label class="set" name="response" id="response"
							ondblclick="callShowDiv(this);"> <%=label.get("response")%></label>
						</td>
						<td width="30%" valign="top" class="formth"> 
							<label class="set" name="Listcomments" id="Listcomments"
							ondblclick="callShowDiv(this);"><%=label.get("Listcomments")%></label>
						</td>

						<td width="10%" class="formth" colspan="1"><label class="set"
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
							<td class="sortabletd" width="5%" nowrap="nowrap"><%=p%><s:hidden
								name="checkListitemcode" /></td>

							<td class="sortabletd" width="15%"><s:property
								value="checkListName" />&nbsp;</td>

							<td class="sortabletd" width="15%"><s:select
								list=" #{'S':'Satisfied','N ':'Not Satisfied','NA':'Not Applicable'}"
								name="checkListresponce" />&nbsp;</td>
							<td class="sortabletd" width="25%" nowrap="nowrap"><s:textarea
								name="checkListComments" id='<%="checkListComments"+z%>'
								cols="20" rows="2" />&nbsp;&nbsp;<img
								src="../pages/images/zoomin.gif" height="12" align="absmiddle"
								width="12" theme="simple"
								onclick="javascript:callWindow('<%="checkListComments"+z%>','Listcomments','false','500','500');"></td>

							<td width="30%">
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


			<td width="100%" colspan="4">
			<table width="100%" border="0" align="center" cellpadding="0"
				class="formbg" cellspacing="0">
				<tr>
					<td width="20%"><!--  Overall Background Check List Comments-->
					<label class="set" name="overallcomments" id="overallcomments"
						ondblclick="callShowDiv(this);"><%=label.get("overallcomments")%></label>
					:</td>
					<td width="75%"><s:textarea name="overallComments" cols="50"
						rows="3" />&nbsp;&nbsp; <img src="../pages/images/zoomin.gif"
						height="12" align="absmiddle" width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_overallComments','overallcomments','false','500','500');"></td>
				</tr>

				<tr>

					<td><s:hidden name="buttonpan">
					</s:hidden>


					<div id="saveForInDoor"></div>

					<div id="forwardForOutDoor"></div>
					</td>
				</tr>
			</table>
			<s:hidden name="Chkreqcode"></s:hidden> <s:hidden
				name="Chkcandidatecode"></s:hidden> <s:hidden name="Chkoffercode" /> 
			</td>
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

</s:form>

<script>
callForIndoor();
function callForIndoor()
{
  document.getElementById('outSourceAgency1').style.display='none';
  document.getElementById('outSourceAgency2').style.display='none';
  try {
  
  if(document.getElementById('paraFrm_outsourceAgencyName').value!="")
  {
  
  	document.getElementById('bgCheckType1').checked=true;
  	document.getElementById('outSourceAgency1').style.display='';
  	document.getElementById('outSourceAgency2').style.display='';
  }
  else
   	document.getElementById('bgCheckType2').checked=true;
   }
   catch(e)
   {
   
   	document.getElementById('bgCheckType1').checked=true;
   }
  
    
  
 
}


	function enableOutsource(){ 
        document.getElementById('saveForInDoor').style.display='none';
        document.getElementById('forwardForOutDoor').style.display='';
        document.getElementById('outSourceAgency2').style.display='';
        document.getElementById('outSourceAgency1').style.display='';
        	
    	}
    	
    	
    	function enableIndoorSource()
    	{   document.getElementById('saveForInDoor').style.display='';
            document.getElementById('forwardForOutDoor').style.display='none';
            document.getElementById('outSourceAgency1').style.display='none';
            document.getElementById('outSourceAgency2').style.display='none';
    	}


      function checkList()
       {
       var listtype=document.getElementById('paraFrm_checkListType').value;
       if(document.getElementById('paraFrm_checkListType').value!="S")
		{ 
		document.getElementById('paraFrm_Chkstatus').value="0"; 
		return true;
		}
		else
		{ 
		alert("Please Select Check List Type");
		  return false;
		}
       }
var fieldName = ["paraFrm_candidateCode","paraFrm_reqName","paraFrm_checkListType"];
//var lableName = ["Candidate Name","Requisition Code","Select Check List Type"];
var lableName = ["candidate.name","requi.code","CheckListType"];
var badflag = ["select","select","select"];


   
function addnewFun()
{
	document.getElementById('paraFrm').action="BackGroundCheck_addNew.action";
	document.getElementById('paraFrm').submit();
}



function saveFun()
{ 

 if(!validateBlank(fieldName,lableName,badflag))
        return false;  
        
          var listtype=document.getElementById('paraFrm_checkListType').value;
       if(document.getElementById('paraFrm_checkListType').value=="S")
		{ 
		alert("Please select Check List Type");
		  return false;
		
		}
		
       
      if(document.getElementById('paraFrm_chkLength').value=="" || document.getElementById('paraFrm_chkLength').value=="0"){
		alert("Please click on Show List.");
		return false;
		}
		var listlength=document.getElementById('paraFrm_chkLength').value;
 
 
		  if(document.getElementById('paraFrm_Chkstatus').value=="1")
		  {
		  var st=document.getElementById('paraFrm_checkListType').value;
		
		   try{
		  var st1=document.getElementById('paraFrm_Chkstatus1').value;
		 
		   if((st!=""&& st1!="") && st1!=st)
		 	{  
			 alert("Please click on Show List.");
		    return false;
		    }else
		    {
		    
		    }
		  }
		  catch(e)
		  {
		 
		  }
		  }  
		 
  
   var filedlength;
      for(var i=0;i<listlength;i++)
		{
		
		  filedlength=document.getElementById('checkListComments'+i).value;
		  
		  if(filedlength !="" && filedlength.length>500)
		  {
		   alert("Maximum length of "+document.getElementById('Listcomments').innerHTML +" is 500 characters.");
		   document.getElementById('checkListComments'+i).focus();
		   return false;
		  }
      }
      
        filedlength=document.getElementById('paraFrm_overallComments').value;
		  
		  if(filedlength !="" && filedlength.length>500)
		  {
		   alert("Maximum length of "+document.getElementById('overallcomments').innerHTML +" is 500 characters.");
		   return false;
		  }
                 
          var conf=confirm("Do you really want to save this record ?");
  			if(conf) 
  			{
  			    document.getElementById('paraFrm').action="BackGroundCheck_save.action";
				document.getElementById('paraFrm').submit(); 
  				return true;
  			}
	  		else
	  		{
	  			 return false;
	  		}
	  		        
    
	
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
    	
function callChange(){
	document.getElementById('paraFrm_Chkstatus').value="1"; 
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
		document.getElementById('paraFrm').action = "BackGroundCheck_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
		return true ; 
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


function sendmailtopreviousemployerFun() { 
 if(!validateBlank(fieldName,lableName,badflag)) {
 	return false;
 }
          
 var listtype=document.getElementById('paraFrm_checkListType').value;
 if(document.getElementById('paraFrm_checkListType').value=="S") { 
		alert("Please select Check List Type");
		return false;
 }
		
 if(document.getElementById('paraFrm_chkLength').value=="" || document.getElementById('paraFrm_chkLength').value=="0") {
		alert("Please click on Show List.");
		return false;
 }

 var listlength=document.getElementById('paraFrm_chkLength').value;
 if(document.getElementById('paraFrm_Chkstatus').value=="1") {
	 var st=document.getElementById('paraFrm_checkListType').value;
	 try{
		  var st1=document.getElementById('paraFrm_Chkstatus1').value;
		   if((st!=""&& st1!="") && st1!=st) {  
			 alert("Please click on Show List.");
		     return false;
		   } else {
		    
		    }
	} catch(e) {
		 
	}
 }  
  
  var filedlength;
  for(var i=0;i<listlength;i++) {
	filedlength=document.getElementById('checkListComments'+i).value;
	if(filedlength !="" && filedlength.length>500) {
	 alert("Maximum length of "+document.getElementById('Listcomments').innerHTML +" is 500 characters.");
	 document.getElementById('checkListComments'+i).focus();
	 return false;
   }
  }
      
  filedlength=document.getElementById('paraFrm_overallComments').value;
	if(filedlength !="" && filedlength.length>500) {
		   alert("Maximum length of "+document.getElementById('overallcomments').innerHTML +" is 500 characters.");
		   return false;
	}
   
 	var mailMessageWindow = window.open('', 'mailMessageWindow', 'width=800, height=400, top=150, left=0, scrollbars=yes, resizable=yes, menubar=no, toolbar=no, status=yes');	 
	document.getElementById('paraFrm').target = "mailMessageWindow";
	document.getElementById("paraFrm").action = 'BackGroundCheck_backGroundCheckMailAlert.action';
    document.getElementById("paraFrm").submit();
    document.getElementById('paraFrm').target = 'main';
}

</script>