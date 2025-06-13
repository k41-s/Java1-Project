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
public class VenueTransferable implements Transferable {

    public static final DataFlavor VENUE_FLAVOR = new DataFlavor(Venue.class, "Venue");
    private static final DataFlavor[] SUPPORTED_FLAVORS = {VENUE_FLAVOR};

    private final Venue venue;

    public VenueTransferable(Venue venue) {
        this.venue = venue;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return SUPPORTED_FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return VENUE_FLAVOR.equals(flavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(flavor)) {
            return venue;
        }
        throw new UnsupportedFlavorException(flavor);
    }

}
