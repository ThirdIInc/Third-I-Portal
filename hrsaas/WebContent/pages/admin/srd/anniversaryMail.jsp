<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>
<s:form action="BirthdayMail" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
			<s:hidden name="flag" />
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Anniversary
					Mail </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><input type="button" class="token"
						value="    Send Anniversary Mail" onclick="return calculate();" />
						<input type="button" class="token"
						value="    Back"  onclick="return backhomepage();" /></td>
					
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td width="15%" colspan="1" height="22"><strong
						class="text_head"><label class="set" name="send.mail.to"
						id="send.mail.to" ondblclick="callShowDiv(this);"><%=label.get("send.mail.to")%></label></strong></td>
				</tr>
				<tr>
					<td width="15%" colspan="1" height="22"><label class="set"
						name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
					<td width="35%" colspan="1" height="22"><s:select
						theme="simple" name="sendMail.departmentName" headerKey="-1"
						headerValue="   --Select--   " cssStyle="width:170px"
						list="#{'o':'Only his/her Dept.','M':'Select Multiple Department'}"
						onchange="return callDepartment();" /></td>
					<td colspan="1" width="50%">
					<div id="div2">
					<table width="100%">
						<tr>
							<s:hidden name="departmentCode" />
							<td class="formtext" align="left"><s:a href="#"
								onclick="call('departmentCode', 'dp')">
								click here to select <label class="set"
						name="department" id="department1" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
						</s:a></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td width="15%" colspan="2" align="center"><Strong>AND/OR</strong></td>
				</tr>
				<tr>
					<td width="15%" colspan="1" height="22"><label class="set"
						name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
					<td width="35%" colspan="1" height="22"><s:select
						theme="simple" name="divName" headerKey="-1"
						headerValue="   --Select--   " cssStyle="width:170px"
						list="#{'o':'Only his/her Div.','M':'Select Multiple division'}"
						onchange="return callDivision();" /></td>
					<td colspan="1" width="50%">
					<div id="divisionDiv">
					<table width="100%">
						<tr>
							<s:hidden name="divCode" />
							<td class="formtext" align="left"><s:a href="#"
								onclick="call('divCode', 'dv')">
								click here to select <label class="set"
						name="division" id="division1" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:
						</s:a></td>
						</tr>
					</table>
					</div>
					</td>
				</tr>
				<tr>
					<td width="15%" colspan="2" align="center"><Strong> AND/OR</strong></td>
				</tr>
				<tr>

					<td width="15%" colspan="1" height="22"><label class="set"
						name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
					<td width="35%" colspan="1" height="22"><s:select
						theme="simple" name="branch" headerKey="-1"
						headerValue="   --Select--   " cssStyle="width:170px"
						list="#{'o':'Only his/her Branch','M':'Select Multiple Branch'}"
						onchange="return callBranch();" /></td>
					<td colspan="1" width="50%">
					<div id="branchDiv">
					<table width="100%">
						<tr>
							<s:hidden name="branchCode" />
							<td class="formtext" align="left"><s:a href="#"
								onclick="call('branchCode', 'br')">
								click here to select <label class="set"
						name="branch" id="branch1" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
						</s:a></td>

						</tr>
					</table>
					</div>
					</td>

				</tr>
				<tr>
					<td width="15%" colspan="2" align="center"><Strong>OR</strong></td>
				</tr>
				<tr>

					<td width="15%" colspan="1" height="22"><label class="set"
						name="entire.organization" id="entire.organization"
						ondblclick="callShowDiv(this);"><%=label.get("entire.organization")%></label>:</td>
					<td width="85%" colspan="2" nowrap="nowrap"><input
						type="checkbox" class="checkbox" name="confChk" id="confChk"
						onclick="check();" /></td>
				</tr>
				<tr>
					<td width="15%" colspan="2" align="center"><Strong>OR</strong></td>
				</tr>
				<tr>

					<td width="25%" colspan="1"><label class="set" name="employee"
						id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
					<td width="75%" colspan="2" height="22"><s:textfield
								name="token" theme="simple" size="10"
								readonly="true" /><s:textfield size="28"
						theme="simple" maxlength="90" name="employee" /><img
						src="../pages/common/css/default/images/search2.gif" width="16"
						height="15"
						onclick="javascript:callsF9(500,325,'BirthdayMail_f9sendemp.action');">
					<s:hidden name="empid" /></td>

				</tr>

				<tr>
					<td colspan="1" width="20%"><label name="template"
						id="template" ondblclick="callShowDiv(this);"><%=label.get("template")%></label>
					:</td>
					<td colspan="2" width="50%"><s:textfield size="28"
						name="tempName" readonly="true" /> <s:hidden name="tempCode" />
					<img src="../pages/common/css/default/images/search2.gif"
						width="16" height="15"
						onclick="javascript:callsF9(500,325,'BirthdayMail_f9anniversarymailTemplate.action');">
					&nbsp;&nbsp;<input type="button" name="preview" class="token"
						value="Preview" onclick="previewTemplate();"></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td class="formth" align="center"><label class="set"
						name="Sr.No." id="Sr.No." ondblclick="callShowDiv(this);"><%=label.get("Sr.No.")%></label></td>
					<td class="formth" align="center"><label class="set"
						name="list.Of.anniversary.employee" id="list.Of.anniversary.employee"
						ondblclick="callShowDiv(this);"><%=label.get("list.Of.anniversary.employee")%></label></td>
					<td class="formth"> &nbsp;</td>
				</tr>


				<%!int d = 0;%>

				<%
						int i = 0;
						%>
				<s:iterator value="arrlist">
					<tr>
						<td width="10%" class="sortabletd"><%=++i%><s:hidden name="srNo" /></td>
						<td width="80%" class="sortabletd"><s:property value="empName" /><input
							type="hidden" name="hdeleteCode" id="hdeleteCode<%=i%>"
							value='<s:property value="hdeleteCode"/>' /></td>
						<s:hidden name="empCode" value="%{empCode}" />
						<td nowrap="nowrap" width="10%" class="sortabletd"><input type="checkbox"
							class="checkbox" id="confChk<%=i%>" name="confChk"
							value='<s:property value="confChk"/>'
							onclick="setEmpCode('<s:property value="empCode"/>',<%=i%>);" /></td>
					</tr>
				</s:iterator>
			</table>
			<%
						d = i;
						%> <%
				} catch (Exception e) {
				}
			%>
			</td>
		</tr>
	</table>
