<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<%@page import="java.util.HashMap"%>
<script type="text/javascript">var specArray = new Array();</script>
<script language="JavaScript" type="text/javascript" src="..pages/common/include/javascript/sorttable.js"></script>

<script type="text/javascript" src="../pages/common/include/javascript/jquery-1.2.2.pack.js"></script>

<script type="text/javascript" src="../pages/common/include/javascript/ddaccordion.js"></script>

<script type = "text/javascript" src = "../pages/common/datetimepicker.js"></script>


<s:form action="JobDescriptionMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
		<s:hidden name="show"  />
		 <s:hidden name="hiddenCode" />
		 <s:hidden name="editFlag"/><s:hidden name="flagView"/>

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
					<td width="93%" class="txt"><strong class="text_head">Job
					Description</strong></td>
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
							<td width="78%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp"/></td>
							<td width="22%">
							<div align="right"><span class="style2"><font color="red">*</font></span> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" width="100%">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td colspan="3" width="100%">
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td colspan="3" width="100%" class="formhead"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>
								
								<tr>
									<td width="22%" height="22" class="formtext"><label
										class="set" name="jdesc.edate" id="jdesc.edate" ondblclick="callShowDiv(this);"><%=label.get("jdesc.edate")%></label>
										<font color="red">*</font>
									:</td>
									<td width="34%" height="22"><s:hidden name="jdMaster.jdCode" />
											<s:textfield name="jdMaster.jdEffDate" size="30" maxlength="10"
											onkeypress="return numbersWithHiphen();"  readonly="false"/>
											
										<s:a
											href="javascript:NewCal('paraFrm_jdMaster_jdEffDate','DDMMYYYY');">
											<img src="../pages/images/Date.gif" class="iconImage"
												height="16" align="absmiddle" width="16">
										</s:a>
									</td>
									
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>


								<tr>
									<td width="22%" height="22" class="formtext">
									<label class="set" name="jdesc.jname" id="jdesc.jname"
										ondblclick="callShowDiv(this);"><%=label.get("jdesc.jname")%></label>
										<font color="red">*</font>
									:</td>
									<td width="34%" height="22">
										<s:textfield size="30" maxlength="50"
											name="jdMaster.jdDescName" readonly="false" />
									</td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>

								<tr>
									<td  height="22" valign="top" class="formtext" nowrap="nowrap"><label
										class="set" name="jdesc.jdesc" id="jdesc.jdesc" ondblclick="callShowDiv(this);"><%=label.get("jdesc.jdesc")%></label>
									:</td>
									<td height="22" nowrap="nowrap">
									<s:textarea name="jdMaster.jdDesc" rows="4" cols="32"
											readonly="false" onkeyup="callLength('jdMaster_jdDesc','descCnt1','1000');"/>
									
									<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_jdMaster_jdDesc','jdesc.jdesc','','paraFrm_descCnt1','1000');" >
										&nbsp;Remaining chars <s:textfield name="descCnt1"
											readonly="true" size="5"></s:textfield></td>
											<td height="22" class="formtext"></td>
									<td height="22">&nbsp;</td>
									
								</tr>
								
								

								<tr>
									<td width="22%" height="22" valign="top" class="formtext" nowrap="nowrap"><label
										class="set" name="jdesc.randr" id="jdesc.randr" ondblclick="callShowDiv(this);"><%=label.get("jdesc.randr")%></label>
										<font color="red">*</font>
									:</td>
									<td height="22" nowrap="nowrap">
									<s:textarea name="jdMaster.jdRoleDesc" rows="4" cols="32"
											readonly="false" onkeyup="callLength('jdMaster_jdRoleDesc','descCnt','1000');" />
									
											<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_jdMaster_jdRoleDesc','jdesc.randr','','paraFrm_descCnt','1000');" >
										&nbsp;Remaining chars <s:textfield name="descCnt"
											readonly="true" size="5"></s:textfield>
									</td>
									
								</tr>

						<!-- ADDED BY DHANASHREE BEGINS -->
						<tr>
							<td width="22%" height="22" class="formtext"><label
								class="set" name="rec.days" id="rec.days"
								ondblclick="callShowDiv(this);"><%=label.get("rec.days")%></label>

							:</td>
							<td width="34%" height="22"><s:textfield size="30"
								maxlength="50" name="recruitmentDays" readonly="false" onkeypress="return numbersOnly();" /></td>
							<td width="27%" height="22" class="formtext">&nbsp;</td>
							<td width="18%" height="22">&nbsp;</td>

						</tr>
						<tr>
							<td width="22%" colspan="1"><label class="set" name="grade"
								id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
							:</td>
							<td width="34%" colspan="1"><s:textfield size="30"
								name="gradeName" readonly="true" /> <s:hidden name="gradeId" />
							<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="14" width="14" theme="simple"
								onclick="javascript:callsF9(500,325,'JobDescriptionMaster_f9gradeName.action');" />
							</td>

						</tr>
						<!-- ADDED BY DHANASHREE ENDS -->


						<tr>
									<td width="22%" height="22" class="formtext" nowrap="nowrap"><label
										class="set" name="jdesc.statu" id="jdesc.statu" ondblclick="callShowDiv(this);"><%=label.get("jdesc.statu")%></label>:
									</td>
									<td width="34%" height="22">
										<s:select name="jdMaster.status" disabled="false"
											list="#{'A':'Active','D':'Deactive'}" 
											cssStyle="width:180;z-index:5;"
											/>
									</td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>
								<tr>

									<td colspan="3" align="center"><img
										src="../pages/common/css/default/images/space.gif" width="5"
										height="7" /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

			

			
		<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td colspan="3" align="Right" width="22%">
					
					</td>		
				</tr>	
		</table>
