<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>

<s:form action="PerformanceMetrics" id="paraFrm" validate="true"
	theme="simple" target="main">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="performanceMetricsId" />
	<s:hidden name="selectParentFlag" />
	<s:hidden name="screenWidth"/>
<s:hidden name="myPage" id="myPage" />
<s:hidden name="dashBoardID" id="dashBoardID" />
<s:hidden name="autoId" />
	<table width="100%" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" align="right" class="formbg">
				<tr>
					<td><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Performance
					Metrics ( CRM )</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td align="left"><input type="button" class="save"
				value="   Save " onclick="return saveFun();" />&nbsp;<input type="button" class="back"
				value="   Back " onclick="return backFun();" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" class="formbg" border="0">

				<tr>
					<td width="28%" colspan="1" height="22">Account Name
					:<font color="red"></font></td>
					<td width="25%" colspan="1" id="calculatedBox1"><s:hidden
						name="accountCode" /> <s:textfield name="accountName" size="25"
						readonly="true" cssStyle="background-color: #F2F2F2;" /> <!--<img
						src="../pages/images/recruitment/search2.gif" width="16"
						height="15" class="iconImage" id='ctrlHide'
						onclick="javascript:callsF9(500,325,'PerformanceMetrics_f9parent.action');">
						-->
						</td>
						<td height="22" class="formtext">&nbsp;</td>
					<td height="22">&nbsp;</td>
				</tr> 

 
				<tr  id="applyChildTable">
					<td width="28%" height="22" class="formtext"><label
						name="apply.child" class="set" id="apply.child"
						ondblclick="callShowDiv(this);"><%=label.get("apply.child")%></label>
					:</td>
					<td><s:hidden name="childCode"/>
					<!--<input type="button" class="back"
				value="   Apply " onclick="return applyChildFun();" />
				--><s:checkbox name="isApplyChild" onclick="hideChild();"/>
				</td>
					<td height="22" class="formtext">&nbsp;</td>
					<td height="22">&nbsp;</td>

				</tr>
			
				
				<tr id="selectedChildTable">
							<td width="15%"><label class="set" id="apply.child.selected"
								name="apply.child.selected" ondblclick="callShowDiv(this);"><%=label.get("apply.child.selected")%></label>:<font
								color="red"></font></td>

							<td colspan="3"><s:hidden name="selectChildCode" /> <s:textarea
								cols="90" rows="1" theme="simple" readonly="true"
								name="selectChildName" /> <img
								src="../pages/images/search2.gif" class="iconImage" height="16"
								align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_selectChildName',350,250,'PerformanceMetrics_f9ChildAccount.action?accountParentCode=<s:property value='accountCode'/>',event,'false','no','right')"></td>
						</tr>
				
			</table>
			</td>
		</tr>
		
		
		<tr>
			<td colspan="6">
			<table width="100%" class="formbg" border="0">   
				<tr> 
					<td height="22" width="20%">&nbsp;</td>
					<td height="22" width="10%">CRM Customization</td>
					<td height="22" width="10%">Client Customization</td>
					<td height="22" width="20%"><strong  class="text_head"><s:textfield name="" size="28" 
						readonly="true" cssStyle="background-color: #A4E3A4;text-align:center;" value="Parameter Range" /></strong></td>
					<td height="22" width="20%"><strong  class="text_head"><s:textfield name="" size="28"
						readonly="true" cssStyle="background-color: #FFFF91;text-align:center" value="Parameter Range" /></strong></td> 
					<td height="22" width="20%"><strong  class="text_head"><s:textfield name="" size="28" 
						readonly="true" cssStyle="background-color: #F08484;text-align:center" value="Parameter Range" /></strong></td> 
				</tr> 
				<tr> 
						 <td width="3%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						<td width="3%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap">
							
							<input type="checkbox" id="crmAll" style="cursor: hand;" title="Select all" 
							onclick="checkAllRecords(this, 'crmFlag', 'otherLengthVar');" />
							
						</td>
						<td width="3%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap">
							
							<input type="checkbox" id="clientAll" style="cursor: hand;" title="Select all" 
							onclick="checkAllRecords(this, 'clientFlag', 'otherLengthVar');" />
							
						</td>
						 <td width="3%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="3%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="3%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>					
				</tr>
				
				
				
				
				<%
																				int j = 1;
																				%>
											
											
											
				<s:iterator value="metricsList">
				
				
				<tr > 
					

					<td class="sortableTD" align="left"  width="20%" >
						<s:hidden name="metricCode"/>
						<s:property value="metricName"/>
						<s:hidden name="metricAbbr"/> 
					</td>
					<td width="10%" align="center">
					
					<!--<s:checkbox name="crmFlag" id="crmFlag" onclick="setCheckCRM();"/>
					<s:textfield name="crmFlagHidden" id="crmFlagHidden"/>
					
					-->
					<input type="checkbox" class="checkbox" name="crmFlag" 
														id="<%="crmFlag"+j%>" 
														onclick="callChecked('crmFlag<%=j%>', 'crmFlag<%=j%>');"/>
					<input type="hidden" class="text" name="crmFlagHidden" 
														id="<%="crmFlagHidden"+j%>" value='<s:property value="crmFlagHidden" />'/>
														
														
					</td>
					
					<td width="10%" align="center">
					<!--<s:checkbox name="clientFlag" onclick="setCheckClient();"/>
					<s:textfield name="clientFlagHidden"/>
						--><input type="checkbox" class="checkbox" name="clientFlag" 
														id="<%="clientFlag"+j%>" 
														onclick="callChecked('clientFlag<%=j%>', 'clientFlag<%=j%>');"/>
					<input type="hidden" class="text" name="clientFlagHidden" 
														id="<%="clientFlagHidden"+j%>" value='<s:property value="clientFlagHidden" />'/>
					
					
					
					</td>
					<td height="22" width="20%"><s:textfield name="greenValStart" size="10"
						onkeypress="return restrictChars(event, this);"  cssStyle="text-align:right" maxlength="5"/>&nbsp;&nbsp;<s:textfield name="greenValEnd" size="10"
						onkeypress="return restrictChars(event, this);"  cssStyle="text-align:right"   maxlength="5"/></td>
					<td height="22" width="20%"><s:textfield name="yellowValueStart" size="10"
						onkeypress="return restrictChars(event, this);"  cssStyle="text-align:right"  maxlength="5"/>&nbsp;&nbsp;<s:textfield name="yellowValueEnd" size="10"
						onkeypress="return restrictChars(event, this);"  cssStyle="text-align:right"  maxlength="5"/></td> 
					<td height="22" width="20%"><s:textfield name="redValueStart" size="10"
						onkeypress="return restrictChars(event, this);"  cssStyle="text-align:right"  maxlength="5"/>&nbsp;&nbsp;<s:textfield name="redValueEnd" size="10"
						onkeypress="return restrictChars(event, this);"  cssStyle="text-align:right"  maxlength="5"/></td>
					
				</tr>
				<%
				j++;
				%>
				</s:iterator>
				<input type="hidden" name="otherLengthVar" id="otherLengthVar" value="<%=j%>"/> 
			
		</table>
		</td>
		</tr>
		<tr>
			<td align="left"><input type="button" class="save"
				value="   Save " onclick="return saveFun();" />&nbsp;<input type="button" class="back"
				value="   Back " onclick="return backFun();" /></td>
		</tr>
		<tr>
			<td></td>
		</tr>
		<tr>
			<td>
			
			<table width="100%" class="formbg" border="0">
			
			<tr> 
						 <td width="10%" align="left"  id="ctrlShow"
							nowrap="nowrap" > <strong class="text_head"> Manage SLA</strong>
						</td>
						 <td width="5%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="5%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						<td width="5%" align="right"  id="ctrlShow"
							nowrap="nowrap">
							
							&nbsp;
							
						</td>
						<td width="5%" align="right"  id="ctrlShow"
							nowrap="nowrap">
							
							&nbsp;
						</td>
						 <td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						<td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						<td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						<td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						<td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>					
				</tr>
				
				<tr> 
						 <td width="10%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap">Caption
						</td>
						
						 <td width="5%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap">CRM Flag
						</td>
						 <td width="5%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap">Client Flag
						</td>
						<td width="5%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap">
							SLA Type
						</td>
						<td width="5%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap">
							Hrs
						</td>
						 <td width="20%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap" colspan="2"><strong class="text_head"><s:textfield
						name="" size="28" readonly="true"
						cssStyle="background-color: #A4E3A4;text-align:center;"
						value="Parameter Range" /></strong>
						</td>
						 <td width="20%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap" colspan="2"><strong class="text_head"><s:textfield
						name="" size="28" readonly="true"
						cssStyle="background-color: #FFFF91;text-align:center"
						value="Parameter Range" /></strong>
						</td>
						 <td width="20%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap" colspan="2"><strong class="text_head"><s:textfield
						name="" size="28" readonly="true"
						cssStyle="background-color: #F08484;text-align:center"
						value="Parameter Range" /></strong>
						</td>	
						<td width="10%" align="right" class="formth" id="ctrlShow"
							nowrap="nowrap"></td>				
				</tr>

				<tr>
					<td class="sortableTD" align="left" width="10%"><s:textfield
						name="captionName" id="captionName" />
						</td>

					<td width="5%" align="center"><input type="checkbox"
						class="checkbox" name="slaCrmFlag" id="slaCrmFlag"
						onclick="callCheckedSla('slaCrmFlag', 'slaCrmFlag');" />
					<s:hidden name="slaCrmFlagHidden"
						id="slaCrmFlagHidden"
						 /></td>

					<td width="5%" align="center"><input type="checkbox"
						class="checkbox" name="slaClientFlag" id="slaClientFlag"
						onclick="callCheckedSla('slaClientFlag', 'slaClientFlag');" />
					<s:hidden name="slaClientFlagHidden"
						id="slaClientFlagHidden"
						/></td>
					<td width="5%" align="center"><s:select list="#{'CONTACT':'CONTACT', 'RESPONSE':'RESPONSE', 'RESTORE':'RESTORE'}"  id="slaType" name="slaType"></s:select>
					</td>
					<td width="5%" align="center" ><s:textfield name="hrs" id="hrs" size="5" cssStyle="text-align:right" onkeypress="return numbersWithDot(event);"/></td>
					<td height="22" width="10%" align="right"><s:textfield id="slaGreenValStart" name="slaGreenValStart"
						size="10" onkeypress="return restrictChars(event, this);"
						cssStyle="text-align:right" maxlength="5" />
						</td>
						<td height="22" width="10%" align="right"><s:textfield
						id="slaGreenValEnd" name="slaGreenValEnd" size="10"
						onkeypress="return restrictChars(event, this);"
						cssStyle="text-align:right" maxlength="5" /></td>
					<td height="22" width="10%" align="right"><s:textfield
						id="slaYellowValStart" name="slaYellowValStart" size="10"
						onkeypress="return restrictChars(event, this);"
						cssStyle="text-align:right" maxlength="5" />
						</td>
						<td height="22" width="10%" align="right"><s:textfield
						id="slaYellowValEnd" name="slaYellowValEnd" size="10"
						onkeypress="return restrictChars(event, this);"
						cssStyle="text-align:right" maxlength="5" /></td>
					<td height="22" width="10%" align="right"><s:textfield id="slaRedValStart" name="slaRedValStart"
						size="10" onkeypress="return restrictChars(event, this);"
						cssStyle="text-align:right" maxlength="5" />
						</td>
						<td height="22" width="10%" align="right"><s:textfield
						id="slaRedValEnd" name="slaRedValEnd" size="10"
						onkeypress="return restrictChars(event, this);"
						cssStyle="text-align:right" maxlength="5" /></td>

					<td height="22" width="10%" align="center"><input type="button" name= "Add SLA" id="Add" value="Add SLA" onclick="return addSLA();"></td>
				</tr>
				
				<tr> 
						 <td width="10%" align="left"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="5%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="5%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						<td width="5%" align="right"  id="ctrlShow"
							nowrap="nowrap">
							
							&nbsp;
							
						</td>
						<td width="5%" align="right"  id="ctrlShow"
							nowrap="nowrap">
							
							&nbsp;
						</td>
						 <td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>
						 <td width="10%" align="right"  id="ctrlShow"
							nowrap="nowrap">&nbsp;
						</td>				
				</tr>
				<s:iterator value="slaList">
					<tr>
						<td align="left" width="10%"><s:property value="captionName" />
						
						</td>
					
						<td align="center" width="5%"><s:property value="slaCrmFlagHidden" /></td>
					
						<td align="center" width="5%"><s:property value="slaClientFlagHidden" /></td>
					
						<td align="left" width="5%"><s:property value="slaType" /></td>
					
						<td align="right" width="5%"><s:property value="hrs" /></td>

						<td align="right" width="10%"><s:property value="slaGreenValStart" />
						</td><td width="10%" align="right"><s:property
							value="slaGreenValEnd"  /></td>

						<td align="right" width="10%"><s:property value="slaYellowValStart" /> </td><td align="right" width="10%"><s:property
							value="slaYellowValEnd" /></td>

						<td align="right" width="10%"><s:property value="slaRedValStart" /></td><td width="10%" align="right"><s:property
							value="slaRedValEnd" /></td> 
							<td align="center" width="10%">
													<a href="#"><img
														src="../pages/mypage/images/icons/edit.png" 
														onclick="editFun('<s:property value="autoId"/>')" width="13" height="13" border="0" /></a>&nbsp;&nbsp;
													<a href="#"><img src="../pages/common/css/icons/delete.gif" width="13" height="13" class="iconImage" onclick="deleteCurrentRow('<s:property value="autoId"/>')"></a>
													</td>
					</tr>
					
				</s:iterator>
				</table>
			</td>
		</tr>
				
	
	</table>
