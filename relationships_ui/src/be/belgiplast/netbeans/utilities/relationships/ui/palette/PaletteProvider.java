/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.belgiplast.netbeans.utilities.relationships.ui.palette;

import be.belgiplast.utilities.relationships.EntityType;
import be.belgiplast.utilities.util.Decorator;
import java.awt.Image;
import javax.swing.Action;
import org.openide.util.datatransfer.NewType;

/**
 *
 * @author benoit
 */
public interface PaletteProvider extends Decorator{
    String getDisplayName(EntityType type);
    Image getIcon(EntityType type);
    Action[] getActions(EntityType type);
    NewType[] getNewTypes(EntityType type);
}
