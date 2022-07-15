<!DOCTYPE html>
<html>
<head>
    <title>Slides</title>
</head>
<body>
    <h1>Kiosk Slideshow Page</h1>
    <div class=""><a href="index.html">Main Menu</a></div>
    <div class=""><a href="SlideNewForm.php">Add a new slide</a></div>
    <br />
    <h2>List of Slides</h2>
    <div class="Slide Table">

        <?php
        $conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
        if($conn->connect_error){
            echo "$conn->connect_error";
            die("Connection Failed : ". $conn->connect_error);
        } else {
            $img_dir = "/kiosk/images/slide/";
            $sql = "SELECT * FROM Slides";
            $result = $conn->query($sql);

            if ($result->num_rows > 0) {
                while($row = $result->fetch_assoc()) {
                    $passVal = $row["ID"];
                    ?>
                    <img src="<?php echo $img_dir . $row["Image"]?>" alt="Slide picture" style="width:200px;height:200px;">
                    <form action="SlideRemoveConfirm.php" method="post">
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
                    >Remove</button> <br> <br>
                    <?php
                }
            } else {
            echo "0 Slides found";
            }
        }
        $conn->close();
        ?>

    </div>


    <br />
    <div class=""><a href="index.html">Main Menu</a></div>
    <div class=""><a href="SlideNewForm.php">Add a new slide</a></div>
</body>
</html>