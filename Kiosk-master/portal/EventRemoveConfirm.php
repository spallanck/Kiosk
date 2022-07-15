<!DOCTYPE html>
<html>
<head>
    <title>Removing Event</title>
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
    $_SESSION['removeEvent'] = $row["ID"];
?>

<body>
    <h1>Removing Event </h1>
    <p> Remove <?php echo $row["Title"]?> from the database?</p>

    <div class=""><a href="EventRemoveScript.php">Yes</a></div>
    <br>
    <div class=""><a href="Event.php">No</a></div>
</body>
</html>