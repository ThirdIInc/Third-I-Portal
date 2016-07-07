<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript">
var records = new Array();
</script>

<s:form action="RulesAndPolicyMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<input type="hidden" name="fieldName" id="paraFrm_fieldName">
	<s:hidden name="path" value="%{getText('data_path')}" id="pathFld" />

	<s:hidden name="rulesAndPolicyID" />

	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">

				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Rules
					And Policy Master</strong></td>

					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>

					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>




		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">


				<tr>
					<td><label class="set" name="type.of.category"
						id="type.of.category" ondblclick="callShowDiv(this);"><%=label.get("type.of.category")%></label><font
						color="red">*</font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						cssStyle="width:150" name="categoryType"
						list="#{'R':'Rule ','P':'policy'}" /></td>
				</tr>

				<tr>
					<td><label class="set" name="type.of.committee"
						id="type.of.committee" ondblclick="callShowDiv(this);"><%=label.get("type.of.committee")%></label><font
						color="red">*</font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						cssStyle="width:150" name="committeeType"
						list="#{'H':'Health & Safety ','S':'Sexual Harassment'}" /></td>
				</tr>

				<tr>
					<td><label class="set" name="Name" id="Name"
						ondblclick="callShowDiv(this);"><%=label.get("Name")%></label><font
						color="red">*</font> :</td>



					<td><s:textfield size="25" theme="simple" name="name" /></td>
				</tr>

				<tr>
					<td><label name="details" class="set" id="details"
						ondblclick="callShowDiv(this);"><%=label.get("details")%>
					</label><font color="red">*</font>:</td>

					<td colspan="4"><s:textarea name="details" cols="68" rows="3"
						onkeyup="callLength('addCnt');" /><img
						src="../pages/images/zoomin.gif" height="12" align="absmiddle"
						id='ctrlHide' width="12" theme="simple"
						onclick="javascript:callWindow('paraFrm_details','details','','2000','2000');">
					<td id='ctrlHide'>Remaining chars<s:textfield name="addCnt"
						readonly="true" size="5"></s:textfield></td>



				</tr>
				<%
					int x = 0;
				%>
				<tr>
					<td><label class="set" name="communication.language"
						id="communication.language" ondblclick="callShowDiv(this);"><%=label.get("communication.language")%></label><font
						color="red"></font> :</td>

					<td><input type="hidden" name="hidCommCode" /> <s:checkbox
						name="engFlag" onclick="setEnglish();" />&nbsp;English <s:hidden
						name="eng" /><s:checkbox name="hindiFlag" onclick="setHindi(); " />&nbsp;Hindi
					<s:hidden name="hin" /><s:checkbox name="marathiFlag"
						onclick="setMarathi(); " />&nbsp;Marathi <s:hidden name="mar" /><s:checkbox
						name="othersFlag" onclick="showOthersFlag();" />&nbsp;Others</td>
				</tr>
				<tr>
					<td></td>
					<td><span id="others"><s:textfield
						name="othersLanguage" size="40" onkeypress="" /></span></td>


				</tr>

				<tr>
					<td><label class="set" name="communication.medium"
						id="communication.medium" ondblclick="callShowDiv(this);"><%=label.get("communication.medium")%></label><font
						color="red"></font> :</td>

					<td><s:checkbox name="noticeBoardFlag" onclick="setNotice();" />&nbsp;Notice
					Board <s:hidden name="notice" /> <s:checkbox name="circularFlag"
						onclick="setCircular();" />&nbsp;Circular <s:hidden
						name="circular" /><s:checkbox name="othersMediumFlag"
						onclick="showOthersMediumFlag();" />&nbsp;Others</td>
				</tr>
				<tr>
					<td></td>
					<td><span id="othersMed"><s:textfield
						name="othersMedium" size="40" onkeypress="" id="othersMedium"/></span></td>


				</tr>
				<tr>
					<td width="20%"><label class="set" name="upload.document"
						id="upload.document" ondblclick="callShowDiv(this);"><%=label.get("upload.document")%></label>:</td>
					<td><s:textfield name="uploadLocFileName" readonly="true"
						size="40" /></td>
					<td colspan=""><input name="Upload" type="button"
						class="token" value="Browse"
						onclick="uploadFile('uploadLocFileName');" />&nbsp; <input
						type="button" name="Add Proof" value="Upload" class="token"
						onclick="return callUpload();" /></td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<%
							int cnt = 0;
								int cnt1 = 0;
						%>
						<s:iterator value="ittUploadList">
							<a href="#"
								onclick=" showproofname('<s:property value='ittproofName' />');">
							<s:hidden name="ittproofName" /> <s:property
								value="ittproofName" /><br>
							</a>
						</s:iterator>
						&nbsp;
						</td>
						<s:iterator value="proofList">
							<tr>
								<td width="3%"><%=++cnt%>. <s:hidden name="proofSrNo" /></td>
								<td width="40%"><a href="#"
									onclick="showproofname('<s:property value="proofName" />');">
								<s:hidden name="proofName" /> <s:property value="proofName" />
								</a></td>
								<td colspan="2"><a href="#"
									onclick="callForRemoveUpload(<%=cnt%>);">Remove Proof</a></td>
							</tr>
							<%
								cnt1 = cnt;
							%>
						</s:iterator>
						<%
							cnt1 = 0;
						%>
					</table>
					</td>
				</tr>

			</table>
			</td>
		</tr>


		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

