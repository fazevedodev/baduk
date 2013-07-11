/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Image;

/**
 *
 * @author Fabiano
 */
public interface SettingsFrameListener {
    public void onSettingsSaved(Image boardTexture, int tabIndex, boolean useTextures, boolean useCoordinates);
}
