package org.example.www.query;

public class QueryProxy implements org.example.www.query.Query_PortType {
  private String _endpoint = null;
  private org.example.www.query.Query_PortType query_PortType = null;
  
  public QueryProxy() {
    _initQueryProxy();
  }
  
  public QueryProxy(String endpoint) {
    _endpoint = endpoint;
    _initQueryProxy();
  }
  
  private void _initQueryProxy() {
    try {
      query_PortType = (new org.example.www.query.Query_ServiceLocator()).getquerySOAP();
      if (query_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)query_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)query_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (query_PortType != null)
      ((javax.xml.rpc.Stub)query_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.example.www.query.Query_PortType getQuery_PortType() {
    if (query_PortType == null)
      _initQueryProxy();
    return query_PortType;
  }
  
  public int add(int req1) throws java.rmi.RemoteException{
    if (query_PortType == null)
      _initQueryProxy();
    return query_PortType.add(req1);
  }
  
  
}