</s:form>


<script type="text/javascript">
onload();

	function onload(){
		var parFlag = document.getElementById('paraFrm_selectParentFlag').value;
	
		if(parFlag == 'YN'){
			///alert("parFlag"+parFlag);
	 		document.getElementById('selectedChildTable').style.display='none';
	 		document.getElementById('applyChildTable').style.display='none';
	 		
 		}else{
 			///alert("parFlag"+parFlag);
 			document.getElementById('applyChildTable').style.display='';
 			document.getElementById('selectedChildTable').style.display='';
 			
 		}
 		hideChild();
 		onloadCall();
 		//document.getElementById('slaCrmFlagHidden').value="N";
 		//document.getElementById('slaClientFlagHidden').value="N";
 		
 		if(document.getElementById('slaCrmFlagHidden').value=='Yes')
 		{
 			document.getElementById('slaCrmFlag').checked=true;
 			document.getElementById('slaCrmFlagHidden').value="Y";
 		}
 		else{
 			document.getElementById('slaCrmFlagHidden').value="N";
 			
 		}
 		if(document.getElementById('slaClientFlagHidden').value=='Yes')
 		{
 			document.getElementById('slaClientFlag').checked=true;
 			document.getElementById('slaClientFlagHidden').value="Y";
 		}
 		else{
 		document.getElementById('slaClientFlagHidden').value="N";
 		}
	}
	
	function editFun(autoId){
try{
   
	document.getElementById('paraFrm').target="main";
	document.getElementById("paraFrm").action='PerformanceMetrics_editSla.action?autoId='+autoId;
	document.getElementById("paraFrm").submit();
}catch(e){
alert(e);}
}
	
	
	function numbersWithDot(e){
	document.onkeypress = numbersWithDot;
	var key //= (window.event) ? event.keyCode : e.which;
	if (window.event)
	    key = event.keyCode
	else
	   	key = e.which
	clear();
	// Was key that was pressed a numeric character (0-9) or backspace (8)?
	if ( key > 47 && key < 58 || key == 8 || key == 46 || key == 0)
	 	return true; // if so, do nothing
	else // otherwise, discard character
		return false;
	if (window.event) //IE
	    window.event.returnValue = null;     else //Firefox
	    e.preventDefault();
}

