<%@ taglib prefix="s" uri="/struts-tags"%>   
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="ManpowerReqsn" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" class="formbg"
		cellspacing="0">  
		
	 <tr>
		 <td colspan="3" width="100%">
			<table width="100%" border="0" align="center" class="formbg">
				<tr>
					<td valign="bottom" class="txt" width="3%" colspan="1"><strong
						class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="97%" class="txt" colspan="2"><strong
						class="text_head">Manpower Requisition Report</strong></td>
				</tr>
			</table>
		</td>
		</tr>
		<tr>
		<s:hidden name="selectedReqName"/><s:hidden name="selectedReq"/><s:hidden name="editFlag"/>
		<s:hidden name="divId"/><s:hidden name="aId"/><s:hidden name="backFlag"/>
		<s:hidden name="hiringMgrId"/><s:hidden name="empToken"/><s:hidden name="reqsStatus"/><s:hidden name="hidSelectedReqName"/>
		<td width="78%"> <s:hidden name="reqCode" />  <s:hidden name="fDate" /><s:hidden name="reportView"/>
		<s:hidden name="reportType"/><s:hidden name="settingName"/><s:hidden name="hidReportRadio"/><s:hidden name="hidReportView"/> 
		<s:hidden name="hidFirstAsc"/><s:hidden name="hidFirstDesc"/><s:hidden name="hidSecAsc"/><s:hidden name="hidSecDesc"/><s:hidden name="hidThirdAsc"/><s:hidden name="hidThirdDesc"/>
		  <s:hidden name="appStatus"/><s:hidden name="tDate" /><s:hidden name="divCode"/><s:hidden name="brnCode"/> 
		  <s:hidden name="reqStatus"/><s:hidden name="deptCode"/><s:hidden name="posCode"/> 
		  <s:hidden name="hiringMgr"/><s:hidden name="empToken"/><s:hidden name="dateFilter"/>  <s:hidden name="frmDate"/><s:hidden name="toDate"/> 
		   <s:hidden name="repType"/><s:hidden name="firstSort"/><s:hidden name="radio1"/> <s:hidden name="firstAsc"/><s:hidden name="radioFlag1"/><s:hidden name="firstDesc"/> 
		   <s:hidden name="itReqName1"/><s:hidden name="itPostition"/><s:hidden name="secondSort"/> <s:hidden name="radio2"/> <s:hidden name="secAsc"/><s:hidden name="radioFlag2"/><s:hidden name="secDesc"/>  
		   <s:hidden name="itStatus"/><s:hidden name="itDiv"/><s:hidden name="thirdSort"/> <s:hidden name="radio3"/> <s:hidden name="thirdAsc"/><s:hidden name="radioFlag3"/><s:hidden name="thirdDesc"/> 
		   <s:hidden name="itBranch"/><s:hidden name="itDept"/><s:hidden name="divName"/><s:hidden name="brnName"/><s:hidden name="deptName"/><s:hidden name="posName"/>  
		   <s:hidden name="itApprStatus"/><s:hidden name="advVacVal"/><s:hidden name="advVacOpt"/>    
		   <s:hidden name="itHirmanger" /><s:hidden name="itCandiName" />  
		   <s:hidden name="itJobDecName" /><s:hidden name="itJobDec" /> 
		   <s:hidden name="itRoleperson" /><s:hidden name="itSpecReq" />  
		   <s:hidden name="itPerReq" />    
 	       <s:hidden name="itVacanCompn" /><s:hidden name="itVacexpMin" />  
		   <s:hidden name="itVacexpMax" /><s:hidden name="itVacType" /> 
		   <s:hidden name="itVacanContract" /><s:hidden name="itVacConType" />  
		   <s:hidden name="itReqApprCode" /> 
		   <s:hidden name="itReqLevel" /><s:hidden name="itReqType" /><s:hidden name="dateFlag"/>
		   <s:hidden name="itReqType1" /><s:hidden name="reportFlag"/><s:hidden name="scrnFlg"/> 
		   <s:hidden name="itReqCode1" /><s:hidden name="itReqDate1" /><s:hidden name="radioFlag"/>
		    
	       <input type="button"  class="back" value="Back" onclick="callBack();">
		   <input type="button"  class="report" value="Export Into Pdf" onclick="callPdfReport();">
		   <input type="button"  class="report" value="Export Into Xls" onclick="callXlsReport();">
		   <input type="button"  class="report" value="Export Into Doc" onclick="callDocReport();">
		   &nbsp;&nbsp;&nbsp;<strong class="forminnerhead"><label
								class="set" id="export.all" name="export.all"
								ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label></strong><input type="checkbox" name="exportAll" id="exportAll" onclick="callReportAll();"/>
		   
		</td> 
			<td width="22%">
			    
			</td>
		 </tr>   
		 
          <tr>
          <td colspan="3"> 
           <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg" class="sortable"  >
                  <tr>
                    
                     <s:hidden name="hdPage" id="hdPage" value="%{hdPage}" />
					<%try
					{
						int totalPage = (Integer) request.getAttribute("totalPage");
						int pageNo = (Integer) request.getAttribute("pageNo");
						String reqCode = (String) request.getAttribute("reqCode");
						
					%>
                       
						<tr>
							<td align="right"><s:if test="noData"></s:if><s:else><b>Page:</b></s:else> 
						 <input type="hidden" name="totalPage"  id="totalPage" value="<%=totalPage%>">
						 	<a href="#" onclick="callPage('1','F');"  >
										<img title="First Page" src="../pages/common/img/first.gif" width="10" height="10" class="iconImage"/>
									</a>&nbsp;
									<a href="#" onclick="callPage('P','P');" >
										<img  title="Previous Page" src="../pages/common/img/previous.gif" width="10" height="10" class="iconImage"/>
									</a> 
							    <input type ="text" name="pageNoField" id="pageNoField" theme="simple" size="3"  value="<%= pageNo%>"  onkeypress="callPageText(event);return numbersOnly()"   maxlength="4" /> of <%=totalPage%>
						 		 		<a href="#" onclick="callPage('N','N')" >
											<img  title="Next Page" src="../pages/common/img/next.gif" class="iconImage" />
										</a>&nbsp;
										<a href="#" onclick="callPage('<%=totalPage%>','L');" >
											<img title="Last Page"  src="../pages/common/img/last.gif" width="10" height="10" class="iconImage"/>
										</a>
						</td>
						</tr> 
						<%}
					catch(Exception e)
					{
				    	e.printStackTrace();
					}%> 
        </table>
      </td> 
  </tr>  
  
  <tr> 
  <td colspan="3">
     <table width="100%" class="formbg">
     	
				 <tr>
                  		<strong class="text_head">Manpower Information :</strong>
                   		 <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                 </tr>
		         <tr>
			          <td width="12%" colspan="1" nowrap="nowrap"><label  class = "set"  name="reqs.code" id="reqs.code" ondblclick="callShowDiv(this);"><%=label.get("reqs.code")%></label> : </td>
			          <td width="30%" colspan="1"> <s:property value="itReqName1"/>  </td>
			          <td width="12%" colspan="1"><label  class = "set"  name="reqs.date" id="reqs.date" ondblclick="callShowDiv(this);"><%=label.get("reqs.date")%></label> : </td>
			          <td width="46%" colspan="1"><s:property value="itReqDate1"/> </td> 
		         </tr> 
		         <tr>
			          <td width="12%" colspan="1"><label  class = "set"  name="position" id="position" ondblclick="callShowDiv(this);"><%=label.get("position")%></label> : </td>
			          <td width="30%" colspan="1"> <s:property value="itPostition"/> </td>
			          <td width="12%" colspan="1"><label  class = "set"  name="appstatus" id="appstatus" ondblclick="callShowDiv(this);"><%=label.get("appstatus")%></label> : </td>
			          <td width="46%" colspan="1"><s:property value="itApprStatus"/> </td> 
		         </tr> 
		         <tr>
			          <td width="12%" colspan="1"><label  class = "set"  name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> : </td>
			          <td width="30%" colspan="1"> <s:property value="itDiv"/> </td>
			          <td width="12%" colspan="1" nowrap="nowrap"><label  class = "set"  name="reqstatus" id="reqstatus" ondblclick="callShowDiv(this);"><%=label.get("reqstatus")%></label> : </td>
			          <td width="46%" colspan="1"><s:property value="itStatus"/> </td> 
		         </tr>
		         
		        <tr>
			          <td width="12%" colspan="1"><label  class = "set"  name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> : </td>
			          <td width="30%" colspan="1"> <s:property value="itBranch"/> </td>
			          <td width="12%" colspan="1"><label  class = "set"  name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> : </td>
			          <td width="46%" colspan="1"><s:property value="itDept"/> </td> 
		         </tr>
		         
		          <tr>
			          <td width="12%" colspan="1" nowrap="nowrap"><label  class = "set"  name="hiring.mgr" id="hiring.mgr" ondblclick="callShowDiv(this);"><%=label.get("hiring.mgr")%></label> : </td>
			          <td width="30%" colspan="1"> <s:property value="itHirmanger"/> </td>
			          <td width="12%" colspan="1"><label  class = "set"  name="recruitmenttype" id="recruitmenttype" ondblclick="callShowDiv(this);"><%=label.get("recruitmenttype")%></label> :</td>
			          <td width="46%" colspan="1"><s:property value="itReqType1"/>&nbsp;&nbsp;<s:property value="itReqType"/> </td> 
		         </tr>
         
      </table>
  </td>
  </tr> 
  
  <tr><td colspan="3">
  
  		<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="0" class="formbg">
						
						
						 <tr>
                  		<strong class="text_head">Job Description :
			      		 </strong>
                   		 <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
                  		</tr>
						
						<tr>
							<td width="24%" colspan="1" height="27"><label  class = "set"  name="job.name" id="job.name" ondblclick="callShowDiv(this);"><%=label.get("job.name")%></label> :</td>
							<td width="76%" height="27" colspan="4"><s:property value="itJobDecName" />
                            </td>
					    </tr>
					   
                  	<tr><td>&nbsp;&nbsp;</td></tr>
					   
					 <tr>
              
                    		<td width="24%" height="22" class="formtext" colspan="1" valign="top"><label  class = "set"  name="job.desc" id="job.desc" ondblclick="callShowDiv(this);"><%=label.get("job.desc")%></label> :</td>
                    		<td width="76%" valign="top"><label>
                       		<s:property value="itJobDec"   /> </td>   	
             
                     		
                  	</tr>
                  	
                  		<tr><td>&nbsp;&nbsp;</td></tr>
                  	<tr>
							<td width="24%" colspan="1" height="27" valign="top">	<label  class = "set"  name="roles.res" id="roles.res" ondblclick="callShowDiv(this);"><%=label.get("roles.res")%></label> :</td>
							<td width="76%" height="27" colspan="4" valign="top"><s:property value="itRoleperson" />
                            </td>
				   </tr>
                  	<tr><td>&nbsp;&nbsp;</td></tr>
                  	
                  	 <tr>
              
                    		<td width="24%" height="22" class="formtext"  valign="top"><label  class = "set"  name="specialreq" id="specialreq" ondblclick="callShowDiv(this);"><%=label.get("specialreq")%></label>:</td>
                    		<td width="76%" height="27" valign="top" colspan="4"><label>
                       		<s:property value="itSpecReq"   />
                    		</label></td>
                    		
        		                 		
                  	</tr>
                  	
                  	<tr><td>&nbsp;&nbsp;</td></tr>
                  	<tr>
							<td width="24%" colspan="1" height="27" valign="top"><label  class = "set"  name="persreq" id="persreq" ondblclick="callShowDiv(this);"><%=label.get("persreq")%></label> :</td>
							<td width="76%" height="27" colspan="4"><s:property value="itPerReq" />
                            </td>
					</tr>
			</table>
  </td>
  </tr>
  <tr>
                  	     <td><strong class="text_head" >Vacancy Details : </strong></td>
                   		 <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
  </tr>
  <tr><td colspan="5">
  		<table class="formbg" width="100%">
  		
  			   <tr>
								<td width="30%" valign="top" class="formth" nowrap="nowrap"><label class="set" name="serial.no" id="serial.no1"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
								<td width="35%" valign="top" class="formth" align="center"><label  class = "set"  name="vacn" id="vacn" ondblclick="callShowDiv(this);"><%=label.get("vacn")%></label></td>
								<td width="35%" valign="top" class="formth" align="center"><label  class = "set"  name="required.date" id="required.date" ondblclick="callShowDiv(this);"><%=label.get("required.date")%></label></td>
											
								
								
				</tr>
				
				
					<%int v=0; %>
                      <s:iterator value="vacList" >
                               <tr >
                                <td width="30%" align="center" class="sortableTD"><%=++v%></td>
                                <td width="35%"  align="center" class="sortableTD">&nbsp;<s:property value="noOfVac" /></td>
	    			 			<td width="35%"  align="center" class="sortableTD">&nbsp;<s:property value="vacDate" /></td>
	    			 			          					
                                </tr>
                        </s:iterator>
				
				
				
  		
  		
  	</table>
  
  
  
  </td>
  </tr>
  <tr>
  <td>
  			  <table width="100%">
			  <tr> 				<td width="19%" height="22" class="formtext" nowrap="nowrap">
								<label  class = "set"  name="comp" id="comp" ondblclick="callShowDiv(this);"><%=label.get("comp")%></label> :</span></td>
			                    <td width="30%" height="22"><s:property value="itVacanCompn"  />
			                    </td>
			                    <td width="20%" height="22" class="formtext" nowrap="nowrap">
								<label  class = "set"  name="exper" id="exper" ondblclick="callShowDiv(this);"><%=label.get("exper")%></label>  :</td>
			                    <td width="32%" height="22"><label  class = "set"  name="minexp" id="minexp" ondblclick="callShowDiv(this);"><%=label.get("minexp")%></label> :<label>
			                      <s:property value="itVacexpMin"  />
								<label  class = "set"  name="maxexp" id="maxexp" ondblclick="callShowDiv(this);"><%=label.get("maxexp")%></label> :
			                    </label><s:property value="itVacexpMax" /></td>
			  </tr>
			     <tr>
	                    <td width="19%" height="22" class="formtext" nowrap="nowrap">
						<label  class = "set"  name="vactype" id="vactype" ondblclick="callShowDiv(this);"><%=label.get("vactype")%></label> :</span></td>
	                    <td width="30%" height="22"><label>
	                   <s:property value="itVacType"/></label></td>
	                    <td width="20%" height="22" class="formtext" nowrap="nowrap">
						 <label  class = "set"  name="contracttype" id="contracttype" ondblclick="callShowDiv(this);"><%=label.get("contracttype")%></label> :</td>
	                    <td width="32%" height="22"><label>
	                    <s:property value="itVacanContract" />&nbsp;&nbsp;
						 <s:property value="itVacConType" /></label></td>
	                  </tr>
			  
			  
			  </table>
 </td>
 </tr>
 <s:if test="qualFlag">
  <tr>
                  	     <td><strong class="text_head" >Qualification Details : </strong></td>
                   		 <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
  </tr>
 
 <tr>
	 <td colspan="3">
	 					  <table id="tblQuali" width="100%" class="formbg">
                           <tr><td width="5%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="serial.no" id="serial.no2"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
								<td width="10%" valign="top" class="formth" nowrap="nowrap" align="center"><label  class = "set"  name="qtyp" id="qtyp" ondblclick="callShowDiv(this);"><%=label.get("qtyp")%></label></td>
								<td width="18%" valign="top" class="formth" align="center"><label  class = "set"  name="qabbr" id="qabbr" ondblclick="callShowDiv(this);"><%=label.get("qabbr")%></label></td>
								<td width="12%" valign="top" class="formth" align="right" nowrap="nowrap" align="center"><label  class = "set"  name="qlvl" id="qlvl" ondblclick="callShowDiv(this);"><%=label.get("qlvl")%></label></td>
								<td width="10%" valign="top" class="formth" align="center"><label  class = "set"  name="spabbr" id="spabbr" ondblclick="callShowDiv(this);"><%=label.get("spabbr")%></label></td>
								<td width="6%" valign="top" class="formth" align="center" nowrap="nowrap" ><label  class = "set"  name="mrks" id="mrks" ondblclick="callShowDiv(this);"><%=label.get("mrks")%></label></td>
								<td width="6%" valign="top" class="formth" align="left"><label  class = "set"  name="opt" id="opt" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></td>
								
								
															
							</tr>
							
							 <%int i=0; %>
							  
							   
                    
                               <s:iterator value="qualList" >
                               
                                  <tr >
                                 <td width="5%" align="center" class="sortableTD"><%=++i%></td>
                                 <td width="10%"  align="left" class="sortableTD">&nbsp;<s:property value="qualType"/></td>         
                                 <td nowrap="nowrap" width="15%" align="left" class="sortableTD">&nbsp;<s:property value="qualName"/></td>
                                 <td width="8%"  align="left" class="sortableTD">&nbsp;<s:property value="qualLvl"/></td>
		   						 <td width="10%"  align="left" nowrap="nowrap" class="sortableTD">&nbsp;<s:property value="spl"/></td>			  
	      	   		             <td width="10%"  align="left" class="sortableTD">&nbsp;<s:property value="cutOff" /> </td>        
	      	   		             <td width="8%"  align="left" class="sortableTD">&nbsp;&nbsp;&nbsp;<s:property value="sel" />
	      	   		              
                                </tr>
                            </s:iterator>
                              
                       </table>
	 
	 
	 </td>
 </tr>
 </s:if>
  <s:if test="skillFlag">
  <tr>
                  	     <td><strong class="text_head" >Skill Details : </strong></td>
                   		 <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
  </tr>
  
  <tr><td colspan="3">
  					 <table border="0" id="tblSkill" width="100%" class="formbg">
                           <tr>
								<td width="5%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="serial.no" id="serial.no3"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
								<td width="10%" valign="top" class="formth" align="center" nowrap="nowrap" ><label  class = "set"  name="styp" id="styp" ondblclick="callShowDiv(this);"><%=label.get("styp")%></label></td>
								<td width="18%" valign="top" class="formth" align="center" nowrap="nowrap"><label  class = "set"  name="sname" id="sname" ondblclick="callShowDiv(this);"><%=label.get("sname")%></label></td>
								
								<td width="12%" valign="top" class="formth" align="center" nowrap="nowrap"><label  class = "set"  name="sexp" id="sexp" ondblclick="callShowDiv(this);"><%=label.get("sexp")%></label></td>
								<td width="10%" valign="top" class="formth" align="center" nowrap="nowrap"><label  class = "set"  name="opt" id="opt1" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></td>
							
							
															
							</tr>
							<%int j=0; %>
							 <s:iterator value="skillList" >
                               
                                  <tr >
	                                   <td width="5%" align="center" class="sortableTD"><%=++j%><s:hidden  name="hssrlNo" /></td>
	                                   <td width="10%" align="left" nowrap="nowrap" class="sortableTD">&nbsp;<s:property value="skillType"/></td>
	                                   <td nowrap="nowrap" width="18%" align="left" class="sortableTD">&nbsp;<s:property value="skillName"/></td>
			   						
		    			 				<td width="12%"  align="center" class="sortableTD">&nbsp;<s:property value="skillExp" /></td>
										<td width="10%" align="center" class="sortableTD">&nbsp;<s:property value="skillSel"/></td>
										
										
	              
	                   					
                  
                  
                                </tr>
                            
                           
                          </s:iterator>
							
					</table>
  
  
  </td>
  </tr>
  </s:if>
  <s:if test="domFlag">
  <tr>
                  	     <td><strong class="text_head" >Domain/Functional Details : </strong></td>
                   		 <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
  </tr>
  
  
  <tr><td colspan="3">  
  
  					 <table border="0" id="tblDom" width="100%" class="formbg">
                         <% int d=0; %>
							  <tr>
							 
							    
							    
							    <td width="5%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="serial.no" id="serial.no4"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
							    <td width="10%" valign="top" class="formth" nowrap="nowrap"><label  class = "set"  name="dtyp" id="dtyp" ondblclick="callShowDiv(this);"><%=label.get("dtyp")%></label></td>
							    <td width="18%" valign="top" class="formth" nowrap="nowrap" align="center"><label  class = "set"  name="dname" id="dname" ondblclick="callShowDiv(this);"><%=label.get("dname")%></label></td>
							    <td width="12%" valign="top" class="formth" nowrap="nowrap" align="center"><label  class = "set"  name="sexp" id="sexp1" ondblclick="callShowDiv(this);"><%=label.get("sexp")%></label></td>
							    <td width="10%" valign="top" class="formth" nowrap="nowrap" align="center"><label  class = "set"  name="opt" id="opt2" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></td>
							
							   
							    
							  </tr>
							  <%int m=0; %>
							
							     <s:iterator value="domList" >
							<tr>
							    <td width="5%" align="center" class="sortableTD"><%=++m %></td>
							     <td width="10%" class="sortableTD">&nbsp;<s:property value=" domType"    /></td>
							     <td width="18%" class="sortableTD">&nbsp;<s:property value=" domName"    /></td>
							     <td width="12%" align="center" class="sortableTD">&nbsp;<s:property value=" domExp" /></td>
							     <td width="10%" align="center" class="sortableTD">&nbsp;<s:property value="domSel"/> </td>
							   
							  </tr>
							 </s:iterator>
							
							
						</table>
  
  
  </td>
  </tr>
  </s:if>
  <s:if test="certFlag">
  <tr>
                  	     <td><strong class="text_head" >Certification Details : </strong></td>
                   		 <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
  </tr>
  
  <tr><td colspan="3">
  
   						<table border="0" id="tblCert" width="100%" class="formbg">
                         
							  <tr>
							 
							    
							    
							    <td width="5%" valign="top" class="formth" nowrap="nowrap" align="center"><label class="set" name="serial.no" id="serial.no5"
								ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
							    <td width="10%" valign="top" class="formth" nowrap="nowrap" align="center"><label  class = "set"  name="ctyp" id="ctyp" ondblclick="callShowDiv(this);"><%=label.get("ctyp")%></label></td>
							    <td width="12%" valign="top" class="formth" nowrap="nowrap" align="left"><label  class = "set"  name="cname" id="cname" ondblclick="callShowDiv(this);"><%=label.get("cname")%></label></td>
							    <td width="12%" valign="top" class="formth" nowrap="nowrap" align="left"><label  class = "set"  name="ciss" id="ciss" ondblclick="callShowDiv(this);"><%=label.get("ciss")%></label></td>
							    <td width="18%" valign="top" class="formth" nowrap="nowrap" align="center"><label  class = "set"  name="cvalid" id="cvalid" ondblclick="callShowDiv(this);"><%=label.get("cvalid")%></label></td>
							    <td width="10%" valign="top" class="formth" nowrap="nowrap" align="left">&nbsp;&nbsp;&nbsp;&nbsp;<label  class = "set"  name="opt" id="opt3" ondblclick="callShowDiv(this);"><%=label.get("opt")%></label></td>
							   
							    
							  </tr>
							     <%int ce=0; %>
							  <s:iterator value="certList" >
							    <tr>
							    <td width="5%" align="center" class="sortableTD"><%=++ce%></td>
							  
							     <td width="10%" align="left" class="sortableTD">&nbsp;<s:property value="certType"/></td>
							    <td width="18%" align="left" class="sortableTD">&nbsp;<s:property value="certName" /></td>
							    			    							   
							     <td width="12%" align="left" class="sortableTD">&nbsp;<s:property value="certIssueBy" /></td>
							     <td width="18%" align="left" class="sortableTD">&nbsp;<s:property value="certValid" /></td>
							     
							    <td width="10%" align="left"  class="sortableTD">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							    <s:property value="certOption"  /></select>
							    </td>
							     
							    
							    
						
							   
							  </tr>
							 </s:iterator>
						</table>	  
  
  
  
  
  </td>
  
  
  
  </tr>
  </s:if>
  
  <s:if test="apprvFlag">
  <tr>
                  	     <td><strong class="text_head" >Approver Details : </strong></td>
                   		 <td colspan="5" class="formhead"><img src="../pages/common/css/default/images/space.gif" width="5" height="7" /></td>
  </tr>
  
  
  <tr><td colspan="3">
		   <table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
		   			 <tr>
						  <td class="formth" width="10%"><label class="set" name="serial.no" id="serial.no6"
						   ondblclick="callShowDiv(this);"><%=label.get("serial.no")%></label></td>
						  <td class="formth" width="30%"><label  class = "set"  name="apprvName" id="apprvName" ondblclick="callShowDiv(this);"><%=label.get("apprvName")%></label></td>
						  <td class="formth" width="30%"><label  class = "set"  name="apprvDate" id="apprvDate" ondblclick="callShowDiv(this);"><%=label.get("apprvDate")%></label></td>
						  <td class="formth" width="30%"><label  class = "set"  name="apprvRem" id="apprvRem" ondblclick="callShowDiv(this);"><%=label.get("apprvRem")%></label></td>
					  
					  </tr>
					  <%int a=0; %>
					    <s:iterator value="apprvList">
					  <tr>
					  		<td class="sortableTD" width="10%">&nbsp;<%=++a%></td>
					  		<td class="sortableTD" width="30%">&nbsp;<s:property value="apprvName"/></td>
					  		<td class="sortableTD" width="30%">&nbsp;<s:property value="apprvDate"/></td>
					  		<td class="sortableTD" width="30%">&nbsp;<s:property value="apprvRem"/></td>
					  				  
					  </tr>
					  </s:iterator>
		   		
		   
		   
		   </table>
  
  
  </td></tr>
  </s:if>
  <tr>
  <td colspan="3" width="100%">
 <input type="button"  class="back" value="Back" onclick="callBack();">
		   <input type="button"  class="report" value="Export Into Pdf" onclick="callPdfReport();">
		   <input type="button"  class="report" value="Export Into Xls" onclick="callXlsReport();">
		   <input type="button"  class="report" value="Export Into Doc" onclick="callDocReport();">
		  
					    &nbsp;&nbsp;&nbsp;<strong class="forminnerhead"><label
								class="set" id="export.all" name="export.all"
								ondblclick="callShowDiv(this);"><%=label.get("export.all")%></label></strong><input type="checkbox" name="exportAll1" id="exportAll1" onclick="callReportAll1();"/>
  
  </td>
  
  </tr>
 </table><s:hidden name="exportAllData"/><s:hidden name="mraRepCode"/>
 </s:form>
			
	
	 <script> 
