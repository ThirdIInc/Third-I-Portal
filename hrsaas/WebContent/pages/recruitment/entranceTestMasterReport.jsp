<%@ taglib prefix="s" uri="/struts-tags"%>

<table>	
	       <tr>
		<td width="100%" colspan="6" align="center"><b>EntranceTestMaster Report</b></td>
	        </tr>
	        
	        <tr>
	        <td>&nbsp;</td>
	        </tr>
	
	</table>

<table class="bodyTable" width="100%" border="1">

<tr>
		<td class="bodyCell" width="10%"><b>Exam Code: </b></td>
		<td class="bodyCell" width="30%"><b>Exam Name </b></td>
		<td class="bodyCell" width="30%"><b>Total Marks </b></td>
		<td class="bodyCell" width="30%"><b> Passing Marks </b></td>
	
		
</tr>

<s:iterator value="distList" status="stat">
		<tr>
			<td class="bodyCell" width="10%" nowrap="nowrap"><s:property value="entrance.examCode" /></td>
			<td class="bodyCell" width="30%"><s:property value="entrance.examName" /></td>
			<td class="bodyCell" width="30%"><s:property value="entrance.totalMarks" /></td>
			<td class="bodyCell" width="30%"><s:property value="entrance.passMarks" /></td>
			
			
		</tr>
	</s:iterator>
	
</table>


