<!DOCTYPE html>
<html>
<head>
    <title>Clubs</title>
</head>
<body>
    <h1>Department Clubs Page</h1>
    <div class=""><a href="index.html">Main Menu</a></div>
    <br />
    <h2>List of Clubs</h2>
    <div class="Club Table">

        <?php
        $conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
        if($conn->connect_error){
            echo "$conn->connect_error";
            die("Connection Failed : ". $conn->connect_error);
        } else {
            $sql = "SELECT * FROM Clubs ORDER BY Title ASC";
            $result = $conn->query($sql);

            if ($result->num_rows > 0) {
                while($row = $result->fetch_assoc()) {
                    $passVal = $row["ID"];
                    ?>
                    <form action="ClubEditForm.php" method="post">
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
                    >Edit</button>
                    <?php
                    echo $row["Title"], "<br>";
                }
            } else {
            echo "0 Clubs found";
            }
        }
        $conn->close();
        ?>

    </div>
    <h2>Other Features</h2>
    <div class=""><a href="ClubNewForm.php">Add a club here</a></div>
    <div class=""><a href="ClubRemoveForm.php">Remove a club here</a></div>

    <br /><br /><br />
    <div class=""><a href="index.html">Main Menu</a></div>
</body>
</html>