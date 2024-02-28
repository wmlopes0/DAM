package InterfazGrafica;

import LogicaJuego.Carta;
import LogicaJuego.Constantes;
import LogicaJuego.Juego;
import LogicaJuego.Jugador;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

/**
 *
 * @author Walter
 */
public class VentanaJuego extends javax.swing.JDialog {

    //Variables globales
    private VentanaInicio ventanaPrincipal;
    private Juego juego;
    private JLabel ventanaJuego;//Label que contiene todo, es el que uso para poner el fondo.(FONDO DONADO POR ROSA :))

    //Constructor
    public VentanaJuego(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.ventanaPrincipal = (VentanaInicio) parent;
        this.juego = ventanaPrincipal.juego;
        //Pintar tablero
        pintarTablero();
        //Comunico las ventanas
        juego.comunicarVentanaJuego(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelManoUsuario = new javax.swing.JPanel();
        jPanelCartasVisiblesUsuario = new javax.swing.JPanel();
        jPanelCartasVisiblesCpu1 = new javax.swing.JPanel();
        jPanelCartasVisiblesCpu2 = new javax.swing.JPanel();
        jLabelNombreUsuario = new javax.swing.JLabel();
        jLabelNombreCpu1 = new javax.swing.JLabel();
        jLabelNombreCpu2 = new javax.swing.JLabel();
        jLabelTuMano = new javax.swing.JLabel();
        jLabelRonda = new javax.swing.JLabel();
        jLabelTeToca = new javax.swing.JLabel();
        jLabelPuntuaciones = new javax.swing.JLabel();
        jLabelPuntuacionUsuario = new javax.swing.JLabel();
        jLabelPuntuacionCpu1 = new javax.swing.JLabel();
        jLabelPuntuacionCpu2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabelNombreUsuario.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14)); // NOI18N
        jLabelNombreUsuario.setText("NombreUsuario");

        jLabelNombreCpu1.setText("CPU 1");

        jLabelNombreCpu2.setText("CPU 2");

        jLabelTuMano.setText("TU MANO");

        jLabelRonda.setText("RONDA");

        jLabelPuntuaciones.setText("Puntuaciones");

        jLabelPuntuacionUsuario.setText("P usuario");

        jLabelPuntuacionCpu1.setText("P cpu1");

