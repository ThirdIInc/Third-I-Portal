/**
 * EmployeeUploads.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package webservice;

public class EmployeeUploads  implements java.io.Serializable {
    private java.lang.String getXML;

    public EmployeeUploads() {
    }

    public EmployeeUploads(
           java.lang.String getXML) {
           this.getXML = getXML;
    }


    /**
     * Gets the getXML value for this EmployeeUploads.
     * 
     * @return getXML
     */
    public java.lang.String getGetXML() {
        return getXML;
    }


    /**
     * Sets the getXML value for this EmployeeUploads.
     * 
     * @param getXML
     */
    public void setGetXML(java.lang.String getXML) {
        this.getXML = getXML;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EmployeeUploads)) return false;
        EmployeeUploads other = (EmployeeUploads) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.getXML==null && other.getGetXML()==null) || 
             (this.getXML!=null &&
              this.getXML.equals(other.getGetXML())));
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
        if (getGetXML() != null) {
            _hashCode += getGetXML().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

}
