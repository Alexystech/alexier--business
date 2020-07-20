/*
 * Copyright (c) Develop by Alexis Vazquez
 */

package com.alexis.gui.explicit;

import com.alexis.connectiondb.ConnectionToDB;

import javax.swing.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.sql.ResultSet;

public class ExplicitMainGUI {

    private ConnectionToDB connectionToDB;
    private ResultSet resultSet;

    public ExplicitMainGUI(ConnectionToDB connectionToDB) {
        this.connectionToDB = connectionToDB;
    }

    public List<Integer> codePartition(String codigo) {
        List<Integer>partitions = new LinkedList<Integer>();
        int contador = 0;
        String temporal = "";

        for (int n = 0; n < codigo.length(); n++) {
            contador++;
            temporal += codigo.charAt(n);

            if (contador == 2) {
                partitions.add(Integer.parseInt(temporal));
                temporal = "";
                contador = 0;
            }
        }

        return partitions;
    }

    /**
     * con este metodo validare si las particiones del
     * codigo del producto esta dentro de los rangos
     * de un tipo/producto/empresa existente y si el
     * codigo que se quiere consultar esta registrado
     * en la base de datos
     * @param codePartition
     * @return
     */
    /**
     * queda pendiente resolver la parte de la
     * implementacion, tengo pensado usar un switch
     * para saber que indice del codePartition List
     * voy a evaluar si hay mas de 3 o menos de 3 se
     * va automaticamente a false.
     * la abstraccion quedaria asi...
     * - switch que me diga que evaluaciones voy a implementar
     *
     * - abran 3 casos validos donde se evaluara si hay una
     *   empresa / un tipo de producto / un producto
     *
     * - en este ultimo nivel de abstraccion se evaluara
     *   si el fragmentCode esta registrado en la base de datos
     * @param codePartition
     * @return
     * @throws SQLException
     */
    public boolean isCorrectQuery(List<Integer>codePartition) throws SQLException {
        byte numberCodePartition = 0;
        boolean isCorrectyQuery = false;

        for (int fragmentCode : codePartition) {
            switch (numberCodePartition) {
                case 1:
                    if (isBusiness(fragmentCode)) {
                        if (isRegisteredBusiness(fragmentCode)) {
                            isCorrectyQuery = true;
                        } else {
                            isCorrectyQuery = false;
                            break;
                        }
                    } else {
                        isCorrectyQuery = false;
                        break;
                    }
                    break;
                case 2:
                    if (isTipeOfProduct(fragmentCode)) {
                        if (isRegisteredTipeOfProduct(fragmentCode)) {
                            isCorrectyQuery = true;
                        } else {
                            isCorrectyQuery = false;
                            break;
                        }
                    } else {
                        isCorrectyQuery = false;
                        break;
                    }
                    break;
                case 3:
                    if (isAProduct(fragmentCode)) {

                    }
                    break;
                default:
                    isCorrectyQuery = false;
                    break;
            }
            numberCodePartition++;
        }

        return isCorrectyQuery;
    }

    public boolean isBusiness(int fragmentCode) {
        return (fragmentCode >= 111 && fragmentCode < 200)? true : false;
    }

    public boolean isTipeOfProduct(int fragmentCode) {
        return (fragmentCode >= 200 && fragmentCode < 500)? true : false;
    }

    public boolean isAProduct(int fragmentCode) {
        return (fragmentCode >= 500 && fragmentCode < 800)? true : false;
    }

    public boolean isRegisteredBusiness(int fragmentCode) throws SQLException {
        boolean isRegisteredBusiness = false;
        try {
            resultSet = connectionToDB
                    .getStatement()
                    .executeQuery("SELECT * FROM empresa WHERE codigo = "+ fragmentCode);

            while (resultSet.next()) {
                if (resultSet.getInt("codigo") == fragmentCode) {
                    isRegisteredBusiness = true;
                }
            }
        } catch (SQLException e) {
            isRegisteredBusiness = false;
        }
        return isRegisteredBusiness;
    }

    public boolean isRegisteredTipeOfProduct(int fragmentCode) throws SQLException{
        boolean isRegistered = false;
        try {
            resultSet = connectionToDB
                    .getStatement()
                    .executeQuery("SELECT * FROM tipo_producto WHERE codigo = "+ fragmentCode);

            while (resultSet.next()) {
                if (resultSet.getInt("codigo") == fragmentCode) {
                    isRegistered = true;
                }
            }
        } catch (SQLException e) {
            isRegistered = false;
        }
        return isRegistered;
    }

    public boolean isRegisteredProduct(int fragmentCode) throws SQLException {

        try {
            resultSet = connectionToDB
                    .getStatement()
                    .executeQuery("SELECT * FROM ");
        } catch (SQLException e) {
            return false;
        }
        //borrar
        return false;
    }

    public boolean queryInformation(int fragmentCode, String table, String codigo) throws SQLException {
        boolean isRegisterd = false;
        try {
            resultSet = connectionToDB
                    .getStatement()
                    .executeQuery("SELECT * FROM "+table+" WHERE "+codigo+" = "+fragmentCode);
            while (resultSet.next()) {
                if (isEqualCodeToFragmentCode(resultSet,codigo,fragmentCode)) {
                    isRegisterd = true;
                }
            }
        } catch (SQLException e) {
            isRegisterd = false;
        }
        return isRegisterd;
    }

