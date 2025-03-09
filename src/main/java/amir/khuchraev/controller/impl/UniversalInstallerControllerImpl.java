package amir.khuchraev.controller.impl;

import amir.khuchraev.controller.UniversalInstallerController;
import amir.khuchraev.managers.FileManager;
import amir.khuchraev.managers.MetaExeManager;
import amir.khuchraev.managers.RegistrationManagerInWindowsRegistry;
import amir.khuchraev.models.ProgramRegistrationInWindowsModel;
import amir.khuchraev.ui.View;

import java.io.File;
import java.io.IOException;

public class UniversalInstallerControllerImpl implements UniversalInstallerController {

    private String setupProgramPath = "";
    private String homeProgramPath = "";
    private View view;
    final private RegistrationManagerInWindowsRegistry registrationManager;
    final private MetaExeManager metaExeManager;
    final private FileManager fileManager;

    public UniversalInstallerControllerImpl(
            RegistrationManagerInWindowsRegistry registrationManager,
            FileManager fileManager,
            MetaExeManager metaExeManager
    ) {
        this.registrationManager = registrationManager;
        this.fileManager = fileManager;
        this.metaExeManager = metaExeManager;
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
        try {
            fileManager.copyFilesFromTo(rootDirectory, homeProgramPath);
            ProgramRegistrationInWindowsModel model = metaExeManager.getProgramMetaInfo(
                    new File(homeProgramPath + "\\" + exeSelected.getName()),
                    new File(homeProgramPath)
            );
            System.out.println("appKeyName " + model.getAppKeyName());
            System.out.println("displayName " + model.getDisplayName());
            System.out.println("displayVersion " + model.getDisplayVersion());
            System.out.println("publisher " + model.getPublisher());
            System.out.println("installLocation " + model.getInstallLocation());
            System.out.println("displayIcon " + model.getDisplayIcon());
            view.showDialogSetupProgramFinished();
        } catch (IOException e) {
            view.showErrorDialogCopyFiles();
        }
    }
}
