   <%@ taglib prefix="s" uri="/struts-tags"%> 
   <%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/include/javascript/superTables.js"></script>
<script type="text/javascript" src="../pages/common/include/javascript/syncscroll.js"></script> 

 <%
  String dataField =""+request.getAttribute("data");
 String chkBox =""+request.getAttribute("chkBox"); 
 /////REQUEST PARAMETERS FOR ENABLING AND DISABLING LOCK/UNLOCK BUTTONS  
 String para ="";
 String chk ="";
 String payMod ="input";  
 if(dataField.equals("data"))
 { 
	 para ="input";  
 }else
 {
	 para ="none"; 
 }
 System.out.print("value of para"+para) ;
 if(chkBox.equals("chkBox"))
 {    chk ="input";
	 payMod="none";
 }else
 {
	 chk ="none";
	 payMod="input";
 } 
  %>
<s:form  action="PAProcess" validate="true" id="paraFrm" theme="simple" name ="paraFrm">
<s:hidden name="paProcessId" />	
<s:hidden name="paPro.empId" />
<s:hidden name="branchId" />
<s:hidden name="divisionId" />
<s:hidden name="deptId" />
<s:hidden name="desgnId" />
<s:hidden name="EmpTypeId" />
<s:hidden name="payBillId" />
<s:hidden name="componentId" />
<s:hidden name="empToken" /> 
<s:hidden name="crId" />
<s:hidden name="compAbbr" /> 
<s:hidden name="compAbbrPer" /> 
<s:hidden name="compAbbrAmtRs" /> 
<s:hidden name="frmMonYear" />
<s:hidden name="toMonYear" />
<s:hidden name="finaliseFlag" />
<s:hidden name="processStatus" />
<s:hidden name="flagButton" />
<s:hidden name="apprFlag"/>

	<table><tr><td colspan="2"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td></tr></table>
	<table width="100%" class="formbg">
		 
		<tr>
			<td valign="bottom" class="txt"><strong class="formhead"><img
				src="../pages/images/recruitment/review_shared.gif" width="25"
				height="25" /></strong></td>
			<td width="93%" class="txt"><strong class="formhead">Periodic Allowance Process  </strong></td>
			<td width="3%" valign="top" class="txt"> <div align="right"><img	src="../pages/images/recruitment/help.gif" width="16" height="16" /></div> 	</td>
		</tr>
	</table>
	<table width="100%">
		 
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr >
						<td  width="60%">   
						<s:if test="%{viewFlag}">
					   <input type="button" class="search" value="Search " onclick="javascript:callsF9(500,325,'PAProcess_f9Search.action');" />
					   </s:if>
					<s:if test="lockFlag">
					  <s:submit cssClass="reset" action="PAProcess_process"  theme="simple" value="Reprocess" onclick="return callProcess();" />
					</s:if>
					<s:else>
					  <s:submit cssClass="reset" action="PAProcess_process"  theme="simple" value="Process" onclick="return callProcess();" />
					</s:else>
					  <s:if test="insertFlag">  
					   <s:submit cssClass="reset" action="PAProcess_save"  theme="simple" value="Save"  onclick="return callSave(); " />
					 </s:if>
					 
					 <s:if test="deleteFlag">
					    <s:submit cssClass="delete" action="PAProcess_delete" theme="simple" value="Delete" onclick="return callDel();"/>
					 </s:if>
					<s:submit cssClass="reset" action="PAProcess_reset"  theme="simple" value="Reset" />
					<s:if test="lockFlag">
					<input type="button"  Class="token"  id ="LockUnlock"   value='<s:property value="flagButton"/>' onclick="return callLockConfirm();" />				
				    </s:if>
				</td>
				<td> </td> 
				<td width="22%"><div align="right"><font color="red">*</font> Indicates
						Required</div>
						</td>
					</tr>
				</table>
			<label></label></td>
		</tr>
	 
 <tr>
	<td colspan="3">
	   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
			<tr>
				<td>
				    <table width="100%" border="0" align="center" cellpadding="0" 	cellspacing="2" >						
					  <tr>
				        <td height="22"  width="17%"  class="formtext"><label  class = "set"  id="compName" name="compName" ondblclick="callShowDiv(this);"><%=label.get("compName")%></label><font color="red">*</font>:</td>
				        <td>
					             <s:textfield name="componentName" size="30" theme="simple" maxlength="70" readonly="true" ></s:textfield>             
					             <img src="../pages/images/search2.gif" class="iconImage" height="18" align="absmiddle"
						  		 width="18" onclick="javascript:callsF9(500,325,'PAProcess_f9Component.action');">
						  		&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
						  		 <label  class = "set"  id="period" name="period" ondblclick="callShowDiv(this);"><%=label.get("period")%></label> : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;  
						  		     <s:textfield name ="period" size="15" readonly="true" />
				          </td>   
				         <td height="22"   class="formtext"> </td>
				         <td><s:hidden name = "hidDisbType" /></td>           
					  </tr>      
				      <tr>
				         <td height="22"   class="formtext"><label  class = "set"  id="desbType" name="desbType" ondblclick="callShowDiv(this);"><%=label.get("desbType")%></label><font color="red">*</font>:</td> 
				         <td colspan="3">  
				             <s:if test ="disbursFlag">
					              <s:if test="radioFlag"> 
						              <input type="radio" name ="disbType" value="A"  checked="checked"    onclick ="callDisbur('AR');"/>  <label  class = "set"  id="appRate" name="appRate" ondblclick="callShowDiv(this);"><%=label.get("appRate")%></label>. 
								      <input type="radio" name ="disbType" value="M"    onclick ="callDisbur('MR');"/><label  class = "set"  id="manDisbur" name="manDisbur" ondblclick="callShowDiv(this);"><%=label.get("manDisbur")%></label>. 
					              </s:if>
				                 <s:else>
					                <input type="radio" name ="disbType" value="A"      onclick ="callDisbur('AR');"/>   <label  class = "set"  id="appRate" name="appRate" ondblclick="callShowDiv(this);"><%=label.get("appRate")%></label>. 
							        <input type="radio" name ="disbType" value="M"   checked="checked"  onclick ="callDisbur('MR');"/> <label  class = "set"  id="manDisbur" name="manDisbur" ondblclick="callShowDiv(this);"><%=label.get("manDisbur")%></label>. 
				                 </s:else>
				            </s:if>
				           <s:else>
					              <input type="radio" name ="disbType" value="A"      onclick ="callDisbur('AR');"/>   <label  class = "set"  id="appRate" name="appRate" ondblclick="callShowDiv(this);"><%=label.get("appRate")%></label>. 
							      <input type="radio" name ="disbType" value="M"     onclick ="callDisbur('MR');"/>   <label  class = "set"  id="manDisbur" name="manDisbur" ondblclick="callShowDiv(this);"><%=label.get("manDisbur")%></label>. 
				           </s:else>
				          </td>
				       </tr>     
				   <tr>
				   <td colspan="4">
						<table border="0" width="100%"> 
							<tr>
								<td  width="15%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font color="red">*</font> :</td>
								<td  width="32%"><s:textfield name="divisionName" readonly="true"
									maxlength="50" size="30" />  
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="callsF9(500,325,'PAProcess_f9Division.action');">  </td> 
								 <td width="15%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
								 <td width="28%"><s:textfield name="branchName" readonly="true"
									maxlength="50" size="30" />  
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="callsF9(500,325,'PAProcess_f9Branch.action');">
								     </td>
							 </tr>
							 <tr>
							    <td width="15%"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
								<td width="32%" ><s:textfield name="deptName" readonly="true"
									maxlength="50" size="30" />  
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="callsF9(500,325,'PAProcess_f9Department.action');">
								   </td> 
								<td width="15%"><label  class = "set"  id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label> :</td>
								<td width="28%" ><s:textfield name="payBillName" readonly="true"
									maxlength="50" size="30" />  
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="callsF9(500,325,'PAProcess_f9PayBill.action');">
								 </td>
							</tr> 
							<tr>
								<td width="15%"><label  class = "set"  id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>   :</td>
								<td width="32%"><s:textfield name="desgnName" readonly="true"
									maxlength="50" size="30" />  
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="callsF9(500,325,'PAProcess_f9Designation.action');">
								 </td>
								 <td  width="15%"><label  class = "set"  id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :</td>
								 <td  width="28%"><s:textfield name="EmpTypeName" readonly="true"
									maxlength="50" size="30" />  
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="callsF9(500,325,'PAProcess_f9EmpType.action');">
								 </td> 
							</tr>  
							 <tr>
								<td width="15%"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :</td>
								<td width="32%"><s:textfield name="empName" readonly="true"
									maxlength="50" size="30" />  
									<img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="16" align="absmiddle" width="16"
										onclick="callsF9(500,325,'PAProcess_f9Employee.action');">
								 </td> 
								<td  width="15%"><label  class = "set"  id="procdate" name="procdate" ondblclick="callShowDiv(this);"><%=label.get("procdate")%></label> :</td>
								<td  width="28%"><s:textfield name="processDate" onblur="return validateDate('paraFrm_processDate','To Date');"   onkeypress="return numbersWithHiphen();" maxlength="10"   size="12" />  
								 <s:a href="javascript:NewCal('paraFrm_processDate','DDMMYYYY');">
								     <img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18">
							         </s:a>
									</td>
							 </tr>   
							<tr>
								<td width="15%"><label  class = "set"  id="frmDate" name="frmDate" ondblclick="callShowDiv(this);"><%=label.get("frmDate")%></label> <font color="red">*</font> :</td>
								<td width="32%">    
								<s:textfield size="12"  name="paFromDate" onblur="return validateDate('paraFrm_paFromDate','From Date');" onkeypress="return numbersWithHiphen();" maxlength="10" />
				                      <s:a href="javascript:NewCal('paraFrm_paFromDate','DDMMYYYY');">
								     <img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18">
							         </s:a> 
								</td>
								 <td  width="15%"><label  class = "set"  id="toDate" name="toDate" ondblclick="callShowDiv(this);"><%=label.get("toDate")%></label><font color="red">*</font> :</td>
								<td  width="28%" >  <s:textfield size="12"  name="paToDate" onblur="return validateDate('paraFrm_paToDate','To Date');" onkeypress="return numbersWithHiphen();" maxlength="10" />
				                      <s:a href="javascript:NewCal('paraFrm_paToDate','DDMMYYYY');">
								     <img src="../pages/images/recruitment/Date.gif" class="iconImage" height="18" align="absmiddle" width="18">
							         </s:a>
					             </td> 
							</tr>   
							<tr>
								 <td width="15%"><label  class = "set"  id="incSal" name="incSal" ondblclick="callShowDiv(this);"><%=label.get("incSal")%></label> : </td>
								 <td width="32%">    <s:checkbox id ="salaryFlag" name ="salaryFlag" id ="salaryFlag" onclick="callSalTab();" /> <s:hidden name="hiddenChk" /></td>
								 <td  colspan="2"> <div id="salTab" style="display:<%=chk%>;"> 
						        <label  class = "set"  id="monYear" name="monYear" ondblclick="callShowDiv(this);"><%=label.get("monYear")%></label> <font color="red">*</font>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 						        
						              &nbsp;&nbsp;&nbsp;&nbsp;    <s:select name="salFrmMonth"  headerKey="0" headerValue="--Select--" list="#{'1':'Jan','2':'Feb','3':'Mar','4':'Apr','5':'May','6':'June','7':'July','8':'Aug','9':'Sep' ,'10':'Oct','11':'Nov','12':'Dec'}" />
						           &nbsp; <s:textfield name="salFrmYear" size="4" maxlength="4" onkeypress="return numbersOnly();" />
						       	 	 </div>  	
                                  </td> 
							</tr> 
							<tr>
								 <td colspan="4">  
									  <div id="PayModeTab" style="display:<%=payMod%>" >  
							           <label  class = "set"  id="modPay" name="modPay" ondblclick="callShowDiv(this);"><%=label.get("modPay")%></label>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   <s:select name="payMode"  headerKey="B" headerValue="--Select--" list="#{'C':'Cash','Q':'Cheque','T':'Transfer'}"  onchange="callPayMode();"/> 
							           </div>
								 </td> 
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
	    <td colspan="3" width="100%">
	           <div id="ApprTab"  style="display:<%=para%>" >
		          <table border="0" width="100%">
		            <tr>
		               <td> 
			                  <% int i=1; %>
					       <s:if test="apprFlag"> 
								<table width="99%"  id="tblPAApr" class="formbg">									 			
									 			<tr>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text" size="5" readonly="readonly" class="tokenPay" value=" Sr No."/></td>									
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text" size="28" readonly="readonly" class="tokenPay" value="Employee Name"/></td> 														
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text" size="5" readonly="readonly" class="tokenPay" value="Rating"/></td>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text"  size="5" readonly="readonly" class="tokenPay" value="Score"/></td>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text" size="8" readonly="readonly" class="tokenPay" value="<s:property value ="compAbbr"/>"/></td>
														<td> <input style="text-align: center; border: none; margin: 1px;" type = "text" size="8" readonly="readonly" class="tokenPay" value="Eliglible <s:property value ="compAbbr"/>"/> </td>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text"  size="5" readonly="readonly" class="tokenPay" value="<s:property value ="compAbbrPer"/>"/></td>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text" size="8" readonly="readonly" class="tokenPay" value="<s:property value ="compAbbrAmtRs"/> )"/></td>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text"  size="5" readonly="readonly" class="tokenPay" value="Tax"/></td>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text" size="8" readonly="readonly" class="tokenPay" value="Total Amt."/></td>
														 <td><input style="text-align: center; border: none; margin: 1px;" type = "text" readonly="readonly"  class="tokenPay" value="Remark"/></td>
													  </tr>
									           	<s:iterator value="paList">
														 	
														 	<tr>
															 <td width="52"><s:hidden name ="itEmpId" id ="itEmpId<%=i%>" />
														 	<s:hidden name ="mMonth"/> <s:hidden name ="mMonthDays"/> <s:hidden name ="mYear"/> 
									 			  	     <s:hidden  name ="mAmount"/> 
														 	    <s:hidden name ="itEmpName" />
															 	<input style="text-align: left; background-color: #F2F2F2;" size="5" type="text" value="<%=i%>" readonly="readonly"/>
															 </td>
															 <td width="210" >
															 	<input style="text-align: left; background-color: #F2F2F2;" type="text" size="28" value="<s:property value="itEmpName"/>" readonly="readonly"></td>
															 <td width="55" align="center"> <input type = "text"    name ="itRating"  id ="itRating<%=i%>" value ="<s:property value="itRating"/>" size="5" readonly="readonly" /></td>
															 <td width="55" align="center">  <input type = "text"  style="text-align:right " name ="itRatingScore" id ="itRatingScore<%=i%>" value ="<s:property value="itRatingScore"/>" size="5" readonly="readonly" /></td>
															 <td width="60" align="center"> <input type="text"  style="text-align:right " name="itEntitledAmtActual" id="itEntitledAmtActual<%=i%>" size="8"    value ="<s:property value="itEntitledAmtActual"/>" readonly="readonly" ></td>
															 <td width="60" align="center"> <input type="text"  style="text-align:right " name="itEntitledAmt" id="itEntitledAmt<%=i%>" size="8"    value ="<s:property value="itEntitledAmt"/>" readonly="readonly" ></td>
															 <td width="60" align="center"> <input type="text"  style="text-align:right " name="itEntitledAmtScore" id="itEntitledAmtScore<%=i%>" size="5" maxlength="3" value ="<s:property value="itEntitledAmtScore"/>" onkeyup="callPIpercent('<%=i%>');" onkeypress="return numbersWithColon();"  ></td>
															 <td width="85" align="center"> <input type="text"  style="text-align:right "  name="itPIAmt" id="itPIAmt<%=i%>" size="8" onkeypress="return numbersWithColon();" onkeyup="return calcCompPercent('<%=i%>')"  value ="<s:property value="itPIAmt"/>"  ></td>
															 <td width="85" align="center"> <input type="text"  style="text-align:right "  name="itTaxAmt" id="itTaxAmt<%=i%>" size="8" onkeypress="return numbersWithColon();" onkeyup="return callForTaxCal('<%=i%>')"  value ="<s:property value="itTaxAmt"/>"  ></td>
															 <td width="85" align="center"> <input type="text"  style="text-align:right "  name="itTotalAmt" id="itTotalAmt<%=i%>" size="8" onkeypress="return numbersWithColon();"  readonly="readonly"  value ="<s:property value="itTotalAmt"/>"  ></td>
														 	 <td width="83" align="center">  <s:textarea  name="itRemark" id="itRemark<%=i%>"  rows="1" cols="22" theme="simple"   > </s:textarea>
														 	 </td>
														 	</tr>  <%i++; %>
														</s:iterator>  
									  </table> 
							 </s:if> 
							 <s:else> 	 
								    <table width="98%"  id="tblMan" class="formbg">									 			
									 			<tr>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text" size="5"  readonly="readonly" class="tokenPay" value=" Sr No."/></td>									
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text" size="35" readonly="readonly" class="tokenPay" value="Employee Name"/></td> 														
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text" size="10" readonly="readonly" class="tokenPay" value="<s:property value ="compAbbr"/>"/></td>
														<td> <input style="text-align: center; border: none; margin: 1px;" type = "text" size="8" readonly="readonly" class="tokenPay" value="Eliglible <s:property value ="compAbbr"/>"/> </td>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text"  size="5" readonly="readonly" class="tokenPay" value="<s:property value ="compAbbrPer"/>"/></td>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text" size="10" readonly="readonly" class="tokenPay" value="<s:property value ="compAbbrAmtRs"/>"/></td>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text"  size="5" readonly="readonly" class="tokenPay" value="Tax"/></td>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text" size="8" readonly="readonly" class="tokenPay" value="Total Amt."/></td>
														<td><input style="text-align: center; border: none; margin: 1px;" type = "text"  readonly="readonly" class="tokenPay" value="Remark"/></td>
													  </tr>
									           	    <s:iterator value="paList">
														 	
														 	<tr>
															 <td width="52"><s:hidden name ="itEmpId" id ="itEmpId<%=i%>" />
														 	<s:hidden name ="mMonth"/> <s:hidden name ="mMonthDays"/> <s:hidden name ="mYear"/> 
									 			  	     <s:hidden  name ="mAmount"/> 
														 	    <s:hidden name ="itEmpName" />
															 	<input style="text-align: left; background-color: #F2F2F2;" size="5" type="text" value="<%=i%>" readonly="readonly"/>
															 </td>
															 <td width="210" >
															 	<input style="text-align: left; background-color: #F2F2F2;" type="text" size="35" value="<s:property value="itEmpName"/>" readonly="readonly"></td>
															 <td width="60" align="center"> <input type="text"  style="text-align:right " name="itEntitledAmtActual" id="itEntitledAmtActual<%=i%>" size="10"    value ="<s:property value="itEntitledAmtActual"/>" readonly="readonly" > </td>
															 <td width="60" align="center"> <input type="text"  style="text-align:right " name="itEntitledAmt" id="itEntitledAmt<%=i%>" size="8"    value ="<s:property value="itEntitledAmt"/>" readonly="readonly" ></td>
															 <td width="60" align="center"> <input type="text"  style="text-align:right " name="itEntitledAmtScore" id="itEntitledAmtScore<%=i%>" size="5" value ="<s:property value="itEntitledAmtScore"/>" maxlength="3"  onkeyup="callPIpercent('<%=i%>');"  onkeypress="return numbersWithColon();"  ></td>
															 <td width="85" align="center"> <input type="text"  style="text-align:right "  name="itPIAmt" id="itPIAmt<%=i%>" size="10" onkeypress="return numbersWithColon();"  onkeyup="return calcCompPercent('<%=i%>')"  value ="<s:property value="itPIAmt"/>"  ></td>
															 <td width="85" align="center"> <input type="text"  style="text-align:right "  name="itTaxAmt" id="itTaxAmt<%=i%>" size="8" onkeypress="return numbersWithColon();" onkeyup="return callForTaxCal('<%=i%>')"  value ="<s:property value="itTaxAmt"/>"  ></td>
															 <td width="85" align="center"> <input type="text"  style="text-align:right "  name="itTotalAmt" id="itTotalAmt<%=i%>" size="8" onkeypress="return numbersWithColon();"  readonly="readonly"  value ="<s:property value="itTotalAmt"/>"  ></td>
														 	
														 	 <td width="83" align="center">  <s:textarea  name="itRemark" id="itRemark<%=i%>"  rows="1" cols="22" theme="simple"   > </s:textarea>
														 	 </td>
														 	</tr>  <%i++; %>
													</s:iterator>  
										  </table> 
								 </s:else>	 
					        </td>
				        </tr>
					  </table>
				   </div> 
			    </td>
			  </tr> 
