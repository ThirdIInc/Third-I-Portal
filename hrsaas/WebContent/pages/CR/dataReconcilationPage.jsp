<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>
<STYLE type=text/css>

a:hover{
COLOR:#FF0000;
text-decoration:underline;
}


</STYLE>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="PerformanceMetrics" id="paraFrm" validate="true"
	theme="simple" target="main">
	
<s:hidden name="accountCode" />
<s:hidden name="accountId" />
<s:hidden name="parentFlag" />
<s:hidden name="restoreFlagCheck"/> 
<s:hidden name="contactFlagCheckedCount"/>
<s:hidden name="responseFlagCheckedCount"/>
<s:hidden name="fccFlagCheck"/>
<s:hidden name="etaReportFlagCheck"/>
<s:hidden name="myPage" id="myPage" />
<s:hidden name="myPageDataRecon" id="myPageDataRecon" />
<s:hidden name="dashBoardID" id="dashBoardID" />
<s:hidden name="screenWidth"/>
<s:hidden name="show" />
	
	<div align="center" id="overlay" 
	style="z-index: 3;  position: absolute; width: 1300px;  
		height: 1300px;margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid : DXImageTransform . Microsoft . alpha(opacity = 15); -moz-opacity: .1; opacity: .1;display: none;">
</div>
	
	<div id="confirmationDiv" 
		style='position: absolute; 
		z-index: 3; 100 px; 
		height: 150px; 
		visibility: hidden; 
		top: 300px;  
		left: 300px;background-color: #FFF;'>
	</div>
	
	<table width="100%" align="right" class="formbg" border="0"  style="margin-bottom: 5.8em;">  
		<tr>
			<td valign="bottom" width="100%">
			<table width="100%" align="right" class="formbg" >
				<tr>
					<td ><strong class="text_head"> <img
						src="../pages/images/recruitment/review_shared.gif" 
						height="25" /> </strong> </td>
					<td width="70%" class="txt"><strong class="text_head"> Data Reconciliation  </strong></td>
					<td width="15%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
					<td width="15%" ></td>
				</tr> 
			</table>
			</td>
		</tr>
		<tr>
			<td align="left"><input
				type="button" class="back" value=" Back to list "
				onclick="return backFun();" /></td>
		</tr>
		<tr>
			<td colspan="4"> 
			<table width="100%" class="formbg" >    
				
				<tr>
					<td>
					<table width="100%" border="0">
						<tr>
							<td width="15%" colspan="1" height="22"><label class="set"
								name="business.name" id="business.name"
								ondblclick="callShowDiv(this);"><%=label.get("business.name")%></label>
							:<font color="red"></font></td>
							<td width="50%" colspan="2" height="22"><s:textfield
								theme="simple" name="accountName" size="50" readonly="true"
								cssStyle="background-color: #F2F2F2;" /></td> 
							<s:if test="closedCallSummaryReportFlag">	
							<%-- 	<td align="right">
					<!--<input type="button" class="view" value=" Report"
										onclick="return callViewOtRegister();" />
										
										
					--><b>Export :</b>&nbsp;<a href="#" onclick="callReport('Xls');">
		<img src="../pages/images/buttonIcons/file-xls.png" class="iconImage"
			align="absmiddle" onclick="callReport('Xls');" title="Excel"><span
			style="padding-left: 5px;">Closed Call Summary Report</span></a>					
										
																				</td> --%>
										</s:if>
										
						</tr>
						<tr>
							<td width="15%" class="formtext"><label class="set"
								id="data.reconciliation.from.date" name="data.reconciliation.from.date" ondblclick="callShowDiv(this);"><%=label.get("data.reconciliation.from.date")%></label>:<font
								color="red">*</font></td>
							<td width="50%"><s:textfield name="fromDate" size="10"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<label class="set"
								id="data.reconciliation.to.date" name="data.reconciliation.to.date" ondblclick="callShowDiv(this);"><%=label.get("data.reconciliation.to.date")%></label>:<font
								color="red">*</font><s:hidden name="today" />
								<s:textfield name="toDate" size="10"
								onkeypress="return numbersWithHiphen();" theme="simple"
								maxlength="10" /> <s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"
									class="iconImage" height="16" align="absmiddle" width="16">
							</s:a>
							</td>
							
						</tr>
						<!--Show Calls Type-->
						<tr>
						    <td class="formtext" colspan="1" height="20" class="formtext" width="15%" >
						      	<label  class = "set" name="data.reconciliation.call.type"  id="data.reconciliation.call.type" 
						      	ondblclick="callShowDiv(this);"><%=label.get("data.reconciliation.call.type")%></label><font color="red"></font> :
						     </td>
						     <td>
						     
						     <input type="radio" name="slaTypeRadio" id="missedSla" value="MC"
						
						onclick="setRadioValue(this);"/>Missed SLA
						<input type="radio"  name="slaTypeRadio" id="allCalls"  value="AC"
						onclick="setRadioValue(this);"/>All Calls
						<input type="radio"  name="slaTypeRadio" id="excludeCalls"  value="EC"
						onclick="setRadioValue(this);"/>Exclude Calls
						    	
						     	<s:hidden name="slaTypeHiddenRadio" id="slaTypeHiddenRadio"/>	
						     	
						     </td>
					     </tr>
					     
					</table>
					</td>
				</tr>
			<tr>
				<td >
				<table width="100%" > 	
				<tr align="center"><td>
								<input type="button"
									class="add" value=" Generate List "
									onclick="return callDataReconciliation();" />
									</td>
									<s:if test="viewPublishedFlag">
											
									<td align="right"><input
									type="button" class="back" value=" Publish to client "
									onclick="return isPublishedFun();" /></td>
									
									</s:if>
							</tr>
			  		</table>
			  		</td>
		  		</tr>
			
	
		
			<s:if test="viewDataReconListDtlFlag">
			<tr>
				<td >
				<table width="100%" > 	
			<tr>
			<s:if test="viewPublishedFlag"> 
			
			<td colspan="4" cssStyle="font-size: 1em;"><strong>Note: </strong> Click on Order ID to view Closed Call Details.</td>
	         
			<td align="right" >
				<input
				type="button" class="save" value=" Save "
				onclick="return saveFun();" /></td> 
				</s:if>
			</tr>
			</table>
			</td>
			</tr>
			
		<tr>
			
			<td colspan="5"  width="100%"> 
				<table class="formbg" width="100%" cellspacing="1" cellspadding="0"	border="0">
				
				<tr>
					<td align="left" >
						<strong class="text_head"> Data Reconciliation List </strong>
					</td><td>&nbsp;&nbsp;</td>
					<td align="right">
						<%
							int totalPage = 0;
							int pageNo = 0;
						%>
							<s:if test="dataReconciliationListLength">
								<table>
								<tr>
									<td id="ctrlShow" width="100%" align="right"><b>Page:</b> 
									<%
									totalPage = (Integer) request.getAttribute("totalPage");
									pageNo = (Integer) request.getAttribute("pageNo");
									%> <a href="#"
										onclick="callPage('1', 'F', '<%=totalPage%>', 'PerformanceMetrics_generateDataReconListDtl.action');">
									<img title="First Page" src="../pages/common/img/first.gif"
										width="10" height="10" class="iconImage" /> </a>&nbsp; <a
										href="#"
										onclick="callPage('P', 'P', '<%=totalPage%>', 'PerformanceMetrics_generateDataReconListDtl.action');">
									<img title="Previous Page"
										src="../pages/common/img/previous.gif" width="10" height="10"
										class="iconImage" /> </a> <input type="text" name="pageNoField"
										id="pageNoField" size="3" value="<%=pageNo%>" maxlength="10"
										onkeypress="callPageText(event, '<%=totalPage%>', 'PerformanceMetrics_generateDataReconListDtl.action');return numbersOnly();" />
									of <%=totalPage%> <a href="#"
										onclick="callPage('N', 'N', '<%=totalPage%>', 'PerformanceMetrics_generateDataReconListDtl.action')">
									<img title="Next Page" src="../pages/common/img/next.gif"
										class="iconImage" /> </a>&nbsp; <a href="#"
										onclick="callPage('<%=totalPage%>', 'L', '<%=totalPage%>', 'PerformanceMetrics_generateDataReconListDtl.action');">
									<img title="Last Page" src="../pages/common/img/last.gif"
										width="10" height="10" class="iconImage" /> </a></td>
								</tr>
								</table>
							</s:if>
					</td>
				</tr>
		<tr>
			<td colspan="15">  
			<table width="100%" class="formbg" border="0">      
				<tr class="formth" > 
				<td height="22" width="3%">Sr.No.</td>
					<td height="22" width="8%">Order ID</td>
					<td height="22"width="18%">Customer Name</td>
					<td height="22" width="8%" align="center">Call Date </td>
					<td height="22"width="10%">SLA</td> 
					<td height="22" width="10%">Contact Date</td> 
					<td height="22" width="8%">Disp Date </td>
					<td height="22" width="10%">Onsite Date </td>
					<td height="22" width="10%">Closed Date </td>
					<s:if test="contactFlagCheckedCount">
						<td height="22" width="2%">Contact</td>
					</s:if>
					<s:if test="responseFlagCheckedCount">
						<td height="22" width="2%">Response</td>
					</s:if>
					<s:if test="restoreFlagCheck">
						<td height="22" width="2%">Restore</td>
					</s:if>
					<s:if test="etaReportFlagCheck">
						<td height="22" width="2%">ETA</td>
					</s:if>
					<s:if test="fccFlagCheck">
						<td height="22" width="2%">FCC</td>
					</s:if>
					<td height="22" width="5%">Comments</td>
					
					<td height="22" width="5%">Exclude Call</td>
					
				</tr> 
				<s:if test="dataReconciliationListLength">
				<%!int d2 = 0;%>
				<%
										int i2 = pageNo * 20 - 20;		
																	
									%>
				<%
												int j = 1;
											%>
				<s:iterator value="dataReconciliationList">
				<tr > 
				<td align="center" class="sortableTD" width="3%"><%=++i2%></td>
					<td height="22" width="8%">
					<s:hidden name="orderId"/>
					<a href="#" class="link"  onclick="javascript: showCallDetails('<s:property value="orderId"/>')"><s:property value="orderId"/></a>
					
					
					<s:hidden name="accountChildCode"/>
					  <s:hidden name="perSummDate"/>
					</td>
					<td height="22" width="18%"><s:property value="customerName"/></td>
					<td height="22" align="center"width="8%"><s:property value="callDate"/> <br> <s:property value="callTime"/></td>
					<td height="22" width="10%"><s:property value="sla"/></td> 
					<td height="22" align="center" width="10%"><s:property value="responseDate"/><br/><s:property value="responseTime"/></td>
					<td height="22" align="center"width="8%"><s:property value="dispatchDate"/> <br> <s:property value="dispatchTime"/></td>
					<td height="22" align="center"width="10%"><s:property value="onsiteDate"/> <br> <s:property value="onsiteTime"/></td>
					<td height="22" align="center"width="10%"><s:property value="closedDate"/><s:hidden name="closedDate"/> <br> <s:property value="closedTime"/></td>
					
					<s:if test="%{bean.contactFlagCheckedCount}"> 
						<td height="22" align="center" width="2%">
					</s:if> 
					<s:else>	
						<td height="22" align="center" width="2%" style="display: none;">
					</s:else>
						<input type="checkbox" class="checkbox" name="contactFlag" 
															id="<%="contactFlag"+j%>" 
															onclick="callChecked('contactFlag<%=j%>', 'contactFlag<%=j%>');"/>
						 <input type="hidden" class="text" name="contactFlagHidden" 
															id="<%="contactFlagHidden"+j%>" value='<s:property value="contactFlagHidden" />'/>
						
						<input type="hidden" class="text" name="orgContactFlagHidden" 
															id="<%="orgContactFlagHidden"+j%>" value='<s:property value="orgContactFlagHidden" />'/>
															
															
						</td>
					
				<s:if test="%{bean.responseFlagCheckedCount}">
						<td height="22" align="center" width="2%">
					</s:if> 
					<s:else>	
						<td height="22" align="center" width="2%" style="display: none;">
					</s:else>
						<input type="checkbox" class="checkbox" name="responseFlag" 
															id="<%="responseFlag"+j%>" 
															onclick="callResponseChecked('responseFlag<%=j%>', 'responseFlag<%=j%>');"/>
						<input type="hidden" class="text" name="responseFlagHidden" 
															id="<%="responseFlagHidden"+j%>" value='<s:property value="responseFlagHidden" />'/>
						<input type="hidden" class="text" name="orgResponseFlagHidden" 
															id="<%="orgResponseFlagHidden"+j%>" value='<s:property value="orgResponseFlagHidden" />'/>
						
						</td>
					
					
					<s:if test="%{bean.restoreFlagCheck}">
						<td height="22" align="center" width="2%">
					</s:if> 
					<s:else>	
						<td height="22" align="center" width="2%" style="display: none;">
					</s:else>	
						<input type="checkbox" class="checkbox" name="restoreFlag" 
															id="<%="restoreFlag"+j%>" 
															onclick="callRestoreChecked('restoreFlag<%=j%>', 'restoreFlag<%=j%>');"/>
						<input type="hidden" class="text" name="restoreFlagHidden" 
															id="<%="restoreFlagHidden"+j%>" value='<s:property value="restoreFlagHidden" />'/>
						
						<input type="hidden" class="text" name="orgRestoreFlagHidden" 
															id="<%="orgRestoreFlagHidden"+j%>" value='<s:property value="orgRestoreFlagHidden" />'/>
						
						</td>
					
					
					<s:if test="%{bean.etaReportFlagCheck}">
						<td height="22" align="center" width="2%">
					</s:if>
					<s:else>	
						<td height="22" align="center" width="2%" style="display: none;">
					</s:else>
						<input type="checkbox" class="checkbox" name="etaFlag" 
															id="<%="etaFlag"+j%>" 
															onclick="callEtaChecked('etaFlag<%=j%>', 'etaFlag<%=j%>');"/>
					 	
						<input type="hidden" class="text" name="etaFlagHidden" 
															id="<%="etaFlagHidden"+j%>" value='<s:property value="etaFlagHidden" />'/>
						
						<input type="hidden" class="text" name="orgEtaFlagHidden" 
															id="<%="orgEtaFlagHidden"+j%>" value='<s:property value="orgEtaFlagHidden" />'/>
						
						</td>
					
					
					
					<s:if test="%{bean.fccFlagCheck}">
						<td height="22" align="center" width="2%">
					</s:if>
					<s:else>	
						<td height="22" align="center" width="2%" style="display: none;">
					</s:else>
						<input type="checkbox" class="checkbox" name="fccFlag" 
															id="<%="fccFlag"+j%>" 
															onclick="callFccChecked('fccFlag<%=j%>', 'fccFlag<%=j%>');"/>
					 	
						<input type="hidden" class="text" name="fccFlagHidden" 
															id="<%="fccFlagHidden"+j%>" value='<s:property value="fccFlagHidden" />'/>
						
						<input type="hidden" class="text" name="orgFccFlagHidden" 
															id="<%="orgFccFlagHidden"+j%>" value='<s:property value="orgFccFlagHidden" />'/>
						
						</td>
						
						
						
					
					<td class="sortableTD" width="5%" align="center">
								 <input	type="text" name="commentsDataRecon"  
								 	value="<s:property value="commentsDataRecon"/>"
									 id="<%="commentsDataRecon"+j%>" size="20" maxlength="1000"  />
					
					
					<input type="hidden" class="text" name="slaHrs" 
														id="<%="slaHrs"+j%>" value='<s:property value="slaHrs" />'/>
					
					</td>
					
					
						<td height="22" align="center" width="2%">
						
							<input type="checkbox" class="checkbox" name="excludeCallFlag" 
																id="<%="excludeCallFlag"+j%>" 
																onclick="callExcludeCallChecked('excludeCallFlag<%=j%>', 'excludeCallFlag<%=j%>');"/>
						 	
							<input type="hidden" class="text" name="excludeCallFlagHidden" 
																id="<%="excludeCallFlagHidden"+j%>" value='<s:property value="excludeCallFlagHidden" />'/>
							
						</td>
						
					</tr>
					
					
					<%
									
									
										d2 = i2;
									%>
												<%
					j++;
												%>
												
				</s:iterator>
				<input type="hidden" name="otherLengthVar" id="otherLengthVar" value="<%=j%>"/>
				
				
			<tr class="formbg"><td  colspan="14" width="100%" align="right">
					
					
					
				
			<s:if test="dataReconciliationListLength"><b>Total No. Of Records:</b>&nbsp;<s:property value="totalDataReconRecords" /></s:if></td>
			
			
			</tr>
						
				</s:if>
							
								<s:else>
									<td align="center" colspan="14" nowrap="nowrap">
										<font color="red" >There is no data to display</font>
									</td>
								</s:else>
			</table>
		</td>
	</tr>
	
	<tr>
			<td align="left" colspan="2"><input 
				type="button" class="back" value=" Back to list "
				onclick="return backFun();" /></td>
				&nbsp; <s:if test="viewPublishedFlag">
				<td align="right"><input
				type="button" class="save" value=" Save "
				onclick="return saveFun();" /></td></s:if>
		</tr>	
	</table>
		   </td>
		   </tr>
		   </s:if>
		
		
		
		 
	</table>
	<s:hidden name="hiddenContactFlagCount" id="hiddenContactFlagCount" />
	<s:hidden name="hiddenResponceFlagCount" id="hiddenResponceFlagCount" />
	<s:hidden name="hiddenRestoreFlagCount" id="hiddenRestoreFlagCount" />
	
	<s:hidden name="hiddenOrgContactFlagCount" id="hiddenOrgContactFlagCount" />
	<s:hidden name="hiddenOrgResponceFlagCount" id="hiddenOrgResponceFlagCount" />
	<s:hidden name="hiddenOrgRestoreFlagCount" id="hiddenOrgRestoreFlagCount" />
	
	<s:hidden name="hidden24ContactFlagCount" id="hidden24ContactFlagCount" />
	<s:hidden name="hidden24ResponceFlagCount" id="hidden24ResponceFlagCount" />
	<s:hidden name="hidden24RestoreFlagCount" id="hidden24RestoreFlagCount" />
	
		<s:hidden name="hidden8ContactFlagCount" id="hidden8ContactFlagCount" />
	<s:hidden name="hidden8ResponceFlagCount" id="hidden8ResponceFlagCount" />
	<s:hidden name="hidden8RestoreFlagCount" id="hidden8RestoreFlagCount" />
	
	
	<s:hidden name="hidden4ContactFlagCount" id="hidden4ContactFlagCount" />
	<s:hidden name="hidden4ResponceFlagCount" id="hidden4ResponceFlagCount" />
	<s:hidden name="hidden4RestoreFlagCount" id="hidden4RestoreFlagCount" />
	<s:hidden name="report" />
	
	
