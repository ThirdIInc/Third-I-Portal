<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"></script>
<s:form action="AdminConfSettings" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" border="0" align="right" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Admin
					Configuration</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<s:hidden name="paraId" />
		<tr>
			<td>
			<table width="100%" border="0">
				<td width="80%"><jsp:include
					page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
				<td width="20%">
				<div align="right"><span class="style2"><font
					color="red">*</font></span> Indicates Required</div>
				</td>
			</table>
			</td>
		</tr>
		
		<!-- CASH VOUCHER DETAILS -->
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="formbg">
					<tr>
						<td class="txt" colspan="2">
							<table width="100%" border="0"  class="formbg">
								<tr>
									<td colspan="2"><strong class="text_head"><label class="set" id="cashVoucher" name="cashVoucher"
								onDblClick="callShowDiv(this);"><%=label.get("cashVoucher")%></strong></td>
								
								</tr>
							</table>
						</td>
					</tr>
						
					
						<tr>
							<td><label class="set" id="ManagerApproval"
								name="ManagerApproval" onDblClick="callShowDiv(this);"><%=label.get("ManagerApproval")%></label>
							:</td>
							<td id="ctrlShow"><s:select list="#{'Y':'YES','N':'NO'}" name="managerFlag"></s:select>
							</td>
						</tr>
						<tr>
							<td><label class="set" id="AdminApproval" name="AdminApproval"
								onDblClick="callShowDiv(this);"><%=label.get("AdminApproval")%></label>
							:</td>
							<td id="ctrlShow"><s:select list="#{'Y':'YES','N':'NO'}" name="adminFlag"></s:select>
							</td>
						</tr>
						<tr>
							<td><label class="set" id="division" name="division"
								onDblClick="callShowDiv(this);"><%=label.get("division")%></label>
							:</td>
							<td id="ctrlShow">
							<s:hidden name="divCode" />
							 <s:textfield name="division" size="30" value="%{division}" theme="simple" readonly="true" /> 
								<img src="../pages/images/recruitment/search2.gif" height="18"
								     align="absmiddle" width="18" onclick="javascript:callsF9(500,325,'AdminConfSettings_f9Div.action');">
							</td>
						</tr>
						<tr>
							<td><label class="set" id="admin" name="admin"
								onDblClick="callShowDiv(this);"><%=label.get("admin")%></label> :</td>
							<td id="ctrlShow">
							<s:hidden name="empToken"></s:hidden> 
							<s:textfield name="empName" size="30" value="%{empName}" theme="simple" readonly="true" />
							<s:hidden name="empId"></s:hidden> 
							<img src="../pages/images/recruitment/search2.gif" height="18"  align="absmiddle" width="18"
								 onclick="javascript:callsF9(500,325,'AdminConfSettings_f9Admin.action');">
							<s:submit value="Add" cssClass="add" action="AdminConfSettings_addAdminConfDtl"></s:submit></td>
						</tr>
						<tr><td>&nbsp;</td></tr>
				<!-- EMPLOYEE LIST START -->
					    <tr>
							<td  colspan="2" width="100%">
								<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="formbg">
									<%int counter11 = 0;%>
									<tr>
										<td class="formth" width="6%" height="22" valign="top"><label name="srno" class = "set"  id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="division" class = "set"  id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="AdminEmp" class = "set"  id="AdminEmp" ondblclick="callShowDiv(this);"><%=label.get("AdminEmp")%></label></td>
										<td class="formth" width="9%" height="22" valign="top"><label name="delete1" class = "set"  id="delete1" ondblclick="callShowDiv(this);"><%=label.get("delete1")%></label></td>
									</tr>
									<s:iterator value="adminConfList">
										<tr>
											<td align="center"><%=++counter11%>
												<s:hidden name="srNoIt" />
												<s:hidden name="adminConfIdIt" />
												
											</td>
											<td align="center">
												<s:property value="divNameIt" />
												<s:hidden name="divNameIt" />
												<s:hidden name="divCodeIt" />
											</td>
											<td align="center">
												
												<s:property value="adminNameIt" />
												<s:hidden name="adminNameIt" />
												<s:hidden name="adminCodeIt" />
											</td>	
											<td align="center" id="ctrlShow">
												<input type="button" class="rowDelete"	theme="simple"  title="Delete Record"    onclick="return deleteAdminConfDtl('<%=(counter11)%>');" />
											</td>		
										</tr>
									</s:iterator>
								</table>
							</td>
					    </tr>
						<tr><td>&nbsp;</td></tr>
					    <tr>
							<td><label class="set" id="account" name="account" onDblClick="callShowDiv(this);"><%=label.get("account")%></label>:</td>
							<td id="ctrlShow">
								<s:select list="#{'Y':'YES','N':'NO'}" name="acctFlag"></s:select>
							</td>
						</tr>
						<tr>
							<td><label class="set" id="division" name="division" onDblClick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
							<td id="ctrlShow">
								<s:hidden name="accDivCode" /> 
								<s:textfield name="accDivision" size="30" value="%{accDivision}" theme="simple" readonly="true" /> 
								<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'AdminConfSettings_f9AccDiv.action');">
							</td>
						</tr>
						<tr>
							<td><label class="set" id="accountant" name="accountant" onDblClick="callShowDiv(this);"><%=label.get("accountant")%></label> :</td>
							<td id="ctrlShow">
								<s:hidden name="accEmpToken"></s:hidden>
								<s:textfield name="accEmpName" size="30" value="%{accEmpName}" theme="simple" readonly="true" />
								<s:hidden name="accEmpId"></s:hidden> 
								<img src="../pages/images/recruitment/search2.gif" height="18"	align="absmiddle" width="18"
									  onclick="javascript:callsF9(500,325,'AdminConfSettings_f9AccAdmin.action');">
								<s:submit value="Add" cssClass="add" action="AdminConfSettings_addAccountantConfDtl"></s:submit>
							</td>
					   </tr>
				   		    <tr><td>&nbsp;</td></tr>
					    <tr>
							<td td colspan="2" width="100%">
								<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="formbg">
									<%int counter = 0; %>
									<tr>
										<td class="formth" width="6%" height="22" valign="top"><label name="srno" class = "set"  id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="division" class = "set"  id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="AdminEmp" class = "set"  id="AdminEmp" ondblclick="callShowDiv(this);"><%=label.get("AdminEmp")%></label></td>
										<td class="formth" width="9%" height="22" valign="top"><label name="delete1" class = "set"  id="delete1" ondblclick="callShowDiv(this);"><%=label.get("delete1")%></label></td>
									</tr>
									<s:iterator value="accConfList">
										<tr>
											<td align="center">
												<%=++counter%>
												<s:hidden name="accSrNoIt" />
												<s:hidden name="accConfIdIt" />
												
											</td>
											<td align="center">
												<s:property value="accDivNameIt" />
												<s:hidden name="accDivNameIt" />
												<s:hidden name="accDivCodeIt" />
											</td>
											<td align="center">
												<s:property value="accAdminNameIt" />
												<s:hidden name="accAdminNameIt" />
												<s:hidden name="accAdminCodeIt" />
											</td>	
											<td align="center" id="ctrlShow">
												<input type="button" class="rowDelete"	theme="simple"  title="Delete Record" onclick="deleteAccConfDtl('<%=counter%>')"/>
											</td>		
										</tr>
			
									</s:iterator>
									
								</table>
							</td>
					    </tr>	
		 		</table>
		 	</tr>	
		 	<tr><td>&nbsp;</td></tr>
