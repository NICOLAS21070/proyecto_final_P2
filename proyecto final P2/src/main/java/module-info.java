module org.uniquindio.edu.co.poo.proyecto_final_p2 {
    requires javafx.controls;
    requires javafx.fxml;

    exports org.uniquindio.edu.co.poo.proyecto_final_p2;
    exports org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController;

    opens org.uniquindio.edu.co.poo.proyecto_final_p2.ViewController to javafx.fxml;
    opens org.uniquindio.edu.co.poo.proyecto_final_p2 to javafx.fxml;
    opens org.uniquindio.edu.co.poo.proyecto_final_p2.Model to javafx.base;



}