</s:form>

<script>

document.getElementById('paraFrm_divName').value='-1';
 document.getElementById('paraFrm_divCode').value='';
document.getElementById('paraFrm_sendMail_departmentName').value='-1';
document.getElementById('paraFrm_departmentCode').value='';
 document.getElementById('paraFrm_branch').value='-1';
 document.getElementById('paraFrm_branchCode').value='';
document.getElementById('div2').style.display='none';
document.getElementById('divisionDiv').style.display='none';
document.getElementById('branchDiv').style.display='none';
	function callDepartment()
{
var isvar = document.getElementById('paraFrm_sendMail_departmentName').value;

document.getElementById('confChk').checked = false;
document.getElementById('confChk').value = "none";

document.getElementById('paraFrm_empid').value = '';
document.getElementById('paraFrm_employee').value = '';
document.getElementById('paraFrm_token').value = '';

if(isvar=="M" )
{
  document.getElementById('div2').style.display='';
   }else {
  
	document.getElementById('div2').style.display='none';
				}

}

function callDivision(){
	var isvar = document.getElementById('paraFrm_divName').value;
	
	  document.getElementById('confChk').checked = false;
     document.getElementById('confChk').value = "none";
     document.getElementById('paraFrm_empid').value = '';
document.getElementById('paraFrm_employee').value = '';
  document.getElementById('paraFrm_token').value = '';   
	
	if(isvar=="M" ){
	   document.getElementById('divisionDiv').style.display='';
	 	 
	}else {
		 document.getElementById('divisionDiv').style.display='none';
	}
}

function callBranch()
{
var isvar = document.getElementById('paraFrm_branch').value;
/**
    document.getElementById('div2').style.display='none';
    document.getElementById('divisionDiv').style.display='none'
    
    document.getElementById('paraFrm_sendMail_departmentName').value='-1';
    document.getElementById('paraFrm_departmentCode').value='';
    document.getElementById('paraFrm_divName').value='-1';
     document.getElementById('paraFrm_divCode').value='';
     */
         document.getElementById('confChk').checked = false;
         document.getElementById('confChk').value = "none";
         

document.getElementById('paraFrm_empid').value = '';
document.getElementById('paraFrm_employee').value = '';
document.getElementById('paraFrm_token').value = '';
if(isvar=="M" )
{
  document.getElementById('branchDiv').style.display='';
  }
  else {
		document.getElementById('branchDiv').style.display='none';
	
	}
	}

