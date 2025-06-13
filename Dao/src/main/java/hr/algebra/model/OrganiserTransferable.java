/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author kaish
 */
public class OrganiserTransferable implements Transferable {

    public static final DataFlavor ORGANISER_FLAVOR = new DataFlavor(Organiser.class, "Organiser");
    private static final DataFlavor[] SUPPORTED_FLAVORS = {ORGANISER_FLAVOR};

    private final Organiser organiser;

    public OrganiserTransferable(Organiser organiser) {
        this.organiser = organiser;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return ORGANISER_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor)) {
            return organiser;
        }
        throw new UnsupportedFlavorException(flavor);
    }

}
