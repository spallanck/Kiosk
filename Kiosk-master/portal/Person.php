<!DOCTYPE html>
<html>
<head>
    <title>Personnel</title>
</head>
<body>
    <h1>Department Personnel Page</h1>
    <div class=""><a href="index.html">Main Menu</a></div>
    <br />
    <h2>List of Personnel</h2>
    <div class="Person Table">

        <?php
        $conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
        if($conn->connect_error){
            echo "$conn->connect_error";
            die("Connection Failed : ". $conn->connect_error);
        } else {
            $sql = "SELECT * FROM Person ORDER BY Name ASC";
            $result = $conn->query($sql);

            if ($result->num_rows > 0) {
                while($row = $result->fetch_assoc()) {
                    $passVal = $row["ID"];
                    ?>
                    <form action="PersonEditForm.php" method="post">
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
                    echo $row["Name"], "<br>";
                }
            } else {
            echo "0 People found";
            }
        }
        $conn->close();
        ?>

    </div>
    <h2>Other Features</h2>
    <div class=""><a href="PersonNewForm.php">Add a person here</a></div>
    <div class=""><a href="PersonRemoveForm.php">Remove a person here</a></div>

    <br /><br /><br />
    <div class=""><a href="index.html">Main Menu</a></div>
</body>
</html>