function callReportAll(){
		
			if(document.getElementById('exportAll').checked){
		 	document.getElementById('paraFrm_exportAllData').value="Y";
		 	document.getElementById('exportAll1').checked=true;
			}
			if(!document.getElementById('exportAll').checked){
		 	document.getElementById('paraFrm_exportAllData').value="N";
		 	document.getElementById('exportAll1').checked=false;
			}
			
			
	}		 
	 
	 function callReportAll1(){
		
			if(document.getElementById('exportAll1').checked){
			document.getElementById('exportAll').checked=true;
		 	document.getElementById('paraFrm_exportAllData').value="Y";
			}
			if(!document.getElementById('exportAll1').checked){
			document.getElementById('exportAll').checked=false;
		 	document.getElementById('paraFrm_exportAllData').value="N";
			}
			
	}	
callExport();
function callExport(){
document.getElementById('exportAll1').checked=true;
document.getElementById('exportAll').checked=true;
document.getElementById('paraFrm_exportAllData').value="Y";
}	
    function callPage(id)
	{  
	 document.getElementById('hdPage').value = id;  
     document.getElementById('paraFrm').action = "ManpowerReqsn_callJspView.action";
     document.getElementById('paraFrm').submit(); 
	}
 
    function callPageStatus(stage)
	 {  
		var pgNo=document.getElementById('hdPage').value; 
		if(stage=="P"){
			pgNo=eval(pgNo)-1;
		 }else{
		    pgNo=eval(pgNo)+1;
		 } 
		 document.getElementById('hdPage').value = pgNo;  
	     document.getElementById('paraFrm').action = "ManpowerReqsn_callJspView.action";
	     document.getElementById('paraFrm').submit(); 
	} 
	
	function callBack()
	{
	
	document.getElementById('paraFrm_backFlag').value="true";	
	 document.getElementById('paraFrm').action = "ManpowerReqsn_input.action";
     document.getElementById('paraFrm').submit(); 
	}
	
	function callDocReport()
	{
	 document.getElementById('paraFrm').action = "ManpowerReqsn_report.action?rep=Txt";
     document.getElementById('paraFrm').submit(); 
	}
	
	function callXlsReport()
	{
	 document.getElementById('paraFrm').action = "ManpowerReqsn_report.action?rep=Xls";
     document.getElementById('paraFrm').submit(); 
	}
	
	function callPdfReport()
	{
	 document.getElementById('paraFrm').action = "ManpowerReqsn_report.action?rep=Pdf";
     document.getElementById('paraFrm').submit(); 
	}
	
	function callPageText(id){ 
	
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
		 	document.getElementById('paraFrm_exportAllData').value="N";
		 	var actPage = document.getElementById('hdPage').value;   
	     
		 	 
	        if(pageNo==""){
	         alert("Please Enter Page Number.");
	         document.getElementById('pageNoField').focus();
			 return false;
	        }
		   if(Number(pageNo)<=0)
		    {
		     alert("Page number should be greater than zero."); 
		      document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    if(Number(totalPage)<Number(pageNo))
		    {
		     alert("Page number should not be greater than "+totalPage+".");
		     document.getElementById('pageNoField').focus();
		     document.getElementById('pageNoField').value=actPage;  
			 return false;
		    }
		    
		    if(pageNo==actPage){  
		      document.getElementById('pageNoField').focus();
		      return false;
	        }
	        
	         document.getElementById('hdPage').value=pageNo;
	
	
		   
			document.getElementById('paraFrm').action='ManpowerReqsn_callJspView.action';
			document.getElementById('paraFrm').submit();
		}
		
	}
	
	function callPage(id,pageImg){  
 	 pageNo =document.getElementById('pageNoField').value;	
 	 totalPage =document.getElementById('totalPage').value;	 
 	 
 	   if(pageNo==""){
	        alert("Please enter page number.");
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
		     alert("Page number should not be greater than total Page number.");
		     document.getElementById('pageNoField').focus();
			 return false;
		    }
		    	
       
       
        if(pageImg=="P")
       {
	        if(pageNo=="1"){
	         alert("There is no previous page");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       if(pageImg=="N")
       {
	        if(Number(pageNo)==Number(totalPage)){
	         alert("There is no next page");
	         document.getElementById('pageNoField').focus();
	         return false;
	        } 
       }  
       
		if(id=='P'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)-1;
		}
		if(id=='N'){
		var p=document.getElementById('hdPage').value;
		id=eval(p)+1;
		} 
		document.getElementById('hdPage').value=id;
		document.getElementById('paraFrm_exportAllData').value="N";
		document.getElementById('paraFrm').action='ManpowerReqsn_callJspView.action';
		document.getElementById('paraFrm').submit(); 
	}	

	 </script>