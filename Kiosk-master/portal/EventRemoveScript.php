<?php
	header("Location: http://localhost/Event.php");
	$conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
	if($conn->connect_error){
		die("Connection Failed : ". $conn->connect_error);
	}else {
		session_start();
		$sql = "DELETE FROM Event WHERE ID = " . $_SESSION['removeEvent'];
		$conn->query($sql);
		$conn->close();
	}
	exit();
?>