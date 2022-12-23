import javax.swing.*;
import java.awt.event.*;
import java.io.*;

/**
 * Survey GUI that allows user for file input text field and output file by filtering a survey
 */
public class SurveyGUI extends JFrame {

    /**
     * File enter textfield reads in SurveyResult file
     */
    private JTextField fileField = new JTextField();

    /**
     * File output textfield writes results to this file
     */
    private JTextField outputField = new JTextField();

    /**
     * Used to set up my GUI
     */
    private final JFrame frame;

    /**
     * Textfield that changes frequency count when filter is used for rating 1
     */
    private JTextField oneFreq = new JTextField();

    /**
     * Textfield that changes frequency count when filter is used for rating 2
     */
    private JTextField twoFreq = new JTextField();

    /**
     * Textfield that changes frequency count when filter is used for rating 3
     */
    private JTextField threeFreq = new JTextField();

    /**
     * Textfield that changes frequency count when filter is used for rating 4
     */
    private JTextField fourFreq = new JTextField();

    /**
     * Textfield that changes frequency count when filter is used for rating 5
     */
    private JTextField fiveFreq = new JTextField();

    /**
     * Groups each of the demographic buttons
     */
    private ButtonGroup filterOptions = new ButtonGroup();

    /**
     * gender button
     */
    private JRadioButton gender;

    /**
     * age group button
     */
    private JRadioButton age;

    /**
     * state of residency button
     */
    private JRadioButton state;
    /**
     * checks csv comma value 5 rating
     */
    private String rating;

    /**
     * checks csv comma value 4 product
     */
    private String product;

    /**
     * counts rating 1 frequencies
     */
    private int oneCount = 0;

    /**
     * counts rating 2 frequencies
     */
    private int twoCount = 0;

    /**
     * counts rating 3 frequencies
     */
    private int threeCount = 0;

    /**
     * count rating 4 frequencies
     */
    private int fourCount = 0;

    /**
     * count rating 5 frequencies
     */
    private int fiveCount = 0;

    /**
     * combo box for genders
     */
    private JComboBox<String> genderChoose;

    /**
     * list of genders for gender choose combo box
     */
    private final String[] genderList = {"Male", "Female"};

    /**
     * combo box for age groups
     */
    private JComboBox<String> ageChoose;

    /**
     * age group list for age choose combo box
     */
    private final String[] ageGroupList = {"18-24", "25-34", "35-44", "45-54", "55-64", "65-74", "75+"};

    /**
     * combo box for state of residency
     */
    private JComboBox<String> stateChoose;

