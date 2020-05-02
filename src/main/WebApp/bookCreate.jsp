<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<center>
<h1>LIBRARY MANAGEMENT SYSTEM</h1><hr color="black">

<form action="addBooks" method="post">
  <table>
    <tr>
      <td align="right">ISBN:</td>
      <td align="left"><input type="text" name="isbn"></td>
    </tr>
    <tr>
      <td align="right">TITLE:</td>
      <td align="left"><input type="text" name="title"></td>
    </tr>
    <tr>
      <td align="right">AUTHOR:</td>
      <td align="left"><input type="text" name="author"></td>
    </tr>
    <tr>
      <td align="right">PRICE:</td>
      <td align="left"><input type="text" name="price"></td>
    </tr>
  </table>
  <input type="Submit" value="ADD BOOK">
</form><br><hr color="black">


<form action="getBooks" method="get" >
<b>Books to be find :</b>
<!--  <input type="text" name="isbn" placeholder="Enter ISBN">-->
<input type="Submit" value="List of Books">
</form><br><hr color="black">

<form action="deleteBooks" method="get" >
<b>Book to be removed:</b>
<input type="text" name="isbn" placeholder="Enter ISBN">
<input type="Submit" value="Remove Book">
</form><br><hr color="black">

<b>Update Book</b>
<form action="updateBooks" method="post">
  <table>
    <tr>
      <td align="right">ISBN:</td>
      <td align="left"><input type="text" name="isbn"></td>
    </tr>
    <tr>
      <td align="right">TITLE:</td>
      <td align="left"><input type="text" name="title"></td>
    </tr>
    <tr>
      <td align="right">AUTHOR:</td>
      <td align="left"><input type="text" name="author"></td>
    </tr>
    <tr>
      <td align="right">PRICE:</td>
      <td align="left"><input type="text" name="price"></td>
    </tr>
  </table>
  <input type="Submit" value="Update Book">
</form><br><hr color="black">

</center>
</body>
</html>
