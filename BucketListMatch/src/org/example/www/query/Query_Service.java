/**
 * Query_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.example.www.query;

public interface Query_Service extends javax.xml.rpc.Service {
    public java.lang.String getquerySOAPAddress();

    public org.example.www.query.Query_PortType getquerySOAP() throws javax.xml.rpc.ServiceException;

    public org.example.www.query.Query_PortType getquerySOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