</s:form>


<script type="text/javascript">
	
	onLoad();
	
	function setRadioValue(id){
	///alert(id.value);
		document.getElementById('slaTypeHiddenRadio').value =id.value;	
	}
	
	function onLoad() {
			try{ 
			
			var radioValue = document.getElementById('slaTypeHiddenRadio').value;
		
			if(radioValue=='MC'){
				
				document.getElementById('missedSla').checked = true;
				document.getElementById('slaTypeHiddenRadio').value='MC';
			}else if(radioValue=='AC'){
				document.getElementById('allCalls').checked = true;
				document.getElementById('slaTypeHiddenRadio').value='AC';
			}else if(radioValue=='EC'){
				document.getElementById('excludeCalls').checked = true;
				document.getElementById('slaTypeHiddenRadio').value='EC';
			}else {
				
				document.getElementById('missedSla').checked = true;
				document.getElementById('slaTypeHiddenRadio').value='MC';
			}
						
			var count=0;
			var countClient=0;
			var listCount = document.getElementById('otherLengthVar').value;
				for(var i = 1; i < listCount; i++) {
					if(document.getElementById('contactFlagHidden'+i).value=="N" || document.getElementById('contactFlagHidden'+i).value=="null"){ 
					
						document.getElementById('contactFlag'+i).checked = false;
						document.getElementById('contactFlagHidden'+i).value=="N"
					}
					else if(document.getElementById('contactFlagHidden'+i).value=="E"){
					document.getElementById('contactFlag'+i).disabled = true;
					document.getElementById('contactFlagHidden'+i).value=="E"
					}
					else{
						document.getElementById('contactFlag'+i).checked = true;
						
					}
					if(document.getElementById('responseFlagHidden'+i).value=="N" || document.getElementById('responseFlagHidden'+i).value=="null"){
					
						document.getElementById('responseFlag'+i).checked = false;
						document.getElementById('responseFlagHidden'+i).value="N";
					}
					else if(document.getElementById('responseFlagHidden'+i).value=="E"){
						document.getElementById('responseFlag'+i).disabled = true;
						document.getElementById('responseFlagHidden'+i).value="E";
						
					}
					else{
						document.getElementById('responseFlag'+i).checked = true;
						
					}
					
					if(document.getElementById('restoreFlagHidden'+i).value=="N" || document.getElementById('restoreFlagHidden'+i).value=="null"){
					
						document.getElementById('restoreFlag'+i).checked = false;
						document.getElementById('restoreFlagHidden'+i).value="N";
					}
					else if(document.getElementById('restoreFlagHidden'+i).value=="E"){
						document.getElementById('restoreFlag'+i).disabled = true;
						document.getElementById('restoreFlagHidden'+i).value="E";
					}
					
					else{
						document.getElementById('restoreFlag'+i).checked = true;
						
					}
					
					if(document.getElementById('fccFlagHidden'+i).value=="N" || document.getElementById('fccFlagHidden'+i).value=="null"){
						document.getElementById('fccFlag'+i).checked = false;
						document.getElementById('fccFlagHidden'+i).value="N";
					}
					else if(document.getElementById('fccFlagHidden'+i).value=="E"){
						document.getElementById('fccFlag'+i).disabled = true;
						document.getElementById('fccFlagHidden'+i).value="E";
					}
					else{
					
						document.getElementById('fccFlag'+i).checked = true;
						
					}
					
					if(document.getElementById('etaFlagHidden'+i).value=="N" || document.getElementById('etaFlagHidden'+i).value=="null"){
					
						document.getElementById('etaFlag'+i).checked = false;
						document.getElementById('etaFlagHidden'+i).value="N";
					}
					else if(document.getElementById('etaFlagHidden'+i).value=="E"){
					
						document.getElementById('etaFlag'+i).disabled = true;
						document.getElementById('etaFlagHidden'+i).value="E";
						
					}
					else{
						document.getElementById('etaFlag'+i).checked = true;
						
					}
					
					if(document.getElementById('excludeCallFlagHidden'+i).value=="N" || document.getElementById('excludeCallFlagHidden'+i).value=="null"){
					
						document.getElementById('excludeCallFlag'+i).checked = false;
						document.getElementById('excludeCallFlagHidden'+i).value="N";
					}else{
						document.getElementById('excludeCallFlag'+i).checked = true;
						
					}
					
				} 
			}catch(e){
				////alert(e);
			}
		}
		
	
		function showCallDetails(callId) {
		///alert(callId);
		try{
			window.open('','new','top=50,left=50,width=1200,height=680,scrollbars=yes,status=no,resizable=yes');
	 		document.getElementById("paraFrm").target="new";
	 		document.getElementById("paraFrm").action='PerformanceMetrics_getCallDetails.action?callId='+callId; 
	  		document.getElementById("paraFrm").submit();
	  		document.getElementById("paraFrm").target="main";
	  		}catch(e){
	  		///alert(e);
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
					if(document.getElementById('contactFlag'+i).disabled==true){
					clientFlag=clientFlag+1;
						document.getElementById('contactFlagHidden'+i).value="E";
					}
					else if(document.getElementById('contactFlag'+i).checked == true){
						crmFlag=crmFlag+1;
						document.getElementById('contactFlagHidden'+i).value="Y";
					}
					
					
				}
	  	} else {
	  		document.getElementById(field).value="N";
	  		for(var i = 1; i < listCount; i++) {
	  			
	  			if(document.getElementById('contactFlag'+i).disabled==true){
					clientFlag=clientFlag+1;
						document.getElementById('contactFlagHidden'+i).value="E";
					}
					else if(!document.getElementById('contactFlag'+i).checked == true){
					 	document.getElementById('contactFlagHidden'+i).value="N";
					}
				
	  		}
	  	} 
	  	} catch(e){  
	  		////alert(e); 
	  	}
	}
	
	
		function callRestoreChecked(checkBox, field){
		try{
			var count=1;
			var crmFlag=1;
			var clientFlag=1;
		
		var listCount = document.getElementById('otherLengthVar').value;
			if(document.getElementById(checkBox).checked == true){	  
				document.getElementById(field).value="Y";
				for(var i = 1; i < listCount; i++) {
					if(document.getElementById('restoreFlag'+i).disabled==true){
					clientFlag=clientFlag+1;
						document.getElementById('restoreFlagHidden'+i).value="E";
					}
					else if(document.getElementById('restoreFlag'+i).checked == true){
						clientFlag=clientFlag+1;
						document.getElementById('restoreFlagHidden'+i).value="Y";
					}
					
					
				}
	  	} else {
	  		document.getElementById(field).value="N";
	  		for(var i = 1; i < listCount; i++) {
	  			if(document.getElementById('restoreFlag'+i).disabled==true){
					clientFlag=clientFlag+1;
						document.getElementById('restoreFlagHidden'+i).value="E";
					}
					else if(!document.getElementById('restoreFlag'+i).checked == true){
					 	document.getElementById('restoreFlagHidden'+i).value="N";
					}
					
	  		}
	  	} 
	  	} catch(e){  
	  		////alert(e); 
	  	}
	}
	
	
	function callResponseChecked(checkBox, field){
		try{
			var count=1;
			var crmFlag=1;
			var clientFlag=1;
		
		var listCount = document.getElementById('otherLengthVar').value;
		
			
			if(document.getElementById(checkBox).checked == true){	  
				document.getElementById(field).value="Y";
				for(var i = 1; i < listCount; i++) {
					
					if(document.getElementById('responseFlag'+i).disabled==true){
					clientFlag=clientFlag+1;
						document.getElementById('responseFlagHidden'+i).value="E";
					}
					else if(document.getElementById('responseFlag'+i).checked == true){
					
						clientFlag=clientFlag+1;
						document.getElementById('responseFlagHidden'+i).value="Y";
					}
					
					
				}
	  	} else {
	  		document.getElementById(field).value="N";
	  		for(var i = 1; i < listCount; i++) {
	  			if(document.getElementById('responseFlag'+i).disabled==true){
					clientFlag=clientFlag+1;
						document.getElementById('responseFlagHidden'+i).value="E";
					}
	  			
				else if(!document.getElementById('responseFlag'+i).checked == true){
					 	document.getElementById('responseFlagHidden'+i).value="N";
					}
					
	  		}
	  	} 
	  	} catch(e){  
	  		////alert(e); 
	  	}
	}
	
	
	
	function callFccChecked(checkBox, field){
		try{
			var count=1;
			var crmFlag=1;
			var clientFlag=1;
		
		var listCount = document.getElementById('otherLengthVar').value;
			if(document.getElementById(checkBox).checked == true){	  
				document.getElementById(field).value="Y";
				for(var i = 1; i < listCount; i++) {
					if(document.getElementById('fccFlag'+i).disabled==true){
					clientFlag=clientFlag+1;
						document.getElementById('fccFlagHidden'+i).value="E";
					}
					else if(document.getElementById('fccFlag'+i).checked == true){
						clientFlag=clientFlag+1;
						document.getElementById('fccFlagHidden'+i).value="Y";
					}
					
				}
	  	} else {
	  		document.getElementById(field).value="N";
	  		for(var i = 1; i < listCount; i++) {
	  				if(document.getElementById('fccFlag'+i).disabled==true){
					clientFlag=clientFlag+1;
						document.getElementById('fccFlagHidden'+i).value="E";
					}
					else if(!document.getElementById('fccFlag'+i).checked == true){
					 	document.getElementById('fccFlagHidden'+i).value="N";
					}
	  		}
	  	} 
	  	} catch(e){  
	  		////alert(e); 
	  	}
	}
	
	function callEtaChecked(checkBox, field){
		try{
			var count=1;
			var crmFlag=1;
			var clientFlag=1;
		
		var listCount = document.getElementById('otherLengthVar').value;
			if(document.getElementById(checkBox).checked == true){	  
				document.getElementById(field).value="Y";
				for(var i = 1; i < listCount; i++) {
					if(document.getElementById('etaFlag'+i).disabled==true){
					clientFlag=clientFlag+1;
						document.getElementById('etaFlagHidden'+i).value="E";
					}
					else if(document.getElementById('etaFlag'+i).checked == true){
						clientFlag=clientFlag+1;
						document.getElementById('etaFlagHidden'+i).value="Y";
					}
					
					
				}
	  	} else {
	  		document.getElementById(field).value="N";
	  		for(var i = 1; i < listCount; i++) {
	  				if(document.getElementById('etaFlag'+i).disabled==true){
					clientFlag=clientFlag+1;
						document.getElementById('etaFlagHidden'+i).value="E";
					}
					else if(!document.getElementById('etaFlag'+i).checked == true){
					 	document.getElementById('etaFlagHidden'+i).value="N";
					}
	  		}
	  	} 
	  	} catch(e){  
	  		////alert(e); 
	  	}
	}
	
