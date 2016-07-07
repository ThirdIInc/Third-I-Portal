<!-- JSP file for front end for Transfer Application -->

<%@taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="TransferApplication" validate="true" id="paraFrm"
	validate="true" theme="simple">
	
	
	 
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
    
    
    
    <tr>
			<td valign="bottom" class="txt">
			<table  width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg" >
			<tr>
			<td>
			<strong class="text_head"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="text_head">Transfer Application
			</strong></td>
			<td width="3%" valign="top" class="txt">
			<div align="right"><img
				src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
				</td>
				</tr>
				</table>
			</td>
		</tr>    
    
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" >
          <tr>
            <td width="78%">
            <s:if test="showFlag">
				</s:if><s:else>
            		<s:if test="trfApp.insertFlag">
            			<s:submit cssClass="add" action="TransferApplication_save"
					theme="simple" value="    Save " onclick="return callSave()" /></s:if>
					<s:elseif test="trfApp.updateFlag">
					<s:submit cssClass="add" action="TransferApplication_save"
					theme="simple" value="    Save " onclick="return callSave()" />
					 </s:elseif>
					 <s:if test="trfApp.viewFlag">
					  <s:if test="trfApp.generalFlag">
					<s:submit cssClass="reset" action="TransferApplication_genreset" theme="simple"
						value="     Reset    " />
				</s:if> <s:else>
					<s:submit cssClass="reset" action="TransferApplication_reset" theme="simple"
						value="     Reset  " />
				</s:else> 
				</s:if>
				<!--<s:submit cssClass="reset" action="TransferApplication_reset" theme="simple"
						value="     Reset  " />-->
				<s:if test="trfApp.deleteFlag">
				<input type="button" class="delete"
					onclick="callDelete('TransferApplication_delete.action')"
					value="     Delete " /></s:if>
					
					
				
				 <s:if test="trfApp.updateFlag">
				 <s:submit cssClass="edit" action="TransferApplication_lock" theme="simple"
						value="    Lock  " onclick="return callLock()"/>
				 </s:if>
				 <s:else>
				</s:else>
				
			
				 <s:if test="trfApp.viewFlag">
				<input type="button" class="token"	onclick="callReport('TransferApplication_report.action')"
					value=" Report " /></s:if>
				 <s:if test="trfApp.viewFlag">
          		<input type="button" class="search" value="     Search " onclick="javascript:callsF9(500,325,'TransferApplication_f9trfaction.action');"/>
				</s:if>
				</s:else>
				
				
				<input type="button" value="Back" onclick="callBack();">
				
				
				</td>
				
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
        </table>
          <label></label></td>
    </tr>
    
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
          <tr>
            <td><table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
                <tr>
                  <td colspan="5" class="formhead"><strong class="forminnerhead"><label  class = "set" name="application.details" id="application.details" ondblclick="callShowDiv(this);"><%=label.get("application.details")%></label></strong></td>
        </tr>
                <tr>
                  <td width="24%" height="22" class="formtext"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :<font color="red">*</font></td>
                  <td height="22" colspan="4"><label><s:hidden name="showFlag"></s:hidden>
                <s:hidden name="empId" theme="simple"  /><s:hidden name="level" /><s:hidden name="trfId" />
                <s:textfield name="empToken" theme="simple"  size="10"
				readonly="true" /><s:textfield 	label="%{getText('empName')}" theme="simple" name="empName" size="81" readonly="true" />
                   
                <s:if test="genLoginFlag">
                <img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="15" onclick="javascript:callsF9(500,325,'TransferApplication_f9empaction.action');">
                </s:if><s:else></s:else>      
               &nbsp;                
                </label>
                <label></label></td>
                </tr>
                
                 <tr>
                  <td height="22" class="formtext"><label  class = "set" name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
                  <td width="27%" height="22"><s:textfield	label="%{getText('rank')}" name="rank" size="29" readonly="true" />
                 </td>
                  <td width="4%" height="22">&nbsp;</td>
                  <td width="17%" class="formtext"></td>
                  <td width="28%"></td>
                </tr>
                
                
                   <tr>
                  <td height="22" class="formtext"><label  class = "set" name="application.date" id="application.date" ondblclick="callShowDiv(this);"><%=label.get("application.date")%></label> :</td>
                  <td width="27%" height="22"><s:textfield
				label="%{getText('appDt')}" name="appDt" size="29"  readonly="true"/>
                 </td>
                  <td width="4%" height="22">&nbsp;</td>
                  <td width="17%" class="formtext"> <label  class = "set" name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label> :</td>
                  <td width="28%"><s:textfield
				label="%{getText('status')}" name="status" size="26.99" readonly="true" />
                  
                 </td>
              </tr>
                
                </table>
                </tr>
                </td>
                </table>
                
                 
                </td>
                </tr>
                
                <tr>
                <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
                 <tr>
                <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2">
               
               <tr>
                  <td height="22" class="formtext"><label  class = "set" name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
                  <td width="27%" height="22"><s:hidden name="curCentId" /><s:textfield
				label="%{getText('curCent')}"  name="curCent" size="29"
				readonly="true" />
                 </td>
                  <td width="4%" height="22">&nbsp;</td>
                  <td width="17%" class="formtext"><label  class = "set" name="new.branch" id="new.branch" ondblclick="callShowDiv(this);"><%=label.get("new.branch")%></label> :</td>
                  <td width="28%"><s:hidden name="newCentId" /><s:textfield

				label="%{getText('newCent')}"  name="newCent" size="27"
				readonly="true" /><s:if test="%{centFlag}">
				</s:if><s:else>
				 <img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="15" onclick="javascript:callsF9(500,325,'TransferApplication_f9centaction.action');">
				</s:else></td>
              </tr>
              
              
               <tr>
                  <td height="22" class="formtext"><label  class = "set" name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :</td>
                  <td width="27%" height="22"><s:hidden name="curDivId" /><s:textfield
				label="%{getText('curDiv')}"  name="curDiv" size="29"
				readonly="true" />
                 </td>
                  <td width="4%" height="22">&nbsp;</td>
                  <td width="17%" class="formtext"><label  class = "set" name="new.division" id="new.division" ondblclick="callShowDiv(this);"><%=label.get("new.division")%></label> :</td>
                  <td width="28%"><s:hidden name="newDivId" /><s:textfield

				label="%{getText('newDiv')}"  name="newDiv" size="27"
				readonly="true" /> <s:if test="%{divFlag}">
				</s:if><s:else>
				 <img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="15"
				onclick="javascript:callsF9(500,325,'TransferApplication_f9divaction.action');" />
				</s:else></td>
              </tr>
               
                
               <tr>
                  	<td height="22" class="formtext"><label  class = "set" name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
                  	<td width="27%" height="22"><s:hidden name="curDeptId" /><s:textfield
					label="%{getText('curDept')}" name="curDept"
					size="29" readonly="true" />
               		</td>
               	 	<td width="4%" height="22">&nbsp;</td>
                 	<td width="17%" class="formtext"><label  class = "set" name="new.department" id="new.department" ondblclick="callShowDiv(this);"><%=label.get("new.department")%></label> :</td>
	                <td width="28%"><s:hidden name="newDeptId" /><s:textfield					
					label="%{getText('newDept')}"  name="newDept" size="27"
					readonly="true" /><s:if test="%{deptFlag}">
					</s:if><s:else>
					<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="15"
					onclick="javascript:callsF9(500,325,'TransferApplication_f9deptaction.action');" /> 
					</s:else></td>
               </tr>
                
              
              <tr>

			 <td height="22" class="formtext"><label  class = "set" name="reliever.required" id="reliever.required" ondblclick="callShowDiv(this);"><%=label.get("reliever.required")%></label> :</td>
		 	 <td width="27%" height="22">
		 	<s:if test="%{relReqFlag}">
		 	 <s:select name="relReq"
				disabled="true" list="#{' ':'Select','Y':'Yes','N':'No'}" />
		 	</s:if><s:else>
		 	 
		 	 <s:select name="relReq"
				onchange="showText();" disabled="false" list="#{' ':'Select','Y':'Yes','N':'No'}" />
			</s:else></td>
			 <td width="4%" height="22">&nbsp;</td>
			 <td width="17%" class="formtext"><label  class = "set" name="relieving.date" id="relieving.date" ondblclick="callShowDiv(this);"><%=label.get("relieving.date")%></label><font color="red">*</font> :</td>
			 <td><s:textfield
				label="%{getText('relDt')}" name="relDt" size="27" onkeypress="return numbersWithHiphen();" />
				 <s:if test="dateFlag"></s:if><s:else>
				 <s:a href="javascript:NewCal('paraFrm_relDt','DDMMYYYY');">
				 
				 
				 <img src="../pages/images/recruitment/Date.gif"  class="iconImage" height="16" align="absmiddle"	width="16" ></s:a>
				 </s:else></td>
			


		</tr>
		
		
		<tr>
			<td colspan="5" width="100%">
			<div id="div2">
			<table width="100%">

				<tr>
				  <td width="21%" height="22" class="formtext" colsapn="1"><label  class = "set" name="reliever.name" id="reliever.name" ondblclick="callShowDiv(this);"><%=label.get("reliever.name")%></label> <font color="red">*</font> :</td>

					 <td width="79%" height="22" colspan="4">&nbsp;&nbsp;&nbsp;<s:hidden
						name="relToken" /><s:hidden	name="propName" /><s:textfield label="%{getText('relName')}"
						name="relName" size="29" readonly="true" /><s:hidden name="relId" /><s:if test="relFlag"></s:if><s:else>
						<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="16" align="absmiddle" width="15" onclick="javascript:callsF9(500,325,'TransferApplication_f9relaction.action');" /> 
						
					</s:else></td>
				</tr>
			</table>
			</div>
			</td>	
		</tr>
		
		 <tr>
                  <td width="24%" height="22" class="formtext"><label  class = "set" name="reason" id="reason" ondblclick="callShowDiv(this);"><%=label.get("reason")%></label> :</td>
                  <td height="22" colspan="4"><label>
              
              <s:if test="showFlag"><s:textarea name="reason" rows="3"
				cols="99" /></s:if><s:else><s:textarea name="reason" rows="3"
				cols="99"/> </s:else>   
               &nbsp;                
                </label>
                </td>
                </tr></table>
                
                </td>
          </tr>
           
          
      </table></td>
    </tr>          
     
    <tr>
      <td colspan="3">
      <s:if test="%{trfApp.isForApprove}">
      
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
          <tr>
            <td class="formtext"><table width="100%" border="0" cellpadding="1" cellspacing="1" >
                <tr>
                 <td width="30%" class="formth"><label  class = "set" name="approved.by" id="approved.by" ondblclick="callShowDiv(this);"><%=label.get("approved.by")%></label></td>
                 <td width="20%" class="formth"><label  class = "set" name="approved.date" id="approved.date" ondblclick="callShowDiv(this);"><%=label.get("approved.date")%></label></td>
                 <td width="40%" class="formth"><label  class = "set" name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label></td>
                 <td width="10%" class="formth"><label  class = "set" name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
               
                </tr>
                
                <s:iterator value="trnAppList" status="stat">
                <tr >
                  <td width="30%" class="border2" width="30%"><s:property value="emp" /></td>
                  <td width="20%" class="border2" width="25%"><s:property value="apprDate" /></td>
                  <td  width="40%" class="border2" width="25%"><s:property value="remark" /></td>
                   <td  width="10%" class="border2" width="25%"><s:property value="trfStatus" /></td>
                  
                 
	    
	  
                </tr>
              </s:iterator>  
            </table>
          
            </td>
          </tr>
      </table>
      </s:if>
      
      </td>
    </tr>
  </table>  
  
   <s:hidden name="source" id="source" />
   
	</s:form>

