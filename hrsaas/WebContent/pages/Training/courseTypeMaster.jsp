<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="CourseTypeMaster" id="paraFrm" validate="true"
	theme="simple">
	<s:hidden name="hiddencode"/>
	<s:hidden name="courseCode"/>
	<s:hidden name="removeUpload"/>
	<s:hidden name="dataPath"/>
	<table width="100%" border="0" align="right" class="formbg"
		cellpadding="1" cellspacing="1">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="1"
				cellspacing="1" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Course
					Creation </strong></td>
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
				<td width="80%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td width="20%">
				<div align="right"><span class="style2"><font
					color="red">*</font></span> Indicates Required</div>
				</td>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="1"
						cellspacing="1">
						<tr>
							<td width="15%"><label class="set" name="nmCourse"
								id="nmCourse" ondblclick="callShowDiv(this);"><%=label.get("nmCourse")%></label>:</td>
							<td width="85%" colspan="2" height="22"><s:textfield
								size="25" maxlength="50" name="courseName" /></td>
						</tr>

						<tr>
							<td width="15%"><label class="set" name="cType" id="cType"
								ondblclick="callShowDiv(this);"><%=label.get("cType")%></label>:</td>
							<td width="15%" colspan="1" height="22"><s:select
								cssStyle="width:100"
								list="map"
								name="courseType" /></td>
							<td width="70%"><a href="CourseSubTypeMaster_input.action"> <font
								color="blue"><u>Add New Course Type</u></font></a></td>
						</tr>
						<tr>
							<td width="15%"><label class="set" name="labelLevel"
								id="labelLevel" ondblclick="callShowDiv(this);"><%=label.get("labelLevel")%></label>:</td>
							<td width="85%" colspan="2" height="22"><s:select
								cssStyle="width:100"
								list=" #{'B':'Beginner','I':'Intermediate','E':'Expert'}"
								name="level" /></td>
						</tr>

						<tr>
							<td width="20%"><label class="set" name="certification"
								id="certification" ondblclick="callShowDiv(this);"><%=label.get("certification")%></label>
							:</td>
							<td width="80%" colspan="2" height="22"><s:checkbox
								name="isCertified" /></td>
						</tr>
						<tr>
							<td width="15%"><label class="set" name="skillAdv"
								id="skillAdv" ondblclick="callShowDiv(this);"><%=label.get("skillAdv")%></label>:</td>
							<td width="85%" colspan="2" height="22"><s:textarea
								cols="22" rows="1" name="skillAdv" /></td>
						</tr>
						<!--<tr>
							<td width="15%"><label class="set" name="visible"
								id="visible" ondblclick="callShowDiv(this);"><%=label.get("visible")%></label>:</td>
							<td width="85%" colspan="2" height="22"><s:textfield
								maxlength="10" size="25" name="visibleTo" onkeypress="numbersOnly();"/></td>
						</tr>-->
						<s:if test="isAttFile">
							<tr>
								<td>Upload File :</td>
								<td colspan="3"><s:textfield name="uploadFileName"
									readonly="true" size="30" /> <s:hidden
									name="userUploadFileName" /> <input name="Upload"
									type="button" class="token" value="Browse"
									onclick="uploadFile('uploadFileName');" />&nbsp; <input
									type="button" name="" value=" Upload File" class=" add"
									onclick="return callUpload();" /></td>
							</tr>
						</s:if>
						<s:else>
							<tr>
								<td>View File :</td>
							</tr>
						</s:else>
						<tr>
							<td colspan="4">
							<table width="100%" border="0">
								<%
									int cnt = 0;
									int cnt1 = 0;
								%>
								<s:iterator value="attchFilesList" status="stat">
									<tr>
										<td><%=++cnt%> <s:hidden name="attFileSrNo" /><s:hidden
											name="attFileName" /> <a href="#"
											onclick="callDownload('<s:property value="attFileName" />');">
										<s:property value="attFileName" /></a></td>
										<td colspan="3" width="70%" align="left"><a href="#"
											id="ctrlHide" onclick="callForRemoveUpload(<%=cnt%>);">Remove</a></td>
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
						<tr>
							<td width="15%"><label class="set" name="active" id="active"
								ondblclick="callShowDiv(this);"><%=label.get("active")%></label>:</td>
							<td width="85%" colspan="2"><s:checkbox name="isACtive" /></td>

						</tr>
						<a href="CourseTypeMaster_.action">File Upload Interceptor Example</a>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>

<script>

/*function validate(){
	var cname = document.getElementById('paraFrm_courseName').value;
	if(cname != " "){
	 alert("Please Enter "+document.getElementById('nmCourse').innerHTML.toLowerCase()+);
	}

}*/

function editFun(){	
		if(!validate()){
			
			return false;
		}
		else{
		    document.getElementById('paraFrm').target = "_self";
  	 		document.getElementById('paraFrm').action = 'CourseTypeMaster_save.action';
			document.getElementById('paraFrm').submit();

		}
	}
	
function saveFun(){
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'CourseTypeMaster_save.action';
		document.getElementById('paraFrm').submit();
	}

function resetFun(){
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'CourseTypeMaster_reset.action';
		document.getElementById('paraFrm').submit();
	}

function uploadFile(fieldName) {
		var path = document.getElementById('paraFrm_dataPath').value;
		alert(path);
		window.open('<%=request.getContextPath()%>/pages/common/uploadFile.jsp?path='+ path + '&field=' + fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');		
	} 
	
function callUpload(){
		var uploadFile = document.getElementById('paraFrm_uploadFileName').value;		
		if(uploadFile==""){
			alert("Please Upload file");
			return false;
		}
		document.getElementById('paraFrm').action = 'CourseTypeMaster_addMultipleFiles.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
		return true;
	}
function callForRemoveUpload(id)
    {
    	var conf=confirm("Are you sure !\n You want to Remove this record ?");
		if(conf){
	  		document.getElementById('paraFrm_removeUpload').value=id;
	  		document.getElementById('paraFrm').target="_self";
	  		document.getElementById("paraFrm").action="CourseTypeMaster_removeUploadFile.action";
			document.getElementById("paraFrm").submit();
		}	
    }
    
function callDownload(templateName) {
	  	document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = 'CourseTypeMaster_viewUploadedFile.action?templateName=' + templateName;
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
		
</script>