    private boolean isEqualCodeToFragmentCode(ResultSet resultset,String codigo,int fragmentCode)
            throws SQLException {
        return (resultset.getInt(codigo) == fragmentCode)? true : false;
    }

    /**
     * Otro pedito que te tienes que aventar
     * despues de validar que el codigo
     * ingresado es correcto....
     * este metodo sirve para buscar la informacion que se
     * esta solicitando con el codigo ingresado en el
     * JTextField
     * @param codePartition
     * @return
     * @throws SQLException
     */
    public List<Object> queryInformation(List<Integer>codePartition) throws SQLException {
        List<Object>queryInformation = new LinkedList<Object>();

        String codigo = "";
        String empresa = "";
        String tipo_producto = "";
        String producto = "";
        double precio = 0;

        try {
            for (int n : codePartition) {

                if (n > 110 && n < 200) {
                    //empresas
                    resultSet = connectionToDB
                            .getStatement()
                            .executeQuery("SELECT * FROM empresa WHERE codigo = " + n);

                    while (resultSet.next()) {
                        empresa = resultSet.getString("nombre");
                        codigo = Integer.toString(resultSet.getInt("codigo"));
                        queryInformation.add(empresa);
                    }
                } else if (n > 199 && n <= 400) {
                    //tipo de productos
                    resultSet = connectionToDB
                            .getStatement()
                            .executeQuery("SELECT * FROM tipo_producto WHERE dni = " + n);

                    while (resultSet.next()) {
                        tipo_producto = resultSet.getString("nombre_tipo_producto");
                        codigo += Integer.toString(resultSet.getInt("dni"));
                        queryInformation.add(tipo_producto);
                    }
                } else if (n >= 500 && n < 600) {
                    //lacteos
                    resultSet = connectionToDB
                            .getStatement()
                            .executeQuery("SELECT * FROM lacteos WHERE codigo_lacteo = " + n);

                    while (resultSet.next()) {
                        producto = resultSet.getString("nombre_lacteo");
                        precio = resultSet.getDouble("precio");
                        codigo += Integer.toString(resultSet.getInt("codigo_lacteo"));
                        queryInformation.add(producto);
                        queryInformation.add(precio);
                    }
                } else if (n >= 600 && n < 650) {
                    //bebidas tipo soda
                    resultSet = connectionToDB
                            .getStatement()
                            .executeQuery("SELECT * FROM bebida_soda WHERE codigo_bebida = " + n);

                    while (resultSet.next()) {
                        producto = resultSet.getString("nombre_bebida");
                        precio = resultSet.getDouble("precio");
                        codigo += Integer.toString(resultSet.getInt("codigo_bebida"));
                        queryInformation.add(producto);
                        queryInformation.add(precio);
                    }
                } else if (n >= 650 && n < 675) {
                    //bebidas tipo energetica
                    resultSet = connectionToDB
                            .getStatement()
                            .executeQuery("SELECT * FROM bebida_energetica WHERE codigo_bebida = " + n);

                    while (resultSet.next()) {
                        producto = resultSet.getString("nombre_bebida");
                        precio = resultSet.getDouble("precio");
                        codigo += Integer.toString(resultSet.getInt("codigo_bebida"));
                        queryInformation.add(producto);
                        queryInformation.add(precio);
                    }
                } else if (n >= 675 && n < 700) {
                    //bebidas tipo alcoholicas
                    resultSet = connectionToDB
                            .getStatement()
                            .executeQuery("SELECT * FROM bebida_alcoholica WHERE codigo_bebida = " + n);

                    while (resultSet.next()) {
                        producto = resultSet.getString("nombre_bebida");
                        precio = resultSet.getDouble("precio");
                        codigo += Integer.toString(resultSet.getInt("codigo_bebida"));
                        queryInformation.add(producto);
                        queryInformation.add(precio);
                    }
                } else if (n >= 700 && n < 800) {
                    //galletas
                    resultSet = connectionToDB
                            .getStatement()
                            .executeQuery("SELECT * FROM galletas WHERE codigo_galletas = " + n);

                    while (resultSet.next()) {
                        producto = resultSet.getString("nombre_galletas");
                        precio = resultSet.getDouble("precio");
                        codigo += Integer.toString(resultSet.getInt("codigo_galletas"));
                        queryInformation.add(producto);
                        queryInformation.add(precio);
                    }
                } else {
                    /**
                     * pendiente...
                     * pienzo en disparar custom exceptions
                     * cuando se encuentren series de numeros
                     * que estan fuera do los rangos
                     */
                    JOptionPane
                            .showMessageDialog(
                                    null,
                                    "Errores en el codigo del producto" + n,
                                    "Error",JOptionPane.ERROR_MESSAGE
                            );
                    break;
                }

            }
            queryInformation.add(Integer.parseInt(codigo));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return queryInformation;
    }

    /**
     * otro pedo para el final
     * @param ventas
     * @return
     */
    public static String[] toArray(List<String>ventas) {
        String[] arr = new String[ventas.size()];
        for (int index = 0; index < ventas.size(); index++) {
            arr[index] = ventas.get(index);
        }
        return arr;
    }

}
