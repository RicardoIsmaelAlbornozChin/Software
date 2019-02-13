
package metodonumerico;
//declararion de librerias
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class interfaz extends JFrame implements ActionListener{
    operaciones operations = new operaciones();
    //declaracion de componenetes.
    JTextField txtDateTrue;
    JTextField txtDateAprox;
    JButton btnAccept;
    JComboBox cmdTypeView; //truncamiento o redondeo
    JLabel lblAnswerER; //respuesta de error relativo
    JLabel lblAnswerEA; //respuesta del error absoluto
    JLabel lblAnswerERP; //respuesta del error relativo porcentual.
    
    public interfaz(){
        super("Metodos Numericos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false); //no se redimensionara el JFRAME
        this.setLocationRelativeTo(null); //centrar 
        
        //inicializacion de componentes graficos
        txtDateTrue = new JTextField(5);
        txtDateAprox = new JTextField(5);
        btnAccept = new JButton("Aceptar");
        
        cmdTypeView = new JComboBox();
        cmdTypeView.addItem("Por Defecto");
        cmdTypeView.addItem("Truncamiento");
        cmdTypeView.addItem("Redondedo");
        
        lblAnswerEA = new JLabel("0"); 
        lblAnswerEA.setForeground(Color.RED);
        lblAnswerEA.setFont(new Font("Arial", Font.PLAIN, 17));
        
        lblAnswerER = new JLabel("0");
        lblAnswerER.setForeground(Color.BLUE);
        lblAnswerER.setFont(new Font("Arial", Font.PLAIN, 17));
        
        lblAnswerERP = new JLabel("0%");
        lblAnswerERP.setForeground(Color.DARK_GRAY);
        lblAnswerERP.setFont(new Font("Arial", Font.PLAIN, 17));
        
        //inicializamos el contenedor
        Container container = getContentPane();
        
        //panel de accion.
        JPanel panelDates = new JPanel();
        panelDates.setLayout(new GridLayout(4,2,0,10));
        panelDates.add(new JLabel("Valor real: "));
        panelDates.add(txtDateTrue);
        panelDates.add(new JLabel("Valor Aproximado: "));
        panelDates.add(txtDateAprox);
        panelDates.add(new JLabel("Mostrar Como: "));
        panelDates.add(cmdTypeView);
        
        JPanel panelOptions = new JPanel();
        panelOptions.setLayout(new GridLayout(8,1,0,10));
        panelOptions.add(btnAccept);
        lblAnswerER.setHorizontalAlignment(lblAnswerER.CENTER); //centra el JLabel
        lblAnswerEA.setHorizontalAlignment(lblAnswerEA.CENTER); //centra el Jlabel
        lblAnswerERP.setHorizontalAlignment(lblAnswerERP.CENTER); //centrar el JLabel
        
        panelOptions.add(new JLabel("Error Absoluto"));
        panelOptions.add(lblAnswerEA);
        panelOptions.add(new JLabel("Error Relativo"));
        panelOptions.add(lblAnswerER);
        panelOptions.add(new JLabel("Error Relativo Porcentual"));
        panelOptions.add(lblAnswerERP);
        
        //asociamos el administrador de eventos al JFrame click
        btnAccept.addActionListener(this); 
        
        setVisible(true);
        
        /*MOSTRAMOS EL CONTENIDO*/
        container.add(panelDates, BorderLayout.NORTH);
        container.add(panelOptions, BorderLayout.CENTER);

        setVisible(true);
        setSize(400,420);
    }
    
    @Override
      public void actionPerformed(ActionEvent e){
      
          if(e.getSource() == btnAccept){
            double[] resultsOperations = new double[3]; 
              
            int dateViewIndex = cmdTypeView.getSelectedIndex(); //posicion del elemento de la Vista
            
            if(txtDateAprox.getText().length()==0 && txtDateTrue.getText().length()==0){ //detectar errores de txt vacios
               JOptionPane.showMessageDialog(null, "Llene los campos", "Advertencia", JOptionPane.ERROR_MESSAGE);
            }else{
                double dateTrue = Double.parseDouble(txtDateTrue.getText());
                double dateAprox = Double.parseDouble(txtDateAprox.getText());
                
                resultsOperations =  operations.operationSelection(dateTrue, dateAprox, dateViewIndex);
                lblAnswerEA.setText(Double.toString(resultsOperations[0]));
                lblAnswerER.setText(Double.toString(resultsOperations[1]));
                lblAnswerERP.setText(Double.toString(resultsOperations[2]) + "%");
            }
        }
    }
}
