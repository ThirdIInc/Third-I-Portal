<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="HelpDeskSLAMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="show" />
	<s:hidden name="hiddencode" />
	<s:hidden name="editFlag" />
	<s:hidden name="cancelFlag" />
	<s:hidden name="slaCategoryCounter" />
	<table class="formbg" align="right" width="100%">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Configure SLA
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
			<td colspan="3" width="100%">

			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<!-- The Following code Denotes  Include JSP Page For Button Panel -->
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
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<s:hidden name="slaCode" />
									<td width="22%" height="22" class="formtext"><label
										name="sla.name" class="set" id="sla.name"
										ondblclick="callShowDiv(this);"><%=label.get("sla.name")%></label>
									:<font color="red">*</font></td>
									<td width="17%" height="22"><s:textfield name="slaName"
										theme="simple" size="25" maxlength="50"
										onkeypress="return allCharacters();" /></td>
									<td width="27%" height="22" class="formtext">&nbsp;</td>
									<td width="18%" height="22">&nbsp;</td>
								</tr>
								<tr>
									<td width="22%" height="22" valign="top" class="formtext"
										nowrap="nowrap"><label name="sla.desc" class="set"
										id="sla.desc" ondblclick="callShowDiv(this);"><%=label.get("sla.desc")%></label>
									:<font color="red">*</font></td>
									<td height="22" colspan="1"><s:textarea name="slaDesc"
										cols="50" rows="3" onkeyup="callLength('descCnt');"></s:textarea>
									</td>
									<td height="22" colspan="1" valign="bottom" id='ctrlHide'><img
										src="../pages/images/zoomin.gif" height="12" align="absmiddle"
										width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_slaDesc','sla.desc','','200','200');">
									&nbsp;Remaining chars<s:textfield name="descCnt"
										readonly="true" size="5"></s:textfield></td>
								</tr>
								<tr>
									<td width="22%" height="22" class="formtext"><label
										name="active" class="set" id="active"
										ondblclick="callShowDiv(this);"><%=label.get("active")%></label>
									:</td>
									<td><s:checkbox name="isActive" /></td>
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
		<tr>
			<td colspan="3">
			<%
			try {
			%>
			<table width="100%" border="0" class="formbg" cellpadding="0"
				cellspacing="0">
				<tr>
					<td class="formtext">
					<table width="100%" border="0">
						<tr>
							<s:hidden name="myPage" id="myPage" />
							<td class="formth" align="center"><label class="set"
								name="serial.no" id="serial.no1" ondblclick="callShowDiv(this);"><%=label.get("sla.sr")%></label>
							</td>
							<td class="formth" align="center"><label class="set"
								name="sla.status" id="sla.status1"
								ondblclick="callShowDiv(this);"><%=label.get("sla.status")%></label>
							</td>
							<td class="formth" align="center"><label class="set"
								name="sla.duration" id="sla.duration"
								ondblclick="callShowDiv(this);"><%=label.get("sla.duration")%></label>
							</td>
							<td class="formth" align="center"><label class="set"
								name="sla.duration" id="sla.duration"
								ondblclick="callShowDiv(this);"><%=label.get("sla.escalation1")%></label>
							</td>
							<td class="formth" align="center"><label class="set"
								name="sla.duration" id="sla.duration"
								ondblclick="callShowDiv(this);"><%=label.get("sla.escalation2")%></label>
							</td>
							<td class="formth" align="center"><label class="set"
								name="sla.duration" id="sla.duration"
								ondblclick="callShowDiv(this);"><%=label.get("sla.escalation3")%></label>
							</td>
							</tr>
							<s:if test="modeLengthList">
								<%
										int counter = 0;
										int count = 0;
								%>
								<%!int d = 0;%>
								<%
								int i = 0;
								%>
								<s:iterator value="slaStatusList">
									<tr>
										<td width="5%" align="center" class="sortableTD"><%=++count%>
										<%
										++i;
										%>
										</td>
										<td width="10%" align="left" class="sortableTD"><s:hidden
											name="slaCategId" id='<%="slaCategId" + i%>' /> <input
											type="hidden" name="hdeleteCode1" id="hdeleteCode1" /> <s:property
											value="slaStatus" /></td>
											<!-- 
										<td width="8%" align="center" nowrap="nowrap"
											class="sortableTD">
											<input type="checkbox" class="checkbox"
												id="confChk1<%=i%>" name="confChk1"
												onclick="checkActivate('<s:property  value="slaCategId"/>','<%=i%>')"/>
												<s:hidden name="confChkHid" id='<%="confChkHid" + i%>'/>
										</td>	 -->
																			
										<td width="18%" align=center class="sortableTD">
										<s:if test='%{slaStatus=="Open"}'>
										</s:if><s:else>
										<input type="hidden" name="hdeleteCode1" id="hdeleteCode1<%=i%>" />
										<s:textfield name="slaDuration" size="3" maxlength="3"
											id='<%="slaDuration" + i%>'
											onkeypress="return numbersOnly();" /> <s:select
											name="durType"
											list="#{'':'--Select--','H':'Hours','M':'Mins','D':'Days'}"
											id='<%="durType" + i%>' cssStyle="width:60;z-index:5;" />
											</s:else>&nbsp;</td>

									<td width="20%" height="22" align="left" class="sortableTD">
									<s:if test='%{slaStatus=="Open"}'>
									&nbsp;
									</s:if><s:else>
									<table border="0" width="100%">
										<tr>
											<td colspan="3" align="left"><input type="hidden" id="paraFrm_escalateOneEmpId<%=counter%>"
											name="escalateOneEmpId"
											value='<s:property value="escalateOneEmpId"/>' />
										<input type="hidden"
											id="paraFrm_escalateOneEmpToken<%=counter%>"
											name="escalateOneEmpToken"
											value='<s:property value="escalateOneEmpToken"/>' />
											<input type="text"	id="paraFrm_escalateOneEmpName<%=counter%>"
													name="escalateOneEmpName"
													value='<s:property value="escalateOneEmpName"/>' size="20"
													readonly="true" />&nbsp; <img align="absmiddle"
													src="../pages/common/css/default/images/search2.gif"
													onclick="callSearch(<%=counter%>,'f9EscalateToEmployeeOne');"
													id='ctrlHide'>
											</td>
										</tr>
										<tr>
											<td align="left" id="ctrlHide">after</td>
											<td align="center"><s:textfield name="escDurationOne" size="5"
													maxlength="3" id='<%="escDurationOne" + i%>'
													onkeypress="return numbersOnly();" /></td>
											<td><s:select
													name="durTypeOne"
													list="#{'':'--Select--','H':'Hours','M':'Mins','D':'Days'}"
													id='<%="durTypeOne" + i%>' cssStyle="width:72;z-index:5;" /></td>
										</tr>
									</table>
									</s:else>
									</td>
									<td width="20%" height="22" align="left" class="sortableTD">
									<s:if test='%{slaStatus=="Open"}'>
									&nbsp;
									</s:if><s:else>
									<table border="0" width="100%">
										<tr>
											<td colspan="3" >
											<input	type="hidden" id="paraFrm_escalateTwoEmpId<%=counter%>"
													name="escalateTwoEmpId"	value='<s:property value="escalateTwoEmpId"/>' />
											<input	type="hidden" id="paraFrm_escalateTwoEmpToken<%=counter%>"
													name="escalateTwoEmpToken"	value='<s:property value="escalateTwoEmpToken"/>' />
											<input	type="text" id="paraFrm_escalateTwoEmpName<%=counter%>"
													name="escalateTwoEmpName" value='<s:property value="escalateTwoEmpName"/>' size="20" readonly="true"/>
											<img align="absmiddle"	src="../pages/common/css/default/images/search2.gif"
											onclick="callSearch(<%=counter%>,'f9EscalateToEmployeeTwo');" id='ctrlHide'>
											</td>
										</tr>
										<tr>
											<td id="ctrlHide">after</td>
											<td align="center"><s:textfield name="escDurationTwo" size="5" maxlength="3" id='<%="escDurationTwo" + i%>'
											onkeypress="return numbersOnly();" /> </td>
											<td><s:select
											name="durTypeTwo" list="#{'':'--Select--','H':'Hours','M':'Mins','D':'Days'}"
											id='<%="durTypeTwo" + i%>' cssStyle="width:72;z-index:5;" /></td>
										</tr>
									</table>
									</s:else>
									</td>
									<td width="20%" height="22" align="left" class="sortableTD">
									<s:if test='%{slaStatus=="Open"}'>
									&nbsp;
									</s:if><s:else>
									<table border="0" width="100%">
										<tr>
											<td colspan="3">
											<input	type="hidden" id="paraFrm_escalateThreeEmpId<%=counter%>"
													name="escalateThreeEmpId"	value='<s:property value="escalateThreeEmpId"/>' />
										<input	type="hidden" id="paraFrm_escalateThreeEmpToken<%=counter%>"
													name="escalateThreeEmpToken"	value='<s:property value="escalateThreeEmpToken"/>' />
										<input	type="text" id="paraFrm_escalateThreeEmpName<%=counter%>"
													name="escalateThreeEmpName" value='<s:property value="escalateThreeEmpName"/>' size="20" readonly="true"/>
										<img align="absmiddle" src="../pages/common/css/default/images/search2.gif"
											onclick="callSearch(<%=counter%>,'f9EscalateToEmployeeThree');" id='ctrlHide'>
											</td>
										</tr>
										<tr>
											<td id="ctrlHide">after</td>
											<td align="center"><s:textfield name="escDurationThree" size="5" maxlength="3" id='<%="escDurationThree" + i%>'
											onkeypress="return numbersOnly();" /></td>
											<td><s:select
											name="durTypeThree" list="#{'':'--Select--','H':'Hours','M':'Mins','D':'Days'}"
											id='<%="durTypeThree" +counter%>' cssStyle="width:72;z-index:5;" /></td>
										</tr>
									</table>
									</s:else>
									</td>
									</tr>
									<%
									counter++;
									%>
								</s:iterator>
								<%
								d = i;
								%>
							</s:if>
					</table>
					<s:if test="modeLengthList"></s:if> <s:else>
						<table width="100%">
							<tr>
								<td align="center"><font color="red">No Data To
								Display</font></td>
							</tr>
						</table>
					</s:else> <%
 		} catch (Exception e) {
 		e.printStackTrace();
 	}
 %>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<tr>
			<td width="75%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
	<s:hidden name="rowId" />
