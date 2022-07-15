<?php
	header("Location: http://localhost/Club.php");

	// Database stuff
	session_start();
	$id = $_SESSION['clubID'];
	$title = $_POST['title'];
	$date = $_POST['date'];
	$time = $_POST['time'];
	$loc = $_POST['loc'];
	$desc = $_POST['desc'];
	$inc = 1;

	// Database connection
	$conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
	if($conn->connect_error){
		die("Connection Failed : ". $conn->connect_error);
	} else {

		$sql = "DELETE FROM Clubs WHERE id = " . $id;
		$conn->query($sql);
		
		$stmt = $conn->prepare("insert into Clubs(id, title, description, time, day, include) 
		values(?, ?, ?, ?, ?, ?)");
		$stmt->bind_param("issssi", $id, $title, $desc, $time, $date, $inc);
		$execval = $stmt->execute();
		$stmt->close();
		$conn->close();
	}
	exit();
?>