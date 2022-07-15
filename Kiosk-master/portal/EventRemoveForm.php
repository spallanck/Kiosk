<!DOCTYPE html>
<html>
<head>
    <title>Events</title>
</head>
<body>
    <h2>List of Events</h2>
    <div class="Event Table">

        <?php
        $conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
        if($conn->connect_error){
            echo "$conn->connect_error";
            die("Connection Failed : ". $conn->connect_error);
        } else {
            $sql = "SELECT * FROM Event ORDER BY Title ASC";
            $result = $conn->query($sql);

            if ($result->num_rows > 0) {
                while($row = $result->fetch_assoc()) {
                    $passVal = $row["ID"];
                    ?>
                    <form action="EventRemoveConfirm.php" method="post">
                        <imput type="submit"
                        value = <?php echo $passVal?> 
                        name="button"
                        id="button"/>
                    </form>
                    <button
                        type="submit" 
                        value="<?php echo $passVal?>"
                        name="button"
                        id="button"
                    >Remove</button>
                    <?php
                    echo $row["Title"], "<br>";
                }
            } else {
            echo "0 Events found";
            }
        }
        $conn->close();
        ?>
    </div> <br><br>
    <div class=""><a href="Event.php">Back to Event Info</a></div>
    <div class=""><a href="index.html">Main Menu</a></div>

</body>
</html>