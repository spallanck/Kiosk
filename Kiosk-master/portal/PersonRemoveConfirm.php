<!DOCTYPE html>
<html>
<head>
    <title>Removing Person</title>
</head>

<?php
    $conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
    if($conn->connect_error){
        die("Connection Failed : ". $conn->connect_error);
    }
    $passVal = $_POST['button'];
    $sql = "SELECT * FROM Person WHERE ID = " . $passVal;
    $result = $conn->query($sql);
    $row = $result->fetch_assoc();
    $conn->close();
    session_start();
    $_SESSION['removeID'] = $row["ID"];
?>

<body>
    <h1>Removing Person </h1>
    <p> Remove <?php echo $row["Name"]?> from the database?</p>

    <div class=""><a href="PersonRemoveScript.php">Yes</a></div>
    <div class=""><a href="Person.php">No</a></div>
</body>
</html>