        jLabelPuntuacionCpu2.setText("P cpu2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabelPuntuacionCpu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabelPuntuacionCpu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(72, 72, 72)
                                        .addComponent(jPanelCartasVisiblesCpu2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelPuntuaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jPanelCartasVisiblesCpu1, javax.swing.GroupLayout.PREFERRED_SIZE, 423, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelNombreCpu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelNombreCpu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(39, 39, 39)
                                    .addComponent(jPanelManoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelRonda, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabelTuMano, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGap(52, 52, 52)))
                                    .addComponent(jPanelCartasVisiblesUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabelNombreUsuario))
                        .addContainerGap(216, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPuntuacionUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelTeToca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNombreUsuario)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelManoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelNombreCpu1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNombreCpu2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCartasVisiblesUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelTuMano)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelRonda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCartasVisiblesCpu1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPuntuaciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTeToca, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPuntuacionUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelCartasVisiblesCpu2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(229, 229, 229))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelPuntuacionCpu1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelPuntuacionCpu2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(217, 217, 217))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Método que contiene las llamadas necesarias para pintar el tablero 
    private void pintarTablero() {
        establecerFondo();//Esteblezco el fondo
        crearPaneles();//Creo y coloco los paneles necesarios donde irán las cartas
        pintarLabels();//Creo y coloco los labels
        actualizarCartas();//Actualizo las cartas en la pantalla
    }

    //Método que establece el fondo de la pantalla
    private void establecerFondo() {
        // Cargar la imagen de fondo
        ImageIcon icon = new ImageIcon("src/IMG/mesa.png");
        // Crear un JLabel con la imagen de fondo
        JLabel labelConFondo = new JLabel(icon);
        this.ventanaJuego = labelConFondo;
        labelConFondo.setLayout(null);
        // Establecer el JLabel como el contenido del JDialog
        this.setContentPane(labelConFondo);
        this.pack();//Fuerzo que la ventana se redimensione para adaptarse al tamaño de la imagen de fondo.
    }

    //Método que se encarga de configurar y colocar los jPanel necesarios
    private void crearPaneles() {
        //Mano usuario
        jPanelManoUsuario.setBounds(125, 32, Constantes.ANCHO_PANEL, Constantes.ALTO_PANEL);//Establezco dimensiones
        jPanelManoUsuario.setBackground(new Color(0, 0, 0, 0));//Establezco color transparente
        jPanelManoUsuario.setOpaque(false); //Informo a Swing de que el panel debe ser transparente
        ventanaJuego.add(jPanelManoUsuario); //Añado el panel a ventanaJuego
        //Cartas visibles usuario
        jPanelCartasVisiblesUsuario.setBounds(125, 192, Constantes.ANCHO_PANEL, Constantes.ALTO_PANEL);
        jPanelCartasVisiblesUsuario.setBackground(new Color(0, 0, 0, 0));
        jPanelCartasVisiblesUsuario.setOpaque(false);
        ventanaJuego.add(jPanelCartasVisiblesUsuario);
        //Cartas visibles Cpu1
        jPanelCartasVisiblesCpu1.setBounds(125, 368, Constantes.ANCHO_PANEL, Constantes.ALTO_PANEL);
        jPanelCartasVisiblesCpu1.setBackground(new Color(0, 0, 0, 0));
        jPanelCartasVisiblesCpu1.setOpaque(false);
        ventanaJuego.add(jPanelCartasVisiblesCpu1);
        //Cartas visibles Cpu2
        jPanelCartasVisiblesCpu2.setBounds(125, 547, Constantes.ANCHO_PANEL, Constantes.ALTO_PANEL);
        jPanelCartasVisiblesCpu2.setBackground(new Color(0, 0, 0, 0));
        jPanelCartasVisiblesCpu2.setOpaque(false);
        ventanaJuego.add(jPanelCartasVisiblesCpu2);
    }

    //Método que se encarga de configurar y colocar los JLabel necesarios
    private void pintarLabels() {
        //TU MANO
        jLabelTuMano.setText("Tu mano");
        jLabelTuMano.setBounds(2, 65, jLabelTuMano.getWidth(), jLabelTuMano.getHeight());
        jLabelTuMano.setVerticalAlignment(SwingConstants.CENTER);
        jLabelTuMano.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelTuMano.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
        jLabelTuMano.setForeground(new Color(122, 53, 18));
        jLabelTuMano.setBackground(new Color(255, 255, 255, 150));
        jLabelTuMano.setOpaque(true);
        ventanaJuego.add(jLabelTuMano);
        //NOMBRE USUARIO
        jLabelNombreUsuario.setText(ventanaPrincipal.obtenerNombreUsuario());
        jLabelNombreUsuario.setBounds(2, 225, jLabelNombreUsuario.getWidth(), jLabelNombreUsuario.getHeight());
        jLabelNombreUsuario.setVerticalAlignment(SwingConstants.CENTER);
        jLabelNombreUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelNombreUsuario.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
        jLabelNombreUsuario.setForeground(new Color(122, 53, 18));
        jLabelNombreUsuario.setBackground(new Color(255, 255, 255, 150));
        jLabelNombreUsuario.setOpaque(true);
        ventanaJuego.add(jLabelNombreUsuario);
        //NOMBRE CPU1
        jLabelNombreCpu1.setText(juego.cpu1.getNombre());
        jLabelNombreCpu1.setBounds(2, 401, jLabelNombreCpu1.getWidth(), jLabelNombreCpu1.getHeight());
        jLabelNombreCpu1.setVerticalAlignment(SwingConstants.CENTER);
        jLabelNombreCpu1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelNombreCpu1.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
        jLabelNombreCpu1.setForeground(new Color(122, 53, 18));
        jLabelNombreCpu1.setBackground(new Color(255, 255, 255, 150));
        jLabelNombreCpu1.setOpaque(true);
        ventanaJuego.add(jLabelNombreCpu1);
        //NOMBRE CPU2
        jLabelNombreCpu2.setText(juego.cpu2.getNombre());
        jLabelNombreCpu2.setBounds(2, 580, jLabelNombreCpu2.getWidth(), jLabelNombreCpu2.getHeight());
        jLabelNombreCpu2.setVerticalAlignment(SwingConstants.CENTER);
        jLabelNombreCpu2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelNombreCpu2.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 14));
        jLabelNombreCpu2.setForeground(new Color(122, 53, 18));
        jLabelNombreCpu2.setBackground(new Color(255, 255, 255, 150));
        jLabelNombreCpu2.setOpaque(true);
        ventanaJuego.add(jLabelNombreCpu2);
        //RONDA
        jLabelRonda.setName("jLabelRonda");
        jLabelRonda.setText("RONDA " + juego.getRonda() + "/3");
        jLabelRonda.setBounds(830, 65, jLabelRonda.getWidth(), jLabelRonda.getHeight());
        jLabelRonda.setVerticalAlignment(SwingConstants.CENTER);
        jLabelRonda.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelRonda.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 20));
        jLabelRonda.setForeground(new Color(122, 53, 18));
        jLabelRonda.setBackground(new Color(255, 255, 255, 150));
        jLabelRonda.setOpaque(true);
        ventanaJuego.add(jLabelRonda);
        //TE TOCA
        ImageIcon imagen = new ImageIcon("src/IMG/tetoca.png");
        jLabelTeToca.setBounds(0, 0, 1030, 25);
        jLabelTeToca.setVerticalAlignment(SwingConstants.CENTER);
        jLabelTeToca.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelTeToca.setIcon(imagen);
        jLabelTeToca.setOpaque(true);
        ventanaJuego.add(jLabelTeToca);
        //PUNTUACIONES
        jLabelPuntuaciones.setText("PUNTUACIONES");
        jLabelPuntuaciones.setBounds(820, 185, jLabelPuntuaciones.getWidth(), jLabelPuntuaciones.getHeight());
        jLabelPuntuaciones.setVerticalAlignment(SwingConstants.CENTER);
        jLabelPuntuaciones.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelPuntuaciones.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 20));
        jLabelPuntuaciones.setForeground(new Color(122, 53, 18));
        jLabelPuntuaciones.setBackground(new Color(255, 255, 255, 150));
        jLabelPuntuaciones.setOpaque(true);
        ventanaJuego.add(jLabelPuntuaciones);
        //PUNTUACION USUARIO
        jLabelPuntuacionUsuario.setName("jLabelPuntuacionUsuario");
        jLabelPuntuacionUsuario.setText(jLabelNombreUsuario.getText() + "  " + juego.usuario.getPuntuacion());
        jLabelPuntuacionUsuario.setBounds(820, 227, jLabelPuntuacionUsuario.getWidth(), jLabelPuntuacionUsuario.getHeight());
        jLabelPuntuacionUsuario.setVerticalAlignment(SwingConstants.CENTER);
        jLabelPuntuacionUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelPuntuacionUsuario.setFont(new java.awt.Font("Microsoft YaHei UI", 2, 18));
        jLabelPuntuacionUsuario.setForeground(new Color(122, 53, 18));
        jLabelPuntuacionUsuario.setBackground(new Color(255, 255, 255, 150));
        jLabelPuntuacionUsuario.setOpaque(true);
        ventanaJuego.add(jLabelPuntuacionUsuario);
        //PUNTUACION CPU1
        jLabelPuntuacionCpu1.setName("jLabelPuntuacionCpu1");
        jLabelPuntuacionCpu1.setText(jLabelNombreCpu1.getText() + "  " + juego.cpu1.getPuntuacion());
        jLabelPuntuacionCpu1.setBounds(820, 257, jLabelPuntuacionCpu1.getWidth(), jLabelPuntuacionCpu1.getHeight());
        jLabelPuntuacionCpu1.setVerticalAlignment(SwingConstants.CENTER);
        jLabelPuntuacionCpu1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelPuntuacionCpu1.setFont(new java.awt.Font("Microsoft YaHei UI", 2, 18));
        jLabelPuntuacionCpu1.setForeground(new Color(122, 53, 18));
        jLabelPuntuacionCpu1.setBackground(new Color(255, 255, 255, 150));
        jLabelPuntuacionCpu1.setOpaque(true);
        ventanaJuego.add(jLabelPuntuacionCpu1);
        //PUNTUACION CPU2
        jLabelPuntuacionCpu2.setName("jLabelPuntuacionCpu2");
        jLabelPuntuacionCpu2.setText(jLabelNombreCpu2.getText() + "  " + juego.cpu2.getPuntuacion());
        jLabelPuntuacionCpu2.setBounds(820, 287, jLabelPuntuacionCpu2.getWidth(), jLabelPuntuacionCpu2.getHeight());
        jLabelPuntuacionCpu2.setVerticalAlignment(SwingConstants.CENTER);
        jLabelPuntuacionCpu2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabelPuntuacionCpu2.setFont(new java.awt.Font("Microsoft YaHei UI", 2, 18));
        jLabelPuntuacionCpu2.setForeground(new Color(122, 53, 18));
        jLabelPuntuacionCpu2.setBackground(new Color(255, 255, 255, 150));
        jLabelPuntuacionCpu2.setOpaque(true);
        ventanaJuego.add(jLabelPuntuacionCpu2);
    }

    //Este método actualiza las cartas en la pantalla
    public void actualizarCartas() {
        //Elimino todos los componentes existentes en los paneles y actualizo
        borrarComponentesActualizar();
        //Recorro los jugadores
        juego.getJugadores().forEach(jugador -> {
            //Si el jugador es USER
            if (!jugador.esCPU()) {
                //Me recorro sus cartas visibles, creando un botón para cada una y añadiendo su representacion a su respectivo JPanel
                for (int i = 0; i < jugador.getCartasVisibles().size(); i++) {
                    Carta carta = jugador.getCartasVisibles().get(i);
                    JButton boton = new JButton(carta.getRepresentacion());
                    boton.setName(String.valueOf(i));
                    configurarEstilosBoton(boton);//Configuro los estilos del boton
                    generarActionListenerBoton(boton);//Genero un action listener para el boton
                    jPanelCartasVisiblesUsuario.add(boton);//Añado el boton al panel correspondiente
                    jPanelCartasVisiblesUsuario.revalidate();//Refresco
                    jPanelCartasVisiblesUsuario.repaint();//Refresco
                }
                //Me recorro su mano de cartas añadiendo su representacion a su respectivo JPanel
                jugador.getMano().forEach(carta -> {
                    JLabel jLabel = new JLabel(carta.getRepresentacion());
                    jPanelManoUsuario.add(jLabel);//Añado el label al panel correspondiente
                    jPanelManoUsuario.revalidate();//Refresco
                    jPanelManoUsuario.repaint();//Refresco
                });
            } else {
                //Si es un CPU creo un label con la representacion de la carta y lo añado a su respectivo Jpanel
                jugador.getMano().forEach(carta -> {
                    JLabel jLabel = new JLabel(carta.getRepresentacion());
                    if (jugador.getNombre().equalsIgnoreCase(Constantes.CPU1)) {
                        jPanelCartasVisiblesCpu1.add(jLabel);//Añado el label al panel correspondiente
                        jPanelCartasVisiblesCpu1.revalidate();//Refresco
                        jPanelCartasVisiblesCpu1.repaint();//Refresco
                    } else {
                        jPanelCartasVisiblesCpu2.add(jLabel);//Añado el label al panel correspondiente
                        jPanelCartasVisiblesCpu2.revalidate();//Refresco
                        jPanelCartasVisiblesCpu2.repaint();//Refresco
                    }
                });
            }
        });
    }

    //Método auxiliar que vacia los componentes de los paneles, actualiza y obliga a refrescar. Es usado en el actualizarCartas()
    public void borrarComponentesActualizar() {
        jPanelCartasVisiblesCpu1.removeAll();
        jPanelCartasVisiblesCpu1.revalidate();
        jPanelCartasVisiblesCpu1.repaint();
        jPanelCartasVisiblesCpu2.removeAll();
        jPanelCartasVisiblesCpu2.revalidate();
        jPanelCartasVisiblesCpu2.repaint();
        jPanelCartasVisiblesUsuario.removeAll();
        jPanelCartasVisiblesUsuario.revalidate();
        jPanelCartasVisiblesUsuario.repaint();
        jPanelManoUsuario.removeAll();
        jPanelManoUsuario.revalidate();
        jPanelManoUsuario.repaint();
    }

    //Método que actualiza la información necesaria en tiempo de ejecución, el número de ronda, la puntuación...
    public void actualizarInfo() {
        jLabelRonda.setText("RONDA " + juego.getRonda() + "/3");
        jLabelPuntuacionUsuario.setText(jLabelNombreUsuario.getText() + "  " + juego.usuario.getPuntuacion());
        jLabelPuntuacionCpu1.setText(jLabelNombreCpu1.getText() + "  " + juego.cpu1.getPuntuacion());
        jLabelPuntuacionCpu2.setText(jLabelNombreCpu2.getText() + "  " + juego.cpu2.getPuntuacion());
        ventanaJuego.revalidate();
        ventanaJuego.repaint();
    }

    //Método auxiliar que configura los estilos del boton carta
    private void configurarEstilosBoton(JButton boton) {
        // Ajustar el tamaño del botón al tamaño de la imagen
        boton.setPreferredSize(new Dimension(Constantes.ANCHO_CARTA, Constantes.ALTO_CARTA));
        // Configurar el botón para que solo muestre la imagen
        boton.setBorderPainted(false);      // Quitar el borde
        boton.setFocusPainted(false);       // Quitar el efecto de enfoque
        boton.setContentAreaFilled(false);  // Quitar el fondo del botón
        boton.setMargin(new Insets(0, 0, 0, 0)); // Ajustar márgenes
    }

    //Método que recibe un JButton (Carta) por parámetro y le añade el ActionListener
    private void generarActionListenerBoton(JButton boton) {
        //Genero un actionListener para el botón
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton botonUsado = (JButton) e.getSource(); // Obtengo el botón que disparó el evento
                int cartaSeleccionada = Integer.valueOf(botonUsado.getName());//Obtengo el texto del boton (Indice de la carta) y lo convierto en Integer
                juego.jugarTurnoUsuario(cartaSeleccionada);
            }
        });
    }

    //Método auxiliar que recibe un ImageIcon, lo redimensiona y lo retorna
    private ImageIcon redimensionarImagen(ImageIcon imageIcon) {
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(Constantes.ANCHO_ICONO, Constantes.ALTO_ICONO, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImg);
    }

    //Método que finaliza el juego, comprueba el ganador, y muestra un jOptionPanel con el resultado.
    public void finalizarJuego() {
        Jugador ganador = juego.comprobarGanador();
        actualizarInfo();//Actualizo info
        if (ganador.esCPU()) {
            //VENTANA PERDER
            ImageIcon iconoPerder = new ImageIcon("src/IMG/perder.png");
            iconoPerder = redimensionarImagen(iconoPerder);
            JOptionPane.showMessageDialog(null, "¡LO SIENTO!  " + ganador.getNombre() + "  te ha ganado con " + ganador.getPuntuacion() + " puntos...", "GAME OVER", JOptionPane.INFORMATION_MESSAGE, iconoPerder);
        } else {
            //VENTANA GANAR
            ImageIcon iconoGanar = new ImageIcon("src/IMG/ganar.png");
            iconoGanar = redimensionarImagen(iconoGanar);
            JOptionPane.showMessageDialog(null, "¡ENHORABUENA! has ganado con " + ganador.getPuntuacion() + " puntos!", "WIN", JOptionPane.INFORMATION_MESSAGE, iconoGanar);
        }
        dispose();//Cierro VentanaJuego
    }

    //===========================================================================
    //Main
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
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentanaJuego dialog = new VentanaJuego(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelNombreCpu1;
    private javax.swing.JLabel jLabelNombreCpu2;
    private javax.swing.JLabel jLabelNombreUsuario;
    private javax.swing.JLabel jLabelPuntuacionCpu1;
    private javax.swing.JLabel jLabelPuntuacionCpu2;
    private javax.swing.JLabel jLabelPuntuacionUsuario;
    private javax.swing.JLabel jLabelPuntuaciones;
    private javax.swing.JLabel jLabelRonda;
    private javax.swing.JLabel jLabelTeToca;
    private javax.swing.JLabel jLabelTuMano;
    private javax.swing.JPanel jPanelCartasVisiblesCpu1;
    private javax.swing.JPanel jPanelCartasVisiblesCpu2;
    private javax.swing.JPanel jPanelCartasVisiblesUsuario;
    private javax.swing.JPanel jPanelManoUsuario;
    // End of variables declaration//GEN-END:variables
}
