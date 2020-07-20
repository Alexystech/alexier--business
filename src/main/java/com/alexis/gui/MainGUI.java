/*
 * Copyright (c) Develop by Alexis Vazquez
 */

package com.alexis.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import com.alexis.connectiondb.ConnectionToDB;

public class MainGUI extends JFrame {
    private JTextField textField1;
    private JButton REGISTRARButton;
    private JButton GENERARDOCUMENTOButton;
    private JPanel mainPanel;
    private JList list1;

    private ConnectionToDB connectionToDB;
    private ResultSet resultSet;

    public MainGUI(ConnectionToDB connectionToDB) {
        this.connectionToDB = connectionToDB;

        add(mainPanel);
        setVisible(true);
        setSize(700,500);

        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    /**
     * otro pedo para el final
     * @param
     * @return
     */

    public void setJList(String[] arr) {
        list1.setListData(arr);
    }
}
