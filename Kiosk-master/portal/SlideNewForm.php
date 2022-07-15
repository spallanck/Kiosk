<!DOCTYPE html>
<html>
<head>
    <title>Adding Slide</title>
</head>
<body>
    <h1>Add New Slide</h1>
    <div class="form">
        <form action="SlideNewScript.php" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="img">Slide Image:</label>
                <input type="file"
                       id="img"
                       name="img" />
            </div>
            <div class="form-group">
                <input type="Submit" class="btn btn-primary" />
            </div>
            <br><br>
            <div class=""><a href="Slide.php">Back to Slideshow Info</a></div>
            <div class=""><a href="index.html">Main Menu</a></div>

            <br />
           
        </form>
    </div>
</body>
</html>