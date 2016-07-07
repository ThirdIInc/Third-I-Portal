<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="CandidateEvolMaster" method="post"
	name="CandidateEvolMaster" validate="true" id="paraFrm" theme="simple">
	<table class="bodyTable" width="100%">
		<tr>
			<td width="100%" align="center" colspan="6" class="pageHeader">Candidate
			Evaluation</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
			
					<td  width="25%" colspan="1">Search:</td>
					<td width="75%" colspan="3"><img src="../pages/images/search.gif" height="18" align="absmiddle" width="18"
					onclick="javascript:callsF9(500,325,'CandidateEvolMaster_f9loanNo.action');">
					
				</td>
				</tr>
			<tr>
			
			<td width="40%" colspan="1">Candidate Name:</td>
			<td width="45" colspan="1"><s:textfield size="50" name="candname" /> </td>
			<td width="15" colspan="2"><img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18" theme="simple"
				onclick="javascript:callsF9(500,325,'CandidateEvolMaster_f9action.action'); "></td>
		</tr>
		<tr>
			
					<td  width="25%" colspan="1">Requisition Code:</td>
					<td width="75%" colspan="3"><s:textfield size="10" name="reqcode" /> </td>
					
				
				</tr>
		<tr>
			<td width="100%" colspan="4" class="sectionHead">Requisition Details</td>
		</tr>
	
			<tr>
			<td width="15%" colspan="1">Division:</td>
			<td width="35%" colspan="1"><s:textfield size="30" name="divname" /></td>
			<td width="15%" colspan="1">Branch:</td>
			<td width="35%" colspan="1"><s:textfield size="30" name="branchname" /></td>
			</tr>
			<tr>
			<td width="25%" colspan="1">Department:</td>
			<td width="25%" colspan="1"><s:textfield size="30" name="deptname" /></td>
			<td width="25%" colspan="1">Designation:</td>
			<td width="25%" colspan="1"><s:textfield size="30" name="desgname" /></td>
			
			</tr>
			<tr>
			<td width="25%" colspan="1" >Job Description:</td>
			<td colspan="1" width="25%"><s:textarea 
				theme="simple" name="jobDesc" rows="2"  cols="50"/></td>
				<td width="25%" colspan="1" >Skill set:</td>
			<td colspan="1" width="25%"><s:textarea 
				theme="simple" name="skill" rows="2"  cols="50"/></td>
		</tr>	
		<tr>
			<td width="25%" colspan="1" >Qualification Required:</td>
			<td colspan="1" width="25%"><s:textarea 
				theme="simple" name="qualreq" rows="2"  cols="50"/></td>
				<td width="25%" colspan="1" >Experience Required:</td>
			<td colspan="1" width="25%"><s:textarea 
				theme="simple" name="expreq" rows="2"  cols="50"/></td>
		</tr>	
		<tr>
		<td width="75%" colspan="3">&nbsp;</td>
		<td width="50%" colspan="1"><a href='round1.htm'>
        <font style="font-size: 8pt" >Round I details</font></a></td>
		<tr><td colspan="4"><hr></td></tr>
	<tr>
		<td width="25%" colspan="1">Round :</td>
			<td width="25%" colspan="1"><s:select name="round" headerKey="1"
				headerValue="Round II" cssStyle="width:125"
				list="#{'III':'Round III','IV':'Round IV','V':'Round V'}" /></td>
			<td width="15%" colspan="1">Interviewer Name:</td>
			<td width="15" colspan="1"><s:textfield size="25" name="candname" /> </td>
			<td width="20%"><img
				src="../pages/images/search.gif" height="18" align="absmiddle"
				width="18" theme="simple"
				onclick="javascript:callsF9(500,325,'CandidateEvolMaster_f9action.action'); "></td>
	
		</tr>
			<tr>
			<td colspan="1"  width="15%" >Interview Date :</td>
			<td colspan="1"  width="85%"><s:textfield   size="15" 
						theme="simple" name="interdate"  value="%{loandate}"/><s:a
				href="javascript:NewCal('interdate','DDMMYYYY');">
				<img src="../pages/images/cal.gif" class="iconImage"  height="16" align="absmiddle" width="16">
			</s:a>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="4" class="sectionHead">Skill Set Evaluation</td>
		</tr>
		<tr>
			<td class="headercell" width="40%" colspan="2">Skills</td>
			<td class="headercell" width="20%"colspan="1">Rating</td>
			<td class="headercell" width="40%"colspan="1">Comments</td>
		</tr>
		
			<tr>
        <td width="40%" colspan="2" class="bodycell"><span style="font-weight: 400">Core Java</span></td>
        <td width="20%" colspan="1"class="bodycell"><s:select name="rating"list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" /></td>
			
        <td width="40%" colspan="1"class="bodycell"><span style="font-weight: 400">Basic Concept clear </span></td>
        
      </tr>	
      <tr>
        <td width="40%" colspan="2" class="bodycell"><span style="font-weight: 400">J2EE</span></td>
        <td width="20%" colspan="1"class="bodycell"><s:select name="rating"list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" /></td>
			
        <td width="40%" colspan="1"class="bodycell"><span style="font-weight: 400">Need of improvement </span></td>
        
      </tr>	
      <tr>
			<td width="100%" colspan="4" class="sectionHead">Personal Assessment Details</td>
		</tr>
		<tr>
			<td class="headercell" width="40%" colspan="2">Personal Assessment</td>
			<td class="headercell" width="20%"colspan="1">Rating</td>
			<td class="headercell" width="40%"colspan="1">Comments</td>
		</tr>
		
			<tr>
        <td width="40%" colspan="2" class="bodycell"><span style="font-weight: 400">Adaptability</span></td>
        <td width="20%" colspan="1"class="bodycell"><s:select name="rating"list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" /></td>
			
        <td width="40%" colspan="1"class="bodycell"><span style="font-weight: 400">Excellent  </span></td>
        
      </tr>	
      <tr>
        <td width="40%" colspan="2" class="bodycell"><span style="font-weight: 400">Communication</span></td>
        <td width="20%" colspan="1"class="bodycell"><s:select name="rating"list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" /></td>
			
        <td width="40%" colspan="1"class="bodycell"><span style="font-weight: 400">Average  </span></td>
        
      </tr>	
      <tr>
        <td width="40%" colspan="2" class="bodycell"><span style="font-weight: 400">Personality</span></td>
        <td width="20%" colspan="1"class="bodycell"><s:select name="rating"list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" /></td>
			
        <td width="40%" colspan="1"class="bodycell"><span style="font-weight: 400">Handsome,smart</span></td>
        
      </tr>	
      <tr>
			<td width="100%" colspan="4" class="sectionHead">Technical Skills Details</td>
		</tr>
		<tr>
			<td class="headercell" width="40%" colspan="2">Technical Skills</td>
			<td class="headercell" width="20%"colspan="1">Rating</td>
			<td class="headercell" width="40%"colspan="1">Comments</td>
		</tr>
		
			<tr>
        <td width="40%" colspan="2" class="bodycell"><span style="font-weight: 400">Database</span></td>
        <td width="20%" colspan="1"class="bodycell"><s:select name="rating"list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" /></td>
			
        <td width="40%" colspan="1"class="bodycell"><span style="font-weight: 400">Good in SQL,Oracle </span></td>
        
      </tr>	
      <tr>
        <td width="40%" colspan="2" class="bodycell"><span style="font-weight: 400">Testing</span></td>
        <td width="20%" colspan="1"class="bodycell"><s:select name="rating"list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" /></td>
			
        <td width="40%" colspan="1"class="bodycell"><span style="font-weight: 400">Average  </span></td>
        
      </tr>	
      <tr>
        <td width="40%" colspan="2" class="bodycell"><span style="font-weight: 400">OS</span></td>
        <td width="20%" colspan="1"class="bodycell"><s:select name="rating"list="#{'1':'1','2':'2','3':'3','4':'4','5':'5','6':'6','7':'7','8':'8','9':'9','10':'10'}" /></td>
			
        <td width="40%" colspan="1"class="bodycell"><span style="font-weight: 400">Good knowledge of LINUX,UNIX</span></td>
        
      </tr>	
      
		<tr>
			<td width="35%" colspan="1" >Overall Comments:</td>
			<td colspan="2" width="65%"><s:textarea 
				theme="simple" name="jobDesc" rows="3"  cols="50"/></td>
		</tr>
		<tr>
		<td width="25%" colspan="1">Status:</td>
			<td width="25%" colspan="1"><s:select name="status" headerKey="1"
				headerValue="Pending" cssStyle="width:125"
				list="#{'S':'Selected','R':'Rejected','H':'On-Hold','B':'Black-listed'}" /></td>	
	
				<td width="10%" colspan="1" >More Round Required :</td>
				<TD width="20%"  colspan="1">Yes <input type ="radio" name="roundrequired" />No <input type ="radio" name="roundrequired" /></td>
				
				</tr>
		<tr><td>&nbsp;&nbsp;</td>
		</tr>
		<tr align="center">
		
			<td colspan="4" width="100%">
			
			 <s:submit cssClass="pagebutton"  
				action="CandidateEvolMaster_submit" theme="simple" value=" Submit "  />
			
				
				
				 </td>
		</tr>




	</table>
</s:form>


<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>