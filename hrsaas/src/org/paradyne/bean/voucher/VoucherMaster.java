package org.paradyne.bean.voucher;

import java.util.ArrayList;

import org.paradyne.lib.BeanBase;

public class VoucherMaster extends BeanBase {
	
	private String voucherCode;
	private String voucherHead;
	private ArrayList voucherList;
	
	private String myPage ;
	private String hiddencode;
	private String show;
	private String hdeleteCode;
	
	ArrayList iteratorlist;
	
	
	
		
 
	private String empListLength;
	private String noData;
	
	
	private String totalRecords="";
	private String listLength="false";	
	ArrayList recordList;
	
	


	/**
	 * @return the listLength
	 */
	public String getListLength() {
		return listLength;
	}

	/**
	 * @param listLength the listLength to set
	 */
	public void setListLength(String listLength) {
		this.listLength = listLength;
	}

	/**
	 * @return the recordList
	 */
	public ArrayList getRecordList() {
		return recordList;
	}

	/**
	 * @param recordList the recordList to set
	 */
	public void setRecordList(ArrayList recordList) {
		this.recordList = recordList;
	}

	public String getHdeleteCode() {
		return hdeleteCode;
	}

	public void setHdeleteCode(String hdeleteCode) {
		this.hdeleteCode = hdeleteCode;
	}
public String getMyPage() {
		return myPage;
	}
	public void setMyPage(String myPage) {
		this.myPage = myPage;
	}
	public String getHiddencode() {
		return hiddencode;
	}
	public void setHiddencode(String hiddencode) {
		this.hiddencode = hiddencode;
	}
	public String getShow() {
		return show;
	}
	public void setShow(String show) {
		this.show = show;
	}
	public ArrayList getIteratorlist() {
		return iteratorlist;
	}
	public void setIteratorlist(ArrayList iteratorlist) {
		this.iteratorlist = iteratorlist;
	}
	public VoucherMaster()
	{
		
	}
	public VoucherMaster(String vcode,String vhead)
	{
		super();
		this.voucherCode=vcode;
		this.voucherHead=vhead;
		
	}
	public String getVoucherCode() {
		return voucherCode;
	}
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}
	public String getVoucherHead() {
		return voucherHead;
	}
	public void setVoucherHead(String voucherHead) {
		this.voucherHead = voucherHead;
	}
	public ArrayList getVoucherList() {
		return voucherList;
	}
	public void setVoucherList(ArrayList voucherList) {
		this.voucherList = voucherList;
	}

	/**
	 * @return the empListLength
	 */
	public String getEmpListLength() {
		return empListLength;
	}

	/**
	 * @param empListLength the empListLength to set
	 */
	public void setEmpListLength(String empListLength) {
		this.empListLength = empListLength;
	}

	/**
	 * @return the noData
	 */
	public String getNoData() {
		return noData;
	}

	/**
	 * @param noData the noData to set
	 */
	public void setNoData(String noData) {
		this.noData = noData;
	}

	/**
	 * @return the totalRecords
	 */
	public String getTotalRecords() {
		return totalRecords;
	}

	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(String totalRecords) {
		this.totalRecords = totalRecords;
	}

	
	
	
	
	
}
