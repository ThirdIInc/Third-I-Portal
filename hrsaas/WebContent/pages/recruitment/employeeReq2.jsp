<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="EmployeeRequi" validate="true" id="paraFrm"  validate="true" theme="simple">

	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" >
        <tr>
          <td colspan="3" valign="bottom" class="txt">&nbsp;</td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" background="../pages/common/css/default/images/lines.gif" class="txt"><img src="../pages/common/css/default/images/lines.gif" width="16" height="1" /></td>
        </tr>
        <tr>
          <td colspan="3" valign="bottom" class="txt"><img src="../pages/common/css/default/images/space.gif" width="5" height="5" /></td>
        </tr>
        <tr>
          <td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
          <td width="93%" class="txt"><strong class="text_head">Manpower Requisition </strong></td>
          <td width="3%" valign="top" class="txt"><div align="right"><img src="../pages/common/css/default/images/help.gif" width="16" height="16" /></div></td>
        </tr>
        <tr>
          <td height="5" colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
        </tr>
        <tr>
      
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td width="78%">
               <s:if test="req.insertFlag">
              <s:submit cssClass="token" value="    Previous " action="EmployeeRequi_back"  onclick="return savevalidation();" />
               	</s:if>
	  		   	<s:if test="req.updateFlag">
	  		   	<s:submit cssClass="token" value="    Save and Previous " action="EmployeeRequi_get"  onclick="return updatevalidation();" /></s:if>
			   
               <s:if test="req.deleteFlag">
               <s:submit  cssClass="token"   value="    Save and Finish" action="EmployeeRequi_delete" onclick="return valDelete();"/>
              </s:if>
					<s:submit  action="EmployeeRequi_reset"  cssClass="reset" value="    Reset" />  
            
            
							
							
							</td>
              <td width="22%"><div align="right"><span class="style2">*</span> Indicates Required </div></td>
            </tr>
          </table>            <label></label></td>
        </tr>
        <tr>
          <td colspan="3"><img src="../pages/common/css/default/images/space.gif" width="5" height="4" /></td>
        </tr>
        <tr>
          <td colspan="3">
            <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
        </tr>
        <tr>
          <td colspan="3"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
    <tr>
      <td><table width="98%" border="0" align="center" cellpadding="0" cellspacing="2" >
          
          <tr><strong class="formhead">Competency Details :</strong>
        
            <td class="formtext" >
            
            
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"  >
                <tr class="sortable">
                
                
                  <td >
                  
                    <% 
                        try{
                        	%>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0"></td></tr>
                      
                      <tr> <strong class="formhead">Qualification Details : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      
                      <input type="button" class="add" value="  Add Row"  onclick="return addRowToTableQualification();"/>
                      <input type="button" class="delete" value="  Delete"  onclick="return deleteQualification();"/>
			 </strong>
                        <td colspan="8">
                      
                        <table id="tblQuali">
                           <tr>
								<td width="10%" valign="top" class="formth" nowrap="nowrap">Sr No.</td>
								<td width="20%" valign="top" class="formth" align="right">Qualification Type</td>
								<td width="20%" valign="top" class="formth" align="right">Qualification Name</td>
								<td width="20%" valign="top" class="formth" align="right">Select</td>
								<td width="20%" valign="top" class="formth" align="right">Qualification Level</td>
								<td width="15%" valign="top" class="formth" align="center">Specialization</td>
								<td width="20%" valign="top" class="formth" align="right">Select</td>
								<td width="12%" valign="top" class="formth" align="right">Cut Off Marks</td>
								<td width="15%" valign="top" class="formth" align="right">Select</td>
								<td width="12%" valign="top" class="formth" abbr="right">
								<input class="classcheck" 
									style="text-align: center; border: none; margin: 1px"
									type="checkbox" size="2" name="chkEmpAll" id="chkEmpAll" onclick="return callForQualiAll();">
								
								
						</td><td width="0%" class="formth" abbr="right"></td><td width="0%" class="formth" abbr="right"></td><td width="0%" class="formth" abbr="right"></td>
															
							</tr>
							<%!int v = 0;%>
							 <%int i=0; %>
							  <%int jj=0; %>
							   
                         <s:if test="quaFlag">
                               <s:iterator value="qualList" >
                               
                                  <tr class="border2">
                                   <td width="3%" class="border2"><%=++i%></td>
                                   <td width="15%" class="border2" align="right"><s:select name="hqualType"  cssStyle="width:75" theme="simple" list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td>
		   									  
	      	   		                     
	      	   		                        <%
	      	   		                               
	      	   		                               String quaId = (String)request.getAttribute("paraFrm_qualificationId"+i);   
		   											String qualName= (String)request.getAttribute("paraFrm_qualificationName"+i);
		   											String lvl=(String)request.getAttribute("paraFrm_hqualiLevelName"+i);
		   											String spl=(String)request.getAttribute("paraFrm_hsplName"+i);
		   											String splId=(String)request.getAttribute("paraFrm_hsplId"+i);
		   										
		   											
	      	   		                               %>
		   							<td nowrap="nowrap" width="20%">
		   							 <s:textfield name ="<%="paraFrm_qualificationName" +i%>"   value="<%=qualName %>"  readonly="true"  size="20" /> 
		   						
					  					</td><td><img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
	      	   		                             onclick = "callQualification('<s:property value="<%=""+i%>"/>')"> </td>
		   							
		   							
		   							
                                    <td width="12%" class="border2" align="right">
                                     <s:textfield name ="<%="paraFrm_hqualiLevelName" +i%>"   value="<%=lvl %>"  readonly="true" size="12"   /></td>
                                     
                                     
                                     
                                     
                                     <td width="20%" class="border2" align="right" nowrap="nowrap">
                                      <s:textfield name ="<%="paraFrm_hsplName" +i%>"   value="<%=spl %>"  readonly="true"  size="20" /> </td>
                                                     
                                    <td width="10%" class="border2" align="right" nowrap="nowrap">
                                     <img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" 
	      	   		                             onclick = "callSpecialization('<s:property value="<%=""+i%>"/>')">
                                   </td>                            
                                     
                                  
                                      
	    			 				<td width="10%" class="border2" align="right"><input type="text" name="hcutOff"  value='<s:property value="hcutOff" />' size="12"/></td>
												
									<td width="12%" class="border2" align="left"><s:select name="sel" cssStyle="width:50" theme="simple" list="#{'':'','A':'And','R':'Or'}" /></td>
                     				
                   					 <td width="15%" class="border2">        
                    					<input type="checkbox" class="checkbox" value="N" name="paraFrm_quaChk<%=i%>" id="paraFrm_quaChk<%=i%>"  onclick="callForQuali('<%=i%>')" />
                    
                 
                             
                                  </td>
                                <td>  <input type="hidden" name="<%="paraFrm_hdeleteQuali"+i%>"	id="paraFrm_hdeleteQuali<%=i%>"  /></td>
                                
                                
                                
                                 <td width="0%">
										<input type="hidden" name="<%="paraFrm_qualificationId"+i%>"  id="paraFrm_qualificationId<%=i%>"   />
							    </td>
							    
							    
							     <td width="0%">
										<input type="hidden" name="<%="paraFrm_hsplId"+i%>"  id="paraFrm_hsplId<%=i%>"   />
							    </td>
							    		
							    		
                                     
                                </tr>
                            
                           
                          </s:iterator>
                               </s:if><s:else>
                          
                          
                             	<tr class="border2">
	                                   <td width="5%" class="border2"><%=++i%></td>
	                                   <td width="15%" class="border2" align="right"><s:select name="hqualType" 
			   							cssStyle="width:75" theme="simple" list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td>
			   							  	   		                     
		      	   		                      
			   							<td nowrap="nowrap" width="20%"><s:textfield name ="paraFrm_qualificationName1"   readonly="true"  size="20" /> </td>
			   							
                                   
			   						<td width="10"><img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" onclick = "callQualification('<s:property value="<%=""+i%>"/>')"> </td>
			   						  <td width="12%" class="border2" align="right">
                                     <s:textfield name ="paraFrm_hqualiLevelName1"    readonly="true" size="12"   /></td>
                                  
                                   	  <td width="12%" class="border2" align="right">
                                      <s:textfield name ="paraFrm_hsplName1"    readonly="true" size="20"   /></td>
                                      
                                    
                                   		<td width="10"><img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" onclick = "callSpecialization('<s:property value="<%=""+i%>"/>')"> </td>
                                     
                                     <td width="12%" class="border2" align="right">
                                     <s:textfield name ="hcutOff"    readonly="false" size="12"   /></td>
                                     <td width="12%" class="border2" align="left"><s:select name="sel" cssStyle="width:50" theme="simple" list="#{'':'','A':'And','R':'Or'}" /></td>
                                     
                                      <td width="15%" class="border2">        
                   						 <input type="checkbox" class="checkbox" value="N" name="paraFrm_quaChk1" id="paraFrm_quaChk1"  onclick="callForQuali('<%=i%>')" />
                                       </td>
                                       <td width="0%">
							    
											<input type="hidden" name="paraFrm_hdeleteQuali1" id="paraFrm_hdeleteQuali1"  />
							    		</td>
							    		
							    		 <td width="0%">
										<input type="hidden" name="paraFrm_qualificationId1" id="paraFrm_qualificationId1"   />
							    		</td>
							    		
							    		
                						 <td width="0%">
										<input type="hidden" name="paraFrm_hsplId1" id="paraFrm_hsplId1"   />
							    		</td>
                                     
                                     
                                     
                                  
                                 
                  
                                </tr>
                          
                          
                          
                          
                          
                          
                          </s:else>
                        
                       </table></td>
                      </tr>
                      
                      <tr><td>  <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                      
                      </tr>
                      
                       <tr><td>  <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                      
                      </tr>
                      
                      
                      
                
                
                
                
                
                
            </table>
            <%
            }catch(Exception e){
            
            e.printStackTrace();
            
            } %>
            
            </td>
          </tr>
          <tr>
            <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          </tr>
      </table>
      </td>
      </tr>
      <tr>
      
      <td>  <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
      
      
      </tr>
      
      <tr>
      
      <td>  <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
      
      
      </tr>
      
      <tr>
      
      
      <td colspan="6">
      
      
      
      
       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"  >
                <tr class="sortable">
                
                
                  <td >
                  
                    <% 
                        try{
                        	%>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0"></td></tr>
                      
                      <tr> <strong class="formhead">Skill Details : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      
                      <input type="button" class="add" value="  Add Row"  onclick="addRowToTableSkill();"/>
                      <input type="button" class="delete" value="  Delete"  onclick="return deleteSkill();"/>
			 </strong>
                        <td colspan="8">
                      
                       <table border="0" id="tblSkill">
                           <tr>
								<td width="5%" valign="top" class="formth" nowrap="nowrap">Sr No.</td>
								<td width="15%" valign="top" class="formth" align="center">Skill Type</td>
								<td width="20%" valign="top" class="formth" align="center">Skill Name</td>
								<td width="15%" valign="top" class="formth" align="left">Select</td>
								<td width="15%" valign="top" class="formth" align="center">Experience</td>
								<td width="15%" valign="top" class="formth" align="center">Select</td>
								<td width="12%" valign="top" class="formth" abbr="right">
								<input class="classcheck" 
									style="text-align: center; border: none; margin: 1px"
									type="checkbox" size="2" name="chkSkillAll" id="chkSkillAll" onclick="return callForSkillAll();">
								<td width="0"></td><td width="0"></td>
								
							</td>
															
							</tr>
							<%! int s=0; %>
							
							  <%int j=0; %>
							  
                         		<s:if test="skill">
                               <s:iterator value="skillList" >
                               
                                  <tr class="border2">
	                                   <td width="5%" class="border2"><%=++j%><s:hidden  name="hssrlNo" /></td>
	                                   <td width="15%" class="border2" align="right"><s:select name="skillType" id="<%="skillType"+j %>"
			   							cssStyle="width:75" theme="simple" list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td>
			   									  
		      	   		                     
		      	   		                        <%
		      	   		                              
		      	   		                                String si = (String)request.getAttribute("paraFrm_skillId"+j);   
			   											String sn=(String)request.getAttribute("paraFrm_skillName"+j);
			   											
			   											
		      	   		                               %>
			   							<td nowrap="nowrap" width="20%"><s:textfield name ="<%="paraFrm_skillName"+j%>"    value="<%=sn%>" readonly="true"  size="35" /> </td>
			   							<td><img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" onclick = "callSkill('<s:property value="<%=""+j%>"/>')"> </td>
			   						     
		    			 				<td width="10%" class="border2" align="right"><input type="text" name="skillExp"  value='<s:property value="skillExp" />' size="20"/></td>
										<td width="12%" class="border2" align="left"><s:select name="skillSel" cssStyle="width:65" theme="simple" list="#{'A':'And','R':'Or'}" /></td>
	              
	                   					  <input type="hidden" name="<%="hdeleteSkill"+j%>"	id="hdeleteSkill<%=j%>"  />
	                    				<td width="15%" class="border2" ><input type="checkbox" class="checkbox" value="N" name=<%="confChkSkill"+j%> id="confChkSkill<%=j%>"  onclick="callForSkill('<%=j%>')" /></td>
	                     				<td width="0"><input type="hidden" name="<%="hdeleteSkill"+j%>"	id="hdeleteSkill<%=j%>"  /></td>
	                       				<td width="0"><s:hidden name ="<%="skillId"+j%>" value="<%=si%>" /></td>
                  
                  
                                </tr>
                            
                           
                          </s:iterator>
                            
                        
                          </s:if><s:else>
                          
                          
                             <tr class="border2">
	                                   <td width="5%" class="border2"><%=++j%></td>
	                                   <td width="15%" class="border2" align="right"><s:select name="skillType" id="<%="skillType"+j %>"
			   							cssStyle="width:75" theme="simple" list="#{'':'Select','E':'Essential','D':'Desirable'}" /></td>
			   							  	   		                     
		      	   		                      
			   							<td nowrap="nowrap" width="20%"><s:textfield name ="paraFrm_skillName1"   readonly="true"  size="35" /> </td>
			   						<td width="10"><img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple" onclick = "callSkill('<s:property value="<%=""+j%>"/>')"> </td>
			   						     
		    			 				<td width="10%" class="border2" align="right"><input type="text" name="skillExp"  value='<s:property value="skillExp" />' size="20"/></td>
										<td width="12%" class="border2" align="left" ><s:select name="skillSel" cssStyle="width:55" theme="simple" list="#{'':'','A':'And','R':'Or'}" /></td>
	              
	                   					  <input type="hidden" name="<%="hdeleteSkill"+j%>"	id="hdeleteSkill<%=j%>"  />
	                    				<td width="15%" class="border2"><input type="checkbox" class="checkbox" value="N" name="confChkSkill" id="confChkSkill<%=j%>"  onclick="callForSkill('<%=j%>')" /></td>
	                     		<td width="0%">
									<input type="hidden" name="paraFrm_skillId1" id="paraFrm_skillId1"   />
							    </td>
							  <td width="0%">
							    
									<input type="hidden" name="hdeleteSkill1" id="hdeleteSkill1"  />
							    </td>
                  
                                </tr>
                          
                          
                          
                          
                          
                          
                          </s:else>
                              
                        
                       </table></td>
                      </tr>
                      
                      <tr><td>  <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                      
                      </tr>
                      
                       <tr><td>  <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                      
                      </tr>
                            
            </table>
            <%
            }catch(Exception e){
            
            e.printStackTrace();
            
            } %>
            
            </td>
          </tr>
          <tr>
            <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          </tr>
      </table>
          
      </td>
    </tr>
    
    
     <tr>
      
      <td>  <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
      
      
      </tr>
      
      <tr>
      
      <td>  <img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
      
      
      </tr>
      
      <tr>
      
      
      <td colspan="6">
      
      
      
      
       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"  >
                <tr>
                <% int m=0,n=1; %>
                
                  <td >
                  
                    <% 
                        try{
                        	%>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0"></td></tr>
                      
                      <tr> <strong class="formhead">Domain/Functional Details : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      
                      <input type="button" class="add" value="  Add Row"  onclick="addRowToTableDomain();"/>
                      <input type="button" class="delete" value="  Delete"  onclick="deleteDomain();"/>
                    
			 </strong>
                        <td colspan="6">
                        
                        <table border="1" id="tblDom">
                         <% int d=0; %>
							  <tr>
							 
							    
							    
							    <td width="5%" valign="top" class="formth" nowrap="nowrap">Srl No.</td>
							    <td width="15%" valign="top" class="formth" nowrap="nowrap">Domain Type</td>
							    <td width="15%" valign="top" class="formth" nowrap="nowrap">Domain Name</td>
							    <td width="5%" valign="top" class="formth" nowrap="nowrap">Select</td>
							    <td width="15%" valign="top" class="formth" nowrap="nowrap">Experience</td>
							    <td width="15%" valign="top" class="formth" nowrap="nowrap">Select</td>
							    <td width="15%" valign="top" class="formth" nowrap="nowrap"><input type="checkbox" class="checkbox" value="N" name="confChkDom" id="confChkDom"  onclick="clickAllDom()" /></td>
							    <td width="0%"></td><td width="0%"></td>
							    
							  </tr>
							  
							  <s:if test="%{domFlag}">
							    
							  <s:iterator value="domList" >
							    <tr class="border2">
							    <td width="5%" class="border2"><%=++m %></td>
							    <%
		      	   		                               
		      	   		                               
			   											String domain= (String)request.getAttribute("domName"+m);
							    						String code=(String)request.getAttribute("domId"+m);
						
										   										
			   											
		      	   		                               %>
							     <td width="15%" class="border2">
							     
							     <s:select name="domType" 
			   							cssStyle="width:75" theme="simple" list="#{'':'Select','E':'Essential','D':'Desirable'}" />
							     
							     
							     
							     <!--
							    <select name="domType">
							    <option value="">Select</option>
							    <option value="E">Essential</option>
							    <option value="D">Desirable</option>
							    </select>
							    --></td>
							    <td width="15%" class="border2">
							<s:textfield name ="<%="domName" +m%>"   value="<%=domain %>"  readonly="true"  size="35" /> 
							    </td>
							     
							        
							     <td>
							     <img src="../pages/images/search2.gif" height="16" align="absmiddle" width="12" theme="simple"    onclick = "callDom('<s:property value="<%=""+m%>"/>')">
							     
							     
							     </td>
							   
							     <td width="5%" class="border2"><s:textfield name ="domExp"      size="20" />
							     </td>
							     
							    <td width="15%" class="border2">
							    
							    <s:select name="domSel" 
			   							cssStyle="width:50" theme="simple" list="#{'':'','A':'And','O':'Or'}" />
							    
							    
							    
							    
							    <!--
							    <select name="domSel">
							    <option value="">   </option>
							    <option value="A">And</option>
							    <option value="O">Or</option>
							    </select>
							    --></td>
							    
							    
							     <td width="15%" class="border2">
							<input type="checkbox" class="checkbox" value="N" name="<%="domChk"+m%>" id="domChk<%=m%>"  onclick="callForDom('<s:property value="<%=""+m%>"/>')" />
							    </td><td width="0%" class="border2">
							    <input type="hidden" name="<%="hdeleteDom"+m%>"	id="hdeleteDom<%=m%>"  />
							    
							    </td><td width="0%" class="border2">
							    
									<input type="hidden" name="<%="domId"+m%>" id="paraFrm_domId<%=m%>" value="<%=code%>"  />
							    </td>
							  
							   
							  </tr>
							 </s:iterator>
							
							 </s:if><s:else>
							 
							  <tr class="border2">
							    <td width="5%" class="border2"><%=++d %></td>
							     <td width="15%" class="border2">
							     <s:select name="domType" 
			   							cssStyle="width:75" theme="simple" list="#{'':'Select','E':'Essential','D':'Desirable'}" />
							     
							    </td>
							    <td width="15%" class="border2">
							   
							    <s:textfield name ="domName1"    readonly="true"  size="35" /> 
							    </td>
							     
							        
							     <td>
							     <img src="../pages/images/search2.gif" height="16" align="absmiddle" width="16" theme="simple"    onclick = "callDom('<s:property value="<%=""+d%>"/>')">
							     
							     
							     </td>
							   
							     <td width="5%" class="border2"><input type="text" name="domExp"
							     id="domExp" size="35" />
							     </td>
							     
							    <td width="15%" class="border2">
							     <s:select name="domSel" 
			   							cssStyle="width:50" theme="simple" list="#{'':'','A':'And','O':'Or'}" />
							    </td>
							    
							    
							     <td width="15%" class="border2">
							<input type="checkbox" class="checkbox" value="N" name="domChk1" id="domChk1"  onclick="callForDom('<%=d%>');" />
							    </td>
							    
							    <td width="0%">
									<input type="hidden" name="domId1" id="paraFrm_domId1"   />
							    </td>
							  <td width="0%">
							    
									<input type="hidden" name="hdeleteDom1" id="paraFrm_hdeleteDom1"  />
							    </td>
							   
							  </tr>
							 
							 </s:else>
						</table>

            <%
            }catch(Exception e){
            
            e.printStackTrace();
            
            } %>
            
            </td>
          </tr>
          <tr>
            <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          </tr>
      </table>
      
      
      
      
          
      </td>
    </tr>
    
    
     <tr>
      
      
      <td colspan="6">
      
      
      
      
       <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg"  >
                <tr class="sortable">
                <% int c=0; %>
                
                  <td >
                  
                    <% 
                        try{
                        	%>
                  <table width="100%" border="0" cellpadding="0" cellspacing="0"></td></tr>
                      
                      <tr> <strong class="formhead">Certification Details : &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      
                      <input type="button" class="add" value="  Add Row"  onclick="addRowToTableCert();"/>
                      <input type="button" class="delete" value="  Delete"  onclick="deleteRow();"/>
                    
			 </strong>
                        <td colspan="9">
                        
                        <table border="1" id="tblCert">
                         
							  <tr>
							 
							    
							    
							    <td width="5%" valign="top" class="formth" nowrap="nowrap">Srl No.</td>
							    <td width="15%" valign="top" class="formth" nowrap="nowrap">Certification Type</td>
							    <td width="15%" valign="top" class="formth" nowrap="nowrap">Certification Name</td>
							    <td width="5%" valign="top" class="formth" nowrap="nowrap">Issued By</td>
							    <td width="15%" valign="top" class="formth" nowrap="nowrap">Valid Till</td>
							    <td width="15%" valign="top" class="formth" nowrap="nowrap">Option</td>
							    <td width="15%" valign="top" class="formth" nowrap="nowrap"><input type="checkbox" name="confChkCert" id="confChkCert"  onclick="return clickAll();"/></td>
							    <td width="0%" ></td>
							    
							  </tr>
							  
							  <s:if test="%{certFlag}">
							    
							  <s:iterator value="certList" >
							    <tr class="border2">
							    <td width="5%" class="border2"><%=++c%></td>
							  
							     <td width="15%" class="border2">
							    <select name="certType">
							    <option value="E">Essential</option>
							    <option value="D">Desirable</option>
							    </select>
							    </td>
							    <td width="15%" class="border2">
								<s:textfield name ="certName"    readonly="false"  size="20" />
							    </td>
							    			    							   
							     <td width="15%" class="border2"><s:textfield name ="certIssueBy"    readonly="false"  size="20" />
							     </td>
							     <td width="15%" class="border2"><s:textfield name ="certValid"    readonly="false"  size="20" />
							     </td>
							     
							    <td width="15%" class="border2">
							  <s:select name="certOption" 
			   							cssStyle="width:50" theme="simple" list="#{'':'','A':'And','O':'Or'}" />
							    <!--<select name="certOption">
							    <option value="A">And</option>
							    <option value="O">Or</option>
							    --></select>
							    </td>
							     
							    
							    
							     <td width="15%" class="border2">
									<input type="checkbox" class="checkbox" value="N" name="<%="certChk"+c%>"  id="certChk<%=c%>"  onclick="callForCert('<%=c%>');" />
							    </td><td width="0%">
							    <input type="hidden" name="<%="hdeleteCert"+c%>"	id="hdeleteCert<%=c%>"  />
							    
							    </td>
							  
							   
							  </tr>
							 </s:iterator>
							
							 </s:if><s:else>
							 
							   <tr class="border2">
							    <td width="5%" class="border2"><%=++c%></td>
							  
							     <td width="15%" class="border2">
							       <s:select name="certType" 
			   							cssStyle="width:75" theme="simple" list="#{'':'Select','E':'Essential','D':'Desirable'}" />
							     
							     
							     
							     
							     
							     <!--
							    <select name="certType">
							    <option value="E">Essential</option>
							    <option value="D">Desirable</option>
							    --></select>
							    </td>
							    <td width="15%" class="border2">
								<s:textfield name ="certName"    readonly="false"  size="20" />
							    </td>
							    			    							   
							     <td width="15%" class="border2"><s:textfield name ="certIssueBy"    readonly="false"  size="20" />
							     </td>
							     <td width="15%" class="border2"><s:textfield name ="certValid"    readonly="false"  size="20" />
							     </td>
							     
							    <td width="15%" class="border2">
							    
							      <s:select name="certOption" 
			   							cssStyle="width:50" theme="simple" list="#{'':'','A':'And','O':'Or'}" />
							    
							    
							    
							    <!--
							    <select name="certOption">
							    <option value="A">And</option>
							    <option value="O">Or</option>
							    </select>
							    --></td>
							    
							    
							     <td width="15%" class="border2">
							      
									<input type="checkbox" class="checkbox" name="certChk1"  value="N" onclick="callForCert('<%=c%>');" />
							    </td>
							    <td width="0%">
							    
									<input type="hidden" name="hdeleteCert1" id="hdeleteCert1"  />
							    </td>
							  
							   
							  </tr>
							 
							 </s:else>
						</table>

            <%
            }catch(Exception e){
            
            e.printStackTrace();
            
            } %>
            
            </td>
          </tr>
          <tr>
            <td colspan="2" class="formtext"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
          </tr>
      </table>
      
      
      
      
          
      </td>
    </tr>
    
    
    
    
    
    
    
  </table>