<!-- CASH VOUCHER DETAILS END -->
<!-- BUSINESS CARD DETAILS START -->
	<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="0" class="formbg">
						<tr>
							<td class="txt" colspan="2">
								<table width="100%" border="0"  class="formbg">
									<tr>
										<td colspan="2"><strong class="text_head"><label class="set" id="businessCard" name="businessCard" 
										onDblClick="callShowDiv(this);"><%=label.get("businessCard")%></label></strong></td>
								
									</tr>
								</table>
							</td>
						</tr>
						
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td><label class="set" id="AdminApproval"
								name="AdminApproval" onDblClick="callShowDiv(this);"><%=label.get("AdminApproval")%></label>
							:</td>
							<td id="ctrlShow"><s:select list="#{'Y':'YES','N':'NO'}" name="bCardManagerFlag"></s:select>
							</td>
						</tr>

						<tr>
							<td><label class="set" id="division" name="division" onDblClick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
							<td id="ctrlShow">
								<s:hidden name="bCardDivCode" /> 
								<s:textfield name="bCardDivision" size="30" value="%{bCardDivision}" theme="simple" readonly="true" />
								 <img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18"
									  onclick="javascript:callsF9(500,325,'AdminConfSettings_f9BCardDiv.action');">
							</td>
						</tr>
						<tr>
							<td><label class="set" id="admin" name="admin" onDblClick="callShowDiv(this);"><%=label.get("admin")%></label> :</td>
							<td id="ctrlShow">
								<s:hidden name="bCardEmpToken"></s:hidden> 
								<s:textfield name="bCardEmpName" size="30" value="%{bCardEmpName}" theme="simple" readonly="true" />
								<s:hidden name="bCardEmpId"></s:hidden>
								<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'AdminConfSettings_f9BCardAdmin.action');">
								<s:submit value="Add" cssClass="add" action="AdminConfSettings_addBCardAdminConfDtl"></s:submit>
							</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
				<!-- EMPLOYEE LIST START -->
					    <tr>
							<td colspan="2" width="100%">
								<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="formbg">
									<%int busCnt1 = 0;  %>
									<tr>
										<td class="formth" width="6%" height="22" valign="top"><label name="srno" class = "set"  id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="division" class = "set"  id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="AdminEmp" class = "set"  id="AdminEmp" ondblclick="callShowDiv(this);"><%=label.get("AdminEmp")%></label></td>
										<td class="formth" width="9%" height="22" valign="top"><label name="delete1" class = "set"  id="delete1" ondblclick="callShowDiv(this);"><%=label.get("delete1")%></label></td>
									</tr>
									<s:iterator value="adminBCardConfList">
										<tr>
											<td align="center">
												<%=++busCnt1%>
												<s:hidden name="bCardSrNoIt" />
												<s:hidden name="bCardConfIdIt" />
											</td>
											<td align="center">
												<s:property value="bCardDivNameIt" />
												<s:hidden name="bCardDivNameIt" />
												<s:hidden name="bCardDivCodeIt" />
											</td>
											<td align="center">
												<s:property value="bCardAdminNameIt" />
												<s:hidden name="bCardAdminNameIt" />
												<s:hidden name="bCardAdminCodeIt" />
											</td>	
											<td align="center" id="ctrlShow">
												<input type="button" class="rowDelete"	theme="simple"  title="Delete Record"    onclick="return deleteBCardConfDtl('<%=(busCnt1)%>');" />
											</td>		
										</tr>
										
									</s:iterator>
									
								</table>
							</td>
					    </tr>
					    <tr><td>&nbsp;</td></tr>
					    <tr>
					    		<td><label class="set" id="vendorBCApproval" name="vendorBCApproval" onDblClick="callShowDiv(this);"><%=label.get("vendorBCApproval")%></label>:</td>
								<td id="ctrlShow"><s:select list="#{'Y':'YES','N':'NO'}" name="bCardVendorFlag"></s:select></td>
					    </tr>
					    <tr>
					    	<td><label class="set" id="vendorName" name="vendorName" onDblClick="callShowDiv(this);"><%=label.get("vendorName")%></label>:</td>
					    	<td id="ctrlShow"><s:textfield name="bCardVndrName" size="20"/></td>
					    </tr>
					    <tr>
					    	<td><label class="set" id="vendorEmail" name="vendorEmail" onDblClick="callShowDiv(this);"><%=label.get("vendorEmail")%></label>:</td>
					    	<td id="ctrlShow"><s:textfield name="bCardVndrEmail" size="20" /></td>
					    </tr>
					    <tr>
					    	<td><label class="set" id="vendorPhNum" name="vendorPhNum" onDblClick="callShowDiv(this);"><%=label.get("vendorPhNum")%></label>:</td>
					    	<td id="ctrlShow"><s:textfield name="bCardVndrPhNum" size="20"/></td>
					    </tr>
					     <tr>
							<td>&nbsp;</td>
							
						</tr>
						<tr><td>&nbsp;</td></tr>
					  <tr>
						<!-- Vendor Details List -->
							<td colspan="2" width="100%">
								<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="formbg">
									
									<tr>
									
										<td class="formth" width="20%" height="22" valign="top"><label name="vendorName" class = "set"  id="division" ondblclick="callShowDiv(this);"><%=label.get("vendorName")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="vendorEmail" class = "set"  id="AdminEmp" ondblclick="callShowDiv(this);"><%=label.get("vendorEmail")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="vendorPhNum" class = "set"  id="AdminEmp" ondblclick="callShowDiv(this);"><%=label.get("vendorPhNum")%></label></td>
										
									</tr>
									<s:iterator value="eCardVendorConfList">
										<tr>
											
											<td align="center">
												<s:property value="bCardVndrNameIt" />
											     <s:hidden name="bCardVndrNameIt" />
											</td>
											<td align="center">
												
												<s:property value="bCardVndrEmailIt" />
												<s:hidden name="bCardVndrEmailIt" />
											
											</td>	
											<td align="center">
												
												<s:property value="bCardVndrPhNumIt" />
												<s:hidden name="bCardVndrPhNumIt" />
											
											</td>
											
											
										</tr>
										
									</s:iterator>
									
								</table>
							</td>
					    </tr>
					    <tr><td>&nbsp;</td></tr><!-- Vendor List ends -->
		 	</table>
		</td>
