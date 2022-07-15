<!DOCTYPE html>
<html>
<head>
    <title>Editing Event</title>
</head>

<?php
    $conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
    if($conn->connect_error){
        die("Connection Failed : ". $conn->connect_error);
    }
    $passVal = $_POST['button'];
    $sql = "SELECT * FROM Event WHERE ID = " . $passVal;
    $result = $conn->query($sql);
    $row = $result->fetch_assoc();
    $conn->close();

    session_start();
    $_SESSION['eventID'] = $passVal;
    $img_dir = "/kiosk/images/event/";
?>
<body>
    <h1>Editing <?php echo $row["Title"]?></h1>
    <h4>Required fields are followed by *</h4>
    <div class="form">
        <form action="EventEditScript.php" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">Title:*</label>
                <input type="text"
                       id="title"
                       name="title"
                       maxlength="255"
                       required="required" 
                       value="<?php echo $row["Title"]?>"/>
            </div>
            <div class="form-group">
                <label for="date">Date:*</label>
                <input type="text"
                       id="date"
                       name="date"
                       maxlength="255"
                       required="required"
                       value="<?php echo $row["Date"]?>"/>
            </div>
            <div class="form-group">
                <label for="time">Time:*</label>
                <input type="text"
                       id="time"
                       name="time"
                       maxlength="255"
                       required="required"
                       value="<?php echo $row["Time"]?>"/>
            </div>
            <div class="form-group">
                <label for="loc">Location: </label>
                <input type="text"
                       id="loc"
                       name="loc"
                       maxlength="255"
                       value="<?php echo $row["Location"]?>"/>
            </div>
            <div class="form-group">
                Event Description:<br />
                <textarea rows="3" cols="50" id="desc" name="desc" maxlength="255"><?php echo $row["Description"]?></textarea>
            </div>
            <p>**If no image is provided the existing image will be used. </p>
            <img src="<?php echo $img_dir . $row["Image"]?>" alt="Current Image" style="width:200px;height:200px;">
            <div class="form-group">
                <label for="img">Event Image:</label>
                <input type="file"
                       id="img"
                       name="img" />
            </div>
            <br />
            <div class="form-group">
                <input type="Submit" class="btn btn-primary" />
            </div>
            <br>
            <div class=""><a href="Event.php">Back to Events Page</a></div>
            <div class=""><a href="index.html">Main Menu</a></div>
            <br>
            <div class=""><h4>Leaving page will not save data, please use submit button</h4></div>
            <h3>About Date/Time format</h3>
            <p>**Time format does not matter, could be "4pm", "11-3", "all day", etc.</p>
            <p>**Date format does not matter, could be "4/11/2022", "6-31", "Thursday", etc.</p>

            <br />
        </form>
    </div>
</body>
</html>