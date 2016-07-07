package org.paradyne.model.payroll.incometax;

import java.util.HashMap;
import org.paradyne.lib.ModelBase;


public class EmployeeTaxCalculation extends ModelBase {
	/**
	 * METHOS TO CALCULATE EMPLOYEE INCOME TAX AMOUNT
	 *empObj[0][0]=EMPLOYEE CODE
	 *empObj[0][1]=BONUS AMOUNT
	 *empObj[0][2]=GENDER
	 *String fromYear=2011
	 *String toYear=2012
	 *METHOD RETURN 
	 *obj[0][0]=EMPLOYEE CODE
	 *obj[0][1]=BONUS AMOUNT
	 *obj[0][2]=TAX AMOUNT
	 */
	
	public Object[][]getEmpSlabTaxAmount(Object[][]empObj,String fromYear,String toYear){
		Object[][]obj=null;
		//GET EMPLOYEE INCOME TAX SLAB
		String slabQUery="SELECT TAX_FROM_AMOUNT,TAX_TO_AMT,TAX_PERCENT ,TAX_EMP_TYPE FROM HRMS_TAX_SLAB WHERE  TAX_FROM_YEAR="+fromYear+" AND TAX_to_YEAR="+toYear+" " 
						+"	ORDER BY TAX_EMP_TYPE,TAX_PERCENT ";
		HashMap<String, Object[][]>slabMap=getSqlModel().getSingleResultMap(slabQUery, 3, 2);
		//GET EMPLOYEE TOTAL APPLICABLE INCOME 
		String taxableQuery="SELECT NVL(TDS_TAXABLE_INCOME,0) ,TDS_EMP_ID FROM HRMS_TDS WHERE TDS_FROM_YEAR="+fromYear+" AND TDS_TO_YEAR="+toYear+" ";
		HashMap<String, Object[][]>taxableIncomeMap=getSqlModel().getSingleResultMap(taxableQuery, 1, 2);
		
		
		if(empObj!=null && empObj.length>0){
			//START FOR LOPP FOR EMPLOYEE
			obj=new Object[empObj.length][3];
			for (int i = 0; i < empObj.length; i++) {
				double empTotalTaxableAMount=0.0;
				double empTaxableAMount=0;
				double empBonusAmount=0.0;
				double empTax=0.0;
				double empTaxPercentage=0.0;
				//ADD EMPLOYEE BONUS AMOUNT INTO EMPLOYEE TOTAL TAXABLE AMOUNT
				empBonusAmount=Double.parseDouble(String.valueOf(empObj[i][1]));
				//INITIATE THE VALUES
				obj[i][0]=empObj[i][0];//EMPLOYEE CODE
				obj[i][1]=empBonusAmount;//EMPLOYEE BONUS AMOUNT
				obj[i][2]=empTax;//EMPLOYEE TAX
				
				//GET EMPLOYEE TAXABLE INCOME 
				if(taxableIncomeMap!=null && taxableIncomeMap.size()>0){
					Object[][]empIncomeObj=taxableIncomeMap.get(String.valueOf(empObj[i][0]));
					if(empIncomeObj!=null && empIncomeObj.length>0){
						//GET EMPLOYEE TAXABLE AMOUNT
						empTaxableAMount=Double.parseDouble(String.valueOf(empIncomeObj[0][0]));
					}
				}
				
				
				empTotalTaxableAMount=empTaxableAMount+empBonusAmount;
				//IDENTIFY SLAB FOR DEDUCT TAX AMOUNT
				
				
				if(slabMap!=null && slabMap.size()>0){
					Object[][]slabObj=slabMap.get(String.valueOf(empObj[i][2]));
					if(slabObj!=null && slabObj.length>0){
						for (int j = 0; j < slabObj.length; j++) {
							double fromAmt=Double.parseDouble(String.valueOf(slabObj[j][0]));
							double toAmt=Double.parseDouble(String.valueOf(slabObj[j][1]));
							double taxPercentage=Double.parseDouble(String.valueOf(slabObj[j][2]));
							
							if((empTaxableAMount>=fromAmt && empTaxableAMount<=toAmt)&&
									(empTotalTaxableAMount>=fromAmt && empTotalTaxableAMount<=toAmt)){
								empTaxPercentage=taxPercentage;
								empTax=(empBonusAmount*empTaxPercentage)/100;
								empBonusAmount=empBonusAmount-empTax;				
								obj[i][0]=empObj[i][0];//EMPLOYEE CODE
								obj[i][1]=empBonusAmount;//EMPLOYEE BONUS AMOUNT
								obj[i][2]=empTax;//EMPLOYEE TAX
							}	
							else if((empTaxableAMount>=fromAmt && empTaxableAMount<=toAmt)){
								empTaxPercentage=taxPercentage;
								double taxNextPercentage=0.0;
								try {
									taxNextPercentage = Double.parseDouble(String
											.valueOf(slabObj[j + 1][2]));
								} catch (Exception e) {
									taxNextPercentage=0.0;
								}
								double empDiffAmt=toAmt>empTaxableAMount?(toAmt-empTaxableAMount):0;
								double applicableAmt=empBonusAmount-empDiffAmt;
								double first=(empDiffAmt*empTaxPercentage)/100;
								double second=(applicableAmt*taxNextPercentage)/100;
								empTax=first+second;
								empBonusAmount=empBonusAmount-empTax;	
								obj[i][0]=empObj[i][0];//EMPLOYEE CODE
								obj[i][1]=empBonusAmount;//EMPLOYEE BONUS AMOUNT
								obj[i][2]=empTax;//EMPLOYEE TAX
							}
						}
					}
					else{
						obj[i][0]=empObj[i][0];//EMPLOYEE CODE
						obj[i][1]=empBonusAmount;//EMPLOYEE BONUS AMOUNT
						obj[i][2]=empTax;//EMPLOYEE TAX
					}
				}
				else{
					//empTax=(empBonusAmount*empTaxPercentage)/100;
					//empBonusAmount=empBonusAmount-empTax;
					obj[i][0]=empObj[i][0];//EMPLOYEE CODE
					obj[i][1]=empBonusAmount;//EMPLOYEE BONUS AMOUNT
					obj[i][2]=empTax;//EMPLOYEE TAX
				}
				
			}//END OF FOR LOOP			
			
		}	
		return obj;
	}
	
}