<s:hidden name="checkRemoveUpload" />
	</table>

</s:form>


<script>


showOthersFlag();
showOthersMediumFlag();
onLoad();

function onLoad()
{
	document.getElementById('paraFrm_othersMed').style.display = 'none';
}

function showOthersMediumFlag(){
		if(document.getElementById('paraFrm_othersMediumFlag').checked){
			document.getElementById('othersMed').style.display = '';
			
		}
		else{
			document.getElementById('othersMed').style.display = 'none';
			
		}
	}
	

	function setEnglish(){
		 	if(document.getElementById('engFlag').checked==true)
		 		document.getElementById('paraFrm_eng').value="E";
		 	else
		 		document.getElementById('paraFrm_eng').value="N";
		 }

	function setHindi(){
		 	if(document.getElementById('hindiFlag').checked==true)
		 		document.getElementById('paraFrm_hin').value="H";
		 	else
		 		document.getElementById('paraFrm_hin').value="N";
		 }
		 
	function setMarathi(){
		 	if(document.getElementById('marathiFlag').checked==true)
		 		document.getElementById('paraFrm_mar').value="M";
		 	else
		 		document.getElementById('paraFrm_mar').value="N";
		 }
	
	function setNotice(){
		 	if(document.getElementById('noticeBoardFlag').checked==true)
		 		document.getElementById('paraFrm_notice').value="N";
		 	else
		 		document.getElementById('paraFrm_notice').value="";
		 }

	function setCircular(){
		 	if(document.getElementById('circularFlag').checked==true)
		 		document.getElementById('paraFrm_circular').value="C";
		 	else
		 		document.getElementById('paraFrm_circular').value="";
		 }
	

 	function showOthersFlag(){
		if(document.getElementById('paraFrm_othersFlag').checked){
			document.getElementById('others').style.display = '';
			
		}
		else{
			document.getElementById('others').style.display = 'none';
			document.getElementById('paraFrm_others').value = ''; 
		}
	}
	
	
	
	function uploadFile(fldId)
{

	var path=document.getElementById('pathFld').value+"\\TMS\\<%=session.getAttribute("session_pool")%>\\Tickets";
	window.open('<%=request.getContextPath()%>/pages/recruitment/uploadResume.jsp?path='+path+'&field='+fldId,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
}
	
	function callUpload()
{
	try
	{	
		var uploadFile = document.getElementById('paraFrm_uploadLocFileName').value;	
	
		if(uploadFile=="")
		{		
			alert("Please Select Document to upload.");
			return false;
		}	
	}
	catch(e)
	{
		alert("Error Occured in callUpload===================> "+e);		
	}
	document.getElementById("paraFrm").target ="";
	document.getElementById("paraFrm").action="RulesAndPolicyMaster_addMultipleProof.action" ;
	document.getElementById("paraFrm").submit();
} 
 

function callForRemoveUpload(id)
{
   var conf=confirm("Are you sure !\n You want to Remove this record ?");
	if(conf)
	{
		document.getElementById('paraFrm_checkRemoveUpload').value=id;
		document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="RulesAndPolicyMaster_removeUploadFile.action";
		document.getElementById("paraFrm").submit();
	}	
}
 function showproofname(fileName)
	{
	 	document.getElementById('paraFrm').target = "_blank";
		document.getElementById('paraFrm').action = "RulesAndPolicyMaster_viewAttachedProof.action?fileName="+fileName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	} 
 
	
	
  	function saveFun() {
  	
  	
  
				
		
		var categoryType=document.getElementById('paraFrm_categoryType').value;
  		
  		
  	if(categoryType==""){
			alert("Please select "+document.getElementById('type.of.category').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_categoryType').focus();
			return false;
	}
	

  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'RulesAndPolicyMaster_save.action';
		document.getElementById('paraFrm').submit();
      	
		
  	
  			}
  	
  		function resetFun() {
		
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'RulesAndPolicyMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="RulesAndPolicyMaster_back.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	function deleteFun() 
{
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'RulesAndPolicyMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	
	
}
		function editFun() {
		document.getElementById('paraFrm').action = 'RulesAndPolicyMaster_edit.action';
		document.getElementById('paraFrm').submit();
		
	}	
	
	function callLength(type){ 
		
		 if(type=='addCnt'){
					
					/*alert("i am in calllength method>>>>>>");*/
					
					var add =document.getElementById('paraFrm_details').value;
					var remain = 2000 - eval(add .length);
					document.getElementById('paraFrm_addCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_details').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_details').style.background = '#FFFFFF';
				
				}
				} 
  	</script>


