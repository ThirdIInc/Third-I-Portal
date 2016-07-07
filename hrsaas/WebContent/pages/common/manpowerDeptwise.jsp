
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>


<table width="100%" align="center" cellpadding="0" cellspacing="0">
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
								<cewolf:img chartid="verticalBar3D1" renderer="/cewolf" width="700" height="300" />
												
		</td>												

	</tr>
</table>


