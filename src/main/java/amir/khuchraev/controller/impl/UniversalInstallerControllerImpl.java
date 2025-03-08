package amir.khuchraev.controller.impl;

import amir.khuchraev.controller.UniversalInstallerController;

public class UniversalInstallerControllerImpl implements UniversalInstallerController {

    private String setupProgramPath = "";
    private String homeProgramPath = "";

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

    }
}