</s:form>
<script> 

function saveFun()
{
try{
	var f9Fields= ["paraFrm_jdMaster_jdDescName",'paraFrm_jdMaster_jdRoleDesc'];
	var fieldName=["paraFrm_jdMaster_jdDescName",'paraFrm_jdMaster_jdRoleDesc'];
	var lableName=["jdesc.jname","jdesc.randr"];
	var type=["enter","enter"];
	
	
	if(document.getElementById('paraFrm_jdMaster_jdEffDate').value=="")
	{
		alert("Please enter/select "+document.getElementById("jdesc.edate").innerHTML.toLowerCase());
		return false;	
	}
	if(!validateDate('paraFrm_jdMaster_jdEffDate','jdesc.edate'))
    return false;  
    
	if(!validateBlank(fieldName,lableName,type))
	return false;
    var desc =	document.getElementById('paraFrm_jdMaster_jdDesc').value;
	var rolesandres=document.getElementById('paraFrm_jdMaster_jdRoleDesc').value;
	if(desc != "" && desc.length > 1000){
		alert("Maximum length of "+ document.getElementById('jdesc.jdesc').innerHTML.toLowerCase()+" is 1000 characters.");
		return false;
    }    
    
	if(rolesandres != "" && rolesandres.length > 1000){
		alert("Maximum length of "+ document.getElementById('jdesc.randr').innerHTML.toLowerCase()+" is 1000 characters.");
		return false;
	}
	
	}
	catch(e)
	{
	}
	

	document.getElementById('paraFrm').action="JobDescriptionMaster_save.action";
	document.getElementById('paraFrm').submit();
	return true;
	
}
function cancelFun(){
    	document.getElementById('paraFrm').action="JobDescriptionMaster_cancelFirst.action";
        document.getElementById("paraFrm").submit();
    
}
//For Report Button

function reportFun()
{
	document.getElementById('paraFrm').action="JobDescriptionMaster_report.action";
    document.getElementById("paraFrm").submit();
}
function callingStage(stage){
		document.getElementById('paraFrm').action = "JobDescriptionMaster_cancel.action?callingStage="+stage;
		document.getElementById('paraFrm').submit();
	}
	
	
	
	 
	 function deleteRecord(){
		if(!callDelete('paraFrm_jdMaster_jdDescName'))return false;
		
		else{
			document.getElementById('paraFrm').action="JobDescriptionMaster_delete.action";
		   document.getElementById('paraFrm').submit();
		}
	}
	
  function savevalidation(){
  	var f9Fields= ["paraFrm_jdMaster_jdEffDate", "paraFrm_jdMaster_jdDescName", 'paraFrm_jdMaster_jdDesc', 
  					'paraFrm_jdMaster_jdRoleDesc'];
  
    var fieldName = ['paraFrm_jdMaster_jdEffDate'];
	var lableName = ["jdesc.edate"];
	var flag = ["enter/select"];
	  
	   if(!validateBlank(fieldName,lableName,flag))
          return false;
	
	if(!validateDate("paraFrm_jdMaster_jdEffDate","<%=label.get("jdesc.edate")%>")){
		return false;
	}
	
	var fieldName1 = ['paraFrm_jdMaster_jdDescName'];
	var lableName1 = ["jdesc.jname"];
	var flag1      = ["enter"];
	
	   if(!validateBlank(fieldName1,lableName1,flag1))
          return false;
	
	var desc    =	document.getElementById('paraFrm_jdMaster_jdDesc').value;
	
	if(desc != "" && desc.length >999){
		alert("Maximum length of "+ document.getElementById('jdesc.jdesc').innerHTML.toLowerCase()+" is 999 characters.");
		return false;
    }    
	
	var fieldName2 = ['paraFrm_jdMaster_jdRoleDesc'];
	var lableName2 = ["jdesc.randr"];
	var flag2      = ["enter"];
	
	  if(!validateBlank(fieldName2,lableName2,flag2))
          return false;
	  
	
	var jobRole =	document.getElementById('paraFrm_jdMaster_jdRoleDesc').value;
  	
    if(jobRole.length >999){
 		alert("Maximum length of "+ document.getElementById('jdesc.randr').innerHTML.toLowerCase()+" is 999 characters.");
		return false;
    }    
    
    if(!f9specialchars(f9Fields))
	return false;   
 }
 
  	

	
	function callForEdit(id){
	  	document.getElementById('paraFrm_hiddenCode').value=id;
	
	   	document.getElementById("paraFrm").action="JobDescriptionMaster_calforedit.action";
	   	document.getElementById("paraFrm").target="main";
	    document.getElementById("paraFrm").submit();
   }
	
		function callForDelete1(id,i)
	   {
	   
	
	   if(document.getElementById('confChk'+i).checked == true)
	   {	  
	    document.getElementById('hdeleteCode'+i).value=id;
	   }
	   else
	   document.getElementById('hdeleteCode'+i).value="";
	   
   }
	


  </script>
