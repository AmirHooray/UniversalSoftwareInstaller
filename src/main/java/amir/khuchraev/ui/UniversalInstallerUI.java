package amir.khuchraev.ui;

import amir.khuchraev.controller.UniversalInstallerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static amir.khuchraev.consts.StringConst.*;

public class UniversalInstallerUI extends JFrame implements View {

    private JTextField installProgramField;
    private JTextField installFolderField;
    private JCheckBox shortcutCheckBox;
    private UniversalInstallerController controller;

    public UniversalInstallerUI(UniversalInstallerController controller) {
        this.controller = controller;
        this.controller.attacheView(this);
        setTitle(PROGRAM_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 270);
        setResizable(false);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        createTitle(mainPanel);
        createChooseProgramInstalledPanel(mainPanel);
        createChoosePathInstalled(mainPanel);
        createShortcutCheckBox(mainPanel);
        createButtonSetupProgram(mainPanel);

        add(mainPanel);
    }


    private void createTitle(JPanel mainPanel) {
        JLabel titleLabel = new JLabel(MAIN_TITLE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(20));
    }

    private void createChooseProgramInstalledPanel(JPanel mainPanel) {
        JPanel installProgramPanel = new JPanel(new BorderLayout(5, 5));
        JLabel installProgramLabel = new JLabel(CHOOSE_TITLE);
        installProgramField = new JTextField();
        JButton installProgramBrowseButton = new JButton(DISCOVER_TITLE);
        installProgramBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(UniversalInstallerUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String absolutePath = fileChooser.getSelectedFile().getAbsolutePath();
                    installProgramField.setText(absolutePath);
                    controller.setSetupProgramPath(absolutePath);
                }
            }
        });
        installProgramPanel.add(installProgramLabel, BorderLayout.WEST);
        installProgramPanel.add(installProgramField, BorderLayout.CENTER);
        installProgramPanel.add(installProgramBrowseButton, BorderLayout.EAST);
        mainPanel.add(installProgramPanel);
        mainPanel.add(Box.createVerticalStrut(10));
    }

    private void createChoosePathInstalled(JPanel mainPanel) {
        JPanel folderPanel = new JPanel(new BorderLayout(5, 5));
        JLabel folderLabel = new JLabel(PATH_TITLE);
        installFolderField = new JTextField();
        JButton folderBrowseButton = new JButton(DISCOVER_TITLE);
        folderBrowseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser folderChooser = new JFileChooser();
                folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int result = folderChooser.showOpenDialog(UniversalInstallerUI.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String absolutePath = folderChooser.getSelectedFile().getAbsolutePath();
                    installFolderField.setText(absolutePath);
                    controller.setHomeProgramPath(absolutePath);
                }
            }
        });
        folderPanel.add(folderLabel, BorderLayout.WEST);
        folderPanel.add(installFolderField, BorderLayout.CENTER);
        folderPanel.add(folderBrowseButton, BorderLayout.EAST);
        mainPanel.add(folderPanel);
        mainPanel.add(Box.createVerticalStrut(10));
    }

    private void createShortcutCheckBox(JPanel mainPanel) {
        shortcutCheckBox = new JCheckBox(CREATE_SHORTCUT_TITLE);
        shortcutCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(shortcutCheckBox);
        mainPanel.add(Box.createVerticalStrut(20));
    }

    private void createButtonSetupProgram(JPanel mainPanel) {
        JButton setupButton = new JButton(TITLE_START_SETUP_PROGRAM);
        setupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        setupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clickProgramSetup();
            }
        });
        mainPanel.add(setupButton);
        mainPanel.add(Box.createVerticalStrut(10));
    }

    @Override
    public void showDialogSetupProgramPathEmpty() {
        JOptionPane.showMessageDialog(UniversalInstallerUI.this,
                ERROR_CHOOSE_PROGRAM_TITLE,
                ERROR_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showDialogHomeProgramPathEmpty() {
        JOptionPane.showMessageDialog(UniversalInstallerUI.this,
                ERROR_CHOOSE_PATH_TITLE,
                ERROR_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showDialogSetupProgramStart() {
        JOptionPane.showMessageDialog(UniversalInstallerUI.this,
                START_TITLE,
                "", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showDialogErrorFileSelected() {
        JOptionPane.showMessageDialog(UniversalInstallerUI.this,
                ERROR_FILE_SELECTED,
                ERROR_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void showErrorDialogChosenPathNotCorrect() {
        JOptionPane.showMessageDialog(UniversalInstallerUI.this,
                ERROR_TITLE_CHOSEN_PATH,
                ERROR_TITLE, JOptionPane.INFORMATION_MESSAGE);
    }
}