<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="PFParameter" method="post" id="paraFrm" validate="true"
	target="main" theme="simple">
	
	<div align="center" id="overlay"
		style="z-index: 3; position: absolute; width: 790px; height: 920px; margin: 0px; left: 0; top: 100; background-color: #A7BEE2; background-image: url('images/grad.gif'); filter: progid :   DXImageTransform .   Microsoft .   alpha(opacity =   15); -moz-opacity: .1; opacity: .1; display: none;">
	</div>
	<div id="confirmationDiv"
	style='position: absolute; z-index: 3; 100 px; height: 150px; visibility: hidden; top: 200px; left: 100px;'></div>
	
<s:hidden name="show"  />
<s:hidden name="hiddencode" />
<s:hidden name="myPageEmpConf"/>
<s:hidden name="EPFflag"/>
<s:hidden name="GPFflag"/>
<s:hidden name="VPFflag"/>
<s:hidden name="PFTflag"/>
<table class="formbg" width="100%" cellpadding="2" cellspacing="1">

		<tr>
			<td colspan="3" width="100%"><%@ include file="pfConfigHeader.jsp"%></td>
		</tr>
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Configure Employee For PF</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">

	 <table width="100%" border="0" align="right" cellpadding="2" cellspacing="2" >
    <tr>
      <td colspan="3">
      <table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
          <tr>
            <td width="78%">	
	    		<s:if test="%{insertFlag}"><s:submit cssClass="add" action="PFParameter_saveEmpConfig" onclick="return callSave();" value="  Save" /></s:if>
						<s:submit cssClass="reset" action="PFParameter_resetEmpConfig" theme="simple" value="  Reset" />
	    		<s:hidden name="pfParam.pfCode"></s:hidden>
	    </td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
          </tr>
        </table>
          </td>
    </tr>
   
    <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
            <tr>
			
		<tr>
			<td width="35%" colspan="2"><label  class = "set" name="pfparam.allEmployee" id="pfparam.allEmployee" ondblclick="callShowDiv(this);"><%=label.get("pfparam.allEmployee")%></label> :</td>
			<td width="25%" colspan="1"><s:checkbox theme="simple" name="allEmpAppl" onclick="callAllEmplAppl();" /></td>
			<td width='25%' colspan="1" ></td>
			<td width='25%' colspan="1" ></td>
			<s:hidden name='empListFlag'/>
			</td>
		
		</tr>
		
		<tr id='TR1'>
							
							<s:if test="EPFflag">
							 <td width='20%' class="formth" align="center" >
								<label  class = "set" name="pfparam.epfAppl" id="pfparam.epfAppl" ondblclick="callShowDiv(this);"><%=label.get("pfparam.epfAppl")%></label>
							</td>
							</s:if>
							
							<s:if test="GPFflag">
					        <td width='20%'class="formth" align="center">
					        	<label  class = "set" name="pfparam.gpfAppl" id="pfparam.gpfAppl" ondblclick="callShowDiv(this);"><%=label.get("pfparam.gpfAppl")%></label>
					        </td>
					        </s:if>
					        
					        <s:if test="VPFflag">
					         <td width='20%'class="formth" align="center">
								<label  class = "set" name="pfparam.vpfAppl" id="pfparam.vpfAppl" ondblclick="callShowDiv(this);"><%=label.get("pfparam.vpfAppl")%></label>
							</td>
							</s:if>
							
							<s:if test="PFTflag">
					        <td width='20%'class="formth" align="center">
					        	<label  class = "set" name="pfparam.pftAppl" id="pfparam.pftAppl" ondblclick="callShowDiv(this);"><%=label.get("pfparam.pftAppl")%></label>
					        </td>
					        </s:if>
					        
					        <td width='20%'class="formth" align="center">
					        	<label  class = "set" name="pfparam.pfNotAppl" id="pfparam.pfNotAppl" ondblclick="callShowDiv(this);"><%=label.get("pfparam.pfNotAppl")%></label>
					        	
					        </td>
		 </tr>
		 <tr id='TR2'>
							<s:hidden name='allEmpEPF' value="N"/>
							<s:hidden name='allEmpGPF' value="N"/>
							<s:hidden name='allEmpVPF' value="N"/>
							<s:hidden name='allEmpPFT' value="N"/>
							<s:if test="EPFflag">
							 <td width='20%' align="center"  >
								<input type="checkbox" name='allEmpEPFCh' id='allEmpEPFCh' onclick="return allEmpEpf();"/>
							</td>
							</s:if>
							
							<s:if test="GPFflag">
					        <td width='20%' align="center">
					        	<input type="checkbox" name='allEmpGPFCh' id='allEmpGPFCh' onclick="return allEmpGpf();"/>
					        </td>
					        </s:if>
					        
					        <s:if test="VPFflag">
					         <td width='20%' align="center">
								<input type="checkbox" name='allEmpVPFCh' id='allEmpVPFCh' onclick="return allEmpVpf();"/>
							</td>
							</s:if>
							
							
							<s:if test="PFTflag">
					        <td width='20%' align="center" >
					        	<input type="checkbox" name='allEmpPFTCh' id='allEmpPFTCh' onclick="return allEmpPft();"/>
					        </td>
					        </s:if>
					        <td width='20%' align="center">
					        	<input type="checkbox" name='allEmpPFNotCh' id='allEmpPFNotCh' onclick="return allEmpPfNot();"/><s:hidden name='allEmpPFNot'/>
					        </td>
		 </tr>
		
            </table></td>
          </tr>
      </table></td>
    </tr>
    
    
    <tr id='empwiseConfigTable'>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
           				 <tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Select filters to configure employees </strong>  </td>
						</tr>
							
			
		<tr >
			<td  width="25%" id='TD1'><label  class = "set" name="pfparam.applDiv" id="pfparam.applDiv" ondblclick="callShowDiv(this);"><%=label.get("pfparam.applDiv")%></label> :</td>
			<td width="25%" id='TD2' nowrap="nowrap"><s:hidden name="applDivisionCode"></s:hidden>
			<s:textarea name="applDivisionName" cols="25" rows="2" readonly="true"></s:textarea>	
					<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_f9applDiv.action'); "></td>
			<td width='25%' colspan="1" ></td>
			<td width='25%' colspan="1" ></td>
		</tr>
		
		<tr >
			<td  width="25%"><label  class = "set" name="pfparam.applBranches" id="pfparam.applBranches" ondblclick="callShowDiv(this);"><%=label.get("pfparam.applBranches")%></label> :</td>
			<td width="25%" nowrap="nowrap"><s:hidden name="applBranchCode"></s:hidden>
			<s:textarea name="applBranchName" cols="25" rows="2" readonly="true"></s:textarea>	
					<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_f9applBranch.action'); ">
		
			</td>
			<td  width="25%"><label  class = "set" name="pfparam.applDept" id="pfparam.applDept" ondblclick="callShowDiv(this);"><%=label.get("pfparam.applDept")%></label> :</td>
			<td width="25%" nowrap="nowrap"><s:hidden name="applDeptCode"></s:hidden>
			<s:textarea name="applDeptName" cols="25" rows="2" readonly="true"></s:textarea>	
					<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_f9applDept.action'); ">
		
			</td>
		
		</tr>
		<tr >
			<td  width="25%"><label  class = "set" name="pfparam.applDesg" id="pfparam.applDesg" ondblclick="callShowDiv(this);"><%=label.get("pfparam.applDesg")%></label> :</td>
			<td width="25%" nowrap="nowrap"><s:hidden name="applDesgCode"></s:hidden>
			<s:textarea name="applDesgName" cols="25" rows="2" readonly="true"></s:textarea>	
					<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_f9applDesg.action'); ">
		
			</td>
			<td  width="25%"><label  class = "set" name="pfparam.applEType" id="pfparam.applEType" ondblclick="callShowDiv(this);"><%=label.get("pfparam.applEType")%></label> :</td>
			<td width="25%" nowrap="nowrap"><s:hidden name="applETypeCode"></s:hidden>
			<s:textarea name="applETypeName" cols="25" rows="2" readonly="true"></s:textarea>	
					<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_f9applEType.action'); ">
		
			</td>
		
		</tr>
		
		<tr >
			<td  width="25%"><label  class = "set" name="pfparam.applGrade" id="pfparam.applGrade" ondblclick="callShowDiv(this);"><%=label.get("pfparam.applGrade")%></label> :</td>
			<td width="25%" nowrap="nowrap"><s:hidden name="applGradeCode"></s:hidden>
			<s:textarea name="applGradeName" cols="25" rows="2" readonly="true"></s:textarea>	
					<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_f9applGrade.action'); ">
		
			</td>
			<td  width="25%"><label  class = "set" name="pfparam.applEmp" id="pfparam.applEmp" ondblclick="callShowDiv(this);"><%=label.get("pfparam.applEmp")%></label> :</td>
			<td width="25%" nowrap="nowrap"><s:hidden name="applEmpCode"></s:hidden>
			<s:textarea name="applEmpName" cols="25" rows="2" readonly="true"></s:textarea>	
					<img src="../pages/images/recruitment/search2.gif" height="16" align="absmiddle"
				width="16" theme="simple" onclick="javascript:callsF9(500,325,'PFParameter_f9applEmp.action'); ">
		
			</td>
		
		</tr>
				
		<tr>
		<td colspan="4" align="center"><input type="button" class="token" value='View Employee List' onclick="callViewEmp();"/>
		</tr>
		
              
            </table></td>
          </tr>
      </table></td>
    </tr>
    <s:if test="empListFlag">
       <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
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
						<td align="right" colspan="3" nowrap="nowrap"><b>Page:</b> 
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
							
							<td width='10%' class="formth" align="center"><!--Sr No.  -->
								<label  class = "set" name="pfparam.srno" id="pfparam.srno" ondblclick="callShowDiv(this);"><%=label.get("pfparam.srno")%></label>
							</td>
							<td width='20%' class="formth" align="center">
								<label  class = "set" name="employee.id" id="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label>
							</td>
					        <td width='20%' class="formth" align="center">
					        	<label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					        </td>
					        <s:if test="EPFflag">
					        <td class="formth" align="center">
								<label  class = "set" name="pfparam.epfAppl" id="pfparam.epfAppl" ondblclick="callShowDiv(this);"><%=label.get("pfparam.epfAppl")%></label>
								<input type="checkbox" name='epfAll' id='epfAll' onclick="selectAllEpf();"/>
							</td></s:if>
							<s:if test="GPFflag">
					        <td class="formth" align="center">
					        	<label  class = "set" name="pfparam.gpfAppl" id="pfparam.gpfAppl" ondblclick="callShowDiv(this);"><%=label.get("pfparam.gpfAppl")%></label>
					        	<input type="checkbox" name='gpfAll' id='gpfAll' onclick="selectAllGpf();"/>
					        </td></s:if>
					        <s:if test="VPFflag">
					         <td class="formth" align="center">
								<label  class = "set" name="pfparam.vpfAppl" id="pfparam.vpfAppl" ondblclick="callShowDiv(this);"><%=label.get("pfparam.vpfAppl")%></label>
								<input type="checkbox" name='vpfAll' id='vpfAll' onclick="selectAllVpf();"/>
							</td></s:if>
							<s:if test="PFTflag">
					        <td class="formth" align="center">
					        	<label  class = "set" name="pfparam.pftAppl" id="pfparam.pftAppl" ondblclick="callShowDiv(this);"><%=label.get("pfparam.pftAppl")%></label>
					        	<input type="checkbox" name='pftAll' id='pftAll' onclick="selectAllPft();"/>
					        </td></s:if>
					        <td class="formth" align="center">
					        	<label  class = "set" name="pfparam.pfNotAppl" id="pfparam.pfNotAppl" ondblclick="callShowDiv(this);"><%=label.get("pfparam.pfNotAppl")%></label>
					        	<input type="checkbox" name='pfNotAll' id='pfNotAll' onclick="selectAllPfNot();"/>
					        </td>
					     </tr>
					    <s:if test="noData">
					<tr>
						<td width="100%" colspan="8" align="center"><font color="red">No
						Data To Display</font></td>
					</tr>
				</s:if>
					<%int i=1; int k=0;%>
							<s:iterator value="empList">
								<%k=i; %>
								<tr>
									<td width="10%" align="center"><%=(20*(pageNo-1))+k%></td>
									<td width="20%" align="left"><s:property value="empId" /><s:hidden name='empCode' id='<%="empCode"+k%>'/><s:hidden name='empId'/></td>
									<td width="20%" align="left"><s:property value="empName" /></td>
									<s:if test="pfParam.EPFflag">
									<td align="center"><input type="checkbox" name='epfApplChk' id='<%="epfApplChk"+k%>' onclick="setCheckboxValue(<%=k%>,'epf');callEPFAppl('<%=k %>');"/><s:hidden name='epfAppl' id='<%="epfAppl"+k%>'/></td>
									</s:if>
									<s:if test="pfParam.GPFflag">
									<td align="center"><input type="checkbox" name='gpfApplChk' id='<%="gpfApplChk"+k%>' onclick="setCheckboxValue(<%=k%>,'gpf');callGPFAppl('<%=k %>');"/><s:hidden name='gpfAppl' id='<%="gpfAppl"+k%>'/></td>
									</s:if>
									<s:if test="pfParam.VPFflag">
									<td align="center"><input type="checkbox" name='vpfApplChk' id='<%="vpfApplChk"+k%>' onclick="setCheckboxValue(<%=k%>,'vpf');callVPFAppl('<%=k %>');"/><s:hidden name='vpfAppl' id='<%="vpfAppl"+k%>'/></td>
									</s:if>
									<s:if test="pfParam.PFTflag">
									<td align="center"><input type="checkbox" name='pftApplChk' id='<%="pftApplChk"+k%>' onclick="setCheckboxValue(<%=k%>,'pft');callPFTAppl('<%=k %>');"/><s:hidden name='pftAppl' id='<%="pftAppl"+k%>'/></td>
									</s:if>
									
									<td width="11%" align="center"><input type="checkbox" name='pfNotApplChk' id='<%="pfNotApplChk"+k%>' onclick="setCheckboxValue(<%=k%>,'pfNot');callPfnotAppl('<%=k %>');"/><s:hidden name='pfNotAppl' id='<%="pfNotAppl"+k%>'/></td>
								</tr>
								<%k++;i++; %>
							</s:iterator>
							<%i=1; %>
              <input type="hidden" name='count' id='count' value="<%=k-1%>"/>
              
            </table>
           
            </td>
          </tr>
          
      </table></td>
    </tr></s:if>
    
     <tr>
      <td colspan="3"><table width="100%" border="0" cellpadding="2" cellspacing="2" class="formbg">
          <tr>
            <td><table width="98%" border="0" align="center" cellpadding="2" cellspacing="2">
           
		
			<tr>
              <td colspan="1" width="25%">Configured Employees :</td>
              <td colspan="3" width="75%"><s:textfield name='confEmp' size='20' readonly="true"/></td>
              
              </tr>
              <tr>
              <td colspan="1" width="25%">Non Configured Employees :</td>
              <td colspan="3" width="75%"><s:textfield name='nonConfEmp' size='20' readonly="true"/></td>
              
              </tr>
		
              
            </table></td>
          </tr>
      </table></td>
    </tr>
    
    <tr>
      <td colspan="3">
      <table width="98%" border="0" align="center" cellpadding="1" cellspacing="1">
          <tr>
            <td width="78%">	
	    		<s:if test="%{insertFlag}"><s:submit cssClass="add" action="PFParameter_saveEmpConfig" onclick="return callSave();" value="  Save" /></s:if>
						<s:submit cssClass="reset" action="PFParameter_resetEmpConfig" theme="simple" value="  Reset"  />
	    		<s:hidden name="pfParam.pfCode"></s:hidden>
	    </td>
          </tr>
        </table>
          </td>
    </tr>
    
  </table>
  </td>
  </tr>
  </table>
  

	</s:form>

		
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">