</tr>
<tr><td>&nbsp;</td></tr>
<!-- BUSINESS CARD DETAILS END -->
<!-- EMPLOYEE CARD DETAILS STARTS -->
			<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="1"
				cellspacing="0" class="formbg">
						<tr>
							<td class="txt" colspan="2">
								<table width="100%" border="0"  class="formbg">
									<tr>
										<td colspan="2"><strong class="text_head"><label class="set" id="employeeCard" 
										name="employeeCard" onDblClick="callShowDiv(this);"><%=label.get("employeeCard")%></label></strong></td>
								
									</tr>
								</table>
							</td>
						</tr>
						
						<tr><td>&nbsp;</td></tr>
						<tr>
							<td><label class="set" id="division" name="division" onDblClick="callShowDiv(this);"><%=label.get("division")%></label>:</td>
							<td id="ctrlShow">
								<s:hidden name="eCardDivCode" /> 
								<s:textfield name="eCardDivision" size="30" value="%{eCardDivision}" theme="simple" readonly="true" />
								 <img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18"
									  onclick="javascript:callsF9(500,325,'AdminConfSettings_f9ECardDiv.action');">
							</td>
						</tr>
						<tr>
							<td><label class="set" id="admin" name="admin" onDblClick="callShowDiv(this);"><%=label.get("admin")%></label> :</td>
							<td id="ctrlShow">
								<s:hidden name="eCardEmpToken"></s:hidden> 
								<s:textfield name="eCardEmpName" size="30" value="%{eCardEmpName}" theme="simple" readonly="true" />
								<s:hidden name="eCardEmpId"></s:hidden>
								<img src="../pages/images/recruitment/search2.gif" height="18" align="absmiddle" width="18"
									onclick="javascript:callsF9(500,325,'AdminConfSettings_f9ECardAdmin.action');">
								<s:submit value="Add" cssClass="add" action="AdminConfSettings_addECardAdminConfDtl"></s:submit>
							</td>
						</tr>
						<tr><td>&nbsp;</td></tr>
				<!-- EMPLOYEE LIST START -->
					    <tr>
							<td colspan="2" width="100%">
								<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="formbg">
									<%int eCnt1 = 0;  %>
									<tr>
										<td class="formth" width="6%" height="22" valign="top"><label name="srno" class = "set"  id="srno" ondblclick="callShowDiv(this);"><%=label.get("srno")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="division" class = "set"  id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="AdminEmp" class = "set"  id="AdminEmp" ondblclick="callShowDiv(this);"><%=label.get("AdminEmp")%></label></td>
										<td class="formth" width="9%" height="22" valign="top"><label name="delete1" class = "set"  id="delete1" ondblclick="callShowDiv(this);"><%=label.get("delete1")%></label></td>
									</tr>
									<s:iterator value="adminECardConfList">
										<tr>
											<td align="center">
												<%=++eCnt1%>
												<s:hidden name="eCardSrNoIt" />
												<s:hidden name="eCardConfIdIt" />
											</td>
											<td align="center">
												<s:property value="eCardDivNameIt" />
												<s:hidden name="eCardDivNameIt" />
												<s:hidden name="eCardDivCodeIt" />
											</td>
											<td align="center">
												
												<s:property value="eCardAdminNameIt" />
												<s:hidden name="eCardAdminNameIt" />
												<s:hidden name="eCardAdminCodeIt" />
											</td>	
											<td align="center" id="ctrlShow">
												<input type="button" class="rowDelete"	theme="simple"  title="Delete Record"    onclick="return deleteECardConfDtl('<%=(eCnt1)%>');" />
											</td>		
										</tr>
										
									</s:iterator>
									
								</table>
							</td>
					    </tr>
					    <tr><td>&nbsp;</td></tr>
					    <tr>
					    		<td><label class="set" id="eCardVendorApproval" name="vendorApproval" onDblClick="callShowDiv(this);"><%=label.get("vendorApproval")%></label>:</td>
								<td id="ctrlShow"><s:select list="#{'Y':'YES','N':'NO'}" name="eCardVendorFlag"></s:select></td>
					    </tr>
					    <tr>
					    	<td><label class="set" id="vendorName" name="vendorName" onDblClick="callShowDiv(this);"><%=label.get("vendorName")%></label>:</td>
					    	<td id="ctrlShow"><s:textfield name="eCardVndrName" size="20"/></td>
					    </tr>
					    <tr>
					    	<td><label class="set" id="vendorEmail" name="vendorEmail" onDblClick="callShowDiv(this);"><%=label.get("vendorEmail")%></label>:</td>
					    	<td id="ctrlShow"><s:textfield name="eCardVndrEmail" size="20"/></td>
					    </tr>
					    <tr>
					    	<td><label class="set" id="vendorPhNumIt" name="vendorPhNum" onDblClick="callShowDiv(this);"><%=label.get("vendorPhNum")%></label>:</td>
					    	<td id="ctrlShow"><s:textfield name="eCardVndrPhNum" size="20"/></td>
					    	
					    </tr>
					   
							
					    <tr>
							<td>&nbsp;</td>
							
						</tr>
						<tr><td>&nbsp;</td></tr>
						<tr>
						<!-- Vendor Details List -->
							<td colspan="2" width="100%">
								<table width="100%" border="0" align="center" cellpadding="1" cellspacing="0" class="formbg">
									
									<tr>
									
										<td class="formth" width="20%" height="22" valign="top"><label name="vendorName" class = "set"  id="division" ondblclick="callShowDiv(this);"><%=label.get("vendorName")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="vendorEmail" class = "set"  id="AdminEmp" ondblclick="callShowDiv(this);"><%=label.get("vendorEmail")%></label></td>
										<td class="formth" width="20%" height="22" valign="top"><label name="vendorPhNum" class = "set"  id="AdminEmp" ondblclick="callShowDiv(this);"><%=label.get("vendorPhNum")%></label></td>
										
									</tr>
									<s:iterator value="eCardVendorConfList">
										<tr>
											
											<td align="center">
												<s:property value="eCardVndrNameIt" />
											     <s:hidden name="eCardVndrNameIt" />
											</td>
											<td align="center">
												
												<s:property value="eCardVndrEmailIt" />
												<s:hidden name="eCardVndrEmailIt" />
											
											</td>	
											<td align="center">
												
												<s:property value="eCardVndrPhNumIt" />
												<s:hidden name="eCardVndrPhNumIt" />
											
											</td>
											
											
										</tr>
										
									</s:iterator>
									
								</table>
							</td>
					    </tr>
					    <tr><td>&nbsp;</td></tr>
		 	</table>
		</td>
