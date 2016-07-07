<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>


<div align="center" id="overlay"
	style="z-index: 3; position: absolute; width: 776px; height: 450px; margin: 0px; left: 0; top: 0; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid :   DXImageTransform .   Microsoft .   alpha(opacity =   15); -moz-opacity: .1; opacity: .1;">
</div>
<div id="progressBar"
	style="z-index: 3; position: absolute; width:770px; height: 100% ">
<table  width="100%">
	<tr>
		<td height="200"></td>
	</tr>
	<tr>
		<td align="center"><img src="../pages/images/ajax-loader.gif">
		</td>
	</tr>
	<tr>
		<td align="center"><span
			style="color: red; font-size: 16px; font-weight: bold; z-index: 1000px;">Processing...</span>
		</td>
	</tr>
	<tr>
		<td align="center"><span
			style="color: red; font-size: 16px; font-weight: bold; z-index: 1000px;">Please
		do not close the browser and do not click anywhere</span></td>
	</tr>
</table>
</div>
<s:form action="PFInterestCal" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%" cellpadding="2" cellspacing="1">
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="1" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">PF
					Interest Calculation</strong></td>
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
			<table width="100%" border="0" align="right" cellpadding="2"
				cellspacing="2">
				<tr>
					<td colspan="2">
					<table width="100%" border="0" cellpadding="2" cellspacing="1">
						<tr>
							<td width="78%"><!--<s:if test="%{insertFlag}">
  						<input type="button" class="add" theme="simple"  value="  Save" onclick="return formValidate()" />
  						</s:if>
  				<s:if test="%{deleteFlag}">
  						<input type="button" class="delete" theme="simple"  value="  Delete" onclick="return formValidate('save',this)" />
  						</s:if>--> <input type="button" class="reset" theme="simple"
								value="  Reset" onclick="return callReset()" /></td>
							<td>
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="2">
								<tr>
									<td colspan="4" class="formhead"><strong
										class="forminnerhead"></strong></td>
								</tr>
								<tr>
									<td colspan="1" width="30%"><label class="set"
										id="calForAll" name="calForAll"
										ondblclick="callShowDiv(this);"><%=label.get("calForAll")%></label>
									: <font color="red">*</font></td>
									<td colspan="3" width="70%"><s:radio name="calcType"
										onclick="checkCalType();"
										list="#{'A':'Calculate For All Employees','F':'Filter-wise Calculation'}"></s:radio>
									</td>

								</tr>


							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<s:if test="filterFlag">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="2">
								<tr>
									<td colspan="4" class="formhead"><strong
										class="forminnerhead"></strong></td>
								</tr>
								<tr>
									<td width="25%" id='TD1'><label class="set"
										name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
									:</td>
									<td width="25%" id='TD2' nowrap="nowrap"><s:hidden
										name="applDivisionCode"></s:hidden> <s:textarea
										name="applDivisionName" cols="25" rows="2" readonly="true"></s:textarea>
									<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'PFInterestCal_f9applDiv.action'); "></td>
									<td width='25%' colspan="1"></td>
									<td width='25%' colspan="1"></td>
								</tr>

								<tr>
									<td width="25%"><label class="set" name="branch"
										id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td width="25%" nowrap="nowrap"><s:hidden
										name="applBranchCode"></s:hidden> <s:textarea
										name="applBranchName" cols="25" rows="2" readonly="true"></s:textarea>
									<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'PFInterestCal_f9applBranch.action'); ">

									</td>
									<td width="25%"><label class="set" name="department"
										id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:</td>
									<td width="25%" nowrap="nowrap"><s:hidden
										name="applDeptCode"></s:hidden> <s:textarea
										name="applDeptName" cols="25" rows="2" readonly="true"></s:textarea>
									<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'PFInterestCal_f9applDept.action'); ">

									</td>
								</tr>

								<tr>
									<td width="25%"><label class="set" name="designation"
										id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
									:</td>
									<td width="25%" nowrap="nowrap"><s:hidden
										name="applDesgCode"></s:hidden> <s:textarea
										name="applDesgName" cols="25" rows="2" readonly="true"></s:textarea>
									<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'PFInterestCal_f9applDesg.action'); ">

									</td>
									<td width="25%"><label class="set" name="employee.type"
										id="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
									:</td>
									<td width="25%" nowrap="nowrap"><s:hidden
										name="applETypeCode"></s:hidden> <s:textarea
										name="applETypeName" cols="25" rows="2" readonly="true"></s:textarea>
									<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'PFInterestCal_f9applEType.action'); ">
									</td>
								</tr>

								<tr>
									<td width="25%"><label class="set" name="grade" id="grade"
										ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
									:</td>
									<td width="25%" nowrap="nowrap"><s:hidden
										name="applGradeCode"></s:hidden> <s:textarea
										name="applGradeName" cols="25" rows="2" readonly="true"></s:textarea>
									<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'PFInterestCal_f9applGrade.action'); ">

									</td>
									<td width="25%"><label class="set" name="employee"
										id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									:</td>
									<td width="25%" nowrap="nowrap"><s:hidden
										name="applEmpCode"></s:hidden> <s:textarea name="applEmpName"
										cols="25" rows="2" readonly="true"></s:textarea> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16" theme="simple"
										onclick="javascript:callsF9(500,325,'PFInterestCal_f9applEmp.action'); ">
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr></s:if>
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="2" cellspacing="2"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="2">
								<tr>
									<td colspan="4" class="formhead"><strong
										class="forminnerhead"></strong></td>
								</tr>
								<tr>
									<td colspan="1" width="20%"><label class="set"
										id="dateFrm" name="dateFrm" ondblclick="callShowDiv(this);"><%=label.get("dateFrm")%></label>
									: <font color="red">*</font></td>
									<td colspan="1" width="30%"><s:select theme="simple"
										name="fromMonth" cssStyle="width:80" disabled="true"
										list="#{'04':'April','05':'May'}" /> <s:textfield
										theme="simple" name="fromYear" size="9" maxlength="4"
										onkeypress="return numbersOnly();" onkeyup="setToYear();"
										onblur="setToYear();" /></td>

									<td colspan="1" width="20%"><label class="set" id="dateTo"
										name="dateTo" ondblclick="callShowDiv(this);"><%=label.get("dateTo")%></label>
									: <font color="red">*</font></td>
									<td colspan="1" width="30%" nowrap="nowrap"><s:select
										theme="simple" name="toMonth" cssStyle="width:80"
										onchange="setToYear();"
										list="#{'':'-- Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
									<s:textfield theme="simple" name="toYear" readonly="true"
										maxlength="4" size="9" /></td>

								</tr>
								<tr>
									<td colspan="4" align="center"><input type="button"	value=' Calculate ' id="view" class="token"
										onclick="callDetails();" />
										<s:hidden name='calcFlag' /> 
										<s:hidden name='filterFlag' /> 
										<s:hidden name='intRate' /> 
										<s:hidden name='intAmount' /> 
										<s:hidden name='totDeposite' /> 
										<s:hidden name='totWithdraw' /> 
										<s:hidden name='closingBalance' />
										 <s:hidden name="myPage" /> 
										 <s:hidden name='empId' /></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>

				<s:if test="calcFlag">
					<tr>
						<td colspan="3">
						<table width="100%" border="0" cellpadding="2" cellspacing="2"
							class="formbg">
							<tr>
								<td>
								<table width="98%" border="0" align="center" cellpadding="1"
									cellspacing="1">
									<tr>
										<td colspan="5" class="formhead"><strong
											class="forminnerhead"></strong></td>
									</tr>
				<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Employee List </strong>  </td>
								
								<%
								int totalPage = 0;
								int pageNo =0;
								try{
					 totalPage = (Integer) request.getAttribute("totalPage");
					pageNo = (Integer) request.getAttribute("PageNo");
					
								}catch(Exception e){
									
								}
					%> <s:if test="noData"></s:if><s:else>
						<td align="right" colspan="4"><b>Page:</b> 
					 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="10" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
						</td>
						</s:else>
						</tr>

									<tr>

										<td width='5%' class="formth" align="center"><!--Sr No.  -->
										<label class="set" name="srno" id="srno"
											ondblclick="callShowDiv(this);"><%=label.get("srno")%></label>
										</td>
										<td width='15%' class="formth" align="center"><label
											class="set" name="employee.id" id="employee.id"
											ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
										</td>
										<td width='20%' class="formth" align="center"><label
											class="set" name="employee" id="employee"
											ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
										</td>
										<td width='10%' class="formth" align="center"><label
											class="set" name="opBalance" id="opBalance"
											ondblclick="callShowDiv(this);"><%=label.get("opBalance")%></label>
										</td>
										<td width='10%' class="formth" align="center"><label
											class="set" name="deposite" id="deposite"
											ondblclick="callShowDiv(this);"><%=label.get("deposite")%></label>
										</td>
										<td width='10%' class="formth" align="center"><label
											class="set" name="withdraw" id="withdraw"
											ondblclick="callShowDiv(this);"><%=label.get("withdraw")%></label>
										</td>
										<td width='10%' class="formth" align="center"><label
											class="set" name="interest" id="interest"
											ondblclick="callShowDiv(this);"><%=label.get("interest")%></label>
										</td>
										<td width='10%' class="formth" align="center"><label
											class="set" name="cloBalance" id="cloBalance"
											ondblclick="callShowDiv(this);"><%=label.get("cloBalance")%></label>
										</td>
										<td width='10%' class="formth" align="center">Recalculate</td>


									</tr>
									<%
										int i = 1;
										int k = 0;
									%>
									<s:iterator value="empList">
										<%
										k = i;
										%>
										<tr>
											<td width="5%" align="center"><%=(20*(pageNo-1))+k%></td>
											<td width="15%" align="left"><s:property
												value="empTokenList" /><s:hidden name='empTokenList' /><s:hidden
												name='empCodeList' /></td>
											<td width="20%"><s:property value="empNameList" /> <s:hidden
												name="empNameList" /></td>
											<td width="12%" align="right"><s:property
												value="opnBalanceList" /> <s:hidden name="opnBalanceList" /></td>
											<td width="12%" align="right"><s:property
												value="depositeAmtList" /> <s:hidden name="depositeAmtList" /></td>
											<td width="12%" align="right"><s:property
												value="withdrawAmtList" /> <s:hidden name="withdrawAmtList" /></td>
											<td width="12%" align="right"><s:property
												value="intAmtList" /><s:hidden name="intAmtList" /></td>
											<td width="12%" align="right"><s:property
												value="clsBalanceList" /><s:hidden name="clsBalanceList" /></td>
											<td width="12%" align="right"><input type="button"
												class="edit" value='  Recalculate'
												onclick="return callEmpDetail(<s:property value='empCodeList'/>)" /></td>
										</tr>
										<%
											k++;
											i++;
										%>
									</s:iterator>
									<%
									i = 1;
									%>

									<input type="hidden" name='count' id='count' value="<%=k-1%>" />
								</table>
								<!--<s:if test="calcFlag">		
				<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0" >
								<tr style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;">
									<td width="5%" align="center" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse">&nbsp;</td>
									<td width="15%" align="center" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse">&nbsp;</td>
									<td width="13%" align="center" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfSub" /></b> <s:hidden name="totPfSub" /></td>
									<td width="13%" align="center" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfRefund" /></b> <s:hidden name="totPfRefund" /></td>
									<td width="13%" align="left" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfMPCont" /></b> <s:hidden name="totPfMPCont" /></td>
									<td width="13%" align="center" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfLoan" /></b> <s:hidden name="totPfLoan" /></td>
									<td width="13%" align="left" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfRepay" /></b><s:hidden name="totPfRepay" /></td>
									<td width="15%" align="center" style="border-bottom: 1px solid #000000;border-top: 1px solid #000000;border-collapse: collapse"><b><s:property value="totPfProg" /></b><s:hidden name="totPfProg" /><s:hidden name="totPfProgActual" /></td>
								</tr>
							
           
						</table></s:if>--></td>
							</tr>
						</table>
				</s:if>
			</table>
			</td>
		</tr>
	</table>
