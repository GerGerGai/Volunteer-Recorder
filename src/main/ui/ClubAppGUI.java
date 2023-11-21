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

        infoArea = new JTextArea(200,300);

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
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand(addString);
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

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

    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

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

            int index = volunteerList.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

//            listModel.insertElementAt(employeeName.getText(), index);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
//            employeeName.requestFocusInWindow();
//            employeeName.setText("");

            //Select the new item and make it visible.
            volunteerList.setSelectedIndex(index);
            volunteerList.ensureIndexIsVisible(index);
        }

        @Override
        public void insertUpdate(DocumentEvent e) {

            enableButton();
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }


        @Override
        public void removeUpdate(DocumentEvent e) {

        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    class RemoveListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = volunteerList.getSelectedIndex();
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

        }
    }

    class BarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class SaveListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

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
