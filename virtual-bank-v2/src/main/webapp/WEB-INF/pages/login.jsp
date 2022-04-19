<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login | Virtual Bank</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <link rel="icon" href="./assets/vb.png">
    <link rel="stylesheet" href="./styles/login.css" />
  </head>
  <body>
    <div class="content">

            <div class="img"></div>
            <h1>Welcome to the Virtual Bank!</h1>
            <h4>Please login to your account.</h4>
            <hr width="100%"/>
            <div class="form">
                <form action="?action=login" method="post">
                  <label for="cardholder">Cardholder</label>
                  <input type="text"  name="cardholder" id="cardholder" class="form-control">
                       
                   <label for="cardNumber">Card number</label>
                    <input type="text"  name="cardNumber" id="cardNumber" class="form-control">

                    <label for="password">Password</label>
                    <input type="password" name="password" id="password" class="form-control">

                    <button type="submit" class="btn btn-outline-primary">Login</button>
                </form>
            </div>
        </div>
        
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
