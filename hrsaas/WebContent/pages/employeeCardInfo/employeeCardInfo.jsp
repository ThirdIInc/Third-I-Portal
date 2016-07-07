<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%	String str = (String) request.getAttribute("photo");%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="EmployeeCardInfo" method="post" name="employeeCardInfoForm" id="paraFrm" theme="simple">
<s:hidden name="ecardAdmin" value="EcardAdmin"/>
<table width="100%" border="0" align="center" cellpadding="2" cellspacing="1" class="formbg">

		<tr>
			<td colspan="7">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee ID Card Application</strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			
		</tr>
		<s:hidden name="status"></s:hidden>
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="1" cellspacing="0">
				<tr>
					<td>
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					<td width="30%" id="ctrlHide" align="right"><font color="red">*</font> Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:if test="commentFlag">
		<tr>
			<td colspan="7">
				<table width="100%" border="0" cellpadding="1" cellspacing="0" 	class="formbg">
					<tr>
						<td >
							<label class = "set" name="comments" id="comments" ondblclick="callShowDiv(this);"><%=label.get("comments")%></label>
						</td>
				 		<td>
								<s:textarea name="apprComments" value="%{apprComments}" rows="2" cols="70"  />
				 		</td>		
					</tr>
				</table>
			</td>
		</tr>
		</s:if>
		
	<!-- APPROVER DETAILS STARTS -->
		<s:if test="%{apprDtlLst == null || apprDtlLst.size() == 0}">
		</s:if>
		<s:else>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="2" class="formbg">
					<tr>
						<td colspan="6" class="formhead"><strong class="forminnerhead">
								<label class="set" name="appr.heading" id="appr.heading" ondblclick="callShowDiv(this);"><%=label.get("appr.heading")%></label>
							</strong>
						</td>
					</tr>
					<tr>
						<td class="formth" width="10%"><b>
							<label id="appr.srno" name="appr.srno" class="set" ondblclick="callShowDiv(this);"><%=label.get("appr.srno")%></label></b>
						</td>
						<td class="formth" width="15%"><b>
							<label id="appr.empName" name="appr.empName" class="set" ondblclick="callShowDiv(this)"><%=label.get("appr.empName")%></label></b>
						</td>
						<td class="formth" width="10%"><b>
							<label id="appr.date" name="appr.date" class="set" ondblclick="callShowDiv(this)"><%=label.get("appr.date")%></label></b>
						</td>
						<td class="formth" width="10%"><b>
							<label id="appr.status" name="appr.status" class="set" ondblclick="callShowDiv(this)"><%=label.get("appr.status")%></label></b>
						</td>
						<td class="formth" width="30%"><b>
							<label id="appr.comments" name="appr.comments" class="set" ondblclick="callShowDiv(this)"><%=label.get("appr.comments")%></label></b>
						</td>
					</tr>
				
				<s:iterator value="apprDtlLst">
					<tr>
						<td class="sortableTD" align="center">
							<s:property value="apprSrNoIt" />
							<s:hidden name="apprSrNoIt"></s:hidden>
						</td>
						
						<td class="sortableTD" align="center">
							<s:property value="apprNameIt" />
							<s:hidden name="apprNameIt"></s:hidden>
						</td>
						
						<td class="sortableTD" align="center">
							<s:property value="approvedDateIt" />
							<s:hidden name="approvedDateIt"></s:hidden>
						</td>
						
						<td class="sortableTD" align="center">
							<s:property value="apprStatusIt" />
							<s:hidden name="apprStatusIt"></s:hidden>
						</td>
						
						<td class="sortableTD" align="center">
							<s:property value="apprCommentsIt" />
							<s:hidden name="apprCommentsIt"></s:hidden>
						</td>
					</tr>
				</s:iterator>
				
			</table>
			</td>
		</tr>
		</s:else>
	
	
	
	<!-- APPROVER DETAILS END -->
		<!-- APPROVER LIST  AND KEEP INFORMED TABLE  STARTS -->
		<tr>
			<td colspan="7">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<td width="50%" nowrap="nowrap">
						<strong>The Approver(s) for this application :</strong>
					</td>
					<td colspan="2" nowrap="nowrap"></td>
						<s:if test="keepInfoFlag"><td  align="left" width="11%" nowrap="nowrap"><strong>Keep Informed To : </strong></td>	
						<td width="13%">							
								<s:hidden name="kiEmployeeId" />
								<s:hidden name="kiEmployeeToken" />
								<s:textfield name="kiEmployeeName" readonly="true" cssStyle="background-color: #F2F2F2;"/>							
						</td>
						</s:if>	
						<td width="5%" colspan="1">
							<s:if test="keepInfoFlag">
								<img src="../pages/common/css/default/images/search2.gif" 
								class="iconImage" width="16" height="15" 
								onclick="javascript:getKeepInformedEmp();" />
							</s:if>								
						</td>
						<td width="15%">
							<s:if test="keepInfoFlag">
								<s:submit name="" value=" Add" cssClass="add" 
								action="EmployeeCardInfo_addKeepInformedEmpList" 
								onclick="return callKeepInformed();" />
						    </s:if>	
						</td>
				</tr>
				
				<!-- APPROVER LIST  TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="3">
					<table width="100%" border="0" cellpadding="1" cellspacing="0">
						<tr>
								<%
							int y = 0;
							%>
							<%!int managerLength = 0;%>
							<s:iterator value="managerList">
								<tr>
									<td>
										<s:hidden name="managerNameIt" />
										<s:hidden name="managerIdIt" />
										<STRONG><s:property value="srNoIt" /></STRONG> 
										<s:property value="managerNameIt" />
									</td>

								</tr>
									<%
								y++;
								%>
							</s:iterator>
							<%
							managerLength = y;%>
						</tr>
					</table>
					</td>
				</tr>

				<!-- APPROVER LIST  TABLE  ENDS -->
				<!-- KEEP INFORMED LIST TABLE  STARTS -->
				<tr valign="top">
					<td colspan="3" rowspan="5">
					<table width="100%" border="0" cellpadding="1" cellspacing="0">
						<%int counter11 = 0; int counter2 = 0;%>
						<s:iterator value="keepInformedList" status="stat">
							<tr>
								<td width="80%"><%=++counter11%>
									<s:hidden name="serialNoIt" />
									<s:hidden name="keepInformedEmpIdIt" />
									<s:hidden name="keepInformedEmpTokenIt" />
									<s:property value="keepInformedEmpNameIt"  />
									<s:hidden name="keepInformedEmpNameIt" />
								</td>	
								<s:if test="%{bean.keepInfoFlag}">
									<td width="20%">
										<a href="#"	onclick="callForRemove(<%=counter11%>,'<s:property value="keepInformedEmpTokenIt"/>');">Remove</a>
									</td>
								</s:if >		
							</tr>
							<%
							counter2 = counter11;
							%>
						</s:iterator>
						<%counter2 = 0;	%>
					</table>
					</td>
					<td></td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- KEEP INFORMED LIST TABLE  ENDS -->
		
		
		<!--  -->
		
		<!-- Select employee filter -->
		
		<tr>
		<s:hidden name="empId" /> 
		<s:hidden name="busCardApplCode" /> 
		
		<td colspan="7">
			<table  width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="formbg">
				 <tr>
					
							<td width="25%" colspan="1" class="formtext">
								<label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> <font
										color="red">*</font> :
							</td>
							<td width="75%" colspan="3">
								<s:if test="generalFlag">
									<s:textfield name="empToken" value="%{empToken1}" size="25"  theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;" />
									<s:textfield name="empName" value="%{empName1}" size="25"  theme="simple" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
							</s:if><s:else>
							<s:textfield name="empToken" size="10" value="%{empToken1}" theme="simple" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
								<s:textfield name="empName" size="50" value="%{empName1}" theme="simple" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
							<s:if test="empFlag">
									<img src="../pages/images/recruitment/search2.gif" height="18"
											 align="absmiddle" width="18"
											 onclick="javascript:callsF9(500,325,'EmployeeCardInfo_f9Employee.action');">		 
								</s:if>
								</s:else>	  
							</td>
				</tr>
			</table>
		</td>
		</tr>
		<!-- Employee Information tr -->
		<tr>
			<td>
				<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="formbg">
					<tr>
							<td>
								<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" >
									<tr>
										<td colspan="2"><strong>Applicant Details in Records</strong></td>
									</tr>
									
									<tr>
												<td><label class="set" id="empToken" name="empToken"
														ondblclick="callShowDiv(this);"><%=label.get("empToken")%></label> 
												</td>
												<td>
													<s:textfield name="empToken1" size="25"  theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/>
												</td>
									</tr>
								
									<tr>			
												<td><label class="set" id="employeeName" name="employeeName"
														ondblclick="callShowDiv(this);"><%=label.get("employeeName")%></label> 
												</td>
												<td>
													<s:textfield name="empName1" size="25"  theme="simple" readonly="true" cssStyle="background-color: #F2F2F2;"/>
												</td>
									</tr>	
									
									<!-- Image Display -->
									<tr>
												<td height="22"><label  class="set1" name="photograph" id="photograph"" ondblclick="callShowDiv(this);"><%=label.get("photograph")%></label> :</td>
												<td >
													<table >
														<tr>
															<td >
																<s:if test="%{bean.flag}">
																	<img src="../pages/images/employee/NoImage.jpg " height="100" align="middle" />
																</s:if>
																<s:else>
																	
																	<%if(str.equals("NoImage.jpg"))   { %>
																		<img src="../pages/images/employee/NoImage.jpg" height="100" align="middle" />
																	<% } else  { %>
																	<img src="../pages/images/<%=session.getAttribute("session_pool") %>/employee/<%=str %>" height="110" id="img"/>
																	<%} %>
																</s:else>
															</td>
														</tr>
													</table>
											    </td>	
									
									</tr>	
									
									<!-- Image Display -->
											
									<tr>			
												<td><label class="set" id="desig1" name="desig1"
														   onDblClick="callShowDiv(this);"><%=label.get("desig1")%></label>:
												</td>
												<td>
													<s:textfield size="25" name="desig"  readonly="true"  cssStyle="background-color: #F2F2F2;"/>
												</td>
									</tr>	
										
									<tr>
												<td><label class="set" id="dept1" name="dept1"
														onDblClick="callShowDiv(this);"><%=label.get("dept1")%></label>:
												</td>			
												<td>
													<s:textfield size="25" name="dept" readonly="true" cssStyle="background-color: #F2F2F2;" />
												</td>
									</tr>
								
									<tr>			
												<td>
													<label class="set" id="companyName1" name="companyName1"
															onDblClick="callShowDiv(this);"><%=label.get("companyName1")%></label> :
												</td>
												<td>
													<s:textfield size="25" name="companyName" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
												</td>
									</tr>
									
									<tr>			
												<td>
													<label class="set" id="branch1" name="branch1"
															onDblClick="callShowDiv(this);"><%=label.get("branch1")%></label> :
												</td>
												<td>
													<s:textarea rows="2" cols="25" name="branch" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
												</td>
									</tr>
									
									<tr>			
												<td><label class="set" id="pinCode1" name="pinCode1"
													onDblClick="callShowDiv(this);"><%=label.get("pinCode1")%></label> :</td>
												<td>
													<s:textfield size="25" name="pinCode" readonly="true" cssStyle="background-color: #F2F2F2;" />
												</td>
									</tr>
								
									<tr>			
												<td><label class="set" id="location1" name="location1"
													onDblClick="callShowDiv(this);"><%=label.get("location1")%></label> :</td>
												<td>
													<s:textfield size="25" name="location" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
												</td>
								   </tr>
								 
								   <tr>
												<td><label class="set" id="officeNum1" name="officeNum1"
													onDblClick="callShowDiv(this);"><%=label.get("officeNum1")%></label> :</td>
												<td>
													<s:textfield size="25" name="officeNum" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
												</td>
									</tr>
									
									<tr>
												<td><label class="set" id="mobile1" name="mobile1"
													onDblClick="callShowDiv(this);"><%=label.get("mobile1")%></label> :</td>
												<td>
													<s:textfield size="25" name="mobile" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
												</td>
									</tr>
									
									<tr>			
												<td><label class="set" id="fax" name="fax"
													onDblClick="callShowDiv(this);"><%=label.get("fax")%></label> :</td>
												<td>
													<s:textfield size="25" name="faxNum" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
												</td>
									</tr>
								
									<tr>
												<td><label class="set" id="emailId1" name="emailId1"
													onDblClick="callShowDiv(this);"><%=label.get("emailId1")%></label> :</td>
												<td>
													<s:textfield size="25" name="emailId" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
												</td>
										
									</tr>
								
									<tr>
												<td><label class="set" id="webSite" name="webSite"
													onDblClick="callShowDiv(this);"><%=label.get("webSite")%></label> :</td>
												<td>
													<s:textfield size="25" name="webSiteAddr" readonly="true"  cssStyle="background-color: #F2F2F2;"/>
												</td>
										
									</tr>
									
								</table>
							</td>
							
							<td style="background-color: #f9f9f9;height: 1px;" >&nbsp;</td>
							
							<!-- ----------------------------  -->
							<td>
								<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" >
									<tr>
										<td colspan="2"><strong>Employee ID Card Information To Be Printed:</strong></td>
									</tr>
									<tr>
										<td>
												<label class="set" id="cpySysInfo" name="cpySysInfo"
													   ondblclick="callShowDiv(this);"><%=label.get("cpySysInfo")%></label>: 
										</td>
										<td>
										 	<s:checkbox theme="simple" name="sysInfoFlag" onclick="copyAllEmployeeInfo();"   />	
										</td>
									</tr>
										
									
									<tr>
												<td><label class="set" id="empToken" name="empToken"
														ondblclick="callShowDiv(this);"><%=label.get("empToken")%></label> :
												</td>
												<td>
													<s:textfield name="empTokenp" size="25" value="%{empTokenp}" theme="simple"  />
												</td>
									</tr>
								
									<tr>			
												<td><label class="set" id="employeeName" name="employeeName"
														ondblclick="callShowDiv(this);"><%=label.get("employeeName")%></label><font color="red">*</font>: 
												</td>
												<td>
													<s:textfield name="empNamep" size="25" value="%{empNamep}" theme="simple"  />
												</td>
									</tr>			
									
									<tr>
												<td height="22"><label  class="set1" name="photograph" id="photograph"" ondblclick="callShowDiv(this);"><%=label.get("photograph")%></label> :</td>
												<td>
													<table>
														<tr>
															<td>
								<div id="showImage" style="display: none;">								
													<s:if test="%{bean.flag}">
																	<img src="../pages/images/employee/NoImage.jpg " height="100" align="middle" />
																</s:if>
																<s:else>
																	<%	String str1 = (String) request.getAttribute("photo");%>
																	<%if(str1.equals("NoImage.jpg"))   { %>
																		<img src="../pages/images/employee/NoImage.jpg" height="100" align="middle" />
																	<% } else  { %>
																	<img src="../pages/images/<%=session.getAttribute("session_pool") %>/employee/<%=str %>" height="110" id="img"/>
																	<%} %>
																</s:else>								
															
							</div>		
							
							<div id="showImageAttach" style="display: none;">								
													<s:if test="%{bean.flag}">
																	<img src="../pages/images/employee/NoImage.jpg " height="100" align="middle" />
																</s:if>
																<s:else>
																	<%	String str1Attach = (String) request.getAttribute("photoAttach")==null?"NoImage.jpg":(String) request.getAttribute("photoAttach");
																	
																	%>
																	<%if(str1Attach.equals("NoImage.jpg"))   { %>
																		<img src="../pages/images/employee/NoImage.jpg" height="100" align="middle" />
																	<% } else  { %>
																	<img src="../pages/images/<%=session.getAttribute("session_pool") %>/employee/<%=str1Attach %>" height="100" id="img"/>
																	<%} %>
																</s:else>								
															
							</div>											
			
			 
		
											 			
															</td>
															</tr>
															<tr>
															<!--  <td><s:textfield name="uploadFileName1" size="18"
															maxlength="30" readonly="true" /></td>-->
															<!-- <td height="22">
															
																<input name="Browse" type="button" class="token" value="Upload" onclick="uploadFile('uploadFileName1');" />		
															</td>
															<td height="22">
															
																<input name="Browse" type="button" class="token" value="Attach" onclick="AttachPhoto();" />		
															</td>-->
															</tr>
														
													</table>
											    </td>	
									
									</tr>	
											
									
								
									<tr>			
												<td><label class="set" id="desig1" name="desig1"
														   onDblClick="callShowDiv(this);"><%=label.get("desig1")%></label>:
												</td>
												<td>
													<s:textfield size="25" name="desigp"    />
												</td>
									</tr>	
									
									<tr>
												<td><label class="set" id="dept1" name="dept1"
														onDblClick="callShowDiv(this);"><%=label.get("dept1")%></label>:
												</td>			
												<td>
													<s:textfield size="25" name="deptp"   />
												</td>
									</tr>
									
									<tr>			
												<td>
													<label class="set" id="companyName1" name="companyName1"
															onDblClick="callShowDiv(this);"><%=label.get("companyName1")%></label> <font color="red">*</font>:
												</td>
												<td>
													<s:textfield size="25" name="companyNamep"  />
												</td>
									</tr>
									
									<tr>			
												<td>
													<label class="set" id="branch1" name="branch1"
															onDblClick="callShowDiv(this);"><%=label.get("branch1")%></label> <font color="red">*</font>:
												</td>
												<td>
													<s:textarea rows="2" cols="25" name="branchp"   />
												</td>
									</tr>
									
									<tr>			
												<td><label class="set" id="pinCode1" name="pinCode1"
													onDblClick="callShowDiv(this);"><%=label.get("pinCode1")%></label> :</td>
												<td>
													<s:textfield size="25" name="pinCodep" maxlength="10" onkeypress="return numbersonly1(this)"  />
												</td>
									</tr>
									
									<tr>			
												<td><label class="set" id="location1" name="location1"
													onDblClick="callShowDiv(this);"><%=label.get("location1")%></label> :</td>
												<td>
													<s:textfield size="25" name="locationp"   />
												</td>
								   </tr>
								 
								   <tr>
												<td><label class="set" id="officeNum1" name="officeNum1"
													onDblClick="callShowDiv(this);"><%=label.get("officeNum1")%></label> <font color="red">*</font>:</td>
												<td>
													<s:textfield size="25" name="officeNump"   maxlength="15" onkeypress="return numbersonly1(this)" />
												</td>
									</tr>
								
									<tr>
												<td><label class="set" id="mobile1" name="mobile1"
													onDblClick="callShowDiv(this);"><%=label.get("mobile1")%></label> :</td>
												<td>
													<s:textfield size="25"  maxlength="15" name="mobilep"   onkeypress="return numbersonly1(this)" />
												</td>
									</tr>
								
									<tr>			
												<td><label class="set" id="fax" name="fax"
													onDblClick="callShowDiv(this);"><%=label.get("fax")%></label> :</td>
												<td>
													<s:textfield size="25" name="faxNump"  maxlength="15" onkeypress="return numbersonly1(this)"  />
												</td>
									</tr>
									
									<tr>
												<td><label class="set" id="emailId1" name="emailId1"
													onDblClick="callShowDiv(this);"><%=label.get("emailId1")%></label> :</td>
												<td>
													<s:textfield size="25" name="emailIdp"   />
												</td>
										
									</tr>
									
									<tr>
												<td><label class="set" id="webSite" name="webSite"
													onDblClick="callShowDiv(this);"><%=label.get("webSite")%></label> :</td>
												<td>
													<s:textfield size="25" name="webSiteAddrp"   />
												</td>
										
									</tr>
								
								</table>
							</td>
					</tr>
				</table>
			</td>
			
		</tr>
		
		
		
		<tr>
				<td colspan="7">
							<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
								<tr>
									<td><strong> Comments</strong></td>
								</tr>
								<tr>
									<td><label class="set" id="comment1" name="comment1" onDblClick="callShowDiv(this);"><%=label.get("comment1")%></label> :</td>
									<td>
										<s:textarea rows="3" cols="50" name="comment"   />
								    </td>
								</tr>
							</table>
						</td>
		</tr>	
		<s:hidden name="checkRemove" />
		<tr>
			<td >
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</td>
					
				</tr>
			</table>
			</td>
		</tr>
