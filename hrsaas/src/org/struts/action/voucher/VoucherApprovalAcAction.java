package org.struts.action.voucher;

import org.paradyne.bean.voucher.VoucherApprovalAc;
import org.paradyne.model.voucher.VoucherApprovalAcModel;

import org.struts.lib.ParaActionSupport;

public class VoucherApprovalAcAction extends ParaActionSupport {
	VoucherApprovalAc voucherApprovAc;
	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		voucherApprovAc = new VoucherApprovalAc();

	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return voucherApprovAc;
	}

	public VoucherApprovalAc getVoucherApprovAc() {
		return voucherApprovAc;
	}

	public void setVoucherApprovAc(VoucherApprovalAc voucherApprovAc) {
		this.voucherApprovAc = voucherApprovAc;
	}
	 public String callstatus()
	  {
		  VoucherApprovalAcModel model=new VoucherApprovalAcModel();
		   model.initiate(context,session);
		  String s=voucherApprovAc.getStatus();
			model.collect(voucherApprovAc,s);
			model.terminate();
			return "success";
		  
	  }
	  
	  public String save(){
		  VoucherApprovalAcModel model=new VoucherApprovalAcModel();
		  model.initiate(context,session);
		  boolean result=false;
		  String [] voucherNo = request.getParameterValues("voucherApprovAc.voucherNo");
		  String [] status    = request.getParameterValues("checkStatus");
		  
		  result = model.changeStatus(voucherApprovAc, status, voucherNo);
		  if(result){
			  addActionMessage("Records Are Saved Successfully..");
		  }else{
			  addActionMessage("Records Not Saved..!");
		  }
		  model.terminate();
		  return "success";
	  }
	
}