function deleteCurrentRow(autoId){
	
	var conf=confirm("Are you sure to delete this record?");   
  		if(conf) {
  		document.getElementById("paraFrm").target='_self';
	document.getElementById("paraFrm").action='PerformanceMetrics_deleteSla.action?autoId='+autoId;
	document.getElementById("paraFrm").submit();
		
			}
   }

function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PerformanceMetrics_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function saveFun(){
	var parFlag = document.getElementById('paraFrm_selectParentFlag').value;
	var parentCheckboxVar = document.getElementById('paraFrm_isApplyChild').checked;
	if(parFlag == 'YY' && (parentCheckboxVar == true)){
		if(document.getElementById('paraFrm_childCode').value==""){
				alert("Their is no child records to apply Performance Metrics.");
	  			return false;
			}
	}
			
		document.getElementById('paraFrm').target="main";		  
	   	document.getElementById("paraFrm").action="PerformanceMetrics_savePerformanceMetrics.action";
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
		
	}
			
		function resetFun(){
			document.getElementById('paraFrm_firstName').value="";
			document.getElementById('paraFrm_lastName').value="";
			document.getElementById('paraFrm_emailClientAddress').value="";
			document.getElementById('paraFrm_cellNumber').value="";
			document.getElementById('paraFrm_parentClientCode').value="";
			document.getElementById('paraFrm_parentClientName').value="";
		}
	
	// Retrieve last key pressed.  Works in IE and Netscape.
          // Returns the numeric key code for the key pressed.
          function getKey(e)
          {
            if (window.event)
               return window.event.keyCode;
            else if (e)
               return e.which;
            else
               return null;
          }
          function restrictChars(e, obj)
          {
            var CHAR_AFTER_DP = 2;  // number of decimal places
            var validList = "0123456789.";  // allowed characters in field
            var key, keyChar;
            key = getKey(e);
            if (key == null) return true;
            // control keys
            // null, backspace, tab, carriage return, escape
            if ( key==0 || key==8 || key==9 || key==13 || key==27 )
               return true;
            // get character
            keyChar = String.fromCharCode(key);
            // check valid characters
            if (validList.indexOf(keyChar) != -1)
            {
              // check for existing decimal point
              var dp = 0;
              if( (dp = obj.value.indexOf( ".")) > -1)
              {
                if( keyChar == ".")
                  return false;  // only one allowed
                else
                {
                  // room for more after decimal point?
                  if( obj.value.length - dp <= CHAR_AFTER_DP)
                    return true;
                }
              }
              else return true;
            }
            // not a valid character
            return false;
          }
		function applyChildFun(){
			if(document.getElementById('paraFrm_childCode').value==""){
				alert("Their is no child records to apply Performance Metrics.");
	  			return false;
			}
			document.getElementById('paraFrm').target="main";		  
		   	document.getElementById("paraFrm").action="PerformanceMetrics_applyChildPerformanceMetrics.action";
		    document.getElementById("paraFrm").submit();
		    document.getElementById('paraFrm').target="main";
		}
		
		function hideChild(){
			
			var parentCheckboxVar = document.getElementById('paraFrm_isApplyChild').checked;
			var parFlag = document.getElementById('paraFrm_selectParentFlag').value;
 		if(parentCheckboxVar == false && parFlag == 'YY'){
 		
	 		document.getElementById('selectedChildTable').style.display='';
	 		document.getElementById('paraFrm_selectChildCode').value="";
			document.getElementById('paraFrm_selectChildName').value="";
 		}else{
 		
 			document.getElementById('selectedChildTable').style.display='none';
 			
 		}
	}
		
		function callCheckedSla(checkBox, field){
		try{
		
	
			if(document.getElementById('slaCrmFlag').checked == true){
						
						document.getElementById('slaCrmFlagHidden').value="Y";
						document.getElementById('slaCrmFlag').value="Y";
					}
			if(document.getElementById('slaCrmFlag').checked == false){
						
						document.getElementById('slaCrmFlagHidden').value="N";
						document.getElementById('slaCrmFlag').value="N";
					}		
			if(document.getElementById('slaClientFlag').checked == true){
						
						document.getElementById('slaClientFlagHidden').value="Y";
						document.getElementById('slaClientFlag').value="Y";
					}
			if(document.getElementById('slaClientFlag').checked == false){
						
						document.getElementById('slaClientFlagHidden').value="N";
						document.getElementById('slaClientFlag').value="N";
					}
			
		
		
		}
		catch(e){
		alert(e);
		}
		
		}
		
		
		function callChecked(checkBox, field){
		try{
			var count=1;
			var crmFlag=1;
			var clientFlag=1;
		var listCount = document.getElementById('otherLengthVar').value;
		
			if(document.getElementById(checkBox).checked == true){	  
				document.getElementById(field).value="Y";
				for(var i = 1; i < listCount; i++) {
					if(document.getElementById('crmFlag'+i).checked == true){
						crmFlag=crmFlag+1;
						document.getElementById('crmFlagHidden'+i).value="Y";
					}
					if(document.getElementById('clientFlag'+i).checked == true){
						clientFlag=clientFlag+1;
						document.getElementById('clientFlagHidden'+i).value="Y";
					}
					
				}
				 
				document.getElementById('crmFlag').checked =false;
				document.getElementById('clientFlag').checked =false;
				if(crmFlag=='28'){
				document.getElementById('crmFlag').checked =true;
				}
				if(clientFlag=='28'){
				document.getElementById('clientFlag').checked =true;
				}
			
	  	} else {
	  		document.getElementById(field).value="N";
	  		for(var i = 1; i < listCount; i++) {
	  			if(!document.getElementById('crmFlag'+i).checked == true){
						document.getElementById('crmAll').checked =false;
					 	document.getElementById('crmFlagHidden'+i).value="N";
					}
					
				if(!document.getElementById('clientFlag'+i).checked == true){
						document.getElementById('clientAll').checked =false;
					 	document.getElementById('clientFlagHidden'+i).value="N";
					}
	  		}
	  	} 
	  	} catch(e){  
	  		///alert(e); 
	  	}
	}
	
	function checkAllRecords(object, checkBox, totalRecordsField) {
try{
	totalRecords = document.getElementById(totalRecordsField).value;
	////alert(totalRecords); 
	if(object.checked) {
			for(var i = 1; i <= totalRecords; i++) {
				document.getElementById(checkBox + i).checked = true;
				
				//CRM FLAG
			 	if(document.getElementById('crmFlag'+i).checked == true){
						document.getElementById('crmFlag'+i).checked = true;
						document.getElementById('crmFlagHidden'+i).value="Y";
				}
				//CLIENT FLAG 
				if(document.getElementById('clientFlag'+i).checked == true){
						document.getElementById('clientFlag'+i).checked = true;
						document.getElementById('clientFlagHidden'+i).value="Y";
				}
			} 
		} else {
			for(var i = 1; i <= totalRecords; i++) {
				document.getElementById(checkBox + i).checked = false;
				
				//CRM FLAG
			 	if(!document.getElementById('crmFlag'+i).checked == true){
						document.getElementById('crmFlag'+i).checked = false;
						document.getElementById('crmFlagHidden'+i).value="N";
				}
				//CLIENT FLAG 
				if(!document.getElementById('clientFlag'+i).checked == true){
						document.getElementById('clientFlag'+i).checked = false;
						document.getElementById('clientFlagHidden'+i).value="N";
				}
				
			} 
		}
		}catch(e){
			///alert(e);
		}
}

	function onloadCall() {
			try{
			var count=1;
			var countClient=1;
			var listCount = document.getElementById('otherLengthVar').value;
			
				for(var i = 1; i < listCount; i++) {
					if(document.getElementById('crmFlagHidden'+i).value=="Y"){ 
					count=count+1;
						document.getElementById('crmFlag'+i).checked = true;
					}else{
						document.getElementById('crmFlag'+i).checked = false;
						document.getElementById('crmFlagHidden'+i).value=="N"
					}
					if(document.getElementById('clientFlagHidden'+i).value=="Y"){
					countClient=countClient+1;
						document.getElementById('clientFlag'+i).checked = true;
					}else{
						document.getElementById('clientFlag'+i).checked = false;
						document.getElementById('clientFlagHidden'+i).value="N";
					}
				} 
				 
				if(count=='27'){
					document.getElementById('crmAll').checked =true;
				}else{
					document.getElementById('crmAll').checked =false;
				}
				if(countClient=='27'){
					document.getElementById('clientAll').checked =true;
				}else{
					document.getElementById('clientAll').checked =false;
				}
			}catch(e){
				///alert(e);
			}
		
		}
		
		function addSLA(){
		if(document.getElementById('captionName').value=='')
		{
		alert("Please enter Caption");
		return false;
		}
		if(document.getElementById('hrs').value==''){
		alert("Please enter Hrs");
		return false;
		}
		
		
		document.getElementById('paraFrm').target="main";		  
	   	document.getElementById("paraFrm").action="PerformanceMetrics_saveSLA.action";
	    document.getElementById("paraFrm").submit();
	    document.getElementById('paraFrm').target="main";
		
		}
	
	</script>