</table>
<s:hidden name="source" id="source"/>
<s:hidden name="isAttachClick" id="isAttachClick"/>
</s:form>
<script type="text/javascript">

showOnload();

function showOnload()
{
		
		
		document.getElementById('showImage').style.display='none';
	document.getElementById('showImageAttach').style.display='none';
	
	
}
	function copyAllEmployeeInfo(){
		if(document.getElementById('paraFrm_sysInfoFlag').checked){
			document.getElementById('paraFrm_empTokenp').value=document.getElementById('paraFrm_empToken1').value;
			document.getElementById('paraFrm_empNamep').value=document.getElementById('paraFrm_empName1').value;
			document.getElementById('paraFrm_desigp').value=document.getElementById('paraFrm_desig').value;
			document.getElementById('paraFrm_deptp').value=document.getElementById('paraFrm_dept').value;
			document.getElementById('paraFrm_companyNamep').value=document.getElementById('paraFrm_companyName').value;
			document.getElementById('paraFrm_branchp').value=document.getElementById('paraFrm_branch').value;
			document.getElementById('paraFrm_pinCodep').value=document.getElementById('paraFrm_pinCode').value;
			document.getElementById('paraFrm_locationp').value=document.getElementById('paraFrm_location').value;
			document.getElementById('paraFrm_officeNump').value=document.getElementById('paraFrm_officeNum').value;
			document.getElementById('paraFrm_mobilep').value=document.getElementById('paraFrm_mobile').value;
			document.getElementById('paraFrm_faxNump').value=document.getElementById('paraFrm_faxNum').value;
			document.getElementById('paraFrm_emailIdp').value=document.getElementById('paraFrm_emailId').value;
			document.getElementById('paraFrm_webSiteAddrp').value=document.getElementById('paraFrm_webSiteAddr').value;
			document.getElementById('showImage').style.display='';
		
		}else{
			document.getElementById('paraFrm_empTokenp').value="";
			document.getElementById('paraFrm_empNamep').value="";
			document.getElementById('paraFrm_desigp').value="";
			document.getElementById('paraFrm_deptp').value="";
			document.getElementById('paraFrm_companyNamep').value="";
			document.getElementById('paraFrm_branchp').value="";
			document.getElementById('paraFrm_pinCodep').value="";
			document.getElementById('paraFrm_locationp').value="";
			document.getElementById('paraFrm_officeNump').value="";
			document.getElementById('paraFrm_mobilep').value="";
			document.getElementById('paraFrm_faxNump').value="";
			document.getElementById('paraFrm_emailIdp').value="";
			document.getElementById('paraFrm_webSiteAddrp').value="";
			document.getElementById('showImage').style.display='none';
			document.getElementById('showImagePhoto').style.display='';
			//document.getElementById('paraFrm_uploadFileName1').value="";
		}
		
	}
	function draftFun(){
		var managerLength ='<%=managerLength%>';
		
		if(managerLength ==0){
			alert("Please configure Admin");
			return false;
		}
		
		var fieldName = ["paraFrm_empName","paraFrm_empNamep","paraFrm_companyNamep","paraFrm_branchp","paraFrm_officeNump"];
		var lableName = ["employee","employeeName","companyName1","branch1","officeNum1"];
		var flag = ['enter','enter','enter','enter','enter'];
		
		if(!validateBlank(fieldName, lableName,flag)){
           return false;
        }
        if(!document.getElementById('paraFrm_emailIdp').value =="")
	  	{
			var abc=validateEmail('paraFrm_emailIdp');
		  	if(!abc){
		  		return false;
		  	}
	  	}
	  	if(!document.getElementById('paraFrm_webSiteAddrp').value ==""){
	  		var website=validateWebSite('paraFrm_webSiteAddrp');
			if(!website){
				return false;
			}
	  	}
	  	
		var conf=confirm("Do you really want to draft  ?");
  		if(conf) {
  			document.getElementById('paraFrm_status').value="D";
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "EmployeeCardInfo_saveEmployeeCardInfo.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	function sendforapprovalFun(){
	var managerLength ='<%=managerLength%>';
		
		if(managerLength ==0){
			alert("Please configure admin");
			return false;
		}
		var fieldName = ["paraFrm_empName","paraFrm_empNamep","paraFrm_companyNamep","paraFrm_branchp","paraFrm_officeNump"];
		var lableName = ["employee","employeeName","companyName1","branch1","officeNum1"];
		var flag = ['enter','enter','enter','enter','enter'];
		
		if(!validateBlank(fieldName, lableName,flag)){
           return false;
        }
        if(!document.getElementById('paraFrm_emailIdp').value =="")
	  	{
			var abc=validateEmail('paraFrm_emailIdp');
		  	if(!abc){
		  		return false;
		  	}
	  	}
	  	if(!document.getElementById('paraFrm_webSiteAddrp').value ==""){
	  		var website=validateWebSite('paraFrm_webSiteAddrp');
			if(!website){
				return false;
			}
	  	}
	  	
		var conf=confirm("Do you really want to send for approval  ?");
  		if(conf) {
			document.getElementById('paraFrm_status').value="P";
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "EmployeeCardInfo_saveEmployeeCardInfo.action";
			document.getElementById('paraFrm').submit();
		}
	}
	
	
	function deleteFun(){
		var fieldName = ["paraFrm_empName","paraFrm_empNamep","paraFrm_companyNamep","paraFrm_branchp","paraFrm_officeNump"];
		var lableName = ["employee","employeeName","companyName1","branch1","officeNum1"];
		var flag = ['enter','enter','enter','enter','enter'];
		
		if(!validateBlank(fieldName, lableName,flag)){
           return false;
        }
		var conf=confirm("Do you really want to delete ?");
  		if(conf){
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "EmployeeCardInfo_delete.action";
			document.getElementById('paraFrm').submit();
		}	
	}
	function reportFun(){
		var fieldName = ["paraFrm_empName","paraFrm_empNamep","paraFrm_companyNamep","paraFrm_branchp","paraFrm_officeNump"];
		var lableName = ["employee","employeeName","companyName1","branch1","officeNum1"];
		var flag = ['enter','enter','enter','enter','enter'];
		
		if(!validateBlank(fieldName, lableName,flag)){
           return false;
        }
        if(!document.getElementById('paraFrm_emailIdp').value =="")
	  	{
			var abc=validateEmail('paraFrm_emailIdp');
		  	if(!abc){
		  		return false;
		  	}
	  	}
	  	if(!document.getElementById('paraFrm_webSiteAddrp').value ==""){
	  		var website=validateWebSite('paraFrm_webSiteAddrp');
			if(!website){
				return false;
			}
	  	}
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "EmployeeCardInfo_report.action";
		document.getElementById('paraFrm').submit();
	}
	
	
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = "EmployeeCardInfo_reset.action";
		document.getElementById('paraFrm').submit();
	}
	
	
	function approveFun(){
		var conf=confirm("Do you really want to  approve ?");
	  	if(conf){	
	  		document.getElementById('paraFrm_status').value="A";
			document.getElementById('paraFrm').target="_self";
			document.getElementById("paraFrm").action="EmployeeCardInfo_saveApproverDataEmployeeCardInfo.action";
			document.getElementById("paraFrm").submit();
		}				
	}
	/*function mailtovendorFun(){	
		document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action="EmployeeCardInfo_vendorDetails.action";
		document.getElementById("paraFrm").submit();
	}*/
	function rejectFun(){
		var conf=confirm("Do you really want to  rejected ?");
	  	if(conf){	
	  		document.getElementById('paraFrm_status').value="R";
			document.getElementById('paraFrm').target="_self";
			document.getElementById("paraFrm").action="EmployeeCardInfo_saveApproverDataEmployeeCardInfo.action";
			document.getElementById("paraFrm").submit();
		}				
	}
	function sendbackFun(){
		var conf=confirm("Do you really want to  send back ?");
	  	if(conf){	
	  		document.getElementById('paraFrm_status').value="B";
			document.getElementById('paraFrm').target="_self";
			document.getElementById("paraFrm").action="EmployeeCardInfo_saveApproverDataEmployeeCardInfo.action";
			document.getElementById("paraFrm").submit();
		}				
	}
	
	
	function callKeepInformed()
	{
		 //var empcode=document.getElementById('paraFrm_empCode').value;
		 var emp =document.getElementById('paraFrm_kiEmployeeName').value;
		 if(emp==""){
			 alert("Please select Keep Informed To ");
			 return false;
		 }
		return true;
	}
	function getKeepInformedEmp()
	{
		try
		{ 
			var empcode=document.getElementById('paraFrm_empToken').value;
			if(empcode==""){
				alert("Please select "+document.getElementById('empToken').innerHTML.toLowerCase());
				return false;
			}else{
					callsF9(500,325,'EmployeeCardInfo_f9KeepInformedEmployee.action');
			}
		}catch(e){alert(e);} 
	}
	function callForRemove(id,empCode)
	{
			document.getElementById('paraFrm_checkRemove').value=id;
		    var conf=confirm("Are you sure !\n You want to Remove this record ?");
	  		if(conf){	
				document.getElementById('paraFrm').target="_self";
				document.getElementById("paraFrm").action='EmployeeCardInfo_removeKeepInformed.action?kiInfrEmployeeCode='+empCode;
				document.getElementById("paraFrm").submit();
			}	
			else
			{
				return false;
			}
		return true;			

   }
   function numbersonly1(myfield)
   {
		 
		 var pinCode=document.getElementById('paraFrm_pinCodep').value
		
		 var officeNum=document.getElementById('paraFrm_officeNump').value
		
		 var mobileNum=document.getElementById('paraFrm_mobilep').value
		
		 var faxNum=document.getElementById('paraFrm_faxNump').value
		
		 
		  
		 if(!pinCode==""){
		    var iChars = "0123456789-/ ";
		   	for (var i = 0; i < pinCode.length; i++) {			
			  	if (!(iChars.indexOf(pinCode.charAt(i)) != -1)) {
				  	alert ("Only Numerics Allowed in Pincode");
				  	document.getElementById('paraFrm_pinCodep').value="";
				  	return false;
				}
  			}//for loop close
  		 }
  		 if(!mobileNum==""){
		    var iChars = "0123456789-/ ";
		   	for (var i = 0; i < mobileNum.length; i++) {			
			  	if (!(iChars.indexOf(mobileNum.charAt(i)) != -1)) {
				  	alert ("Only Numerics Allowed in Mobile number");
				  	document.getElementById('paraFrm_mobilep').value="";
				  	return false;
				}
  			}//for loop close
  		 }
  		 
  		 if(!officeNum==""){
		    var iChars = "0123456789-/ ";
		   	for (var i = 0; i < officeNum.length; i++) {			
			  	if (!(iChars.indexOf(officeNum.charAt(i)) != -1)) {
				  	alert ("Only Numerics Allowed in Office number");
				  	document.getElementById('paraFrm_officeNump').value="";
				  	return false;
				}
  			}//for loop close
  		 }
  		 if(!faxNum==""){
		    var iChars = "0123456789-/ ";
		   	for (var i = 0; i < faxNum.length; i++) {			
			  	if (!(iChars.indexOf(faxNum.charAt(i)) != -1)) {
				  	alert ("Only Numerics Allowed in Fax number");
				  	document.getElementById('paraFrm_faxNump').value="";
				  	return false;
				}
  			}//for loop close
  		 }
	}
	function uploadFile(fieldName) {
		var path="images/<%=session.getAttribute("session_pool")%>/employee";
		window.open('../pages/common/uploadFileHrm.jsp?path='+path+'&field='+fieldName,'','width=400,height=200,scrollbars=no,resizable=no,top=50,left=100');
		document.getElementById('paraFrm').target="main";
		
	} 
	function AttachPhoto(fieldName) {
	
	document.getElementById('showImage').style.display='';
	
		document.getElementById('paraFrm').target="_self";
		document.getElementById("paraFrm").action='EmployeeCardInfo_uploadPhoto.action';
	
	document.getElementById("paraFrm").submit();
		
	} 
	function backFun()
	{
		document.getElementById('paraFrm').target = "_self";
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
</script>