</s:form>

<script>

	function saveFun(){	
		var val=trim(document.getElementById('paraFrm_slaName').value);

		  if(val==""){
			  alert("Please enter "+document.getElementById('sla.name').innerHTML.toLowerCase());
			  document.getElementById('paraFrm_slaName').focus();
			  return false;
		  }
		var fields=["paraFrm_slaDesc"];
	    var labels=["sla.desc"];
	    var flag = ["enter"];
	 	 if(!validateBlank(fields,labels,flag))
	     return false;
	 	
		var slaDesc = document.getElementById('paraFrm_slaDesc').value;
		if(slaDesc.length > 200){
			alert("Maximum length of "+document.getElementById('sla.desc').innerHTML.toLowerCase()+
			" is 200 characters.");
			return false;
		}
		//ITERATOR VALIDATION
		var flag='<%=d %>';
		//alert(flag);
	   	for(var a=2;a<=flag;a++){
		    if(trim(document.getElementById('slaDuration'+a).value)==""){
		    	alert("Please enter "+document.getElementById('sla.duration').innerHTML.toLowerCase());
			  	document.getElementById('slaDuration'+a).focus();
		    	return false;
		    }
		    if(document.getElementById('durType'+a).value==""){
		    	alert("Please enter "+document.getElementById('sla.duration').innerHTML.toLowerCase()+" type");
			  	document.getElementById('durType'+a).focus();
		    	return false;
		    }
		}
	
		
		document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'HelpDeskSLAMaster_save.action';
		document.getElementById('paraFrm').submit();
	}
	
	  
	
	function callLength(type){ 
		
		 if(type=='descCnt'){
			
					var cmt =document.getElementById('paraFrm_slaDesc').value;
					var remain = 200 - eval(cmt.length);
					document.getElementById('paraFrm_descCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_slaDesc').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_slaDesc').style.background = '#FFFFFF';
				
				}
				}  



	function resetFun() {
		document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'HelpDeskSLAMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HelpDeskSLAMaster_back.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'HelpDeskSLAMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	function callSearchOld(action,empId,empToken,empName) {
		var myWinDiv = window.open('', 'myWinDiv', 'top = 230, left = 200, width = 750, height = 420, scrollbars = no, status = no, resizable = yes');
		document.getElementById("paraFrm").target = 'myWinDiv';
		document.getElementById("paraFrm").action = "HelpDeskSLAMaster_"+action+".action?empId="+empId+"&empToken="+empToken+"&empName="+empName;
		document.getElementById("paraFrm").submit();
	}
	function callSearch(rowNum, action){
		document.getElementById('paraFrm_rowId').value=rowNum;
		javascript:callsF9(500,325,'HelpDeskSLAMaster_'+action+'.action');
	}
	function editFun() {
		return true;
	}
	
	 function checkActivate(id,i){
	try{
	   if(document.getElementById('confChk1'+i).checked == true)
	   {	  
	    document.getElementById('confChkHid'+i).value="true";
	   }
	   else
	   document.getElementById('confChkHid'+i).value="";
	   }catch(e){
	   	//alert(e);
	   }
   }
   
   onload();
   function onload(){
   	try{
	   var flag='<%=d %>';
	   for(var a=1;a<=flag;a++){	
		   if(document.getElementById('confChkHid'+a).value=="true")
		   {	  
		    document.getElementById('confChk1'+a).checked = true;
		    //alert(document.getElementById('confChk1'+a).value);
		   }
		   else
		   	document.getElementById('confChk1'+a).checked = false;
	   }
	   }catch(e){
	   	//alert(e);
	   }
   }
   
	
</script>
