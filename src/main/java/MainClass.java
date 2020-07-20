/*
 * Copyright (c) Develop by Alexis Vazquez
 */

import com.alexis.connectiondb.ConnectionToDB;
import com.alexis.gui.MainGUI;
import com.alexis.gui.PruevaFX;

import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {

        ConnectionToDB connectionToDB = new ConnectionToDB();
        MainGUI myGUI = new MainGUI(connectionToDB);
        myGUI.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }
}