    /**
     * state residence list used to be put in state Choose combox
     */
    private final String[] resList = {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI",
            "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH",
            "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
    /**
     * combo box for product
     */
    private JComboBox<String> itemChoose;

    /**
     * product item list
     */
    private final String[] itemList = {"calculator", "cell phone", "headphones", "toothpaste", "USB drive"};

    /**
     * Initializes GUI frame with an input and output file textfield, a combo box to choose each product,
     * 3 radio buttons that open up each demographic combo box filter.
     * Also has rating frequencies when selections are made
     */
    public SurveyGUI() {
        frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setTitle("Survey GUI");
        frame.setSize(675, 355);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        JLabel welcome = new JLabel("Survey Processing Filter");
        welcome.setBounds(10, 10, 190, 30);
        panel.add(welcome);

        JLabel file = new JLabel("Enter File Path: ");
        file.setBounds(15, 45, 250, 30);
        panel.add(file);

        fileField.setBounds(130, 45, 300, 30);
        panel.add(fileField);

        JLabel output = new JLabel("Enter Output File:");
        output.setBounds(15, 85, 250, 30);
        panel.add(output);

        outputField.setBounds(145, 85, 300, 30);
        panel.add(outputField);

        JLabel item = new JLabel("Enter Item to Filter: ");
        item.setBounds(15, 125, 200, 30);
        panel.add(item);

        itemChoose = new JComboBox<>(itemList);
        itemChoose.setBounds(170, 125, 150, 30);
        panel.add(itemChoose);

        JLabel filter = new JLabel("Enter Demographic to Filter: ");
        filter.setBounds(15, 155, 250, 30);
        panel.add(filter);

        gender = new JRadioButton("Gender");
        gender.setBounds(15, 185, 120, 30);
        panel.add(gender);

        genderChoose = new JComboBox<String>(genderList);
        genderChoose.setBounds(15, 220, 90, 30);
        panel.add(genderChoose);
        genderChoose.setVisible(false);

        age = new JRadioButton("Age Group");
        age.setBounds(135, 185, 100, 30);
        panel.add(age);

        ageChoose = new JComboBox<>(ageGroupList);
        ageChoose.setBounds(135, 220, 90, 30);
        panel.add(ageChoose);
        ageChoose.setVisible(false);

        state = new JRadioButton("State of Residence");
        state.setBounds(265, 185, 200, 30);
        panel.add(state);

        stateChoose = new JComboBox<>(resList);
        stateChoose.setBounds(265, 220, 90, 30);
        panel.add(stateChoose);
        stateChoose.setVisible(false);

        filterOptions.add(gender);
        filterOptions.add(age);
        filterOptions.add(state);
        filterOptions.isSelected(null);

        JLabel rating = new JLabel("Rating ");
        rating.setBounds(490, 90, 100, 30);
        panel.add(rating);

        JLabel oneStar = new JLabel("1");
        oneStar.setBounds(520, 120, 40, 30);
        panel.add(oneStar);

        JLabel twoStar = new JLabel("2");
        twoStar.setBounds(520, 150, 40, 30);
        panel.add(twoStar);

        JLabel threeStar = new JLabel("3");
        threeStar.setBounds(520, 180, 40, 30);
        panel.add(threeStar);

        JLabel fourStar = new JLabel("4");
        fourStar.setBounds(520, 210, 40, 30);
        panel.add(fourStar);

        JLabel fiveStar = new JLabel("5");
        fiveStar.setBounds(520, 240, 40, 30);
        panel.add(fiveStar);

        JLabel freq = new JLabel("Frequency");
        freq.setBounds(560, 90, 100, 30);
        panel.add(freq);

        oneFreq.setBounds(580, 120, 40, 30);
        oneFreq.setText("0");
        panel.add(oneFreq);

        twoFreq.setBounds(580, 150, 40, 30);
        twoFreq.setText("0");
        panel.add(twoFreq);

        threeFreq.setBounds(580, 180, 40, 30);
        threeFreq.setText("0");
        panel.add(threeFreq);

        fourFreq.setBounds(580, 210, 40, 30);
        fourFreq.setText("0");
        panel.add(fourFreq);

        fiveFreq.setBounds(580, 240, 40, 30);
        fiveFreq.setText("0");
        panel.add(fiveFreq);

        genderButton genderClick = new genderButton();
        gender.addActionListener(genderClick);

        ageButton ageClick = new ageButton();
        age.addActionListener(ageClick);

        stateButton stateClick = new stateButton();
        state.addActionListener(stateClick);

        boxSwitch switchCombo = new boxSwitch();
        genderChoose.addItemListener(switchCombo);
        ageChoose.addItemListener(switchCombo);
        stateChoose.addItemListener(switchCombo);

        demoFilter selBox = new demoFilter();
        genderChoose.addItemListener(selBox);
        ageChoose.addItemListener(selBox);
        stateChoose.addItemListener(selBox);

        frame.setVisible(true);
    }

    /**
     * used to reset frequnecy count and reset textfields in GUI
     */
    public void reset() {
        oneCount = 0;
        twoCount = 0;
        threeCount = 0;
        fourCount = 0;
        fiveCount = 0;
        oneFreq.setText("0");
        twoFreq.setText("0");
        threeFreq.setText("0");
        fourFreq.setText("0");
        fiveFreq.setText("0");
    }

    /**
     * detects if a change is made in the combo box and resets the values
     */
    public class boxSwitch implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent itemEvent) {
            if (gender.isSelected()) {
                if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
                    reset();
                }
            } else if (age.isSelected()) {
                if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
                    reset();
                } else if (state.isSelected()) {
                    if (itemEvent.getStateChange() == ItemEvent.DESELECTED) {
                        reset();
                    }
                }
            }
        }
    }

    /**
     * when selected will show gender combo box and will hide both combo
     * boxes if previously selected
     */
    public class genderButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            reset();
            genderChoose.setVisible(gender.isSelected());
            ageChoose.setVisible(false);
            stateChoose.setVisible(false);

        }
    }

    /**
     * when selected will show age group combo box and will hide both combo
     * boxes if previously selected
     */
    public class ageButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            reset();
            ageChoose.setVisible(age.isSelected());
            genderChoose.setVisible(false);
            stateChoose.setVisible(false);
        }
    }

    /**
     * when selected will show age group combo box and will hide both combo
     * boxes if previously selected
     */
    public class stateButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            reset();
            stateChoose.setVisible(state.isSelected());
            genderChoose.setVisible(false);
            ageChoose.setVisible(false);
        }
    }


    /**
     * Filter method will read in the fileField and outputField, the buffered reader will read in each line.
     * Each comma seperated value is stored in an array value. Next the program checks what product item is selected
     * and then checks to see which filter is being applied. Then the filter will return the number of frequencies
     */
    public class demoFilter implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent event) {
            String line = "";
            try (PrintWriter writer = new PrintWriter(outputField.getText())) {
                BufferedReader br = new BufferedReader(new FileReader(fileField.getText()));
                StringBuilder sb = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    String genderSel = values[0];
                    String ageSel = values[1];
                    String stateSel = values[2];
                    product = values[3];
                    rating = values[4];
                    String newLine = (values[0] + ", " + values[1] + ", " + values[2] + ", " + values[3]
                                + ", " + values[4]);
                    if (product.equals(itemChoose.getSelectedItem())) {
                        if (gender.isSelected() && genderSel.equals(genderChoose.getSelectedItem())
                        ) {
                            if (rating.equals("1")) {
                                oneCount++;
                                oneFreq.setText(String.valueOf(oneCount));
                            } else if (rating.equals("2")) {
                                twoCount++;
                                //sb.append("2: " + twoCount +"\n");
                                twoFreq.setText(String.valueOf(twoCount));
                            } else if (rating.equals("3")) {
                                threeCount++;
                                threeFreq.setText(String.valueOf(threeCount));
                            } else if (rating.equals("4")) {
                                fourCount++;
                                fourFreq.setText(String.valueOf(fourCount));
                            } else {
                                fiveCount++;
                                fiveFreq.setText(String.valueOf(fiveCount));
                            }
                        } else if (age.isSelected() && ageSel.equals(ageChoose.getSelectedItem())
                        ) {
                            if (rating.equals("1")) {
                                oneCount++;
                                oneFreq.setText(String.valueOf(oneCount));
                            } else if (rating.equals("2")) {
                                twoCount++;
                                twoFreq.setText(String.valueOf(twoCount));
                            } else if (rating.equals("3")) {
                                threeCount++;
                                threeFreq.setText(String.valueOf(threeCount));
                            } else if (rating.equals("4")) {
                                fourCount++;
                                fourFreq.setText(String.valueOf(fourCount));
                            } else {
                                fiveCount++;
                                fiveFreq.setText(String.valueOf(fiveCount));
                            }
                        } else if (state.isSelected() && stateSel.equals(stateChoose.getSelectedItem())) {
                            if (rating.equals("1")) {
                                oneCount++;
                                oneFreq.setText(String.valueOf(oneCount));
                            } else if (rating.equals("2")) {
                                twoCount++;
                                twoFreq.setText(String.valueOf(twoCount));
                            } else if (rating.equals("3")) {
                                threeCount++;
                                threeFreq.setText(String.valueOf(threeCount));
                            } else if (rating.equals("4")) {
                                fourCount++;
                                fourFreq.setText(String.valueOf(fourCount));
                            } else {
                                fiveCount++;
                                fiveFreq.setText(String.valueOf(fiveCount));
                            }
                        }
                    }
                }
                sb.append("Rating:  Frequency: \n");
                sb.append("1        " + oneCount + "\n");
                sb.append("2        " + twoCount + "\n");
                sb.append("3        " + threeCount + "\n");
                sb.append("4        " + fourCount + "\n");
                sb.append("5        " + fiveCount + "\n");
                    writer.write(sb.toString());
            } catch (IOException e) {
                    fileField.setText("Invalid File Input");
                    e.printStackTrace();
            }
        }
    }
}


