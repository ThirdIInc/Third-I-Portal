
<%@ taglib prefix="s" uri="/struts-tags" %>

			
				<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
					
					
					
					<s:iterator value="recruitList" status="stat">  	
					
					
					<tr onMouseOver="this.className='cellbg'"
				onMouseOut="this.className='color'" class="color"><td class="whitetable1"><a href="<s:property value="recruitLink"/>"><img src="../pages/images/report.gif" class="iconImage"
				height="15" width="15">&nbsp;<s:property value="recruitName"/></a></td></tr>
				
					
					</s:iterator>
										
				</table>
				
			
