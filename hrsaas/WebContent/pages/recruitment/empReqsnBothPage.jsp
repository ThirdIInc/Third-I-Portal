<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.HashMap"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script language="JavaScript" type="text/javascript"
	src="..pages/common/include/javascript/sorttable.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/jquery-1.2.2.pack.js"></script>

<script type="text/javascript"
	src="../pages/common/include/javascript/ddaccordion.js"></script>

<script type="text/javascript">
ddaccordion.init({
	headerclass: "technology", //Shared CSS class name of headers group
	contentclass: "thelanguage", //Shared CSS class name of contents group
	collapseprev: false, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc]. [] denotes no content.
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["closedlanguage", "openlanguage"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["prefix", "<img src='../pages/common/css/default/images/plus.gif' align='left' vspace='3'  hspace='3' /> ", "<img src='../pages/common/css/default/images/minus.gif'align='left' vspace='3' hspace='3'  /> "], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "slow" //speed of animation: "fast", "normal", or "slow"
})

</script>
<s:form action="EmployeeRequi" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="reqCode" />
	<table width="100%" align="right" class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						 height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Manpower
					Requisition </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td height="5" colspan="3"><s:if test="dashletFlag">
			</s:if> <s:else>
				<strong class="text_head"><font color="red">Please
				verify the details you have entered:</font></strong>
			</s:else></td>
		</tr>
		 
		<s:hidden name="reqNameSer" />
		<s:hidden name="reqDateSer" />
		<s:hidden name="reqPosSer" />
		<s:hidden name="reqHireSer" />
		<s:hidden name="updateFirstFlag" />
		<s:hidden name="hiddenStatus" />
		<s:hidden name="formAction" id="formAction" />
		<s:hidden name="statusKey" id="statusKey" />
		<s:hidden name="formFlag" id="formFlag" />
		<s:hidden name="hiddenApproveStatus" />
		<s:hidden name="idReq" />
		<s:hidden name="enableFirst" />
		<s:hidden name="enableSecond" />
		<s:hidden name="jdEffDate" />
		<s:hidden name="viewDom" />
		<s:hidden name="viewCert" />
		<s:hidden name="viewQua" />
		<s:hidden name="viewSkill" />
		<s:hidden name="source" id="source" />

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%"><s:if test="dashletFlag">
					</s:if> <s:else>
						<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
					</s:else></td>
					<td width="22%"></td>
				</tr>
			</table>
			<label></label></td>
		</tr>

		<s:hidden name="myPage" id="myPage" />
		<s:hidden name="show" />
		<s:hidden name="saveFirstFlag" />
		<s:hidden name="saveSecondFlag" />
		<s:hidden name="viewFirstCancel" />
		<s:hidden name="viewSecondCancel" />
		<s:hidden name="referalFlag" />
		 
		<s:if test="listFlag">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td colspan="2">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							class="formbg">
							<tr>
								<td>
								<table width="99%" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td height="27" class="formtxt"><a
											href="EmployeeRequi_showApplicationList.action?status=P">Pending
										List</a> | <a
											href="EmployeeRequi_showApplicationList.action?status=A">Approved
										List</a> | <a
											href="EmployeeRequi_showApplicationList.action?status=R">Rejected
										List</a> | <a
											href="EmployeeRequi_showApplicationList.action?status=H">
										On Hold List</td>
									</tr>
								</table>
								</td>
							</tr>
						</table>
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
						<table width="99%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td height="27" class="formtxt"><strong> 
								<%	String status = (String) request.getAttribute("listType");
 									if (status != null) {
 										out.println(status);
 									} else {
 										out.println("Pending List");
 									}
 								%> 
 								</strong></td>
								<td align="right"><b>Page:</b> 
								<%
 									int total1 = 0;
 									int PageNo1 = 0;
 								try {
 										total1 = (Integer) request.getAttribute("abc");
 										System.out.println("val of rowCont" + total1);
 										PageNo1 = (Integer) request.getAttribute("xyz");
 									} catch (Exception e) {
 										e.printStackTrace();
 									}
 								%> <%
 									if (!(PageNo1 == 1)) {
 								%><a href="#" onclick="callPage('1','<%=status %>');"> <img
									src="../pages/common/img/first.gif" width="10" height="10"
									class="iconImage" /> </a>&nbsp; <a href="#"
									onclick="previousPage('<%=status %>')"><img
									src="../pages/common/img/previous.gif" width="10" height="10"
									class="iconImage" /></a> <%
 									}
 									if (total1 < 5) {
 									for (int i = 1; i <= total1; i++) {
 								%> <a href="#" onclick="callPage('<%=i %>','<%=status %>');"> <%
 									if (PageNo1 == i) {
 								%> <b><u><%=i%></u></b> <%
 									} else
 								%><%=i%> </a> <%
 									}
 								}
	
 								if (total1 >= 5) {
 								for (int i = 1; i <= 5; i++) {
 								%> <a href="#" onclick="callPage('<%=i %>','<%=status %>');"> <%
 								if (PageNo1 == i) {
 								%> <b><u><%=i%></u></b> <%
 								} else
 								%><%=i%> </a> <%
 						}
 	}
 	if (!(PageNo1 == 1)) {
 %>...<a href="#" onclick="nextPage('<%=status %>')"> <img
									src="../pages/common/img/next.gif" class="iconImage" /> </a>
								&nbsp;<a href="#"
									onclick="callPage('<%=total1%>','<%=status %>');"> <img
									src="../pages/common/img/last.gif" width="10" height="10"
									class="iconImage" /></a> <%
 }
 %> <select name="selectname" onchange="onPage('<%=status %>')"
									id="selectname">
									<%
									for (int i = 1; i <= total1; i++) {
									%>

									<option value="<%=i%>"><%=i%></option>
									<%
									}
									%>
								</select>
						</table>
						</td>
					</tr>
					<tr>
						<td colspan="1">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="sortable">
							<tr>
								<td width="6%" valign="top" class="formth">Sr No</td>
								<td width="13%" valign="top" class="formth">Requisition
								Code</td>
								<td width="13%" valign="top" class="formth">Requisition
								Date</td>
								<td width="15%" valign="top" class="formth">Applied For</td>
								<td width="15%" valign="top" class="formth">Applied By</td>
								<td width="15%" valign="top" class="formth">Hiring Manager</td>

							</tr>

							<s:if test="noData">
								<tr>
									<td width="100%" colspan="8" align="center"><font
										color="red">There is no data to display</font></td>
								</tr>
							</s:if>

							<%!int i = 0;%>
							<%
								int k = 1;
								int c = 0;
							%>

							<s:iterator value="loadList">
								<tr>
									<td width="6%" class="border2"> <s:property value="srNo" /></td>
									<td width="13%" class="border2"><s:property
										value="reqName" /> <s:hidden name="empCode" /> <s:hidden
										name="reqNo" /></td>
									<td width="13%" class="border2"><s:property value="reqDt" /></td>
									<td width="15%" class="border2"><s:property
										value="appliedFor" /></td>
									<td width="15%" class="border2"><s:property
										value="appliedBy" />&nbsp;</td>
									<td width="15%" class="border2"><s:property
										value="hiringManager" /></td>


								</tr>
								<%
									k++;
									c++;
								%>
							</s:iterator>

							<%
							i = k;
							%>
						</table>
						<input type="hidden" name="count" id="count" value="<%=c%>" /></td>
					</tr>
					<!--
                                 
                  
                  <tr>
                    <td ><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
                  </tr>
               
            -->
				</table>
				</td>
			</tr>
			<!--
        
        <tr>
          <td colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="4" /></td>
        </tr>
        -->
		</s:if>
		<s:else>
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="2">
							<tr>
								<strong class="text_head">Manpower Information : </strong>
								<td colspan="5" class="formhead"><!--
                    <img src="../pages/common/css/default/images/space.gif" width="5" height="7" />
                    --></td>
							</tr>
							<tr>

								<td width="24%" height="22" class="formtext"><label
									class="set" name="reqs.code" id="reqs.code"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label>
								:</td>
								<td width="27%" height="22"><label> <s:hidden
									name="reqName" id="reqName1" /> <s:property value="reqName" />
								</label></td>
								<td width="4%" height="22"></td>
								<td width="20%" height="22" class="formtext"><label
									class="set" name="reqs.date" id="reqs.date"
									ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label>
								:</td>
								<td width="25%" height="22"><s:property value="reqDt" /></td>
							</tr>
							<tr>
								<td height="22" class="formtext"><label class="set"
									name="position" id="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label>
								:</td>
								<td height="22"><s:hidden name="reqPositionCode" /><s:property
									value="reqPositionName" /></td>
								<td height="22"></td>
								<td height="22" class="formtext"><label class="set"
									name="appstatus" id="appstatus" ondblclick="callShowDiv(this);"><%=label.get("appstatus")%></label>
								:</td>
								<td height="22"><s:hidden name="reqApprStatus"
									id="reqApprStatus1" /><s:property value="reqApprStatus" /></td>
								<td height="22"></td>
							</tr>
							<s:hidden name="appliedBy" />
							<tr>
								<td height="22" class="formtext"><label class="set"
									name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
								:</td>
								<td height="22"><s:hidden name="reqDivCode" /><s:property
									value="reqDiv" /></td>
								<td height="22"></td>
								<td height="22" class="formtext"><label class="set"
									name="reqstatus" id="reqstatus" ondblclick="callShowDiv(this);"><%=label.get("reqstatus")%></label>
								:</td>
								<td height="22"><s:hidden name="reqStatus" /><s:property
									value="reqStatus" /></td>
								<td height="22"></td>


							</tr>


							<s:if test="referalFlag">
							</s:if>
							<s:else>



								<tr>
									<td height="22" class="formtext"><label class="set"
										name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td height="22"><s:hidden theme="simple" name="reqBrnCode" /><s:property
										value="reqBrn" /></td>
									<td height="22">&nbsp;</td>
									<td height="22" class="formtext"></td>
									<td height="22"></td>
								</tr>



								<tr>
									<td height="22" class="formtext"><label class="set"
										name="department" id="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:</td>
									<td height="22"><s:hidden theme="simple"
										name="reqDeptCode" /><s:property value="reqDept" /></td>
									<td height="22">&nbsp;</td>
									<td height="22" class="formtext"></td>
									<td height="22"></td>
								</tr>
								<%
									String internalVal = (String) request.getAttribute("int");
									String externalVal = (String) request.getAttribute("ext");
								%>
								<tr>

									<td height="22" class="formtext"><label class="set"
										name="hiring.mgr" id="hiring.mgr"
										ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label>
									:</td>
									<td height="22"><s:hidden name="hiringcode" /><s:hidden
										name="hiringToken" /> <s:property value="hiringManager" /></td>

									<td height="22"></td>
									<td height="22" class="formtext"><label class="set"
										name="recruitmenttype" id="recruitmenttype"
										ondblclick="callShowDiv(this);"><%=label.get("recruitmenttype")%></label>
									:</td>
									<td height="22"><input type="hidden" name="internal"
										id="internal" /><s:checkbox name="inter" disabled="true"
										id="inter" onclick="callForInternal();" /> <%
 if (String.valueOf(internalVal).equals("true")) {
 %>
									<script>
							document.getElementById('inter').checked =true;
						</script> <%
 }
 %> &nbsp;<label class="set" name="intern" id="intern"
										ondblclick="callShowDiv(this);"><%=label.get("intern")%></label>
									&nbsp; <s:checkbox name="exter" id="exter" disabled="true"
										onclick="callForExternal();" /><input type="hidden"
										name="external" id="external" /> <%
 if (String.valueOf(externalVal).equals("true")) {
 %>
									<script>
							document.getElementById('exter').checked =true;
						</script> <%
 }
 %> &nbsp;<label class="set" name="extern" id="extern"
										ondblclick="callShowDiv(this);"><%=label.get("extern")%></label>

									</td>
									<td height="22"></td>
								</tr>
							</s:else>

							 
			 <!-- APPROVER SECTION BEGINS -->
				<tr>
				<td colspan="6">
				 <table width="100%" border="0" cellpadding="1" cellspacing="0">
					<tr>
						<td colspan="1" width="35%" nowrap="nowrap"><strong>The
						Approver(s) for this application : </strong></td>

						<td colspan="1" width="15%" nowrap="nowrap"></td>

						<td colspan="1" width="20%" nowrap="nowrap"><strong>Keep
						Informed To : </strong></td>

						<td colspan="1" width="15%"> </td>

						<td colspan="1" width="5%"> </td>

						<td colspan="1" width="10%"> </td>
					</tr>

					<!-- APPROVER LIST  TABLE  STARTS -->
					<tr valign="top">
						<td colspan="3" rowspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<s:hidden name="firstApproverCode" />
								<s:hidden name="firstApproverToken" />
								<s:iterator value="approverList">
									<tr>
										<td><s:hidden name="approverName" /> <STRONG><s:property
											value="srNoIterator" /></STRONG> <s:property value="approverName" />
										</td>
									</tr>
								</s:iterator>
							</tr>
						</table>
						</td>
					</tr>
					<!-- APPROVER LIST  TABLE  ENDS -->

					<!-- KEEP INFORMED LIST TABLE  STARTS -->
					<tr valign="top">
						<td colspan="3">
						  <table width="100%" border="0" cellpadding="0" cellspacing="0">
								<%
									int counter11 = 0;
									int counter2 = 0;
								%>
							 <s:iterator value="keepInformedList" status="stat">
								<tr>
									<td colspan="1" width="10%">&nbsp;</td>
									<td colspan="1" width="60%" nowrap="nowrap"><%=++counter11%>
									<s:hidden name="serialNo" /> <s:hidden
										name="keepInformedEmpNameItt" /> <s:property
										value="keepInformedEmpNameItt" /> <s:hidden name="empid" />
									</td>
									<td width="30%"> </td>
								</tr>
								<%
									counter2 = counter11;
								%>
							</s:iterator>
							<%
								counter2 = 0;
							%>
						  </table>
						 </td>
						<td></td>
					 </tr>
				 <!-- KEEP INFORMED LIST TABLE  ENDS -->
				</table>
				</td>
			</tr>
		<!-- APPROVER SECTION ENDS -->
						  </table>
						</td>
					</tr>
				</table>
			  </td>
			</tr>
			<tr>
				<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<strong class="formhead"> </strong>
						<td>
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="0">


							<tr>
								<strong class="text_head">Job Description : </strong>
								<td colspan="5" class="formhead"><img
									src="../pages/common/css/default/images/space.gif" width="5"
									height="7" /></td>
							</tr>

							<tr>
								<td width="24%" colspan="1" height="27"><label class="set"
									name="job.name" id="job.name" ondblclick="callShowDiv(this);"><%=label.get("job.name")%></label>
								:</td>
								<td width="76%" height="27" colspan="4">
									<s:hidden name="jdCode" />
									 <s:property value="jdDescName"/>
								</td>
							</tr>

							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>

							<tr>
								<td width="24%" height="22" class="formtext" colspan="1"
									valign="top"><label class="set" name="job.desc"
									id="job.desc" ondblclick="callShowDiv(this);"><%=label.get("job.desc")%></label>
								:</td>
								<td width="76%" valign="top">
									<s:textarea theme="simple" cols="35" rows="2" name="jdDesc" disabled="true"/>
									<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_jdDesc','job.desc','readonly','','2000');" >
								</td>
							</tr>

							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td width="24%" colspan="1" height="27" valign="top"><label
									class="set" name="roles.res" id="roles.res"
									ondblclick="callShowDiv(this);"><%=label.get("roles.res")%></label>
								:</td>
								<td width="76%" height="27" colspan="4" valign="top">
									<s:textarea theme="simple" cols="35" rows="2" name="jdRoleDesc" disabled="true"/>
									<img src="../pages/images/zoomin.gif" height="12" align="bottom" width="12" theme="simple"
										onclick="javascript:callWindow('paraFrm_jdRoleDesc','roles.res','readonly','','2000');" >
								</td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>

							<tr>

								<td width="24%" height="22" class="formtext" valign="top"><label
									class="set" name="specialreq" id="specialreq"
									ondblclick="callShowDiv(this);"><%=label.get("specialreq")%></label>
								:</td>
								<td width="76%" height="27" valign="top" colspan="4"><label>
								<s:property value="specialReq" /> </label></td>


							</tr>

							<tr>
								<td>&nbsp;&nbsp;</td>
							</tr>
							<tr>
								<td width="24%" colspan="1" height="27" valign="top"><label
									class="set" name="persreq" id="persreq"
									ondblclick="callShowDiv(this);"><%=label.get("persreq")%></label>
								:</td>
								<td width="76%" height="27" colspan="4"><s:property
									value="persoanlReq" /></td>
							</tr>

							<!-- <tr>
							<td width="24%" colspan="1" height="27"><label  class = "set"  name="job.name" id="job.name" ondblclick="callShowDiv(this);"><%=label.get("job.name")%></label> :</td>
							<td height="27" colspan="4"><s:hidden name="jdCode"/><s:property value="jdDescName" />
                            </td>
					    </tr>
					    
					     <tr>
              
                    		<td width="24%" height="22" class="formtext" colspan="1"><label  class = "set"  name="job.desc" id="job.desc" ondblclick="callShowDiv(this);"><%=label.get("job.desc")%></label> :</td>
                    		<td width="27%" ><label>
                       		<s:property value="jdDesc"   />
                    		</label></td>
                    		<td width="4%" height="22" colspan="3"></td>
                    		<td width="20%" height="22" class="formtext" ><label  class = "set"  name="roles.res" id="roles.res" ondblclick="callShowDiv(this);"><%=label.get("roles.res")%></label> :</td>
                    		<td width="25%" height="22" colspan="1">
                     		 <s:property value="jdRoleDesc" />
             
                     		</td>
                  	</tr>
					    
					    
					    <!--
					    
					    <tr>
							<td width="24%" colspan="1" height="27" valign="top"><label  class = "set"  name="jobdescription" id="jobdescription" ondblclick="callShowDiv(this);"><%=label.get("jobdescription")%></label> :</td>
							<td width="25" colspan="1" height="27" valign="top" >
                            <s:property value="jdDesc" /></td>
							<td width="4%" height="23" colspan="1"></td>
							<td width="20%" colspan="2" height="27" valign="top"><label  class = "set"  name="jobroleresp" id="jobroleresp" ondblclick="callShowDiv(this);"><%=label.get("jobroleresp")%></label> :</td>
							<td height="27" width="25%">
                             <s:property value="jdRoleDesc" /></td>
					    </tr>
					    
					      -->
							<!-- <tr>
							<td width="24%" colspan="1" height="27"></td>
							<td height="27" colspan="4">
                            </td>
					    </tr>
					    
					     <tr>
              
                    		<td width="24%" height="22" class="formtext" colspan="1"><label  class = "set"  name="specialreq" id="specialreq" ondblclick="callShowDiv(this);"><%=label.get("specialreq")%></label>:</td>
                    		<td width="27%" ><label>
                       		<s:property value="specialReq"   />
                    		</label></td>
                    		<td width="4%" height="22" colspan="3"></td>
                    		<td width="20%" height="22" class="formtext" ><label  class = "set"  name="persreq" id="persreq" ondblclick="callShowDiv(this);"><%=label.get("persreq")%></label> : </td>
                    		<td width="25%" height="22" colspan="1">
                     		 <s:property value="persoanlReq" />
             
                     		</td>
                  	</tr>-->
							<!--
					    
					    <tr>
							<td width="24%" colspan="2" height="27" valign="top"><label  class = "set"  name="specialreq" id="specialreq" ondblclick="callShowDiv(this);"><%=label.get("specialreq")%></label>:</td>
							<td height="27" >
                              <s:property value="specialReq"/></td>
							<td height="23" width="4%" colspan="1"></td>
							<td width="20%" colspan="2" height="27" valign="top"><label  class = "set"  name="persreq" id="persreq" ondblclick="callShowDiv(this);"><%=label.get("persreq")%></label> : </td>
							<td height="27" width="25%" valign="top">
                           <s:property value="persoanlReq" /></td>
					    </tr>
					    
					    
					    
					    
					    
					    
					    
					    -->
							<!--
                
  						<tr>
							<td width="20%" colspan="2" height="27" valign="top"><label  class = "set"  name="jobdescription" id="jobdescription" ondblclick="callShowDiv(this);"><%=label.get("jobdescription")%></label> :</td>
							<td height="27" valign="top">
                            <s:property value="jdDesc" /></td>
				
							<td width="20%" colspan="2" height="27" valign="top"><label  class = "set"  name="jobroleresp" id="jobroleresp" ondblclick="callShowDiv(this);"><%=label.get("jobroleresp")%></label> : </td>
							<td height="27" valign="top">
                            <s:property value="jdRoleDesc" /></td>
					    </tr>
					    
					    <tr>
							<td width="20%" colspan="2" height="27" valign="top"><label  class = "set"  name="specialreq" id="specialreq" ondblclick="callShowDiv(this);"><%=label.get("specialreq")%></label>:</td>
							<td height="27" valign="top">
                           <s:property value="specialReq" /></td>
				
							<td width="20%" colspan="2" height="27" valign="top"><label  class = "set"  name="persreq" id="persreq" ondblclick="callShowDiv(this);"><%=label.get("persreq")%></label> :</td>
							<td height="27" valign="top">
                            <s:property value="persoanlReq" /></td>
					    </tr>
				-->
						</table>
						</td>
					</tr>
				</table>






				<s:if test="dashletFlag">
				</s:if> <s:else>

					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2">

								<tr>
									<td class="formtext">
									<table width="100%" border="0" cellpadding="0" cellspacing="0"
										class="formbg">
										<tr class="sortable">
											<td>
											<table width="100%" border="0" cellpadding="0"
												cellspacing="0">
												</td>
												</tr>

												<tr>
													<strong class="text_head">Vacancy Details : </strong>
													<td colspan="5">
													<table width="100%" id="tblVac" class="sortable">
														<tr>
															<td width="10%" valign="top" class="formth"
																nowrap="nowrap"><b><label class="set" name="sr"
																id="sr1" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
															<td width="15%" valign="top" class="formth"
																align="center"><b><label class="set"
																name="vacn" id="vacn" ondblclick="callShowDiv(this);"><%=label.get("vacn")%></label></b></td>
															<td width="20%" valign="top" class="formth"
																align="center"><b><label class="set"
																name="required.date" id="required.date"
																ondblclick="callShowDiv(this);"><%=label.get("required.date")%></label></b></td>



														</tr>
														<%
														int v = 0;
														%>
														<s:iterator value="vacList">
															<tr>
																<td width="10%" align="center" class="sortableTD"><%=++v%><!--
                                   
                                   
                                   <s:property value="srNo" />
                                   
                                   --></td>

																<td width="15%" align="left" class="sortableTD">&nbsp;
																<s:property value="noOfVac" id="<%="noOfVac"+v%>" /></td>
																<td width="15%" align="left" class="sortableTD">&nbsp;
																<s:property value="vacDate" id="<%="vacDate"+v%>" /></td>


															</tr>
														</s:iterator>
													</table>
													</td>
												</tr>
												<!--
                       
                       <tr>
                    <td width="19%" height="22" class="formtext" nowrap="nowrap">
					Compensation In Lacs :</span></td>
                    <td width="30%" height="22"><s:property value="reqCompensation"  />
                    </td>
                    <td width="20%" height="22" class="formtext" nowrap="nowrap">
					Experience :</td>
                    <td width="32%" height="22">Min Exp <label>
                      <s:property value="minExp"  />
					  Max Exp 
                    </label><s:property value="maxExp" /></td>
                  </tr>
                  
                   <tr>
                    <td width="19%" height="22" class="formtext" nowrap="nowrap">
					Vacancy Type :</span></td>
                    <td width="30%" height="22"><label>
                   <s:property value="vacType"
				/></label></td>
                    <td width="20%" height="22" class="formtext" nowrap="nowrap">
					 Contract Type :</td>
                    <td width="32%" height="22"><label>
                    <s:property value="reqConType" />
					 <s:property value="reqPartFull" /></label></td>
                  </tr>
                  
                  -->
											</table>
											</td>
										</tr>






									</table>
									</td>
								</tr>
								<!--
         
          
           <tr>
            <td colspan="2" class="formtext">
            
            
            
            <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          </tr>
      -->
							</table>
							<table width="100%">


								<tr>

									<td width="24%" height="22" class="formtext" nowrap="nowrap"><label
										class="set" name="comp" id="comp"
										ondblclick="callShowDiv(this);"><%=label.get("comp")%></label>
									:</td>

									<td width="27%" height="22"><s:property
										value="reqCompensation" /></td>
									<td width="4%" height="22"></td>
									<td width="20%" height="22" class="formtext" nowrap="nowrap"
										colspan="1">&nbsp;<label class="set" name="experience"
										id="experience" ondblclick="callShowDiv(this);"><%=label.get("experience")%></label>
									:</td>
									<td width="25%" height="22" colspan="1"><label class="set"
										name="minexp" id="minexp" ondblclick="callShowDiv(this);"><%=label.get("minexp")%></label><label>
									<s:property value="minExp" /> <label class="set" name="maxexp"
										id="maxexp" ondblclick="callShowDiv(this);"><%=label.get("maxexp")%></label>
									<s:property value="maxExp" /></td>
								</tr>

								<tr>

									<td width="24%" height="22" class="formtext"><label
										class="set" name="vactype" id="vactype"
										ondblclick="callShowDiv(this);"><%=label.get("vactype")%></label>
									:</span></td>
									<td width="27%" height="22"><label> <s:property
										value="vacType" /></label></td>
									<td width="4%" height="22"></td>
									<td width="20%" height="22" class="formtext" nowrap="nowrap"><label
										class="set" name="contracttype" id="contracttype"
										ondblclick="callShowDiv(this);"><%=label.get("contracttype")%></label>
									:</td>
									<td width="25%" height="22"><label> <s:property
										value="reqConType" /></label><label> <s:property
										value="reqPartFull" /></label></td>
								</tr>
							</table>



							</td>

						</tr>

					</table>
				</s:else> <!--<img src="../pages/common/css/default/images/space.gif" width="5" height="7" />


	 --> <s:if test="quaFlag">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<strong class="text_head">Qualification Details :</strong>
							<td colspan="8">

							<table id="tblQuali" width="100%" class="sortable">
								<tr>
									<s:hidden name="updateSecondFlag" />
									<td width="5%" class="formth" nowrap="nowrap" align="center"><b><label
										class="set" name="sr" id="sr" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
									<td width="13%" valign="top" class="formth" align="center"><b><label
										class="set" name="qtyp" id="qtyp"
										ondblclick="callShowDiv(this);"><%=label.get("qtyp")%></label></b></td>
									<td width="17%" valign="top" class="formth" align="center"><b><label
										class="set" name="qabbr" id="qabbr"
										ondblclick="callShowDiv(this);"><%=label.get("qabbr")%></label></b></td>
									<td width="20%" valign="top" class="formth" align="right"
										nowrap="nowrap" align="center"><b><label class="set"
										name="qlvl" id="qlvl" ondblclick="callShowDiv(this);"><%=label.get("qlvl")%></label></b></td>
									<td width="25%" valign="top" class="formth" align="center"><b><label
										class="set" name="spabbr" id="spabbr"
										ondblclick="callShowDiv(this);"><%=label.get("spabbr")%></label></b></td>

									<td width="11%" valign="top" class="formth" align="center"
										nowrap="nowrap"><b><label class="set" name="mrks"
										id="mrks" ondblclick="callShowDiv(this);"><%=label.get("mrks")%></label></b></td>
									<td width="10%" valign="top" class="formth" align="left"><b><label
										class="set" name="opt" id="opt"
										ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></b></td>


								</tr>
								<%!int v = 0;%>
								<%
								int i = 0;
								%>
								<%
								int jj = 0;
								%>

								<s:if test="quaFlag">
									<s:iterator value="qualList">

										<tr>
											<td width="5%" align="center" class="sortableTD"><%=++i%></td>
											<td width="13%" align="left" class="sortableTD">&nbsp; <s:property
												value="hqualType" /></td>


											<%
												String quaId = (String) request.getAttribute("qualificationId" + i);

												String qualName = (String) request.getAttribute("qualificationName"
														+ i);
												String lvl = (String) request.getAttribute("hqualiLevelName" + i);
												String spl = (String) request.getAttribute("hsplName" + i);
												String splId = (String) request.getAttribute("hsplId" + i);
											%>
											<td nowrap="nowrap" width="17%" align="left"
												class="sortableTD">&nbsp;<%=qualName%></td>


											<td width="20%" align="left" class="sortableTD">&nbsp;<%=lvl%></td>




											<td width="25%" align="left" nowrap="nowrap"
												class="sortableTD">&nbsp;<%=spl%></td>





											<td width="11%" align="left" class="sortableTD">&nbsp;<s:property
												value="hcutOff" /></td>

											<td width="10%" align="left" class="sortableTD">&nbsp;&nbsp;&nbsp;<s:property
												value="sel" /></td>




										</tr>


									</s:iterator>
								</s:if>
							</table>
							</td>
						</tr>
					</table>

				</s:if> <s:if test="skill">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						</td>
						</tr>

						<tr>
							<strong class="text_head">Skill Details : </strong>
							<td colspan="8">

							<table border="0" id="tblSkill" width="100%" class="sortable"">
								<tr>
									<td width="5%" valign="top" class="formth" nowrap="nowrap"
										align="center"><b><label class="set" name="sr"
										id="sr1" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
									<td width="13%" valign="top" class="formth" align="center"
										nowrap="nowrap"><b><label class="set" name="styp"
										id="styp" ondblclick="callShowDiv(this);"><%=label.get("styp")%></label></b></td>
									<td width="37%" valign="top" class="formth" align="center"
										nowrap="nowrap"><b><label class="set" name="sname"
										id="sname" ondblclick="callShowDiv(this);"><%=label.get("sname")%></label></b></td>

									<td width="34%" valign="top" class="formth" align="center"
										nowrap="nowrap"><b><label class="set"
										name="experience" id="experience1"
										ondblclick="callShowDiv(this);"><%=label.get("experience")%></label></b></td>
									<td width="9%" valign="top" class="formth" align="center"
										nowrap="nowrap"><b><label class="set" name="opt"
										id="opt" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></b></td>



								</tr>
								<%!int s = 0;%>

								<%
								int j = 0;
								%>

								<s:if test="skill">
									<s:iterator value="skillList">

										<tr>
										<tr>
											<td width="5%" align="center" class="sortableTD"><%=++j%><s:hidden
												name="hssrlNo" /></td>
											<td width="13%" align="left" nowrap="nowrap"
												class="sortableTD">&nbsp; <s:property value="skillType" />
											</td>


											<%
												String si = (String) request.getAttribute("skillId" + j);
												String sn = (String) request.getAttribute("skillName" + j);
											%>
											<td nowrap="nowrap" width="36%" align="left"
												class="sortableTD">&nbsp;<%=sn%></td>


											<td width="34%" align="center" class="sortableTD">&nbsp;<s:property
												value="skillExp" /></td>
											<td width="9%" align="center" class="sortableTD">&nbsp;

											<s:property value="skillSel" /></td>




										</tr>




										</tr>


									</s:iterator>


								</s:if>


							</table>
							</td>
						</tr>
						<!--
                      
                      <tr><td>  <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                      
                      </tr>
                      
                       <tr><td>  <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                      
                      </tr>
                            
            -->
					</table>
				</s:if> <s:if test="%{domFlag}">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">

						<tr>
							<strong class="text_head">Domain/Functional Details : </strong>
							<td colspan="8">

							<table border="0" id="tblDom" width="100%" class="sortable">
								<%
								int d = 0;
								%>
								<tr>

									<td width="5%" valign="top" class="formth" nowrap="nowrap"
										align="center"><b><label class="set" name="sr"
										id="sr2" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
									<td width="13%" valign="top" class="formth" nowrap="nowrap"><b><label
										class="set" name="dtyp" id="dtyp"
										ondblclick="callShowDiv(this);"><%=label.get("dtyp")%></label></b></td>
									<td width="37%" valign="top" class="formth" nowrap="nowrap"
										align="center"><b><label class="set" name="dname"
										id="dname" ondblclick="callShowDiv(this);"><%=label.get("dname")%></label></b></td>
									<td width="34%" valign="top" class="formth" nowrap="nowrap"
										align="center"><b><label class="set"
										name="experience" id="experience2"
										ondblclick="callShowDiv(this);"><%=label.get("experience")%></label></b></td>
									<td width="9%" valign="top" class="formth" nowrap="nowrap"
										align="center"><b><label class="set" name="opt"
										id="opt" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></b></td>



								</tr>
								<%
								int m = 0;
								%>
								<s:if test="%{domFlag}">

									<s:iterator value="domList">
										<tr>
											<td width="5%" align="center" class="sortableTD"><%=++m%></td>
											<%
												String domain = (String) request.getAttribute("domName" + m);
												String code = (String) request.getAttribute("domId" + m);
											%>
											<td width="13%" class="sortableTD">&nbsp;<s:property
												value=" domType" /></td>
											<td width="37%" class="sortableTD">&nbsp;<%=domain%></td>




											<td width="34%" align="center" class="sortableTD">&nbsp;<s:property
												value=" domExp" /></td>

											<td width="9%" align="center" class="sortableTD">&nbsp;<s:property
												value="domSel" /></td>




										</tr>
									</s:iterator>

								</s:if>
							</table>


							</td>
						</tr>
						<!--<tr>
            <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          </tr>
      -->
					</table>
				</s:if> <s:if test="%{certFlag}">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">

						<tr>
							<strong class="text_head">Certification Details : </strong>
							<td>

							<table border="0" id="tblCert" width="100%" class="sortable">

								<tr>


									<td width="5%" valign="top" class="formth" nowrap="nowrap"
										align="center"><b><label class="set" name="sr"
										id="sr5" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
									<td width="13%" valign="top" class="formth" align="center"><b><label
										class="set" name="ctyp" id="ctyp"
										ondblclick="callShowDiv(this);"><%=label.get("ctyp")%></label></b></td>
									<td width="24%" valign="top" class="formth" nowrap="nowrap"
										align="left"><b><label class="set" name="cname"
										id="cname" ondblclick="callShowDiv(this);"><%=label.get("cname")%></label></b></td>
									<td width="24%" valign="top" class="formth" nowrap="nowrap"
										align="left"><b><label class="set" name="ciss"
										id="ciss" ondblclick="callShowDiv(this);"><%=label.get("ciss")%></label></b></td>
									<td width="25%" valign="top" class="formth" nowrap="nowrap"
										align="center"><b><label class="set" name="cvalid"
										id="cvalid" ondblclick="callShowDiv(this);"><%=label.get("cvalid")%></label></b></td>
									<td width="9%" valign="top" class="formth" nowrap="nowrap"
										align="left">&nbsp;&nbsp;&nbsp;&nbsp;<b><label
										class="set" name="opt" id="opt"
										ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></b></td>


								</tr>

								<s:if test="%{certFlag}">
									<%
									int ce = 0;
									%>
									<s:iterator value="certList">
										<tr>
											<td width="5%" align="center" class="sortableTD"><%=++ce%></td>

											<td width="13%" align="left" class="sortableTD">&nbsp;<s:property
												value="certType" /></td>
											<td width="24%" align="left" class="sortableTD">&nbsp;<s:property
												value="certName" /></td>

											<td width="24%" align="left" class="sortableTD">&nbsp;<s:property
												value="certIssueBy" /></td>
											<td width="25%" align="left" class="sortableTD">&nbsp;<s:property
												value="certValid" /></td>

											<td width="9%" align="left" class="sortableTD">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<s:property value="certOption" /></select></td>





										</tr>
									</s:iterator>

								</s:if>
							</table>



							</td>
						</tr>
						<!--
          <tr>
            <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          </tr>
      -->
					</table>

				</s:if></td>

			</tr>
			<!--
        
        <tr>
          <td><img src="../pages/common/css/default/images/space.gif" /></td>
        </tr>
        
          -->
			<tr>
				<td width="78%"><s:if test="dashletFlag">
				</s:if> <s:else>
					<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
				</s:else></td>
				<td width="22%" align="right"><strong></strong></td>
				<td></td>

			</tr>

		</s:else>
	</table>