function check()
{

if( document.getElementById('confChk').checked ==true)
{
 
  document.getElementById('div2').style.display='none';

    document.getElementById('paraFrm_sendMail_departmentName').value='-1';
    document.getElementById('divisionDiv').style.display='none';
    document.getElementById('paraFrm_divName').value='-1';
    document.getElementById('branchDiv').style.display='none';

    document.getElementById('paraFrm_branch').value='-1';
    document.getElementById('paraFrm_empid').value = '';
document.getElementById('paraFrm_employee').value = '';
 document.getElementById('paraFrm_token').value = ''; 	
}
}
function call(code, actionName){
	window.open('','new','top=100,left=300,width=400,height=400,scrollbars=yes,status=no,resizable=no');
	document.getElementById("paraFrm").target="new";
	document.getElementById("paraFrm").action='BirthdayMail_deptlist.action?code='+code+'&actionName='+actionName;
	document.getElementById("paraFrm").submit();
	document.getElementById("paraFrm").target="main";
}
chkTest();
function chkTest()
{
	var emp_id=document.getElementById("paraFrm_empid").value;
	var id='<%=d %>';
	//alert('id'+id);
	var flag = false;
	if(emp_id!='')
		flag = true;
	else
		flag = false;
		for(i=1;i<=id;i++)
		{
			try
			{
				var aa=document.getElementById('hdeleteCode'+i).value;
				if(!aa=='')
				{
					document.getElementById('confChk'+i).checked =flag;
				}
			}catch(e){}
		}
}

function setEmpCode(id,i){


 if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
	  
	   
   }
   function calculate()
	{
	var department=document.getElementById('paraFrm_sendMail_departmentName').value;
	var division=document.getElementById('paraFrm_divName').value;
	var branchname=document.getElementById('paraFrm_branch').value;
	var name=document.getElementById('paraFrm_employee').value;
	if((department=='-1')&&(division=='-1')&&(branchname=='-1')&&(name=='')&&(document.getElementById('confChk').checked ==false))
	{
		alert(' Please Select atleast one criteria from '+document.getElementById('send.mail.to').innerHTML.toLowerCase()+' option');
		return false;
	}
	
	var dept=document.getElementById('paraFrm_departmentCode').value;
	var div=document.getElementById('paraFrm_divCode').value;
	var branch=document.getElementById('paraFrm_branchCode').value;
	
	if(department=='M')
	{
		if(dept=='')
		{
		alert('please select atleast one  department from multiple department list');
		return false;
		}
	}
	if(division=='M')
	{
		if(div=="")
		{
		alert('please select atleast one  division from  multiple division list');
		return false;
		}
	}
	if(branchname=='M')
	{
		if(branch=='')
		{
		alert('please select atleast one  branch from  multiple branch list');
		return false;
		}
	}
	 var flag='<%=d %>';
	 if(flag=='0'){
		 alert( "No Annivesary days have been found on today's date");
		 return false;
	 }
	 
	
	
	 if(chk()){
		 var con=confirm('Do you want to send the message? ');
		 if(con){
			 var flag='<%=d %>';
			 for(var a=1;a<=flag;a++){	
				document.getElementById('confChk'+a).checked = false;
			 }
		   	document.getElementById('paraFrm').action="BirthdayMail_sendAnniversaryMailList.action";
		    document.getElementById('paraFrm').submit();
	    }
	    else
	    { 
		    var flag='<%=d %>';
		  	for(var a=1;a<=flag;a++){	
				document.getElementById('confChk'+a).checked = false;
				document.getElementById('hdeleteCode'+a).value="";
			}
	    	 return false;
	 	}
	
	}else {
		 alert('Please select atleast one record form Anniversary List');
		 return false;
	}
}
	
	function chk(){
	var flag='<%=d %>';
	
	  for(var a=1;a<=flag;a++){	
	   if(document.getElementById('confChk'+a).checked == true)
	   {	
	  // alert('11');  
	    return true;
	   }	   
	  }
	  return false;
	}
function previewTemplate()
{
	tempCode = document.getElementById('paraFrm_tempCode').value;
	if(tempCode == "")
	{
		alert("Please select "+document.getElementById('template').innerHTML.toLowerCase());
		return false;
	}
	window.open("<%=request.getContextPath() %>/AppStudio/AnniversaryTemplate_setTemplateDataForPreview.action?tempCode="+tempCode,"","height=550,width=700,scrollbars=yes");
}
function  backhomepage()
{
 document.getElementById('paraFrm').action="BirthdayMail_homepage.action";
 document.getElementById('paraFrm').submit();
}
</script>


