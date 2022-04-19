<%@page import="net.etfbl.ip.vm.dto.Transaction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="userBean" type="net.etfbl.ip.vm.beans.UserBean" scope="session"></jsp:useBean>
<jsp:useBean id="transactionBean" type="net.etfbl.ip.vm.beans.TransactionBean" scope="session"></jsp:useBean>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Profile | Virtual Bank</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <link rel="icon" href="./assets/vb.png">
    
    <link rel="stylesheet" href="./styles/profile.css" />
  </head>
  

  <body>
    <header>
      <div class="logo"></div>
      <a href="?action=logout"  class="btn btn-outline-secondary">Log out</a>
    </header>

    <h1>Welcome back, <%= userBean.getUser().getFirstName() %>!</h1>

       <div class="info">
        <div class="balance">
            <p><b>Balance:</b> &nbsp; $ <%= userBean.getCard().getBalance() %></p>
          </div>
          <div class="number">
            <p><b>Card number:</b> &nbsp; <%= userBean.getCard().getNumber() %></p>
          </div>
        <div class="disable-div"> 
         
          <% if(userBean.getCard().isActive()) {%> 
           <div class="circle" style="background-color: green;"></div>  
          <p>Card is active.</p>
          <a  class="btn btn-outline-danger disable-btn" href="?action=toggle">Disable card</a>
          <% } else { %> 
           <div class="circle" style="background-color: red;"></div>  
           <p>Card is not active.</p>
          <a class="btn btn-outline-success disable-btn" href="?action=toggle" >Enable card</a>
           <% } %>
        </div>
    </div>
   
   

    <h2>Transactions</h2>
    <hr width="100%" />

    <div class="transactions">
    <% if(transactionBean.getTransactions(userBean.getCard().getId())==null) { %>
    <p>No transactions.</p>
    <% } else { %>    
      <table class="table table-hover">
        <thead>
          <tr>
            <th scope="col">Transaction ID</th>
            <th scope="col">Time</th>
            <th scope="col">Amount ($)</th>
          </tr>
        </thead>
        <tbody>
        <% for (Transaction transaction : transactionBean.getTransactions(userBean.getCardId())) { %>
          <tr>
            <th scope="row"><%= transaction.getId() %></th>
            <td><%= transaction.getTimestamp() %></td>
            <td><%= transaction.getAmount() %></td>
          </tr>
         <% } %>
        </tbody>
      </table>
      <% } %>
    </div>

    <div class="footer">
        <p>ip 2022 &copy;</p>
    </div>
    <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