</s:form>



<script type="text/javascript">
		var fields =["paraFrm_fromYear","paraFrm_toMonth"];
		var labels =["dateFrm","dateTo"];
		var types =["enter","select"];
function callDetails(){
		document.getElementById('paraFrm_myPage').value="1";
		if(!validateBlank(fields,labels, types)){
			return false;
			}
		var fromYear=document.getElementById('paraFrm_fromYear').value;
		if(fromYear.length !=4 || eval(fromYear)==0){
			alert("Please enter valid "+document.getElementById('dateFrm').innerHTML);
			document.getElementById('paraFrm_fromYear').focus();
		return false;
		}
		enableBlockDiv();
		document.getElementById('paraFrm_calcFlag').value='true';
		document.getElementById('paraFrm').action='PFInterestCal_getDetails.action';
		document.getElementById('paraFrm').submit();
			//disableBlockDiv();
}
callOnload();
function callOnload(){
disableBlockDiv();
		if(document.getElementById('paraFrm_filterFlag').value=="true"){
			document.getElementById('paraFrm_calcTypeF').checked=true;
		}else{
			document.getElementById('paraFrm_calcTypeA').checked=true;
		}
		if(document.getElementById("paraFrm_fromYear").value==""){
		 setCurrentYear("paraFrm_fromYear");
		 }
	
}
function setToYear(){
	var toMonth = document.getElementById('paraFrm_toMonth').value;
	var fromYear = document.getElementById('paraFrm_fromYear').value;
	if(fromYear !=""){
	if(eval(toMonth)<4){
		document.getElementById('paraFrm_toYear').value=eval(fromYear)+1;
	}else if(eval(toMonth)>=4){
		document.getElementById('paraFrm_toYear').value=eval(fromYear);
	}else{
		document.getElementById('paraFrm_toYear').value='';
	}
	}else{
		document.getElementById('paraFrm_toYear').value='';
	}
}
function checkCalType(){
	if(!document.getElementById('paraFrm_calcTypeA').checked){
		document.getElementById('paraFrm_calcTypeF').checked=true;
		document.getElementById('paraFrm_filterFlag').value='true';
	}else{
		document.getElementById('paraFrm_calcTypeF').checked=false;
		document.getElementById('paraFrm_filterFlag').value='false';
	}	
		document.getElementById('paraFrm_calcFlag').value='false';
		document.getElementById('paraFrm').action='PFInterestCal_input.action';
		document.getElementById('paraFrm').submit();
	
}
function callEmpDetail(empId){
		document.getElementById('paraFrm_empId').value=empId;
		win=window.open('','win','top=260,left=150,width=800,height=450,scrollbars=yes,status=no,resizable=no');
		document.getElementById("paraFrm").target="win";
		document.getElementById('paraFrm').action="PFInterestCal_openEmpCalcWindow.action";
		document.getElementById('paraFrm').submit();
		document.getElementById("paraFrm").target="main";
}
function formValidate(){
	if(!validateBlank(fields,labels, types)){
			return false;
			}
		
		if(document.getElementById('paraFrm_calcFlag').value=='false'){
			alert("Please calculate the interest first");
			document.getElementById('view').focus();
			return false;
		}
		document.getElementById('paraFrm').action='PFInterestCal_save.action';
		document.getElementById('paraFrm').submit();
	}
	function callReset(){
		document.getElementById('paraFrm').action='PFInterestCal_reset.action';
		document.getElementById('paraFrm').submit();
	}
	
	
	 function callPageText(id,status1){
 			 
	   if(status1=="null" || status1=="" ){		
				status1="P";
			}
			var key //= (window.event) ? event.keyCode : e.which;
			if (window.event)
			    key = event.keyCode
			else
			   	key = id.which
			clear();
					
			if(key==13) 
			{ 
			pageNo =document.getElementById('pageNoField').value;		
		 	totalPage =document.getElementById('totalPage').value;		
			document.getElementById('paraFrm_myPage').value=pageNo;
	      
	        if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		   document.getElementById('paraFrm').action='PFInterestCal_displayPfDetails.action';
			document.getElementById('paraFrm').submit();
		}
		
		
	}
	 function callPage(id,pageImg){
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	 
 	   if(pageNo==""){
	        alert("Please Enter Page Number.");
	        document.getElementById('pageNoField').focus();
			 return false;
	        }
	 if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
	  if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       if(pageImg=="F")
         {
	        if(pageNo=="1"){
	         alert("This is first page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
       	if(pageImg=="L")
         {
	        if(eval(pageNo)==eval(totalPage)){
	         alert("This is last page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       } 
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page.");
	         document.getElementById('pageNoField').focus();
	         return false;
	        }  
       }  
       
         var p=document.getElementById('pageNoField').value;
		if(id=='P'){
		id=eval(p)-1;
		}
		if(id=='N'){
		id=eval(p)+1;
		} 
		
		document.getElementById('paraFrm_myPage').value=id;
		document.getElementById('paraFrm').action='PFInterestCal_displayPfDetails.action';
		document.getElementById('paraFrm').submit();
		 
	}
	 /* ===================================================================================
	Function	: checkPincode
	Explanation : for blocking the screen (all frames with div) when request execution 
	prepared by : Venkatesh
   ===================================================================================*/
	function enableBlockDiv(){
	  		
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "visible";
				document.getElementById("overlay").style.display = "block";
				document.getElementById("progressBar").style.visibility = "visible";
				document.getElementById("progressBar").style.display = "block";   
			}
			catch(e){
			}
	  }
	  
	  	/* ===================================================================================
		Function	: checkPincode
		Explanation : for disabling the blocking  screen (all frames ) when request execution completes
		prepared by : Venkatesh
	   ===================================================================================*/
	  function disableBlockDiv(){
	  		
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";   
			}
			catch(e){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
				document.getElementById("progressBar").style.visibility = "hidden";
				document.getElementById("progressBar").style.display = "none";  
			}
	  }
</script>
