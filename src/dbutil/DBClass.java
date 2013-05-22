package src.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/** Database connection class & utilities **/

public class DBClass {

  public  String sDriver = "org.sqlite.JDBC"; 
  public  String sUrl = null;
  public  int iTimeout = 30;
  public  Connection conn = null;
  public  Statement statement = null;

  public  DBClass()
  {}

  public DBClass(String sUrlToLoad) throws Exception {
    init(sUrlToLoad);
  }

  public void init(String sUrlVar) throws Exception {
    setUrl(sUrlVar);
    setConnection();
    setStatement();
  }

  private void setUrl(String sUrlVar) {
    sUrl = sUrlVar;
  }

  public  void setConnection() throws Exception {
    Class.forName(sDriver);
    conn = DriverManager.getConnection(sUrl);
  }


  public  Connection getConnection() {
    return conn;
  }

  public  void setStatement() throws Exception {
    if (conn == null) {
      setConnection();
    }
    statement = conn.createStatement();
    statement.setQueryTimeout(iTimeout);  // set timeout to 30 sec.
  }

  public  Statement getStatement() {
    return statement;
  }

  public  void executeStmt(String instruction) throws SQLException {
    statement.executeUpdate(instruction);
  }


  public void executeStmt(String[] instructionSet) throws SQLException {
    for (int i = 0; i < instructionSet.length; i++) {
      executeStmt(instructionSet[i]);
    }
  }

  public ResultSet executeQry(String instruction) throws SQLException {
    return statement.executeQuery(instruction);
  } 

  public void closeConnection() {
    try { conn.close(); } catch (Exception ignore) {}
  }

}