function trimData(str) {     
	if(!str || typeof str != 'string')         
		return null;     
		return str.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' '); 
}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PerformanceMetrics_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function saveFun() {
	 var con=confirm('Do you really want to save Records.?');
	 if(con) {
	 ////checkBoxCount(); 
	 ////orgSysCount(); 
	 ////Checked24Count(); 
	 ////Checked8Count(); 
	 ////Checked4Count(); 
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PerformanceMetrics_saveDataReconciliation.action';
		document.getElementById('paraFrm').submit();
	}
}

	function callDataReconciliation(){
	try{
	
			var radioValue = document.getElementById('slaTypeHiddenRadio').value;
		
			if(radioValue=='MC'){
				
				document.getElementById('missedSla').checked = true;
				document.getElementById('slaTypeHiddenRadio').value='MC';
			}else if(radioValue=='AC'){
				document.getElementById('allCalls').checked = true;
				document.getElementById('slaTypeHiddenRadio').value='AC';
			}else if(radioValue=='EC'){
				document.getElementById('excludeCalls').checked = true;
				document.getElementById('slaTypeHiddenRadio').value='EC';
			}
	
	
		///Date Shift for period start
		var fDate=document.getElementById('paraFrm_fromDate').value;
 		var tDate=document.getElementById('paraFrm_toDate').value;
 
 		if(trim(document.getElementById('paraFrm_fromDate').value) == "") {
				alert("Please select or enter "+document.getElementById('data.reconciliation.from.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
		}
		if(trim(document.getElementById('paraFrm_toDate').value) == "") {
				alert("Please select or enter "+document.getElementById('data.reconciliation.to.date').innerHTML.toLowerCase());
				document.getElementById('paraFrm_toDate').focus();
				return false;
		}
		
		if(!validateDate("paraFrm_fromDate","data.reconciliation.from.date")){
  			return false;
  		
  		}
  	
  		if(!validateDate("paraFrm_toDate","data.reconciliation.to.date")){
		return false;
	}
		
	if(!dateDifferenceEqual(fDate, tDate, 'paraFrm_toDate', 'data.reconciliation.from.date', 'data.reconciliation.to.date')){
	return false;
	}
		
		var fromDate = fDate.split("-");	
		var toDate = tDate.split("-");	
		var newDate = (toDate[0] - fromDate[0])+1; 
		
			if( newDate > 7 ){
				alert("From date and to date difference should not be greater than 7 days.");
				document.getElementById('paraFrm_toDate').value = "";
				document.getElementById('paraFrm_toDate').focus();
				return false;
			}
	
			
		   	document.getElementById("paraFrm").action="PerformanceMetrics_generateDataReconListDtl.action";
		    document.getElementById("paraFrm").submit();
		     document.getElementById('paraFrm').target="_self";
		   
		    }catch(e){
		   //// alert(e);
		    }
	}
	
	function callPage(id, pageImg, totalPageHid, action) {
		
		pageNo = document.getElementById('pageNoField').value;	
		actPage = document.getElementById('myPageDataRecon').value; 
       	if(pageImg != "F" & pageImg != "L") { 
			if(pageNo == "") {
				alert("Please Enter Page Number.");
		        document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(pageNo) <= 0) {
				alert("Page number should be greater than zero.");
			    document.getElementById('pageNoField').value = actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		  	if(Number(totalPageHid) < Number(pageNo)) {
				alert("Page number should not be greater than " + totalPageHid + ".");
				document.getElementById('pageNoField').value=actPage;
			    document.getElementById('pageNoField').focus();
				return false;
			}
		}		    	
       	if(pageImg == "F") {
			if(pageNo == "1") {
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }else{
	        
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			} 
       	}       
		if(pageImg == "L") {
			if(eval(pageNo) == eval(totalPageHid)) {
				alert("This is last page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }else{
	        
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
       	}
       	if(pageImg == "P") {
			if(pageNo == "1") {
				alert("There is no previous page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
	        }else{
	        
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
		}
       	if(pageImg == "N") {
			if(Number(pageNo) == Number(totalPageHid)) {
				alert("There is no next page.");
	         	document.getElementById('pageNoField').focus();
	         	return false;
			}else{
			
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
		}
       	if(id == 'P') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) - 1;
		}
		if(id == 'N') {
			var p = document.getElementById('pageNoField').value;
			id = eval(p) + 1;
		}
		document.getElementById('myPageDataRecon').value = id;
		 
	}
	
	function callPageText(id, totalPage, action){   
		try{
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;
		 	var actPage = document.getElementById('myPageDataRecon').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	         
	         document.getElementById('myPageDataRecon').value=pageNo;
		   
		   if(pageNo==""){
				document.getElementById('paraFrm').action=action;
				document.getElementById('paraFrm').target = '_self';
				document.getElementById('paraFrm').submit();
		   }else{
		   		displayConfirmDiv();
				document.getElementById('confirmationDiv').style.display='block';
			}
		} 
		}catch(e){alert(e);}
	}
	
	function displayConfirmDiv(){
		document.getElementById("confirmationDiv").style.visibility = 'visible';
		document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=150 border=0 class=formbg>'
		+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
 		document.getElementById("overlay").style.display = "block";
	}
	
	function proceedWithSave(){
		try{
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('confirmationDiv').style.display='none';
		document.getElementById("overlay").style.display = "none";
		enableBlockDiv();
		document.getElementById('paraFrm').action="PerformanceMetrics_saveDataReconciliation.action";
		document.getElementById('paraFrm').submit();
		}
		catch(e){} 
	}
	function proceedWithoutSave(){
		try{
		enableBlockDiv();
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById('confirmationDiv').style.display='none';
		document.getElementById("overlay").style.display = "none";
		document.getElementById('paraFrm').action="PerformanceMetrics_generateDataReconListDtl.action";
		document.getElementById('paraFrm').submit();
		}catch(e){}
	}
	
	function cancel(){
		document.getElementById("confirmationDiv").style.visibility = 'hidden';
		document.getElementById("overlay").style.display = "none";
	}

	function enableBlockDiv(){
		try{
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
		}catch(e){}
	  }
	function disableBlockDiv(){
  		try{
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			}catch(e){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
	  }
	  
	 function checkBoxCount(){
	  ///for total contact flag count start
	  	var inputs = document.getElementsByName("contactFlag"); //or document.forms[0].elements;  
		    var cbs = []; //will contain all checkboxes  
		    var checked = []; //will contain all checked checkboxes  
		    for (var i = 0; i < inputs.length; i++) {  
		      if (inputs[i].type == "checkbox") {  
		        cbs.push(inputs[i]);  
		        if (inputs[i].checked) {  
		          checked.push(inputs[i]);  
		        }  
		      }  
		    }  
		    var nbCbs = cbs.length; //number of checkboxes  
		    var nbChecked = checked.length; //number of checked checkboxes  
	        
	       document.getElementById('hiddenContactFlagCount').value = nbChecked;
	     ///for total contact flag count end 
	     
	     ///for total responce flag count start
	  	var inputsResponce = document.getElementsByName("responseFlag"); //or document.forms[0].elements;  
		    var cbsResponce = []; //will contain all checkboxes  
		    var checkedResponce = []; //will contain all checked checkboxes  
		    for (var i = 0; i < inputsResponce.length; i++) {  
		      if (inputsResponce[i].type == "checkbox") {  
		        cbsResponce.push(inputsResponce[i]);  
		        if (inputsResponce[i].checked) {  
		          checkedResponce.push(inputsResponce[i]);  
		        }  
		      }  
		    }  
		    var nbCbsResponce = cbsResponce.length; //number of checkboxes  
		    var nbResponceChecked = checkedResponce.length; //number of checked checkboxes  
	   ///   alert(nbResponceChecked);
	       document.getElementById('hiddenResponceFlagCount').value = nbResponceChecked;
	     ///for total responce flag count end  
	     
	     ///for total restore flag count start
	  	var inputsRestore = document.getElementsByName("restoreFlag"); //or document.forms[0].elements;  
		    var cbsRestore = []; //will contain all checkboxes  
		    var checkedRestore = []; //will contain all checked checkboxes  
		    for (var i = 0; i < inputsRestore.length; i++) {  
		      if (inputsRestore[i].type == "checkbox") {  
		        cbsRestore.push(inputsRestore[i]);  
		        if (inputsRestore[i].checked) {  
		          checkedRestore.push(inputsRestore[i]);  
		        }  
		      }  
		    }  
		    var nbCbsRestore = cbsRestore.length; //number of checkboxes  
		    var nbRestoreChecked = checkedRestore.length; //number of checked checkboxes  
	     /// alert(nbRestoreChecked);
	       document.getElementById('hiddenRestoreFlagCount').value = nbRestoreChecked;
	     ///for total restore flag count end  
	  }
	  
	  function orgSysCount(){
	  	// Contact Flag start
	 try{
	 var inputsContact = document.getElementsByName("orgContactFlagHidden"); //or document.forms[0].elements;  
		   
		    var checkedContact = []; //will contain all checked checkboxes  
		    for (var i = 0; i < inputsContact.length; i++) {  
		      if (inputsContact[i].type == "text" && inputsContact[i].value=='Y') {  
		       
		        if (inputsContact[i].value) {  
		          checkedContact.push(inputsContact[i]);  
		        }  
		      }  
		    }   
	  var nbContactChecked = checkedContact.length; //number of checked checkboxes  
	 ///   alert(nbRestoreChecked); 
	    document.getElementById('hiddenOrgContactFlagCount').value = nbContactChecked; 
	     }catch(e){
	    	///alert(e); 
	    }
	// Contact Flag end
	
	 // Response Flag start
	 try{
	 var inputsResponce = document.getElementsByName("orgResponseFlagHidden"); //or document.forms[0].elements;  
		   
		    var checkedResponce = []; //will contain all checked checkboxes  
		    for (var i = 0; i < inputsResponce.length; i++) {  
		      if (inputsResponce[i].type == "text" && inputsResponce[i].value=='Y') {  
		       
		        if (inputsResponce[i].value) {  
		          checkedResponce.push(inputsResponce[i]);  
		        }  
		      }  
		    }  
	  var nbResponceChecked = checkedResponce.length; //number of checked checkboxes  
	    ///alert(nbResponceChecked); 
	    document.getElementById('hiddenOrgResponceFlagCount').value = nbResponceChecked; 
	    }catch(e){
	    	///alert(e); 
	    }
	// Response Flag end  
	// Restore Flag start
	 var inputsRestore = document.getElementsByName("orgRestoreFlagHidden"); //or document.forms[0].elements;  
		   
		    var checkedRestore = []; //will contain all checked checkboxes   
		    for (var i = 0; i < inputsRestore.length; i++) {  
		      if (inputsRestore[i].type == "text" && inputsRestore[i].value=='Y') {  
		       
		        if (inputsRestore[i].value) {  
		          checkedRestore.push(inputsRestore[i]);  
		        }  
		      }  
		    }   
	  var nbRestoreChecked = checkedRestore.length; //number of checked checkboxes  
	   /// alert(nbRestoreChecked);  
	    document.getElementById('hiddenOrgRestoreFlagCount').value = nbRestoreChecked; 
	// Restore Flag end
	  }
	  
	  function Checked24Count(){
	  	// Contact Flag start
	 try{
	 var inputsContact = document.getElementsByName("slaHrs"); //or document.forms[0].elements;  
		var inputsContactFlagContact = document.getElementsByName("contactFlagHidden");   
		    var checkedContact = []; //will contain all checked checkboxes  
		    for (var i = 0; i < inputsContact.length; i++) {  
		      if (inputsContact[i].type == "text" && inputsContact[i].value=='24' && inputsContactFlagContact[i].value=='Y') {  
		       
		        if (inputsContact[i].value) {  
		          checkedContact.push(inputsContact[i]);  
		        }  
		      }  
		    }   
	  var nbContactChecked = checkedContact.length; //number of checked checkboxes  
	 ///   alert(nbRestoreChecked); 
	    document.getElementById('hidden24ContactFlagCount').value = nbContactChecked; 
	     }catch(e){
	    	////alert(e);  
	    }
	// Contact Flag end
	  
	   	// Responce Flag start
	 try{
	 var inputsResponce = document.getElementsByName("slaHrs"); //or document.forms[0].elements;  
		var inputsResponceFlagContact = document.getElementsByName("responseFlagHidden");   
		    var checkedResponce = []; //will contain all checked checkboxes  
		    for (var i = 0; i < inputsContact.length; i++) {  
		      if (inputsResponce[i].type == "text" && inputsResponce[i].value=='24' && inputsResponceFlagContact[i].value=='Y') {  
		       
		        if (inputsResponce[i].value) {  
		          checkedResponce.push(inputsResponce[i]);  
		        }  
		      }  
		    }   
	  var nbResponceChecked = checkedResponce.length; //number of checked checkboxes  
	 ///   alert(nbResponceChecked); 
	    document.getElementById('hidden24ResponceFlagCount').value = nbResponceChecked; 
	     }catch(e){
	    	////alert(e);  
	    }
	// Responce Flag end
	
	  }
	  
	
	 function Checked8Count(){
	  	// Contact Flag start
	 try{
	 var inputsContact = document.getElementsByName("slaHrs"); //or document.forms[0].elements;  
		var inputsContactFlagContact = document.getElementsByName("contactFlagHidden");   
		    var checkedContact = []; //will contain all checked checkboxes  
		    for (var i = 0; i < inputsContact.length; i++) {  
		      if (inputsContact[i].type == "text" && inputsContact[i].value=='8' && inputsContactFlagContact[i].value=='Y') {  
		       
		        if (inputsContact[i].value) {  
		          checkedContact.push(inputsContact[i]);  
		        }  
		      }  
		    }   
	  var nbContactChecked = checkedContact.length; //number of checked checkboxes  
	 ///   alert(nbRestoreChecked); 
	    document.getElementById('hidden8ContactFlagCount').value = nbContactChecked; 
	     }catch(e){
	    	////alert(e);  
	    }
	// Contact Flag end
	  
	   	// Responce Flag start
	 try{
	 var inputsResponce = document.getElementsByName("slaHrs"); //or document.forms[0].elements;  
		var inputsResponceFlagContact = document.getElementsByName("responseFlagHidden");   
		    var checkedResponce = []; //will contain all checked checkboxes  
		    for (var i = 0; i < inputsContact.length; i++) {  
		      if (inputsResponce[i].type == "text" && inputsResponce[i].value=='8' && inputsResponceFlagContact[i].value=='Y') {  
		       
		        if (inputsResponce[i].value) {  
		          checkedResponce.push(inputsResponce[i]);  
		        }  
		      }  
		    }   
	  var nbResponceChecked = checkedResponce.length; //number of checked checkboxes  
	 ///   alert(nbResponceChecked); 
	    document.getElementById('hidden8ResponceFlagCount').value = nbResponceChecked; 
	     }catch(e){
	    	////alert(e);  
	    }
	// Responce Flag end
	
	  }
	  
	  function Checked4Count(){
	  	// Contact Flag start
	 try{
	 var inputsContact = document.getElementsByName("slaHrs"); //or document.forms[0].elements;  
		var inputsContactFlagContact = document.getElementsByName("contactFlagHidden");   
		    var checkedContact = []; //will contain all checked checkboxes  
		    for (var i = 0; i < inputsContact.length; i++) {  
		      if (inputsContact[i].type == "text" && inputsContact[i].value=='4' && inputsContactFlagContact[i].value=='Y') {  
		       
		        if (inputsContact[i].value) {  
		          checkedContact.push(inputsContact[i]);  
		        }  
		      }  
		    }   
	  var nbContactChecked = checkedContact.length; //number of checked checkboxes  
	 ///   alert(nbRestoreChecked); 
	    document.getElementById('hidden4ContactFlagCount').value = nbContactChecked; 
	     }catch(e){
	    	////alert(e);  
	    }
	// Contact Flag end
	  
	   	// Responce Flag start
	 try{
	 var inputsResponce = document.getElementsByName("slaHrs"); //or document.forms[0].elements;  
		var inputsResponceFlagContact = document.getElementsByName("responseFlagHidden");   
		    var checkedResponce = []; //will contain all checked checkboxes  
		    for (var i = 0; i < inputsContact.length; i++) {  
		      if (inputsResponce[i].type == "text" && inputsResponce[i].value=='4' && inputsResponceFlagContact[i].value=='Y') {  
		       
		        if (inputsResponce[i].value) {  
		          checkedResponce.push(inputsResponce[i]);  
		        }  
		      }  
		    }   
	  var nbResponceChecked = checkedResponce.length; //number of checked checkboxes  
	 ///   alert(nbResponceChecked); 
	    document.getElementById('hidden4ResponceFlagCount').value = nbResponceChecked; 
	     }catch(e){
	    	///alert(e);  
	    }
	// Responce Flag end
	
	  }
	   
	  function isPublishedFun(){
	  var fromDateVar = trim(document.getElementById('paraFrm_fromDate').value);
	  var toDateVar = trim(document.getElementById('paraFrm_toDate').value);
	 
	  	 var con=confirm("It will publish data from " +fromDateVar+ " to " +toDateVar+ ". Do you really want to proceed.?");
	 if(con) {
	 
	 	document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'PerformanceMetrics_publishData.action';
		document.getElementById('paraFrm').submit();
	}
	  }
	  
	  
	  function callReport(type){
	
		document.getElementById('paraFrm_report').value=type;
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action ='PerformanceMetrics_getReport.action';
		document.getElementById('paraFrm').submit();
	}
	
	function callExcludeCallChecked(checkBox, field){
		try{
			var count=1;
			var crmFlag=1;
			var clientFlag=1;
		
		var listCount = document.getElementById('otherLengthVar').value;
			if(document.getElementById(checkBox).checked == true){	  
				document.getElementById(field).value="Y";
				for(var i = 1; i < listCount; i++) {
					
					if(document.getElementById('excludeCallFlag'+i).checked == true){
						clientFlag=clientFlag+1;
						document.getElementById('excludeCallFlagHidden'+i).value="Y";
					}
					
				}
	  	} else {
	  		document.getElementById(field).value="N";
	  		for(var i = 1; i < listCount; i++) {
	  		
					if(!document.getElementById('excludeCallFlag'+i).checked == true){
					 	document.getElementById('excludeCallFlagHidden'+i).value="N";
					}
	  		}
	  	} 
	  	} catch(e){  
	  		////alert(e); 
	  	}
	}
	  
</script>
