package prototypefys;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Daniel
 */
public class resetPassword {

    public resetPassword() {

    }

    public StackPane maakPasswordReset() {

        StackPane basis = new StackPane();

        HBox base = new HBox(200);
        base.setAlignment(Pos.CENTER);

        VBox vbox1 = new VBox(20);
        vbox1.setAlignment(Pos.CENTER);

        return basis;
    }

    public static void main(String[] args) {
        int generatedPass;

        generatedPass = (int) (Math.random() * 999999 + 100000);

        System.out.println(generatedPass);

    }

}
