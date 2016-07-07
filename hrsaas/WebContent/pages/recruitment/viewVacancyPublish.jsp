<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="VacancyPublish" validate="true" id="paraFrm" theme="simple">
<s:hidden name="headerView"/>
<s:hidden name="formAction" id="formAction"/>
<s:hidden name="statusKey" id="statusKey"/>
<s:hidden name="publishButtonFlag" id="publishButtonFlag"/>
<s:hidden name="flagVal" id="flagVal"/>
<s:hidden name="rePublishFlag"/>
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
        	<td width="100%" colspan="3">
        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
				     <tr>
					     <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
					     <td width="93%" class="txt"><strong class="text_head">Vacancy Publish</strong></td>
					     <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
				     </tr>
				 </table>
			</td>
		</tr>		
		 <tr>
			<td colspan="5">
				<table width="100%">
					<tr>
						<td width="78%" colspan="2" nowrap="nowrap">
						<s:if test="rePublishFlag"></s:if>
						<s:else><input type="button"  onclick="republishFun()"  class="token" theme="simple"
								value=" Republish " /></s:else>
							
								
							<s:if test="flag">	
								<input type="button" name="search" class="cancel"
									value="Cancel" onclick="callPublish()" />
								</s:if><s:else>	
									<input type="button" name="search" class="cancel"
									value="Cancel" onclick="callManagement()" /></s:else>
													
						</td>
						<td width="22%">
						<div align="right"><font color="red">*</font>Indicates Required</div>
						</td>
					</tr>
				</table>
					<label></label>
			</td>
		 </tr>
		  <tr>
			<td colspan="5">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"><!--Table 2-->
					<tr>
						<td width="100%"><strong>Recruitment Detail</strong></td>
					</tr>
					<tr>
						<td width="100%"></td>
					</tr>
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2"><!--Table 1-->
								<tr>
									<td width="20%" ><label  class = "set" name="reqs.code" id="reqs.code" 
			            		ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> :</td>
									<td width="25%"><s:property value="reqName" /></td>
									<td width="25%" ><label  class = "set" name="reqs.date" id="Requisition.Date" 
			            		ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label> :</td>
									<td width="25%"><s:property value="reqDate" /><s:hidden name="reqDate" /></td>
								</tr>
								<tr>
									<td width="20%" ><label  class = "set" name="position" id="position" 
			            		ondblclick="callShowDiv(this);"><%=label.get("position")%></label> :</td>
									<td width="25%"><s:property value="position"/></td>
									<td width="25%"><label  class = "set" name="noofvacan" id="noofvacan" 
			            		ondblclick="callShowDiv(this);"><%=label.get("noofvacan")%></label> :</td>
									<td width="25%"><s:property value="totalVacancy" /></td>
								</tr>
								<tr>
									<td width="20%" ><label  class = "set" name="Applied.By" id="Applied.By" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Applied.By")%></label> :</td>
									<td width="25%"><s:property value="appliedBy" /></td>
									<td width="25%" ><label  class = "set" name="required.date" id="Required.Date" 
			            		ondblclick="callShowDiv(this);"><%=label.get("required.date")%></label> :</td>
									<td width="25%"><s:property value="requiredDate" /><s:hidden name="requiredDate" /></td>
								</tr>
								<tr>
									<td width="20%" ><label  class = "set" name="hiring.mgr" id="Hiring.Manager" 
			            		ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label> :</td>
									<td width="25%"><s:property value="hiringMgr" /><s:hidden name="hiringMgr" /></td>
									<td width="25%"></td>
									<td width="25%"></td>
								</tr>
								
								<tr>
									<td width="20%">&nbsp;</td>
									<td width="25%">&nbsp;</td>
									<td width="25%">&nbsp;</td>
									<td width="25%">&nbsp;</td>
								</tr>
								
								<tr>
									<td width="20%"><strong>Recruitment Type:</strong></td>
									<td width="25%"><s:checkbox disabled="true" name="intEmployee" id="intEmployee" />&nbsp;<label  class = "set" name="Internal.Employees" id="Internal.Employees" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Internal.Employees")%></label></td>
									<td width="25%">
								<!-- 
										<s:checkbox disabled="true" name="extEmployee" id="extEmployee" />&nbsp;<label  class = "set" name="External.Employees" id="External.Employees" 
			            		ondblclick="callShowDiv(this);"><%=label.get("External.Employees")%></label>												
								 -->
								 </td>
									<td width="25%"></td>
								</tr>
								<tr>
									<td width="20%">&nbsp;</td>
									<td width="25%">&nbsp;</td>
									<td width="25%">&nbsp;</td>
									<td width="25%">&nbsp;</td>
								</tr>
						<!-- 
							<tr>
									<td width="20%"><strong>Publish To:</strong></td>
									<td width="25%"><s:checkbox disabled="true" name="copWeb" id="copWeb" />&nbsp;<label  class = "set" name="coweb" id="coweb" 
			            		ondblclick="callShowDiv(this);"><%=label.get("coweb")%></label></td>
									<td width="25%"><s:checkbox disabled="true" name="jobCons" id="jobCons"   />
								
									&nbsp;<label  class = "set" name="Job.Consultant" id="Job.Consultant" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Job.Consultant")%></label></td>
									</tr>
									<tr><td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></td>
									<td width="25%"><s:checkbox disabled="true" name="refProgram" id="refProgram" />&nbsp;<label  class = "set" name="Referral.Program" id="Referral.Program" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Referral.Program")%></label></td>
								<td width="25%"><s:checkbox name="onlinejobPort" disabled="true"  id="onjobportId"/>
									&nbsp;<label class="set"
										name="onjob.port" id="onjob.port"
										ondblclick="callShowDiv(this);"><%=label.get("onjob.port")%></label>
									</td>
								</tr>
								
						 -->	
								
								<tr>
									<td width="20%"><strong>Publish To :</strong></td>									
									<td width="25%">
										<s:checkbox disabled="true" name="jobCons" id="jobCons" /> &nbsp;
										<label class = "set" name="Job.Consultant" id="Job.Consultant" 
			            				ondblclick="callShowDiv(this);"><%=label.get("Job.Consultant")%></label>
									</td>
									
									<td width="25%">
										<s:checkbox disabled="true" name="refProgram" id="refProgram" onclick="callRefDiv();"/>&nbsp;
										<label  class = "set" name="Referral.Program" id="Referral.Program" 
			            				ondblclick="callShowDiv(this);"><%=label.get("Referral.Program")%></label>
									</td>									

									<td width="25%">
										<s:checkbox name="onlinejobPort" disabled="true"  id="onjobportId"/>&nbsp;
										<label class="set" name="onjob.port" id="onjob.port"
										ondblclick="callShowDiv(this);"><%=label.get("onjob.port")%></label>
									</td>
								</tr>
								<tr id="divShow">
									<td width="20%"><label class="set" name="refDivision"
										id="refDivision" ondblclick="callShowDiv(this);"><%=label.get("refDivision")%></label>:</td>
									<td width="35%"><s:hidden name="divCode" theme="simple" /><s:textarea
										name="divsion" theme="simple" disabled="true" cols="50"></s:textarea></td>									
								</tr>
								
							</table><!-- table 1 -->	
						</td>
					</tr>
					<tr>
						<td width="100%">&nbsp;</td>
					</tr>
     <tr><!-- Start of recruiter list -->
      <td colspan="5">
       	<table width="50%" border="0" cellpadding="0" cellspacing="0" class="formbg"  ><!-- table 7 -->
                <tr class="sortable">
                  <td >
                    <%
                    try {
                    %>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0"></td></tr>
                  	<tr><!-- Start of recruiter list -->
     
		       		<tr>
			       		<td ><strong class="formhead">Recruiter Assignment : </strong></td>
		       		</tr>
                  
                      <tr> 
                        <td colspan="5">
                      
                       <table width="100%" class="sortable" border="0" id="tblRecruiter"><!-- table 8 -->
                           <tr>
								<td width="15%"   valign="top" class="formth" nowrap="nowrap"><label class="set" name="serial.no" id="serial.no"
ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
								<td width="60%" valign="top" class="formth" align="center"><label  class = "set" name="Recruiter.Name" id="Recruiter.Name" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Recruiter.Name")%></label></td>
								<td width="25%" valign="top" class="formth" align="center"><label  class = "set" name="asgvacan" id="asgvacan" 
			            		ondblclick="callShowDiv(this);"><%=label.get("asgvacan")%></label></td>
								
								
							</td>
															
							</tr>
							<%!int s = 0;%>
							
							  <%
														  int j = 0;
														  %>
							  
                               <s:iterator value="recruiterList" >
                               
                                  <tr class="border2">
	                                   <td width="15%"  class="sortableTD"><%=++j%></td>
			   							<td  class="sortableTD" width="60%"><s:property value="skillName" /> &nbsp;</td>
                                 
	                                   
			   						     
		    			 				<td width="25%"  class="sortableTD"><s:hidden  value="skillId" /><s:property value="skillExp" />&nbsp;</td>
                                </tr>
                          </s:iterator>
                       </table></td>
                      </tr>
                      <tr>
            </table>
            <%
            		} catch (Exception e) {

            		e.printStackTrace();

            	}
            %>
            
            </td>
          </tr>
          <tr>
            <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          </tr>
      </table>
          
      </td>
    </tr><!-- end of recruiter list -->
    
    
    
    <tr id="jobConsultFlag"><!-- Start of consultant list -->
      <td colspan="5">
       	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"  ><!-- table 7 -->
                <tr class="sortable">
                  <td >
                    <%
                    try {
                    %>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0"></td></tr>
                      <tr> 
                      <tr>
			       		<td ><strong class="formhead">Consultant List : </strong></td>
		       		</tr>
                        <td colspan="5">
                      
                       <table border="0" id="tblConsultant"><!-- table 8 -->
                           <tr>
								<td width="5%" valign="top" class="formth" nowrap="nowrap"><label class="set" name="serial.no" id="serial.no1"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>

								<td width="30%" valign="top" class="formth" align="center"><label  class = "set" name="Consultant.Name" id="Consultant.Name" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Consultant.Name")%></label></td>
			            		
								<td width="15%" valign="top" class="formth" align="center"><label  class = "set" name="City" id="City" 
			            		ondblclick="callShowDiv(this);"><%=label.get("City")%></label></td>
			            		
								<td width="15%" valign="top" class="formth" align="center"><label  class = "set" name="Phone" id="Phone" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Phone")%></label></td>
			            		
								<td width="20%" valign="top" class="formth" align="center"><label  class = "set" name="Email.Address" id="Email.Address" 
			            		ondblclick="callShowDiv(this);"><%=label.get("Email.Address")%></label></td>
			            		
								<td width="15%" valign="top" class="formth" align="center"><label  class = "set" name="asgconsult" id="asgconsult" 
			            		ondblclick="callShowDiv(this);"><%=label.get("asgconsult")%></label></td>
							
															
							</tr>
							<%!int c = 0;%>
							
							  <%
														  int j = 0;
														  %>
							  
                               <s:iterator value="consultantList" >
                               
                                  <tr class="border2">
	                                   <td width="5%"  class="sortableTD" ><%=++j%>&nbsp;</td>
			   							<td   class="sortableTD" width="30%"><s:property value="consultantName" />&nbsp; </td>
		    			 				<td width="15%"  class="sortableTD" class="formth" ><s:property value="cityName" />&nbsp;<s:hidden name="city" /></td>
		    			 				<td width="15%"  class="sortableTD" ><s:property  value="phoneNo"/>&nbsp;</td>
		    			 				<td width="20%"  class="sortableTD" ><s:property value="emailAdd" />&nbsp;</td>
		    			 				<td width="15%"  class="sortableTD" ><s:property  value="consultantVacancies" />&nbsp;</td>

                  
                  
                                </tr>
                          </s:iterator>
                       </table></td>
                      </tr>
                      <tr>
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
    </tr><!-- end of recruiter list -->
    
    	  
		<tr><!-- Start of consultant list -->
     	   <td colspan="5">
       			<table width="100%" border="0" cellpadding="0" cellspacing="0" ><!-- table 7 -->
       				<tr>

       				

       					<td width="21%"><label  class = "set" name="Comments" id="Comments" 

			            		ondblclick="callShowDiv(this);"><%=label.get("Comments")%></label> :</td>
       					<td  width="79%"><s:property  value="comments" />
						</td>
					</tr>
					<tr>
		           	 <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
		          	</tr>
					
					<tr>
						
						<td align="left" nowrap="nowrap">
							<s:if test="rePublishFlag"></s:if>
						<s:else><input type="button"  onclick="republishFun()"  class="token" theme="simple"
								value=" Republish " /></s:else>
							<input type="button" name="search" class="cancel"
									value="Cancel" onclick="callManagement()" />				
						</td>
					</tr>					
       			</table>
       		</td>	
       	</tr>
    
				</table><!--Table 2-->
			</td>
		</tr>
	</table><!-- Final table -->
		<s:hidden name="rowId" />
		<s:hidden name="vacanDtlCode"/>
		<s:hidden name="reqCode"/>
		<s:hidden name="appEmpId"/>
		<s:hidden name="hiringEmpId"/>
			<s:hidden name="myPage" id="myPage" />
		
</s:form>
<script>

function callPublish(){
 
 if(document.getElementById('statusKey').value=="" || document.getElementById('statusKey').value=="null"){
	 document.getElementById("paraFrm").action="VacancyManagement_backPublish.action";
 }else{
  document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value+'&flag='+document.getElementById('flagVal').value;
 }
  document.getElementById("paraFrm").submit();
}

function callManagement(){

		
		 if(document.getElementById('statusKey').value=="" || document.getElementById('statusKey').value=="null"){
		 	document.getElementById("paraFrm").action="VacancyManagement_backPublish.action";
		 }else{
		 	document.getElementById('paraFrm').action = document.getElementById('formAction').value+'?status='+document.getElementById('statusKey').value+'&flag='+document.getElementById('flagVal').value;
	    
	    }
	     document.getElementById("paraFrm").submit();
	    	//document.getElementById("paraFrm").action="VacancyManagement_backPublish.action"; //?code='+reqCode+'&position='+position+'&appliedBy='+appBy+'&hiringMgr='+hirManager+'&requiDate='+requiDt+'&noOfVacan='+noVacancy+'&reqDate='+reqDate+'&vacanDtlCode='+vacanDtlCode;
	 	
	}
  chechboxFlag();
function chechboxFlag(){
	
	if(document.getElementById('jobCons').checked == true)
	{
	
	document.getElementById('jobConsultFlag').style.display='';
	}else{
	
	document.getElementById('jobConsultFlag').style.display='none';
	}
}

function republishFun()
{

 		var conf=confirm("Do you really want to republish this vacancy ?");
  			if(conf) 
  			{
  				document.getElementById('paraFrm').action="VacancyPublish_rePublish.action";
				document.getElementById('paraFrm').submit(); 
  				return true;
  			}
	  		else
	  		{
	  			 return false;
	  		}
	  		return false;
	  		
}

function callRefDiv(){
	if(document.getElementById('refProgram').checked== true){
	  document.getElementById('divShow').style.display ='';
	}
	else{
		document.getElementById('divShow').style.display ='none';
	}
}
callRefDiv();
</script>