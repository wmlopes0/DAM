package ejemploassertj;

import com.clase.ejemploassertj.AppDemo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.core.matcher.JButtonMatcher.withName;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author wmartinl01
 */
public class TestApp {

    private FrameFixture window;

    /**
     * Constructor de clase
     */
    public TestApp() {
    }

    /**
     * Fuerza a una prueba a fallar si el acceso a los componentes de la GUI no
     * se realiza en el EDT (Event Dispatch Thread)
     */
    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    /**
     * Inicializa los dispositivos de prueba, se ejecuta cada vez que se ejecute
     * un método de prueba
     */
    @Before
    public void setUp() {
        AppDemo frame = GuiActionRunner.execute(() -> new AppDemo());
        window = new FrameFixture(frame);
        window.show();
    }

    /**
     * Limpia los recursos utilizados después de ejecutar cada método de prueba
     * y libera el bloqueo de teclado y moyse para la siguiente prueba
     */
    @After
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void ConversionOctal() {
        window.textBox("numero").enterText("45");
        window.comboBox("opcion").selectItem("Octal");
        window.button(withName("convertir")).click();
        //realiza la comparación de resultados
        assertThat(window.textBox("resultado").text()).isEqualTo("55");
    }

    @Test
    public void conversionHexadecimal() {
        window.textBox("numero").enterText("26");
        window.comboBox("opcion").selectItem("Hexadecimal");
        window.button(withName("convertir")).click();
        //realiza la comparación de resultados
        assertThat(window.textBox("resultado").text()).isEqualTo("1A");
    }

    @Test
    public void numeroFueraDerango() {
        window.textBox("numero").enterText("226");
        window.comboBox("opcion").selectItem("Hexadecimal");
        window.button(withName("convertir")).click();
        //cierra la ventana de alerta
        window.dialog().button().click();
        //verifica que controles se reinicien a cero "0"
        assertThat(window.textBox("numero").text()).isEqualTo("0");
        assertThat(window.textBox("resultado").text()).isEqualTo("0");
    }

    @Test
    public void valorNumericoNoValido() {
        window.textBox("numero").enterText("");
        window.comboBox("opcion").selectItem("Octal");
        window.button("convertir").click();
        //Cierra ventana de alerta
        window.dialog().button(withText("No lo vuelvo hacer")).click();
        //se comprueba que controles esten deshabilitados
        window.textBox("numero").requireDisabled();
        window.comboBox("opcion").requireDisabled();
        window.button("convertir").requireDisabled();
    }

    @Test
    public void errorCritico() {
        window.textBox("numero").enterText("98899979941993239211992309990991");
        window.comboBox("opcion").selectItem("Hexadecimal");
        window.button("convertir").click();
        //cierra ventana de alerta
        window.dialog().button().click();
        //verifica que textbox se haya reiniciado a cero "0"
        assertThat(window.textBox("numero").text()).isEqualTo("0");
    }

}
