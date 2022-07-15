<!DOCTYPE html>
<html>
<head>
    <title>Editing Person</title>
</head>

<?php
    $conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
    if($conn->connect_error){
        die("Connection Failed : ". $conn->connect_error);
    }
    $img_dir = "/kiosk/images/person/";
    $passVal = $_POST['button'];
    $sql = "SELECT * FROM Person WHERE ID = " . $passVal;
    $result = $conn->query($sql);
    $row = $result->fetch_assoc();
    $conn->close();

    session_start();
    $_SESSION['editID'] = $passVal;
?>
<body>
    <h1>Editing <?php echo $row["Name"]?></h1>
    <div class="form">
        <form action="PersonEditScript.php" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="name">Name:*</label>
                <input type="text"
                       id="name"
                       name="name"
                       maxlength="255"
                       required="required"
                       value="<?php echo $row["Name"]?>"/>
            </div>
            <div class="form-group">
                <label for="email">Email:*</label>
                <input type="text"
                       id="email"
                       name="email"
                       maxlength="255"
                       required="required" 
                       value="<?php echo $row["Email"]?>"/>
            </div>
            <div class="form-group">
                <label for="phone">Phone:*</label>
                <input type="tel"
                       id="phone"
                       name="phone"
                       maxlength="31"
                       required="required" 
                       value="<?php echo $row["Phone"]?>"/>
            </div>
            <div class="form-group">
                <p> **Only include room number "CF 123A" should be input as "123A"</p>
                <label for="room">Room: </label>
                <input type="text"
                       id="room"
                       name="room"
                       maxlength="15" 
                       value="<?php echo $row["Room"]?>"/>
            </div>
            <br />
            <div class="form-group">
                Classes:<br />
                <textarea rows="1" cols="50" id="class" name="class" maxlength="1023"><?php echo $row["Class"]?></textarea>
            </div>
            <div class="form-group">
                Interests:<br />
                <textarea rows="2" cols="50" id="interest" name="interest" maxlength="1023"><?php echo $row["Interests"]?></textarea>
            </div>
            <div class="form-group">
                Biography: <br />
                <textarea rows="4" cols="50" id="bio" name="bio" maxlength="1023" ><?php echo $row["Biography"]?></textarea>
            </div>
            <p>**If no image is provided the existing image will be used. </p>
            <img src="<?php echo $img_dir . $row["MainPic"]?>" alt="Card picture" style="width:200px;height:200px;">
            <div class="form-group">
                <label for="proPic">Card Picture:</label>
                <input type="file"
                       id="proPic"
                       name="proPic"/>
            </div>
            <img src="<?php echo $img_dir . $row["ProfilePic"]?>" alt="Card picture" style="width:200px;height:200px;">
            <div class="form-group">
                <label for="mainPic">Info Picture:</label>
                <input type="file"
                       id="mainPic"
                       name="mainPic"/>
            </div>
            <br />
            <div class="form-group">
                <input type="submit" class="btn btn-primary" />
            </div>

            <div class=""><h4>Leaving page will not save data, please use submit button</h4></div>
            <div class=""><a href="Person.php">Back to Person Info</a></div>
            <div class=""><a href="index.html">Main Menu</a></div>

            <br>
            <h3>About images</h3>
            <p>The card picture is the image located on the small person cards on the right side of the map feature.</p>
            <h4> **If no card picture is provided, a default picture will be used instead.</h4>
            <p>The info picture is the image located on the large person profile page that appears over the map.</p>
            <h4> **If no info page picture is provided, the above picture will be used if provided.</h4>
        </form>
    </div>
</body>
</html>