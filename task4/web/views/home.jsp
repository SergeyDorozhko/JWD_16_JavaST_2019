<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Task4</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="jumbotron text-center">
        <h1>Task4</h1>
    </div>
</div>

<div class="container">

    <div class="col-sm-5">

<%--        <form class="was-validated" action="/task4/result" method="post" enctype="multipart/form-data">--%>
    <form class="was-validated" method="post" enctype="multipart/form-data">

            <div class="row">

                <div class="custom-file">
                    <input type="text" name="description"/>


                    <input name="xmlFile" type="file" class="custom-file-input" id="xmlFile" accept="application/xml" required>
                    <label class="custom-file-label" for="xmlFile">Choose XML file</label>

                    <div class="invalid-feedback">File not selected</div>

                    <div class="valid-feedback">OK!</div>

                </div>

                <br/><br/><br/><br/>

            </div>

            <br/> <br/>
            <br/> <br/>


            <div class="form-group">
                <label for="exampleFormControlSelect1">Select Parser</label>
                <select class="form-control" id="exampleFormControlSelect1" name="parser">
                    <option>DOM</option>
                    <option>SAX</option>
                    <option>StAX</option>
                </select>
            </div>


            <br/> <br/>
            <br/>
            <br/>

            <div class="row">
                <button type="submit" class="btn btn-primary btn-md" value="result">Parse</button>

            </div>


        </form>

    </div>

</div>
</body>

</html>
