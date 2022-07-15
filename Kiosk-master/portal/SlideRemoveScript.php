<?php
	header("Location: http://localhost/Slide.php");
	$conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
	if($conn->connect_error){
		die("Connection Failed : ". $conn->connect_error);
	}else {
		session_start();
		$sql = "DELETE FROM Slides WHERE ID = " . $_SESSION['removeSlide'];
		$conn->query($sql);
		$conn->close();
	}
	exit();
?>