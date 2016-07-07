<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>


<s:form action="PromotionReportAction" id="paraFrm" theme="simple">
	<table class="formbg" width="100%" cellpadding="2" cellspacing="1">
		
<s:hidden name="recordsPerPage" value="20"/>
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Promotion
					 Letter </strong></td>
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
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="70%" colspan="3">
							<s:if test="pramotion.generalFlag">
							<input type="button" class="search" value="  Search"
								onclick="javascript:callsF9(500,325,'PromotionReportAction_f9emp.action'); " />
								<input type="button" class="search" value="  Generate Letter"
								onclick="callUrlFun(); " />
							<input type="button" class="token"
									onclick="return callSendLetter();" value="Send Mail" />
							</s:if>
									<s:else>
									<input type="button" class="search" value="  Generate Letter"
								onclick="callUrlFun(); " />
							<input type="button" class="token"
									onclick="return callSendLetter();" value="Send Mail" />
							<s:submit cssClass="reset" action="PromotionReportAction_reset"
								theme="simple" value="    Reset" />
									</s:else>
							<!-- <input type="button" class="search" value="  Search"
								onclick="javascript:callsF9(500,325,'PromotionReportAction_f9emp.action'); " />
								
							<s:if test="%{viewFlag}">
								<input type="button" class="token"
									onclick="return callPromotionLetter();" value="Generate Letter" />
							</s:if>  -->
							</td>

							<td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					<label></label></td>

				</tr>
				
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2" >
								<tr>
									<td width="15%" colspan="1"><label class="set"
										name="employee" id="employee"
										ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><font color="red" >* </font>
									:</td>
									<td width="85%" colspan="4"><s:textfield name="empToken"
										size="20" value="%{empToken}" readonly="true" /> <s:textfield
										name="empName" size="70" value="%{empName}" readonly="true" />
									<s:hidden name="empCode" value="%{empCode}" /><s:hidden name="empMailId"/>
									<s:if test="pramotion.generalFlag"></s:if>
									<s:else>
										<img src="../pages/images/search2.gif"
											height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callsF9(500,325,'PromotionReportAction_f9emp.action'); ">
									</s:else>
							</td>
								</tr>
								
								<tr>
									<td width="15%" colspan="1"><label class="set"
										name="promotion.eff.date" id="promotion.eff.date"
										ondblclick="callShowDiv(this);"><%=label.get("promotion.eff.date")%></label><font color="red" >* </font>:</td>
										
										<s:if test="pramotion.generalFlag">
										<td width="35%" colspan="1"><s:textfield
										 size="10" theme="simple" name="effDate" readonly="true" onkeypress="return numbersWithHiphen();" maxlength="10"/></td>
										
										</s:if>
										<s:else>
									<td width="35%" colspan="1"><s:textfield
										 size="10" theme="simple" name="effDate"  onkeypress="return numbersWithHiphen();" maxlength="10"/><s:a
									href="javascript:NewCal('paraFrm_effDate','DDMMYYYY');">


									<img class="iconImage" id="ctrlHide"
										src="../pages/images/recruitment/Date.gif" width="16"
										height="16" border="0" align="absmiddle" />
								</s:a></td>
									</s:else>
									
								</tr>
								
								<!-- <s:hidden name="tempCode"/>-->
								
				 <tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="templateName" name="templateName" ondblclick="callShowDiv(this);"><%=label.get("templateName")%></label> <font
								color="red">*</font> :<s:hidden name="tempCode"/></td>
						<td width="30%" colspan="2"><s:textfield
								name="tempName" size="30" theme="simple"
								readonly="true" />
								<s:if test="pramotion.generalFlag"></s:if>
									<s:else>
										<img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callsF9(500,325,'PromotionReportAction_f9actionTemplate.action'); ">
							</s:else>
							</td>
										
									
														
				</tr>
				
				<s:if test="pramotion.generalFlag"></s:if>
									<s:else>
										<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="divisionName" name="divisionName" ondblclick="callShowDiv(this);"><%=label.get("promotion.division")%></label> <font
								color="red">&nbsp;</font> :<s:hidden name="divCode"/></td>
						<td width="30%" colspan="2">
						<s:textarea name="div" cols="100" rows="1" readonly="true"/>
						<img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callDropdown('paraFrm_div',200,250,'PromotionReportAction_f9Division.action',event,'false','','right')"></td>
						
				</tr>
				
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="departmentName" name="departmentName" ondblclick="callShowDiv(this);"><%=label.get("promotion.dept")%></label> <font
								color="red">&nbsp;</font> :<s:hidden name="deptCode"/></td>
						<td width="30%" colspan="2">
						<s:textarea name="dept" cols="100" rows="1" readonly="true"/>
						<img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callDropdown('paraFrm_dept',200,250,'PromotionReportAction_f9Dept.action',event,'false','','right')"></td>
						
				</tr>
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="desginationName" name="desginationName" ondblclick="callShowDiv(this);"><%=label.get("promotion.desg")%></label> <font
								color="red">&nbsp;</font> :<s:hidden name="desgCode"/></td>
						<td width="30%" colspan="2">
						<s:textarea name="desg" cols="100" rows="1" readonly="true"/>
						<img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callDropdown('paraFrm_desg',200,250,'PromotionReportAction_f9Designation.action',event,'false','','right')"></td>
						
				</tr>
				<tr>
						<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="branchName" name="branchName" ondblclick="callShowDiv(this);"><%=label.get("promotion.branch")%></label> <font
								color="red">&nbsp;</font> :<s:hidden name="branchCode"/></td>
						<td width="30%" colspan="2">
						<s:textarea name="branch" cols="100" rows="1" readonly="true"/>
						<img src="../pages/images/search2.gif"
							height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callDropdown('paraFrm_branch',200,250,'PromotionReportAction_f9Branch.action',event,'false','','right')"></td>
						
				</tr>									</s:else>
				
				
				<s:if test="pramotion.generalFlag">
				<tr>
								<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="letter.type" name="letter.type" ondblclick="callShowDiv(this);"><%=label.get("letter.type")%></label> :<s:hidden name="signAuthCode"/></td>
								<td width="75%" colspan="3"><s:select name="letterType" list="#{'pdf':'PDF'}"/></td>
						
							</tr>	
				</s:if>
				
									<s:else>
									<tr>
								<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="letter.type" name="letter.type" ondblclick="callShowDiv(this);"><%=label.get("letter.type")%></label> :<s:hidden name="signAuthCode"/></td>
								<td width="75%" colspan="3"><s:select name="letterType" list="#{'pdf':'PDF','doc':'WORD'}"/></td>
						
							</tr>	
									</s:else>
							
							<s:if test="pramotion.generalFlag"></s:if>
									<s:else>
									<tr>
								<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="signAuth" name="signAuth" ondblclick="callShowDiv(this);"><%=label.get("signAuth")%></label> :<s:hidden name="signAuthCode"/></td>
								<td width="75%" colspan="3"><s:textfield name="signAuthToken" size="10" theme="simple" readonly="true" />
								<s:textfield	name="signAuthName" size="50" theme="simple" readonly="true" />
								<img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callsF9(500,325,'PromotionReportAction_f9actionSignAuth.action')" ></td>
						
							</tr>
							<s:hidden name="secSignAuthCode"/>
							<s:hidden name="secSignAuthName"/>
									</s:else>
							
							<!--  <tr>
								<td width="25%" colspan="1" class="formtext"><label  class = "set"  id="secSignAuth" name="secSignAuth" ondblclick="callShowDiv(this);"><=label.get("secSignAuth")%></label> :</td>
								<td width="75%" colspan="3"><s:textfield name="secSignAuthToken" size="10" theme="simple" readonly="true" />
								<s:textfield	name="secSignAuthName" size="50" theme="simple" readonly="true" />
								<img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" onclick="javascript:callsF9(500,325,'PromotionReportAction_f9SecSignAuth.action')" ></td>
						
							</tr>-->
								
								<s:hidden name="promCode"  />
								<s:hidden name="strength"  />
								<s:hidden name="weakness"  />
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
			<td colspan="3">
				<table width="100%" class="formbg">
					
					<s:if test="recordFlag">	
						<%
						try {
							int i=0;
								int totalRecords =0;
								int recPerPage=0;
								totalRecords = (Integer) request.getAttribute("totalRecords");
								recPerPage = (Integer) request.getAttribute("recPerPage");
								int total =totalRecords;								
								int loopcount = 0, pagecount =recPerPage, extrecords = 0;
									//(String) request.getParameter("totalRecords");
									//System.out.println("------------->"+totalRecords);
								if (totalRecords != 0 ) {
									
									if (total > pagecount) {
								loopcount = total / pagecount;
								extrecords = total % pagecount;
									}
									if (loopcount > 0) {
								for ( i = 0; i < loopcount; i++) {
									//System.out.println("--------loopcount----->"+loopcount);
									// set of urls
									%>
								<tr><td nowrap="nowrap">	
								<a href="#" onclick="callFun('<%=(i*pagecount)+1%>','<%=(i*pagecount)+pagecount%>');">Click here to Download Promotion Letter  for <%=(i*pagecount)+1%> to <%=(i*pagecount)+pagecount%> </a> 
									</td></tr>
									<%
								}
								if (extrecords > 0) {
									// add final url
									//System.out.println("--------extrecords----->"+extrecords);
									%>
								<tr><td nowrap="nowrap">	<a href="#" onclick="callFun('<%=(i*pagecount)+1%>','<%=total%>');">Click here to Download Promotion Letter  for <%=(i*pagecount)+1%> to <%=total%> </a> 
								</td></tr>
								<% 
								}
									} else {
										//System.out.println("--------total----->"+total);
										//single url
										%>
								<tr><td nowrap="nowrap">
								<a href="#" onclick="callFun('<%=(i*pagecount)+1%>','<%=total%>');">Click here to Download Promotion Letter  for <%=(i*pagecount)+1%> to <%=total%> </a> 
							</td></tr>
								<% 
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
								
							}
						%>
					</s:if>	
					
					</table>
					</td>
			</tr>

			</table>
			</td>
		</tr>

	</table>
</s:form>
<script>

function callUrlFun()
{
	if(document.getElementById('paraFrm_effDate').value == ''){
  			alert("Please select the "+document.getElementById("promotion.eff.date").innerHTML.toLowerCase());
  			return false;
  		}
	document.getElementById("paraFrm").action='PromotionReportAction_getURL.action';  	 
	 document.getElementById("paraFrm").submit();
}
function callFun(rowstart,rowend)
 {
 	//alert("rowstart : "+rowstart);
 	//alert("rowend : "+rowend);
   	  document.getElementById("paraFrm").action='PromotionReportAction_getPromotionLetter.action?rangefromValue='+eval(rowstart)+'&rangetoValue='+eval(rowend);
      document.getElementById('paraFrm').target="_blank";
	  document.getElementById("paraFrm").submit();	
 }
 
 function callPromotionLetter(){
  		
  		if(document.getElementById('paraFrm_empCode').value == ''){
  			alert("Please select the "+document.getElementById("employee").innerHTML.toLowerCase());
  			return false;
  		}
  		
  		if(document.getElementById('paraFrm_tempCode').value == ''){
  			alert("Please select the "+document.getElementById("templateName").innerHTML.toLowerCase());
  			document.getElementById('paraFrm_templateName').focus();
  			return false;
  		}
  		
	  document.getElementById('paraFrm').target="_blank";
      document.getElementById('paraFrm').action="PromotionReportAction_getPromotionLetter.action";
	  document.getElementById('paraFrm').submit();  
      document.getElementById('paraFrm').target="main";
  
   	
	
}
 function callSendLetter(){
 	if(document.getElementById('paraFrm_empCode').value == ''){
  			alert("Please select the "+document.getElementById("employee").innerHTML.toLowerCase());
  			return false;
  		}
  		//alert(document.getElementById('paraFrm_empMailId').value);
  	if(document.getElementById('paraFrm_empMailId').value == ''){
  			alert("Employee e-mail Id is not available");
  			return false;
  		}
  		
  		if(document.getElementById('paraFrm_tempCode').value == ''){
  			alert("Please select the "+document.getElementById("templateName").innerHTML.toLowerCase());
  			document.getElementById('paraFrm_templateName').focus();
  			return false;
  		}
  		
  	var conf=confirm('Do you really want to send the mail ?');
			 	if(!conf){
			 	return false;
			 	}
      document.getElementById('paraFrm').action="PromotionReportAction_sendMail.action";
	  document.getElementById('paraFrm').submit();  
 }
 
</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>