</table>  
</s:form>




<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>

function calcCompPercent(code){
 
		var finalAmount=document.getElementById('itPIAmt'+code).value;//3
		var entitledScore=document.getElementById('itEntitledAmtScore'+code).value;//2
		var entitledAmount=document.getElementById('itEntitledAmt'+code).value;//1
		var finalScrore=0;
		
		if(finalAmount==""){
		 finalAmount=0;
		}
		  
		if( eval(finalAmount)>eval(entitledAmount) ){		 	
		 	alert('Final amount cannot be greater than Eliglible amount');
		 	document.getElementById('itPIAmt'+code).value=0;
		 	document.getElementById('itEntitledAmtScore'+code).value=0;		 	
		 	return false;											
		}
		finalScrore=( Math.round(finalAmount)*100 ) / Math.round(entitledAmount);
		document.getElementById('itEntitledAmtScore'+code).value=Math.round(eval(finalScrore));
		
		if(document.getElementById('itEntitledAmtScore'+code).value=="" || document.getElementById('itEntitledAmtScore'+code).value=="NaN"){
		 document.getElementById('itEntitledAmtScore'+code).value=0;
		}
		
		// for tax deduction
  var itEntitledAmtScore =document.getElementById('itPIAmt'+code).value;
  var tax=document.getElementById('itTaxAmt'+code).value;
   document.getElementById('itTotalAmt'+code).value=Math.abs(itEntitledAmtScore)-Math.abs(tax);
				   
}

