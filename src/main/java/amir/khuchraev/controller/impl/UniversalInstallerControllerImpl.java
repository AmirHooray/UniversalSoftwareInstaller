package amir.khuchraev.controller.impl;

import amir.khuchraev.controller.UniversalInstallerController;
import amir.khuchraev.managers.RegistrationManagerInWindowsRegistry;
import amir.khuchraev.ui.View;

import java.io.File;

public class UniversalInstallerControllerImpl implements UniversalInstallerController {

    private String setupProgramPath = "";
    private String homeProgramPath = "";
    private View view;
    private RegistrationManagerInWindowsRegistry registrationManager;

    public UniversalInstallerControllerImpl(RegistrationManagerInWindowsRegistry registrationManager) {
        this.registrationManager = registrationManager;
    }

    @Override
    public void attacheView(View view) {
        this.view = view;
    }

    @Override
    public void setSetupProgramPath(String setupProgramPath) {
        this.setupProgramPath = setupProgramPath;
    }

    @Override
    public void setHomeProgramPath(String homeProgramPath) {
        this.homeProgramPath = homeProgramPath;
    }

    @Override
    public void clickProgramSetup() {
        if (setupProgramPath.isEmpty()) {
            view.showDialogSetupProgramPathEmpty();
            return;
        }
        if (homeProgramPath.isEmpty()) {
            view.showDialogHomeProgramPathEmpty();
            return;
        }
        view.showDialogSetupProgramStart();
        startProgramSetup();
    }

    private void startProgramSetup() {
        File choosenFile = new File(setupProgramPath);
        File chosenPath = new File(homeProgramPath);
        if (!chosenPath.isDirectory()) {
            view.showErrorDialogChosenPathNotCorrect();
            return;
        }
        if (choosenFile.isDirectory()) {
            directorySelected();
        } else {
            String fileName = choosenFile.getName().toLowerCase();
            if (fileName.endsWith(".zip")) {
                zipSelected();
            } else if (fileName.endsWith(".exe")) {
                exeSelected();
            } else {
                view.showDialogErrorFileSelected();
            }
        }
    }

    private void directorySelected() {

    }

    private void zipSelected() {

    }

    private void exeSelected() {

    }
}
