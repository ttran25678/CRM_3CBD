<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <title>Title</title>
<style>
    body {
    background: rgb(99, 39, 120)
    }

    .form-control:focus {
        box-shadow: none;
        border-color: #BA68C8
    }

    .profile-button {
        background: rgb(99, 39, 120);
        box-shadow: none;
        border: none
    }

    .profile-button:hover {
        background: #682773
    }

    .profile-button:focus {
        background: #682773;
        box-shadow: none
    }

    .profile-button:active {
        background: #682773;
        box-shadow: none
    }

    .back:hover {
        color: #682773;
        cursor: pointer
    }

    .labels {
        font-size: 11px
    }

    .add-experience:hover {
        background: #BA68C8;
        color: #fff;
        cursor: pointer;
        border: solid 1px #BA68C8
    }
</style> 

</head>
  <body>
    <div class="container rounded bg-white mt-5 mb-5">
        <div class="row">
            <div class="col-md-3 border-right">
                
            </div>
            <div class="col-md-6 border-right">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <h4 class="text-right">Project Update</h4>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12"><label class="labels">Id</label><input type="text" class="form-control" placeholder="Id project" value=""></div>
                        <div class="col-md-12"><label class="labels">Name</label><input type="text" class="form-control" placeholder="Name project" value=""></div>
                        <div class="col-md-12"><label class="labels">Description</label><input type="text" class="form-control" placeholder="Description project" value=""></div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-md-6"><label class="labels">Start date</label><input type="text" class="form-control" placeholder="dd/mm/yyyy" value=""></div>
                        <div class="col-md-6"><label class="labels">End date</label><input type="text" class="form-control"  placeholder="dd/mm/yyyy" value=""></div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-12"><label class="labels">Owner</label><input type="text" class="form-control" placeholder="Owner id" value=""></div>
                    </div>
                    <div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button">Update</button></div>
                </div>
            </div>
            <div class="col-md-3">
                
            </div>
        </div>
    </div>
  </body>
</html>