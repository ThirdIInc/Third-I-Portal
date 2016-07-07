/**
 * 
 */
package org.paradyne.model.admin.master;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.model.payroll.incometax.CommonTaxCalculationModel;
import org.paradyne.bean.admin.master.PFParameterMaster;

//import com.crystaldecisions.reports.reportdefinition.fo;
import com.ibm.icu.util.Calendar;

import com.sun.corba.se.impl.copyobject.FallbackObjectCopierImpl;


/**
 * @author ritac
 *
 */
public class PFParameterModel extends ModelBase {
	
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(PFParameterModel.class);
	NumberFormat formatter = new DecimalFormat("#0.00");
	PFParameterMaster pfParam;
	
	public String savePF(PFParameterMaster pfParam, String path)
	{
		
		Object data[][]=new Object[1][15];
		
		data[0][0]=pfParam.getEffDate();		
		data[0][1]=pfParam.getDeduction();
		data[0][2]=pfParam.getEmpPF();
		data[0][3]=pfParam.getEmpFamilyPF();
		data[0][4]=pfParam.getPensionFund();
		data[0][5]=pfParam.getCompFund();
		//data[0][6]=pfParam.getPfType();
		data[0][6]=pfParam.getDebitCode();
		data[0][7]=pfParam.getPfFormula();
		if(pfParam.getPfadmincharge().equals(""))
			data[0][8]=0.0;
		else
			data[0][8]=pfParam.getPfadmincharge();
		if(pfParam.getEdlicontribution().equals(""))
			data[0][9]=0.0;
		else
		data[0][9]=pfParam.getEdlicontribution();
		
		if(pfParam.getPfedlicharge().equals(""))
			data[0][10]=0.0;
		else
			data[0][10]=pfParam.getPfedlicharge();
		/*
		if(pfParam.getPfMinAmt().equals(""))
			data[0][12]=0.0;
		else
			data[0][12]=pfParam.getPfMinAmt();
		*/
		//Rules data
		if(pfParam.getDeductCriteria().equals("0"))
		{
			data[0][11] = 0;
			pfParam.setFlag1("true");
		}
		else{
		data[0][11] =pfParam.getPfDedEmolument();
		pfParam.setFlag1("false");
		}
		data[0][12] = pfParam.getDeductCriteria();
		data[0][13] = pfParam.getNoMaxLimitChk();
		data[0][14] = pfParam.getPfEmoMaxLimit();
		boolean result=getSqlModel().singleExecute(getQuery(1), data);
		
		if(result) {
			
			String query = "SELECT MAX(PF_CODE) FROM HRMS_PF_CONF";
			Object obj[][] = getSqlModel().getSingleResult(query);
			pfParam.setPfCode(checkNull(String.valueOf(obj[0][0])));
				xml_pfparameter(path);
				/**
				 * Following code calculates the tax
				 * and updates tax process
				 */
				/*try {
					CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
					taxmodel.initiate(context, session);
					logger.info("I m calling tax calculation method");
					Object[][] empList = getSqlModel().getSingleResult("SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'");
					Calendar cal = Calendar.getInstance();
					cal.setTime(new Date());
					int fromYear = Integer.parseInt(String.valueOf(cal.get(Calendar.YEAR)));
					int month = Integer.parseInt(String.valueOf(cal.get(Calendar.MONTH)));
					logger.info("month in pf===="+month);
					logger.info("fromYear in pf===="+fromYear);
					if(month <= 2)
						fromYear--;
					if(empList != null && empList.length > 0)
						taxmodel.calculateTax(empList,String.valueOf(fromYear),String.valueOf(fromYear+1));
					taxmodel.terminate();
				} catch (Exception e) {
					logger.error("Exception in savePF() Pf Parameter while calling calculateTax : "+e);
					e.printStackTrace();
				}*/
			  return "Record Saved Successfully";
			  
		  } else {
			  return "Record already Exist for this date!";
			  
		  }

		

		
	}