<script>
	
	showText();
	function reliever() {
	var flag="true";
	
	var rel=document.getElementById('paraFrm_relReq').value;
	if(rel=="" || rel=="N") {
	flag="false";
	}
	
	}
	
	
	
	
	
	
	function callSave(){ 
	

var applicationDate=document.getElementById('paraFrm_appDt').value;
var relievingDate=document.getElementById('paraFrm_relDt').value;
var sts=document.getElementById('paraFrm_status').value;
var eName=document.getElementById('paraFrm_empName').value;
var rName=document.getElementById('paraFrm_relName').value;
var rel=document.getElementById('paraFrm_relReq').value;
//for label
	var empName=document.getElementById('employee').innerHTML.toLowerCase();
	var reName=document.getElementById('reliever.name').innerHTML.toLowerCase();
	var reDt=document.getElementById('relieving.date').innerHTML.toLowerCase();

	
		if(!(sts=="" || sts=="Pending")){
		alert("You Can't Modify the Application !");
		return false;
		}
		
		if(rel=="Y"){
		  if(rName==""){
		  alert("Please select the "+reName);
		  return false;
		
		}
		
		}
		
		
		


	
	if(document.getElementById('paraFrm_empName').value==""){
	alert("Please select "+empName);
	return false;
	
	}
		

	
	
	
	if(document.getElementById('paraFrm_relDt').value==""){
	alert("Please enter "+reDt);
	return false;
	}
	
	
	if(eName==rName){
	alert(empName+" and "+reName+" can't be same. ");	
	return false;
	
	}
	
	
	
		
if(!validateDate("paraFrm_relDt",relieving.date)){
		return false;
	}
		
		if(!dateDifferenceEqual(applicationDate, relievingDate, 'paraFrm_appDt', 'application.date', 'relieving.date')){
	
	return false;
	}
		
		
	
 	  	
	}
	
	
	  function callDelete(name) {
  			if(document.getElementById("paraFrm_trfId").value=="") {
  			alert("Please select the Transfer");
  			return false;
  			} else {
		
  			var conf=confirm("Are you sure to delete this record ? ");
  			if(conf) {
  			document.getElementById('paraFrm').target="main";
			document.getElementById('paraFrm').action=name;	
			document.getElementById('paraFrm').submit();	
  			
  			}
  	        }
  			}
	
	
	function callLock(){
		if(document.getElementById("paraFrm_trfId").value==""){
			alert("Please select the Transfer Record.");
			return false;
		
		}else{
					var conf=confirm("Are you sure to Lock this record ?");
					if(conf){
					
						return true;
					
					}else {
						return false;
					}
		}
	
	
	
	
	}
	
  			
  			
 
			 function callBack()
 {
 		document.getElementById('paraFrm').target = "_self";
	 	//this is for mypage back button
		if(document.getElementById('source').value=='mymessages')
		{
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
		}
		else if(document.getElementById('source').value=='myservices')
		{
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
		}
		document.getElementById('paraFrm').submit();
 }
  		
	
	
	
	
	
	
	
	function callReport(name) {
			if(document.getElementById('paraFrm_trfId').value=="") {
			alert("Please select the Transfer");
			return false;
			
			} else {
	

			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action=name;	
			document.getElementById('paraFrm').submit();	
			document.getElementById('paraFrm').target="main";						
			}
	
	}
	
	
	
		
   
   
   
	function showText(){
			
				var chq = document.getElementById('paraFrm_relReq').value;
				
				if(chq=="Y" ){
				document.getElementById('div2').style.display='';
				}else if(chq=="N" || chq==" "){
				document.getElementById('div2').style.display='none';
				}
				
			}
	
	</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

































