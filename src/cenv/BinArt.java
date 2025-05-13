package cenv;

import cartago.*;

public class BinArt extends Artifact {

    // Note: this artifact should be responsable for drawing the bin on the canvas.
    // Instead we use this god-forsaken hack with MixedAgentArch to send the
    // status of the bin to fac1env to be drawn by the FactoryModel.

    private int binNumber;

    public void init(int binNumber) {
        this.binNumber = binNumber;
    }

    @OPERATION
    void refill_bin() {
        if (hasObsProperty("binfull")) {
            failed("Bin is already full");
        } else {
            defineObsProperty("binfull", this.binNumber);
        }
    }

    @OPERATION
    void empty() {
        if (hasObsProperty("binfull")) {
            removeObsProperty("binfull");
        } else {
            failed("Bin is already empty");
        }   
    }

}