<%@ taglib prefix="s" uri="/struts-tags" %>

  <table class="bodyTable" width="100%">
  	<tr>
  		<td  width="100%" colspan="5"  align="center" ><b><U>APPLICATION FOR LEAVE - OFFICERS </U></td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
    	
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">1.&nbsp;&nbsp;</td>
    	<td width="25%">Employee Name :</td>
    	<td width="50%" colspan="3"><s:property value="empName"/></td>
    	<td width="25%"></td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">2.&nbsp;&nbsp;</td>
    	<td width="25%">Rank :</td>
    	<td width="25%"><s:property value="department"/></td>
    	<td width="25%">Center :</td>
    	<td width="25%"><s:property value="center"/></td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">3.&nbsp;&nbsp;</td>
    	<td width="25%">Prefix :</td>
    	<td width="25%"><s:property value="prefix"/></td>
    	<td width="25%">Suffix :</td>
    	<td width="25%"><s:property value="suffix"/></td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">4.&nbsp;&nbsp;</td>
    	<td width="25%">Total Leave Days :</td>
    	<td width="25%"><s:property value="leaveTotalDays"/></td>
    	<td width="25%"></td>
    	<td width="25%"></td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">5.&nbsp;&nbsp;</td>
    	
    	<td width="25%">Leave Address :</td>
    	<td width="75%" colspan="3"><s:property value="contactDetails"/></td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">6.&nbsp;&nbsp;</td>
    	<td width="25%">Nearest Naval Unit:</td>
    	<td width="75%" colspan="3"><s:property value="medicalCert"/></td>
    	
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">7.&nbsp;&nbsp;</td>
  		<td colspan="4" >Ration to be :</td></tr>
	<tr><td width="10">&nbsp;</td>
		<td>&nbsp;</td>
		<td colspan="3"><FONT size="6">.</FONT><s:property value="rationRW"  /> &nbsp;&nbsp;&nbsp; To draw RIK(S/V)/Victg in WNC(O) Mess</td>
	</tr>
	<tr><td width="10">&nbsp;</td>
		<td >&nbsp;</td>
		<td colspan="2"><FONT size="6">.</FONT><s:property value="rationCHQ" />&nbsp;&nbsp;&nbsp;
		To draw CLR wef :
		<s:property value="wefDate" /></td>
		<td colspan="1" ></td>
	</tr>
	<tr><td width="10">&nbsp;</td>
		<td>&nbsp;</td>
		<td colspan="3" >&nbsp;&nbsp;&nbsp;&nbsp;Vide HQWNC Letter  :
		<s:property value="letterNo" /></td>
		</tr>
		<tr><td colspan="2" >&nbsp;</td>
		<td  colspan="3" >&nbsp;&nbsp;&nbsp;&nbsp;Letter Date  :
		<s:property value="letterDate" /></td>
	</tr>
	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">8.&nbsp;&nbsp;</td>
  	    <td width="25%">Comments :</td>
    	<td width="75%" colspan="3"><s:property value="comments"/></td>
    </tr>
  	
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">9.&nbsp;&nbsp;</td>
    	<td colspan="4">Leave Detail :</td>
  	</tr>
	 <tr><td colspan="5">
			 <table class="bodyTable" width="100%">
			  	<tr>  			
					<td class="bodyCell" width="30%"><b><font size="1" >Leave Type</font></td>
					<td class="bodyCell" width="20%"><b><font size="1" >Leave From Date</font></td>
					<td class="bodyCell" width="20%"><b><font size="1" >Leave To Date</font></td>
					<td class="bodyCell" width="10%"><b><font size="1" >Leave Days</font></td>
				</tr>	
				
				<s:iterator value="att" status="stat">            	
				 <tr>	 
				      <td class="bodyCell" width="30%"><s:property value="levType"/></td>
			          <!--<td class="bodyCell" width="10%"><s:property value="levOpeningBalance"/></td>
				      <td class="bodyCell" width="10%"><s:property value="levClosingBalance"/></td>
				      --><td class="bodyCell" width="20%"><s:property value="leaveFromDtl"/></td>
					  <td class="bodyCell" width="20%"><s:property value="leaveToDtl"/></td>
					  <td class="bodyCell" width="10%"><s:property value="levTotal"/></td>
				  </tr>           
				</s:iterator>
			</table>
		</td>
	</tr>	
	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
	<tr><td width="10">10.&nbsp;&nbsp;</td>
  		<td>Status :</td>
		 <td colspan="3" align="left" ><s:property value="status"/></td>
  	</tr>
  	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td width="10">11.&nbsp;&nbsp;</td>
  		<td colspan="4" >Recommended By/Approved By:-</td>
  	</tr>
	<tr><td colspan="5"><!--	
				<table class="bodyTable" width="100%">
				  	<tr>  			
						<td align="center" colspan="3"><b><font size="1" ><U>Recommended /Not Recommended & Forwarded</U></font></td>			
					</tr>
					<tr>  			
						<td align="center" colspan="3">&nbsp;</td>			
					</tr> 	     	
					<tr> <td>Name :</td>	 
					      <td colspan="2" align="left" ><s:property value="assesedName"/></td>
					  </tr>  
					  <tr> <td>Comment :</td>
					      <td colspan="2" align="left" ><s:property value="assesedComment"/></td>
					  </tr>  
					  <tr> <td>Date :</td>
					      <td colspan="2" align="left" ><s:property value="assesedDate"/></td>
					  </tr>         
				</table>	
		-->
			<div bgcolor="#FFFFFF" align="center">
						<table width="100%">
							<tr>
								<td style="background-color:gray" >Officer Name</td>
								<td style="background-color:gray"  >Comments</td>
								<td style="background-color:gray"  >Date</td>
							</tr>
							<s:iterator value="leaveStatus" >
								<tr>
									<td  ><s:property  value="assesedName" /></td>
									<td width="50%" ><s:property  value="assesedComment" /></td>
									<td ><s:property  value="assesedDate" /></td>
								</tr>
							</s:iterator>
						</table>
				   </div>
		</td>
	</tr>
	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
  	<tr><td colspan="5">	
				<!--<table class="bodyTable" width="100%">
				  	
					<tr>  			
						<td align="center" colspan="3"><b><font size="1" ><U>Approved /Not Approved as per Current Orders</U></font></td>			
					</tr>	
					<tr>  			
						<td align="center" colspan="3">&nbsp;</td>			
					</tr>    	
					<tr> <td>Name :</td>	 
					      <td colspan="2" align="left" ><s:property value="approverName"/></td>
					  </tr>  
					  <tr> <td>Comment :</td>
					      <td colspan="2" align="left" ><s:property value="approverId"/></td>
					  </tr>  
					  <tr> <td>Date :</td>
					      <td colspan="2" align="left" ><s:property value="forwardName"/></td>
					  </tr>
					  <tr><td colspan="3" >&nbsp;</td></tr> 
					  <tr> <td>Status :</td>
					      <td colspan="2" align="left" ><s:property value="status"/></td>
					  </tr>       
				</table>	
		--></td>
	</tr>
	<tr>
    	<td width="100%" colspan="5">&nbsp;</td>
  	</tr>
	<!--<tr>
    	<td width="100%" colspan="5" align="right"><s:submit cssClass="pagebutton" value="Print"/></td>
  	</tr>
  --></table>
