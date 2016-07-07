<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>

<table width="100%" border="0" align="center" cellpadding="0"
	cellspacing="0" bgcolor="#FFFFFF">

	

	<tr align="center">
			<td align="center" width="100%">
				<cewolf:chart id="meterChart" title="" type="dial" legendanchor="west" showlegend="true">
					<cewolf:gradientpaint>
							<cewolf:point x="0" y="0" color="#FFFFFF" />
					</cewolf:gradientpaint>
					
					<cewolf:data>
							<cewolf:producer id="meterData" />
					</cewolf:data>
					
					<cewolf:chartpostprocessor id="meterRanges">
					</cewolf:chartpostprocessor>
				</cewolf:chart> 
				<cewolf:img chartid="meterChart" renderer="/cewolf" width="300" height="250"></cewolf:img> 
							
			</td>
	</tr>	

</table>




