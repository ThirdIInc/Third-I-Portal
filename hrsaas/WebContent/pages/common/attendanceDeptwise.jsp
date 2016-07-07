
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>


<table width="100%" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td width = "50%">
				
					
						         <div style=" solid #B1C8E3; background-color:white; margin-bottom: 1em; padding: 10px">
									<div >
										<table width="100%" align="center" cellpadding="0" cellspacing="0">
											
											<tr>
												<td>
													<cewolf:chart id="pie3d" title="" type="pie" legendanchor="west" showlegend="false">
														<cewolf:gradientpaint>
										            		<cewolf:point x="0" y="0" color="#FFFFFF" />
										            	</cewolf:gradientpaint>
														<cewolf:data>
															<cewolf:producer id="pieData1" />
														</cewolf:data>
													
													</cewolf:chart> 
													<cewolf:img chartid="pie3d" renderer="/cewolf" width="350" height="150"></cewolf:img> 
													<br>
												</td>
											</tr>							
										</table>						           		
									</div>
								</div>
							</td>
						</tr>
						<tr>
						
				
	
	</tr>
	</table>
			
				
	