document.getElementById('configEmpTab').className= "on";
callOnload();
function callOnload(){
		if(document.getElementById('paraFrm_allEmpAppl').checked){
			
			document.getElementById('TR1').style.display='';
			document.getElementById('TR2').style.display='';
			document.getElementById('empwiseConfigTable').style.display='none';
			
		}else{
			document.getElementById('TR1').style.display='none';
			document.getElementById('TR2').style.display='none';
			document.getElementById('empwiseConfigTable').style.display='';
		}
		setCheckboxOnload();
	}
	
	
function callSave(){
	if(!document.getElementById('paraFrm_allEmpAppl').checked){
	try{
		if(eval(document.getElementById('count').value) <0){
			alert("No employee to save");
			return false;
		}
	}catch(e){
		alert("No employee to save");
		return false;
	}
	}
}

function callAllEmplAppl(){
		if(document.getElementById('paraFrm_allEmpAppl').checked){
			document.getElementById('TR1').style.display='';
			document.getElementById('TR2').style.display='';
			document.getElementById('empwiseConfigTable').style.display='none';
			
		}else{
			document.getElementById('TR1').style.display='none';
			document.getElementById('TR2').style.display='none';
			document.getElementById('empwiseConfigTable').style.display='';
		}
		document.getElementById('paraFrm').action ="PFParameter_getConfigEmpScreen.action";
 		document.getElementById('paraFrm').submit();
}

