<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="SuggestionApproval" validate="true" id="paraFrm"
	theme="simple">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head"> Suggestion Approval </strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	

	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" >
      <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td colspan="2"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
                <tr>
                  <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                     <tr>
                        <td height="27" class="formtxt"><a href="SuggestionApproval_ckeckdata.action?status=P">
                        	Pending List</a> |  <a href="SuggestionApproval_ckeckdata.action?status=A">
                        	Approved List</a> | <a href="SuggestionApproval_ckeckdata.action?status=R"> Rejected List</a>

                        	 <s:hidden name="outAppStatus"/></td>
                      </tr>
                  </table></td>
                </tr>
              </table></td>
            </tr>
            <tr>
              <td width="78%">
               <img src="../pages/images/recruitment/space.gif" width="5" height="4" /><br />
	              <s:if test="%{apprflag}"></s:if>
	              <s:elseif test="%{insertFlag}">
		             
		                <s:submit cssClass="save" value="    Save " action="SuggestionApproval_save" onclick="return saveValid()"/>
	               </s:elseif>
              <input name="Submit3" type="button" class="reset" value="    Reset" onclick="return callReset();"/></td>
              <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
            </tr>
          </table>            
          <label></label></td>
        </tr>
        <tr>
          <td colspan="3"><img src="../pages/images/recruitment/space.gif" width="5" height="4" /></td>
        </tr>
        <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
              <tr>
                <td><table width="99%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td height="27" class="formtxt">
                    	<strong><%String status = (String)request.getAttribute("stat");
	                    	if(status!=null){out.println(status);}
	                    	else{out.println("Select Status");}%></strong></td>
                  </tr>
                  <tr>
                    <td>
                    <table width="100%" border="0" cellpadding="1" cellspacing="1"   class="sortable">
                        <tr>

                          <td width="13%" height="22" valign="top" class="formth" nowrap="nowrap"><label  class = "set" name="employee.id" id="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></td>
                          <td width="25%" valign="top" class="formth"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label></td>
                          <td width="15%" valign="top" class="formth" align="center"><label  class = "set" name="date" id="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label></td>
                         <!--   
                         <td width="15%" valign="top" class="formth">Suggestion</td>
                          -->
                          <td width="10%" valign="top" class="formth"><label  class = "set" name="status" id="status" ondblclick="callShowDiv(this);"><%=label.get("status")%></label></td>
						  <td width="10%" valign="top" class="formth"><label  class = "set" name="view" id="view" ondblclick="callShowDiv(this);"><%=label.get("view")%></label></td>
                          <td width="25%" valign="top" class="formth"><label  class = "set" name="remarks" id="remarks" ondblclick="callShowDiv(this);"><%=label.get("remarks")%></label></td>
                          <td>&nbsp;</td>
                        </tr>
                        
                        <s:if test="noData">
	                        <tr>
	                        	<td width="100%" colspan="7" align="center"><font color="red">No Data To Display</font></td>
	                        </tr>
	                    </s:if>
                        
                         <%! int i =0; %>
	       				<%int k = 1; int c=0;%>
	       				
                        <s:iterator value="list" status="stat">
                        <!--  outdate suggestionDate outLoc suggestion-->
	                        
	                        <tr class="border2">
								
									<td class="sortabletd" width="13%"><s:property
									value="etoken" />
								<s:hidden name="suggcode"></s:hidden>
								<s:hidden name="level" />
								<s:hidden name="suggestion" />
								</td>
								<td class="sortabletd" width="25%"><s:property
									value="ename" /><s:hidden name="ecode" /><s:hidden
									name="eApprCode" /></td>
								<td class="sortabletd" width="15%" nowrap="nowrap"><s:property
									value="suggestionDate" /></td>
									
									<td class="sortabletd" width="10%"><s:select
									name="checkStatus"
									list="#{'P':'Pending','A':'Approved','R':'Rejected'}"
									id='<%="check"+k %>' cssStyle="width:100" theme="simple" /></td>
								<td class="sortabletd" width="10%" align="center"><input
									type="button" name="view_Details" class="pagebutton"
									value="View Details"
									onclick="viewDetails('<s:property value="suggcode"/>')" /> <s:hidden
									name="etoken"></s:hidden></td>
								
								<td class="sortabletd" width="25%">
								<s:if test="approval.apprflag">
								 <textarea name="comments" id="<%="comments"+c%>" rows="2" readonly="true"
										cols="30">
										<s:property value="comments" /></textarea>

						</s:if> <s:else>
									<s:textarea name="comments" id='<%="comments"+c%>' rows="2"
										cols="30"></s:textarea>
								</s:else> 
								</td>
								<td>
								<s:if test="approval.apprflag">
								<img src="../pages/images/zoomin.gif" height="12"
										align="absmiddle" width="12" theme="simple"
										onclick="javascript:callWindow('<%="comments"+c%>','remarks','readonly','','900');">
									</s:if><s:else>
									<img src="../pages/images/zoomin.gif" height="12"
										align="absmiddle" width="12" theme="simple"
										onclick="javascript:callWindow('<%="comments"+c%>','remarks','','','900');">
									
									</s:else>
									</td>
								
	                        </tr>
	                        <%k++; c++;%>
                        
                        </s:iterator>
                      
                        <%i=k; %>
                    </table>
                    </td>
                  </tr>
                  <s:hidden name="suggdupCode" ></s:hidden>
                  
                  
                  
              <tr>
							<td>&nbsp;
							<s:hidden  name="count"  value="<%=String.valueOf(c)%>"/>
							<!--  id="count" -->
							</td>
						</tr>
                  
                  
                
                </table>
                </td>
              </tr>
            </table></td>
        </tr>
        
        </table>
        </td></tr>
       
  </table>
  <script language="JavaScript" type="text/javascript" src="../pages/common/include/javascript/sorttable.js"></script>
  </s:form>
  
