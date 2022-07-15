<!DOCTYPE html>
<html>
<head>
    <title>Removing Club</title>
</head>

<?php
    $conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
    if($conn->connect_error){
        die("Connection Failed : ". $conn->connect_error);
    }
    $passVal = $_POST['button'];
    $sql = "SELECT * FROM Clubs WHERE ID = " . $passVal;
    $result = $conn->query($sql);
    $row = $result->fetch_assoc();
    $conn->close();
    session_start();
    $_SESSION['removeClub'] = $row["ID"];
?>

<body>
    <h1>Removing Club </h1>
    <p> Remove <?php echo $row["Title"]?> from the database?</p>

    <div class=""><a href="ClubRemoveScript.php">Yes</a></div>
    <br>
    <div class=""><a href="Club.php">No</a></div>
</body>
</html>