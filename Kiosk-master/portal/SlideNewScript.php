<?php
	header("Location: http://localhost/Slide.php");

	// Image stuff
	$img_dir = "C:temp/kiosk/images/slide/";
	$img = basename($_FILES["img"]["name"]);
	$flag = 1;

	// Check if image file is actual image or fake image
	if(isset($_POST["submit"])) {
		$check = getimagesize($_FILES["img"]["tmp_name"]);
		if($check !== false) {
			$flag = 1;
		} else {
			$fFlag = 0;
		}
	}

	// Allow certain file formats
	//only JPG, JPEG, & PNG files are allowed
	$imageFileType = strtolower(pathinfo($img,PATHINFO_EXTENSION));
	if($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg") {
		$proFlag = 0;
	}

	// Upload files if valid
	if($flag == 1) {
		move_uploaded_file($_FILES["img"]["tmp_name"], $img_dir . $img);
		$id = NULL;
		$inc = 1;
		$conn = new mysqli('localhost','kioskteam','casaposh2202','KioskDatabase');
		if($conn->connect_error){
			die("Connection Failed : ". $conn->connect_error);
		} else {
			$stmt = $conn->prepare("insert into Slides(id, image, include) values(?, ?, ?)");
			$stmt->bind_param("isi", $id, $img, $inc);
			$execval = $stmt->execute();
			$stmt->close();
			$conn->close();
		}
	} else {
		$img = null;
	}
	exit();
?>