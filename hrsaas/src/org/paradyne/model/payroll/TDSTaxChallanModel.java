package org.paradyne.model.payroll;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.TDSTaxChallan;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
public class TDSTaxChallanModel extends ModelBase {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(org.struts.lib.ParaActionSupport.class);
	/*
	 * Following function is called in the action class when view report button is clicked. 
	 */
	public void generateReport(TDSTaxChallan bean,HttpServletResponse response){
		try{
			int cellwidthForParaEmployer []= {100};
			int allignForParaEmployer[]     = {0};
			Object matter [][] = new Object [1][1];
			Object instList [][] = new Object [4][1];
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator("Pdf","TDS Tax Challan");
			rg.addText("For payments from"+Utility.month(Integer.parseInt(bean.getFromMonth()))+" "+bean.getFromYear()+"" ,0,2,0);
			
			Object[][] obj=new Object[1][3];
			String msg="*Important:Please see notesoverleaf before filling up the challan. ";
			String msg1="T.D.S/T.C.S Tax Challan";
			String msg2="Single copy(to be sent to the ZAO)";
			obj[0][0] = msg;
			obj[0][1] = msg1;
			obj[0][2] = msg2;
			rg.tableBodyNoBorder(obj,new int[] {10, 10,10},new int[]{ 10, 10,10});
			
			Object[][] taxHeader=new Object[1][3];
			taxHeader[0][0] = "CHALLAN NO \nITNS\n 281";
			taxHeader[0][1] = "Tax Applicable(Tick one)* \n TAX DEDUCTED/COLLECTED AT SOURCE FORM \n (0020) COMPANY DEDUCTEES         (0021)NON-COMPANY DEDUCTEES";
			taxHeader[0][2] = "Assessment \n Year";
			rg.tableBody(taxHeader,new int[] {20, 60,20},new int[]{ 10, 10,10});
			//rg.tableBodyNoBorder(taxHeader,new int[] {20, 60,20},new int[]{ 10, 10,10});
			rg.addText("\n",0,0,0);
			
			Object[][] personDetails=new Object[3][1];
			personDetails[0][0] = "Tax Deduction Account No.(TAN) ";
			personDetails[1][0] ="Full Name ";
			personDetails[2][0] = "Complete Address with City & State";
			
			rg.tableBodyNoBorder(personDetails,new int[] {10},new int[]{ 10});
			
			Object[][] personDetails1=new Object[1][3];
			personDetails1[0][0]="Tel.No           :";
			personDetails1[0][1]="";
			personDetails1[0][2]="Pin :";	
			rg.tableBodyNoBorder(personDetails1,new int[] {10, 10,10},new int[]{ 10, 10,10});
			rg.addText("\n",0,0,0);
			Object[][] paymentDetails=new Object[1][3];
			paymentDetails[0][0]="Type of Payment";
			paymentDetails[0][1]="Code*";	
			paymentDetails[0][2]="FOR USE IN RECEIVING BANK";
			rg.tableBodyNoBorder(paymentDetails,new int[] {10, 10, 10},new int[]{ 10, 10, 10});
			
			Object[][] paymentDetails1=new Object[1][3];
			paymentDetails1[0][0]="(Tick One)";
			paymentDetails1[0][1]="(Please see overleaf)";	
			paymentDetails1[0][2]="Debit to A/c/Cheque credited on";
			rg.tableBodyNoBorder(paymentDetails1,new int[] {10, 10, 10},new int[]{ 10, 10, 10});
			
			Object[][] paymentDetails3=new Object[2][2];
			
			paymentDetails3[0][0] = "TDS/TCS Payable by Taxpayer                             :(100)";
			paymentDetails3[1][0] = "TDS/TCS RegularAssessment(Raised by I.T Deptt) :(400)";
			rg.tableBodyNoBorder(paymentDetails3,new int[] {10, 10},new int[]{ 10, 10});
			
			Object[][] dateHeader=new Object[1][3];
			
			dateHeader[0][0] = "";
			dateHeader[0][1] = "";
			dateHeader[0][2] = "DD   MM    YY";
			rg.tableBodyNoBorder(dateHeader,new int[] {30, 40,30},new int[]{ 10, 10,10});
			rg.addTextBold("DETAILS OF PAYMENT", 0,0,0);
	
			
			
			
			Object[][] paymentDetails4=new Object[7][7];
			paymentDetails4[0][0] = "Income Tax";
			paymentDetails4[1][0] = "Sucharge";
			paymentDetails4[2][0] = "Ed.Cess(Prim)";
			paymentDetails4[3][0] = "Ed.Cess(Sec & H)";
			paymentDetails4[4][0] = "Interest";
			paymentDetails4[5][0] = "Penality";
			paymentDetails4[6][0] = "Toatal";
			
			rg.tableBodyNoBorder(paymentDetails4,new int[] {10, 10,10,10, 10,10,10},new int[]{ 10, 10,10,10, 10,10,10});
			
			rg.addText("\n", 0,0,0);
			rg.addTextBold("Total(in words)", 0,0,0);
		    String  strSrNo="CRORES";
		    String strMonth="LACS";
		    String strChallan="THOUSANDS";
		    String strAmt="HUNDREDS";
		    String strBankName="TENS";
		    Object[][] tableData=new Object[1][5];
		    tableData[0][0]=strSrNo;
		    tableData[0][1]=strMonth;
		    tableData[0][2]=strChallan;
		    tableData[0][3]=strAmt;
		    tableData[0][4]=strBankName;
			int tableCellwidth [] ={20, 20, 20, 20, 20};
			int tableAllignment [] ={1, 1, 1, 1, 1};
			rg.tableBody(tableData, tableCellwidth,tableAllignment);
			//rg.tableBodyNoBorder( tableData, tableCellwidth,tableAllignment);
			
			rg.addText("\n",0,0,0);
			Object[][] accDetails=new Object[1][2];
			String chequeNo="Paid in Cash/Debit to A/c/Cheque No.";
			String labelName="Dated.";
			accDetails[0][0] =chequeNo; 
			accDetails[0][1] = labelName;
			rg.tableBodyNoBorder(accDetails,new int[] {20, 10},new int[]{ 10, 10});
			
			//rg.addText("Drawn on", 0,0,0);
			
			Object[][] drawn=new Object[1][1];
			drawn[0][0] ="Drawn on"; 
			rg.tableBodyNoBorder(drawn,new int[] {20},new int[]{ 10});
			
			rg.addText("(Name of the Bank and Branch)", 0,1,0);
			rg.addText("\n",0,0,0);
			Object[][] header2=new Object[1][2];
			header2[0][0] ="Date"; 
			header2[0][1] = "Signature of person making payment";
			rg.tableBodyNoBorder(header2,new int[] {20, 10},new int[]{ 10, 10});
			
			rg.addTextBold("Taxpayers Counterfoil(To be fill up by tax payer)        "+"         SPACE FOR BANK SEAL", 0,0,0);
			//rg.addText("TAN",0,0,0);
			Object[][] tan=new Object[1][1];
			tan[0][0] ="TAN";  
			rg.tableBodyNoBorder(tan,new int[] {100},new int[]{ 10});
			
			Object[][] receiveMsg=new Object[1][1];
			receiveMsg[0][0] ="Received from";  
			rg.tableBodyNoBorder(receiveMsg,new int[] {100},new int[]{ 10});
			rg.addText("(Name)",0,1,0);		
			
			Object[][] header1=new Object[1][2];
			header1[0][0] ="Cash/Debit to A/c /Cheque No.";; 
			header1[0][1] = "for Rs.";;
			rg.tableBodyNoBorder(header1,new int[] {20, 10},new int[]{ 10, 10});
		
			Object[][] receiveMsg1=new Object[1][1];
			receiveMsg1[0][0] ="Rs(in words)";  
			rg.tableBodyNoBorder(receiveMsg1,new int[] {100},new int[]{ 10});
			
			Object[][] receiveMsg2=new Object[1][1];
			receiveMsg1[0][0] ="Drawn on";  
			rg.tableBodyNoBorder(receiveMsg2,new int[] {100},new int[]{ 10});
			
			rg.addText("(Name of the Bank and Branch)",0,1,0);
			
			Object[][] header11=new Object[1][2];
			header11[0][0] ="Company/Non-Company"; 
			header11[0][1] = "Deductees"; 
			rg.tableBodyNoBorder(header11,new int[] {10, 10},new int[]{ 10, 10});
			
			Object[][] taxMessage=new Object[1][2];
			taxMessage[0][0] ="On account of Tax Deducted at Source(TDS)/Tax collected at source(TCS) from"; 
			taxMessage[0][1] = "(Fill up code)"; 
			rg.tableBodyNoBorder(taxMessage,new int[] {80, 20},new int[]{ 10, 10});
			
			rg.addText("for the Assessment Year",0,0,0);
			rg.addText("\n",0,0,0);
		    rg.addTextBold("* NOTES", 0,1,0);
		    rg.addTextBold("Please note thatquoting false TAN may attract a penality of RS.10000/- as per section 272BB of I.T Act ,1961. ", 0,0,0);
		    rg.addTextBold("Use a Seperate challan for each nature(type)of payment.The relevant Codes are :", 0,0,0);
		    
		    Object[][] header=new Object[3][3];
		    header[0][0]="Section ";
		    header[0][1]="Nature of Payment";
		    header[0][2]="Code";
			rg.tableBodyNoBorder(header,new int[] {10, 70, 20},new int[]{ 10, 10, 10});
			
		    
		    Object[][] message=new Object[32][32];
			
		    message[0][0] = "192";
		    message[0][1] = "Payment to Govt.Employees other than Union Government Employees ";
		    message[0][2] = "92A";
		    
		    message[1][0] = "192";
		    message[1][1] = "Payment of Employees other than  Government Employees ";
		    message[1][2] = "92B";
		    
		    message[2][0] = "193";
		    message[2][1] = "Interest on Securities";
		    message[2][2] = "193";
		    
		    message[3][0] = "194";
		    message[3][1] = "Dividend";
		    message[3][2] = "194";
		    
		    message[4][0] = "194A";
		    message[4][1] = "Interest other than intereston securities";
		    message[4][2] = "94A";
		    
		    message[5][0] = "194B";
		    message[5][1] = "Winning from lotteries and crossword puzzles";
		    message[5][2] = "94B";
		    
		    message[6][0] = "194BB";
		    message[6][1] = "Winning from horse race";
		    message[6][2] = "4BB";
		    
		    message[7][0] = "194C";
		    message[7][1] = "Payment of contractor and sub-contractor";
		    message[7][2] = "94C";
		    
		    message[8][0] = "194D";
		    message[8][1] = "Insurance Commission";
		    message[8][2] = "94D";
		    
		    message[9][0] = "194E";
		    message[9][1] = "Payments to non-resident sportsmen/Sport Association ";
		    message[9][2] = "94E";
			
			message[10][0] = "194EE";
		    message[10][1] = "Payments in respect of deposits under National Savings  Schemes ";
		    message[10][2] = "4EE";
		    
		    message[11][0] = "194F";
		    message[11][1] = "Payments on account of Re-purchase of units by mutual funds UTI";
		    message[11][2] = "94F";
		    
		    message[12][0] = "194G";
		    message[12][1] = "Commission ,Prize etc.on safe of lottery tickets";
		    message[12][2] = "94G";
		    
		    message[13][0] = "194H";
		    message[13][1] = "Commission or Brokeage";
		    message[13][2] = "94H";

			message[14][0] = "194I";
		    message[14][1] = "Rent";
		    message[14][2] = "94I";
			
			message[15][0] = "194J";
		    message[15][1] = "Fees for ProfessionalOr I.T Services";
		    message[15][2] = "94J";
		    
		    message[16][0] = "194k";
		    message[16][1] = "Income payable to a Residence Assessee in respect  of units of a Specified mutual fund or of the unit of the UTI";
		    message[16][2] = "94k";
					   
		    message[17][0] = "194LA";
		    message[17][1] = "Payment of Compensation on Acquisition of certain immovable Property";
		    message[17][2] = "94L";

			message[18][0] = "195";
		    message[18][1] = "Other sums payable to a non-residents.";
		    message[18][2] = "195";
		   	
		   	message[19][0] = "196A";
		    message[19][1] = "Income in respect of  units of non-residents";
		    message[19][2] = "96B";		   			    
		
		    		    
		    message[20][0] = "196B";
		    message[20][1] = "Payments in respect of  units to an offshore Fund";
		    message[20][2] = "96B";
		    
		    message[21][0] = "196C";
		    message[21][1] = "Income from foreign Currency Bonds or Shares of Indian Company payable to Non-Resident";
		    message[21][2] = "96C";
		    
		    message[22][0] = "196D";
		    message[22][1] = "Income of foreign institutional investors from securities";
		    message[22][2] = "96D";
		    
		    message[23][0] = "206C";
		    message[23][1] = "Collection at source from alcoholic liquor for human consumption";
		    message[23][2] = "6CA";

			message[24][0] = "206C";
		    message[24][1] = "Collection at source from Timber obtained under Forest lease.";
		    message[24][2] = "6CB";
		    
		    message[25][0] = "206C";
		    message[25][1] = "Collection at source from Timber obtained any mode other than a Forest lease.";
		    message[25][2] = "6CC";

			message[26][0] = "206C";
		    message[26][1] = "Collection at source from any other Forest Produce(not being Tendu Lease).";
		    message[26][2] = "6CD";
		    
		    message[27][0] = "206C";
		    message[27][1] = "Collection at source from Scrap.";
		    message[27][2] = "6CE";
		    
		    message[28][0] = "206C";
		    message[28][1] = "Collection at source from Contractor or licensce or lease relating to parking lots.";
		    message[28][2] = "6CF";
		    
		    message[29][0] = "206C";
		    message[29][1] = "Collection at source from Contractor or licensce or lease relating to toll plaza.";
		    message[29][2] = "6CG";
		    
		    message[30][0] = "206C";
		    message[30][1] = "Collection at source from Contractor or licensce or lease relating to mine or quarry.";
		    message[30][2] = "6CH";
		    
		    message[31][0] = "206C";
		    message[31][1] = "Collection at source from Tendu leaves.";
		    message[31][2] = "6CI";
		    int messagewidth[]= {10,70,20};
			int messagealign[]= {0,0,0};
			rg.tableBodyNoBorder(message,messagewidth,messagealign);
			
		   Object[][] instList2=new Object[1][1];
		   String message4="PLEASE TICK THE RELEVANT BOX AT THE TOP OF THE CHALLAN.SEPERATE CHALLANS SHOULD BE USED FOR DEPOSITING TAX DEDUCTED AT SOURCE FROM COMPANY DEDUCTEES AND FROM NON-COMPANYDEDUCTES";
		   instList2[0][0]=message4;
		   rg.tableBodyNoBorder(instList2, cellwidthForParaEmployer, allignForParaEmployer);
		   String message5 = "KINDLY ENSURE THAT THE BANK'SACKNOWLEDGE CONTAINS  FOLLOWING  :-"
		   +"  \n1. 7 DIGIT BSR CODE OF THE BANK BRANCH."
		   +"  \n2. DATE OF DEPOSITE OF CHALLAN (DD MM YY)."
		   +"  \n3. CHALLAN SERIAL NUMBER."
		   +"  \n THESE WILL HAVE TO BE QUOTED IN YOUR RETURN OF INCOME.";   
		   Object[][] instList3=new Object[1][1];
		   instList3[0][0]=message5;
		   rg.tableBodyNoBorder(instList3, cellwidthForParaEmployer, allignForParaEmployer);
		   rg.createReport(response);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.getMessage());
		}
	
	
	}
	
}