<script>
	callOnload();
	function callOnload(){
		var check = <%=i%>;
		var value = document.getElementById('paraFrm_outAppStatus').value;
		if(value=="A" || value == "R"){
			for(var k = 1;k < check ;k++ ){
				document.getElementById("check"+k).disabled=true;	
			}
		}
	}		

	function saveValid(){
	try{
		var value1 = document.getElementById('paraFrm_outAppStatus').value;
		var invCount="0";
		var length=document.getElementById("paraFrm_count").value;

		if(document.getElementById("paraFrm_count").value==0){
			alert("There is no record to save");
			return false;
		}

		for (ii=1; ii <= length;ii++){
			if(document.getElementById("check"+ii).value=="P"){
				invCount =eval(invCount)+eval(1);
			}
		}
		if(invCount==length){
		alert("Please change the status of atleast one application!");
		return false;
		}
		
		
		for(var i=0;i<length;i++)
		{
		
		 var filedlength=document.getElementById('comments'+i).value;
		  
		  if(filedlength !="" && filedlength.length>900)
		  {
		   alert("Maximum length of "+document.getElementById('remarks').innerHTML.toLowerCase()+" is less than 900 characters.");
		   document.getElementById('comments'+i).focus();
		   return false;
		  }
      }
		
	
		var value = document.getElementById('paraFrm_outAppStatus').value;
		if(value=="A" || value == "R"){
			for(var k = 1;k < check ;k++ ){
				document.getElementById("check"+k).disabled=true;	
			}
		}
		
		}catch(e)
		{
		// alert("eeee "+e);
		}
		//alert("end of Validation...!!");
		return true;
	}
	
	function viewDetails(suggcode){
		document.getElementById('paraFrm_suggdupCode').value =suggcode;
		document.getElementById('paraFrm').target = '_blank';
		document.getElementById('paraFrm').action = "Suggestion_retriveForApproval.action";
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = "main";
	}
	
	function callReset(){
	    document.getElementById('paraFrm_outAppStatus').value="P";
		document.getElementById('paraFrm').action = "SuggestionApproval_input.action";
		document.getElementById('paraFrm').submit();
	}
	
	
	
	
</script>
