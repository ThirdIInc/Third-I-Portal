<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="TaxComputationMisReport" validate="true" id="paraFrm" validate="true" theme="simple">
<table class="formbg" width="100%">
<s:hidden name="recordsPerPage" value="50"/>
<s:hidden name="recordFlag"  />
<s:hidden name="signAuthtoken"  />
<s:hidden name="signAuthName"  />
<s:hidden name="signAuthEmpId"  />
<s:hidden name="signAuthEmpDesg"  />
<s:hidden name="signAuthEmpFather"  />
<tr>
	<td colspan="3" width="100%">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="formbg">
			<tr>
				<td width="4%" valign="bottom" class="txt"><strong
					class="formhead"> <img
					src="../pages/images/recruitment/review_shared.gif" width="25"
					height="25" /></strong></td>
				<td width="93%" class="txt"><strong class="text_head">Tax Computation Report </strong></td>
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
		<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0"><!-- table 1 -->
			<tr>
				<td colspan="2">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"><!-- table 2 -->
					<tr>
						<td>
							<s:if test="%{taxComputation.generalFlag}">
				  				<input type="button" class="token" value=" Submit"
								onclick="return callGeneralReport('StmtCalculation_generalReport.action');" />
				 			</s:if><s:else>
							<input type="button" class="token" value=" Submit"
								onclick="return callReport('StmtCalculation_report.action');" />
							</s:else>	
							<input type="button" class="token"  onclick="resetScreen();" value="Reset"/>		
						</td>
						<td>
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
						</td>
					</tr>	
					</table><!--end of table 2  -->
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!--table 3  -->
						<tr>
							<td>
								<table width="98%" border="0" align="center" cellpadding="0" cellspacing="2"><!--table 4  -->
								<tr>
									<td colspan="5" class="formhead"><strong
										class="forminnerhead"></strong></td>
								</tr>
								<s:hidden name="divisionId" /> 
								
								<s:if test="%{taxComputation.generalFlag}">
									<tr>
										<td colspan="1" width="20%"><label class="set"
										id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
										color="red">*</font>:</td>
									<td colspan="1" width="30%">
									
									<s:textfield name="divisionName" theme="simple" readonly="true"	maxlength="50" size="25" /> 
									</td>
									</tr>
								</s:if>
								<s:else>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
										color="red">*</font>:</td>
									<td colspan="1" width="30%">
									
									<s:textfield name="divisionName" theme="simple" readonly="true"
										maxlength="50" size="25" /> 
										<img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'StmtCalculation_f9Division.action');">

									</td>


									<td colspan="1" width="20%"><label class="set" id="branch"
										name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td colspan="1" width="30%"><s:hidden name="branchId" />
									<s:textfield name="branchName" theme="simple" readonly="true"
										maxlength="50" size="25" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'StmtCalculation_f9Branch.action');">

									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="department" name="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:</td>
									<td><s:hidden name="deptId" /> <s:textfield
										name="deptName" theme="simple" readonly="true"
										maxlength="50" size="25" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'StmtCalculation_f9Dept.action');">
									</td>


									<td colspan="1" width="20%"><label class="set"
										id="employee.type" name="employee.type"
										ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
									:</td>
									<td colspan="1" width="30%"><s:hidden name="empTypeId" />
									<s:textfield name="empTypeName" theme="simple"
										readonly="true" maxlength="50" size="25" /> <img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'StmtCalculation_f9Emptype.action');">

									</td>
								</tr>
								<tr>
									<td width="20%"><label class="set" id="paybill1"
										name="paybill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:</td>
										<td width="30%"><s:hidden name="paybillId"/>
										<s:textfield theme="simple" name="paybillName" size="25" readonly="true" />
										<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
											onclick="javascript:callsF9(800,525,'StmtCalculation_f9paybill.action');">
									</td>
									<td>
										<label class="set"
										id="reporting.to" name="reporting.to"
										ondblclick="callShowDiv(this);"><%=label.get("reporting.to")%>
									</td>
									<td><s:hidden name="reportingToId" />
										<s:hidden name="reportingToToken" />
										<s:textfield name="reportingToName" theme="simple"
										readonly="true" maxlength="50" size="25" /> 
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'StmtCalculation_f9reportingTo.action');">	
									</td>
								</tr>
								</s:else>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="frmYear" name="frmYear"
										ondblclick="callShowDiv(this);"><%=label.get("frmYear")%></label></label><font
										color="red">*</font>:</td>
									<td><s:textfield name="frmYear" theme="simple"  maxlength="4" size="25"
										onkeypress="return numbersonly(this);"
											onblur="add()" />
									</td>
									<td colspan="1" width="20%"><label class="set"
										id="toYear" name="toYear"
										ondblclick="callShowDiv(this);"><%=label.get("toYear")%></label>
									:</td>
									<td colspan="1" width="30%">
									<s:textfield name="toYear" theme="simple"
										readonly="true" maxlength="50" size="25" /> 
									</td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									:</td>
									<td colspan="3" width="80%" nowrap="nowrap"><s:hidden name="empId" /> 
									<s:textfield theme="simple" readonly="true" name="empToken" size="25"/> 
									<s:textfield theme="simple" readonly="true" size="71" name="empName"/> 
									<s:if test="taxComputation.generalFlag">
									</s:if>
									<s:else> 
									<img
										src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'StmtCalculation_f9employee.action');">
									</s:else> 
									</td>
								</tr>
								</table><!--end of table 4  -->
							</td>
						</tr>	
					</table><!--end of table 3  -->
				</td>
			</tr>			
		</table><!--end of table 1  -->
	</td>
