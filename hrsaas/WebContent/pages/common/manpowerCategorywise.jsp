<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>


<table width="100%">
						<tr>
							<td align="left">

								
							</td>
						</tr>
						<tr>
							<td valign="top" height="200" >
						         
					<div id="QW">
															<table width="100%" align="center" cellpadding="0" cellspacing="0">
											<tr>
												<td class="whitetable"><b>Total Employees:</b><s:property value="tot"/></td>
											</tr>
											<tr>
												<td>
													<cewolf:chart id="pie3d" title="" type="pie3D" legendanchor="east" showlegend="false">
														<cewolf:gradientpaint>
										            		<cewolf:point x="0" y="0" color="#FFFFFF" />
										            	</cewolf:gradientpaint>
														<cewolf:data>
															<cewolf:producer id="pieData2" />
														</cewolf:data>
														
													</cewolf:chart> 
													<cewolf:img chartid="pie3d" renderer="/cewolf" width="300" height="150"></cewolf:img> 
													<br>
												</td>
											</tr>							
										</table>						           		
					
					</div>
					
								
							</td>
						</tr>
						
					</table>	
			
