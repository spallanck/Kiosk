<?php
	header("Location: http://localhost/Person.php");

	// Image stuff
	$img_dir = "C:/temp/kiosk/images/person/";
	$img_pro = basename($_FILES["proPic"]["name"]);
	$img_main = basename($_FILES["mainPic"]["name"]);
	$proFlag = 1;
	$mainFlag = 1;

	// Check if image files are actual image or fake image
	if(isset($_POST["submit"])) {
		$check = getimagesize($_FILES["proPic"]["tmp_name"]);
		if($check !== false) {
			$proFlag = 1;
		} else {
			$proFlag = 0;
		}
		$check = getimagesize($_FILES["mainPic"]["tmp_name"]);
		if($check !== false) {
			$mainFlag = 1;
		} else {
			$mainFlag = 0;
		}
	}

	// Allow certain file formats
	//only JPG, JPEG, & PNG files are allowed
	$imageFileType = strtolower(pathinfo($img_pro,PATHINFO_EXTENSION));
	if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg") {
		$proFlag = 0;
	}
	$imageFileType = strtolower(pathinfo($img_main,PATHINFO_EXTENSION));
	if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg") {
		$mainFlag = 0;
	}

	// Upload files if valid
	if($proFlag == 1) {
		move_uploaded_file($_FILES["proPic"]["tmp_name"], $img_dir . $img_pro);
	} else {
		$img_pro = null;
	}
	if($mainFlag == 1) {
		move_uploaded_file($_FILES["mainPic"]["tmp_name"], $img_dir . $img_main);
	} else {
		$img_main = null;
	}

	// Database stuff
	$id = NULL;
	$name = $_POST['name'];
	$email = $_POST['email'];
	$room = $_POST['room'];
	$phone = $_POST['phone'];
	$interest = $_POST['interest'];
	$bio = $_POST['bio'];
	$class = $_POST['class'];

	// Database connection
	$conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
	if($conn->connect_error){
		die("Connection Failed : ". $conn->connect_error);
	} else {
		$stmt = $conn->prepare("insert into Person(id, name, email, room, phone, interests, biography, class, mainPic, profilePic) 
		values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		$stmt->bind_param("isssssssss", $id, $name, $email, $room, $phone, $interest, $bio, $class, $img_main, $img_pro);
		$execval = $stmt->execute();
		$stmt->close();
		$conn->close();
	}
	exit();
?>