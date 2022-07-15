<?php
	header("Location: http://localhost/Person.php");
	$conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
	if($conn->connect_error){
		die("Connection Failed : ". $conn->connect_error);
	}else {
		session_start();
		$sql = "DELETE FROM Person WHERE ID = " . $_SESSION['removeID'];
		$conn->query($sql);
		$conn->close();
	}
	exit();
?>