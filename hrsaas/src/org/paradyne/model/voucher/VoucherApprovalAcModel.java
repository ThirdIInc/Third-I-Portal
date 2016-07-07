package org.paradyne.model.voucher;

import java.util.ArrayList;
import org.paradyne.bean.voucher.VoucherApprovalAc;
import org.paradyne.lib.ModelBase;

public class VoucherApprovalAcModel extends ModelBase {

	public void collect(VoucherApprovalAc vouchApprov,String status)
	{
			Object [] stat = new Object[1];
			stat[0] = status;
			Object[][] result =  getSqlModel().getSingleResult(getQuery(1), stat);
						
			ArrayList<Object> tableList = new ArrayList<Object>();
		 for(int i=0;i<result.length;i++)
		{
			VoucherApprovalAc bean1= new VoucherApprovalAc();
			
			bean1.setVoucherNo((String.valueOf(result[i][0])));
			bean1.setEmpName(String.valueOf(result[i][2]));
			bean1.setDate(String.valueOf(result[i][3]));
			bean1.setTotalAmt(String.valueOf(result[i][4]));
			bean1.setCheckStatus(String.valueOf(result[i][5]));
			
			tableList.add(bean1);
		}
		
		vouchApprov.setList(tableList);

	}
	
	

	public boolean changeStatus(VoucherApprovalAc bean, String [] status, String [] voucherNo) {
		Object [][] changeStatus = new Object[status.length][2];
		boolean result = false;
		for (int i=0;i<status.length;i++){
			changeStatus[i][0] = status[i];
			changeStatus[i][1] = voucherNo[i];
		}
		String stat = bean.getStatus();
		
		result = getSqlModel().singleExecute(getQuery(2), changeStatus);
		collect(bean, stat);
      return result;
		
		
	}

	
}