</tr>
<!-- EMPLOYEE CARD DETAILS END -->
		<tr>
			<td width="100%"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>

	</table>
</s:form>
<script>

function saveFun()
{
	 if(!document.getElementById('paraFrm_eCardVndrEmail').value =="")
	 {
			var abc=validateEmail('paraFrm_eCardVndrEmail');
		  	if(!abc){
		  		return false;
		  	}
	 }
	  if(!document.getElementById('paraFrm_bCardVndrEmail').value =="")
	 {
			var abc=validateEmail('paraFrm_bCardVndrEmail');
		  	if(!abc){
		  		return false;
		  	}
	 }
	 
	 var mobileNum=document.getElementById('paraFrm_bCardVndrPhNum').value;
	 var eMobileNum=document.getElementById('paraFrm_eCardVndrPhNum').value;
	 if(!mobileNum==""){
		    var iChars = "0123456789-/ ";
		   	for (var i = 0; i < mobileNum.length; i++) {			
			  	if (!(iChars.indexOf(mobileNum.charAt(i)) != -1)) {
				  	alert ("Only Numerics Allowed in phone number");
				  	document.getElementById('paraFrm_bCardVndrPhNum').value="";
				  	return false;
				}
  			}//for loop close
  	 }
  	 if(!eMobileNum==""){
		    var iChars = "0123456789-/ ";
		   	for (var i = 0; i < eMobileNum.length; i++) {			
			  	if (!(iChars.indexOf(eMobileNum.charAt(i)) != -1)) {
				  	alert ("Only Numerics Allowed in phone number");
				  	document.getElementById('paraFrm_eCardVndrPhNum').value="";
				  	return false;
				}
  			}//for loop close
  	 }
  	 
	 var con=confirm('Do you want to save the record(s) ?');
	 if(con){
		document.getElementById("paraFrm").target = "_self";
     	document.getElementById("paraFrm").action ="AdminConfSettings_saveConfDetails.action";
		document.getElementById("paraFrm").submit();
	}
}
function resetFun(){
	    document.getElementById("paraFrm").target = "_self";
     	document.getElementById("paraFrm").action ="AdminConfSettings_reset.action";
		document.getElementById("paraFrm").submit();
}
function deleteAdminConfDtl(id) {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	 	document.getElementById("paraFrm_paraId").value=id;
		document.getElementById("paraFrm").target = "_self";
     	document.getElementById("paraFrm").action = "AdminConfSettings_deleteAdminConfDetails.action";
		document.getElementById("paraFrm").submit();
	}
}
function deleteAccConfDtl(id) {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	    document.getElementById("paraFrm_paraId").value=id;
		document.getElementById("paraFrm").target = "_self";
     	document.getElementById("paraFrm").action ="AdminConfSettings_deleteAccConfDetails.action";
		document.getElementById("paraFrm").submit();
	}
}
function deleteBCardConfDtl(id) {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	 	document.getElementById("paraFrm_paraId").value=id;
		document.getElementById("paraFrm").target = "_self";
     	document.getElementById("paraFrm").action ="AdminConfSettings_deleteBCardConfDetails.action";
		document.getElementById("paraFrm").submit();
	}
}
function deleteECardConfDtl(id) {
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
	 document.getElementById("paraFrm_paraId").value=id;
		document.getElementById("paraFrm").target = "_self";
     	document.getElementById("paraFrm").action ="AdminConfSettings_deleteECardConfDetails.action";
		document.getElementById("paraFrm").submit();
	}
}	

</script>