</s:form>
<script>
	function sendforapprovalFun(){
		
	var conf=confirm("Do you really want to send this record for Approval ?");
   if(conf) {
	
	     document.getElementById('paraFrm').action="EmployeeRequi_sendForApproval.action";
	   	 document.getElementById('paraFrm').submit();
	   	 
	   }
	}
	
	function cancelFun(){
	if(document.getElementById('paraFrm_enableFirst').value=="true"){
			
			document.getElementById('paraFrm').action="EmployeeRequi_showFirstEnabledPage.action";
			document.getElementById('paraFrm').submit();
	}else if(document.getElementById('paraFrm_enableSecond').value=="true"){
		
			document.getElementById('paraFrm').action="EmployeeRequi_showSecondEnabledPage.action";
			document.getElementById('paraFrm').submit();
	}else if(document.getElementById('paraFrm_viewFirstCancel').value=="true"){
			
			document.getElementById('paraFrm').action="EmployeeRequi_showfirstView.action";
			document.getElementById('paraFrm').submit();
	}else{
			
			  document.getElementById('paraFrm').action="EmployeeRequi_showSecondView.action";
			  document.getElementById('paraFrm').submit();
		
	}
	return false;
	
	}
	
	
function editFun(){
	var status = document.getElementById("paraFrm_hiddenApproveStatus").value;
	if(status=="Pending") {
		alert("Application is already pending.So can't be edited.");
		return false;
	} else if(status=="Approved") {
		alert("Application is already approved.So can't be edited.");
		return false;
	} else if(status=="On Hold") {
		alert("Application is On Hold.So can't be edited.");
		return false;
	} else if(status=="Quick Requisition")	{
		alert("Quick Requisition can't be edited.");
		return false;
	} else {
		document.getElementById("paraFrm").action="EmployeeRequi_edit.action";
	    document.getElementById("paraFrm").submit();
	}
	return false;
}
	
	
	</script>