function callViewEmp(){
	document.getElementById('paraFrm_empListFlag').value='true';
	document.getElementById('paraFrm_myPageEmpConf').value=1;
 	document.getElementById('paraFrm').action ="PFParameter_getEmployeeList.action";
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
			document.getElementById('paraFrm_myPageEmpConf').value=pageNo;
	      
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
		   
			displayConfirmDiv();
			document.getElementById('confirmationDiv').style.display='block';
		}
		
}


function setCheckboxOnload(){
	//alert("onload");
		var type = ["epf","gpf","vpf","pft","pfNot"];
		var count = eval(document.getElementById('count').value);
		
		//alert(count);
		for (var i=0;i<type.length;i++){
		var unCheckedOne =true;
		try{
			for(var rowId=1;rowId<=count;rowId++){
			if(document.getElementById(type[i]+'Appl'+rowId).value =='Y'){
				document.getElementById(type[i]+'ApplChk'+rowId).checked = true;
			}else{
				document.getElementById(type[i]+'ApplChk'+rowId).checked = false;
				unCheckedOne=false;
			}
		}
		}catch(e){}
		try{
		if(unCheckedOne && eval(count)>0){
			document.getElementById(type[i]+'All').checked = true;
		}
		}catch(e){}
		}
		checkPFnotAppl();
		
}
function checkPFnotAppl(){
	var count = eval(document.getElementById('count').value);
	var type = ["epf","gpf","vpf","pft"];
	for(var rowId=1;rowId<=count;rowId++){
	 var pfNotAppl=true;
	 for (var i=0;i<type.length;i++){
		try{
		if(document.getElementById(type[i]+'ApplChk'+rowId).checked){
			pfNotAppl =false;
			break;
		}
		}
		catch(e){}
		
	}
	document.getElementById('pfNotApplChk'+rowId).checked=pfNotAppl;
	//alert("pfAppl=="+pfNotAppl);
}
}

