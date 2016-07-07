/**
 * EmployeeUploadsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class EmployeeUploadsResponse  implements java.io.Serializable {
    private java.lang.Object employeeUploadsResult;

    public EmployeeUploadsResponse() {
    }

    public EmployeeUploadsResponse(
           java.lang.Object employeeUploadsResult) {
           this.employeeUploadsResult = employeeUploadsResult;
    }


    /**
     * Gets the employeeUploadsResult value for this EmployeeUploadsResponse.
     * 
     * @return employeeUploadsResult
     */
    public java.lang.Object getEmployeeUploadsResult() {
        return employeeUploadsResult;
    }


    /**
     * Sets the employeeUploadsResult value for this EmployeeUploadsResponse.
     * 
     * @param employeeUploadsResult
     */
    public void setEmployeeUploadsResult(java.lang.Object employeeUploadsResult) {
        this.employeeUploadsResult = employeeUploadsResult;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EmployeeUploadsResponse)) return false;
        EmployeeUploadsResponse other = (EmployeeUploadsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.employeeUploadsResult==null && other.getEmployeeUploadsResult()==null) || 
             (this.employeeUploadsResult!=null &&
              this.employeeUploadsResult.equals(other.getEmployeeUploadsResult())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getEmployeeUploadsResult() != null) {
            _hashCode += getEmployeeUploadsResult().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