	public String savePFT(PFParameterMaster bean,HttpServletRequest request)
	{
		
		Object data[][]=new Object[1][9];
		Object [][]maxPFCode = getSqlModel().getSingleResult("SELECT MAX(NVL(PFT_CODE,0)) +1 FROM HRMS_PFTRUST_CONF");
		
		logger.info("maxcode length=="+maxPFCode.length);
		String maxPFTCode ="1";
		try {
			/*if (maxPFCode == null || maxPFCode.length <= 0 || checkNull(String.valueOf(maxPFCode[0][0])).equals("")) {
				logger.info(" if maxcode null==");
				maxPFTCode = "1";
			} else {
				logger.info(" else maxcode null=="+maxPFCode[0][0]);
				maxPFTCode = checkNull(String.valueOf(maxPFCode[0][0]));
			}*/
			if (maxPFCode != null && maxPFCode.length >0) {
				logger.info(" else maxcode null=="+maxPFCode[0][0]);
				maxPFTCode = checkNull(String.valueOf(maxPFCode[0][0]));
			} else {
				maxPFTCode = "1";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		data[0][0]=maxPFTCode;
		data[0][1]=bean.getPercOfDedPFT();		
		data[0][2]=bean.getMaxLimitOFDed();
		data[0][3]=bean.getDebitCodePFT();
		data[0][4]=bean.getMinLoanLimit();
		data[0][5]=bean.getMaxLoanLimit();
		data[0][6]=bean.getLoanTypeCode();
		data[0][7]=bean.getInterestRate();
		data[0][8]=bean.getEffDatePFT();
				
		boolean result=getSqlModel().singleExecute(getQuery(6), data);
		
		if(result) {
			bean.setPfCodePFT(maxPFTCode);
			result = saveLoanPurpose(bean,request);
			System.out.println("-----------------RESULT---->>>>>>>>>>>>>>>"+result);
			  
		  } 
		if(result) {
			logger.info("pfCode if=="+bean.getPfCodePFT());
			  return "Record Saved Successfully";
			  
		  } else {
			  logger.info("pfCode else=="+bean.getPfCodePFT());
			  return "Duplicate entry found";
			  
		  }
		
	}
	public String saveGPF(PFParameterMaster bean)
	{
		
		Object data[][]=new Object[1][3];
		Object [][]maxPFCode = getSqlModel().getSingleResult("SELECT MAX(NVL(GPF_CODE,0)) +1 FROM HRMS_GPF_CONF");
		
		
		
		logger.info("maxcode length=="+maxPFCode.length);
		String maxGPFCode ="1";
		try {
			if (maxPFCode == null || maxPFCode.length <= 0 || checkNull(String.valueOf(maxPFCode[0][0])).equals("")) {
				logger.info(" if maxcode null==");
				maxGPFCode = "1";
			} else {
				logger.info(" else maxcode null==");
				maxGPFCode = checkNull(String.valueOf(maxPFCode[0][0]));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		boolean result=false;
		boolean result1=false;
		data[0][0]=maxGPFCode;
		data[0][1]=bean.getMaxLimitOFDedGPF();
		//data[0][2]=bean.getDebitCodeGPF();
		
		//updated by anantha lakshmi
		String listQuery="SELECT GPF_DEBIT_CODE FROM HRMS_GPF_CONF";
		Object gpfDBCode[][] = getSqlModel().getSingleResult(listQuery);
		if(gpfDBCode!=null && gpfDBCode.length>0){
			for(int i=0;i<gpfDBCode.length;i++){
				if(!String.valueOf(gpfDBCode[i][0]).equals(bean.getDebitCodeGPF())){
					data[0][2]=bean.getDebitCodeGPF();
					result1=true;
				}else{
					result1=false;
					break;
				}
			}
			
		}else{
			data[0][2]=bean.getDebitCodeGPF();
			result=getSqlModel().singleExecute(getQuery(8), data);
		}
		if(result1){
			System.out.println("++++++++++++++++++++++");
			result=getSqlModel().singleExecute(getQuery(8), data);
		}
				
		
		
		if(result) {
			bean.setPfCodeGPF(maxGPFCode);
			logger.info("pfCode if=="+bean.getPfCodeGPF());
			  return "Record Saved Successfully";
			  
		  } else {
			  logger.info("pfCode else=="+bean.getPfCodeGPF());
			  return "Duplicate records can't be saved!";
			  
		  }
	}
	public String saveVPF(PFParameterMaster bean)
	{
		boolean result=false;
		boolean isDuplicate=false;
		Object data[][]=new Object[1][5];
		Object [][]maxPFCode = getSqlModel().getSingleResult("SELECT MAX(NVL(VPF_CODE,0)) +1 FROM HRMS_VPF_CONF");
		
		logger.info("maxcode length=="+maxPFCode.length);
		String maxVPFCode ="1";
		try {
			if (maxPFCode == null || maxPFCode.length <= 0 || checkNull(String.valueOf(maxPFCode[0][0])).equals("")) {
				logger.info(" if maxcode null==");
				maxVPFCode = "1";
			} else {
				logger.info(" else maxcode null==");
				maxVPFCode = checkNull(String.valueOf(maxPFCode[0][0]));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		data[0][0]=maxVPFCode;
		data[0][1]=bean.getDeductionFormVPF();
		data[0][2]=bean.getVpfDedType();
		//data[0][3]=bean.getDebitCodeVPF();
		data[0][4]=bean.getMaxLimitOFDedVPF();
		
		
		//updated by anantha lakshmi
		String listQuery="SELECT VPF_DEBIT_CODE FROM HRMS_VPF_CONF";
		Object gpfDBCode[][] = getSqlModel().getSingleResult(listQuery);
		if(gpfDBCode!=null && gpfDBCode.length>0){
			for(int i=0;i<gpfDBCode.length;i++){
				if(!String.valueOf(gpfDBCode[i][0]).equals(bean.getDebitCodeVPF())){
					data[0][3]=bean.getDebitCodeVPF();
					isDuplicate=true;
				}else{
					isDuplicate=false;
					break;
				}
			}
			
		}else{
			data[0][3]=bean.getDebitCodeVPF();
			result=getSqlModel().singleExecute(getQuery(10), data);
		}
		if(isDuplicate){
			System.out.println("++++++++++++++++++++++");
			result=getSqlModel().singleExecute(getQuery(10), data);
		}
				
				
		//boolean result=getSqlModel().singleExecute(getQuery(10), data);
		
		if(result) {
			bean.setPfCodeVPF(maxVPFCode);
			logger.info("pfCode if=="+bean.getPfCodeVPF());
			  return "Record Saved Successfully";
			  
		  } else {
			  logger.info("pfCode else=="+bean.getPfCodeVPF());
			  return "Duplicate records can't be saved!";
			  
		  }
	}
	public String updateGPF(PFParameterMaster bean)
	{
		Object data[][]=new Object[1][3];
		data[0][0]=bean.getMaxLimitOFDedGPF();
		//data[0][1]=bean.getDebitCodeGPF();
		data[0][2]=bean.getPfCodeGPF();
		
		boolean result=false;
		boolean isDuplicat=false;
		//updated by anantha lakshmi
		String listQuery="SELECT GPF_DEBIT_CODE FROM HRMS_GPF_CONF";
		Object gpfDBCode[][] = getSqlModel().getSingleResult(listQuery);
		if(gpfDBCode!=null && gpfDBCode.length>0){
			for(int i=0;i<gpfDBCode.length;i++){
				if(!String.valueOf(gpfDBCode[i][0]).equals(bean.getDebitCodeGPF())){
					data[0][1]=bean.getDebitCodeGPF();
					isDuplicat=true;
				}else{
					isDuplicat=false;
					break;
				}
			}
			
		}
		if(isDuplicat){
			System.out.println("++++++++++++++++++++++");
			result=getSqlModel().singleExecute(getQuery(9), data);
		}

		if(result) {
			  return "Record updated Successfully";
		} else {
			  return "Record can't be updated.";
		}
	}
	public String updateVPF(PFParameterMaster bean)
	{
		Object data[][]=new Object[1][5];
		data[0][0]=bean.getDeductionFormVPF();
		data[0][1]=bean.getVpfDedType();
		//data[0][2]=bean.getDebitCodeVPF();
		boolean result=false;
		boolean isDuplicat=false;
		//updated by anantha lakshmi
		String listQuery="SELECT VPF_DEBIT_CODE FROM HRMS_VPF_CONF";
		Object gpfDBCode[][] = getSqlModel().getSingleResult(listQuery);
		if(gpfDBCode!=null && gpfDBCode.length>0){
			for(int i=0;i<gpfDBCode.length;i++){
				if(!String.valueOf(gpfDBCode[i][0]).equals(bean.getDebitCodeVPF())){
					data[0][1]=bean.getDebitCodeVPF();
					isDuplicat=true;
				}else{
					isDuplicat=false;
					break;
				}
			}
			
		}
		if(isDuplicat){
			System.out.println("++++++++++++++++++++++");
			result=getSqlModel().singleExecute(getQuery(11), data);
		}
		
		
		
		data[0][3]=bean.getMaxLimitOFDedVPF();
		data[0][4]=bean.getPfCodeVPF();
				
		//boolean result=getSqlModel().singleExecute(getQuery(11), data);
		
		if(result) {
			  return "Record updated Successfully";
			  
		  } else {
			  return "Duplicate records can't be updated.";
			  
		  }
	}
	public String updatePFT(PFParameterMaster bean,HttpServletRequest request)
	{
		
		Object data[][]=new Object[1][9];
		
		data[0][0]=bean.getPercOfDedPFT();		
		data[0][1]=bean.getMaxLimitOFDed();
		data[0][2]=bean.getDebitCodePFT();
		data[0][3]=bean.getMinLoanLimit();
		data[0][4]=bean.getMaxLoanLimit();
		data[0][5]=bean.getLoanTypeCode();
		data[0][6]=bean.getInterestRate();
		data[0][7]=bean.getEffDatePFT();
		data[0][8]=bean.getPfCodePFT();
				
		boolean result=getSqlModel().singleExecute(getQuery(7), data);
		if(result){
			result = saveLoanPurpose(bean,request);
		}
		if(result) {
				
			  return "Record updated Successfully";
			  
		  } else {
			  return "Duplicate entry found";
			  
		  }

		
	}
	public void showPurposes(PFParameterMaster bean, HttpServletRequest request){
		String query ="SELECT PFT_PURPOSE, PFT_ELIGIBILITY, PFT_PURPOSE_CODE FROM HRMS_PFTRUST_LOAN_PURS"
					+" WHERE PFT_CODE ="+bean.getPfCodePFT()+" ORDER BY PFT_PURPOSE_CODE ";
		Object [][]purposeObj = getSqlModel().getSingleResult(query);
		
		if(purposeObj !=null || purposeObj.length >0){
			ArrayList tableList = new ArrayList();
			for (int i = 0; i < purposeObj.length; i++) {
				PFParameterMaster listBean = new PFParameterMaster();
				listBean.setLoanPurpose(checkNull(String.valueOf(purposeObj[i][0])));
				listBean.setLoanEligibility(checkNull(String.valueOf(purposeObj[i][1])));
				listBean.setLoanPurposeCode(checkNull(String.valueOf(purposeObj[i][2])));
				tableList.add(listBean);
			}
			bean.setPurposeList(tableList);
		}
	}
	public boolean saveLoanPurpose(PFParameterMaster bean,HttpServletRequest request){
		
		if(getSqlModel().singleExecute("DELETE FROM HRMS_PFTRUST_LOAN_PURS WHERE PFT_CODE="+bean.getPfCodePFT()));
		
		String loanPurpose 		[]= request.getParameterValues("loanPurpose");
		String loanEligibilty	[]=	request.getParameterValues("loanEligibility");
		
		Object [][] addObj = new Object[loanPurpose.length][4];
		Object maxPurposeCode [][]=getSqlModel().getSingleResult("SELECT MAX(PFT_PURPOSE_CODE)+1 FROM HRMS_PFTRUST_LOAN_PURS");
		String addQuery = "INSERT INTO HRMS_PFTRUST_LOAN_PURS(PFT_CODE, PFT_PURPOSE, PFT_ELIGIBILITY, PFT_PURPOSE_CODE) VALUES(?,?,?,?)";
		
		for (int i = 0; i < addObj.length; i++) {
			addObj[i][0] = bean.getPfCodePFT();
			addObj[i][1] = loanPurpose[i];
			addObj[i][2] = loanEligibilty[i];
			try{
				System.out.println("Integer.parseInt===================>>>>>>>>"+Integer.parseInt(""+maxPurposeCode[0][0])+i);
			//addObj[i][3] = (Integer.parseInt(""+maxPurposeCode[0][0])+i);
				addObj[i][3] = (maxPurposeCode[0][0]);
			}catch(Exception ex){
				addObj[i][3] = 1+i;
			}
		}
		return getSqlModel().singleExecute(addQuery,addObj);
		
		
	}
	public String updatePF(PFParameterMaster pfParam, String path) {

	
			Object data[][]=new Object[1][16];
			
			data[0][0]=pfParam.getEffDate().trim();	
			logger.info("0000000000000000=="+data[0][0]);
			data[0][1]=pfParam.getDeduction().trim();
			logger.info("deductttttttt=="+data[0][1]);
			data[0][2]=pfParam.getEmpPF().trim();
			logger.info("emppppppppppfffffff=="+data[0][2]);
			data[0][3]=pfParam.getEmpFamilyPF().trim();
			logger.info("familyyyyyyyyyyyyyyy=="+data[0][3]);
			data[0][4]=pfParam.getPensionFund().trim();
			logger.info("ppppppppppppp=="+data[0][4]);
			data[0][5]=pfParam.getCompFund().trim();
			logger.info("ccccccccccccccccc=="+data[0][5]);
			//data[0][6]=pfParam.getPfType();
			logger.info("pffffffffffffffffff=="+data[0][6]);
			data[0][6]=String.valueOf(pfParam.getDebitCode()).trim();
			logger.info("ddddddddd=="+data[0][7]);
			data[0][7]=pfParam.getPfFormula().trim();
			logger.info("fffffffffffffffff=="+data[0][8]);
			
			logger.info("codeeeeeeeeeeeeee=="+data[0][9]);
			
			if(pfParam.getPfadmincharge().equals(""))
				data[0][8]=0.0;
			else
				data[0][8]=pfParam.getPfadmincharge();
			if(pfParam.getEdlicontribution().equals(""))
				data[0][9]=0.0;
			else
			data[0][9]=pfParam.getEdlicontribution();
			
			if(pfParam.getPfedlicharge().equals(""))
				data[0][10]=0.0;
			else
				data[0][10]=pfParam.getPfedlicharge();
			/*
			if(pfParam.getPfMinAmt().equals(""))
				data[0][12]=0.0;
			else
				data[0][12]=pfParam.getPfMinAmt().trim();
			*/
			if(pfParam.getDeductCriteria().equals("0")){
				data[0][11] = 0;
				pfParam.setFlag1("true");
			}
			else{
			data[0][11] =pfParam.getPfDedEmolument();
			pfParam.setFlag1("false");
			}
			data[0][12] = pfParam.getDeductCriteria();
			data[0][13] = pfParam.getNoMaxLimitChk();
			data[0][14] = pfParam.getPfEmoMaxLimit();
			data[0][15]=pfParam.getPfCode().trim();
			boolean result=getSqlModel().singleExecute(getQuery(2), data);
			logger.info("result=========");
			
			if(result) {
					xml_pfparameter(path);
					/**
					 * Following code calculates the tax
					 * and updates tax process
					 */
					/*try {
						CommonTaxCalculationModel taxmodel = new CommonTaxCalculationModel();
						taxmodel.initiate(context, session);
						logger.info("I m calling tax calculation method");
						Object[][] empList = getSqlModel().getSingleResult("SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_STATUS='S'");
						Calendar cal = Calendar.getInstance();
						cal.setTime(new Date());
						int fromYear = Integer.parseInt(String.valueOf(cal.get(Calendar.YEAR)));
						int month = Integer.parseInt(String.valueOf(cal.get(Calendar.MONTH)));
						if(month <= 2)
							fromYear--;
						if(empList != null && empList.length > 0)
							taxmodel.calculateTax(empList,String.valueOf(fromYear),String.valueOf(fromYear+1));
						taxmodel.terminate();
					} catch (Exception e) {
						logger.error("Exception in savePF() Pf Parameter while calling calculateTax : "+e);
						e.printStackTrace();
					}*/
					
				  return "Record Updated Successfully";
				  
			  } else {
				  return "Record cann't be Updated!Record already Exist for this date!";
				  
			  }

			
		
	}

	public String deletePF(PFParameterMaster pfParam, String poolDir) {
		
		Object [][] code=new Object[1][1];
		code[0][0]=pfParam.getPfCode();
		boolean result=getSqlModel().singleExecute(getQuery(3), code);
		if(result) {
			xml_pfparameter(poolDir);
			  return "deleted";
			  
		  } else {
			  return "not deleted";
			  
		  }
		
	}
public String deletePFT(PFParameterMaster pfParam) {
		
		Object [][] code=new Object[1][1];
		code[0][0]=pfParam.getPfCodePFT();
		boolean result=getSqlModel().singleExecute(getQuery(12), code);
		if(result) {
			  return "deleted";
			  
		  } else {
			  return "not deleted";
			  
		  }
		
	}
public String deleteGPF(PFParameterMaster pfParam) {
	
	Object [][] code=new Object[1][1];
	code[0][0]=pfParam.getPfCodeGPF();
	boolean result=getSqlModel().singleExecute(getQuery(13), code);
	if(result) {
		  return "deleted";
		  
	  } else {
		  return "not deleted";
		  
	  }
	
}
public String deleteVPF(PFParameterMaster pfParam) {
	
	Object [][] code=new Object[1][1];
	code[0][0]=pfParam.getPfCodeVPF();
	boolean result=getSqlModel().singleExecute(getQuery(14), code);
	if(result) {
		  return "deleted";
		  
	  } else {
		  return "not deleted";
		  
	  }
	
}
	

	public void getReport(PFParameterMaster bean,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context, String path) {
		/*CrystalReport cr=new CrystalReport();
		String path="org/paradyne/rpt/admin/master/pfparameter.rpt";
		cr.createReport(request, response, context,session, path, "");*/
		
		
		try {
			String reportType = bean.getReportType();
			if(reportType.equals("") || reportType == null || reportType.equals("null")){
				reportType = "pdf";
			}			
			String title = "PF Parameter";
			
			ReportDataSet  rds = new ReportDataSet();
			String fileName = "PF_Parameter";
			String reportPathName=fileName+"."+reportType;
			rds.setFileName(fileName);
			rds.setReportName(title);
			rds.setReportType(reportType);
			rds.setUserEmpId(bean.getUserEmpId());
			rds.setReportHeaderRequired(true);
			rds.setPageOrientation("landscape");
			rds.setPageSize("TABLOID");
			rds.setTotalColumns(15);
			ReportGenerator rg = null;
			
			if(path.equals("")){
				rg = new ReportGenerator(rds,session,context, request);
				new ReportGenerator(rds,session,context);
			}else{
				logger.info("################# ATTACHMENT PATH #############"+path+fileName+"."+reportType);
				rg = new ReportGenerator(rds,path,session,context, request);
				request.setAttribute("reportPath", path+fileName+"."+reportType);
				request.setAttribute("fileName", fileName + "."	+ reportType);
				request.setAttribute("action", "PFParameter_input.action?path="+reportPathName);
			}		
			
			String[] header1 = new String[11];
			header1[0] = "S.No";
			header1[1] = "Effective Date";
			header1[2] = "PF Percentage";
			header1[3] = "Employee's Contribution";
			header1[4] = "Employer's Contribution";
			header1[5] = "Debit Name";
			header1[6] = "PF Formula";
			header1[7] = "Deduct PF for Employees having PF emolument >";
			header1[8] = "Max Limit check for PF emoluments";
			header1[9] = "Maximum Limit on PF Emoluments";
			header1[10] = "Administrative Overheads";
			int[] headerAlign1 = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
			int[] headerWidth1 = { 5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
			int[] headerColSpan1 = { 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 3 };

			TableDataSet tdsObj = new TableDataSet();
			tdsObj.setData(null);
			tdsObj.setHeader(header1);
			tdsObj.setHeaderBorderDetail(3);
			tdsObj.setCellAlignment(headerAlign1);
			tdsObj.setCellWidth(headerWidth1);
			tdsObj.setHeaderColSpan(headerColSpan1);
			
			rg.addTableToDoc(tdsObj);
			
			String header2[] = new String[15];
			header2[0] = "";
			header2[1] = "";
			header2[2] = "";
			header2[3] = "Employee PF %";
			header2[4] = "Employee Family PF %";
			header2[5] = "Pension Fund  %";
			header2[6] = "CompanyFund %";
			header2[7] = "";
			header2[8] = "";
			header2[9] = "";
			header2[10] = "";
			header2[11] = "";
			header2[12] = "EDLI Employee Contribution %";
			header2[13] = "PF Admin Charge %";
			header2[14] = "PF EDLI Admin Charge %";
			int[] headerAlign2 = { 1, 0, 2, 2, 2, 2, 2, 0, 0, 2, 0, 2, 2, 2, 2 };
			int[] headerWidth2 = { 5, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
			
			String query = "SELECT CREDIT_NAME,'C'||CREDIT_CODE  FROM HRMS_CREDIT_HEAD ORDER BY CREDIT_CODE";
			Object[][] creditCodeObj = getSqlModel().getSingleResult(query);
			HashMap creditCodeMap = new HashMap(100);
			for (int j = 0; j < creditCodeObj.length; j++) {
				creditCodeMap.put(String.valueOf(creditCodeObj[j][1]), String.valueOf(creditCodeObj[j][0]));
			}
			
			Object[][] result = getSqlModel().getSingleResult(getQuery(17));
			Object[][] data = null; 
			if (!(result == null || result.length <= 0)) {
				data = new Object[result.length][16];
				for (int i = 0; i < result.length; i++) {
					data[i][0] = i+1;
					data[i][1] = result[i][1];
					data[i][2] = formatter.format(result[i][2]);
					data[i][3] = formatter.format(result[i][3]);
					data[i][4] = formatter.format(result[i][4]);
					data[i][5] = formatter.format(result[i][5]);
					data[i][6] = formatter.format(result[i][6]);
					data[i][7] = result[i][7];
					data[i][8] = result[i][8];

					String strMain = data[i][8].toString();
					StringTokenizer stk = new StringTokenizer(data[i][8]
							.toString(), "#");
					while (stk.hasMoreElements()) {
						String key = stk.nextToken();
						if (creditCodeMap.containsKey(key)) {
							strMain = strMain.replace("#" + key + "#",
									creditCodeMap.get(key).toString() + " ");
						}
					}
					data[i][8] = strMain;
					data[i][9] = formatter.format(result[i][9]);
					data[i][10] = result[i][10];
					if (checkNull(data[i][10].toString()).equalsIgnoreCase("Yes")) {
						data[i][11] = formatter.format(result[i][11]);
					} else {
						data[i][11] = "0.00";
					}
					data[i][12] = formatter.format(result[i][12]);
					data[i][13] = formatter.format(result[i][13]);
					data[i][14] = formatter.format(result[i][14]);
				}
			}
			
			TableDataSet tdsObj1 = new TableDataSet();
			tdsObj1.setData(data);
			tdsObj1.setHeaderTable(true);
			tdsObj1.setHeader(header2);
			tdsObj1.setHeaderBorderDetail(3);
			tdsObj1.setCellAlignment(headerAlign2);
			tdsObj1.setCellWidth(headerWidth2);		
			tdsObj1.setBorder(true);
			tdsObj1.setBorderDetail(3);
			rg.addTableToDoc(tdsObj1);
			
			rg.process();			
			if(path.equals("")){
				rg.createReport(response);
			}else{
				/* Generates the report as attachment*/
				rg.saveReport(response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

public void  Data(PFParameterMaster  bean, HttpServletRequest request) {
		
	
	Object [][] obj = getSqlModel().getSingleResult(getQuery(4));
	String pageIndex[] = Utility.doPaging(bean.getMyPage(), obj.length, 20);
	if (pageIndex == null) {
		pageIndex[0] = "0";
		pageIndex[1] = "20";
		pageIndex[2] = "1";
		pageIndex[3] = "1";
		pageIndex[4] = "";
	}
	request.setAttribute("totalPage", Integer.parseInt(String
			.valueOf(pageIndex[2])));
	request.setAttribute("pageNo", Integer.parseInt(String
			.valueOf(pageIndex[3])));

	if (pageIndex[4].equals("1"))
		bean.setMyPage("1");
	ArrayList<Object> list = new ArrayList<Object>();
	if (obj != null && obj.length > 0) {

		// for (int i = 0; i < obj.length; i++) {s
		for (int i = Integer.parseInt(pageIndex[0]); i < Integer.parseInt(pageIndex[1]); i++) {
			  PFParameterMaster  bean1 = new PFParameterMaster ();
			  
			   bean1.setPfCode(checkNull(String.valueOf(obj[i][0])));
			   bean1.setEffDate(checkNull(String.valueOf(obj[i][1])));
			   bean1.setDeduction(checkNull(String.valueOf(obj[i][2])));
			list.add(bean1);
		}

		bean.setTotalRecords("" + obj.length);
		bean.setMakeList("true");
	} else {
		bean.setMakeList("false");
	}
	bean.setIteratorlist(list);	

	}
public void calforedit(PFParameterMaster bean) {
		
		
		String query = "SELECT PF_CODE,TO_CHAR(PF_DATE,'DD-MM-YYYY')," +
				"NVL(PF_PERCENTAGE,''),NVL(PF_EMPLOYEE,''),NVL(PF_EMP_FAMILY,''),NVL(PF_COMPANY,'')," +
				"NVL(PF_CMP_FAMILY,'')" +
				",NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,''),NVL(PF_FORMULA,'')" +
				",NVL(PF_DEBIT_CODE,''),NVL(PF_ADMIN_CHARGES,0),NVL(PF_EDLI_EMPCONT,0),NVL(PF_EDLI_ADMIN_CHARGES,0), "+
				" NVL(PF_DEDUCT_EMOL_AMT,0),NVL(PF_DEDUCT_CRITERIA,0),NVL(PF_EMOL_NO_MAX_LIMIT_CHK,'N'),NVL(PF_EMOL_MAX_LIMIT,'0') "+
				" FROM HRMS_PF_CONF "+
		
		"  LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_PF_CONF.PF_DEBIT_CODE)"+
		" WHERE PF_CODE = "+bean.getHiddencode() ;
		
			
		
		Object [][]data=getSqlModel().getSingleResult(query);
		
		if(data != null && data.length > 0){
			bean.setPfCode(checkNull(String.valueOf(data[0][0])));
			bean.setEffDate(checkNull(String.valueOf(data[0][1])));
			//bean.setPfType(checkNull(String.valueOf(data[0][2])));
			bean.setDeduction(checkNull(String.valueOf(data[0][2])));
			bean.setEmpPF(checkNull(String.valueOf(data[0][3])));
			bean.setEmpFamilyPF(checkNull(String.valueOf(data[0][4])));
			bean.setPensionFund(checkNull(String.valueOf(data[0][5])));
			bean.setCompFund(checkNull(String.valueOf(data[0][6])));
			bean.setDebitName(checkNull(String.valueOf(data[0][7])));
			bean.setPfFormula(checkNull(String.valueOf(data[0][8])));
			bean.setDebitCode(checkNull(String.valueOf(data[0][9])));
			
			bean.setPfadmincharge(String.valueOf(data[0][10]));
			bean.setEdlicontribution(String.valueOf(data[0][11]));
			bean.setPfedlicharge(String.valueOf(data[0][12]));
			
			bean.setPfDedEmolument(String.valueOf(data[0][13]));
			bean.setDeductCriteria(String.valueOf(data[0][14]));
			if(data[0][14].equals("0"))
				bean.setFlag1("true");
			else
				bean.setFlag1("false");
			bean.setNoMaxLimitChk(String.valueOf(data[0][15]));
			bean.setPfEmoMaxLimit(String.valueOf(data[0][16]));
			//bean.setPfMinAmt(String.valueOf(data[0][14]));
		}
        
		
	}
public boolean calfordelete(PFParameterMaster bean) {
	
	Object [][] delete = new Object [1][1];
	delete [0][0] =  bean.getHiddencode();
	return getSqlModel().singleExecute(getQuery(3), delete);


}
public String checkNull(String result){
	if(result ==null ||result.equals("null")){
		return "";
	}
	else{
		return result;
	}
}


public boolean deletecheckedRecords(PFParameterMaster bean, String[] code, String poolDir) {
	boolean result=false;
	int count=0;
	if(code !=null)
	{
		for (int i = 0; i < code.length; i++) {
			
			if(!code[i].equals("")){
				
				Object [][] delete = new Object [1][1];
				delete [0][0] =code[i] ;
				result=getSqlModel().singleExecute(getQuery(3), delete);
				if(!result)
					count++;
				
			}
		}
	}
	if(result){
		xml_pfparameter(poolDir);
	}
	if(count!=0)
	{	result=false;
		return result;
		}
	else
		{result=true;
		return result; }
}


/** WRITING IN XML **/
/** @author reebaj **/
public void xml_pfparameter(String path) {
	
	if (!(path == null || path.equals("") || path.equals(null)))
		path = "/" + path;
	
	try {
		new XMLReaderWriter().write(buildPfParameter("PAYROLL", "PF PARAMETER"),
				path);
	} catch (Exception e) {
		// TODO: handle exception
	}
}


public Document buildPfParameter(String head, String subhead) {
	String query1 = " SELECT PF_CODE, PF_FORMULA, PF_PERCENTAGE, TO_CHAR(PF_DATE,'dd-mm-yyyy'),PF_DEBIT_CODE,PF_DEDUCT_EMOL_AMT,PF_DEDUCT_CRITERIA,PF_EMOL_NO_MAX_LIMIT_CHK,PF_EMOL_MAX_LIMIT "
					+ " FROM HRMS_PF_CONF "
					+ " ORDER BY  PF_DATE DESC";
	Object data[][] = getSqlModel().getSingleResult(query1);
	Document document = DocumentHelper.createDocument();
	logger.info("data.length"+data.length);
	Element header;
	Element element;
	Element root = document.addElement(head);
	if(data != null && data.length > 0){
		//header = root.addElement("PFPARAM").addAttribute("name", subhead);
		
		for (int i = 0; i < data.length; i++) {
		header = root.addElement("PFPARAM").addAttribute("code",
				String.valueOf(data[i][0])).addAttribute("formula",
				String.valueOf(data[i][1])).addAttribute("percentage",
				String.valueOf(data[i][2])).addAttribute("date",
				String.valueOf(data[i][3])).addAttribute("debitCode",
				String.valueOf(data[i][4])).addAttribute("emolAmt", 
				String.valueOf(data[i][5])).addAttribute("deductCriteria",
				String.valueOf(data[i][6])).addAttribute("maxLimitCheck",
				String.valueOf(data[i][7])).addAttribute("emolMaxLimit",
				String.valueOf(data[i][8]));
		}
		
	}else {
		logger.info("NO PF PARAMETER");
			//header = root.addElement("PFPARAM").addAttribute("name", subhead);
			header = root.addElement("PFPARAM").addAttribute("code",
				String.valueOf("code")).addAttribute("formula",
				String.valueOf("fmla")).addAttribute("percentage",
				String.valueOf("pcnt")).addAttribute("date",
				String.valueOf("date")).addAttribute("debitCode",
				String.valueOf("dbcode")).addAttribute("emolAmt", 
				String.valueOf("emolAmt")).addAttribute("deductCriteria",
				String.valueOf("deductCriteria")).addAttribute("maxLimitCheck",
				String.valueOf("maxLimitCheck")).addAttribute("emolMaxLimit",
				String.valueOf("emolMaxLimit"));
	}
	return document;
}

public void f9setdata(PFParameterMaster bean) {
	// TODO Auto-generated method stub
	
	String query = "SELECT PF_CODE,TO_CHAR(PF_DATE,'DD-MM-YYYY')," +
					"NVL(PF_PERCENTAGE,''),NVL(PF_EMPLOYEE,''),NVL(PF_EMP_FAMILY,''),NVL(PF_COMPANY,'')," +
					"NVL(PF_CMP_FAMILY,'')" +
					",NVL(HRMS_DEBIT_HEAD.DEBIT_NAME,''),NVL(PF_FORMULA,'')" +
					",NVL(PF_DEBIT_CODE,''),NVL(PF_ADMIN_CHARGES,0),NVL(PF_EDLI_EMPCONT,0),NVL(PF_EDLI_ADMIN_CHARGES,0),"+
					" NVL(PF_DEDUCT_EMOL_AMT,0),NVL(PF_DEDUCT_CRITERIA,0),NVL(PF_EMOL_NO_MAX_LIMIT_CHK,'N'),NVL(PF_EMOL_MAX_LIMIT,'0') "+
					"  FROM HRMS_PF_CONF  LEFT JOIN HRMS_DEBIT_HEAD ON(HRMS_DEBIT_HEAD.DEBIT_CODE = HRMS_PF_CONF.PF_DEBIT_CODE)"+
					" WHERE PF_CODE = "+bean.getPfCode() ;

				Object [][]data=getSqlModel().getSingleResult(query);
				
				//bean.setPfCode(checkNull(String.valueOf(data[0][0])));
				bean.setEffDate(checkNull(String.valueOf(data[0][1])));
				//bean.setPfType(checkNull(String.valueOf(data[0][2])));
				bean.setDeduction(checkNull(String.valueOf(data[0][2])));
				bean.setEmpPF(checkNull(String.valueOf(data[0][3])));
				bean.setEmpFamilyPF(checkNull(String.valueOf(data[0][4])));
				bean.setPensionFund(checkNull(String.valueOf(data[0][5])));
				bean.setCompFund(checkNull(String.valueOf(data[0][6])));
				bean.setDebitName(checkNull(String.valueOf(data[0][7])));
				bean.setPfFormula(checkNull(String.valueOf(data[0][8])));
				bean.setDebitCode(checkNull(String.valueOf(data[0][9])));
				
				bean.setPfadmincharge(String.valueOf(data[0][10]));
				bean.setEdlicontribution(String.valueOf(data[0][11]));
				bean.setPfedlicharge(String.valueOf(data[0][12]));
				
				bean.setPfDedEmolument(String.valueOf(data[0][13]));
				bean.setDeductCriteria(String.valueOf(data[0][14]));
				if(data[0][14].equals("0"))
					bean.setFlag1("true");
				bean.setNoMaxLimitChk(String.valueOf(data[0][15]));
				bean.setPfEmoMaxLimit(String.valueOf(data[0][16]));				
				//bean.setPfMinAmt(String.valueOf(data[0][14]));
					
}

public boolean savePFApplicability(PFParameterMaster bean){
	Object [][]pfFlags = new Object[1][4];
	boolean result = false;
	for (int i = 0; i < pfFlags[0].length; i++) {
		pfFlags[0][i]="N";
	}
	if(bean.getEPFflag().equals("true")){
		pfFlags [0][0] = "Y";
	}
	if(bean.getGPFflag().equals("true")){
		pfFlags [0][1] = "Y";
	}
	if(bean.getVPFflag().equals("true")){
		pfFlags [0][2] = "Y";
	}
	if(bean.getPFTflag().equals("true")){
		pfFlags [0][3] = "Y";
	}
	result = getSqlModel().singleExecute(getQuery(5),pfFlags);
	return result;
}
public void getPFApplicability(PFParameterMaster bean){
	Object pfFlags[][]=getSqlModel().getSingleResult("SELECT NVL(CONF_EPF,'N'), NVL(CONF_GPF,'N'), NVL(CONF_VPF,'N'), NVL(CONF_PFTRUST,'N') FROM HRMS_SALARY_CONF");
	
	if(String.valueOf(pfFlags[0][0]).equals("Y")){
		bean.setEPFflag("true");
	}
	if(String.valueOf(pfFlags[0][1]).equals("Y")){
		bean.setGPFflag("true");
	}
	if(String.valueOf(pfFlags[0][2]).equals("Y")){
		bean.setVPFflag("true");
	}
	if(String.valueOf(pfFlags[0][3]).equals("Y")){
		bean.setPFTflag("true");
	}
	
}

public void addPurposeRow(PFParameterMaster bean, HttpServletRequest request){
	
	String purpose []= request.getParameterValues("loanPurpose");
	String purposeCode []= request.getParameterValues("loanPurposeCode");
	String eligibility []= request.getParameterValues("loanEligibility");
	ArrayList tableList = new ArrayList();
	if(!(purpose ==null || purpose.length <= 0)){
		
		for (int i = 0; i < eligibility.length; i++) {
			PFParameterMaster listBean = new PFParameterMaster();
			listBean.setLoanPurpose(checkNull(purpose[i]));
			listBean.setLoanPurposeCode(checkNull(purposeCode[i]));
			listBean.setLoanEligibility(checkNull(eligibility[i]));
			tableList.add(listBean);
		}
	}
		bean.setLoanPurpose("");
		bean.setLoanPurposeCode("");
		bean.setLoanEligibility("");
		tableList.add(bean);
		
		bean.setPurposeList(tableList);
	
}
public void removePurposeRow(PFParameterMaster bean, HttpServletRequest request){
	
	String purpose []= request.getParameterValues("loanPurpose");
	String purposeCode []= request.getParameterValues("loanPurposeCode");
	String eligibility []= request.getParameterValues("loanEligibility");
	ArrayList tableList = new ArrayList();
	String []removeIds =bean.getParaId().split(",");
	
	if(!(purpose ==null || purpose.length <= 0)){
		
		for (int i = 0; i < eligibility.length; i++) {
			boolean equalFlag = false;
			for (int j = 0; j < removeIds.length; j++) {
				logger.info("Integer.parseInt(removeIds[j])=="+removeIds[j]+"for i=="+i);
				if(String.valueOf(removeIds[j]).equals(""+(i+1))){
					logger.info("inside if");
					equalFlag = true;
					break;
				}
			}
			if(!equalFlag){
			PFParameterMaster listBean = new PFParameterMaster();
			listBean.setLoanPurpose(checkNull(purpose[i]));
			listBean.setLoanPurposeCode(checkNull(purposeCode[i]));
			listBean.setLoanEligibility(checkNull(eligibility[i]));
			tableList.add(listBean);
			}
		}
	}
		
		bean.setPurposeList(tableList);
	
}

public void getEmploeeList(PFParameterMaster bean,HttpServletRequest request){
	String empQuery ="SELECT EMP_TOKEN,HRMS_EMP_OFFC.EMP_ID,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME AS NAME,"
					+" NVL(SAL_EPF_FLAG,'N'), NVL(SAL_VPF_FLAG,'N'), NVL(SAL_GPF_FLAG,'N'), NVL(SAL_PFTRUST_FLAG,'N'),"
					+" CASE WHEN NVL(SAL_EPF_FLAG,'N')='N' AND NVL(SAL_VPF_FLAG,'N')='N' AND NVL(SAL_GPF_FLAG,'N')='N' AND NVL(SAL_PFTRUST_FLAG,'N')='N' THEN 'Y' ELSE 'N' END "
					+" FROM HRMS_EMP_OFFC "
					+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID)WHERE EMP_STATUS='S' AND ( 1=0 " ;
		
		boolean isFilterSelected =false;
		if(!bean.getApplDivisionCode().equals("")){
			empQuery += " OR EMP_DIV IN("+bean.getApplDivisionCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplBranchCode().equals("")){
			empQuery += " OR EMP_CENTER IN("+bean.getApplBranchCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplDeptCode().equals("")){
			empQuery += " OR EMP_DEPT IN("+bean.getApplDeptCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplDesgCode().equals("")){
			empQuery += " OR EMP_RANK IN("+bean.getApplDesgCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplETypeCode().equals("")){
			empQuery += " OR EMP_TYPE IN("+bean.getApplETypeCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplGradeCode().equals("")){
			empQuery += " OR EMP_CADRE IN("+bean.getApplGradeCode()+")";
			isFilterSelected =true;
		}
		if(!bean.getApplEmpCode().equals("")){
			empQuery += " OR HRMS_EMP_OFFC.EMP_ID IN("+bean.getApplEmpCode()+")";
			isFilterSelected =true;
		}
		if(!isFilterSelected){
			empQuery +="OR 1=1";
		}
		
		empQuery +=") ORDER BY UPPER(NAME) ";
		
		Object [][]empData = getSqlModel().getSingleResult(empQuery);
		try{
		String[] pageIndex = Utility.doPaging(bean.getMyPageEmpConf(),empData.length, 20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("PageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		if(pageIndex[4].equals("1"))
			bean.setMyPageEmpConf("1");
		
		ArrayList tableList = new ArrayList();
		if(empData != null && empData.length !=0){
			for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) {
			PFParameterMaster listBean = new PFParameterMaster();
			listBean.setEmpId(checkNull(String.valueOf(empData[i][0])));
			listBean.setEmpName(checkNull(String.valueOf(empData[i][2])));
			listBean.setEmpCode(checkNull(String.valueOf(empData[i][1])));
			listBean.setEpfAppl(checkNull(String.valueOf(empData[i][3])));
			listBean.setGpfAppl(checkNull(String.valueOf(empData[i][5])));
			listBean.setVpfAppl(checkNull(String.valueOf(empData[i][4])));
			listBean.setPftAppl(checkNull(String.valueOf(empData[i][6])));
			listBean.setPfNotAppl(checkNull(String.valueOf(empData[i][7])));
			
			tableList.add(listBean);
		}
		bean.setNoData("false");
		bean.setEmpList(tableList);
		setConfiguredEmployees(bean);
	}else{
		bean.setNoData("true");
	}
		}catch (Exception e) {
			bean.setNoData("true");
		}
		
					
}
/*public void setConfiguredEmployees(PFParameterMaster bean){
	String configEmpQuery="SELECT EMP_ID FROM HRMS_SALARY_MISC";
	String nonConfigQuery ="SELECT COUNT(EMP_ID) FROM HRMS_EMP_OFFC WHERE EMP_ID NOT IN("+configEmpQuery+")";
	
	Object [][]configEmp =getSqlModel().getSingleResult(configEmpQuery);
	Object [][]nonConfigEmp =getSqlModel().getSingleResult(nonConfigQuery);
	
	try {
		bean.setConfEmp(String.valueOf(configEmp.length));
	} catch (Exception e) {
		bean.setConfEmp("0");
	}
	try {
		bean.setNonConfEmp(String.valueOf(nonConfigEmp[0][0]));
	} catch (Exception e) {
		bean.setNonConfEmp("0");
	}
	
}*/
	public void setConfiguredEmployees(PFParameterMaster bean) {
		// String configEmpQuery="SELECT EMP_ID FROM HRMS_SALARY_MISC";
		System.out.println("bean.getApplDivisionCode() :: "
				+ bean.getApplDivisionCode());
		String nonConfigQuery = "";
		String configEmpQuery = "SELECT HRMS_SALARY_MISC.EMP_ID FROM HRMS_SALARY_MISC "
				+ "INNER JOIN HRMS_EMP_OFFC ON(HRMS_SALARY_MISC.EMP_ID=HRMS_EMP_OFFC.EMP_ID) "
				+ "WHERE HRMS_EMP_OFFC.EMP_DIV IN ("
				+ bean.getApplDivisionCode() + ")";

		if (bean.getApplDivisionCode() != null)
			nonConfigQuery = "SELECT COUNT(EMP_ID) FROM HRMS_EMP_OFFC WHERE EMP_ID NOT IN("
					+ configEmpQuery
					+ ") AND HRMS_EMP_OFFC.EMP_DIV IN ("
					+ bean.getApplDivisionCode() + ")";

		Object[][] configEmp = getSqlModel().getSingleResult(configEmpQuery);
		Object[][] nonConfigEmp = getSqlModel().getSingleResult(nonConfigQuery);

		try {
			if (configEmp != null && configEmp.length > 0) {
				bean.setConfEmp(String.valueOf(configEmp.length));
			} else {
				bean.setConfEmp("0");
			}

		} catch (Exception e) {
			bean.setConfEmp("0");
		}
		try {
			if (nonConfigEmp != null && nonConfigEmp.length > 0) {
				bean.setNonConfEmp(String.valueOf(nonConfigEmp[0][0]));
			} else {
				bean.setNonConfEmp("0");
			}
		} catch (Exception e) {
			bean.setNonConfEmp("0");
		}

	}
public boolean saveEmpConfig(PFParameterMaster bean, HttpServletRequest request){
	
	String empId []=request.getParameterValues("empCode");
	String epfFlag []=request.getParameterValues("epfAppl");
	String gpfFlag []=request.getParameterValues("gpfAppl");
	String vpfFlag []=request.getParameterValues("vpfAppl");
	String pftFlag []=request.getParameterValues("pftAppl");
	String pfNotFlag []=request.getParameterValues("pfNotAppl");
	
	if(epfFlag==null ||epfFlag.length==0){
		logger.info("epfFlag is NULL");
		epfFlag=new String[empId.length];
		for (int i = 0; i < epfFlag.length; i++) {
			epfFlag[i]="N";
		}
	}
	if(gpfFlag==null ||gpfFlag.length==0){
		logger.info("gpfFlag is NULL");
		gpfFlag=new String[empId.length];
		for (int i = 0; i < gpfFlag.length; i++) {
			gpfFlag[i]="N";
		}
	}
	if(vpfFlag==null ||vpfFlag.length==0){
		logger.info("vpfFlag is NULL");
		vpfFlag=new String[empId.length];
		for (int i = 0; i < vpfFlag.length; i++) {
			vpfFlag[i]="N";
		}
	}
	if(pftFlag==null ||pftFlag.length==0){
		logger.info("pftFlag is NULL");
		pftFlag=new String[empId.length];
		for (int i = 0; i < pftFlag.length; i++) {
			pftFlag[i]="N";
		}
	}
	Object [][]checkObject=null;
	
	Vector addVector=new Vector();
	Vector updateVector=new Vector();
	for (int i = 0; i < empId.length; i++) {
		/*logger.info("empId["+i+"]=="+empId[i]);
		logger.info("epfFlag["+i+"]=="+epfFlag[i]);
		logger.info("gpfFlag["+i+"]=="+gpfFlag[i]);
		logger.info("vpfFlag["+i+"]=="+vpfFlag[i]);
		logger.info("pftFlag["+i+"]=="+pftFlag[i]);
		logger.info("pfNotFlag["+i+"]=="+pfNotFlag[i]);*/
		checkObject = null;
		
		checkObject = getSqlModel().getSingleResult("SELECT EMP_ID FROM HRMS_SALARY_MISC WHERE EMP_ID ="+String.valueOf(empId[i]));
		if(checkObject.length >0 && checkObject!=null ){
			Object tempUpdateObj [][]=new Object[1][5];
			tempUpdateObj [0][0] = String.valueOf(epfFlag[i]);
			tempUpdateObj [0][1] = String.valueOf(vpfFlag[i]);
			tempUpdateObj [0][2] = String.valueOf(gpfFlag[i]);
			tempUpdateObj [0][3] = String.valueOf(pftFlag[i]);
			tempUpdateObj [0][4] = String.valueOf(empId[i]);
			updateVector.add(tempUpdateObj);
			//getSqlModel().singleExecute(getQuery(15),tempUpdateObj);
		}else {
			Object tempAddObj [][]=new Object[1][5];
			tempAddObj [0][0] = String.valueOf(empId[i]);
			tempAddObj [0][1] = String.valueOf(epfFlag[i]);
			tempAddObj [0][2] = String.valueOf(vpfFlag[i]);
			tempAddObj [0][3] = String.valueOf(gpfFlag[i]);
			tempAddObj [0][4] = String.valueOf(pftFlag[i]);
			
			addVector.add(tempAddObj);
			//getSqlModel().singleExecute(getQuery(16),tempAddObj);
		}
		
		
	}
	/*logger.info("addVector size==="+addVector.size());
	logger.info("updateVector size==="+updateVector.size());*/
	if(addVector.size()>0){
		Object addObj[][]=new Object[addVector.size()][5];
		for (int i = 0; i < addObj.length; i++) {
			addObj[i][0] = String.valueOf(((Object[][]) addVector.get(i))[0][0]);
			addObj[i][1] = String.valueOf(((Object[][]) addVector.get(i))[0][1]);
			addObj[i][2] = String.valueOf(((Object[][]) addVector.get(i))[0][2]);
			addObj[i][3] = String.valueOf(((Object[][]) addVector.get(i))[0][3]);
			addObj[i][4] = String.valueOf(((Object[][]) addVector.get(i))[0][4]);
		}
		getSqlModel().singleExecute(getQuery(16),addObj);
	}
	if(updateVector.size()>0){
		Object updateObj[][]=new Object[updateVector.size()][5];
		for (int i = 0; i < updateObj.length; i++) {
			updateObj[i][0] = String.valueOf(((Object[][]) updateVector.get(i))[0][0]);
			updateObj[i][1] = String.valueOf(((Object[][]) updateVector.get(i))[0][1]);
			updateObj[i][2] = String.valueOf(((Object[][]) updateVector.get(i))[0][2]);
			updateObj[i][3] = String.valueOf(((Object[][]) updateVector.get(i))[0][3]);
			updateObj[i][4] = String.valueOf(((Object[][]) updateVector.get(i))[0][4]);
		}
		getSqlModel().singleExecute(getQuery(15),updateObj);
		
	}
	setConfiguredEmployees(bean);
	return false;
}
public boolean saveAllEmpConfig(PFParameterMaster bean, HttpServletRequest request){
	Object empObj[][]=null;
	
	String configEmpQuery="SELECT HRMS_SALARY_MISC.EMP_ID FROM HRMS_SALARY_MISC "
						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_SALARY_MISC.EMP_ID)"
						+" WHERE EMP_STATUS='S'";
	String nonConfigQuery ="SELECT EMP_ID FROM HRMS_EMP_OFFC WHERE EMP_ID NOT IN("+configEmpQuery+") AND EMP_STATUS='S'";
	
	empObj=getSqlModel().getSingleResult(configEmpQuery);
	
	/*logger.info("bean.getAllEmpEPF==="+bean.getAllEmpEPF());
	logger.info("bean.getAllEmpGPF==="+bean.getAllEmpGPF());
	logger.info("bean.getAllEmpVPF==="+bean.getAllEmpVPF());
	logger.info("bean.getAllEmpPFt==="+bean.getAllEmpPFT());
	logger.info("bean.getAllEmpPFNot==="+bean.getAllEmpPFNot());*/
	
	
	try {
		Object updateObj[][] = new Object[empObj.length][5];
		logger.info("total configured empl==="+empObj.length);
		logger.info("bean.getAllEmpEPF()==="+bean.getAllEmpEPF());
		logger.info("bean.getAllEmpGPF()==="+bean.getAllEmpGPF());
		logger.info("bean.getAllEmpVPF()==="+bean.getAllEmpVPF());
		logger.info("bean.getAllEmpPFT()==="+bean.getAllEmpPFT());
		for (int i = 0; i < empObj.length; i++) {
			updateObj[i][0] = bean.getAllEmpEPF();
			updateObj[i][1] = bean.getAllEmpVPF();
			updateObj[i][2] = bean.getAllEmpGPF();
			updateObj[i][3] = bean.getAllEmpPFT();
			updateObj[i][4] = String.valueOf(empObj[i][0]);
		}
		getSqlModel().singleExecute(getQuery(15), updateObj);
	} catch (Exception e) {
		// TODO: handle exception
	}
	empObj =null;
	empObj=getSqlModel().getSingleResult(nonConfigQuery);
	try {
		Object insertObj[][] = new Object[empObj.length][5];
		logger.info("total nonconfigured empl==="+empObj.length);
		for (int i = 0; i < empObj.length; i++) {
			insertObj[i][0] = String.valueOf(empObj[i][0]);
			insertObj[i][1] = bean.getAllEmpEPF();
			insertObj[i][2] = bean.getAllEmpVPF();
			insertObj[i][3] = bean.getAllEmpGPF();
			insertObj[i][4] = bean.getAllEmpPFT();
			
		}
		getSqlModel().singleExecute(getQuery(16), insertObj);
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	setConfiguredEmployees(bean);
	return false;
}
public String getReturnPage(PFParameterMaster bean){
	String returnPage ="";
	Object pfFlags[][]=getSqlModel().getSingleResult("SELECT NVL(CONF_EPF,'N'), NVL(CONF_GPF,'N'), NVL(CONF_VPF,'N'), NVL(CONF_PFTRUST,'N') FROM HRMS_SALARY_CONF");
	
	if(String.valueOf(pfFlags[0][0]).equals("Y")){
		returnPage ="EPFScreen"; 
		
	}else if(String.valueOf(pfFlags[0][1]).equals("Y")){
		returnPage ="GPFScreen";
		
	}else if(String.valueOf(pfFlags[0][2]).equals("Y")){
		returnPage ="VPFScreen";
		
	}else if(String.valueOf(pfFlags[0][3]).equals("Y")){
		returnPage ="PFTrustScreen"; 
	}else 
		returnPage="ConfigEmpScreen";
	
	return returnPage;
}
public String [][]getPFTabs(){
	String pfTabs [][]= null;
	
	String query =("SELECT NVL(CONF_EPF,'N'), NVL(CONF_GPF,'N'), NVL(CONF_VPF,'N'), NVL(CONF_PFTRUST,'N') FROM HRMS_SALARY_CONF");
	Object [][]pfFlags = getSqlModel().getSingleResult(query);
	Vector tabVector = new Vector();
	if(String.valueOf(pfFlags[0][0]).equals("Y")){
		String [][]tempArray = new String [1][2];
		tempArray [0][0] ="EPF";
		tempArray [0][1] ="getEPFScreen";
		tabVector.add(tempArray);
	}
	if(String.valueOf(pfFlags[0][1]).equals("Y")){
		String [][]tempArray = new String [1][2];
		tempArray [0][0] ="GPF";
		tempArray [0][1] ="getGPFScreen";
		tabVector.add(tempArray);
	}
	if(String.valueOf(pfFlags[0][2]).equals("Y")){
		String [][]tempArray = new String [1][2];
		tempArray [0][0] ="VPF";
		tempArray [0][1] ="getVPFScreen";
		tabVector.add(tempArray);
	}
	if(String.valueOf(pfFlags[0][3]).equals("Y")){
		String [][]tempArray = new String [1][2];
		tempArray [0][0] ="PF Trust";
		tempArray [0][1] ="getPFTrustScreen";
		tabVector.add(tempArray);
	}
	pfTabs = new String[tabVector.size()][2];
	for (int i = 0; i < pfTabs.length; i++) {
		pfTabs [i][0] =  String.valueOf(((String[][]) tabVector.get(i))[0][0]);
		pfTabs [i][1] =  String.valueOf(((String[][]) tabVector.get(i))[0][1]);
		logger.info("pfTabs["+i+"][0]="+pfTabs[i][0]);
		logger.info("pfTabs["+i+"][1]="+pfTabs[i][1]);
	}
	return pfTabs;
}

}
