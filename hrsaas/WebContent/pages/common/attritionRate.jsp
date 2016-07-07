
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>


<table width="100%" align="center" cellpadding="0" cellspacing="0">
<tr>
			<td colspan="2" width="100%" valign="top">
			
					<table width="50%" border="0" align="left" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
						
						
					<tr>					    
							<td>
								
										<table bgcolor="#FFFFFF">
											<tr>
												<td>
													<cewolf:chart id="verticalBar3D1" title="" type="verticalBar3D" xaxislabel="" yaxislabel="" showlegend="false">
															<cewolf:gradientpaint>
										            		<cewolf:point x="0" y="0" color="#FFFFFF" />
										            	</cewolf:gradientpaint>
										            	<cewolf:chartpostprocessor id="meterRanges">
															</cewolf:chartpostprocessor>
														<cewolf:data>
															<cewolf:producer id="categoryData" />
														</cewolf:data>
													</cewolf:chart> 
													<cewolf:img chartid="verticalBar3D1" renderer="/cewolf" width="300" height="183" />
												</td>
											</tr>
										</table>
									
								
							</td>												
						</tr>
					</table>
				
			</td>
		</tr>
		
		
		
	</table>