function callForTaxCal(code)
{
  var itPIAmt=document.getElementById('itPIAmt'+code).value;
  var itTaxAmt =document.getElementById('itTaxAmt'+code).value;
  if(itPIAmt==""){ itPIAmt=0;   }
  if(itTaxAmt==""){ itTaxAmt=0; }
  var totAmt = Math.abs(itPIAmt)-Math.abs(itTaxAmt);	
  document.getElementById('itTotalAmt'+code).value=totAmt;  
}

	
	
function callSalTab()
{  
 var salaryFlag = document.getElementById('salaryFlag').value;  
	if(document.getElementById('salaryFlag').checked==true)
	{
	document.getElementById('salTab').style.display = '';
	document.getElementById('PayModeTab').style.display = 'none';
	document.getElementById('paraFrm_hiddenChk').value = 'Y';   
	 }else
	 { 
	  document.getElementById('salTab').style.display = 'none'; 
	  document.getElementById('PayModeTab').style.display = '';
	  document.getElementById('paraFrm_hiddenChk').value = 'N';
	 }
	 document.getElementById('paraFrm_salFrmMonth').value = '0'; 
	 document.getElementById('paraFrm_salFrmYear').value = ''; 
}

function callSave()
{
    var finaliseFlag = document.getElementById('paraFrm_finaliseFlag').value;
    var divisionId = document.getElementById('paraFrm_divisionId').value ;
   var componentId = document.getElementById('paraFrm_componentId').value ;
   var paFromDate = document.getElementById('paraFrm_paFromDate').value ; 
   var paToDate =  document.getElementById('paraFrm_paToDate').value ;
    var processDate =  document.getElementById('paraFrm_processDate').value ;
   var compName=document.getElementById('compName').innerHTML.toLowerCase();
   var desbType=document.getElementById('desbType').innerHTML.toLowerCase();
   var division=document.getElementById('division').innerHTML.toLowerCase();
   var frmDate=document.getElementById('frmDate').innerHTML.toLowerCase();
   var toDate=document.getElementById('toDate').innerHTML.toLowerCase();
   desbType
    if(componentId=="")
    {
      alert("Please select "+compName);
      return false;
    }
   
    
    if( document.getElementById('paraFrm_hidDisbType').value =="")
    {
      alert("Please Select "+desbType); 
      return false;
    }
    if(divisionId=="")
    {
      alert("Please Select "+division);
      return false;
    }
   
     if(paFromDate=="")
    {
      alert("Please Enter "+frmDate); 
      return false;
    } 
     if(paToDate=="")
    {
     alert("Please Enter "+toDate);
      return false;
    } 
   
    strDate1 = paFromDate.split("-"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	strDate2 = paToDate.split("-"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	
	proDate = processDate.split("-"); 
	currtime = new Date(proDate[2],proDate[1]-1,proDate[0]); 
	
	if(endtime <= starttime) 
	{ 
		alert("To Date should be greater than From Date");
		document.getElementById('paraFrm_paFromDate').focus(); 
		return false;
	}
 
    if(document.getElementById('paraFrm_hiddenChk').value=="Y")
	{
		      if(document.getElementById('paraFrm_salFrmMonth').value=="0")
		    {
		      alert("Please Select Salary "+document.getElementById('monYear').innerHTML.toLowerCase());
		      document.getElementById('paraFrm_salFrmMonth').focus();
		      return false;
		    }
		     if(document.getElementById('paraFrm_salFrmYear').value =="")
		    {
		      alert("Please Enter Salary "+document.getElementById('monYear').innerHTML.toLowerCase());
		      document.getElementById('paraFrm_salFrmYear').focus();
		      return false;
		    }
      } 
       if((document.getElementById('paraFrm_hiddenChk').value=="N") ||( document.getElementById('paraFrm_hiddenChk').value==""))
	   {
	       if(document.getElementById('paraFrm_payMode').value =="B")
		    {  alert("Please Select "+document.getElementById('modPay').innerHTML.toLowerCase());
			      return false;
		    }
	   } 
	   
	   if(document.getElementById('paraFrm_processStatus').value !='done')
	   {
	       alert("Please do the Process !");
			      return false;
	   }
	   if(finaliseFlag=='L')
	{
	  alert('PA process is locked . To process,Please unlock it!');
	  return false;
	}
 
	 
}
function callPIpercent(id)
{ 
 
  var piScore = document.getElementById('itEntitledAmtScore'+id).value ; 
  var piAmt = document.getElementById('itEntitledAmt'+id).value ; 
  if(piScore=="")
  {
    piScore=0;
    }
   if(piAmt=="")
   {
    piAmt=0;
    }
    if(eval(piScore)>100)
    {
     alert("PA Score Should not be exceed 100 ");
     document.getElementById('itEntitledAmtScore'+id).value ="0";
     document.getElementById('itPIAmt'+id).value ="0";
     return false;
    }
  var finaAmt =Math.round(piAmt)*(Math.round(piScore)/100) ; 
  document.getElementById('itPIAmt'+id).value =Math.round(finaAmt);
  
  // for tax deduction
  var piAmt =document.getElementById('itPIAmt'+id).value;
  var tax=document.getElementById('itTaxAmt'+id).value
   document.getElementById('itTotalAmt'+id).value=Math.abs(piAmt)-Math.abs(tax);
  
}

function callDisbur(val)
{
 
  if(val=='AR')
  { 
     document.getElementById('paraFrm_hidDisbType').value ='A'; 
  }
  if(val=='MR')
  { 
     document.getElementById('paraFrm_hidDisbType').value ='M'; 
  }
}

 

 
 function callProcess()
 { 
   var finaliseFlag = document.getElementById('paraFrm_finaliseFlag').value;
    var divisionId = document.getElementById('paraFrm_divisionId').value ;
   var componentId = document.getElementById('paraFrm_componentId').value ;
   var paFromDate = document.getElementById('paraFrm_paFromDate').value ; 
   var paToDate =  document.getElementById('paraFrm_paToDate').value ;
   var processDate =  document.getElementById('paraFrm_processDate').value ;
   document.getElementById('paraFrm_processStatus').value='done';   
   
    if(finaliseFlag=='L')
	{
	  alert('PA process is locked . To process,Please unlock it!');
	  return false;
	}
   

 
    if(componentId=="")
    {
      alert("Please Select "+document.getElementById('compName').innerHTML.toLowerCase());
      return false;
    }
   
 
    if( document.getElementById('paraFrm_hidDisbType').value =="")
    {
      alert("Please Select "+document.getElementById('desbType').innerHTML.toLowerCase());
      return false;
    }
    if(divisionId=="")
    {
      alert("Please Select "+document.getElementById('division').innerHTML.toLowerCase());
      return false;
    }
       
      if(paFromDate=="")
    {
      alert("Please Enter "+document.getElementById('frmDate').innerHTML.toLowerCase());
      return false;
    } 
     if(paToDate=="")
    {
     alert("Please Enter "+document.getElementById('toDate').innerHTML.toLowerCase());
      return false;
    } 
    
    strDate1 = paFromDate.split("-"); 
	starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	
	strDate2 = paToDate.split("-"); 
	endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	
	proDate = processDate.split("-"); 
	currtime = new Date(proDate[2],proDate[1]-1,proDate[0]); 
	    
	if(endtime <= starttime) 
	{ 
		alert("To Date should be greater than From Date");
		document.getElementById('paraFrm_paFromDate').focus(); 
		return false;
	}
    
    if(!document.getElementById('paraFrm_paProcessId').value==""){
        
     var conf=confirm('Do you really want to reprocess');
     if(conf==true){
     
      return true;
      
     }
     return false;
   }
     
    if(document.getElementById('paraFrm_hiddenChk').value=="Y")
	{
		      if(document.getElementById('paraFrm_salFrmMonth').value=="0")
		    {
		      alert("Please Select Salary "+document.getElementById('monYear').innerHTML.toLowerCase());
		      document.getElementById('paraFrm_salFrmMonth').focus();
		      return false;
		    }
		     if(document.getElementById('paraFrm_salFrmYear').value =="")
		    {
		      alert("Please Enter Salary "+document.getElementById('monYear').innerHTML.toLowerCase());
		      document.getElementById('paraFrm_salFrmYear').focus();
		      return false;
		    }
      } 
       if((document.getElementById('paraFrm_hiddenChk').value=="N") ||( document.getElementById('paraFrm_hiddenChk').value==""))
	   {
	       if(document.getElementById('paraFrm_payMode').value =="B")
		    {  alert("Please Select "+document.getElementById('modPay').innerHTML.toLowerCase());
			      return false;
		    }
	   } 
 }
  
  function callPayMode()
  {
     document.getElementById('paraFrm_salFrmMonth').value = '0'; 
	 document.getElementById('paraFrm_salFrmYear').value = ''; 
  }
  function autoDate () {
	var tDay = new Date();
	var tMonth = tDay.getMonth()+1;
	var tDate = tDay.getDate();
			if ( tMonth < 10) tMonth = "0"+tMonth;
			if ( tDate < 10)
			    { 
			          tDate = "0"+tDate;
			     }
			        if(document.getElementById('paraFrm_paProcessId').value==""){
				document.getElementById("paraFrm_processDate").value = tDate+"-"+tMonth+"-"+tDay.getFullYear();
				}
			 
}
autoDate();

function callDel()
{
  var finaliseFlag = document.getElementById('paraFrm_finaliseFlag').value;
   if(finaliseFlag=='L')
	{
	  alert('PA process is locked . To process,Please unlock it!');
	  return false;
	}
	
	   if(document.getElementById('paraFrm_paProcessId').value=="")
	   {
		   alert("Please select a record to delete");
		   return false;
	   } 
      var conf=confirm("Do you really want to delete this record ?");
  			if(conf) 
  			{
  				return true;
  			}
	  		else
	  		{
	  			 return false;
	  		}
	  	return true;
	
}
	 

</script>



<script>
 
var req;
var which;
//WareHouse

function getWareHouseURL(url, formName)
{

	try
	{
		url = url + getFormAsStringWareHouse(formName);
	}
	catch(e)
	{
	 
	}
	if (window.XMLHttpRequest) // Non-IE browsers
	{ 
		req = new XMLHttpRequest();
	    req.onreadystatechange = processStateChangeLock;
	    try 
	    {
	    	req.open("GET", url, true); //was get 
	    }
	    catch (e) 
	    {
	      	alert("Problem Communicating with Server\n"+e);
	    }
	    req.send(null);
	} 
	else if (window.ActiveXObject) // IE 
	{
		req = new ActiveXObject("Microsoft.XMLHTTP");
	    if (req) 
	    {
	    	req.onreadystatechange = processStateChangeLock;        
	      	req.open("GET", url, true);
	       	req.send();
	    }
	}    
}

function getFormAsStringWareHouse(formName)
{
	try
	{
	
		var returnString = "";
		var formElements = document.forms[formName].elements;
		for (var i = formElements.length - 1; i >= 0; i--)
 		{
 			if(formElements[i].name == 'paProcessId')
 			{
 			   returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
 			}
 		}
	}
	catch(e)
	{
	 
	}
	return returnString;
}

function processStateChangeLock() 
{
	if(req.readyState == 4) // Complete 
	{
		if (req.status == 200) // OK response
	    {
	    	var res = req.responseText;	  
	    	alert(res);   
		}
		parent.frames[2].name='main';
	}
}


 function callLockConfirm()
	 { 
	 	   if(document.getElementById('LockUnlock').value=="Lock")
				   {
				     var conf=confirm("Do you really want Lock the Process ?");
			  			if(conf) 
			  			{
			  			
			  			     //////SEND AJAX REQUEST TO ACTION
			  			        getWareHouseURL("PAProcess_Lock.action?", "paraFrm"); 
			  			       document.getElementById('paraFrm_finaliseFlag').value="L";
			  			       document.getElementById('LockUnlock').value="Unlock" 
			  				return true;
			  			}
				  		else
				  		{
				  			 return false;
				  		}
	  	}else
			{
			  	   var conf=confirm("Do you really want Unlock the Process ?");
		  			if(conf) 
		  			{
		  				 //////SEND AJAX REQUEST TO ACTION 
		  				   getWareHouseURL("PAProcess_unLock.action?", "paraFrm"); 
		  				   document.getElementById('paraFrm_finaliseFlag').value="U";
		  				   document.getElementById('LockUnlock').value="Lock" 
		  				     return true;
		  			}
			  		else
			  		{
			  			 return false;
			  		}
	  	    }
	 }
	 
	 function callUnLockConfirm()
	 {
	     var conf=confirm("Do you really want Unlock the Process ?");
  			if(conf) 
  			{
  				 //////SEND AJAX REQUEST TO ACTION 
  				   getWareHouseURL("PAProcess_unLock.action?", "paraFrm"); 
  				   document.getElementById('paraFrm_finaliseFlag').value="U";
  				 
  				// document.getElementById('UNLOCK').style.display = 'none'; 
	           //  document.getElementById('LOCK').style.display = '';
  				 
  				return true;
  			}
	  		else
	  		{
	  			 return false;
	  		}
	 }
	if(document.getElementById('paraFrm_paProcessId').value !="" ||document.getElementById('paraFrm_processStatus').value=='done' )
	{
	isScroll();
	}
	function isScroll()
	{
		var apprFlag = document.getElementById('paraFrm_apprFlag').value; 
		if(apprFlag=='true')
		{
			 myST = superTable("tblPAApr", {fixedCols : 2,rightCols:3,viewCols:4});  //{fixedCols : 8,rightCols:1,viewCols:6})
		}
		if(apprFlag=='false')
		{
			 
			myST = superTable("tblMan", {fixedCols :2,rightCols:2,viewCols:4}); //{fixedCols :6,rightCols:1,viewCols:6});
		}
	}
</script>


 