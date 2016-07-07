<!-- @author: Prajakta Bhandare 
	 @ Date: 26 Dec 2012-->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="LeaveBalance" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="isNotGeneralUser" />
	<s:hidden name="editFlag" />
	<s:hidden name="empId" />
	<s:hidden name="empName"/>
	<s:hidden name="hourFlag"/>
	
	<div style="float: left; width: 100%">
	<table width="98%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="middle">
					<fieldset><legend class="legend"> <img
						src="../pages/mypage/images/icons/leave16.png" width="16"
						height="16" />&nbsp;&nbsp;Leave Balance </legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0">
								<tr>
									<td width="100%">
									<table width="100" border="0" align="right" cellpadding="2"
										cellspacing="3">
										<s:if test="editFlag">
											<tr align="right">
												<s:if test="%{leaveBal.updateFlag}">

													<td width="93%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="1%">|</td>
												</s:if>

												<td width="2%"><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
												<td width="3%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
												<td>|</td>
											</tr>
										</s:if>
										<s:else>
											<tr align="right">
												<s:if test="%{leaveBal.updateFlag}">
													<td width="93%"><a href="#"><img
														src="../pages/mypage/images/icons/edit.png"
														onclick="editFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="editFun()"
														class="iconbutton">Edit</a></td>
													<td width="1%">|</td>
												</s:if>
												<s:if test="isNotGeneralUser">
													<td width="100%" align="right"><a href="#"><img
														src="../pages/mypage/images/icons/search.png"
														onclick="searchFun()" width="10" height="10" border="0" /></a></td>
													<td align="right"><a href="#" onclick="searchFun()"
														class="iconbutton">Search</a></td>
														<td>|</td>
												</s:if>
											</tr>
										</s:else>
									</table>
									</td>
								</tr>
								<tr>
									<td height="1" bgcolor="#cccccc" class="style1"></td>
								</tr>
								<tr>
									<td colspan="4">
									<fieldset><legend class="legend1">Balance
									Details</legend>
									<table width="100%" border="0" align="right" cellpadding="1"
										cellspacing="1">
										<tr>
											<td>
											<table width="100%" border="0" align="right" cellpadding="1"
												cellspacing="1">
												<s:if test="editFlag">
												<%!int z = 0;%>
													<s:if test="hourFlag">
														<tr>
															<td colspan="7">
															<table width="100%" border="0" cellpadding="1"
																cellspacing="1" >
																<tr>
																	<td width="29%"
																		bgcolor="#EEF4FB" align="center"><label
																		name="leavtype" id="leavtype"
																		ondblclick="callShowDiv(this);"><%=label.get("leavtype")%></label></td>
																	<td colspan="2" 
																		bgcolor="#EEF4FB" align="center"><label
																		name="leaventitle" id="leaventitle"
																		ondblclick="callShowDiv(this);"><%=label.get("leaventitle")%></label></td>
																	<td colspan="2" 
																		bgcolor="#EEF4FB" align="center"><label
																		name="availbal" id="availbal"
																		ondblclick="callShowDiv(this);"><%=label.get("availbal")%></label></td>
																	<td colspan="2" 
																		bgcolor="#EEF4FB" align="center"><label
																		name="onholdbal" id="onholdbal"
																		ondblclick="callShowDiv(this);"><%=label.get("onholdbal")%></label></td>
																</tr>

																<tr>
																	<td width="29%"></td>
																	<td   bgcolor="#EEF4FB"
																		align="center"><label
																		name="days" id="days"
																		ondblclick="callShowDiv(this);"><%=label.get("days")%></label></td>
																	<td   bgcolor="#EEF4FB"
																		align="center"><label
																		name="time" id="time"
																		ondblclick="callShowDiv(this);"><%=label.get("time")%></label></td>
																	<td   bgcolor="#EEF4FB"
																		align="center"><label
																		name="days" id="days"
																		ondblclick="callShowDiv(this);"><%=label.get("days")%></label></td>
																	<td   bgcolor="#EEF4FB"
																		align="center"><label
																		name="time" id="time"
																		ondblclick="callShowDiv(this);"><%=label.get("time")%></label></td>
																	<td  bgcolor="#EEF4FB"
																		align="center"><label
																		name="days" id="days"
																		ondblclick="callShowDiv(this);"><%=label.get("days")%></label></td>
																	<td   bgcolor="#EEF4FB"
																		align="center"><label
																		name="time" id="time"
																		ondblclick="callShowDiv(this);"><%=label.get("time")%></label></td>
																</tr>
																
																<%
																																int a = 1;
																																%>

																<s:iterator value="leaveList" status="stat" id="lv">

																	<tr class="sortableTD">
																		<s:hidden name="leaveCode" value="%{leaveCode}" />
																		<td width="29%" nowrap="nowrap" class="text1"
																			align="left"><s:property value="leaveName" /></td>
																		<td class="text1" width="11%" align="center"><input
																			type="text" size="10" name="opBal" maxlength="5"
																			value='<s:property value="opBal"/>'
																			style="text-align: right;" id="opBal<%=a %>"
																			theme="simple" onkeypress="return numbersWithDot();" /></td>

																		<td class="text1" width="11%" align="left">
																		<input type="text" size="10" name="opBalHrs"
																			value='<s:property value="opBalHrs"/>'
																			style="text-align: right;" id="opBalHrs<%=a %>"
																			theme="simple" maxlength="5"
																			onkeypress="return numbersWithColon();" /></td>

																		<td width="11%" align="center" class="text1"><input
																			type="text" size="10" name="clBal" maxlength="5"
																			value='<s:property value="clBal"/>'
																			style="text-align: right;" id="clBal<%=a %>"
																			theme="simple" onkeypress="return numbersWithDot();" />
																		<input type="hidden"
																			value='<s:property value="clBal"/>'
																			id="hiddenclBal<%=a %>" /></td>

																		<td width="11%" align="left" class="text1">
																		<input type="text" size="10" name="clBalHrs"
																			value='<s:property value="clBalHrs"/>'
																			style="text-align: right;" id="clBalHrs<%=a %>"
																			theme="simple" maxlength="5"
																			onkeypress="return numbersWithColon();" /> <input
																			type="hidden" id="hiddenclBalHrs<%=a %>"
																			value='<s:property value="clBalHrs"/>' /></td>

																		<td width="11%" align="center" class="text1"><input
																			type="text" size="11" readonly="readonly"
																			name="onholdBal"
																			style="background-color: #F2F2F2; text-align: right;"
																			value='<s:property value="onholdBal"/>'
																			id="onholdBal<%=a %>" theme="simple"
																			onkeypress="return numbersWithDot();" /></td>

																		<td width="11%" align="left" class="text1">
																		<input type="text" size="11" readonly="readonly"
																			name="onholdBalHrs"
																			style="background-color: #F2F2F2; text-align: right;"
																			value='<s:property value="onholdBalHrs"/>'
																			id="onholdBalHrs<%=a %>" theme="simple" maxlength="5"
																			onkeypress="return numbersWithColon();" /></td>

																	</tr>
																	<%
																	a++;
																	%>
																</s:iterator>

																<tr>
																	<%
																	z = a;
																	%>
																	
																</tr>
															</table>
															</td>
														</tr>
													</s:if>
													<s:else>
														<tr>
															<td width="25%" bgcolor="#EEF4FB" align="center"><label
																name="leavtype" id="leavtype"
																ondblclick="callShowDiv(this);"><%=label.get("leavtype")%></label>
															</td>
															<td width="25%" bgcolor="#EEF4FB" align="center"><label
																name="leaventitle" id="leaventitle"
																ondblclick="callShowDiv(this);"><%=label.get("leaventitle")%></label>
															</td>
															<td width="25%" bgcolor="#EEF4FB" align="center"><label
																name="availbal" id="availbal"
																ondblclick="callShowDiv(this);"><%=label.get("availbal")%></label>
															</td>
															<td width="25%" bgcolor="#EEF4FB" align="center"><label
																name="onholdbal" id="onholdbal"
																ondblclick="callShowDiv(this);"><%=label.get("onholdbal")%></label>
															</td>
														</tr>
														<%
														int y = 1;
														%>

														<s:iterator value="leaveList" status="stat">
															<tr class="sortableTD">
																<s:hidden name="leaveCode" value="%{leaveCode}" />
																<td class="text1" width="30%" nowrap="nowrap"
																	align="left"><s:property value="leaveName" /></td>
																<td class="text1" width="23%" align="center"><input
																	type="text" name="opBal" maxlength="5"
																	value='<s:property value="opBal"/>'
																	style="text-align: right;" id="opBal<%=y %>"
																	theme="simple" onkeypress="return numbersWithHiphen();" />
																<input type="hidden" name="opBalHrs"
																	value='<s:property value="opBalHrs"/>'
																	id="opBalHrs<%=y %>" /></td>
																<td class="text1" width="23%" align="center"><input
																	type="text" name="clBal" maxlength="5"
																	value='<s:property value="clBal"/>'
																	style="text-align: right;" id="clBal<%=y %>"
																	theme="simple" onkeypress="return numbersWithHiphen();" />
																<input type="hidden" value='<s:property value="clBal"/>'
																	id="hiddenclBal<%=y %>" /> <input type="hidden"
																	name="clBalHrs" value='<s:property value="clBalHrs"/>'
																	id="clBalHrs<%=y %>" /></td>
																<td class="text1" width="23%" align="center"><input
																	type="text" readonly="readonly" name="onholdBal"
																	style="background-color: #F2F2F2; text-align: right;"
																	value='<s:property value="onholdBal"/>'
																	id="onholdBal<%=y %>" theme="simple"
																	onkeypress="return numbersWithHiphen();" /> <input
																	type="hidden" name="onholdBalHrs"
																	value='<s:property value="onholdBalHrs"/>'
																	id="onholdBalHrs<%=y %>" /></td>

															</tr>

															<%
															y++;
															%>
														</s:iterator>
														<%
														z = y;
														%>
													</s:else>
												</s:if>
												<s:else>
													<s:if test="hourFlag">
														<tr>
															<td colspan="7">
															<table width="100%" border="0" cellpadding="1"
																cellspacing="1" class="sortable">
																<tr>
																	<td width="29%" valign="top" 
																		bgcolor="#EEF4FB" align="center"><label
																		name="leavtype" id="leavtype"
																		ondblclick="callShowDiv(this);"><%=label.get("leavtype")%></label></td>
																	<td height="27" colspan="2" valign="top" 
																		bgcolor="#EEF4FB" align="center"><label
																		name="leaventitle" id="leaventitle"
																		ondblclick="callShowDiv(this);"><%=label.get("leaventitle")%></label></td>
																	<td colspan="2" valign="top" 
																		bgcolor="#EEF4FB" align="center"><label
																		name="availbal" id="availbal"
																		ondblclick="callShowDiv(this);"><%=label.get("availbal")%></label></td>
																	<td colspan="2" valign="top" 
																		bgcolor="#EEF4FB" align="center"><label
																		name="onholdbal" id="onholdbal"
																		ondblclick="callShowDiv(this);"><%=label.get("onholdbal")%></label></td>
																</tr>

																<tr>
																	<td width="29%"></td>
																	<td   bgcolor="#EEF4FB"
																		align="center"><label
																		name="days" id="days"
																		ondblclick="callShowDiv(this);"><%=label.get("days")%></label></td>
																	<td   bgcolor="#EEF4FB"
																		align="center"><label
																		name="time" id="time"
																		ondblclick="callShowDiv(this);"><%=label.get("time")%></label></td>
																	<td   bgcolor="#EEF4FB"
																		align="center"><label
																		name="days" id="days"
																		ondblclick="callShowDiv(this);"><%=label.get("days")%></label></td>
																	<td   bgcolor="#EEF4FB"
																		align="center"><label
																		name="time" id="time"
																		ondblclick="callShowDiv(this);"><%=label.get("time")%></label></td>
																	<td  bgcolor="#EEF4FB"
																		align="center"><label
																		name="days" id="days"
																		ondblclick="callShowDiv(this);"><%=label.get("days")%></label></td>
																	<td   bgcolor="#EEF4FB"
																		align="center"><label
																		name="time" id="time"
																		ondblclick="callShowDiv(this);"><%=label.get("time")%></label></td>
																</tr>
																

																<s:iterator value="leaveList" status="stat">

																	<tr class="sortableTD">
																		<s:hidden name="leaveCode" value="%{leaveCode}" />
																		<td width="29%" nowrap="nowrap" class="text1"
																			align="left"  ><s:property value="leaveName" /></td>
																		<td class="text1" width="9%" align="right"  ><s:property
																			value="opBal" /></td>
																		<td class="text1" width="12%" align="right"  >
																		<s:property value="opBalHrs" /></td>

																		<td width="12%" align="right" class="text1"  ><s:property
																			value="clBal" /></td>

																		<td width="11%" align="right" class="text1"  >
																		<s:property value="clBalHrs" /></td>

																		<td width="11%" align="right" class="text1"  >
																		<s:property value="onholdBal" /></td>

																		<td width="16%" align="right" class="text1" >
																		<s:property value="onholdBalHrs" /></td>

																	</tr>
																	
																</s:iterator>

																<tr>
																	
																</tr>
															</table>
															</td>
														</tr>
														<s:if test="noData">
															<tr>
																<td colspan="4" align="center"><font color="red">No
																data to display</font></td>
															</tr>
														</s:if>
													</s:if>
													<s:else>
														<tr>
															<td width="25%" bgcolor="#EEF4FB" align="center"><label
																name="leavtype" id="leavtype"
																ondblclick="callShowDiv(this);"><%=label.get("leavtype")%></label>
															</td>
															<td width="25%" bgcolor="#EEF4FB" align="center"><label
																name="leaventitle" id="leaventitle"
																ondblclick="callShowDiv(this);"><%=label.get("leaventitle")%></label>
															</td>
															<td width="25%" bgcolor="#EEF4FB" align="center"><label
																name="availbal" id="availbal"
																ondblclick="callShowDiv(this);"><%=label.get("availbal")%></label>
															</td>
															<td width="25%" bgcolor="#EEF4FB" align="center"><label
																name="onholdbal" id="onholdbal"
																ondblclick="callShowDiv(this);"><%=label.get("onholdbal")%></label>
															</td>
														</tr>
														
														<s:iterator value="leaveList" status="stat" id="leave">
															<tr class="sortableTD">
																<s:hidden name="leaveCode" value="%{leaveCode}" />
																<td class="text1" width="30%" nowrap="nowrap"
																	align="left" ><s:property value="leaveName" /></td>
																<td class="text1" width="23%" align="right"  ><s:property value="opBal"/>
																	<s:hidden value="opBalHrs"/>
																</td>
																<td class="text1" width="23%" align="right"  ><s:property value="clBal"/>
															<s:hidden value="clBalHrs"/></td>
																<td class="text1" width="23%" align="right">
																	<s:property value="onholdBal"/><s:hidden value="onholdBalHrs"/></td>

															</tr>

															
														</s:iterator>
														<s:if test="noData">
															<tr>
																<td colspan="4" align="center"><font color="red">No
																data to display</font></td>
															</tr>
														</s:if>
													</s:else>
												</s:else>
											</table>
											</td>
										</tr>
									</table>
									</fieldset>
									</td>
								</tr>
								<tr height="10"></tr>
								<tr>
									<td height="1px" bgcolor="#cccccc" class="style1"></td>
								</tr>
								<tr>
									<td width="100%">
									<table width="100" border="0" align="right" cellpadding="2"
										cellspacing="3">
										<s:if test="editFlag">
											<tr align="right">
												<s:if test="%{leaveBal.updateFlag}">

													<td width="93%"><a href="#"><img
														src="../pages/mypage/images/icons/save.png"
														onclick="saveFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="saveFun()"
														class="iconbutton">Save</a></td>
													<td width="1%">|</td>
												</s:if>
												<td width="2%"><a href="#"><img
													src="../pages/mypage/images/icons/cancel.png"
													onclick="cancelFun()" width="10" height="10" border="0" /></a></td>
												<td width="3%"><a href="#" onclick="cancelFun()"
													class="iconbutton">Cancel</a></td>
												<td>|</td>
											</tr>
										</s:if>
										<s:else>
											<tr align="right">
												<s:if test="%{leaveBal.updateFlag}">
													<td width="93%"><a href="#"><img
														src="../pages/mypage/images/icons/edit.png"
														onclick="editFun()" width="10" height="10" border="0" /></a></td>
													<td width="2%"><a href="#" onclick="editFun()"
														class="iconbutton">Edit</a></td>
													<td width="1%">|</td>
												</s:if>
												<s:if test="isNotGeneralUser">
													<td width="100%" align="right"><a href="#"><img
														src="../pages/mypage/images/icons/search.png"
														onclick="searchFun()" width="10" height="10" border="0" /></a></td>
													<td align="right"><a href="#" onclick="searchFun()"
														class="iconbutton">Search</a></td>
													<td>|</td>
												</s:if>
											</tr>
										</s:else>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						
						
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	</div>
</s:form>

