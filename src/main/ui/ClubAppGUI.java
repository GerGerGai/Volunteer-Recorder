package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.ArrayList;

import java.io.FileNotFoundException;
import java.io.IOException;

import model.*;
import model.Event;
import model.exceptions.LogException;
import persistance.JsonReader;
import persistance.JsonWriter;




// Inspired by "https://docs.oracle.com/javase/8/docs/api/" and "https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html"
// GUI for volunteer manager
public class ClubAppGUI extends JFrame
                        implements ListSelectionListener, WindowListener {

    private JList volunteerList;
    private DefaultListModel listModel;

    private static final String addString = "Add";
    private static final String removeString = "Remove";
    private static final String moreinfoString = "Info";
    private static final String barGraphString = "Bar";
    private static final String saveString = "Save";
    private static final String loadString = "Load";

    private JButton addButton;
    private JButton removeButton;
    private JButton infoButton;
    private JButton barButton;
    private JButton saveButton;
    private JButton loadButton;

    private JTextArea infoArea;

    private static final int WIDTH = 500;
    private static final int HEIGHT = 300;

    private static final String JSON_STORE = "./data/Education.json";

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    Education education;
    Director director1;
    UniversityVolunteer volunteer1;
    KenyaStudent student1;


    // EFFECTS: create the Volunteer Manager GUI
    public ClubAppGUI() {

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        JPanel outmostPanel = new JPanel(new BorderLayout());

        listModel = new DefaultListModel();
//        listModel.addElement("Jane Doe");
//        listModel.addElement("John Smith");
//        listModel.addElement("Kathy Green");

        //Create the list and put it in a scroll pane.
        initVolunteerList();
        JScrollPane listScrollPane = new JScrollPane(volunteerList);

        initButtons();

        JPanel buttonPane1 = initButtonPane1();

        JPanel buttonPane2 = initButtonPane2();

        infoArea = new JTextArea(10,30);

        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.add(listScrollPane, BorderLayout.CENTER);

        topPanel.add(buttonPane1, BorderLayout.PAGE_END);

        outmostPanel.add(topPanel,BorderLayout.NORTH);
        outmostPanel.add(infoArea,BorderLayout.CENTER);
        outmostPanel.add(buttonPane2,BorderLayout.PAGE_END);

        setContentPane(outmostPanel);
        setTitle("VolunteerManager");
        setSize(WIDTH, HEIGHT);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(this);
        setVisible(true);


    }

    // EFFECTS: required by ListSelectionEvent
    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    // MODIFIES: this
    // EFFECTS: initialize all the buttons for the outmost frame
    private void initButtons() {
        addButton = new JButton(addString);
//        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(new AddListener());
//        addButton.setEnabled(false);

        removeButton = new JButton(removeString);
        removeButton.setActionCommand(removeString);
        removeButton.addActionListener(new RemoveListener());

        infoButton = new JButton(moreinfoString);
        infoButton.setActionCommand(moreinfoString);
        infoButton.addActionListener(new InfoListener());

        barButton = new JButton(barGraphString);
        barButton.setActionCommand(barGraphString);
        barButton.addActionListener(new BarListener());

        saveButton = new JButton(saveString);
        saveButton.setActionCommand(saveString);
        saveButton.addActionListener(new SaveListener());

        loadButton = new JButton(loadString);
        loadButton.setActionCommand(loadString);
        loadButton.addActionListener(new LoadListener());
    }

    // MODIFIES: this
    // EFFECTS: initialize the first half of button panel
    private JPanel initButtonPane1() {
        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(barButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.add(infoButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.add(removeButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.add(addButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        return buttonPane;
    }



    // MODIFIES: this
    // EFFECTS: initialize the second half of button panel
    private JPanel initButtonPane2() {
        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));

        buttonPane.add(saveButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));

        buttonPane.add(loadButton);

        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        return buttonPane;
    }

    // MODIFIES: this
    // EFFECTS: initialize the volunteer list
    private void initVolunteerList() {

        volunteerList = new JList(listModel);
        volunteerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        volunteerList.setSelectedIndex(0);
        volunteerList.addListSelectionListener(this);
        volunteerList.setVisibleRowCount(5);

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        EventLog events = EventLog.getInstance();


        for (Event next : events) {
            System.out.println(next.toString());
            System.out.println("\n\n");
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {


    }



    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    // the listener for AddButton
    class AddListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        private JLabel label1 = new JLabel("id");
        private JTextField field1 = new JTextField(20);

        private JLabel label2 = new JLabel("name");
        private JTextField field2 = new JTextField(20);

        private JLabel label3 = new JLabel("major: input exactly one of the four: math, chemistry, biology, physics");
        private JTextField field3 = new JTextField(20);

        private JLabel label4 = new JLabel("year");
        private JTextField field4 = new JTextField(20);

        JFrame outMostFrame = new JFrame();
        JPanel outMostPanel = new JPanel(new BorderLayout());
        JPanel innerPanel1 = new JPanel(new BorderLayout());
        JPanel innerPanel2 = new JPanel(new BorderLayout());
        JPanel innerPanel3 = new JPanel(new BorderLayout());
        JPanel innerPanel4 = new JPanel(new BorderLayout());
        JPanel innerPanel5 = new JPanel(new BorderLayout());
        JPanel innerPanel6 = new JPanel(new BorderLayout());

        JButton addButton = new JButton("Add");


        //Required by ActionListener.
        // MODIFIES: this
        // EFFECTS: perform the action for add volunteer
        public void actionPerformed(ActionEvent e) {
            setUpWindow();

        }

        // MODIFIES: this
        // EFFECTS: set up the window well
        private void setUpWindow() {

            addButton.setActionCommand("Add");
            addButton.addActionListener(new AddToListListener());

            innerPanel1.add(label1,BorderLayout.NORTH);
            innerPanel1.add(field1,BorderLayout.SOUTH);
            innerPanel2.add(label2,BorderLayout.NORTH);
            innerPanel2.add(field2,BorderLayout.SOUTH);
            innerPanel3.add(label3,BorderLayout.NORTH);
            innerPanel3.add(field3,BorderLayout.SOUTH);
            innerPanel4.add(label4,BorderLayout.NORTH);
            innerPanel4.add(field4,BorderLayout.SOUTH);

            innerPanel5.add(innerPanel1,BorderLayout.NORTH);
            innerPanel5.add(innerPanel2,BorderLayout.SOUTH);
            innerPanel6.add(innerPanel3,BorderLayout.NORTH);
            innerPanel6.add(innerPanel4,BorderLayout.SOUTH);

            outMostPanel.add(innerPanel5,BorderLayout.WEST);
            outMostPanel.add(innerPanel6,BorderLayout.EAST);
            outMostPanel.add(addButton,BorderLayout.CENTER);

            outMostFrame.setContentPane(outMostPanel);
            outMostFrame.setTitle("VolunteerManager");
            outMostFrame.setSize(500, 200);

            outMostFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            outMostFrame.setVisible(true);

        }

        // Listener for the add button within the add volunteer window
        class AddToListListener implements ActionListener {

            // MODIFIES: this
            // EFFECTS: add volunteer to the GUI and the system
            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(field1.getText());
                int year = Integer.parseInt(field4.getText());
                String name = field2.getText();
                String major = field3.getText();
                Volunteer newVolunteer = new UniversityVolunteer(name,major,
                        id,year);
                if (education == null) {
                    ArrayList<Volunteer> volunteers = new ArrayList<>();
                    volunteers.add(newVolunteer);
                } else {
                    education.addVolunteers(newVolunteer);
                    listModel.addElement(name);
                }



                // Reset the text field.
                field1.requestFocusInWindow();
                field1.setText("");
                field2.requestFocusInWindow();
                field2.setText("");
                field3.requestFocusInWindow();
                field3.setText("");
                field4.requestFocusInWindow();
                field4.setText("");
            }
        }



        // MODIFIES: this
        // EFFECTS: change the status of the button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }


    }

    // Listener for remove volunteer button
    class RemoveListener implements ActionListener {

        // MODIFIES: this
        // EFFECTS: remove the indexed volunteer from GUI and the system
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = volunteerList.getSelectedIndex();
            ArrayList<Volunteer> volunteers = education.getVolunteerList();
            Volunteer removeVolunteer = volunteers.get(index);
            education.removeVolunteers(removeVolunteer);
            listModel.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                removeButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                volunteerList.setSelectedIndex(index);
                volunteerList.ensureIndexIsVisible(index);
            }
        }
    }


    // Listener for info button
    class InfoListener implements ActionListener {


        // MODIFIES: this
        // EFFECTS: show the detailed information of the selected volunteer
        @Override
        public void actionPerformed(ActionEvent e) {

            infoArea.selectAll();
            infoArea.replaceSelection("");

            int index = volunteerList.getSelectedIndex();
            ArrayList<Volunteer> volunteers = education.getVolunteerList();
            Volunteer volunteer = volunteers.get(index);

            int id = volunteer.getId();
            int year = volunteer.getYear();
            String name = volunteer.getName();
            String major = volunteer.getMajor();

            infoArea.append("Name: " + name + "\nID: " + Integer.toString(id)
                    + "\nMajor: " + major + "\nYear: " + Integer.toString(year));

        }
    }


    // Listener for the bar button
    class BarListener implements ActionListener {

        ArrayList<Volunteer> volunteers;
        int mathNum = 0;
        int physNum = 0;
        int chemNum = 0;
        int bioNum = 0;

        double[] values = new double[4];
        String[] names = new String[4];

        JFrame outMostFrame = new JFrame();

        ChartPanelUI chartPanelUI = new ChartPanelUI(values,names,"Major: # of Students");

        // MODIFIES: this
        // EFFECTS: show the bar graph of number of students in each major
        @Override
        public void actionPerformed(ActionEvent e) {


            outMostFrame.setSize(400, 300);

            if (education == null) {
                volunteers = new ArrayList<>();
            } else {
                volunteers = education.getVolunteerList();
                countNums();

            }

            setUpData();

            outMostFrame.getContentPane().add(chartPanelUI);


            outMostFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            outMostFrame.setVisible(true);

        }

        // MODIFIES: this
        // EFFECTS: count the number of students in each major
        private void countNums() {
            mathNum = 0;
            chemNum = 0;
            physNum = 0;
            bioNum = 0;
            for (Volunteer volunteer : volunteers) {
                if (volunteer.getMajor().equals("math")) {
                    mathNum++;
                } else if (volunteer.getMajor().equals("chemistry")) {
                    chemNum++;
                } else if (volunteer.getMajor().equals("physics")) {
                    physNum++;
                } else if (volunteer.getMajor().equals("biology")) {
                    bioNum++;
                }
            }
        }

        // MODIFIES: this
        // EFFECTS: show up the bar frame
        private void setUpData() {

            values[0] = mathNum;
            names[0] = "Math: " + mathNum;

            values[1] = physNum;
            names[1] = "Physics: " + physNum;

            values[2] = chemNum;
            names[2] = "Chemistry: " + chemNum;

            values[3] = bioNum;
            names[3] = "Biology: " + bioNum;



        }

    }

    // Listener for save button
    class SaveListener implements ActionListener {

        private JFrame outMostFrame;
        private JPanel outMostPanel;

        private JButton yesButton;
        private JButton noButton;

        private String yesString = "Yes";
        private String noString = "No";

        JLabel question;


        // MODIFIES: this
        // EFFECTS: Save the system's current state
        @Override
        public void actionPerformed(ActionEvent e) {

            outMostPanel = new JPanel(new BorderLayout());
            outMostFrame = new JFrame();

            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane,
                    BoxLayout.LINE_AXIS));

            initThisButton();

            buttonPane.add(yesButton);
            buttonPane.add(Box.createHorizontalStrut(10));
            buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
            buttonPane.add(Box.createHorizontalStrut(10));

            buttonPane.add(noButton);

            buttonPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

            question = new JLabel("Do you want to save your work?");

            outMostPanel.add(question,BorderLayout.CENTER);
            outMostPanel.add(buttonPane,BorderLayout.SOUTH);

            outMostFrame.setContentPane(outMostPanel);
            outMostFrame.setTitle("Save");
            outMostFrame.setSize(300, 200);

            outMostFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            outMostFrame.setVisible(true);


        }

        // MODIFIES: this
        // EFFECTS: initialize the current buttons
        private void initThisButton() {
            yesButton = new JButton(yesString);
            noButton = new JButton(noString);

            yesButton.setActionCommand(yesString);
            yesButton.addActionListener(new YesListener());

            noButton.setActionCommand(noString);
            noButton.addActionListener(new NoListener());
        }


        // Listener for the yes button
        class YesListener implements ActionListener {

            // MODIFIES: this
            // EFFECTS: save the system
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    jsonWriter.open();
                    jsonWriter.write(education);
                    jsonWriter.close();
                    question.setText("Saved " + education.getName() + " to " + JSON_STORE);
                    showImage();
                } catch (FileNotFoundException ioe) {
                    question.setText("Unable to write to file: " + JSON_STORE);
                }
            }

            // MODIFIES: this
            // EFFECTS: pop up a check mark image
            private void showImage() {
                JFrame frame = new JFrame("Saved!");
                frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                try {
                    ImageIcon image = new ImageIcon("myimage.png");
                    JLabel displayField = new JLabel(image);
                    frame.add(displayField);
                } catch (Exception exception) {
                    System.out.println("Image not found!");
                }

                frame.setSize(300,300);
                frame.setVisible(true);
            }
        }

        // Listener for the no button
        class NoListener implements ActionListener {

            // MODIFIES: this
            // EFFECTS: do not save the system.
            @Override
            public void actionPerformed(ActionEvent e) {
                outMostFrame.setVisible(false);
            }
        }
    }

    // Listener for the load button
    class LoadListener implements ActionListener {

        private JFrame outMostFrame;
        private JPanel outMostPanel;

        private JButton yesButton;
        private JButton noButton;

        private String yesString = "Yes";
        private String noString = "No";

        JLabel question;

        // MODIFIES: this
        // EFFECTS: load the system from last time
        @Override
        public void actionPerformed(ActionEvent e) {

            outMostPanel = new JPanel(new BorderLayout());
            outMostFrame = new JFrame();

            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new BoxLayout(buttonPane,
                    BoxLayout.LINE_AXIS));

            initThisButton();

            buttonPane.add(yesButton);
            buttonPane.add(Box.createHorizontalStrut(10));
            buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
            buttonPane.add(Box.createHorizontalStrut(10));

            buttonPane.add(noButton);

            buttonPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

            question = new JLabel("Do you want to start from the last time?");

            outMostPanel.add(question,BorderLayout.CENTER);
            outMostPanel.add(buttonPane,BorderLayout.SOUTH);

            outMostFrame.setContentPane(outMostPanel);
            outMostFrame.setTitle("Load");
            outMostFrame.setSize(300, 200);

            outMostFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            outMostFrame.setVisible(true);



        }

        // Listener for the yes button
        class YesListener implements ActionListener {

            // MODIFIES: this
            // EFFECTS: load the history
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    education = jsonReader.read();
                    ArrayList<Volunteer> volunteers = education.getVolunteerList();
                    for (Volunteer v : volunteers) {
                        String name = v.getName();
                        listModel.addElement(name);
                    }
                    outMostFrame.setVisible(false);
                    EventLog.getInstance().clear();



                } catch (IOException ioe) {
                    question.setText("Unable to read from file: " + JSON_STORE);
                }

            }
        }

        // Listener for the no button
        class NoListener implements ActionListener {

            // MODIFIES: this
            // EFFECTS: do not load the history
            @Override
            public void actionPerformed(ActionEvent e) {

                outMostFrame.setVisible(false);

            }
        }

        // MODIFIES: this
        // EFFECTS: initialize the current button
        private void initThisButton() {
            yesButton = new JButton(yesString);
            noButton = new JButton(noString);

            yesButton.setActionCommand(yesString);
            yesButton.addActionListener(new YesListener());

            noButton.setActionCommand(noString);
            noButton.addActionListener(new NoListener());
        }
    }

    public static void main(String[] args) {
        new ClubAppGUI();
    }


}
