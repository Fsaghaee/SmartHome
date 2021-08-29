
<?php
if (!empty($_GET["ip"]) || !empty($_GET["status"])) {
    $url = "http://" . $_GET['ip'] . "/cm?cmnd=Power%20" . $_GET['status'];
    UpdateStatus($_GET['ip'], $_GET["status"]);
    $payload = file_get_contents($url);
}

function UpdateStatus($iptemp, $statustemp) {
    $xml = simplexml_load_file("LightsXml.xml");
    $i = -1;
    foreach ($xml->children() as $light) {
        $i = $i + 1;
        $ip = $light->ip;
        if ($ip == $iptemp) {

            $light->status[0] = $statustemp;
            writeLogFile("Ip :" . $ip = $light->ip . " Status :" . $light->status[0] . " Time :" . date('Y-m-d h:i:sa') . "</br>");
        }
    }
    $xml->asXML("LightsXml.xml");
}


$now = new DateTime();
$twoPm = new DateTime();

if ($now->format('h') == 00) {
  
    $file = 'log.txt';
   file_put_contents($file, "");
}
?>


<html>
    <head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>

        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <div class="container">
            <h3>Connected Devices :</h3>

            <table class="table table-sm table-dark">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Room</th>
                        <th scope="col">IP</th>
                    </tr>
                </thead>
                <tbody>

                    <?php
                    $xml01 = simplexml_load_file("LightsXml.xml");
                    $i = -1;
                    foreach ($xml01->children() as $light) {
                        $i = $i + 1;
                        echo"<tr>";
                        echo "<th>" . $i . "</th>";
                        echo "<td>" . $light->room . "</td>";
                        echo "<td>" . $light->ip . "</td>";
			echo "<td>" . $light->status . "</td>";
                        echo"</tr>";
                    }
                    ?>

                </tbody>
            </table>

       
            
            <h6>Log : </h6>
            <?php
         

            readfile('log.txt');
            ?>

    </body>
</html>
<?php


function writeLogFile($log) {
    $file = 'log.txt';
    file_put_contents($file, $log, FILE_APPEND | LOCK_EX);
}
?>