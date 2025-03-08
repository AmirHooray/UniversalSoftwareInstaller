package amir.khuchraev.controller.impl;

import amir.khuchraev.controller.UniversalInstallerController;
import amir.khuchraev.managers.FileManager;
import amir.khuchraev.managers.RegistrationManagerInWindowsRegistry;
import amir.khuchraev.ui.View;

import java.io.File;

public class UniversalInstallerControllerImpl implements UniversalInstallerController {

    private String setupProgramPath = "";
    private String homeProgramPath = "";
    private View view;
    private RegistrationManagerInWindowsRegistry registrationManager;
    private FileManager fileManager;

    public UniversalInstallerControllerImpl(
            RegistrationManagerInWindowsRegistry registrationManager,
            FileManager fileManager
    ) {
        this.registrationManager = registrationManager;
        this.fileManager = fileManager;
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
            directorySelected(choosenFile);
        } else {
            String fileName = choosenFile.getName().toLowerCase();
            if (fileName.endsWith(".zip")) {
                zipSelected(choosenFile);
            } else if (fileName.endsWith(".exe")) {
                exeSelected(choosenFile);
            } else {
                view.showDialogErrorFileSelected();
            }
        }
    }

    private void directorySelected(File choosenFile) {
        File exeFile = null;
        File[] files = choosenFile.listFiles();
        if (files == null) {
            view.showDialogErrorProgramNotFound();
            return;
        }
        for (File file : files) {
            if (file.getName().toLowerCase().endsWith(".exe")) {
                exeFile = file;
            }
        }
        if (exeFile != null) {
            copeFilesAndRegistryExe(exeFile, choosenFile);
        } else {
            view.showDialogErrorProgramNotFound();
        }
    }

    private void zipSelected(File choosenFile) {

    }

    private void exeSelected(File choosenFile) {

    }

    private void copeFilesAndRegistryExe(File exeSelected, File rootDirectory) {
        fileManager.copyFilesFromTo(rootDirectory, homeProgramPath);
    }
}
