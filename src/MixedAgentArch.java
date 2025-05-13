import java.util.HashSet;
import java.util.Set;

import jason.architecture.AgArch;
import jason.asSemantics.ActionExec;
import jason.infra.local.LocalAgArch;
import jason.infra.local.LocalEnvironment;

import jason.environment.Environment;

import src.env.fac1env;

public class MixedAgentArch extends AgArch {

    Set<String> jasonEnvActions = new HashSet<String>();

    @Override
    public void init() throws Exception {
        // Add all actions that should be sent to the Jason environment
        jasonEnvActions.add("pick_part");
        jasonEnvActions.add("release_part");
        jasonEnvActions.add("hold_part");
        jasonEnvActions.add("lock_area");
        jasonEnvActions.add("unlock_area");
        jasonEnvActions.add("unhold_part");
        jasonEnvActions.add("move_towards");
        jasonEnvActions.add("weld");
    }

    /** Send specific actions to Jason environment */
    @Override
    public void act(ActionExec act) {
        if (jasonEnvActions.contains(act.getActionTerm().getFunctor())) {
            getCentArch().act(act); // uses the local ag arch
        } else {
            super.act(act); // uses cartago ag arch
        }
    }

    protected LocalAgArch getCentArch() {
        AgArch arch = getTS().getAgArch().getFirstAgArch();
        while (arch != null) {
            if (arch instanceof LocalAgArch) {
                return (LocalAgArch)arch;
            }
            arch = arch.getNextAgArch();
        }
        return null;
    }
}