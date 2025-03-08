package amir.khuchraev.controller;

import amir.khuchraev.ui.View;

public interface UniversalInstallerController {

    void attacheView(View view);

    void setSetupProgramPath(String setupProgramPath);

    void setHomeProgramPath(String homeProgramPath);

    void clickProgramSetup();
}