<script>
function editFun(){
		document.getElementById('paraFrm').target="main";
		document.getElementById("paraFrm").action="LeaveBalance_edit.action";
		document.getElementById("paraFrm").submit();
}

function searchFun(){
	javascript:callsF9(500,325,'LeaveBalance_f9action.action')
}

function cancelFun(){
		document.getElementById('paraFrm').target="main";
		document.getElementById("paraFrm").action="LeaveBalance_cancel.action";
		document.getElementById("paraFrm").submit();
}

function saveFun(){
		var avail=document.getElementById('availbal').innerHTML.toLowerCase()+"time";
		var lventitle=document.getElementById('leaventitle').innerHTML.toLowerCase()+"time";
		var hourFlag = document.getElementById('paraFrm_hourFlag').value;
			var flag='<%=z %>';
			try{
		  	for(var a=1;a<=flag;a++){	
				var opBalHrs = trim(document.getElementById('opBalHrs'+a).value);
				if(opBalHrs!=""){
					if(!validateTime('opBalHrs'+a, 'leaventitle'))return false;
				}
				var clBalHrs = trim(document.getElementById('clBalHrs'+a).value);
				if(clBalHrs!=""){
					if(!validateTime('clBalHrs'+a, 'availbal'))return false;
				}
				
			}
			}catch(e){
			}
		return calculate();
		
}

