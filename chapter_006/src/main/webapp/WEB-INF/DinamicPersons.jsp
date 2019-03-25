<%--
  Created by IntelliJ IDEA.
  User: p.toporov
  Date: 22/03/2019
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function addRow() {
            var firstName = $('#first_name').val();
            var lastName = $('#Surname').val();
            var email = $('#email').val();
            if (validate()) {
                $('#table tr:last').after(
                    '<tr>'
                    + '<td>' + firstName + '</td>'
                    + '<td>' + lastName + '</td>'
                    + '<td>' + email + '</td>'
                    + '</tr>')
            }
        }

        function validate() {
            var result = true;
            var fields = document.getElementsByClassName('validate-form');
            var sex = document.getElementsByName('sex');
            for (var i = 0; i < fields.length; i++) {
                if (fields[i].lastElementChild.value === "" || (!sex[0].checked && !sex[1].checked)) {
                    alert("Fill in the main fields!");
                    result = false;
                    break;
                }
                else if ($('#email').val().indexOf('.', 0) == -1 || $('#email').val().indexOf('@', 0) == -1) {
                    alert("Email is not correct!");
                    result = false;
                    break;
                }
            }
            return result;
        }

        $($.ajax({
            method: "get",
            url: "./json",
            complete: function (data) {
                var userlist = JSON.parse(data.responseText);
                var result = "";
                for (var i = 0; i < userlist.length; i++) {
                    result = result.concat('<tr><td>' + userlist[i].Firstnamae + '</td><td>'
                        + userlist[i].Lastname + '</td><td>'
                        + userlist[i].email + '</td></tr>');
                }
                $('#table thead').after('<tbody>' + result + '</tbody>');
            }
        }));

        function ajaxResp() {
            var formData = {
                Firstnamae: $('#first_name').val(),
                Lastname: $('#Surname').val(),
                email: $('#email').val(),
                Password: $('#pwd').val(),
                sex: document.getElementsByName('sex')[0].checked
            };
            var jtext = JSON.stringify(formData);
            $.ajax({
                method: "POST",
                url: "./json",
                data: jtext,
                complete: function (data) {
                    console.log(data);
                    if (data.statusText === "success") {
                        addRow();
                    }
                }
            });
        }

        function addFormToServer() {
            if (validate()) {
                ajaxResp();
            }
        }

    </script>
</head>
<form>
    <div class="container">
        <div class="container-fluid">
            <h1 align="center"> My users list dynamic page</h1>
        </div>
        <form id="myForm" action="/json">
            <form action="/action_page">
                <div class="validate-form">
                    <label for="first_name">* First Name:</label>
                    <input type="text" class="form-control" placeholder="Enter first name" id="first_name" required>
                </div>
                <div class="validate-form">
                    <label for="Surname">Last Name:</label>
                    <input type="text" class="form-control" placeholder="Enter surname" id="Surname" required>
                </div>
                <div class="validate-form">
                    <label for="email">* Email address:</label>
                    <input type="email" class="form-control" placeholder="Enter E-mail" id="email" required>
                </div>
                <div>
                    <div class="validate-form">
                        <label for="pwd">* Password:</label>
                        <input type="password" class="form-control" placeholder="Enter password" id="pwd" required>
                    </div>
                </div>

                <label class="Sex">
                    <h>*</h>
                    <input type="radio" name="sex" id="SexM" checked>
                    :Male
                </label>
                <label class="Sex">
                    <input type="radio" name="sex" id="SexF">
                    :Female
                </label>
            </form>
            <div class="checkbox">
                <label><input type="checkbox"> Remember me</label>
            </div>
            <button type="submit" class="btn btn-default" onclick="return validate();">Submit</button>
            <button type="button" class="btn btn-default" onclick="return addFormToServer();">Add</button>
        </form>
        <div class="container">
            <table class="table table-striped" id="table">
                <thead>
                <tr>
                    <th>Firstname</th>
                    <th>Lastname</th>
                    <th>Email</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</form>
</body>
</html>

