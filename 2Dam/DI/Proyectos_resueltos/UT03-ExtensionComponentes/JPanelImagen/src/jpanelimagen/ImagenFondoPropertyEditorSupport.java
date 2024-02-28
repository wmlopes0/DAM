package jpanelimagen;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Walter
 */
public class ImagenFondoPropertyEditorSupport extends PropertyEditorSupport {

    private ImagenFondoPanel imagenFondoPanel = new ImagenFondoPanel();

    @Override
    public boolean supportsCustomEditor() {
        //Si tiene un editor de propiedades personalizado
        return true;
    }

    @Override
    public Component getCustomEditor() {
        //Nos pide el panel
        return imagenFondoPanel;
    }

    @Override
    public String getJavaInitializationString() {
        return super.getJavaInitializationString();
    }

    @Override
    public Object getValue() {
        //Devuelve el valor (gracias al método que creamos para devolver nuestra ImagenFondo)
        return imagenFondoPanel.getSelectedValue();
    }

}
