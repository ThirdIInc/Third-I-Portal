
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>


<table width="100%" align="center" cellpadding="0" cellspacing="0">

				
					
						  
											<tr>
												<td class="whitetable"><b>Attendance Status:</b></td>
											</tr>
											<tr>
												<td>
				<cewolf:chart id="pie3d" title="" type="pie3D" legendanchor="west" showlegend="true">
					<cewolf:gradientpaint>
							<cewolf:point x="0" y="0" color="#FFFFFF" />
					</cewolf:gradientpaint>
					
					<cewolf:data>
							<cewolf:producer id="pieData" />
					</cewolf:data>
					
					<cewolf:chartpostprocessor id="meterRanges">
					</cewolf:chartpostprocessor>
				</cewolf:chart> 
				<cewolf:img chartid="pie3d" renderer="/cewolf" width="400" height="200"></cewolf:img> 
							
			</td>
											</tr>							
										
	</table>
			
				
	