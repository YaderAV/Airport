/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package airport.views;

import airport.Models.Entities.Flights.Flight;
import airport.Models.Entities.Location;
import airport.Models.Entities.Passenger;
import airport.Models.Entities.Plane;
import airport.Models.Observable.DataObserver;
import airport.Models.Observable.FlightRepository;
import airport.Models.Observable.LocationRepository;
import airport.Models.Observable.PassengerRepository;
import airport.Models.Observable.PlaneRepository;

import airport.controllers.utils.parser.Parsers;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edangulo
 */
public class AirportFrame extends javax.swing.JFrame implements DataObserver {

    /**
     * Creates new form AirportFrame
     */
    private int x, y;
    private ArrayList<Passenger> passengers;
    private ArrayList<Plane> planes;
    private ArrayList<Location> locations;
    private ArrayList<Flight> flights;
    private PassengerRepository passengerRepository;
    private FlightRepository flightRepository;
    private LocationRepository locationRepository;
    private PlaneRepository planeRepository;

    public AirportFrame() {
        initComponents();
        views.addChangeListener(e -> {
            int selectedIndex = views.getSelectedIndex();
            String selectedTabTitle = views.getTitleAt(selectedIndex);

            if (selectedTabTitle.equals("Add to flight")) {
                cargarVuelosEnComboBox();
            }
        });

        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);
        this.generateMonths();
        this.generateDays();
        this.generateHours();
        this.generateMinutes();
        this.blockPanels();

    }

    public void setPassengerRepository(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public void setLocationRepository(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public void setPlaneRepository(PlaneRepository planeRepository) {
        this.planeRepository = planeRepository;
    }

    @Override
    public void onDataChanged() {
        updatePassengerTable();
        updateFlightTable();
        updateLocationTable();
        updatePlaneTable();
        cargarIDsEnComboBox();

    }

    

    private void cargarVuelosEnComboBox() {
        showFlightsButton.removeAllItems();
        showFlightsButton.addItem("Select Flight");  // Item por defecto

        for (Flight f : flightRepository.getAllFlights()) {
            String info = f.getId() + " (" + f.getDepartureLocation().getAirportID() + " ➔ " + f.getArrivalLocation().getAirportID() + ")";
            showFlightsButton.addItem(info);
        }

        System.out.println("DEBUG - Vuelos cargados: " + showFlightsButton.getItemCount());
    }

    private void updateMyFlightsTable(String passengerId) throws IOException, Exception {
        DefaultTableModel model = flightRepository.updateMyFlightsTable(passengerId);
        tableMyFlights.setModel(model);
    }

    private void onUpdateUser() {
        String selectedId = (String) userSelect.getSelectedItem();
        if (selectedId == null || selectedId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un usuario.");
            return;
        }

        String newFirstName = fieldFirstNameUpdate.getText();
        String newLastName = fieldLastNameUpdate.getText();
        String newCountry = fieldCountryUpdate.getText();
        String newPhoneCode = fieldPrefixUpdate.getText();
        String newPhone = fieldPhoneUpdate.getText();
        String newYear = fieldYearUpdate.getText();
        String newMonth = (String) monthUpdate.getSelectedItem();
        String newDay = (String) dayUpdate.getSelectedItem();

        passengerRepository.updatePassenger(
                selectedId, newFirstName, newLastName, newYear, newMonth, newDay, newPhoneCode, newPhone, newCountry
        );

        JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente.");
    }
    
    private void updatePlaneTable() {
        tablePlanes = planeRepository.getPassengerRowsR();
        viewAllPlanes.setViewportView(tablePlanes);
    }

    private void updateFlightTable() {
        tableAllFlights = passengerRepository.getPassengerRowsR();
        viewAllFlights.setViewportView(tablePassengers);
    }
    private void updateLocationTable() {
        tableLocations = locationRepository.getLocationRowsR();
        viewAllLocations.setViewportView(tableLocations);
    }

    private void updatePassengerTable() {
        tablePassengers = passengerRepository.getPassengerRowsR();
        viewPassengers.setViewportView(tablePassengers);
    }

    private void cargarIDsEnComboBox() {
        userSelect.removeAllItems();
        userSelect.addItem("Select User");  // Agregar el primer ítem por defecto (opcional)
        for (Passenger p : passengerRepository.getAllPassengers()) {
            userSelect.addItem(String.valueOf(p.getId()));
        }

    }

   

    private void blockPanels() {
        //9, 11
        for (int i = 1; i < views.getTabCount(); i++) {
            if (i != 9 && i != 11) {
                views.setEnabledAt(i, false);
            }
        }
    }

    private void generateMonths() {
        for (int i = 1; i < 13; i++) {
            monthPassengerR.addItem("" + i);
            monthUpdate.addItem("" + i);
            departureMonth.addItem("" + i);
        }
    }

    private void generateDays() {
        for (int i = 1; i < 32; i++) {
            dayPassengerR.addItem("" + i);
            dayUpdate.addItem("" + i);
            departureDay.addItem("" + i);
        }
    }

    private void generateHours() {
        for (int i = 0; i < 24; i++) {
            arrivalHour.addItem("" + i);
            scaleHour.addItem("" + i);
            departureHour.addItem("" + i);
            delayHour.addItem("" + i);
        }
    }

    private void generateMinutes() {
        for (int i = 0; i < 60; i++) {
            departureMinute.addItem("" + i);
            arrivalMinute.addItem("" + i);
            scaleMinute.addItem("" + i);
            delayMinute.addItem("" + i);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        container = new airport.views.PanelRound();
        topView = new airport.views.PanelRound();
        exit = new javax.swing.JButton();
        views = new javax.swing.JTabbedPane();
        administrationPanel = new javax.swing.JPanel();
        user = new javax.swing.JRadioButton();
        administrator = new javax.swing.JRadioButton();
        userSelect = new javax.swing.JComboBox<>();
        passengerRPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fieldPreFix = new javax.swing.JTextField();
        fieldIDpassenger = new javax.swing.JTextField();
        fieldYear = new javax.swing.JTextField();
        fieldCountry = new javax.swing.JTextField();
        fieldPhone = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        fieldLastName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        monthPassengerR = new javax.swing.JComboBox<>();
        fieldFirstName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        dayPassengerR = new javax.swing.JComboBox<>();
        btRegister = new javax.swing.JButton();
        airplaneRPlane = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        fieldIDairplane = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        fieldBrand = new javax.swing.JTextField();
        fieldModel = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        fieldMaxCapacity = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        fieldAirline = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        fieldCreateAirplane = new javax.swing.JButton();
        locationRPanel = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        fieldAirportID = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        fieldAirportName = new javax.swing.JTextField();
        fieldAirportCity = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        fieldAirportCountry = new javax.swing.JTextField();
        fieldAirportLatitude = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        fieldAirportLongitude = new javax.swing.JTextField();
        btCreateLocation = new javax.swing.JButton();
        flightRPanel = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        fieldIDflight = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        planeFlightR = new javax.swing.JComboBox<>();
        locationDeparture = new javax.swing.JComboBox<>();
        jLabel24 = new javax.swing.JLabel();
        locationArrival = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        locationScale = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        departureYear = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        departureMonth = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        departureDay = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        departureHour = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        departureMinute = new javax.swing.JComboBox<>();
        arrivalHour = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        arrivalMinute = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        scaleHour = new javax.swing.JComboBox<>();
        scaleMinute = new javax.swing.JComboBox<>();
        btCreateFlight = new javax.swing.JButton();
        updateInfoPanel = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        fieldIDupdate = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        fieldFirstNameUpdate = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        fieldLastNameUpdate = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        fieldYearUpdate = new javax.swing.JTextField();
        monthUpdate = new javax.swing.JComboBox<>();
        dayUpdate = new javax.swing.JComboBox<>();
        fieldPhoneUpdate = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        fieldPrefixUpdate = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        fieldCountryUpdate = new javax.swing.JTextField();
        btUpdate = new javax.swing.JButton();
        addToFlightPanel = new javax.swing.JPanel();
        fieldIDaddTFlight = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        showFlightsButton = new javax.swing.JComboBox<>();
        btAddFlight = new javax.swing.JButton();
        showMyFlightsPanel = new javax.swing.JPanel();
        viewMyFlights = new javax.swing.JScrollPane();
        tableMyFlights = new javax.swing.JTable();
        btRefreshMyFlights = new javax.swing.JButton();
        showAllPassengersPanel = new javax.swing.JPanel();
        viewPassengers = new javax.swing.JScrollPane();
        tablePassengers = new javax.swing.JTable();
        btRefreshPassengers = new javax.swing.JButton();
        showAllFlightsPanel = new javax.swing.JPanel();
        viewAllFlights = new javax.swing.JScrollPane();
        tableAllFlights = new javax.swing.JTable();
        refreshAllFlights = new javax.swing.JButton();
        showAllPlanesPanel = new javax.swing.JPanel();
        btRefreshAllPlanes = new javax.swing.JButton();
        viewAllPlanes = new javax.swing.JScrollPane();
        tablePlanes = new javax.swing.JTable();
        showAllLocationsPanel = new javax.swing.JPanel();
        viewAllLocations = new javax.swing.JScrollPane();
        tableLocations = new javax.swing.JTable();
        btRefreshAllLocations = new javax.swing.JButton();
        delayFlightPanel = new javax.swing.JPanel();
        delayHour = new javax.swing.JComboBox<>();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        delayID = new javax.swing.JComboBox<>();
        jLabel48 = new javax.swing.JLabel();
        delayMinute = new javax.swing.JComboBox<>();
        btDelay = new javax.swing.JButton();
        lowView = new airport.views.PanelRound();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        container.setRadius(40);
        container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topView.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                topViewMouseDragged(evt);
            }
        });
        topView.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                topViewMousePressed(evt);
            }
        });

        exit.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        exit.setText("X");
        exit.setBorderPainted(false);
        exit.setContentAreaFilled(false);
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout topViewLayout = new javax.swing.GroupLayout(topView);
        topView.setLayout(topViewLayout);
        topViewLayout.setHorizontalGroup(
            topViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topViewLayout.createSequentialGroup()
                .addContainerGap(1083, Short.MAX_VALUE)
                .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        topViewLayout.setVerticalGroup(
            topViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topViewLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(exit))
        );

        container.add(topView, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 1150, -1));

        views.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N

        administrationPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        user.setText("User");
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        administrationPanel.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, -1, -1));

        administrator.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        administrator.setText("Administrator");
        administrator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administratorActionPerformed(evt);
            }
        });
        administrationPanel.add(administrator, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 164, -1, -1));

        userSelect.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        userSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select User" }));
        userSelect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userSelectActionPerformed(evt);
            }
        });
        administrationPanel.add(userSelect, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 130, -1));

        views.addTab("Administration", administrationPanel);

        passengerRPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel1.setText("Country:");
        passengerRPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, -1, -1));

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel2.setText("ID:");
        passengerRPanel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel3.setText("First Name:");
        passengerRPanel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel4.setText("Last Name:");
        passengerRPanel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel5.setText("Birthdate:");
        passengerRPanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, -1, -1));

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel6.setText("+");
        passengerRPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 20, -1));

        fieldPreFix.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        passengerRPanel.add(fieldPreFix, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 50, -1));

        fieldIDpassenger.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        passengerRPanel.add(fieldIDpassenger, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 130, -1));

        fieldYear.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        passengerRPanel.add(fieldYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 90, -1));

        fieldCountry.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        passengerRPanel.add(fieldCountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 130, -1));

        fieldPhone.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        passengerRPanel.add(fieldPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 340, 130, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel7.setText("Phone:");
        passengerRPanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel8.setText("-");
        passengerRPanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 30, -1));

        fieldLastName.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        passengerRPanel.add(fieldLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 220, 130, -1));

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel9.setText("-");
        passengerRPanel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 30, -1));

        monthPassengerR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        monthPassengerR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));
        passengerRPanel.add(monthPassengerR, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        fieldFirstName.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        passengerRPanel.add(fieldFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 130, -1));

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel10.setText("-");
        passengerRPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 30, -1));

        dayPassengerR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        dayPassengerR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));
        passengerRPanel.add(dayPassengerR, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 280, -1, -1));

        btRegister.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btRegister.setText("Register");
        btRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegisterActionPerformed(evt);
            }
        });
        passengerRPanel.add(btRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, -1, -1));

        views.addTab("Passenger registration", passengerRPanel);

        airplaneRPlane.setLayout(null);

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel11.setText("ID:");
        airplaneRPlane.add(jLabel11);
        jLabel11.setBounds(53, 96, 24, 26);

        fieldIDairplane.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        airplaneRPlane.add(fieldIDairplane);
        fieldIDairplane.setBounds(180, 93, 130, 32);

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel12.setText("Brand:");
        airplaneRPlane.add(jLabel12);
        jLabel12.setBounds(53, 157, 56, 26);

        fieldBrand.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        airplaneRPlane.add(fieldBrand);
        fieldBrand.setBounds(180, 154, 130, 32);

        fieldModel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        airplaneRPlane.add(fieldModel);
        fieldModel.setBounds(180, 213, 130, 32);

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel13.setText("Model:");
        airplaneRPlane.add(jLabel13);
        jLabel13.setBounds(53, 216, 58, 26);

        fieldMaxCapacity.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        airplaneRPlane.add(fieldMaxCapacity);
        fieldMaxCapacity.setBounds(180, 273, 130, 32);

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel14.setText("Max Capacity:");
        airplaneRPlane.add(jLabel14);
        jLabel14.setBounds(53, 276, 118, 26);

        fieldAirline.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        airplaneRPlane.add(fieldAirline);
        fieldAirline.setBounds(180, 333, 130, 32);

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel15.setText("Airline:");
        airplaneRPlane.add(jLabel15);
        jLabel15.setBounds(53, 336, 70, 26);

        fieldCreateAirplane.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        fieldCreateAirplane.setText("Create");
        fieldCreateAirplane.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldCreateAirplaneActionPerformed(evt);
            }
        });
        airplaneRPlane.add(fieldCreateAirplane);
        fieldCreateAirplane.setBounds(490, 480, 120, 40);

        views.addTab("Airplane registration", airplaneRPlane);

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel16.setText("Airport ID:");

        fieldAirportID.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel17.setText("Airport name:");

        fieldAirportName.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        fieldAirportCity.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel18.setText("Airport city:");

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel19.setText("Airport country:");

        fieldAirportCountry.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        fieldAirportLatitude.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel20.setText("Airport latitude:");

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel21.setText("Airport longitude:");

        fieldAirportLongitude.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        btCreateLocation.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btCreateLocation.setText("Create");
        btCreateLocation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreateLocationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout locationRPanelLayout = new javax.swing.GroupLayout(locationRPanel);
        locationRPanel.setLayout(locationRPanelLayout);
        locationRPanelLayout.setHorizontalGroup(
            locationRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationRPanelLayout.createSequentialGroup()
                .addGroup(locationRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(locationRPanelLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(locationRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(80, 80, 80)
                        .addGroup(locationRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldAirportLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldAirportID, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldAirportName, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldAirportCity, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldAirportCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldAirportLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(locationRPanelLayout.createSequentialGroup()
                        .addGap(515, 515, 515)
                        .addComponent(btCreateLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(515, 515, 515))
        );
        locationRPanelLayout.setVerticalGroup(
            locationRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(locationRPanelLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(locationRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(locationRPanelLayout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel17)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel18)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel19)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel20))
                    .addGroup(locationRPanelLayout.createSequentialGroup()
                        .addComponent(fieldAirportID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(fieldAirportName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(fieldAirportCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(fieldAirportCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(fieldAirportLatitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44)
                .addGroup(locationRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(fieldAirportLongitude, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btCreateLocation, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
        );

        views.addTab("Location registration", locationRPanel);

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel22.setText("ID:");

        fieldIDflight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel23.setText("Plane:");

        planeFlightR.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        planeFlightR.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Plane" }));

        locationDeparture.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        locationDeparture.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel24.setText("Departure location:");

        locationArrival.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        locationArrival.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel25.setText("Arrival location:");

        jLabel26.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel26.setText("Scale location:");

        locationScale.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        locationScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Location" }));

        jLabel27.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel27.setText("Duration:");

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel28.setText("Duration:");

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel29.setText("Departure date:");

        departureYear.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel30.setText("-");

        departureMonth.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        departureMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel31.setText("-");

        departureDay.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        departureDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel32.setText("-");

        departureHour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        departureHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel33.setText("-");

        departureMinute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        departureMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        arrivalHour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        arrivalHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel34.setText("-");

        arrivalMinute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        arrivalMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        jLabel35.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel35.setText("-");

        scaleHour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        scaleHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        scaleMinute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        scaleMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        btCreateFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btCreateFlight.setText("Create");
        btCreateFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCreateFlightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout flightRPanelLayout = new javax.swing.GroupLayout(flightRPanel);
        flightRPanel.setLayout(flightRPanelLayout);
        flightRPanelLayout.setHorizontalGroup(
            flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(flightRPanelLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(flightRPanelLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(locationScale, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, flightRPanelLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(locationArrival, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(flightRPanelLayout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(46, 46, 46)
                        .addComponent(locationDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(flightRPanelLayout.createSequentialGroup()
                        .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fieldIDflight)
                            .addComponent(planeFlightR, 0, 130, Short.MAX_VALUE))))
                .addGap(45, 45, 45)
                .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29))
                .addGap(18, 18, 18)
                .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(flightRPanelLayout.createSequentialGroup()
                        .addComponent(departureYear, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(flightRPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(departureMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(flightRPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(departureDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(flightRPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(departureHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(flightRPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(departureMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(30, 30, 30))
                    .addGroup(flightRPanelLayout.createSequentialGroup()
                        .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(flightRPanelLayout.createSequentialGroup()
                                .addComponent(arrivalHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(flightRPanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(arrivalMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(flightRPanelLayout.createSequentialGroup()
                                .addComponent(scaleHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(14, 14, 14)
                                .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(flightRPanelLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(scaleMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, flightRPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btCreateFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(530, 530, 530))
        );
        flightRPanelLayout.setVerticalGroup(
            flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(flightRPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(flightRPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel22))
                    .addComponent(fieldIDflight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(planeFlightR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(departureHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(departureMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(flightRPanelLayout.createSequentialGroup()
                        .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24)
                                .addComponent(locationDeparture, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29))
                            .addComponent(departureYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(departureMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)
                            .addComponent(jLabel31)
                            .addComponent(departureDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel25)
                                .addComponent(locationArrival, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel28))
                            .addComponent(arrivalHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34)
                            .addComponent(arrivalMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scaleHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35)
                            .addComponent(scaleMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(flightRPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel26)
                                .addComponent(locationScale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addComponent(btCreateFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50))
        );

        views.addTab("Flight registration", flightRPanel);

        jLabel36.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel36.setText("ID:");

        fieldIDupdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        fieldIDupdate.setActionCommand("<Not Set>");
        fieldIDupdate.setEnabled(false);

        jLabel37.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel37.setText("First Name:");

        fieldFirstNameUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        fieldFirstNameUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldFirstNameUpdateActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel38.setText("Last Name:");

        fieldLastNameUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel39.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel39.setText("Birthdate:");

        fieldYearUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        monthUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        monthUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month" }));

        dayUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        dayUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day" }));

        fieldPhoneUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel40.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel40.setText("-");

        fieldPrefixUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        jLabel41.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel41.setText("+");

        jLabel42.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel42.setText("Phone:");

        jLabel43.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel43.setText("Country:");

        fieldCountryUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N

        btUpdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btUpdate.setText("Update");
        btUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updateInfoPanelLayout = new javax.swing.GroupLayout(updateInfoPanel);
        updateInfoPanel.setLayout(updateInfoPanelLayout);
        updateInfoPanelLayout.setHorizontalGroup(
            updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateInfoPanelLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel36)
                                .addGap(108, 108, 108)
                                .addComponent(fieldIDupdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(41, 41, 41)
                                .addComponent(fieldFirstNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addGap(43, 43, 43)
                                .addComponent(fieldLastNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(55, 55, 55)
                                .addComponent(fieldYearUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(monthUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(dayUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel42)
                                .addGap(56, 56, 56)
                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(fieldPrefixUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(fieldPhoneUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabel43)
                                .addGap(63, 63, 63)
                                .addComponent(fieldCountryUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(updateInfoPanelLayout.createSequentialGroup()
                        .addGap(507, 507, 507)
                        .addComponent(btUpdate)))
                .addContainerGap(554, Short.MAX_VALUE))
        );
        updateInfoPanelLayout.setVerticalGroup(
            updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateInfoPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel36)
                    .addComponent(fieldIDupdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(fieldFirstNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(fieldLastNameUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel39)
                    .addComponent(fieldYearUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41)
                    .addComponent(fieldPrefixUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel40)
                    .addComponent(fieldPhoneUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(updateInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addComponent(fieldCountryUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btUpdate)
                .addGap(113, 113, 113))
        );

        views.addTab("Update info", updateInfoPanel);

        fieldIDaddTFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        fieldIDaddTFlight.setEnabled(false);

        jLabel44.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel44.setText("ID:");

        jLabel45.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel45.setText("Flight:");

        showFlightsButton.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        showFlightsButton.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Flight" }));
        showFlightsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showFlightsButtonActionPerformed(evt);
            }
        });

        btAddFlight.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btAddFlight.setText("Add");
        btAddFlight.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddFlightActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addToFlightPanelLayout = new javax.swing.GroupLayout(addToFlightPanel);
        addToFlightPanel.setLayout(addToFlightPanelLayout);
        addToFlightPanelLayout.setHorizontalGroup(
            addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addToFlightPanelLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45))
                .addGap(79, 79, 79)
                .addGroup(addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showFlightsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldIDaddTFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(828, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addToFlightPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btAddFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(509, 509, 509))
        );
        addToFlightPanelLayout.setVerticalGroup(
            addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addToFlightPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addToFlightPanelLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel44))
                    .addComponent(fieldIDaddTFlight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(addToFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(showFlightsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 288, Short.MAX_VALUE)
                .addComponent(btAddFlight, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );

        views.addTab("Add to flight", addToFlightPanel);

        tableMyFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tableMyFlights.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Departure Date", "Arrival Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewMyFlights.setViewportView(tableMyFlights);

        btRefreshMyFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btRefreshMyFlights.setText("Refresh");
        btRefreshMyFlights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshMyFlightsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout showMyFlightsPanelLayout = new javax.swing.GroupLayout(showMyFlightsPanel);
        showMyFlightsPanel.setLayout(showMyFlightsPanelLayout);
        showMyFlightsPanelLayout.setHorizontalGroup(
            showMyFlightsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showMyFlightsPanelLayout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addComponent(viewMyFlights, javax.swing.GroupLayout.PREFERRED_SIZE, 590, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(294, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showMyFlightsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btRefreshMyFlights)
                .addGap(527, 527, 527))
        );
        showMyFlightsPanelLayout.setVerticalGroup(
            showMyFlightsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showMyFlightsPanelLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(viewMyFlights, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(btRefreshMyFlights)
                .addContainerGap())
        );

        views.addTab("Show my flights", showMyFlightsPanel);

        tablePassengers.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tablePassengers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Birthdate", "Age", "Phone", "Country", "Num Flight"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewPassengers.setViewportView(tablePassengers);

        btRefreshPassengers.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btRefreshPassengers.setText("Refresh");
        btRefreshPassengers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshPassengersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout showAllPassengersPanelLayout = new javax.swing.GroupLayout(showAllPassengersPanel);
        showAllPassengersPanel.setLayout(showAllPassengersPanelLayout);
        showAllPassengersPanelLayout.setHorizontalGroup(
            showAllPassengersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showAllPassengersPanelLayout.createSequentialGroup()
                .addGroup(showAllPassengersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showAllPassengersPanelLayout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(btRefreshPassengers))
                    .addGroup(showAllPassengersPanelLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(viewPassengers, javax.swing.GroupLayout.PREFERRED_SIZE, 1078, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        showAllPassengersPanelLayout.setVerticalGroup(
            showAllPassengersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showAllPassengersPanelLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(viewPassengers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btRefreshPassengers)
                .addContainerGap())
        );

        views.addTab("Show all passengers", showAllPassengersPanel);

        tableAllFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        tableAllFlights.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Departure Airport ID", "Arrival Airport ID", "Scale Airport ID", "Departure Date", "Arrival Date", "Plane ID", "Number Passengers"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewAllFlights.setViewportView(tableAllFlights);

        refreshAllFlights.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        refreshAllFlights.setText("Refresh");
        refreshAllFlights.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshAllFlightsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout showAllFlightsPanelLayout = new javax.swing.GroupLayout(showAllFlightsPanel);
        showAllFlightsPanel.setLayout(showAllFlightsPanelLayout);
        showAllFlightsPanelLayout.setHorizontalGroup(
            showAllFlightsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showAllFlightsPanelLayout.createSequentialGroup()
                .addGroup(showAllFlightsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showAllFlightsPanelLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(viewAllFlights, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(showAllFlightsPanelLayout.createSequentialGroup()
                        .addGap(521, 521, 521)
                        .addComponent(refreshAllFlights)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        showAllFlightsPanelLayout.setVerticalGroup(
            showAllFlightsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showAllFlightsPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(viewAllFlights, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(refreshAllFlights)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        views.addTab("Show all flights", showAllFlightsPanel);

        btRefreshAllPlanes.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btRefreshAllPlanes.setText("Refresh");
        btRefreshAllPlanes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshAllPlanesActionPerformed(evt);
            }
        });

        tablePlanes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Brand", "Model", "Max Capacity", "Airline", "Number Flights"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewAllPlanes.setViewportView(tablePlanes);

        javax.swing.GroupLayout showAllPlanesPanelLayout = new javax.swing.GroupLayout(showAllPlanesPanel);
        showAllPlanesPanel.setLayout(showAllPlanesPanelLayout);
        showAllPlanesPanelLayout.setHorizontalGroup(
            showAllPlanesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showAllPlanesPanelLayout.createSequentialGroup()
                .addGroup(showAllPlanesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showAllPlanesPanelLayout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(btRefreshAllPlanes))
                    .addGroup(showAllPlanesPanelLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(viewAllPlanes, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        showAllPlanesPanelLayout.setVerticalGroup(
            showAllPlanesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showAllPlanesPanelLayout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addComponent(viewAllPlanes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btRefreshAllPlanes)
                .addGap(17, 17, 17))
        );

        views.addTab("Show all planes", showAllPlanesPanel);

        tableLocations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Airport ID", "Airport Name", "City", "Country"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewAllLocations.setViewportView(tableLocations);

        btRefreshAllLocations.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btRefreshAllLocations.setText("Refresh");
        btRefreshAllLocations.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRefreshAllLocationsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout showAllLocationsPanelLayout = new javax.swing.GroupLayout(showAllLocationsPanel);
        showAllLocationsPanel.setLayout(showAllLocationsPanelLayout);
        showAllLocationsPanelLayout.setHorizontalGroup(
            showAllLocationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showAllLocationsPanelLayout.createSequentialGroup()
                .addGroup(showAllLocationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showAllLocationsPanelLayout.createSequentialGroup()
                        .addGap(508, 508, 508)
                        .addComponent(btRefreshAllLocations))
                    .addGroup(showAllLocationsPanelLayout.createSequentialGroup()
                        .addGap(245, 245, 245)
                        .addComponent(viewAllLocations, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(253, Short.MAX_VALUE))
        );
        showAllLocationsPanelLayout.setVerticalGroup(
            showAllLocationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showAllLocationsPanelLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(viewAllLocations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btRefreshAllLocations)
                .addGap(17, 17, 17))
        );

        views.addTab("Show all locations", showAllLocationsPanel);

        delayHour.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        delayHour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hour" }));

        jLabel46.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel46.setText("Hours:");

        jLabel47.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel47.setText("ID:");

        delayID.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        delayID.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ID" }));

        jLabel48.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        jLabel48.setText("Minutes:");

        delayMinute.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        delayMinute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Minute" }));

        btDelay.setFont(new java.awt.Font("Yu Gothic UI", 0, 18)); // NOI18N
        btDelay.setText("Delay");
        btDelay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout delayFlightPanelLayout = new javax.swing.GroupLayout(delayFlightPanel);
        delayFlightPanel.setLayout(delayFlightPanelLayout);
        delayFlightPanelLayout.setHorizontalGroup(
            delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(delayFlightPanelLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(delayFlightPanelLayout.createSequentialGroup()
                        .addComponent(jLabel48)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(delayMinute, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(delayFlightPanelLayout.createSequentialGroup()
                        .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel47)
                            .addComponent(jLabel46))
                        .addGap(79, 79, 79)
                        .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(delayHour, 0, 106, Short.MAX_VALUE)
                            .addComponent(delayID, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(820, 820, 820))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, delayFlightPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btDelay)
                .addGap(531, 531, 531))
        );
        delayFlightPanelLayout.setVerticalGroup(
            delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(delayFlightPanelLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(delayID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46)
                    .addComponent(delayHour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(delayFlightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(delayMinute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 307, Short.MAX_VALUE)
                .addComponent(btDelay)
                .addGap(33, 33, 33))
        );

        views.addTab("Delay flight", delayFlightPanel);

        container.add(views, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1150, 620));

        javax.swing.GroupLayout lowViewLayout = new javax.swing.GroupLayout(lowView);
        lowView.setLayout(lowViewLayout);
        lowViewLayout.setHorizontalGroup(
            lowViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1150, Short.MAX_VALUE)
        );
        lowViewLayout.setVerticalGroup(
            lowViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
        );

        container.add(lowView, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 660, 1150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(container, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void topViewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topViewMousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_topViewMousePressed

    private void topViewMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_topViewMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_topViewMouseDragged

    private void administratorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administratorActionPerformed
        if (user.isSelected()) {
            user.setSelected(false);
            userSelect.setSelectedIndex(0);

        }
        for (int i = 1; i < views.getTabCount(); i++) {
            views.setEnabledAt(i, true);
        }
        views.setEnabledAt(5, false);
        views.setEnabledAt(6, false);
    }//GEN-LAST:event_administratorActionPerformed

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        if (administrator.isSelected()) {
            administrator.setSelected(false);
        }
        for (int i = 1; i < views.getTabCount(); i++) {

            views.setEnabledAt(i, false);

        }
        views.setEnabledAt(9, true);
        views.setEnabledAt(5, true);
        views.setEnabledAt(6, true);
        views.setEnabledAt(7, true);
        views.setEnabledAt(11, true);
        cargarIDsEnComboBox();
    }//GEN-LAST:event_userActionPerformed

    private void btRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegisterActionPerformed
        String idText = fieldIDpassenger.getText();
        String firstname = fieldFirstName.getText();
        String lastname = fieldLastName.getText();
        String yearText = fieldYear.getText();
        String monthText = monthPassengerR.getSelectedItem().toString();
        String dayText = dayPassengerR.getSelectedItem().toString();
        String phoneCodeText = fieldPreFix.getText();
        String phoneText = fieldPhone.getText();
        String country = fieldCountry.getText();

        passengerRepository.createPassengerFromRawData(
                idText, firstname, lastname, yearText, monthText, dayText, phoneCodeText, phoneText, country
        );
        this.userSelect.addItem("" + idText);


    }//GEN-LAST:event_btRegisterActionPerformed

    private void fieldCreateAirplaneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldCreateAirplaneActionPerformed
        try {
            String id = fieldIDairplane.getText();
            String brand = fieldBrand.getText();
            String model = fieldModel.getText();
            int maxCapacity = Parsers.INTEGER.parse(fieldMaxCapacity.getText());
            String airline = fieldAirline.getText();

            this.planes.add(new Plane(id, brand, model, maxCapacity, airline));

            this.planeFlightR.addItem(id);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_fieldCreateAirplaneActionPerformed

    private void btCreateLocationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreateLocationActionPerformed
        // TODO add your handling code here:
        try {
            String id = fieldAirportID.getText();
            String name = fieldAirportName.getText();
            String city = fieldAirportCity.getText();
            String country = fieldAirportCountry.getText();
            double latitude = Parsers.DOUBLE.parse(fieldAirportLatitude.getText());
            double longitude = Parsers.DOUBLE.parse(fieldAirportLongitude.getText());

            this.locations.add(new Location(id, name, city, country, latitude, longitude));

            this.locationDeparture.addItem(id);
            this.locationArrival.addItem(id);
            this.locationScale.addItem(id);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error en los datos del aeropuerto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btCreateLocationActionPerformed

    private void btCreateFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCreateFlightActionPerformed

    }//GEN-LAST:event_btCreateFlightActionPerformed

    private void btUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btUpdateActionPerformed
        onUpdateUser();

    }//GEN-LAST:event_btUpdateActionPerformed

    private void btAddFlightActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddFlightActionPerformed

        try {
            String passengerID = fieldIDaddTFlight.getText();
            String flightID = showFlightsButton.getSelectedItem().toString();

            if (passengerID == null || passengerID.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un pasajero.");
                return;
            }

            if (flightID == null || flightID.equals("Flight")) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona un vuelo válido.");
                return;
            }

            // Llamada al repositorio/controlador (solo pasando IDs)
            Passenger passenger = passengerRepository.getPassengerById(passengerID);
            String flightId = flightRepository.extractFlightID(flightID);
            flightRepository.addPassengerToFlight(flightId, passenger);

            JOptionPane.showMessageDialog(this, "Pasajero agregado correctamente al vuelo.");
            updatePassengerTable();
            updateFlightTable();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al agregar pasajero al vuelo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btAddFlightActionPerformed

    private void btDelayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelayActionPerformed

    }//GEN-LAST:event_btDelayActionPerformed

    private void btRefreshMyFlightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshMyFlightsActionPerformed
        try {
            String id = userSelect.getSelectedItem().toString();
            if (!id.equals(userSelect.getItemAt(0))) {  // Si no es "Select User"
                fieldIDupdate.setText(id);
                fieldIDaddTFlight.setText(id);

                // Actualizar la tabla de "Show my flights"
                updateMyFlightsTable(id);
            } else {
                // Si es "Select User", limpiar campos y tabla
                fieldIDupdate.setText("");
                fieldIDaddTFlight.setText("");

                DefaultTableModel model = (DefaultTableModel) tableMyFlights.getModel();
                model.setRowCount(0);
            }
        } catch (Exception e) {
            // En caso de error (por ejemplo, no se puede parsear el ID), puedes loguear o ignorar.
        }

    }//GEN-LAST:event_btRefreshMyFlightsActionPerformed

    private void btRefreshPassengersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshPassengersActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tablePassengers.getModel();
        model.setRowCount(0);
        for (Passenger passenger : this.passengers) {
            model.addRow(new Object[]{passenger.getId(), passenger.getFullname(), passenger.getBirthDate(), passenger.getAge(), passenger.getFullPhone(), passenger.getCountry(), passenger.getNumberFlights()});
        }
    }//GEN-LAST:event_btRefreshPassengersActionPerformed

    private void refreshAllFlightsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshAllFlightsActionPerformed
        try {
            // TODO add your handling code here:
            JTable table= flightRepository.onRefreshAllFlights();
            viewAllFlights.setViewportView(table);
           refreshAllFlights.addActionListener(e->{
                try {
                    flightRepository.onRefreshAllFlights();
                } catch (IOException ex) {
                    Logger.getLogger(AirportFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(AirportFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_refreshAllFlightsActionPerformed

    private void btRefreshAllPlanesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshAllPlanesActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tablePlanes.getModel();
        model.setRowCount(0);
        for (Plane plane : this.planes) {
            model.addRow(new Object[]{plane.getId(), plane.getBrand(), plane.getModel(), plane.getMaxCapacity(), plane.getAirline(), plane.getNumFlights()});
        }
    }//GEN-LAST:event_btRefreshAllPlanesActionPerformed

    private void btRefreshAllLocationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRefreshAllLocationsActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) tableLocations.getModel();
        model.setRowCount(0);
        for (Location location : this.locations) {
            model.addRow(new Object[]{location.getAirportID(), location.getAirportName(), location.getAirportCity(), location.getAirportCountry()});
        }
    }//GEN-LAST:event_btRefreshAllLocationsActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void userSelectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userSelectActionPerformed

        try {
            String id = userSelect.getSelectedItem().toString();
            if (!id.equals(userSelect.getItemAt(0))) {  // Si no es "Select User"
                fieldIDupdate.setText(id);
                fieldIDaddTFlight.setText(id);

            } else {
                // Si es "Select User", limpiar campos y tabla
                fieldIDupdate.setText("");
                fieldIDaddTFlight.setText("");

                DefaultTableModel model = (DefaultTableModel) tableMyFlights.getModel();
                model.setRowCount(0);
            }
        } catch (Exception e) {
            // En caso de error (por ejemplo, no se puede parsear el ID), puedes loguear o ignorar.
        }

    }//GEN-LAST:event_userSelectActionPerformed

    private void showFlightsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showFlightsButtonActionPerformed
        try {
            String selected = showFlightsButton.getSelectedItem().toString();

            if (!selected.equals("Select Flight")) { // Validar que no sea el item por defecto
                System.out.println("Vuelo seleccionado: " + selected);
                // Aquí puedes hacer lo que necesites con el vuelo seleccionado
            } else {
                System.out.println("Selecciona un vuelo válido");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_showFlightsButtonActionPerformed

    private void fieldFirstNameUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldFirstNameUpdateActionPerformed

    }//GEN-LAST:event_fieldFirstNameUpdateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel addToFlightPanel;
    private javax.swing.JPanel administrationPanel;
    private javax.swing.JRadioButton administrator;
    private javax.swing.JPanel airplaneRPlane;
    private javax.swing.JComboBox<String> arrivalHour;
    private javax.swing.JComboBox<String> arrivalMinute;
    private javax.swing.JButton btAddFlight;
    private javax.swing.JButton btCreateFlight;
    private javax.swing.JButton btCreateLocation;
    private javax.swing.JButton btDelay;
    private javax.swing.JButton btRefreshAllLocations;
    private javax.swing.JButton btRefreshAllPlanes;
    private javax.swing.JButton btRefreshMyFlights;
    private javax.swing.JButton btRefreshPassengers;
    private javax.swing.JButton btRegister;
    private javax.swing.JButton btUpdate;
    private airport.views.PanelRound container;
    private javax.swing.JComboBox<String> dayPassengerR;
    private javax.swing.JComboBox<String> dayUpdate;
    private javax.swing.JPanel delayFlightPanel;
    private javax.swing.JComboBox<String> delayHour;
    private javax.swing.JComboBox<String> delayID;
    private javax.swing.JComboBox<String> delayMinute;
    private javax.swing.JComboBox<String> departureDay;
    private javax.swing.JComboBox<String> departureHour;
    private javax.swing.JComboBox<String> departureMinute;
    private javax.swing.JComboBox<String> departureMonth;
    private javax.swing.JTextField departureYear;
    private javax.swing.JButton exit;
    private javax.swing.JTextField fieldAirline;
    private javax.swing.JTextField fieldAirportCity;
    private javax.swing.JTextField fieldAirportCountry;
    private javax.swing.JTextField fieldAirportID;
    private javax.swing.JTextField fieldAirportLatitude;
    private javax.swing.JTextField fieldAirportLongitude;
    private javax.swing.JTextField fieldAirportName;
    private javax.swing.JTextField fieldBrand;
    private javax.swing.JTextField fieldCountry;
    private javax.swing.JTextField fieldCountryUpdate;
    private javax.swing.JButton fieldCreateAirplane;
    private javax.swing.JTextField fieldFirstName;
    private javax.swing.JTextField fieldFirstNameUpdate;
    private javax.swing.JTextField fieldIDaddTFlight;
    private javax.swing.JTextField fieldIDairplane;
    private javax.swing.JTextField fieldIDflight;
    private javax.swing.JTextField fieldIDpassenger;
    private javax.swing.JTextField fieldIDupdate;
    private javax.swing.JTextField fieldLastName;
    private javax.swing.JTextField fieldLastNameUpdate;
    private javax.swing.JTextField fieldMaxCapacity;
    private javax.swing.JTextField fieldModel;
    private javax.swing.JTextField fieldPhone;
    private javax.swing.JTextField fieldPhoneUpdate;
    private javax.swing.JTextField fieldPreFix;
    private javax.swing.JTextField fieldPrefixUpdate;
    private javax.swing.JTextField fieldYear;
    private javax.swing.JTextField fieldYearUpdate;
    private javax.swing.JPanel flightRPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JComboBox<String> locationArrival;
    private javax.swing.JComboBox<String> locationDeparture;
    private javax.swing.JPanel locationRPanel;
    private javax.swing.JComboBox<String> locationScale;
    private airport.views.PanelRound lowView;
    private javax.swing.JComboBox<String> monthPassengerR;
    private javax.swing.JComboBox<String> monthUpdate;
    private javax.swing.JPanel passengerRPanel;
    private javax.swing.JComboBox<String> planeFlightR;
    private javax.swing.JButton refreshAllFlights;
    private javax.swing.JComboBox<String> scaleHour;
    private javax.swing.JComboBox<String> scaleMinute;
    private javax.swing.JPanel showAllFlightsPanel;
    private javax.swing.JPanel showAllLocationsPanel;
    private javax.swing.JPanel showAllPassengersPanel;
    private javax.swing.JPanel showAllPlanesPanel;
    private javax.swing.JComboBox<String> showFlightsButton;
    private javax.swing.JPanel showMyFlightsPanel;
    private javax.swing.JTable tableAllFlights;
    private javax.swing.JTable tableLocations;
    private javax.swing.JTable tableMyFlights;
    private javax.swing.JTable tablePassengers;
    private javax.swing.JTable tablePlanes;
    private airport.views.PanelRound topView;
    private javax.swing.JPanel updateInfoPanel;
    private javax.swing.JRadioButton user;
    private javax.swing.JComboBox<String> userSelect;
    private javax.swing.JScrollPane viewAllFlights;
    private javax.swing.JScrollPane viewAllLocations;
    private javax.swing.JScrollPane viewAllPlanes;
    private javax.swing.JScrollPane viewMyFlights;
    private javax.swing.JScrollPane viewPassengers;
    private javax.swing.JTabbedPane views;
    // End of variables declaration//GEN-END:variables
}