</tr>
<tr>
	<td colspan="3">
		<table width="100%" class="formbg"><!--table 5 -->
		<s:if test="taxComputation.generalFlag"></s:if>
		<s:else>
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
									System.out.println("------------->"+totalRecords);
								if (totalRecords != 0 ) {
									
									if (total > pagecount) {
								loopcount = total / pagecount;
								extrecords = total % pagecount;
									}
									if (loopcount > 0) {
								for ( i = 0; i < loopcount; i++) {
									System.out.println("--------loopcount----->"+loopcount);
									// set of urls
									%>
								<tr><td nowrap="nowrap">	
								<a href="#" onclick="callFun('<%=i%>');">Click here to Download Tax Computation report  for <%=(i*pagecount)+1%> to <%=(i*pagecount)+pagecount%> </a> 
									</td></tr>
									<%
								}
								if (extrecords > 0) {
									// add final url
									System.out.println("--------extrecords----->"+extrecords);
									%>
								<tr><td nowrap="nowrap">	<a href="#" onclick="callFun('<%=i%>');">Click here to Download Tax Computation report  for <%=(i*pagecount)+1%> to <%=total%> </a> 
								</td></tr>
								<% 
								}
									} else {
										System.out.println("--------total----->"+total);
										//single url
										%>
								<tr><td nowrap="nowrap">
								<a href="#" onclick="callFun('<%=i%>');">Click here to Download Tax Computation report  for <%=(i*pagecount)+1%> to <%=total%> </a> 
							</td></tr>
								<% 
									}
								}

							} catch (Exception e) {
								e.printStackTrace();
								
							}
						%>
			</s:if><!--end of recordFlag if-->
		</s:else><!--end of generalFlag else-->
		</table><!--end of table 5-->
	</td>
</tr>				
</table><!-- End of main table -->
</s:form>
<script>
   function add()
   {
    var from = document.getElementById('paraFrm_frmYear').value;
    
    if(from=="")
    {
    	 document.getElementById('paraFrm_toYear').value="";
    }
    else
    {
   	 var x=eval(from) +1;
	  document.getElementById('paraFrm_toYear').value=x;
	  }
   }
   
   function callReport(myaction){
		var frmYear = document.getElementById('paraFrm_frmYear').value;
		var division = document.getElementById('paraFrm_divisionId').value;
		var empName = document.getElementById('paraFrm_empId').value;
		if(division=="")
		{
		alert("Please Select "+document.getElementById('division').innerHTML.toLowerCase());
		return false;
		}
		if(frmYear == ''){
			alert("Please Enter "+document.getElementById('frmYear').innerHTML.toLowerCase());
			return false;
		}
		
		if(empName=="")
		{
			document.getElementById('paraFrm').target="main";
		}
		else{
			document.getElementById('paraFrm').target="_blank";
		}
		
		document.getElementById('paraFrm').action=myaction;	
		document.getElementById('paraFrm').submit();   		
   } //end of callReport function
   
    function callFun(rowstart)
 {
 var frmYear = document.getElementById('paraFrm_frmYear').value;
 	if(frmYear.length < 4) {
   			alert(document.getElementById('frmYear').innerHTML + " should have atleast 4 digits");
   			document.getElementById('paraFrm_frmYear').focus();
   			return false;
   	}
 	  document.getElementById("paraFrm_recordFlag").value=true;
   	  document.getElementById("paraFrm").action='StmtCalculation_reportforLink.action?rangeValue='+eval(rowstart);
      document.getElementById('paraFrm').target="main";
	  document.getElementById("paraFrm").submit();	
	 
 }
 
 function callGeneralReport(myaction){
 		var frmYear = document.getElementById('paraFrm_frmYear').value;
	 	if(frmYear.length < 4) {
   			alert(document.getElementById('frmYear').innerHTML + " should have atleast 4 digits");
   			document.getElementById('paraFrm_frmYear').focus();
   			return false;
	   	}
 		document.getElementById('paraFrm').target="_blank";	
		document.getElementById('paraFrm').action=myaction;	
		document.getElementById('paraFrm').submit();	
		document.getElementById('paraFrm').target="main";
 }
 
  function resetScreen(){
  		document.getElementById('paraFrm').target="_self";
		document.getElementById('paraFrm').action="StmtCalculation_reset.action";	
		document.getElementById('paraFrm').submit();	
	}
   
</script>