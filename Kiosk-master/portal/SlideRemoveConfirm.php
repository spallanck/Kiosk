<!DOCTYPE html>
<html>
<head>
    <title>Removing Slide</title>
</head>

<?php
    $conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
    if($conn->connect_error){
        die("Connection Failed : ". $conn->connect_error);
    }
    $img_dir = "/kiosk/images/slide/";
    $passVal = $_POST['button'];
    $sql = "SELECT * FROM Slides WHERE ID = " . $passVal;
    $result = $conn->query($sql);
    $row = $result->fetch_assoc();
    $conn->close();
    session_start();
    $_SESSION['removeSlide'] = $row["ID"];
?>

<body>
    <h1>Removing Slide </h1>
    <p> Remove this slide from the database?</p>
    <div class=""><a href="SlideRemoveScript.php">Yes</a></div>
    <br>
    <div class=""><a href="Slide.php">No</a></div>
    <br>
    <img src="<?php echo $img_dir . $row["Image"]?>" alt="Slide picture" style="width:200px;height:200px;">
</body>
</html>