function calculate()
{
	  var value_length='<%=z%>'; 
	for(var p=1;p<value_length;p++){
		var  open = document.getElementById('opBal'+p).value;
		var close = document.getElementById('clBal'+p).value;
			var n=new Number(close);
		var str=n.toString();
		
		if(str=='NaN'){
	
			alert("Please enter valid data in "+document.getElementById('availbal').innerHTML.toLowerCase());
			
			return false;
		}
		
		var num = new Number(open)
		var str1 = num.toString();
		
		if(str1=='NaN')
		{
			alert("Please enter valid data in "+document.getElementById('leaventitle').innerHTML.toLowerCase());
			return false;
		}
				  
	}
	 for(var q=1;q<value_length;q++)
		 {
					var  openBal = document.getElementById('opBal'+q).value;
				  var closeBal = document.getElementById('clBal'+q).value;
				
					 if(eval(closeBal)>eval(openBal))
					 {
					 	 document.getElementById('clBal'+q).value= document.getElementById('hiddenclBal'+q).value;
					 	alert(" "+document.getElementById('availbal').innerHTML.toLowerCase()+" Should not be greater than "+document.getElementById('leaventitle').innerHTML.toLowerCase()+" ");
					 	return false;
					 }
	 
			}
	
		document.getElementById('paraFrm').target="main";
		document.getElementById("paraFrm").action="LeaveBalance_save.action";
		document.getElementById("paraFrm").submit();
	}

</script>
