<?php
	header("Location: http://localhost/Club.php");
	$conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
	if($conn->connect_error){
		die("Connection Failed : ". $conn->connect_error);
	}else {
		session_start();
		$sql = "DELETE FROM Clubs WHERE ID = " . $_SESSION['removeClub'];
		$conn->query($sql);
		$conn->close();
	}
	exit();
?>