function setCheckboxValue(rowId,type){
	try{
		if(document.getElementById(type+'ApplChk'+rowId).checked){
			document.getElementById(type+'Appl'+rowId).value ='Y';
		}else{
			document.getElementById(type+'Appl'+rowId).value ='N';
		}
		}catch(e){}
		//alert('value=='+document.getElementById(type+'Appl'+rowId).value);
}

function callPfnotAppl(rowId){
	
		if(document.getElementById('pfNotApplChk'+rowId).checked){
		try{
			document.getElementById('epfApplChk'+rowId).checked=false;
			document.getElementById('epfAppl'+rowId).value='N';
		}catch(e){}
		
		try{
			document.getElementById('gpfApplChk'+rowId).checked=false;
			document.getElementById('gpfAppl'+rowId).value='N';
		}catch(e){}
		
		try{
			document.getElementById('vpfApplChk'+rowId).checked=false;
			document.getElementById('vpfAppl'+rowId).value='N';
		}catch(e){}
		
		try{
			document.getElementById('pftApplChk'+rowId).checked=false;
			document.getElementById('pftAppl'+rowId).value='N';
		}catch(e){}
		
		}
	}
	function callGPFAppl(rowId){
		if(document.getElementById('gpfApplChk'+rowId).checked){
						
			document.getElementById('pfNotApplChk'+rowId).checked=false;
			document.getElementById('pfNotAppl'+rowId).value='N';
			
		try{
			document.getElementById('pftApplChk'+rowId).checked=false;
			document.getElementById('pftAppl'+rowId).value='N';
		}catch(e){}
			
		}
		callAllUncheck(rowId);
	}
	function callEPFAppl(rowId){
		if(document.getElementById('epfApplChk'+rowId).checked){
		
			document.getElementById('pfNotApplChk'+rowId).checked=false;
			document.getElementById('pfNotAppl'+rowId).value='N';
			
		try{
			document.getElementById('pftApplChk'+rowId).checked=false;
			document.getElementById('pftAppl'+rowId).value='N';
		}catch(e){}
			
		}
		callAllUncheck(rowId);
	}
	function callVPFAppl(rowId){
		if(document.getElementById('vpfApplChk'+rowId).checked){
		
			document.getElementById('pfNotApplChk'+rowId).checked=false;
			document.getElementById('pfNotAppl'+rowId).value='N';
			
		try{
			document.getElementById('pftApplChk'+rowId).checked=false;
			document.getElementById('pftAppl'+rowId).value='N';
		}catch(e){}
			
		}
		callAllUncheck(rowId);
	}
	function callPFTAppl(rowId){
		if(document.getElementById('pftApplChk'+rowId).checked){
		
		try{
			document.getElementById('epfApplChk'+rowId).checked=false;
			document.getElementById('epfAppl'+rowId).value='N';
		}catch(e){}
		
		try{
			document.getElementById('gpfApplChk'+rowId).checked=false;
			document.getElementById('gpfAppl'+rowId).value='N';
		}catch(e){}
		
		try{
			document.getElementById('vpfApplChk'+rowId).checked=false;
			document.getElementById('vpfAppl'+rowId).value='N';
		}catch(e){}
			
			document.getElementById('pfNotApplChk'+rowId).checked=false;
			document.getElementById('pfNotAppl'+rowId).value='N';
			
		}
		callAllUncheck(rowId);
	}
	function callAllUncheck(rowId){
		var epfCheck="false";
		var gpfCheck="false";
		var vpfCheck="false";
		var pftCheck="false";
		try{
		epfCheck =document.getElementById('epfApplChk'+rowId).checked;
		}catch(e){}
		try{
		gpfCheck =document.getElementById('gpfApplChk'+rowId).checked;
		}catch(e){}
		try{
		vpfCheck =document.getElementById('vpfApplChk'+rowId).checked;
		}catch(e){}
		try{
		pftCheck =document.getElementById('pftApplChk'+rowId).checked;
		}catch(e){}
		
		if((!epfCheck) && (!gpfCheck) && (!vpfCheck) && (!pftCheck)){
			document.getElementById('pfNotApplChk'+rowId).checked=true;
			document.getElementById('pfNotAppl'+rowId).value='Y';
		}else{
			
		}
	}
	function selectAllEpf(){
		var checkAll= document.getElementById('epfAll').checked;
		var count =document.getElementById('count').value;
		if(checkAll){
			for(var i=1;i<=count;i++){
				document.getElementById('epfApplChk'+i).checked=true;
				document.getElementById('epfAppl'+i).value='Y';
				try{
					document.getElementById('pftApplChk'+i).checked=false;
					document.getElementById('pftAppl'+i).value='N';
					document.getElementById('pftAll').checked=false;
				}catch(e){}
				
					document.getElementById('pfNotApplChk'+i).checked=false;
					document.getElementById('pfNotAppl'+i).value='N';
					document.getElementById('pfNotAll').checked=false;
				
			}
		}else{
			for(var i=1;i<=count;i++){
				document.getElementById('epfApplChk'+i).checked=false;
				document.getElementById('epfAppl'+i).value='N';
			}
		}
		
	}
	function selectAllGpf(){
		var checkAll= document.getElementById('gpfAll').checked;
		var count =document.getElementById('count').value;
		if(checkAll){
			for(var i=1;i<=count;i++){
				document.getElementById('gpfApplChk'+i).checked=true;
				document.getElementById('gpfAppl'+i).value='Y';
				
				try{
					document.getElementById('pftApplChk'+i).checked=false;
					document.getElementById('pftAppl'+i).value='N';
					document.getElementById('pftAll').checked=false;
				}catch(e){}
				
					document.getElementById('pfNotApplChk'+i).checked=false;
					document.getElementById('pfNotAppl'+i).value='N';
					document.getElementById('pfNotAll').checked=false;
			}
		}else{
			for(var i=1;i<=count;i++){
				document.getElementById('gpfApplChk'+i).checked=false;
				document.getElementById('gpfAppl'+i).value='N';
			}
		}
		
	}
	function selectAllVpf(){
		var checkAll= document.getElementById('vpfAll').checked;
		var count =document.getElementById('count').value;
		if(checkAll){
			for(var i=1;i<=count;i++){
				document.getElementById('vpfApplChk'+i).checked=true;
				document.getElementById('vpfAppl'+i).value='Y';
				
				try{
					document.getElementById('pftApplChk'+i).checked=false;
					document.getElementById('pftAppl'+i).value='N';
					document.getElementById('pftAll').checked=false;
				}catch(e){}
				
					document.getElementById('pfNotApplChk'+i).checked=false;
					document.getElementById('pfNotAppl'+i).value='N';
					document.getElementById('pfNotAll').checked=false;
			}
		}else{
			for(var i=1;i<=count;i++){
				document.getElementById('vpfApplChk'+i).checked=false;
				document.getElementById('vpfAppl'+i).value='N';
			}
		}
		
	}
	function selectAllPft(){
		var checkAll= document.getElementById('pftAll').checked;
		var count =document.getElementById('count').value;
		if(checkAll){
			for(var i=1;i<=count;i++){
				document.getElementById('pftApplChk'+i).checked=true;
				document.getElementById('pftAppl'+i).value='Y';
				
				try{
					//alert("------------");
					document.getElementById('epfApplChk'+i).checked=false;
					document.getElementById('epfAppl'+i).value='N';
					document.getElementById('epfAll').checked=false;
				}catch(e){}
				try{
					document.getElementById('gpfApplChk'+i).checked=false;
					document.getElementById('gpfAppl'+i).value='N';
					document.getElementById('gpfAll').checked=false;
				}catch(e){}
				
				try{
					document.getElementById('vpfApplChk'+i).checked=false;
					document.getElementById('vpfAppl'+i).value='N';
					document.getElementById('vpfAll').checked=false;
				}catch(e){}
				
				document.getElementById('pfNotApplChk'+i).checked=false;
				document.getElementById('pfNotAppl'+i).value='N';
				document.getElementById('pfNotAll').checked=false;
			}
		}else{
			for(var i=1;i<=count;i++){
				document.getElementById('pftApplChk'+i).checked=false;
				document.getElementById('pftAppl'+i).value='N';
			}
		}
		
	}
	function selectAllPfNot(){
		var checkAll= document.getElementById('pfNotAll').checked;
		var count =document.getElementById('count').value;
		if(checkAll){
			for(var i=1;i<=count;i++){
				document.getElementById('pfNotApplChk'+i).checked=true;
				document.getElementById('pfNotAppl'+i).value='Y';
				try{
					document.getElementById('pftApplChk'+i).checked=false;
					document.getElementById('pftAppl'+i).value='N';
					document.getElementById('pftAll').checked=false;
				}catch(e){}
				try{
					document.getElementById('epfApplChk'+i).checked=false;
					document.getElementById('epfAppl'+i).value='N';
					document.getElementById('epfAll').checked=false;
				}catch(e){}
				try{
					document.getElementById('gpfApplChk'+i).checked=false;
					document.getElementById('gpfAppl'+i).value='N';
					document.getElementById('gpfAll').checked=false;
				}catch(e){}
				try{
					document.getElementById('vpfApplChk'+i).checked=false;
					document.getElementById('vpfAppl'+i).value='N';
					document.getElementById('vpfAll').checked=false;
				}catch(e){}
			}
		}else{
			for(var i=1;i<=count;i++){
				document.getElementById('pfNotApplChk'+i).checked=false;
				document.getElementById('pfNotAppl'+i).value='N';
			}
		}
		
	}
	
	function allEmpEpf(){
	
		if(document.getElementById('allEmpEPFCh').checked){
			document.getElementById('paraFrm_allEmpEPF').value='Y';
			
			try{
				document.getElementById('allEmpPFTCh').checked=false;
				document.getElementById('paraFrm_allEmpPFT').value='N';
			}catch(e){}
			
				document.getElementById('allEmpPFNotCh').checked=false;
				document.getElementById('paraFrm_allEmpPFNot').value='N';
			
		}else{
			document.getElementById('paraFrm_allEmpEPF').value='N';
		}	
	}
	function allEmpGpf(){
	
		if(document.getElementById('allEmpGPFCh').checked){
			document.getElementById('paraFrm_allEmpGPF').value='Y';
			
			try{
				document.getElementById('allEmpPFTCh').checked=false;
				document.getElementById('paraFrm_allEmpPFT').value='N';
			}catch(e){}
			
				document.getElementById('allEmpPFNotCh').checked=false;
				document.getElementById('paraFrm_allEmpPFNot').value='N';
			
		}else{
			document.getElementById('paraFrm_allEmpGPF').value='N';
		}	
	}
	function allEmpVpf(){
		if(document.getElementById('allEmpVPFCh').checked){
			document.getElementById('paraFrm_allEmpVPF').value='Y';
			
			try{
				document.getElementById('allEmpPFTCh').checked=false;
				document.getElementById('paraFrm_allEmpPFT').value='N';
			}catch(e){}
			
				document.getElementById('allEmpPFNotCh').checked=false;
				document.getElementById('paraFrm_allEmpPFNot').value='N';
		}else{
			document.getElementById('paraFrm_allEmpVPF').value='N';
		}	
	}
	function allEmpPft(){
		if(document.getElementById('allEmpPFTCh').checked){
			document.getElementById('paraFrm_allEmpPFT').value='Y';
			
			try{
				document.getElementById('allEmpEPFCh').checked=false;
				document.getElementById('paraFrm_allEmpEPF').value='N';
			}catch(e){}
			try{
				document.getElementById('allEmpGPFCh').checked=false;
				document.getElementById('paraFrm_allEmpGPF').value='N';
			}catch(e){}
			try{
				document.getElementById('allEmpVPFCh').checked=false;
				document.getElementById('paraFrm_allEmpVPF').value='N';
			}catch(e){}
			
			document.getElementById('allEmpPFNotCh').checked=false;
			document.getElementById('paraFrm_allEmpPFNot').value='N';
			
		}else{
			document.getElementById('paraFrm_allEmpPFT').value='N';
		}	
	}
	function allEmpPfNot(){
		if(document.getElementById('allEmpPFNotCh').checked){
			document.getElementById('paraFrm_allEmpPFNot').value='Y';
			
			try{
				document.getElementById('allEmpEPFCh').checked=false;
				document.getElementById('paraFrm_allEmpEPF').value='N';
			}catch(e){}
			try{
				document.getElementById('allEmpGPFCh').checked=false;
				document.getElementById('paraFrm_allEmpGPF').value='N';
			}catch(e){}
			try{
				document.getElementById('allEmpVPFCh').checked=false;
				document.getElementById('paraFrm_allEmpVPF').value='N';
			}catch(e){}
			try{
				document.getElementById('allEmpPFTCh').checked=false;
				document.getElementById('paraFrm_allEmpPFT').value='N';
			}catch(e){}
			
		}else{
			document.getElementById('paraFrm_allEmpPFNot').value='N';
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
		
		document.getElementById('paraFrm_myPageEmpConf').value=id;
		displayConfirmDiv();
		document.getElementById('confirmationDiv').style.display='block';
		 
	}
	
	
	function enableBlockDiv(){
	  		
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "visible";
				document.getElementById("overlay").style.display = "block";
			}
			catch(e){
			}
	  }
	  
	  
	  function disableBlockDiv(){
	  		
	  		try{
		  		
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
			catch(e){
				document.getElementById("overlay").style.visibility = "hidden";
				document.getElementById("overlay").style.display = "none";
			}
	  }	
	
	var verticalpos="fromtop"
	if (!document.layers)
	document.write('</div>');
	function JSFX_FloatTopDiv()
{
	var startX = 250,
	startY = 200;
	var ns = (navigator.appName.indexOf("Netscape") != -1);
	var d = document;
	function ml(id)
	{
		var el=d.getElementById?d.getElementById(id):d.all?d.all[id]:d.layers[id];
		if(d.layers)el.style=el;
		el.sP=function(x,y){this.style.left=x;this.style.top=y;};
		el.x = startX;
		if (verticalpos=="fromtop")
		el.y = startY;
		else{
		el.y = ns ? pageYOffset + innerHeight : parent.document.body.scrollTop + parent.document.body.clientHeight;
		el.y -= startY;
		}
		return el;
	}
	parent.frames[2].stayTopLeft=function()
	{
		if (verticalpos=="fromtop"){
		var pY = ns ? pageYOffset : parent.document.body.scrollTop;
		ftlObj.y += (pY + startY - ftlObj.y)/8;
		}
		else{
		var pY = ns ? pageYOffset + innerHeight : parent.document.body.scrollTop + parent.document.body.clientHeight;
		ftlObj.y += (pY - startY - ftlObj.y)/8;
		}
		ftlObj.sP(ftlObj.x, ftlObj.y);
		setTimeout("stayTopLeft()", 10);
	}
	ftlObj = ml("confirmationDiv");
	stayTopLeft();
}
function displayConfirmDiv(){
			document.getElementById("confirmationDiv").style.visibility = 'visible';
			 document.getElementById("confirmationDiv").innerHTML = '<table width=500 height=100 border=0 class=formbg>'
		 			+'<tr><td class="txt"><b><center>Please select anyone of the option given below</td></tr>'
		 			+'<tr><td><b><center><input type="button" value="Proceed With Save" onclick="proceedWithSave();" class="token"/>'
		 			+'&nbsp;<input type="button" class="token" value="Proceed Without Save" onclick="proceedWithoutSave();"/>'
		 			+'&nbsp;<input type="button" class="token" value="Cancel" onclick="cancel();"/></td></tr></table>';
		 	document.getElementById("overlay").style.display = "block";
		JSFX_FloatTopDiv();
}	 

function proceedWithSave(){


	try{
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	document.getElementById('confirmationDiv').style.display='none';
	document.getElementById("overlay").style.display = "none";
	enableBlockDiv();
	document.getElementById('paraFrm').action="PFParameter_proceedWithSave.action";
	document.getElementById('paraFrm').submit();
	}
	catch(e){
		//alert(e);
	}
}
function proceedWithoutSave(){
	try{
	enableBlockDiv();
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	document.getElementById('confirmationDiv').style.display='none';
	document.getElementById("overlay").style.display = "none";
	document.getElementById('paraFrm').action="PFParameter_proceedWithoutSave.action";
	document.getElementById('paraFrm').submit();
	}
	catch(e){
		//alert(e);
	}
}
function cancel(){
	document.getElementById("confirmationDiv").style.visibility = 'hidden';
	document.getElementById("overlay").style.display = "none";
	
}
</script>		
		
