<?php
	header("Location: http://localhost/Event.php");

	// Image stuff
	$img_dir = "C:/temp/kiosk/images/event/";
	$img = basename($_FILES["img"]["name"]);
	$flag = 1;

	// Check if image files are actual image or fake image
	if(isset($_POST["submit"])) {
		$check = getimagesize($_FILES["img"]["tmp_name"]);
		if($check !== false) {
			$flag = 1;
		} else {
			$flag = 0;
		}
	}

	// Allow certain file formats
	//only JPG, JPEG, & PNG files are allowed
	$imageFileType = strtolower(pathinfo($img,PATHINFO_EXTENSION));
	if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg") {
		$flag = 0;
	}

	// Upload files if valid
	if($flag == 1) {
		move_uploaded_file($_FILES["img"]["tmp_name"], $img_dir . $img);
	} else {
		$img = null;
	}

	// Database stuff
	session_start();
	$id = $_SESSION['eventID'];
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
		// Old Entry
		$sql = "SELECT * FROM Event WHERE id = " . $id;
		$result = $conn->query($sql);
		$row = $result->fetch_assoc();
		if ($img == null) {
			$img = $row["Image"];
		}
		$sql = "DELETE FROM Event WHERE id = " . $id;
		$conn->query($sql);
		// New Entry
		$stmt = $conn->prepare("insert into Event(id, title, location, description, image, date, time, include) 
		values(?, ?, ?, ?, ?, ?, ?, ?)");
		$stmt->bind_param("issssssi", $id, $title, $loc, $desc, $img, $date, $time, $inc);
		$execval = $stmt->execute();
		$stmt->close();
		$conn->close();
	}
	exit();
?>