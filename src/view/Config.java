/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Collection;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import jmodbus.JModbus;
import jmodbus.PortConfiguration;
import jmodbus.SerialJModbus;

/**
 *
 * @author LUCAS
 */
public class Config extends javax.swing.JFrame {

    Thread thread;

    /**
     * Creates new form Config
     */
    public Config() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        textDireccion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        textID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        comboReintentos = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        comboTimeout = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        comboRate = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        comboPuerto = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txAreaTraffic = new javax.swing.JTextArea();
        btnStart = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnSend = new javax.swing.JButton();
        cbxOpcionVisualizacion = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaValores = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        comboFuncion = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        nroValores = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel16.setText("Configuracion");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Esclavo"));

        textDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDireccionActionPerformed(evt);
            }
        });

        jLabel7.setText("Direccion Inicial:");

        jLabel6.setText("ID:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(88, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Maestro"));

        comboReintentos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8" }));

        jLabel5.setText("N° Reintentos:");

        comboTimeout.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "500", "1000", "1500", "2000" }));
        comboTimeout.setSelectedIndex(1);

        jLabel4.setText("Timeout (ms):");

        comboRate.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1200", "2400", "4800", "9600", "14400", "19200" }));
        comboRate.setSelectedIndex(3);
        comboRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboRateActionPerformed(evt);
            }
        });

        jLabel2.setText("Baud Rate:");

        comboPuerto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboPuerto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboPuertoActionPerformed(evt);
            }
        });

        jLabel1.setText("Puertos:");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Serial");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("TCP");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel3.setText("IP:");

        jLabel15.setText("PUERTO:");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2)
                                    .addComponent(comboPuerto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboRate, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jRadioButton1)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton2)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(comboTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboReintentos, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboPuerto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboTimeout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboReintentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 209, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Maestro", jPanel1);

        jLabel10.setText("Tráfico");

        txAreaTraffic.setColumns(20);
        txAreaTraffic.setRows(5);
        jScrollPane1.setViewportView(txAreaTraffic);

        btnStart.setText("Start");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnStop.setText("Stop");
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnSend.setText("Enviar petición");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        cbxOpcionVisualizacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Decimal", "Hexadecimal", "Binario" }));

        jLabel12.setText("Acciones");

        jLabel13.setText("Opción visualización");

        tablaValores.setModel(crearTabla(1));
        jScrollPane4.setViewportView(tablaValores);

        jLabel14.setText("Variables");

        comboFuncion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "3", "6", "16" }));
        comboFuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFuncionActionPerformed(evt);
            }
        });

        jLabel9.setText("Funcion:");

        jLabel8.setText("N° Variables:");

        nroValores.setText("1");
        nroValores.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nroValoresKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnStart)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnStop))
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                            .addComponent(cbxOpcionVisualizacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(comboFuncion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(nroValores))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStart)
                    .addComponent(btnStop)
                    .addComponent(btnSend)
                    .addComponent(cbxOpcionVisualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(comboFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nroValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Comunicación", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed

        boolean valorCorrecto = true;
        int[] valores = new int[tablaValores.getRowCount()];
        int nroVariables;
        try {
            nroVariables = Integer.valueOf(nroValores.getText());
        } catch (NumberFormatException e) {
            nroVariables = 1;
        }
        
        this.modbus = new SerialJModbus(comboPuerto.getSelectedItem().toString(),
                Integer.valueOf(comboRate.getSelectedItem().toString()),
                Integer.valueOf(comboTimeout.getSelectedItem().toString()),
                Integer.valueOf(comboReintentos.getSelectedItem().toString()), Integer.parseInt(textID.getText()),
                Integer.valueOf(textDireccion.getText()), nroVariables,
                Integer.valueOf(comboFuncion.getSelectedItem().toString()));

        for (int i = 0; i < tablaValores.getRowCount(); i++) {
            try {
                int valor = Integer.valueOf((String) tablaValores.getValueAt(i, 1));
                if (valor > 65535) {
                    valorCorrecto = false;
                    break;
                }
                valores[i] = valor;
            } catch (ClassCastException e) {
                valores[i] = 0;
            }
        }

        if (valorCorrecto) {
            String tramaRecibida = modbus.execute(valores, cbxOpcionVisualizacion.getSelectedIndex(), tablaValores);
            txAreaTraffic.setText(tramaRecibida + "\n" + txAreaTraffic.getText());
        } else {
            txAreaTraffic.setText("El valor ingresado excede el limite del registro. " + "\n");
        }

        this.modbus.close();
    }//GEN-LAST:event_btnSendActionPerformed

    private void comboFuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFuncionActionPerformed
        // TODO add your handling code here:
        if (comboFuncion.getSelectedItem().toString().equals("3")) {
            tablaValores.setEnabled(false);
        } else if (comboFuncion.getSelectedItem().toString().equals("6")) {
            nroValores.setText("1");
            tablaValores.setEnabled(true);
        } else {
            tablaValores.setEnabled(true);
        }
    }//GEN-LAST:event_comboFuncionActionPerformed

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStartActionPerformed
        if (this.modbus != null) {
            this.modbus.close();
        }
        boolean valorCorrecto = true;
        final int[] valores = new int[tablaValores.getRowCount()];
        //this.modbus = new Modbus("COM2", 9600, 30, 3, "1", 0, 10, 3);
        int nroVariables;
        try {
            nroVariables = Integer.valueOf(nroValores.getText());
        } catch (NumberFormatException e) {
            nroVariables = 1;
        }
        this.modbus = new SerialJModbus(comboPuerto.getSelectedItem().toString(),
                Integer.valueOf(comboRate.getSelectedItem().toString()),
                Integer.valueOf(comboTimeout.getSelectedItem().toString()),
                Integer.valueOf(comboReintentos.getSelectedItem().toString()), 
                Integer.parseInt(textID.getText()),
                Integer.valueOf(textDireccion.getText()), nroVariables,
                Integer.valueOf(comboFuncion.getSelectedItem().toString()));

        for (int i = 0; i < tablaValores.getRowCount(); i++) {
            try {
                int valor = Integer.valueOf((String) tablaValores.getValueAt(i, 1));
                if (valor > 65535) {
                    valorCorrecto = false;
                    break;
                }
                valores[i] = valor;
            } catch (ClassCastException e) {
                valores[i] = 0;
            }
        }
        if (valorCorrecto) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    long endTimeMillis = System.currentTimeMillis() + 1500;
                    while (true) {
                        if (System.currentTimeMillis() > endTimeMillis) {
                            //String tramaRecibida = modbus.execute(null);
                            String tramaRecibida = modbus.execute(valores, cbxOpcionVisualizacion.getSelectedIndex(), tablaValores);
                            txAreaTraffic.setText(tramaRecibida + "\n" + txAreaTraffic.getText());
                            endTimeMillis = System.currentTimeMillis() + 1500;
                        }
                    }
                }
            });
            thread.start();
        } else {
            txAreaTraffic.setText("El valor ingresado excede el limite del registro. " + "\n");
        }
    }//GEN-LAST:event_btnStartActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        if (this.thread != null) {
            this.thread.stop();
            if (this.modbus != null) {
                this.modbus.close();
            }
        }
    }//GEN-LAST:event_btnStopActionPerformed

    private void nroValoresKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nroValoresKeyReleased
        // TODO add your handling code here:
        int nfilas;
        try {
            nfilas = Integer.valueOf(nroValores.getText());
        } catch (NumberFormatException e) {
            nfilas = 0;
        }
        tablaValores.setModel(crearTabla(nfilas));
    }//GEN-LAST:event_nroValoresKeyReleased

    private void comboRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboRateActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        comboPuerto.setEnabled(false);
        comboRate.setEnabled(false);
        comboReintentos.setEnabled(false);
        comboTimeout.setEnabled(false);
        jTextField1.setEnabled(true);
        jTextField2.setEnabled(true);
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        comboPuerto.setEnabled(true);
        comboRate.setEnabled(true);
        comboReintentos.setEnabled(true);
        comboTimeout.setEnabled(true);
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void comboPuertoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboPuertoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboPuertoActionPerformed

    private void textDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDireccionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textDireccionActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Config.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Config().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnStart;
    private javax.swing.JButton btnStop;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbxOpcionVisualizacion;
    private javax.swing.JComboBox comboFuncion;
    private javax.swing.JComboBox comboPuerto;
    private javax.swing.JComboBox comboRate;
    private javax.swing.JComboBox comboReintentos;
    private javax.swing.JComboBox comboTimeout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField nroValores;
    private javax.swing.JTable tablaValores;
    private javax.swing.JTextField textDireccion;
    private javax.swing.JTextField textID;
    private javax.swing.JTextArea txAreaTraffic;
    // End of variables declaration//GEN-END:variables

    private JModbus modbus;

    private void init() {
        PortConfiguration portConfiguration = new PortConfiguration();
        Collection ports = portConfiguration.buscarDispositivos();
        this.comboPuerto.removeAllItems();
        for (Object port : ports) {
            this.comboPuerto.addItem(port);
        }
    }

    private DefaultTableModel crearTabla(Integer valueOf) {
        DefaultTableModel defaultTableModel = new DefaultTableModel();
        int nFilas;
        try {
            if(this.textDireccion.getText().equals("")) this.textDireccion.setText("0");
            nFilas = Integer.valueOf(nroValores.getText()) + Integer.valueOf(this.textDireccion.getText());
        } catch (NumberFormatException e) {
            nFilas = 0;
        }
        defaultTableModel.addColumn("Alias");
        defaultTableModel.addColumn("Valor");
        for (int i = 0; i < nFilas; i++) {
            //fila[0] = 
            defaultTableModel.addRow(new Object[]{i, 0});
        }
        return defaultTableModel;
    }
}
