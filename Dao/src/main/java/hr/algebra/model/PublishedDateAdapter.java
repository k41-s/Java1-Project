/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.model;

import java.time.OffsetDateTime;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 *
 * @author dnlbe
 */
public class PublishedDateAdapter extends XmlAdapter<String, OffsetDateTime>{

    @Override
    public OffsetDateTime unmarshal(String text) throws Exception {
        return OffsetDateTime.parse(text, Event.DATE_FORMATTER);
    }

    @Override
    public String marshal(OffsetDateTime date) throws Exception {
        return date.format(Event.DATE_FORMATTER);
    }
    
}
