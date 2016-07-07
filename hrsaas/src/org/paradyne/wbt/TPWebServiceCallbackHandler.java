
/**
 * TPWebServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.4.1  Built on : Aug 13, 2008 (05:03:35 LKT)
 */

    package org.paradyne.wbt;

    /**
     *  TPWebServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class TPWebServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public TPWebServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public TPWebServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for UpdateEmp_USERGUID_ToTechPortal method
            * override this method for handling normal response from UpdateEmp_USERGUID_ToTechPortal operation
            */
           public void receiveResultUpdateEmp_USERGUID_ToTechPortal(
                    org.paradyne.wbt.TPWebServiceStub.UpdateEmp_USERGUID_ToTechPortalResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from UpdateEmp_USERGUID_ToTechPortal operation
           */
            public void receiveErrorUpdateEmp_USERGUID_ToTechPortal(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for CheckTechnicianSession method
            * override this method for handling normal response from CheckTechnicianSession operation
            */
           public void receiveResultCheckTechnicianSession(
                    org.paradyne.wbt.TPWebServiceStub.CheckTechnicianSessionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from CheckTechnicianSession operation
           */
            public void receiveErrorCheckTechnicianSession(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for AddPrograms method
            * override this method for handling normal response from AddPrograms operation
            */
           public void receiveResultAddPrograms(
                    org.paradyne.wbt.TPWebServiceStub.AddProgramsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from AddPrograms operation
           */
            public void receiveErrorAddPrograms(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for UpdateWBTResultToTechPortal method
            * override this method for handling normal response from UpdateWBTResultToTechPortal operation
            */
           public void receiveResultUpdateWBTResultToTechPortal(
                    org.paradyne.wbt.TPWebServiceStub.UpdateWBTResultToTechPortalResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from UpdateWBTResultToTechPortal operation
           */
            public void receiveErrorUpdateWBTResultToTechPortal(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for HelloWorld method
            * override this method for handling normal response from HelloWorld operation
            */
           public void receiveResultHelloWorld(
                    org.paradyne.wbt.TPWebServiceStub.HelloWorldResponse result
                        ) {
           }

           
          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from HelloWorld operation
           */
            public void receiveErrorHelloWorld(java.lang.Exception e) {
            }
                


    }
    