<img src="../pages/common/css/default/images/space.gif" width="5" height="7" />

</td>
</tr>
        
      
      
  </table>
    <s:hidden name="rowId" />
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
   
   
   <script>
    function callForQuali(id)
	   {
	 	
	
	   if(document.getElementById('paraFrm_quaChk'+id).checked == true)
	   {	  
	    document.getElementById('paraFrm_hdeleteQuali'+id).value="Y";
	    alert(""+document.getElementById('paraFrm_hdeleteQuali'+id).value);
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
  
  
  
  
  
  
  
  
  
  
  
   function callForSkill(id)
	   {
	 	  
	
	   if(document.getElementById('confChkSkill'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteSkill'+id).value="Y";
	   
	   }
	   else{
	   document.getElementById('hdeleteSkill'+id).value="";
	   	
   		}
  }
  
  function callForDom(id)	   {
 	
	
   if(document.getElementById('domChk'+id).checked == true)
   {	  
	    document.getElementById('hdeleteDom'+id).value="Y";
	
   }else{
   		document.getElementById('hdeleteDom'+id).value="";
	   	
   		}
  }


   function deleteDomain()
	{
		 if(chkDomain()){
		 var con=confirm('Do you want to delete these records?');
		 if(con){
		    document.getElementById('paraFrm').action="EmployeeRequi_deleteDomain.action";
	  		document.getElementById('paraFrm').submit();
		    } else{
		     return true;
		    }
		 }else {
		 	alert('Please Select Atleast one Record To Delete');
		 	 return false;
		 }
	 
	}
  
  
  
  
  
  function chkDomain(){
	var tbl = document.getElementById('tblDom');
		var rowLen = tbl.rows.length;
		  for(var a=1;a<rowLen;a++){	
		   if(document.getElementById('domChk'+a).checked == true)
			   {	
		 	    return true;
		        }	   
		  }
		  return false;
	}
  
  
     function deleteSkill()
	{
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
	
     
   
   function callSkill(id)
    {
	    
	      document.getElementById('paraFrm_rowId').value=id; 
	      callsF9(500,325,'EmployeeRequi_f9Skill.action'); 
     
     
   }
   
   
   
   
   
   
  
 
   
  
   
    function callForCert(id)
	   {
	 	  
	
	   if(document.getElementById('certChk'+id).checked == true)
	   {	  
	    document.getElementById('hdeleteCert'+id).value="Y";
	
	   }
	   else{
	   document.getElementById('hdeleteCert'+id).value="";
	   	
   		}
  }

   
   
   
    
   
  

 


   
   
function addRowToTableCert()
{
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
  sel.options[0] = new Option('Essential', 'E');
  sel.options[1] = new Option('Desirable', 'D');
  cellSel.appendChild(sel);
  
 
  
  
  
  var cell = row.insertCell(2);
  var ed = document.createElement('input');
  ed.type = 'text';
  ed.name = 'certName' ;
  ed.size =20;
  ed.cssClass='border2';
  cell.appendChild(ed);
  

 
	var cell3 = row.insertCell(3);
	var issue=  document.createElement('input');
	issue.type='text';
	issue.name='certIssueBy';
	issue.size=20;
	issue.cssClass='border2';
 	cell3.appendChild(issue);
  

  // right cell
  var cellRight = row.insertCell(4);
  var el = document.createElement('input');
  
  el.type = 'text';
  el.name = 'certValid';
  el.size = 20;
  el.cssClass='border2';
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
 			alert("val of iteration"+iteration);
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
  sel.options[0] = new Option('Essential', 'E');
  sel.options[1] = new Option('Desirable', 'D');
  cellSel.appendChild(sel);
  
 
  
  
  
   var cell = row.insertCell(2);
      
  var ed = document.createElement('input');
  ed.type = 'text';
  ed.name = 'paraFrm_skillName' + iteration;
  ed.id = 'paraFrm_skillName' + iteration;
  ed.size =35;
  

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
  hidSkillId.name = 'paraFrm_skillId'+iteration;
  hidSkillId.id = 'paraFrm_skillId' + iteration;
  hiddenSkill.appendChild(hidSkillId);
 
	
  
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
  quaName.name = 'paraFrm_qualificationName' + iteration;
  quaName.id = 'paraFrm_qualificationName' + iteration;
  quaName.size =20;
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
  quaLvl.name = 'paraFrm_hqualiLevelName'+iteration;
  quaLvl.id =  'paraFrm_hqualiLevelName'+iteration;
  quaLvl.size = 12;
  
  cellQualevel.appendChild(quaLvl);
  
  
   var cellSpecializ = row.insertCell(5);
  var spl = document.createElement('input');
  
  spl.type = 'text';
  spl.name = 'paraFrm_hsplName'+iteration;
  spl.id =   'paraFrm_hsplName'+iteration;
  spl.size = 20;
  
  cellSpecializ.appendChild(spl);
  
  var cellQuaSplImg = row.insertCell(6);
var splImg=  document.createElement('img');
splImg.type='image';
splImg.src='../pages/images/search2.gif';
 splImg.height='16';
 splImg.align='absmiddle';
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
  hidQualiId.name = 'paraFrm_qualificationId'+iteration;
  hidQualiId.id = 'paraFrm_qualificationId'+iteration;
  hiddenQuali.appendChild(hidQualiId);
  
  var hiddenSplCell=row.insertCell(12);
  var hiddenSpl= document.createElement('input');
  hiddenSpl.type = 'hidden';
  hiddenSpl.name = 'paraFrm_hsplId'+iteration;
  hiddenSpl.id = 'paraFrm_hsplId'+iteration;
  hiddenSplCell.appendChild(hiddenSpl);
  //}
 
  
   }  
   


function chkDuplicate(idx){
//alert("index is"+idx);
if(idx>2){
var previous=idx-1;


for (count = 1; count < idx-1; count++)
{
alert(count);
	var currQuali=document.getElementById('paraFrm_qualificationName'+previous).value;
alert("current quali"+currQuali);
	
	var previousQuali=document.getElementById('paraFrm_qualificationName'+count).value;
	alert("value of qua in for loop"+previousQuali);
	alert("eee"+(previousQuali==currQuali));
if(previousQuali==currQuali){
alert(">>>>");
return true;
}
}//End of for loop

}//End idx

return false;
}


   </script>