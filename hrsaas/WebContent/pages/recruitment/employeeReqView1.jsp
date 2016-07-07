<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<%@page import="java.util.HashMap"%>
<s:form action="EmployeeRequi" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<s:hidden name="addNewFlag" />
	<s:hidden name="dashletFlag" />
	<s:hidden name="reqFlag" />
	<s:hidden name="hiddenStatus" />
	<s:hidden name="hiddenApproveStatus" />
	<s:hidden name="leadTimeFlag" />
	<s:hidden name="reqName" />
	<s:hidden name="flagView" />
	<s:hidden name="jdEffDate" />
	<s:hidden name="createNewFlag" />
	<s:hidden name="offerAppointFlag" />
	<s:hidden name="formAction" id="formAction" />
	<s:hidden name="statusKey" id="statusKey" />
	<s:hidden name="formFlag" id="formFlag" />
	<s:hidden name="backFlag" />

	<s:hidden name="viewDom" />
	<s:hidden name="myPage" />
	<s:hidden name="viewCert" />
	<s:hidden name="viewQua" />
	<s:hidden name="viewSkill" />
	<s:hidden name="position" />
	<s:hidden name="division" />
	<s:hidden name="branch" />
	<s:hidden name="department" />
	<s:hidden name="requisitionName" />
	<s:hidden name="requisitionCode" />
	<s:hidden name="viewDom" />
	<s:hidden name="viewCert" />
	<s:hidden name="viewQua" />
	<s:hidden name="viewSkill" />
	<s:hidden name="reqPositionName" />
	<s:hidden name="reqDiv" />
	<s:hidden name="reqBrn" />
	<s:hidden name="reqDept" />
	<s:hidden name="srNo" />
	<s:hidden name="reqCode" />
	<s:hidden name="reqsLevel" />

	<s:hidden name="positionCode" />
	<s:hidden name="divisionCode" />
	<s:hidden name="branchCode" />
	<s:hidden name="deptCode" />
	<s:hidden name="replaceEmpId" />
	<s:hidden name="source" id="source" />
	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td colspan="3">
			<table width="100%" cellpadding="0" cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/common/css/default/images/review_shared.gif"
						width="25" height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Manpower
					Requisition</strong></td>
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
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /> <s:if
						test="%{offerAppointFlag}">
						<s:submit cssClass="token"
							action="EmployeeRequi_backToOfferAppoint" theme="simple"
							value='Back To %{createNewFlag}' onclick="return callView()" />
					</s:if></td>
					<td width="22%"></td>
				</tr>
			</table>
			</td>
		</tr>
		 
		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="100%"><strong class="formhead">Competency
					Details :</strong></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2">
				<tr>
					<td class="formtext">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td>
							<%
							try {
							%>
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								</td>
								</tr>
								<tr>
									<strong class="text_head">Qualification Details :</strong>
									<td colspan="7">

									<table id="tblQuali" width="100%" class="formbg">
										<tr>
											<s:hidden name="updateSecondFlag" />

											<td width="5%" class="formth" nowrap="nowrap" align="center"><b><label
												class="set" name="sr" id="sr"
												ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
											<td width="13%" valign="top" class="formth" align="center"><b><label
												class="set" name="qtyp" id="qtyp"
												ondblclick="callShowDiv(this);"><%=label.get("qtyp")%></label></b></td>
											<td width="17%" valign="top" class="formth" align="center"><b><label
												class="set" name="qabbr" id="qabbr"
												ondblclick="callShowDiv(this);"><%=label.get("qabbr")%></label></b></td>
											<td width="20%" valign="top" class="formth" align="right"
												nowrap="nowrap" align="center"><b><label
												class="set" name="qlvl" id="qlvl"
												ondblclick="callShowDiv(this);"><%=label.get("qlvl")%></label><b></td>
											<td width="25%" valign="top" class="formth" align="center"><b><label
												class="set" name="spabbr" id="spabbr"
												ondblclick="callShowDiv(this);"><%=label.get("spabbr")%></label></b></td>

											<td width="10%" valign="top" class="formth" align="center"
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
													<td width="13%" align="left" class="sortableTD">&nbsp;
													<s:property value="hqualType" /> 
													<!--
                                   						<s:select name="hqualType" disabled="true" cssStyle="width:75" theme="simple" list="#{'':'Select','E':'Essential','D':'Desirable'}" />
                                   					-->
                                   					</td>
													<%
															String quaId = (String) request.getAttribute("qualificationId"
															+ i);

															String qualName = (String) request
															.getAttribute("qualificationName" + i);
															String lvl = (String) request.getAttribute("hqualiLevelName"
															+ i);
															String spl = (String) request.getAttribute("hsplName" + i);
															String splId = (String) request.getAttribute("hsplId" + i);
													%>
													<td nowrap="nowrap" width="17%" align="left"
														class="sortableTD">&nbsp;<%=qualName%></td>

													<td width="20%" align="left" class="sortableTD">&nbsp;<%=lvl%></td>
													<td width="25%" align="left" nowrap="nowrap"
														class="sortableTD">&nbsp;<%=spl%></td>
													<td width="10%" align="left" class="sortableTD">&nbsp;<s:property
														value="hcutOff" /></td>
													<td width="10%" align="left" class="sortableTD">&nbsp;&nbsp;&nbsp;<s:property
														value="sel" />
														<!--
															<s:select name="sel" disabled="true" cssStyle="width:50" theme="simple" list="#{'':'','A':'And','R':'Or'}" />
														-->
													</td>
												</tr>
											</s:iterator>
										</s:if>

										<s:else>
											<tr>
												<td align="center" colspan="7" width="100%"><font
													color="red">There is no data to display.</font></td>
											</tr>
										</s:else>
									</table>
									</td>
								</tr>
							</table>
							<%
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
					<td colspan="6">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td>
							<%
							try {
							%>
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								</td>
								</tr>
								<tr>
									<strong class="text_head">Skill Details : </strong>
									<td colspan="7">

									<table border="0" id="tblSkill" width="100%">
										<tr>
											<td width="5%" valign="top" class="formth" nowrap="nowrap"
												align="center"><b><label class="set" name="sr"
												id="sr1" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
											<td width="12.5%" valign="top" class="formth" align="center"
												nowrap="nowrap"><b><label class="set" name="styp"
												id="styp" ondblclick="callShowDiv(this);"><%=label.get("styp")%></label></b></td>
											<td width="37%" valign="top" class="formth" align="center"
												nowrap="nowrap"><b><label class="set" name="sname"
												id="sname" ondblclick="callShowDiv(this);"><%=label.get("sname")%></label></b></td>

											<td width="34.5%" valign="top" class="formth" align="center"
												nowrap="nowrap"><b><label class="set"
												name="experience" id="experience"
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
													<td width="5%" align="center" class="sortableTD"><%=++j%><s:hidden
														name="hssrlNo" /></td>
													<td width="13%" align="left" nowrap="nowrap"
														class="sortableTD">&nbsp; <s:property
														value="skillType" /> 
														<!--
	                                   						<s:select name="skillType" id="<%="skillType"+j %>"
			   												disabled="true" cssStyle="width:75" theme="simple" list="#{'':'Select','E':'Essential','D':'Desirable'}" />
			   											-->
			   										</td>

													<%
															String si = (String) request.getAttribute("skillId" + j);
															String sn = (String) request.getAttribute("skillName" + j);
													%>
													<td nowrap="nowrap" width="37%" align="left"
														class="sortableTD">&nbsp;<%=sn%></td>

													<td width="34%" align="center" class="sortableTD">&nbsp;<s:property
														value="skillExp" /></td>
													<td width="9%" align="center" class="sortableTD">&nbsp;
													<s:property value="skillSel" /> 
													<!--<s:select name="skillSel" cssStyle="width:65" disabled="true" theme="simple" list="#{'A':'And','R':'Or'}" /> -->
													</td>
												</tr>
											</s:iterator>
										</s:if>
										<s:else>
											<tr>
												<td align="center" colspan="7" width="100%"><font
													color="red">There is no data to display.</font></td>
											</tr>
										</s:else>
									</table>
									</td>
								</tr>
							</table>
							<%
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

					<td colspan="6">

					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<%
							int m = 0, n = 1;
							%>

							<td>
							<%
							try {
							%>
							<table width="100%" border="0" cellpadding="2" cellspacing="2">
								</td>
								</tr>
								<tr>
									<strong class="text_head">Domain/Functional Details :
									</strong>
									<td colspan="8">
									<table border="0" id="tblDom" width="100%">
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
												name="experience" id="experience1"
												ondblclick="callShowDiv(this);"><%=label.get("experience")%></label></b></td>
											<td width="9%" valign="top" class="formth" nowrap="nowrap"
												align="center"><b><label class="set" name="opt"
												id="opt" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></b></td>
										</tr>

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
										<s:else>
											<tr>
												<td align="center" width="100%" colspan="6"><font
													color="red">There is no data to display.</font></td>
											</tr>
										</s:else>
									</table>

									<%
											} catch (Exception e) {
											e.printStackTrace();
										}
									%>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
				<tr>
					<td colspan="6">
					<table width="100%" border="0" class="formbg">
						<tr>
							<%
							int c = 0;
							%>

							<td>
							<%
							try {
							%>
							<table width="100%" border="0">
								</td>
								</tr>
								<tr>
									<td><strong class="text_head">Certification
									Details : </strong></td>
								</tr>
								<tr>
									<td>
									<table border="0" id="tblCert" width="100%">
										<tr>
											<td width="5%" valign="top" class="formth" nowrap="nowrap"
												align="center"><b><label class="set" name="sr"
												id="sr3" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b></td>
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

											<s:iterator value="certList">
												<tr>
													<td width="5%" align="center" class="sortableTD"><%=++c%></td>

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
										<s:else>

											<tr>
												<td align="center" width="100%" colspan="6"><font
													color="red">There is no data to display.</font></td>
											</tr>

										</s:else>
									</table>


									</td>
								</tr>

							</table>

							<%
									} catch (Exception e) {

									e.printStackTrace();

								}
							%>
							</td>
						</tr>

					</table>
					</td>
				</tr>


			</table>
			<s:hidden name="rowId" /></td>
		</tr>

		<tr>

			<td width="100%" colspan="3"><s:hidden name="apprvFlag" /> <s:if
				test="apprvFlag">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td nowrap="nowrap"><strong>Approver Details :</strong></td>
					</tr>
					<tr>
						<td class="formth" width="10%"><b> <label class="set"
							name="sr" id="sr1" ondblclick="callShowDiv(this);"><%=label.get("sr")%></label></b>
						</td>
						<td class="formth" width="25%"><b><label class="set"
							name="apprvName" id="apprvName" ondblclick="callShowDiv(this);"><%=label.get("apprvName")%></label></b></td>
						<td class="formth" width="20%"><b><label class="set"
							name="apprvDate" id="apprvDate" ondblclick="callShowDiv(this);"><%=label.get("apprvDate")%></label></b></td>
						<td class="formth" width="15%"><b><label class="set"
							name="appstatus" id="appstatus1" ondblclick="callShowDiv(this);"><%=label.get("appstatus")%></label></b></td>
						<td class="formth" width="30%"><b><label class="set"
							name="apprvRem" id="apprvRem" ondblclick="callShowDiv(this);"><%=label.get("apprvRem")%></label></b></td>

					</tr>
					<%
					int i = 0;
					%>
					<s:iterator value="apprvList">
						<tr>
							<td class="sortableTD" width="10%" align="center">&nbsp;<%=++i%></td>
							<td class="sortableTD" width="25%">&nbsp;<s:property
								value="apprvName" /></td>
							<td class="sortableTD" width="20%">&nbsp;<s:property
								value="apprvDate" /></td>
							<td class="sortableTD" width="15%">&nbsp;<s:property
								value="apprvStat" /></td>
							<td class="sortableTD" width="30%">&nbsp;<s:property
								value="apprvRem" /></td>

						</tr>
					</s:iterator>
				</table>
			</s:if></td>

		</tr>

		<s:hidden name="commentFlag" />
		<s:if test="commentFlag">
			<tr>
				<td width="100%" colspan="3">

				<table width="100%" class="formbg">
					<tr>
						<td width="24%" colspan="1" valign="top"><label class="set"
							name="apprvRem" id="apprvRem" ondblclick="callShowDiv(this);"><%=label.get("apprvRem")%></label>
						:</td>
						<td width="74%"><s:textarea cols="50" rows="4"
							name="apprvComment"></s:textarea> <img
							src="../pages/images/zoomin.gif" height="12" align="bottom"
							width="12" theme="simple"
							onclick="javascript:callWindow('paraFrm_apprvComment','apprvRem','','200','200');"></td>
					</tr>
				</table>
				</td>
			</tr>
		</s:if>

		<tr>
			<td width="60%" nowrap="nowrap"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /> <s:if
				test="%{offerAppointFlag}">
				<s:submit cssClass="token" action="EmployeeRequi_backToOfferAppoint"
					theme="simple" value='Back To %{createNewFlag}'
					onclick="return callView()" />
			</s:if></td>
			<td width="40%" align="right"><strong>Page 2 of 2</strong></td>
		</tr>
	</table>
</s:form>

<script>
function sendforapprovalFun(){
  var status=document.getElementById("paraFrm_hiddenApproveStatus").value;
  	if(status=="Pending"){
			alert("Application is already pending.So can't be send for approval.");
			return false;
		}else if(status=="Approved"){
			alert("Application is already approved.So can't be send for approval.");
			return false;
		}else if(status=="On Hold")	{
			alert("Application is On Hold.So can't be send for approval.");
			return false;
		}else if(status=="Quick Requisition")	{
			alert("Quick Requisition can't be send for approval.");
			return false;
		} else{	
				//var wind = window.open('','wind','width=1000,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
				//document.getElementById('paraFrm').target = "wind";
				document.getElementById('paraFrm').action = "EmployeeRequi_sendForApproveInSecView.action";
				document.getElementById('paraFrm').submit();
				//document.getElementById('paraFrm').target = "main";
			//document.getElementById('paraFrm').target="_blank";
  		 	 //document.getElementById('paraFrm').action="EmployeeRequi_sendForApprove.action";
	      	 //document.getElementById('paraFrm').submit();
	      	 // document.getElementById('paraFrm').target="main";
	        }	       
  return false;
  }
  
function previousFun(){
   	var newSt = document.getElementById('paraFrm_hiddenApproveStatus').value;
   	if(document.getElementById('paraFrm_reqFlag').value=="true"){
   			document.getElementById('paraFrm').action="EmployeeRequi_firstPageView.action";
  			document.getElementById('paraFrm').submit();
   		} else if (newSt=="New Requisition") {
   			document.getElementById('paraFrm').action="EmployeeRequi_previousPage.action";
  			document.getElementById('paraFrm').submit();
  		} else if (newSt=="Send Back") {
   			document.getElementById('paraFrm').action="EmployeeRequi_previousPage.action";
  		    document.getElementById('paraFrm').submit();
  		} else { 
     	    document.getElementById('paraFrm').action="EmployeeRequi_previousPageView.action";
  		    document.getElementById('paraFrm').submit();
       }
   return false;
   }
   
   function backFun(){
	   	document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value;
	   	document.getElementById('paraFrm').submit();
   }
   
   function deleteFun(){
	var status=document.getElementById("paraFrm_hiddenApproveStatus").value;
		if(status=="Pending"){
			alert("Application is already pending.So can't be deleted.");
			return false;
		}else if(status=="Approved"){
			alert("Application is already approved.So can't be deleted.");
			return false;
		}else if(status=="On Hold")	{
			alert("Application is On Hold.So can't be deleted.");
			return false;
		}else if(status=="Rejected"){
			alert("Application is Rejected.So can't be deleted.");
			return false;
		
		}else if(status=="Quick Requisition")	{
			alert("Quick Requisition can't be deleted.");
			return false;		
		}else{
	
		var conf=confirm("Do you really want to delete the records?");
		if(conf){
			document.getElementById("paraFrm").action="EmployeeRequi_delete.action";
	   		document.getElementById("paraFrm").submit();
				
		}else{
		
		     return false;
		}
	}
	
	}
   
   
    function callForQuali(id)
	   {
	 	
	
	   if(document.getElementById('paraFrm_quaChk'+id).checked == true)
	   {	  
	    document.getElementById('paraFrm_hdeleteQuali'+id).value="Y";
	 
	   }
	   else{
	   document.getElementById('paraFrm_hdeleteQuali'+id).value="";
	   	
   		}
  }
   
   function callForQualiAll()
	   {
	 
			var tbl = document.getElementById('tblQuali');
		var rowLen = tbl.rows.length;
		if(document.getElementById('chkEmpAll').checked){
		 for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_quaChk'+i).checked = true;
			  document.getElementById('paraFrm_hdeleteQuali'+i).value="Y";
		  }
			
	    }else{
	    
	     for(i = 1; i < rowLen; i++){
			document.getElementById('paraFrm_quaChk'+i).checked =false;
			  document.getElementById('paraFrm_hdeleteQuali'+i).value="";
		  }
	   }	
	
  }
   
   function deleteQualification()
	{
	 if(chkQuali()){
	 var con=confirm('Do you want to delete these records?');
	 if(con){
	    document.getElementById('paraFrm').action="EmployeeRequi_deleteQualification.action";
  		document.getElementById('paraFrm').submit();
	    } else{
	     return true;
	    }
	 }else {
	 	alert('Please select atleast one record to delete.');
	 	 return false;
	 }
	 
	}
	
	
	
	function chkQuali(){
	
		
			var tbl = document.getElementById('tblQuali');
			var rowLen = tbl.rows.length;
	
	  for(var a=1;a<rowLen;a++){	
	   if(document.getElementById('paraFrm_quaChk'+a).checked == true)
		   {	
	 	    return true;
	   }	   
	  }
	  return false;
	}
   
   
     function callForSkillAll()
  {
	 	  
		var tbl = document.getElementById('tblSkill');
		var rowLen = tbl.rows.length;
	if (document.getElementById("chkSkillAll").checked == true){
	for (var idx = 1; idx < rowLen; idx++) {
	
				document.getElementById("confChkSkill"+idx).checked = true;
				 document.getElementById('hdeleteSkill'+idx).value="Y";
				
	    }

 }else{
	for (var idx = 1; idx < rowLen; idx++) {
	
	document.getElementById("confChkSkill"+idx).checked = false;
	document.getElementById('hdeleteSkill'+idx).value="";
     }
  }	 
		
	
  }
  
function clickAllDom() {
	var tbl = document.getElementById('tblDom');
	var rowLen = tbl.rows.length;
	if (document.getElementById("confChkDom").checked == true){
		for (var idx = 1; idx < rowLen; idx++) {
			document.getElementById("domChk"+idx).checked = true;
			document.getElementById("hdeleteDom"+idx).value ="Y";
		}
	}else{
		for (var idx = 1; idx < rowLen; idx++) {
			document.getElementById("domChk"+idx).checked = false;
			document.getElementById("hdeleteDom"+idx).value ="";
		}
	}
}
  
function callForSkill(id) {
	   if(document.getElementById('confChkSkill'+id).checked == true) {	  
	    document.getElementById('hdeleteSkill'+id).value="Y";
	   } else {
	   	document.getElementById('hdeleteSkill'+id).value="";
   		}
  }
  
function callForDom(id)	{
   if(document.getElementById('domChk'+id).checked == true) {	  
	    document.getElementById('hdeleteDom'+id).value="Y";
   }else{
   		document.getElementById('hdeleteDom'+id).value="";
   		}
  }

function deleteDomain() {
		 if(chkDomain()){
		 var con=confirm('Do you want to delete these records?');
		 if(con){
		    document.getElementById('paraFrm').action="EmployeeRequi_deleteDomain.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return true;
		    }
		 }else {
		 	alert('Please select one record to delete');
		 	 return false;
		 }
}
  
  
function chkDomain(){
	var tbl = document.getElementById('tblDom');
		var rowLen = tbl.rows.length;
		  for(var a=1;a<rowLen;a++){	
		   if(document.getElementById('domChk'+a).checked == true) {	
		 	    return true;
		   }	   
		  }
		  return false;
}
  
function deleteSkill() {
		 if(chkSkill()){
		 var con=confirm('Do you want to delete these records?');
		 if(con){
		    document.getElementById('paraFrm').action="EmployeeRequi_deleteSkill.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return true;
		    }
		 }else {
		 	alert('Please Select Atleast one Record To Delete');
		 	 return false;
		 }
}
   
function chkSkill(){
	var tbl = document.getElementById('tblSkill');
		var rowLen = tbl.rows.length;
		  for(var a=1;a<rowLen;a++){	
		   if(document.getElementById('confChkSkill'+a).checked == true)
			   {	
		 	    return true;
		        }	   
		  }
		  return false;
	}
	
function callSkill(id) {
	      document.getElementById('paraFrm_rowId').value=id; 
	      callsF9(500,325,'EmployeeRequi_f9Skill.action'); 
   }
   
function callForCert(id) {
	   if(document.getElementById('certChk'+id).checked == true) {	  
	    document.getElementById('hdeleteCert'+id).value="Y";
	   } else{
	   document.getElementById('hdeleteCert'+id).value="";
   		}
 }

function addRowToTableCert() {
  var tbl = document.getElementById('tblCert');
  var lastRow = tbl.rows.length;
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
  //reorderRows(tbl, iteration);
  var cellLeft = row.insertCell(0);
  var textNode = document.createTextNode(iteration);
  cellLeft.appendChild(textNode);
  var cellSel = row.insertCell(1);
 
  var sel = document.createElement('select');
  sel.name = 'certType' ;
  sel.cssClass='border2';
  sel.options[0] = new Option('Select', '');
  sel.options[1] = new Option('Essential', 'E');
  sel.options[2] = new Option('Desirable', 'D');
  cellSel.appendChild(sel);

  var cell = row.insertCell(2);
  var ed = document.createElement('input');
  ed.type = 'text';
  ed.name = 'certName' ;
  ed.id= 'certName' +iteration;
  ed.size =20;
  cell.appendChild(ed);
  

 
	var cell3 = row.insertCell(3);
	var issue=  document.createElement('input');
	issue.size=20;
	issue.type='text';
	issue.name='certIssueBy';
	
	
 	cell3.appendChild(issue);
  

  // right cell
  var cellRight = row.insertCell(4);
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'certValid';
  el.size = 20;
 
  cellRight.appendChild(el);
  
  // select cell
  var cellRightSel = row.insertCell(5);
  var domsel = document.createElement('select');
 
  domsel.name = 'certOption' ;
  domsel.options[0] = new Option('', '');
  domsel.options[1] = new Option('And', 'A');
  domsel.options[2] = new Option('Or', 'O');
  cellRightSel.appendChild(domsel);
  
  
  
   // select cell
  var cellLast = row.insertCell(6);
  var domChk = document.createElement('input');

 
  domChk.type = 'checkbox';
  domChk.name = 'certChk'+iteration;
  domChk.id = 'certChk' + iteration;
  domChk.onclick=function(){
 			  if(document.getElementById('certChk'+iteration).checked == true)
	   {	
	    		document.getElementById('hdeleteCert'+iteration).value="Y";
	    		
	
	   }  else{
	  			 document.getElementById('hdeleteCert'+iteration).value="";
	   	
   		}
 		

	     	
	     
	     	
};
  cellLast.appendChild(domChk);
  
  
  var hiddenDel=row.insertCell(7);
  var hid = document.createElement('input');
  hid.type = 'hidden';
  hid.name = 'hdeleteCert'+iteration;
  hid.id = 'hdeleteCert' + iteration;
  hiddenDel.appendChild(hid);
  
   var hiddenDet=row.insertCell(8);
  var hidCode = document.createElement('input');
  hidCode.type = 'hidden';
  hidCode.name = 'certDetCode';
  
  hiddenDet.appendChild(hidCode);
  
	
  
   }
   
   
   
   
   function clickAll() {




var tbl = document.getElementById('tblCert');
var rowLen = tbl.rows.length;
if (document.getElementById("confChkCert").checked == true){
for (var idx = 1; idx < rowLen; idx++) {

document.getElementById("certChk"+idx).checked = true;
document.getElementById("hdeleteCert"+idx).checked ="Y";

}

}else{
for (var idx = 1; idx < rowLen; idx++) {

document.getElementById("certChk"+idx).checked = false;
document.getElementById("hdeleteCert"+idx).checked ="";
}

}
}
   
   
   
   
   function deleteRow() {
   
   
var checkedObjArray = new Array();
var cCount = 0;
   
var tbl = document.getElementById("tblCert");
var error = false;
if (document.getElementById("confChkCert").checked == false)
error = true;


var rowLen = tbl.rows.length-1;

for (var idx = 1; idx <=rowLen; idx++) {

if(document.getElementById("certChk"+idx).checked==true){

error=false;
//checkedObjArray[cCount] = tbl.tBodies[0].rows[idx];
//				cCount++;

	   }

	}

if (error == true) {
alert ("Please select atleast one checkbox.");

return false;
}



   document.getElementById('paraFrm').action="EmployeeRequi_deleteCertification.action";
   document.getElementById('paraFrm').submit();



}




   
   



 
 
function addRowToTableDomain()
{
  var tbl = document.getElementById('tblDom');
  
  var lastRow = tbl.rows.length;
 
  
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
  
  
  // left cell
  var cellLeft = row.insertCell(0);
  var textNode = document.createTextNode(iteration);
  cellLeft.appendChild(textNode);
  
  
  var cellSel = row.insertCell(1);
  var sel = document.createElement('select');
  sel.name = 'domType' ;
 // sel.id = 'paraFrm_domType' + iteration;
  sel.options[0] = new Option('Select', '');
  sel.options[1] = new Option('Essential', 'E');
  sel.options[2] = new Option('Desirable', 'D');
  cellSel.appendChild(sel);
  
 
  
  
  
   var cell = row.insertCell(2);
   
   				
   
  
   									  
 
   
   
  var ed = document.createElement('input');
  ed.type = 'text';
  ed.name = 'domName' + iteration;
  ed.id = 'paraFrm_domName' + iteration;
  ed.size =35;
  ed.readOnly="true";

    cell.appendChild(ed);
  

 
var cell3 = row.insertCell(3);
var img=  document.createElement('img');
img.type='image';
img.src='../pages/images/search2.gif';
 img.height='16';
 img.align='absmiddle';
 img.width='16';
 img.id='img'+ iteration;
 img.theme='simple';
 img.onclick=function(){
 		
 			document.getElementById('paraFrm_rowId').value=iteration;
 			callsF9(500,325,'EmployeeRequi_f9Domain.action');
 		
	//     	callsF9(500,325,'//EmployeeRequi_f9Domain.action?field=domName'+iteration); 
	     	
	     
	     	
};
 cell3.appendChild(img);
  

  // right cell
  var cellRight = row.insertCell(4);
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'domExp';

  el.size =20;
  
  

 // el.onkeypress = keyPressTest;
  cellRight.appendChild(el);
  
  // select cell
  var cellRightSel = row.insertCell(5);
  var domsel = document.createElement('select');
 
  domsel.name = 'domSel' ;
 // domsel.id = 'paraFrm_domSel' + iteration;
  domsel.options[0] = new Option('', '');
  domsel.options[1] = new Option('And', 'A');
  domsel.options[2] = new Option('Or', 'O');
  cellRightSel.appendChild(domsel);
  
  
  
   // select cell
  var cellLast = row.insertCell(6);
  var domChk = document.createElement('input');
 
   domChk.type = 'checkbox';
  domChk.name = 'domChk'+iteration;
  domChk.id = 'domChk'+iteration;
  domChk.onclick=function(){
 			  if(document.getElementById('domChk'+iteration).checked == true)
	   {	
	    		document.getElementById('hdeleteDom'+iteration).value="Y";
	    		
	
	   }  else{
	  			 document.getElementById('hdeleteDom'+iteration).value="";
	   	
   		}
 		

	     	
	     
	     	
};
  
  
  
  
  
 // domChk.id = 'paraFrm_domChk' + iteration;
  cellLast.appendChild(domChk);
  
  
  var hiddenDel=row.insertCell(7);
  var hid = document.createElement('input');
  hid.type = 'hidden';
  hid.name = 'hdeleteDom'+iteration;
  hid.id = 'hdeleteDom' + iteration;
  hiddenDel.appendChild(hid);
  
  
  
  var hiddenDomId=row.insertCell(8);
  var hidDomId = document.createElement('input');
  hidDomId.type = 'hidden';
  hidDomId.name = 'domId'+iteration;
  hidDomId.id = 'paraFrm_domId' + iteration;
  hiddenDomId.appendChild(hidDomId);
  
  var hiddenDomCode=row.insertCell(9);
  var hiddenDom = document.createElement('input');
  hiddenDom.type = 'hidden';
  hiddenDom.name = 'domDetCode';
 
  hiddenDomCode.appendChild(hiddenDom);
 
	
  
   }  


  function callDom(id)
    {

		
	    document.getElementById('paraFrm_rowId').value=id; 
	    callsF9(500,325,'EmployeeRequi_f9Dom.action'); 
     	// document.getElementById('paraFrm').target="_blank";
      
   }
   
 

function addRowToTableSkill()
{
  var tbl = document.getElementById('tblSkill');
  
  var lastRow = tbl.rows.length;
 
  
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  var row = tbl.insertRow(lastRow);
  
 
  // left cell
  var cellLeft = row.insertCell(0);
  var textNode = document.createTextNode(iteration);
  cellLeft.appendChild(textNode);
  
  
  var cellSel = row.insertCell(1);
  var sel = document.createElement('select');
  sel.name = 'skillType' ;
 // sel.id = 'paraFrm_skillType' + iteration;
 sel.options[0]=new Option('Select', '');
  sel.options[1] = new Option('Essential', 'E');
  sel.options[2] = new Option('Desirable', 'D');
  cellSel.appendChild(sel);
  
 
  
  
  
   var cell = row.insertCell(2);
      
  var ed = document.createElement('input');
  ed.type = 'text';
  ed.name = 'skillName' + iteration;
  ed.id = 'paraFrm_skillName' + iteration;
  ed.size =35;
  ed.readOnly="true";

    cell.appendChild(ed);
  

 
var cell3 = row.insertCell(3);
var img=  document.createElement('img');
img.type='image';
img.src='../pages/images/search2.gif';
 img.height='16';
 img.align='absmiddle';
 img.width='16';
 img.id='img'+ iteration;
 img.theme='simple';
 img.onclick=function(){
 			
 			document.getElementById('paraFrm_rowId').value=iteration;
 			callsF9(500,325,'EmployeeRequi_f9SkillAction.action');
 		
	
	     	
	     
	     	
};
 cell3.appendChild(img);
  

  // right cell
  var cellRight = row.insertCell(4);
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'skillExp';
 el.id = 'paraFrm_skillExp';
  el.size = 20;
  
  cellRight.appendChild(el);
  
  // select cell
  var cellRightSel = row.insertCell(5);
  var skillSelect = document.createElement('select');
 
  skillSelect.name = 'skillSel' ;
 // domsel.id = 'paraFrm_skillSel' + iteration;
  skillSelect.options[0] = new Option('And', 'A');
  skillSelect.options[1] = new Option('Or', 'O');
  cellRightSel.appendChild(skillSelect);
  
  
  
   // select cell
  var cellLast = row.insertCell(6);
  var skillChk = document.createElement('input');
 
   skillChk.type = 'checkbox';
  skillChk.name = 'confChkSkill'+iteration;
  skillChk.id = 'confChkSkill'+iteration;
  skillChk.onclick=function(){
 			  if(document.getElementById('confChkSkill'+iteration).checked == true)
	   {	
	    		document.getElementById('hdeleteSkill'+iteration).value="Y";
	    		
	
	   }  else{
	  			 document.getElementById('hdeleteSkill'+iteration).value="";
	   	
   		}
 		

	     	
	     
	     	
};
 

  cellLast.appendChild(skillChk);
  
  
  var hiddenDel=row.insertCell(7);
  var hid = document.createElement('input');
  hid.type = 'hidden';
  hid.name = 'hdeleteSkill'+iteration;
  hid.id = 'hdeleteSkill' + iteration;
  hiddenDel.appendChild(hid);
  
  
  
  var hiddenSkill=row.insertCell(8);
  var hidSkillId = document.createElement('input');
  hidSkillId.type = 'hidden';
  hidSkillId.name = 'skillId'+iteration;
  hidSkillId.id = 'paraFrm_skillId' + iteration;
  hiddenSkill.appendChild(hidSkillId);
  
  
    var hiddenDetCode=row.insertCell(9);
  var hiddenCode = document.createElement('input');
  hiddenCode.type = 'hidden';
  hiddenCode.name = 'skillDetCode';
  hiddenDetCode.appendChild(hiddenCode);
 
	
  
   }  
   
   
   function callQualification(id)
    {
    
      document.getElementById('paraFrm_rowId').value=id; 
     
      callsF9(500,325,'EmployeeRequi_f9Quali.action'); 
     
     
    }
    
    
     function callSpecialization(id)
    {
   
      document.getElementById('paraFrm_rowId').value=id; 
     
      callsF9(500,325,'EmployeeRequi_f9Speci.action'); 
     
     
    }
   
   
   
   
   function addRowToTableQualification()
{
  var tbl = document.getElementById('tblQuali');
  
  var lastRow = tbl.rows.length;
 
  
  // if there's no header row in the table, then iteration = lastRow + 1
  var iteration = lastRow;
  
//  if(chkDuplicate(iteration)){
 // alert("Duplicate");
 // return false;
//  }else{
  var row = tbl.insertRow(lastRow);
  

  // left cell
  var cellLeft = row.insertCell(0);
  var textNode = document.createTextNode(iteration);
  cellLeft.appendChild(textNode);
  
  
  var cellQualType = row.insertCell(1);
  var quaTyp = document.createElement('select');
  quaTyp.name = 'hqualType' ;
  quaTyp.options[0] = new Option('Select', '');
  quaTyp.options[1] = new Option('Essential', 'E');
  quaTyp.options[2] = new Option('Desirable', 'D');
  cellQualType.appendChild(quaTyp);
  
 
  
  
  
   var cellQualName = row.insertCell(2);
      
  var quaName = document.createElement('input');
  quaName.type = 'text';
  quaName.name = 'qualificationName' + iteration;
  quaName.id = 'paraFrm_qualificationName' + iteration;
  quaName.size =20;
  quaName.readOnly="true";
  cellQualName.appendChild(quaName);
  

 
var cellQuaNameImg = row.insertCell(3);
var img=  document.createElement('img');
img.type='image';
img.src='../pages/images/search2.gif';
 img.height='16';
 img.align='absmiddle';
 img.width='16';
 img.id='img'+ iteration;
 img.theme='simple';
 img.onclick=function(){
 			
 			 document.getElementById('paraFrm_rowId').value=iteration; 
        	callsF9(500,325,'EmployeeRequi_f9Qualification.action'); 
     
 		
	
	     	
	     
	     	
};
 cellQuaNameImg.appendChild(img);
  

  // right cell
  var cellQualevel = row.insertCell(4);
  var quaLvl = document.createElement('input');
  
  quaLvl.type = 'text';
  quaLvl.name = 'hqualiLevelName'+iteration;
  quaLvl.id =  'paraFrm_hqualiLevelName'+iteration;
  quaLvl.size = 12;
  quaLvl.readOnly="true";
  cellQualevel.appendChild(quaLvl);
  
  
   var cellSpecializ = row.insertCell(5);
  var spl = document.createElement('input');
  
  spl.type = 'text';
  spl.name = 'hsplName'+iteration;
  spl.id =   'paraFrm_hsplName'+iteration;
  spl.size = 20;
  spl.readOnly="true";
  
  cellSpecializ.appendChild(spl);
  
  var cellQuaSplImg = row.insertCell(6);
var splImg=  document.createElement('img');
splImg.type='image';
splImg.src='../pages/images/search2.gif';
 splImg.height='16';
 splImg.align='right';
 splImg.width='16';
 splImg.id='img'+ iteration;
 splImg.theme='simple';
 splImg.onclick=function(){
 			
 			 document.getElementById('paraFrm_rowId').value=iteration; 
        	callsF9(500,325,'EmployeeRequi_f9Specialization.action'); 
     
 		
	
	     	
	     
	     	
};
 cellQuaSplImg.appendChild(splImg);
  
  
  var cellQuaCut = row.insertCell(7);
  var cut = document.createElement('input');
  
  cut.type = 'text';
  cut.name = 'hcutOff';
  
  cut.size = 12;
  
  
  cellQuaCut.appendChild(cut);
  
  
  // select cell
  var cellQuaOpt = row.insertCell(8);
  var quaOpt = document.createElement('select');
  quaOpt.name = 'sel' ;
   quaOpt.options[0] = new Option('  ', '');
  quaOpt.options[1] = new Option('And', 'A');
  quaOpt.options[2] = new Option('Or', 'O');
  quaOpt.align="left";
  cellQuaOpt.appendChild(quaOpt);
  
  
  var cellQuaChk = row.insertCell(9);
  var qualiChk = document.createElement('input');
 
   qualiChk.type = 'checkbox';
  qualiChk.name = 'paraFrm_quaChk'+iteration;
  qualiChk.id = 'paraFrm_quaChk'+iteration;
  qualiChk.onclick=function(){
 			  if(document.getElementById('paraFrm_quaChk'+iteration).checked == true)
	   {	
	    		document.getElementById('paraFrm_hdeleteQuali'+iteration).value="Y";
	    		
	
	   }  else{
	  			 document.getElementById('paraFrm_hdeleteQuali'+iteration).value="";
	   	
   		}
 		

	     	
	     
	     	
};
 

  cellQuaChk.appendChild(qualiChk);
  
   
  var hiddenDel=row.insertCell(10);
  var hid = document.createElement('input');
  hid.type = 'hidden';
  hid.name = 'paraFrm_hdeleteQuali'+iteration;
  hid.id = 'paraFrm_hdeleteQuali' + iteration;
  hiddenDel.appendChild(hid);
  
  
  
  var hiddenQuali=row.insertCell(11);
  var hidQualiId = document.createElement('input');
  hidQualiId.type = 'hidden';
  hidQualiId.name = 'qualificationId'+iteration;
  hidQualiId.id = 'paraFrm_qualificationId'+iteration;
  hiddenQuali.appendChild(hidQualiId);
  
  var hiddenSplCell=row.insertCell(12);
  var hiddenSpl= document.createElement('input');
  hiddenSpl.type = 'hidden';
  hiddenSpl.name = 'hsplId'+iteration;
  hiddenSpl.id = 'paraFrm_hsplId'+iteration;
  hiddenSplCell.appendChild(hiddenSpl);
  //}
  
  
   var hiddenDetCode=row.insertCell(13);
  var hiddenCode= document.createElement('input');
  hiddenCode.type = 'hidden';
  hiddenCode.name = 'quaDetCode';
  hiddenDetCode.appendChild(hiddenCode);
 
  
   }  
   


function chkDuplicate(idx){
	if(idx>2){
		var previous=idx-1;
		for (count = 1; count < idx-1; count++) {
		var currQuali=document.getElementById('paraFrm_qualificationName'+previous).value;
		var previousQuali=document.getElementById('paraFrm_qualificationName'+count).value;
			if(previousQuali==currQuali){
				return true;
			}
		}
	}
	return false;
}

function cancelFun(){
	if(document.getElementById('source').value=='mymessages') {
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_input.action';
	} else if(document.getElementById('source').value=='myservices') {
		document.getElementById('paraFrm').action = 'MypageProcessManagerAlerts_serviceData.action';
	} else {
		document.getElementById("paraFrm").action="EmployeeRequi_cancelSecond.action";
	}	
	document.getElementById("paraFrm").submit();
}
	

function saveFun(){
	if(document.getElementById("paraFrm_updateSecondFlag").value=="true"){
			if(!chkDup()){
				return false;;
			}else if(!chkDupSkill()){
				return false;
			}else if(!chkDupDom()){
				return false;
			}else if(!chkDupCert()){
				return false;
			}else{
					document.getElementById("paraFrm").action="EmployeeRequi_updateSecond.action";
				    document.getElementById("paraFrm").submit();
			}	    
		}else{
		document.getElementById("paraFrm").action="EmployeeRequi_saveSecond.action";
	    document.getElementById("paraFrm").submit();
	    }
	}
	
function editFun(){
		var status=document.getElementById('paraFrm_hiddenApproveStatus').value;
		if(status=="Pending"){
			alert("Application is already pending.So can't be edited.");
			return false;
		}else if(status=="Approved"){
			alert("Application is already approved.So can't be edited.");
			return false;
		}else if(status=="On Hold")	{
			alert("Application is On Hold.So can't be edited.");
			return false;
		}else if(status=="Quick Requisition")	{
			alert("Quick Requisition can't be edited.");
			return false;
		}
		document.getElementById("paraFrm").action="EmployeeRequi_editSec.action";
	    document.getElementById("paraFrm").submit();
	  return false;  
}

function chkDup(){
var tbl = document.getElementById('tblQuali');
var lastRow = tbl.rows.length;
for(var i=1;i<lastRow;i++){
 		 		var quaName= document.getElementById('paraFrm_qualificationName'+i).value;
 		 		var spName = document.getElementById('paraFrm_hsplName'+i).value;
 		 	
 		 	var cnt=0;
 		 		for(var d=1;d<lastRow;d++){
	 		 			
	 		 			var dupQua= document.getElementById('paraFrm_qualificationName'+d).value;
 		 				var dupSpl = document.getElementById('paraFrm_hsplName'+d).value;
	 		 		
	 		 		if(dupQua == quaName && dupSpl==spName){
	 		 			cnt++;
	 		 		}
	 		 		if(cnt > 1){
	 		 			alert('Duplicate Records Found in Qualification Details.');
	 		 			return false;
	 		 		}
 		 		}
 		 	if(quaName==""){
 		 	    alert("Please enter the row value Qualification Name/Specialization Name in Qualification details.");
 		 	   return false;
 		 	}	
 		}
 	return true;
 }		
 
 
 function chkDupSkill(){

var tbl = document.getElementById('tblSkill');
  
var lastRow = tbl.rows.length;

for(var i=1;i<lastRow;i++){
 		 		var skill= document.getElementById('paraFrm_skillName'+i).value;
 		 		
 		 		
 		 	var cnt=0;
 		 		for(var d=1;d<lastRow;d++){
	 		 			
	 		 			var dupSkill= document.getElementById('paraFrm_skillName'+d).value;
 		 			
	 		 		
	 		 		if(dupSkill == skill){
	 		 			cnt++;
	 		 		}
	 		 		if(cnt > 1){
	 		 			alert('Duplicate Records Found in Skill Deatils');
	 		 			return false;
	 		 		}
 		 		}
 		 	if(skill==""){
 		 	alert("Please enter the row value in Skill Details.");
 		 	 return false;
 		 	}	
 		}
 	
 		
 	return true;	 
 		
 }			
 
 
 
 function chkDupDom(){

var tbl = document.getElementById('tblDom');
  
var lastRow = tbl.rows.length;

for(var i=1;i<lastRow;i++){
 		 		var dom= document.getElementById('paraFrm_domName'+i).value;
 		 		
 		 		
 		 	var cnt=0;
 		 		for(var d=1;d<lastRow;d++){
	 		 			
	 		 			var dupDom= document.getElementById('paraFrm_domName'+d).value;
 		 			
	 		 		
	 		 		if(dupDom == dom){
	 		 			cnt++;
	 		 		}
	 		 		if(cnt > 1){
	 		 			alert('Duplicate Records Found in Domain Details.');
	 		 			return false;
	 		 		}
 		 		}
 		 	if(dom==""){
 		 	 alert("Please enter the row value in Domain Details");
 		 	 return false;
 		     }	
 		}
 	
 		
 		 
 return true;		
 }		
 
 
 function chkDupCert(){

var tbl = document.getElementById('tblCert');
  
var lastRow = tbl.rows.length;

for(var i=1;i<lastRow;i++){
 		 		var cert= document.getElementById('certName'+i).value;
 		 		
 		 		
 		 	var cnt=0;
 		 		for(var d=1;d<lastRow;d++){
	 		 			
	 		 			var dupCert= document.getElementById('certName'+d).value;
 		 			
	 		 		
	 		 		if(dupCert == cert){
	 		 			cnt++;
	 		 		}
	 		 		if(cnt > 1){
	 		 			alert('Duplicate Records Found in Certification Details.');
	 		 			return false;
	 		 		}
 		 		}
 		 	if(cert==""){
 		 	alert("Please enter the row value in Certification Details.");
 		 	return false;
 		 	
 			}	
 		}
 	
 		
 return true;		 
 		
 }	
 
   function exportpdfreportFun(){
   		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action="EmployeeRequi_reportFun.action";	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";
   
   }			
function exporttextreportFun(){
   		document.getElementById('paraFrm').target="_blank";
		document.getElementById('paraFrm').action="EmployeeRequi_reportFunText.action";	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";
   
   }
   
   
   function approveFun(){
   		
   		var comment=document.getElementById('paraFrm_apprvComment').value;
   		if(comment.length >200){
   		  alert("Maximum length of "+document.getElementById('apprvRem').innerHTML.toLowerCase()+" is 200 characters.");
		  return false;
   		 }
   		 var con=confirm("Do you really want to Approve the application?");
   		if(con){
	   		document.getElementById('formAction').value="RequisitionApproval_approve.action";		
	   		document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value+'&comm='+comment;
		    document.getElementById('paraFrm').submit();
   		}
   
    return false;
   }
   
   
   function onholdFun(){
   			
   		var comment=document.getElementById('paraFrm_apprvComment').value;
   		if(comment.length >200){
   		  alert("Maximum length of "+document.getElementById('apprvRem').innerHTML.toLowerCase()+" is 200 characters.");
		  return false;
   		 }
   		 var con=confirm("Do you really want to On Hold the application?");
   		if(con){
	   		document.getElementById('formAction').value="RequisitionApproval_onHold.action";		
	   		document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value+'&comm='+comment;
		    document.getElementById('paraFrm').submit();
   		}
   		 return false;
   
   }
   
   function rejectFun(){
   			
   		    var comment=document.getElementById('paraFrm_apprvComment').value;
   		if(comment.length >200){
   		  alert("Maximum length of "+document.getElementById('apprvRem').innerHTML.toLowerCase()+" is 200 characters.");
		  return false;
   		 }
   		 var con=confirm("Do you really want to Reject the application?");
   		if(con){
	   		document.getElementById('formAction').value="RequisitionApproval_reject.action";		
	   		document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value+'&comm='+comment;
		    document.getElementById('paraFrm').submit();
   		}
    return false;
   }
   
   function backFun(){
	 
	
	 if(document.getElementById('paraFrm_dashletFlag').value=="true")
	 {
	    document.getElementById('paraFrm').action = "<%=request.getContextPath()%>/common/RecruitmentHome_input.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target ="main";
	
	 }else{
	 
	  	document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value;
	   	document.getElementById('paraFrm').submit();
	 }
	   
   }
   
   </script>