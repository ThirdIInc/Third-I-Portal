<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
	
<s:form name="" action="" validate="" id="paraFrm" theme="simple">
	<% 
		String apprFor = "";
		try{
			System.out.println("hi1....");
			apprFor = (String) request.getAttribute("appFor");
		}catch(NullPointerException e){
			//out.print("apprFor>>"+apprFor);
			apprFor = " ";
			System.out.println("hi2....");
			e.printStackTrace();
		}finally{
			 //out.print("apprFor>>"+apprFor);
			 if(apprFor==null){
				 apprFor = "";
			 }
		}
	%>
<s:hidden name="appStatus" />
<table width="100%" border="0"  cellpadding="1" cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Travel
					Application Preview</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
		  <td><input type="button"  value="   Close   " class="token" onclick="window.close();" /></td>
		 </tr>
		<tr>
		 <td>
		  <table width="100%" align="center" class="formbg" border="0">
		   		<tr>
			    <td width="15%"> 
			    	<label class="set" name="traappfor" id="traappfor1" ondblclick="callShowDiv(this);"><%=label.get("traappfor")%></label>:
			    </td>
			    <td width="15%">
			    	<s:if test='%{hAppFor=="S"}'>
			    		Self
			    	</s:if>
			    	<s:elseif test='%{hAppFor=="G"}'>
			    		Guest
			    	</s:elseif>
			    	<s:elseif test='%{hAppFor=="O"}'>
			    		Others
			    	</s:elseif>
			    </td> 
			    <td width="25%">
				    <s:if test='%{hAppFor=="S"}'>
			    		<label class="set" name="traaccom" id="traaccom" ondblclick="callShowDiv(this);"><%=label.get("traaccom")%></label>:
			    	</s:if>
			    	<s:elseif test='%{hAppFor=="G" || hAppFor=="O"}'>
				    	<label class="set" name="traaccom" id="traaccom1" ondblclick="callShowDiv(this);"><%=label.get("traaccom1")%></label>:
					</s:elseif>	
			    </td>
			    <td width="35%">			     
			     <s:property value="accompWith"/>
			    </td>
			   </tr>
			   <tr>
			    <td>
			    	<label class="set" name="traappdate" id="traappdate1" ondblclick="callShowDiv(this);"><%=label.get("traappdate")%></label>:
			    </td>
			    <td>
			     <s:property value="appDate"/>
			    </td>
			    <td>
			    	<label class="set" name="traStatus" id="traStatus1" ondblclick="callShowDiv(this);"><%=label.get("traStatus")%></label>:
			    </td>
			    <td>
			        <s:if test='%{appStatus=="N"||appStatus=="" ||appStatus=="W"  }'>
			      	 	Draft
			      	</s:if>
			      	<s:if test='%{appStatus=="A"}' >
			       		Approved
			      	</s:if>
			      	<s:if test='%{appStatus=="AC"}' >
			       		Cancellation Approved
			      	</s:if>			      	
			      	<s:if test='%{appStatus=="H"}' >
			       		On Hold
			      	</s:if>
			      	<s:if test='%{appStatus=="R"}' >
			       		Rejected
			      	</s:if>
			      	<s:if test='%{appStatus=="P" ||appStatus=="FT"}' >
			       		Pending
			      	</s:if>
			      	<s:if test='%{appStatus=="B"}' >
			       		Returned
			      	</s:if>
			      	<s:if test='%{appStatus=="PC" || appStatus=="FC"}' >
			       		Pending for Cancellation
			      	</s:if>
			      	<s:if test='%{appStatus=="CC"}' >
			  			Cancelled
			  		</s:if>
			    </td>
			   </tr>
			   <tr>
			    <td>
			    	<label class="set" name="trainitor" id="trainitor1" ondblclick="callShowDiv(this);"><%=label.get("trainitor")%></label>: 
			    </td>
			    <td colspan="3">		
				    <!--<s:property value="initToken"/>
				    --><s:property value="initName"/>				    		    
			    </td>
			   </tr>
		  </table>		 
		 </td>		
		</tr>
				
		<tr>
		 <td>
		 <div id="empDiv" style="display: <%=apprFor.equals("S")||apprFor.equals("O")||apprFor.equals("")?"block":"none" %>;" >
		  <table width="100%" width="100%" align="center" class="formbg">
		    <tr>
		     <td colspan="4"><b>Employee Information</b></td>
		    </tr>
		    <tr>
		    <td colspan="4">
		     <table width="100%">
		      <tr>
		       <td class="formth">
		       		<b><label class="set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
		       </td>
		       <td class="formth"> 
		       		<b><label class="set" name="Empname" id="Empname1"	ondblclick="callShowDiv(this);"><%=label.get("Empname")%></label></b>
		       </td>
		       <td class="formth">
		       		<b><label class="set" name="Bname" id="Bname1" ondblclick="callShowDiv(this);"><%=label.get("Bname")%></label></b>
		       </td>
		       <td class="formth">
		       		<b><label class="set" name="Deptname" id="Deptname1" ondblclick="callShowDiv(this);"><%=label.get("Deptname")%></label></b>
		       </td>
		       <td class="formth">
		       		<b><label class="set" name="Designame" id="Designame1"	ondblclick="callShowDiv(this);"><%=label.get("Designame")%></label></b>
		       </td>
		       <td class="formth">
		       		<b><label class="set" name="EmpAge" id="EmpAge"	ondblclick="callShowDiv(this);"><%=label.get("EmpAge")%></label></b>
		       </td>
		       <td class="formth"><b><label class="set" name="ContactNO" id="ContactNO1" 
					ondblclick="callShowDiv(this);"><%=label.get("ContactNO")%></label></b></td>	       			       
		       
		      </tr>
		     
		      <%int i=1; %>
		      <s:iterator value="empList">
		       <tr>
		        <td class="sortableTD"><%=i%></td>
		        <td class="sortableTD">
		           <s:property value="empName" /> 
		         </td> 
		        <td class="sortableTD"><s:property value="branch" /></td>
		        <td class="sortableTD"><s:property value="dept" /></td>
		        <td class="sortableTD"><s:property value="desg" /></td>
		        <td class="sortableTD">
		        	<s:property value="age" />&nbsp;
		        </td>
		        <td class="sortableTD">
		        		<s:property value="contact" />&nbsp;
		        </td>		     
		       </tr>
		       <%i++; %>
		      </s:iterator>
		      <% if(i==1){ %>
		       <tr>
		        <td colspan="7" align="center"><font color="red">No Data to display</font></td>
		       </tr>
		      <% } %>
		     </table>
		    </td>
			</tr>
		  </table>
		  </div>
		 </td>		
		</tr>
		<tr>
		 <td>
		  <div id="guestDiv" style="display: <%=apprFor.equals("G")?"block":"none" %>;">
		   <table width="100%" width="100%" align="center" class="formbg">
		    <tr>
		     <td colspan="1"><b>Guest Information</b></td>
		    </tr>
		      <tr>
		       <td colspan="6">
		       <table width="100%">
		       <tr>
		       <td class="formth" width="5%">
		       		<label class="set" name="sno" id="sno"	ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
		       	</td>
		       <td class="formth" width="20%">
		       		<label class="set" name="Guestname" id="Guestname2"	ondblclick="callShowDiv(this);"><%=label.get("Guestname")%></label>
		       </td>	
		       <td class="formth" width="5%">
		       		<label class="set" name="EmpAge" id="EmpAge3" ondblclick="callShowDiv(this);"><%=label.get("EmpAge")%></label>
		       </td>
		       <td class="formth" width="10%">
		       		<label class="set" name="ContactNO" id="ConductNO4" ondblclick="callShowDiv(this);"><%=label.get("ContactNO")%></label>
		       </td>
		      </tr>		      
		      <%int j=1; %>
		      <s:iterator value="guestList">
		       <tr>
		        <td class="sortableTD"><%=j%></td>
		        <td class="sortableTD"> 		           
		           	<s:property value="guestName" />
		        </td> 
		        <td align="center" class="sortableTD">
		        	<s:property value="guestAge" />&nbsp;
		        </td>
		        <td align="left" class="sortableTD">
		        	<s:property value="guestContact" />&nbsp;
		        </td>
		       </tr>
		       <%j++; %> 
		      </s:iterator>
		      <% if(j==1){ %>
		       <tr>
		        <td colspan="5" align="center" class="sortableTD"><font color="red">No Data to display</font></td>
		       </tr>
		      <% } %>
		      </table>
		     </td>
		    </tr>
		   </table>		  
		  </div>	
		 </td>		
		</tr>
		
		<tr>
		 <td>
		  <table width="100%" class="formbg" border="0">
		   <tr> 
		    <td colspan="4" width="100%"><b>Tour Details</b></td>
		   </tr> 
		   <tr>  
		    <td width="10%">
		    	<label class="set" name="Trastdate" id="Trastdate1" ondblclick="callShowDiv(this);"><%=label.get("Trastdate")%></label>
		      <font color="red">*</font> :</td>
		    <td width="10%">
			   <s:property value="startDate"/>
		    </td> 
		     <td width="10%">
		     <label class="set" name="Traenddate" id="Traenddate1" 	ondblclick="callShowDiv(this);"><%=label.get("Traenddate")%></label> : 
			</td>
		    <td width="10%">
		    	<s:property value="endDate"/>
		    </td>
		   </tr>		  
		   <tr>
		    <td width="10%">
		    	<label class="set" name="TraReqname" id="TraReqname1" ondblclick="callShowDiv(this);"><%=label.get("TraReqname")%></label>
		    	<font color="red">*</font>:</td>
		    <td width="10%"><s:property value="trvlReqName"/></td>
		    <td width="10%">
		    	<label class="set" name="Trapurpose" id="Trapurpose1" ondblclick="callShowDiv(this);"><%=label.get("Trapurpose")%></label>
		    	<font color="red">*</font>:</td>
		    <td width="10%">
		    	<s:property value="purpose"/>	
		    </td>
		   </tr> 
		   <tr>
		    <td width="10%">
		    	<label class="set" name="Ftype" id="Ftype1" ondblclick="callShowDiv(this);"><%=label.get("Ftype")%></label> :
		    </td>
		    <td width="10%">
		    	<s:property value="foodType"/>	
		    </td> 
		    <td width="10%">
		    	<label class="set" name="TravelType" id="TravelType" ondblclick="callShowDiv(this);"><%=label.get("TravelType")%></label> <font color="red">*</font>:
		    </td> 
		    <td width="10%">
		     <s:property value="trvlType"/>	
		     </td>
		   </tr>
		   <tr>
		    <td width="10%">
		    	<label class="set" name="TraArg" id="TraArg1" ondblclick="callShowDiv(this);"><%=label.get("TraArg")%></label> :
		    </td>
		    <td width="10%">		     
		     <table width="100%">
		      <tr>
		       <td>
			        <s:if test='%{trRadio=="S"}'>
			        	Self
			       </s:if>
			       <s:if test='%{trRadio=="C"}'>
			        	Company
			       </s:if> 		        	         
		       </td>
		      </tr>
		     </table>
		    </td>
		    <td width="10%">
		    	<label class="set" name="Accom" id="Accom1" ondblclick="callShowDiv(this);"><%=label.get("Accom")%></label> :
		    </td>
		    <td width="10%">
		     <table width="100%">
		      <tr>
		       <td>
			       <s:if test='%{accomRadio=="S"}'>
			        	Self
			       </s:if>
			       <s:if test='%{accomRadio=="C"}'>
			        	Company
			       </s:if>	       
		      </tr>
		     </table>
		    </td>
		   </tr>
		    
		   <tr>
		    <td width="15%">
		    	<label class="set" name="Locconv" id="Locconv1" ondblclick="callShowDiv(this);"><%=label.get("Locconv")%></label>  :
		    </td>
		    <td>
		     <table width="100%">
		      <tr>
		       <td>
			       	<s:if test='%{locRadio=="S"}'>
			        	Self
			       </s:if>
			       <s:if test='%{locRadio=="C"}'>
			        	Company
			       </s:if>			        
		       </td>
		       <td colspan="2">&nbsp;</td> 
		      </tr>
		     </table>
		    </td>
		   </tr>
		  </table>
		 </td>
		</tr>
		 	
		
		<tr>
		 <td>
		  <table width="100%" class="formbg" border="0">
		    <tr>
		     <td colspan="3"><b>Journey Details</b></td>
		    </tr>
		    <tr>
		     <td class="formth" width="4%">
		      <b><label class="set" name="sno" id="sno1" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
		     </td>
		     <td class="formth" width="20%">
		      <b><label class="set" name="Frplace" id="Frplace1" ondblclick="callShowDiv(this);"><%=label.get("Frplace")%></label></b>
		      <font color="red">*</font>
		     </td>
		     <td class="formth" width="20%">
		      <b><label class="set" name="Toplace" id="Toplace0"	ondblclick="callShowDiv(this);"><%=label.get("Toplace")%></label></b> 
		      <font color="red">*</font>
		     </td>
		     <td class="formth" width="18%">
		      <b><label class="set" name="JMclass" id="JMclass" ondblclick="callShowDiv(this);"><%=label.get("JMclass")%></label></b>
		      <font color="red">*</font>
		     </td>
		     <td class="formth" width="18%">
		      <b><label class="set" name="Jourdate" id="Jourdate0" 	ondblclick="callShowDiv(this);"><%=label.get("Jourdate")%></label> <font color="red">*</font><br>(DD-MM-YYYY)
		      </td>
		     <td class="formth" width="15%">
		      <b><label class="set" name="Timing" id="Timing" ondblclick="callShowDiv(this);"><%=label.get("Timing")%></label><br>(HH:MM)</b>
		     </td>		     
		    </tr>
		    <%int k=1; %>
		    <s:iterator value="jourList">		    
		     <tr>
		      <td  class="sortableTD" ><%=k %></td>
		      <td  class="sortableTD" align="left"><s:property value="frmPlace" /></td>
		      <td  class="sortableTD" align=""left""><s:property value="toPlace" /></td>
		      <td class="sortableTD" align=""left"">		      	
		      	<s:property value="jourMode" />		      	    
		      </td>		      
		      <td align="center" class="sortableTD" align="center">
		      	<s:property value="jourDate" />		      	
		      </td class="sortableTD">
		      <td class="sortableTD" align="center"><s:property value="jourTime" /></td>		     
		     </tr>
		     <%k++; %>
		    </s:iterator>
		    <%if(k==1){ %>
		    	<tr>
		    	 <td colspan="7" align="center"><font color="red">No Data to display</font></td>
		    	</tr>
		    <%} %>		    
		   </table>
		 </td>		
		</tr>
		
		<tr>
		 <td>
		   <table width="100%" class="formbg" border="0">
		    <tr> 
		     <td colspan="3"><b>Lodging Details</b></td> 		     
		    </tr>		      
		    <tr>
		     <td class="formth" width="3%">
		     	<b><label class="set" name="sno" id="sno2" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
		     </td>
		     <td class="formth" width="17%">
		     	<b><label class="set" name="LodType" id="LodType" 	ondblclick="callShowDiv(this);"><%=label.get("LodType")%></label></b>
		     	 <font color="red">*</font>
		     </td>
		     <td class="formth" width="17%">
		     	<b><label class="set" name="RoomType" id="RoomType2" ondblclick="callShowDiv(this);"><%=label.get("RoomType")%></label></b>
		     	<font color="red">*</font></td>
		     <td class="formth" width="17%"> 
		     	<b><label class="set" name="traCity" id="traCity" ondblclick="callShowDiv(this);"><%=label.get("traCity")%></label></b>
		     	<font color="red">*</font></td>
		     <td class="formth" width="10%">
		     	<b><label class="set" name="Prefloc" id="Prefloc" ondblclick="callShowDiv(this);"><%=label.get("Prefloc")%></label></b>
		     </td>
		     <td class="formth" width="10%">
		     	<b><label class="set" name="FrDate" id="FrDate" ondblclick="callShowDiv(this);"><%=label.get("FrDate")%></label></b><font color="red">*</font><br>(DD-MM-YYYY)
		     	
		     </td>
		     <td class="formth" width="5%">
		     	<b><label class="set" name="FrTime" id="FrTime" ondblclick="callShowDiv(this);"><%=label.get("FrTime")%></label><br>(HH:MM)</b>
		     </td>
		     <td class="formth" width="10%">
		     	<b><label class="set" name="ToDate" id="ToDate" ondblclick="callShowDiv(this);"><%=label.get("ToDate")%></label></b><font color="red">*  </font><b><br>(DD-MM-YYYY)</b> 
		     </td>
		     <td class="formth" width="5%">
		     	<b><label class="set" name="ToTime" id="ToTime" ondblclick="callShowDiv(this);"><%=label.get("ToTime")%></label><br>(HH:MM)</b>
		     </td>		     
		     </tr>
		    <%int l=1; %>  
		    <s:iterator value="accomList">		    
			      <tr>
			      <td  class="sortableTD"><%=l %></td>
			      <td class="sortableTD">
				      <s:property value="lodgType" />				      
			      </td>
			      <td class="sortableTD">
			       <s:property value="roomType" />			        
			      </td>
			      <td class="sortableTD">
			       <s:property value="city" />				             
			      </td>
			      <td class="sortableTD">
			       <s:property value="prfrdLoc" />&nbsp; 
			      </td>
			      <td class="sortableTD">
			       <s:property value="frmDate" />			      
			      </td>
			      <td class="sortableTD">
			       <s:property value="frmTime" />&nbsp; 
			      </td>
			      <td class="sortableTD">
			       <s:property value="toDate" />			        
			      </td>
			      <td class="sortableTD">
			       <s:property value="toTime" />&nbsp;
			      </td> 			     	      
			     </tr>	
			     <%l++; %>	     
		    </s:iterator>
		    <%if(l==1){ %>
		    	<tr>
		    	 <td colspan="10" align="center"><font color="red">No Data to display</font></td>
		    	</tr>
		    <%} %>		    
		   </table>
		 </td>		
		</tr>
		
		<tr>
		 <td>
		    <table width="100%" class="formbg" border="0">
		    <tr>
		     <td colspan="4"><b>Local Conveyance Details</b></td>		    
		    </tr>		    
		    <tr>
		     <td class="formth">
		     	<b><label class="set" name="sno" id="sno3" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="traCity" id="traCity1" ondblclick="callShowDiv(this);"><%=label.get("traCity")%></label><font color="red">*</font></b>
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="TraDet" id="TraDet1" ondblclick="callShowDiv(this);"><%=label.get("TraDet")%></label></b>
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="locMedium" id="locMedium" ondblclick="callShowDiv(this);"><%=label.get("locMedium")%></label></b>
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="FrDate" id="FrDate3" ondblclick="callShowDiv(this);"><%=label.get("FrDate")%></label> <br>(DD-MM-YYYY)</b>
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="FrTime" id="FrTime3" ondblclick="callShowDiv(this);"><%=label.get("FrTime")%></label> <br>(HH:MM)</b>
		     </td>
		     <td class="formth">
		     	<b><label class="set" name="ToDate" id="ToDate3" ondblclick="callShowDiv(this);"><%=label.get("ToDate")%></label> <br>(DD-MM-YYYY)</b>
		     </td>
		     <td class="formth">
		     	 <b><label class="set" name="ToTime" id="ToTime3" ondblclick="callShowDiv(this);"><%=label.get("ToTime")%></label> <br>(HH:MM)</b>
		     </td>		    
		    </tr>
		    <%int m=1; %>
		    <s:iterator value="locList">		    
			      <tr>
			      <td class="sortableTD"><%=m%></td>
			      <td class="sortableTD"><s:property value="locCity" />&nbsp;</td>	      
			      <td class="sortableTD"><s:property value="trvlDtls" />&nbsp;</td>	
			      <td class="sortableTD"><s:property value="medium" />&nbsp;</td>	
			      <td class="sortableTD" align="center">
			       <s:property value="locFrmDate" />&nbsp;			        
			      </td>
			      <td class="sortableTD" align="center"><s:property value="locFromTime" />&nbsp;</td>	
			      <td class="sortableTD" align="center">
			      	<s:property value="locToDate" />&nbsp;			      	 	      
			      </td>	
			      <td class="sortableTD" align="center"><s:property value="locToTime" />&nbsp;</td>				      
			     </tr>
			      <%m++; %> 		     
		    </s:iterator>
		    <%if(m==1){ %>
		    	<tr>
		    	 <td colspan="9" align="center"><font color="red">No Data to display</font></td>
		    	</tr>
		    <%} %>	
		    
		   </table>
		 </td>
		</tr>
		 
		 <tr>
		  <td>
		   <table width="100%" class="formbg">
		    <tr>
		     <td colspan="5"><b>Advance Details</b></td>
		    </tr>
		    <tr>
		     <td class="formth" width="5%">
		     	<b><label class="set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></b>
		     </td>
		     <s:if test='%{hAppFor=="S"||hAppFor=="O"}'>
		     	<td class="formth" width="40%">
		     	 <b><label class="set" name="Empname" id="Empname0" ondblclick="callShowDiv(this);"><%=label.get("Empname")%></label></b>
		     	</td> 
		     </s:if>
		     <s:else>
		     	<td class="formth" width="40%">
		     	  <b><label class="set" name="guest" id="guest1" ondblclick="callShowDiv(this);"><%=label.get("guest")%></label></b>
		     	</td>
		     </s:else>
		     <td class="formth" width="15%">
		     	<b><label class="set" name="AdvAmount" id="AdvAmount" ondblclick="callShowDiv(this);"><%=label.get("AdvAmount")%></label></b>
		     </td>
		     <td class="formth" width="20%">
		     	<b><label class="set" name="PrePaymode" id="PrePaymode" ondblclick="callShowDiv(this);"><%=label.get("PrePaymode")%></label> </b>
		     </td>
		     <td class="formth" width="20%">
		     	<b><label class="set" name="ExpSetDate" id="ExpSetDate" ondblclick="callShowDiv(this);"><%=label.get("ExpSetDate")%></label><br>(DD-MM-YYYY)</b>
		     <font color="red">*</font> </td>
		    </tr>
		    <%int n=1; %>
		    <s:iterator value="advList">		     
			     <tr>
			      <td class="sortableTD"><%=n%></td>	
			      <s:if test='%{trvlApp.hAppFor=="S" || trvlApp.hAppFor=="O"}'>
			     	<td class="sortableTD"><s:property value="empName" /></td>
			     	</s:if>
			      <s:else> 
			     	<td class="sortableTD"><s:property value="guestName" /></td>
			      </s:else>
			      <td class="sortableTD" align="right">			      	
			      	<s:property value="advAmount" />&nbsp;
			      	<s:property value="currency"/>&nbsp;
			      </td>
			      <td class="sortableTD" align="left">
			      	<s:if test='%{payMode=="C"}'>
			      	 Cash
			      	</s:if>
			      	<s:if test='%{payMode=="Q"}'>
			      	 Cheque
			      	</s:if>
			      	<s:if test='%{payMode=="T"}'>
			      	 Transfer
			      	</s:if>
			      	&nbsp;
			      </td>
			      <td class="sortableTD" align="center"><s:property value="expStlmntDate" />&nbsp;</td>  
			     </tr>
			     <%n++;%>
		    </s:iterator>
		  </table>		  
		  </td>
		 </tr>
		 
		 <tr>
		  <td>
		     <table width="100%"  class="formbg">
		  	 <tr>
		     <td colspan="5"><b>Identity Details</b></td>
		    </tr>
		    <tr>
		     <td class="formth" width="5%">
		     	<label class="set" name="sno" id="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label>
		     </td>
			      <s:if test='%{hAppFor=="S" || hAppFor=="O"}'>
			     	<td class="formth" width="40%">
			     		<b><label class="set" name="Empname" id="Empname1" ondblclick="callShowDiv(this);"><%=label.get("Empname")%></label></b>
			     	</td>
			      </s:if>
			      <s:else>
			     	<td class="formth" width="40%">
			     		<b><label class="set" name="guest" id="guest" ondblclick="callShowDiv(this);"><%=label.get("guest")%></label></b>	     		
			     	</td>
			      </s:else>
		     <td class="formth" width="15%">
		     	<b><label class="set" name="IdproofName" id="IdproofName" ondblclick="callShowDiv(this);"><%=label.get("IdproofName")%></label></b>
		     </td>
		     <td class="formth" width="20%">
		     	<b><label class="set" name="IdNumer" id="IdNumer" ondblclick="callShowDiv(this);"><%=label.get("IdNumer")%></label></b>
		     </td>
		     <td class="formth" width="20%">
		     	<b><label class="set" name="IdComments" id="IdComments" ondblclick="callShowDiv(this);"><%=label.get("IdComments")%></label></b>
		     </td>		     
		    </tr>
		    <%int o=1;%> 
		    <s:iterator value="identityList">				   
				  <tr>
				     <td class="sortableTD" valign="top"><%=o%></td>			     
				     <s:if test='%{trvlApp.hAppFor=="S" ||trvlApp.hAppFor=="O"}'>
				     	<td class="sortableTD" valign="top"><s:property value="empName" /></td>
				     </s:if>
				      <s:else>
				     	<td class="sortableTD" valign="top"><s:property value="guestName" /></td>
				      </s:else> 
				      <td class="sortableTD" valign="top"> 
				       <s:property value="idProof"/>&nbsp;
				      </td> 
				      <td class="sortableTD" valign="top">
				      	<s:property value="idNumber"/>&nbsp;
				      </td>
				      <td class="sortableTD" valign="top">
				       <s:property value="comments"/>&nbsp;
				      </td>
				    </tr>			  
		    <%o++;%>
		    </s:iterator>
		  </table>
		  </td>	
		 </tr>
		 
		 <tr>
		  <td width="100%" class="formbg">
		   <table>
				 <tr>
				  <td width="15%">
				  	<label class="set" name="applicantComments" id="applicantComments" ondblclick="callShowDiv(this);"><%=label.get("applicantComments")%></label> :
				  </td>
				  <td width="85%">
				  	<s:property value="applComm" />
				  </td>
				 </tr>	
		   </table>
		  </td>
		 </tr>
		 
		 <tr>
		  <td><input type="button"  value="   Close   " class="token" onclick="window.close();" /></td>
		 </tr>
	 
		</table>

</s:form>