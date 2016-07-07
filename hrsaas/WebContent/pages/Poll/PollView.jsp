<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="PollQuestions" validate="true" id="paraFrm"
	theme="simple">
	<table class="bodyTable" width="100%" >
		<tr>
			<td width="100%" colspan="4" class="pageHeader" align="center">Poll View </td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
			
		<td width="100%" colspan="6" class="sectionHead">Yesterday's Poll Result</td>
			</tr>
			
		<tr>
			<td width="25%">Question  :Who should be Indian cricket team's Coach? </td>
		</tr>
		
	</table>
	<table class="bodyTable" width="100%">
  		<tr>
  		
  			<td class="headercell" width="80%">Options</td> 			
  			<td class="headercell" width="10%">Percentage</td>  			
  		</tr>
  		<tr>
       
        <td width="80%" class="bodycell"><span style="font-weight: 400">Indian Coach</span></td>
    	<td width="10%" class="bodycell"><span style="font-weight: 400">37</span></td>
      <tr>
      <tr>
       <td width="80%" class="bodycell"><span style="font-weight: 400">Foreign Coach</span></td>
    	<td width="10%" class="bodycell"><span style="font-weight: 400">63</span></td>
      <tr>
    
	
	
	<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr><tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr><tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr><tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
			
		<td width="100%" colspan="6" class="sectionHead">Express Yourself</td>
			</tr>
			<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
			<tr>
			<td width="100%">Question:Who is well suited for Indian cricket team's Test Captain's post?</td>
			</tr>
			</table>
			<table class="bodyTable" width="100%">
  		<tr>
  			<td class="headercell" width="5%">Sr.no</td>  			
  			<td class="headercell" width="10%">Select</td> 
  			<td class="headercell" width="85%">Options</td>  			
  			 			
  		</tr>
  		<tr>
        <td width="5%" class="bodycell"><span style="font-weight: 400">1</span></td>
        <td  class="bodycell">&nbsp;<input type="radio" name="select" value="select" ></td>
        <td width="50%" class="bodycell"><span style="font-weight: 400">Sachin Tendulkar</span></td>
        </tr>
        <tr>
        <td width="5%" class="bodycell"><span style="font-weight: 400">2</span></td>
        <td  class="bodycell">&nbsp;<input type="radio" name="select" value="select" ></td>
        <td width="50%" class="bodycell"><span style="font-weight: 400">Dhoni</span></td>
        </tr>
        <tr>
        <td width="5%" class="bodycell"><span style="font-weight: 400">3</span></td>
        <td  class="bodycell">&nbsp;<input type="radio" name="select" value="select" ></td>
        <td width="50%" class="bodycell"><span style="font-weight: 400">Anil Kumble</span></td>
        </tr>
  		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
	
			
			
	<tr align="center">
  		<td colspan="6">
  		
  		
  		<s:submit cssClass="pagebutton"   action="QuestionnaireMaster_reset" theme="simple"   value="  Submit " />&nbsp;
  		 		
  		</td>
		</tr>
	
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">

</script>
