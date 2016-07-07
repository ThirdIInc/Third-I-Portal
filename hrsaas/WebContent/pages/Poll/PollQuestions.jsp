<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="PollQuestions" validate="true" id="paraFrm"
	theme="simple">
	<table class="bodyTable" width="100%" >
		<tr>
			<td width="100%" colspan="4" class="pageHeader" align="center">Poll's Question</td>
		</tr>
		<tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
		<tr>
			
			<td width="20%"colspan="1">Date  :</td>
			<td width="75%"colspan="3"><s:textfield size="20"
				name="Date" /> <s:a
				href="javascript:NewCal('Date','DDMMYYYY');">
<img src="../pages/images/cal.gif" class="iconImage"   height="16" align="absmiddle"
					width="16">
			</s:a></td>
			</tr>
		<tr>
			<td width="20%"colspan="1">Question  :</td>
			<td width="80%"colspan="1"><s:textfield size="75" name="quesname" /> </td>
			</tr>
		<tr>
			<td width="20%"colspan="1">Options :</td>
			<td width="85%"colspan="1"><s:textfield size="75" name="options" />
		<input type="button"  onclick="addRow('PollQuestions_add.action')" value=" Add " />
			</td>
		</tr>	
		
		
	</table>
	<table class="bodyTable" width="100%">
  		
  		<tr>
  			<td class="headercell" width="50%">Options</td>  			
  			
	</tr>
	<tr>
        <td width="50%" class="bodycell"><span style="font-weight: 400">Indian Coach</span></td>
    <tr>
    <tr>
        <td width="50%" class="bodycell"><span style="font-weight: 400">Foreign Coach</span></td>
    <tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr><tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr><tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr><tr>
			<td width="100%" colspan="4">&nbsp;</td>
		</tr>
	<tr align="center">
  		<td colspan="6">
  		
  		<s:submit cssClass="pagebutton"   action="QuestionnaireMaster_save" theme="simple"  value="   Save   " />&nbsp;
  		<s:submit cssClass="pagebutton"   action="QuestionnaireMaster_reset" theme="simple"   value="  Reset  " />&nbsp;
  		<s:submit cssClass="pagebutton"   action="QuestionnaireMaster_delete" theme="simple"   value="  Delete  " />&nbsp;  		
  		</td>
		</tr>
	
	</table>
</s:form>
<script type="text/javascript" src="../pages/common/datetimepicker.js">

</script>


<script type="text/javascript">

</script>
