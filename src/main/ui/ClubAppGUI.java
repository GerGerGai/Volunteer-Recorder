package ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import model.*;
import persistance.JsonReader;
import persistance.JsonWriter;


public class ClubAppGUI extends JFrame
                        implements ListSelectionListener {

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
        setVisible(true);


    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

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

    private void initVolunteerList() {

        volunteerList = new JList(listModel);
        volunteerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        volunteerList.setSelectedIndex(0);
        volunteerList.addListSelectionListener(this);
        volunteerList.setVisibleRowCount(5);

    }

    class AddListener implements ActionListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        private JLabel label1 = new JLabel("id");
        private JTextField field1 = new JTextField(20);

        private JLabel label2 = new JLabel("name");
        private JTextField field2 = new JTextField(20);

        private JLabel label3 = new JLabel("major");
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
        public void actionPerformed(ActionEvent e) {
//            String name = employeeName.getText();
//
//            //User didn't type in a unique name...
//            if (name.equals("") || alreadyInList(name)) {
//                Toolkit.getDefaultToolkit().beep();
//                employeeName.requestFocusInWindow();
//                employeeName.selectAll();
//                return;
//            }

            setUpWindow();


        }

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

        class AddToListListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                int id = Integer.parseInt(label1.getText().toString());
                int year = Integer.parseInt(label4.getText().toString());
                String name = label2.getText();
                String major = label3.getText();
                Volunteer newVolunteer = new UniversityVolunteer(name,major,
                        id,year);
                education.addVolunteers(newVolunteer);



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



        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }


    }

    class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = volunteerList.getSelectedIndex();
            ArrayList<Volunteer> volunteers = education.getVolunteerList();
            volunteers.remove(index);
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

    class InfoListener implements ActionListener {

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
//            infoArea.append("ID: " + Integer.toString(id));
//            infoArea.append("Major: " + major);
//            infoArea.append("Year: " + Integer.toString(year));


        }
    }

    class BarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class SaveListener implements ActionListener {

        private JFrame outMostFrame;
        private JPanel outMostPanel;

        private JButton yesButton;
        private JButton noButton;

        private String yesString = "Yes";
        private String noString = "No";

        JLabel question;


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

        private void initThisButton() {
            yesButton = new JButton(yesString);
            noButton = new JButton(noString);

            yesButton.setActionCommand(yesString);
            yesButton.addActionListener(new YesListener());

            noButton.setActionCommand(noString);
            noButton.addActionListener(new NoListener());
        }

        class YesListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    jsonWriter.open();
                    jsonWriter.write(education);
                    jsonWriter.close();
                    question.setText("Saved " + education.getName() + " to " + JSON_STORE);
                } catch (FileNotFoundException ioe) {
                    question.setText("Unable to write to file: " + JSON_STORE);
                }
            }
        }

        class NoListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                outMostFrame.setVisible(false);
            }
        }
    }

    class LoadListener implements ActionListener {

        private JFrame outMostFrame;
        private JPanel outMostPanel;

        private JButton yesButton;
        private JButton noButton;

        private String yesString = "Yes";
        private String noString = "No";

        JLabel question;

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

        class YesListener implements ActionListener {

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


                } catch (IOException ioe) {
                    question.setText("Unable to read from file: " + JSON_STORE);
                }

            }
        }

        class NoListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                outMostFrame.setVisible(false);

            }
        }

        private void initThisButton() {
            yesButton = new JButton(yesString);
            noButton = new JButton(noString);

            yesButton.setActionCommand(yesString);
            yesButton.addActionListener(new YesListener());

            noButton.setActionCommand(noString);
            noButton.addActionListener(new NoListener());
        }
    }

//    private static void createAndShowGUI() {
//        //Create and set up the window.
//        JFrame frame = new JFrame("ClubAppForVolunteers");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //Create and set up the content pane.
//        JComponent newContentPane = new ClubAppGUI();
//        newContentPane.setOpaque(true); //content panes must be opaque
//        frame.setContentPane(newContentPane);
//
//        //Display the window.
//        frame.pack();
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
}
